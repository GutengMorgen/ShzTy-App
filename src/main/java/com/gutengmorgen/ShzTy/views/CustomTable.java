package com.gutengmorgen.ShzTy.views;

import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

import com.gutengmorgen.ShzTy.services.ReturnDTO;
import com.gutengmorgen.ShzTy.services.InsertDTO;

public class CustomTable<T extends ReturnDTO> extends JTable {
    private static final long serialVersionUID = 6949002519327279008L;
    private MainTableModel<T> dataModel;
    private Class<? extends InsertDTO> createClass;
    private Class<? extends InsertDTO> updateClass;

    public CustomTable(String name) {
	setName(name);
	dataModel = null;
	DefaultSettings();
    }

    //NOTE: separa las interfaces de DTO en dos tipos insert y return
    public CustomTable(String name, MainTableModel<T> model) {
	setName(name);
	setCustomModel(model);
	DefaultSettings();
    }

    public void setCustomModel(MainTableModel<T> model) {
	this.dataModel = model;
	super.setModel(dataModel);
    }
    
    public MainTableModel<T> getCustomModel(){
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
			new TablePopupMenu(CustomTable.this, row).show(CustomTable.this, e.getX(), e.getY());
		    } else {
			new TablePopupMenu(CustomTable.this).show(CustomTable.this, e.getX(), e.getY());;
		    }
		}
	    }

	    private boolean isValidRow(int row) {
		return row >= 0 && row < CustomTable.this.getRowCount();
	    }
	});
    }

    public Class<? extends InsertDTO> getCreateClass() {
	return createClass;
    }

    public void setCreateClass(Class<? extends InsertDTO> createClass) {
	this.createClass = createClass;
    }

    public Class<? extends InsertDTO> getUpdateClass() {
	return updateClass;
    }

    public void setUpdateClass(Class<? extends InsertDTO> updateClass) {
	this.updateClass = updateClass;
    }

  
}
