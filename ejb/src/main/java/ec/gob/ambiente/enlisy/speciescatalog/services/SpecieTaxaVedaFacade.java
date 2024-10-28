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
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecieTaxaVeda;


/**
 * Servicios para la administracion de specie vedas
 * @author EXCO
 *
 */
@Stateless
public class SpecieTaxaVedaFacade extends AbstractFacade<SpecieTaxaVeda, Integer>{

	public SpecieTaxaVedaFacade() {
		super(SpecieTaxaVeda.class, Integer.class);		
	}
	
		
	/**
	 * Obtener lista por specie
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SpecieTaxaVeda> findVedas(Integer specieId)
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM SpecieTaxaVeda o where o.spveStatus = true and o.sptaId.sptaId = :elCodigo");
			query.setParameter("elCodigo", specieId);
			List<SpecieTaxaVeda> result=(List<SpecieTaxaVeda>)query.getResultList();
			if(!result.isEmpty())
			{
				
				return result;
			}
			
		}catch(NoResultException e)
		{
			return new ArrayList<SpecieTaxaVeda>();
		}
		return new ArrayList<SpecieTaxaVeda>();
	}
	
	
	/**
	 * Guardar un specie_veda
	 * @param agreement
	 * @param user
	 * @return
	 */
	public SpecieTaxaVeda save(SpecieTaxaVeda agreement, User user) {
		SpecieTaxaVeda agre = null;
		try {
			if (agreement.getSpveId() == null) {

				agreement.setSpveStatus(true);
				agreement.setSpveDateCreate(new Date());
				agreement.setSpveUserCreate(user.getUserName());
				agre = create(agreement);

			} else {
				agreement.setSpveDateUpdate(new Date());
				agreement.setSpveUserUpdate(user.getUserName());
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
			Query query = super.getEntityManager().createQuery("delete from SpecieTaxaVeda o  where o.sptaId.sptaId = :codigoEspecie and o.spveStatus = true");
			query.setParameter("codigoEspecie", specie.getSptaId());
			query.executeUpdate();
							
			
		}catch(NoResultException e)
		{
			
		}
	}
}
