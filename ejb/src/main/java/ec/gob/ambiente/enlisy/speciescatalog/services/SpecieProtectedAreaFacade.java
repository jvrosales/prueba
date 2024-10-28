package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecieProtectedArea;



/**
 * Servicios para la administracion de las areas protegidas en donde
 * se encuentran las especies
 * @author EXCO
 *
 */
@Stateless
public class SpecieProtectedAreaFacade extends AbstractFacade<SpecieProtectedArea, Integer>{

	public SpecieProtectedAreaFacade() {
		super(SpecieProtectedArea.class, Integer.class);		
	}

	
	/**
	 * Guardar registro especie-area protegida
	 * @param specieAreaProtected
	 * @param user
	 * @return
	 */
	
	public SpecieProtectedArea save(SpecieProtectedArea specieProtectedArea, User user)
	{
		SpecieProtectedArea specieProtectedReturn=null;
		try
		{			
			if(specieProtectedArea.getSppaId() == null)
			{				
				
				specieProtectedArea.setSppaStatus(true);
				specieProtectedArea.setSppaDateCreate(new Date());
				specieProtectedArea.setSppaUserCreate(user.getUserName());
				specieProtectedReturn=create(specieProtectedArea);
				
			}
			else
			{
				specieProtectedArea.setSppaDateUpdate(new Date());
				specieProtectedArea.setSppaUserUpdate(user.getUserName());
				specieProtectedReturn=edit(specieProtectedArea);
			}
					
			
			return specieProtectedReturn;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return specieProtectedReturn;
		}
	}
	
	/**
	 * Recuperar las areas en las que se encuentran
	 * una especie
	 * @param idSpecie
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SpecieProtectedArea> getAreasBySpecie(Integer idSpecie) {
		
		List<SpecieProtectedArea> result=new ArrayList<SpecieProtectedArea>();
		try
		{
			Query query = super.getEntityManager().createQuery("select o from SpecieProtectedArea o where o.specie.sptaId = :elCodigo and o.sppaStatus = true");
			query.setParameter("elCodigo", idSpecie);
			result=(List<SpecieProtectedArea>)query.getResultList();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			
		}
		return result;
		}
	
	
	
	
}
