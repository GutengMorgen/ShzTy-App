package com.gutengmorgen.ShzTy.repositories;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.gutengmorgen.ShzTy.models.Albums.Album;
import com.gutengmorgen.ShzTy.models.Artists.Artist;
import com.gutengmorgen.ShzTy.repositories.extras.RepositoryBase;

public class AlbumRepository extends RepositoryBase<Album> {

    public AlbumRepository() {
		super(Album.class);
	}

	public List<Album> findAll() {
	try (Session se = factory.openSession()) {
	    String jpql = "SELECT DISTINCT al FROM Album al " + "LEFT JOIN FETCH al.albumFormat "
		    + "LEFT JOIN FETCH al.genres " + "LEFT JOIN FETCH al.tracks " + "LEFT JOIN FETCH al.artist";
	    return se.createQuery(jpql, Album.class).getResultList();
	}
    }

    // TODO: ocurre un problema cuando se incluye a artist en el toString de Album
    public Album findById(Long id) {
	try (Session se = factory.openSession()) {
	    String jpql = "SELECT al FROM Album al " + "LEFT JOIN FETCH al.albumFormat " + "LEFT JOIN FETCH al.genres "
		    + "LEFT JOIN FETCH al.tracks " + "WHERE al.id = :alId";
	    TypedQuery<Album> query = se.createQuery(jpql, Album.class).setParameter("alId", id);
	    return query.getSingleResult();
	} catch (Exception e) {
	    return null;
	}
    }

    public boolean existsByNameInArtist(String name, Artist a) {
	try (Session se = factory.openSession()) {
	    String jpql = "SELECT COUNT(al) FROM Album al WHERE al.title = :title AND al.artist = :artist";
	    TypedQuery<Long> query = se.createQuery(jpql, Long.class)
		    .setParameter("title", name)
		    .setParameter("artist",a);

	    return query.getSingleResult() > 0;
	}
    }
}
