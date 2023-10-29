package com.gutengmorgen.ShzTy.views.Components;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingWorker;

import com.gutengmorgen.ShzTy.views.DtoMapper;
import com.gutengmorgen.ShzTy.views.Extras.ModelDTO;

public class TablePopupMenu extends JPopupMenu implements ActionListener {
	private static final long serialVersionUID = -5926733140674775800L;

	private final JMenuItem miUpdate;
	private final JMenuItem miRemove;
	private final JMenuItem miAdd;
	private final JMenuItem miRefresh;
	private CustomTable<?> table;

	public TablePopupMenu() {
		miUpdate = new JMenuItem("Update Current Row");
		miUpdate.addActionListener(this);
		add(miUpdate);
		miRemove = new JMenuItem("Remove Current Row");
		miRemove.addActionListener(this);
		add(miRemove);
		miAdd = new JMenuItem("Add New Row");
		miAdd.addActionListener(this);
		add(miAdd);
		miRefresh = new JMenuItem("Refresh Table");
		miRefresh.addActionListener(this);
		add(miRefresh);
	}

	@Override
	public void show(Component invoker, int x, int y) {
		table = (CustomTable<?>) invoker;
		super.show(invoker, x, y);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == miUpdate) {
			DtoMapper.contraFooter(table, ModelDTO.UPDATE);
		} else if (e.getSource() == miRemove) {
			alert();
		} else if (e.getSource() == miAdd) {
			DtoMapper.contraFooter(table, ModelDTO.CREATE);
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
