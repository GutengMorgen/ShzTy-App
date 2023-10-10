package com.gutengmorgen.ShzTy.controller;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import com.gutengmorgen.ShzTy.models.Genres.Genre;
import com.gutengmorgen.ShzTy.services.GenreService;

public class MainController {
    private final GenreService genreService = new GenreService();

    public JScrollPane getGenreScrollList() {
	DefaultListModel<Genre> model = new DefaultListModel<>();
	for (Genre g : genreService.getAllGenres()) {
	    model.addElement(g);
	}
	JList<Genre> list = new JList<>(model);
	JScrollPane sp = new JScrollPane();
	sp.setViewportView(list);
	return sp;
    }
}
