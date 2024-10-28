package ec.gob.ambiente.enlisy.wildlifeevent.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.wildlifeevent.model.RescueTracking;

@Stateless
public class RescueTrackingFacade extends AbstractFacade<RescueTracking,Integer> {

	
	public RescueTrackingFacade() {
		super(RescueTracking.class,Integer.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<RescueTracking> findTrackingByEvent(Integer idRescueEvent)
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM RescueTracking o where o.reseId.reseId = :id and o.rtraStatus=true");
			query.setParameter("id", idRescueEvent);
			List<RescueTracking> result=(List<RescueTracking>)query.getResultList();
			
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<RescueTracking>();
		}
		return new ArrayList<RescueTracking>();
	}
	
	
	public RescueTracking save(RescueTracking tracking, User user) {
		RescueTracking trackingRetornar = null;
		try {
			if (tracking.getRtraId() == null) {

				tracking.setRtraStatus(true);
				tracking.setRtraDateCreate(new Date());
				tracking.setRtraUserCreate(user.getUserName());
				trackingRetornar = create(tracking);

			} else {
				tracking.setRtraDateUpdate(new Date());
				tracking.setRtraUserUpdate(user.getUserName());
				trackingRetornar = edit(tracking);
			}

			return trackingRetornar;
		} catch (Exception ex) {
			return trackingRetornar;
		}
	}
	
	public boolean removeTrackingByEvent(Integer idRescueEvent)
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("DELETE FROM RescueTracking o where o.reseId.reseId = :id and o.rtraStatus=true");
			query.setParameter("id", idRescueEvent);
			query.executeUpdate();
			return true;
			
		}catch(NoResultException e)
		{
			return false;
		}
		
	}
	
	
	
	
}
