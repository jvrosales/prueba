package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.speciescatalog.model.UseExoticAlienSpecie;





/**
 * Servicios para la administracion de los
	usos de una especie exotica
 * @author EXCO
 *
 */
@Stateless
public class UseExoticAlienSpecieFacade extends AbstractFacade<UseExoticAlienSpecie, Integer>{

	public UseExoticAlienSpecieFacade() {
		super(UseExoticAlienSpecie.class, Integer.class);		
	}

	/**
	 * Guardar especie-use
	 * @param UseExoticAlienSpecie
	 * @param user
	 * @return
	 */
	
	
	public UseExoticAlienSpecie save(UseExoticAlienSpecie useEspecie, User user)
	{
		UseExoticAlienSpecie useEspecieReturn=null;
		try
		{			
			if(useEspecie.getUeasId() == null)
			{				
				
				useEspecie.setUeasStatus(true);
				useEspecie.setUeasDateCreate(new Date());
				useEspecie.setUeasUserCreate(user.getUserName());
				useEspecieReturn=create(useEspecie);
				
			}
			else
			{
				useEspecie.setUeasDateUpdate(new Date());
				useEspecie.setUeasUserUpdate(user.getUserName());
				useEspecieReturn=edit(useEspecie);
			}
					
			
			return useEspecieReturn;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return useEspecieReturn;
		}
	}
	
	/**
	 * Listar usos de una especie
	 * @param idSummary
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<UseExoticAlienSpecie> getUsersBySumary(Integer idSummary) {
		
		List<UseExoticAlienSpecie> result=new ArrayList<UseExoticAlienSpecie>();
		try
		{
			Query query = super.getEntityManager().createQuery("select o from UseExoticAlienSpecie o where o.summaryExotic.seasId = :elCodigo and o.ueasStatus = true");
			query.setParameter("elCodigo", idSummary);
			result=(List<UseExoticAlienSpecie>)query.getResultList();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return new ArrayList<UseExoticAlienSpecie>();
			
			
		}
		return result;
		}
	
	
	
		
	
}
