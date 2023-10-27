package com.gutengmorgen.ShzTy.models.Genres.DTOs;

import com.gutengmorgen.ShzTy.models.Genres.Genre;
import com.gutengmorgen.ShzTy.services.extras.ReturnDTO;
import com.gutengmorgen.ShzTy.views.Extras.ForGUI;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenreViewModel implements ReturnDTO {
	@ForGUI(name = "ID")
	Long id;
	@ForGUI(name = "Name")
	String name;
	
	public GenreViewModel(Genre g) {
		this.id = g.getId();
		this.name = g.getName();
	}
}
