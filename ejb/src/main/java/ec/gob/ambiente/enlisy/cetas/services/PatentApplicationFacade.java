package ec.gob.ambiente.enlisy.cetas.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.cetas.model.PatentApplication;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class PatentApplicationFacade extends AbstractFacade<PatentApplication, Integer>{
	
	public PatentApplicationFacade() {
		super(PatentApplication.class, Integer.class);		
	}
		
	/**
	 * Obtiene los datos de una patente buscados por codigo de tramite
	 * @param code Codigo de Tramite
	 * @return Application
	 */
	@SuppressWarnings("unchecked")
	public PatentApplication findByCode(String code)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM PatentApplication o where o.paapCode= :code and o.paapStatus = true order by 1");
			query.setParameter("code", code);
			
			List<PatentApplication> resultList=query.getResultList();
			
			if(resultList!=null&&!resultList.isEmpty())
				return resultList.get(0);			 
			
		}catch(NoResultException e)
		{
			return null;
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public PatentApplication findByCode(String code,String status)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM PatentApplication o where o.paapCode= :code and o.paapStatusPatent=:status and o.paapStatus = true and o.paapIdPrevious is not null order by 1 desc");
			query.setParameter("code", code);
			query.setParameter("status", status);
			
			List<PatentApplication> resultList=query.getResultList();
			
			if(resultList!=null&&!resultList.isEmpty())
				return resultList.get(0);			 
			
		}catch(NoResultException e)
		{
			return null;
		}
		
		return null;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<PatentApplication> findByUser(String user)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM PatentApplication o where o.paapStatus = true  and o.paapUserCreate= :user order by 1 desc");
			query.setParameter("user", user);
			List<PatentApplication> result = (List<PatentApplication>) query.getResultList();
			if (result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<PatentApplication> findByUser(String user, String status)
	{
		try
		{
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM PatentApplication o where o.paapStatus = true and "
					+ " o.paapUserCreate= :user and o.paapStatusPatent is not null "
					+ " and o.paapStatusPatent = :status order by o.paapDateUpdate desc");
			query.setParameter("user", user);
			query.setParameter("status", status);
			List<PatentApplication> result = (List<PatentApplication>) query.getResultList();
			if (result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}
	
	
	
	/**
	 * Guardar Patente
	 * @param patentApplication
	 * @param user
	 * @return
	 */
	public boolean save(PatentApplication patentApplication, User user)
	{
		try
		{			
			if(patentApplication.getPaapId() == null)
			{	
				patentApplication.setPaapStatus(true);
				patentApplication.setPaapDateCreate(new Date());
				patentApplication.setPaapUserCreate(user.getUserName());
				create(patentApplication);
			}
			else
			{
				patentApplication.setPaapDateUpdate(new Date());
				patentApplication.setPaapUserUpdate(user.getUserName());
				edit(patentApplication);
			}
			
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	/**
	 * Eliminar Patente
	 * @param patentApplication
	 * @param user
	 * @return
	 */
	public void delete(PatentApplication patentApplication, User user){
		patentApplication.setPaapStatus(false);
		patentApplication.setPaapDateUpdate(new Date());
		patentApplication.setPaapUserUpdate(user.getUserName());
		edit(patentApplication);
	}
	

}