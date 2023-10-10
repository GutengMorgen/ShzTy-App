package com.gutengmorgen.ShzTy.models.Artists.DtoArtists;

import java.util.Set;
import java.util.stream.Collectors;

import com.gutengmorgen.ShzTy.models.Artists.Artist;

public record DtoSimpleReturnArtist(
	Long id,
	String Name,
        String Country,
        Set<String> Languages,
        Set<String> Genres) {
    
    public DtoSimpleReturnArtist(Artist artist) {
	this(
		artist.getId(),
		artist.getName(),
		artist.getCountry(),
		artist.getLanguages().stream().map(language -> language.getName()).collect(Collectors.toSet()),
		artist.getGenres().stream().map(genre -> genre.getName()).collect(Collectors.toSet())
	);
    }
}
