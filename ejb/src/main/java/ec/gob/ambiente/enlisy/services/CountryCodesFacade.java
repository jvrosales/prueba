package ec.gob.ambiente.enlisy.services;

import ec.gob.ambiente.enlisy.dao.AbstractFacadeModel;
import ec.gob.ambiente.enlisy.model.CountryCodes;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Stateless
public class CountryCodesFacade extends AbstractFacadeModel<CountryCodes> {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1272264421901364360L;

	public CountryCodesFacade() {
        super(CountryCodes.class);
    }

    @Override
    public void configure(String filter) {
        initialize();
    }

    @SuppressWarnings("unchecked")
    public CountryCodes findByGeloId(Integer geloId){
        initialize();
        CriteriaBuilder cb = getCriteriaBuilder();
        clearWherePredicates();
        clearOrderByPredicates();
        addWherePredicate(cb.equal(getRecord().get("geloId"), geloId));
        List<CountryCodes> result = buildQuery().getResultList();
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

}