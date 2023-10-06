package com.gutengmorgen.ShzTy.Repositories;

import java.util.List;

public interface RepositoryBase<T> {
    List<T> findAll();
    void save(T entity);
    void update(T entity);
    void delete(T entity);
    T findById(Long id);
    boolean existsByName(String name);
}
