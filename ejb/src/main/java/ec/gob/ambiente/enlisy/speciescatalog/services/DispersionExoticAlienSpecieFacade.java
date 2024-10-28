package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.speciescatalog.model.DispersionExoticAlienSpecie;




/**
 * Servicios para la administracion de la
	dispersion de una especie exotica
 * @author EXCO
 *
 */
@Stateless
public class DispersionExoticAlienSpecieFacade extends AbstractFacade<DispersionExoticAlienSpecie, Integer>{

	public DispersionExoticAlienSpecieFacade() {
		super(DispersionExoticAlienSpecie.class, Integer.class);		
	}

	/**
	 * Guardar especie-dispersion
	 * @param DispersionExoticAlienSpecie
	 * @param user
	 * @return
	 */
	
	
	public DispersionExoticAlienSpecie save(DispersionExoticAlienSpecie specieDispersion, User user)
	{
		DispersionExoticAlienSpecie specieDispersionReturn=null;
		try
		{			
			if(specieDispersion.getDeasId() == null)
			{				
				
				specieDispersion.setDeasStatus(true);
				specieDispersion.setDeasDateCreate(new Date());
				specieDispersion.setDeas_user_create(user.getUserName());
				specieDispersionReturn=create(specieDispersion);
				
			}
			else
			{
				specieDispersion.setDeasDateUpdate(new Date());
				specieDispersion.setDeasUserUpdate(user.getUserName());
				specieDispersionReturn=edit(specieDispersion);
			}
					
			
			return specieDispersionReturn;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return specieDispersionReturn;
		}
	}
	
	/**
	 * Listar dispersion de una especie
	 * @param idSpecie
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<DispersionExoticAlienSpecie> getDispersionsBySumary(Integer idSummary) {
		
		List<DispersionExoticAlienSpecie> result=new ArrayList<DispersionExoticAlienSpecie>();
		try
		{
			Query query = super.getEntityManager().createQuery("select o from DispersionExoticAlienSpecie o where o.summaryExotic.seasId = :elCodigo and o.deasStatus = true");
			query.setParameter("elCodigo", idSummary);
			result=(List<DispersionExoticAlienSpecie>)query.getResultList();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return new ArrayList<DispersionExoticAlienSpecie>();
			
			
		}
		return result;
		}
	
	
	
		
	
}
