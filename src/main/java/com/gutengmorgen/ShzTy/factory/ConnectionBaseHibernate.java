package com.gutengmorgen.ShzTy.factory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionBaseHibernate {
    private static String persistenUnitName = "HypersistenceOptimizer";
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
	    .createEntityManagerFactory(persistenUnitName);

    public static EntityManagerFactory getManagerFactory() {
	return ENTITY_MANAGER_FACTORY;
    }

    public static void closeManagerFactory() {
	if (ENTITY_MANAGER_FACTORY != null && ENTITY_MANAGER_FACTORY.isOpen()) {
	    ENTITY_MANAGER_FACTORY.close();
	}
    }
}
