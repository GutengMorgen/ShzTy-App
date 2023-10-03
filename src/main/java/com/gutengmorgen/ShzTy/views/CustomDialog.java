package com.gutengmorgen.ShzTy.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.lang.reflect.Field;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;

import com.gutengmorgen.ShzTy.models.Albums.DtoAlbums.DtoCreateAlbum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomDialog extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JButton okButton;
    private JButton cancelButton;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	try {
	    CustomDialog dialog = new CustomDialog("Custom Dialog");
	    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    dialog.autoFill(DtoCreateAlbum.class);
	    dialog.setVisible(true);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /**
     * Create the dialog.
     */
    public CustomDialog(String title) {
	setTitle(title);
	setBounds(100, 100, 450, 300);
	getContentPane().setLayout(new BorderLayout());
	contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	getContentPane().add(contentPanel, BorderLayout.CENTER);
	contentPanel.setLayout(new GridLayout(0, 2, 0, 0));

	{
	    JPanel buttonPane = new JPanel();
	    buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
	    getContentPane().add(buttonPane, BorderLayout.SOUTH);
	    {
		okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		okButton.addActionListener(this);
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
	    }
	    {
		cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		cancelButton.addActionListener(this);
		buttonPane.add(cancelButton);
	    }
	}
    }

    public void autoFill(Class<?> classToRead) {
	Field[] fields = classToRead.getDeclaredFields();

	for (Field field : fields) {
	    String fieldName = field.getName();
	    Class<?> fieldType = field.getType();

	    JLabel label = new JLabel(hasValidAnnotations(field) ? fieldName + " *" : fieldName);
	    contentPanel.add(label);

	    if (fieldType.getSimpleName().equals("String")) {
		JTextField textField = new JTextField(10);
		contentPanel.add(textField);
		
	    } else if (fieldType.getSimpleName().equals("Long") | fieldType.getSimpleName().equals("int")) {
		JTextField textField = new JTextField(10);
		((AbstractDocument) textField.getDocument()).setDocumentFilter(new FilterOnlyNumbers());
		contentPanel.add(textField);
		
	    } else if (fieldType.getSimpleName().equals("Date")) {
		JTextField textField = new JTextField(10);
		((AbstractDocument) textField.getDocument()).setDocumentFilter(new FilterDateFormat());
		contentPanel.add(textField);
		
	    } else if (fieldType.getSimpleName().equals("Set")) {
		String[] options = { "option 1", "option 2", "option 3", "option 4" };
		JComboBox<String> comboBox = new JComboBox<String>(options);
		contentPanel.add(comboBox);
	    }

//	    System.out.println(fieldName + ", Tipo: " + fieldType.getName());
	}
    }

    private boolean hasValidAnnotations(Field field) {
	if (field.isAnnotationPresent(NotNull.class))
	    return true;
	else if (field.isAnnotationPresent(NotBlank.class))
	    return true;
	else
	    return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource().equals(okButton)) {
	    okAction();
	} else if (e.getSource().equals(cancelButton)) {
	    cancelAction();
	}

    }

    private void okAction() {
	// TODO Auto-generated method stub

    }

    private void cancelAction() {
	// TODO Auto-generated method stub

    }
}
