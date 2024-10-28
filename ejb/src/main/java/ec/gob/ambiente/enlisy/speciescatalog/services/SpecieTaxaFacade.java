package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.math.BigInteger;
import java.util.*;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.speciescatalog.model.HigherClassification;
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecieTaxa;

/**
 * Servicios para la administracion de las publicaciones del especialista
 *
 * @author EXCO
 *
 */
@Stateless
public class SpecieTaxaFacade extends AbstractFacade<SpecieTaxa, Integer> {

	public SpecieTaxaFacade() {
		super(SpecieTaxa.class, Integer.class);
	}

	/**
	 * Guardar especie
	 *
	 * @param specieTaxa
	 * @param user
	 * @return
	 */
	public SpecieTaxa save(SpecieTaxa specieTaxa, User user) {
		SpecieTaxa specie = null;
		try {
			if (specieTaxa.getSptaId() == null || specieTaxa.getSptaId().compareTo(Integer.valueOf(0)) == 0) {

				specieTaxa.setSptaStatus(true);
				specieTaxa.setSptaUserCreate(user.getUserName());
				specieTaxa.setSptaDateCreate(new Date());
				specie = create(specieTaxa);

			} else {
				specieTaxa.setSptaDateUpdate(new Date());
				specieTaxa.setSptaUserUpdate(user.getUserName());
				specie = edit(specieTaxa);
			}

			return specie;
		} catch (Exception ex) {
			return specie;
		}
	}

	@SuppressWarnings("unchecked")
	public List<SpecieTaxa> findSpeciesByHigherClassification(HigherClassification classification) {
		try {
			Query query = super.getEntityManager().createQuery(
					"select s from SpecieTaxa s where s.sptaHierarchyCode like :parentId order by s.sptaScientificName");
			String filter = classification.getHiclIdParent().getHiclId() + ";" + classification.getHiclId() + "%";
			query.setParameter("parentId", filter);
			List<SpecieTaxa> result = (List<SpecieTaxa>) query.getResultList();
			if (result.size() > 0) {
				return result;
			}
		} catch (NoResultException e) {
			return new ArrayList<SpecieTaxa>();
		}
		return new ArrayList<SpecieTaxa>();
	}

	@SuppressWarnings("unchecked")
	public List<SpecieTaxa> findSpeciesByHigherClassificationId(int parentId) {
		try {
			Query query = super.getEntityManager().createQuery(
					"select s from SpecieTaxa s where s.higherClassification.hiclId=:parentId order by s.sptaScientificName");
			query.setParameter("parentId", parentId);
			List<SpecieTaxa> result = (List<SpecieTaxa>) query.getResultList();
			if (result.size() > 0) {
				return result;
			}
		} catch (NoResultException e) {
			return new ArrayList<SpecieTaxa>();
		}
		return new ArrayList<SpecieTaxa>();
	}

	/**********************************************************************
	 * Metodos para procesos de Listas Rojas
	 **********************************************************************/

