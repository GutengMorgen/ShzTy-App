package com.gutengmorgen.ShzTy.models.Albums.DtoAlbums;

import java.sql.Date;
import java.util.Set;

import com.gutengmorgen.ShzTy.services.InsertDTO;
import com.gutengmorgen.ShzTy.views.Extras.ForGUI;
import com.gutengmorgen.ShzTy.views.Extras.GUIType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlbumUpdateDTO implements InsertDTO {
    @ForGUI(name = "Title:")
    private String title;
    @ForGUI(name = "Release Date:", type = GUIType.DATE)
    private Date releaseDate;
    @ForGUI(name = "Artist:", type = GUIType.SINGLE_OPTION, useEntity = "Artist")
    private Long artistId;
    @ForGUI(name = "Album Format:", type = GUIType.SINGLE_OPTION, useEntity = "AlbumFormat")
    private Long albumFormatId;
    @ForGUI(name = "Genre:", type = GUIType.MULTI_OPTION, useEntity = "Genre")
    private Set<Long> genresId;

    @Override
    public String toString() {
	return "AlbumUpdateDTO [title=" + title + ", releaseDate=" + releaseDate + ", artistId=" + artistId
		+ ", albumFormatId=" + albumFormatId + ", genresId=" + genresId + "]";
    }
}
