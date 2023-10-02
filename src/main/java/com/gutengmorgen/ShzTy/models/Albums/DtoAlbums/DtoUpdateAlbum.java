package com.gutengmorgen.ShzTy.models.Albums.DtoAlbums;

import java.sql.Date;
import java.util.Set;

public record DtoUpdateAlbum(
	String title,
        Date releaseDate,
        Long artistId,
        Long albumFormatId,
        Set<Long> genresId) {

}
