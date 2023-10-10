package com.gutengmorgen.ShzTy.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

public class TablePopupMenu extends JPopupMenu implements ActionListener {
    private static final long serialVersionUID = 1L;
    
    private JMenuItem menuItemInfo;
    private JMenuItem menuItemUpdate;
    private JMenuItem menuItemRemove;
    private JMenuItem menuItemAdd;
    private JTable table;
    private Long rowId;

    public TablePopupMenu(JTable table, int row) {
	this.table = table;
	this.rowId = (Long) table.getValueAt(row, 0);
	
	menuItemInfo = new JMenuItem("Show More Info");
	menuItemInfo.addActionListener(this);
	menuItemUpdate = new JMenuItem("Update Current Row");
	menuItemUpdate.addActionListener(this);
	menuItemRemove = new JMenuItem("Remove Current Row");
	menuItemRemove.addActionListener(this);
	menuItemAdd = new JMenuItem("Add New Row");
	menuItemAdd.addActionListener(this);
	
	add(menuItemInfo);
	add(menuItemUpdate);
	add(menuItemRemove);
	add(menuItemAdd);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if(e.getSource() == menuItemInfo) {
	    showInfo();
	} else if (e.getSource() == menuItemUpdate) {
	    updateItem();
	} else if (e.getSource() == menuItemRemove) {
	    removeItem();
	} else if (e.getSource() == menuItemAdd) {
	    addItem();
	}
	
    }

    private void showInfo() {
	CustomDialog dialog = new CustomDialog("Info of Current Row");
	dialog.autoFillReturn(DtoMapper.map(table.getName(), rowId));
	dialog.setVisible(true);
    }
    
    
    private void updateItem() {
	//TODO: para crear y editar se usaran el mismo frame
//	CustomDialog dialog = new CustomDialog("Update Current Item");
//	dialog.autoFill(DtoMapper.map(DTO_MODEL.UPDATE));
//	dialog.setVisible(true);
    }
    
    private void addItem() {
	//TODO: para crear y editar se usaran el mismo frame
//	CustomDialog dialog = new CustomDialog("Add New Item");
//	dialog.autoFill(DtoMapper.map(DTO_MODEL.CREATE));
//	dialog.setVisible(true);
    }
    
    private void removeItem() {
	int a = JOptionPane.showConfirmDialog(table, "Are you sure?");
	if(a == JOptionPane.YES_OPTION){
	    System.out.println("Se elemino el Artist");
	}
    }
}
