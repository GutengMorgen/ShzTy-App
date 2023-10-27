package com.gutengmorgen.ShzTy.repositories.extras;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.gutengmorgen.ShzTy.factory.HibernateUtils;

public class RepositoryBase<T> {
	public final SessionFactory factory;
	private Class<T> entityClass;
	private final String fromEntity;

	public RepositoryBase(Class<T> entityClass) {
		this.entityClass = entityClass;
		this.fromEntity = " FROM " + entityClass.getSimpleName() + " e ";
		this.factory = HibernateUtils.getSessionFactory();
	}

	public List<T> findAll() {
		try (Session s = factory.openSession()) {
			String jpql = "SELECT e" + fromEntity;
			return s.createQuery(jpql, getEntityClass()).getResultList();
		}
	}

	public <V> List<V> findAllBy(Class<V> valueClass, String value) {
		try (Session s = factory.openSession()) {
			String jpql = "SELECT e." + value + fromEntity;
			return s.createQuery(jpql, valueClass).getResultList();
		}
	}

	public T findById(Long id) {
		try (Session s = factory.openSession()) {
			String jpql = "SELECT e" + fromEntity + "WHERE e.id = id";
			return s.createQuery(jpql, getEntityClass()).setParameter("id", id).getSingleResult();
		}
	}

	public Long findIdByName(String parmName, String name) {
		try (Session s = factory.openSession()) {
			String jpql = "SELECT e.id" + fromEntity + "WHERE e." + parmName + " = :name";
			return s.createQuery(jpql, Long.class).setParameter("name", name).getSingleResult();
		}
	}

	public List<String> findAllByName(String parmName) {
		try(Session s = factory.openSession()){
			String jpql = "SELECT e." + parmName + fromEntity;
			return s.createQuery(jpql, String.class).getResultList();
		}
	}
	
	public boolean existsByName(String parmName, String name) {
		try (Session s = factory.openSession()) {
			String jpql = "SELECT COUNT(e)" + fromEntity + "WHERE e." + parmName + " = :name";
			return s.createQuery(jpql, Boolean.class).setParameter("name", name).getSingleResult();
		}
	}

	public void save(T entity) {
		Transaction tx = null;
		try (Session s = factory.openSession()) {
			tx = s.beginTransaction();
			s.persist(entity);
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
		try (Session s = factory.openSession()) {
			tx = s.beginTransaction();
			s.update(entity);
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
		try (Session s = factory.openSession()) {
			tx = s.beginTransaction();
			s.delete(entity);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				System.out.println("this error: " + e.getMessage());
				e.printStackTrace();
				tx.rollback();
			}
		}
	}

	public boolean existsById(Long id) {
		try (Session s = factory.openSession()) {
			String jpql = "SELECT COUNT(e)" + fromEntity + "WHERE e.id = :id";
			TypedQuery<Long> query = s.createQuery(jpql, Long.class).setParameter("id", id);

			return query.getSingleResult() > 0;
		}
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
}
