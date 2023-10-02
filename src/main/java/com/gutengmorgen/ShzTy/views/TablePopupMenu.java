package com.gutengmorgen.ShzTy.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

import com.gutengmorgen.ShzTy.models.Artists.Artist;
import com.gutengmorgen.ShzTy.models.DtoArtists.DtoCreateArtist;

public class TablePopupMenu extends JPopupMenu implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JMenuItem menuItemInfo;
    private JMenuItem menuItemEdit;
    private JMenuItem menuItemRemove;
    private JMenuItem menuItemAdd;
    private JTable table;

    public TablePopupMenu() {
	menuItemInfo = new JMenuItem("Show More Info");
	menuItemInfo.addActionListener(this);
	menuItemEdit = new JMenuItem("Edit Current Row");
	menuItemEdit.addActionListener(this);
	menuItemRemove = new JMenuItem("Remove Current Row");
	menuItemRemove.addActionListener(this);
	menuItemAdd = new JMenuItem("Add New Row");
	menuItemAdd.addActionListener(this);
	
	add(menuItemInfo);
	add(menuItemEdit);
	add(menuItemRemove);
	add(menuItemAdd);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if(e.getSource() == menuItemInfo) {
	    showInfo();
	} else if (e.getSource() == menuItemEdit) {
	    editItem();
	} else if (e.getSource() == menuItemRemove) {
	    removeItem();
	} else if (e.getSource() == menuItemAdd) {
	    addItem();
	}
	
    }
    
    public void show(MouseEvent event, JTable table) {
	this.table = table;
	
	int currentRow = table.getSelectedRow();
	int rowHeight = table.getRowHeight(currentRow);
	if(rowHeight == 50) menuItemInfo.setText("Show Less Info");
	
	show(table, event.getX(), event.getY());
    }

    private void showInfo() {
	//TODO: para show more se desplegara toda la info del artist agrandando su row y luego volvera a su tamaño inicial con un boton, show less
	
	int currentRow = table.getSelectedRow();
	int rowHeight = table.getRowHeight(currentRow);
	if(rowHeight == 50)
	    table.setRowHeight(currentRow, 16);
	else
	    table.setRowHeight(currentRow, 50);
    }
    
    private void editItem() {
	//TODO: para crear y editar se usaran el mismo frame
	
	CustomDialog dialog = new CustomDialog("Edit Current Item");
	dialog.autoFill(DtoCreateArtist.class);
	dialog.setVisible(true);
    }
    
    private void addItem() {
	//TODO: para crear y editar se usaran el mismo frame
	
	CustomDialog dialog = new CustomDialog("Add New Item");
	dialog.autoFill(DtoCreateArtist.class);
	dialog.setVisible(true);
    }
    
    private void removeItem() {
	//TODO: para eliminar saldra una ventana de emergencia
	
	int a = JOptionPane.showConfirmDialog(table, "Are you sure?");
	if(a == JOptionPane.YES_OPTION){
	    System.out.println("Se elemino el Artist");
	}
    }
}
