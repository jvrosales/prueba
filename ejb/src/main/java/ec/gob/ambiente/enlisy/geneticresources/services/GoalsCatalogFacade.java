package ec.gob.ambiente.enlisy.geneticresources.services;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.geneticresources.model.GoalsCatalog;

@Stateless
public class GoalsCatalogFacade extends AbstractFacade<GoalsCatalog, Integer> implements Serializable{	

	/**
	 * 
	 */
	private static final long serialVersionUID = -3441996797605500696L;

	public GoalsCatalogFacade() {
		super(GoalsCatalog.class, Integer.class);		
	}
	
	public GoalsCatalog findById(Integer id)
	{
		try {
			Query query = super.getEntityManager().createQuery("select o from GoalsCatalog o where o.gocaId = :id and o.gocaStatus = true "
					+ "order by 1 asc");
			query.setParameter("id", id);
			
			return (GoalsCatalog)query.getSingleResult();
					
		} catch (NoResultException e) {
			return null;
		}
		
	}
	
	public GoalsCatalog findByCode(String code,boolean onlyEnableds)
	{
		try {
			String enableds=onlyEnableds?" and gocaEnable=true":"";
			Query query = super.getEntityManager().createQuery("select o from GoalsCatalog o where "
					+ "o.gocaCode = :code and o.gocaStatus = true "+enableds + " order by 1 asc");
			query.setParameter("code", code);			
			return (GoalsCatalog)query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		
		
	}
	
	@SuppressWarnings("unchecked")
	public List<GoalsCatalog> findByCodeParent(String code,boolean onlyEnableds)
	{
		try {
			String enableds=onlyEnableds?" and gocaEnable=true":"";
			String sql=code!=null?"select o from GoalsCatalog o where o.gocaCodeParent = '"+code+"' and "
					+ "o.gocaStatus = true":"select o from GoalsCatalog o where o.gocaCodeParent is null "
							+ "and o.gocaStatus = true";
						
			Query query = super.getEntityManager().createQuery(sql+enableds + " order by 1 asc");			
			List<GoalsCatalog> result=query.getResultList();
			if(result.size() > 0)					
				return result;
		} catch (NoResultException e) {
			return null;
		}		
		return null;
	}
	
	
	
	

	
}