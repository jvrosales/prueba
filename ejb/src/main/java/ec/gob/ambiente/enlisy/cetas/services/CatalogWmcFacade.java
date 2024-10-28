package ec.gob.ambiente.enlisy.cetas.services;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.cetas.model.CatalogWmc;

@Stateless
public class CatalogWmcFacade extends AbstractFacade<CatalogWmc, Integer> implements Serializable{

	private static final long serialVersionUID = 8852726480903658111L;
	
	public CatalogWmcFacade() {
		super(CatalogWmc.class, Integer.class);		
	}
	
	public CatalogWmc findById(Integer cwmcId)
	{
		try {
			Query query = super.getEntityManager().createQuery("select o from CatalogWmc o where o.cwmcId = :cwmcId and o.cwmcStatus = true");
			query.setParameter("cwmcId", cwmcId);
			return (CatalogWmc) query.getSingleResult();			
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public CatalogWmc findByCode(String cwmcCode)
	{
		try {
			Query query = super.getEntityManager().createQuery("select o from CatalogWmc o where o.cwmcCode = :cwmcCode and o.cwmcStatus = true");
			query.setParameter("cwmcCode", cwmcCode);
			
			if(query.getResultList().size() > 0)
			{
				CatalogWmc catalog = (CatalogWmc) query.getResultList().get(0);	
				return catalog;
			}
		} catch (NoResultException e) {
			e.printStackTrace();
		}
				
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CatalogWmc> findLikeCode(String cwmcCode)
	{
		try {
			Query query = super.getEntityManager().createQuery("select o from CatalogWmc o where o.cwmcCode like :cwmcCode and o.cwmcStatus = true order by 1");
			query.setParameter("cwmcCode", cwmcCode);
			
			List<CatalogWmc> catalogs = (List<CatalogWmc>) query.getResultList();	
			
			if(catalogs.size() > 0)
			{					
				return catalogs;
			}
		}catch (NoResultException e) {
			e.printStackTrace();
		}
				
		return null;
		
	}
	
	public CatalogWmc findByDescription(String cwmcDescription, String cwmcCode)
	{
		try {
			Query query = super.getEntityManager().createQuery("select o from CatalogWmc o where o.cwmcCode like :cwmcCode and o.cwmcDescription = :cwmcDescription and o.cwmcStatus = true");
			query.setParameter("cwmcDescription", cwmcDescription);
			query.setParameter("cwmcCode", cwmcCode);
			
			if(query.getResultList().size() > 0)
			{
				CatalogWmc catalog = (CatalogWmc) query.getResultList().get(0);	
				return catalog;
			}
		} catch (NoResultException e) {
			e.printStackTrace();
		}
				
		return null;
	}
	
	
}