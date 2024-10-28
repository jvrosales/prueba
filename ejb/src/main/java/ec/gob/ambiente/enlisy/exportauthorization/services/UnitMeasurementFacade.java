package ec.gob.ambiente.enlisy.exportauthorization.services;

import ec.gob.ambiente.enlisy.dao.AbstractFacadeModel;
import ec.gob.ambiente.enlisy.exportauthorization.model.UnitMeasurement;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UnitMeasurementFacade extends AbstractFacadeModel<UnitMeasurement> {

    private static final long serialVersionUID = 1L;

    public UnitMeasurementFacade(){
        super(UnitMeasurement.class);
    }

    @Override
    public void configure(String filter) {
        initialize();
    }

    @SuppressWarnings("unchecked")
    public List<UnitMeasurement> getAllUnits(){
        initialize();
        CriteriaBuilder cb = getCriteriaBuilder();
        clearWherePredicates();
        clearOrderByPredicates();
        addWherePredicate(cb.equal(getRecord().get("unmeStatus"), true));
        addOrderByPredicate(cb.asc(getRecord().get("unmeName")));
        List<UnitMeasurement> result = buildQuery().getResultList();
        if (!result.isEmpty()) {
            return result;
        }
        return new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
	public UnitMeasurement getUnitByCode(String code){
        initialize();
        CriteriaBuilder cb = getCriteriaBuilder();
        clearWherePredicates();
        clearOrderByPredicates();
        addWherePredicate(cb.and(
                cb.equal(getRecord().get("unmeCode"), code),
                cb.equal(getRecord().get("unmeStatus"),true)));
        List<UnitMeasurement> result = buildQuery().getResultList();
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return new UnitMeasurement();
    }
}