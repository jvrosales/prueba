package ec.gob.ambiente.enlisy.geneticresources.services;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.geneticresources.model.ProposedCollection;
import ec.gob.ambiente.enlisy.geneticresources.model.ProposedCollectionEquipment;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class ProposedCollectionEquipmentFacade extends AbstractFacade<ProposedCollectionEquipment, Integer> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8084714275922546669L;

	public ProposedCollectionEquipmentFacade() {
		super(ProposedCollectionEquipment.class, Integer.class);		
	}
	
	public ProposedCollectionEquipment findById(Integer id)
	{
		Query query = super.getEntityManager().createQuery("select o from ProposedCollectionEquipment o where o.prceId = :id and o.prceStatus = true");
		query.setParameter("id", id);
		
		if(query.getResultList().size() > 0)
		{
			ProposedCollectionEquipment catalog = (ProposedCollectionEquipment) query.getResultList().get(0);	
			return catalog;
		}
		
		return null;
	}

	
	/**
	 * Obtener Equipos y materiales de la Propuesta de recoleccion por id
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ProposedCollectionEquipment> findByProposedCollection(ProposedCollection proposedCollection)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ProposedCollectionEquipment o where o.proposedCollection.prcoId= :id and o.prceStatus = true");
			query.setParameter("id", proposedCollection.getPrcoId());
			
			List<ProposedCollectionEquipment> result=(List<ProposedCollectionEquipment>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	
	
	
	/**
	 * Guardar  Equipos y materiales de la propuesta
	 * @param ProposedCollectionEquipment
	 * @param user
	 * @return
	 */
	public boolean save(ProposedCollectionEquipment proposedCollectionEquipment, User user)
	{
		try
		{			
			if(proposedCollectionEquipment.getPrceId() == null)
			{					
				proposedCollectionEquipment.setPrceStatus(true);
				proposedCollectionEquipment.setPrceDateCreate(new Date());
				proposedCollectionEquipment.setPrceUserCreate(user.getUserName());
				create(proposedCollectionEquipment);
				
			}
			else
			{
				proposedCollectionEquipment.setPrceDateUpdate(new Date());	
				proposedCollectionEquipment.setPrceUserUpdate(user.getUserName());
				edit(proposedCollectionEquipment);
			}
			
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	public void delete(ProposedCollectionEquipment proposedCollectionEquipment, User user){
		proposedCollectionEquipment.setPrceStatus(false);
		proposedCollectionEquipment.setPrceDateUpdate(new Date());	
		proposedCollectionEquipment.setPrceUserUpdate(user.getUserName());
		edit(proposedCollectionEquipment);
	}
	
}