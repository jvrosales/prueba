package ec.gob.ambiente.enlisy.services;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.PatentFormComments;
import ec.gob.ambiente.enlisy.model.PatentInformation;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.suia.crud.service.CrudServiceBean;

@Stateless
public class PatentFormCommentsFacade extends AbstractFacade<PatentFormComments, Integer> implements Serializable {
	private static final long serialVersionUID = -4594424897085245484L;

	public PatentFormCommentsFacade() {
		super(PatentFormComments.class, Integer.class);
	}

	@EJB
	private CrudServiceBean crudServiceBean;

	/**
	 * Actualizar datos de perfil de usuario cuando es usuario
	 *
	 * @param user
	 */

	public boolean createUserData(PatentFormComments patentFormComments, User user) {

		try {
			if (patentFormComments.getId() == null) {
				patentFormComments.setStatus(true);
				patentFormComments.setDate(new Date());
				patentFormComments.setUser(user);
				create(patentFormComments);
			}else {
				edit(patentFormComments);
			}
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean deleteUserData(PatentFormComments patentFormComments, User user) {
		try {
			patentFormComments.setStatus(false);
			edit(patentFormComments);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<PatentFormComments> buscarPorTarea(PatentInformation patentInformation) {
		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT p FROM PatentFormComments p where p.idClass = :patentId and p.status = true order by 1 desc",
					PatentFormComments.class);
			query.setParameter("patentId", patentInformation.getId());
			List<PatentFormComments> result = (List<PatentFormComments>) query.getResultList();
		
			if (result.size() > 0) {
				return result;
			}

		} catch (NoResultException e) {
			return null;
		}
		return null;

	}
	@SuppressWarnings("unchecked")
	public List<PatentFormComments> buscarPorTarea(PatentInformation patentInformation, Integer observacion) {
		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT p FROM PatentFormComments p where p.idClass = :patentId and p.status = true and p.pafoObservationType = :observacion order by 1 desc",
					PatentFormComments.class);
			query.setParameter("patentId", patentInformation.getId());
			query.setParameter("observacion", observacion);
			List<PatentFormComments> result = (List<PatentFormComments>) query.getResultList();
		
			if (result.size() > 0) {
				return result;
			}

		} catch (NoResultException e) {
			return null;
		}
		return null;

	}

}