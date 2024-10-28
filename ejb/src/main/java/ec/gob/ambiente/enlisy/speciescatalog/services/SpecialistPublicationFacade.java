package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecialistApplication;
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecialistPublication;

/**
 * Servicios para la administracion de las publicaciones del especialista
 * @author EXCO
 *
 */
@Stateless
public class SpecialistPublicationFacade extends AbstractFacade<SpecialistPublication, Integer>{

	public SpecialistPublicationFacade() {
		super(SpecialistPublication.class, Integer.class);		
	}
	
	/**
	 * Buscar las publicaciones de los ultimos 2 anios asociadas a un especialista
	 * @param specialist
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<SpecialistPublication> findBySpecialist(SpecialistApplication specialist)
	{
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.YEAR, -2);
		int anio=calendar.get(Calendar.YEAR);
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM SpecialistPublication o where o.spapSpecialist.spapId= :id and o.sppuStatus = true and o.sppuYear >= :anio");
			query.setParameter("id", specialist.getSpapId());
			query.setParameter("anio", anio);
			
			List<SpecialistPublication> result=(List<SpecialistPublication>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	/**
	 * Buscar las publicaciones anteriores a 2 anios asociadas a un especialista
	 * @param specialist
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<SpecialistPublication> findPreviousBySpecialist(SpecialistApplication specialist)
	{
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.YEAR, -2);
		int anio=calendar.get(Calendar.YEAR);
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM SpecialistPublication o where o.spapSpecialist.spapId= :id and o.sppuStatus = true and o.sppuYear < :anio");
			query.setParameter("id", specialist.getSpapId());
			query.setParameter("anio", anio);
			
			List<SpecialistPublication> result=(List<SpecialistPublication>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	/**
	 * Guardar Publicacion
	 * @param solicitudEspecialista
	 * @param usuario
	 * @return
	 */
	public SpecialistPublication save(SpecialistPublication specialistPublication, User user)
	{
		SpecialistPublication publicacion=null;
		try
		{			
			if(specialistPublication.getSppuId() == null)
			{				
				
				specialistPublication.setSppuStatus(true);
				specialistPublication.setSppuDateCreate(new Date());
				specialistPublication.setSppuUserCreate(user.getUserName());
				publicacion=create(specialistPublication);
				
			}
			else
			{
				specialistPublication.setSppuDateUpdate(new Date());
				specialistPublication.setSppuUserUpdate(user.getUserName());
				publicacion=edit(specialistPublication);
			}
					
			
			return publicacion;
		}
		catch(Exception ex)
		{
			return publicacion;
		}
	}
	
	/**
	 * Eliminar publicacion
	 * @param specialistPublication
	 * @param user
	 */
	public void delete(SpecialistPublication specialistPublication, User user){
		specialistPublication.setSppuStatus(false);
		specialistPublication.setSppuDateUpdate(new Date());	
		specialistPublication.setSppuUserUpdate(user.getUserName());
		edit(specialistPublication);
	}
	
	/**
	 * Eliminar publicaciones de un especialista
	 * @param specialistApplication
	 * @param usuario
	 * @return
	 */
	public void deleteBySpecialist(SpecialistApplication specialistApplication, User user)
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("update SpecialistPublication o set o.sppuStatus = false, o.sppuDateUpdate = :laFecha , o.sppuUserUpdate = :elUsuario  where o.spapSpecialist.spapId = :codigoEspecialista");
			query.setParameter("laFecha", new Date());
			query.setParameter("elUsuario", user.getUserName());
			query.setParameter("codigoEspecialista", specialistApplication.getSpapId());
			query.executeUpdate();
							
			
		}catch(NoResultException e)
		{
			
		}
	}
	
	
	/**
	 * Eliminado fisico publicaciones de un especialista
	 * @param specialistApplication
	 * @return
	 */
	public void physicalDeleteBySpecialist(SpecialistApplication specialistApplication)
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("delete from SpecialistPublication o  where o.spapSpecialist.spapId = :codigoEspecialista and o.sppuStatus = true");
			query.setParameter("codigoEspecialista", specialistApplication.getSpapId());
			query.executeUpdate();
							
			
		}catch(NoResultException e)
		{
			
		}
	}
}
