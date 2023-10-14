package com.gutengmorgen.ShzTy.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class testing {
    private JFrame frame;
    private JPanel panel;
    private GridBagConstraints constraints;
    private int currentRow = 0;
    private int currentColumn = 0;

    public testing() {
        frame = new JFrame("Auto GridBagLayout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        panel = new JPanel(new GridBagLayout());
        constraints = new GridBagConstraints();
        
//        addComponent(new JButton("Button 1"));
//        addComponent(new JLabel("Label 1"));
//        addComponent(new JButton("Button 2"));
//        addComponent(new JLabel("Label 2"));
        
        frame.getContentPane().add(panel, BorderLayout.WEST);

        JButton addButton = new JButton("Add Component");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addComponent(new JLabel("Label " + (currentRow + 1)));
            }
        });

        frame.getContentPane().add(addButton, BorderLayout.SOUTH);
    }

    public void addComponent(Component component) {
	constraints.gridx = currentColumn; // Set the column for the current component
        constraints.gridy = currentRow;
//        constraints.fill = GridBagConstraints.NONE;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.insets = new Insets(5,5,5,10);
        
        panel.add(component, constraints);

        currentColumn++; // Increment the column for the next component
        if (currentColumn >= 2) { // Wrap to the next row after the second column
            currentColumn = 0;
            currentRow++;
        }
        
        frame.revalidate();
//        frame.pack();
        frame.repaint();
    }

    public void display() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                testing example = new testing();
                example.display();
            }
        });
    }
}
