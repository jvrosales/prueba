package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.speciescatalog.model.ReproductionExoticAlienSpecie;




/**
 * Servicios para la administracion de la
	dispersion de una especie exotica
 * @author EXCO
 *
 */
@Stateless
public class ReproductionExoticAlienSpecieFacade extends AbstractFacade<ReproductionExoticAlienSpecie, Integer>{

	public ReproductionExoticAlienSpecieFacade() {
		super(ReproductionExoticAlienSpecie.class, Integer.class);		
	}

	/**
	 * Guardar especie-reproduccion
	 * 	 * @param ReproductionExoticAlienSpecie
	 * @param user
	 * @return
	 */
	
	
	public ReproductionExoticAlienSpecie save(ReproductionExoticAlienSpecie reproduction, User user)
	{
		ReproductionExoticAlienSpecie reproductionReturn=null;
		try
		{			
			if(reproduction.getReasId() == null)
			{				
				
				reproduction.setReasStatus(true);
				reproduction.setReasDateCreate(new Date());
				reproduction.setReasUserCreate(user.getUserName());
				reproductionReturn=create(reproduction);
				
			}
			else
			{
				reproduction.setReasDateUpdate(new Date());
				reproduction.setReasUserUpdate(user.getUserName());
				reproductionReturn=edit(reproduction);
			}
					
			
			return reproductionReturn;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return reproductionReturn;
		}
	}
	
	/**
	 * Listar reproduccion de una especie
	 * @param idSpecie
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<ReproductionExoticAlienSpecie> getReproductionBySumary(Integer idSummary) {
		
		List<ReproductionExoticAlienSpecie> result=new ArrayList<ReproductionExoticAlienSpecie>();
		try
		{
			Query query = super.getEntityManager().createQuery("select o from ReproductionExoticAlienSpecie o where o.summaryExotic.seasId = :elCodigo and o.reasStatus = true");
			query.setParameter("elCodigo", idSummary);
			result=(List<ReproductionExoticAlienSpecie>)query.getResultList();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return new ArrayList<ReproductionExoticAlienSpecie>();
			
			
		}
		return result;
		}
	
	
	
		
	
}
