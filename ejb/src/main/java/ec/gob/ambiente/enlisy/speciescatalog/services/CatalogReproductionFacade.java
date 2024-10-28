package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.speciescatalog.model.CatalogReproduction;



/**
 * Servicios para la administracion de catalogo de reproduccion 
 * @author EXCO
 *
 */
@Stateless
public class CatalogReproductionFacade extends AbstractFacade<CatalogReproduction, Integer>{

	public CatalogReproductionFacade() {
		super(CatalogReproduction.class, Integer.class);		
	}
	
		
	/**
	 * Recuperar lista de reproduccion reino animal
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CatalogReproduction> findReproductionsAnimal()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CatalogReproduction o where o.crepStatus = true and o.crepAnimal=true");
			List<CatalogReproduction> result=(List<CatalogReproduction>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CatalogReproduction>();
		}
		return new ArrayList<CatalogReproduction>();
	}
	
	/**
	 * Recuperar lista de reproduccion reino plantae
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CatalogReproduction> findReproductionsPlantae()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CatalogReproduction o where o.crepStatus = true and o.crepPlantae=true");
			List<CatalogReproduction> result=(List<CatalogReproduction>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CatalogReproduction>();
		}
		return new ArrayList<CatalogReproduction>();
	}
	
	/**
	 * Recuperar lista de reproduccion reino fungi
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CatalogReproduction> findReproductionsFungi()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CatalogReproduction o where o.crepStatus = true and o.crepFungi=true");
			List<CatalogReproduction> result=(List<CatalogReproduction>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CatalogReproduction>();
		}
		return new ArrayList<CatalogReproduction>();
	}
	
	/**
	 * Recuperar lista de reproduccion reino eubacteria
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CatalogReproduction> findReproductionsEubacteria()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CatalogReproduction o where o.crepStatus = true and o.crepEubacteria=true");
			List<CatalogReproduction> result=(List<CatalogReproduction>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CatalogReproduction>();
		}
		return new ArrayList<CatalogReproduction>();
	}
	
	/**
	 * Recuperar lista de reproduccion reino arqueobacteria
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CatalogReproduction> findReproductionsArqueobacteria()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CatalogReproduction o where o.crepStatus = true and o.crepArcheobacteria=true");
			List<CatalogReproduction> result=(List<CatalogReproduction>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CatalogReproduction>();
		}
		return new ArrayList<CatalogReproduction>();
	}
	
	/**
	 * Recuperar lista de reproduccion reino protista
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CatalogReproduction> findReproductionsProtista()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CatalogReproduction o where o.crepStatus = true and o.crepProtista=true");
			List<CatalogReproduction> result=(List<CatalogReproduction>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CatalogReproduction>();
		}
		return new ArrayList<CatalogReproduction>();
	}
	
	/**
	 * Recuperar lista de reproduccion reino chromista
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CatalogReproduction> findReproductionsChromista()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CatalogReproduction o where o.crepStatus = true and o.crepChromista=true");
			List<CatalogReproduction> result=(List<CatalogReproduction>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CatalogReproduction>();
		}
		return new ArrayList<CatalogReproduction>();
	}
	
	
	/**
	 * Recuperar lista de reproduccion reino chromista
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CatalogReproduction> findReproductionsViruses()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CatalogReproduction o where o.crepStatus = true and o.crepViruses=true");
			List<CatalogReproduction> result=(List<CatalogReproduction>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CatalogReproduction>();
		}
		return new ArrayList<CatalogReproduction>();
	}
}
