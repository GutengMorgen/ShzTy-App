package com.gutengmorgen.ShzTy.views;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;
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
	setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	setFillsViewportHeight(true);
	getTableHeader().setReorderingAllowed(false);
//	tableArtist.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//	TableColumn column = tableArtist.getColumnModel().getColumn(1);
//	column.setPreferredWidth(1);
	addMouseListener(new MouseInputAdapter() {

	    @Override
	    public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		if (SwingUtilities.isRightMouseButton(e)) {
		    int row = CustomTable.this.rowAtPoint(e.getPoint());

		    if (isValidRow(row)) {
			CustomTable.this.setRowSelectionInterval(row, row);
			new TablePopupMenu(CustomTable.this, row).show(CustomTable.this, e.getX(), e.getY());
		    }
		}
	    }

	    private boolean isValidRow(int row) {
		return row >= 0 && row < CustomTable.this.getRowCount();
	    }
	});
    }
}
