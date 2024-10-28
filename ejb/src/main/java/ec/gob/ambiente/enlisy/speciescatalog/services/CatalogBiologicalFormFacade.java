package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.speciescatalog.model.CatalogBiologicalForm;



/**
 * Servicios para la administracion de catalogo de formas biologicas
 * @author EXCO
 *
 */
@Stateless
public class CatalogBiologicalFormFacade extends AbstractFacade<CatalogBiologicalForm, Integer>{

	public CatalogBiologicalFormFacade() {
		super(CatalogBiologicalForm.class, Integer.class);		
	}
	
		
	/**
	 * Recuperar lista de formas reino animal
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CatalogBiologicalForm> findFormsAnimal()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CatalogBiologicalForm o where o.cabfStatus = true and o.cabfAnimal=true");
			List<CatalogBiologicalForm> result=(List<CatalogBiologicalForm>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CatalogBiologicalForm>();
		}
		return new ArrayList<CatalogBiologicalForm>();
	}
	
	/**
	 * Recuperar lista de formas reino plantae
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CatalogBiologicalForm> findFormsPlantae()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CatalogBiologicalForm o where o.cabfStatus = true and o.cabfPlantae=true");
			List<CatalogBiologicalForm> result=(List<CatalogBiologicalForm>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CatalogBiologicalForm>();
		}
		return new ArrayList<CatalogBiologicalForm>();
	}
	
	/**
	 * Recuperar lista de formas reino fungi
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CatalogBiologicalForm> findFormsFungi()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CatalogBiologicalForm o where o.cabfStatus = true and o.cabfFungi=true");
			List<CatalogBiologicalForm> result=(List<CatalogBiologicalForm>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CatalogBiologicalForm>();
		}
		return new ArrayList<CatalogBiologicalForm>();
	}
	
	/**
	 * Recuperar lista de formas reino eubacteria
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CatalogBiologicalForm> findFormsEubacteria()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CatalogBiologicalForm o where o.cabfStatus = true and o.cabfEubacteria=true");
			List<CatalogBiologicalForm> result=(List<CatalogBiologicalForm>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CatalogBiologicalForm>();
		}
		return new ArrayList<CatalogBiologicalForm>();
	}
	
	/**
	 * Recuperar lista de formas reino arqueobacteria
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CatalogBiologicalForm> findFormsArqueobacteria()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CatalogBiologicalForm o where o.cabfStatus = true and o.cabfArcheobacteria=true");
			List<CatalogBiologicalForm> result=(List<CatalogBiologicalForm>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CatalogBiologicalForm>();
		}
		return new ArrayList<CatalogBiologicalForm>();
	}
	
	/**
	 * Recuperar lista de formas reino protista
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CatalogBiologicalForm> findFormsProtista()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CatalogBiologicalForm o where o.cabfStatus = true and o.cabfProtista=true");
			List<CatalogBiologicalForm> result=(List<CatalogBiologicalForm>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CatalogBiologicalForm>();
		}
		return new ArrayList<CatalogBiologicalForm>();
	}
	
	
	/**
	 * Recuperar lista de formas reino chromista
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CatalogBiologicalForm> findFormsChromista()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CatalogBiologicalForm o where o.cabfStatus = true and o.cabfChromista=true");
			List<CatalogBiologicalForm> result=(List<CatalogBiologicalForm>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CatalogBiologicalForm>();
		}
		return new ArrayList<CatalogBiologicalForm>();
	}
	
	
	/**
	 * Recuperar lista de formas reino viruses
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CatalogBiologicalForm> findFormsViruses()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CatalogBiologicalForm o where o.cabfStatus = true and o.cabfViruses=true");
			List<CatalogBiologicalForm> result=(List<CatalogBiologicalForm>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CatalogBiologicalForm>();
		}
		return new ArrayList<CatalogBiologicalForm>();
	}
}
