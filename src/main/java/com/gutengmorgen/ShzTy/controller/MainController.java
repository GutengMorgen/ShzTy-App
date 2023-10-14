package com.gutengmorgen.ShzTy.controller;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import com.gutengmorgen.ShzTy.models.Genres.Genre;
import com.gutengmorgen.ShzTy.repositories.LanguageRepository;
import com.gutengmorgen.ShzTy.services.GenreService;

public class MainController {
    private final GenreService genreService = new GenreService();
//    private final LanguageRepository languageRepository = new LanguageRepository();

    public JComboBox<Genre> genreCB() {
	DefaultComboBoxModel<Genre> lm = new DefaultComboBoxModel<>();
	lm.addAll(genreService.getAllGenres());
	return new JComboBox<>(lm);
    }
}
