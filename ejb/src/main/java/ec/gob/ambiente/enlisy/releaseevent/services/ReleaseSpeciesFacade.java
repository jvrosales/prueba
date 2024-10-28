package ec.gob.ambiente.enlisy.releaseevent.services;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import ec.gob.ambiente.enlisy.dao.AbstractFacadeModel;
import ec.gob.ambiente.enlisy.releaseevent.model.ReleaseSpecy;

@Stateless
public class ReleaseSpeciesFacade extends AbstractFacadeModel<ReleaseSpecy> {

	private static final long serialVersionUID = 1L;
	
	public ReleaseSpeciesFacade() {
		super(ReleaseSpecy.class);
	}
	
	@Override
	public void configure(String filter) {
		initialize();
	}
	
	@SuppressWarnings("unchecked")
	public List<ReleaseSpecy> findAllNotFinalize() {
		boolean finalizeStatus = false;
		initialize();
		CriteriaBuilder cb = getCriteriaBuilder();
		clearWherePredicates();
		clearOrderByPredicates();
		addWherePredicate(cb.equal(getRecord().get("respFinalizeStatus"), finalizeStatus));
		addOrderByPredicate(cb.desc(getRecord().get("releaseWildlife")));
		List<ReleaseSpecy> result = buildQuery().getResultList();
		if (!result.isEmpty()) {
			return result;
		}
		return new ArrayList<ReleaseSpecy>();
	}
}