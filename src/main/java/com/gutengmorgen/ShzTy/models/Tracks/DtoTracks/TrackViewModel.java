package com.gutengmorgen.ShzTy.models.Tracks.DtoTracks;

import java.sql.Date;
import java.util.Set;
import java.util.stream.Collectors;

import com.gutengmorgen.ShzTy.models.Tracks.Track;
import com.gutengmorgen.ShzTy.services.extras.ReturnDTO;
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
public class TrackViewModel implements ReturnDTO {
    @ForGUI(name = "ID")
    private Long id;
    @ForGUI(name = "Title")
    private String title;
    @ForGUI(name = "Release Date", type = VarType.DATE)
    private Date releaseDate;
    @ForGUI(name = "Genres", type = VarType.MULTI_OPTION)
    private Set<String> genreIDs;
    @ForGUI(name = "PlayList", type = VarType.SINGLE_OPTION)
    private String playListId;

    public TrackViewModel(Track t) {
	this.id = t.getId();
	this.title = t.getTitle();
	this.releaseDate = t.getRelease_date();
	this.genreIDs = t.getGenres().stream().map(genre -> genre.getName()).collect(Collectors.toSet());
	this.playListId = t.getPlayList().getTile();
    }
}
