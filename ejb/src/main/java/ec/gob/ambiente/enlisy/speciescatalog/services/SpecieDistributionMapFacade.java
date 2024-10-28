package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.chemistry.opencmis.client.api.Document;

import ec.gob.ambiente.alfresco.service.AlfrescoServiceBean;
import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecieDistributionMap;
import ec.gob.ambiente.exceptions.CmisAlfrescoException;
import ec.gob.ambiente.util.Constant;

/**
 * Servicios para la administracion de las imagnes de distribucion de una especie
 * @author EXCO
 *
 */
@Stateless
public class SpecieDistributionMapFacade extends AbstractFacade<SpecieDistributionMap, Integer>{

	public SpecieDistributionMapFacade() {
		super(SpecieDistributionMap.class, Integer.class);		
	}
	
	 @EJB(lookup = Constant.ALFRESCO_SERVICE_BEAN)
	    private AlfrescoServiceBean alfrescoServiceBean; 
	
	 public SpecieDistributionMap saveAlfrescoBdd(SpecieDistributionMap document,String userName)
		{		
			try
			{			
				String nombreCarpeta="MAAE-"+document.getSpdmGuiSpecie();				
					String folderId=guardarCarpeta(nombreCarpeta);
					String alfrescoId=guardarArchivo(document.getContent(),document.getSpdmName(), folderId,nombreCarpeta);
					document.setSpdmAlfrescoId(alfrescoId);
					String nombreDocumento=document.getSpdmName().toLowerCase();
					nombreCarpeta=nombreCarpeta.toLowerCase();
					if(!nombreDocumento.contains(nombreCarpeta))
						nombreDocumento=nombreCarpeta+"-"+nombreDocumento;
					document.setSpdmName(nombreDocumento);
				
					document.setSpdmUserCreate(userName);
					document.setSpdmDateCreate(new Date());
					document.setSpdmStatus(true);
					return create(document);
					
				
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return null;
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
	 * Obtener imagen de distribucion del mapa de una especie
	 * @param idEspecie
	 * @return
	 */
	
	
	public SpecieDistributionMap findDistributionMapByEspecie(Integer idEspecie)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM SpecieDistributionMap o where o.specieSummary.specie.sptaId= :codigo  and o.spdmStatus = true and spdmTypePhotograph= :tipo");
			query.setParameter("codigo", idEspecie);
			query.setParameter("tipo", "DISTRIBUCIONUNO");
			
			
			SpecieDistributionMap result=(SpecieDistributionMap)query.getSingleResult();
			if(result!=null)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	/**
	 * Guardar documento en la base de datos
	 * @param document
	 * @param userName
	 * @return
	 */
	public boolean saveBdd(SpecieDistributionMap document,String userName)
	{		
		try
		{			
			if(document.getSpdmId()==null)
			{
				document.setSpdmUserCreate(userName);
				document.setSpdmDateCreate(new Date());
				document.setSpdmStatus(true);
				create(document);
				return true;
			}
			else
			{
				document.setSpdmUserUpdate(userName);
				document.setSpdmDateUpdate(new Date());
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
	 * Obtener imagen dos de distribucion del mapa de una especie
	 * @param idEspecie
	 * @return
	 */
	
	
	public SpecieDistributionMap findDistributionMapDosByEspecie(Integer idEspecie)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM SpecieDistributionMap o where o.specieSummary.specie.sptaId= :codigo  and o.spdmStatus = true and spdmTypePhotograph= :tipo");
			query.setParameter("codigo", idEspecie);
			query.setParameter("tipo", "DISTRIBUCIONDOS");
			
			
			SpecieDistributionMap result=(SpecieDistributionMap)query.getSingleResult();
			if(result!=null)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	/**
	 * Obtener imagen tres de distribucion del mapa de una especie
	 * @param idEspecie
	 * @return
	 */
	
	
	public SpecieDistributionMap findDistributionMapTresByEspecie(Integer idEspecie)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM SpecieDistributionMap o where o.specieSummary.specie.sptaId= :codigo  and o.spdmStatus = true and spdmTypePhotograph= :tipo");
			query.setParameter("codigo", idEspecie);
			query.setParameter("tipo", "DISTRIBUCIONTRES");
			
			
			SpecieDistributionMap result=(SpecieDistributionMap)query.getSingleResult();
			if(result!=null)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	
	public SpecieDistributionMap versionarAlfrescoBdd(SpecieDistributionMap document,String userName)
	{		
		try
		{			
			if(document.getContent()!=null&&(document.getSpdmStatus()==null||document.getSpdmStatus()))
			{
				alfrescoServiceBean.replaceDocumentContent(document.getSpdmAlfrescoId(), document.getContent(), document.getSpdmExtension());
				String nombreCarpeta="MAAE-"+document.getSpdmGuiSpecie();
				String nombreDocumento=document.getSpdmName().toLowerCase();
				nombreCarpeta=nombreCarpeta.toLowerCase();
				if(!nombreDocumento.contains(nombreCarpeta))
					nombreDocumento=nombreCarpeta+"-"+nombreDocumento;
				document.setSpdmName(nombreDocumento);
			}
			
				document.setSpdmUserUpdate(userName);
				document.setSpdmDateUpdate(new Date());
				return edit(document);
				
				
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public SpecieDistributionMap versionarEliminarAlfresco(SpecieDistributionMap document,String userName)
	{		
		try
		{			
			alfrescoServiceBean.replaceDocumentContent(document.getSpdmAlfrescoId(), document.getContent(), document.getSpdmExtension());
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	
	public SpecieDistributionMap actualizarFotografia(SpecieDistributionMap document,
			String userName) {
		try {
			document.setSpdmUserUpdate(userName);
			document.setSpdmDateUpdate(new Date());
			return edit(document);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
