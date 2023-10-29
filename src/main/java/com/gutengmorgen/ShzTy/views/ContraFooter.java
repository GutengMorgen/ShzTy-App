package com.gutengmorgen.ShzTy.views;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.gutengmorgen.ShzTy.services.extras.InsertDTO;
import com.gutengmorgen.ShzTy.services.extras.MainServices;
import com.gutengmorgen.ShzTy.services.extras.ReturnDTO;
import com.gutengmorgen.ShzTy.views.Components.CustomTable;
import com.gutengmorgen.ShzTy.views.Components.CustomTextField;
import com.gutengmorgen.ShzTy.views.Extras.ForGUI;
import com.gutengmorgen.ShzTy.views.Extras.MainTableModel;
import com.gutengmorgen.ShzTy.views.Extras.ModelDTO;
import com.gutengmorgen.ShzTy.views.Extras.VarType;

import lombok.Getter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class ContraFooter {
	private static ContraFooter instance;
	@Getter
	private final JPanel footerView;
	private JButton sendBtn;
	private final GridBagConstraints constraints = new GridBagConstraints();
	private int rowIndex = 0;
	private int columnIndex = 0;
	private InsertDTO subject;
	private Field[] subjectFields;
	public List<CustomTextField> customTextFields = new ArrayList<>();
	public List<CustomTextField> mandatoryFields = new ArrayList<>();

	public static void initialize(TabExtension tabExtension) {
		if (instance == null)
			instance = new ContraFooter(tabExtension);
	}

	public static ContraFooter getInstance() {
		if (instance == null)
			throw new IllegalStateException("ContraFooter must be initialized with TabExtension");
		return instance;
	}

	public ContraFooter(TabExtension tabExtension) {
		this.footerView = tabExtension.getFooterView();
	}

	@SuppressWarnings("unchecked")
	public void autoFill(ReturnDTO obj) {
		for (Field field : obj.getClass().getDeclaredFields()) {
			ForGUI forGUI = check(field);

			try {
				Object value = field.get(obj);
				JComponent component = null;

				if (value == null)
					break;

				if (forGUI.type() == VarType.SIMPLE_TEXT) {
					component = new JLabel(value.toString());
				} else if (forGUI.type() == VarType.SINGLE_OPTION) {
					DefaultComboBoxModel<String> lm = new DefaultComboBoxModel<>();
					lm.addAll((Collection<? extends String>) value);
					component = new JComboBox<>(lm);
				}

				if (component != null)
					addComponent(forGUI.name(), component);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		closeAutoFill();
	}

	// TODO: hacer que se seteen los valores de una entidad cuando se haga update
	public void autoFillInsert(InsertDTO object) {
		this.subject = object;
		this.subjectFields = object.getClass().getDeclaredFields();

		for (Field field : subjectFields) {
			ForGUI forGUI = check(field);

			CustomTextField comp = new CustomTextField(forGUI.type(), forGUI.useEntity());
			if (forGUI.mandatory())
				mandatoryFields.add(comp);

			addComponent(forGUI.name(), comp);
			customTextFields.add(comp);
		}
		addBtn(ModelDTO.CREATE);
		closeAutoFill();
	}

	private void closeAutoFill() {
		rowIndex = 0;
		columnIndex = 0;
	}

	private InsertDTO union() {
		for (int i = 0; i < customTextFields.size(); i++) {
			Object value = customTextFields.get(i).textToDataType();
			try {
				Field field = subjectFields[i];
				field.setAccessible(true);
				field.set(subject, value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return subject;
	}

	// TODO: ocurre un error en el autocomplete cuando se preciona enter al final,
	// en multioption
	public <R extends ReturnDTO> void okAction(CustomTable<R> table, ModelDTO md) {
		sendBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!mandatoryFields.isEmpty())
					for (CustomTextField field : mandatoryFields)
						if (field.getText().isBlank())
							return;

				MainTableModel<R> model = table.getCustomModel();
				MainServices<R> service = table.getService();
				if (md == ModelDTO.CREATE)
					model.insertRow(service.save(union()));
				else if (md == ModelDTO.UPDATE)
					System.out.println(union());
				model.updateRow(table.getSelectedRow(), service.update(union(),
						table.getIdEntity()));
			}
		});
	}

	private ForGUI check(Field field) {
		field.setAccessible(true);
		if (field.isAnnotationPresent(ForGUI.class))
			return field.getAnnotation(ForGUI.class);
		else
			throw new RuntimeException("Todos los parametros del objecto deben tener la anotacion ForGUI");
	}

	private void addComponent(String name, JComponent comp) {
		constraints.gridx = columnIndex;
		constraints.gridy = rowIndex;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets = new Insets(6, 10, 6, 10);
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.WEST;

		footerView.add(new JLabel(name), constraints);

		columnIndex++;
		constraints.gridx = columnIndex;
		if (comp instanceof CustomTextField)
			comp.setPreferredSize(new Dimension(150, comp.getPreferredSize().height));
		footerView.add(comp, constraints);

		if (columnIndex == 5) {
			columnIndex = 0;
			rowIndex++;
		} else {
			columnIndex++;
		}
	}

	private void addBtn(ModelDTO md) {
		constraints.gridx = columnIndex;
		constraints.gridy = rowIndex;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets = new Insets(6, 10, 6, 10);
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.WEST;

		sendBtn = new JButton("Send");
		footerView.add(sendBtn, constraints);
	}
}
