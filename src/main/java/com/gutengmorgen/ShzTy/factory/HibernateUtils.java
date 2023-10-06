package com.gutengmorgen.ShzTy.factory;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static final SessionFactory SESSION_FACTORY = buildSessinFactory();

    private static SessionFactory buildSessinFactory() {
	try {
	    return new Configuration().configure().buildSessionFactory();
	} catch (Exception ex) {
            throw new RuntimeException("Unacceptable:: Failed to initialize Hibernate SessionFactory -> " + ex.getMessage());
	}
    }
    
    public static SessionFactory getSessionFactory() {
	return SESSION_FACTORY;
    }

    public static void closeSessionFactory() {
	if (SESSION_FACTORY != null && !SESSION_FACTORY.isClosed()) {
	    SESSION_FACTORY.close();
	}
    }
}
