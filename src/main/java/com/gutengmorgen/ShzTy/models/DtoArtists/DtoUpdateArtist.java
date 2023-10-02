package com.gutengmorgen.ShzTy.models.DtoArtists;

import java.sql.Date;
import java.util.Set;

public record DtoUpdateArtist(
        String Name,
        Date BornDate,
        String Gender,
        String Country,
        String Biography,
        Set<Long> LanguageIDs,
        Set<Long> GenreIDs) {
    
}
