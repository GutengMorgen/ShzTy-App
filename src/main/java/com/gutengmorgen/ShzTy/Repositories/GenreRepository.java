package com.gutengmorgen.ShzTy.Repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gutengmorgen.ShzTy.factory.HibernateUtils;
import com.gutengmorgen.ShzTy.models.Artists.Artist;
import com.gutengmorgen.ShzTy.models.Genres.Genre;
import com.gutengmorgen.ShzTy.models.Languages.Language;

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
	Transaction tx = null;
	try {
	    tx = sess.beginTransaction();
	    sess.persist(entity);
	    tx.commit();
	} catch (Exception e) {
	    if (tx != null) {
		tx.rollback();
		System.out.println(e.getMessage());
	    }
	} finally {
//	    sess.close();
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
	try {
	    String jpql = "SELECT g FROM Genre g " + "LEFT JOIN FETCH g.artists " + "WHERE g.id = :genreId";
	    Query query = sess.createQuery(jpql, Genre.class).setParameter("genreId", id);
	    return (Genre) query.getSingleResult();
	} finally {
//	    sess.close();
	}
    }

    @Override
    public boolean existsByName(String name) {
	// TODO Auto-generated method stub
	return false;
    }
}
