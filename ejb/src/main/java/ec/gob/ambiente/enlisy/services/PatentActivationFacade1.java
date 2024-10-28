package ec.gob.ambiente.enlisy.services;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.PatentActivationMCM;

@Stateless
public class PatentActivationFacade1 extends AbstractFacade<PatentActivationMCM, Integer> implements Serializable {
	
	private static final long serialVersionUID = -4594424897085245484L;

	public PatentActivationFacade1() {
		super(PatentActivationMCM.class, Integer.class);		
	}
	
	/**
	 * Actualizar datos de perfil de usuario
	 * @param user
	 */

	public PatentActivationMCM findByPreliminary(String preliminary)
	{
		TypedQuery<PatentActivationMCM> query = super.getEntityManager().createQuery("select u from PatentActivation u "
				+ "where u.preliminary = :preliminary ", PatentActivationMCM.class);
		query.setParameter("preliminary", preliminary);
		
		PatentActivationMCM PatentActivation = new PatentActivationMCM();
		if(query.getResultList().size() > 0)
		{
			PatentActivation = query.getResultList().get(0);
		}
		return PatentActivation;
	}
	


}