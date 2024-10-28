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
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecieTaxaInformationSource;


/**
 * Servicios para la administracion de fuentes de informacion adicional de una especie
 * @author EXCO
 *
 */
@Stateless
public class SpecieTaxaInformationSourceFacade extends AbstractFacade<SpecieTaxaInformationSource, Integer>{

	public SpecieTaxaInformationSourceFacade() {
		super(SpecieTaxaInformationSource.class, Integer.class);		
	}

	/**
	 * Guardar especie-fuente de informacion
	 * @param specieSource
	 * @param user
	 * @return
	 */
	
	
	public SpecieTaxaInformationSource save(SpecieTaxaInformationSource specieSource, User user)
	{
		SpecieTaxaInformationSource specieSourceReturn=null;
		try
		{			
			if(specieSource.getStisId()== null)
			{				
				
				specieSource.setStisStatus(true);
				specieSource.setStisDateCreate(new Date());
				specieSource.setStisUserCreate(user.getUserName());
				specieSourceReturn=create(specieSource);
				
			}
			else
			{
				specieSource.setStisDateUpdate(new Date());
				specieSource.setStisUserUpdate(user.getUserName());
				specieSourceReturn=edit(specieSource);
			}
					
			
			return specieSourceReturn;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return specieSourceReturn;
		}
	}
	
	/**
	 * Retorna la lista de fuentes de informacion adicionales
	 * @param idSpecie
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SpecieTaxaInformationSource> getInformationSourceBySpecie(Integer idSpecie) {
		
		List<SpecieTaxaInformationSource> result=new ArrayList<SpecieTaxaInformationSource>();
		try
		{
			Query query = super.getEntityManager().createQuery("select o from SpecieTaxaInformationSource o where o.specieTaxa.sptaId = :elCodigo and o.stisStatus = true");
			query.setParameter("elCodigo", idSpecie);
			result=(List<SpecieTaxaInformationSource>)query.getResultList();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			
		}
		return result;
		}
	
	
	/**
	 * Eliminar fuentes adicionales de informacion
	 * @param specie
	 * @param user
	 */
	public void deleteBySpecie(SpecieTaxa specie, User user)
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("delete from SpecieTaxaInformationSource o  where o.specieTaxa.sptaId = :codigoEspecie and o.stisStatus = true");
			query.setParameter("codigoEspecie", specie.getSptaId());
			query.executeUpdate();
							
			
		}catch(NoResultException e)
		{
			
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<SpecieTaxaInformationSource> getInformationSourceBySpecieAndPrincipal(Integer idSpecie) {
		
		List<SpecieTaxaInformationSource> result=new ArrayList<SpecieTaxaInformationSource>();
		try
		{
			Query query = super.getEntityManager().createQuery("select o from SpecieTaxaInformationSource o where o.specieTaxa.sptaId = :elCodigo and o.stisStatus = true and o.stisPrincipal = true");
			query.setParameter("elCodigo", idSpecie);
			result=(List<SpecieTaxaInformationSource>)query.getResultList();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			
		}
		return result;
		}
	
	
@SuppressWarnings("unchecked")
public List<SpecieTaxaInformationSource> getInformationSourceBySpecieAndPrincipalAndType(Integer idSpecie, Integer idTipo) {
		
		List<SpecieTaxaInformationSource> result=new ArrayList<SpecieTaxaInformationSource>();
		try
		{
			Query query = super.getEntityManager().createQuery("select o from SpecieTaxaInformationSource o where o.specieTaxa.sptaId = :elCodigo and o.stisStatus = true and o.stisPrincipal = true and o.sourceType.bigcId = :elTipo");
			query.setParameter("elCodigo", idSpecie);
			query.setParameter("elTipo", idTipo);
			result=(List<SpecieTaxaInformationSource>)query.getResultList();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			
		}
		return result;
		}
		
	
}
