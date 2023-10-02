package com.gutengmorgen.ShzTy.views;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

public class TableMouseListenir extends MouseAdapter {
    JTable table;
    TablePopupMenu popupMenu;
    
    public TableMouseListenir(JTable table, TablePopupMenu popupMenu) {
	this.table = table;
	this.popupMenu = popupMenu;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
	super.mouseReleased(e);
	
	if(e.isPopupTrigger()) {
		int row = table.rowAtPoint(e.getPoint());
		
		if(row >= 0 && row < table.getRowCount()) {
		    table.setRowSelectionInterval(row, row);
		    popupMenu.show(e, table);
		}
	    }
    }

}
