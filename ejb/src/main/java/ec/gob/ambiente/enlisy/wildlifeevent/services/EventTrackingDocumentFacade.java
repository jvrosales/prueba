package ec.gob.ambiente.enlisy.wildlifeevent.services;

import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;
import org.apache.chemistry.opencmis.client.api.Document;
import ec.gob.ambiente.alfresco.service.AlfrescoServiceBean;
import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.wildlifeevent.model.EventTrackingDocument;
import ec.gob.ambiente.exceptions.CmisAlfrescoException;
import ec.gob.ambiente.util.Constant;

@Stateless
public class EventTrackingDocumentFacade extends AbstractFacade<EventTrackingDocument, Integer> {
	
	@EJB(lookup = Constant.ALFRESCO_SERVICE_BEAN)
	private AlfrescoServiceBean alfrescoServiceBean;
	
	public EventTrackingDocumentFacade() {
		super(EventTrackingDocument.class, Integer.class);
	}
	
	public boolean saveAlfrescoBdd(EventTrackingDocument document, String userName) {
		try {
			if (document.getContent() != null && (document.getEvtdStatus() == null || document.getEvtdStatus())) {
				String nombreCarpeta = document.getEvtdIdProcess();
				String folderId = guardarCarpeta(nombreCarpeta);
				String alfrescoId = guardarArchivo(document.getContent(), document.getEvtdName(), folderId,
						nombreCarpeta);
				document.setEvtdAlfrescoId(alfrescoId);
				String nombreDocumento = document.getEvtdName().toLowerCase();
				nombreCarpeta = nombreCarpeta.toLowerCase();
				if (!nombreDocumento.contains(nombreCarpeta))
					nombreDocumento = nombreCarpeta + "-" + nombreDocumento;
				document.setEvtdName(nombreDocumento);
			}

			if (document.getEvtdId() == null) {
				document.setEvtdUserCreate(userName);
				document.setEvtdDateCreate(new Date());
				document.setEvtdStatus(true);
				create(document);
				return true;
			} else {
				document.setEvtdUserUpdate(userName);
				document.setEvtdDateUpdate(new Date());
				edit(document);
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;	
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
			// TODO Auto-generated catch block
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
	 * Obtener id de tipo de documento
	 * 
	 * @param nameDocumentType
	 * @return
	 */
	public Integer typeDocumentCode(String nameDocumentType) {
		Query q;
		q = getEntityManager().createNativeQuery("SELECT doty_id FROM suia_iii.document_types where doty_name = ?1");
		q.setParameter(1, nameDocumentType);
		Integer result = (Integer) q.getSingleResult();
		return Integer.valueOf(result.intValue());
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
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}