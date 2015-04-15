package br.com.jsf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractDao<T> {

	@PersistenceContext(unitName = "primary")
	private EntityManager em;
	private final Class<T> clazz;

	public AbstractDao(Class<T> clazz) {
		this.clazz = clazz;
	}

	public T find(Object id) {
		return em.find(clazz, id);
	}

	public void create(T t) {
		em.persist(t);
	}

	public void update(T t) {
		em.merge(t);
	}

	public void delete(T t) {
		em.remove(t);
	}

	@SuppressWarnings("unchecked")
	public List<T> list() {
		return this.em.createQuery("FROM " + this.clazz.getSimpleName()).getResultList();
	}
}
