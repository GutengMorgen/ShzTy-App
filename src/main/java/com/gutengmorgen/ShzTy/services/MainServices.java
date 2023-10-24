package com.gutengmorgen.ShzTy.services;

import java.util.List;

import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.DtoCreateArtist;
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.DtoUpdateArtist;

public interface MainServices<T extends ReturnDTO> {
    //NOTE: en los services apropiados convertir el insertTypeDto en segun correspondan
    T save(InsertDTO dto);
    T update(InsertDTO dto, Long id);
    List<T> simpleList();
    ReturnDTO getById(Long id);
}
