package ec.gob.ambiente.enlisy.geneticresources.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.geneticresources.model.ProposedCollection;
import ec.gob.ambiente.enlisy.geneticresources.model.MobilizationGuide;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class MobilizationGuideFacade extends AbstractFacade<MobilizationGuide, Integer>{
		

	public MobilizationGuideFacade() {
		super(MobilizationGuide.class, Integer.class);		
	}
		
	/**
	 * Obtener Guia de movilizacion por id
	 * @param id
	 * @return
	 */
	public MobilizationGuide findById(Integer id)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM MobilizationGuide o where o.moguId= :id and o.moguStatus = true");
			query.setParameter("id", id);
			
			return (MobilizationGuide) query.getSingleResult();
			
		}catch(NoResultException e)
		{
			return null;
		}
	}
	
	/**
	 * Obtener Guias por Propuesta de recoleccion
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<MobilizationGuide> findByProposedCollection(ProposedCollection proposedCollection)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM MobilizationGuide o where o.proposedCollection.prcoId= :id and o.moguStatus = true");
			query.setParameter("id", proposedCollection.getPrcoId());
			
			List<MobilizationGuide> result=(List<MobilizationGuide>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	/**
	 * Obtener Guias Emitidas Finalizadas
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<MobilizationGuide> findFinalized()
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM MobilizationGuide o where o.moguFinalized= true and o.moguStatus = true");			
			
			List<MobilizationGuide> result=(List<MobilizationGuide>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	/**
	 * Obtener Guias Emitidas Finalizadas por Area del Usuario
	 * @return List<MobilizationGuide>
	 */
	@SuppressWarnings("unchecked")
	public List<MobilizationGuide> findFinalized(User user)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM MobilizationGuide o where o.moguFinalized= true and o.moguStatus = true and o.geloOrigin.geloCodificationInec like :codificationInec");			
			query.setParameter("codificationInec", user.getArea().getUbicacionesGeografica().getGeloCodificationInec().subSequence(0, 2)+"%");
			List<MobilizationGuide> result=(List<MobilizationGuide>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return null;
		}
		return null;
	}
	
	/**
	 * Guardar
	 * @param MobilizationGuide
	 * @param user
	 * @return
	 */
	public boolean save(MobilizationGuide MobilizationGuide, User user)
	{
		try
		{			
			if(MobilizationGuide.getMoguId() == null)
			{	
				
				MobilizationGuide.setMoguStatus(true);
				MobilizationGuide.setMoguDateCreate(new Date());
				MobilizationGuide.setMoguUserCreate(user.getUserName());
				create(MobilizationGuide);
				
			}
			else
			{
				MobilizationGuide.setMoguDateUpdate(new Date());	
				MobilizationGuide.setMoguUserUpdate(user.getUserName());
				edit(MobilizationGuide);
			}
			
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	public void delete(MobilizationGuide MobilizationGuide, User user){
		MobilizationGuide.setMoguStatus(false);
		MobilizationGuide.setMoguDateUpdate(new Date());	
		MobilizationGuide.setMoguUserUpdate(user.getUserName());
		edit(MobilizationGuide);
	}

}