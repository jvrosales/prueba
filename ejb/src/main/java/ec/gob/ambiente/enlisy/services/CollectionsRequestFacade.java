package ec.gob.ambiente.enlisy.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.CollectionsRequest;
import ec.gob.ambiente.enlisy.model.PatentRequest;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.suia.crud.service.CrudServiceBean;

@Stateless
public class CollectionsRequestFacade extends AbstractFacade<CollectionsRequest, Integer> implements Serializable {
	private static final long serialVersionUID = -4594424897085245484L;

	@EJB
	private CrudServiceBean crudServiceBean;

	public CollectionsRequestFacade() {
		super(CollectionsRequest.class, Integer.class);
	}

	public boolean createUserData(CollectionsRequest collectionsRequest, User user) {

		try {

			collectionsRequest.setCollStatus(true);
			collectionsRequest.setCollDateCreate(new Date());
			collectionsRequest.setCollUserCreate(user.getUserName());
			create(collectionsRequest);

			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean updateUserData(CollectionsRequest collectionsRequest, User user) {
		try {
			collectionsRequest.setCollDateUpdate(new Date());
			collectionsRequest.setCollUserUpdate(user.getUserName());
			edit(collectionsRequest);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public List<CollectionsRequest> findByPatentRequest(PatentRequest patentRequest) {
		TypedQuery<CollectionsRequest> query = super.getEntityManager().createQuery(
				"select u from CollectionsRequest u where u.patentRequest.id = :id ", CollectionsRequest.class);
		query.setParameter("id", patentRequest.getId());
		List<CollectionsRequest> collectionsRequest = new ArrayList<CollectionsRequest>();
		if (query.getResultList().size() > 0) {
			collectionsRequest = query.getResultList();
		}
		return collectionsRequest;
	}

	@SuppressWarnings("unchecked")
	public List<CollectionsRequest> findByWildlifeManagementCenter(PatentRequest patentRequest) {
		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM CollectionsRequest o where o.patentRequest.id= :id and o.collStatus = true");
			query.setParameter("id", patentRequest.getId());

			List<CollectionsRequest> result = (List<CollectionsRequest>) query.getResultList();
			if (result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<CollectionsRequest> findByWildlifeManagementCenterRenovacion(PatentRequest patentRequest,
			PatentRequest patentRequestAnterior) {
		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM CollectionsRequest o where o.patentRequest.id in (:id, :idAnterior) and o.collStatus = true and (o.collLoadingMethod = '2' or o.collLoadingMethod = '3') ");
			query.setParameter("id", patentRequest.getId());
			query.setParameter("idAnterior", patentRequestAnterior.getId());

			List<CollectionsRequest> result = (List<CollectionsRequest>) query.getResultList();
			if (result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<CollectionsRequest> findByWildlifeManagementCenterError(PatentRequest patentRequest) {
		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM CollectionsRequest o where o.patentRequest.id= :id and o.collStatus = true and o.collSpecieCode = null");
			query.setParameter("id", patentRequest.getId());

			List<CollectionsRequest> result = (List<CollectionsRequest>) query.getResultList();
			if (result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<CollectionsRequest> findByWildlifeManagementCenterErrorRenovacion(PatentRequest patentRequest,
			PatentRequest patentAnterior) {
		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM CollectionsRequest o where o.patentRequest.id in (:id, :idAnterior) and o.collStatus = true and o.collSpecieCode = null");
			query.setParameter("id", patentRequest.getId());
			query.setParameter("idAnterior", patentAnterior.getId());
			List<CollectionsRequest> result = (List<CollectionsRequest>) query.getResultList();
			if (result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<CollectionsRequest> findByListOneXOne(PatentRequest patentRequest) {
		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM CollectionsRequest o where o.patentRequest.id= :id and o.collStatus = true and o.collSpecieCode = null");
			query.setParameter("id", patentRequest.getId());

			List<CollectionsRequest> result = (List<CollectionsRequest>) query.getResultList();
			if (result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<CollectionsRequest> findByListOneXOneRenovacion(PatentRequest patentRequest,
			PatentRequest patentAnterior) {
		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM CollectionsRequest o where o.patentRequest.id in (:id, :idAnterior) and o.collStatus = true and o.collSpecieCode = null");
			query.setParameter("id", patentRequest.getId());
			query.setParameter("idAnterior", patentAnterior.getId());
			List<CollectionsRequest> result = (List<CollectionsRequest>) query.getResultList();
			if (result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<CollectionsRequest> findByforDelete(PatentRequest patentRequest) {
		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM CollectionsRequest o where o.patentRequest.id= :id and o.collStatus = true");
			query.setParameter("id", patentRequest.getId());

			List<CollectionsRequest> result = (List<CollectionsRequest>) query.getResultList();
			if (result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}

	public CollectionsRequest findSpecieforIn(PatentRequest patentRequest) {
		TypedQuery<CollectionsRequest> query = super.getEntityManager().createQuery(
				"SELECT o FROM CollectionsRequest o where o.patentRequest.id= :id and o.collStatus = true",
				CollectionsRequest.class);
		query.setParameter("id", patentRequest.getId());

		CollectionsRequest metodoIngreso = new CollectionsRequest();
		if (query.getResultList().size() > 0) {
			metodoIngreso = query.getResultList().get(0);
		}
		return metodoIngreso;
	}

	public boolean deleteFileExcel(PatentRequest patentRequest, int num) {
		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM CollectionsRequest o where o.patentRequest.id= :id and o.collStatus = true and o.collLoadingMethod = num");
			query.setParameter("id", patentRequest.getId());
			query.setParameter("num", num);

			if (query.getResultList().size() > 0) {
				return true;
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	public boolean deleteSpecieData(CollectionsRequest collectionsRequest, User user) {
		try {
			collectionsRequest.setCollUserUpdate(user.getUserName());
			collectionsRequest.setCollStatus(false);
			collectionsRequest.setCollDateUpdate(new Date());
			edit(collectionsRequest);

		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<CollectionsRequest> findLikeParent(Integer id) {
		try {
			Query query = super.getEntityManager().createQuery(
					"select o from CollectionsRequest o where o.patentRequest.id= :patentRequest and o.collStatus = true order by 1");
			query.setParameter("patentRequest", id);
			List<CollectionsRequest> catalogs = (List<CollectionsRequest>) query.getResultList();

			if (catalogs.size() > 0) {
				return catalogs;
			}
		} catch (NoResultException e) {
			e.printStackTrace();
		}

		return null;

	}

	@SuppressWarnings("unchecked")
	public List<CollectionsRequest> findLikeParentRenova(PatentRequest patentRequest, PatentRequest patentAnterior) {
		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM CollectionsRequest o where o.patentRequest.id in (:id, :idAnterior) and o.collStatus = true and (o.collLoadingMethod = '2' or o.collLoadingMethod = '3')");
			query.setParameter("id", patentRequest.getId());
			query.setParameter("idAnterior", patentAnterior.getId());
			List<CollectionsRequest> catalogs = (List<CollectionsRequest>) query.getResultList();

			if (catalogs.size() > 0) {
				return catalogs;
			}
		} catch (NoResultException e) {
			e.printStackTrace();
		}

		return null;

	}

	@SuppressWarnings("unchecked")
	public List<CollectionsRequest> findLikeNumberIdentifications() {
		try {
			Query query = super.getEntityManager()
					.createQuery("SELECT o FROM CollectionsRequest o where o.collStatus = true");

			List<CollectionsRequest> result = (List<CollectionsRequest>) query.getResultList();
			if (result.size() > 0)
				return result;

		} catch (NoResultException e) {
			e.printStackTrace();
		}

		return null;

	}

}
