package ec.gob.ambiente.enlisy.riskanalysis.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.chemistry.opencmis.client.api.Document;

import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.PdfReader;

import ec.gob.ambiente.alfresco.service.AlfrescoServiceBean;
import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.riskanalysis.model.RiskAnalysisApplicationDocument;
import ec.gob.ambiente.exceptions.CmisAlfrescoException;
import ec.gob.ambiente.util.Constant;

/**
 * Servicios para la administracion de documentos de solicitudes de analisis de
 * riesgo
 * 
 * @author EXCO
 *
 */
@Stateless
public class RiskAnalysisApplicationDocumentFacade extends AbstractFacade<RiskAnalysisApplicationDocument, Integer> {

	public RiskAnalysisApplicationDocumentFacade() {
		super(RiskAnalysisApplicationDocument.class, Integer.class);
	}

	@EJB(lookup = Constant.ALFRESCO_SERVICE_BEAN)
	private AlfrescoServiceBean alfrescoServiceBean;

	/**
	 * Recuperar Documento historial de invasividad de la especie
	 * 
	 * @param idSolicitud
	 * @return
	 */
	public RiskAnalysisApplicationDocument findDocHistInvBySolicitud(Integer idSolicitud) {
		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM RiskAnalysisApplicationDocument o where o.riaaId.riaaId= :codigo and o.raadType = :tipo and o.raadStatus = true");
			query.setParameter("codigo", idSolicitud);
			query.setParameter("tipo", "HIST_INV");

			RiskAnalysisApplicationDocument result = (RiskAnalysisApplicationDocument) query.getSingleResult();
			if (result != null)
				return result;

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}

	/**
	 * Recuperar documento relaciones con taxones invasores cercanos
	 * 
	 * @param idSolicitud
	 * @return
	 */
	public RiskAnalysisApplicationDocument findDocRelBySolicitud(Integer idSolicitud) {
		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM RiskAnalysisApplicationDocument o where o.riaaId.riaaId= :codigo and o.raadType = :tipo and o.raadStatus = true");
			query.setParameter("codigo", idSolicitud);
			query.setParameter("tipo", "RELA_TAX");

			RiskAnalysisApplicationDocument result = (RiskAnalysisApplicationDocument) query.getSingleResult();
			if (result != null)
				return result;

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}

	/**
	 * Obtener documento vectores de especies invasoras
	 * 
	 * @param idSolicitud
	 * @return
	 */
	public RiskAnalysisApplicationDocument findDocVecEspInvBySolicitud(Integer idSolicitud) {
		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM RiskAnalysisApplicationDocument o where o.riaaId.riaaId= :codigo and o.raadType = :tipo and o.raadStatus = true");
			query.setParameter("codigo", idSolicitud);
			query.setParameter("tipo", "VECT_ESP");

			RiskAnalysisApplicationDocument result = (RiskAnalysisApplicationDocument) query.getSingleResult();
			if (result != null)
				return result;

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}

	/**
	 * Obtener el documento de riesgo de introduccion
	 * 
	 * @param idSolicitud
	 * @return
	 */
	public RiskAnalysisApplicationDocument findDocIntRisk(Integer idSolicitud) {
		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM RiskAnalysisApplicationDocument o where o.riaaId.riaaId= :codigo and o.raadType = :tipo and o.raadStatus = true");
			query.setParameter("codigo", idSolicitud);
			query.setParameter("tipo", "INTR_RIS");

			RiskAnalysisApplicationDocument result = (RiskAnalysisApplicationDocument) query.getSingleResult();
			if (result != null)
				return result;

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}

	/**
	 * Obtener el documento de riesgo de establecimiento
	 * 
	 * @param idSolicitud
	 * @return
	 */
	public RiskAnalysisApplicationDocument findDocEstRisk(Integer idSolicitud) {
		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM RiskAnalysisApplicationDocument o where o.riaaId.riaaId= :codigo and o.raadType = :tipo and o.raadStatus = true");
			query.setParameter("codigo", idSolicitud);
			query.setParameter("tipo", "ESTA_RIS");

			RiskAnalysisApplicationDocument result = (RiskAnalysisApplicationDocument) query.getSingleResult();
			if (result != null)
				return result;

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}

	/**
	 * Obtener el documento de riesgo de dispersion
	 * 
	 * @param idSolicitud
	 * @return
	 */

	public RiskAnalysisApplicationDocument findDocDispRisk(Integer idSolicitud) {
		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM RiskAnalysisApplicationDocument o where o.riaaId.riaaId= :codigo and o.raadType = :tipo and o.raadStatus = true");
			query.setParameter("codigo", idSolicitud);
			query.setParameter("tipo", "DISP_RIS");

			RiskAnalysisApplicationDocument result = (RiskAnalysisApplicationDocument) query.getSingleResult();
			if (result != null)
				return result;

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}

	/**
	 * Metodo para recuperar documento de impacto sanitarios
	 * 
	 * @param idSolicitud
	 * @return
	 */
	public RiskAnalysisApplicationDocument findDocImpSan(Integer idSolicitud) {
		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM RiskAnalysisApplicationDocument o where o.riaaId.riaaId= :codigo and o.raadType = :tipo and o.raadStatus = true");
			query.setParameter("codigo", idSolicitud);
			query.setParameter("tipo", "IMPA_SAN");

			RiskAnalysisApplicationDocument result = (RiskAnalysisApplicationDocument) query.getSingleResult();
			if (result != null)
				return result;

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}

