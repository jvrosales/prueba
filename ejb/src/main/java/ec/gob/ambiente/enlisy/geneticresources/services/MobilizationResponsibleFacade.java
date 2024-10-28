package ec.gob.ambiente.enlisy.geneticresources.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.geneticresources.model.MobilizationGuide;
import ec.gob.ambiente.enlisy.geneticresources.model.MobilizationResponsible;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class MobilizationResponsibleFacade extends AbstractFacade<MobilizationResponsible, Integer>{
		

	public MobilizationResponsibleFacade() {
		super(MobilizationResponsible.class, Integer.class);		
	}
		
	/**
	 * Obtener Responsable de Guia de movilizacion por id
	 * @param id
	 * @return
	 */
	public MobilizationResponsible findById(Integer id)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM MobilizationResponsible o where o.moreId= :id and o.moreStatus = true");
			query.setParameter("id", id);
			
			return (MobilizationResponsible) query.getSingleResult();
			
		}catch(NoResultException e)
		{
			return null;
		}
	}
	
	/**
	 * Obtener Responsable de Guias por Propuesta de recoleccion
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<MobilizationResponsible> findByMobilizationGuide(MobilizationGuide mobilizationGuide)
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM MobilizationResponsible o where o.mobilizationGuide.moguId= :id and o.moreStatus = true");
			query.setParameter("id", mobilizationGuide.getMoguId());
			
			List<MobilizationResponsible> result=(List<MobilizationResponsible>)query.getResultList();
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
	 * @param MobilizationResponsible
	 * @param user
	 * @return
	 */
	public boolean save(MobilizationResponsible MobilizationResponsible, User user)
	{
		try
		{			
			if(MobilizationResponsible.getMoreId() == null)
			{	
				
				MobilizationResponsible.setMoreStatus(true);
				MobilizationResponsible.setMoreDateCreate(new Date());
				MobilizationResponsible.setMoreUserCreate(user.getUserName());
				create(MobilizationResponsible);
				
			}
			else
			{
				MobilizationResponsible.setMoreDateUpdate(new Date());	
				MobilizationResponsible.setMoreUserUpdate(user.getUserName());
				edit(MobilizationResponsible);
			}			
			
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	public void delete(MobilizationResponsible MobilizationResponsible, User user){
		MobilizationResponsible.setMoreStatus(false);
		MobilizationResponsible.setMoreDateUpdate(new Date());	
		MobilizationResponsible.setMoreUserUpdate(user.getUserName());
		edit(MobilizationResponsible);
	}

}