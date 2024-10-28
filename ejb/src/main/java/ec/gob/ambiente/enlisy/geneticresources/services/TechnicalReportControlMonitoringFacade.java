package ec.gob.ambiente.enlisy.geneticresources.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.geneticresources.model.ProposedCollection;
import ec.gob.ambiente.enlisy.geneticresources.model.ProposedCollectionControlMonitoring;
import ec.gob.ambiente.enlisy.geneticresources.model.TechnicalReportControlMonitoring;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class TechnicalReportControlMonitoringFacade extends AbstractFacade<TechnicalReportControlMonitoring, Integer> {
	
	public TechnicalReportControlMonitoringFacade(){
		super(TechnicalReportControlMonitoring.class, Integer.class);
	}
	
	
	public Integer reportCode(String sequenceName) {
		Query q;
		q = getEntityManager().createNativeQuery("select nextval('"+sequenceName.toLowerCase()+"');");
		java.math.BigInteger result= (java.math.BigInteger)q.getSingleResult();
		return Integer.valueOf(result.intValue());
	}
	
	@SuppressWarnings("unchecked")
	public List<TechnicalReportControlMonitoring> findByControl(ProposedCollectionControlMonitoring control){
		try {
			
			List<TechnicalReportControlMonitoring> controlList = new ArrayList<TechnicalReportControlMonitoring>();;
			
			Query query = super.getEntityManager().createQuery("SELECT o FROM TechnicalReportControlMonitoring o "
					+ "where o.proposedCollectionControlMonitoring = :control and o.trcmStatus = true order by 1 desc");
			query.setParameter("control", control);
			
			controlList=(List<TechnicalReportControlMonitoring>)query.getResultList();
			if(!controlList.isEmpty())
				return controlList;			
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
		return null;
	}
	
	
	public void save(TechnicalReportControlMonitoring report, User user)
	{
		try
		{	
			if(report.getTrcmId() == null){
				report.setTrcmStatus(true);
				report.setTrcmDateCreate(new Date());
				report.setTrcmUserCreate(user.getUserName());
				create(report);
			}else{
				report.setTrcmDateUpdate(new Date());
				report.setTrcmUserUpdate(user.getUserName());
				edit(report);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<TechnicalReportControlMonitoring> findByProposedCollection(ProposedCollection propuesta){
		try {
			
			List<TechnicalReportControlMonitoring> controlList = new ArrayList<TechnicalReportControlMonitoring>();;
			
			Query query = super.getEntityManager().createQuery("SELECT o FROM TechnicalReportControlMonitoring o "
					+ "where o.proposedCollection = :propuesta and o.trcmStatus = true and o.trcmLegalReport = true order by 1 desc");
			query.setParameter("propuesta", propuesta);
			
			controlList=(List<TechnicalReportControlMonitoring>)query.getResultList();
			if(!controlList.isEmpty())
				return controlList;			
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
		return null;
	}

}
