package com.gutengmorgen.ShzTy.models.Artists.DtoArtists;

import java.sql.Date;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.gutengmorgen.ShzTy.views.ForGUI;
import com.gutengmorgen.ShzTy.views.PropertieType;

public record DtoCreateArtist(
	@ForGUI(name = "Name*:")
        @NotBlank(message = "Name is required")
        String name,
	@ForGUI(name = "Born Date:")
        Date bornDate,
	@ForGUI(name = "Gender*:")
        @NotBlank(message = "Gender is required")
        String gender,
	@ForGUI(name = "Country:")
        String country,
	@ForGUI(name = "Biography:")
        String biography,
	@ForGUI(name = "Languages:", type = PropertieType.MULTI_OPTION)
        Set<Long> languageIDs,
	@ForGUI(name = "Genres:", type = PropertieType.MULTI_OPTION)
        Set<Long> genreIDs) {
}
