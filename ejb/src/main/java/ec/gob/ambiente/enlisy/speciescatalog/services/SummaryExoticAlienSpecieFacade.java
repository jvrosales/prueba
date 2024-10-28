package ec.gob.ambiente.enlisy.speciescatalog.services;


import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.speciescatalog.model.SummaryExoticAlienSpecie;



/**
 * Servicios para la administracion de sumario de especie exoticas y/o invasoras
 * @author EXCO
 *
 */
@Stateless
public class SummaryExoticAlienSpecieFacade extends AbstractFacade<SummaryExoticAlienSpecie, Integer>{

	public SummaryExoticAlienSpecieFacade() {
		super(SummaryExoticAlienSpecie.class, Integer.class);		
	}

	/**
	 * Guardar sumario especie exotica
	 * @param specieSummary
	 * @param user
	 * @return
	 */
	public SummaryExoticAlienSpecie save(SummaryExoticAlienSpecie summaryExotic, User user)
	{
		SummaryExoticAlienSpecie summaryExoticReturn=null;
		try
		{			
			if(summaryExotic.getSeasId()== null)
			{				
				
				summaryExotic.setSeasStatus(true);
				summaryExotic.setSeasDateCreate(new Date());
				summaryExotic.setSeasUserCreate(user.getUserName());
				summaryExoticReturn=create(summaryExotic);
				
			}
			else
			{
				summaryExotic.setSeasDateUpdate(new Date());
				summaryExotic.setSeasUserUpdate(user.getUserName());
				summaryExoticReturn=edit(summaryExotic);
			}
					
			
			return summaryExoticReturn;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return summaryExoticReturn;
		}
	}
	
	/**
	 * Obtener sumario por id de sumario general de la especie
	 * @param idSummaryGeneral
	 * @return
	 */
	
	public SummaryExoticAlienSpecie getSummaryBySpecie(Integer idSummaryGeneral) {
		
		SummaryExoticAlienSpecie result=new SummaryExoticAlienSpecie();
		try
		{
			Query query = super.getEntityManager().createQuery("select o from SummaryExoticAlienSpecie o where o.specie.sptaId = :elCodigo and o.seasStatus = true");
			query.setParameter("elCodigo", idSummaryGeneral);
			result=(SummaryExoticAlienSpecie)query.getSingleResult();
			
		}
		catch(Exception ex)
		{
			return new SummaryExoticAlienSpecie();
			
		}
		return result;
		}
	
	
	
	
}
