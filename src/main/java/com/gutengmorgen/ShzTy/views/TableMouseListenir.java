package com.gutengmorgen.ShzTy.views;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

public class TableMouseListenir extends MouseAdapter {
    JTable table;

    public TableMouseListenir(JTable table) {
	this.table = table;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
	if (e.isPopupTrigger()) {
	    int row = table.rowAtPoint(e.getPoint());

	    if (isValidRow(row)) {
		table.setRowSelectionInterval(row, row);
		new TablePopupMenu(table, row).show(table, e.getX(), e.getY());
	    }
	}
    }

    private boolean isValidRow(int row) {
	return row >= 0 && row < table.getRowCount();
    }
}
