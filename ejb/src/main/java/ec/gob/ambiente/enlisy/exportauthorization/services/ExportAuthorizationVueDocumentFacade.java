package ec.gob.ambiente.enlisy.exportauthorization.services;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.chemistry.opencmis.client.api.Document;

import ec.gob.ambiente.alfresco.service.AlfrescoServiceBean;
import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.exportauthorization.model.ExportAuthorizationVueDocument;
import ec.gob.ambiente.exceptions.CmisAlfrescoException;
import ec.gob.ambiente.util.Constant;

@Stateless
public class ExportAuthorizationVueDocumentFacade extends AbstractFacade<ExportAuthorizationVueDocument, Integer> {

	@EJB(lookup = Constant.ALFRESCO_SERVICE_BEAN)
	private AlfrescoServiceBean alfrescoServiceBean;

	public ExportAuthorizationVueDocumentFacade() {
		super(ExportAuthorizationVueDocument.class, Integer.class);
	}

	public boolean saveAlfrescoBdd(ExportAuthorizationVueDocument document, String userName) {
		try {
			if (document.getContent() != null && (document.getEavdStatus() == null || document.getEavdStatus())) {
				String nombreCarpeta = document.getEavdIdProcess();
				String folderId = guardarCarpeta(nombreCarpeta);
				String alfrescoId = guardarArchivo(document.getContent(), document.getEavdName(), folderId,
						nombreCarpeta);
				document.setEavdAlfrescoId(alfrescoId);
				String nombreDocumento = document.getEavdName().toLowerCase();
				nombreCarpeta = nombreCarpeta.toLowerCase();
				if (!nombreDocumento.contains(nombreCarpeta))
					nombreDocumento = nombreCarpeta + "-" + nombreDocumento;
				document.setEavdName(nombreDocumento);
			}

			if (document.getEavdId() == null) {
				document.setEavdUserCreate(userName);
				document.setEavdDateCreate(new Date());
				document.setEavdStatus(true);
				create(document);
				return true;
			} else {
				document.setEavdUserUpdate(userName);
				document.setEavdDateUpdate(new Date());
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

    @SuppressWarnings("unchecked")
    public List<ExportAuthorizationVueDocument> findDocumentByProcessId(String processId) {
        try {
            Query query = super
                    .getEntityManager()
                    .createQuery( " SELECT p FROM ExportAuthorizationVueDocument p where  p.eavdIdProcess = :processId and p.eavdStatus = true");
            query.setParameter("eavdIdProcess", processId);

            List<ExportAuthorizationVueDocument> result= (List<ExportAuthorizationVueDocument>) query.getResultList();
            if(result.size()>0)
            {
                for(ExportAuthorizationVueDocument ret : result){
                    ret.setContent(descargarDocumentoPorId(ret.getEavdAlfrescoId()));
                }
                return result;
            }

        }catch(NoResultException e)
        {
            return null;
        }
        return null;

    }

    @SuppressWarnings("unchecked")
    public ExportAuthorizationVueDocument findDocumentByProcessId(String processId, Integer documentType) {
        try {
            Query query = super
                    .getEntityManager()
                    .createQuery( " SELECT p FROM ExportAuthorizationVueDocument p where  p.eavdIdProcess = :processId " +
                            "and p.documentId = :documentType and p.eavdStatus = true");
            query.setParameter("processId", processId);
            query.setParameter("documentType", documentType);

            List<ExportAuthorizationVueDocument> result= (List<ExportAuthorizationVueDocument>) query.getResultList();
            if(!result.isEmpty())
            {
                for(ExportAuthorizationVueDocument ret : result){
                    ret.setContent(descargarDocumentoPorId(ret.getEavdAlfrescoId()));
                }
                return result.get(0);
            }

        }catch(NoResultException e)
        {
            return null;
        }
        return null;
    }
}

