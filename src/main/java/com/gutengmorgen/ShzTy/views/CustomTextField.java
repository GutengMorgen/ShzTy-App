package com.gutengmorgen.ShzTy.views;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Set;

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
	    this.setToolTipText("Solo se permite fecha en este formato: yyyy-MM-dd");
	} else if (type == GUIType.DIGITS) {
	    ((AbstractDocument) getDocument()).setDocumentFilter(new FilterOnlyNumbers());
	    this.setToolTipText("Solo se permite numeros");
	} else if (type == GUIType.SINGLE_OPTION) {
	    MainController controller = new MainController();
	    new AutoComplete(this, GUIType.SINGLE_OPTION, controller.lookup());
	    this.setToolTipText("Solo se permite seleccionar una opcion");
	} else if (type == GUIType.MULTI_OPTION) {
	    MainController controller = new MainController();
	    new AutoComplete(this, GUIType.MULTI_OPTION, controller.lookup());
	    this.setToolTipText("Utilizar < , > para agregar mas opciones");
	}
    }

    public GUIType getType() {
	return type;
    }

    public void setType(GUIType type) {
	this.type = type;
    }

    public Object TextToType() {
	Object obj = null;
	String text = getText();
	if (type == GUIType.DATE) {
	    try {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		obj = new Date(format.parse(text).getTime());
	    } catch (java.text.ParseException e) {
		e.printStackTrace();
	    }
	} else if (type == GUIType.DIGITS) {
	    obj = Integer.parseInt(text);
	} else if (type == GUIType.SINGLE_OPTION) {
	    obj = Set.of(1L);
	} else if (type == GUIType.MULTI_OPTION) {
	    obj = Set.of(2L, 3L);
	} else {
	    obj = text;
	}
	return obj;
    }
}
