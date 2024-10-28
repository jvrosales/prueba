package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.speciescatalog.model.CatalogDispersion;



/**
 * Servicios para la administracion de catalogo de dispersion 
 * @author EXCO
 *
 */
@Stateless
public class CatalogDispersionFacade extends AbstractFacade<CatalogDispersion, Integer>{

	public CatalogDispersionFacade() {
		super(CatalogDispersion.class, Integer.class);		
	}
	
		
	/**
	 * Recuperar lista de dispersion reino animal
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CatalogDispersion> findDispersionsAnimal()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CatalogDispersion o where o.cadiStatus = true and o.cadiAnimal=true");
			List<CatalogDispersion> result=(List<CatalogDispersion>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CatalogDispersion>();
		}
		return new ArrayList<CatalogDispersion>();
	}
	
	/**
	 * Recuperar lista de dietas reino plantae
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CatalogDispersion> findDispersionPlantae()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CatalogDispersion o where o.cadiStatus = true and o.cadiPlantae=true");
			List<CatalogDispersion> result=(List<CatalogDispersion>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CatalogDispersion>();
		}
		return new ArrayList<CatalogDispersion>();
	}
	
	/**
	 * Recuperar lista de dispersion reino fungi
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CatalogDispersion> findDispersionsFungi()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CatalogDispersion o where o.cadiStatus = true and o.cadiFungi=true");
			List<CatalogDispersion> result=(List<CatalogDispersion>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CatalogDispersion>();
		}
		return new ArrayList<CatalogDispersion>();
	}
	
	/**
	 * Recuperar lista de dispersion reino eubacteria
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CatalogDispersion> findDispersionsEubacteria()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CatalogDispersion o where o.cadiStatus = true and o.cadiEubacteria=true");
			List<CatalogDispersion> result=(List<CatalogDispersion>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CatalogDispersion>();
		}
		return new ArrayList<CatalogDispersion>();
	}
	
	/**
	 * Recuperar lista de dispersion reino arqueobacteria
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CatalogDispersion> findDispersionsArqueobacteria()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CatalogDispersion o where o.cadiStatus = true and o.cadiArcheobacteria=true");
			List<CatalogDispersion> result=(List<CatalogDispersion>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CatalogDispersion>();
		}
		return new ArrayList<CatalogDispersion>();
	}
	
	/**
	 * Recuperar lista de formas reino protista
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CatalogDispersion> findDispersionProtista()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CatalogDispersion o where o.cadiStatus = true and o.cadiProtista=true");
			List<CatalogDispersion> result=(List<CatalogDispersion>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CatalogDispersion>();
		}
		return new ArrayList<CatalogDispersion>();
	}
	
	/**
	 * Recuperar lista de formas reino chromista
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CatalogDispersion> findDispersionChromista()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CatalogDispersion o where o.cadiStatus = true and o.cadiChromista=true");
			List<CatalogDispersion> result=(List<CatalogDispersion>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CatalogDispersion>();
		}
		return new ArrayList<CatalogDispersion>();
	}
	
	/**
	 * Recuperar lista de formas reino viruses
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CatalogDispersion> findDispersionViruses()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CatalogDispersion o where o.cadiStatus = true and o.cadiViruses=true");
			List<CatalogDispersion> result=(List<CatalogDispersion>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CatalogDispersion>();
		}
		return new ArrayList<CatalogDispersion>();
	}
}
