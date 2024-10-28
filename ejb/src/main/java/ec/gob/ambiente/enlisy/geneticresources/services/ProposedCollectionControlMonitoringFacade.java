package ec.gob.ambiente.enlisy.geneticresources.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.geneticresources.model.ProposedCollection;
import ec.gob.ambiente.enlisy.geneticresources.model.ProposedCollectionControlMonitoring;
import ec.gob.ambiente.enlisy.model.GeographicalLocation;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class ProposedCollectionControlMonitoringFacade extends AbstractFacade<ProposedCollectionControlMonitoring, Integer>{

	public ProposedCollectionControlMonitoringFacade(){
		super(ProposedCollectionControlMonitoring.class, Integer.class);
	}
	
	public void save(ProposedCollectionControlMonitoring control, User user){
		
		if(control.getPccmId() == null){
			control.setPccmStatus(true);
			control.setPccmUserCreate(user.getUserName());
			control.setPccmDateCreate(new Date());
			create(control);
			
		}else{
			control.setPccmUserUpdate(user.getUserName());
			control.setPccmDateUpdate(new Date());
			edit(control);
		}		
	}
	
	public ProposedCollectionControlMonitoring findById(Integer id){
		
		Query query = super.getEntityManager().createQuery("select p from ProposedCollectionControlMonitoring p where p.pccmId = :pccmId and p.pccmStatus = true");
		query.setParameter("pccmId", id);
		
		if(query.getResultList().size() > 0){
			ProposedCollectionControlMonitoring controlMonitoring = (ProposedCollectionControlMonitoring) query.getResultList().get(0);
			return controlMonitoring;
		}
		
		return null;
	}
	
	public ProposedCollectionControlMonitoring findByProposedCollection(ProposedCollection propuesta){
		
		Query query = super.getEntityManager().createQuery("select p from ProposedCollectionControlMonitoring p where p.proposedCollection = :propuesta and p.pccmStatus = true");
		query.setParameter("propuesta", propuesta);
		
		if(query.getResultList().size() > 0){
			ProposedCollectionControlMonitoring controlMonitoring = (ProposedCollectionControlMonitoring) query.getResultList().get(0);
			return controlMonitoring;
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProposedCollectionControlMonitoring> findByGeographicalLocationPeriodo(GeographicalLocation location, int mes, int anio){
		
		Query query = super.getEntityManager().createQuery("select p from ProposedCollectionControlMonitoring p "
				+ "where p.geographicalLocation = :location and p.pccmStatus = true and MONTH(p.pccmControlPeriod) = :mes "
				+ "and YEAR(p.pccmControlPeriod) = :anio");
		query.setParameter("location", location);
		query.setParameter("mes", mes);
		query.setParameter("anio", anio);
		
		if(query.getResultList().size() > 0){
			List<ProposedCollectionControlMonitoring> controlMonitoring = (List<ProposedCollectionControlMonitoring>) query.getResultList();
			return controlMonitoring;
		}
		
		return null;
	}
	
	public ProposedCollectionControlMonitoring findByCode(String code){
		
		Query query = super.getEntityManager().createQuery("select p from ProposedCollectionControlMonitoring p "
				+ "where p.pccmStatus = true and p.pccmCode = :code");
		query.setParameter("code", code);		
		
		if(query.getResultList().size() > 0){
			ProposedCollectionControlMonitoring controlMonitoring = (ProposedCollectionControlMonitoring) query.getResultList().get(0);
			return controlMonitoring;
		}
		
		return null;
	}
}