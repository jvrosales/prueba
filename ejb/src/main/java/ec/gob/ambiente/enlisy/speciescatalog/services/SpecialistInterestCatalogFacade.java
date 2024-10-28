package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecialistInterestCatalog;

/**
 * Servicios para la administracion de los intereses del especialista
 * @author EXCO
 *
 */
@Stateless
public class SpecialistInterestCatalogFacade extends AbstractFacade<SpecialistInterestCatalog, Integer>{

	public SpecialistInterestCatalogFacade() {
		super(SpecialistInterestCatalog.class, Integer.class);		
	}
	
		
	/**
	 * Recuperar catalogo de intereses
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<SpecialistInterestCatalog> findTInterestsCatalog()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM SpecialistInterestCatalog o where o.spinStatus = true order by o.spinOrder");
			List<SpecialistInterestCatalog> result=(List<SpecialistInterestCatalog>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<SpecialistInterestCatalog>();
		}
		return new ArrayList<SpecialistInterestCatalog>();
	}
	
	
}
