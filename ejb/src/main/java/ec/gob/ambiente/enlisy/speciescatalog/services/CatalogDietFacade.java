package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.speciescatalog.model.CatalogDiet;



/**
 * Servicios para la administracion de catalogo de dietas 
 * @author EXCO
 *
 */
@Stateless
public class CatalogDietFacade extends AbstractFacade<CatalogDiet, Integer>{

	public CatalogDietFacade() {
		super(CatalogDiet.class, Integer.class);		
	}
	
		
	/**
	 * Recuperar lista de dietas reino animal
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CatalogDiet> findDietsAnimal()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CatalogDiet o where o.cdieStatus = true and o.cdieAnimal=true");
			List<CatalogDiet> result=(List<CatalogDiet>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CatalogDiet>();
		}
		return new ArrayList<CatalogDiet>();
	}
	
	/**
	 * Recuperar lista de dietas reino plantae
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CatalogDiet> findDietsPlantae()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CatalogDiet o where o.cdieStatus = true and o.cdiePlantae=true");
			List<CatalogDiet> result=(List<CatalogDiet>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CatalogDiet>();
		}
		return new ArrayList<CatalogDiet>();
	}
	
	/**
	 * Recuperar lista de dietas reino fungi
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CatalogDiet> findDietsFungi()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CatalogDiet o where o.cdieStatus = true and o.cdieFungi=true");
			List<CatalogDiet> result=(List<CatalogDiet>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CatalogDiet>();
		}
		return new ArrayList<CatalogDiet>();
	}
	
	/**
	 * Recuperar lista de dietas reino eubacteria
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CatalogDiet> findDietsEubacteria()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CatalogDiet o where o.cdieStatus = true and o.cdieEubacteria=true");
			List<CatalogDiet> result=(List<CatalogDiet>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CatalogDiet>();
		}
		return new ArrayList<CatalogDiet>();
	}
	
	/**
	 * Recuperar lista de dietas reino arqueobacteria
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CatalogDiet> findDietsArqueobacteria()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CatalogDiet o where o.cdieStatus = true and o.cdieArcheobacteria=true");
			List<CatalogDiet> result=(List<CatalogDiet>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CatalogDiet>();
		}
		return new ArrayList<CatalogDiet>();
	}
	
	/**
	 * Recuperar lista de formas reino protista
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CatalogDiet> findDietsProtista()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CatalogDiet o where o.cdieStatus = true and o.cdieProtista=true");
			List<CatalogDiet> result=(List<CatalogDiet>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CatalogDiet>();
		}
		return new ArrayList<CatalogDiet>();
	}
	
	/**
	 * Recuperar lista de formas reino chromista
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CatalogDiet> findDietsChromista()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CatalogDiet o where o.cdieStatus = true and o.cdieChromista=true");
			List<CatalogDiet> result=(List<CatalogDiet>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CatalogDiet>();
		}
		return new ArrayList<CatalogDiet>();
	}
	
	/**
	 * Recuperar lista de formas reino viruses
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CatalogDiet> findDietsViruses()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CatalogDiet o where o.cdieStatus = true and o.cdieViruses=true");
			List<CatalogDiet> result=(List<CatalogDiet>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CatalogDiet>();
		}
		return new ArrayList<CatalogDiet>();
	}
}
