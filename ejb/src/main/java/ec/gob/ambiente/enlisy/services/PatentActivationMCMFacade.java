package ec.gob.ambiente.enlisy.services;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.PatentActivationMCM;
import ec.gob.ambiente.enlisy.model.User;


@Stateless
public class PatentActivationMCMFacade extends AbstractFacade<PatentActivationMCM, Integer> implements Serializable {
	
	private static final long serialVersionUID = -4594424897085245484L;

	public PatentActivationMCMFacade() {
		super(PatentActivationMCM.class, Integer.class);		
	}
	
	/**
	 * Actualizar datos de perfil de usuario
	 * @param user
	 */

	public PatentActivationMCM findByStatus(Boolean status)
	{
		TypedQuery<PatentActivationMCM> query = super.getEntityManager().createQuery("select u from PatentActivationMCM u "
				+ "where u.status = :status", PatentActivationMCM.class);
		query.setParameter("status", status);
		
		PatentActivationMCM patentActivationMCM = new PatentActivationMCM();
		if(query.getResultList().size() > 0)
		{
			patentActivationMCM = query.getResultList().get(0);
		}
		return patentActivationMCM;
	}
	
	/**
	 * insertar y actualizar PatentActivationMCM
	 * @param user
	 */
	public boolean saveUpdate(PatentActivationMCM patentActivationMCM, User user ) {
		try
		{			
			
			if(patentActivationMCM.getId() == null)
			{
				patentActivationMCM.setDateCreate(new Date());
				patentActivationMCM.setUserCreate(user.getUserName());
				create(patentActivationMCM);
			}
			else
			{
				patentActivationMCM.setDateUpdate(new Date());
				patentActivationMCM.setUserUpdate(user.getUserName());
				edit(patentActivationMCM);
			}
			
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}

	
	

}