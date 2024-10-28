package ec.gob.ambiente.enlisy.geneticresources.services;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.geneticresources.model.ProposedCollection;
import ec.gob.ambiente.enlisy.geneticresources.model.ResearchActivities;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class ResearchActivitiesFacade extends AbstractFacade<ResearchActivities, Integer> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5462256967867829789L;

	public ResearchActivitiesFacade() {
		super(ResearchActivities.class, Integer.class);		
	}
	


	/**
	 * Obtener Activades de recoleccion de la Propuesta de recoleccion por id
	 * @param id
	 * @return
	 */
	public ResearchActivities findById(Integer id)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ResearchActivities o where o.reacId= :id and o.reacStatus = true");
			query.setParameter("id", id);
			
			return (ResearchActivities) query.getSingleResult();
			
		}catch(NoResultException e)
		{
			return null;
		}
	}
	
	/**
	 * Obtener  Activades de recoleccion de la Propuesta de recoleccion por id
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ResearchActivities> findByProposedCollection(ProposedCollection proposedCollection)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ResearchActivities o where o.proposedCollection.prcoId= :id and o.reacStatus = true");
			query.setParameter("id", proposedCollection.getPrcoId());
			
			List<ResearchActivities> result=(List<ResearchActivities>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	
	
	
	/**
	 * Guardar  Activades de recoleccion de la Propuesta de recoleccion
	 * @param ResearchActivities
	 * @param user
	 * @return
	 */
	public boolean save(ResearchActivities researchActivities, User user)
	{
		try
		{			
			if(researchActivities.getReacId() == null)
			{	
				
				researchActivities.setReacStatus(true);
				researchActivities.setReacDateCreate(new Date());
				researchActivities.setReacUserCreate(user.getUserName());
				create(researchActivities);
				
			}
			else
			{
				researchActivities.setReacDateUpdate(new Date());	
				researchActivities.setReacUserUpdate(user.getUserName());
				edit(researchActivities);
			}
			
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	public void delete(ResearchActivities researchActivities, User user){
		researchActivities.setReacStatus(false);
		researchActivities.setReacDateUpdate(new Date());	
		researchActivities.setReacUserUpdate(user.getUserName());
		edit(researchActivities);
	}
	
	//control y seguimiento
	@SuppressWarnings("unchecked")
	public List<ResearchActivities> findByProposedCollectionFieldActivity(ProposedCollection proposedCollection)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ResearchActivities o "
					+ "where o.proposedCollection.prcoId= :id and "
					+ "o.reacStatus = true ");
//					+ "and "
//					+ "o.reacDateStart <= :fechaInicio and "
//					+ "o.reacDateEnd >= :fechaFin ");
			
			query.setParameter("id", proposedCollection.getPrcoId());
//			query.setParameter("fechaInicio", new Date());
//			query.setParameter("fechaFin", new Date());
			
			List<ResearchActivities> result=(List<ResearchActivities>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<ResearchActivities> findByDate()
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ResearchActivities o "
					+ "where o.reacStatus = true and "
					+ "((CURRENT_DATE BETWEEN o.reacDateStart and o.reacDateEnd) or "
					+ " extract(MONTH from CURRENT_DATE) = extract(MONTH from o.reacDateStart)) and "
					+ "o.reacFieldActivity = 'true' and "
					+ "o.proposedCollection.prcoStatusProposed = :status and "
					+ "o.proposedCollection.prcoControl is null order by 1"); 
			
			query.setParameter("status", "APROBADO");
			
			/*
			 * 
			 * ((CURRENT_DATE BETWEEN prco_date_start and prco_date_end )
or extract(MONTH FROM CURRENT_DATE) = extract(MONTH FROM prco_date_start))

			 * MONTH(p.pccmControlPeriod) = :mes "
				+ "and YEAR(p.pccmControlPeriod) = :anio"
			 */
			
//			query.setParameter("fechaInicio", new Date());
//			query.setParameter("fechaFin", new Date());
			
			List<ResearchActivities> result=(List<ResearchActivities>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<ResearchActivities> findByFecha(ProposedCollection propuesta)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ResearchActivities o "
					+ "where o.reacStatus = true and "
					+ "((CURRENT_DATE BETWEEN o.reacDateStart and o.reacDateEnd) or "
					+ " extract(MONTH from CURRENT_DATE) = extract(MONTH from o.reacDateStart)) and "
					+ "o.proposedCollection = :propuesta  and "
					+ "o.reacFieldActivity = 'true' order by 1"); 
			
			query.setParameter("propuesta", propuesta);
						
			List<ResearchActivities> result=(List<ResearchActivities>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}

}