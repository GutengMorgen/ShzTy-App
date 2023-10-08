package com.gutengmorgen.ShzTy.models.Albums.DtoAlbums;

import java.sql.Date;
import java.util.Set;
import java.util.stream.Collectors;

import com.gutengmorgen.ShzTy.models.Albums.Album;

public record DtoReturnAlbum(
	String title,
        Date releaseDate,
        String albumFormat,
        int countTracks, //TODO: removerlo????
//        int playTime,
        Set<String> genres) {

    public DtoReturnAlbum(Album album) {
	this(
		album.getTitle(),
		album.getRelease_date(),
		album.getAlbumFormat().getName(),
		album.tracksCount(),
		album.getGenres().stream().map(genre -> genre.getName()).collect(Collectors.toSet())
	);
    }

}
