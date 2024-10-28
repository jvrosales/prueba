package ec.gob.ambiente.enlisy.actionplan.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.actionplan.model.ExpectedResult;
import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;

/**
 * Servicios para administracion de registro resultado esperado
 * @author EXCO-PA
 *
 */
@Stateless
public class ExpectedResultFacade extends AbstractFacade<ExpectedResult, Integer>{

	public ExpectedResultFacade() {
		super(ExpectedResult.class, Integer.class);		
	}
	
	/**
	 * Buscar resultado esperado
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ExpectedResult> findExpectedResult()
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT e FROM ExpectedResult e where e.exreStatus = true and e.specificGoal.spgoStatus=true and e.specificGoal.overrallObjective.strategicLine.stliStatus=true and e.specificGoal.overrallObjective.strategicLine.actionPlan.acplStatus = true order by e.exreId");
			List<ExpectedResult> result=(List<ExpectedResult>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<ExpectedResult>();
		}
		return new ArrayList<ExpectedResult>();
	}
	
	/**
	 * Guardar resultado esperado
	 * @param expectedResult
	 * @param user
	 * @return
	 */
	public ExpectedResult save(ExpectedResult expectedResult, User user)
	{
		ExpectedResult expectedR=null;
		try
		{			
			if(expectedResult.getExreId() == null)
			{				
				expectedResult.setExreStatus(true);
				expectedResult.setExreDateCreate(new Date());
				expectedResult.setExreUserCreate(user.getUserName());
				expectedR=create(expectedResult);
			}else			{
				expectedResult.setExreDateUpdate(new Date());
				expectedResult.setExreUserUpdate(user.getUserName());
				expectedR=edit(expectedResult);
			}
			return expectedR;
		}
		catch(Exception ex)
		{
			return expectedR;
		}
	}
	
	public ExpectedResult eliminar(ExpectedResult expectedResult, User user)
	{
		ExpectedResult expectedR=null;
		try
		{			
			expectedResult.setExreStatus(false);
			expectedResult.setExreDateUpdate(new Date());
			expectedResult.setExreUserUpdate(user.getUserName());
			expectedR=edit(expectedResult);
			return expectedR;
		}
		catch(Exception ex)
		{
			return expectedR;
		}
	}
	
	/**
	 * Buscar resultados esperados relacionadas con objetivos especificos
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ExpectedResult> findExpectedResultSpecificGoal(Integer spgoId)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT e FROM ExpectedResult e where e.specificGoal.spgoId=:spgoId and e.exreStatus = true");
			query.setParameter("spgoId", spgoId);
			List<ExpectedResult> result=(List<ExpectedResult>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<ExpectedResult>();
		}
		return new ArrayList<ExpectedResult>();
	}
	
}
