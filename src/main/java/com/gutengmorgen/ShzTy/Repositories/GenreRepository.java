package com.gutengmorgen.ShzTy.Repositories;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;

import com.gutengmorgen.ShzTy.factory.HibernateUtils;
import com.gutengmorgen.ShzTy.models.Genres.Genre;

public class GenreRepository implements RepositoryBase<Genre> {
    private final Session sess;

    public GenreRepository() {
	sess = HibernateUtils.getSessionFactory().openSession();
    }

    public void closeAll() {
	sess.close();
	HibernateUtils.closeSessionFactory();
    }

    @Override
    public List<Genre> findAll() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void save(Genre entity) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void update(Genre entity) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void delete(Genre entity) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public Genre findById(Long id) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public boolean existsByName(String name) {
	// TODO Auto-generated method stub
	return false;
    }
}
