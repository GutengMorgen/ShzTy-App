package com.gutengmorgen.ShzTy.factory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class TestConnection {
    public static void main(String[] args) {
	SessionFactory sFactory = HibernateUtils.getSessionFactory();
	Session sess = sFactory.openSession();

	System.out.println("geg");
	sess.close();
	HibernateUtils.closeSessionFactory();
    }
}
