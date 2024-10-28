package ec.gob.ambiente.enlisy.cetas.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.chemistry.opencmis.client.api.Document;

import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.PdfReader;

import ec.gob.ambiente.alfresco.service.AlfrescoServiceBean;
import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.cetas.model.PatentApplication;
import ec.gob.ambiente.enlisy.cetas.model.PatentDocuments;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.exceptions.CmisAlfrescoException;
import ec.gob.ambiente.util.Constant;

@Stateless
public class PatentDocumentsFacade extends AbstractFacade<PatentDocuments, Integer> {

    @EJB(lookup = Constant.ALFRESCO_SERVICE_BEAN)
    private AlfrescoServiceBean alfrescoServiceBean;   
    

	public PatentDocumentsFacade() {
		super(PatentDocuments.class, Integer.class);		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<PatentDocuments> findByPatentApplication(PatentApplication patent) {
		try {
			Query query = super
					.getEntityManager()
					.createQuery( " SELECT p FROM PatentDocuments p where  p.patentApplication.paapId = :patent and p.pado_status= true order by 1 desc");					
			query.setParameter("patent", patent.getPaapId());
			return (List<PatentDocuments>) query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}	
	
	@SuppressWarnings("unchecked")
	public PatentDocuments findByTypeByPatentApplication(PatentApplication patent, Integer type) {
		try {
			Query query = super
					.getEntityManager()
					.createQuery( " SELECT p FROM PatentDocuments p where  p.patentApplication.paapId = :patent and  p.doty_id= :type and  p.pado_status= true order by 1 desc", PatentDocuments.class);					
			query.setParameter("patent", patent.getPaapId());
			query.setParameter("type", type);
						
			List<PatentDocuments> result= (List<PatentDocuments>) query.getResultList();
			if(result.size()>0)
			{
				PatentDocuments ret=result.get(0);				
				ret.setContent(descargarDocumentoPorId(ret.getPado_alfresco_id()));
				return ret;
			}
				
		}catch(NoResultException e)
		{
			return null;
		}
		return null;			
	}
	
	
	@Deprecated
	public boolean save(PatentDocuments documents,User user)
	{		
		try
		{	
			if(documents.getPado_id()==null)
			{
				documents.setPado_user_create(user.getUserName());
				documents.setPado_date_create(new Date());
				create(documents);
				return true;
			}
			else
			{
				documents.setPado_user_update(user.getUserName());
				documents.setPado_date_update(new Date());
				edit(documents);
				return true;
			}		
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean saveAlfrescoBdd(PatentDocuments patentDocuments,String userName)
	{		
		try
		{			
			if(patentDocuments.getContent()!=null&&(patentDocuments.getPado_status()==null||patentDocuments.getPado_status()))
			{
				//String nombreCarpeta=patentDocuments.getPatentApplication().getPaapCode()!=null?patentDocuments.getPatentApplication().getPaapCode():patentDocuments.getPatentApplication().getWildlifeManagementCenter().getWmceCode();
				String nombreCarpeta=patentDocuments.getPatentApplication().getPaapCode();
				String folderId=guardarCarpeta(nombreCarpeta);
				String alfrescoId=guardarArchivo(patentDocuments.getContent(),patentDocuments.getPado_name(), folderId,nombreCarpeta);
				patentDocuments.setPado_alfresco_id(alfrescoId);
				String nombreDocumento=patentDocuments.getPado_name().toLowerCase();
				nombreCarpeta=nombreCarpeta.toLowerCase();
				if(!nombreDocumento.contains(nombreCarpeta))
					nombreDocumento=nombreCarpeta+"-"+nombreDocumento;
				patentDocuments.setPado_name(nombreDocumento);
			}
			
			if(patentDocuments.getPado_id()==null)
			{
				patentDocuments.setPado_user_create(userName);
				patentDocuments.setPado_date_create(new Date());
				patentDocuments.setPado_status(true);
				create(patentDocuments);
				return true;
			}
			else
			{
				patentDocuments.setPado_user_update(userName);
				patentDocuments.setPado_date_update(new Date());
				edit(patentDocuments);
				return true;
			}		
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
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
	
	public String buscarCarpeta(String nombreCarpeta)
	{		
		try {
			return alfrescoServiceBean.findFolder(Constant.getRootId(), nombreCarpeta).getId();
		} catch (CmisAlfrescoException e) {
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
	
	public byte[] descargarDocumentoPorId(String id){	      
	       try {
			return alfrescoServiceBean.downloadDocumentById(id);
		} catch (CmisAlfrescoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       return null;
	 }
	
	
	public String obtenerUrlAlfresco(String idAlfresco) {
		try {
			return alfrescoServiceBean.downloadDocumentByObjectId(idAlfresco);
		} catch (CmisAlfrescoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

    public boolean verificarFirmaAlfresco(String idDocAlfresco) {
        File file = null;
        try {
            file = alfrescoServiceBean.downloadDocumentByIdFile(idDocAlfresco);
            return verificarFirma(file.getAbsolutePath());
        } catch (CmisAlfrescoException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
	
	@SuppressWarnings("rawtypes")
	private boolean verificarFirma(String fileUrl) {
	        try {

	            PdfReader reader = new PdfReader(fileUrl);
	            AcroFields af = reader.getAcroFields();
	            ArrayList names = af.getSignatureNames();
	            return names.size() > 0;

	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	 
	
	
	
}