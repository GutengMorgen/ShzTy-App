package com.gutengmorgen.ShzTy.Services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.gutengmorgen.ShzTy.factory.ConnectionBaseHibernate;
import com.gutengmorgen.ShzTy.models.Artists.Artist;
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.DtoSimpleReturnArtist;

public class ArtistService {
    private final EntityManager entityManager;

    public ArtistService() {
        entityManager = ConnectionBaseHibernate.getManagerFactory().createEntityManager();
    }
    
    public void close() {
	entityManager.close();
	ConnectionBaseHibernate.closeManagerFactory();
    }
    
    public List<Artist> getAllArtist(){
	String jpql = "SELECT a FROM Artist a";
	Query query = entityManager.createQuery(jpql);
	
	return query.getResultList();
    }
    
    
    public List<DtoSimpleReturnArtist> getAllSimpleArtist(){
	String jpql = "SELECT a FROM Artist a";
	Query query = entityManager.createQuery(jpql);
	
	List<Artist> list = query.getResultList();
	
	List<DtoSimpleReturnArtist> actualList = list.stream().map(artist -> new DtoSimpleReturnArtist(artist)).toList(); 
	return actualList;
    }
    
}
