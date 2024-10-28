package ec.gob.ambiente.enlisy.actionplan.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.actionplan.model.PlanActionFunder;
import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;

/**
 * Servicios para administracion de registro detalle plan de accion con financiadores
 * @author EXCO-PA
 *
 */
@Stateless
public class PlanActionFunderFacade extends AbstractFacade<PlanActionFunder, Integer>{

	public PlanActionFunderFacade() {
		super(PlanActionFunder.class, Integer.class);		
	}
	
	/**
	 * Buscar Plan de acccion con financiador
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PlanActionFunder> findPlanActionFunder()
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT p FROM PlanActionFunder p where p.plafStatus = true");
			List<PlanActionFunder> result=(List<PlanActionFunder>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<PlanActionFunder>();
		}
		return new ArrayList<PlanActionFunder>();
	}
	
	/**
	 * Guardar plan de accion con financiador
	 * @param planActionFunder
	 * @param user
	 * @return
	 */
	public PlanActionFunder save(PlanActionFunder planActionFunder, User user)
	{
		PlanActionFunder planActionF=null;
		try
		{			
			if(planActionFunder.getPlafId() == null)
			{				
				planActionFunder.setPlafStatus(true);
				planActionFunder.setPlafDateCreate(new Date());
				planActionFunder.setPlafUserCreate(user.getUserName());
				planActionF=create(planActionFunder);
			}else			{
				planActionFunder.setPlafDateUpdate(new Date());
				planActionFunder.setPlafUserUpdate(user.getUserName());
				planActionF=edit(planActionFunder);
			}
			return planActionF;
		}
		catch(Exception ex)
		{
			return planActionF;
		}
	}
	
	public PlanActionFunder eliminar(PlanActionFunder planActionFunder, User user)
	{
		PlanActionFunder planActionF=null;
		try
		{			
			planActionFunder.setPlafStatus(false);
			planActionFunder.setPlafDateUpdate(new Date());
			planActionFunder.setPlafUserUpdate(user.getUserName());
			planActionF=edit(planActionFunder);
			return planActionF;
		}
		catch(Exception ex)
		{
			return planActionF;
		}
	}
	
	/**
	 * Buscar plan de accion financiador relacionadas con registro plan de acción
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PlanActionFunder> findPlanActionFunder(Integer plarId)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT p FROM PlanActionFunder p where p.plafStatus = true and p.planActionRecord.plarId=:plarId");
			query.setParameter("plarId", plarId);
			List<PlanActionFunder> result=(List<PlanActionFunder>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<PlanActionFunder>();
		}
		return new ArrayList<PlanActionFunder>();
	}
	
}
