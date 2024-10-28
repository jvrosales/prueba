package ec.gob.ambiente.enlisy.actionplan.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.actionplan.model.PlanActionRecord;
import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;

/**
 * Servicios para administracion de registro plan de acción
 * @author EXCO-PA
 *
 */
@Stateless
public class PlanActionRecordFacade extends AbstractFacade<PlanActionRecord, Integer>{

	public PlanActionRecordFacade() {
		super(PlanActionRecord.class, Integer.class);		
	}
	
	/**
	 * Buscar registro plan de acción
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PlanActionRecord> findPlanActionRecord()
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT p FROM PlanActionRecord p where p.plarStatus = true");
			List<PlanActionRecord> result=(List<PlanActionRecord>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<PlanActionRecord>();
		}
		return new ArrayList<PlanActionRecord>();
	}
	
	/**
	 * Guardar registro plan de acción
	 * @param expectedResult
	 * @param user
	 * @return
	 */
	public PlanActionRecord save(PlanActionRecord planActionRecord, User user)
	{
		PlanActionRecord planActionR=null;
		try
		{			
			if(planActionRecord.getPlarId() == null || planActionRecord.getPlarId().compareTo(Integer.valueOf(0))==0 )
			{				
				planActionRecord.setPlarStatus(true);
				planActionRecord.setPlarDateCreate(new Date());
				planActionRecord.setPlarUserCreate(user.getUserName());
				planActionR=create(planActionRecord);
			}else			{
				planActionRecord.setPlarDateUpdate(new Date());
				planActionRecord.setPlarUserUpdate(user.getUserName());
				planActionR=edit(planActionRecord);
			}
			return planActionR;
		}
		catch(Exception ex)
		{
			return planActionR;
		}
	}
	
	public PlanActionRecord eliminar(PlanActionRecord planActionRecord, User user)
	{
		PlanActionRecord planActionR=null;
		try
		{			
			planActionRecord.setPlarStatus(false);
			planActionRecord.setPlarDateUpdate(new Date());
			planActionRecord.setPlarUserUpdate(user.getUserName());
			planActionR=edit(planActionRecord);
			return planActionR;
		}
		catch(Exception ex)
		{
			return planActionR;
		}
	}
	/**
	 * Buscar actividad en registro plan de acción
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PlanActionRecord> findPlanActionRecordActivity(Integer actiId)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT p FROM PlanActionRecord p where  p.activity.actiId=:actiId and p.plarStatus = true");
			query.setParameter("actiId", actiId);
			List<PlanActionRecord> result=(List<PlanActionRecord>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<PlanActionRecord>();
		}
		return new ArrayList<PlanActionRecord>();
	}
	
}
