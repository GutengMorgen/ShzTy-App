package com.gutengmorgen.ShzTy.models.Tracks.DtoTracks;

import java.sql.Date;
import java.util.Set;

public record DtoReturnTrack(
        String title,
        Date releaseDate,
        int playTime,
        String notes,
        Set<Long> genreIDs,
        Long albumId,
        Long playListId) {

}
