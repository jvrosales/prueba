package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecieApplication;


/**
 * Servicios para administracion de la solicitud de registro de taxon
 * @author EXCO
 *
 */
@Stateless
public class SpecieApplicationFacade extends AbstractFacade<SpecieApplication, Integer>{

	public SpecieApplicationFacade() {
		super(SpecieApplication.class, Integer.class);		
	}
	
	/**
	 * Guardar Solicitud de Taxon
	 * @param solicitudTaxon
	 * @param usuario
	 * @return
	 */
	public SpecieApplication save(SpecieApplication specieApplication, User user)
	{
		SpecieApplication application=null;
		try
		{			
			if(specieApplication.getSappId() == null)
			{				
				
				specieApplication.setSappStatus(true);
				specieApplication.setSappDateCreate(new Date());
				specieApplication.setSappUserCreate(user.getUserName());
				application=create(specieApplication);
				
			}
			else
			{
				specieApplication.setSappDateUpdate(new Date());
				specieApplication.setSappUserUpdate(user.getUserName());
				application=edit(specieApplication);
			}
					
			
			return application;
		}
		catch(Exception ex)
		{
			return application;
		}
	}
	
	/**
	 * Recuperar Solicitud de Especialista por numero Tramite
	 * @param codigo del tramite
	 * @return
	 */
	public SpecieApplication findByCode(String code)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM SpecieApplication o where o.sappCode= :codigo and o.sappStatus = true");
			query.setParameter("codigo", code);
			
			
			SpecieApplication result=(SpecieApplication)query.getSingleResult();
			if(result!=null)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
}
