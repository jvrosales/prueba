package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecieTaxa;
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecieTaxaHabit;



/**
 * Servicios para la administracion de las provincias en donde
 * se encuentran las especies
 * @author EXCO
 *
 */
@Stateless
public class SpecieTaxaHabitFacade extends AbstractFacade<SpecieTaxaHabit, Integer>{

	public SpecieTaxaHabitFacade() {
		super(SpecieTaxaHabit.class, Integer.class);		
	}

	/**
	 * Guardar especie-habit
	 * @param specieHabit
	 * @param user
	 * @return
	 */
	
	
	public SpecieTaxaHabit save(SpecieTaxaHabit specieHabit, User user)
	{
		SpecieTaxaHabit specieHabitReturn=null;
		try
		{			
			if(specieHabit.getSthaId() == null)
			{				
				
				specieHabit.setSthaStatus(true);
				specieHabit.setSthaDateCreate(new Date());
				specieHabit.setSthaUserCreate(user.getUserName());
				specieHabitReturn=create(specieHabit);
				
			}
			else
			{
				specieHabit.setSthaDateUpdate(new Date());
				specieHabit.setSthaUserUpdate(user.getUserName());
				specieHabitReturn=edit(specieHabit);
			}
					
			
			return specieHabit;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return specieHabitReturn;
		}
	}
	
	/**
	 * Listar habitos de una especie
	 * @param idSpecie
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<SpecieTaxaHabit> getHabitsBySpecie(Integer idSpecie) {
		
		List<SpecieTaxaHabit> result=new ArrayList<SpecieTaxaHabit>();
		try
		{
			Query query = super.getEntityManager().createQuery("select o from SpecieTaxaHabit o where o.specieTaxa.sptaId = :elCodigo and o.sthaStatus = true");
			query.setParameter("elCodigo", idSpecie);
			result=(List<SpecieTaxaHabit>)query.getResultList();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			
		}
		return result;
		}
	
	
	/**
	 * Eliminar habitos de una especie
	 * @param specie
	 * @param user
	 */
	
	public void deleteBySpecie(SpecieTaxa specie, User user)
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("delete from SpecieTaxaHabit o  where o.specieTaxa.sptaId = :codigoEspecie and o.sthaStatus = true");
			query.setParameter("codigoEspecie", specie.getSptaId());
			query.executeUpdate();
							
			
		}catch(NoResultException e)
		{
			
		}
	}
		
	
}
