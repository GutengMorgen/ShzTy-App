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
public class TrackReturnDTO implements ReturnDTO {
    @ForGUI(name = "Id:")
    private Long id;
    @ForGUI(name = "Title:")
    private String title;
    @ForGUI(name = "Release Date:")
    private Date releaseDate;
    @ForGUI(name = "Play Time:")
    private String playTime;
    @ForGUI(name = "Notes:", type = VarType.SIMPLE_TEXT)
    private String notes;
    @ForGUI(name = "Genres:", type = VarType.SINGLE_OPTION)
    private Set<String> genreIDs;
    @ForGUI(name = "Album:")
    private String albumId;
    @ForGUI(name = "PlayList:")
    private String playListId;

    public TrackReturnDTO(Track t) {
	this.id = t.getId();
	this.title = t.getTitle();
	this.releaseDate = t.getRelease_date();
	this.playTime = convert(t.getPlay_time());
	this.notes = t.getNotes();
	this.genreIDs = t.getGenres().stream().map(genre -> genre.getName()).collect(Collectors.toSet());
	this.albumId = t.getAlbum().getTitle();
	this.playListId = t.getPlayList().getTile();
    }

    private String convert(int seconds) {
	int h = seconds / 3600;
	int m = (seconds % 3600) / 60;
	int s = seconds % 60;

	return String.format("%02d:%02d:%02d", h, m, s);
    }
}
