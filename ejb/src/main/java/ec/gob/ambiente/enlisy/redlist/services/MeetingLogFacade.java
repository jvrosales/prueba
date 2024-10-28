package ec.gob.ambiente.enlisy.redlist.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.chemistry.opencmis.client.api.Document;

import ec.gob.ambiente.alfresco.service.AlfrescoServiceBean;
import ec.gob.ambiente.enlisy.dao.AbstractFacadeModel;
import ec.gob.ambiente.enlisy.redlist.model.MeetingLog;
import ec.gob.ambiente.exceptions.CmisAlfrescoException;
import ec.gob.ambiente.util.Constant;

@Stateless
public class MeetingLogFacade extends AbstractFacadeModel<MeetingLog> {

	private static final long serialVersionUID = 1L;

	@EJB(lookup = Constant.ALFRESCO_SERVICE_BEAN)
	private AlfrescoServiceBean alfrescoServiceBean;

	public MeetingLogFacade() {
		super(MeetingLog.class);
	}

	@Override
	public void configure(String filter) {
		initialize();
	}

	@SuppressWarnings("unchecked")
	public List<MeetingLog> findByCallRedListId(Integer callRedListId) {
		try {
			Query query = super.getEntityManager().createQuery("SELECT M FROM MeetingLog M "
					+ "WHERE M.callRedList.careId=" + callRedListId + " ORDER BY M.meloDateCreate ");
			List<MeetingLog> result = (List<MeetingLog>) query.getResultList();
			if (result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return new ArrayList<MeetingLog>();
		}
		return new ArrayList<MeetingLog>();
	}

	public Integer maxSequenceThisYear(Integer callRedListId) {
		try {
			Calendar calendar = Calendar.getInstance();
			Date today = new Date();
			calendar.setTime(today);
			int year = calendar.get(Calendar.YEAR);
			Query query = super.getEntityManager()
					.createQuery("SELECT coalesce(max(M.meloSequence),0) from MeetingLog M where "
							+ "date_part('year', M.meloMeetingDate)=:year");
			query.setParameter("year", year);
			Integer result = (Integer) query.getSingleResult();
			if (result != null) {
				return result;
			}
		} catch (NoResultException e) {
			return 0;
		}
		return 0;
	}

	/**
	 * Guardar carpeta
	 * 
	 * @param nombreCarpeta
	 * @return
	 */

	public String guardarCarpeta(String nombreCarpeta) {
		try {
			return alfrescoServiceBean.createFolderStructure(nombreCarpeta, Constant.getRootId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String guardarArchivo(byte[] fileByte, String fileName, String folderIds, String folderName) {
		try {
			Document document = alfrescoServiceBean.fileSaveStream(fileByte, fileName, folderIds, folderName,
					folderName, 1);
			String objectId = document.getId();
			objectId = objectId.substring(0, objectId.lastIndexOf(";"));
			return objectId;
		} catch (CmisAlfrescoException e) {
			e.printStackTrace();

		}
		return null;
	}

	/**
	 * 
	 * @param idAlfresco
	 * @return
	 */
	public String obtenerUrlAlfresco(String idAlfresco) {
		try {
			return alfrescoServiceBean.downloadDocumentByObjectId(idAlfresco);
		} catch (CmisAlfrescoException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Recuperar bytes documento
	 * 
	 * @param id
	 * @return
	 */
	public byte[] descargarDocumentoPorId(String id) {
		try {
			return alfrescoServiceBean.downloadDocumentById(id);
		} catch (CmisAlfrescoException e) {
			e.printStackTrace();
		}
		return null;
	}
}
