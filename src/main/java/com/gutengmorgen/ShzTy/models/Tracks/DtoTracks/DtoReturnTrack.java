package com.gutengmorgen.ShzTy.models.Tracks.DtoTracks;

import java.sql.Date;
import java.util.Set;
import java.util.stream.Collectors;

import com.gutengmorgen.ShzTy.models.Tracks.Track;

public record DtoReturnTrack(
        String title,
        Date releaseDate,
        int playTime,
        String notes,
        Set<Long> genreIDs,
        Long albumId,
        Long playListId) {

    
    public DtoReturnTrack(Track t) {
	this(
		t.getTitle(),
		t.getRelease_date(),
		t.getPlay_time(),
		t.getNotes(),
		t.getGenres().stream().map(genre -> genre.getId()).collect(Collectors.toSet()),
		t.getAlbum().getId(),
		t.getPlayList().getId()
	);
    }
}
