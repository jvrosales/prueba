package ec.gob.ambiente.enlisy.geneticresources.services;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.geneticresources.model.GeneticResourcesCatalog;

@Stateless
public class GeneticResourcesCatalogFacade extends AbstractFacade<GeneticResourcesCatalog, Integer> implements Serializable{

	private static final long serialVersionUID = 8852726480903658111L;
	
	public GeneticResourcesCatalogFacade() {
		super(GeneticResourcesCatalog.class, Integer.class);		
	}
	
	public GeneticResourcesCatalog findById(Integer grcaId)
	{
		try {
			Query query = super.getEntityManager().createQuery("select o from GeneticResourcesCatalog o where o.grcaId = :grcaId and o.grcaStatus = true");
			query.setParameter("grcaId", grcaId);
			return (GeneticResourcesCatalog) query.getSingleResult();			
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public GeneticResourcesCatalog findByCode(String grcaCode)
	{
		try {
			Query query = super.getEntityManager().createQuery("select o from GeneticResourcesCatalog o where o.grcaCode = :grcaCode and o.grcaStatus = true");
			query.setParameter("grcaCode", grcaCode);
			
			if(query.getResultList().size() > 0)
			{
				GeneticResourcesCatalog catalog = (GeneticResourcesCatalog) query.getResultList().get(0);	
				return catalog;
			}
		} catch (NoResultException e) {
			e.printStackTrace();
		}
				
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<GeneticResourcesCatalog> findLikeCode(String grcaCode)
	{
		try {
			Query query = super.getEntityManager().createQuery("select o from GeneticResourcesCatalog o where o.grcaCode like :grcaCode and o.grcaStatus = true order by 1");
			query.setParameter("grcaCode", grcaCode);
			
			List<GeneticResourcesCatalog> catalogs = (List<GeneticResourcesCatalog>) query.getResultList();	
			
			if(catalogs.size() > 0)
			{					
				return catalogs;
			}
		}catch (NoResultException e) {
			e.printStackTrace();
		}
				
		return null;
		
	}
	
	
	public GeneticResourcesCatalog findByDescription(String grcaDescription, String grcaCode)
	{
		try {
			Query query = super.getEntityManager().createQuery("select o from GeneticResourcesCatalog o where o.grcaCode like :grcaCode and o.grcaDescription = :grcaDescription and o.grcaStatus = true");
			query.setParameter("grcaDescription", grcaDescription);
			query.setParameter("grcaCode", grcaCode);
			
			if(query.getResultList().size() > 0)
			{
				GeneticResourcesCatalog catalog = (GeneticResourcesCatalog) query.getResultList().get(0);	
				return catalog;
			}
		} catch (NoResultException e) {
			e.printStackTrace();
		}
				
		return null;
	}
}