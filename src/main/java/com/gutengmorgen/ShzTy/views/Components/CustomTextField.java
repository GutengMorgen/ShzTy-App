package com.gutengmorgen.ShzTy.views.Components;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;

import com.gutengmorgen.ShzTy.controller.MainController;
import com.gutengmorgen.ShzTy.views.Extras.ParmType;
import com.gutengmorgen.ShzTy.views.ForComponents.AutoComplete;
import com.gutengmorgen.ShzTy.views.ForComponents.FilterDateFormat;
import com.gutengmorgen.ShzTy.views.ForComponents.FilterOnlyNumbers;

public class CustomTextField extends JTextField {
    private static final long serialVersionUID = -4818068062887542415L;

    private ParmType type;
    private String useEntity;

    public CustomTextField(ParmType type) {
	this.type = type;

	if (type == ParmType.DATE) {
	    ((AbstractDocument) getDocument()).setDocumentFilter(new FilterDateFormat());
	    this.setToolTipText("Solo se permite fecha en este formato: yyyy-MM-dd");
	} else if (type == ParmType.DIGITS) {
	    ((AbstractDocument) getDocument()).setDocumentFilter(new FilterOnlyNumbers());
	    this.setToolTipText("Solo se permite numeros");
	}
    }

    public CustomTextField(ParmType type, String useEntity) {
	this.type = type;
	this.useEntity = useEntity;

	if (type == ParmType.SINGLE_OPTION) {
	    MainController controller = new MainController();
	    new AutoComplete(this, ParmType.SINGLE_OPTION, controller.lookup(useEntity));
	    this.setToolTipText("Solo se permite seleccionar una opcion");
	} else if (type == ParmType.MULTI_OPTION) {
	    MainController controller = new MainController();
	    new AutoComplete(this, ParmType.MULTI_OPTION, controller.lookup(useEntity));
	    this.setToolTipText("Utilizar < , > para agregar mas opciones");
	}
    }

    public ParmType getType() {
	return type;
    }

    public void setType(ParmType type) {
	this.type = type;
    }

    public Object TextToType() {
	Object obj = null;
	String text = getText();
	
	if(text.isBlank())
	    return null;
	
	if (type == ParmType.DATE) {
	    try {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		obj = new Date(format.parse(text).getTime());
	    } catch (java.text.ParseException e) {
		e.printStackTrace();
	    }
	} else if (type == ParmType.DIGITS) {
	    obj = Integer.parseInt(text);
	} else if (type == ParmType.SINGLE_OPTION) {
	    obj = textToSet(text.trim());
	} else if (type == ParmType.MULTI_OPTION) {
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
