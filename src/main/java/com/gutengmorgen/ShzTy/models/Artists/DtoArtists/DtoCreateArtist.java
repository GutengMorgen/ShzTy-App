package com.gutengmorgen.ShzTy.models.Artists.DtoArtists;

import java.sql.Date;
import java.util.Set;

import javax.validation.constraints.NotBlank;

//@NotBlank(message = "hello man")
public record DtoCreateArtist(
        @NotBlank(message = "Name is required")
        String Name,
        Date BornDate,
        @NotBlank(message = "Gender is required")
        String Gender,
        String Country,
        String Biography,
        Set<Long> LanguageIDs,
        Set<Long> GenreIDs) {
}
