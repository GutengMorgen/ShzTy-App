package com.gutengmorgen.ShzTy.views.ForComponents;

import java.util.regex.Pattern;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class FilterOnlyNumbers extends DocumentFilter {
    private final Pattern pattern = Pattern.compile("^\\d+$");
    
    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
	super.remove(fb, offset, length);
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
	    throws BadLocationException {
	if (isValid(text)) {
            super.replace(fb, offset, length, text, attrs);
        }
    }

    private boolean isValid(String text) {
        return pattern.matcher(text).matches();
    }
}
