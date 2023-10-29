package com.gutengmorgen.ShzTy.views.Components;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;

import com.gutengmorgen.ShzTy.controller.MainController;
import com.gutengmorgen.ShzTy.views.AllEntities;
import com.gutengmorgen.ShzTy.views.Extras.VarType;
import com.gutengmorgen.ShzTy.views.ForComponents.AutoComplete;
import com.gutengmorgen.ShzTy.views.ForComponents.FilterDateFormat;
import com.gutengmorgen.ShzTy.views.ForComponents.FilterOnlyNumbers;
import com.gutengmorgen.ShzTy.views.ForComponents.FilterSingleOption;

public class CustomTextField extends JTextField {
	private static final long serialVersionUID = -4818068062887542415L;

	private final VarType varType;
	private final AllEntities useEntity;

	public CustomTextField(VarType varType, AllEntities entity) {
		this.varType = varType;
		this.useEntity = entity;
		String label = "Ingrese el texto";

		if (varType == VarType.SINGLE_OPTION) {
			((AbstractDocument) getDocument()).setDocumentFilter(new FilterSingleOption());
			new AutoComplete(this, VarType.SINGLE_OPTION, MainController.lookup(entity));
			label = "Solo se permite seleccionar una opcion";
		} else if (varType == VarType.MULTI_OPTION) {
			new AutoComplete(this, VarType.MULTI_OPTION, MainController.lookup(entity));
			label = "Utilizar < , > para agregar mas opciones";
		} else if (varType == VarType.DATE) {
			((AbstractDocument) getDocument()).setDocumentFilter(new FilterDateFormat());
			label = "Solo se permite fecha en este formato: yyyy-MM-dd";
		} else if (varType == VarType.DIGITS) {
			((AbstractDocument) getDocument()).setDocumentFilter(new FilterOnlyNumbers());
			label = "Solo se permite numeros";
		}

		setToolTipText(label);
	}

	public Object textToDataType() {
		String text = getText().trim();

		if (text.isBlank())
			return null;

		return switch (varType) {
			case TEXT, SIMPLE_TEXT -> text;
			case DATE -> textToDate(text);
			case DIGITS -> Integer.parseInt(text);
			case SINGLE_OPTION -> findEntity(text);
			case MULTI_OPTION -> textToSet(text);
		};
	}

	private Date textToDate(String txt) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			return new Date(format.parse(txt).getTime());
		} catch (java.text.ParseException e) {
			throw new RuntimeException("Formato de fecha invalido: " + e.getMessage());
		}
	}

	private Set<Long> textToSet(String txt) {
		Set<Long> set = new HashSet<>();

		for (String t : txt.split(",")) {
			set.add(findEntity(t));
		}
		return set;
	}

	private Long findEntity(String txt) {
		return MainController.getIdByName(txt, useEntity);
	}
}