	/**
	 * Obtener documento de impactos economicos
	 * 
	 * @param idSolicitud
	 * @return
	 */
	public RiskAnalysisApplicationDocument findDocImpEcon(Integer idSolicitud) {
		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM RiskAnalysisApplicationDocument o where o.riaaId.riaaId= :codigo and o.raadType = :tipo and o.raadStatus = true");
			query.setParameter("codigo", idSolicitud);
			query.setParameter("tipo", "IMPA_ECON");

			RiskAnalysisApplicationDocument result = (RiskAnalysisApplicationDocument) query.getSingleResult();
			if (result != null)
				return result;

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}

	/**
	 * Obtener documento de impactos ambientales
	 * 
	 * @param idSolicitud
	 * @return
	 */

	public RiskAnalysisApplicationDocument findDocImpAmb(Integer idSolicitud) {
		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM RiskAnalysisApplicationDocument o where o.riaaId.riaaId= :codigo and o.raadType = :tipo and o.raadStatus = true");
			query.setParameter("codigo", idSolicitud);
			query.setParameter("tipo", "IMPA_AMB");

			RiskAnalysisApplicationDocument result = (RiskAnalysisApplicationDocument) query.getSingleResult();
			if (result != null)
				return result;

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}

	/**
	 * Obtener documento de impactos ecologicos
	 * 
	 * @param idSolicitud
	 * @return
	 */
	public RiskAnalysisApplicationDocument findDocImpEcol(Integer idSolicitud) {
		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM RiskAnalysisApplicationDocument o where o.riaaId.riaaId= :codigo and o.raadType = :tipo and o.raadStatus = true");
			query.setParameter("codigo", idSolicitud);
			query.setParameter("tipo", "IMPA_ECOL");

			RiskAnalysisApplicationDocument result = (RiskAnalysisApplicationDocument) query.getSingleResult();
			if (result != null)
				return result;

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}

	/**
	 * Obtener documento de plan de manejo
	 * 
	 * @param idSolicitud
	 * @return
	 */
	public RiskAnalysisApplicationDocument findDocPlanManejo(Integer idSolicitud) {
		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM RiskAnalysisApplicationDocument o where o.riaaId.riaaId= :codigo and o.raadType = :tipo and o.raadStatus = true");
			query.setParameter("codigo", idSolicitud);
			query.setParameter("tipo", "PLAN_MAN");

			RiskAnalysisApplicationDocument result = (RiskAnalysisApplicationDocument) query.getSingleResult();
			if (result != null)
				return result;

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}
	
	/**
	 * Buscar documento tecnico
	 * @param idSolicitud
	 * @return
	 */
	public RiskAnalysisApplicationDocument findDocTecnico(Integer idSolicitud) {
		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM RiskAnalysisApplicationDocument o where o.riaaId.riaaId= :codigo and o.raadType = :tipo and o.raadStatus = true");
			query.setParameter("codigo", idSolicitud);
			query.setParameter("tipo", "DOCU_TEC");

			RiskAnalysisApplicationDocument result = (RiskAnalysisApplicationDocument) query.getSingleResult();
			if (result != null)
				return result;

		} catch (NoResultException e) {
			return null;
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

	/**
	 * Crear la carpeta y guardar el archivo
	 * 
	 * @param document
	 * @param userName
	 * @return
	 */
	public RiskAnalysisApplicationDocument saveAlfrescoBdd(RiskAnalysisApplicationDocument document, String userName) {
		try {

			String nombreCarpeta = document.getRiaaId().getRiaaCode();
			String folderId = guardarCarpeta(nombreCarpeta);
			String alfrescoId = guardarArchivo(document.getContent(), document.getRaadName(), folderId, nombreCarpeta);
			document.setRaadAlfrescoId(alfrescoId);
			String nombreDocumento = document.getRaadName().toLowerCase();
			nombreCarpeta = nombreCarpeta.toLowerCase();
			if (!nombreDocumento.contains(nombreCarpeta))
				nombreDocumento = nombreCarpeta + "-" + nombreDocumento;
			document.setRaadName(nombreDocumento);

			document.setRaadUserCreate(userName);
			document.setRaadDateCreate(new Date());
			document.setRaadStatus(true);
			return create(document);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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

	/**
	 * Guardar archivo
	 * 
	 * @param fileByte
	 * @param fileName
	 * @param folderIds
	 * @param folderName
	 * @return
	 */
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
	 * Versionar el contenido de un documento
	 * 
	 * @param document
	 * @param userName
	 * @return
	 */
	public RiskAnalysisApplicationDocument versionarAlfrescoBdd(RiskAnalysisApplicationDocument document,
			String userName) {
		try {

			alfrescoServiceBean.replaceDocumentContent(document.getRaadAlfrescoId(), document.getContent(),
					document.getRaadExtension());
			String nombreCarpeta = document.getRiaaId().getRiaaCode();
			String nombreDocumento = document.getRaadName().toLowerCase();
			nombreCarpeta = nombreCarpeta.toLowerCase();
			if (!nombreDocumento.contains(nombreCarpeta))
				nombreDocumento = nombreCarpeta + "-" + nombreDocumento;
			document.setRaadName(nombreDocumento);

			document.setRaadUserUpdate(userName);
			document.setRaadDateUpdate(new Date());
			return edit(document);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Actualizar la informacion del documento
	 * 
	 * @param document
	 * @param userName
	 * @return
	 */
	public RiskAnalysisApplicationDocument actualizarDocumento(RiskAnalysisApplicationDocument document,
			String userName) {
		try {
			document.setRaadUserUpdate(userName);
			document.setRaadDateUpdate(new Date());
			return edit(document);

		} catch (Exception e) {
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
