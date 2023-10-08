package com.gutengmorgen.ShzTy.repositories;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.gutengmorgen.ShzTy.factory.HibernateUtils;

public class RepositoryBase<T> {
    public final SessionFactory factory;
    
    public RepositoryBase(){
	this.factory = HibernateUtils.getSessionFactory();
    }
    
    public void save(T entity) {
	Transaction tx = null;
	try (Session sess = factory.openSession()) {
	    tx = sess.beginTransaction();
	    sess.persist(entity);
	    tx.commit();
	} catch (Exception e) {
	    if (tx != null) {
		System.out.println("this error: " + e.getMessage());
		e.printStackTrace();
		tx.rollback();
	    }
	}
    }
    
    public void update(T entity) {
	Transaction tx = null;
	try (Session sess = factory.openSession()) {
	    tx = sess.beginTransaction();
	    sess.update(entity);
	    tx.commit();
	} catch (Exception e) {
	    if (tx != null) {
		System.out.println("this error: " + e.getMessage());
		e.printStackTrace();
		tx.rollback();
	    }
	}
    }
    
    public void delete(T entity) {
	Transaction tx = null;
	try (Session sess = factory.openSession()) {
	    tx = sess.beginTransaction();
	    sess.delete(entity);
	    tx.commit();
	} catch (Exception e) {
	    if (tx != null) {
		System.out.println("this error: " + e.getMessage());
		e.printStackTrace();
		tx.rollback();
	    }
	}
    }
    
    public boolean existsById(Class<?> entity, Long id) {
	try (Session sess = factory.openSession()) {
	    String jpql = "SELECT COUNT(e) FROM " + entity.getSimpleName() + " e WHERE e.id = :id";
	    TypedQuery<Long> query = sess.createQuery(jpql, Long.class).setParameter("id", id);

	    return query.getSingleResult() > 0;
	}
    }
}
