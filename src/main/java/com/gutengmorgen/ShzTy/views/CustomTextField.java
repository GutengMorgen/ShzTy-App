package com.gutengmorgen.ShzTy.views;

import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;

import com.gutengmorgen.ShzTy.controller.MainController;

public class CustomTextField extends JTextField {
    private static final long serialVersionUID = -4818068062887542415L;

    private GUIType type;

    public CustomTextField(GUIType type) {
	this.type = type;

	if (type == GUIType.DATE) {
	    ((AbstractDocument) getDocument()).setDocumentFilter(new FilterDateFormat());
	} else if (type == GUIType.DIGITS) {
	    ((AbstractDocument) getDocument()).setDocumentFilter(new FilterOnlyNumbers());
	} else if (type == GUIType.SINGLE_OPTION) {
	    MainController controller = new MainController();
	    AutoCompleteTest auto = new AutoCompleteTest(this, GUIType.SINGLE_OPTION);
	    auto.setLookup(controller.lookup());
	} else if (type == GUIType.MULTI_OPTION) {
	    MainController controller = new MainController();
	    AutoCompleteTest auto = new AutoCompleteTest(this, GUIType.SINGLE_OPTION);
	    auto.setLookup(controller.lookup());
	}
    }

    public GUIType getType() {
	return type;
    }

    public void setType(GUIType type) {
	this.type = type;
    }

}
