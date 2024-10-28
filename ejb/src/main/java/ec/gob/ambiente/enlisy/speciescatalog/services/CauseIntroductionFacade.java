package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.speciescatalog.model.CauseIntroduction;



/**
 * Servicios para la administracion de causas de introduccion
 * @author EXCO
 *
 */
@Stateless
public class CauseIntroductionFacade extends AbstractFacade<CauseIntroduction, Integer>{

	public CauseIntroductionFacade() {
		super(CauseIntroduction.class, Integer.class);		
	}
	
		
	/**
	 * Recuperar lista de causas reino animal
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CauseIntroduction> findCausesAnimal()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CauseIntroduction o where o.cainStatus = true and o.cainAnimal=true");
			List<CauseIntroduction> result=(List<CauseIntroduction>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CauseIntroduction>();
		}
		return new ArrayList<CauseIntroduction>();
	}
	
	/**
	 * Recuperar lista de causas reino plantae
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CauseIntroduction> findCausesPlantae()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CauseIntroduction o where o.cainStatus = true and o.cainPlantae=true");
			List<CauseIntroduction> result=(List<CauseIntroduction>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CauseIntroduction>();
		}
		return new ArrayList<CauseIntroduction>();
	}
	
	/**
	 * Recuperar lista de causas reino fungi
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CauseIntroduction> findCausesFungi()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CauseIntroduction o where o.cainStatus = true and o.cainFungi=true");
			List<CauseIntroduction> result=(List<CauseIntroduction>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CauseIntroduction>();
		}
		return new ArrayList<CauseIntroduction>();
	}
	
	/**
	 * Recuperar lista de causas reino eubacteria
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CauseIntroduction> findCausesEubacteria()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CauseIntroduction o where o.cainStatus = true and o.cainEubacteria=true");
			List<CauseIntroduction> result=(List<CauseIntroduction>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CauseIntroduction>();
		}
		return new ArrayList<CauseIntroduction>();
	}
	
	/**
	 * Recuperar lista de formas reino arqueobacteria
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CauseIntroduction> findCausesArqueobacteria()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CauseIntroduction o where o.cainStatus = true and o.cainArcheobacteria=true");
			List<CauseIntroduction> result=(List<CauseIntroduction>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CauseIntroduction>();
		}
		return new ArrayList<CauseIntroduction>();
	}
	
	/**
	 * Recuperar lista de causas reino protista
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CauseIntroduction> findCausesProtista()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CauseIntroduction o where o.cainStatus = true and o.cainProtista=true");
			List<CauseIntroduction> result=(List<CauseIntroduction>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CauseIntroduction>();
		}
		return new ArrayList<CauseIntroduction>();
	}
	
	/**
	 * Recuperar lista de causas reino chromista
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CauseIntroduction> findCausesChromista()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CauseIntroduction o where o.cainStatus = true and o.cainChromista=true");
			List<CauseIntroduction> result=(List<CauseIntroduction>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CauseIntroduction>();
		}
		return new ArrayList<CauseIntroduction>();
	}
	
	/**
	 * Recuperar lista de causas reino viruses
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<CauseIntroduction> findCausesViruses()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM CauseIntroduction o where o.cainStatus = true and o.cainViruses=true");
			List<CauseIntroduction> result=(List<CauseIntroduction>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<CauseIntroduction>();
		}
		return new ArrayList<CauseIntroduction>();
	}
}
