package ec.gob.ambiente.enlisy.geneticresources.services;

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.geneticresources.model.ProposedRenovate;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class ProposedRenovateFacade extends AbstractFacade<ProposedRenovate, Integer>{


	public ProposedRenovateFacade() {
		super(ProposedRenovate.class, Integer.class);		
	}
	
	public ProposedRenovate findByCode(Integer code)
	{
		try
		{			
			Query query = super.getEntityManager().createQuery("SELECT p FROM ProposedRenovate p, ProposedCollection o  where    p.proposedCollection.prcoId=o.prcoId and o.prcoId= :code  and  p.prreStatus=true ", ProposedRenovate.class);
			query.setParameter("code", code);
			
			return (ProposedRenovate) query.getSingleResult();
			
		}catch(NoResultException e)
		{
			return null;
		}
	}
		
	public boolean save(ProposedRenovate propRenovate, User user)
	{
		try
		{			
			if(propRenovate.getPrreId() == null)
			{	
				
				propRenovate.setPrreStatus(true);
				propRenovate.setPrreDateCreate(new Date());
				propRenovate.setPrreUserCreate(user.getUserName());
				create(propRenovate);
				
			}
			else
			{
				propRenovate.setPrreDateUpdate(new Date());
				propRenovate.setPrreUserUpdate(user.getUserName());
				edit(propRenovate);
			}
			
			
			
			
			
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	public void delete(ProposedRenovate proposedRenovate, User user){
		proposedRenovate.setPrreStatus(false);
		proposedRenovate.setPrreDateUpdate(new Date());
		proposedRenovate.setPrreUserUpdate(user.getUserName());
		edit(proposedRenovate);
	}
}
