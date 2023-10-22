package com.gutengmorgen.ShzTy.views;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

public class CustomTable extends JTable {
    private static final long serialVersionUID = 6949002519327279008L;

    public CustomTable(String name) {
	setName(name);
	DefaultSettings();
    }
    
    public CustomTable(String name, AbstractTableModel model) {
	setName(name);
	setModel(model);
	DefaultSettings();
    }

    public void DefaultSettings() {
	setShowVerticalLines(false);
	setRequestFocusEnabled(false);
	setFocusTraversalKeysEnabled(false);
	setFocusable(false);
	setBorder(null);
//	tableArtist.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	setFillsViewportHeight(true);
	getTableHeader().setReorderingAllowed(false);
//	TableColumn column = tableArtist.getColumnModel().getColumn(1);
//	column.setPreferredWidth(1);
	addMouseListener(new TableMouseListenir(this));
    }
}
