package ec.gob.ambiente.enlisy.exportauthorization.services;

import ec.gob.ambiente.enlisy.dao.AbstractFacadeModel;
import ec.gob.ambiente.enlisy.exportauthorization.model.TariffSubheading;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class TariffSubheadingFacade extends AbstractFacadeModel<TariffSubheading> {

    private static final long serialVersionUID = 1L;

    public TariffSubheadingFacade() {
        super(TariffSubheading.class);
    }

    @Override
    public void configure(String filter) {
        initialize();
    }

    @SuppressWarnings("unchecked")
    public List<TariffSubheading> getAllTariff(){
        initialize();
        CriteriaBuilder cb = getCriteriaBuilder();
        clearWherePredicates();
        clearOrderByPredicates();
        addWherePredicate(cb.equal(getRecord().get("tarsStatus"), true));
        addOrderByPredicate(cb.asc(getRecord().get("tarsCode")));
        List<TariffSubheading> result = buildQuery().getResultList();
        if (!result.isEmpty()) {
            return result;
        }
        return new ArrayList<>();
    }
}