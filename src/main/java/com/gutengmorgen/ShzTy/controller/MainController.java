package com.gutengmorgen.ShzTy.controller;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;

import com.gutengmorgen.ShzTy.models.Genres.Genre;
import com.gutengmorgen.ShzTy.models.Languages.Language;
import com.gutengmorgen.ShzTy.services.GenreService;
import com.gutengmorgen.ShzTy.services.LanguageService;
import com.gutengmorgen.ShzTy.views.AutocompleteField;

public class MainController {

    public JComponent entityInCB(String entityName) {
	JComponent comp = null;
	if (entityName.equals("Genre")) {
	    GenreService service = new GenreService();
	    DefaultComboBoxModel<Genre> lm = new DefaultComboBoxModel<>();
	    lm.addAll(service.getAllGenres());
	    comp = new JComboBox<>(lm);
	}
	else if (entityName.equals("Language")) {
	    LanguageService service = new LanguageService();
	    DefaultComboBoxModel<Language> lm = new DefaultComboBoxModel<>();
	    lm.addAll(service.getAllLanguages());
	    comp = new JComboBox<>(lm);
	}
	return comp;
    }
    
    public AutocompleteField multiField() {
	final List<String> values = Arrays.asList("Frame", "Dialog", "Label", "Tree", "Table", "List", "Field");

	final Function<String, List<String>> lookup = text -> values.stream()
		.filter(v -> v.toLowerCase().contains(text.toLowerCase()) && !v.equals(text)).toList();

	return new AutocompleteField(lookup);
    }
}
