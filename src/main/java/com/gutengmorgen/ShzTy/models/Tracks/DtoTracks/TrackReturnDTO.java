package com.gutengmorgen.ShzTy.models.Tracks.DtoTracks;

import java.sql.Date;
import java.util.Set;
import java.util.stream.Collectors;

import com.gutengmorgen.ShzTy.models.Tracks.Track;
import com.gutengmorgen.ShzTy.services.ReturnDTO;
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
public class TrackReturnDTO implements ReturnDTO {
    @ForGUI(name = "Id:")
    private Long id;
    @ForGUI(name = "Title:")
    private String title;
    @ForGUI(name = "Release Date:", type = GUIType.DATE)
    private Date releaseDate;
    @ForGUI(name = "Play Time:")
    private int playTime;
    @ForGUI(name = "Notes:", type = GUIType.TEXT)
    private String notes;
    @ForGUI(name = "Genres:", type = GUIType.SINGLE_OPTION, useEntity = "Genre")
    private Set<Long> genreIDs;
    @ForGUI(name = "Album:", type = GUIType.SINGLE_OPTION, useEntity = "Album")
    private Long albumId;
    @ForGUI(name = "PlayList:", type = GUIType.SINGLE_OPTION, useEntity = "PlayList")
    private Long playListId;

    public TrackReturnDTO(Track t) {
	this.id = t.getId();
	this.title = t.getTitle();
	this.releaseDate = t.getRelease_date();
	//TODO: hacer que playtime se nuestre en minutos
	this.playTime = t.getPlay_time();
	this.notes = t.getNotes();
	this.genreIDs = t.getGenres().stream().map(genre -> genre.getId()).collect(Collectors.toSet());
	this.albumId = t.getAlbum().getId();
	this.playListId = t.getPlayList().getId();
    }
}
