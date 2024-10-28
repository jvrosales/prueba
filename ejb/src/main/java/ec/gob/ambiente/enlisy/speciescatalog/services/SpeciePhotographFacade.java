package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.chemistry.opencmis.client.api.Document;

import ec.gob.ambiente.alfresco.service.AlfrescoServiceBean;
import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.speciescatalog.model.SpeciePhotograph;
import ec.gob.ambiente.exceptions.CmisAlfrescoException;
import ec.gob.ambiente.util.Constant;

/**
 * Servicios para la administracion de las fotografias de una especie
 * @author EXCO
 *
 */
@Stateless
public class SpeciePhotographFacade extends AbstractFacade<SpeciePhotograph, Integer>{

	public SpeciePhotographFacade() {
		super(SpeciePhotograph.class, Integer.class);		
	}
	
	 @EJB(lookup = Constant.ALFRESCO_SERVICE_BEAN)
	    private AlfrescoServiceBean alfrescoServiceBean;
	
	 /**
	public SpeciePhotograph saveAlfrescoBdd(SpeciePhotograph document,String userName)
	{		
		try
		{			
			if(document.getContent()!=null&&(document.getSpphStatus()==null||document.getSpphStatus()))
			{
				String nombreCarpeta=Constant.SIGLAS_INSTITUCION +"-"+document.getSpphGuiSpecie();				
				String folderId=guardarCarpeta(nombreCarpeta);
				String alfrescoId=guardarArchivo(document.getContent(),document.getSpphName(), folderId,nombreCarpeta);
				document.setSpphAlfrescoId(alfrescoId);
				String nombreDocumento=document.getSpphName().toLowerCase();
				nombreCarpeta=nombreCarpeta.toLowerCase();
				if(!nombreDocumento.contains(nombreCarpeta))
					nombreDocumento=nombreCarpeta+"-"+nombreDocumento;
				document.setSpphName(nombreDocumento);
			}
			
			if(document.getSpphId()==null)
			{
				document.setSpphUserCreate(userName);
				document.setSpphDateCreate(new Date());
				document.setSpphStatus(true);
				return create(document);
				
			}
			else
			{
				document.setSpphUserUpdate(userName);
				document.setSpphDateUpdate(new Date());
				return edit(document);
				
			}		
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	**/
	
