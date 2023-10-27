package com.gutengmorgen.ShzTy.models.Albums.DtoAlbums;

import java.sql.Date;
import java.util.Set;

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
public class AlbumUpdateDTO implements InsertDTO {
    @ForGUI(name = "Title:")
    private String title;
    @ForGUI(name = "Release Date:", type = VarType.DATE)
    private Date releaseDate;
    @ForGUI(name = "Artist:", type = VarType.SINGLE_OPTION, useEntity = AllEntities.Artist)
    private Long artistId;
    @ForGUI(name = "Album Format:", type = VarType.SINGLE_OPTION, useEntity = AllEntities.AlbumFormat)
    private Long albumFormatId;
    @ForGUI(name = "Genre:", type = VarType.MULTI_OPTION, useEntity = AllEntities.Genre)
    private Set<Long> genresId;

    @Override
    public String toString() {
	return "AlbumUpdateDTO [title=" + title + ", releaseDate=" + releaseDate + ", artistId=" + artistId
		+ ", albumFormatId=" + albumFormatId + ", genresId=" + genresId + "]";
    }
}
