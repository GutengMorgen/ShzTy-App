package com.gutengmorgen.ShzTy.repositories;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.gutengmorgen.ShzTy.models.Genres.Genre;

public class GenreRepository extends RepositoryBase<Genre> {

    public List<Genre> findAll() {
	try(Session sess = factory.openSession()){
	    String jpql = "SELECT g FROM Genre g";
	    TypedQuery<Genre> query = sess.createQuery(jpql,Genre.class);
	    return query.getResultList();
	}
    }

    public Genre findById(Long id) {
	try (Session sess = factory.openSession()) {
	    String jpql = "SELECT g FROM Genre g " + "WHERE g.id = :genreId";
	    TypedQuery<Genre> query = sess.createQuery(jpql, Genre.class).setParameter("genreId", id);
	    return query.getSingleResult();
	} catch (Exception e) {
	    return null;
	}
    }

    public boolean existsByName(String name) {
	try(Session sess = factory.openSession()){
	    String jpql = "SELECT COUNT(g) FROM Genre g WHERE g.name = :name";
	    TypedQuery<Long> query = sess.createQuery(jpql, Long.class)
		    .setParameter("name", name);

	    return query.getSingleResult() > 0;
	}
    }
}
