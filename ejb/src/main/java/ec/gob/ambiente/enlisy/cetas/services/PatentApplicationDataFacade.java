package ec.gob.ambiente.enlisy.cetas.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.cetas.model.CatalogWmc;
import ec.gob.ambiente.enlisy.cetas.model.PatentApplication;
import ec.gob.ambiente.enlisy.cetas.model.PatentApplicationData;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class PatentApplicationDataFacade extends AbstractFacade<PatentApplicationData, Integer>{
		

	public PatentApplicationDataFacade() {
		super(PatentApplicationData.class, Integer.class);		
	}
		
	/**
	 * Obtener Datos de la Propuesta de recoleccion por id
	 * @param id
	 * @return
	 */
	public PatentApplicationData findById(Integer id)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM PatentApplicationData o where o.paadId= :id and o.paadStatus = true");
			query.setParameter("id", id);
			
			return (PatentApplicationData) query.getSingleResult();
			
		}catch(NoResultException e)
		{
			return null;
		}
	}
	
	/**
	 * Obtener Datos Patente por id
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PatentApplicationData> findByPatentApplication(PatentApplication patentApplication)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM PatentApplicationData o where o.patentApplication.paapId= :id and o.paadStatus = true");
			query.setParameter("id", patentApplication.getPaapId());
			
			List<PatentApplicationData> result=(List<PatentApplicationData>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	
	/**
	 * Obtener Datos por Patente orden descendente
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PatentApplicationData> findByPatentApplicationOrden(PatentApplication patentApplication)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM PatentApplicationData o where o.patentApplication.paapId= :id and o.paadStatus = true order by 1 desc");
			query.setParameter("id", patentApplication.getPaapId());
			
			List<PatentApplicationData> result=(List<PatentApplicationData>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	
	
	/**
	 * Obtener Datos por Patente orden descendente
	 * @param id
	 * @return
	 */
	public PatentApplicationData findByPatentApplicationLast(PatentApplication patentApplication)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM PatentApplicationData o where o.patentApplication.paapId= :id and o.paadStatus = true order by 1 desc");
			query.setParameter("id", patentApplication.getPaapId());
			query.setMaxResults(1);  //esta función devuelve un resultado como el LIMIT
			
			return (PatentApplicationData) query.getSingleResult();
			
		}catch(NoResultException e)
		{
			return null;
		}
		
	}
	
	
	/**
	 * Obtener Datos por Patente y tipo de información orden descendente
	 * @param id
	 * @return
	 */
	public PatentApplicationData findByPatentAndTypeLast(PatentApplication patentApplication, CatalogWmc typeInformation)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM PatentApplicationData o where o.patentApplication.paapId= :id and o.typeInformation.cwmcId= :idCatalog and o.paadStatus = true order by 1 desc");
			query.setParameter("id", patentApplication.getPaapId());
			query.setParameter("idCatalog", typeInformation.getCwmcId());
			query.setMaxResults(1);  //esta función devuelve un resultado como el LIMIT
			
			return (PatentApplicationData) query.getSingleResult();
			
		}catch(NoResultException e)
		{
			return null;
		}
		
	}
	
	
	
	
	
	/**
	 * Guardar Datos Patente
	 * @param PatentApplicationData
	 * @param user
	 * @return
	 */
	public boolean save(PatentApplicationData PatentApplicationData, User user)
	{
		try
		{			
			if(PatentApplicationData.getPaadId() == null)
			{	
				
				PatentApplicationData.setPaadStatus(true);
				PatentApplicationData.setPaadDateCreate(new Date());
				PatentApplicationData.setPaadUserCreate(user.getUserName());
				create(PatentApplicationData);
				
			}
			else
			{
				PatentApplicationData.setPaadDateUpdate(new Date());	
				PatentApplicationData.setPaadUserUpdate(user.getUserName());
				edit(PatentApplicationData);
			}
			
			
			
			
			
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	public void delete(PatentApplicationData PatentApplicationData, User user){
		PatentApplicationData.setPaadStatus(false);
		PatentApplicationData.setPaadDateUpdate(new Date());	
		PatentApplicationData.setPaadUserUpdate(user.getUserName());
		edit(PatentApplicationData);
	}

}