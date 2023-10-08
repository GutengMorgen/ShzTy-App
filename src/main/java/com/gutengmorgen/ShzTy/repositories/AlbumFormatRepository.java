package com.gutengmorgen.ShzTy.repositories;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.gutengmorgen.ShzTy.models.AlbumFormats.AlbumFormat;

public class AlbumFormatRepository extends RepositoryBase<AlbumRepository> {

    public AlbumFormat findById(Long id) {
	try (Session sess = factory.openSession()) {
	    String jpql = "SELECT af FROM AlbumFormat af " + "WHERE af.id = :afId";
	    TypedQuery<AlbumFormat> query = sess.createQuery(jpql, AlbumFormat.class).setParameter("afId", id);
	    return query.getSingleResult();
	} catch (Exception e) {
	    return null;
	}
    }
}
