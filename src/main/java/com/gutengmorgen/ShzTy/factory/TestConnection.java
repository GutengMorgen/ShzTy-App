package com.gutengmorgen.ShzTy.factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class TestConnection {
    public static void main(String[] args) {
	EntityManagerFactory factory = ConnectionBaseHibernate.getManagerFactory();
	EntityManager em = factory.createEntityManager();


	System.out.println("geg");
	em.close();
	ConnectionBaseHibernate.closeManagerFactory();
    }
}
