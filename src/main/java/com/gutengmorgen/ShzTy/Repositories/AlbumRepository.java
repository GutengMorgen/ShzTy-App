package com.gutengmorgen.ShzTy.Repositories;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.gutengmorgen.ShzTy.models.Albums.Album;

public class AlbumRepository extends RepositoryBase<Album> {

    public List<Album> findAll() {
	try (Session sess = factory.openSession()) {
	    String jpql = "SELECT DISTINCT a FROM Artist a " + "LEFT JOIN FETCH a.albums " + "LEFT JOIN FETCH a.genres "
		    + "LEFT JOIN FETCH a.languages ";
	    return sess.createQuery(jpql, Album.class).getResultList();
	}
    }

    public Album findById(Long id) {
	try (Session sess = factory.openSession()) {
	    String jpql = "SELECT a FROM Artist a " + "LEFT JOIN FETCH a.albums " + "LEFT JOIN FETCH a.genres "
		    + "LEFT JOIN FETCH a.languages " + "WHERE a.id = :artistId";
	    TypedQuery<Album> query = sess.createQuery(jpql, Album.class).setParameter("artistId", id);
	    return query.getSingleResult();
	} catch (Exception e) {
	    return null;
	}
    }

    public boolean existsByName(String name) {
	try (Session sess = factory.openSession()) {
	    String jpql = "SELECT COUNT(a) FROM Artist a WHERE a.name = :name";
	    TypedQuery<Long> query = sess.createQuery(jpql, Long.class).setParameter("name", name);

	    return query.getSingleResult() > 0;
	}
    }

}
