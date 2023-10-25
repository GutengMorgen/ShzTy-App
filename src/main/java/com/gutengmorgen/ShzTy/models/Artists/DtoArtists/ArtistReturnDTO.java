package com.gutengmorgen.ShzTy.models.Artists.DtoArtists;

import java.sql.Date;
import java.util.Set;
import java.util.stream.Collectors;

import com.gutengmorgen.ShzTy.models.Artists.Artist;
import com.gutengmorgen.ShzTy.services.ReturnDTO;
import com.gutengmorgen.ShzTy.views.Extras.ForGUI;
import com.gutengmorgen.ShzTy.views.Extras.ParmType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArtistReturnDTO implements ReturnDTO {
    @ForGUI(name = "ID:")
    private Long id;
    @ForGUI(name = "Name:")
    String name;
    @ForGUI(name = "Born Date:")
    Date bornDate;
    @ForGUI(name = "Gender:")
    String gender;
    @ForGUI(name = "Country:")
    String country;
    @ForGUI(name = "Biography:")
    String biography;
    @ForGUI(name = "Albums:", type = ParmType.SINGLE_OPTION)
    Set<String> albums;
    @ForGUI(name = "Languages:", type = ParmType.SINGLE_OPTION)
    Set<String> languages;
    @ForGUI(name = "Genres:", type = ParmType.SINGLE_OPTION)
    Set<String> genres;

    public ArtistReturnDTO(Artist a) {
	this.id = a.getId();
	this.name = a.getName();
	this.bornDate = a.getBorn_date();
	this.gender = a.getGender();
	this.country = a.getCountry();
	this.biography = a.getBiography();
	this.albums = a.getAlbums().stream().map(al -> al.getTitle()).collect(Collectors.toSet());
	this.languages = a.getLanguages().stream().map(l -> l.getName()).collect(Collectors.toSet());
	this.genres = a.getGenres().stream().map(g -> g.getName()).collect(Collectors.toSet());
    }
}
