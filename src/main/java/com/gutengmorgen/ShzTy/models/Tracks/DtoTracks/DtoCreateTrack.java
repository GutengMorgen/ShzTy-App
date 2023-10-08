package com.gutengmorgen.ShzTy.models.Tracks.DtoTracks;

import java.sql.Date;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record DtoCreateTrack(
	@NotBlank(message = "Title is required")
        String title,
        @NotNull(message = "ReleaseDate is required")
        Date releaseDate,
        @NotNull(message = "PlayTime is required")
        int playTime,
//        String url,
        String notes,
        Set<Long> genreIDs,
        @NotNull(message = "AlbumId is required")
        Long albumId,
        @NotNull(message = "PlayListId is required")
        Long playListId) {

}
