package ec.gob.ambiente.enlisy.wildlifeevent.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;

import ec.gob.ambiente.enlisy.dao.AbstractFacadeModel;
import ec.gob.ambiente.enlisy.wildlifeevent.model.GeneralData;
import ec.gob.ambiente.enlisy.wildlifeevent.model.PeopleWildlifeEvent;

@Stateless
public class PeopleWildlifeEventFacade extends AbstractFacadeModel<PeopleWildlifeEvent>{

	private static final long serialVersionUID = 1L;

	public PeopleWildlifeEventFacade() {
		super(PeopleWildlifeEvent.class);
	}

	@Override
	public void configure(String filter) {
		initialize();
	}
	
	@SuppressWarnings("unchecked")
	public PeopleWildlifeEvent findByGeneralData(GeneralData generalData) {
		initialize();
		CriteriaBuilder cb = getCriteriaBuilder();
		clearWherePredicates();
		clearOrderByPredicates();
		addWherePredicate(cb.equal(getRecord().get("generalData"), generalData));
		List<PeopleWildlifeEvent> result = buildQuery().getResultList(); 
		if(!result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}
}
