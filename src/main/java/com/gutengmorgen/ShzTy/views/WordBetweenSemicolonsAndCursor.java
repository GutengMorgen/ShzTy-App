package com.gutengmorgen.ShzTy.views;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.BorderLayout;

public class WordBetweenSemicolonsAndCursor {
    static JTextField textField = new JTextField("This;is;an;example;text;with;words;between;semicolons.");
    static JLabel resultLabel = new JLabel("Word between semicolons and cursor: ");

    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    JFrame frame = new JFrame("Word Between Semicolons and Cursor");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setLayout(new BorderLayout());

	    frame.add(textField, BorderLayout.NORTH);
	    frame.add(resultLabel, BorderLayout.SOUTH);
	    frame.add(new JButton("fef"));

	    textField.getDocument().addDocumentListener(new DocumentListener() {
		@Override
		public void insertUpdate(DocumentEvent e) {
		    updateWordAtCursor();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
		    textField.setCaretPosition(textField.getCaretPosition() - 1);
		    updateWordAtCursor();
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
		    updateWordAtCursor();
		}
	    });

	    frame.setSize(400, 200);
	    frame.setVisible(true);
	});
    }

    private static void updateWordAtCursor() {
	String text = textField.getText();
	int caret = textField.getCaretPosition();

	int sIndex = text.lastIndexOf(';', caret - 1);
	int eIndex = text.indexOf(';', caret);

//        System.out.println(caret);
//        System.out.println("prev: " + sIndex + " - " + "post: " + eIndex);
	if (sIndex >= 0 && eIndex >= 0) {
	    String wordBetweenSemicolons = text.substring(sIndex + 1, eIndex);
	    resultLabel.setText("Word between semicolons and cursor: " + wordBetweenSemicolons);
	} else if (sIndex == -1) {
	    String word = text.substring(0, eIndex);
	    resultLabel.setText("Word between semicolons and cursor: " + word);
	} else if (eIndex == -1) {
	    String word = text.substring(sIndex + 1, text.length());
	    resultLabel.setText("Word between semicolons and cursor: " + word);
	}
    }
}
