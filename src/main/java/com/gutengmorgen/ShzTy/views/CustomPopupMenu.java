package com.gutengmorgen.ShzTy.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class CustomPopupMenu extends JPopupMenu implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JMenuItem menuShowInfo;
    private JMenuItem menuItemEdit;
    private JMenuItem menuItemRemove;
    private JMenuItem menuItemAdd;

    public CustomPopupMenu() {
	menuShowInfo = new JMenuItem("Show More Info");
	menuShowInfo.addActionListener(this);
	menuItemEdit = new JMenuItem("Edit Current Row");
	menuItemEdit.addActionListener(this);
	menuItemRemove = new JMenuItem("Remove Current Row");
	menuItemRemove.addActionListener(this);
	menuItemAdd = new JMenuItem("Add New Row");
	menuItemAdd.addActionListener(this);
	//TODO: para crear y editar se usaran el mismo frame
	//TODO: para eliminar saldra una ventana de emergencia
	//TODO: para show more se desplegara toda la info del artist agrandando su row y luego volvera a su tamaño inicial con un boton, show less
	
	add(menuShowInfo);
	add(menuItemEdit);
	add(menuItemRemove);
	add(menuItemAdd);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	JMenuItem menuItem = (JMenuItem) e.getSource();
	if(menuItem.equals(menuShowInfo)) {
	    showInfo();
	} else if (menuItem.equals(menuItemEdit)) {
	    editItem();
	} else if (menuItem.equals(menuItemRemove)) {
	    removeItem();
	} else if (menuItem.equals(menuItemAdd)) {
	    addItem();
	}
	
    }

    private void addItem() {
	// TODO Auto-generated method stub
	
    }

    private void removeItem() {
	// TODO Auto-generated method stub
	
    }

    private void editItem() {
	// TODO Auto-generated method stub
	
    }

    private void showInfo() {
	System.out.println("show info");
	
    }
    
}
