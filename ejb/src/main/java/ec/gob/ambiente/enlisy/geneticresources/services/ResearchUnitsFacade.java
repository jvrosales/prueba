package ec.gob.ambiente.enlisy.geneticresources.services;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.geneticresources.model.ProposedCollection;
import ec.gob.ambiente.enlisy.geneticresources.model.ResearchUnits;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class ResearchUnitsFacade extends AbstractFacade<ResearchUnits, Integer> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6615996860450258681L;

	public ResearchUnitsFacade() {
		super(ResearchUnits.class, Integer.class);		
	}
	


	/**
	 * Obtener Unidades de investigacion de la Propuesta de recoleccion por Id
	 * @param id
	 * @return
	 */
	public ResearchUnits findById(Integer id)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ResearchUnits o where o.reunId= :id and o.reunStatus = true");
			query.setParameter("id", id);
			
			return (ResearchUnits) query.getSingleResult();
			
		}catch(NoResultException e)
		{
			return null;
		}
	}
	
	/**
	 * Obtener  Unidades de investigacion de la Propuesta de recoleccion por Propuesta
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ResearchUnits> findByProposedCollection(ProposedCollection proposedCollection)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ResearchUnits o where o.proposedCollection.prcoId= :id and o.reunStatus = true");
			query.setParameter("id", proposedCollection.getPrcoId());
			
			List<ResearchUnits> result=(List<ResearchUnits>)query.getResultList();
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
	public boolean save(ResearchUnits researchUnits, User user)
	{
		try
		{			
			if(researchUnits.getReunId() == null)
			{	
				
				researchUnits.setReunStatus(true);
				researchUnits.setReunDateCreate(new Date());
				researchUnits.setReunUserCreate(user.getUserName());
				create(researchUnits);
				
			}
			else
			{
				researchUnits.setReunDateUpdate(new Date());	
				researchUnits.setReunUserUpdate(user.getUserName());
				edit(researchUnits);
			}
			
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	public void delete(ResearchUnits researchUnits, User user){
		researchUnits.setReunStatus(false);
		researchUnits.setReunDateUpdate(new Date());	
		researchUnits.setReunUserUpdate(user.getUserName());
		edit(researchUnits);
	}

}