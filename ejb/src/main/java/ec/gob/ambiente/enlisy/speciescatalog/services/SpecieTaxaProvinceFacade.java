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
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecieTaxaProvince;


/**
 * Servicios para la administracion de las provincias en donde
 * se encuentran las especies
 * @author EXCO
 *
 */
@Stateless
public class SpecieTaxaProvinceFacade extends AbstractFacade<SpecieTaxaProvince, Integer>{

	public SpecieTaxaProvinceFacade() {
		super(SpecieTaxaProvince.class, Integer.class);		
	}

	
	/**
	 * Guardar registro especie-provincia
	 * @param specieProvince
	 * @param user
	 * @return
	 */
	
	public SpecieTaxaProvince save(SpecieTaxaProvince specieProvince, User user)
	{
		SpecieTaxaProvince specieProvinceReturn=null;
		try
		{			
			if(specieProvince.getSptpId() == null)
			{				
				
				specieProvince.setSptpStatus(true);
				specieProvince.setSptpDateCreate(new Date());
				specieProvince.setSptpUserCreate(user.getUserName());
				specieProvinceReturn=create(specieProvince);
				
			}
			else
			{
				specieProvince.setSptpDateUpdate(new Date());
				specieProvince.setSptpUserUpdate(user.getUserName());
				specieProvinceReturn=edit(specieProvince);
			}
					
			
			return specieProvinceReturn;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return specieProvinceReturn;
		}
	}
	
	/**
	 * Recuperar las provincias en las que se encuentran
	 * una especie
	 * @param idSpecie
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SpecieTaxaProvince> getProvincesBySpecie(Integer idSpecie) {
		
		List<SpecieTaxaProvince> result=new ArrayList<SpecieTaxaProvince>();
		try
		{
			Query query = super.getEntityManager().createQuery("select o from SpecieTaxaProvince o where o.specie.sptaId = :elCodigo and o.sptpStatus = true");
			query.setParameter("elCodigo", idSpecie);
			result=(List<SpecieTaxaProvince>)query.getResultList();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			
		}
		return result;
		}
	
	
	/**
	 * Eliminar provincias
	 * @param specie
	 * @param user
	 */
	public void deleteBySpecie(SpecieTaxa specie, User user)
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("delete from SpecieTaxaProvince o  where o.specie.sptaId = :codigoEspecie and o.sptpStatus = true");
			query.setParameter("codigoEspecie", specie.getSptaId());
			query.executeUpdate();
							
			
		}catch(NoResultException e)
		{
			
		}
	}
		
	
}
