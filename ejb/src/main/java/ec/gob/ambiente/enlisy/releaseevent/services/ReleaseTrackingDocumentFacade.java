package ec.gob.ambiente.enlisy.releaseevent.services;

import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;
import org.apache.chemistry.opencmis.client.api.Document;
import ec.gob.ambiente.alfresco.service.AlfrescoServiceBean;
import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.releaseevent.model.ReleaseTrackingsDocument;
import ec.gob.ambiente.exceptions.CmisAlfrescoException;
import ec.gob.ambiente.util.Constant;

@Stateless
public class ReleaseTrackingDocumentFacade extends AbstractFacade<ReleaseTrackingsDocument, Integer>{

	@EJB(lookup = Constant.ALFRESCO_SERVICE_BEAN)
	private AlfrescoServiceBean alfrescoServiceBean;
	
	public ReleaseTrackingDocumentFacade() {
		super(ReleaseTrackingsDocument.class, Integer.class);
	}
	
	public boolean saveAlfrescoBdd(ReleaseTrackingsDocument document, String userName) {
		try {
			if (document.getContent() != null && (document.getRetdStatus() == null || document.getRetdStatus())) {
				String nombreCarpeta = document.getRetdIdProcess();
				String folderId = guardarCarpeta(nombreCarpeta);
				String alfrescoId = guardarArchivo(document.getContent(), document.getRetdName(), folderId,
						nombreCarpeta);
				document.setRetdAlfrescoId(alfrescoId);
				String nombreDocumento = document.getRetdName().toLowerCase();
				nombreCarpeta = nombreCarpeta.toLowerCase();
				if (!nombreDocumento.contains(nombreCarpeta))
					nombreDocumento = nombreCarpeta + "-" + nombreDocumento;
				document.setRetdName(nombreDocumento);
			}

			if (document.getRetdId() == null) {
				document.setRetdUserCreate(userName);
				document.setRetdDateCreate(new Date());
				document.setRetdStatus(true);
				create(document);
				return true;
			} else {
				document.setRetdUserUpdate(userName);
				document.setRetdDateUpdate(new Date());
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