package ec.gob.ambiente.enlisy.speciescatalog.services;


import java.util.Date;


import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecieSummary;



/**
 * Servicios para la administracion de sumario
 * @author EXCO
 *
 */
@Stateless
public class SpecieSummaryFacade extends AbstractFacade<SpecieSummary, Integer>{

	public SpecieSummaryFacade() {
		super(SpecieSummary.class, Integer.class);		
	}

	/**
	 * Guardar sumario especie
	 * @param specieSummary
	 * @param user
	 * @return
	 */
	public SpecieSummary save(SpecieSummary specieSummary, User user)
	{
		SpecieSummary specieSummaryReturn=null;
		try
		{			
			if(specieSummary.getSpsuId() == null)
			{				
				
				specieSummary.setSpsuStatus(true);
				specieSummary.setSpsuDateCreate(new Date());
				specieSummary.setSpsuUserCreate(user.getUserName());
				specieSummaryReturn=create(specieSummary);
				
			}
			else
			{
				specieSummary.setSpsuDateUpdate(new Date());
				specieSummary.setSpsuUserUpdate(user.getUserName());
				specieSummaryReturn=edit(specieSummary);
			}
					
			
			return specieSummaryReturn;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return specieSummaryReturn;
		}
	}
	
	/**
	 * Obtener sumario por id de especie
	 * @param idSpecie
	 * @return
	 */
	
	public SpecieSummary getSummaryBySpecie(Integer idSpecie) {
		
		SpecieSummary result=new SpecieSummary();
		try
		{
			Query query = super.getEntityManager().createQuery("select o from SpecieSummary o where o.specie.sptaId = :elCodigo and o.spsuStatus = true");
			query.setParameter("elCodigo", idSpecie);
			query.setMaxResults(1);
			result=(SpecieSummary)query.getSingleResult();
			
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return new SpecieSummary();			
		}
		return result;
		}
	
	
	
	
}
