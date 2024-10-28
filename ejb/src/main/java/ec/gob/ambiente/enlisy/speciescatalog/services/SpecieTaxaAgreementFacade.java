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
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecieTaxaAgreement;


/**
 * Servicios para la administracion de specie convenios
 * @author EXCO
 *
 */
@Stateless
public class SpecieTaxaAgreementFacade extends AbstractFacade<SpecieTaxaAgreement, Integer>{

	public SpecieTaxaAgreementFacade() {
		super(SpecieTaxaAgreement.class, Integer.class);		
	}
	
		
	/**
	 * Obtener lista por specie
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SpecieTaxaAgreement> findAgreements(Integer specieId)
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM SpecieTaxaAgreement o where o.sagrStatus = true and o.sptaId.sptaId = :elCodigo");
			query.setParameter("elCodigo", specieId);
			List<SpecieTaxaAgreement> result=(List<SpecieTaxaAgreement>)query.getResultList();
			if(!result.isEmpty())
			{
				
				return result;
			}
			
		}catch(NoResultException e)
		{
			return new ArrayList<SpecieTaxaAgreement>();
		}
		return new ArrayList<SpecieTaxaAgreement>();
	}
	
	
	/**
	 * Guardar un specie_convenio
	 * @param artificialGroup
	 * @param user
	 * @return
	 */
	public SpecieTaxaAgreement save(SpecieTaxaAgreement agreement, User user) {
		SpecieTaxaAgreement agre = null;
		try {
			if (agreement.getSagrId() == null) {

				agreement.setSagrStatus(true);
				agreement.setSagrDateCreate(new Date());
				agreement.setSagrUserCreate(user.getUserName());
				agre = create(agreement);

			} else {
				agreement.setSagrDateUpdate(new Date());
				agreement.setSagrUserUpdate(user.getUserName());
				agre = edit(agreement);
			}

			return agre;
		} catch (Exception ex) {
			return agre;
		}
	}
	
	public void deleteBySpecie(SpecieTaxa specie, User user)
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("delete from SpecieTaxaAgreement o  where o.sptaId.sptaId = :codigoEspecie and o.sagrStatus = true");
			query.setParameter("codigoEspecie", specie.getSptaId());
			query.executeUpdate();
							
			
		}catch(NoResultException e)
		{
			
		}
	}
}
