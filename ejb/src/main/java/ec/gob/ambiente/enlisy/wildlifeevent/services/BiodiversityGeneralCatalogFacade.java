package ec.gob.ambiente.enlisy.wildlifeevent.services;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import ec.gob.ambiente.enlisy.dao.AbstractFacadeModel;
import ec.gob.ambiente.enlisy.wildlifeevent.model.BiodiversityCatalogType;
import ec.gob.ambiente.enlisy.wildlifeevent.model.BiodiversityGeneralCatalog;

@Stateless
public class BiodiversityGeneralCatalogFacade extends AbstractFacadeModel<BiodiversityGeneralCatalog> {

	public BiodiversityGeneralCatalogFacade() {
		super(BiodiversityGeneralCatalog.class);
	}

	private static final long serialVersionUID = 1L;

	@Override
	public void configure(String filter) {
		initialize();
	}

	@SuppressWarnings("unchecked")
	public BiodiversityGeneralCatalog findByDescription(String description) {
		initialize();
		CriteriaBuilder cb = getCriteriaBuilder();
		clearWherePredicates();
		clearOrderByPredicates();
		addWherePredicate(cb.equal(getRecord().get("bigcDescription"), description));
		List<BiodiversityGeneralCatalog> result = buildQuery().getResultList();
		if (!result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public BiodiversityGeneralCatalog findByCode(String code) {
		initialize();
		CriteriaBuilder cb = getCriteriaBuilder();
		clearWherePredicates();
		clearOrderByPredicates();
		addWherePredicate(cb.equal(getRecord().get("bigcCode"), code));
		List<BiodiversityGeneralCatalog> result = buildQuery().getResultList();
		if (!result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BiodiversityGeneralCatalog> findByCodeDesag(Integer code) {
		initialize();
		CriteriaBuilder cb = getCriteriaBuilder();
		clearWherePredicates();
		clearOrderByPredicates();
		//addWherePredicate(cb.equal(getRecord().get("bictId"), code));
		addWherePredicate(cb.equal(getRecord().get("bigcValue"), "1"));
		List<BiodiversityGeneralCatalog> result = buildQuery().getResultList();
		if (!result.isEmpty()) {
			return result;
		}
		return new ArrayList<BiodiversityGeneralCatalog>();
	}
	
	@SuppressWarnings("unchecked")
	public List<BiodiversityGeneralCatalog> findByParent(BiodiversityGeneralCatalog parentId) {
		initialize();
		CriteriaBuilder cb = getCriteriaBuilder();
		clearWherePredicates();
		clearOrderByPredicates();
		addWherePredicate(cb.equal(getRecord().get("biodiversityGeneralCatalog"), parentId));
		List<BiodiversityGeneralCatalog> result = buildQuery().getResultList();
		if (!result.isEmpty()) {
			return result;
		}
		return new ArrayList<BiodiversityGeneralCatalog>();
	}
	
	@SuppressWarnings("unchecked")
	public List<BiodiversityGeneralCatalog> findByBict(BiodiversityCatalogType bictId) {
		initialize();
		CriteriaBuilder cb = getCriteriaBuilder();
		clearWherePredicates();
		clearOrderByPredicates();
		addWherePredicate(cb.equal(getRecord().get("biodiversityCatalogType"), bictId));
		List<BiodiversityGeneralCatalog> result = buildQuery().getResultList();
		if (!result.isEmpty()) {
			return result;
		}
		return new ArrayList<BiodiversityGeneralCatalog>();
	}
	
	/**
	 * Obtener etapas de vida por Reino
	 * @param bictId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<BiodiversityGeneralCatalog> findByBictReino(BiodiversityCatalogType bictId, String reino) {
		initialize();
		CriteriaBuilder cb = getCriteriaBuilder();
		clearWherePredicates();
		clearOrderByPredicates();
		addWherePredicate(cb.equal(getRecord().get("biodiversityCatalogType"), bictId));
		addWherePredicate(cb.like(getRecord().get("bigcValue").as(String.class), reino));
		List<BiodiversityGeneralCatalog> result = buildQuery().getResultList();
		if (!result.isEmpty()) {
			return result;
		}
		return new ArrayList<BiodiversityGeneralCatalog>();
	}
	
	@SuppressWarnings("unchecked")
	public List<BiodiversityGeneralCatalog> findByCodeDesagGen(String code) {
		initialize();
		CriteriaBuilder cb = getCriteriaBuilder();
		clearWherePredicates();
		clearOrderByPredicates();
		addWherePredicate(cb.equal(getRecord().get("bigcValue"), "nuevo_destino_final"));
		List<BiodiversityGeneralCatalog> result = buildQuery().getResultList();
		if (!result.isEmpty()) {
			return result;
		}
		return new ArrayList<BiodiversityGeneralCatalog>();
	}
	
	@SuppressWarnings("unchecked")
	public List<BiodiversityGeneralCatalog> findByBictOrder(BiodiversityCatalogType bictId) {
		initialize();
		CriteriaBuilder cb = getCriteriaBuilder();
		clearWherePredicates();
		clearOrderByPredicates();
		addWherePredicate(cb.equal(getRecord().get("biodiversityCatalogType"), bictId));
		addOrderByPredicate(cb.asc(getRecord().get("bigcDescription")));
		List<BiodiversityGeneralCatalog> result = buildQuery().getResultList();
		if (!result.isEmpty()) {
			return result;
		}
		return new ArrayList<BiodiversityGeneralCatalog>();
	}

	@SuppressWarnings("unchecked")
	public List<BiodiversityGeneralCatalog> findByCodeType(String code) {
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT s FROM BiodiversityGeneralCatalog s where s.bigcStatus = true and s.biodiversityCatalogType.bictCode=:codigo order by s.bigcId");
			query.setParameter("codigo", code);
			List<BiodiversityGeneralCatalog> result=(List<BiodiversityGeneralCatalog>)query.getResultList();
			if(result.size()>0)
				return result;
		}catch(NoResultException e)
		{
			return new ArrayList<BiodiversityGeneralCatalog>();
		}
		return new ArrayList<BiodiversityGeneralCatalog>();
	}
}