package com.gutengmorgen.ShzTy.views.Components;

import javax.swing.JComponent;
import javax.swing.JLabel;

public class CustomLabel extends JLabel {
	private JComponent child;

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
		if (child != null)
			this.child.setVisible(visibility);
	}
}
