package com.gutengmorgen.ShzTy.models.Artists.DtoArtists;

import java.sql.Date;
import java.util.Set;

import com.gutengmorgen.ShzTy.views.ForGUI;
import com.gutengmorgen.ShzTy.views.GUIType;

public record DtoUpdateArtist(
	@ForGUI(name = "Name:")
        String name,
	@ForGUI(name = "Born Date:", type = GUIType.DATE)
        Date bornDate,
	@ForGUI(name = "Gender:")
        String gender,
	@ForGUI(name = "Country:")
        String country,
	@ForGUI(name = "Biography:")
        String biography,
	@ForGUI(name = "Languages:", type = GUIType.MULTI_OPTION, useEntity = "Language")
        Set<Long> languageIDs,
	@ForGUI(name = "Genres:", type = GUIType.SINGLE_OPTION, useEntity = "Genre")
        Set<Long> genreIDs) {

    public DtoUpdateArtist() {
	this(null, null, null, null, null, null, null);
    }
    
}
