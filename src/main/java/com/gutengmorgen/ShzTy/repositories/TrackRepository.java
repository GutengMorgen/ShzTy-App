package com.gutengmorgen.ShzTy.repositories;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.gutengmorgen.ShzTy.models.Artists.Artist;
import com.gutengmorgen.ShzTy.models.Tracks.Track;

public class TrackRepository extends RepositoryBase<Track> {
    // NOTE: al parecer en una relacion manytoone no se necesita hacer el LEFT JOIN
    // FETCH, supongo que es porque obtengo solo una propiedad de la entidad
    // relacionada en el toString como: album.getId()
    public List<Track> findAll() {
	try (Session se = factory.openSession()) {
	    String jpql = "SELECT DISTINCT t FROM Track t" + "LEFT JOIN FETCH t.genres ";
	    return se.createQuery(jpql, Track.class).getResultList();
	}
    }

    public Track findById(Long id) {
	try (Session se = factory.openSession()) {
	    String jpql = "SELECT t FROM Track t " + "LEFT JOIN FETCH t.genres " + "WHERE t.id = :tId";
	    TypedQuery<Track> query = se.createQuery(jpql, Track.class).setParameter("tId", id);
	    return query.getSingleResult();
	} catch (Exception e) {
	    return null;
	}
    }

    public boolean existsByNameInArtist(String name, Artist a) {
	try (Session se = factory.openSession()) {
	    String jpql = "SELECT COUNT(al) FROM Album al WHERE al.title = :title AND al.artist = :artist";
	    TypedQuery<Long> query = se.createQuery(jpql, Long.class).setParameter("title", name).setParameter("artist",
		    a);

	    return query.getSingleResult() > 0;
	}
    }
}
