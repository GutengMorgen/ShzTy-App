package com.gutengmorgen.ShzTy.views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.Font;

public class TestingTabbed extends JFrame {
	private static final long serialVersionUID = 1L;
	private final int width = 642;
	private final int height = 306;
	private final int titleH = 25;
	private final int centerMaxH = 262;
	private final int centerMinH = 80;
	private int footerH = 20;

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
		setUndecorated(true);
		setBounds(100, 100, width, height);

		JPanel main = new JPanel();
		main.setBorder(new EmptyBorder(2, 2, 2, 2));
		GridBagLayout mgbl = new GridBagLayout();
		mgbl.columnWidths = new int[] { width };
		mgbl.rowHeights = new int[] { titleH, width - titleH };
		mgbl.columnWeights = new double[] { 1.0 };
		mgbl.rowWeights = new double[] { 0.0, 1.0 };
		main.setLayout(mgbl);
		setContentPane(main);

		TitleBar title = new TitleBar(this);
		addGBC(main, title, 0);

		JPanel around = new JPanel();
		addGBC(main, around, 1);
		GridBagLayout agbl = new GridBagLayout();
		agbl.columnWidths = new int[] { width };
		agbl.rowHeights = new int[] { centerMaxH, footerH };
		agbl.columnWeights = new double[] { 1.0 };
		agbl.rowWeights = new double[] { 1.0, 1.0 };
		around.setLayout(agbl);

		JScrollPane center = new JScrollPane();
		addGBC(around, center, 0);
		title.addTab("Artist", center);

		JPanel footer = new JPanel();
		footer.setLayout(null);
		addGBC(around, footer, 1);

		JButton dBtn = new JButton("<---->");
		dBtn.setBorder(null);
		dBtn.setFont(new Font("Consolas", Font.PLAIN, 10));
		dBtn.setBounds(width - 100 - 2, 0, 100, 20);
		dBtn.setContentAreaFilled(false);
//		dBtn.setFocusPainted(false);
		dBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (dBtn.getText().equals("o----o")) {
					agbl.rowHeights = new int[] { centerMaxH, footerH };
					dBtn.setText("<---->");
				} else {
					agbl.rowHeights = new int[] { centerMinH, footerH };
					dBtn.setText("o----o");
				}
			}
		});
		footer.add(dBtn);
	}

	private void addGBC(JComponent father, JComponent child, int gridy) {
		GridBagConstraints gbl = new GridBagConstraints();
		gbl.fill = GridBagConstraints.BOTH;
		gbl.gridx = 0;
		gbl.gridy = gridy;
		father.add(child, gbl);
	}
}
