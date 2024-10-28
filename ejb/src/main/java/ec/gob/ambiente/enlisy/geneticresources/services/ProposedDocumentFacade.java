package ec.gob.ambiente.enlisy.geneticresources.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.chemistry.opencmis.client.api.Document;

import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.PdfReader;

import ec.gob.ambiente.alfresco.service.AlfrescoServiceBean;
import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.geneticresources.model.ExportAuthorization;
import ec.gob.ambiente.enlisy.geneticresources.model.ProposedCollection;
import ec.gob.ambiente.enlisy.geneticresources.model.ProposedDocument;
import ec.gob.ambiente.enlisy.geneticresources.model.ResearchApplication;
import ec.gob.ambiente.enlisy.model.Flujo;
import ec.gob.ambiente.enlisy.model.PatentInformation;
import ec.gob.ambiente.enlisy.model.TipoDocumento;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.exceptions.CmisAlfrescoException;
import ec.gob.ambiente.util.Constant;

@Stateless
public class ProposedDocumentFacade extends AbstractFacade<ProposedDocument, Integer> {

    @EJB(lookup = Constant.ALFRESCO_SERVICE_BEAN)
    private AlfrescoServiceBean alfrescoServiceBean;   
    

	public ProposedDocumentFacade() {
		super(ProposedDocument.class, Integer.class);		
	}
	
