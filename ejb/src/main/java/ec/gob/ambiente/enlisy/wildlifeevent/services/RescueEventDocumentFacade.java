package ec.gob.ambiente.enlisy.wildlifeevent.services;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.chemistry.opencmis.client.api.Document;

import ec.gob.ambiente.alfresco.service.AlfrescoServiceBean;
import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.wildlifeevent.model.RescueEventDocument;
import ec.gob.ambiente.exceptions.CmisAlfrescoException;
import ec.gob.ambiente.util.Constant;

/**
 * Servicios para la administracion de los informes de inspeccion rescate
 * @author EXCO
 *
 */
@Stateless
public class RescueEventDocumentFacade extends AbstractFacade<RescueEventDocument, Integer>{

	public RescueEventDocumentFacade() {
		super(RescueEventDocument.class, Integer.class);		
	}
	
	 @EJB(lookup = Constant.ALFRESCO_SERVICE_BEAN)
	    private AlfrescoServiceBean alfrescoServiceBean; 
	
	public boolean saveAlfrescoBdd(RescueEventDocument document,String userName)
	{		
		try
		{			
			if(document.getContent()!=null&&(document.getRsdStatus()==null||document.getRsdStatus()))
			{
				String nombreCarpeta=document.getRsdIdProcess();				
				String folderId=guardarCarpeta(nombreCarpeta);
				String alfrescoId=guardarArchivo(document.getContent(),document.getRsdName(), folderId,nombreCarpeta);
				document.setRsdAlfrescoId(alfrescoId);
				String nombreDocumento=document.getRsdName().toLowerCase();
				nombreCarpeta=nombreCarpeta.toLowerCase();
				if(!nombreDocumento.contains(nombreCarpeta))
					nombreDocumento=nombreCarpeta+"-"+nombreDocumento;
				document.setRsdName(nombreDocumento);
			}
			
			if(document.getRsdId()==null)
			{
				document.setRsdUserCreate(userName);
				document.setRsdDateCreate(new Date());
				document.setRsdStatus(true);
				create(document);
				return true;
			}
			else
			{
				document.setRsdUserUpdate(userName);
				document.setRsdDateUpdate(new Date());
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

	/**
	 * Obtener el acta de rescate
	 * @param idRescueEvent
	 * @return
	 */
	public RescueEventDocument findActa(Integer idRescueEvent)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM RescueEventDocument o where o.rescueEvent.reseId= :codigo and o.rsdType= :tipo and o.rsdStatus = true");
			query.setParameter("codigo", idRescueEvent);
			query.setParameter("tipo", "ACTA");
			
			
			RescueEventDocument result=(RescueEventDocument)query.getSingleResult();
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
	public RescueEventDocument findInforme(Integer idRescueEvent)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM RescueEventDocument o where o.rescueEvent.reseId= :codigo and o.rsdType= :tipo and o.rsdStatus = true");
			query.setParameter("codigo", idRescueEvent);
			query.setParameter("tipo", "INFO");
			
			
			RescueEventDocument result=(RescueEventDocument)query.getSingleResult();
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
	
	public RescueEventDocument findFoto(Integer idRescueEvent)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM RescueEventDocument o where o.rescueEvent.reseId= :codigo and o.rsdType= :tipo and o.rsdStatus = true");
			query.setParameter("codigo", idRescueEvent);
			query.setParameter("tipo", "FOTO");
			
			
			RescueEventDocument result=(RescueEventDocument)query.getSingleResult();
			if(result!=null)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
}

