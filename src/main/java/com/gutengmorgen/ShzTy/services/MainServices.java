package com.gutengmorgen.ShzTy.services;

import java.util.List;

public interface MainServices<T extends ReturnDTO> {
    T save(InsertDTO origin);
    T update(InsertDTO origin, Long id);
    List<T> simpleList();
    ReturnDTO getById(Long id);
}
