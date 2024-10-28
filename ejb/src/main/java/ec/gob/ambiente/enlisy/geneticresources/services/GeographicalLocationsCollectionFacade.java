package ec.gob.ambiente.enlisy.geneticresources.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.geneticresources.model.GeographicalLocationsCollection;
import ec.gob.ambiente.enlisy.geneticresources.model.ProposedCollection;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class GeographicalLocationsCollectionFacade extends AbstractFacade<GeographicalLocationsCollection, Integer>{
		

	public GeographicalLocationsCollectionFacade() {
		super(GeographicalLocationsCollection.class, Integer.class);		
	}
		
	/**
	 * Obtener Ubicacion Geografica de la Propuesta de recoleccion por id
	 * @param id
	 * @return
	 */
	public GeographicalLocationsCollection findById(Integer id)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM GeographicalLocationsCollection o where o.gelcId= :id and o.gelcStatus = true");
			query.setParameter("id", id);
			
			return (GeographicalLocationsCollection) query.getSingleResult();
			
		}catch(NoResultException e)
		{
			return null;
		}
	}
	
	/**
	 * Obtener Ubicacion Geografica de la Propuesta de recoleccion por id
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<GeographicalLocationsCollection> findByProposedCollection(ProposedCollection proposedCollection)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM GeographicalLocationsCollection o where o.proposedCollection.prcoId= :id and o.gelcStatus = true order  by 1");
			query.setParameter("id", proposedCollection.getPrcoId());
			
			List<GeographicalLocationsCollection> result=(List<GeographicalLocationsCollection>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	
	
	
	/**
	 * Guardar Ubicacion Geografica de la Propuesta de recoleccion
	 * @param GeographicalLocationsCollection
	 * @param user
	 * @return
	 */
	public boolean save(GeographicalLocationsCollection GeographicalLocationsCollection, User user)
	{
		try
		{			
			if(GeographicalLocationsCollection.getGelcId() == null)
			{	
				
				GeographicalLocationsCollection.setGelcStatus(true);
				GeographicalLocationsCollection.setGelcDateCreate(new Date());
				GeographicalLocationsCollection.setGelcUserCreate(user.getUserName());
				create(GeographicalLocationsCollection);
				
			}
			else
			{
				GeographicalLocationsCollection.setGelcDateUpdate(new Date());	
				GeographicalLocationsCollection.setGelcUserUpdate(user.getUserName());
				edit(GeographicalLocationsCollection);
			}
			
			
			
			
			
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	public void delete(GeographicalLocationsCollection GeographicalLocationsCollection, User user){
		GeographicalLocationsCollection.setGelcStatus(false);
		GeographicalLocationsCollection.setGelcDateUpdate(new Date());	
		GeographicalLocationsCollection.setGelcUserUpdate(user.getUserName());
		edit(GeographicalLocationsCollection);
	}

}