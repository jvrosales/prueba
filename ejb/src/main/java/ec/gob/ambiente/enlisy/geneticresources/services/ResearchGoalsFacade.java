package ec.gob.ambiente.enlisy.geneticresources.services;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.geneticresources.model.ProposedCollection;
import ec.gob.ambiente.enlisy.geneticresources.model.ResearchGoals;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class ResearchGoalsFacade extends AbstractFacade<ResearchGoals, Integer> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1398678149201410665L;

	public ResearchGoalsFacade() {
		super(ResearchGoals.class, Integer.class);		
	}

	/**
	 * Obtener Metas de la Investigacion de la Propuesta de recoleccion por id
	 * @param id
	 * @return
	 */
	public ResearchGoals findById(Integer id)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ResearchGoals o where o.regoId= :id and o.regoStatus = true");
			query.setParameter("id", id);
			
			return (ResearchGoals) query.getSingleResult();
			
		}catch(NoResultException e)
		{
			return null;
		}
	}
	
	/**
	 * Obtener  Metas de la Investigacion de la Propuesta de recoleccion por id
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ResearchGoals> findByProposedCollection(ProposedCollection proposedCollection)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ResearchGoals o where o.proposedCollection.prcoId= :id and o.regoStatus = true");
			query.setParameter("id", proposedCollection.getPrcoId());
			
			List<ResearchGoals> result=(List<ResearchGoals>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	
	
	
	/**
	 * Guardar  Metas de la Investigacion de la Propuesta de recoleccion
	 * @param ResearchGoals
	 * @param user
	 * @return
	 */
	public boolean save(ResearchGoals researchActivities, User user)
	{
		try
		{			
			if(researchActivities.getRegoId() == null)
			{	
				
				researchActivities.setRegoStatus(true);
				researchActivities.setRegoDateCreate(new Date());
				researchActivities.setRegoUserCreate(user.getUserName());
				create(researchActivities);
				
			}
			else
			{
				researchActivities.setRegoDateUpdate(new Date());	
				researchActivities.setRegoUserUpdate(user.getUserName());
				edit(researchActivities);
			}
			
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	public void delete(ResearchGoals researchActivities, User user){
		researchActivities.setRegoStatus(false);
		researchActivities.setRegoDateUpdate(new Date());	
		researchActivities.setRegoUserUpdate(user.getUserName());
		edit(researchActivities);
	}

}