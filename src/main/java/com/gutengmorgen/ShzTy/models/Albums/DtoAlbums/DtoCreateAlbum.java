package com.gutengmorgen.ShzTy.models.Albums.DtoAlbums;

import java.sql.Date;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoCreateAlbum(
	@NotBlank(message = "Title is required")
        String title,
        @NotNull(message = "Release date is required")
        Date releaseDate,
        @NotNull(message = "Artist is required")
        Long artistId,
        @NotNull(message = "Album format is required")
        Long albumFormatId,
        Set<Long> genresId) {
}
