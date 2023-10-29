package com.gutengmorgen.ShzTy;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.gutengmorgen.ShzTy.factory.HibernateUtils;

public class Main {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
	SessionFactory sFactory = HibernateUtils.getSessionFactory();
	Session session = sFactory.openSession();
	
        String jpql = "SELECT a.name FROM Artist a";
        Query query = session.createQuery(jpql);

        List<String> artistList = query.getResultList();

        for (var artist : artistList) {
	    System.out.println(artist.toString());
	}
	
	session.close();
	HibernateUtils.closeSessionFactory();
    }

}
