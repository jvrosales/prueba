package ec.gob.ambiente.enlisy.actionplan.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.actionplan.model.Funder;
import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;

/**
 * Servicios para administracion de registro de Financiador
 * @author EXCO-PA
 *
 */
@Stateless
public class FunderFacade extends AbstractFacade<Funder, Integer>{

	public FunderFacade() {
		super(Funder.class, Integer.class);		
	}
	
	/**
	 * Buscar Financiadores
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Funder> findFunders()
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT f FROM Funder f where f.fundStatus = true");
			List<Funder> result=(List<Funder>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<Funder>();
		}
		return new ArrayList<Funder>();
	}
	
	/**
	 * Guardar Financiadores
	 * @param funder
	 * @param user
	 * @return
	 */
	public Funder save(Funder funder, User user)
	{
		Funder funderS=null;
		try
		{			
			if(funder.getFundId() == null)
			{				
				funder.setFundStatus(true);
				funder.setFundDateCreate(new Date());
				funder.setFundUserCreate(user.getUserName());
				funderS=create(funder);
			}else			{
				funder.setFundDateUpdate(new Date());
				funder.setFundUserUpdate(user.getUserName());
				funderS=edit(funder);
			}
			return funderS;
		}
		catch(Exception ex)
		{
			return funderS;
		}
	}
	
	public Funder eliminar(Funder funder, User user)
	{
		Funder funderE=null;
		try
		{			
			funder.setFundStatus(false);
			funder.setFundDateUpdate(new Date());
			funder.setFundUserUpdate(user.getUserName());
			funderE=edit(funder);
			return funderE;
		}
		catch(Exception ex)
		{
			return funderE;
		}
	}
	
}
