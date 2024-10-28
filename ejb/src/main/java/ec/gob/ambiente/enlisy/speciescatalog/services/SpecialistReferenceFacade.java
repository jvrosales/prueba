package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecialistApplication;
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecialistReference;

/**
 * Servicios para la administracion de las referencias del especialista
 * @author EXCO
 *
 */
@Stateless
public class SpecialistReferenceFacade extends AbstractFacade<SpecialistReference, Integer>{

	public SpecialistReferenceFacade() {
		super(SpecialistReference.class, Integer.class);		
	}
	
	/**
	 * Buscar las referencias profesionales asociadas a un especialista
	 * @param specialist
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<SpecialistReference> findBySpecialist(SpecialistApplication specialist)
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM SpecialistReference o where o.spapSpecialist.spapId= :id and o.spreStatus = true and o.spreType= :tipo");
			query.setParameter("id", specialist.getSpapId());
			query.setParameter("tipo", "PR");
			
			List<SpecialistReference> result=(List<SpecialistReference>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	/**
	 * Buscar las referencias personales asociadas a un especialista
	 * @param specialist
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<SpecialistReference> findPersonalBySpecialist(SpecialistApplication specialist)
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM SpecialistReference o where o.spapSpecialist.spapId= :id and o.spreStatus = true and o.spreType= :tipo");
			query.setParameter("id", specialist.getSpapId());
			query.setParameter("tipo", "PE");
			
			List<SpecialistReference> result=(List<SpecialistReference>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	/**
	 * Guardar Referencia
	 * @param specialistReference
	 * @param usuario
	 * @return
	 */
	public SpecialistReference save(SpecialistReference specialistReference, User user)
	{
		SpecialistReference referencia=null;
		try
		{			
			if(specialistReference.getSpreId()== null)
			{				
				
				specialistReference.setSpreStatus(true);
				specialistReference.setSpreDateCreate(new Date());
				specialistReference.setSpreUserCreate(user.getUserName());
				referencia=create(specialistReference);
				
			}
			else
			{
				specialistReference.setSpreDateUpdate(new Date());
				specialistReference.setSpreUserUpdate(user.getUserName());
				referencia=edit(specialistReference);
			}
					
			
			return referencia;
		}
		catch(Exception ex)
		{
			return referencia;
		}
	}
	
	/**
	 * Eliminar referencia
	 * @param specialistReference
	 * @param user
	 */
	public void delete(SpecialistReference specialistReference, User user){
		specialistReference.setSpreStatus(false);
		specialistReference.setSpreDateUpdate(new Date());	
		specialistReference.setSpreUserUpdate(user.getUserName());
		edit(specialistReference);
	}
	
	/**
	 * Eliminar referencias de un especialista
	 * @param specialistApplication
	 * @param usuario
	 * @return
	 */
	public void deleteBySpecialist(SpecialistApplication specialistApplication, User user)
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("update SpecialistReference o set o.spreStatus = false, o.spreDateUpdate = :laFecha , o.spreUserUpdate = :elUsuario  where o.spapSpecialist.spapId = :codigoEspecialista and o.spreStatus = true");
			query.setParameter("codigoEspecialista", specialistApplication.getSpapId());
			query.executeUpdate();
							
			
		}catch(NoResultException e)
		{
			
		}
	}
	
	/**
	 * Eliminado fisico referencias de un especialista
	 * @param specialistApplication
	  * @return
	 */
	public void physicalDeleteBySpecialist(SpecialistApplication specialistApplication)
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("delete from SpecialistReference o  where o.spapSpecialist.spapId = :codigoEspecialista");
			query.setParameter("codigoEspecialista", specialistApplication.getSpapId());
			query.executeUpdate();
							
			
		}catch(NoResultException e)
		{
			
		}
	}
	
	
}
