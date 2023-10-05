package com.gutengmorgen.ShzTy.views;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.MaskFormatter;

import com.toedter.calendar.JDateChooser;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

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
     * @throws ParseException 
     */
    public TestingTextFields() throws ParseException {
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

	// Crea un formato de fecha
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Crea un JFormattedTextField con el formato de fecha
        JFormattedTextField dateField = new JFormattedTextField(dateFormat);

        // Añade un valor por defecto si lo deseas
        dateField.setValue(new Date());

        // Añade el JFormattedTextField al panel
        contentPane.add(dateField);

        JButton validateButton = new JButton("Validar Fecha");

        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dateString = dateField.getText();
                try {
                    Date date = dateFormat.parse(dateString);
                    // La conversión fue exitosa, la fecha es válida
                    JOptionPane.showMessageDialog(contentPane, "Fecha válida: " + date);
                } catch (ParseException ex) {
                    // La conversión falló, la fecha no es válida
                    JOptionPane.showMessageDialog(contentPane, "Fecha no válida. Ingresa una fecha en el formato yyyy-MM-dd.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JDateChooser dateChooser = new JDateChooser();
        contentPane.add(dateChooser);
    }

}
