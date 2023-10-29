package com.gutengmorgen.ShzTy.views.ForComponents;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.util.regex.Pattern;

public class FilterSingleOption extends DocumentFilter {
    private final Pattern pattern = Pattern.compile("");

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        super.remove(fb, offset, length);
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        super.insertString(fb, offset, string, attr);
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if(!text.contains(",") || !text.contains(";"))
            super.replace(fb, offset, length, text, attrs);
    }
}
