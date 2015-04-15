package br.com.jsf.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractDao<T> {

	private Class<T> entityClass;

	@PersistenceContext(unitName = "primary")
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public AbstractDao() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
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
		return this.em.createQuery("FROM " + entityClass.getSimpleName()).getResultList();
	}
}
