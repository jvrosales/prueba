package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecieTaxaLiteraryReference;



/**
 * Servicios para la administracion de las referencias literarias de una especie 
 * @author EXCO
 *
 */
@Stateless
public class SpecieTaxaLiteraryReferenceFacade extends AbstractFacade<SpecieTaxaLiteraryReference, Integer>{

	public SpecieTaxaLiteraryReferenceFacade() {
		super(SpecieTaxaLiteraryReference.class, Integer.class);		
	}

	/**
	 * Guardar especie-literary reference
	 * @param specieHabit
	 * @param user
	 * @return
	 */
	
	
	public SpecieTaxaLiteraryReference save(SpecieTaxaLiteraryReference specieLiteraryReference, User user)
	{
		SpecieTaxaLiteraryReference specieLiteraryReferenceReturn=null;
		try
		{			
			if(specieLiteraryReference.getStlrId() == null)
			{				
				
				specieLiteraryReference.setStlrStatus(true);
				specieLiteraryReference.setStlrDateCreate(new Date());
				specieLiteraryReference.setStlrUserCreate(user.getUserName());
				specieLiteraryReferenceReturn=create(specieLiteraryReference);
				
			}
			else
			{
				specieLiteraryReference.setStlrDateUpdate(new Date());
				specieLiteraryReference.setStlrUserUpdate(user.getUserName());
				specieLiteraryReferenceReturn=edit(specieLiteraryReference);
			}
					
			
			return specieLiteraryReferenceReturn;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return specieLiteraryReferenceReturn;
		}
	}
	
	/**
	 * Listar referencias de una especie
	 * @param idSpecie
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<SpecieTaxaLiteraryReference> getReferencesBySpecie(Integer idSpecie) {
		
		List<SpecieTaxaLiteraryReference> result=new ArrayList<SpecieTaxaLiteraryReference>();
		try
		{
			Query query = super.getEntityManager().createQuery("select o from SpecieTaxaLiteraryReference o where o.specieTaxa.sptaId = :elCodigo and o.stlrStatus = true");
			query.setParameter("elCodigo", idSpecie);
			result=(List<SpecieTaxaLiteraryReference>)query.getResultList();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return new ArrayList<SpecieTaxaLiteraryReference>();
		}
		return result;
		}
	
	
	
		
	
}
