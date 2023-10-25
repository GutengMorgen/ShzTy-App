package com.gutengmorgen.ShzTy.models.Albums.DtoAlbums;

import java.sql.Date;
import java.util.Set;
import java.util.stream.Collectors;

import com.gutengmorgen.ShzTy.models.Albums.Album;
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
public class AlbumReturnDTO implements ReturnDTO {
    @ForGUI(name = "Id:")
    private Long id;
    @ForGUI(name = "Title:")
    private String title;
    @ForGUI(name = "Release Date:")
    private Date releaseDate;
    @ForGUI(name = "Album Format:")
    private String albumFormat;
    @ForGUI(name = "Count Track:")
    private int countTracks; // TODO: removerlo????
//        int playTime,
    @ForGUI(name = "Genres:", type = ParmType.SINGLE_OPTION, useEntity = "Genre")
    Set<String> genres;

    public AlbumReturnDTO(Album al) {
	this.id = al.getId();
	this.title = al.getTitle();
	this.releaseDate = al.getRelease_date();
	this.albumFormat = al.getAlbumFormat().getName();
	this.countTracks = al.tracksCount();
	this.genres = al.getGenres().stream().map(g -> g.getName()).collect(Collectors.toSet());
    }

}
