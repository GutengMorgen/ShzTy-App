package com.gutengmorgen.ShzTy.models.Artists.DtoArtists;

import java.sql.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.gutengmorgen.ShzTy.models.Artists.Artist;

public record DtoReturnArtist(
	Long id,
	String name,
	Date bornDate,
	String gender,
        String country,
        String biography,
        List<String> albums,
        Set<String> languages,
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