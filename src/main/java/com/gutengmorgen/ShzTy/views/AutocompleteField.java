package com.gutengmorgen.ShzTy.views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import com.gutengmorgen.ShzTy.services.GenreService;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class AutocompleteField extends JTextField implements FocusListener, DocumentListener, KeyListener {

    private static final long serialVersionUID = 1L;

    /**
     * {@link Function} for text lookup. It simply returns {@link List} of
     * {@link String} for the text we are looking results for.
     */
    private final Function<String, List<String>> lookup;

    /**
     * {@link List} of lookup results. It is cached to optimize performance for more
     * complex lookups.
     */
    private final List<String> results;

    private final JWindow popup;

    private final JList list;

    private final ListModel model;

    private StringBuilder builder;

    public AutocompleteField(final Function<String, List<String>> lookup) {
	super();
	this.lookup = lookup;
	this.results = new ArrayList<>();

	final Window parent = SwingUtilities.getWindowAncestor(this);
	popup = new JWindow(parent);
	popup.setType(Window.Type.POPUP);
	popup.setFocusableWindowState(false);
	popup.setAlwaysOnTop(true);
	builder = new StringBuilder();

	model = new ListModel();
	list = new JList<String>(model);

	popup.add(new JScrollPane(list) {
	    @Override
	    public Dimension getPreferredSize() {
		final Dimension ps = super.getPreferredSize();
		ps.width = AutocompleteField.this.getWidth();
		return ps;
	    }
	});

	addFocusListener(this);
	getDocument().addDocumentListener(this);
	addKeyListener(this);
    }

    private void showAutocompletePopup() {
	final Point los = AutocompleteField.this.getLocationOnScreen();
	popup.setLocation(los.x, los.y + getHeight());
	popup.setVisible(true);
    }

    private void hideAutocompletePopup() {
	popup.setVisible(false);
    }

    @Override
    public void focusGained(final FocusEvent e) {
	SwingUtilities.invokeLater(() -> {
	    if (results.size() > 0) {
		showAutocompletePopup();
	    }
	});
    }

    @Override
    public void focusLost(final FocusEvent e) {
	SwingUtilities.invokeLater(this::hideAutocompletePopup);
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

    @Override
    public void keyTyped(final KeyEvent e) {
    }

    @Override
    public void keyReleased(final KeyEvent e) {
    }

    @Override
    public void insertUpdate(final DocumentEvent e) {
	documentChanged();
    }

    @Override
    public void removeUpdate(final DocumentEvent e) {
	documentChanged();
    }

    @Override
    public void changedUpdate(final DocumentEvent e) {
	documentChanged();
    }

    private String split = ",";

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
	int[] indexes = findIndex(getText(), getCaretPosition());
	return getText().substring(indexes[0], indexes[1]);
    }

    private void replaceText(String text) {
	int[] indexes = findIndex(getText(), getCaretPosition());

	String result = getText().substring(0, indexes[0]) + text + getText().substring(indexes[1]);
	setText(result);
	setCaretPosition(indexes[0] + text.length());
    }

    private void documentChanged() {
	SwingUtilities.invokeLater(() -> {
	    // Updating results list
	    results.clear();

	    results.addAll(lookup.apply(textToApply()));

	    // Updating list view
	    model.updateView();
	    list.setVisibleRowCount(Math.min(results.size(), 10));

	    // Selecting first result
	    if (results.size() > 0) {
		list.setSelectedIndex(0);
	    }

	    // Ensure autocomplete popup has correct size
	    popup.pack();

	    // Display or hide popup depending on the results
	    if (results.size() > 0) {
		showAutocompletePopup();
	    } else {
		hideAutocompletePopup();
	    }
	});
    }

    private class ListModel extends AbstractListModel {
	@Override
	public int getSize() {
	    return results.size();
	}

	@Override
	public Object getElementAt(final int index) {
	    return results.get(index);
	}

	/**
	 * Properly updates list view.
	 */
	public void updateView() {
	    super.fireContentsChanged(AutocompleteField.this, 0, getSize());
	}
    }

    public static void main(final String[] args) {
	final JFrame frame = new JFrame("Sample autocomplete field");
//	GenreService service = new GenreService();

	// Sample data list
//	final List<String> values = service.getAllGenres().stream().map(g -> g.getName()).toList();
//	
//	// Simple lookup based on our data list
//	final Function<String, List<String>> lookup = text -> values.stream()
//		.filter(v -> !text.isEmpty() && v.toLowerCase().contains(text.toLowerCase()) && !v.equals(text))
//		.toList();
//	final List<String> values = service.getAllGenres().stream().map(g -> g.getName()).toList();
	// Simple lookup based on our data list
	final List<String> values = Arrays.asList("Frame", "Dialog", "Label", "Tree", "Table", "List", "Field");

	final Function<String, List<String>> lookup = text -> values.stream()
		.filter(v -> v.toLowerCase().contains(text.toLowerCase()) && !v.equals(text)).toList();

	// Autocomplete field itself
	final AutocompleteField field = new AutocompleteField(lookup);
	field.setColumns(15);

	final JPanel border = new JPanel(new BorderLayout());
	border.setBorder(new EmptyBorder(50, 50, 50, 50));
	border.add(field);
	frame.add(border);

	frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	frame.pack();
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);
    }
}
