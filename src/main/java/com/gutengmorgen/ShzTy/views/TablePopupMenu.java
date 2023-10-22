package com.gutengmorgen.ShzTy.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingWorker;

public class TablePopupMenu extends JPopupMenu implements ActionListener {
    private static final long serialVersionUID = -5926733140674775800L;

    private JMenuItem miInfo;
    private JMenuItem miUpdate;
    private JMenuItem miRemove;
    private JMenuItem miAdd;
    private JMenuItem miRefresh;
    private JTable table;
    private Long rowId;

    public TablePopupMenu(JTable table, int row) {
	this.table = table;
	this.rowId = (Long) table.getValueAt(row, 0);

	miInfo = new JMenuItem("Show More Info");
	miInfo.addActionListener(this);
	miUpdate = new JMenuItem("Update Current Row");
	miUpdate.addActionListener(this);
	miRemove = new JMenuItem("Remove Current Row");
	miRemove.addActionListener(this);
	miAdd = new JMenuItem("Add New Row");
	miAdd.addActionListener(this);
	miRefresh = new JMenuItem("Refresh Table");
	miRefresh.addActionListener(this);

	add(miInfo);
	add(miUpdate);
	add(miRemove);
	add(miAdd);
	add(miRefresh);
    }

    public TablePopupMenu(JTable table) {
	this.table = table;

	miRefresh = new JMenuItem("Refresh Table");
	miRefresh.addActionListener(this);

	add(miRefresh);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == miInfo) {
	    CustomDialog dialog = new CustomDialog("Info of Current Row");
	    DtoMapper.mapper(table, rowId, dialog, DTO_MODEL.RETURN);
	    dialog.setVisible(true);
	} else if (e.getSource() == miUpdate) {
	    CustomDialog dialog = new CustomDialog("Update Current Row");
	    DtoMapper.mapper(table, rowId, dialog, DTO_MODEL.UPDATE);
	    dialog.setVisible(true);
	} else if (e.getSource() == miRemove) {
	    int a = JOptionPane.showConfirmDialog(table, "Are you sure?");
	    if (a == JOptionPane.YES_OPTION) {
		System.out.println("Se elimino el Artist");
	    }
	} else if (e.getSource() == miAdd) {
	    CustomDialog dialog = new CustomDialog("Add New Item");
//	dialog.autoFill(DtoMapper.map(DTO_MODEL.CREATE));
	    DtoMapper.mapper(table, rowId, dialog, DTO_MODEL.CREATE);
	    dialog.setVisible(true);
	} else if (e.getSource() == miRefresh) {
	    refresh();
	}
    }

    private void refresh() {
	SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

	    @Override
	    protected Void doInBackground() throws Exception {
		MainTableModel<?> m = (MainTableModel<?>) table.getModel();
		m.refreshModel();
		table.revalidate();
		table.repaint();
		return null;
	    }

	    @Override
	    protected void done() {
//		super.done();
		table.revalidate();
		table.repaint();
	    }

	};
	
	worker.execute();
    }
}
