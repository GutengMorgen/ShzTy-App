package com.gutengmorgen.ShzTy.models.Albums.DtoAlbums;

import java.sql.Date;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.gutengmorgen.ShzTy.services.InsertDTO;
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
public class AlbumCreateDTO implements InsertDTO {
    @ForGUI(name = "Title*:")
    @NotBlank(message = "Title is required")
    private String title;
    @ForGUI(name = "Release Date*:", type = ParmType.DATE)
    @NotNull(message = "Release date is required")
    private Date releaseDate;
    @ForGUI(name = "Artist*:", type = ParmType.SINGLE_OPTION, useEntity = "Artist")
    @NotNull(message = "Artist is required")
    private Long artistId;
    @ForGUI(name = "Album Format*:", type = ParmType.SINGLE_OPTION, useEntity = "AlbumFormat")
    @NotNull(message = "Album format is required")
    private Long albumFormatId;
    @ForGUI(name = "Genres:", type = ParmType.MULTI_OPTION, useEntity = "Genre")
    Set<Long> genreIDs;

    @Override
    public String toString() {
	return "AlbumCreateDTO [title=" + title + ", releaseDate=" + releaseDate + ", artistId=" + artistId
		+ ", albumFormatId=" + albumFormatId + ", genreIDs=" + genreIDs + "]";
    }
}
