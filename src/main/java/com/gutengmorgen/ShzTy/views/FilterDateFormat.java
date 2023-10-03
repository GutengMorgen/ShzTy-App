package com.gutengmorgen.ShzTy.views;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class FilterDateFormat extends DocumentFilter {
    int count = 0;

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
	super.remove(fb, offset, length);
	count--;
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
	    throws BadLocationException {
//	String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
//	System.out.println(fb.getDocument().getText(0, fb.getDocument().getLength()));
	
	//-12346790
	//12346790-
	int textLength = fb.getDocument().getLength();
	if(textLength == 10)
	    return;
	else if(textLength == 4)
	    fb.replace(offset, length, "-" + text, attrs);
	else if(textLength == 7)
	    fb.replace(offset, length, "-" + text, attrs);
	else
	    fb.replace(offset, length, text, attrs);
    }
    
}
