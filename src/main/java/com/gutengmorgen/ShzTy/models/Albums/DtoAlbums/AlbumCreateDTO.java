package com.gutengmorgen.ShzTy.models.Albums.DtoAlbums;

import java.sql.Date;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.gutengmorgen.ShzTy.services.extras.InsertDTO;
import com.gutengmorgen.ShzTy.views.AllEntities;
import com.gutengmorgen.ShzTy.views.Extras.ForGUI;
import com.gutengmorgen.ShzTy.views.Extras.VarType;

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
    @NotBlank()
    private String title;
    @ForGUI(name = "Release Date*:", type = VarType.DATE)
    @NotNull()
    private Date releaseDate;
    @ForGUI(name = "Artist*:", type = VarType.SINGLE_OPTION, useEntity = AllEntities.Artist)
    @NotNull()
    private Long artistId;
    @ForGUI(name = "Album Format*:", type = VarType.SINGLE_OPTION, useEntity = AllEntities.AlbumFormat)
    @NotNull()
    private Long albumFormatId;
    @ForGUI(name = "Genres:", type = VarType.MULTI_OPTION, useEntity = AllEntities.Genre)
    Set<Long> genreIDs;

    @Override
    public String toString() {
	return "AlbumCreateDTO [title=" + title + ", releaseDate=" + releaseDate + ", artistId=" + artistId
		+ ", albumFormatId=" + albumFormatId + ", genreIDs=" + genreIDs + "]";
    }
}
