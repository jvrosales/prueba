package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecieTaxaNationalEndemism;


/**
 * Servicios para la administracion de las provincias en donde
 * se encuentran las especies
 * @author EXCO
 *
 */
@Stateless
public class SpecieTaxaNationalEndemismFacade extends AbstractFacade<SpecieTaxaNationalEndemism, Integer>{

	public SpecieTaxaNationalEndemismFacade() {
		super(SpecieTaxaNationalEndemism.class, Integer.class);		
	}

	
	/**
	 * Guardar registro especie-provincia
	 * @param specieProvince
	 * @param user
	 * @return
	 */
	
	public SpecieTaxaNationalEndemism save(SpecieTaxaNationalEndemism specieTaxaNationalEndemism, User user)
	{
		SpecieTaxaNationalEndemism specieTaxaNationalEndemismReturn=null;
		try
		{			
			if(specieTaxaNationalEndemism.getSpenId() == null)
			{				
				
				specieTaxaNationalEndemism.setSpenStatus(true);
				specieTaxaNationalEndemism.setSpenDateCreate(new Date());
				specieTaxaNationalEndemism.setSpenUserCreate(user.getUserName());
				specieTaxaNationalEndemismReturn=create(specieTaxaNationalEndemism);
				
			}
			else
			{
				specieTaxaNationalEndemism.setSpenDateUpdate(new Date());
				specieTaxaNationalEndemism.setSpenUserUpdate(user.getUserName());
				specieTaxaNationalEndemismReturn=edit(specieTaxaNationalEndemism);
			}
					
			
			return specieTaxaNationalEndemismReturn;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return specieTaxaNationalEndemismReturn;
		}
	}
	
	/**
	 * Recuperar las provincias en las que se encuentran
	 * una especie
	 * @param idSpecie
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SpecieTaxaNationalEndemism> getProvincesBySpecie(Integer idSpecie) {
		
		List<SpecieTaxaNationalEndemism> result=new ArrayList<SpecieTaxaNationalEndemism>();
		try
		{
			Query query = super.getEntityManager().createQuery("select o from SpecieTaxaNationalEndemism o where o.sptaId.sptaId = :elCodigo and o.spenStatus = true");
			query.setParameter("elCodigo", idSpecie);
			result=(List<SpecieTaxaNationalEndemism>)query.getResultList();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			
		}
		return result;
		}
	
	
	 
		
	
}
