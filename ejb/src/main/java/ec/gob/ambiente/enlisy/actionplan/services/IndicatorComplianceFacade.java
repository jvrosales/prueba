package ec.gob.ambiente.enlisy.actionplan.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.actionplan.model.IndicatorCompliance;
import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;

/**
 * Servicios para administracion de registro de indicador de cumplimiento
 * @author EXCO-PA
 *
 */
@Stateless
public class IndicatorComplianceFacade extends AbstractFacade<IndicatorCompliance, Integer>{

	public IndicatorComplianceFacade() {
		super(IndicatorCompliance.class, Integer.class);		
	}
	
	/**
	 * Buscar Financiadores
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<IndicatorCompliance> findIndicatorsCompliance()
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT i FROM IndicatorCompliance i where i.incoStatus = true");
			List<IndicatorCompliance> result=(List<IndicatorCompliance>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<IndicatorCompliance>();
		}
		return new ArrayList<IndicatorCompliance>();
	}
	
	/**
	 * Guardar Financiadores
	 * @param funder
	 * @param user
	 * @return
	 */
	public IndicatorCompliance save(IndicatorCompliance indicatorCompliance, User user)
	{
		IndicatorCompliance indicatorComplianceS=null;
		try
		{			
			if(indicatorCompliance.getIncoId() == null)
			{				
				indicatorCompliance.setIncoStatus(true);
				indicatorCompliance.setIncoDateCreate(new Date());
				indicatorCompliance.setIncoUserCreate(user.getUserName());
				indicatorComplianceS=create(indicatorCompliance);
			}else			{
				indicatorCompliance.setIncoDateUpdate(new Date());
				indicatorCompliance.setIncoUserUpdate(user.getUserName());
				indicatorComplianceS=edit(indicatorCompliance);
			}
			return indicatorComplianceS;
		}
		catch(Exception ex)
		{
			return indicatorComplianceS;
		}
	}
	
	public IndicatorCompliance eliminar(IndicatorCompliance indicatorCompliance, User user)
	{
		IndicatorCompliance indicatorComplianceE=null;
		try
		{			
			indicatorCompliance.setIncoStatus(false);
			indicatorCompliance.setIncoDateUpdate(new Date());
			indicatorCompliance.setIncoUserUpdate(user.getUserName());
			indicatorComplianceE=edit(indicatorCompliance);
			return indicatorComplianceE;
		}
		catch(Exception ex)
		{
			return indicatorComplianceE;
		}
	}
	
}
