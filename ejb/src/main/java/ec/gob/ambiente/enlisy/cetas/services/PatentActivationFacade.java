package ec.gob.ambiente.enlisy.cetas.services;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.cetas.model.PatentActivation;
import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class PatentActivationFacade extends AbstractFacade<PatentActivation, Integer> implements Serializable{

	private static final long serialVersionUID = 8852726480903658111L;
	
	public PatentActivationFacade() {
		super(PatentActivation.class, Integer.class);		
	}
	
	public PatentActivation findById(Integer paacId)
	{
		try {
			Query query = super.getEntityManager().createQuery("select o from PatentActivation o where o.paacId = :paacId and o.paacStatus = true");
			query.setParameter("paacId", paacId);
			return (PatentActivation) query.getSingleResult();			
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	
	
	public PatentActivation findByProcedure(String paacProcedure)
	{
		try {
			Query query = super.getEntityManager().createQuery("select o from PatentActivation o where o.paacProcedure = :paacProcedure and o.paacStatus = true");
			query.setParameter("paacProcedure", paacProcedure);
			
			
			if(query.getResultList().size() > 0)
			{
				//PatentActivation activation = (PatentActivation) query.getResultList().get(0);
				PatentActivation activation = (PatentActivation) query.getSingleResult(); //codigo copiado de CollectionsZcrjbFacade
				return activation;
			}
		} catch (NoResultException e) {
			e.printStackTrace();
		}
				
		return null;
	}
	
	
	public PatentActivation findByProcedure(String paacProcedure, Integer paacPeriod)
	{
		try {
			Query query = super.getEntityManager().createQuery("select o from PatentActivation o where o.paacProcedure = :paacProcedure and o.paacPeriod = :paacPeriod and o.paacStatus = true");
			query.setParameter("paacProcedure", paacProcedure);
			query.setParameter("paacPeriod", paacPeriod);
			
			if(query.getResultList().size() > 0)
			{
				PatentActivation activation = (PatentActivation) query.getResultList().get(0);
				return activation;
			}
		} catch (NoResultException e) {
			e.printStackTrace();
		}
				
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<PatentActivation> findByProcedureAll(String paacProcedure)
	{
		try {
			Query query = super.getEntityManager().createQuery("select o from PatentActivation o where o.paacProcedure = :paacProcedure");
			query.setParameter("paacProcedure", paacProcedure);
			
			
			List<PatentActivation> result = (List<PatentActivation>) query.getResultList();
			if (result.size() > 0)
				return result;
			
			
		} catch (NoResultException e) {
			return null;
		}
				
		return null;
	}
	
	
	
	public PatentActivation findByDate(Date paacProcedureDate)
	{
		try {
			//Query query = super.getEntityManager().createQuery("select o from PatentActivation o where o.paacProcedure = :paacProcedure and o.paacStatus = true");
			//Query query = super.getEntityManager().createQuery("select o from PatentActivation o where :paacProcedureDate between '2018-10-01' and '2018-12-31'");
			Query query = super.getEntityManager().createQuery("select o from PatentActivation o where :paacProcedureDate between o.paacProcedureStartDate and o.paacProcedureEndDate and o.paacStatus = true");
			query.setParameter("paacProcedureDate", paacProcedureDate);
			
			
			if(query.getResultList().size() > 0)
			{
				PatentActivation activation = (PatentActivation) query.getResultList().get(0);
				//PatentActivation activation = (PatentActivation) query.getSingleResult(); //codigo copiado de CollectionsZcrjbFacade
				return activation;
			}
		} catch (NoResultException e) {
			e.printStackTrace();
		}
				
		return null;
	}
	
	
	
	/**
	 * Guardar Activacion de Patente
	 * @param patentActivation
	 * @param user
	 * @return
	 */
	public boolean save(PatentActivation patentActivation, User user)
	{
		try
		{			
			if(patentActivation.getPaacId() == null)
			{				
				patentActivation.setPaacStatus(true);
				patentActivation.setPaacDateCreate(new Date());
				patentActivation.setPaacUserCreate(user.getUserName());
				create(patentActivation);
			}
			else
			{
				patentActivation.setPaacDateUpdate(new Date());
				patentActivation.setPaacUserUpdate(user.getUserName());
				edit(patentActivation);
			}
			
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	public void delete(PatentActivation patentActivation, User user){
		patentActivation.setPaacStatus(false);
		patentActivation.setPaacDateUpdate(new Date());
		patentActivation.setPaacUserUpdate(user.getUserName());
		edit(patentActivation);
	}
	
	
}