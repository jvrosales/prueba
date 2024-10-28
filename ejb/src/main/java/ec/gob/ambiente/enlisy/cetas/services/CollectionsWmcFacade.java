package ec.gob.ambiente.enlisy.cetas.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
//import ec.gob.ambiente.enlisy.geneticresources.model.ProposedCollection;
//import ec.gob.ambiente.enlisy.geneticresources.model.ProposedCollectionSpecies;
import ec.gob.ambiente.enlisy.cetas.model.PatentApplication;
import ec.gob.ambiente.enlisy.cetas.model.WildlifeManagementCenter;
import ec.gob.ambiente.enlisy.cetas.model.CollectionsWmc;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class CollectionsWmcFacade extends AbstractFacade<CollectionsWmc, Integer>{
		

	public CollectionsWmcFacade() {
		super(CollectionsWmc.class, Integer.class);		
	}
		
	/**
	 * Obtener Especies del Centro de Tenencia por id
	 * @param id
	 * @return
	 */
	public CollectionsWmc findById(Integer id)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CollectionsWmc o where o.collId= :id and o.collStatus = true");
			query.setParameter("id", id);
			
			return (CollectionsWmc) query.getSingleResult();
			
		}catch(NoResultException e)
		{
			return null;
		}
	}
	
	/**
	 * Obtener Especies del Centro de Tenencia de la Patente por id
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CollectionsWmc> findByPatentApplication(PatentApplication patentApplication)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CollectionsWmc o where o.patentApplication.paapId= :id and o.collStatus = true");
			query.setParameter("id", patentApplication.getPaapId());
			
			List<CollectionsWmc> result=(List<CollectionsWmc>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	
	/**
	 * Obtener Especies del Centro de Tenencia de la por id del centro
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CollectionsWmc> findByWildlifeManagementCenter(WildlifeManagementCenter wildlifeManagementCenter)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CollectionsWmc o where o.wildlifeManagementCenter.wmceId= :id and o.collStatus = true");
			query.setParameter("id", wildlifeManagementCenter.getWmceId());
			
			List<CollectionsWmc> result=(List<CollectionsWmc>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	
	
	
	/**
	 * Guardar Especies del Centro de Tenencia - Zoologicos, Centros de Rescate, Jardin Botanico
	 * @param CollectionsWmc
	 * @param user
	 * @return
	 */
	public boolean save(CollectionsWmc collections, User user)
	{
		try
		{			
			if(collections.getCollId() == null)
			{	
				collections.setCollStatus(true);
				collections.setCollDateCreate(new Date());
				collections.setCollUserCreate(user.getUserName());
				create(collections);
			}
			else
			{
				collections.setCollDateUpdate(new Date());	
				collections.setCollUserUpdate(user.getUserName());
				edit(collections);
			}
			
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	
	/**
	 * Guardar Especies del Centro de Tenencia para Renovacion
	 * @param CollectionsWmc
	 * @param user
	 * @return
	 */
	/*
	public boolean save_renovate(CollectionsWmc collections, User user, PatentApplication patente)
	{
		try
		{			
			if(collections.getCollId() == null)
			{	
				collections.setCollStatus(true);
				collections.setCollDateCreate(new Date());
				collections.setCollUserCreate(user.getUserName());
				create(collections);
			}
			else
			{
				collections.setCollDateUpdate(new Date());	
				collections.setCollUserUpdate(user.getUserName());
				collections.setPatentApplication(patente);
				edit(collections);
			}
			
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	*/
	
	public void delete(CollectionsWmc collections, User user){
		collections.setCollStatus(false);
		collections.setCollDateUpdate(new Date());	
		collections.setCollUserUpdate(user.getUserName());
		edit(collections);
	}

}