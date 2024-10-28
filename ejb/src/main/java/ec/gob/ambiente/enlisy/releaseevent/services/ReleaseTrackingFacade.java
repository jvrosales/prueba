package ec.gob.ambiente.enlisy.releaseevent.services;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import ec.gob.ambiente.enlisy.dao.AbstractFacadeModel;
import ec.gob.ambiente.enlisy.releaseevent.model.ReleaseSpecy;
import ec.gob.ambiente.enlisy.releaseevent.model.ReleaseTracking;

@Stateless
public class ReleaseTrackingFacade extends AbstractFacadeModel<ReleaseTracking>{
	
	private static final long serialVersionUID = 1L;
	
	public ReleaseTrackingFacade() {
		super(ReleaseTracking.class);
	}

	@Override
	public void configure(String filter) {
		initialize();
	}
	
	@SuppressWarnings("unchecked")
	public List<ReleaseTracking> findBySpecieRelease(ReleaseSpecy specieId){
		initialize();
		CriteriaBuilder cb = getCriteriaBuilder();
		clearWherePredicates();
		clearOrderByPredicates();
		addWherePredicate(cb.equal(getRecord().get("releaseSpecy"), specieId));
		List<ReleaseTracking> result = buildQuery().getResultList();
		if (!result.isEmpty()) {
			return result;
		}
		return new ArrayList<ReleaseTracking>();
	}
}