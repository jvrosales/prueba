package ec.gob.ambiente.enlisy.geneticresources.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.geneticresources.model.ExportAuthorization;
import ec.gob.ambiente.enlisy.geneticresources.model.ExportAuthorizationImporter;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class ExportAuthorizationImporterFacade extends AbstractFacade<ExportAuthorizationImporter, Integer>{
	public ExportAuthorizationImporterFacade() {
		super(ExportAuthorizationImporter.class, Integer.class);		
	}
	
	/**
	 * Obtener importador de la autorizacion de exportacion
	 * @param authorization
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ExportAuthorizationImporter findImporterByAuthorization(ExportAuthorization authorization)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ExportAuthorizationImporter o "
					+ "where o.exportAuthorization.exauId= :id and o.exaiStatus = true "
					+ "order by  exaiId asc");
			query.setParameter("id", authorization.getExauId());
			
			List<ExportAuthorizationImporter> result=(List<ExportAuthorizationImporter>)query.getResultList();
			if (result != null && !result.isEmpty())
				return result.get(0);
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public ExportAuthorizationImporter findByPin(String peopPin) {

		try {
			Query query = super
					.getEntityManager()
					.createQuery(
							"select o from ExportAuthorizationImporter o where o.exaiIdentification = :peopPin order by o.exaiId desc");
			query.setParameter("peopPin", peopPin);
			List<ExportAuthorizationImporter> peopleList = (List<ExportAuthorizationImporter>) query.getResultList();
			if (!peopleList.isEmpty())
				return peopleList.get(0);

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}
	
	/**
	 * Obtiene los importadores editados por numero de tramite de autorizacion
	 * @param exauApplicationCode Numero de tramite de autorización
	 * @return Application
	 */
	@SuppressWarnings("unchecked")
	public  List<ExportAuthorizationImporter> findEditedAuthByProcedureNumber(String exauApplicationCode) {
		try {
			Query query = super
					.getEntityManager()
					.createQuery(
							"SELECT e FROM ExportAuthorizationImporter e "
							+ "where e.exportAuthorization.exauApplicationCode =:code and e.exaiStatus = false order by exaiId asc");
			query.setParameter("code", exauApplicationCode);

			List<ExportAuthorizationImporter> resultList = (List<ExportAuthorizationImporter>) query.getResultList();

			if (resultList != null && !resultList.isEmpty())
				return resultList;

		} catch (NoResultException e) {
			return null;
		}

		return null;
	}
	
	/**
	 * Guardar importador de la autorizacion
	 * @param exportAuthorizationImporter
	 * @param user
	 */
	public boolean save(ExportAuthorizationImporter exportAuthorizationImporter, User user) {
		try {
			if (exportAuthorizationImporter.getExaiId() == null) {

				exportAuthorizationImporter.setExaiStatus(true);
				exportAuthorizationImporter.setExaiIsHistorical(false);
				exportAuthorizationImporter.setExaiDateCreate(new Date());
				exportAuthorizationImporter.setExaiUserCreate(user.getUserName());
				create(exportAuthorizationImporter);

			} else {
				exportAuthorizationImporter.setExaiDateUpdate(new Date());
				exportAuthorizationImporter.setExaiUserUpdate(user.getUserName());
				edit(exportAuthorizationImporter);
			}

			return true;
		} catch (Exception ex) {
			return false;
		}
	}
}
