package ec.gob.ambiente.enlisy.actionplan.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.actionplan.model.Activity;
import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;

/**
 * Servicios para administracion de registro actividad plan de acción
 * @author EXCO-PA
 *
 */
@Stateless
public class ActivityFacade extends AbstractFacade<Activity, Integer>{

	public ActivityFacade() {
		super(Activity.class, Integer.class);		
	}
	
	/**
	 * Buscar actividad
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Activity> findActivity()
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT a FROM Activity a where a.actiStatus = true and a.expectedResult.exreStatus=true and a.expectedResult.specificGoal.spgoStatus=true and a.expectedResult.specificGoal.overrallObjective.strategicLine.stliStatus=true and a.expectedResult.specificGoal.overrallObjective.strategicLine.actionPlan.acplStatus = true order by a.actiId");
			List<Activity> result=(List<Activity>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<Activity>();
		}
		return new ArrayList<Activity>();
	}
	
	/**
	 * Guardar actividad
	 * @param expectedResult
	 * @param user
	 * @return
	 */
	public Activity save(Activity activity, User user)
	{
		Activity activ=null;
		try
		{			
			if(activity.getActiId() == null)
			{				
				activity.setActiStatus(true);
				activity.setActiDateCreate(new Date());
				activity.setActiUserCreate(user.getUserName());
				activ=create(activity);
			}else			{
				activity.setActiDateUpdate(new Date());
				activity.setActiUserUpdate(user.getUserName());
				activ=edit(activity);
			}
			return activ;
		}
		catch(Exception ex)
		{
			return activ;
		}
	}
	
	public Activity eliminar(Activity activity, User user)
	{
		Activity activ=null;
		try
		{			
			activity.setActiStatus(false);
			activity.setActiDateUpdate(new Date());
			activity.setActiUserUpdate(user.getUserName());
			activ=edit(activity);
			return activ;
		}
		catch(Exception ex)
		{
			return activ;
		}
	}
	
	/**
	 * Buscar actividades relacionadas con resultados esperados
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Activity> findActivityExpectedResult(Integer exreId)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT a FROM Activity a where a.expectedResult.exreId=:exreId and a.actiStatus = true");
			query.setParameter("exreId", exreId);
			List<Activity> result=(List<Activity>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<Activity>();
		}
		return new ArrayList<Activity>();
	}
	/**
	 * Buscar actividades relacionadas con resultados esperados
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Activity> findNoInActivityPlanAction(Integer exreId)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT a FROM Activity a where a.expectedResult.exreId=:exreId and a.actiStatus = true and a.actiId not in(select p.activity.actiId from PlanActionRecord p where p.plarStatus=true)");
			query.setParameter("exreId", exreId);
			List<Activity> result=(List<Activity>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<Activity>();
		}
		return new ArrayList<Activity>();
	}
	
}
