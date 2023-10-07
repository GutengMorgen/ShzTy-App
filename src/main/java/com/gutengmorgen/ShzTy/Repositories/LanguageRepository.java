package com.gutengmorgen.ShzTy.Repositories;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gutengmorgen.ShzTy.factory.HibernateUtils;
import com.gutengmorgen.ShzTy.models.Genres.Genre;
import com.gutengmorgen.ShzTy.models.Languages.Language;

public class LanguageRepository implements RepositoryBase<Language> {
    private final Session sess;

    public LanguageRepository() {
	sess = HibernateUtils.getSessionFactory().openSession();
    }

    public void closeSessionFactory() {
	HibernateUtils.closeSessionFactory();
    }

    @Override
    public List<Language> findAll() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void save(Language entity) {
	Transaction tx = null;
	try {
	    tx = sess.beginTransaction();
	    sess.persist(entity);
	    tx.commit();
	} catch (Exception e) {
	    if (tx != null) {
		tx.rollback();
		System.out.println(e.getMessage());
	    }
	} finally {
//	    sess.close();
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
	try {
	    String jpql = "SELECT l FROM Language l " + "LEFT JOIN FETCH l.artists " + "WHERE l.id = :languageId";
	    Query query = sess.createQuery(jpql, Language.class).setParameter("languageId", id);
	    return (Language) query.getSingleResult();
	} finally {
//	    sess.close();
	}
    }

    @Override
    public boolean existsByName(String name) {
	// TODO Auto-generated method stub
	return false;
    }
}
