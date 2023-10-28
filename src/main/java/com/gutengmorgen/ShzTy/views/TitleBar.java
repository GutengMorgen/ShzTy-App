package com.gutengmorgen.ShzTy.views;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.MouseInputAdapter;

import com.gutengmorgen.ShzTy.views.Components.CustomLabel;

public class TitleBar extends JPanel {
	private int posX;
	private int posY;
	private boolean dragging = false;
	private JFrame frame;
	private GridBagLayout gbl = new GridBagLayout();
	private JPanel tabPanel = new JPanel();
	private List<CustomLabel> labels = new ArrayList<>();

	public TitleBar(JFrame frame) {
		this.frame = frame;
		setBorder(null);
		setBackground(Color.WHITE);
		gbl.columnWeights = new double[] { 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		setLayout(gbl);

		tabPanel.setBackground(Color.WHITE);
		FlowLayout fl = new FlowLayout(FlowLayout.LEADING);
		fl.setVgap(0);
		fl.setHgap(2);
		tabPanel.setLayout(fl);
		GridBagConstraints gbl_tabs = new GridBagConstraints();
		gbl_tabs.fill = GridBagConstraints.HORIZONTAL;
		gbl_tabs.gridx = 0;
		gbl_tabs.gridy = 0;
		add(tabPanel, gbl_tabs);

		JLabel minimize = new JLabel("-");
		defaultActions(minimize, 1, false);

		JLabel close = new JLabel("x");
		defaultActions(close, 2, true);

		addMouseListener(new MouseInputAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					posX = e.getX();
					posY = e.getY();
					dragging = true;
					setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				dragging = false;
			}
		});

		addMouseMotionListener(new MouseInputAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				if (dragging && SwingUtilities.isLeftMouseButton(e)) {
					int newX = (int) (frame.getLocation().getX() + e.getX() - posX);
					int newY = (int) (frame.getLocation().getY() + e.getY() - posY);
					frame.setLocation(newX, newY);
				}
			}
		});
	}

	private void defaultActions(JLabel comp, int gridx, boolean finish) {
		GridBagConstraints gbl_c = new GridBagConstraints();
		gbl_c.fill = GridBagConstraints.HORIZONTAL;
		gbl_c.gridx = gridx;
		gbl_c.gridy = 0;
		comp.setBackground(Color.WHITE);
		comp.setCursor(new Cursor(Cursor.HAND_CURSOR));
		comp.setForeground(Color.BLACK);
		comp.setOpaque(true);
		comp.setHorizontalTextPosition(SwingConstants.CENTER);
		comp.setHorizontalAlignment(SwingConstants.CENTER);
		comp.setBorder(new EmptyBorder(2, 20, 2, 20));
		comp.addMouseListener(new MouseInputAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				if (finish)
					frame.dispose();
				else
					frame.setExtendedState(Frame.ICONIFIED);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				comp.setBackground(new Color(102, 0, 0));
				comp.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				comp.setBackground(Color.WHITE);
				comp.setForeground(Color.BLACK);
			}
		});
		add(comp, gbl_c);
	}

	public void addTab(String name, JComponent child, JScrollPane port) {
		CustomLabel lb = new CustomLabel(name, child, port);
		tabSettings(lb);
		tabPanel.add(lb);
	}

	public void lastTabIndex() {
		CustomLabel lb = labels.get(labels.size() - 1);
		activeTab(lb);
	}

	public void setActiveTab(int index) {
		if (index >= 0 && index < labels.size()) {
			CustomLabel lb = labels.get(index);
			activeTab(lb);
		} else {
			throw new IndexOutOfBoundsException("Index" + index + " out of bounds for labels");
		}
	}

	private void tabSettings(CustomLabel label) {
		label.setBorder(new CompoundBorder(new LineBorder(new Color(230, 230, 230)), new EmptyBorder(2, 2, 2, 2)));
		label.setOpaque(true);
		labels.add(label);
		label.addMouseListener(new MouseInputAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				for (CustomLabel lb : labels) {
					if (lb.equals(e.getSource())) {
						activeTab(lb);
					} else {
						lb.childVisibility(false);
						lb.setBackground(new Color(240, 240, 240));
					}
				}
			}
		});
	}

	private void activeTab(CustomLabel lb) {
		lb.childVisibility(true);
		lb.getPort().setViewportView(lb.getChild());
		lb.setBackground(Color.WHITE);
	}
}
