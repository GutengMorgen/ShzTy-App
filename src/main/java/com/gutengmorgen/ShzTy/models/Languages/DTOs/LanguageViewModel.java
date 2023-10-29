package com.gutengmorgen.ShzTy.models.Languages.DTOs;

import com.gutengmorgen.ShzTy.models.Languages.Language;
import com.gutengmorgen.ShzTy.services.extras.ReturnDTO;
import com.gutengmorgen.ShzTy.views.Extras.ForGUI;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LanguageViewModel implements ReturnDTO {
	@ForGUI(name = "ID")
	Long id;
	@ForGUI(name = "Name")
	String name;

	public LanguageViewModel(Language l) {
		this.id = l.getId();
		this.name = l.getName();
	}
}
