package com.gutengmorgen.ShzTy.models.AlbumFormats.DTOs;

import com.gutengmorgen.ShzTy.models.AlbumFormats.AlbumFormat;
import com.gutengmorgen.ShzTy.services.extras.ReturnDTO;
import com.gutengmorgen.ShzTy.views.Extras.ForGUI;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlbumFormatViewModel implements ReturnDTO{
	@ForGUI(name = "ID")
	Long id;
	@ForGUI(name = "Name")
	String name;
	
	public AlbumFormatViewModel(AlbumFormat af) {
		this.id = af.getId();
		this.name = af.getName();
	}
}
