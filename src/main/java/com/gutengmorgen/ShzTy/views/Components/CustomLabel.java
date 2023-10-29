package com.gutengmorgen.ShzTy.views.Components;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class CustomLabel extends JLabel {
	private JComponent child;
	private JScrollPane port;

	public CustomLabel(String text, JComponent child, JScrollPane port) {
		setText(text);
		this.child = child;
		this.setPort(port);
	}
	
	public CustomLabel(String text, JComponent child) {
		setText(text);
		this.child = child;
	}

	public CustomLabel(String text) {
		setText(text);
	}

	public JComponent getChild() {
		return child;
	}

	public void setChild(JComponent child) {
		this.child = child;
	}

	public void childVisibility(boolean visibility) {
		if (child == null)
			throw new RuntimeException("El hijo del CustomLable es null ");

		this.child.setVisible(visibility);
	}

	public JScrollPane getPort() {
		return port;
	}

	public void setPort(JScrollPane port) {
		this.port = port;
	}
}
