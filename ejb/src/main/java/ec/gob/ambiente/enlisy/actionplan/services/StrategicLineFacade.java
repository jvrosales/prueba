package ec.gob.ambiente.enlisy.actionplan.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.actionplan.model.StrategicLine;
import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;

/**
 * Servicios para administracion de registro lineas estrategicas
 * @author EXCO-PA
 *
 */
@Stateless
public class StrategicLineFacade extends AbstractFacade<StrategicLine, Integer>{

	public StrategicLineFacade() {
		super(StrategicLine.class, Integer.class);		
	}
	
	/**
	 * Buscar lineas estrategicas
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<StrategicLine> findStrategicLines()
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT s FROM StrategicLine s where s.stliStatus = true and s.actionPlan.acplStatus = true order by s.stliId");
			List<StrategicLine> result=(List<StrategicLine>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<StrategicLine>();
		}
		return new ArrayList<StrategicLine>();
	}
	
	/**
	 * Guardar linea estrategica
	 * @param strategicLine
	 * @param user
	 * @return
	 */
	public StrategicLine save(StrategicLine strategicLine, User user)
	{
		StrategicLine strategic=null;
		try
		{			
			if(strategicLine.getStliId() == null)
			{				
				strategicLine.setStliStatus(true);
				strategicLine.setStliDateCreate(new Date());
				strategicLine.setStliUserCreate(user.getUserName());
				strategic=create(strategicLine);
			}else			{
				strategicLine.setStliDateUpdate(new Date());
				strategicLine.setStliUserUpdate(user.getUserName());
				strategic=edit(strategicLine);
			}
			return strategic;
		}
		catch(Exception ex)
		{
			return strategic;
		}
	}
	
	public StrategicLine eliminar(StrategicLine strategicLine, User user)
	{
		StrategicLine strategic=null;
		try
		{			
			strategicLine.setStliStatus(false);
			strategicLine.setStliDateUpdate(new Date());
			strategicLine.setStliUserUpdate(user.getUserName());
			strategic=edit(strategicLine);
			return strategic;
		}
		catch(Exception ex)
		{
			return strategic;
		}
	}
	
	/**
	 * Buscar lineas estrategicas relacionadas con planes de accion
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<StrategicLine> findStrategisLineActionsPlan(Integer acplId)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT s FROM StrategicLine s where s.actionPlan.acplId=:acplId and s.stliStatus = true");
			query.setParameter("acplId", acplId);
			List<StrategicLine> result=(List<StrategicLine>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<StrategicLine>();
		}
		return new ArrayList<StrategicLine>();
	}
	
}
