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
import ec.gob.ambiente.enlisy.wildlifeevent.model.PeopleWildlifeEventsDocument;
import ec.gob.ambiente.exceptions.CmisAlfrescoException;
import ec.gob.ambiente.util.Constant;

@Stateless
public class PeopleWildlifeEventsDocumentFacade extends AbstractFacade<PeopleWildlifeEventsDocument, Integer> {

	@EJB(lookup = Constant.ALFRESCO_SERVICE_BEAN)
	private AlfrescoServiceBean alfrescoServiceBean;

	public PeopleWildlifeEventsDocumentFacade() {
		super(PeopleWildlifeEventsDocument.class, Integer.class);
	}

	public boolean saveAlfrescoBdd(PeopleWildlifeEventsDocument document, String userName) {
		try {
			if (document.getContent() != null && (document.getPwedStatus() == null || document.getPwedStatus())) {
				String nombreCarpeta = document.getPwedIdProcess();
				String folderId = guardarCarpeta(nombreCarpeta);
				String alfrescoId = guardarArchivo(document.getContent(), document.getPwedName(), folderId,
						nombreCarpeta);
				document.setPwedAlfrescoId(alfrescoId);
				String nombreDocumento = document.getPwedName().toLowerCase();
				nombreCarpeta = nombreCarpeta.toLowerCase();
				if (!nombreDocumento.contains(nombreCarpeta))
					nombreDocumento = nombreCarpeta + "-" + nombreDocumento;
				document.setPwedName(nombreDocumento);
			}

			if (document.getPwedId() == null) {
				document.setPwedUserCreate(userName);
				document.setPwedDateCreate(new Date());
				document.setPwedStatus(true);
				create(document);
				return true;
			} else {
				document.setPwedUserUpdate(userName);
				document.setPwedDateUpdate(new Date());
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
	public PeopleWildlifeEventsDocument findDocumentByProcessId(String processId, Integer documentType) {
		try {
			Query query = super
					.getEntityManager()
					.createQuery( " SELECT p FROM PeopleWildlifeEventsDocument p where  p.pwedIdProcess = :processId " +
							"and p.pwedTypeDocumentId = :documentType and p.pwedStatus = true");
			query.setParameter("processId", processId);
			query.setParameter("documentType", documentType);

			List<PeopleWildlifeEventsDocument> result= (List<PeopleWildlifeEventsDocument>) query.getResultList();
			if(!result.isEmpty())
			{
				for(PeopleWildlifeEventsDocument ret : result){
					ret.setContent(descargarDocumentoPorId(ret.getPwedAlfrescoId()));
				}
				return result.get(0);
			}
		}catch(NoResultException e)
		{
			return new PeopleWildlifeEventsDocument();
		}
		return new PeopleWildlifeEventsDocument();
	}
}