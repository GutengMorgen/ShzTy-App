package com.gutengmorgen.ShzTy.factory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
    private static String name = "HypersistenceOptimizer";
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(name);
    
    public static EntityManagerFactory getEmf() {
	return emf;
    }
    
    public static void closeEmf() {
	if(emf != null && emf.isOpen()) {
	    emf.close();
	}
    }
}
