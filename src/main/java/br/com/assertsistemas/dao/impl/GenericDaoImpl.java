package br.com.assertsistemas.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.assertsistemas.dao.GenericDAO;

public class GenericDaoImpl<T, ID extends Serializable> implements GenericDAO<T, ID> {

	private static final long serialVersionUID = 8156347281193565598L;
	private EntityManager entityManager;
	private Class<T> classGeneric;

	public GenericDaoImpl(EntityManager entityManager, Class<T> classGeneric) {
		this.entityManager = entityManager;
		this.classGeneric = classGeneric;
	}

	public void insert(T t) throws Exception {
		entityManager.getTransaction().begin();
		entityManager.persist(t);
		entityManager.getTransaction().commit();
	}

	public void update(T t) throws Exception {
		entityManager.getTransaction().begin();
		entityManager.merge(t);
		entityManager.getTransaction().commit();
	}

	public void delete(T t) throws Exception {
		entityManager.getTransaction().begin();
		entityManager.remove(t);
		entityManager.getTransaction().commit();

	}

	public T findById(ID id) throws Exception {
		return entityManager.find(classGeneric, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() throws Exception {
		return entityManager.createQuery("SELECT o FROM " + classGeneric.getName() + " o").getResultList();
	}

}
