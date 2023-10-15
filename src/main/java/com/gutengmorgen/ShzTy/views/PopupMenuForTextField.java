package com.gutengmorgen.ShzTy.views;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopupMenuForTextField {
    public static void main(String[] args) {
        JFrame frame = new JFrame("TextField Context Menu Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 100);

        JTextField textField = new JTextField(20);
        JPopupMenu popupMenu = new JPopupMenu();

        // Add menu items to the popup menu
        JMenuItem item1 = new JMenuItem("Option 1");
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(item1.getText());
            }
        });
        JMenuItem item2 = new JMenuItem("Option 2");
        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(item2.getText());
            }
        });

        popupMenu.add(item1);
        popupMenu.add(item2);

        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                showPopupMenu();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                showPopupMenu();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                showPopupMenu();
            }

            private void showPopupMenu() {
                if (textField.isFocusOwner() && textField.getText().length() > 0) {
                    popupMenu.setFocusable(false);
                    popupMenu.show(textField, 0, textField.getHeight());
                    
//                    textField.requestFocus();
                } else {
                    popupMenu.setVisible(false);
                }
            }
        });

        frame.add(textField);
        frame.setVisible(true);
    }
}
