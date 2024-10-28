package ec.gob.ambiente.enlisy.geneticresources.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;


import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.geneticresources.model.ProposedCollection;
import ec.gob.ambiente.enlisy.geneticresources.model.TechnicalEvaluationGalapago;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class TechnicalEvaluationGalapagosFacade extends AbstractFacade<TechnicalEvaluationGalapago, Integer> {
	
	public TechnicalEvaluationGalapagosFacade(){
		super(TechnicalEvaluationGalapago.class, Integer.class);
	}
	
	public TechnicalEvaluationGalapago findById(Integer id){
		try{
			
			Query query = getEntityManager().createQuery("SELECT t FROM TechnicalEvaluationGalapago t WHERE t.tegaId = :id AND t.tegaStatus = true");
			query.setParameter("id", id);
			
			return (TechnicalEvaluationGalapago) query.getSingleResult();
			
		}catch(NoResultException e)
		{
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<TechnicalEvaluationGalapago> findByProposedCollection(ProposedCollection proposedCollection)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT t FROM TechnicalEvaluationGalapago t where t.proposedCollection.prcoId= :id and t.tegaStatus = true order by t.tegaId desc ");
			query.setParameter("id", proposedCollection.getPrcoId());
			
			List<TechnicalEvaluationGalapago> result=(List<TechnicalEvaluationGalapago>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	public boolean save(TechnicalEvaluationGalapago technicalEvaluationGalapago, User user)
	{
		try
		{			
			if(technicalEvaluationGalapago.getTegaId() == null)
			{				
				technicalEvaluationGalapago.setTegaStatus(true);
				technicalEvaluationGalapago.setTegaDateCreate(new Date());
				technicalEvaluationGalapago.setTegaUserCreate(user.getUserName());
				create(technicalEvaluationGalapago);				
			}
			else
			{
				technicalEvaluationGalapago.setTegaDateUpdate(new Date());	
				technicalEvaluationGalapago.setTegaUserUpdate(user.getUserName());
				edit(technicalEvaluationGalapago);
			}
			
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public TechnicalEvaluationGalapago findSingleByProposedCollection(ProposedCollection proposedCollection)
	{
		try
		{
			TechnicalEvaluationGalapago obJesult = new TechnicalEvaluationGalapago();
			Query query = super.getEntityManager().createQuery("SELECT t FROM TechnicalEvaluationGalapago t where t.proposedCollection.prcoId= :id and t.tegaStatus = true order by t.tegaId desc ");
			query.setParameter("id", proposedCollection.getPrcoId());

			List<TechnicalEvaluationGalapago> result=(List<TechnicalEvaluationGalapago>)query.getResultList();
			if(result.size()>0)
				obJesult=result.get(0);;
			return obJesult;
		}catch(NoResultException e)
		{
			return null;
		}
	}
	
	public void delete(TechnicalEvaluationGalapago technicalEvaluation, User user){
		technicalEvaluation.setTegaStatus(false);
		technicalEvaluation.setTegaDateUpdate(new Date());
		technicalEvaluation.setTegaUserUpdate(user.getUserName());
		edit(technicalEvaluation);
	}	

}