	@EJB
	private ProposedCollectionFacade proposedCollectionFacade;
	@EJB
	private ResearchApplicationFacade applicationFacade;
	@EJB
	private ExportAuthorizationFacade exportAuthorizationFacade;
	
	
	@SuppressWarnings("unchecked")
	public List<ProposedDocument> findByProposedCollection(ProposedCollection project) {
		try {
			Query query = super
					.getEntityManager()
					.createQuery( " SELECT p FROM ProposedDocument p where  p.proposedCollection.prcoId = :project and p.docu_status= true order by 1 desc");					
			query.setParameter("project", project.getPrcoId());
			return (List<ProposedDocument>) query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}	
	
	@SuppressWarnings("unchecked")
	public ProposedDocument findByTypeByProposedCollection(ProposedCollection project, Integer type) {
		try {
			Query query = super
					.getEntityManager()
					.createQuery( " SELECT p FROM ProposedDocument p where  p.proposedCollection.prcoId = :project and  p.doty_id= :type and  p.docu_status= true order by 1 desc", ProposedDocument.class);					
			query.setParameter("project", project.getPrcoId());
			query.setParameter("type", type);
						
			List<ProposedDocument> result= (List<ProposedDocument>) query.getResultList();
			if(result.size()>0)
			{
				ProposedDocument ret=result.get(0);				
				ret.setContent(descargarDocumentoPorId(ret.getDocu_alfresco_id()));
				return ret;
			}
				
		}catch(NoResultException e)
		{
			return null;
		}
		return null;			
	}
	
	public boolean save(ProposedDocument documents,User user)
	{		
		try
		{	
			if(documents.getDocu_id()==null)
			{
				documents.setDocu_creator_user(user.getUserName());
				documents.setDocu_creation_date(new Date());
				create(documents);
				return true;
			}
			else
			{
				documents.setDocu_user_update(user.getUserName());
				documents.setDocu_date_update(new Date());
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
	
	public boolean saveAlfrescoBdd(ProposedDocument proposedDocument,String userName)
	{		
		try
		{			
			if(proposedDocument.getContent()!=null&&(proposedDocument.getDocu_status()==null||proposedDocument.getDocu_status()))
			{
				String codigo = proposedDocument.getProposedCollection().getPrcoCode()!=null?proposedDocument.getProposedCollection().getPrcoCode():proposedDocument.getProposedCollection().getResearchApplication()!=null?proposedDocument.getProposedCollection().getResearchApplication().getReapCode():proposedDocument.getProposedCollection().getProposedCollection().getResearchApplication().getReapCode();
				String nombreCarpeta=codigo;
				String folderId=guardarCarpeta(nombreCarpeta);
				String alfrescoId=guardarArchivo(proposedDocument.getContent(),proposedDocument.getDocu_name(), folderId,nombreCarpeta);
				proposedDocument.setDocu_alfresco_id(alfrescoId);
				String nombreDocumento=proposedDocument.getDocu_name().toLowerCase();
				nombreCarpeta=nombreCarpeta.toLowerCase();
				if(!nombreDocumento.contains(nombreCarpeta))
					nombreDocumento=nombreCarpeta+"-"+nombreDocumento;
				proposedDocument.setDocu_name(nombreDocumento);
			}
			
			if(proposedDocument.getDocu_id()==null)
			{
				proposedDocument.setDocu_creator_user(userName);
				proposedDocument.setDocu_creation_date(new Date());
				proposedDocument.setDocu_status(true);
				create(proposedDocument);
				return true;
			}
			else
			{
				proposedDocument.setDocu_user_update(userName);
				proposedDocument.setDocu_date_update(new Date());
				edit(proposedDocument);
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
	 

	//control y seguimiento	 
	 @SuppressWarnings("unchecked")
	 public List<ProposedDocument> findImageByProposedCollectionAndType(ProposedCollection project, Integer type) {
		 try {
				Query query = super
						.getEntityManager()
						.createQuery( " SELECT p FROM ProposedDocument p where  p.proposedCollection.prcoId = :project and p.docu_status= true and p.doty_id = :type order by 1");					
				query.setParameter("project", project.getPrcoId());
				query.setParameter("type", type);
							
				List<ProposedDocument> result= (List<ProposedDocument>) query.getResultList();
				if(result.size()>0)
				{
					for(ProposedDocument ret : result){								
						ret.setContent(descargarDocumentoPorId(ret.getDocu_alfresco_id()));
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
	public ProposedDocument findByTypeByProposedByAuthorization(ProposedCollection project,
			ExportAuthorization authorization, Integer type) {
		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT p FROM ProposedDocument p where "
							+ "p.proposedCollection.prcoId=:project and p.doty_id=:type "
							+ "and p.exportAuthorization.exauId=:authorizationId "
							+ "and p.docu_status= true order by 1 desc", ProposedDocument.class);
			query.setParameter("project", project.getPrcoId());
			query.setParameter("authorizationId", authorization.getExauId());
			query.setParameter("type", type);

			List<ProposedDocument> result = (List<ProposedDocument>) query.getResultList();
			if (result.size() > 0) {
				ProposedDocument ret = result.get(0);
				ret.setContent(descargarDocumentoPorId(ret.getDocu_alfresco_id()));
				return ret;
			}

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}
	
	public boolean saveFileAlfrescoBdd(ProposedDocument proposedDocument, String folderName, String userName)
	{		
		try
		{			
			if (proposedDocument.getContent() != null && (proposedDocument.getDocu_status() == null || proposedDocument.getDocu_status())) {
				String nombreCarpeta = folderName;
				String folderId = guardarCarpeta(nombreCarpeta);
				String alfrescoId = guardarArchivo(
										proposedDocument.getContent(),
										proposedDocument.getDocu_name(), folderId,
										nombreCarpeta);
				proposedDocument.setDocu_alfresco_id(alfrescoId);
				String nombreDocumento = proposedDocument.getDocu_name().toLowerCase();
				nombreCarpeta = nombreCarpeta.toLowerCase();
				if (!nombreDocumento.contains(nombreCarpeta))
					nombreDocumento = nombreCarpeta + "-" + nombreDocumento;
				proposedDocument.setDocu_name(nombreDocumento);
			}
			
			if(proposedDocument.getDocu_id()==null)
			{
				proposedDocument.setDocu_creator_user(userName);
				proposedDocument.setDocu_creation_date(new Date());
				proposedDocument.setDocu_status(true);
				create(proposedDocument);
				return true;
			}
			else
			{
				proposedDocument.setDocu_user_update(userName);
				proposedDocument.setDocu_date_update(new Date());
				edit(proposedDocument);
				return true;
			}		
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public ProposedDocument saveAlfrescoBddDocumento(ProposedDocument proposedDocument,String userName)
	{		
		try
		{			
			if(proposedDocument.getContent()!=null&&(proposedDocument.getDocu_status()==null||proposedDocument.getDocu_status()))
			{
				String nombreCarpeta=proposedDocument.getProposedCollection().getPrcoCode()!=null?proposedDocument.getProposedCollection().getPrcoCode():proposedDocument.getProposedCollection().getResearchApplication().getReapCode();				
				String folderId=guardarCarpeta(nombreCarpeta);
				String alfrescoId=guardarArchivo(proposedDocument.getContent(),proposedDocument.getDocu_name(), folderId,nombreCarpeta);
				proposedDocument.setDocu_alfresco_id(alfrescoId);
				String nombreDocumento=proposedDocument.getDocu_name().toLowerCase();
				nombreCarpeta=nombreCarpeta.toLowerCase();
				if(!nombreDocumento.contains(nombreCarpeta))
					nombreDocumento=nombreCarpeta+"-"+nombreDocumento;
				proposedDocument.setDocu_name(nombreDocumento);
			}
			
			if(proposedDocument.getDocu_id()==null)
			{
				proposedDocument.setDocu_creator_user(userName);
				proposedDocument.setDocu_creation_date(new Date());
				proposedDocument.setDocu_status(true);
				create(proposedDocument);
				return proposedDocument;
			}
			else
			{
				proposedDocument.setDocu_user_update(userName);
				proposedDocument.setDocu_date_update(new Date());
				edit(proposedDocument);
				return proposedDocument;
			}		
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ProposedDocument> getDocumentosPorFlujoNombresUnicos(Flujo flujo, String tramite){
		List<ProposedDocument> documentos = recuperarDocumentosPorFlujo(flujo, tramite);
		Map<String, ProposedDocument> documentosFiltrados = new HashMap<String, ProposedDocument>();
		for(ProposedDocument doc : documentos){
			if(!documentosFiltrados.containsKey(doc.getDocu_name())){
				documentosFiltrados.put(doc.getDocu_name(), doc);
			}else{
				if(documentosFiltrados.get(doc.getDocu_name()).getDoty_id().equals(doc.getDoty_id())){
					Integer maxId = documentosFiltrados.get(doc.getDocu_name()).getDocu_id();
					if(doc.getDocu_id() > maxId){
						documentosFiltrados.put(doc.getDocu_name(), doc);
					}else{
						documentosFiltrados.put(doc.getDocu_name() + "_" + doc.getDoty_id(), doc);
					}
				}
			}			
		}
		
		List<ProposedDocument> documentosResult = new ArrayList<ProposedDocument>();
		for(String nombreDocumento : documentosFiltrados.keySet()){
			ProposedDocument doc = documentosFiltrados.get(nombreDocumento);
			if(doc != null && doc.getDocu_alfresco_id() != null && !doc.getDocu_alfresco_id().isEmpty()){
				documentosResult.add(doc);
			}
		}
		
		Collections.sort(documentosResult, new Comparator<ProposedDocument>() {
			@Override
			public int compare(ProposedDocument o1, ProposedDocument o2) {
				if (o1.getDocu_creation_date().before(o2.getDocu_creation_date()))
					return 1;
				if (o1.getDocu_creation_date().after(o2.getDocu_creation_date()))
					return -1;
				return 0;
			}
		});
		
		return documentosResult;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProposedDocument> recuperarDocumentosPorFlujo(Flujo flujo, String tramite){
		
		String prefijo = flujo.getPrefijoTablaDocumento();
		String queryString=null;
		
		if (String.valueOf(flujo.getIdProceso()).equals("Biodiversidad.AutorizacionRecoleccionEspeciesContratoMarco") ||
				String.valueOf(flujo.getIdProceso()).equals("Biodiversidad.AutorizacionRecoleccionEspeciesSinFinesComerciales")){
			ProposedCollection propuesta=proposedCollectionFacade.findByCode(tramite);
			if(String.valueOf(flujo.getIdProceso()).equals("Biodiversidad.AutorizacionRecoleccionEspeciesContratoMarco")){
				// si la propuesta es null verifico si se trata de una actualizacion
				if(propuesta == null){
					propuesta=proposedCollectionFacade.findByTramite(tramite);
				}
			}

			if (propuesta == null) {
				ResearchApplication solicitudInvestigacion = applicationFacade.findByCode(tramite);
				propuesta=new ProposedCollection();
				propuesta.setResearchApplication(solicitudInvestigacion);
			}
			
			queryString = "SELECT " + prefijo + "_id, " + prefijo + "_name, " + prefijo + "_creation_date, " + prefijo + "_alfresco_id, doty_id "
					+ "FROM  " + flujo.getTablaDocumento() + " d "
					+ "WHERE prco_id = "
					+ propuesta.getPrcoId() + " and " + prefijo + "_status = true and exau_id is null ";
			
		} else if(String.valueOf(flujo.getIdProceso()).equals("Biodiversidad.PatentesBiodiversidadMCM")){
			
			PatentInformation propuesta = proposedCollectionFacade.findPatentByCode(tramite);
			
			queryString = "SELECT " + prefijo + "_id, " + prefijo + "_name, " + prefijo + "_creation_date, " + prefijo + "_alfresco_id, doty_id "
					+ "FROM  " + flujo.getTablaDocumento() + " d "
					+ "WHERE pare_id = "
					+ propuesta.getPareId() + " and " + prefijo + "_status = true";
		}
		
		
		Query query = getEntityManager().createNativeQuery(queryString);
		
		List<Object> result = (List<Object>)query.getResultList();
		
		List<ProposedDocument> documentos = new ArrayList<ProposedDocument>();
		for(Object object : result){
			Object[] resDoc = (Object[]) object;
			
			TipoDocumento tipoDoc = new TipoDocumento();
			tipoDoc.setId((Integer) resDoc[4]);
			
			ProposedDocument documento = new ProposedDocument();
			documento.setDocu_id((Integer) resDoc[0]);
			documento.setDocu_name((String) resDoc[1]);
			documento.setDocu_creation_date((Date) resDoc[2]);
			documento.setDocu_alfresco_id((String) resDoc[3]);
			documento.setDoty_id(tipoDoc.getId());
			
			documentos.add(documento);
		}
		
		return documentos;		
	}	
}