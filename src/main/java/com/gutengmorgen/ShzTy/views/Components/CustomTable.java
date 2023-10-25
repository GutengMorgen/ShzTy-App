package com.gutengmorgen.ShzTy.views.Components;

import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

import com.gutengmorgen.ShzTy.services.ReturnDTO;
import com.gutengmorgen.ShzTy.views.Extras.MainTableModel;

public class CustomTable<R extends ReturnDTO> extends JTable {
    private static final long serialVersionUID = 6949002519327279008L;
    private MainTableModel<R> dataModel;

    public CustomTable(String name) {
	setName(name);
	dataModel = null;
	DefaultSettings();
    }

    public CustomTable(String name, MainTableModel<R> model) {
	setName(name);
	setCustomModel(model);
	DefaultSettings();
    }

    public void setCustomModel(MainTableModel<R> model) {
	this.dataModel = model;
	super.setModel(dataModel);
    }
    
    public MainTableModel<R> getCustomModel(){
	return dataModel;
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
	addMouseListener(new MouseInputAdapter() {

	    @Override
	    public void mousePressed(MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e)) {
		    int row = CustomTable.this.rowAtPoint(e.getPoint());
		    if (isValidRow(row)) {
			CustomTable.this.setRowSelectionInterval(row, row);
			new TablePopupMenu<R>(CustomTable.this, false).show(CustomTable.this, e.getX(), e.getY());
		    } else {
			new TablePopupMenu<R>(CustomTable.this, true).show(CustomTable.this, e.getX(), e.getY());
		    }
		}
	    }

	    private boolean isValidRow(int row) {
		return row >= 0 && row < CustomTable.this.getRowCount();
	    }
	});
    }

    public Long getIdEntity() {
	//puede devolver el selectedrow anterior si el actual es invalido
	return (Long) getValueAt(getSelectedRow(), 0);
    }
}
