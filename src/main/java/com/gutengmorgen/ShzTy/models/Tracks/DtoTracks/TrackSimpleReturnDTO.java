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
public class TrackSimpleReturnDTO implements ReturnDTO {
    @ForGUI(name = "ID")
    private Long id;
    @ForGUI(name = "Title")
    private String title;
    @ForGUI(name = "Release Date", type = GUIType.DATE)
    private Date releaseDate;
    @ForGUI(name = "Genres", type = GUIType.MULTI_OPTION, useEntity = "Genre")
    private Set<Long> genreIDs;
    @ForGUI(name = "PlayList", type = GUIType.SINGLE_OPTION, useEntity = "PlayList")
    private Long playListId;

    public TrackSimpleReturnDTO(Track t) {
	this.id = t.getId();
	this.title = t.getTitle();
	this.releaseDate = t.getRelease_date();
	this.genreIDs = t.getGenres().stream().map(genre -> genre.getId()).collect(Collectors.toSet());
	this.playListId = t.getPlayList().getId();
    }
}
