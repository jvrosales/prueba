package ec.gob.ambiente.suia.iv.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.chemistry.opencmis.client.api.Document;
import org.jboss.logging.Logger;

import ec.gob.ambiente.alfresco.service.AlfrescoServiceBean;
import ec.gob.ambiente.exceptions.CmisAlfrescoException;
import ec.gob.ambiente.util.Constant;

/**
 * @author avfp
 *
 */
@Stateless
public class SuiaIvDocumentoFacade {

	Logger LOGGER = Logger.getLogger(SuiaIvDocumentoFacade.class);

	@EJB(lookup = Constant.ALFRESCO_SERVICE_BEAN)
	private AlfrescoServiceBean alfrescoServiceBean;

	public String guardarDocumentoAlfresco(String ruta, String nombreArchivo, byte[] fileByte, String nombreProceso,
			Integer idRegistro) {
		try {
			String rutaAlfresco = guardarCarpeta(ruta);
			Document document = alfrescoServiceBean.fileSaveStream(fileByte, nombreArchivo, rutaAlfresco, nombreProceso,
					nombreProceso, idRegistro);
			String objectId = document.getId();
			objectId = objectId.substring(0, objectId.lastIndexOf(";"));
			return objectId;
		} catch (CmisAlfrescoException e) {
			LOGGER.error(e);
			return null;
		}

	}

	private String guardarCarpeta(String directorio) {
		try {
			return alfrescoServiceBean.createFolderStructure(directorio, Constant.getRootId());
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return null;
	}

	public byte[] descargarDocumentoPorId(String identificadorRutaAlfresco) {
		try {
			return alfrescoServiceBean.downloadDocumentById(identificadorRutaAlfresco);
		} catch (CmisAlfrescoException e) {
			LOGGER.error(e);
		}
		return null;
	}
}
