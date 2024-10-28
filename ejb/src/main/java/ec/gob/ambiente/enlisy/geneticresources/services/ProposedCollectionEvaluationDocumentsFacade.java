package ec.gob.ambiente.enlisy.geneticresources.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.geneticresources.model.ProposedCollectionEvaluationDocuments;
import ec.gob.ambiente.enlisy.geneticresources.model.ProposedCollectionTechnicalEvaluation;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class ProposedCollectionEvaluationDocumentsFacade extends AbstractFacade<ProposedCollectionEvaluationDocuments, Integer>{

	public ProposedCollectionEvaluationDocumentsFacade() {
		super(ProposedCollectionEvaluationDocuments.class, Integer.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<ProposedCollectionEvaluationDocuments> findByTechnicalEvaluation(ProposedCollectionTechnicalEvaluation technicalEvaluation)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ProposedCollectionEvaluationDocuments o "
					+ "where o.proposedCollectionTechnicalEvaluation= :id and "
					+ "o.documentEvaluation.bigcStatus=true and o.pcdeStatus = true ");
			query.setParameter("id", technicalEvaluation);
			
			List<ProposedCollectionEvaluationDocuments> result=(List<ProposedCollectionEvaluationDocuments>)query.getResultList();
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
	public boolean save(ProposedCollectionEvaluationDocuments proposedCollectionEvaluationValues, User user)
	{
		try
		{	
			if(proposedCollectionEvaluationValues.getPcdeId() == null){
				proposedCollectionEvaluationValues.setPcdeStatus(true);
				proposedCollectionEvaluationValues.setPcdeDateCreate(new Date());
				proposedCollectionEvaluationValues.setPcdeUserCreate(user.getUserName());
				create(proposedCollectionEvaluationValues);
			}else{
				proposedCollectionEvaluationValues.setPcdeDateUpdate(new Date());
				proposedCollectionEvaluationValues.setPcdeUserUpdate(user.getUserName());
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