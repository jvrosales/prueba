package ec.gob.ambiente.enlisy.wildlifeevent.services;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import ec.gob.ambiente.enlisy.dao.AbstractFacadeModel;
import ec.gob.ambiente.enlisy.wildlifeevent.model.EventTracking;
import ec.gob.ambiente.enlisy.wildlifeevent.model.GeneralData;

@Stateless
public class EventTrackingFacade extends AbstractFacadeModel<EventTracking>{

	private static final long serialVersionUID = 1L;
	
	public EventTrackingFacade() {
		super(EventTracking.class);
	}
	
	@Override
	public void configure(String filter) {
		initialize();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<EventTracking> findByGeneralData(GeneralData gdataId){
		initialize();
		CriteriaBuilder cb = getCriteriaBuilder();
		clearWherePredicates();
		clearOrderByPredicates();
		addWherePredicate(cb.equal(getRecord().get("generalData"), gdataId));
		List<EventTracking> result = buildQuery().getResultList();
		if (!result.isEmpty()) {
			return result;
		}
		return new ArrayList<EventTracking>();
	}
}
