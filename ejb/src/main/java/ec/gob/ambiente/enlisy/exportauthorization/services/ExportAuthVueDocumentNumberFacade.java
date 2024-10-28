package ec.gob.ambiente.enlisy.exportauthorization.services;

import ec.gob.ambiente.alfresco.service.AlfrescoServiceBean;
import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.exportauthorization.model.ExportAuthorizationVueDocumentNumber;
import ec.gob.ambiente.exceptions.CmisAlfrescoException;
import ec.gob.ambiente.util.Constant;
import org.apache.chemistry.opencmis.client.api.Document;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless
public class ExportAuthVueDocumentNumberFacade extends AbstractFacade<ExportAuthorizationVueDocumentNumber, Integer> {

    @SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

    @EJB(lookup = Constant.ALFRESCO_SERVICE_BEAN)
    private AlfrescoServiceBean alfrescoServiceBean;

    public ExportAuthVueDocumentNumberFacade(){
        super(ExportAuthorizationVueDocumentNumber.class, Integer.class);
    }

    public boolean saveAlfrescoBdd(ExportAuthorizationVueDocumentNumber document, String userName) {
        try {
            if (document.getContent() != null && (document.getEadnStatus() == null || document.getEadnStatus())) {
                String nombreCarpeta = document.getEadnIdProcess();
                String folderId = guardarCarpeta(nombreCarpeta);
                String alfrescoId = guardarArchivo(document.getContent(), document.getEadnDocumentName(), folderId,
                        nombreCarpeta);
                document.setEadnAlfrescoId(alfrescoId);
                String nombreDocumento = document.getEadnDocumentName().toLowerCase();
                nombreCarpeta = nombreCarpeta.toLowerCase();
                if (!nombreDocumento.contains(nombreCarpeta))
                    nombreDocumento = nombreCarpeta + "-" + nombreDocumento;
                document.setEadnDocumentName(nombreDocumento);
            }

            if (document.getEadnId() == null) {
                document.setEadnUserCreate(userName);
                document.setEadnDateCreate(new Date());
                document.setEadnStatus(true);
                create(document);
                return true;
            } else {
                document.setEadnUserUpdate(userName);
                document.setEadnDateUpdate(new Date());
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
    public List<ExportAuthorizationVueDocumentNumber> findDocumentByProcessId(String processId) {
        try {
            Query query = super
                    .getEntityManager()
                    .createQuery( " SELECT p FROM ExportAuthorizationVueDocumentNumber p where  p.eadnIdProcess = :processId and p.eadnStatus = true");
            query.setParameter("processId", processId);

            List<ExportAuthorizationVueDocumentNumber> result= (List<ExportAuthorizationVueDocumentNumber>) query.getResultList();
            if(result.size()>0)
            {
                for(ExportAuthorizationVueDocumentNumber ret : result){
                    ret.setContent(descargarDocumentoPorId(ret.getEadnAlfrescoId()));
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
	public List<ExportAuthorizationVueDocumentNumber> findUserByDocument(String documentNumber){
        try{
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM biodiversity.enabling_documents ");
            if (documentNumber!=null && !documentNumber.equals("")){
                sql.append("WHERE numero IN ('" + documentNumber + "') ");
            }
            Query query = super.getEntityManager().createNativeQuery(sql.toString());
            List<Object[]> registers = (List<Object[]>) query.getResultList();
            if (!registers.isEmpty()){
                List<ExportAuthorizationVueDocumentNumber> result = new ArrayList<>(registers.size());
                for(Object[] reg: registers){
                    ExportAuthorizationVueDocumentNumber doc = new ExportAuthorizationVueDocumentNumber();
                    doc.setUsername((String) reg[0]);
                    doc.setDocumentType((String) reg[1]);
                    doc.setDocumentNumber((String) reg[2]);
                    doc.setDocumentStatus((String) reg[3]);
                    doc.setValidDate((Date) reg[4]);
                    result.add(doc);
                }
                return result;
            }
        }catch (NoResultException e){
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }
}