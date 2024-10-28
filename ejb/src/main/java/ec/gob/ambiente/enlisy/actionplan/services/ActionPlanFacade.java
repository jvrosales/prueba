package ec.gob.ambiente.enlisy.actionplan.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.actionplan.model.ActionPlan;
import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;

/**
 * Servicios para administracion de registro de Planes de Accion
 * @author EXCO-PA
 *
 */
@Stateless
public class ActionPlanFacade extends AbstractFacade<ActionPlan, Integer>{

	public ActionPlanFacade() {
		super(ActionPlan.class, Integer.class);		
	}
	
	/**
	 * Buscar Action plan
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ActionPlan> findActions()
	{
		try
		{
			//
			Query query = super.getEntityManager().createQuery("SELECT a FROM ActionPlan a where a.acplStatus = true order by acplId desc");
			List<ActionPlan> result=(List<ActionPlan>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<ActionPlan>();
		}
		return new ArrayList<ActionPlan>();
	}
	
	/**
	 * Guardar Plan de Acción
	 * @param actionPlan
	 * @param user
	 * @return
	 */
	public ActionPlan save(ActionPlan actionPlan, User user)
	{
		ActionPlan action=null;
		try
		{			
			if(actionPlan.getAcplId() == null)
			{				
				actionPlan.setAcplStatus(true);
				actionPlan.setAcplDateCreate(new Date());
				actionPlan.setAcplUserCreate(user.getUserName());
				action=create(actionPlan);
			}else			{
				actionPlan.setAcplDateUpdate(new Date());
				actionPlan.setAcplUserUpdate(user.getUserName());
				action=edit(actionPlan);
			}
			return action;
		}
		catch(Exception ex)
		{
			return action;
		}
	}
	
	public ActionPlan eliminar(ActionPlan actionPlan, User user)
	{
		ActionPlan action=null;
		try
		{			
			actionPlan.setAcplStatus(false);
			actionPlan.setAcplDateUpdate(new Date());
			actionPlan.setAcplUserUpdate(user.getUserName());
			action=edit(actionPlan);
			return action;
		}
		catch(Exception ex)
		{
			return action;
		}
	}
	
}
