package ec.gob.ambiente.enlisy.releaseevent.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import ec.gob.ambiente.enlisy.dao.AbstractFacadeModel;
import ec.gob.ambiente.enlisy.releaseevent.model.ReleaseWildlife;

@Stateless
public class ReleaseWildlifeFacade extends AbstractFacadeModel<ReleaseWildlife> {

	private static final long serialVersionUID = 1L;

	public ReleaseWildlifeFacade() {
		super(ReleaseWildlife.class);
	}
	
	@Override
	public void configure(String filter) {
		initialize();
	}
	
	@SuppressWarnings("unchecked")
	public ReleaseWildlife findByCode(String code) {
		initialize();
		CriteriaBuilder cb = getCriteriaBuilder();
		clearWherePredicates();
		clearOrderByPredicates();
		addWherePredicate(cb.equal(getRecord().get("rewiCode"), code));
		List<ReleaseWildlife> result = buildQuery().getResultList();
		if (!result.isEmpty()) {
			return result.get(0);
		}
		return null;		
	}
}