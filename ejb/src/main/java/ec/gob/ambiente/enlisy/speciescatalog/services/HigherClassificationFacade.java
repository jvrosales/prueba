package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;

import ec.gob.ambiente.alfresco.connection.ConnectionCmisAlfrescoSUIA;
import ec.gob.ambiente.alfresco.connection.Documents;
import ec.gob.ambiente.alfresco.service.AlfrescoServiceBean;
import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.speciescatalog.model.HigherClassification;
import ec.gob.ambiente.enlisy.speciescatalog.model.TaxaRank;
import ec.gob.ambiente.sib_suia_ora.catalogobiodiversidad.model.HigherLoadTaxa;
import ec.gob.ambiente.sib_suia_ora.catalogobiodiversidad.model.SpecieLoadTaxa;
import ec.gob.ambiente.util.Constant;

/**
 * Servicios para la administracion de las publicaciones del especialista
 * 
 * @author EXCO
 *
 */

@Stateless
public class HigherClassificationFacade extends AbstractFacade<HigherClassification, Integer> {

	public HigherClassificationFacade() {
		super(HigherClassification.class, Integer.class);
	}
	
	@EJB(lookup = Constant.ALFRESCO_SERVICE_BEAN)
    private AlfrescoServiceBean alfrescoServiceBean;
	
	@Inject
    ConnectionCmisAlfrescoSUIA cmisAlfrescoSUIA;

	/**
	 * Recuperar concidencias de reino
	 * 
	 * @param name
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public List<HigherClassification> findKingdomLikeName(String name) {
		try {
			Query query = super.getEntityManager().createQuery(
					"select o from HigherClassification o where upper(o.hiclScientificName) like :nombre and o.hiclStatus = true and o.taxaRank.taraId = 1 order by o.hiclScientificName");
			query.setParameter("nombre", name.toUpperCase());
			List<HigherClassification> result = (List<HigherClassification>) query.getResultList();
			if (result.size() > 0)
				return result;
		} catch (NoResultException e) {
			return new ArrayList<HigherClassification>();
		}
		return new ArrayList<HigherClassification>();
	}

	/**
	 * Recuperar concidencias de phylum
	 * 
	 * @param name
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public List<HigherClassification> findKPhylumLikeForKingdom(String name, String nameKingdom) {
		try {
			Query query = super.getEntityManager().createQuery(
					"select o from HigherClassification o where upper(o.hiclScientificName) like :nombre and o.hiclStatus = true and o.taxaRank.taraId = 2 and upper(o.hiclParentName) = :nombrePadre order by o.hiclScientificName");
			query.setParameter("nombre", name.toUpperCase());
			query.setParameter("nombrePadre", nameKingdom.toUpperCase());
			List<HigherClassification> result = (List<HigherClassification>) query.getResultList();
			if (result.size() > 0)
				return result;
		} catch (NoResultException e) {
			return new ArrayList<HigherClassification>();
		}
		return new ArrayList<HigherClassification>();
	}

	@SuppressWarnings("unchecked")
	public List<HigherClassification> findKClaseLikeForPhylum(String name, String namePhylum) {
		try {
			Query query = super.getEntityManager().createQuery(
					"select o from HigherClassification o where upper(o.hiclScientificName) like :nombre and o.hiclStatus = true and o.taxaRank.taraId = 3 and upper(o.hiclParentName) = :nombrePadre order by o.hiclScientificName");
			query.setParameter("nombre", name.toUpperCase());
			query.setParameter("nombrePadre", namePhylum.toUpperCase());
			List<HigherClassification> result = (List<HigherClassification>) query.getResultList();
			if (result.size() > 0)
				return result;
		} catch (NoResultException e) {
			return new ArrayList<HigherClassification>();
		}
		return new ArrayList<HigherClassification>();
	}

	@SuppressWarnings("unchecked")
	public List<HigherClassification> findKOrdenLikeForClass(String name, String nameClass) {
		try {
			Query query = super.getEntityManager().createQuery(
					"select o from HigherClassification o where upper(o.hiclScientificName) like :nombre and o.hiclStatus = true and o.taxaRank.taraId = 4 and upper(o.hiclParentName) = :nombrePadre order by o.hiclScientificName");
			query.setParameter("nombre", name.toUpperCase());
			query.setParameter("nombrePadre", nameClass.toUpperCase());
			List<HigherClassification> result = (List<HigherClassification>) query.getResultList();
			if (result.size() > 0)
				return result;
		} catch (NoResultException e) {
			return new ArrayList<HigherClassification>();
		}
		return new ArrayList<HigherClassification>();
	}

	@SuppressWarnings("unchecked")
	public List<HigherClassification> findKFamilyLikeForOrden(String name, String nameOrden) {
		try {
			Query query = super.getEntityManager().createQuery(
					"select o from HigherClassification o where upper(o.hiclScientificName) like :nombre and o.hiclStatus = true and o.taxaRank.taraId = 5 and upper(o.hiclParentName) = :nombrePadre order by o.hiclScientificName");
			query.setParameter("nombre", name.toUpperCase());
			query.setParameter("nombrePadre", nameOrden.toUpperCase());
			List<HigherClassification> result = (List<HigherClassification>) query.getResultList();
			if (result.size() > 0)
				return result;
		} catch (NoResultException e) {
			return new ArrayList<HigherClassification>();
		}
		return new ArrayList<HigherClassification>();
	}

	@SuppressWarnings("unchecked")
	public List<HigherClassification> findKGeneroLikeForFamily(String name, String nameFamily) {
		try {
			Query query = super.getEntityManager().createQuery(
					"select o from HigherClassification o where upper(o.hiclScientificName) like :nombre and o.hiclStatus = true and o.taxaRank.taraId = 6 and upper(o.hiclParentName) = :nombrePadre order by o.hiclScientificName");
			query.setParameter("nombre", name.toUpperCase());
			query.setParameter("nombrePadre", nameFamily.toUpperCase());
			List<HigherClassification> result = (List<HigherClassification>) query.getResultList();
			if (result.size() > 0)
				return result;
		} catch (NoResultException e) {
			return new ArrayList<HigherClassification>();
		}
		return new ArrayList<HigherClassification>();
	}

	@SuppressWarnings("unchecked")
	public List<HigherClassification> findKingdom() {
		try {
			Query query = super.getEntityManager().createQuery(
					"select o from HigherClassification o where o.hiclStatus = true and o.taxaRank.taraId = 1 order by o.hiclScientificName");
			List<HigherClassification> result = (List<HigherClassification>) query.getResultList();
			if (result.size() > 0)
				return result;
		} catch (NoResultException e) {
			return new ArrayList<HigherClassification>();
		}
		return new ArrayList<HigherClassification>();
	}

	@SuppressWarnings("unchecked")
	public List<HigherClassification> findPhylumKingdom(Integer idKingdom) {
		try {
			Query query = super.getEntityManager().createQuery(
					"select o from HigherClassification o where o.hiclStatus = true and o.taxaRank.taraId = 2 and o.hiclIdParent.hiclId = :idKingdom order by o.hiclScientificName");
			query.setParameter("idKingdom", idKingdom);
			List<HigherClassification> result = (List<HigherClassification>) query.getResultList();
			if (result.size() > 0)
				return result;
		} catch (NoResultException e) {
			return new ArrayList<HigherClassification>();
		}
		return new ArrayList<HigherClassification>();
	}

	@SuppressWarnings("unchecked")
	public List<HigherClassification> findClassByPhylum(Integer idPhylum) {
		try {
			Query query = super.getEntityManager().createQuery(
					"select o from HigherClassification o where o.hiclStatus = true and o.taxaRank.taraId = 3 and o.hiclIdParent.hiclId = :idPhylum order by o.hiclScientificName");
			query.setParameter("idPhylum", idPhylum);
			List<HigherClassification> result = (List<HigherClassification>) query.getResultList();
			if (result.size() > 0)
				return result;
		} catch (NoResultException e) {
			return new ArrayList<HigherClassification>();
		}
		return new ArrayList<HigherClassification>();
	}

	@SuppressWarnings("unchecked")
	public List<HigherClassification> findOrderByClass(Integer idClass) {
		try {
			Query query = super.getEntityManager().createQuery(
					"select o from HigherClassification o where o.hiclStatus = true and o.taxaRank.taraId = 4 and o.hiclIdParent.hiclId = :idClass order by o.hiclScientificName");
			query.setParameter("idClass", idClass);
			List<HigherClassification> result = (List<HigherClassification>) query.getResultList();
			if (result.size() > 0)
				return result;
		} catch (NoResultException e) {
			return new ArrayList<HigherClassification>();
		}
		return new ArrayList<HigherClassification>();
	}

	@SuppressWarnings("unchecked")
	public List<HigherClassification> findFamilyByOrder(Integer idOrder) {
		try {
			Query query = super.getEntityManager().createQuery(
					"select o from HigherClassification o where o.hiclStatus = true and o.taxaRank.taraId = 5 and o.hiclIdParent.hiclId = :idOrder order by o.hiclScientificName");
			query.setParameter("idOrder", idOrder);
			List<HigherClassification> result = (List<HigherClassification>) query.getResultList();
			if (result.size() > 0)
				return result;
		} catch (NoResultException e) {
			return new ArrayList<HigherClassification>();
		}
		return new ArrayList<HigherClassification>();
	}

	@SuppressWarnings("unchecked")
	public List<HigherClassification> findGenusByFamily(Integer idFamily) {
		try {
			Query query = super.getEntityManager().createQuery(
					"select o from HigherClassification o where o.hiclStatus = true and o.taxaRank.taraId = 6 and o.hiclIdParent.hiclId = :idFamily order by o.hiclScientificName");
			query.setParameter("idFamily", idFamily);
			List<HigherClassification> result = (List<HigherClassification>) query.getResultList();
			if (result.size() > 0)
				return result;
		} catch (NoResultException e) {
			return new ArrayList<HigherClassification>();
		}
		return new ArrayList<HigherClassification>();
	}
	
	@SuppressWarnings("unchecked")
	public List<HigherClassification> findClassificationByParent(String name) {
		try {
			Query query = super.getEntityManager().createQuery(
					"select hc from HigherClassification hc where hc.hiclStatus = true and hc.hiclIdParent.hiclId in ("
							+ "select hcp.hiclId from HigherClassification hcp where hcp.hiclStatus = true and upper(hcp.hiclScientificName) = :name and hcp.taxaRank.taraId = 1) order by hc.hiclScientificName");
			query.setParameter("name", name);
			List<HigherClassification> result = (List<HigherClassification>) query.getResultList();
			if (result.size() > 0) {
				return result;
			}
		} catch (NoResultException e) {
			return new ArrayList<HigherClassification>();
		}
		return new ArrayList<HigherClassification>();
	}

	/**
	 * Guardar clasificacion superior
	 * 
	 * @param solicitudEspecialista
	 * @param usuario
	 * @return
	 */
	public HigherClassification save(HigherClassification higher, User user) {
		HigherClassification higherClass = null;
		try {
			if (higher.getHiclId() == null || higher.getHiclId().compareTo(Integer.valueOf(0)) == 0) {

				higher.setHiclStatus(true);
				higher.setHiclDateCreate(new Date());
				higher.setHiclUserCreate(user.getUserName());
				higherClass = create(higher);

			} else {

				higher.setHiclDateUpdate(new Date());
				higher.setHiclUserUpdate(user.getUserName());
				higherClass = edit(higher);
			}

			return higherClass;
		} catch (Exception ex) {
			ex.printStackTrace();
			return higherClass;
		}
	}

