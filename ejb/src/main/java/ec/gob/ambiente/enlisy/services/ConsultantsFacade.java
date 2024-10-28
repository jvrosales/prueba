package ec.gob.ambiente.enlisy.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.Consultants;

@Stateless
public class ConsultantsFacade extends AbstractFacade<Consultants, Integer> {

	public ConsultantsFacade() {
		super(Consultants.class, Integer.class);		
	}
	
	/**
	 * Buscar Consultor
	 * @param ruc
	 * @return
	 */	
	@SuppressWarnings("unchecked")
	public Consultants findByRuc(String param) {
		try {
			Query query = getEntityManager().createQuery(" select o from Consultants o where o.consStatus=true and o.consRuc like :param");		
			query.setParameter("param",	param+"%");
			List<Consultants> result=query.getResultList();
			if(result!=null && !result.isEmpty())
				return result.get(0);
		} catch (NoResultException e) {
			return null;
		}
		return null;
				
	}
	
	/**
	 * Buscar Consultor
	 * @param codigo registro
	 * @return
	 */	
	public Consultants findByRecord(String param) {
		try {
			Query query = getEntityManager().createQuery(" select o from Consultants o where o.consStatus=true and o.consRecord = :param");		
			query.setParameter("param",	param);
			return (Consultants) query.getSingleResult();			
		} catch (NoResultException e) {
			return null;
		}
				
	}
	
}