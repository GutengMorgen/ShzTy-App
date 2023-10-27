package com.gutengmorgen.ShzTy.repositories;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.gutengmorgen.ShzTy.models.Languages.Language;
import com.gutengmorgen.ShzTy.repositories.extras.RepositoryBase;

public class LanguageRepository extends RepositoryBase<Language> {

	public LanguageRepository() {
		super(Language.class);
	}

	public Language findById(Long id) {
		try (Session sess = factory.openSession()) {
			String jpql = "SELECT l FROM Language l " + "LEFT JOIN FETCH l.artists " + "WHERE l.id = :languageId";
			TypedQuery<Language> query = sess.createQuery(jpql, Language.class).setParameter("languageId", id);
			return query.getSingleResult();
		}
	}

}
