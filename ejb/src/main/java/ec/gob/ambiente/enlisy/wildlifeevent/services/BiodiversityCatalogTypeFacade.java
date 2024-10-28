package ec.gob.ambiente.enlisy.wildlifeevent.services;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import ec.gob.ambiente.enlisy.dao.AbstractFacadeModel;
import ec.gob.ambiente.enlisy.wildlifeevent.model.BiodiversityCatalogType;

@Stateless
public class BiodiversityCatalogTypeFacade extends AbstractFacadeModel<BiodiversityCatalogType>{

	private static final long serialVersionUID = 1L;
	
	public BiodiversityCatalogTypeFacade() {
		super(BiodiversityCatalogType.class);
	}
	
	@Override
	public void configure(String filter) {
		initialize();
	}
	
	@SuppressWarnings("unchecked")
	public BiodiversityCatalogType findByType(String type) {
		initialize();
		CriteriaBuilder cb = getCriteriaBuilder();
		clearWherePredicates();
		clearOrderByPredicates();
		addWherePredicate(cb.equal(getRecord().get("bictCode"), type));
		List<BiodiversityCatalogType> result = buildQuery().getResultList();
		if (!result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}
}
