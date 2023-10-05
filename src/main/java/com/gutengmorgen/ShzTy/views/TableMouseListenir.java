package com.gutengmorgen.ShzTy.views;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

public class TableMouseListenir extends MouseAdapter {
    JTable table;
    TablePopupMenu tablePopupMenu;
    Class<?> clazz;
    
    public TableMouseListenir(JTable table, TablePopupMenu tablePopupMenu, Class<?> clazz) {
	this.table = table;
	this.tablePopupMenu = tablePopupMenu;
	this.clazz = clazz;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
	
	if(e.isPopupTrigger()) {
		int row = table.rowAtPoint(e.getPoint());
		
		if(isValidRow(row)) {
		    table.setRowSelectionInterval(row, row);
		    tablePopupMenu.show(e, clazz);
		}
	    }
    }

    private boolean isValidRow(int row) {
        return row >= 0 && row < table.getRowCount();
    }
}
