package ec.gob.ambiente.enlisy.speciescatalog.services;


import java.util.Date;


import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecieForestalSummary;




/**
 * Servicios para la administracion de sumario forestal
 * @author EXCO
 *
 */
@Stateless
public class SpecieForestalSummaryFacade extends AbstractFacade<SpecieForestalSummary, Integer>{

	public SpecieForestalSummaryFacade() {
		super(SpecieForestalSummary.class, Integer.class);		
	}

	/**
	 * Guardar sumario especie
	 * @param specieSummary
	 * @param user
	 * @return
	 */
	public SpecieForestalSummary save(SpecieForestalSummary specieSummary, User user)
	{
		SpecieForestalSummary specieSummaryReturn=null;
		try
		{			
			if(specieSummary.getSpfsId() == null)
			{				
				
				specieSummary.setSpfsStatus(true);
				specieSummary.setSpfsDateCreate(new Date());
				specieSummary.setSpfsUserCreate(user.getUserName());
				specieSummaryReturn=create(specieSummary);
				
			}
			else
			{
				specieSummary.setSpfsDateUpdate(new Date());
				specieSummary.setSpfsUserUpdate(user.getUserName());
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
	
	public SpecieForestalSummary getSummaryBySpecie(Integer idSpecie) {
		
		SpecieForestalSummary result=new SpecieForestalSummary();
		try
		{
			Query query = super.getEntityManager().createQuery("select o from SpecieForestalSummary o where o.specie.sptaId = :elCodigo and o.spfsStatus = true");
			query.setParameter("elCodigo", idSpecie);
			query.setMaxResults(1);
			result=(SpecieForestalSummary)query.getSingleResult();						
						
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return new SpecieForestalSummary();
			
		}
		return result;
		}
	
	/**
	 * Buscar sumario forestal por sumario general
	 * @param idSummary
	 * @return
	 */
	public SpecieForestalSummary getSummaryForestalBySummaryGeneral(Integer idSummary) {
		
		SpecieForestalSummary result=new SpecieForestalSummary();
		try
		{
			Query query = super.getEntityManager().createQuery("select o from SpecieForestalSummary o where o.summary.spsuId = :elCodigo and o.spfsStatus = true");
			query.setParameter("elCodigo", idSummary);
			query.setMaxResults(1);
			result=(SpecieForestalSummary)query.getSingleResult();						
						
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return new SpecieForestalSummary();
			
		}
		return result;
		}
	
	
	
}
