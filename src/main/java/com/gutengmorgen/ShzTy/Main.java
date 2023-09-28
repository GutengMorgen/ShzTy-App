package com.gutengmorgen.ShzTy;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.gutengmorgen.ShzTy.Conection.HibernateUtil;
import com.gutengmorgen.ShzTy.Entities.Artist;

public class Main {

    public static void main(String[] args) {
	EntityManagerFactory emf = HibernateUtil.getEmf();
	EntityManager em = emf.createEntityManager();
	
        String jpql = "SELECT a FROM Artist a";
        Query query = em.createQuery(jpql);

        List<Artist> artistList = query.getResultList();

        for (Artist artist : artistList) {
	    System.out.println(artist.toString());
	}
	
	em.close();
	HibernateUtil.closeEmf();
    }

}
