package ec.gob.ambiente.enlisy.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.PatentRequest;
import ec.gob.ambiente.enlisy.model.PatentTechnicalReportV1;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.suia.crud.service.CrudServiceBean;

@Stateless
public class PatentTechnicalReportV1Facade extends AbstractFacade<PatentTechnicalReportV1, Integer>
		implements Serializable {
	private static final long serialVersionUID = -4594424897085245484L;

	public PatentTechnicalReportV1Facade() {
		super(PatentTechnicalReportV1.class, Integer.class);
	}

	@EJB
	private CrudServiceBean crudServiceBean;

	/**
	 * Actualizar datos de perfil de usuario cuando es usuario
	 *
	 * @param user
	 */

	public boolean createUserData(PatentTechnicalReportV1 patentTechnicalReport, User user) {
		try {
			if (patentTechnicalReport.getId() == null) {
				create(patentTechnicalReport);
			} else {
				edit(patentTechnicalReport);
			}
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public PatentTechnicalReportV1 findByPatentRequestId(Integer id) {

		TypedQuery<PatentTechnicalReportV1> query = super.getEntityManager().createQuery(
				"select u from PatentTechnicalReportV1 u " + "where u.patentRequest.id = :id ",
				PatentTechnicalReportV1.class);
		query.setParameter("id", id);

		PatentTechnicalReportV1 patentTechnicalReportV1 = new PatentTechnicalReportV1();
		if (query.getResultList().size() > 0) {
			patentTechnicalReportV1 = query.getResultList().get(0);
		}
		return patentTechnicalReportV1;

	}

	public List<PatentTechnicalReportV1> findByPatentRequest(PatentRequest patentRequest) {
		TypedQuery<PatentTechnicalReportV1> query = super.getEntityManager().createQuery(
				"select u from PatentTechnicalReportV1 u where u.patentRequest.id = :id ",
				PatentTechnicalReportV1.class);
		query.setParameter("id", patentRequest.getId());
		List<PatentTechnicalReportV1> collectionsRequest = new ArrayList<PatentTechnicalReportV1>();
		if (query.getResultList().size() > 0) {
			collectionsRequest = query.getResultList();
		}
		return collectionsRequest;
	}

}