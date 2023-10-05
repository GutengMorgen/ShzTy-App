package com.gutengmorgen.ShzTy;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.gutengmorgen.ShzTy.factory.ConnectionBaseHibernate;

public class Main {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
	EntityManagerFactory emf = ConnectionBaseHibernate.getManagerFactory();
	EntityManager em = emf.createEntityManager();
	
        String jpql = "SELECT a.name FROM Artist a";
        Query query = em.createQuery(jpql);

        List<String> artistList = query.getResultList();

        for (var artist : artistList) {
	    System.out.println(artist.toString());
	}
	
	em.close();
	ConnectionBaseHibernate.closeManagerFactory();
    }

}
