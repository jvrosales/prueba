package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecieEcosystem;



/**
 * Servicios para la administracion de los ecosistemas en donde
 * se encuentran las especies
 * @author EXCO
 *
 */
@Stateless
public class SpecieEcosystemFacade extends AbstractFacade<SpecieEcosystem, Integer>{

	public SpecieEcosystemFacade() {
		super(SpecieEcosystem.class, Integer.class);		
	}

	
	/**
	 * Guardar registro especie-ecosistema
	 * @param specieEcosystem
	 * @param user
	 * @return
	 */
	
	public SpecieEcosystem save(SpecieEcosystem specieEcosystem, User user)
	{
		SpecieEcosystem specieEcosystemReturn=null;
		try
		{			
			if(specieEcosystem.getSpecId()== null)
			{				
				
				specieEcosystem.setSpecStatus(true);
				specieEcosystem.setSpecDateCreate(new Date());
				specieEcosystem.setSpecUserCreate(user.getUserName());
				specieEcosystemReturn=create(specieEcosystem);
				
			}
			else
			{
				specieEcosystem.setSpecDateUpdate(new Date());
				specieEcosystem.setSpecUserUpdate(user.getUserName());
				specieEcosystemReturn=edit(specieEcosystem);
			}
					
			
			return specieEcosystemReturn;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return specieEcosystemReturn;
		}
	}
	
	/**
	 * Recuperar los ecosistemas en las que se encuentran
	 * una especie
	 * @param idSpecie
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SpecieEcosystem> getEcosystemsBySpecie(Integer idSpecie) {
		
		List<SpecieEcosystem> result=new ArrayList<SpecieEcosystem>();
		try
		{
			Query query = super.getEntityManager().createQuery("select o from SpecieEcosystem o where o.specie.sptaId = :elCodigo and o.specStatus = true");
			query.setParameter("elCodigo", idSpecie);
			result=(List<SpecieEcosystem>)query.getResultList();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			
		}
		return result;
		}
	
	
	
	
}
