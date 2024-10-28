package ec.gob.ambiente.enlisy.wildlifeevent.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;

import ec.gob.ambiente.enlisy.dao.AbstractFacadeModel;
import ec.gob.ambiente.enlisy.wildlifeevent.model.GeneralData;
import ec.gob.ambiente.enlisy.wildlifeevent.model.RunOverEvent;

@Stateless
public class RunOverEventFacade extends AbstractFacadeModel<RunOverEvent> {

	private static final long serialVersionUID = 1L;
	
	public RunOverEventFacade() {
		super(RunOverEvent.class);
	}
	
	@Override
	public void configure(String filter) {
		initialize();
	}
	
	@SuppressWarnings("unchecked")
	public RunOverEvent findByGeneralData(GeneralData generalData) {
		initialize();
		CriteriaBuilder cb = getCriteriaBuilder();
		clearWherePredicates();
		clearOrderByPredicates();
		addWherePredicate(cb.equal(getRecord().get("generalData"), generalData));
		List<RunOverEvent> result = buildQuery().getResultList(); 
		if(!result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}

}
