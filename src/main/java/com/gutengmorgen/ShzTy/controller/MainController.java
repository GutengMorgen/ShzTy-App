package com.gutengmorgen.ShzTy.controller;

import java.util.List;
import java.util.function.Function;

import com.gutengmorgen.ShzTy.services.GenreService;
import com.gutengmorgen.ShzTy.services.LanguageService;

public class MainController {

    public Function<String, List<String>> lookup(String useEntity) {
	List<String> values = null;
	Function<String, List<String>> lookup;

	if (useEntity.equals("Language")) {
	    LanguageService s = new LanguageService();
	    values = s.getAllLanguages().stream().map(l -> l.getName()).toList();
	} else if (useEntity.equals("Genre")) {
	    GenreService s = new GenreService();
	    values = s.getAllGenres().stream().map(g -> g.getName()).toList();
	} else {
	    throw new RuntimeException("No se encontro la entidad relacionada");
	}

	final List<String> finalValues = values;

	lookup = text -> finalValues.stream()
		.filter(v -> v.toLowerCase().contains(text.toLowerCase()) && !v.equals(text)).toList();

	return lookup;
    }

    public Long getEntityId(String t, String useEntity) {
	if (useEntity.equals("Language")) {
	    LanguageService s = new LanguageService();
	    return s.getIdByName(t);
	} else if (useEntity.equals("Genre")) {
	    GenreService s = new GenreService();
	    return s.getIdByName(t);
	} else
	    throw new RuntimeException("No se encontro la entidad relacionada " + useEntity);
    }
}
