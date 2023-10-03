package com.gutengmorgen.ShzTy.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.JTextField;

public class TestingTextFields extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    TestingTextFields frame = new TestingTextFields();
		    frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the frame.
     */
    public TestingTextFields() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 449, 127);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

	setContentPane(contentPane);
	
	textField = new JTextField();
	((AbstractDocument) textField.getDocument()).setDocumentFilter(new FilterOnlyNumbers());
	contentPane.add(textField);
	textField.setColumns(10);
	
	textField_1 = new JTextField();
	((AbstractDocument) textField_1.getDocument()).setDocumentFilter(new FilterDateFormat());
	contentPane.add(textField_1);
	textField_1.setColumns(10);
    }

}
