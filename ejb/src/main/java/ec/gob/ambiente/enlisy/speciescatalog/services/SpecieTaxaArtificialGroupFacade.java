package ec.gob.ambiente.enlisy.speciescatalog.services;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecieTaxa;
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecieTaxaArtificialGroup;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Servicios para la administracion de nombres comunes
 * @author EXCO
 *
 */
@Stateless
public class SpecieTaxaArtificialGroupFacade extends AbstractFacade<SpecieTaxaArtificialGroup, Integer>{

	public SpecieTaxaArtificialGroupFacade() {
		super(SpecieTaxaArtificialGroup.class, Integer.class);		
	}

	/**
	 * Guardar especie, grupo artificial
	 * @param specieGroup
	 * @param user
	 * @return
	 */
	public SpecieTaxaArtificialGroup save(SpecieTaxaArtificialGroup specieGroup, User user)
	{
		SpecieTaxaArtificialGroup specieGroupReturn=null;
		try
		{			
			if(specieGroup.getStagId() == null)
			{				
				
				specieGroup.setStagStatus(true);
				specieGroup.setStagDateCreate(new Date());
				specieGroup.setStagUserCreate(user.getUserName());
				specieGroupReturn=create(specieGroup);
				
			}
			else
			{
				specieGroup.setStagDateUpdate(new Date());
				specieGroup.setStagUserUpdate(user.getUserName());
				specieGroupReturn=edit(specieGroup);
			}
					
			
			return specieGroupReturn;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return specieGroupReturn;
		}
	}
	
	/**
	 * Retorna la lista de grupos artificiales de una especie
	 * @param idSpecie
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SpecieTaxaArtificialGroup> getArtificialGroupsBySpecie(Integer idSpecie) {
		
		List<SpecieTaxaArtificialGroup> result=new ArrayList<SpecieTaxaArtificialGroup>();
		try
		{
			Query query = super.getEntityManager().createQuery("select o from SpecieTaxaArtificialGroup o where o.specieTaxa.sptaId = :elCodigo and o.stagStatus = true");
			query.setParameter("elCodigo", idSpecie);
			result=(List<SpecieTaxaArtificialGroup>)query.getResultList();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			
		}
		return result;
		}
	
	
	/**
	 * Eliminar grupos artificiales
	 * @param specie
	 * @param user
	 */
	public void deleteBySpecie(SpecieTaxa specie, User user)
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("delete from SpecieTaxaArtificialGroup o  where o.specieTaxa.sptaId = :codigoEspecie and o.stagStatus = true");
			query.setParameter("codigoEspecie", specie.getSptaId());
			query.executeUpdate();
							
			
		}catch(NoResultException e)
		{
			
		}
	}
	
	
	/**
	 * Retorna la lista de registros
	 * de la especie con grupo artificial 
	 * forestal
	 * @param idSpecie
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SpecieTaxaArtificialGroup> getForestalBySpecie(Integer idSpecie) {
		
		List<SpecieTaxaArtificialGroup> result=new ArrayList<SpecieTaxaArtificialGroup>();
		try
		{
			Query query = super.getEntityManager().createQuery("select o from SpecieTaxaArtificialGroup o where o.specieTaxa.sptaId = :elCodigo and o.stagStatus = true and o.artificialGroup.argrId=36");
			query.setParameter("elCodigo", idSpecie);
			result=(List<SpecieTaxaArtificialGroup>)query.getResultList();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			new ArrayList<SpecieTaxaArtificialGroup>();
			
		}
		return result;
		}


	@SuppressWarnings("unchecked")
	public List<SpecieTaxaArtificialGroup> getSpeciesByArtificialGroup(Integer artificialGroupId) {
		List<SpecieTaxaArtificialGroup> result = new ArrayList<>();
		try{
			Query query = super.getEntityManager().createQuery("select o from SpecieTaxaArtificialGroup o where o.artificialGroup.argrId = :artificialGroupId and o.stagStatus = true");
			query.setParameter("artificialGroupId", artificialGroupId);
			result = (List<SpecieTaxaArtificialGroup>)query.getResultList();
		}catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
}