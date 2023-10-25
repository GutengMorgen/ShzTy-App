package com.gutengmorgen.ShzTy.views.Components;

import java.awt.BorderLayout;
import java.awt.Component;
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

import com.gutengmorgen.ShzTy.services.InsertDTO;
import com.gutengmorgen.ShzTy.services.MainServices;
import com.gutengmorgen.ShzTy.services.ReturnDTO;
import com.gutengmorgen.ShzTy.views.Extras.ForGUI;
import com.gutengmorgen.ShzTy.views.Extras.ParmType;
import com.gutengmorgen.ShzTy.views.Extras.MainTableModel;
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
    private JButton okButton;
    private JButton cancelButton;
    private int rowIndex = 0;
    private InsertDTO subject;
    private Field[] subjectFields;

    public CustomDialog(String title) {
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

	cancelButton = new JButton("Cancel");
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

		if (forGUI.type() == ParmType.SIMPLE_TEXT) {
		    component = new JLabel(value.toString());
		} else if (forGUI.type() == ParmType.SINGLE_OPTION) {
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

	    JComponent comp = null;

	    if (forGUI.type() == ParmType.SINGLE_OPTION) {
		CustomTextField c = new CustomTextField(ParmType.SINGLE_OPTION, forGUI.useEntity());
		comp = c;
	    } else if (forGUI.type() == ParmType.MULTI_OPTION) {
		CustomTextField c = new CustomTextField(ParmType.MULTI_OPTION, forGUI.useEntity());
		comp = c;
	    } else if (forGUI.type() == ParmType.DATE) {
		comp = new CustomTextField(ParmType.DATE);
	    } else {
		comp = new CustomTextField(ParmType.SIMPLE_TEXT);
	    }

	    addComponent(forGUI.name(), comp);
	}
	closeAutoFill();
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

    private void closeAutoFill() {
	rowIndex = 0;
	pack();
    }

    // TODO: ocurre un error en el autocomplete cuando se preciona enter al final,
    // en multioption
    private List<Object> resultList() {
	List<Object> result = new ArrayList<>();

	for (Component comp : cPanel.getComponents()) {
	    if (comp instanceof CustomTextField) {
		result.add(((CustomTextField) comp).TextToType());
	    }
	}

	return result;
    }

    private InsertDTO convert() {
	for (int i = 0; i < resultList().size(); i++) {
	    Object v = resultList().get(i);
	    try {
		Field f = subjectFields[i];
		f.setAccessible(true);
		f.set(subject, v);
	    } catch (IllegalArgumentException | IllegalAccessException e) {
		e.printStackTrace();
	    }
	}
	return subject;
    }

    public <R extends ReturnDTO> void okAction(CustomTable<R> table, ModelDTO md) {
	okButton.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		if (md == ModelDTO.CREATE)
		    table.getCustomModel().insertRow(table.getService().save(convert()));
		else if (md == ModelDTO.UPDATE)
		    table.getCustomModel().updateRow(table.getSelectedRow(),
			    table.getService().update(convert(), table.getIdEntity()));
		dispose();
	    }
	});
    }
}
