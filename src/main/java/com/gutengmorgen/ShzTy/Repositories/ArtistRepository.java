package com.gutengmorgen.ShzTy.Repositories;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gutengmorgen.ShzTy.factory.HibernateUtils;
import com.gutengmorgen.ShzTy.models.Artists.Artist;
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.DtoCreateArtist;
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.DtoSimpleReturnArtist;
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.DtoSimpleTestingArtist;
import com.gutengmorgen.ShzTy.models.Genres.Genre;

public class ArtistRepository implements RepositoryBase<Artist> {
    private final Session sess;

    public ArtistRepository() {
	sess = HibernateUtils.getSessionFactory().openSession();
    }

    public void closeSessionFactory() {
	HibernateUtils.closeSessionFactory();
    }

    // TODO: put this method in ArtistDAO
    public List<DtoSimpleReturnArtist> getAllSimpleArtist() {
	List<Artist> list = sess.createQuery("FROM Artist", Artist.class).getResultList();

	List<DtoSimpleReturnArtist> actualList = list.stream().map(artist -> new DtoSimpleReturnArtist(artist))
		.toList();
	return actualList;
    }

//    @Override
//    public List<Artist> findAll() {
//	try {
//	    List<Artist> list = sess.createQuery("FROM Artist", Artist.class).getResultList();
//	    return list;
//	} finally {
//	    sess.close();
//	}
//    }

    @Override
    public List<Artist> findAll(){
	try {
	    String jpql = "SELECT DISTINCT a FROM Artist a " + 
		    "LEFT JOIN FETCH a.albums " + 
		    "LEFT JOIN FETCH a.genres " + 
		    "LEFT JOIN FETCH a.languages ";
	    return sess.createQuery(jpql, Artist.class).getResultList();
	} finally {
	    sess.close();
	}
    }

    public List<DtoSimpleReturnArtist> findAll_SimpleReturn() {
	try {
	    String jpql = "SELECT a FROM Artist a LEFT JOIN FETCH a.albums";
	    return sess.createQuery(jpql, Artist.class).getResultList().stream()
		    .map(artist -> new DtoSimpleReturnArtist(artist)).toList();
	} finally {
	    sess.close();
	}
    }

    @Override
    public void save(Artist entity) {
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
    
    public void save(Artist a, DtoCreateArtist dto) {
	Transaction tx = null;
	try {
	    tx = sess.beginTransaction();
	    
//	    for (Long genreID : dto.GenreIDs()) {
//		Genre genre = genreRepository.findById(genreID);
//		    if (genre == null)
//			throw new RuntimeException(String.format("Genre with id <%d> not found", genreID));
//		    a.addGenre(genre);
//	    }
	    
	    sess.persist(a);
	    tx.commit();
	} catch (Exception e) {
	    if (tx != null) {
		tx.rollback();
		System.out.println(e.getMessage());
	    }
	} finally {
	    sess.close();
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
	try {
	    String jpql = "SELECT a FROM Artist a " + "LEFT JOIN FETCH a.albums " + "LEFT JOIN FETCH a.genres "
		    + "LEFT JOIN FETCH a.languages " + "WHERE a.id = :artistId";
	    Query query = sess.createQuery(jpql, Artist.class);
	    query.setParameter("artistId", id);
	    Artist artist = (Artist) query.getSingleResult();
//	    Artist artist = sess.get(Artist.class, id);
//	    Hibernate.initialize(artist.getAlbums());
//	    Hibernate.initialize(artist.getGenres());
//	    Hibernate.initialize(artist.getLanguages());
	    return artist;
	} finally {
	    sess.close();
	}
    }

    //TODO: optimizar el query. aun no funciona, intentar crear diferentes queries y luego unirlos 
    public DtoSimpleTestingArtist findByIdReturn(Long id) {
	try {
	    String jpql = "SELECT NEW com.gutengmorgen.ShzTy.models.Artists.DtoArtists.DtoSimpleTestingArtist(a.name, a.gender, a.country, alb.title) " + 
		    "FROM Artist a " + 
		    "JOIN a.albums alb " +
		    "WHERE a.id = :artistId";
	    Query query = sess.createQuery(jpql, DtoSimpleTestingArtist.class);
	    query.setParameter("artistId", id);
	    return (DtoSimpleTestingArtist) query.getSingleResult();
	} finally {
	    sess.close();
	}
    }

    @Override
    public boolean existsByName(String name) {
	String jpql = "SELECT COUNT(a) FROM Artist a WHERE a.name = :name";
	TypedQuery<Long> query = sess.createQuery(jpql, Long.class);
	query.setParameter("name", name);

	Long count = query.getSingleResult();

	return count > 0;
    }
    
    public void testingHibernate() {
	Session sess = HibernateUtils.getSessionFactory().openSession();

	CriteriaBuilder cb = sess.getCriteriaBuilder();
	CriteriaQuery<Artist> cq = cb.createQuery(Artist.class);
	Root<Artist> root = cq.from(Artist.class);
	CriteriaQuery<Artist> all = cq.select(root);

	TypedQuery<Artist> allQuery = sess.createQuery(all);

	for (Artist artist : allQuery.getResultList()) {
	    System.out.println(artist.getName());
	}
    }
}
