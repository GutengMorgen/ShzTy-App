package com.gutengmorgen.ShzTy.controller;

import java.util.Collection;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JScrollPane;

import com.gutengmorgen.ShzTy.models.Genres.Genre;
import com.gutengmorgen.ShzTy.repositories.LanguageRepository;
import com.gutengmorgen.ShzTy.services.GenreService;

public class MainController {
    private final GenreService genreService = new GenreService();
//    private final LanguageRepository languageRepository = new LanguageRepository();

    public JScrollPane genreScrollPane() {
	DefaultListModel<Genre> model = new DefaultListModel<>();
	for (Genre g : genreService.getAllGenres()) {
	    model.addElement(g);
	}
	JList<Genre> list = new JList<>(model);
	JScrollPane sp = new JScrollPane();
	sp.setViewportView(list);
	return sp;
    }

    public JComboBox<Genre> genreCB() {
	DefaultComboBoxModel<Genre> lm = new DefaultComboBoxModel<>();
	lm.addAll(genreService.getAllGenres());
	return new JComboBox<>(lm);
    }
}
