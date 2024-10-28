package ec.gob.ambiente.enlisy.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.PatentInformation;
import ec.gob.ambiente.enlisy.model.PatentTechnicalReportComments;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class PatentTechnicalReportCommentsFacade extends AbstractFacade<PatentTechnicalReportComments, Integer> implements Serializable {
	private static final long serialVersionUID = -4594424897085245484L;

	public PatentTechnicalReportCommentsFacade() {
		super(PatentTechnicalReportComments.class, Integer.class);
	}

	public PatentTechnicalReportComments findById(Integer id) {
		TypedQuery<PatentTechnicalReportComments> query = super.getEntityManager()
				.createQuery("select u from PatentTechnicalReportComments u where u.id = :id ", PatentTechnicalReportComments.class);
		query.setParameter("id", id);

		return query.getSingleResult();
	}

	public PatentTechnicalReportComments findByPatentInformation(PatentInformation patentInformation) {
//		TypedQuery<PatentTechnicalReportComments> query = super.getEntityManager()
//				.createQuery("select u from PatentTechnicalReportComments u where u.patentInformation.Id = :id ", PatentTechnicalReportComments.class);
//		query.setParameter("id", patentInformation.getId());
//		
//		return query.getSingleResult();
		return null;
	}

	public List<PatentTechnicalReportComments> listaObservaciones(PatentInformation patentInformation){
		TypedQuery<PatentTechnicalReportComments> query = super.getEntityManager().createQuery("select u from PatentTechnicalReportComments u where u.patentInformation.Id = :patentInformation and u.status = true order by 1 desc", PatentTechnicalReportComments.class);
		query.setParameter("patentInformation", patentInformation.getId());
		
		List<PatentTechnicalReportComments> listaObservaciones = new ArrayList<PatentTechnicalReportComments>();
		if (query.getResultList().size() > 0) {
			listaObservaciones = (List<PatentTechnicalReportComments>) query.getResultList();
		}
		return listaObservaciones;
		
	}
	
	
	public boolean updateUserData(PatentTechnicalReportComments observacionPlanDeManejo, User user) {

		
		try {
			if (observacionPlanDeManejo.getId() == null) {
				observacionPlanDeManejo.setStatus(true);;
				observacionPlanDeManejo.setFechaCreacion((new Date()));
				observacionPlanDeManejo.setPtrcDateUpdate((new Date()));
				observacionPlanDeManejo.setPtrcCreatorUser(user.getUserName());
				
				create(observacionPlanDeManejo);
			} else {
				observacionPlanDeManejo.setPtrcDateUpdate(new Date());
				observacionPlanDeManejo.setPtrcUserUpdate(user.getUserName());
				edit(observacionPlanDeManejo);
			}
			return true;
		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
	}


	public void delete(PatentTechnicalReportComments patenteFinalizada, User user) {
		patenteFinalizada.setStatus(false);
		patenteFinalizada.setPtrcDateUpdate(new Date());
		patenteFinalizada.setPtrcUserUpdate(user.getUserName());
		edit(patenteFinalizada);
	}
	
	

}
