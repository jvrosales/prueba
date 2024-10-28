package ec.gob.ambiente.enlisy.geneticresources.services;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.geneticresources.model.ProposedCollection;
import ec.gob.ambiente.enlisy.geneticresources.model.ResearchFunding;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class ResearchFundingFacade extends AbstractFacade<ResearchFunding, Integer> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8210144411114417572L;

	public ResearchFundingFacade() {
		super(ResearchFunding.class, Integer.class);		
	}
	


	/**
	 * Obtener Financiamiento de investigacion de la Propuesta de recoleccion por Id
	 * @param id
	 * @return
	 */
	public ResearchFunding findById(Integer id)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ResearchFunding o where o.refuId= :id and o.refuStatus = true");
			query.setParameter("id", id);
			
			return (ResearchFunding) query.getSingleResult();
			
		}catch(NoResultException e)
		{
			return null;
		}
	}
	
	/**
	 * Obtener  Financiamiento de investigacion de la Propuesta de recoleccion por Propuesta
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ResearchFunding> findByProposedCollection(ProposedCollection proposedCollection)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ResearchFunding o where o.proposedCollection.prcoId= :id and o.refuStatus = true");
			query.setParameter("id", proposedCollection.getPrcoId());
			
			List<ResearchFunding> result=(List<ResearchFunding>)query.getResultList();
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
	public boolean save(ResearchFunding researchFunding, User user)
	{
		try
		{			
			if(researchFunding.getRefuId() == null)
			{	
				
				researchFunding.setRefuStatus(true);
				researchFunding.setRefuDateCreate(new Date());
				researchFunding.setRefuUserCreate(user.getUserName());
				create(researchFunding);
				
			}
			else
			{
				researchFunding.setRefuDateUpdate(new Date());	
				researchFunding.setRefuUserUpdate(user.getUserName());
				edit(researchFunding);
			}
			
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	public void delete(ResearchFunding researchFunding, User user){
		researchFunding.setRefuStatus(false);
		researchFunding.setRefuDateUpdate(new Date());	
		researchFunding.setRefuUserUpdate(user.getUserName());
		edit(researchFunding);
	}

}