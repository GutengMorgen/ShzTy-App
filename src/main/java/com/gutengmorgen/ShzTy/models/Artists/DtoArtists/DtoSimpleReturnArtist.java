package com.gutengmorgen.ShzTy.models.Artists.DtoArtists;

import java.sql.Date;
import java.util.Set;

import com.gutengmorgen.ShzTy.models.Artists.Artist;

public record DtoSimpleReturnArtist(
	String Name,
        String Gender,
        String Country,
        int CountTracks, //cuando se haga click en la celda, conducira a tracks y buscara todos los track de este artista
        int CountAlbums,
        Set<String> Languages,
        Set<String> Genres) {
    
    public DtoSimpleReturnArtist(Artist artist) {
	this(
		artist.getName(),
		artist.getGender(),
		artist.getCountry(),
		artist.getAllTracks(), 
		artist.getAllAlbums(),
		Set.of(),
		Set.of());
    }
    
}