	@SuppressWarnings("unchecked")
	public List<SpecieTaxa> findSpeciesByFilters(String hrCode, boolean endemic, int firstResult, int maxResults) {
		try {
			Query query = super.getEntityManager().createQuery("select s from SpecieTaxa s where "
					+ "s.sptaHierarchyCode like :hrCode " + "and s.sptaEndemic=:endemic " + "and s.sptaEcuador=true "
					+ "order by s.sptaScientificName ");
			query.setParameter("hrCode", hrCode);
			query.setParameter("endemic", endemic);
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResults);
			List<SpecieTaxa> result = (List<SpecieTaxa>) query.getResultList();
			if (result.size() > 0) {
				return result;
			}
		} catch (NoResultException e) {
			return new ArrayList<SpecieTaxa>();
		}
		return new ArrayList<SpecieTaxa>();
	}

	public long countSpeciesByFilters(String hrCode, boolean endemic) {
		try {
			Query query = super.getEntityManager().createQuery("select coalesce(count(*),0) from SpecieTaxa s where "
					+ "s.sptaHierarchyCode like :hrCode " + "and s.sptaEndemic=:endemic " + "and s.sptaEcuador=true ");
			query.setParameter("hrCode", hrCode);
			query.setParameter("endemic", endemic);
			Long result = (Long) query.getSingleResult();
			if (result != null) {
				return result;
			}
		} catch (NoResultException e) {
			return 0;
		}
		return 0;
	}

	/**********************************************************************
	 * Metodos para buscador simple de especies
	 **********************************************************************/

	@SuppressWarnings("unchecked")
	public List<SpecieTaxa> findBySimpleSearch(String text, String citesIds, String rlEcIds, String rlUiIds,
											   String agIds, String taxoIds, int firstResult, int maxResults) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM biodiversity.species_taxa_common_name ");
			sql.append("WHERE 1=1 ");
			if (citesIds != null && !citesIds.equals("")) {
				sql.append("AND cite_id IN (" + citesIds + ") ");
			}
			if (rlEcIds != null && !rlEcIds.equals("")) {
				sql.append("AND rlec_id IN (" + rlEcIds + ") ");
			}
			if (rlUiIds != null && !rlUiIds.equals("")) {
				sql.append("AND rlui_id IN (" + rlUiIds + ") ");
			}
			if (taxoIds != null && !taxoIds.equals("")) {
				sql.append("AND spta_hierarchy_code LIKE '"+taxoIds+"' ");
			}
			if (text != null && !text.equals("")) {
				sql.append("AND (UPPER(spta_scientific_name) LIKE '%" + text.toUpperCase() + "%' ");
				sql.append("OR upper(vernacular_name) LIKE '%" + text.toUpperCase() + "%') ");
			}

			Query query = super.getEntityManager().createNativeQuery(sql.toString());
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResults);
			List<Object[]> registers = (List<Object[]>) query.getResultList();
			if (registers.size() > 0) {
				List<SpecieTaxa> result = new ArrayList<>(registers.size());
				for (Object[] reg : registers) {
					SpecieTaxa st = new SpecieTaxa();
					st.setSptaId((Integer) reg[0]);
					st.setSptaScientificName((String) reg[1]);
					st.setVenaVernacularName((String) reg[2]);
					st.setPrioritized((Boolean) reg[8]);
					result.add(st);
				}
				return result;
			}

		} catch (NoResultException e) {
			return new ArrayList<SpecieTaxa>();
		}
		return new ArrayList<SpecieTaxa>();
	}

	public long countSpeciesBySimpleSearch(String text, String citesIds, String rlEcIds, String rlUiIds, String agIds, String taxoIds) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT COALESCE(count(*),0) FROM biodiversity.species_taxa_common_name ");
			sql.append("WHERE 1=1 ");
			if (citesIds != null && !citesIds.equals("")) {
				sql.append("and cite_id in (" + citesIds + ") ");
			}
			if (rlEcIds != null && !rlEcIds.equals("")) {
				sql.append("and rlec_id in (" + rlEcIds + ") ");
			}
			if (rlUiIds != null && !rlUiIds.equals("")) {
				sql.append("and rlui_id in (" + rlUiIds + ") ");
			}
			if (taxoIds != null && !taxoIds.equals("")) {
				sql.append("AND spta_hierarchy_code LIKE '"+taxoIds+"' ");
			}
			if (text != null && !text.equals("")) {
				sql.append("AND (UPPER(spta_scientific_name) LIKE '%" + text.toUpperCase() + "%' ");
				sql.append("OR upper(vernacular_name) LIKE '%" + text.toUpperCase() + "%') ");
			}
			Query query = super.getEntityManager().createNativeQuery(sql.toString());
			BigInteger result = (BigInteger) query.getSingleResult();
			if (result != null) {
				return result.longValue();
			}
		} catch (NoResultException e) {
			return 0;
		}
		return 0;
	}

	/**
	 * Obtener la lista de especies segun los filtros de clasificacion
	 *
	 * @param sql
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public List<Object[]> getSpeciesGrid(String sql) {
		List<Object[]> result = new ArrayList<Object[]>();
		try {
			Query q;
			q = getEntityManager().createNativeQuery(sql);

			result = (List<Object[]>) q.getResultList();
			if (q.getResultList() == null) {
				result = new ArrayList<Object[]>();
			}
		} catch (Exception ex) {

		}
		return result;
	}

	/**
	 * Cambiar el campo de la jerarquia de todos los hijos de la especie a
	 * actualizar
	 *
	 * @param idClasificacionAActualizar
	 * @param idNuevoPadre
	 * @param likeBuscar
	 * @param codigoAReemplazar
	 * @param codigoReemplazar
	 * @param userUpdate
	 * @return
	 */

	public boolean actualizarEspecie(String likeBuscar, String codigoAReemplazar, String codigoReemplazar,
									 String userUpdate,String codigoNombresAReemplazar,String codigoNombresReemplazar) {
		boolean resultado = false;
		try {

			Query q;
			q = getEntityManager().createNativeQuery(
					"UPDATE biodiversity.species_taxa SET spta_hierarchy_code = REPLACE (spta_hierarchy_code, ?1 , ?2), spta_user_update = ?3, spta_higher_classification=REPLACE (spta_higher_classification, ?5 , ?6)  where spta_hierarchy_code LIKE ?4");
			q.setParameter(1, codigoAReemplazar);
			q.setParameter(2, codigoReemplazar);
			q.setParameter(3, userUpdate);
			q.setParameter(4, likeBuscar);
			q.setParameter(5, codigoNombresAReemplazar);
			q.setParameter(6, codigoNombresReemplazar);

			q.executeUpdate();
			resultado = true;
		} catch (Exception e) {
			resultado = false;
		}
		return resultado;
	}

	/**
	 * Actualizar el padre de la especie
	 *
	 * @param idClasificacionAActualizar
	 * @param idNuevoPadre
	 * @return
	 */

	public boolean actualizarPadreEspecie(Integer idClasificacionAActualizar, Integer idNuevoPadre, String userUpdate,
										  String nuevoCodigo, String nuevoNombrePadre,String nuevoConcaNombre) {
		boolean resultado = false;
		try {

			Query q;
			q = getEntityManager().createNativeQuery(
					"UPDATE biodiversity.species_taxa SET hicl_id = ?1, spta_user_update = ?2, spta_hierarchy_code = ?3, spta_higher_classification = ?5 where spta_id = ?4");
			q.setParameter(1, idNuevoPadre);
			q.setParameter(2, userUpdate);
			q.setParameter(3, nuevoCodigo);
			q.setParameter(4, idClasificacionAActualizar);
			q.setParameter(5, nuevoConcaNombre);

			q.executeUpdate();
			resultado = true;
		} catch (Exception e) {
			resultado = false;
		}
		return resultado;
	}

	/**
	 * Buscar coincidencias nombre cientifico
	 * @param nombreCientifico
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public List<Object[]> buscarCoincidenciasNombre(String nombreCientifico) {
		List<Object[]> result = null;

		try {

			Query q;
			q = getEntityManager().createNativeQuery(
					"SELECT spta_id,spta_scientific_gui,spta_taxon_rank_name,spta_scientific_name,spta_hierarchy_code, tara_id, similarity(spta_scientific_name, ?1) AS sml " +
							"  from biodiversity.species_taxa where  similarity(spta_scientific_name, ?2) >= 0.4 order by similarity(spta_scientific_name, ?3)");
			q.setParameter(1, nombreCientifico);
			q.setParameter(2, nombreCientifico);
			q.setParameter(3, nombreCientifico);

			result=q.getResultList();

		} catch (Exception e) {
			result = null;
		}
		return result;

	}


	/**
	 * Buscar por nombre cientifico y rango
	 * @param name
	 * @param idRank
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public List<SpecieTaxa> findByScientificNameAndRank(String name, Integer idRank) {
		try {
			Query query = super.getEntityManager().createQuery(
					"select o from SpecieTaxa o where upper(o.sptaScientificName) = :nombre and o.sptaStatus = true and o.taxaRank.taraId = :idRango");
			query.setParameter("nombre", name.toUpperCase());
			query.setParameter("idRango", idRank);
			List<SpecieTaxa> result = (List<SpecieTaxa>) query.getResultList();
			if (result.size() > 0)
				return result;
		} catch (NoResultException e) {
			return new ArrayList<SpecieTaxa>();
		}
		return new ArrayList<SpecieTaxa>();
	}

	@SuppressWarnings("unchecked")
	public SpecieTaxa findByGuiAndRank(String gui, Integer idRank) {
		try {
			Query query = super.getEntityManager().createQuery(
					"select o from SpecieTaxa o where o.sptaScientificGui = :gui and o.sptaStatus = true and o.taxaRank.taraId = :idRango");
			query.setParameter("gui", gui);
			query.setParameter("idRango", idRank);
			List<SpecieTaxa> result = (List<SpecieTaxa>) query.getResultList();
			if (result.size() > 0)
				return result.get(0);
		} catch (NoResultException e) {
			return null;
		}
		return null;
	}

	/**
	 * Buscar un registro por id en la vista materializada de nombres comunes
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SpecieTaxa> findByIdViewVernacularName(Integer id) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM biodiversity.species_taxa_common_name ");
			sql.append("WHERE spta_id = ");
			sql.append(id);

			Query query = super.getEntityManager().createNativeQuery(sql.toString());
			List<Object[]> registers = (List<Object[]>) query.getResultList();
			if (registers.size() > 0) {
				List<SpecieTaxa> result = new ArrayList<>(registers.size());
				for (Object[] reg : registers) {
					SpecieTaxa st = new SpecieTaxa();
					st.setSptaId((Integer) reg[0]);
					st.setSptaScientificName((String) reg[1]);
					st.setVenaVernacularName((String) reg[2]);
					result.add(st);
				}
				return result;
			}

		} catch (NoResultException e) {
			return new ArrayList<SpecieTaxa>();
		}
		return new ArrayList<SpecieTaxa>();
	}

	public Integer getIdInfTecnico() {
		Integer result = null;
		try {
			Query q;
			q = getEntityManager().createNativeQuery("select rete_id from public.report_template where rete_process_code = 'ANALISIS_RIESGO_INF_TEC' and rete_status = true");

			result = (Integer) q.getSingleResult();

		} catch (Exception ex) {
			return null;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public SpecieTaxa findByScientificGui(String scientificGui) {
		try {
			Query query = super.getEntityManager().createQuery(
					"select o from SpecieTaxa o where upper(o.sptaScientificGui) = :gui and o.sptaStatus = true ");
			query.setParameter("gui", scientificGui.toUpperCase());
			List<SpecieTaxa> result = (List<SpecieTaxa>) query.getResultList();
			if (result.size() > 0)
				return result.get(0);
		} catch (NoResultException e) {
			return new SpecieTaxa();
		}
		return new SpecieTaxa();
	}
}