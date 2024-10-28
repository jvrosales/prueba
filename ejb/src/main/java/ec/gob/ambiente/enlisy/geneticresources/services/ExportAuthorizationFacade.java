package ec.gob.ambiente.enlisy.geneticresources.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.geneticresources.model.ExportAuthorization;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class ExportAuthorizationFacade extends
		AbstractFacade<ExportAuthorization, Integer> {

	public ExportAuthorizationFacade() {
		super(ExportAuthorization.class, Integer.class);
	}
	
	/**
	 * Guardar Autorizacion de exportacion
	 * @param exportAuthorization
	 * @param user
	 */
	public boolean save(ExportAuthorization exportAuthorization, User user) {
		try {
			if (exportAuthorization.getExauId() == null) {

				exportAuthorization.setExauStatus(true);
				exportAuthorization.setExauIsHistorical(false);
				exportAuthorization.setExauDateCreate(new Date());
				exportAuthorization.setExauUserCreate(user.getUserName());
				create(exportAuthorization);

			} else {
				exportAuthorization.setExauDateUpdate(new Date());
				exportAuthorization.setExauUserUpdate(user.getUserName());
				edit(exportAuthorization);
			}

			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	/**
	 * Obtiene los datos de una autorizacion de exportacion buscados por numero de tramite de autorizacion
	 * @param exauApplicationCode Numero de tramite de autorización
	 * @return Application
	 */
	@SuppressWarnings("unchecked")
	public ExportAuthorization findByProcedureNumber(String exauApplicationCode) {
		try {
			Query query = super
					.getEntityManager()
					.createQuery(
							"SELECT e FROM ExportAuthorization e "
							+ "where e.exauApplicationCode= :code and e.exauStatus = true order by 1");
			query.setParameter("code", exauApplicationCode);

			List<ExportAuthorization> resultList = query.getResultList();

			if (resultList != null && !resultList.isEmpty())
				return resultList.get(0);

		} catch (NoResultException e) {
			return null;
		}

		return null;
	}
	
	/**
	 * Obtiene las autorizaciones de exportacion buscados por propuesta
	 * @param code Código de Trámite
	 * @return Application
	 */
	@SuppressWarnings("unchecked")
	public ExportAuthorization findLastAuthorizationByProposed(int idProposed) {
		try {
			Query query = super.getEntityManager().createQuery(
							"SELECT e FROM ExportAuthorization e "
							+ "where e.proposedCollection.prcoId = :code and e.exauStatus = true order by exauId desc");
			query.setParameter("code", idProposed);

			List<ExportAuthorization> resultList = query.getResultList();

			if (resultList != null && !resultList.isEmpty())
				return resultList.get(0);

		} catch (NoResultException e) {
			return null;
		}

		return null;
	}
	
	public Integer authorizationCode(String sequenceName) {
		Query q;
		q = getEntityManager()
				.createNativeQuery("select nextval('"+sequenceName.toLowerCase()+"');");
		java.math.BigInteger result= (java.math.BigInteger)q.getSingleResult();
		return Integer.valueOf(result.intValue());
	}

	public Integer procedureNumberGenerated(String sequenceName) {
		Query q;
		q = getEntityManager()
				.createNativeQuery("select nextval('"+sequenceName.toLowerCase()+"');");
		java.math.BigInteger result= (java.math.BigInteger)q.getSingleResult();
		return Integer.valueOf(result.intValue());
	}
	
	public void delete(ExportAuthorization exportAuthorization, User user){
		exportAuthorization.setExauStatus(false);
		exportAuthorization.setExauDateUpdate(new Date());
		exportAuthorization.setExauUserUpdate(user.getUserName());
		edit(exportAuthorization);
	}
	
	/**
	 * Obtiene las autorizaciones de exportacion editadas por numero de tramite de autorizacion
	 * @param exauApplicationCode Numero de tramite de autorización
	 * @return Application
	 */
	@SuppressWarnings("unchecked")
	public  List<ExportAuthorization> findEditedAuthByProcedureNumber(String exauApplicationCode) {
		try {
			Query query = super
					.getEntityManager()
					.createQuery(
							"SELECT e FROM ExportAuthorization e "
							+ "where e.exauApplicationCode= :code and e.exauStatus = false order by exauId asc");
			query.setParameter("code", exauApplicationCode);

			List<ExportAuthorization> resultList = (List<ExportAuthorization>) query.getResultList();

			if (resultList != null && !resultList.isEmpty())
				return resultList;

		} catch (NoResultException e) {
			return null;
		}

		return null;
	}

	/**
	 * Obtiene las autorizaciones de exportacion emitidas por usuario
	 * @param user
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ExportAuthorization> findFinalizedByUserCreate(String user) {
		try {
			Query query = super.getEntityManager().createQuery(
							"SELECT e FROM ExportAuthorization e "
							+ "where e.exauUserCreate = :user "
							+ "and e.exauStatusAuthorization != null "
							+ "and e.exauStatus = true "
							+ "order by exauApplicationCode desc");
			query.setParameter("user", user);

			List<ExportAuthorization> resultList = query.getResultList();

			if (resultList != null && !resultList.isEmpty())
				return resultList;

		} catch (NoResultException e) {
			return null;
		}

		return null;
	}
}
