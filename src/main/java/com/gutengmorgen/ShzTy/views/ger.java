package com.gutengmorgen.ShzTy.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class ger extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ger dialog = new ger();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ger() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(10, 71, 414, 39);
			contentPanel.add(panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{313, 56, 0, 0};
			gbl_panel.rowHeights = new int[]{35, 0};
			gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JPanel panel_1 = new JPanel();
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
				gbc_panel_1.insets = new Insets(0, 0, 0, 5);
				gbc_panel_1.gridx = 0;
				gbc_panel_1.gridy = 0;
				panel.add(panel_1, gbc_panel_1);
			}
			{
				JButton btnNewButton = new JButton("-");
				GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
				gbc_btnNewButton.anchor = GridBagConstraints.WEST;
				gbc_btnNewButton.fill = GridBagConstraints.VERTICAL;
				gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
				gbc_btnNewButton.gridx = 1;
				gbc_btnNewButton.gridy = 0;
				panel.add(btnNewButton, gbc_btnNewButton);
			}
			{
				JButton btnNewButton_1 = new JButton("x");
				GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
				gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnNewButton_1.gridx = 2;
				gbc_btnNewButton_1.gridy = 0;
				panel.add(btnNewButton_1, gbc_btnNewButton_1);
			}
		}
		
		JScrollPane footerPanel = new JScrollPane();
		footerPanel.setBounds(10, 132, 414, 71);
		contentPanel.add(footerPanel);
		
		JPanel view = new JPanel();
		footerPanel.setViewportView(view);
		GridBagLayout gbl_view = new GridBagLayout();
		gbl_view.columnWidths = new int[]{0};
		gbl_view.rowHeights = new int[]{0};
		gbl_view.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_view.rowWeights = new double[]{Double.MIN_VALUE};
		view.setLayout(gbl_view);
		
		JPanel controls = new JPanel();
		footerPanel.setColumnHeaderView(controls);
		GridBagLayout gbl_controls = new GridBagLayout();
		gbl_controls.columnWidths = new int[]{155, 130, 108, 0};
		gbl_controls.rowHeights = new int[]{15, 0};
		gbl_controls.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_controls.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		controls.setLayout(gbl_controls);
		
		JButton toggle = new JButton("<---->");
		GridBagConstraints gbc_toggle = new GridBagConstraints();
		gbc_toggle.insets = new Insets(0, 0, 0, 5);
		gbc_toggle.gridx = 0;
		gbc_toggle.gridy = 0;
		controls.add(toggle, gbc_toggle);
		toggle.setBorder(null);
		
		JButton action = new JButton("action");
		GridBagConstraints gbc_action = new GridBagConstraints();
		gbc_action.insets = new Insets(0, 0, 0, 5);
		gbc_action.gridx = 1;
		gbc_action.gridy = 0;
		controls.add(action, gbc_action);
		action.setBorder(null);
		
		JButton cancel = new JButton("cancel");
		GridBagConstraints gbc_cancel = new GridBagConstraints();
		gbc_cancel.gridx = 2;
		gbc_cancel.gridy = 0;
		controls.add(cancel, gbc_cancel);
		cancel.setBorder(null);
		{
			JPanel buttonPane = new JPanel();
			FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.RIGHT);
			fl_buttonPane.setVgap(0);
			buttonPane.setLayout(fl_buttonPane);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JLabel lblNewLabel = new JLabel("fweff");
				buttonPane.add(lblNewLabel);
			}
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
