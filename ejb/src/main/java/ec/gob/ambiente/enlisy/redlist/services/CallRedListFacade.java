package ec.gob.ambiente.enlisy.redlist.services;

import javax.ejb.Stateless;

import ec.gob.ambiente.enlisy.dao.AbstractFacadeModel;
import ec.gob.ambiente.enlisy.redlist.model.CallRedList;
 
@Stateless
public class CallRedListFacade extends AbstractFacadeModel<CallRedList>{

	private static final long serialVersionUID = 1L;
	public static final int DRAFT = 0;
	public static final int STARTED = 36;
	public static final int APPROVED = 37;
	public static final int FINALIZED = 38;
	
	public static final int SPECIES_RED_LIST = 1;
	public static final int ECOSYSTEMS_RED_LIST = 2;
	
	public CallRedListFacade() {
		super(CallRedList.class);
	}
	
	@Override
	public void configure(String filter) {
		initialize();
		clearWherePredicates();
		clearOrderByPredicates();
		addWherePredicate(getCriteriaBuilder().equal(getRecord().get("careRedlistType"), CallRedListFacade.SPECIES_RED_LIST));
		addOrderByPredicate(getCriteriaBuilder().desc(getRecord().get("careDate")));
	}
	
	public void configureForEcosystems(String filter) {
		initialize();
		clearWherePredicates();
		clearOrderByPredicates();
		addWherePredicate(getCriteriaBuilder().equal(getRecord().get("careRedlistType"), CallRedListFacade.ECOSYSTEMS_RED_LIST));
		addOrderByPredicate(getCriteriaBuilder().desc(getRecord().get("careDate")));
	}
}
