package com.gutengmorgen.ShzTy.models.Tracks.DtoTracks;

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
public class TrackUpdateDTO implements InsertDTO {
    @ForGUI(name = "Title:")
    private String title;
    @ForGUI(name = "Release Date:", type = GUIType.DATE)
    private Date releaseDate;
    @ForGUI(name = "Play Time:")
    private int playTime;
    @ForGUI(name = "Notes:", type = GUIType.TEXT)
    private String notes;
    @ForGUI(name = "Genres:", type = GUIType.MULTI_OPTION, useEntity = "Genre")
    private Set<Long> genreIDs;
    @ForGUI(name = "Album:", type = GUIType.SINGLE_OPTION, useEntity = "Album")
    private Long albumId;
    @ForGUI(name = "PlayList:", type = GUIType.SINGLE_OPTION, useEntity = "PlayList")
    private Long playListId;

}