package ec.gob.ambiente.enlisy.geneticresources.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.geneticresources.model.PendingsReport;

@Stateless
public class PendingsReportDao extends AbstractFacade<PendingsReport, Integer> {

	public PendingsReportDao() {
		super(PendingsReport.class, Integer.class);		
	}
	
	public boolean verifyPendingReport(String documentId)
	{
		Query query = super.getEntityManager().createQuery("select p from PendingsReport p where p.pereDocumentId = :documentId and p.pereStatusReport = false");
		query.setParameter("documentId", documentId);
		
		if(query.getResultList().size() > 0)
		{
			return true;
		}
		
		return false;
	}

	/**
	 * Obtiene un listado de proyectos pendientes de un proponente determinado
	 * @param documentId Documento de identificación del proponente ejm: cédula, pasaporte
	 * @return List<PendingsReport>
	 */
	@SuppressWarnings("unchecked")
	public List<PendingsReport> getPendingsReportsByDocumentId(String documentId)
	{
		Query query = super.getEntityManager().createQuery("select p from PendingsReport p where p.pereDocumentId = :documentId and p.pereStatusReport = false");
		query.setParameter("documentId", documentId);
				
		return (List<PendingsReport>) query.getResultList();
	}
}