	/**
	 * Obtener clasificaciones segun rango y niveles seleccionados
	 * 
	 * @param nameDocumentType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getHigherClassificationsGrid(String sql) {
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
	 * Cambiar el campo de la jerarquia de todos los hijos de la clasificacion a
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

	public boolean actualizarClasificacion(String likeBuscar, String codigoAReemplazar, String codigoReemplazar,
			String userUpdate, String codigoNombresAReemplazar, String codigoNombresReemplazar) {
		boolean resultado = false;
		try {

			Query q;
			q = getEntityManager().createNativeQuery(
					"UPDATE biodiversity.higher_classifications SET hicl_hierarchy_code = REPLACE (hicl_hierarchy_code, ?1 , ?2), hicl_higher_classification = REPLACE (hicl_higher_classification, ?5 , ?6), hicl_user_update = ?3  where hicl_hierarchy_code LIKE ?4");
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
	 * Actualizar el padre de la clasificacion
	 * 
	 * @param idClasificacionAActualizar
	 * @param idNuevoPadre
	 * @return
	 */

	public boolean actualizarPadreClasificacion(Integer idClasificacionAActualizar, Integer idNuevoPadre,
			String userUpdate, String nuevoCodigo, String nuevoNombrePadre, String nuevoConcaNombre) {
		boolean resultado = false;
		try {

			Query q;
			q = getEntityManager().createNativeQuery(
					"UPDATE biodiversity.higher_classifications SET hicl_id_parent = ?1, hicl_user_update = ?2, hicl_hierarchy_code = ?3, hicl_parent_name = ?4 , hicl_higher_classification = ?5  where hicl_id = ?6");
			q.setParameter(1, idNuevoPadre);
			q.setParameter(2, userUpdate);
			q.setParameter(3, nuevoCodigo);
			q.setParameter(4, nuevoNombrePadre);
			q.setParameter(5, nuevoConcaNombre);
			q.setParameter(6, idClasificacionAActualizar);

			q.executeUpdate();
			resultado = true;
		} catch (Exception e) {
			resultado = false;
		}
		return resultado;
	}

