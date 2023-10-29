package com.gutengmorgen.ShzTy.views.Components;

import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MouseInputAdapter;

import com.gutengmorgen.ShzTy.services.extras.InsertDTO;
import com.gutengmorgen.ShzTy.services.extras.MainServices;
import com.gutengmorgen.ShzTy.services.extras.ReturnDTO;
import com.gutengmorgen.ShzTy.views.DtoMapper;
import com.gutengmorgen.ShzTy.views.Extras.MainTableModel;
import com.gutengmorgen.ShzTy.views.Extras.ModelDTO;

import lombok.Getter;
import lombok.Setter;

public class CustomTable<R extends ReturnDTO> extends JTable {
	private static final long serialVersionUID = 6949002519327279008L;

	private MainTableModel<R> dataModel;
	@Getter
	private InsertDTO createObject;
	@Getter
	private InsertDTO updateObject;
	@Getter
	@Setter
	private MainServices<R> service;
	@Getter
	@Setter
	private TablePopupMenu tablePopup;

	public CustomTable(MainTableModel<R> model, TablePopupMenu popup) {
		setCustomModel(model);
		setTablePopup(popup);
		DefaultSettings();
	}

	public CustomTable(MainTableModel<R> model) {
		setCustomModel(model);
		DefaultSettings();
	}

	public CustomTable(String name) {
		setName(name);
		this.dataModel = null;
	}

	public void setCustomModel(MainTableModel<R> model) {
		this.dataModel = model;
		super.setModel(dataModel);
	}

	public MainTableModel<R> getCustomModel() {
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
						tablePopup.show(CustomTable.this, e.getX(), e.getY());
					} else {
						tablePopup.show(CustomTable.this, e.getX(), e.getY());
					}
				}
			}

			private boolean isValidRow(int row) {
				return row >= 0 && row < CustomTable.this.getRowCount();
			}
		});
		ListSelectionModel selectionModel = this.getSelectionModel();
		selectionModel.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					DtoMapper.contraFooter(CustomTable.this, ModelDTO.RETURN);
				}
			}
		});
	}

	public Long getIdEntity() {
		// puede devolver el selectedrow anterior si el actual es invalido
		return (Long) getValueAt(getSelectedRow(), 0);
	}

	public void setPotencials(MainServices<R> service, InsertDTO createObject, InsertDTO updateObject) {
		this.service = service;
		this.createObject = createObject;
		this.updateObject = updateObject;
	}
}
