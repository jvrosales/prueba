package ec.gob.ambiente.enlisy.geneticresources.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.geneticresources.model.ProposedCollection;
import ec.gob.ambiente.enlisy.geneticresources.model.ProposedCollectionHandlingCenter;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class ProposedCollectionHandlingCenterFacade extends AbstractFacade<ProposedCollectionHandlingCenter, Integer>{


	public ProposedCollectionHandlingCenterFacade() {
		super(ProposedCollectionHandlingCenter.class, Integer.class);		
	}
	
	public ProposedCollectionHandlingCenter findByCode(Integer code)
	{
		try
		{			
			Query query = super.getEntityManager().createQuery("SELECT p FROM ProposedCollectionHandlingCenter p, ProposedCollection o  where    p.proposedCollection.prcoId=o.prcoId and o.prcoId= :code  and  p.pchcStatus=true ", ProposedCollectionHandlingCenter.class);
			query.setParameter("code", code);
			
			return (ProposedCollectionHandlingCenter) query.getSingleResult();
			
		}catch(NoResultException e)
		{
			return null;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ProposedCollectionHandlingCenter> findByProposedCollection(ProposedCollection proposedCollection)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ProposedCollectionHandlingCenter o where o.proposedCollection.prcoId= :id and o.pchcStatus = true");
			query.setParameter("id", proposedCollection.getPrcoId());
			
			List<ProposedCollectionHandlingCenter> result=(List<ProposedCollectionHandlingCenter>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	
	
	public ProposedCollectionHandlingCenter findByProposedCollectionBiologicalGroup(ProposedCollection proposedCollection, String biologicalGroups)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ProposedCollectionHandlingCenter o where o.proposedCollection.prcoId= :id and  o.pchcBiologicalGroups= :biologicalGroups  and o.pchcStatus = true order by o.pchcId desc");
			query.setParameter("id", proposedCollection.getPrcoId());
			query.setParameter("biologicalGroups",biologicalGroups);					
			//return (ProposedPatent) query.getSingleResult();
			
			
			if(query.getResultList().size() > 0)
			{
				ProposedCollectionHandlingCenter catalog = (ProposedCollectionHandlingCenter) query.getResultList().get(0);	
				return catalog;
			}
			return null;

		}catch(NoResultException e)
		{
			return null;
		}
	}
	
	
	
	
		
	public boolean save(ProposedCollectionHandlingCenter propPatent , User user)
	{
		try
		{			
			if(propPatent.getPchcId() == null)
			{	
				
				propPatent.setPchcStatus(true);
				propPatent.setPchcDateCreate(new Date());
				propPatent.setPchcUserCreate(user.getUserName());
				create(propPatent);
				
			}
			else
			{
				propPatent.setPchcDateUpdate(new Date());
				propPatent.setPchcUserUpdate(user.getUserName());
				edit(propPatent);
			}
			
			
			
			
			
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	public void delete(ProposedCollectionHandlingCenter propPatent, User user){
		propPatent.setPchcStatus(false);
		propPatent.setPchcDateUpdate(new Date());
		propPatent.setPchcUserUpdate(user.getUserName());
		edit(propPatent);
	}
}

