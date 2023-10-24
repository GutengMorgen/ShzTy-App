package com.gutengmorgen.ShzTy.models.Artists.DtoArtists;

import java.util.Set;
import java.util.stream.Collectors;

import com.gutengmorgen.ShzTy.models.Artists.Artist;
import com.gutengmorgen.ShzTy.services.InsertDTO;
import com.gutengmorgen.ShzTy.services.ReturnDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArtistSimpleReturnDTO implements ReturnDTO {
    Long id;
    String name;
    String country;
    Set<String> Languages;
    Set<String> Genres;

    public ArtistSimpleReturnDTO(Artist a) {
	this.id = a.getId();
	this.name = a.getName();
	this.country = a.getCountry();
	this.Languages = a.getLanguages().stream().map(l -> l.getName()).collect(Collectors.toSet());
	this.Genres = a.getGenres().stream().map(g -> g.getName()).collect(Collectors.toSet());
    }
}
