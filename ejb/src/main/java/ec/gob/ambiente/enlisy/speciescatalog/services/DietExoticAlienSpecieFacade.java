package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.speciescatalog.model.DietExoticAlienSpecie;




/**
 * Servicios para la administracion de la
	dieta de una especie exotica
 * @author EXCO
 *
 */
@Stateless
public class DietExoticAlienSpecieFacade extends AbstractFacade<DietExoticAlienSpecie, Integer>{

	public DietExoticAlienSpecieFacade() {
		super(DietExoticAlienSpecie.class, Integer.class);		
	}

	/**
	 * Guardar especie-dieta
	 * @param DietExoticAlienSpecie
	 * @param user
	 * @return
	 */
	
	
	public DietExoticAlienSpecie save(DietExoticAlienSpecie specieDiet, User user)
	{
		DietExoticAlienSpecie specieDietReturn=null;
		try
		{			
			if(specieDiet.getDieaId() == null)
			{				
				
				specieDiet.setDieaStatus(true);
				specieDiet.setDieaDateCreate(new Date());
				specieDiet.setDieaUserCreate(user.getUserName());
				specieDietReturn=create(specieDiet);
				
			}
			else
			{
				specieDiet.setDieaDateUpdate(new Date());
				specieDiet.setDieaUserUpdate(user.getUserName());
				specieDietReturn=edit(specieDiet);
			}
					
			
			return specieDietReturn;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return specieDietReturn;
		}
	}
	
	/**
	 * Listar dispersion de una especie
	 * @param idSpecie
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<DietExoticAlienSpecie> getDietsBySumary(Integer idSummary) {
		
		List<DietExoticAlienSpecie> result=new ArrayList<DietExoticAlienSpecie>();
		try
		{
			Query query = super.getEntityManager().createQuery("select o from DietExoticAlienSpecie o where o.summaryExotic.seasId = :elCodigo and o.dieaStatus = true");
			query.setParameter("elCodigo", idSummary);
			result=(List<DietExoticAlienSpecie>)query.getResultList();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return new ArrayList<DietExoticAlienSpecie>();
			
			
		}
		return result;
		}
	
	
	
		
	
}
