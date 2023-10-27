package com.gutengmorgen.ShzTy.models.Artists.DtoArtists;

import java.util.Set;
import java.util.stream.Collectors;

import com.gutengmorgen.ShzTy.models.Artists.Artist;
import com.gutengmorgen.ShzTy.services.extras.ReturnDTO;
import com.gutengmorgen.ShzTy.views.Extras.ForGUI;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArtistViewModel implements ReturnDTO {
	@ForGUI(name = "ID")
	Long id;
	@ForGUI(name = "Name")
	String name;
	@ForGUI(name = "Country")
	String country;
	@ForGUI(name = "Languages")
	Set<String> languages;
	@ForGUI(name = "Genres")
	Set<String> genres;

	public ArtistViewModel(Artist a) {
		this.id = a.getId();
		this.name = a.getName();
		this.country = a.getCountry();
		this.languages = a.getLanguages().stream().map(l -> l.getName()).collect(Collectors.toSet());
		this.genres = a.getGenres().stream().map(g -> g.getName()).collect(Collectors.toSet());
	}
}
