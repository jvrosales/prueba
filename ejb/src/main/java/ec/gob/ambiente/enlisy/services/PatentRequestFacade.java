package ec.gob.ambiente.enlisy.services;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.PatentRequest;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class PatentRequestFacade extends AbstractFacade<PatentRequest, Integer> implements Serializable {
	private static final long serialVersionUID = -4594424897085245484L;

	public PatentRequestFacade() {
		super(PatentRequest.class, Integer.class);
	}

	public PatentRequest findById(Integer id) {
		TypedQuery<PatentRequest> query = super.getEntityManager()
				.createQuery("select u from PatentRequest u " + "where u.id = :id ", PatentRequest.class);
		query.setParameter("id", id);

		return query.getSingleResult();
	}

	public boolean updateUserData(PatentRequest patentRequest, User user) {

		try {
			if (patentRequest.getId() == null) {
				patentRequest.setPareStatus(true);
				patentRequest.setPareCreationDate((new Date()));
				patentRequest.setPareStatusDate((new Date()));
				patentRequest.setPareCreatorUser(user.getUserName());
				
				create(patentRequest);
			} else {
				patentRequest.setPareDateUpdate(new Date());
				patentRequest.setPareUserUpdate(user.getUserName());
				edit(patentRequest);
			}
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public PatentRequest findByPatent(Integer id) {

		TypedQuery<PatentRequest> query = super.getEntityManager().createQuery(
				"select u from PatentRequest u " + "where u.patentInformation.id = :id ", PatentRequest.class);
		query.setParameter("id", id);

		PatentRequest patentRequest = new PatentRequest();
		if (query.getResultList().size() > 0) {
			patentRequest = query.getResultList().get(0);
		}
		return patentRequest;

	}

	public void delete(PatentRequest patentRequest, User user) {
		patentRequest.setPareStatus(false);
		patentRequest.setPareDateUpdate(new Date());
		patentRequest.setPareUserUpdate(user.getUserName());
		edit(patentRequest);
	}

}
