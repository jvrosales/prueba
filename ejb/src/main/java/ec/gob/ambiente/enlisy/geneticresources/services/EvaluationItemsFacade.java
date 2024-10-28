package ec.gob.ambiente.enlisy.geneticresources.services;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.geneticresources.model.EvaluationItems;

@Stateless
public class EvaluationItemsFacade extends AbstractFacade<EvaluationItems, Integer> implements Serializable{


	private static final long serialVersionUID = 7285212008557140347L;


	public EvaluationItemsFacade() {
		super(EvaluationItems.class, Integer.class);		
	}
	
	public EvaluationItems findById(Integer id)
	{
		try {
			Query query = super.getEntityManager().createQuery("select o from EvaluationItems o where o.evitId = :id and o.evitStatus = true");
			query.setParameter("id", id);
			
			return (EvaluationItems) query.getSingleResult();	
		} catch (NoResultException e) {
			return null;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<EvaluationItems> findByEvaluationCode(String evaluationCode)
	{
		try {
			Query query = super.getEntityManager().createQuery("select o from EvaluationItems o where o.evitEvaluationCode = :evaluationCode and o.evitStatus = true order by 1 asc");
			query.setParameter("evaluationCode", evaluationCode);
			List<EvaluationItems> result= query.getResultList();
			if(!result.isEmpty())
				return result;
		} catch (NoResultException e) {
			return null;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> findItemsByEvaluationCode(String evaluationCode)
	{
		try {
			Query query = super.getEntityManager().createQuery("select distinct(o.evitItem) from EvaluationItems o where o.evitEvaluationCode = :evaluationCode and o.evitStatus = true order by o.evitItem");
			query.setParameter("evaluationCode", evaluationCode);
			List<String> result= query.getResultList();
			if(!result.isEmpty())
				return result;
		} catch (NoResultException e) {
			return null;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> findSubitemsByEvaluationCode(String evaluationCode,String item)
	{
		try {
			Query query = super.getEntityManager().createQuery("select distinct(o.evitSubitem) from EvaluationItems o where o.evitEvaluationCode = :evaluationCode and o.evitItem = :item and o.evitStatus = true order by o.evitSubitem");
			query.setParameter("evaluationCode", evaluationCode);
			query.setParameter("item", item);
			List<String> result= query.getResultList();
			if(!result.isEmpty())
				return result;
		} catch (NoResultException e) {
			return null;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<EvaluationItems> findCriteriaByEvaluationCode(String evaluationCode,String item,String subitem)
	{
		try {
			System.out.println("subitem"+subitem);
			
			String subitemSQL;
			if(subitem!=null)
				subitemSQL="o.evitSubitem = :subitem";
			else
				subitemSQL="o.evitSubitem is null";
			
			Query query = super.getEntityManager().createQuery("select o from EvaluationItems o where o.evitEvaluationCode = :evaluationCode and o.evitItem = :item and "+subitemSQL+" and o.evitStatus = true order by o.evitCriteria");
			query.setParameter("evaluationCode", evaluationCode);
			query.setParameter("item", item);
			if(subitem!=null)
				query.setParameter("subitem", subitem);
			List<EvaluationItems> result= query.getResultList();
			if(!result.isEmpty())
				return result;
		} catch (NoResultException e) {
			return null;
		}
		return null;
	}
	
	
}