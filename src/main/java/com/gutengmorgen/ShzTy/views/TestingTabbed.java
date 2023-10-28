package com.gutengmorgen.ShzTy.views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;

public class TestingTabbed extends JFrame {
	private static final long serialVersionUID = 1L;
	private final int width = 642;
	private final int height = 306;
//	private boolean draggingSeparator = false;
//	private int sPosY;
	private int rowH1 = 262;
	private int rowH2 = 20;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					TestingTabbed frame = new TestingTabbed();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TestingTabbed() {
		setTitle("Testing");
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, width, height);

		JPanel main = new JPanel();
		main.setBorder(new EmptyBorder(3, 3, 3, 3));
		setContentPane(main);
		GridBagLayout mgbl = new GridBagLayout();
		mgbl.columnWidths = new int[] { width };
		mgbl.rowHeights = new int[] { 22, 218 };
		mgbl.columnWeights = new double[] { 1.0 };
		mgbl.rowWeights = new double[] { 0.0, 1.0 };
		main.setLayout(mgbl);

		TitleBar title = new TitleBar(this);
		title.setBorder(null);
		GridBagConstraints tgbl = new GridBagConstraints();
		tgbl.fill = GridBagConstraints.BOTH;
		tgbl.gridx = 0;
		tgbl.gridy = 0;
		main.add(title, tgbl);

		JPanel around = new JPanel();
		GridBagConstraints cgbl = new GridBagConstraints();
		cgbl.gridx = 0;
		cgbl.gridy = 1;
		cgbl.fill = GridBagConstraints.BOTH;
		GridBagLayout agbl = new GridBagLayout();
		agbl.columnWidths = new int[] { width };
		agbl.rowHeights = new int[] { 219, 20 };
		agbl.columnWeights = new double[] { 1.0 };
		agbl.rowWeights = new double[] { 1.0, 1.0 };
		around.setLayout(agbl);
		main.add(around, cgbl);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints sgbl = new GridBagConstraints();
		sgbl.fill = GridBagConstraints.BOTH;
		sgbl.insets = new Insets(0, 0, 5, 0);
		sgbl.gridx = 0;
		sgbl.gridy = 0;
		scrollPane.setBorder(null);
		title.addTab("Artist", scrollPane);
		around.add(scrollPane, sgbl);

		JPanel footer = new JPanel();
		footer.setBorder(null);
		footer.setBackground(new Color(230, 230, 230));
		footer.setLayout(null);
		GridBagConstraints fgbl = new GridBagConstraints();
		fgbl.fill = GridBagConstraints.BOTH;
		fgbl.gridx = 0;
		fgbl.gridy = 1;
		around.add(footer, fgbl);

		JButton dBtn = new JButton("-");
		dBtn.setBounds(596, 0, 40, 12);
		GridBagConstraints dgbl = new GridBagConstraints();
		dgbl.insets = new Insets(0, 0, 5, 0);
		dgbl.gridx = 0;
		dgbl.gridy = 1;
		dBtn.setBorderPainted(false);
		dBtn.setContentAreaFilled(false);
		dBtn.setFocusPainted(false);
		dBtn.setBorder(null);
		dBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (dBtn.getText().equals("o")) {
					rowH1 = 262;
					agbl.rowHeights = new int[] { rowH1, rowH2 };
					dBtn.setText("-");
				} else {
					rowH1 = 159;
					agbl.rowHeights = new int[] { rowH1, rowH2 };
					dBtn.setText("o");
				}
			}
		});
		footer.add(dBtn);
	}

	/**
	 * 
	 * footer.addMouseListener(new MouseInputAdapter() {
	 * 
	 * @Override
	 *           public void mousePressed(MouseEvent e) {
	 *           if (SwingUtilities.isRightMouseButton(e)) {
	 *           footer.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
	 *           sPosY = e.getY();
	 *           draggingSeparator = true;
	 *           }
	 *           }
	 * 
	 * @Override
	 *           public void mouseReleased(MouseEvent e) {
	 *           footer.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	 *           draggingSeparator = false;
	 *           }
	 *           });
	 * 
	 *           footer.addMouseMotionListener(new MouseInputAdapter() {
	 * 
	 * @Override
	 *           public void mouseDragged(MouseEvent e) {
	 *           if (!draggingSeparator)
	 *           return;
	 * 
	 *           if (e.getY() < sPosY - 10) {
	 *           rowH1 = rowH1 - 10;
	 *           } else if (e.getY() > sPosY + 10) {
	 *           rowH1 = rowH1 + 10;
	 *           }
	 *           if (rowH1 < 310) {
	 *           gbl.rowHeights = new int[] { rowH1, rowH2 };
	 *           center.revalidate();
	 *           center.repaint();
	 *           }
	 *           }
	 *           });
	 *           }
	 */
}