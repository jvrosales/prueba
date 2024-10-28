package ec.gob.ambiente.enlisy.wildlifeevent.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.wildlifeevent.model.RetentionTracking;

@Stateless
public class RetentionTrackingFacade extends AbstractFacade<RetentionTracking,Integer> {

	
	public RetentionTrackingFacade() {
		super(RetentionTracking.class,Integer.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<RetentionTracking> findTrackingByEvent(Integer idRetentionEvent)
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM RetentionTracking o where o.reteId.reteId = :id and o.retaStatus=true");
			query.setParameter("id", idRetentionEvent);
			List<RetentionTracking> result=(List<RetentionTracking>)query.getResultList();
			
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<RetentionTracking>();
		}
		return new ArrayList<RetentionTracking>();
	}
	
	
	public RetentionTracking save(RetentionTracking tracking, User user) {
		RetentionTracking trackingRetornar = null;
		try {
			if (tracking.getRetaId() == null) {

				tracking.setRetaStatus(true);
				tracking.setRetaDateCreate(new Date());
				tracking.setRetaUserCreate(user.getUserName());
				trackingRetornar = create(tracking);

			} else {
				tracking.setRetaDateUpdate(new Date());
				tracking.setRetaUserUpdate(user.getUserName());
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
			Query query = super.getEntityManager().createQuery("DELETE FROM RetentionTracking o where o.reteId.reteId = :id and o.retaStatus=true");
			query.setParameter("id", idRescueEvent);
			query.executeUpdate();
			return true;
			
		}catch(NoResultException e)
		{
			return false;
		}
		
	}
	
	
	
	
}
