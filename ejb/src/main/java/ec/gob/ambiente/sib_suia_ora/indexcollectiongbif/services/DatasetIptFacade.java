package ec.gob.ambiente.sib_suia_ora.indexcollectiongbif.services;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.services.PatentRequestFacade;
import ec.gob.ambiente.enlisy.wildlifeevent.services.BiodiversityGeneralCatalogFacade;
import ec.gob.ambiente.sib_suia_ora.indexcollectiongbif.model.DatasetIpt;

/**
 * Para la gestion del modelo DatasetIpt
 * 
 * @author RolandoSoria
 *
 */
@Stateless
public class DatasetIptFacade extends AbstractFacade<DatasetIpt, Integer> {

	public DatasetIptFacade() {
		super(DatasetIpt.class, Integer.class);
	}

	@EJB
	private BiodiversityGeneralCatalogFacade bgCatalogFacade;

	@EJB
	private PatentRequestFacade patentRequestFacade;

	/**
	 * Guardar el DatasetIPT
	 * 
	 * @param datasetIPT
	 * @param user
	 * @param patentApplication
	 * @return
	 **/
	// @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public DatasetIpt save(DatasetIpt datasetIPT, User user) {
		DatasetIpt ds = null;
		try {

			if (datasetIPT.getDsiptId() == null) {
				datasetIPT.setDsiptStatus(true);
				datasetIPT.setDsiptDateCreate(new Date());
				datasetIPT.setDsiptUserCreate(user.getUserName());
				datasetIPT.setDsiptExecutionStatus(false);

				ds = create(datasetIPT);
			} else {
				datasetIPT.setDsiptDateUpdate(new Date());
				datasetIPT.setDsiptUserUpdate(user.getUserName());
				ds = edit(datasetIPT);
			}
			return ds;
		}
		catch(Exception e)
		{
			System.out.println("Error al guardar dataset " + e.getMessage());
			return ds;
		}
	}

	/**
	 * Metodo para actualizar el estado de ejecuciÃ³n del proceso de indexacion de
	 * todas o de una publicaciÃ³n especifica
	 * 
	 * @param doi
	 * @param status
	 * @param user
	 */
	public void updateUseDOI(String doi, Boolean status, User user) {

		try {

			Query q;
			EntityManager en = super.getEntityManager();
			q = en.createNativeQuery("UPDATE biodiversity_mcm.dataset_ipt SET dsipt_execution_status = ?1, "
					+ "dsipt_date_update=?2, dsipt_user_update=?3 WHERE dsipt_doi = ?4");
			q.setParameter(1, status);
			q.setParameter(2, new Date());
			q.setParameter(3, user.getUserName());
			q.setParameter(4, doi);
			q.executeUpdate();
		}

		catch (Exception e) {

		}
	}

	/**
	 * Buscar dataset por DOI
	 * 
	 * @param doi
	 * @return
	 */
	public DatasetIpt findByDOI(String doi) {
		try {
			Query query = super.getEntityManager()
					.createQuery("SELECT d FROM DatasetIpt d where d.dsiptDoi = :doi and d.dsiptStatus = true");
			query.setParameter("doi", doi);

			DatasetIpt result = (DatasetIpt) query.getSingleResult();
			if (result != null)
				return result;

		} catch (NoResultException e) {
			System.out.println("No se ha encontrado la entidad para el query findByDOI solicitado");
			return null;
		}
		return null;

	}

	/**
	 * Buscar dataset por Patente Id
	 * 
	 * @param paap_id
	 * @return
	 */
	public DatasetIpt findByPatent(Integer pare_id) {
		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM DatasetIpt o where o.patentRequest.id = :pare_id and o.dsiptStatus = true");
			query.setParameter("pare_id", pare_id);

			DatasetIpt result = (DatasetIpt) query.getSingleResult();
			if (result != null)
				return result;

		} catch (NoResultException e) {
			System.out.println("No se ha encontrado la entidad para el query findByPatent solicitado");
			return null;
		}
		return null;
	}

	public List<DatasetIpt> findByPatentReqId(Integer pare_id) {
		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM DatasetIpt o where o.patentRequest.id = :pare_id and o.dsiptStatus = true");
			query.setParameter("pare_id", pare_id);

			@SuppressWarnings("unchecked")
			List<DatasetIpt> result = (List<DatasetIpt>) query.getResultList();
			if (result.size() > 0) {
				return result;
			}
		} catch (NoResultException e) {
			return null;
		}
		return null;

	}

	/**
	 * Buscar dataset por estado de ejecucion
	 * 
	 * @return Boolean
	 */
	public Boolean findByExecStatus() {
		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM DatasetIpt o where o.dsiptExecutionStatus = true and o.dsiptStatus = true");

			DatasetIpt result = (DatasetIpt) query.getSingleResult();
			if (result != null)
				return true;

		} catch (NoResultException e) {
			System.out.println("No se ha encontrado la entidad para el query findByExecStatus solicitado");
			return false;
		}
		return false;
	}

}
