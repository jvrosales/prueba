package ec.gob.ambiente.enlisy.dao;

/*
 * Copyright (c) 2010, Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software
 * except in compliance with the terms of the license at:
 * http://developer.sun.com/berkeley_license.html
 */

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * 
 * @author SUIA
 */
public abstract class AbstractFacade<T, E> {

	private Class<T> entityClass;
	@SuppressWarnings("unused")
	private Class<E> primaryKeyClass;
  
	@PersistenceContext(unitName = "enlisyPU")
	private EntityManager em;

	protected EntityManager getEntityManager() {
		return em;
	}

	public AbstractFacade(Class<T> entityClass, Class<E> primaryKeyClass) {
		this.entityClass = entityClass;
		this.primaryKeyClass = primaryKeyClass;
	}

	public T create(T entity) {
		getEntityManager().persist(entity);
		em.flush();
		return entity;
	}

	public T edit(T entity) {
		getEntityManager().merge(entity);
		em.flush();
		return entity;
	}

	public void remove(T entity) {
		getEntityManager().remove(getEntityManager().merge(entity));
	}

	public T find(E id) {
		return getEntityManager().find(entityClass, id);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findAll() {
		javax.persistence.criteria.CriteriaQuery cq = getEntityManager()
				.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		return getEntityManager().createQuery(cq).getResultList();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findRange(int[] range) {
		javax.persistence.criteria.CriteriaQuery cq = getEntityManager()
				.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		javax.persistence.Query q = getEntityManager().createQuery(cq);
		q.setMaxResults(range[1] - range[0]);
		q.setFirstResult(range[0]);
		return q.getResultList();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int count() {
		javax.persistence.criteria.CriteriaQuery cq = getEntityManager()
				.getCriteriaBuilder().createQuery();
		javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
		cq.select(getEntityManager().getCriteriaBuilder().count(rt));
		javax.persistence.Query q = getEntityManager().createQuery(cq);
		return ((Long) q.getSingleResult()).intValue();
	}
	
	/**
	 * 
	 * <b> Ejecuta un namedQery con los parametros indicados en el mapa, en el que la clave del mapa es el nombre del
	 * parametro, los parametro de limites indican el rango del total de los registros que se necesitan. </b>
	 * 
	 * @author rene
	 * @version Revision: 1.0
	 *          <p>
	 *          [Autor: rene, Fecha: Oct 28, 2014]
	 *          </p>
	 * @param namedQueryName
	 *            nombre del namedQuery
	 * @param parameters
	 *            parametros del query
	 * @param limiteInicio
	 *            rango de inicio
	 * @param limiteFin
	 *            rango de fin
	 * @return resultado de la consulta
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByCreateQueryPaginado(final String query_,
			final Map<String, Object> parameters, int limiteInicio, int limiteFin) {
		Query query = getEntityManager().createQuery(query_).setFirstResult(limiteInicio)
				.setMaxResults(limiteFin);
		if (parameters != null) {
			Set<Entry<String, Object>> parameterSet = parameters.entrySet();
			for (Entry<String, Object> entry : parameterSet) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return query.getResultList();
	}

}