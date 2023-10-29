package com.gutengmorgen.ShzTy.views.Components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.gutengmorgen.ShzTy.services.extras.InsertDTO;
import com.gutengmorgen.ShzTy.services.extras.MainServices;
import com.gutengmorgen.ShzTy.services.extras.ReturnDTO;
import com.gutengmorgen.ShzTy.views.Extras.ForGUI;
import com.gutengmorgen.ShzTy.views.Extras.MainTableModel;
import com.gutengmorgen.ShzTy.views.Extras.VarType;
import com.gutengmorgen.ShzTy.views.Extras.ModelDTO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class CustomDialog extends JDialog {
	private static final long serialVersionUID = -2988701719467147132L;

	private final JPanel cPanel = new JPanel();
	private final GridBagConstraints constraints = new GridBagConstraints();
	private final JButton okButton;
	private int rowIndex = 0;
	private InsertDTO subject;
	private Field[] subjectFields;
	private final List<CustomTextField> customTextFields = new ArrayList<>();

	public CustomDialog(String title) {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle(title);
		setBounds(100, 100, 480, 175);

		cPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(cPanel, BorderLayout.WEST);
		GridBagLayout gbl = new GridBagLayout();
		cPanel.setLayout(gbl);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		okButton = new JButton("OK");
		buttonPane.add(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonPane.add(cancelButton);
	}

	@SuppressWarnings("unchecked")
	public void autoFill(ReturnDTO obj) {
		for (Field field : obj.getClass().getDeclaredFields()) {
			ForGUI forGUI = check(field);

			try {
				Object value = field.get(obj);
				JComponent component = null;

				if(value == null)
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

            addComponent(forGUI.name(), comp);
            customTextFields.add(comp);
        }
		closeAutoFill();
	}

	private void closeAutoFill() {
		rowIndex = 0;
		pack();
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

	// TODO: ocurre un error en el autocomplete cuando se preciona enter al final, en multioption
	public <R extends ReturnDTO> void okAction(CustomTable<R> table, ModelDTO md) {
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainTableModel<R> model = table.getCustomModel();
				MainServices<R> service = table.getService();

				if (md == ModelDTO.CREATE)
					System.out.println(union());
				else if (md == ModelDTO.UPDATE)
					System.out.println(union());
					//model.updateRow(table.getSelectedRow(), service.update(union(), table.getIdEntity()));
				dispose();
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
		constraints.gridx = 0;
		constraints.gridy = rowIndex;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.WEST;

		cPanel.add(new JLabel(name), constraints);

		constraints.gridx = 1;
		if (comp instanceof CustomTextField)
			comp.setPreferredSize(new Dimension(150, comp.getPreferredSize().height));
		cPanel.add(comp, constraints);

		rowIndex++;
	}
}
