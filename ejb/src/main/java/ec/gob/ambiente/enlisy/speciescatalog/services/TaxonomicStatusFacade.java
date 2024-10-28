package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.speciescatalog.model.TaxonomicStatus;

/**
 * Servicios para la administracion de las publicaciones del especialista
 * 
 * @author EXCO
 *
 */
@Stateless
public class TaxonomicStatusFacade extends AbstractFacade<TaxonomicStatus, Integer> {

	public TaxonomicStatusFacade() {
		super(TaxonomicStatus.class, Integer.class);
	}

	/**
	 * Recuperar lista de estatus reino animal
	 * 
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public List<TaxonomicStatus> findTaxonomicStatusAnimal() {

		try {
			Query query = super.getEntityManager()
					.createQuery("SELECT o FROM TaxonomicStatus o where o.tastStatus = true and o.tastAnimal=true order by o.tastName");
			List<TaxonomicStatus> result = (List<TaxonomicStatus>) query.getResultList();
			if (result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return new ArrayList<TaxonomicStatus>();
		}
		return new ArrayList<TaxonomicStatus>();
	}

	/**
	 * Recuperar lista de estatus reino plantae
	 * 
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public List<TaxonomicStatus> findTaxonomicStatusPlantae() {

		try {
			Query query = super.getEntityManager()
					.createQuery("SELECT o FROM TaxonomicStatus o where o.tastStatus = true and o.tastPlantae=true order by o.tastName");
			List<TaxonomicStatus> result = (List<TaxonomicStatus>) query.getResultList();
			if (result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return new ArrayList<TaxonomicStatus>();
		}
		return new ArrayList<TaxonomicStatus>();
	}

	/**
	 * Recuperar lista de estatus reino plantae
	 * 
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public List<TaxonomicStatus> findTaxonomicStatusFungi() {

		try {
			Query query = super.getEntityManager()
					.createQuery("SELECT o FROM TaxonomicStatus o where o.tastStatus = true and o.tastFungi=true order by o.tastName");
			List<TaxonomicStatus> result = (List<TaxonomicStatus>) query.getResultList();
			if (result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return new ArrayList<TaxonomicStatus>();
		}
		return new ArrayList<TaxonomicStatus>();
	}

	/**
	 * Recuperar lista de estatus reino eubacteria
	 * 
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public List<TaxonomicStatus> findTaxonomicStatusEubacteria() {

		try {
			Query query = super.getEntityManager()
					.createQuery("SELECT o FROM TaxonomicStatus o where o.tastStatus = true and o.tastEubacteria=true order by o.tastName");
			List<TaxonomicStatus> result = (List<TaxonomicStatus>) query.getResultList();
			if (result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return new ArrayList<TaxonomicStatus>();
		}
		return new ArrayList<TaxonomicStatus>();
	}

	/**
	 * Recuperar lista de estatus reino arqueobacteria
	 * 
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public List<TaxonomicStatus> findTaxonomicStatusArqueobacteria() {

		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM TaxonomicStatus o where o.tastStatus = true and o.tastArqueobacteria=true order by o.tastName");
			List<TaxonomicStatus> result = (List<TaxonomicStatus>) query.getResultList();
			if (result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return new ArrayList<TaxonomicStatus>();
		}
		return new ArrayList<TaxonomicStatus>();
	}

	/**
	 * Recuperar lista de estatus reino protista
	 * 
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public List<TaxonomicStatus> findTaxonomicStatusProtista() {

		try {
			Query query = super.getEntityManager()
					.createQuery("SELECT o FROM TaxonomicStatus o where o.tastStatus = true and o.tastProtista=true order by o.tastName");
			List<TaxonomicStatus> result = (List<TaxonomicStatus>) query.getResultList();
			if (result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return new ArrayList<TaxonomicStatus>();
		}
		return new ArrayList<TaxonomicStatus>();
	}

	/**
	 * Recuperar lista de estatus reino chromista
	 * 
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public List<TaxonomicStatus> findTaxonomicStatusChromista() {

		try {
			Query query = super.getEntityManager()
					.createQuery("SELECT o FROM TaxonomicStatus o where o.tastStatus = true and o.tastChromista=true order by o.tastName");
			List<TaxonomicStatus> result = (List<TaxonomicStatus>) query.getResultList();
			if (result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return new ArrayList<TaxonomicStatus>();
		}
		return new ArrayList<TaxonomicStatus>();
	}

	/**
	 * Recuperar lista de estatus reino viruses
	 * 
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public List<TaxonomicStatus> findTaxonomicStatusViruses() {

		try {
			Query query = super.getEntityManager()
					.createQuery("SELECT o FROM TaxonomicStatus o where o.tastStatus = true and o.tastViruses=true order by o.tastName");
			List<TaxonomicStatus> result = (List<TaxonomicStatus>) query.getResultList();
			if (result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return new ArrayList<TaxonomicStatus>();
		}
		return new ArrayList<TaxonomicStatus>();
	}

	/**
	 * Obtener estatus taxonomicos activos
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TaxonomicStatus> getStatus() {

		List<TaxonomicStatus> result = new ArrayList<TaxonomicStatus>();
		try {
			Query query = super.getEntityManager()
					.createQuery("select o from TaxonomicStatus o where o.tastStatus = true order by o.tastName");
			result = (List<TaxonomicStatus>) query.getResultList();

			if (!result.isEmpty()) {
				for (TaxonomicStatus item : result) {
					StringBuilder concatenacion = new StringBuilder();
					String reinos="";
					concatenacion.append("");
					if (item.getTastAnimal() != null) {
						if (item.getTastAnimal()) {
							concatenacion.append("AN,");
						}

					} 
					
					if (item.getTastPlantae() != null) {
						if (item.getTastPlantae()) {
							concatenacion.append("PL,");
						}

					} 
					
					if (item.getTastChromista() != null) {
						if (item.getTastChromista()) {
							concatenacion.append("CH,");
						}

					} 
					
					if (item.getTastEubacteria() != null) {
						if (item.getTastEubacteria()) {
							concatenacion.append("BA,");
						}

					} 
					if (item.getTastArqueobacteria() != null) {
						if (item.getTastArqueobacteria()) {
							concatenacion.append("AR,");
						}

					} 
					if (item.getTastProtista() != null) {
						if (item.getTastProtista()) {
							concatenacion.append("PR,");
						}

					} 
					if (item.getTastFungi() != null) {
						if (item.getTastFungi()) {
							concatenacion.append("FU,");
						}

					} 
					if (item.getTastViruses() != null) {
						if (item.getTastViruses()) {
							concatenacion.append("VI,");
						}

					}
					if(!concatenacion.toString().equals(""))
					{
					reinos= concatenacion.toString().substring(0, concatenacion.toString().length()-1);
					}
					item.setReinos(reinos);
				}
				return result;
			}
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return result;
	}

	/**
	 * Obtener HigherClassifications por status Taxonomino
	 * 
	 * @param sql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getHigherByStatus(Integer idStatus) {
		List<Object[]> result = new ArrayList<Object[]>();
		try {
			Query q;
			q = getEntityManager().createNativeQuery(
					"select hicl_id, tast_id from biodiversity.higher_classifications where tast_id = ?1 and hicl_status = true");
			q.setParameter(1, idStatus);
			result = (List<Object[]>) q.getResultList();
			if (q.getResultList() == null) {
				result = new ArrayList<Object[]>();
			}
		} catch (Exception ex) {
			return new ArrayList<Object[]>();
		}
		return result;
	}

	/**
	 * Obtener Especies por status Taxonomino
	 * 
	 * @param sql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getSpeciesByStatus(Integer idStatus) {
		List<Object[]> result = new ArrayList<Object[]>();
		try {
			Query q;
			q = getEntityManager().createNativeQuery(
					"select spta_id, tast_id from biodiversity.species_taxa where tast_id = ?1 and spta_status = true");
			q.setParameter(1, idStatus);
			result = (List<Object[]>) q.getResultList();
			if (q.getResultList() == null) {
				result = new ArrayList<Object[]>();
			}
		} catch (Exception ex) {
			return new ArrayList<Object[]>();
		}
		return result;
	}
	
	/**
	 * Guardar un registro de status taxonomico
	 * @param taxonomicStatus
	 * @param user
	 * @return
	 */
	public TaxonomicStatus save(TaxonomicStatus taxonomicStatus, User user) {
		TaxonomicStatus taxonomic = null;
		try {
			if (taxonomicStatus.getTastId() == null) {

				taxonomicStatus.setTastStatus(true);
				taxonomicStatus.setTastDateCreate(new Date());
				taxonomicStatus.setTastUserCreate(user.getUserName());
				taxonomic = create(taxonomicStatus);

			} else {
				taxonomicStatus.setTastDateUpdate(new Date());
				taxonomicStatus.setTastUserUpdate(user.getUserName());
				taxonomic = edit(taxonomicStatus);
			}

			return taxonomic;
		} catch (Exception ex) {
			return taxonomic;
		}
	}
	
