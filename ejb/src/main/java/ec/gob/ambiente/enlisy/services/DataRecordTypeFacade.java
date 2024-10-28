package ec.gob.ambiente.enlisy.services;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.DataRecordType;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class DataRecordTypeFacade extends AbstractFacade<DataRecordType, Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataRecordTypeFacade() {
		super(DataRecordType.class, Integer.class);

	}

	public DataRecordType findById(Integer id) {
		TypedQuery<DataRecordType> query = super.getEntityManager()
				.createQuery("select u from PatentCost u " + "where u.id = :id ", DataRecordType.class);
		query.setParameter("id", id);

		return query.getSingleResult();
	}

	public boolean saveUpdate(DataRecordType dataRecordType, User user) {
		try {
			if (dataRecordType.getId() == null) {
				dataRecordType.setDrtyStatus(true);
				dataRecordType.setDrtyCreationDate(new Date());
				dataRecordType.setDrtyCreatorUser(user.getUserName());
				create(dataRecordType);
			} else {
				dataRecordType.setDrtyDateUpdate(new Date());
				dataRecordType.setDrtyUserUpdate(user.getUserName());
				edit(dataRecordType);
			}
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean changeStatus(DataRecordType dataRecordType, User user) {
		try {
			dataRecordType.setDrtyDateUpdate(new Date());
			dataRecordType.setDrtyUserUpdate(user.getUserName());
			dataRecordType.setDrtyValue(false);
			dataRecordType.setDrtyStatus(false);
			edit(dataRecordType);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public DataRecordType findByPatent(Integer id) {

		TypedQuery<DataRecordType> query = super.getEntityManager()
				.createQuery("select u from DataRecordType u where u.patentRequest.id = :id ", DataRecordType.class);
		query.setParameter("id", id);

		DataRecordType dataRecordType = new DataRecordType();
		if (query.getResultList().size() > 0) {
			dataRecordType = query.getResultList().get(0);
		}
		return dataRecordType;

	}

	public DataRecordType findByPatentAdd(Integer id, Integer select) {

		TypedQuery<DataRecordType> query = super.getEntityManager().createQuery(
				"select u from DataRecordType u where u.patentRequest.id = :id and u.catalogoPatentes.id = :select ",
				DataRecordType.class);
		query.setParameter("id", id);
		query.setParameter("select", select);

		DataRecordType dataRecordType = new DataRecordType();
		if (query.getResultList().size() > 0) {
			dataRecordType = query.getResultList().get(0);
		}
		return dataRecordType;

	}

	@SuppressWarnings("unchecked")
	public List<DataRecordType> findLikeParent(Integer id) {
		try {
			Query query = super.getEntityManager().createQuery(
					"select o from DataRecordType o where o.patentRequest.id= :patentRequest and o.drtyStatus = true ORDER BY o.catalogoPatentes ASC");
			query.setParameter("patentRequest", id);
			List<DataRecordType> catalogs = (List<DataRecordType>) query.getResultList();

			if (catalogs.size() > 0) {
				return catalogs;
			}
		} catch (NoResultException e) {
			e.printStackTrace();
		}

		return null;

	}

}
