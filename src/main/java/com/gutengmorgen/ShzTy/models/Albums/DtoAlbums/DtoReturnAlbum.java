package com.gutengmorgen.ShzTy.models.Albums.DtoAlbums;

import java.sql.Date;
import java.util.Set;

public record DtoReturnAlbum(
	String title,
        Date releaseDate,
        String albumFormat,
        int countTracks,
        int playTime,
        Set<String> genres) {

}
