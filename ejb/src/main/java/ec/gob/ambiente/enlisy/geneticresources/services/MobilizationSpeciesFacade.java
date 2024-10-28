package ec.gob.ambiente.enlisy.geneticresources.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.geneticresources.model.MobilizationGuide;
import ec.gob.ambiente.enlisy.geneticresources.model.MobilizationSpecies;
import ec.gob.ambiente.enlisy.geneticresources.model.ProposedCollectionSpecies;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class MobilizationSpeciesFacade extends AbstractFacade<MobilizationSpecies, Integer>{
		

	public MobilizationSpeciesFacade() {
		super(MobilizationSpecies.class, Integer.class);		
	}
		
	/**
	 * Obtener Especies de Guia de movilizacion por id
	 * @param id
	 * @return
	 */
	public MobilizationSpecies findById(Integer id)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM MobilizationSpecies o where o.mospId= :id and o.mospStatus = true");
			query.setParameter("id", id);
			
			return (MobilizationSpecies) query.getSingleResult();
			
		}catch(NoResultException e)
		{
			return null;
		}
	}
	
	/**
	 * Obtener Especies de movilizacion por Guia de Movilizaion
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<MobilizationSpecies> findByMobilizationGuide(MobilizationGuide mobilizationGuide)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM MobilizationSpecies o where o.mobilizationGuide.moguId= :id and o.mospStatus = true");
			query.setParameter("id", mobilizationGuide.getMoguId());
			
			List<MobilizationSpecies> result=(List<MobilizationSpecies>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	/**
	 * Obtener Especies de Guias por Especies de Propuesta de recoleccion
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<MobilizationSpecies> findByProposedCollectionSpecies(ProposedCollectionSpecies proposedCollectionSpecies)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM MobilizationSpecies o where o.proposedCollectionSpecies.prcsId= :prcsId and o.mospStatus = true");
			query.setParameter("prcsId", proposedCollectionSpecies.getPrcsId());
			
			List<MobilizationSpecies> result=(List<MobilizationSpecies>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	/**
	 * Guardar
	 * @param MobilizationSpecies
	 * @param user
	 * @return
	 */
	public boolean save(MobilizationSpecies MobilizationSpecies, User user)
	{
		try
		{			
			if(MobilizationSpecies.getMospId() == null)
			{	
				
				MobilizationSpecies.setMospStatus(true);
				MobilizationSpecies.setMospDateCreate(new Date());
				MobilizationSpecies.setMospUserCreate(user.getUserName());
				create(MobilizationSpecies);
				
			}
			else
			{
				MobilizationSpecies.setMospDateUpdate(new Date());	
				MobilizationSpecies.setMospUserUpdate(user.getUserName());
				edit(MobilizationSpecies);
			}			
			
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	public void delete(MobilizationSpecies MobilizationSpecies, User user){
		MobilizationSpecies.setMospStatus(false);
		MobilizationSpecies.setMospDateUpdate(new Date());	
		MobilizationSpecies.setMospUserUpdate(user.getUserName());
		edit(MobilizationSpecies);
	}

}