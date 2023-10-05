package com.gutengmorgen.ShzTy.views;

import java.util.regex.Pattern;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class FilterDateFormat extends DocumentFilter {
    private final Pattern pattern = Pattern.compile("^[0-9-]*$");

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
	super.remove(fb, offset, length);
    }

    @Deprecated
    public void replace1(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
	    throws BadLocationException {

	// -12346790
	// 12346790-
	// NOTE: formato: YYYY-MM-DD
	// TODO: agregar un limitador para el mes, dia y año
	// 5 y 8 son "-"
	if (isValid(text)) {
	    int textLength = fb.getDocument().getLength();

	    if (textLength == 4) {
		if (Integer.parseInt(text) <= 1)
		    fb.replace(offset, length, "-" + text, attrs);
		else
		    return;
	    }

	    else if (textLength == 6) {
		if (Integer.parseInt(text) <= 2)
		    fb.replace(offset, length, text, attrs);
		else
		    return;
	    }

	    else if (textLength == 7) {
		if (Integer.parseInt(text) <= 3)
		    fb.replace(offset, length, "-" + text, attrs);
		else
		    return;
	    }

	    else if (textLength == 10)
		return;
	    else
		fb.replace(offset, length, text, attrs);
	}

    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
	    throws BadLocationException {

	String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
	String newText = currentText.substring(0, offset) + text + currentText.substring(offset + length);

	if (isValid(newText)) {
	    if (newText.length() == 4 || newText.length() == 7) {
		super.replace(fb, offset, length, text + "-", attrs);
	    } else {
		super.replace(fb, offset, length, text, attrs);
	    }
	} else {
//	    System.out.println("invalid");
	}

    }

    private boolean isValid(String text) {
	return pattern.matcher(text).matches() && text.length() <= 10;
    }
}
