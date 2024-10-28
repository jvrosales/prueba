package ec.gob.ambiente.enlisy.geneticresources.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.geneticresources.model.ProposedCollection;
import ec.gob.ambiente.enlisy.geneticresources.model.ProposedCollectionTechnicalEvaluation;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class ProposedCollectionTechnicalEvaluationFacade extends AbstractFacade<ProposedCollectionTechnicalEvaluation, Integer> {

	public ProposedCollectionTechnicalEvaluationFacade() {
		super(ProposedCollectionTechnicalEvaluation.class, Integer.class);		
	}
	
	/**
	 * Obtener ProposedCollectionTechnicalEvaluation por el id
	 * @param pcteId
	 * @return
	 */
	public ProposedCollectionTechnicalEvaluation findById(Integer pcteId) {

		try {
			Query query = getEntityManager().createQuery("SELECT p FROM ProposedCollectionTechnicalEvaluation p "
									+ "WHERE p.pcteId = :pcteId and p.pcteStatus = true");
			query.setParameter("pcteId", pcteId);

			return (ProposedCollectionTechnicalEvaluation) query.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		}
	}
	
	/**
	 * Se obtiene las evaluaciones por propuesta
	 * @param proposedCollection
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ProposedCollectionTechnicalEvaluation> findByProposedCollection(ProposedCollection proposedCollection){
		
		try {

			Query query = getEntityManager().createQuery("SELECT p FROM ProposedCollectionTechnicalEvaluation p "
					+ "WHERE p.pcteStatus = true and p.proposedCollection = :proposedCollection ORDER BY p.pcteId DESC");
			
			query.setParameter("proposedCollection", proposedCollection);

			List<ProposedCollectionTechnicalEvaluation> list = (List<ProposedCollectionTechnicalEvaluation>) query.getResultList();

			if (list.size() > 0)
				return list;
		} catch (NoResultException e) {
			return null;
		}
		return null;
	}
	
	/**
	 * Guardar
	 * @param proposedCollectionTechnicalEvaluation
	 * @param user
	 * @return
	 */
	public boolean save(ProposedCollectionTechnicalEvaluation proposedCollectionTechnicalEvaluation, User user){
		try{
			
			if(proposedCollectionTechnicalEvaluation.getPcteId() == null){
				proposedCollectionTechnicalEvaluation.setPcteStatus(true);
				proposedCollectionTechnicalEvaluation.setPcteUserCreate(user.getUserName());
				proposedCollectionTechnicalEvaluation.setPcteDateCreate(new Date());
				create(proposedCollectionTechnicalEvaluation);
				
			}else{
				proposedCollectionTechnicalEvaluation.setPcteUserUpdate(user.getUserName());
				proposedCollectionTechnicalEvaluation.setPcteDateUpdate(new Date());
				edit(proposedCollectionTechnicalEvaluation);
			}				
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	
}
