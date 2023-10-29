package com.gutengmorgen.ShzTy.views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import lombok.Getter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JComponent;

public class TabExtension extends JFrame {
	private static final long serialVersionUID = 1L;
	private final int frameW;
	private final int frameH;
	private static final int titleH = 25;
	private static final int footerH = 15;
	private int centerMaxH = 0;
	private static final String UP = "<---->";
	private static final String DOWN = "o----o";
	@Getter
	private TitleBar bar;
	@Getter
	private JPanel footerView;
	@Getter
	private JLabel infoLabel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					TabExtension frame = new TabExtension(900, 500);
					JPanel panel = new JPanel();
					JTextField field = new JTextField(21);
					panel.add(field);
					frame.bar.addTab("something", panel);

					JTextArea textArea = new JTextArea();
					frame.bar.addTab("text", textArea);

					JTextArea textArea2 = new JTextArea();
					frame.bar.addTab("another Text", textArea2);

					frame.bar.setActiveTab(0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TabExtension(int width, int height) {
		this.frameW = width;
		this.frameH = height;
		centerMaxH = (frameH - titleH) - footerH;
		setUndecorated(true);
		setSize(frameW, frameH);

		JPanel main = new JPanel();
		GridBagLayout mgbl = new GridBagLayout();
		mgbl.columnWidths = new int[] { frameW };
		mgbl.rowHeights = new int[] { titleH, frameH - titleH };
		mgbl.columnWeights = new double[] { 1.0 };
		mgbl.rowWeights = new double[] { 0.0, 1.0 };
		main.setLayout(mgbl);
		setContentPane(main);

		bar = new TitleBar(this);
		addGBC(main, bar, 0);

		JPanel around = new JPanel();
		GridBagLayout agbl = new GridBagLayout();
		agbl.columnWidths = new int[] { frameW };
		agbl.rowHeights = new int[] { centerMaxH, footerH };
		agbl.columnWeights = new double[] { 1.0 };
		agbl.rowWeights = new double[] { 1.0, 1.0 };
		around.setLayout(agbl);
		addGBC(main, around, 1);

		JScrollPane centerPort = new JScrollPane();
		bar.setTabPort(centerPort);
		addGBC(around, centerPort, 0);

		JScrollPane footer = new JScrollPane();
		addGBC(around, footer, 1);

		footerView = new JPanel();
		footer.setViewportView(footerView);
		GridBagLayout gbl_view = new GridBagLayout();
		footerView.setLayout(gbl_view);

		JPanel controls = new JPanel();
		footer.setColumnHeaderView(controls);
		GridBagLayout controlsgbl = new GridBagLayout();
		controlsgbl.columnWeights = new double[] { 1.0, 0.0 };
		controls.setLayout(controlsgbl);

		JButton toggle = new JButton(UP);
		GridBagConstraints gbc_toggle = new GridBagConstraints();
		gbc_toggle.anchor = GridBagConstraints.LINE_START;
		gbc_toggle.insets = new Insets(0, 10, 0, 10);
		gbc_toggle.gridx = 0;
		gbc_toggle.gridy = 0;
		controls.add(toggle, gbc_toggle);
		toggle.setBorder(null);
		toggle.setFocusPainted(false);

//		infoLabel = new JLabel("Toggle info bar");
//		GridBagConstraints gbc_action = new GridBagConstraints();
//		gbc_action.anchor = GridBagConstraints.LINE_END;
//		gbc_action.insets = new Insets(0, 10, 0, 10);
//		gbc_action.gridx = 1;
//		gbc_action.gridy = 0;
//		controls.add(infoLabel, gbc_action);
//		infoLabel.setBorder(null);

		toggle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (toggle.getText().equals(DOWN)) {
//					agbl.rowHeights = new int[] { centerMaxH, footerH };
					agbl.rowHeights = new int[] { centerMaxH, footerH };
					toggle.setText(UP);
//					around.revalidate();
//					around.repaint();
				} else {
					int newH = (int) (frameH * 0.7);
					int newF = (int) (frameH * 0.3);
//					agbl.rowHeights = new int[] { centerMinH, footerH };
					agbl.rowHeights = new int[] { newH, newF };
					toggle.setText(DOWN);
//					around.revalidate();
//					around.repaint();
				}
				around.revalidate();
			}
		});
	}

	private void addGBC(JComponent father, JComponent child, int gridy) {
		GridBagConstraints gbl = new GridBagConstraints();
		gbl.fill = GridBagConstraints.BOTH;
		gbl.gridx = 0;
		gbl.gridy = gridy;
		father.add(child, gbl);
	}

	private void addGBC(JComponent father, JComponent child, int gridx, int gridy, Insets insets) {
		GridBagConstraints gbl = new GridBagConstraints();
		gbl.insets = insets;
		gbl.gridx = gridx;
		gbl.gridy = gridy;
	}
}
