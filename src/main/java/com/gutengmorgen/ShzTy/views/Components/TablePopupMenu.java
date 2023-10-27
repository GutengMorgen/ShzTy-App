package com.gutengmorgen.ShzTy.views.Components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingWorker;

import com.gutengmorgen.ShzTy.services.extras.ReturnDTO;
import com.gutengmorgen.ShzTy.views.DtoMapper;
import com.gutengmorgen.ShzTy.views.Extras.ModelDTO;

public class TablePopupMenu<R extends ReturnDTO> extends JPopupMenu implements ActionListener {
    private static final long serialVersionUID = -5926733140674775800L;

    private JMenuItem miInfo;
    private JMenuItem miUpdate;
    private JMenuItem miRemove;
    private JMenuItem miAdd;
    private JMenuItem miRefresh;
    private CustomTable<R> table;

    public TablePopupMenu(CustomTable<R> table, boolean onlyRefresh) {
	this.table = table;

	if (onlyRefresh) {
	    miRefresh = new JMenuItem("Refresh Table");
	    miRefresh.addActionListener(this);

	    add(miRefresh);
	} else {
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == miInfo) {
	    DtoMapper.impact(table, new CustomDialog("Info of Current Row"), ModelDTO.RETURN);
	} else if (e.getSource() == miUpdate) {
	    DtoMapper.impact(table, new CustomDialog("Updating Current Row"), ModelDTO.UPDATE);
	} else if (e.getSource() == miRemove) {
	    alert();
	} else if (e.getSource() == miAdd) {
	    DtoMapper.impact(table, new CustomDialog("Creating new Row"), ModelDTO.CREATE);
	} else if (e.getSource() == miRefresh) {
	    refresh();
	}
    }

    private void alert() {
	int a = JOptionPane.showConfirmDialog(table, "Are you sure?");
	if (a == JOptionPane.YES_OPTION) {
	    System.out.println("Se elimino el Artist");
	}
    }

    private void refresh() {
	new SwingWorker<Void, Void>() {

	    @Override
	    protected Void doInBackground() throws Exception {
		table.getCustomModel().refreshModel();
		return null;
	    }

	    @Override
	    protected void done() {
		table.revalidate();
		table.repaint();
	    }

	}.execute();
    }
}
