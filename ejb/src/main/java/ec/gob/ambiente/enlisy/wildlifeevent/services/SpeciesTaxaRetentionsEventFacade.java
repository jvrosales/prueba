package ec.gob.ambiente.enlisy.wildlifeevent.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.common.JPAResponse;
import ec.gob.ambiente.enlisy.dao.AbstractFacadeModel;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.wildlifeevent.model.BiodiversityGeneralCatalog;
import ec.gob.ambiente.enlisy.wildlifeevent.model.SpeciesTaxaRetentionsEvent;

@Stateless
public class SpeciesTaxaRetentionsEventFacade extends AbstractFacadeModel<SpeciesTaxaRetentionsEvent>{

	
	public SpeciesTaxaRetentionsEventFacade() {
		super(SpeciesTaxaRetentionsEvent.class);
	}

	private static final long serialVersionUID = 1L;

	@Override
	public void configure(String filter) {
		initialize();
	}
	
	@EJB
	BiodiversityGeneralCatalogFacade biodiversityGeneralCatalogFacade;
	
	public JPAResponse saveOrUpdate(List<SpeciesTaxaRetentionsEvent> list, String user, Timestamp date) {
		JPAResponse response = new JPAResponse();
		try {
			if (!list.isEmpty()) {
				for (SpeciesTaxaRetentionsEvent p:list) {
					if (isTransient(p)) {
						p.setSpreUserCreate(user);
						p.setSpreDateCreate(date);
						p.setSpreUserUpdate(user);
						p.setSpreDateUpdate(date);
						saveUncommit(p);
					}else {
						p.setSpreUserUpdate(user);
						p.setSpreDateUpdate(date);
						saveOrUpdateUncommit(p);
					}
				}
				flush();
			}
		}catch(Exception e) {
			response.setSuccessful(false);
			response.setException(e);
		}
		return response;
	}
	
	/**
	 * Buscar especies relacionadas con plan de acción
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SpeciesTaxaRetentionsEvent> findSpeciesTaxaRetentionsEventPlanAction(Integer acplId)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT s FROM SpeciesTaxaRetentionsEvent s where s.spreStatus = true and s.actionPlan.acplId=:acplId");
			query.setParameter("acplId", acplId);
			List<SpeciesTaxaRetentionsEvent> result=(List<SpeciesTaxaRetentionsEvent>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<SpeciesTaxaRetentionsEvent>();
		}
		return new ArrayList<SpeciesTaxaRetentionsEvent>();
	}
	public SpeciesTaxaRetentionsEvent eliminar(SpeciesTaxaRetentionsEvent specieTaxaR, User user)
	{
		SpeciesTaxaRetentionsEvent specie=null;
		try
		{			
			specieTaxaR.setSpreStatus(false);
			Date dateS=new Date();
			Timestamp time=new Timestamp(dateS.getTime());
			specieTaxaR.setSpreDateUpdate(time);
			specieTaxaR.setSpreUserUpdate(user.getUserName());
			specie=edit(specieTaxaR);
			return specie;
		}
		catch(Exception ex)
		{
			return specie;
		}
	}
	
	/**
	 * Buscar especies relacionadas con retencion de especies
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SpeciesTaxaRetentionsEvent> findSpeciesTaxaRetentionsEventRetention(Integer reteId)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT s FROM SpeciesTaxaRetentionsEvent s where s.spreStatus = true and s.retentionEvent.reteId=:reteId");
			query.setParameter("reteId", reteId);
			List<SpeciesTaxaRetentionsEvent> result=(List<SpeciesTaxaRetentionsEvent>)query.getResultList();
			if(result.size()>0)
			{
				for(SpeciesTaxaRetentionsEvent item:result)
				{
					if(item.getPhysicalStateId()!=null && item.getPhysicalStateId()>0)
					{
						BiodiversityGeneralCatalog tipo= biodiversityGeneralCatalogFacade.findById(item.getPhysicalStateId());
						item.setNamePhysicalState(tipo.getDescription());
					}
					if(item.getStageLifeId()!=null && item.getStageLifeId()>0)
					{
						BiodiversityGeneralCatalog tipo= biodiversityGeneralCatalogFacade.findById(item.getStageLifeId());
						item.setNameStageLife(tipo.getDescription());
					}
					
					if(item.getSpreType()!=null && item.getSpreType()>0)
					{
						BiodiversityGeneralCatalog tipo= biodiversityGeneralCatalogFacade.findById(item.getSpreType());
						item.setNameType(tipo.getDescription());
					}
				}
				return result;
			}
			
		}catch(NoResultException e)
		{
			return new ArrayList<SpeciesTaxaRetentionsEvent>();
		}
		return new ArrayList<SpeciesTaxaRetentionsEvent>();
	}
	
	/**
	 * Buscar especies relacionadas con rescates de especies
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SpeciesTaxaRetentionsEvent> findSpeciesTaxaRetentionsEventRescue(Integer reseId)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT s FROM SpeciesTaxaRetentionsEvent s where s.spreStatus = true and s.rescueEvent.reseId=:reseId");
			query.setParameter("reseId", reseId);
			List<SpeciesTaxaRetentionsEvent> result=(List<SpeciesTaxaRetentionsEvent>)query.getResultList();
			if(result.size()>0)
			{
				for(SpeciesTaxaRetentionsEvent item:result)
				{
					if(item.getPhysicalStateId()!=null && item.getPhysicalStateId()>0)
					{
						BiodiversityGeneralCatalog tipo= biodiversityGeneralCatalogFacade.findById(item.getPhysicalStateId());
						item.setNamePhysicalState(tipo.getDescription());
					}
					if(item.getStageLifeId()!=null && item.getStageLifeId()>0)
					{
						BiodiversityGeneralCatalog tipo= biodiversityGeneralCatalogFacade.findById(item.getStageLifeId());
						item.setNameStageLife(tipo.getDescription());
					}
					
					if(item.getSpreType()!=null && item.getSpreType()>0)
					{
						BiodiversityGeneralCatalog tipo= biodiversityGeneralCatalogFacade.findById(item.getSpreType());
						item.setNameType(tipo.getDescription());
					}
				}
				return result;
			}
			
		}catch(NoResultException e)
		{
			return new ArrayList<SpeciesTaxaRetentionsEvent>();
		}
		return new ArrayList<SpeciesTaxaRetentionsEvent>();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpeciesTaxaRetentionsEvent> findByRunOverEvent(Integer ruovId)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT s FROM SpeciesTaxaRetentionsEvent s where s.spreStatus = true and s.runOverEvent.ruovId=:ruovId");
			query.setParameter("ruovId", ruovId);
			List<SpeciesTaxaRetentionsEvent> result=(List<SpeciesTaxaRetentionsEvent>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<SpeciesTaxaRetentionsEvent>();
		}
		return new ArrayList<SpeciesTaxaRetentionsEvent>();
	}
}
