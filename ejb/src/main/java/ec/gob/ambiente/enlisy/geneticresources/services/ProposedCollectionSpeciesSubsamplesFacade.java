package ec.gob.ambiente.enlisy.geneticresources.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.geneticresources.model.ProposedCollectionSpecies;
import ec.gob.ambiente.enlisy.geneticresources.model.ProposedCollectionSpeciesSubSamples;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class ProposedCollectionSpeciesSubsamplesFacade extends AbstractFacade<ProposedCollectionSpeciesSubSamples, Integer>{
		

	public ProposedCollectionSpeciesSubsamplesFacade() {
		super(ProposedCollectionSpeciesSubSamples.class, Integer.class);		
	}
		
	/**
	 * Obtener submuestras de Especies a recolectar de la Propuesta de recoleccion por id
	 * @param id
	 * @return
	 */
	public ProposedCollectionSpeciesSubSamples findById(Integer id)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ProposedCollectionSpeciesSubSamples o where o.pcssId= :id and o.pcssStatus = true");
			query.setParameter("id", id);
			
			return (ProposedCollectionSpeciesSubSamples) query.getSingleResult();
			
		}catch(NoResultException e)
		{
			return null;
		}
	}
	
	/**
	 * Obtener  submuestras de Especies a recolectar de la Propuesta de recoleccion por especie
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ProposedCollectionSpeciesSubSamples> findByProposedCollectionSpecies(ProposedCollectionSpecies proposedCollectionEpecies)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM ProposedCollectionSpeciesSubSamples o where o.proposedCollectionSpecies.prcsId= :id and o.pcssStatus = true");
			query.setParameter("id", proposedCollectionEpecies.getPrcsId());
			
			List<ProposedCollectionSpeciesSubSamples> result=(List<ProposedCollectionSpeciesSubSamples>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	
	
	
	/**
	 * Guardar  Especies a recolectar de la Propuesta de recoleccion
	 * @param ProposedCollectionSpecies
	 * @param user
	 * @return
	 */
	public boolean save(ProposedCollectionSpeciesSubSamples proposedCollectionSpeciesSubsamples, User user)
	{
		try
		{			
			if(proposedCollectionSpeciesSubsamples.getPcssId() == null)
			{	
				
				proposedCollectionSpeciesSubsamples.setPcssStatus(true);
				proposedCollectionSpeciesSubsamples.setPcssDateCreate(new Date());
				proposedCollectionSpeciesSubsamples.setPcssUserCreate(user.getUserName());
				create(proposedCollectionSpeciesSubsamples);
				
			}
			else
			{
				proposedCollectionSpeciesSubsamples.setPcssDateUpdate(new Date());	
				proposedCollectionSpeciesSubsamples.setPcssUserUpdate(user.getUserName());
				edit(proposedCollectionSpeciesSubsamples);
			}
			
			
			
			
			
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	public void delete(ProposedCollectionSpeciesSubSamples proposedCollectionSpeciesSubsamples, User user){
		proposedCollectionSpeciesSubsamples.setPcssStatus(false);
		proposedCollectionSpeciesSubsamples.setPcssDateUpdate(new Date());	
		proposedCollectionSpeciesSubsamples.setPcssUserUpdate(user.getUserName());
		edit(proposedCollectionSpeciesSubsamples);
	}

}