package com.gutengmorgen.ShzTy.views;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;

import com.gutengmorgen.ShzTy.controller.MainController;

public class CustomTextField extends JTextField {
    private static final long serialVersionUID = -4818068062887542415L;

    private GUIType type;
    private String useEntity;

    public CustomTextField(GUIType type) {
	this.type = type;

	if (type == GUIType.DATE) {
	    ((AbstractDocument) getDocument()).setDocumentFilter(new FilterDateFormat());
	    this.setToolTipText("Solo se permite fecha en este formato: yyyy-MM-dd");
	} else if (type == GUIType.DIGITS) {
	    ((AbstractDocument) getDocument()).setDocumentFilter(new FilterOnlyNumbers());
	    this.setToolTipText("Solo se permite numeros");
	}
    }

    public CustomTextField(GUIType type, String useEntity) {
	this.type = type;
	this.useEntity = useEntity;

	if (type == GUIType.SINGLE_OPTION) {
	    MainController controller = new MainController();
	    new AutoComplete(this, GUIType.SINGLE_OPTION, controller.lookup(useEntity));
	    this.setToolTipText("Solo se permite seleccionar una opcion");
	} else if (type == GUIType.MULTI_OPTION) {
	    MainController controller = new MainController();
	    new AutoComplete(this, GUIType.MULTI_OPTION, controller.lookup(useEntity));
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
	
	if(text.isBlank())
	    return null;
	
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
	    obj = textToSet(text.trim());
	} else if (type == GUIType.MULTI_OPTION) {
	    obj = textToSet(text.trim());
	} else {
	    obj = text;
	}
	return obj;
    }

    private Set<Long> textToSet(String text) {
	Set<Long> set = new HashSet<>();

	if (!text.contains(",")) {
	    set.add(findEntity(text));
	} else {
	    for (String t : text.split(",")) {
		set.add(findEntity(t));
	    }
	}

	return set;
    }

    private Long findEntity(String t) {
	MainController c = new MainController();
	return c.getEntityId(t, useEntity);
    }
}
