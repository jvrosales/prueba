package ec.gob.ambiente.enlisy.wildlifeevent.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;

import ec.gob.ambiente.enlisy.dao.AbstractFacadeModel;
import ec.gob.ambiente.enlisy.wildlifeevent.model.GeneralData;

@Stateless
public class GeneralDataFacade extends AbstractFacadeModel<GeneralData> {

	private static final long serialVersionUID = 1L;
	public final String REGISTERED_STATUS = "R";
	public final String AUTHORIZED_STATUS = "A";
	public final String INSPECTED_STATUS = "I";
	public final String CLOSED_STATUS = "C";

	public GeneralDataFacade() {
		super(GeneralData.class);
	}

	@Override
	public void configure(String filter) {
		initialize();
	}

	@SuppressWarnings("unchecked")
	public List<GeneralData> findByWildLifeEvent(Integer code) {
		CriteriaBuilder cb = getCriteriaBuilder();
		clearWherePredicates();
		clearOrderByPredicates();
		addWherePredicate(cb.equal(getRecord().get("gedaEventType"), code));
		addOrderByPredicate(cb.desc(getRecord().get("gedaEventDate")));
		return buildQuery().getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<GeneralData> findByRunOverEvent(Integer code) {
		CriteriaBuilder cb = getCriteriaBuilder();
		clearWherePredicates();
		clearOrderByPredicates();
		addWherePredicate(cb.and(cb.isNotNull(getRecord().get("gedaCode")),cb.equal(getRecord().get("gedaEventType"), code)));
		addOrderByPredicate(cb.desc(getRecord().get("gedaDateUpdate")));
		return buildQuery().getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<GeneralData> findByRetentionEvent(Integer code) {
		CriteriaBuilder cb = getCriteriaBuilder();
		clearWherePredicates();
		clearOrderByPredicates();
		addWherePredicate(cb.equal(getRecord().get("gedaEventType"), code));
		//addWherePredicate(cb.notEqual(getRecord().get("gedaFinalizeStatus"), true));
		addOrderByPredicate(cb.desc(getRecord().get("gedaEventDate")));
		return buildQuery().getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<GeneralData> findByRescueEvent(Integer code) {
		CriteriaBuilder cb = getCriteriaBuilder();
		clearWherePredicates();
		clearOrderByPredicates();
		addWherePredicate(cb.equal(getRecord().get("gedaEventType"), code));
		//addWherePredicate(cb.notEqual(getRecord().get("gedaFinalizeStatus"), true));
		addOrderByPredicate(cb.desc(getRecord().get("gedaEventDate")));
		return buildQuery().getResultList();
	}

	@SuppressWarnings("unchecked")
	public GeneralData findByCode(String code) {
		initialize();
		CriteriaBuilder cb = getCriteriaBuilder();
		clearWherePredicates();
		clearOrderByPredicates();
		addWherePredicate(cb.equal(getRecord().get("gedaCode"), code));
		List<GeneralData> result = buildQuery().getResultList();
		if (!result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<GeneralData> findByRetentionAndRescueEvents(Integer retentionCode, Integer rescueCode, String status) {
		initialize();
		CriteriaBuilder cb = getCriteriaBuilder();
		clearWherePredicates();
		clearOrderByPredicates();
		addWherePredicate(cb.and(getRecord().get("gedaEventType").in(retentionCode, rescueCode),
				cb.equal(getRecord().get("gedaProcessStatus"), status)));
		addOrderByPredicate(cb.desc(getRecord().get("gedaEventDate")));
		List<GeneralData> result = buildQuery().getResultList();
		if (!result.isEmpty()) {
			return result;
		}
		return new ArrayList<GeneralData>();
	}

	public int maxNatusferaId(Integer code) {
		String sql = " SELECT COALESCE(MAX(geda_inaturalist_id),0) "
				+ " FROM biodiversity.general_data WHERE geda_event_type = :code  AND geda_status = 't'";
		return Integer.parseInt(
				getEntityManager().createNativeQuery(sql).setParameter("code", code).getSingleResult().toString());
	}

	public int maxInaturalistId(Integer code) {
		String sql = " SELECT COALESCE(MAX(geda_inaturalist_id),0) FROM " +
				"biodiversity.general_data WHERE geda_source = :code  AND geda_status = 't';";
		return Integer.parseInt(
				getEntityManager().createNativeQuery(sql).setParameter("code", code).getSingleResult().toString());
	}

	@SuppressWarnings("unchecked")
	public List<GeneralData> findByWildLifeEventByProcessStatus(Integer code, String status, Integer province) {
		initialize();
		CriteriaBuilder cb = getCriteriaBuilder();
		clearWherePredicates();
		clearOrderByPredicates();
		if (province == null) {
			addWherePredicate(cb.and(cb.equal(getRecord().get("gedaEventType"), code),
					cb.equal(getRecord().get("gedaProcessStatus"), status)));
		} else {
			addWherePredicate(cb.and(cb.equal(getRecord().get("gedaEventType"), code),
					cb.equal(getRecord().get("gedaProvinceId"), province),
					cb.equal(getRecord().get("gedaProcessStatus"), status)));
		}
		addOrderByPredicate(cb.desc(getRecord().get("gedaEventDate")));
		return buildQuery().getResultList();
	}
}