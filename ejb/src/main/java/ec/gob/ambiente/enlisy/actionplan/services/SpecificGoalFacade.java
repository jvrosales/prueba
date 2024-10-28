package ec.gob.ambiente.enlisy.actionplan.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.actionplan.model.SpecificGoal;
import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;

/**
 * Servicios para administracion de registro objetivo especifico
 * @author EXCO-PA
 *
 */
@Stateless
public class SpecificGoalFacade extends AbstractFacade<SpecificGoal, Integer>{

	public SpecificGoalFacade() {
		super(SpecificGoal.class, Integer.class);		
	}
	
	/**
	 * Buscar objetivo especifico
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SpecificGoal> findSpecificGoal()
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT s FROM SpecificGoal s where s.spgoStatus = true and s.overrallObjective.ovobStatus=true and s.overrallObjective.strategicLine.stliStatus=true and s.overrallObjective.strategicLine.actionPlan.acplStatus = true order by s.spgoId");
			List<SpecificGoal> result=(List<SpecificGoal>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<SpecificGoal>();
		}
		return new ArrayList<SpecificGoal>();
	}
	
	/**
	 * Guardar objetivo especifico
	 * @param specificGoal
	 * @param user
	 * @return
	 */
	public SpecificGoal save(SpecificGoal specificGoal, User user)
	{
		SpecificGoal specific=null;
		try
		{			
			if(specificGoal.getSpgoId() == null)
			{				
				specificGoal.setSpgoStatus(true);
				specificGoal.setSpgoDateCreate(new Date());
				specificGoal.setSpgoUserCreate(user.getUserName());
				specific=create(specificGoal);
			}else			{
				specificGoal.setSpgoDateUpdate(new Date());
				specificGoal.setSpgoUserUpdate(user.getUserName());
				specific=edit(specificGoal);
			}
			return specific;
		}
		catch(Exception ex)
		{
			return specific;
		}
	}
	
	public SpecificGoal eliminar(SpecificGoal specificGoal, User user)
	{
		SpecificGoal specific=null;
		try
		{			
			specificGoal.setSpgoStatus(false);
			specificGoal.setSpgoDateUpdate(new Date());
			specificGoal.setSpgoUserUpdate(user.getUserName());
			specific=edit(specificGoal);
			return specific;
		}
		catch(Exception ex)
		{
			return specific;
		}
	}
	/**
	 * Buscar objetivos especificos relacionadas con objetivos generales
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SpecificGoal> findSpecificGoalOverrallObjectives(Integer ovobId)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT s FROM SpecificGoal s where s.overrallObjective.ovobId=:ovobId and s.spgoStatus = true");
			query.setParameter("ovobId", ovobId);
			List<SpecificGoal> result=(List<SpecificGoal>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<SpecificGoal>();
		}
		return new ArrayList<SpecificGoal>();
	}
}
