package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.speciescatalog.model.CatalogUse;



/**
 * Servicios para la administracion de catalogo de reproduccion 
 * @author EXCO
 *
 */
@Stateless
public class CatalogUseFacade extends AbstractFacade<CatalogUse, Integer>{

	public CatalogUseFacade() {
		super(CatalogUse.class, Integer.class);		
	}
	
		
	/**
	 * Recuperar lista de usos reino animal
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CatalogUse> findUsesAnimal()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CatalogUse o where o.causStatus = true and o.causAnimal=true");
			List<CatalogUse> result=(List<CatalogUse>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CatalogUse>();
		}
		return new ArrayList<CatalogUse>();
	}
	
	/**
	 * Recuperar lista de usos reino plantae
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CatalogUse> findUsesPlantae()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CatalogUse o where o.causStatus = true and o.causPlantae=true");
			List<CatalogUse> result=(List<CatalogUse>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CatalogUse>();
		}
		return new ArrayList<CatalogUse>();
	}
	
	/**
	 * Recuperar lista de usos reino fungi
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CatalogUse> findUsesFungi()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CatalogUse o where o.causStatus = true and o.causFungi=true");
			List<CatalogUse> result=(List<CatalogUse>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CatalogUse>();
		}
		return new ArrayList<CatalogUse>();
	}
	
	/**
	 * Recuperar lista de usos reino eubacteria
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CatalogUse> findUsesEubacteria()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CatalogUse o where o.causStatus = true and o.causEubacteria=true");
			List<CatalogUse> result=(List<CatalogUse>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CatalogUse>();
		}
		return new ArrayList<CatalogUse>();
	}
	
	/**
	 * Recuperar lista de usos reino arqueobacteria
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CatalogUse> findUsesArqueobacteria()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CatalogUse o where o.causStatus = true and o.causArcheobacteria=true");
			List<CatalogUse> result=(List<CatalogUse>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CatalogUse>();
		}
		return new ArrayList<CatalogUse>();
	}
	
	/**
	 * Recuperar lista de reproduccion reino protista
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CatalogUse> findUsesProtista()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CatalogUse o where o.causStatus = true and o.causProtista=true");
			List<CatalogUse> result=(List<CatalogUse>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CatalogUse>();
		}
		return new ArrayList<CatalogUse>();
	}
	
	/**
	 * Recuperar lista de reproduccion reino chromista
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CatalogUse> findUsesChromista()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CatalogUse o where o.causStatus = true and o.causChromista=true");
			List<CatalogUse> result=(List<CatalogUse>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CatalogUse>();
		}
		return new ArrayList<CatalogUse>();
	}
	
	/**
	 * Recuperar lista de reproduccion reino viruses
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CatalogUse> findUsesViruses()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CatalogUse o where o.causStatus = true and o.causViruses=true");
			List<CatalogUse> result=(List<CatalogUse>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CatalogUse>();
		}
		return new ArrayList<CatalogUse>();
	}
}
