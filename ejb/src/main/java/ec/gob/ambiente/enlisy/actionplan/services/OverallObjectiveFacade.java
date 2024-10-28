package ec.gob.ambiente.enlisy.actionplan.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.actionplan.model.OverrallObjective;
import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;

/**
 * Servicios para administracion de registro objetivo general
 * @author EXCO-PA
 *
 */
@Stateless
public class OverallObjectiveFacade extends AbstractFacade<OverrallObjective, Integer>{

	public OverallObjectiveFacade() {
		super(OverrallObjective.class, Integer.class);		
	}
	
	/**
	 * Buscar objetivo general
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<OverrallObjective> findOverrallObjective()
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM OverrallObjective o where o.ovobStatus = true and o.strategicLine.stliStatus=true and o.strategicLine.actionPlan.acplStatus = true order by o.ovobId");
			List<OverrallObjective> result=(List<OverrallObjective>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<OverrallObjective>();
		}
		return new ArrayList<OverrallObjective>();
	}
	
	/**
	 * Guardar linea estrategica
	 * @param strategicLine
	 * @param user
	 * @return
	 */
	public OverrallObjective save(OverrallObjective overrallObjective, User user)
	{
		OverrallObjective overrall=null;
		try
		{			
			if(overrallObjective.getOvobId() == null)
			{				
				overrallObjective.setOvobStatus(true);
				overrallObjective.setOvobDateCreate(new Date());
				overrallObjective.setOvobUserCreate(user.getUserName());
				overrall=create(overrallObjective);
			}else			{
				overrallObjective.setOvobDateUpdate(new Date());
				overrallObjective.setOvobUserUpdate(user.getUserName());
				overrall=edit(overrallObjective);
			}
			return overrall;
		}
		catch(Exception ex)
		{
			return overrall;
		}
	}
	
	public OverrallObjective eliminar(OverrallObjective overrallObjective, User user)
	{
		OverrallObjective overrall=null;
		try
		{			
			overrallObjective.setOvobStatus(false);
			overrallObjective.setOvobDateUpdate(new Date());
			overrallObjective.setOvobUserUpdate(user.getUserName());
			overrall=edit(overrallObjective);
			return overrall;
		}
		catch(Exception ex)
		{
			return overrall;
		}
	}
	
	/**
	 * Buscar objetivos generales relacionadas con Lineas estrategicas
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<OverrallObjective> findOverrallObjectivesStrategicsLine(Integer stliId)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM OverrallObjective o where o.strategicLine.stliId=:stliId and o.ovobStatus = true");
			query.setParameter("stliId", stliId);
			List<OverrallObjective> result=(List<OverrallObjective>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<OverrallObjective>();
		}
		return new ArrayList<OverrallObjective>();
	}
}
