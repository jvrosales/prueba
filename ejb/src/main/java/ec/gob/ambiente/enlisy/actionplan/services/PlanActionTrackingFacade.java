package ec.gob.ambiente.enlisy.actionplan.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.actionplan.model.PlanActionTracking;
import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;

/**
 * Servicios para administracion de registro detalle plan de accion seguimiento
 * @author EXCO-PA
 *
 */
@Stateless
public class PlanActionTrackingFacade extends AbstractFacade<PlanActionTracking, Integer>{

	public PlanActionTrackingFacade() {
		super(PlanActionTracking.class, Integer.class);		
	}
	
	/**
	 * Buscar seguimiento Plan de acccion
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PlanActionTracking> findPlanActionTracking()
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT p FROM PlanActionTracking p where p.platStatus = true");
			List<PlanActionTracking> result=(List<PlanActionTracking>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<PlanActionTracking>();
		}
		return new ArrayList<PlanActionTracking>();
	}
	
	/**
	 * Guardar seguimiento plan de accion
	 * @param planActionTracking
	 * @param user
	 * @return
	 */
	public PlanActionTracking save(PlanActionTracking planActionTracking, User user)
	{
		PlanActionTracking planActionT=null;
		try
		{			
			if(planActionTracking.getPlatId() == null)
			{				
				planActionTracking.setPlatStatus(true);
				planActionTracking.setPlatDateCreate(new Date());
				planActionTracking.setPlatUserCreate(user.getUserName());
				planActionT=create(planActionTracking);
			}else			{
				planActionTracking.setPlatDateUpdate(new Date());
				planActionTracking.setPlatUserUpdate(user.getUserName());
				planActionT=edit(planActionTracking);
			}
			return planActionT;
		}
		catch(Exception ex)
		{
			return planActionT;
		}
	}
	
	public PlanActionTracking eliminar(PlanActionTracking planActionTracking, User user)
	{
		PlanActionTracking planActionT=null;
		try
		{			
			planActionTracking.setPlatStatus(false);
			planActionTracking.setPlatDateUpdate(new Date());
			planActionTracking.setPlatUserUpdate(user.getUserName());
			planActionT=edit(planActionTracking);
			return planActionT;
		}
		catch(Exception ex)
		{
			return planActionT;
		}
	}
	
	/**
	 * Buscar seguimiento plan de accion relacionadas con registro plan de acción
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PlanActionTracking> findPlanActionTracking(Integer plarId)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT p FROM PlanActionTracking p where p.platStatus = true and p.planActionRecord.plarId=:plarId");
			query.setParameter("plarId", plarId);
			List<PlanActionTracking> result=(List<PlanActionTracking>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<PlanActionTracking>();
		}
		return new ArrayList<PlanActionTracking>();
	}
	
}
