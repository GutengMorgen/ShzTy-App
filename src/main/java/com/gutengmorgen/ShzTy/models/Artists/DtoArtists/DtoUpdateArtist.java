package com.gutengmorgen.ShzTy.models.Artists.DtoArtists;

import java.sql.Date;
import java.util.Set;

import com.gutengmorgen.ShzTy.views.ForGUI;
import com.gutengmorgen.ShzTy.views.PropertieType;

public record DtoUpdateArtist(
	@ForGUI(name = "Name:")
        String name,
	@ForGUI(name = "Born Date:")
        Date bornDate,
	@ForGUI(name = "Gender:")
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
