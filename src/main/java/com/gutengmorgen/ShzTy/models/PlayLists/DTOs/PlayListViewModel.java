package com.gutengmorgen.ShzTy.models.PlayLists.DTOs;

import com.gutengmorgen.ShzTy.models.PlayLists.PlayList;
import com.gutengmorgen.ShzTy.services.extras.ReturnDTO;
import com.gutengmorgen.ShzTy.views.Extras.ForGUI;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayListViewModel implements ReturnDTO {
	@ForGUI(name = "ID")
	Long id;
	@ForGUI(name = "Name")
	String name;
	
	public PlayListViewModel(PlayList pl) {
		this.id = pl.getId();
		this.name = pl.getTile();
	}
}
