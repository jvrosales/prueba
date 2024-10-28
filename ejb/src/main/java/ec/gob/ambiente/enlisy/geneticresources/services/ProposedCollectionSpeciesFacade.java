package ec.gob.ambiente.enlisy.geneticresources.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.geneticresources.model.ProposedCollection;
import ec.gob.ambiente.enlisy.geneticresources.model.ProposedCollectionSpecies;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class ProposedCollectionSpeciesFacade extends AbstractFacade<ProposedCollectionSpecies, Integer>{

	public ProposedCollectionSpeciesFacade() {
		super(ProposedCollectionSpecies.class, Integer.class);
	}
		
	/**
	 * Obtener Especies a recolectar de la Propuesta de recoleccion por id
	 * @param id
	 * @return
	 */
	public ProposedCollectionSpecies findById(Integer id)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ProposedCollectionSpecies o where o.prcsId= :id and o.prcsStatus = true");
			query.setParameter("id", id);
			
			return (ProposedCollectionSpecies) query.getSingleResult();
			
		}catch(NoResultException e)
		{
			return null;
		}
	}
	
	/**
	 * Obtener  Especies a recolectar de la Propuesta de recoleccion por id
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ProposedCollectionSpecies> findByProposedCollection(ProposedCollection proposedCollection)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ProposedCollectionSpecies o where o.proposedCollection.prcoId= :id and o.prcsStatus = true order by o.prcsId asc");
			query.setParameter("id", proposedCollection.getPrcoId());
			
			List<ProposedCollectionSpecies> result=(List<ProposedCollectionSpecies>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	/**
	 * Obtener  Especies a recolectar de la Propuesta de recoleccion por id
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ProposedCollectionSpecies> findNewEspeciesByProposedCollection(ProposedCollection proposedCollection)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ProposedCollectionSpecies o where o.proposedCollection.prcoId= :id and o.prcsStatus = true and (o.prcsOriginalRecord is false or o.prcsOriginalRecord is null) order by o.prcsId asc");
			query.setParameter("id", proposedCollection.getPrcoId());
			
			List<ProposedCollectionSpecies> result=(List<ProposedCollectionSpecies>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	
	
	
	/**
	 * Guardar  Especies a recolectar de la Propuesta de recoleccion
	 * @param ProposedCollectionSpecies
	 * @param user
	 * @return
	 */
	public boolean save(ProposedCollectionSpecies ProposedCollectionSpecies, User user)
	{
		try
		{			
			if(ProposedCollectionSpecies.getPrcsId() == null)
			{	
				
				ProposedCollectionSpecies.setPrcsStatus(true);
				ProposedCollectionSpecies.setPrcsDateCreate(new Date());
				ProposedCollectionSpecies.setPrcsUserCreate(user.getUserName());
				create(ProposedCollectionSpecies);
				
			}
			else
			{
				ProposedCollectionSpecies.setPrcsDateUpdate(new Date());	
				ProposedCollectionSpecies.setPrcsUserUpdate(user.getUserName());
				edit(ProposedCollectionSpecies);
			}
			
			
			
			
			
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	public void delete(ProposedCollectionSpecies ProposedCollectionSpecies, User user){
		ProposedCollectionSpecies.setPrcsStatus(false);
		ProposedCollectionSpecies.setPrcsDateUpdate(new Date());	
		ProposedCollectionSpecies.setPrcsUserUpdate(user.getUserName());
		edit(ProposedCollectionSpecies);
	}

}