	 public SpeciePhotograph saveAlfrescoBdd(SpeciePhotograph document,String userName)
		{		
			try
			{			
				String nombreCarpeta="MAAE-"+document.getSpphGuiSpecie();				
					String folderId=guardarCarpeta(nombreCarpeta);
					String alfrescoId=guardarArchivo(document.getContent(),document.getSpphName(), folderId,nombreCarpeta);
					document.setSpphAlfrescoId(alfrescoId);
					String nombreDocumento=document.getSpphName().toLowerCase();
					nombreCarpeta=nombreCarpeta.toLowerCase();
					if(!nombreDocumento.contains(nombreCarpeta))
						nombreDocumento=nombreCarpeta+"-"+nombreDocumento;
					document.setSpphName(nombreDocumento);
				
					document.setSpphUserCreate(userName);
					document.setSpphDateCreate(new Date());
					document.setSpphStatus(true);
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
	    	//List<String[]> obj = alfrescoServiceBean.getFolderContent("Manuales");   
			return alfrescoServiceBean.downloadDocumentById(id);
		} catch (CmisAlfrescoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       return null;
	 }
	
	/**
	 * Recuperar fotografia general de la especie
	 * @param idEspecie
	 * @return
	 */
	
	public SpeciePhotograph findGeneralPhotographByEspecie(Integer idEspecie)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM SpeciePhotograph o where o.specie.sptaId= :codigo and o.spphTypePhotograph = :tipo and o.spphStatus = true");
			query.setParameter("codigo", idEspecie);
			query.setParameter("tipo", "GENERAL");
			
			
			SpeciePhotograph result=(SpeciePhotograph)query.getSingleResult();
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
	public boolean saveBdd(SpeciePhotograph document,String userName)
	{		
		try
		{			
			if(document.getSpphId()==null)
			{
				document.setSpphUserCreate(userName);
				document.setSpphDateCreate(new Date());
				document.setSpphStatus(true);
				create(document);
				return true;
			}
			else
			{
				document.setSpphUserUpdate(userName);
				document.setSpphDateUpdate(new Date());
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
	 * Recuperar fotografias particulares de una especie
	 * @param idEspecie
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<SpeciePhotograph> findParticularPhotographByEspecie(Integer idEspecie)
	{
		List<SpeciePhotograph >result=new ArrayList<SpeciePhotograph>();
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM SpeciePhotograph o where o.specie.sptaId= :codigo and o.spphTypePhotograph = :tipo and o.spphStatus = true");
			query.setParameter("codigo", idEspecie);
			query.setParameter("tipo", "PARTICULAR");
			
			
			result=(List<SpeciePhotograph>)query.getResultList();
			if(result.size() > 0)
				return result;
			
		}catch(NoResultException e)
		{
			return result;
		}
		return result;
	}
	
	
	/**
	 * Recuperar fotografia de analisis saf volumen aprovechado
	 * @param idEspecie
	 * @return
	 */
	
	public SpeciePhotograph findVolumenAprovechadoByEspecie(Integer idEspecie)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM SpeciePhotograph o where o.specie.sptaId= :codigo and o.spphTypePhotograph = :tipo and o.spphStatus = true");
			query.setParameter("codigo", idEspecie);
			query.setParameter("tipo", "ANA_VOL_APR_SAF");
			
			
			SpeciePhotograph result=(SpeciePhotograph)query.getSingleResult();
			if(result!=null)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	
	/**
	 * Recuperar fotografia transversal rolliza uno
	 * @param idEspecie
	 * @return
	 */
	
	public SpeciePhotograph findTransversalRollizaUno(Integer idEspecie)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM SpeciePhotograph o where o.specie.sptaId= :codigo and o.spphTypePhotograph = :tipo and o.spphStatus = true");
			query.setParameter("codigo", idEspecie);
			query.setParameter("tipo", "ORG_TRANS_ROLL_UNO");
			
			
			SpeciePhotograph result=(SpeciePhotograph)query.getSingleResult();
			if(result!=null)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	/**
	 * Recuperar fotografia transversal rolliza dos
	 * @param idEspecie
	 * @return
	 */
	
	public SpeciePhotograph findTransversalRollizaDos(Integer idEspecie)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM SpeciePhotograph o where o.specie.sptaId= :codigo and o.spphTypePhotograph = :tipo and o.spphStatus = true");
			query.setParameter("codigo", idEspecie);
			query.setParameter("tipo", "ORG_TRANS_ROLL_DOS");
			
			
			SpeciePhotograph result=(SpeciePhotograph)query.getSingleResult();
			if(result!=null)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	/**
	 * Recuperar fotografia transversal rolliza tres
	 * @param idEspecie
	 * @return
	 */
	
	public SpeciePhotograph findTransversalRollizaTres(Integer idEspecie)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM SpeciePhotograph o where o.specie.sptaId= :codigo and o.spphTypePhotograph = :tipo and o.spphStatus = true");
			query.setParameter("codigo", idEspecie);
			query.setParameter("tipo", "ORG_TRANS_ROLL_TRES");
			
			
			SpeciePhotograph result=(SpeciePhotograph)query.getSingleResult();
			if(result!=null)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	/**
	 * Recuperar fotografia tangencial aserrada uno
	 * @param idEspecie
	 * @return
	 */
	
	public SpeciePhotograph findTangencialAserradaUno(Integer idEspecie)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM SpeciePhotograph o where o.specie.sptaId= :codigo and o.spphTypePhotograph = :tipo and o.spphStatus = true");
			query.setParameter("codigo", idEspecie);
			query.setParameter("tipo", "ORG_TANG_ASERR_UNO");
			
			
			SpeciePhotograph result=(SpeciePhotograph)query.getSingleResult();
			if(result!=null)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	/**
	 * Recuperar fotografia tangencial aserrada dos
	 * @param idEspecie
	 * @return
	 */
	
	public SpeciePhotograph findTangencialAserradaDos(Integer idEspecie)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM SpeciePhotograph o where o.specie.sptaId= :codigo and o.spphTypePhotograph = :tipo and o.spphStatus = true");
			query.setParameter("codigo", idEspecie);
			query.setParameter("tipo", "ORG_TANG_ASERR_DOS");
			
			
			SpeciePhotograph result=(SpeciePhotograph)query.getSingleResult();
			if(result!=null)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	
	/**
	 * Recuperar fotografia tangencial aserrada tres
	 * @param idEspecie
	 * @return
	 */
	
	public SpeciePhotograph findTangencialAserradaTres(Integer idEspecie)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM SpeciePhotograph o where o.specie.sptaId= :codigo and o.spphTypePhotograph = :tipo and o.spphStatus = true");
			query.setParameter("codigo", idEspecie);
			query.setParameter("tipo", "ORG_TANG_ASERR_TRES");
			
			
			SpeciePhotograph result=(SpeciePhotograph)query.getSingleResult();
			if(result!=null)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	/**
	 * Recuperar fotografia radial aserrada uno
	 * @param idEspecie
	 * @return
	 */
	
	public SpeciePhotograph findRadialAserradaUno(Integer idEspecie)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM SpeciePhotograph o where o.specie.sptaId= :codigo and o.spphTypePhotograph = :tipo and o.spphStatus = true");
			query.setParameter("codigo", idEspecie);
			query.setParameter("tipo", "ORG_RAD_ASERR_UNO");
			
			
			SpeciePhotograph result=(SpeciePhotograph)query.getSingleResult();
			if(result!=null)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	/**
	 * Recuperar fotografia radial aserrada dos
	 * @param idEspecie
	 * @return
	 */
	
	public SpeciePhotograph findRadialAserradaDos(Integer idEspecie)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM SpeciePhotograph o where o.specie.sptaId= :codigo and o.spphTypePhotograph = :tipo and o.spphStatus = true");
			query.setParameter("codigo", idEspecie);
			query.setParameter("tipo", "ORG_RAD_ASERR_DOS");
			
			
			SpeciePhotograph result=(SpeciePhotograph)query.getSingleResult();
			if(result!=null)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	
	/**
	 * Recuperar fotografia radial aserrada tres
	 * @param idEspecie
	 * @return
	 */
	
	public SpeciePhotograph findRadialAserradaTres(Integer idEspecie)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM SpeciePhotograph o where o.specie.sptaId= :codigo and o.spphTypePhotograph = :tipo and o.spphStatus = true");
			query.setParameter("codigo", idEspecie);
			query.setParameter("tipo", "ORG_RAD_ASERR_TRES");
			
			
			SpeciePhotograph result=(SpeciePhotograph)query.getSingleResult();
			if(result!=null)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	/**
	 * Recuperar fotografia anatomica transversal aserrada uno
	 * @param idEspecie
	 * @return
	 */
	
	public SpeciePhotograph findAnatTransAserradaUno(Integer idEspecie)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM SpeciePhotograph o where o.specie.sptaId= :codigo and o.spphTypePhotograph = :tipo and o.spphStatus = true");
			query.setParameter("codigo", idEspecie);
			query.setParameter("tipo", "ANAT_TRANS_ASERR_UNO");
			
			
			SpeciePhotograph result=(SpeciePhotograph)query.getSingleResult();
			if(result!=null)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	
	/**
	 * Recuperar fotografia anatomica transversal aserrada dos
	 * @param idEspecie
	 * @return
	 */
	
	public SpeciePhotograph findAnatTransAserradaDos(Integer idEspecie)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM SpeciePhotograph o where o.specie.sptaId= :codigo and o.spphTypePhotograph = :tipo and o.spphStatus = true");
			query.setParameter("codigo", idEspecie);
			query.setParameter("tipo", "ANAT_TRANS_ASERR_DOS");
			
			
			SpeciePhotograph result=(SpeciePhotograph)query.getSingleResult();
			if(result!=null)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	/**
	 * Recuperar fotografia anatomica transversal aserrada tres
	 * @param idEspecie
	 * @return
	 */
	
	public SpeciePhotograph findAnatTransAserradaTres(Integer idEspecie)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM SpeciePhotograph o where o.specie.sptaId= :codigo and o.spphTypePhotograph = :tipo and o.spphStatus = true");
			query.setParameter("codigo", idEspecie);
			query.setParameter("tipo", "ANAT_TRANS_ASERR_TRES");
			
			
			SpeciePhotograph result=(SpeciePhotograph)query.getSingleResult();
			if(result!=null)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	
	public SpeciePhotograph versionarAlfrescoBdd(SpeciePhotograph document,String userName)
	{		
		try
		{			
			if(document.getContent()!=null&&(document.getSpphStatus()==null||document.getSpphStatus()))
			{
				alfrescoServiceBean.replaceDocumentContent(document.getSpphAlfrescoId(), document.getContent(), document.getSpphExtension());
				String nombreCarpeta="MAAE-"+document.getSpphGuiSpecie();
				String nombreDocumento=document.getSpphName().toLowerCase();
				nombreCarpeta=nombreCarpeta.toLowerCase();
				if(!nombreDocumento.contains(nombreCarpeta))
					nombreDocumento=nombreCarpeta+"-"+nombreDocumento;
				document.setSpphName(nombreDocumento);
			}
			
				document.setSpphUserUpdate(userName);
				document.setSpphDateUpdate(new Date());
				return edit(document);
				
				
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public SpeciePhotograph versionarEliminarAlfresco(SpeciePhotograph document,String userName)
	{		
		try
		{			
			alfrescoServiceBean.replaceDocumentContent(document.getSpphAlfrescoId(), document.getContent(), document.getSpphExtension());
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	
	public SpeciePhotograph actualizarFotografia(SpeciePhotograph document,
			String userName) {
		try {
			document.setSpphUserUpdate(userName);
			document.setSpphDateUpdate(new Date());
			return edit(document);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
