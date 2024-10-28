package ec.gob.ambiente.enlisy.speciescatalog.services;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.speciescatalog.model.IntroductionExoticAlienSpecie;




/**
 * Servicios para la administracion de introduccion especies exoticas
 * @author EXCO
 *
 */
@Stateless
public class IntroductionExoticAlienSpecieFacade extends AbstractFacade<IntroductionExoticAlienSpecie, Integer>{

	public IntroductionExoticAlienSpecieFacade() {
		super(IntroductionExoticAlienSpecie.class, Integer.class);		
	}

	/**
	 * Guardar introduccion especie exotica
	 * @param specieSummary
	 * @param user
	 * @return
	 */
	public IntroductionExoticAlienSpecie save(IntroductionExoticAlienSpecie introduction, User user)
	{
		IntroductionExoticAlienSpecie introductionReturn=null;
		try
		{			
			if(introduction.getIeasId()== null)
			{				
				
				introduction.setIeasStatus(true);
				introduction.setIeasDateCreate(new Date());
				introduction.setIeasUserCreate(user.getUserName());
				introductionReturn=create(introduction);
				
			}
			else
			{
				introduction.setIeasDateCreate(new Date());
				introduction.setIeasUserCreate(user.getUserName());
				introductionReturn=edit(introduction);
			}
					
			
			return introductionReturn;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return introductionReturn;
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<IntroductionExoticAlienSpecie> getIntroductionsBySpecie(Integer idSummary) {
		
		List<IntroductionExoticAlienSpecie> result=new ArrayList<IntroductionExoticAlienSpecie>();
		try
		{
			Query query = super.getEntityManager().createQuery("select o from IntroductionExoticAlienSpecie o where o.specie.sptaId = :elCodigo and o.ieasStatus= true");
			query.setParameter("elCodigo", idSummary);
			result=(List<IntroductionExoticAlienSpecie>)query.getResultList();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			
		}
		return result;
		}
	
	
	
	
}
