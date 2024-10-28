package ec.gob.ambiente.enlisy.geneticresources.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.geneticresources.model.ProposedCollection;
import ec.gob.ambiente.enlisy.geneticresources.model.TechnicalTeamsResearch;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class TechnicalTeamsResearchFacade extends AbstractFacade<TechnicalTeamsResearch, Integer>{

	public TechnicalTeamsResearchFacade() {
		super(TechnicalTeamsResearch.class, Integer.class);
	}
		
	/**
	 * Obtener Datos de la Propuesta de recoleccion por id
	 * @param id
	 * @return
	 */
	public TechnicalTeamsResearch findById(Integer id)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM TechnicalTeamsResearch o where o.tetrId= :id and o.tetrStatus = true");
			query.setParameter("id", id);
			
			return (TechnicalTeamsResearch) query.getSingleResult();
			
		}catch(NoResultException e)
		{
			return null;
		}
	}
	
	/**
	 * Obtener Investigador de la Propuesta de recoleccion por id
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TechnicalTeamsResearch> findByProposedCollection(ProposedCollection proposedCollection)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM TechnicalTeamsResearch o where o.proposedCollection.prcoId= :id and o.tetrStatus = true order by o.tetrId ");
			query.setParameter("id", proposedCollection.getPrcoId());
			
			List<TechnicalTeamsResearch> result=(List<TechnicalTeamsResearch>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	/**
	 * Obtener Investigador nuevos ingresados en la actualizacion de la Propuesta de recoleccion por id
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TechnicalTeamsResearch> findNewTechnickaByProposedCollection(ProposedCollection proposedCollection)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM TechnicalTeamsResearch o where o.proposedCollection.prcoId= :id and o.tetrStatus = true and (o.tetrOriginalRecord is false or o.tetrOriginalRecord is null) order by o.tetrId asc ");
			query.setParameter("id", proposedCollection.getPrcoId());
			
			List<TechnicalTeamsResearch> result=(List<TechnicalTeamsResearch>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	/**
	 * Obtener Investigadores eliminados ingresados en la actualizacion de la Propuesta de recoleccion por id
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TechnicalTeamsResearch> findDeleteTechnickaByProposedCollection(ProposedCollection proposedCollection)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM TechnicalTeamsResearch o where o.proposedCollection.prcoId= :id and o.tetrStatus is false and o.tetrEliminationJustification is not null and o.tetrOriginalRecord is true order by o.tetrId asc ");
			query.setParameter("id", proposedCollection.getPrcoId());
			
			List<TechnicalTeamsResearch> result=(List<TechnicalTeamsResearch>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	
	
	/**
	 * Guardar Investigador de la Propuesta de recoleccion
	 * @param TechnicalTeamsResearch
	 * @param user
	 * @return
	 */
	public boolean save(TechnicalTeamsResearch TechnicalTeamsResearch, User user)
	{
		try
		{			
			if(TechnicalTeamsResearch.getTetrId() == null)
			{	
				
				TechnicalTeamsResearch.setTetrStatus(true);
				TechnicalTeamsResearch.setTetrDateCreate(new Date());
				TechnicalTeamsResearch.setTetrUserCreate(user.getUserName());
				create(TechnicalTeamsResearch);
				
			}
			else
			{
				TechnicalTeamsResearch.setTetrDateUpdate(new Date());	
				TechnicalTeamsResearch.setTetrUserUpdate(user.getUserName());
				edit(TechnicalTeamsResearch);
			}
			
			
			
			
			
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	public void delete(TechnicalTeamsResearch TechnicalTeamsResearch, User user){
		TechnicalTeamsResearch.setTetrStatus(false);
		TechnicalTeamsResearch.setTetrDateUpdate(new Date());	
		TechnicalTeamsResearch.setTetrUserUpdate(user.getUserName());
		edit(TechnicalTeamsResearch);
	}

}
