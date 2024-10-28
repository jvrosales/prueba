package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecieTaxaSynonym;


/**
 * Servicios para la administracion de sinonimos
 * @author EXCO
 *
 */
@Stateless
public class SpecieTaxaSynonymFacade extends AbstractFacade<SpecieTaxaSynonym, Integer>{

	public SpecieTaxaSynonymFacade() {
		super(SpecieTaxaSynonym.class, Integer.class);		
	}

	/**
	 * Guardar especie-sinonimo
	 * @param specieSynonym
	 * @param user
	 * @return
	 */
	
	public SpecieTaxaSynonym save(SpecieTaxaSynonym specieSynonym, User user)
	{
		SpecieTaxaSynonym specieSynonymReturn=null;
		try
		{			
			if(specieSynonym.getSptsId() == null)
			{				
				
				specieSynonym.setSptsStatus(true);
				specieSynonym.setSptsDateCreate(new Date());
				specieSynonym.setSptsUserCreate(user.getUserName());
				specieSynonymReturn=create(specieSynonym);
				
			}
			else
			{
				specieSynonym.setSptsDateUpdate(new Date());
				specieSynonym.setSptsUserUpdate(user.getUserName());
				specieSynonymReturn=edit(specieSynonym);
			}
					
			
			return specieSynonymReturn;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return specieSynonymReturn;
		}
	}
	
	/**
	 * Retorna la lista de sinonimos de una especie
	 * @param idSpecie
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SpecieTaxaSynonym> getSynonymsBySpecie(Integer idSpecie) {
		
		List<SpecieTaxaSynonym> result=new ArrayList<SpecieTaxaSynonym>();
		try
		{
			Query query = super.getEntityManager().createQuery("select o from SpecieTaxaSynonym o where o.specie.sptaId = :elCodigo and o.sptsStatus = true");
			query.setParameter("elCodigo", idSpecie);
			result=(List<SpecieTaxaSynonym>)query.getResultList();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			
		}
		return result;
		}
	
	/**
	 * Retorna la lista de sinonimos de una especie
	 * @param idSpecie
	 * @return
	 */
	public SpecieTaxaSynonym getSynonymsBySpecieAndSynonym(Integer idSpecie,Integer idSynomym) {
		
		SpecieTaxaSynonym result=null;
		try
		{
			Query query = super.getEntityManager().createQuery("select o from SpecieTaxaSynonym o where o.specie.sptaId = :elCodigo and o.sinonimo.sptaId= :elCodigoSinonimo and o.sptsStatus = true");
			query.setParameter("elCodigo", idSpecie);
			query.setParameter("elCodigoSinonimo", idSynomym);
			result=(SpecieTaxaSynonym)(query.getResultList()).get(0);
			
		}
		catch(Exception ex)
		{
			result=null;
			ex.printStackTrace();
			
		}
		return result;
		}
	
	
		
	
}