	/**
	 * Buscar un Estatus Taxonomico por el nombre
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TaxonomicStatus> findTaxonomicStatusByName(String name) {
		try {
			Query query = super.getEntityManager()
					.createQuery("SELECT o FROM TaxonomicStatus o where o.tastStatus = true and UPPER(o.tastName) = :name");
			query.setParameter("name", name.toUpperCase());
			List<TaxonomicStatus> result = (List<TaxonomicStatus>) query.getResultList();
			if (!result.isEmpty()) {
				
				return result;
			}

		} catch (NoResultException e) {
			return new ArrayList<TaxonomicStatus>();
		}
		return new ArrayList<TaxonomicStatus>();
	}
	
	/**
	 * Buscar un Estatus Taxonomico por el nombre
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TaxonomicStatus> findTaxonomicStatusByCode(String code) {
		try {
			Query query = super.getEntityManager()
					.createQuery("SELECT o FROM TaxonomicStatus o where o.tastStatus = true and UPPER(o.tastCode) = :code");
			query.setParameter("code", code.toUpperCase());
			List<TaxonomicStatus> result = (List<TaxonomicStatus>) query.getResultList();
			if (!result.isEmpty()) {
				
				return result;
			}

		} catch (NoResultException e) {
			return new ArrayList<TaxonomicStatus>();
		}
		return new ArrayList<TaxonomicStatus>();
	}
	

}
