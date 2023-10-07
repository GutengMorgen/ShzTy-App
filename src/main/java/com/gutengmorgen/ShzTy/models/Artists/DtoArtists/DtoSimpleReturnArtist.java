package com.gutengmorgen.ShzTy.models.Artists.DtoArtists;

import java.util.Set;
import java.util.stream.Collectors;

import com.gutengmorgen.ShzTy.models.Artists.Artist;

public record DtoSimpleReturnArtist(
	String Name,
        String Gender,
        String Country,
//        int CountTracks, //cuando se haga click en la celda, conducira a tracks y buscara todos los track de este artista
        int CountAlbums,
        Set<String> Languages,
        Set<String> Genres) {
    
    public DtoSimpleReturnArtist(Artist artist) {
	this(
		artist.getName(),
		artist.getGender(),
		artist.getCountry(),
		artist.albumsCount(),
		artist.getLanguages().stream().map(language -> language.getName()).collect(Collectors.toSet()),
		artist.getGenres().stream().map(genre -> genre.getName()).collect(Collectors.toSet())
	);
    }
}
