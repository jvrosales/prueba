package ec.gob.ambiente.enlisy.wildlifeevent.services;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.apache.chemistry.opencmis.client.api.Document;
import ec.gob.ambiente.alfresco.service.AlfrescoServiceBean;
import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.wildlifeevent.model.RunOverEventsDocument;
import ec.gob.ambiente.exceptions.CmisAlfrescoException;
import ec.gob.ambiente.util.Constant;

@Stateless
public class RunOverEventsDocumentsFacade extends AbstractFacade<RunOverEventsDocument, Integer> {

	@EJB(lookup = Constant.ALFRESCO_SERVICE_BEAN)
	private AlfrescoServiceBean alfrescoServiceBean;

	public RunOverEventsDocumentsFacade() {
		super(RunOverEventsDocument.class, Integer.class);
	}

	public boolean saveAlfrescoBdd(RunOverEventsDocument document, String userName) {
		try {
			if (document.getContent() != null && (document.getRoedStatus() == null || document.getRoedStatus())) {
				String nombreCarpeta = document.getRoedIdProcess();
				String folderId = guardarCarpeta(nombreCarpeta);
				String alfrescoId = guardarArchivo(document.getContent(), document.getRoedName(), folderId,
						nombreCarpeta);
				document.setRoedAlfrescoId(alfrescoId);
				String nombreDocumento = document.getRoedName().toLowerCase();
				nombreCarpeta = nombreCarpeta.toLowerCase();
				if (!nombreDocumento.contains(nombreCarpeta))
					nombreDocumento = nombreCarpeta + "-" + nombreDocumento;
				document.setRoedName(nombreDocumento);
			}

			if (document.getRoedId() == null) {
				document.setRoedUserCreate(userName);
				document.setRoedDateCreate(new Date());
				document.setRoedStatus(true);
				create(document);
				return true;
			} else {
				document.setRoedUserUpdate(userName);
				document.setRoedDateUpdate(new Date());
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
	
	@SuppressWarnings("unchecked")
	public RunOverEventsDocument findFileByTypeAndProcessId(Integer type, String processId) {
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT s FROM RunOverEventsDocument s where s.dotyId=:type and s.roedIdProcess=:processId and s.roedStatus = true");
			query.setParameter("type", type);
			query.setParameter("processId", processId);
			List<RunOverEventsDocument> result=(List<RunOverEventsDocument>)query.getResultList();
			if(!result.isEmpty()){
				for(RunOverEventsDocument ret : result){
					ret.setContent(descargarDocumentoPorId(ret.getRoedAlfrescoId()));
				}
				return result.get(0);
			}
		} catch(NoResultException e) {
			return new RunOverEventsDocument();
		}
		return new RunOverEventsDocument();
	}
}