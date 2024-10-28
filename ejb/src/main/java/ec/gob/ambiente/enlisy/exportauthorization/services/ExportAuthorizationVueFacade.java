package ec.gob.ambiente.enlisy.exportauthorization.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.criteria.*;

import ec.gob.ambiente.enlisy.dao.AbstractFacadeModel;
import ec.gob.ambiente.enlisy.exportauthorization.model.ExportAuthorizationVue;

@Stateless
public class ExportAuthorizationVueFacade extends AbstractFacadeModel<ExportAuthorizationVue>{

	private static final long serialVersionUID = 1L;
	
	public ExportAuthorizationVueFacade() {
		super(ExportAuthorizationVue.class);
	}
	
	@Override
	public void configure(String filter) {
		initialize();
	}
	
	@SuppressWarnings("unchecked")
	public ExportAuthorizationVue findByCode(String code) {
		initialize();
		CriteriaBuilder cb = getCriteriaBuilder();
		clearWherePredicates();
		clearOrderByPredicates();
		addWherePredicate(cb.equal(getRecord().get("eavuCode"), code));
		List<ExportAuthorizationVue> result = buildQuery().getResultList();
		if (!result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<ExportAuthorizationVue> findAllWithoutProcessing(){
		initialize();
		CriteriaBuilder cb = getCriteriaBuilder();
		clearWherePredicates();
		clearOrderByPredicates();
		addWherePredicate(cb.isNull(getRecord().get("eavuCode")));
		return buildQuery().getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<ExportAuthorizationVue> findAllCorrectionRequired(){
		initialize();
		CriteriaBuilder cb = getCriteriaBuilder();
		clearWherePredicates();
		clearOrderByPredicates();
		addWherePredicate(cb.equal(getRecord().get("eavuStatusVue"), 410));
		return buildQuery().getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<ExportAuthorizationVue> findAllByDate(){
		initialize();
		CriteriaBuilder cb = getCriteriaBuilder();
		clearWherePredicates();
		clearOrderByPredicates();
		addOrderByPredicate(cb.desc(getRecord().get("eavuRegisterDate")));
		return buildQuery().getResultList();
	}
	
	public Date getMaxRequestNumber() {
		String sql = "SELECT COALESCE(MAX(eavu_register_date),to_date('01/01/1900','dd/mm/yyyy')) "
					+"FROM biodiversity.export_authorization_vue WHERE eavu_status = 't' AND eavu_code is not null ";
		return (Date)getEntityManager().createNativeQuery(sql).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<ExportAuthorizationVue> findAllByLevelAndSource(Integer levelApproval, Integer sourceCode){
		initialize();
		CriteriaBuilder cb = getCriteriaBuilder();
		clearWherePredicates();
		clearOrderByPredicates();
		addWherePredicate(cb.and(
				cb.equal(getRecord().get("eavuLevelApproval"), levelApproval),
				cb.equal(getRecord().get("eavuInformationSourceId"), sourceCode),
				cb.equal(getRecord().get("eavuStatus"), true)
		));
		addOrderByPredicate(cb.desc(getRecord().get("eavuRegisterDate")));
		return buildQuery().getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<ExportAuthorizationVue> findAllBySource(Integer sourceCode, int firstResult, int maxResults,
														String filter, Date year, Integer citesCode){
		initialize();
		CriteriaBuilder cb = getCriteriaBuilder();
		clearWherePredicates();
		clearOrderByPredicates();
		if (filter!=null && !filter.isEmpty()){
			if (year!=null){
				addWherePredicate(cb.and(
						cb.equal(getRecord().get("eavuInformationSourceId"), sourceCode),
						cb.equal(getRecord().get("eavuPermitTypeId"), citesCode),
						cb.equal(getRecord().get("eavuRequestNumber"), filter),
						cb.equal(getRecord().get("eavuStatus"), true),
						cb.equal(getRecord().get("eavuRequestDate"),year)
				));
			}else{
				addWherePredicate(cb.and(
						cb.equal(getRecord().get("eavuInformationSourceId"), sourceCode),
						cb.equal(getRecord().get("eavuPermitTypeId"), citesCode),
						cb.equal(getRecord().get("eavuRequestNumber"), filter),
						cb.equal(getRecord().get("eavuStatus"), true)
				));
			}
		}else{
			if (year!=null){
				addWherePredicate(cb.and(
						cb.equal(getRecord().get("eavuInformationSourceId"), sourceCode),
						cb.equal(getRecord().get("eavuPermitTypeId"), citesCode),
						cb.equal(getRecord().get("eavuStatus"), true),
						cb.equal(getRecord().get("eavuRequestDate"),year)
				));
			}else{
				addWherePredicate(cb.and(
						cb.equal(getRecord().get("eavuInformationSourceId"), sourceCode),
						cb.equal(getRecord().get("eavuPermitTypeId"), citesCode),
						cb.equal(getRecord().get("eavuStatus"), true)
				));
			}
		}
		addOrderByPredicate(cb.desc(getRecord().get("eavuRequestDate")));
		Query query = buildQuery();
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		return query.getResultList();
	}

	public long countBySource(Integer sourceCode, String filter, Date year, Integer citesCode){
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> countCriteria = cb.createQuery(Long.class);
		Root<ExportAuthorizationVue> rt = countCriteria.from(ExportAuthorizationVue.class);
		countCriteria.select(cb.count(rt));
		if (filter!=null && !filter.isEmpty()){
			if (year!=null){
				countCriteria.where(cb.and(
						cb.equal(rt.get("eavuInformationSourceId"), sourceCode),
						cb.equal(rt.get("eavuPermitTypeId"), citesCode),
						cb.equal(rt.get("eavuRequestNumber"), filter),
						cb.equal(rt.get("eavuStatus"), true),
						cb.equal(rt.get("eavuRequestDate"),year)
				));
			}else{
				countCriteria.where(cb.and(
						cb.equal(rt.get("eavuInformationSourceId"), sourceCode),
						cb.equal(rt.get("eavuPermitTypeId"), citesCode),
						cb.equal(rt.get("eavuRequestNumber"), filter),
						cb.equal(rt.get("eavuStatus"), true)
				));
			}
		}else{
			if (year!=null){
				countCriteria.where(cb.and(
						cb.equal(rt.get("eavuInformationSourceId"), sourceCode),
						cb.equal(rt.get("eavuPermitTypeId"), citesCode),
						cb.equal(rt.get("eavuStatus"), true),
						cb.equal(rt.get("eavuRequestDate"),year)
				));
			}else{
				countCriteria.where(cb.and(
						cb.equal(rt.get("eavuInformationSourceId"), sourceCode),
						cb.equal(rt.get("eavuPermitTypeId"), citesCode),
						cb.equal(rt.get("eavuStatus"), true)
				));
			}
		}
		return getEntityManager().createQuery(countCriteria).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<ExportAuthorizationVue> findAllBySource(Integer sourceCode){
		initialize();
		CriteriaBuilder cb = getCriteriaBuilder();
		clearWherePredicates();
		clearOrderByPredicates();
		addWherePredicate(cb.and(
				cb.equal(getRecord().get("eavuInformationSourceId"), sourceCode),
				cb.equal(getRecord().get("eavuStatus"), true)
		));
		addOrderByPredicate(cb.desc(getRecord().get("eavuRegisterDate")));
		return buildQuery().getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<ExportAuthorizationVue> findAllBySourceNotNull(Integer sourceCode){
		initialize();
		CriteriaBuilder cb = getCriteriaBuilder();
		clearWherePredicates();
		clearOrderByPredicates();
		addWherePredicate(
				cb.and(
						cb.isNotNull(getRecord().get("eavuStatusVue")),
						cb.equal(getRecord().get("eavuInformationSourceId"), sourceCode),
						cb.equal(getRecord().get("eavuStatus"), true)
				));
		addOrderByPredicate(cb.desc(getRecord().get("eavuRegisterDate")));
		return buildQuery().getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<ExportAuthorizationVue> findAllForTrace(Integer vueCode, Integer historyCode, int firstResult, int maxResults,
														Integer provinceId, Date startDate, Date endDate, String mcmFilter, List<Integer> speciesResults){
		initialize();
		CriteriaBuilder cb = getCriteriaBuilder();
		clearWherePredicates();
		clearOrderByPredicates();
		List<Integer> ids = new ArrayList<>();
		ids.add(vueCode);
		ids.add(historyCode);
		Expression<String> parentExpression = getRecord().get("eavuInformationSourceId");
		Predicate parentPredicate = parentExpression.in(ids);

		Expression<String> parentExpression2 = getRecord().get("eavuId");
		Predicate parentPredicate2 = parentExpression2.in(speciesResults);

		if (provinceId!=null){
			if (startDate!=null && endDate!=null){
				if (mcmFilter!=null){
					if (!speciesResults.isEmpty()){
						addWherePredicate(cb.and(
								parentPredicate,
								parentPredicate2,
								cb.equal(getRecord().get("eavuExporterProvinceId"),provinceId),
								cb.between(getRecord().<Date>get("eavuRequestDate"),startDate,endDate),
								cb.like(cb.upper(getRecord().<String>get("eavuExporterName")), "%"+mcmFilter.toUpperCase()+"%"),
								cb.equal(getRecord().get("eavuStatus"), true)
						));
					}else{
						addWherePredicate(cb.and(
								parentPredicate,
								cb.equal(getRecord().get("eavuExporterProvinceId"),provinceId),
								cb.between(getRecord().<Date>get("eavuRequestDate"),startDate,endDate),
								cb.like(cb.upper(getRecord().<String>get("eavuExporterName")), "%"+mcmFilter.toUpperCase()+"%"),
								cb.equal(getRecord().get("eavuStatus"), true)
						));
					}
				}else{
					if (!speciesResults.isEmpty()){
						addWherePredicate(cb.and(
								parentPredicate,
								parentPredicate2,
								cb.equal(getRecord().get("eavuExporterProvinceId"),provinceId),
								cb.between(getRecord().<Date>get("eavuRequestDate"),startDate,endDate),
								cb.equal(getRecord().get("eavuStatus"), true)
						));
					}else{
						addWherePredicate(cb.and(
								parentPredicate,
								cb.equal(getRecord().get("eavuExporterProvinceId"),provinceId),
								cb.between(getRecord().<Date>get("eavuRequestDate"),startDate,endDate),
								cb.equal(getRecord().get("eavuStatus"), true)
						));
					}
				}
			}else{
				if (mcmFilter!=null){
					if (!speciesResults.isEmpty()){
						addWherePredicate(cb.and(
								parentPredicate,
								parentPredicate2,
								cb.equal(getRecord().get("eavuExporterProvinceId"),provinceId),
								cb.like(cb.upper(getRecord().<String>get("eavuExporterName")), "%"+mcmFilter.toUpperCase()+"%"),
								cb.equal(getRecord().get("eavuStatus"), true)
						));
					}else{
						addWherePredicate(cb.and(
								parentPredicate,
								cb.equal(getRecord().get("eavuExporterProvinceId"),provinceId),
								cb.like(cb.upper(getRecord().<String>get("eavuExporterName")), "%"+mcmFilter.toUpperCase()+"%"),
								cb.equal(getRecord().get("eavuStatus"), true)
						));
					}
				}else{
					if (!speciesResults.isEmpty()){
						addWherePredicate(cb.and(
								parentPredicate,
								parentPredicate2,
								cb.equal(getRecord().get("eavuExporterProvinceId"),provinceId),
								cb.equal(getRecord().get("eavuStatus"), true)
						));
					}else{
						addWherePredicate(cb.and(
								parentPredicate,
								cb.equal(getRecord().get("eavuExporterProvinceId"),provinceId),
								cb.equal(getRecord().get("eavuStatus"), true)
						));
					}
				}
			}
		}else{
			if (startDate!=null && endDate!=null){
				if (mcmFilter!=null){
					if (!speciesResults.isEmpty()){
						addWherePredicate(cb.and(
								parentPredicate,
								parentPredicate2,
								cb.between(getRecord().<Date>get("eavuRequestDate"),startDate,endDate),
								cb.like(cb.upper(getRecord().<String>get("eavuExporterName")), "%"+mcmFilter.toUpperCase()+"%"),
								cb.equal(getRecord().get("eavuStatus"), true)
						));
					}else{
						addWherePredicate(cb.and(
								parentPredicate,
								cb.between(getRecord().<Date>get("eavuRequestDate"),startDate,endDate),
								cb.like(cb.upper(getRecord().<String>get("eavuExporterName")), "%"+mcmFilter.toUpperCase()+"%"),
								cb.equal(getRecord().get("eavuStatus"), true)
						));
					}
				}else{
					if (!speciesResults.isEmpty()){
						addWherePredicate(cb.and(
								parentPredicate,
								parentPredicate2,
								cb.between(getRecord().<Date>get("eavuRequestDate"),startDate,endDate),
								cb.equal(getRecord().get("eavuStatus"), true)
						));
					}else{
						addWherePredicate(cb.and(
								parentPredicate,
								cb.between(getRecord().<Date>get("eavuRequestDate"),startDate,endDate),
								cb.equal(getRecord().get("eavuStatus"), true)
						));
					}
				}
			}else{
				if (mcmFilter!=null){
					if (!speciesResults.isEmpty()){
						addWherePredicate(cb.and(
								parentPredicate,
								parentPredicate2,
								cb.like(cb.upper(getRecord().<String>get("eavuExporterName")), "%"+mcmFilter.toUpperCase()+"%"),
								cb.equal(getRecord().get("eavuStatus"), true)
						));
					}else{
						addWherePredicate(cb.and(
								parentPredicate,
								cb.like(cb.upper(getRecord().<String>get("eavuExporterName")), "%"+mcmFilter.toUpperCase()+"%"),
								cb.equal(getRecord().get("eavuStatus"), true)
						));
					}
				}else{
					if(!speciesResults.isEmpty()){
						addWherePredicate(cb.and(
								parentPredicate,
								parentPredicate2,
								cb.equal(getRecord().get("eavuStatus"), true)
						));
					}else{
						addWherePredicate(cb.and(
								parentPredicate,
								cb.equal(getRecord().get("eavuStatus"), true)
						));
					}
				}
			}
		}

		addOrderByPredicate(cb.desc(getRecord().get("eavuRequestDate")));
		Query query = buildQuery();
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		return query.getResultList();
	}

	public long countForTrace(Integer vueCode, Integer historyCode, Integer provinceId, Date startDate, Date endDate,
							  String mcmFilter, List<Integer> speciesResults){
		initialize();
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> countCriteria = cb.createQuery(Long.class);
		Root<ExportAuthorizationVue> rt = countCriteria.from(ExportAuthorizationVue.class);
		countCriteria.select(cb.count(rt));
		List<Integer> ids = new ArrayList<>();
		ids.add(vueCode);
		ids.add(historyCode);
		Expression<String> parentExpression = rt.get("eavuInformationSourceId");
		Predicate parentPredicate = parentExpression.in(ids);

		Expression<String> parentExpression2 = rt.get("eavuId");
		Predicate parentPredicate2 = parentExpression2.in(speciesResults);

		if (provinceId !=null){
			if (startDate!=null && endDate!=null){
				if (mcmFilter!=null){
					if (!speciesResults.isEmpty()){
						countCriteria.where(cb.and(
								parentPredicate,
								parentPredicate2,
								cb.equal(rt.get("eavuExporterProvinceId"),provinceId),
								cb.between(rt.<Date>get("eavuRequestDate"),startDate,endDate),
								cb.like(cb.upper(rt.<String>get("eavuExporterName")), "%"+mcmFilter.toUpperCase()+"%"),
								cb.equal(rt.get("eavuStatus"), true)));
					}else{
						countCriteria.where(cb.and(
								parentPredicate,
								cb.equal(rt.get("eavuExporterProvinceId"),provinceId),
								cb.between(rt.<Date>get("eavuRequestDate"),startDate,endDate),
								cb.like(cb.upper(rt.<String>get("eavuExporterName")), "%"+mcmFilter.toUpperCase()+"%"),
								cb.equal(rt.get("eavuStatus"), true)));
					}
				}else{
					if (!speciesResults.isEmpty()){
						countCriteria.where(cb.and(
								parentPredicate,
								parentPredicate2,
								cb.equal(rt.get("eavuExporterProvinceId"),provinceId),
								cb.between(rt.<Date>get("eavuRequestDate"),startDate,endDate),
								cb.equal(rt.get("eavuStatus"), true)));
					}else{
						countCriteria.where(cb.and(
								parentPredicate,
								cb.equal(rt.get("eavuExporterProvinceId"),provinceId),
								cb.between(rt.<Date>get("eavuRequestDate"),startDate,endDate),
								cb.equal(rt.get("eavuStatus"), true)));
					}
				}
			}else{
				if (mcmFilter!=null){
					if (!speciesResults.isEmpty()){
						countCriteria.where(cb.and(
								parentPredicate,
								parentPredicate2,
								cb.equal(rt.get("eavuExporterProvinceId"),provinceId),
								cb.like(cb.upper(rt.<String>get("eavuExporterName")), "%"+mcmFilter.toUpperCase()+"%"),
								cb.equal(rt.get("eavuStatus"), true)));
					}else{
						countCriteria.where(cb.and(
								parentPredicate,
								cb.equal(rt.get("eavuExporterProvinceId"),provinceId),
								cb.like(cb.upper(rt.<String>get("eavuExporterName")), "%"+mcmFilter.toUpperCase()+"%"),
								cb.equal(rt.get("eavuStatus"), true)));
					}
				}else{
					if (!speciesResults.isEmpty()){
						countCriteria.where(cb.and(
								parentPredicate,
								parentPredicate2,
								cb.equal(rt.get("eavuExporterProvinceId"),provinceId),
								cb.equal(rt.get("eavuStatus"), true)));
					}else{
						countCriteria.where(cb.and(
								parentPredicate,
								cb.equal(rt.get("eavuExporterProvinceId"),provinceId),
								cb.equal(rt.get("eavuStatus"), true)));
					}
				}
			}
		}else{
			if (startDate!=null && endDate!=null){
				if (mcmFilter!=null){
					if (!speciesResults.isEmpty()){
						countCriteria.where(cb.and(
								parentPredicate,
								parentPredicate2,
								cb.between(rt.<Date>get("eavuRequestDate"),startDate,endDate),
								cb.like(cb.upper(rt.<String>get("eavuExporterName")), "%"+mcmFilter.toUpperCase()+"%"),
								cb.equal(rt.get("eavuStatus"), true)));
					}else{
						countCriteria.where(cb.and(
								parentPredicate,
								cb.between(rt.<Date>get("eavuRequestDate"),startDate,endDate),
								cb.like(cb.upper(rt.<String>get("eavuExporterName")), "%"+mcmFilter.toUpperCase()+"%"),
								cb.equal(rt.get("eavuStatus"), true)));
					}
				}else{
					if (!speciesResults.isEmpty()){
						countCriteria.where(cb.and(
								parentPredicate,
								parentPredicate2,
								cb.between(rt.<Date>get("eavuRequestDate"),startDate,endDate),
								cb.equal(rt.get("eavuStatus"), true)));
					}else{
						countCriteria.where(cb.and(
								parentPredicate,
								cb.between(rt.<Date>get("eavuRequestDate"),startDate,endDate),
								cb.equal(rt.get("eavuStatus"), true)));
					}
				}
			}else{
				if (mcmFilter!=null){
					if (!speciesResults.isEmpty()){
						countCriteria.where(cb.and(
								parentPredicate,
								parentPredicate2,
								cb.like(cb.upper(rt.<String>get("eavuExporterName")), "%"+mcmFilter.toUpperCase()+"%"),
								cb.equal(rt.get("eavuStatus"), true)));
					}else{
						countCriteria.where(cb.and(
								parentPredicate,
								cb.like(cb.upper(rt.<String>get("eavuExporterName")), "%"+mcmFilter.toUpperCase()+"%"),
								cb.equal(rt.get("eavuStatus"), true)));
					}
				}else{
					if (!speciesResults.isEmpty()){
						countCriteria.where(cb.and(
								parentPredicate,
								parentPredicate2,
								cb.equal(rt.get("eavuStatus"), true)));
					}else{
						countCriteria.where(cb.and(
								parentPredicate,
								cb.equal(rt.get("eavuStatus"), true)));
					}
				}
			}
		}
		return getEntityManager().createQuery(countCriteria).getSingleResult();
	}

	@SuppressWarnings({ "unused", "unchecked" })
	public List<Integer> getPermitsCodes(String specieId){
		List<Integer> permits = new ArrayList<>();
		String jpql = "SELECT E.exportAuthorizationVue.eavuId FROM ExportAuthVueProduct E WHERE E.eavpStatus = true AND E.eavpSpeciesCode = :ID_ESPECIE";
		Query query = getEntityManager().createQuery(jpql);
		query.setParameter("ID_ESPECIE",specieId);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<ExportAuthorizationVue> findAllByUser(String userIdNumber){
		initialize();
		CriteriaBuilder cb = getCriteriaBuilder();
		clearWherePredicates();
		clearOrderByPredicates();
		addWherePredicate(
				cb.and(
						cb.equal(getRecord().get("eavuApplicantIdNumber"), userIdNumber),
						cb.equal(getRecord().get("eavuStatus"), true)
				));
		addOrderByPredicate(cb.desc(getRecord().get("eavuRegisterDate")));
		return buildQuery().getResultList();
	}

	public Integer getReportTemplateByProcessCode(String processCode) {
		Integer result = null;
		try {
			Query q;
			q = getEntityManager().createNativeQuery("select rete_id from public.report_template where rete_process_code = '"+processCode+"' and rete_status = true");

			result = (Integer) q.getSingleResult();

		} catch (Exception ex) {
			return null;
		}
		return result;
	}
}