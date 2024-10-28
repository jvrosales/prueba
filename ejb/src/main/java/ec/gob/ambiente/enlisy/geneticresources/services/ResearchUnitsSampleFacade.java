package ec.gob.ambiente.enlisy.geneticresources.services;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.geneticresources.model.ResearchUnits;
import ec.gob.ambiente.enlisy.geneticresources.model.ResearchUnitsSamples;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class ResearchUnitsSampleFacade extends AbstractFacade<ResearchUnitsSamples, Integer> implements Serializable{
	
	private static final long serialVersionUID = -2118791226165167941L;

	public ResearchUnitsSampleFacade() {
		super(ResearchUnitsSamples.class, Integer.class);		
	}
	


	/**
	 * Obtener Unidades de investigacion de la Propuesta de recoleccion por Id
	 * @param id
	 * @return
	 */
	public ResearchUnitsSamples findById(Integer id)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ResearchUnitsSamples o where o.reusId= :id and o.reusStatus = true");
			query.setParameter("id", id);
			
			return (ResearchUnitsSamples) query.getSingleResult();
			
		}catch(NoResultException e)
		{
			return null;
		}
	}
	
	/**
	 * Obtener  muestras por Unidades de investigacion de la Propuesta 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ResearchUnitsSamples> findByResearchUnits(ResearchUnits researchUnits)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ResearchUnitsSamples o where o.researchUnits.reunId= :id and o.reusStatus = true");
			query.setParameter("id", researchUnits.getReunId());
			
			List<ResearchUnitsSamples> result=(List<ResearchUnitsSamples>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	
	
	/**
	 * Guardar  muestras de la unidades de investigacion de la Propuesta de recoleccion
	 * @param ResearchActivities
	 * @param user
	 * @return
	 */
	public boolean save(ResearchUnitsSamples researchUnitsSamples, User user)
	{
		try
		{			
			if(researchUnitsSamples.getReusId() == null)
			{			
				researchUnitsSamples.setReusStatus(true);
				researchUnitsSamples.setReusDateCreate(new Date());
				researchUnitsSamples.setReusUserCreate(user.getUserName());
				create(researchUnitsSamples);
			}
			else
			{
				researchUnitsSamples.setReusDateUpdate(new Date());	
				researchUnitsSamples.setReusUserUpdate(user.getUserName());
				edit(researchUnitsSamples);
			}
			
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	public void delete(ResearchUnitsSamples researchUnitsSamples, User user){
		researchUnitsSamples.setReusStatus(false);
		researchUnitsSamples.setReusDateUpdate(new Date());	
		researchUnitsSamples.setReusUserUpdate(user.getUserName());
		edit(researchUnitsSamples);
	}

}