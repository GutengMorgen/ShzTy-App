package com.gutengmorgen.ShzTy.repositories;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.gutengmorgen.ShzTy.models.Genres.Genre;
import com.gutengmorgen.ShzTy.models.Languages.Language;

public class LanguageRepository extends RepositoryBase<Language> {

    public List<Language> findAll() {
	try(Session sess = factory.openSession()){
	    String jpql = "SELECT l FROM Language l";
	    TypedQuery<Language> query = sess.createQuery(jpql,Language.class);
	    return query.getResultList();
	}
    }

    public Language findById(Long id) {
	try (Session sess = factory.openSession()) {
	    String jpql = "SELECT l FROM Language l " + "LEFT JOIN FETCH l.artists " + "WHERE l.id = :languageId";
	    TypedQuery<Language> query = sess.createQuery(jpql, Language.class).setParameter("languageId", id);
	    return query.getSingleResult();
	}
    }
}
