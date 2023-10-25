package com.gutengmorgen.ShzTy.models.Albums.DtoAlbums;

import java.sql.Date;
import java.util.Set;
import java.util.stream.Collectors;

import com.gutengmorgen.ShzTy.models.Albums.Album;
import com.gutengmorgen.ShzTy.services.ReturnDTO;
import com.gutengmorgen.ShzTy.views.Extras.ForGUI;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlbumSimpleReturnDTO implements ReturnDTO {
    @ForGUI(name = "ID")
    private Long id;
    @ForGUI(name = "Title")
    private String title;
    @ForGUI(name = "Release Date")
    private Date releaseDate;
    @ForGUI(name = "Genres")
    private Set<String> genres;

    public AlbumSimpleReturnDTO(Album al) {
	this.id = al.getId();
	this.title = al.getTitle();
	this.releaseDate = al.getRelease_date();
	this.genres = al.getGenres().stream().map(a -> a.getName()).collect(Collectors.toSet());
    }
}
