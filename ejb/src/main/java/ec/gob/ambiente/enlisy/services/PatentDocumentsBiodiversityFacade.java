package ec.gob.ambiente.enlisy.services;

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
import ec.gob.ambiente.enlisy.model.PatentDocumentsBiodiversity;
import ec.gob.ambiente.enlisy.model.PatentRequest;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.exceptions.CmisAlfrescoException;
import ec.gob.ambiente.util.Constant;

@Stateless
public class PatentDocumentsBiodiversityFacade extends AbstractFacade<PatentDocumentsBiodiversity, Integer> {

	@EJB(lookup = Constant.ALFRESCO_SERVICE_BEAN)
	private AlfrescoServiceBean alfrescoServiceBean;

	public PatentDocumentsBiodiversityFacade() {
		super(PatentDocumentsBiodiversity.class, Integer.class);
	}

	@SuppressWarnings("unchecked")
	public List<PatentDocumentsBiodiversity> findByPatentRequest(PatentRequest patent) {
		try {
			Query query = super.getEntityManager().createQuery(
					" SELECT p FROM PatentDocumentsBiodiversity p where  p.patentRequest.id = :patent and p.padoStatus= true order by 1 desc");
			query.setParameter("patent", patent.getId());
			return (List<PatentDocumentsBiodiversity>) query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<PatentDocumentsBiodiversity> findByPatentRequestImage(PatentRequest patent) {
		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT p FROM PatentDocumentsBiodiversity p where  p.patentRequest.id = :patent and p.padoStatus= true and p.dotyId = 3232 order by 1 desc");
			query.setParameter("patent", patent.getId());
			return (List<PatentDocumentsBiodiversity>) query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public PatentDocumentsBiodiversity findByPatentRequest(PatentRequest patent, Integer type) {
		try {
			Query query = super.getEntityManager().createQuery(
					" SELECT p FROM PatentDocumentsBiodiversity p where  p.patentRequest.id = :patent and  p.dotyId= :type and  p.padoStatus= true order by 1 desc",
					PatentDocumentsBiodiversity.class);
			query.setParameter("patent", patent.getId());
			query.setParameter("type", type);

			List<PatentDocumentsBiodiversity> result = (List<PatentDocumentsBiodiversity>) query.getResultList();
			if (result.size() > 0) {
				PatentDocumentsBiodiversity ret = result.get(0);
				ret.setContent(descargarDocumentoPorId(ret.getPadoAlfrescoId()));
				return ret;
			}

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}

	public boolean deleteUserData(PatentDocumentsBiodiversity patentDocuments, User user) {
		try {
			patentDocuments.setPadoStatus(false);
			edit(patentDocuments);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Deprecated
	public boolean save(PatentDocumentsBiodiversity documents, User user) {
		try {
			if (documents.getPadoId() == null) {
				documents.setPadoCreatorUser(user.getUserName());
				documents.setPadoCreationDate(new Date());
				documents.setPadoStatus(true);
				create(documents);
				return true;
			} else {
				documents.setPadoUserUpdate(user.getUserName());
				documents.setPadoDateUpdate(new Date());
				documents.setPadoStatus(true);
				edit(documents);
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean saveAlfrescoBdd(PatentDocumentsBiodiversity patentDocuments, String userName,
			String NombreProyecto) {
		try {
			if (patentDocuments.getContent() != null
					&& (patentDocuments.getPadoStatus() == null || patentDocuments.getPadoStatus())) {

				String nombreCarpeta = NombreProyecto;// patentDocuments.getPatentRequest().getPatentInformation().getUniqueCode();
				String folderId = guardarCarpeta(nombreCarpeta);
				String alfrescoId = guardarArchivo(patentDocuments.getContent(), patentDocuments.getPadoName(),
						folderId, nombreCarpeta);
				patentDocuments.setPadoAlfrescoId(alfrescoId);
				String nombreDocumento = patentDocuments.getPadoName().toLowerCase();
				nombreCarpeta = nombreCarpeta.toLowerCase();
				if (!nombreDocumento.contains(nombreCarpeta))
					nombreDocumento = nombreCarpeta + "-" + nombreDocumento;
				patentDocuments.setPadoName(nombreDocumento);
			}

			if (patentDocuments.getPadoId() == null) {
				patentDocuments.setPadoCreatorUser(userName);
				patentDocuments.setPadoCreationDate(new Date());
				patentDocuments.setPadoStatus(true);
				create(patentDocuments);
				return true;
			} else {
				patentDocuments.setPadoUserUpdate(userName);
				patentDocuments.setPadoDateUpdate(new Date());
				edit(patentDocuments);
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean saveAlfrescoBdd(PatentDocumentsBiodiversity patentDocuments, String userName) {
		try {
			if (patentDocuments.getContent() != null
					&& (patentDocuments.getPadoStatus() == null || patentDocuments.getPadoStatus())) {

				String nombreCarpeta = patentDocuments.getPatentRequest().getPatentInformation().getUniqueCode();
				String folderId = guardarCarpeta(nombreCarpeta);
				String alfrescoId = guardarArchivo(patentDocuments.getContent(), patentDocuments.getPadoName(),
						folderId, nombreCarpeta);
				patentDocuments.setPadoAlfrescoId(alfrescoId);
				String nombreDocumento = patentDocuments.getPadoName().toLowerCase();
				nombreCarpeta = nombreCarpeta.toLowerCase();
				if (!nombreDocumento.contains(nombreCarpeta))
					nombreDocumento = nombreCarpeta + "-" + nombreDocumento;
				patentDocuments.setPadoName(nombreDocumento);
			}

			if (patentDocuments.getPadoId() == null) {
				patentDocuments.setPadoCreatorUser(userName);
				patentDocuments.setPadoCreationDate(new Date());
				patentDocuments.setPadoStatus(true);
				create(patentDocuments);
				return true;
			} else {
				patentDocuments.setPadoUserUpdate(userName);
				patentDocuments.setPadoDateUpdate(new Date());
				edit(patentDocuments);
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public String guardarCarpeta(String nombreCarpeta) {
		try {
			return alfrescoServiceBean.createFolderStructure(nombreCarpeta, Constant.getRootId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String buscarCarpeta(String nombreCarpeta) {
		try {
			return alfrescoServiceBean.findFolder(Constant.getRootId(), nombreCarpeta).getId();
		} catch (CmisAlfrescoException e) {
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

	public byte[] descargarDocumentoPorId(String id) {
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

	@SuppressWarnings("unchecked")
	private boolean verificarFirma(String fileUrl) {
		try {

			PdfReader reader = new PdfReader(fileUrl);
			AcroFields af = reader.getAcroFields();
			ArrayList<AcroFields> names = af.getSignatureNames();
			return names.size() > 0;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<PatentDocumentsBiodiversity> findLikeParent(Integer id) {
		try {
			Query query = super.getEntityManager().createQuery(
					"select o from PatentDocumentsBiodiversity o where o.patentRequest.id= :patentRequest and o.padoStatus = true order by 1");
			query.setParameter("patentRequest", id);
			List<PatentDocumentsBiodiversity> catalogs = (List<PatentDocumentsBiodiversity>) query.getResultList();

			if (catalogs.size() > 0) {
				return catalogs;
			}
		} catch (NoResultException e) {
			e.printStackTrace();
		}

		return null;

	}

	@SuppressWarnings("unchecked")
	public PatentDocumentsBiodiversity findByTypeByPatentApplication(PatentRequest patent, Integer type) {
		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT p FROM PatentDocumentsBiodiversity p where  p.patentRequest.id = :patent and  p.dotyId= :type and  p.padoStatus= true order by 1 desc",
					PatentDocumentsBiodiversity.class);
			query.setParameter("patent", patent.getId());
			query.setParameter("type", type);

			List<PatentDocumentsBiodiversity> result = (List<PatentDocumentsBiodiversity>) query.getResultList();
			if (result.size() > 0) {
				PatentDocumentsBiodiversity ret = result.get(0);
				ret.setContent(descargarDocumentoPorId(ret.getPadoAlfrescoId()));
				return ret;
			}

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public PatentDocumentsBiodiversity findByTypeByPatentApplicationName(PatentRequest patent, Integer type, String nombre) {
		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT p FROM PatentDocumentsBiodiversity p where  p.patentRequest.id = :patent and  p.dotyId= :type and  p.padoStatus= true and p.padoName = :nombre order by 1 desc",
					PatentDocumentsBiodiversity.class);
			query.setParameter("patent", patent.getId());
			query.setParameter("type", type);
			query.setParameter("nombre", nombre);

			List<PatentDocumentsBiodiversity> result = (List<PatentDocumentsBiodiversity>) query.getResultList();
			if (result.size() > 0) {
				PatentDocumentsBiodiversity ret = result.get(0);
				ret.setContent(descargarDocumentoPorId(ret.getPadoAlfrescoId()));
				return ret;
			}

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<PatentDocumentsBiodiversity> findPronunciamientoNegacion(User usuarioSolicitante, Integer type) {
		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT p FROM PatentDocumentsBiodiversity p where p.dotyId= :type and p.patentRequest.patentInformation.creatorUser = :usuarioSolicitante and p.padoStatus= true and p.patenteFirmada = true order by 1 desc",
					PatentDocumentsBiodiversity.class);
			query.setParameter("type", type);
			query.setParameter("usuarioSolicitante", usuarioSolicitante.getUserName());
			
			List<PatentDocumentsBiodiversity> result = (List<PatentDocumentsBiodiversity>) query.getResultList();
			if (result.size() > 0) {
				return result;
			}
			
		} catch (NoResultException e) {
			return null;
		}
		return null;
	}
	
	
	public boolean verificarFirmaVersion(String idAlfresco) {
		return verificarFirmaAlfresco(idAlfresco);

	}
	


}