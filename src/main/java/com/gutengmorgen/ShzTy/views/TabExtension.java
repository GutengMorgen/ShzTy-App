package com.gutengmorgen.ShzTy.views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.Font;

public class TabExtension extends JFrame {
	private static final long serialVersionUID = 1L;
	private final int frameW;
	private final int frameH;
	private static final int titleH = 25;
	private static final int footerH = 20;
	private int centerMaxH = 0;
	private static final int centerMinH = 50;
	public TitleBar title;
	public JScrollPane center;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					TabExtension frame = new TabExtension(900, 500);
					JPanel panel = new JPanel();
					JTextField field = new JTextField(21);
					panel.add(field);
					frame.title.addTab("something", panel, frame.center);
					
					JTextArea textArea = new JTextArea();
					frame.title.addTab("text", textArea, frame.center);
					
					JTextArea textArea2 = new JTextArea();
					frame.title.addTab("another Text", textArea2, frame.center);

					frame.title.setActiveTab(0);
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
		setBounds(100, 100, frameW, frameH);

		JPanel main = new JPanel();
		main.setBorder(new EmptyBorder(2, 2, 2, 2));
		GridBagLayout mgbl = new GridBagLayout();
		mgbl.columnWidths = new int[] { frameW };
		mgbl.rowHeights = new int[] { titleH, frameH - titleH };
		mgbl.columnWeights = new double[] { 1.0 };
		mgbl.rowWeights = new double[] { 0.0, 1.0 };
		main.setLayout(mgbl);
		setContentPane(main);

		title = new TitleBar(this);
		addGBC(main, title, 0);

		JPanel around = new JPanel();
		GridBagLayout agbl = new GridBagLayout();
		agbl.columnWidths = new int[] { frameW };
		agbl.rowHeights = new int[] { centerMaxH, footerH };
		agbl.columnWeights = new double[] { 1.0 };
		agbl.rowWeights = new double[] { 1.0, 1.0 };
		around.setLayout(agbl);
		addGBC(main, around, 1);

		center = new JScrollPane();
		center.setBorder(null);
		addGBC(around, center, 0);

		JPanel footer = new JPanel();
		footer.setLayout(null);
		addGBC(around, footer, 1);

		JButton dBtn = new JButton("<---->");
		dBtn.setBorder(null);
		dBtn.setFont(new Font("Consolas", Font.PLAIN, 10));
		dBtn.setBounds(frameW - 100 - 2, 0, 100, 20);
		dBtn.setContentAreaFilled(false);
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
