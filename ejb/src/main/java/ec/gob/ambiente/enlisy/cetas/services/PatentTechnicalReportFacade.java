package ec.gob.ambiente.enlisy.cetas.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.cetas.model.CatalogWmc;
import ec.gob.ambiente.enlisy.cetas.model.PatentApplication;
import ec.gob.ambiente.enlisy.cetas.model.PatentTechnicalReport;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class PatentTechnicalReportFacade extends AbstractFacade<PatentTechnicalReport, Integer>{
		

	public PatentTechnicalReportFacade() {
		super(PatentTechnicalReport.class, Integer.class);		
	}
	
	
	/**
	 * Obtener Datos del informe tecnico por id
	 * @param id
	 * @return
	 */
	public PatentTechnicalReport findById(Integer id)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM PatentTechnicalReport o where o.patrId= :id and o.patrStatus = true");
			query.setParameter("id", id);
			
			return (PatentTechnicalReport) query.getSingleResult();
			
		}catch(NoResultException e)
		{
			return null;
		}
	}
	
	/**
	 * Obtener Datos del informe tecnico por Patente
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PatentTechnicalReport> findByPatentApplication(PatentApplication patentApplication)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM PatentTechnicalReport o where o.patentApplication.paapId= :id and o.patrStatus = true");
			query.setParameter("id", patentApplication.getPaapId());
			
			List<PatentTechnicalReport> result=(List<PatentTechnicalReport>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	
	
	/**
	 * Obtener Datos del informe tecnico por Patente orden descendente
	 * @param id
	 * @return
	 */
	/*
	@SuppressWarnings("unchecked")
	public List<PatentTechnicalReport> findByPatentApplicationOrden(PatentApplication patentApplication)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM PatentTechnicalReport o where o.patentApplication.paapId= :id and o.patrStatus = true order by 1 desc");
			query.setParameter("id", patentApplication.getPaapId());
			
			List<PatentTechnicalReport> result=(List<PatentTechnicalReport>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	*/
	
	
	
	/**
	 * Obtener Datos por Patente y por tipo de informe
	 * @param id
	 * @return
	 */
	
	public PatentTechnicalReport findByPatentApplication(PatentApplication patentApplication, CatalogWmc typeTechnicalRepor)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM PatentTechnicalReport o where o.patentApplication.paapId= :id and o.typeTechnicalRepor.cwmcId = :idCatalog and o.patrStatus = true order by 1 desc");
			query.setParameter("id", patentApplication.getPaapId());
			query.setParameter("idCatalog", typeTechnicalRepor.getCwmcId());
			query.setMaxResults(1);  //esta funcion devuelve un resultado como el LIMIT
			
			return (PatentTechnicalReport) query.getSingleResult();
			
		}catch(NoResultException e)
		{
			return null;
		}
		
	}
	
	
	
	
	/**
	 * Guardar Datos Patente
	 * @param PatentTechnicalReport
	 * @param user
	 * @return
	 */
	public boolean save(PatentTechnicalReport PatentTechnicalReport, User user)
	{
		try
		{			
			if(PatentTechnicalReport.getPatrId() == null)
			{	
				
				PatentTechnicalReport.setPatrStatus(true);
				PatentTechnicalReport.setPatrDateCreate(new Date());
				PatentTechnicalReport.setPatrUserCreate(user.getUserName());
				create(PatentTechnicalReport);
				
			}
			else
			{
				PatentTechnicalReport.setPatrDateUpdate(new Date());	
				PatentTechnicalReport.setPatrUserUpdate(user.getUserName());
				edit(PatentTechnicalReport);
			}
			
			
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	public void delete(PatentTechnicalReport PatentTechnicalReport, User user){
		PatentTechnicalReport.setPatrStatus(false);
		PatentTechnicalReport.setPatrDateUpdate(new Date());	
		PatentTechnicalReport.setPatrUserUpdate(user.getUserName());
		edit(PatentTechnicalReport);
	}

}