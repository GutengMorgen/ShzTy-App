package com.gutengmorgen.ShzTy.repositories;

import com.gutengmorgen.ShzTy.models.Genres.Genre;
import com.gutengmorgen.ShzTy.repositories.extras.RepositoryBase;

public class GenreRepository extends RepositoryBase<Genre> {

	public GenreRepository() {
		super(Genre.class);
	}

}
