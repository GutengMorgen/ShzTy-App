package com.gutengmorgen.ShzTy.Repositories;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.gutengmorgen.ShzTy.factory.HibernateUtils;
import com.gutengmorgen.ShzTy.models.Artists.Artist;
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.DtoSimpleReturnArtist;
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.DtoSimpleTestingArtist;

public class ArtistRepository implements RepositoryBase<Artist> {
    private final SessionFactory sFactory;
    
    public ArtistRepository() {
	this.sFactory = HibernateUtils.getSessionFactory();
    }

    @Override
    public List<Artist> findAll() {
	try (Session sess = sFactory.openSession()) {
	    String jpql = "SELECT DISTINCT a FROM Artist a " + "LEFT JOIN FETCH a.albums " + "LEFT JOIN FETCH a.genres "
		    + "LEFT JOIN FETCH a.languages ";
	    return sess.createQuery(jpql, Artist.class).getResultList();
	}
    }

    public List<DtoSimpleReturnArtist> findAll_SimpleReturn() {
	try (Session sess = sFactory.openSession()) {
	    String jpql = "SELECT a FROM Artist a LEFT JOIN FETCH a.albums";
	    return sess.createQuery(jpql, Artist.class).getResultList().stream()
		    .map(artist -> new DtoSimpleReturnArtist(artist)).toList();
	}
    }

    @Override
    public void save(Artist entity) {
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
    public void update(Artist entity) {
	// TODO Auto-generated method stub

    }

    @Override
    public void delete(Artist entity) {
	// TODO Auto-generated method stub

    }

    @Override
    public Artist findById(Long id) {
	try (Session sess = sFactory.openSession()) {
	    String jpql = "SELECT a FROM Artist a " + "LEFT JOIN FETCH a.albums " + "LEFT JOIN FETCH a.genres "
		    + "LEFT JOIN FETCH a.languages " + "WHERE a.id = :artistId";
	    Query query = sess.createQuery(jpql, Artist.class);
	    query.setParameter("artistId", id);
	    Artist artist = (Artist) query.getSingleResult();
	    return artist;
	}
    }

    // TODO: optimizar el query. aun no funciona, intentar crear diferentes queries
    // y luego unirlos
    public DtoSimpleTestingArtist findByIdReturn(Long id) {
	try (Session sess = sFactory.openSession()) {
	    String jpql = "SELECT NEW com.gutengmorgen.ShzTy.models.Artists.DtoArtists.DtoSimpleTestingArtist(a.name, a.gender, a.country, alb.title) "
		    + "FROM Artist a " + "JOIN a.albums alb " + "WHERE a.id = :artistId";
	    Query query = sess.createQuery(jpql, DtoSimpleTestingArtist.class);
	    query.setParameter("artistId", id);
	    return (DtoSimpleTestingArtist) query.getSingleResult();
	}
    }

    @Override
    public boolean existsByName(String name) {
	Session sess = sFactory.openSession();

	String jpql = "SELECT COUNT(a) FROM Artist a WHERE a.name = :name";
	TypedQuery<Long> query = sess.createQuery(jpql, Long.class);
	query.setParameter("name", name);

	Long count = query.getSingleResult();

	return count > 0;
    }
}
