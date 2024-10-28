package ec.gob.ambiente.enlisy.geneticresources.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.geneticresources.model.ProposedCollection;
import ec.gob.ambiente.enlisy.geneticresources.model.ProposedCollectionEvaluationValues;
import ec.gob.ambiente.enlisy.geneticresources.model.ProposedCollectionTechnicalEvaluation;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class ProposedCollectionEvaluationValuesFacade extends AbstractFacade<ProposedCollectionEvaluationValues, Integer>{
		

	public ProposedCollectionEvaluationValuesFacade() {
		super(ProposedCollectionEvaluationValues.class, Integer.class);		
	}
		
	/**
	 * Obtener ProposedCollectionEvaluationValues por id
	 * @param id
	 * @return
	 */
	public ProposedCollectionEvaluationValues findById(Integer id)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM proposedCollectionEvaluationValues o where o.pcevId= :id and o.pcevStatus = true");
			query.setParameter("id", id);
			
			return (ProposedCollectionEvaluationValues) query.getSingleResult();
			
		}catch(NoResultException e)
		{
			return null;
		}
	}
	
	/**
	 * Obtener ProposedCollectionEvaluationValues por propuesta y tipo de evaluacion
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ProposedCollectionEvaluationValues> findByProposedCollection(ProposedCollection proposedCollection,String evaluationCode)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ProposedCollectionEvaluationValues o where o.proposedCollection.prcoId= :id and o.evaluationItems.evitEvaluationCode= :evaluationCode and o.evaluationItems.evitStatus=true and o.pcevStatus = true");
			query.setParameter("id", proposedCollection.getPrcoId());
			query.setParameter("evaluationCode", evaluationCode);
			
			List<ProposedCollectionEvaluationValues> result=(List<ProposedCollectionEvaluationValues>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProposedCollectionEvaluationValues> findByTechnicalEvaluation(ProposedCollectionTechnicalEvaluation technicalEvaluation,String evaluationCode)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ProposedCollectionEvaluationValues o "
					+ "where o.proposedCollectionTechnicalEvaluation= :id and o.evaluationItems.evitEvaluationCode= :evaluationCode and "
					+ "o.evaluationItems.evitStatus=true and o.pcevStatus = true");
			query.setParameter("id", technicalEvaluation);
			query.setParameter("evaluationCode", evaluationCode);
			
			List<ProposedCollectionEvaluationValues> result=(List<ProposedCollectionEvaluationValues>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	/**
	 * Guardar ProposedCollectionEvaluationValues
	 * @param proposedCollectionEvaluationValues
	 * @param user
	 * @return
	 */
	public boolean save(ProposedCollectionEvaluationValues proposedCollectionEvaluationValues, User user)
	{
		try
		{			
			if(proposedCollectionEvaluationValues.getPcevId() == null)
			{	
				
				proposedCollectionEvaluationValues.setPcevStatus(true);
				proposedCollectionEvaluationValues.setPcevDateCreate(new Date());
				proposedCollectionEvaluationValues.setPcevUserCreate(user.getUserName());
				create(proposedCollectionEvaluationValues);
				
			}
			else
			{
				proposedCollectionEvaluationValues.setPcevDateUpdate(new Date());	
				proposedCollectionEvaluationValues.setPcevUserUpdate(user.getUserName());
				edit(proposedCollectionEvaluationValues);
			}
			
			
			
			
			
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	

}