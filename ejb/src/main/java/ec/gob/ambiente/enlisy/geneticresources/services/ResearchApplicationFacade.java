package ec.gob.ambiente.enlisy.geneticresources.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.geneticresources.model.ResearchApplication;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class ResearchApplicationFacade extends AbstractFacade<ResearchApplication, Integer>{
	
	public ResearchApplicationFacade() {
		super(ResearchApplication.class, Integer.class);		
	}
		
	public Integer procedureNumberGenerated(String sequenceName) {
		Query q;
		q = getEntityManager()
				.createNativeQuery("select nextval('"+sequenceName.toLowerCase()+"');");
		java.math.BigInteger result= (java.math.BigInteger)q.getSingleResult();
		return Integer.valueOf(result.intValue());
	}
	
	/**
	 * Obtiene los datos de una solicitud buscados por código de trámite
	 * @param code Código de Trámite
	 * @return Application
	 */
	public ResearchApplication findByCode(String code)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ResearchApplication o where o.reapCode= :code and o.reapStatus = true");
			query.setParameter("code", code);
			
			return (ResearchApplication) query.getSingleResult();
			
		}catch(NoResultException e)
		{
			return null;
		}
	}
	
	/**
	 * Obtener todas las solicitudes por solicitante
	 * @param people
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ResearchApplication> findByUser(User user)
	{
		Query query = super.getEntityManager().createQuery("SELECT o FROM ResearchApplication o where o.user.userId = :userId and o.reapStatus = true");
		query.setParameter("userId", user.getUserId());
		
		
		return (List<ResearchApplication>)query.getResultList();
	}
	
	
	/**
	 * Guardar Solicitud de Investigacion
	 * @param solicitudInvestigacion
	 * @param tutor
	 * @param organizacion
	 * @param user
	 * @return
	 */
	public boolean save(ResearchApplication researchApplication, User user)
	{
		try
		{			
			if(researchApplication.getReapId() == null)
			{				
				
				researchApplication.setUser(user);
				researchApplication.setReapStatus(true);
				researchApplication.setReapDateCreate(new Date());
				researchApplication.setReapUserCreate(user.getUserName());
				create(researchApplication);
				
			}
			else
			{
				researchApplication.setReapDateUpdate(new Date());
				researchApplication.setUser(user);
				edit(researchApplication);
			}
			
			
			
			
			
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	public void delete(ResearchApplication researchApplication, User user){
		researchApplication.setReapStatus(false);
		researchApplication.setReapDateUpdate(new Date());
		researchApplication.setUser(user);
		edit(researchApplication);
	}

}