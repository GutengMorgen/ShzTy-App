package com.gutengmorgen.ShzTy.repositories;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.gutengmorgen.ShzTy.models.PlayLists.PlayList;

public class PlayListRepository extends RepositoryBase<PlayList> {

    public PlayList findById(Long id) {
	try (Session se = factory.openSession()) {
	    String jpql = "SELECT pl FROM PlayList pl " + "WHERE pl.id = :plId";
	    TypedQuery<PlayList> query = se.createQuery(jpql, PlayList.class).setParameter("plId", id);
	    return query.getSingleResult();
	} catch (Exception e) {
	    return null;
	}
    }

}
