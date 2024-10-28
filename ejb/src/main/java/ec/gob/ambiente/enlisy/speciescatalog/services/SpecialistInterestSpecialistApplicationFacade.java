package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecialistApplication;
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecialistInterestSpecialistApplication;


/**
 * Servicios para la administracion de las publicaciones del especialista
 * @author EXCO
 *
 */
@Stateless
public class SpecialistInterestSpecialistApplicationFacade extends AbstractFacade<SpecialistInterestSpecialistApplication, Integer>{

	public SpecialistInterestSpecialistApplicationFacade() {
		super(SpecialistInterestSpecialistApplication.class, Integer.class);		
	}
	
		
	/**
	 * Recuperar lista de intereses seleccionados de un especialista
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<Integer> findInterestsBySpecialist(Integer idSpecialist)
	{
		List<Integer> result=new ArrayList<Integer>();
		try
		{
		Query q;
		q = getEntityManager()
				.createNativeQuery("select spin_id from biodiversity.specialist_interests_specialist_applications where sisa_status=true and sisa_selected=true and spap_id = ?1");
		q.setParameter(1, idSpecialist);
		result= (List<Integer>)q.getResultList();
		if(q.getResultList()==null)
		{
			result=new ArrayList<Integer>();
		}
		}
		catch(Exception ex)
		{
			
		}
		return result;
	}
	
	/**
	 * Eliminar intereses del especialista
	 * @param specialistApplication
	 * @param user
	 */
	public void deleteBySpecialist(SpecialistApplication specialistApplication, User user)
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("update SpecialistInterestSpecialistApplication o set o.sisaStatus = false, o.sisaDateUpdate = :laFecha , o.sisaUserUpdate = :elUsuario  where o.spapSpecialist.spapId = :codigoEspecialista");
			query.setParameter("laFecha", new Date());
			query.setParameter("elUsuario", user.getUserName());
			query.setParameter("codigoEspecialista", specialistApplication.getSpapId());
			query.executeUpdate();
							
			
		}catch(NoResultException e)
		{
			
		}
	}
	
	/**
	 * Guardar interes especialista
	 * @param specialistInterest
	 * @param user
	 * @return
	 */
	public SpecialistInterestSpecialistApplication save(SpecialistInterestSpecialistApplication specialistInterest, User user)
	{
		SpecialistInterestSpecialistApplication interes=null;
		try
		{			
			if(specialistInterest.getSisaId() == null)
			{				
				
				specialistInterest.setSisaStatus(true);
				specialistInterest.setSisaDateCreate(new Date());
				specialistInterest.setSisaUserUpdate(user.getUserName());
				interes=create(specialistInterest);
				
			}
			else
			{
				specialistInterest.setSisaDateUpdate(new Date());
				specialistInterest.setSisaUserUpdate(user.getUserName());
				interes=edit(specialistInterest);
			}
					
			
			return interes;
		}
		catch(Exception ex)
		{
			return interes;
		}
	}
	
	/**
	 * Buscar el catalogo de intereses de un especialista
	 * @param idSpecialist
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SpecialistInterestSpecialistApplication> findAllInterestsBySpecialist(Integer idSpecialist)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM SpecialistInterestSpecialistApplication o where o.sisaStatus = true and o.spapSpecialist.spapId = :codigoEspecialista");
			query.setParameter("codigoEspecialista", idSpecialist);
			List<SpecialistInterestSpecialistApplication> result=(List<SpecialistInterestSpecialistApplication>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<SpecialistInterestSpecialistApplication>();
		}
		return new ArrayList<SpecialistInterestSpecialistApplication>();
	}
	
	/**
	 * Buscar rol por interes del especialista
	 * @param interes
	 * @return
	 */

	public Integer findRoleByInterest(String interes)
	{
		Integer result=null;
		try
		{
		Query q;
		q = getEntityManager()
				.createNativeQuery("select role_id from suia_iii.roles where role_status=true and role_name = ?1");
		q.setParameter(1, interes);
		result= (Integer)q.getSingleResult();
		
		}
		catch(Exception ex)
		{
			
		}
		return result;
	}
	
	/**
	 * Buscar rol del usuario especialista
	 * @param idRole
	 * @param idUser
	 * @return
	 */
	public Integer findRoleUser(Integer idRole,Integer idUser)
	{
		Integer result=null;
		try
		{
		Query q;
		q = getEntityManager()
				.createNativeQuery("select role_id from suia_iii.roles_users where rous_status=true and role_id = ?1 and user_id= ?2");
		q.setParameter(1, idRole);
		q.setParameter(2, idUser);
		result= (Integer)q.getSingleResult();
		
		}
		catch(Exception ex)
		{
			
		}
		return result;
	}
	
	public void updateInterestSpecialist(Integer idRole, Integer idUser)
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("update RolesUser o set o.rousStatus = false  where o.role.roleId = :idRole and o.user.userId = :idUser");
			query.setParameter("idRole", idRole);
			query.setParameter("idUser", idUser);
			query.executeUpdate();
							
			
		}catch(NoResultException e)
		{
			
		}
	}
	
	/**
	 * Eliminar intereses del especialista
	 * @param specialistApplication
	 * @param user
	 */
	public void physicalDeleteBySpecialist(SpecialistApplication specialistApplication)
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("delete from SpecialistInterestSpecialistApplication o  where o.spapSpecialist.spapId = :codigoEspecialista and o.sisaStatus=true");
			query.setParameter("codigoEspecialista", specialistApplication.getSpapId());
			query.executeUpdate();
							
			
		}catch(NoResultException e)
		{
			
		}
	}
	
}
