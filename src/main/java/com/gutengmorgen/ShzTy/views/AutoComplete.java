package com.gutengmorgen.ShzTy.views;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

public class AutoComplete {
    private JTextComponent component;
    private Function<String, List<String>> lookup;
    private List<String> results;
    private JWindow popup;
    private JList<String> list;
    private DefaultListModel<String> model;
    private String split = ",";
    private GUIType optionMode = GUIType.SINGLE_OPTION;

    public AutoComplete(JTextComponent component, GUIType mode, Function<String,List<String>> lookup) {
	this.component = component;
	this.optionMode = mode;
	this.lookup = lookup;
	DefaultFunctions();
    }

    public AutoComplete(JTextComponent component, Function<String,List<String>> lookup) {
	this.component = component;
	this.lookup = lookup;
	DefaultFunctions();
    }

    private void DefaultFunctions() {
	this.results = new ArrayList<>();
	model = new DefaultListModel<String>();
	list = new JList<String>(model);

	popup = new JWindow(SwingUtilities.getWindowAncestor(component));
	popup.setType(Window.Type.POPUP);
	popup.setFocusableWindowState(false);
	popup.setAlwaysOnTop(true);
	popup.add(new JScrollPane(list) {
	    private static final long serialVersionUID = -2387472513291367137L;

	    @Override
	    public Dimension getPreferredSize() {
		final Dimension ps = super.getPreferredSize();
		ps.width = component.getWidth();
		return ps;
	    }
	});
	
	component.getDocument().addDocumentListener(new DocumentListener() {

	    @Override
	    public void removeUpdate(DocumentEvent e) {
		documentChanged();
	    }

	    @Override
	    public void insertUpdate(DocumentEvent e) {
		documentChanged();
	    }

	    @Override
	    public void changedUpdate(DocumentEvent e) {
		documentChanged();
	    }
	});
	component.addFocusListener(new FocusListener() {

	    @Override
	    public void focusGained(FocusEvent e) {
		if (results.size() > 0)
		    showAutocompletePopup();
	    }

	    @Override
	    public void focusLost(FocusEvent e) {
		hideAutocompletePopup();
	    }

	});
	component.addKeyListener(new KeyListener() {

	    @Override
	    public void keyTyped(KeyEvent e) {
	    }

	    @Override
	    public void keyReleased(KeyEvent e) {
	    }

	    @Override
	    public void keyPressed(final KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
		    final int index = list.getSelectedIndex();
		    if (index != -1 && index > 0) {
			list.setSelectedIndex(index - 1);
		    }
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
		    final int index = list.getSelectedIndex();
		    if (index != -1 && list.getModel().getSize() > index + 1) {
			list.setSelectedIndex(index + 1);
		    }
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		    replaceText((String) list.getSelectedValue());
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
		    hideAutocompletePopup();
		}
	    }
	});
    }
    
    private void showAutocompletePopup() {
	final Point p = component.getLocationOnScreen();
	popup.setLocation(p.x, p.y + component.getHeight());
	popup.setVisible(true);
    }

    private void hideAutocompletePopup() {
	popup.setVisible(false);
    }

    private int[] findIndex(String txt, int caret) {
	int s = txt.lastIndexOf(split, caret - 1);
	int e = txt.indexOf(split, caret);

	if (s >= 0)
	    s++;
	else
	    s = 0;

	if (e == -1)
	    e = txt.length();

	return new int[] { s, e };
    }

    private String textToApply() {
	if (optionMode == GUIType.MULTI_OPTION) {
	    int[] indexes = findIndex(component.getText(), component.getCaretPosition());
	    return component.getText().substring(indexes[0], indexes[1]);
	} else
	    return component.getText();
    }

    private void replaceText(String text) {
	int[] indexes = findIndex(component.getText(), component.getCaretPosition());

	String result = component.getText().substring(0, indexes[0]) + text + component.getText().substring(indexes[1]);
	component.setText(result);
	component.setCaretPosition(indexes[0] + text.length());
    }

    private void documentChanged() {
	SwingUtilities.invokeLater(() -> {
	    // Updating results list
	    results.clear();
	    results.addAll(lookup.apply(textToApply()));

	    // Updating list view
	    model.clear();
	    model.addAll(results);

	    list.setVisibleRowCount(Math.min(results.size(), 10));

	    // Ensure autocomplete popup has correct size
	    popup.pack();

	    if (results.size() > 0) {
		list.setSelectedIndex(0);
		showAutocompletePopup();
	    } else {
		hideAutocompletePopup();
	    }
	});
    }

    public void setOptionMode(GUIType mode) {
	this.optionMode = mode;
    }

    public void setSlipt(String split) {
	this.split = split;
    }

}
