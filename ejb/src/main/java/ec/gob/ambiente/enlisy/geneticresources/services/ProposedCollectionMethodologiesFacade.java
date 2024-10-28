package ec.gob.ambiente.enlisy.geneticresources.services;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.geneticresources.model.ProposedCollection;
import ec.gob.ambiente.enlisy.geneticresources.model.ProposedCollectionMethodologies;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class ProposedCollectionMethodologiesFacade extends AbstractFacade<ProposedCollectionMethodologies, Integer> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1837622629002833671L;

	public ProposedCollectionMethodologiesFacade() {
		super(ProposedCollectionMethodologies.class, Integer.class);		
	}
	
	
	/**
	 * Obtener Equipos y materiales de la Propuesta de recoleccion por id
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ProposedCollectionMethodologies> findByProposedCollection(ProposedCollection proposedCollection)
	{
		try
		{
			Query query = super.getEntityManager()
					.createQuery(" SELECT o FROM ProposedCollectionMethodologies o where o.proposedCollection.prcoId= :id and  o.prcmStatus = true");
			query.setParameter("id", proposedCollection.getPrcoId());
			
			List<ProposedCollectionMethodologies> result=(List<ProposedCollectionMethodologies>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	
	
	
	
	/**
	 * Guardar  Equipos y materiales de la propuesta
	 * @param ProposedCollectionMethodologies
	 * @param user
	 * @return
	 */
	public boolean save(ProposedCollectionMethodologies proposedCollectionMethodologies, User user)
	{
		try
		{			
			if(proposedCollectionMethodologies.getPrcmId() == null)
			{		
				proposedCollectionMethodologies.setPrcmStatus(true);
				proposedCollectionMethodologies.setPrcmDateCreate(new Date());
				proposedCollectionMethodologies.setPrcmUserCreate(user.getUserName());
				create(proposedCollectionMethodologies);
				
			}
			else
			{
				proposedCollectionMethodologies.setPrcmDateUpdate(new Date());	
				proposedCollectionMethodologies.setPrcmUserUpdate(user.getUserName());
				edit(proposedCollectionMethodologies);
			}
			
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	public void delete(ProposedCollectionMethodologies proposedCollectionMethodologies , User user){
		proposedCollectionMethodologies.setPrcmStatus(false);
		proposedCollectionMethodologies.setPrcmDateUpdate(new Date());	
		proposedCollectionMethodologies.setPrcmUserUpdate(user.getUserName());
		edit(proposedCollectionMethodologies);
	}

}
