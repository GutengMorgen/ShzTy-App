package com.gutengmorgen.ShzTy.views;

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

import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class CustomDialog extends JDialog {
    private static final long serialVersionUID = -2988701719467147132L;

    private final JPanel cPanel = new JPanel();
    private final GridBagConstraints constraints = new GridBagConstraints();
    public JButton okButton;
    private JButton cancelButton;
    private int rowIndex = 0;

    public CustomDialog(String title) {
	setTitle(title);
	setBounds(100, 100, 480, 175);

	cPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	getContentPane().add(cPanel, BorderLayout.WEST);
	GridBagLayout gbl_cPanel = new GridBagLayout();
	cPanel.setLayout(gbl_cPanel);

	{
	    JPanel buttonPane = new JPanel();
	    buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
	    getContentPane().add(buttonPane, BorderLayout.SOUTH);
	    {
		okButton = new JButton("OK");
		buttonPane.add(okButton);
	    }
	    {
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
			dispose();
		    }
		});
		buttonPane.add(cancelButton);
	    }
	}
    }

    @SuppressWarnings("unchecked")
    public void autoFillReturn(Object obj) {
	Class<?> class1 = obj.getClass();
	Field[] fields = class1.getDeclaredFields();

	for (Field field : fields) {
	    field.setAccessible(true);

	    if (!field.isAnnotationPresent(ForGUI.class))
		break;

	    ForGUI forGUI = field.getAnnotation(ForGUI.class);

	    try {
		String name = forGUI.name();
		Object value = field.get(obj);
		JComponent component = null;

		if (forGUI.type() == GUIType.SIMPLE_TEXT) {
		    component = new JLabel(value.toString());
		} else if (forGUI.type() == GUIType.SINGLE_OPTION) {
		    DefaultComboBoxModel<String> lm = new DefaultComboBoxModel<>();
		    lm.addAll((Collection<? extends String>) value);
		    component = new JComboBox<>(lm);
		}

		addComponent(name, component);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}

	closeAutoFill();
    }

    public void autoFillToInsert(Class<?> cl) {
	Field[] fields = cl.getDeclaredFields();

	for (Field field : fields) {

	    field.setAccessible(true);
	    if (!field.isAnnotationPresent(ForGUI.class))
		break;

	    ForGUI forGUI = field.getAnnotation(ForGUI.class);

	    try {
		String name = forGUI.name();
		JComponent comp = null;

		if (forGUI.type() == GUIType.SINGLE_OPTION) {
		    CustomTextField c = new CustomTextField(GUIType.SINGLE_OPTION, forGUI.useEntity());
		    comp = c;
		} else if (forGUI.type() == GUIType.MULTI_OPTION) {
		    CustomTextField c = new CustomTextField(GUIType.MULTI_OPTION, forGUI.useEntity());
		    comp = c;
		} else if (forGUI.type() == GUIType.DATE) {
		    comp = new CustomTextField(GUIType.DATE);
		} else {
		    comp = new CustomTextField(GUIType.SIMPLE_TEXT);
		}

		addComponent(name, comp);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
	closeAutoFill();
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
	if (comp instanceof JTextField)
	    comp.setPreferredSize(new Dimension(150, comp.getPreferredSize().height));
	cPanel.add(comp, constraints);

	rowIndex++;
    }

    private void closeAutoFill() {
	rowIndex = 0;
	pack();
    }

    public List<Object> getResult() {
	List<Object> result = new ArrayList<>();

	for (Component comp : cPanel.getComponents()) {
	    if (comp instanceof JTextField) {
		CustomTextField field = (CustomTextField) comp;
		result.add(field.TextToType());
	    }
	}
	
	return result;
    }
    
    //TODO: hacer que esto funcione
    public Object convert(Object test) {
	Class<?> c = test.getClass();
	Field[] fields = c.getDeclaredFields();
	List<Object> result = getResult();
	
	for (int i = 0; i < result.size() - 1; i++) {
	    Object v = result.get(i);
	    try {
		Field f = fields[i];
		f.setAccessible(true);
		f.set(test, v);
	    } catch (IllegalArgumentException | IllegalAccessException e) {
		e.printStackTrace();
	    }
	}
	
	return test;
    }
}
