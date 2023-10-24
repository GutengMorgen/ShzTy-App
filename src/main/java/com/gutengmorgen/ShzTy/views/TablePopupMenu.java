package com.gutengmorgen.ShzTy.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingWorker;

public class TablePopupMenu extends JPopupMenu implements ActionListener {
    private static final long serialVersionUID = -5926733140674775800L;

    private JMenuItem miInfo;
    private JMenuItem miUpdate;
    private JMenuItem miRemove;
    private JMenuItem miAdd;
    private JMenuItem miRefresh;
    private CustomTable<?> table;
    private Long idEntity;

    public TablePopupMenu(CustomTable<?> table, int row) {
	this.table = table;
	this.idEntity = (Long) table.getValueAt(row, 0);

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

    public TablePopupMenu(CustomTable<?> table) {
	this.table = table;

	miRefresh = new JMenuItem("Refresh Table");
	miRefresh.addActionListener(this);

	add(miRefresh);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == miInfo) {
	    CustomDialog d = new CustomDialog("Info of Current Row");
	    DtoMapper.search(table, idEntity, d, DTO_MODEL.RETURN);
	} else if (e.getSource() == miUpdate) {
	    CustomDialog d = new CustomDialog("Updating Current Row");
	    DtoMapper.search(table, idEntity, d, DTO_MODEL.UPDATE);
	} else if (e.getSource() == miRemove) {
	    alert();
	} else if (e.getSource() == miAdd) {
	    CustomDialog d = new CustomDialog("Creating new Row");
	    DtoMapper.search(table, idEntity, d, DTO_MODEL.CREATE);
	} else if (e.getSource() == miRefresh) {
	    refresh();
	}
    }

    private void give(String title, DTO_MODEL model) {
	CustomDialog d = new CustomDialog(title);
	DtoMapper mapper = new DtoMapper(table, idEntity, d, model);
	mapper.mapper();
	d.setVisible(true);
    }

    private void alert() {
	int a = JOptionPane.showConfirmDialog(table, "Are you sure?");
	if (a == JOptionPane.YES_OPTION) {
	    System.out.println("Se elimino el Artist");
	}
    }

    private void refresh() {
	SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

	    @Override
	    protected Void doInBackground() throws Exception {
		((MainTableModel<?>) table.getModel()).refreshModel();
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
