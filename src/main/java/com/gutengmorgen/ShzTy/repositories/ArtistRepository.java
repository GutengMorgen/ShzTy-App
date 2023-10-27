package com.gutengmorgen.ShzTy.repositories;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.gutengmorgen.ShzTy.models.Artists.Artist;
import com.gutengmorgen.ShzTy.repositories.extras.RepositoryBase;

public class ArtistRepository extends RepositoryBase<Artist> {

	public ArtistRepository() {
		super(Artist.class);
	}

	public List<Artist> findAll() {
		try (Session sess = factory.openSession()) {
			String jpql = "SELECT DISTINCT a FROM Artist a " + "LEFT JOIN FETCH a.albums " + "LEFT JOIN FETCH a.genres "
					+ "LEFT JOIN FETCH a.languages ";
			return sess.createQuery(jpql, Artist.class).getResultList();
		}
	}

	public Artist findById(Long id) {
		try (Session sess = factory.openSession()) {
			String jpql = "SELECT a FROM Artist a " + "LEFT JOIN FETCH a.albums " + "LEFT JOIN FETCH a.genres "
					+ "LEFT JOIN FETCH a.languages " + "WHERE a.id = :artistId";
			TypedQuery<Artist> query = sess.createQuery(jpql, Artist.class).setParameter("artistId", id);
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	// TODO: optimizar el query. aun no funciona, intentar crear diferentes queries
	// y luego unirlos
//    public DtoSimpleTestingArtist findByIdReturn(Long id) {
//	try (Session sess = factory.openSession()) {
//	    String jpql = "SELECT NEW com.gutengmorgen.ShzTy.models.Artists.DtoArtists.DtoSimpleTestingArtist(a.name, a.gender, a.country, alb.title) "
//		    + "FROM Artist a " + "JOIN a.albums alb " + "WHERE a.id = :artistId";
//	    TypedQuery<DtoSimpleTestingArtist> query = sess.createQuery(jpql, DtoSimpleTestingArtist.class);
//	    query.setParameter("artistId", id);
//	    return query.getSingleResult();
//	}
//    }

	public boolean existsByName(String name) {
		try (Session sess = factory.openSession()) {
			String jpql = "SELECT COUNT(a) FROM Artist a WHERE a.name = :name";
			TypedQuery<Long> query = sess.createQuery(jpql, Long.class).setParameter("name", name);

			return query.getSingleResult() > 0;
		}
	}
}
