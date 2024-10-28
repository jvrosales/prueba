package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.alfresco.connection.ConnectionCmisAlfrescoSUIA;
import ec.gob.ambiente.alfresco.service.AlfrescoServiceBean;
import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.speciescatalog.model.TaxaRank;
import ec.gob.ambiente.util.Constant;



/**
 * Servicios para la administracion de las publicaciones del especialista
 * @author EXCO
 *
 */
@Stateless
public class TaxaRankFacade extends AbstractFacade<TaxaRank, Integer>{

	public TaxaRankFacade() {
		super(TaxaRank.class, Integer.class);		
	}
	
	@Inject
	  ConnectionCmisAlfrescoSUIA cmisAlfrescoSUIA;
	
	 @EJB(lookup = Constant.ALFRESCO_SERVICE_BEAN)
	    private AlfrescoServiceBean alfrescoServiceBean; 
		
	/**
	 * Recuperar lista de rangos taxonomicos ordenados por nivel
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<TaxaRank> findTaxaRanks()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM TaxaRank o where o.taraStatus = true and o.lenguaje.talaId=1 order by o.taraLevel");
			List<TaxaRank> result=(List<TaxaRank>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<TaxaRank>();
		}
		return new ArrayList<TaxaRank>();
	}
	
	/**
	 * Recuperar lista de rangos de clasificacion superior
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<TaxaRank> findHigherTaxaRanks()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM TaxaRank o where o.taraStatus = true and o.lenguaje.talaId=1 and o.taraLevel<70 order by o.taraLevel");
			List<TaxaRank> result=(List<TaxaRank>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<TaxaRank>();
		} 
		return new ArrayList<TaxaRank>();
	}
	
	
	/**
	 * Recuperar lista de rangos segun el nivel
	 * en idioma Espaniol
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<TaxaRank> findRankForLevelAndSpanish(Integer level)
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM TaxaRank o where o.taraStatus = true and o.lenguaje.talaId=1 and o.taraLevel = :elNivel");
			query.setParameter("elNivel", level);
			List<TaxaRank> result=(List<TaxaRank>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<TaxaRank>();
		}
		return new ArrayList<TaxaRank>();
	}
	
	
	/**
	 * Recuperar lista de rangos taxonomicos ordenados por nivel
	 * excepto reino
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<TaxaRank> findTaxaRanksWithoutKingdom()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM TaxaRank o where o.taraStatus = true and o.lenguaje.talaId=1 and o.taraLevel != 10 order by o.taraLevel");
			List<TaxaRank> result=(List<TaxaRank>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<TaxaRank>();
		}
		return new ArrayList<TaxaRank>();
	}
	
}
