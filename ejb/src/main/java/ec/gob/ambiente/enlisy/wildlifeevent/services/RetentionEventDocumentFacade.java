package ec.gob.ambiente.enlisy.wildlifeevent.services;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.chemistry.opencmis.client.api.Document;

import ec.gob.ambiente.alfresco.service.AlfrescoServiceBean;
import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.wildlifeevent.model.RetentionEventDocument;
import ec.gob.ambiente.exceptions.CmisAlfrescoException;
import ec.gob.ambiente.util.Constant;

/**
 * Servicios para la administracion de los informes de seguimiento plan de accion
 * @author EXCO
 *
 */
@Stateless
public class RetentionEventDocumentFacade extends AbstractFacade<RetentionEventDocument, Integer>{

	public RetentionEventDocumentFacade() {
		super(RetentionEventDocument.class, Integer.class);		
	}
	
	 @EJB(lookup = Constant.ALFRESCO_SERVICE_BEAN)
	    private AlfrescoServiceBean alfrescoServiceBean; 
	
	public boolean saveAlfrescoBdd(RetentionEventDocument document,String userName)
	{		
		try
		{			
			if(document.getContent()!=null&&(document.getRedStatus()==null||document.getRedStatus()))
			{
				String nombreCarpeta=document.getRedIdProcess();				
				String folderId=guardarCarpeta(nombreCarpeta);
				String alfrescoId=guardarArchivo(document.getContent(),document.getRedName(), folderId,nombreCarpeta);
				document.setRedAlfrescoId(alfrescoId);
				String nombreDocumento=document.getRedName().toLowerCase();
				nombreCarpeta=nombreCarpeta.toLowerCase();
				if(!nombreDocumento.contains(nombreCarpeta))
					nombreDocumento=nombreCarpeta+"-"+nombreDocumento;
				document.setRedName(nombreDocumento);
			}
			
			if(document.getRedId()==null)
			{
				document.setRedUserCreate(userName);
				document.setRedDateCreate(new Date());
				document.setRedStatus(true);
				create(document);
				return true;
			}
			else
			{
				document.setRedUserUpdate(userName);
				document.setRedDateUpdate(new Date());
				edit(document);
				return true;
			}		
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Guardar carpeta
	 * @param nombreCarpeta
	 * @return
	 */
	
	public String  guardarCarpeta(String nombreCarpeta)
	{		
		try {		 
			 return alfrescoServiceBean.createFolderStructure(nombreCarpeta, Constant.getRootId()); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return null;
	}
	
	public String guardarArchivo(byte[]fileByte, String fileName, String folderIds,String folderName) {    	
	   	 try {       
	          
	           Document document =alfrescoServiceBean.fileSaveStream(fileByte,fileName, folderIds, folderName,folderName, 1);
	           String objectId = document.getId();
	           objectId= objectId.substring(0, objectId.lastIndexOf(";"));           
	           return objectId;
	       } catch (CmisAlfrescoException e) {
	           e.printStackTrace();
	           
	       }  
	   	return null;
	   }
	
	/**
	 * Obtener id de tipo de documento
	 * @param nameDocumentType
	 * @return
	 */
	public Integer typeDocumentCode(String nameDocumentType) {
		Query q;
		q = getEntityManager()
				.createNativeQuery("SELECT doty_id FROM suia_iii.document_types where doty_name = ?1");
		q.setParameter(1, nameDocumentType);
		Integer result= (Integer)q.getSingleResult();
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
	 * @param id
	 * @return
	 */
	public byte[] descargarDocumentoPorId(String id){	      
	       try {
			return alfrescoServiceBean.downloadDocumentById(id);
		} catch (CmisAlfrescoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       return null;
	 }
	
	public RetentionEventDocument findActa(Integer idRetentionEvent)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM RetentionEventDocument o where o.retentionEvent.reteId= :codigo and o.redType= :tipo and o.redStatus = true");
			query.setParameter("codigo", idRetentionEvent);
			query.setParameter("tipo", "ACTARET");
			
			
			RetentionEventDocument result=(RetentionEventDocument)query.getSingleResult();
			if(result!=null)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	/**
	 * Obtener el informe de rescate
	 * @param idRescueEvent
	 * @return
	 */
	public RetentionEventDocument findActaAdm(Integer idRetentionEvent)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM RetentionEventDocument o where o.retentionEvent.reteId= :codigo and o.redType= :tipo and o.redStatus = true");
			query.setParameter("codigo", idRetentionEvent);
			query.setParameter("tipo", "ACTARETADM");
			
			
			RetentionEventDocument result=(RetentionEventDocument)query.getSingleResult();
			if(result!=null)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	/**
	 *Obtener el registro fotografico del rescate
	 * @param idRescueEvent
	 * @return
	 */
	
	public RetentionEventDocument findInfo(Integer idRetentionEvent)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM RetentionEventDocument o where o.retentionEvent.reteId= :codigo and o.redType= :tipo and o.redStatus = true");
			query.setParameter("codigo", idRetentionEvent);
			query.setParameter("tipo", "INFORET");
			
			
			RetentionEventDocument result=(RetentionEventDocument)query.getSingleResult();
			if(result!=null)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
}
