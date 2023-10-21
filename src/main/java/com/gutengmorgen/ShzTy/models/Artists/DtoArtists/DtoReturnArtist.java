package com.gutengmorgen.ShzTy.models.Artists.DtoArtists;

import java.sql.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.gutengmorgen.ShzTy.models.Artists.Artist;
import com.gutengmorgen.ShzTy.views.GUIType;
import com.gutengmorgen.ShzTy.views.ForGUI;

public record DtoReturnArtist(
	@ForGUI(name = "ID:")
	Long id,
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
	@ForGUI(name = "Albums:", type = GUIType.SINGLE_OPTION)
        List<String> albums,
	@ForGUI(name = "Languages:", type = GUIType.SINGLE_OPTION)
        Set<String> languages,
	@ForGUI(name = "Genres:", type = GUIType.SINGLE_OPTION)
        Set<String> genres) {
    
    public DtoReturnArtist(Artist artist) {
	this(
		artist.getId(),
		artist.getName(),
		artist.getBorn_date(),
		artist.getGender(),
		artist.getCountry(),
		artist.getBiography(),
		artist.getAlbums().stream().map(al -> al.getTitle()).toList(),
		artist.getLanguages().stream().map(language -> language.getName()).collect(Collectors.toSet()),
		artist.getGenres().stream().map(genre -> genre.getName()).collect(Collectors.toSet())
	);
    }
}