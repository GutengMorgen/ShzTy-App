package com.gutengmorgen.ShzTy.repositories;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.gutengmorgen.ShzTy.models.Genres.Genre;

public class GenreRepository extends RepositoryBase<Genre> {

    public List<Genre> findAll() {
	// TODO Auto-generated method stub
	return null;
    }

    public Genre findById(Long id) {
	try (Session sess = factory.openSession()) {
//	    String jpql = "SELECT g FROM Genre g " + "LEFT JOIN FETCH g.artists " + "WHERE g.id = :genreId";
	    String jpql = "SELECT g FROM Genre g " + "WHERE g.id = :genreId";
	    TypedQuery<Genre> query = sess.createQuery(jpql, Genre.class).setParameter("genreId", id);
	    return query.getSingleResult();
	}
    }
}
