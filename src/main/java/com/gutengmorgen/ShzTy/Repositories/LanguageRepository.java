package com.gutengmorgen.ShzTy.Repositories;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.gutengmorgen.ShzTy.factory.HibernateUtils;
import com.gutengmorgen.ShzTy.models.Languages.Language;

public class LanguageRepository implements RepositoryBase<Language> {
    private final SessionFactory sFactory;

    public LanguageRepository() {
	this.sFactory = HibernateUtils.getSessionFactory();
    }

    @Override
    public List<Language> findAll() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void save(Language entity) {
	Transaction tx = null;
	try (Session sess = sFactory.openSession()) {
	    tx = sess.beginTransaction();
	    sess.persist(entity);
	    tx.commit();
	} catch (Exception e) {
	    if (tx != null) {
		tx.rollback();
		System.out.println(e.getMessage());
	    }
	}
    }

    @Override
    public void update(Language entity) {
	// TODO Auto-generated method stub

    }

    @Override
    public void delete(Language entity) {
	// TODO Auto-generated method stub

    }

    @Override
    public Language findById(Long id) {
	try (Session sess = sFactory.openSession()) {
	    String jpql = "SELECT l FROM Language l " + "LEFT JOIN FETCH l.artists " + "WHERE l.id = :languageId";
	    Query query = sess.createQuery(jpql, Language.class).setParameter("languageId", id);
	    return (Language) query.getSingleResult();
	}
    }

    @Override
    public boolean existsByName(String name) {
	// TODO Auto-generated method stub
	return false;
    }
}
