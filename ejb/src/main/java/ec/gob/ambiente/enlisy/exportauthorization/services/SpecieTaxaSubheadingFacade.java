package ec.gob.ambiente.enlisy.exportauthorization.services;

import ec.gob.ambiente.enlisy.dao.AbstractFacadeModel;
import ec.gob.ambiente.enlisy.exportauthorization.model.SpecieTaxaSubheading;
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecieTaxa;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class SpecieTaxaSubheadingFacade extends AbstractFacadeModel<SpecieTaxaSubheading> {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6928029904593063135L;

	public SpecieTaxaSubheadingFacade() {
        super(SpecieTaxaSubheading.class);
    }

    @Override
    public void configure(String filter) {
        initialize();
    }

    @SuppressWarnings("unchecked")
	public List<SpecieTaxaSubheading> getAllSpecieTaxaSubheading(){
        initialize();
        CriteriaBuilder cb = getCriteriaBuilder();
        clearWherePredicates();
        clearOrderByPredicates();
        addWherePredicate(cb.equal(getRecord().get("stasStatus"), true));
        addOrderByPredicate(cb.asc(getRecord().get("stasId")));
        List<SpecieTaxaSubheading> result = buildQuery().getResultList();
        if (!result.isEmpty()) {
            return result;
        }
        return new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
	public List<SpecieTaxaSubheading> getSubheadingBySpecieTaxa(SpecieTaxa specieTaxa){
        initialize();
        CriteriaBuilder cb = getCriteriaBuilder();
        clearWherePredicates();
        clearOrderByPredicates();
        addWherePredicate(cb.and(
                cb.equal(getRecord().get("stasStatus"), true),
                cb.equal(getRecord().get("specieTaxa"), specieTaxa)
        ) );
        List<SpecieTaxaSubheading> result = buildQuery().getResultList();
        if (!result.isEmpty()) {
            return result;
        }
        return new ArrayList<>();
    }
}