	/**
	 * Buscar coincidencias nombre cientifico
	 * 
	 * @param nombreCientifico
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> buscarCoincidenciasNombre(String nombreCientifico) {
		List<Object[]> result = null;

		try {

			Query q;
			q = getEntityManager().createNativeQuery(
					"SELECT hicl_id,hicl_scientific_gui,hicl_taxon_rank_name,hicl_scientific_name,hicl_hierarchy_code, tara_id, similarity(hicl_scientific_name, ?1) AS sml "
							+ "  from biodiversity.higher_classifications where  similarity(hicl_scientific_name, ?2) >= 0.4");
			q.setParameter(1, nombreCientifico);
			q.setParameter(2, nombreCientifico);

			result = q.getResultList();

		} catch (Exception e) {
			result = null;
		}
		return result;

	}

	/**
	 * Buscar por nombre cientifico y rango
	 * 
	 * @param name
	 * @param idRank
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public List<HigherClassification> findByScientificNameAndRank(String name, Integer idRank) {
		try {
			Query query = super.getEntityManager().createQuery(
					"select o from HigherClassification o where upper(o.hiclScientificName) = :nombre and o.hiclStatus = true and o.taxaRank.taraId = :idRango");
			query.setParameter("nombre", name.toUpperCase());
			query.setParameter("idRango", idRank);
			List<HigherClassification> result = (List<HigherClassification>) query.getResultList();
			if (result.size() > 0)
				return result;
		} catch (NoResultException e) {
			return new ArrayList<HigherClassification>();
		}
		return new ArrayList<HigherClassification>();
	}

	/**
	 * Metodo para buscar un registro por gui y rango
	 * 
	 * @param gui
	 * @param idRank
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public HigherClassification findByGuiAndRank(String gui, Integer idRank) {
		try {
			Query query = super.getEntityManager().createQuery(
					"select o from HigherClassification o where hiclScientificGui = :gui and o.hiclStatus = true and o.taxaRank.taraId = :idRango");
			query.setParameter("gui", gui);
			query.setParameter("idRango", idRank);
			List<HigherClassification> result = (List<HigherClassification>) query.getResultList();
			if (result.size() > 0)
				return result.get(0);
		} catch (NoResultException e) {
			return null;
		}
		return null;
	}

	/**
	 * Metodo que devuelve un mapa con la informacion de taxonomia de reino a genero
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> findAllHigherTaxonomy() {
		List<Object[]> result = new ArrayList<>();
		Query q;
		EntityManager en = super.getEntityManager();
		q = en.createNativeQuery("SELECT g.tara_id AS id_rank,r.hicl_id AS id_reino,\r\n"
				+ "    r.hicl_scientific_gui AS gui_reino,\r\n" + "    r.hicl_scientific_name AS reino,\r\n"
				+ "    p.hicl_id AS id_phylum,\r\n" + "    p.hicl_scientific_gui AS gui_phylum,\r\n"
				+ "    p.hicl_scientific_name AS phylum,\r\n" + "    c.hicl_id AS id_clase,\r\n"
				+ "    c.hicl_scientific_gui AS gui_clase,\r\n" + "    c.hicl_scientific_name AS clase,\r\n"
				+ "    o.hicl_id AS id_orden,\r\n" + "    o.hicl_scientific_gui AS gui_orden,\r\n"
				+ "    o.hicl_scientific_name AS orden,\r\n" + "    f.hicl_id AS id_familia,\r\n"
				+ "    f.hicl_scientific_gui AS gui_familia,\r\n" + "    f.hicl_scientific_name AS familia,\r\n"
				+ "    g.hicl_id AS id_genero,\r\n" + "    g.hicl_scientific_gui AS gui_genero,\r\n"
				+ "    g.hicl_scientific_name AS genero\r\n" + "   FROM biodiversity.higher_classifications r,\r\n"
				+ "    biodiversity.higher_classifications p,\r\n" + "    biodiversity.higher_classifications c,\r\n"
				+ "    biodiversity.higher_classifications o,\r\n" + "    biodiversity.higher_classifications f,\r\n"
				+ "    biodiversity.higher_classifications g\r\n"
				+ "  WHERE f.hicl_id = g.hicl_id_parent AND o.hicl_id = f.hicl_id_parent AND c.hicl_id = o.hicl_id_parent AND p.hicl_id = c.hicl_id_parent AND r.hicl_id = p.hicl_id_parent\r\n"
				+ "UNION\r\n" + " SELECT g.tara_id AS id_rank, r.hicl_id AS id_reino,\r\n"
				+ "    r.hicl_scientific_gui AS gui_reino,\r\n" + "    r.hicl_scientific_name AS reino,\r\n"
				+ "    p.hicl_id AS id_phylum,\r\n" + "    p.hicl_scientific_gui AS gui_phylum,\r\n"
				+ "    p.hicl_scientific_name AS phylum,\r\n" + "    c.hicl_id AS id_clase,\r\n"
				+ "    c.hicl_scientific_gui AS gui_clase,\r\n" + "    c.hicl_scientific_name AS clase,\r\n"
				+ "    o.hicl_id AS id_orden,\r\n" + "    o.hicl_scientific_gui AS gui_orden,\r\n"
				+ "    o.hicl_scientific_name AS orden,\r\n" + "    f.hicl_id AS id_familia,\r\n"
				+ "    f.hicl_scientific_gui AS gui_familia,\r\n" + "    f.hicl_scientific_name AS familia,\r\n"
				+ "    g.hicl_id AS id_genero,\r\n" + "    g.hicl_scientific_gui AS gui_genero,\r\n"
				+ "    g.hicl_scientific_name AS genero\r\n" + "   FROM biodiversity.higher_classifications r,\r\n"
				+ "    biodiversity.higher_classifications p,\r\n" + "    biodiversity.higher_classifications c,\r\n"
				+ "    biodiversity.higher_classifications o,\r\n" + "    biodiversity.higher_classifications f,\r\n"
				+ "    biodiversity.higher_classifications g\r\n"
				+ "  WHERE f.hicl_id = g.hicl_id_parent AND o.hicl_id = f.hicl_id_parent AND c.hicl_id = o.hicl_id_parent AND p.hicl_id = c.hicl_id_parent AND r.hicl_id = p.hicl_id_parent AND g.tara_id = 6\r\n"
				+ "UNION\r\n" + " SELECT f.tara_id AS id_rank,r.hicl_id AS id_reino,\r\n"
				+ "    r.hicl_scientific_gui AS gui_reino,\r\n" + "    r.hicl_scientific_name AS reino,\r\n"
				+ "    p.hicl_id AS id_phylum,\r\n" + "    p.hicl_scientific_gui AS gui_phylum,\r\n"
				+ "    p.hicl_scientific_name AS phylum,\r\n" + "    c.hicl_id AS id_clase,\r\n"
				+ "    c.hicl_scientific_gui AS gui_clase,\r\n" + "    c.hicl_scientific_name AS clase,\r\n"
				+ "    o.hicl_id AS id_orden,\r\n" + "    o.hicl_scientific_gui AS gui_orden,\r\n"
				+ "    o.hicl_scientific_name AS orden,\r\n" + "    f.hicl_id AS id_familia,\r\n"
				+ "    f.hicl_scientific_gui AS gui_familia,\r\n" + "    f.hicl_scientific_name AS familia,\r\n"
				+ "    0 AS id_genero,\r\n" + "    '0' AS gui_genero,\r\n" + "    '0' AS genero\r\n"
				+ "   FROM biodiversity.higher_classifications r,\r\n"
				+ "    biodiversity.higher_classifications p,\r\n" + "    biodiversity.higher_classifications c,\r\n"
				+ "    biodiversity.higher_classifications o,\r\n" + "    biodiversity.higher_classifications f\r\n"
				+ "  WHERE o.hicl_id = f.hicl_id_parent AND c.hicl_id = o.hicl_id_parent AND p.hicl_id = c.hicl_id_parent AND r.hicl_id = p.hicl_id_parent AND f.tara_id = 5\r\n"
				+ "UNION\r\n" + " SELECT o.tara_id AS id_rank,r.hicl_id AS id_reino,\r\n"
				+ "    r.hicl_scientific_gui AS gui_reino,\r\n" + "    r.hicl_scientific_name AS reino,\r\n"
				+ "    p.hicl_id AS id_phylum,\r\n" + "    p.hicl_scientific_gui AS gui_phylum,\r\n"
				+ "    p.hicl_scientific_name AS phylum,\r\n" + "    c.hicl_id AS id_clase,\r\n"
				+ "    c.hicl_scientific_gui AS gui_clase,\r\n" + "    c.hicl_scientific_name AS clase,\r\n"
				+ "    o.hicl_id AS id_orden,\r\n" + "    o.hicl_scientific_gui AS gui_orden,\r\n"
				+ "    o.hicl_scientific_name AS orden,\r\n" + "    0 AS id_familia,\r\n"
				+ "    '0' AS gui_familia,\r\n" + "    '0' AS familia,\r\n" + "    0 AS id_genero,\r\n"
				+ "    '0' AS gui_genero,\r\n" + "    '0' AS genero\r\n"
				+ "   FROM biodiversity.higher_classifications r,\r\n"
				+ "    biodiversity.higher_classifications p,\r\n" + "    biodiversity.higher_classifications c,\r\n"
				+ "    biodiversity.higher_classifications o\r\n"
				+ "  WHERE c.hicl_id = o.hicl_id_parent AND p.hicl_id = c.hicl_id_parent AND r.hicl_id = p.hicl_id_parent AND o.tara_id = 4\r\n"
				+ "UNION\r\n" + " SELECT c.tara_id AS id_rank,r.hicl_id AS id_reino,\r\n"
				+ "    r.hicl_scientific_gui AS gui_reino,\r\n" + "    r.hicl_scientific_name AS reino,\r\n"
				+ "    p.hicl_id AS id_phylum,\r\n" + "    p.hicl_scientific_gui AS gui_phylum,\r\n"
				+ "    p.hicl_scientific_name AS phylum,\r\n" + "    c.hicl_id AS id_clase,\r\n"
				+ "    c.hicl_scientific_gui AS gui_clase,\r\n" + "    c.hicl_scientific_name AS clase,\r\n"
				+ "    0 AS id_orden,\r\n" + "    '0' AS gui_orden,\r\n" + "    '0' AS orden,\r\n"
				+ "    0 AS id_familia,\r\n" + "    '0' AS gui_familia,\r\n" + "    '0' AS familia,\r\n"
				+ "    0 AS id_genero,\r\n" + "    '0' AS gui_genero,\r\n" + "    '0' AS genero\r\n"
				+ "   FROM biodiversity.higher_classifications r,\r\n"
				+ "    biodiversity.higher_classifications p,\r\n" + "    biodiversity.higher_classifications c\r\n"
				+ "  WHERE p.hicl_id = c.hicl_id_parent AND r.hicl_id = p.hicl_id_parent AND c.tara_id = 3\r\n"
				+ "UNION\r\n" + " SELECT p.tara_id AS id_rank,r.hicl_id AS id_reino,\r\n"
				+ "    r.hicl_scientific_gui AS gui_reino,\r\n" + "    r.hicl_scientific_name AS reino,\r\n"
				+ "    p.hicl_id AS id_phylum,\r\n" + "    p.hicl_scientific_gui AS gui_phylum,\r\n"
				+ "    p.hicl_scientific_name AS phylum,\r\n" + "    0 AS id_clase,\r\n" + "    '0' AS gui_clase,\r\n"
				+ "    '0' AS clase,\r\n" + "    0 AS id_orden,\r\n" + "    '0' AS gui_orden,\r\n"
				+ "    '0' AS orden,\r\n" + "    0 AS id_familia,\r\n" + "    '0' AS gui_familia,\r\n"
				+ "    '0' AS familia,\r\n" + "    0 AS id_genero,\r\n" + "    '0' AS gui_genero,\r\n"
				+ "    '0' AS genero\r\n" + "   FROM biodiversity.higher_classifications r,\r\n"
				+ "    biodiversity.higher_classifications p\r\n"
				+ "  WHERE r.hicl_id = p.hicl_id_parent AND p.tara_id = 2\r\n" + "UNION\r\n"
				+ " SELECT r.tara_id AS id_rank, r.hicl_id AS id_reino,\r\n"
				+ "    r.hicl_scientific_gui AS gui_reino,\r\n" + "    r.hicl_scientific_name AS reino,\r\n"
				+ "    0 AS id_phylum,\r\n" + "    '0' AS gui_phylum,\r\n" + "    '0' AS phylum,\r\n"
				+ "    0 AS id_clase,\r\n" + "    '0' AS gui_clase,\r\n" + "    '0' AS clase,\r\n"
				+ "    0 AS id_orden,\r\n" + "    '0' AS gui_orden,\r\n" + "    '0' AS orden,\r\n"
				+ "    0 AS id_familia,\r\n" + "    '0' AS gui_familia,\r\n" + "    '0' AS familia,\r\n"
				+ "    0 AS id_genero,\r\n" + "    '0' AS gui_genero,\r\n" + "    '0' AS genero\r\n"
				+ "   FROM biodiversity.higher_classifications r\r\n" + "  WHERE r.tara_id = 1");

		if (q.getResultList() != null && !q.getResultList().isEmpty()) {
			result = q.getResultList();
		}

		return result;
	}

	/**
	 * Cargar mapa de estatus taxonomico y reino
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> getStatusKingdom() {
		Map<String, String> mapResult = new LinkedHashMap<>();
		List<Object[]> result = new ArrayList<>();
		Query q;
		EntityManager en = super.getEntityManager();
		q = en.createNativeQuery("SELECT tast_id, tast_name, tast_code, tast_animal, tast_plantae, tast_fungi, \r\n"
				+ "       tast_eubacteria, tast_archeobacteria, tast_protista, \r\n"
				+ "       tast_chromista, tast_viruses\r\n"
				+ "  FROM biodiversity.taxonomic_status where tast_status=true");
		result = q.getResultList();
		if (result != null && !result.isEmpty()) {
			for (Object[] row : result) {
				if (row != null) {
					if (row[2] != null) {
						StringBuilder rein = new StringBuilder();
						rein.append(row[0]);
						rein.append(";");
						if ((boolean) row[3]) {
							rein.append("ANIMAL");
							rein.append(";");
						}
						if ((boolean) row[4]) {
							rein.append("PLANTAE");
							rein.append(";");
						}
						if ((boolean) row[5]) {
							rein.append("FUNGI");
							rein.append(";");
						}
						if ((boolean) row[6]) {
							rein.append("BACTERIA");
							rein.append(";");
						}
						if ((boolean) row[7]) {
							rein.append("ARCHAEA");
							rein.append(";");
						}
						if ((boolean) row[8]) {
							rein.append("PROTOZOA");
							rein.append(";");
						}
						if ((boolean) row[9]) {
							rein.append("CHROMISTA");
							rein.append(";");
						}
						if ((boolean) row[10]) {
							rein.append("VIRUSES");
							rein.append(";");
						}
						mapResult.put(row[2].toString(), rein.toString());
					}
				}
			}
		}
		return mapResult;
	}
	
	/**
	 * Metodo para insertar o actualizar taxa
	 * 
	 * @param taxa
	 */
	@SuppressWarnings("unused")
	public void insertarActualizar(List<HigherLoadTaxa> taxa) {
		PreparedStatement pstmt = null;
		PreparedStatement pstmtActCampos = null;
		PreparedStatement pstmtActPadre = null;
		PreparedStatement pstmtActHijos = null;
		try {
			Connection con = getConnection();
			if (con != null) {

				String sq1 = "UPDATE biodiversity.higher_classifications SET hicl_id_parent = ?, hicl_user_update = ?, hicl_hierarchy_code = ?, hicl_parent_name = ? , hicl_higher_classification = ?  where hicl_scientific_gui = ?";
				String sq2 = "UPDATE biodiversity.higher_classifications SET hicl_hierarchy_code = REPLACE (hicl_hierarchy_code, ? , ?), hicl_higher_classification = REPLACE (hicl_higher_classification, ? , ?), hicl_user_update = ?  where hicl_hierarchy_code LIKE ?";

				String sq3 = "UPDATE biodiversity.species_taxa SET spta_hierarchy_code = REPLACE (spta_hierarchy_code, ? , ?), spta_user_update = ?, spta_higher_classification=REPLACE (spta_higher_classification, ? , ?)  where spta_hierarchy_code LIKE ?";

				pstmt = con.prepareStatement(
						"insert into biodiversity.higher_classifications (hicl_id_parent,tara_id,hicl_scientific_name,hicl_hierarchy_code,hicl_name_published_year,hicl_cientific_name_authorship,hicl_vernacular_name,hicl_taxon_remarks,hicl_taxon_rank_name,hicl_status,hicl_user_create,hicl_verified_record,hicl_higher_classification,hicl_correct_tax,hicl_parent_name,hicl_verified_for,noco_id,hicl_search_code,hicl_correct_tax_id,hicl_correct_tax_name, hicl_date_create,tast_id, taso_id)  values (?, ?, ?, ?||(select last_value from biodiversity.seq_spta_id), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
				pstmtActCampos = con.prepareStatement(
						"UPDATE biodiversity.higher_classifications SET hicl_cientific_name_authorship = ?, hicl_name_published_year = ?, hicl_vernacular_name = ?, noco_id = ? , hicl_taxon_remarks = ? , hicl_correct_tax = ? , hicl_correct_tax_id = ?, hicl_correct_tax_name = ?, tast_id = ? ,hicl_user_update = ?, hicl_date_update = ?, taso_id = ? where hicl_scientific_gui = ?");
				pstmtActPadre = con.prepareStatement(sq1);
				pstmtActHijos = con.prepareStatement(sq2);
				PreparedStatement pstmtActHijosEspecie = con.prepareStatement(sq3);

				System.out.println("Inicio batch.");
				long start = System.currentTimeMillis();

				for (HigherLoadTaxa higher : taxa) {

					if (higher.getCodigoGui() == null || higher.getCodigoGui().equals("")) {
						pstmt.setInt(1, higher.getParentId());

						pstmt.setInt(2, higher.getRangoTaxonomico());
						pstmt.setString(3, higher.getScientificName());
						pstmt.setString(4, higher.getHiclHierarchyCode());
												
						if (!higher.getAnioPublicacion().equals("0")) {
							pstmt.setString(5, higher.getAnioPublicacion());
						} else {
							pstmt.setNull(5, Types.VARCHAR);
						}
						
						if (!higher.getAutor().equals("N/D")) {
							pstmt.setString(6, higher.getAutor());
						} else {
							pstmt.setNull(6, Types.VARCHAR);
						}
						
						pstmt.setString(7, higher.getNombreComun());
						pstmt.setString(8, higher.getObservaciones()+" "+higher.getOtraFuenteTaxonomica()+" "+higher.getUrlFuenteTaxonomica());
						pstmt.setString(9, higher.getHiclTaxonRankName());
						pstmt.setBoolean(10, higher.getHiclStatus());
						pstmt.setString(11, higher.getHiclUserCreate());
						pstmt.setBoolean(12, higher.getHiclVerifiedRecord());
						pstmt.setString(13, higher.getHiclHigherClassification());
						pstmt.setBoolean(14, higher.getEsTaxonCorrecto());
						pstmt.setString(15, higher.getHiclParentName());
						pstmt.setString(16, higher.getHiclVerifiedFor());
						pstmt.setInt(17, higher.getIdCodigoNomenclatural());
						pstmt.setString(18, higher.getHiclSearchCode());
						if (higher.getIdTaxonCorrecto() != null) {
							pstmt.setInt(19, higher.getIdTaxonCorrecto());
						} else {
							pstmt.setNull(19, Types.INTEGER);
						}
						if (higher.getTaxonCorrecto() != null) {
							pstmt.setString(20, higher.getTaxonCorrecto());
						} else {
							pstmt.setNull(20, Types.VARCHAR);
						}
						java.sql.Date sDate = new java.sql.Date(higher.getHiclDateCreate().getTime());
						pstmt.setDate(21, sDate);
						if (higher.getIdStatusTaxonomico()!= null) {
							pstmt.setInt(22, higher.getIdStatusTaxonomico());
						} else {
							pstmt.setNull(22, Types.INTEGER);
						}
						if (higher.getIdFuenteTaxonomica()!= null) {
							pstmt.setInt(23, higher.getIdFuenteTaxonomica());
						} else {
							pstmt.setNull(23, Types.INTEGER);
						}
						
						pstmt.addBatch();
					} else {
						if (higher.getRangoTaxonomico() == 1) {
							
							if (!higher.getAutor().equals("N/D")) {
								pstmtActCampos.setString(1, higher.getAutor());
							} else {
								pstmtActCampos.setNull(1, Types.VARCHAR);
							}
							
							if (!higher.getAnioPublicacion().equals("0")) {
								pstmtActCampos.setString(2, higher.getAnioPublicacion());
							} else {
								pstmtActCampos.setNull(2, Types.VARCHAR);
							}
							
							pstmtActCampos.setString(3, higher.getNombreComun());
							pstmtActCampos.setInt(4, higher.getIdCodigoNomenclatural());
							pstmtActCampos.setString(5, higher.getObservaciones()+" "+higher.getOtraFuenteTaxonomica()+" "+higher.getUrlFuenteTaxonomica());
							pstmtActCampos.setBoolean(6, higher.getEsTaxonCorrecto());
							if (higher.getIdTaxonCorrecto() != null) {
								pstmtActCampos.setInt(7, higher.getIdTaxonCorrecto());
							} else {
								pstmtActCampos.setNull(7, Types.INTEGER);
							}
							if (higher.getTaxonCorrecto() != null) {
								pstmtActCampos.setString(8, higher.getTaxonCorrecto());

							} else {
								pstmtActCampos.setNull(8, Types.VARCHAR);
							}
							pstmtActCampos.setInt(9, higher.getIdStatusTaxonomico());
							pstmtActCampos.setString(10, higher.getHiclUserCreate());
							java.sql.Date sDate = new java.sql.Date(higher.getHiclDateCreate().getTime());
							pstmtActCampos.setDate(11, sDate);
							if (higher.getIdFuenteTaxonomica()!= null) {
								pstmtActCampos.setInt(12, higher.getIdFuenteTaxonomica());
							} else {
								pstmtActCampos.setNull(12, Types.INTEGER);
							}
							pstmtActCampos.setString(13, higher.getCodigoGui());
							pstmtActCampos.addBatch();
						} else {
							
							if (!higher.getAutor().equals("N/D")) {
								pstmtActCampos.setString(1, higher.getAutor());
							} else {
								pstmtActCampos.setNull(1, Types.VARCHAR);
							}
							
							if (!higher.getAnioPublicacion().equals("0")) {
								pstmtActCampos.setString(2, higher.getAnioPublicacion());
							} else {
								pstmtActCampos.setNull(2, Types.VARCHAR);
							}
							
							pstmtActCampos.setString(3, higher.getNombreComun());
							pstmtActCampos.setInt(4, higher.getIdCodigoNomenclatural());
							pstmtActCampos.setString(5, higher.getObservaciones());
							pstmtActCampos.setBoolean(6, higher.getEsTaxonCorrecto());
							if (higher.getIdTaxonCorrecto() != null) {
								pstmtActCampos.setInt(7, higher.getIdTaxonCorrecto());
							} else {
								pstmtActCampos.setNull(7, Types.INTEGER);
							}
							if (higher.getTaxonCorrecto() != null) {
								pstmtActCampos.setString(8, higher.getTaxonCorrecto());

							} else {
								pstmtActCampos.setNull(8, Types.VARCHAR);
							}
							pstmtActCampos.setInt(9, higher.getIdStatusTaxonomico());
							pstmtActCampos.setString(10, higher.getHiclUserCreate());
							java.sql.Date sDate = new java.sql.Date(higher.getHiclDateCreate().getTime());
							pstmtActCampos.setDate(11, sDate);
							if (higher.getIdFuenteTaxonomica()!= null) {
								pstmtActCampos.setInt(12, higher.getIdFuenteTaxonomica());
							} else {
								pstmtActCampos.setNull(12, Types.INTEGER);
							}
							
							
							
							pstmtActCampos.setString(13, higher.getCodigoGui());
							pstmtActCampos.addBatch();

							/**
							pstmtActPadre.setInt(1, higher.getParentId());
							pstmtActPadre.setString(2, higher.getHiclUserCreate());
							pstmtActPadre.setString(3, higher.getHigherCodeNuevoSinHijos());
							pstmtActPadre.setString(4, higher.getHiclParentName());
							pstmtActPadre.setString(5, higher.getHigherCodeNameNuevoSinHijos());
							pstmtActPadre.setString(6, higher.getCodigoGui());
							pstmtActPadre.addBatch();

							pstmtActHijos.setString(1, higher.getHigherCodeAnt());
							pstmtActHijos.setString(2, higher.getHigherCodeNuevoConHijos());
							pstmtActHijos.setString(5, higher.getHiclUserCreate());
							pstmtActHijos.setString(6, higher.getHigherCodeAnt() + "%");
							pstmtActHijos.setString(3, higher.getHigherCodeNameAnt());
							pstmtActHijos.setString(4, higher.getHigherCodeNameNuevoConHijos());
							pstmtActHijos.addBatch();

							pstmtActHijosEspecie.setString(1, higher.getHigherCodeAnt());
							pstmtActHijosEspecie.setString(2, higher.getHigherCodeNuevoConHijos());
							pstmtActHijosEspecie.setString(3, higher.getHiclUserCreate());
							pstmtActHijosEspecie.setString(6, higher.getHigherCodeAnt() + "%");
							pstmtActHijosEspecie.setString(4, higher.getHigherCodeNameAnt());
							pstmtActHijosEspecie.setString(5, higher.getHigherCodeNameNuevoConHijos());
							pstmtActHijosEspecie.addBatch();
							System.out.println(higher.getHigherCodeAnt());
							System.out.println(higher.getHigherCodeNameAnt());
							System.out.println(higher.getHigherCodeNameNuevoSinHijos());
							System.out.println(higher.getHigherCodeNameNuevoConHijos());
							System.out.println(higher.getHigherCodeNuevoSinHijos());
							System.out.println(higher.getHigherCodeNuevoConHijos());
							**/
						}
					}
				}
				pstmt.executeBatch();
				pstmtActCampos.executeBatch();
				//pstmtActPadre.executeBatch();
				//pstmtActHijos.executeBatch();
				//pstmtActHijosEspecie.executeBatch();
				long end = System.currentTimeMillis();
				System.out.println("Fin batch(insert en): " + (end - start) + " milliseconds.");

			} else {
				System.out.println("Error de conexión ");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (pstmtActCampos != null) {
					pstmtActCampos.close();
				}
				if (pstmtActPadre != null) {
					pstmtActPadre.close();
				}
				if (pstmtActHijos != null) {
					pstmtActHijos.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private Connection getConnection() {

		Connection connMY = null;
		try {

			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:jboss/datasources/SuiaDS");
			connMY = ds.getConnection();
		}

		catch (Exception e) {
			e.printStackTrace();

			// TODO: handle exception
		}

		return connMY;

	}

	/**
	 * Consultar los rangos Especie, Subespecie, Forma, Variedad
	 * 
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public List<TaxaRank> findSpeciesTaxaRanksSpecie() {

		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM TaxaRank o where o.taraStatus = true and o.lenguaje.talaId=1 and o.taraLevel>=70 order by o.taraLevel");
			List<TaxaRank> result = (List<TaxaRank>) query.getResultList();
			if (result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return new ArrayList<TaxaRank>();
		}
		return new ArrayList<TaxaRank>();
	}

	/**
	 * Cargar mapa de grupos artificiales y reino
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> getArtificialGroupKingdom() {
		Map<String, String> mapResult = new LinkedHashMap<>();
		List<Object[]> result = new ArrayList<>();
		Query q;
		EntityManager en = super.getEntityManager();
		q = en.createNativeQuery("SELECT argr_id, argr_name, argr_code, argr_animal, argr_plantae, argr_fungi, \r\n"
				+ "       argr_eubacteria, argr_archeobacteria, argr_protista, \r\n"
				+ "       argr_chromista, argr_viruses\r\n"
				+ "  FROM biodiversity.artificial_groups where argr_status=true");
		result = q.getResultList();
		if (result != null && !result.isEmpty()) {
			for (Object[] row : result) {
				if (row != null) {
					if (row[2] != null) {
						StringBuilder rein = new StringBuilder();
						rein.append(row[0]);
						rein.append(";");
						if ((boolean) row[3]) {
							rein.append("ANIMAL");
							rein.append(";");
						}
						if ((boolean) row[4]) {
							rein.append("PLANTAE");
							rein.append(";");
						}
						if ((boolean) row[5]) {
							rein.append("FUNGI");
							rein.append(";");
						}
						if ((boolean) row[6]) {
							rein.append("BACTERIA");
							rein.append(";");
						}
						if ((boolean) row[7]) {
							rein.append("ARCHAEA");
							rein.append(";");
						}
						if ((boolean) row[8]) {
							rein.append("PROTOZOA");
							rein.append(";");
						}
						if ((boolean) row[9]) {
							rein.append("CHROMISTA");
							rein.append(";");
						}
						if ((boolean) row[10]) {
							rein.append("VIRUSES");
							rein.append(";");
						}
						mapResult.put(row[2].toString(), rein.toString());
					}
				}
			}
		}
		return mapResult;
	}

	/**
	 * Consultar y llenar el mapa de los codigos de las categorias cites
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Integer> getCodigosCites() {
		Map<String, Integer> mapResult = new LinkedHashMap<>();
		List<Object[]> result = new ArrayList<>();
		Query q;
		EntityManager en = super.getEntityManager();
		q = en.createNativeQuery("SELECT cite_id, cite_name" + "  FROM biodiversity.cites where cite_status=true");
		result = q.getResultList();
		if (result != null && !result.isEmpty()) {
			for (Object[] row : result) {
				if (row != null) {

					mapResult.put(row[1].toString(), Integer.parseInt(row[0].toString()));

				}
			}
		}
		return mapResult;
	}

	/**
	 * Consultar y llenar mapa de las categorias de lista roja ecuador
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Integer> getCodigosListaRojaEc() {
		Map<String, Integer> mapResult = new LinkedHashMap<>();
		List<Object[]> result = new ArrayList<>();
		Query q;
		EntityManager en = super.getEntityManager();
		q = en.createNativeQuery("SELECT rlec_id, rlec_name, rlec_initial"
				+ "  FROM biodiversity.red_lists_ecuador where rlec_status=true");
		result = q.getResultList();
		if (result != null && !result.isEmpty()) {
			for (Object[] row : result) {
				if (row != null) {

					mapResult.put(row[2].toString(), Integer.parseInt(row[0].toString()));

				}
			}
		}
		return mapResult;
	}

	/**
	 * Consultar y llenar mapa de las categorias de lista roja uicn
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Integer> getCodigosListaRojaUi() {
		Map<String, Integer> mapResult = new LinkedHashMap<>();
		List<Object[]> result = new ArrayList<>();
		Query q;
		EntityManager en = super.getEntityManager();
		q = en.createNativeQuery("SELECT rlui_id, rlui_name, rlui_initial"
				+ "  FROM biodiversity.red_lists_uicn where rlui_status=true");
		result = q.getResultList();
		if (result != null && !result.isEmpty()) {
			for (Object[] row : result) {
				if (row != null) {

					mapResult.put(row[2].toString(), Integer.parseInt(row[0].toString()));

				}
			}
		}
		return mapResult;
	}

	/**
	 * Consultar y llenar mapa de fuentes taxonomicas
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> getCodigosTaxonomicSource() {
		Map<String, String> mapResult = new LinkedHashMap<>();
		List<Object[]> result = new ArrayList<>();
		Query q;
		EntityManager en = super.getEntityManager();
		q = en.createNativeQuery(
				"SELECT taso_id, taso_name, taso_nomenclature, taso_animal , taso_plantae, taso_fungi, taso_eubacteria, taso_archeobacteria, taso_protista, taso_chromista, taso_viruses"
						+ "  FROM biodiversity.taxonomic_sources where taso_status=true");
		result = q.getResultList();
		if (result != null && !result.isEmpty()) {
			for (Object[] row : result) {
				if (row != null) {
					if (row[2] != null) {
						StringBuilder rein = new StringBuilder();
						rein.append(row[0]);
						rein.append(";");
						if ((boolean) row[3]) {
							rein.append("ANIMAL");
							rein.append(";");
						}
						if ((boolean) row[4]) {
							rein.append("PLANTAE");
							rein.append(";");
						}
						if ((boolean) row[5]) {
							rein.append("FUNGI");
							rein.append(";");
						}
						if ((boolean) row[6]) {
							rein.append("BACTERIA");
							rein.append(";");
						}
						if ((boolean) row[7]) {
							rein.append("ARCHAEA");
							rein.append(";");
						}
						if ((boolean) row[8]) {
							rein.append("PROTOZOA");
							rein.append(";");
						}
						if ((boolean) row[9]) {
							rein.append("CHROMISTA");
							rein.append(";");
						}
						if ((boolean) row[10]) {
							rein.append("VIRUSES");
							rein.append(";");
						}
						mapResult.put(row[1].toString().toUpperCase(), rein.toString());
					}
				}
			}
		}
		return mapResult;
	}
	
	/**
	 * Consultar todas las especies
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> findAllSpecie() {
		List<Object[]> result = new ArrayList<>();
		Query q;
		EntityManager en = super.getEntityManager();
		q = en.createNativeQuery("SELECT e.spta_id AS id_especie,\r\n" + "    e.spta_scientific_gui AS gui_especie,\r\n"
				+ "    e.spta_scientific_name AS nombrecientifico,\r\n" + "    g.hicl_id AS id_genero,\r\n"
				+ "    g.hicl_scientific_gui AS gui_genero,\r\n" + "    g.hicl_scientific_name AS genero, spta_specific_infraspecifc_epit " + "\r\n"
				+ "   FROM biodiversity.higher_classifications g,\r\n" + "    biodiversity.species_taxa e\r\n"
				+ "  WHERE g.hicl_id = e.hicl_id");
		if (q.getResultList() != null && !q.getResultList().isEmpty()) {
			result = q.getResultList();
		}
		return result;
	}

	/**
	 * insertar o actualizar un grupo de especies
	 * 
	 * @param taxa
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public void insertarActualizarEspecie(List<SpecieLoadTaxa> taxa) throws Exception {
		PreparedStatement pstmt = null;
		PreparedStatement pstmtActCampos = null;

		try {
			Connection con = getConnection();
			if (con != null) {

				String sq1 = "UPDATE biodiversity.species_taxa SET  tast_id = ?, cite_id = ?, spta_red_list_ec_criteria = ?, spta_cites_criteria = ?, spta_ecuador = ?, spta_endemic = ?, spta_exotic = ?, spta_domestic = ?, spta_native = ?, spta_migratory = ?, spta_alien = ?, spta_scientific_name_authorship = ?, spta_name_published_year = ?, spta_taxon_remarks = ?, spta_red_list_ec = ?, spta_cites_name = ?, spta_taxonomic_status_name = ?, noco_id = ?,  spta_user_update = ?, spta_date_update = ?, spta_correct_tax = ?, spta_verified_for = ?, spta_red_list_uicn_criteria = ? , spta_red_list_uicn = ?, rlec_id = ?, rlui_id = ?, taso_id = ?, spta_correct_tax_id = ? , spta_correct_tax_name = ? , spta_continental = ?, spta_insular = ?  where spta_scientific_gui = ?";

				String sqGroup = "insert into biodiversity.species_taxa_artificial_groups (argr_id, spta_id, stag_status, stag_user_create, stag_date_create, stag_nombre) values (?,?, ?, ? ,?, (select distinct argr_name from biodiversity.artificial_groups where argr_code = ? LIMIT 1))";
				pstmt = con.prepareStatement(
						"insert into biodiversity.species_taxa (hicl_id ,tast_id,tara_id,cite_id,spta_specific_infraspecifc_epit,spta_scientific_name,spta_red_list_ec_criteria,spta_cites_criteria,spta_ecuador,spta_endemic,spta_exotic,spta_domestic,spta_native,spta_migratory,spta_alien,spta_scientific_name_authorship,spta_name_published_year,spta_taxon_remarks,spta_taxon_rank_name, spta_red_list_ec, spta_cites_name, spta_taxonomic_status_name, noco_id, spta_status, spta_user_create, spta_date_create, spta_hierarchy_code, spta_correct_tax, spta_verified_for, spta_search_code, spta_is_verified, spta_red_list_uicn_criteria, spta_red_list_uicn, rlec_id, rlui_id, taso_id, spta_correct_tax_id, spta_correct_tax_name, spta_higher_classification, spta_continental, spta_insular)  values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?||(select last_value from biodiversity.seq_spta_id), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				pstmtActCampos = con.prepareStatement(sq1);

				System.out.println("Inicio batch.");
				long start = System.currentTimeMillis();

				for (SpecieLoadTaxa specie : taxa) {

					if (specie.getCodigoGui() == null || specie.getCodigoGui().equals("")) {
						pstmt.setInt(1, specie.getParentId());

						pstmt.setInt(2, specie.getIdStatusTaxonomico());
						pstmt.setInt(3, specie.getRangoTaxonomico());
						if (specie.getIdCite() != null) {
							pstmt.setInt(4, specie.getIdCite());
						} else {
							pstmt.setNull(4, Types.INTEGER);
						}
						pstmt.setString(5, specie.getEpitEspecInfr());
						pstmt.setString(6, specie.getScientificName());
						if (specie.getCritListaRojaEc() != null) {
							pstmt.setString(7, specie.getCritListaRojaEc());
						} else {
							pstmt.setNull(7, Types.VARCHAR);
						}

						if (specie.getCritCite() != null) {
							pstmt.setString(8, specie.getCritCite());
						} else {
							pstmt.setNull(8, Types.VARCHAR);
						}

						if (specie.getDeEcuador() != null) {
							pstmt.setBoolean(9, specie.getDeEcuador());
						} else {
							pstmt.setNull(9, Types.BOOLEAN);
						}

						if (specie.getEndemica() != null) {
							pstmt.setBoolean(10, specie.getEndemica());
						} else {
							pstmt.setNull(10, Types.BOOLEAN);
						}

						if (specie.getExotica() != null) {
							pstmt.setBoolean(11, specie.getExotica());
						} else {
							pstmt.setNull(11, Types.BOOLEAN);
						}

						if (specie.getDomestica() != null) {
							pstmt.setBoolean(12, specie.getDomestica());
						} else {
							pstmt.setNull(12, Types.BOOLEAN);
						}

						
						if (specie.getNativa() != null) {
							pstmt.setBoolean(13, specie.getNativa());
						} else {
							pstmt.setNull(13, Types.BOOLEAN);
						}

						if (specie.getMigratoria() != null) {
							pstmt.setBoolean(14, specie.getMigratoria());
						} else {
							pstmt.setNull(14, Types.BOOLEAN);
						}

						if (specie.getInvasora() != null) {
							pstmt.setBoolean(15, specie.getInvasora());
						} else {
							pstmt.setNull(15, Types.BOOLEAN);
						}
						
						
						if (!specie.getAnioPublicacion().equals("0")) {
							pstmt.setString(17, specie.getAnioPublicacion());
						} else {
							pstmt.setNull(17, Types.VARCHAR);
						}
						
						if (!specie.getAutor().equals("N/D")) {
							pstmt.setString(16, specie.getAutor());
						} else {
							pstmt.setNull(16, Types.VARCHAR);
						}
						
						
						pstmt.setString(18, specie.getObservaciones()+" "+specie.getOtraFuenteTaxonomica()+" "+specie.getUrlFuenteTaxonomica());
						pstmt.setString(19, specie.getHiclTaxonRankName());
						if (specie.getNombreListaRojaEc() != null) {
							pstmt.setString(20, specie.getNombreListaRojaEc());
						} else {
							pstmt.setNull(20, Types.VARCHAR);
						}

						if (specie.getNombreCites() != null) {
							pstmt.setString(21, specie.getNombreCites());
						} else {
							pstmt.setNull(21, Types.VARCHAR);
						}

						if (specie.getNombreStatusTaxonomico() != null) {
							pstmt.setString(22, specie.getNombreStatusTaxonomico());
						} else {
							pstmt.setNull(22, Types.VARCHAR);
						}
						pstmt.setInt(23, specie.getIdCodigoNomenclatural());
						pstmt.setBoolean(24, true);
						pstmt.setString(25, specie.getHiclUserCreate());
						java.sql.Date sDate = new java.sql.Date(specie.getHiclDateCreate().getTime());
						pstmt.setDate(26, sDate);
						pstmt.setString(27, specie.getHiclHierarchyCode());
						pstmt.setBoolean(28, specie.getEsTaxonCorrecto());
						pstmt.setString(29, specie.getHiclVerifiedFor());
						pstmt.setString(30, specie.getHiclSearchCode());
						pstmt.setBoolean(31, true);
						pstmt.setString(32, specie.getCritListaRojaUi());
						if (specie.getNombreListaRojaUi() != null) {
							pstmt.setString(33, specie.getNombreListaRojaUi());
						} else {
							pstmt.setNull(33, Types.VARCHAR);
						}

						if (specie.getIdListaRojaEc() != null) {
							pstmt.setInt(34, specie.getIdListaRojaEc());
						} else {
							pstmt.setNull(34, Types.INTEGER);
						}

						if (specie.getIdListaRojaUi() != null) {
							pstmt.setInt(35, specie.getIdListaRojaUi());
						} else {
							pstmt.setNull(35, Types.INTEGER);
						}

						if (specie.getIdFuenteTaxonomica() != null) {
							pstmt.setInt(36, specie.getIdFuenteTaxonomica());
						} else {
							pstmt.setNull(36, Types.INTEGER);
						}

						if (specie.getIdTaxonCorrecto() != null) {
							pstmt.setInt(37, specie.getIdTaxonCorrecto());
						} else {
							pstmt.setNull(37, Types.INTEGER);
						}
						if (specie.getTaxonCorrecto() != null) {
							pstmt.setString(38, specie.getTaxonCorrecto());
						} else {
							pstmt.setNull(38, Types.VARCHAR);
						}
						pstmt.setString(39, specie.getHiclHigherClassification());
						

						if (specie.getContinental() != null) {
							pstmt.setBoolean(40, specie.getContinental());
						} else {
							pstmt.setNull(40, Types.BOOLEAN);
						}
						
						if (specie.getInsular() != null) {
							pstmt.setBoolean(41, specie.getInsular());
						} else {
							pstmt.setNull(41, Types.BOOLEAN);
						}
						pstmt.addBatch();

					} else {
						
						pstmtActCampos.setInt(1, specie.getIdStatusTaxonomico());
						if (specie.getIdCite() != null) {
							pstmtActCampos.setInt(2, specie.getIdCite());
						} else {
							pstmtActCampos.setNull(2, Types.INTEGER);
						}
						if (specie.getCritListaRojaEc() != null) {
							pstmtActCampos.setString(3, specie.getCritListaRojaEc());
						} else {
							pstmtActCampos.setNull(3, Types.VARCHAR);
						}

						if (specie.getCritCite() != null) {
							pstmtActCampos.setString(4, specie.getCritCite());
						} else {
							pstmtActCampos.setNull(4, Types.VARCHAR);
						}

						if (specie.getDeEcuador() != null) {
							pstmtActCampos.setBoolean(5, specie.getDeEcuador());
						} else {
							pstmtActCampos.setNull(5, Types.BOOLEAN);
						}

						if (specie.getEndemica() != null) {
							pstmtActCampos.setBoolean(6, specie.getEndemica());
						} else {
							pstmtActCampos.setNull(6, Types.BOOLEAN);
						}

						if (specie.getExotica() != null) {
							pstmtActCampos.setBoolean(7, specie.getExotica());
						} else {
							pstmtActCampos.setNull(7, Types.BOOLEAN);
						}

						if (specie.getDomestica() != null) {
							pstmtActCampos.setBoolean(8, specie.getDomestica());
						} else {
							pstmtActCampos.setNull(8, Types.BOOLEAN);
						}

						

						if (specie.getNativa() != null) {
							pstmtActCampos.setBoolean(9, specie.getNativa());
						} else {
							pstmtActCampos.setNull(9, Types.BOOLEAN);
						}

						if (specie.getMigratoria() != null) {
							pstmtActCampos.setBoolean(10, specie.getMigratoria());
						} else {
							pstmtActCampos.setNull(10, Types.BOOLEAN);
						}

						if (specie.getInvasora() != null) {
							pstmtActCampos.setBoolean(11, specie.getInvasora());
						} else {
							pstmtActCampos.setNull(11, Types.BOOLEAN);
						}
						
						if (!specie.getAnioPublicacion().equals("0")) {
							pstmtActCampos.setString(13, specie.getAnioPublicacion());
						} else {
							pstmtActCampos.setNull(13, Types.VARCHAR);
						}
						
						if (!specie.getAutor().equals("N/D")) {
							pstmtActCampos.setString(12, specie.getAutor());
						} else {
							pstmtActCampos.setNull(12, Types.VARCHAR);
						}
						
						pstmtActCampos.setString(14, specie.getObservaciones()+" "+specie.getOtraFuenteTaxonomica()+" "+specie.getUrlFuenteTaxonomica());
						if (specie.getNombreListaRojaEc() != null) {
							pstmtActCampos.setString(15, specie.getNombreListaRojaEc());
						} else {
							pstmtActCampos.setNull(15, Types.VARCHAR);
						}

						if (specie.getNombreCites() != null) {
							pstmtActCampos.setString(16, specie.getNombreCites());
						} else {
							pstmtActCampos.setNull(16, Types.VARCHAR);
						}

						if (specie.getNombreStatusTaxonomico() != null) {
							pstmtActCampos.setString(17, specie.getNombreStatusTaxonomico());
						} else {
							pstmtActCampos.setNull(17, Types.VARCHAR);
						}
						pstmtActCampos.setInt(18, specie.getIdCodigoNomenclatural());
						pstmtActCampos.setString(19, specie.getHiclUserCreate());
						java.sql.Date sDate = new java.sql.Date(specie.getHiclDateCreate().getTime());
						pstmtActCampos.setDate(20, sDate);
						pstmtActCampos.setBoolean(21, specie.getEsTaxonCorrecto());
						pstmtActCampos.setString(22, specie.getHiclVerifiedFor());
						pstmtActCampos.setString(23, specie.getCritListaRojaUi());
						if (specie.getNombreListaRojaUi() != null) {
							pstmtActCampos.setString(24, specie.getNombreListaRojaUi());
						} else {
							pstmtActCampos.setNull(24, Types.VARCHAR);
						}

						if (specie.getIdListaRojaEc() != null) {
							pstmtActCampos.setInt(25, specie.getIdListaRojaEc());
						} else {
							pstmtActCampos.setNull(25, Types.INTEGER);
						}

						if (specie.getIdListaRojaUi() != null) {
							pstmtActCampos.setInt(26, specie.getIdListaRojaUi());
						} else {
							pstmtActCampos.setNull(26, Types.INTEGER);
						}

						if (specie.getIdFuenteTaxonomica() != null) {
							pstmtActCampos.setInt(27, specie.getIdFuenteTaxonomica());
						} else {
							pstmtActCampos.setNull(27, Types.INTEGER);
						}

						if (specie.getIdTaxonCorrecto() != null) {
							pstmtActCampos.setInt(28, specie.getIdTaxonCorrecto());
						} else {
							pstmtActCampos.setNull(28, Types.INTEGER);
						}
						if (specie.getTaxonCorrecto() != null) {
							pstmtActCampos.setString(29, specie.getTaxonCorrecto());
						} else {
							pstmtActCampos.setNull(29, Types.VARCHAR);
						}
						pstmtActCampos.setString(32, specie.getCodigoGui());
						if (specie.getContinental() != null) {
							pstmtActCampos.setBoolean(30, specie.getContinental());
						} else {
							pstmtActCampos.setNull(30, Types.BOOLEAN);
						}
						
						if (specie.getInsular() != null) {
							pstmtActCampos.setBoolean(31, specie.getInsular());
						} else {
							pstmtActCampos.setNull(31, Types.BOOLEAN);
						}
						pstmtActCampos.addBatch();

					}
				}
				pstmt.executeBatch();

				pstmtActCampos.executeBatch();

				long end = System.currentTimeMillis();
				System.out.println("Fin batch(insert en): " + (end - start) + " milliseconds.");

			} else {
				System.out.println("Error de conexión ");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (pstmtActCampos != null) {
					pstmtActCampos.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * insertar grupos artificiales de una especie
	 * 
	 * @param taxa
	 * @throws Exception
	 */
	public void insertarActualizarEspecieGrupos(List<SpecieLoadTaxa> taxa) throws Exception {

		PreparedStatement pstmtInsGrup = null;
		String sqGroup = "insert into biodiversity.species_taxa_artificial_groups (argr_id, spta_id, stag_status, stag_user_create, stag_date_create, stag_nombre) values (?,(select spta_id from biodiversity.species_taxa where hicl_id = ? and spta_scientific_name = ?), ?, ? ,?, (select distinct argr_name from biodiversity.artificial_groups where argr_code = ? LIMIT 1))";
		try {
			Connection con = getConnection();
			if (con != null) {
				pstmtInsGrup = con.prepareStatement(sqGroup);
				for (SpecieLoadTaxa specie : taxa) {

					if (specie.getCodigoGui() == null || specie.getCodigoGui().equals("")) {
						if (specie.getGruposArtificiales() != null && !specie.getGruposArtificiales().equals("")) {

							String[] grup = specie.getGruposArtificiales().split(";");
							String[] nombreGrup = specie.getNombresGrupos().split(";");

							for (int i = 0; i < grup.length; i++) {
								pstmtInsGrup.setInt(1, Integer.parseInt(grup[i]));
								pstmtInsGrup.setInt(2, specie.getParentId());
								pstmtInsGrup.setString(3, specie.getScientificName());
								pstmtInsGrup.setBoolean(4, true);
								pstmtInsGrup.setString(5, specie.getHiclUserCreate());
								java.sql.Date sDateS = new java.sql.Date(specie.getHiclDateCreate().getTime());
								pstmtInsGrup.setDate(6, sDateS);
								pstmtInsGrup.setString(7, nombreGrup[i]);
								pstmtInsGrup.addBatch();
							}

						}
					}
				}

				pstmtInsGrup.executeBatch();
			} else {
				System.out.println("Error de conexión ");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmtInsGrup != null) {
					pstmtInsGrup.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * metodo que busca una especie de acuerdo al gui
	 * 
	 * @param gui
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> obtenerGuiEspecie(String gui) {
		List<Object[]> result = new ArrayList<>();
		Query q;
		EntityManager en = super.getEntityManager();
		q = en.createNativeQuery(
				"SELECT spta_hierarchy_code,spta_scientific_gui from biodiversity.species_taxa where spta_scientific_gui = ?");
		q.setParameter(1, gui);
		if (q.getResultList() != null && !q.getResultList().isEmpty()) {
			result = q.getResultList();
		}
		return result;
	}

	/**
	 * Devuelve un registro buscado por gui, nombre cientifico y rango
	 * 
	 * @param gui
	 * @param scientificName
	 * @param idRank
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> obtenerGuiRankScientificName(String gui, String scientificName, Integer idRank) {
		List<Object[]> result = new ArrayList<>();
		try {
			Query q;
			EntityManager en = super.getEntityManager();
			q = en.createNativeQuery(
					"SELECT hicl_hierarchy_code, hicl_scientific_gui from biodiversity.higher_classifications where hicl_scientific_gui = ? and hicl_scientific_name = ? and tara_id = ?");
			q.setParameter(1, gui);
			q.setParameter(2, scientificName);
			q.setParameter(3, idRank);
			if (q.getResultList() != null && !q.getResultList().isEmpty()) {
				result = q.getResultList();
			}
		} catch (Exception e) {
			return new ArrayList<>();
		}
		return result;
	}

	/**
	 * metodo que busca una especie de acuerdo al gui y el nombre cientifico
	 * 
	 * @param gui
	 * @param scientificName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> obtenerGuiEspecieScientificName(String gui, String scientificName) {
		List<Object[]> result = new ArrayList<>();
		try {
			Query q;
			EntityManager en = super.getEntityManager();
			q = en.createNativeQuery(
					"SELECT spta_hierarchy_code, spta_scientific_gui from biodiversity.species_taxa where spta_scientific_gui = ? and spta_scientific_name = ?");
			q.setParameter(1, gui);
			q.setParameter(2, scientificName);
			if (q.getResultList() != null && !q.getResultList().isEmpty()) {
				result = q.getResultList();
			}
		} catch (Exception e) {
			return new ArrayList<>();
		}
		return result;
	}

	/**
	 * Devuelve un registro buscado por gui y rango
	 * 
	 * @param gui
	 * @param scientificName
	 * @param idRank
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> obtenerGuiRank(String gui, Integer idRank) {
		List<Object[]> result = new ArrayList<>();
		try {
			Query q;
			EntityManager en = super.getEntityManager();
			q = en.createNativeQuery(
					"SELECT hicl_hierarchy_code,hicl_scientific_gui from biodiversity.higher_classifications where hicl_scientific_gui = ? and tara_id = ?");
			q.setParameter(1, gui);
			q.setParameter(2, idRank);
			if (q.getResultList() != null && !q.getResultList().isEmpty()) {
				result = q.getResultList();
			}
		} catch (Exception e) {
			return new ArrayList<>();
		}
		return result;
	}

	/**
	 * Obtener por nombre y rango
	 * 
	 * @param scientificName
	 * @param idRank
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> obtenerScientificNameRank(String scientificName, Integer idRank) {
		List<Object[]> result = new ArrayList<>();
		try {
			Query q;
			EntityManager en = super.getEntityManager();
			q = en.createNativeQuery(
					"SELECT hicl_hierarchy_code, hicl_scientific_gui from biodiversity.higher_classifications where hicl_scientific_name = ? and tara_id = ?");
			q.setParameter(1, scientificName);
			q.setParameter(2, idRank);
			if (q.getResultList() != null && !q.getResultList().isEmpty()) {
				result = q.getResultList();
			}
		} catch (Exception e) {
			return new ArrayList<>();
		}
		return result;
	}

	/**
	 * metodo que busca una especie de acuerdo al nombre cientifico
	 * 
	 * @param gui
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> obtenerScientificNameEspecie(String sName) {
		List<Object[]> result = new ArrayList<>();
		try {
			Query q;
			EntityManager en = super.getEntityManager();
			q = en.createNativeQuery(
					"SELECT spta_hierarchy_code, spta_scientific_gui from biodiversity.species_taxa where spta_scientific_name = ?");
			q.setParameter(1, sName);
			if (q.getResultList() != null && !q.getResultList().isEmpty()) {
				result = q.getResultList();
			}
		} catch (Exception e) {
			return new ArrayList<>();
		}
		return result;
	}
	
	
	/**
	 * Llenar mapa con id y nombre de estatus taxonomico
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<Integer, String> getStatusCodigoNombre() {
		Map<Integer, String> mapResult = new LinkedHashMap<>();
		List<Object[]> result = new ArrayList<>();
		Query q;
		EntityManager en = super.getEntityManager();
		q = en.createNativeQuery("SELECT tast_id, tast_name, tast_code  \r\n"
				+ "  FROM biodiversity.taxonomic_status where tast_status=true");
		result = q.getResultList();
		if (result != null && !result.isEmpty()) {
			for (Object[] row : result) {
				if (row != null) {

					mapResult.put((Integer) row[0], row[1].toString());
				}
			}
		}

		return mapResult;
	}

	public HigherClassification buscarporRango(String especie, Integer rango) {
		try {
			TypedQuery<HigherClassification> query = super.getEntityManager().createQuery(
					"select o from HigherClassification o where upper(o.hiclScientificName) like :especie and o.taxaRank.taraId = :rango and  o.hiclStatus = true",
					HigherClassification.class);
			query.setParameter("especie", especie.toUpperCase());
			query.setParameter("rango", rango);
			HigherClassification higherClassification = new HigherClassification();
			if (query.getResultList().size() > 0) {
				higherClassification = query.getResultList().get(0);
			}
			return higherClassification;
		} catch (NoResultException e) {
			return null;
		}

	}

//	public HigherClassification buscarReinoporRango(String especie, Integer rango) {
//		try {
//	
//			
//			TypedQuery<HigherClassification> query = super.getEntityManager().createQuery(
//					"select r from HigherClassification r where r.hiclId = ("
//							+ "select g.hiclIdParent from HigherClassification g where g.hiclId = ("
//							+ "select a.hiclIdParent from HigherClassification a where a.hiclId = ("
//							+ "select c.hiclIdParent from HigherClassification c where c.hiclId = ("
//							+ "select p.hiclIdParent from HigherClassification p where p.hiclId = ("
//							+ "select o.hiclIdParent from HigherClassification o where upper(o.hiclScientificName) like :especie "
//							+ "and o.taxaRank.taraId = :rango and  o.hiclStatus = true)))))",
//							//+ "and o.taxaRank.taraId = :rango and split_part(o.hiclHigherClassification,'|',1) = 'Plantae' and  o.hiclStatus = true)))))",
//					HigherClassification.class);
//			query.setParameter("especie", especie.toUpperCase());
//			query.setParameter("rango", rango);
//			HigherClassification higherClassification = new HigherClassification();
//			if (query.getResultList().size() > 0) {
//				higherClassification = query.getResultList().get(0);
//			}
//			return higherClassification;
//		} catch (NoResultException e) {
//			return null;
//		}
//
//	}
	
	public HigherClassification buscarReinoporRango(String especie, String categoría, Integer rango) {
		try {
			String filtro = "";
	
			if(categoría.equals("Viveros / Orquidearios con fines comerciales")||categoría.equals("Viveros / Orquidearios sin fines comerciales") ||categoría.equals("Jardines botánicos")) {
				filtro = "and split_part(o.hiclHigherClassification,'|',1) = 'Plantae'";
			}
			if(categoría.equals("Zoológicos")||categoría.equals("Acuarios") ||categoría.equals("Centros de cría y reproducción sostenible con fines comerciales") ||categoría.equals("Centros de cría y reproducción sostenible sin fines comerciales")) {
				filtro =  "and split_part(o.hiclHigherClassification,'|',1) = 'Animal'";
			}
			if(categoría.equals("Centros de rescate y rehabilitación")||categoría.equals("Centros de paso")) {
				filtro = "and split_part(o.hiclHigherClassification,'|',1) = 'Plantae'";
				filtro = "and split_part(o.hiclHigherClassification,'|',1) = 'Animal'";
			}
			
			
			
			TypedQuery<HigherClassification> query = super.getEntityManager().createQuery(
					"select r from HigherClassification r where r.hiclId = ("
							+ "select g.hiclIdParent from HigherClassification g where g.hiclId = ("
							+ "select a.hiclIdParent from HigherClassification a where a.hiclId = ("
							+ "select c.hiclIdParent from HigherClassification c where c.hiclId = ("
							+ "select p.hiclIdParent from HigherClassification p where p.hiclId = ("
							+ "select o.hiclIdParent from HigherClassification o where upper(o.hiclScientificName) like :especie "
							+ "and o.taxaRank.taraId = :rango "+filtro+" and o.hiclStatus = true)))))", HigherClassification.class);
			query.setParameter("especie", especie.toUpperCase());
			query.setParameter("rango", rango);
			
			HigherClassification higherClassification = new HigherClassification();
			if (query.getResultList().size() > 0) {
				higherClassification = query.getResultList().get(0);
			}
			return higherClassification;
		} catch (NoResultException e) {
			return null;
		}

	}
	
	
	
	
	public HigherClassification buscarReinoporRango5(String especie, Integer rango) {
		try {
			TypedQuery<HigherClassification> query = super.getEntityManager().createQuery(
					"select g from HigherClassification g where g.hiclId = ("
							+ "select a.hiclIdParent from HigherClassification a where a.hiclId = ("
							+ "select c.hiclIdParent from HigherClassification c where c.hiclId = ("
							+ "select p.hiclIdParent from HigherClassification p where p.hiclId = ("
							+ "select o.hiclIdParent from HigherClassification o where upper(o.hiclScientificName) like :especie "
							+ "and o.taxaRank.taraId = :rango and  o.hiclStatus = true))))",
					HigherClassification.class);
			query.setParameter("especie", especie.toUpperCase());
			query.setParameter("rango", rango);
			HigherClassification higherClassification = new HigherClassification();
			if (query.getResultList().size() > 0) {
				higherClassification = query.getResultList().get(0);
			}
			return higherClassification;
		} catch (NoResultException e) {
			return null;
		}

	}
	
	public HigherClassification buscarReinoporRango4(String especie, Integer rango) {
		try {
			TypedQuery<HigherClassification> query = super.getEntityManager().createQuery(
					"select a from HigherClassification a where a.hiclId = ("
							+ "select c.hiclIdParent from HigherClassification c where c.hiclId = ("
							+ "select p.hiclIdParent from HigherClassification p where p.hiclId = ("
							+ "select o.hiclIdParent from HigherClassification o where upper(o.hiclScientificName) like :especie "
							+ "and o.taxaRank.taraId = :rango and  o.hiclStatus = true)))",
					HigherClassification.class);
			query.setParameter("especie", especie.toUpperCase());
			query.setParameter("rango", rango);
			HigherClassification higherClassification = new HigherClassification();
			if (query.getResultList().size() > 0) {
				higherClassification = query.getResultList().get(0);
			}
			return higherClassification;
		} catch (NoResultException e) {
			return null;
		}

	}

	public HigherClassification findByName(String nombre) {
		TypedQuery<HigherClassification> query = super.getEntityManager().createQuery(
				"select u from HigherClassification u " + "where u.hiclScientificName = :nombre ",
				HigherClassification.class);
		query.setParameter("nombre", nombre);

		HigherClassification higherClassification = new HigherClassification();
		if (query.getResultList().size() > 0) {
			higherClassification = query.getResultList().get(0);
		} else {
			return null;
		}
		return higherClassification;
	}
	
		
	/**
	 * Metodo para actualizar el campo que concatena los nombres de las taxas hijas 
	 * cuando se actualiza el nombre de un taxon
	 * @param likeBuscar
	 * @param codigoNombresAReemplazar
	 * @param codigoNombresReemplazar
	 * @param userUpdate
	 * @return
	 */
	public boolean actualizarNombreCientificoClasificacion(String likeBuscar, String codigoNombresAReemplazar, String codigoNombresReemplazar,
			String userUpdate) {
		boolean resultado = false;
		try {

			Query q;
			q = getEntityManager().createNativeQuery(
					"UPDATE biodiversity.higher_classifications SET hicl_higher_classification = REPLACE (hicl_higher_classification, ?3 , ?4), hicl_user_update = ?1  where hicl_hierarchy_code LIKE ?2");
			
			q.setParameter(1, userUpdate);
			q.setParameter(2, likeBuscar);
			q.setParameter(3, codigoNombresAReemplazar);
			q.setParameter(4, codigoNombresReemplazar);

			q.executeUpdate();
			resultado = true;
		} catch (Exception e) {
			e.printStackTrace();
			resultado = false;
		}
		return resultado;
	}
	/**
	 * Metodo para actualizar el nombre cientifico y el campo que concatena los nombres
	 * cuando se actualiza el nombre de un genero
	 * @param likeBuscar
	 * @param codigoNombresAReemplazar
	 * @param codigoNombresReemplazar
	 * @param userUpdate
	 * @return
	 */
	
	public boolean actualizarNombreCientificoEspecies(Integer idGenero, String codigoNombresAReemplazar, String codigoNombresReemplazar, String nombreGeneroAnterior, String nombreGeneroNuevo,
			String userUpdate) {
		boolean resultado = false;
		try {

			Query q;
			q = getEntityManager().createNativeQuery(
					"UPDATE biodiversity.species_taxa SET spta_higher_classification = REPLACE (spta_higher_classification, ?3 , ?4), spta_scientific_name =  REPLACE (spta_scientific_name, ?5 , ?6), spta_user_update = ?1  where hicl_id = ?2");
			
			q.setParameter(1, userUpdate);
			q.setParameter(2, idGenero);
			q.setParameter(3, codigoNombresAReemplazar);
			q.setParameter(4, codigoNombresReemplazar);
			q.setParameter(5, nombreGeneroAnterior);
			q.setParameter(6, nombreGeneroNuevo);

			q.executeUpdate();
			resultado = true;
		} catch (Exception e) {
			resultado = false;
		}
		return resultado;
	}
	
	/**
	 * Actualizar campo de nombres concatenados cuando se actualiza el nombre cientifico
	 * de un taxon
	 * @param codigoNombresAReemplazar
	 * @param codigoNombresReemplazar
	 * @param userUpdate
	 * @return
	 */
	public boolean actualizarNombreConcatenadoEspecies(String likeBuscar,String codigoNombresAReemplazar, String codigoNombresReemplazar,String userUpdate) {
		boolean resultado = false;
		try {

			Query q;
			q = getEntityManager().createNativeQuery(
					"UPDATE biodiversity.species_taxa SET spta_higher_classification = REPLACE (spta_higher_classification, ?3 , ?4), spta_user_update = ?1  where spta_hierarchy_code LIKE ?2");
			
			q.setParameter(1, userUpdate);
			q.setParameter(2, likeBuscar);
			q.setParameter(3, codigoNombresAReemplazar);
			q.setParameter(4, codigoNombresReemplazar);
			
			q.executeUpdate();
			resultado = true;
		} catch (Exception e) {
			resultado = false;
		}
		return resultado;
	}
	
	/**
	 * Metodo para buscar clases por reino
	 * @param idKingdom
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HigherClassification> findClassByKingdom(Integer idKingdom) {
		try {
			Query query = super.getEntityManager().createQuery(
					"select o from HigherClassification o where o.hiclStatus = true and o.taxaRank.taraId = 3 and o.hiclHierarchyCode like :idKingdom order by o.hiclScientificName");
			query.setParameter("idKingdom", idKingdom.toString()+";%");
			List<HigherClassification> result = (List<HigherClassification>) query.getResultList();
			if (result.size() > 0)
				return result;
		} catch (NoResultException e) {
			return new ArrayList<HigherClassification>();
		}
		return new ArrayList<HigherClassification>();
	}
	
	
	/**
	 * Obtener los documentos plantillas para carga masiva
	 * @param nameFolder
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Object[]> getTemplateCargaMasiva(String nameFolder) {
		try {
			List<Object[]> resultado = new ArrayList<>();
			String idFolder = alfrescoServiceBean.parentRoot(nameFolder);
			List<Object[]> lstFolders = (List)cmisAlfrescoSUIA.getFolderContent(idFolder,false);
			if (!lstFolders.isEmpty()) {
				for (Object [] folder : lstFolders) {
					if(folder[2].toString().equals("Administracion_SIB-ec"))
					{
						
						List<Object[]> lstFoldersInt = (List)cmisAlfrescoSUIA.getFolderContent(folder[0].toString(),false);
						for (Object [] folderInt : lstFoldersInt) {
							if(folderInt[2].toString().equals("Carga_masiva_cpn"))
							{
								List<Documents> lstDocs = cmisAlfrescoSUIA.findDocumentByFolder(folderInt[0].toString());
								resultado=loopDocs(lstDocs);
								break;
							}
						}
						
						break;
					}
						
								
				}
				
			}
			return resultado;		
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}
	
	
	
	private List<Object[]> loopDocs(List<Documents> lst_Docs) {
		try {
			List<Object[]> resultado = new ArrayList<>();			
			for (Documents document : lst_Docs) {
				
				Object[] item = new Object[2];
				item[0]=document.getObjectId();
				item[1]=document.getName();
				resultado.add(item);
			}
			
			return resultado;		
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}
	
	/**
	 * Obetner especies de una taxonomia determinada
	 * @param like
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean actualizarEspecieNombreConcatenado(String likeBuscar,String codigoNombresAReemplazar, String codigoNombresReemplazar,String userUpdate) {
		boolean resultB=false;
		Query q;
		EntityManager en = super.getEntityManager();
		q = en.createNativeQuery("select spta_id, spta_higher_classification from biodiversity.species_taxa where spta_hierarchy_code LIKE ?1");
		q.setParameter(1, likeBuscar);
		if (q.getResultList() != null && !q.getResultList().isEmpty()) {
			PreparedStatement pstmt = null;
			try
			{
			Connection con = getConnection();
			if (con != null) {
				pstmt = con.prepareStatement(
						"update biodiversity.species_taxa SET spta_higher_classification = ? where spta_id = ?");
			System.out.println("Inicio replace.");
			List<Object[]> result = new ArrayList<>();
			result = q.getResultList();
			for(Object[] item: result)
			{
				if(item[0]==null)
				{
					pstmt.setNull(1, Types.VARCHAR);
					pstmt.setInt(2, (Integer)item[0]);
				}
				else
				{
					pstmt.setString(1, (item[1].toString()).replace(codigoNombresAReemplazar, codigoNombresReemplazar));
					pstmt.setInt(2, (Integer)item[0]);
				}
				
				pstmt.addBatch();
			}
			System.out.println("Fin replace.");
			System.out.println("Inicio batch replace.");
			long start = System.currentTimeMillis();
			pstmt.executeBatch();
			long end = System.currentTimeMillis();
			System.out.println("Fin batch(update en): " + (end - start) + " milliseconds.");
			resultB= true;
			}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			return true;
		}
		return resultB;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Object[]> getFormatosRescate(String nameFolder) {
		try {
			List<Object[]> resultado = new ArrayList<>();
			String idFolder = alfrescoServiceBean.parentRoot(nameFolder);
			List<Object[]> lstFolders = (List)cmisAlfrescoSUIA.getFolderContent(idFolder,false);
			if (!lstFolders.isEmpty()) {
				for (Object [] folder : lstFolders) {
					if(folder[2].toString().equals("Administracion_SIB-ec"))
					{
						
						List<Object[]> lstFoldersInt = (List)cmisAlfrescoSUIA.getFolderContent(folder[0].toString(),false);
						for (Object [] folderInt : lstFoldersInt) {
							if(folderInt[2].toString().equals("Rescate_retencion"))
							{
								List<Documents> lstDocs = cmisAlfrescoSUIA.findDocumentByFolder(folderInt[0].toString());
								resultado=loopDocs(lstDocs);
								break;
							}
						}
						
						break;
					}
						
								
				}
				
			}
			return resultado;		
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}
}