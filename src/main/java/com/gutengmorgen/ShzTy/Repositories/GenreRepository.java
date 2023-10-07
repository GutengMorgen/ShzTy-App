package com.gutengmorgen.ShzTy.Repositories;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.gutengmorgen.ShzTy.factory.HibernateUtils;
import com.gutengmorgen.ShzTy.models.Genres.Genre;

public class GenreRepository implements RepositoryBase<Genre> {
    private final SessionFactory sFactory;

    public GenreRepository() {
	this.sFactory = HibernateUtils.getSessionFactory();
    }

    @Override
    public List<Genre> findAll() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void save(Genre entity) {
	Transaction tx = null;
	try (Session sess = sFactory.openSession()) {
	    tx = sess.beginTransaction();
	    sess.persist(entity);
	    tx.commit();
	} catch (Exception e) {
	    if (tx != null) {
		tx.rollback();
		System.out.println(e.getMessage());
	    }
	}
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
	try (Session sess = sFactory.openSession()) {
	    String jpql = "SELECT g FROM Genre g " + "LEFT JOIN FETCH g.artists " + "WHERE g.id = :genreId";
	    Query query = sess.createQuery(jpql, Genre.class).setParameter("genreId", id);
	    return (Genre) query.getSingleResult();
	}
    }

    @Override
    public boolean existsByName(String name) {
	// TODO Auto-generated method stub
	return false;
    }
}
