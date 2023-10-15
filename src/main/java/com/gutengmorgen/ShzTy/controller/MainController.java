package com.gutengmorgen.ShzTy.controller;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;

import com.gutengmorgen.ShzTy.models.Genres.Genre;
import com.gutengmorgen.ShzTy.models.Languages.Language;
import com.gutengmorgen.ShzTy.services.GenreService;
import com.gutengmorgen.ShzTy.services.LanguageService;

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
}
