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
		return this.em.find(clazz, id);
	}

	public T create(T t) {
		this.em.persist(t);
		return t;
	}

	public T update(T t) {
		this.em.merge(t);
		return t;
	}

	public void delete(T t) {
		t = this.em.merge(t);
		this.em.remove(t);
	}

	@SuppressWarnings("unchecked")
	public List<T> list() {
		return this.em.createQuery("FROM " + this.clazz.getSimpleName()).getResultList();
	}
}
