package ec.gob.ambiente.enlisy.geneticresources.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.geneticresources.model.Contracts;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class ContractsFacade extends AbstractFacade<Contracts, Integer>{
	
	public ContractsFacade() {
		super(Contracts.class, Integer.class);		
	}
		
	public Integer contractCode(String sequenceName) {
		Query q;
		q = getEntityManager()
				.createNativeQuery("select nextval('"+sequenceName.toLowerCase()+"');");
		java.math.BigInteger result= (java.math.BigInteger)q.getSingleResult();
		return Integer.valueOf(result.intValue());
	}

	@SuppressWarnings("unchecked")
	public List<Contracts> findByProcessingNumber(String processingNumber)
	{		
		try {
			Query query = super.getEntityManager().createQuery("SELECT o FROM Contracts o where o.cotrProcessingNumber = :processingNumber and o.cotrStatus = true order by 1 desc");
			query.setParameter("processingNumber", processingNumber);
			
			List<Contracts> result=(List<Contracts>)query.getResultList();
			if(!result.isEmpty())
				return result;
			
		} catch (NoResultException e) {
			return null;
		}
				
		return null;
	}
	
	

	public boolean save(Contracts contract, User user)
	{
		try
		{	
			if(contract.getCotrId() == null)
			{	
				contract.setCotrStatus(true);
				contract.setCotrDateCreate(new Date());
				contract.setCotrUserCreate(user.getUserName());
				create(contract);
				return true;
				
			}
			else
			{
				contract.setCotrDateUpdate(new Date());
				contract.setCotrUserUpdate(user.getUserName());
				edit(contract);
				return true;
			}
			
			
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Contracts> findByProcessingNumberMod(String processingNumber)
	{		
		try {
			Query query = super.getEntityManager().createQuery("SELECT o FROM Contracts o where o.cotrProcessingNumber = :processingNumber and o.cotrStatus = true and o.cotrModifyingContract = true order by 1 desc");
			query.setParameter("processingNumber", processingNumber);
			
			List<Contracts> result=(List<Contracts>)query.getResultList();
			if(!result.isEmpty())
				return result;
			
		} catch (NoResultException e) {
			return null;
		}
				
		return null;
	}
	


}