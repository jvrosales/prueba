package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.speciescatalog.model.HigherClassification;
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecieTaxa;
import ec.gob.ambiente.enlisy.speciescatalog.model.TaxonomicSource;

/**
 * Servicios para la administracion de las publicaciones del especialista
 * 
 * @author EXCO
 *
 */
@Stateless
public class TaxonomicSourceFacade extends AbstractFacade<TaxonomicSource, Integer> {

	public TaxonomicSourceFacade() {
		super(TaxonomicSource.class, Integer.class);
	}

	/**
	 * Recuperar lista de estatus reino animal
	 * 
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public List<TaxonomicSource> findTaxonomicSourceAnimal() {

		try {
			Query query = super.getEntityManager()
					.createQuery("SELECT o FROM TaxonomicSource o where o.tasoStatus = true and o.tasoAnimal=true order by o.tasoName");
			List<TaxonomicSource> result = (List<TaxonomicSource>) query.getResultList();
			if (result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return new ArrayList<TaxonomicSource>();
		}
		return new ArrayList<TaxonomicSource>();
	}

	/**
	 * Recuperar lista de estatus reino plantae
	 * 
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public List<TaxonomicSource> findTaxonomicSourcePlantae() {

		try {
			Query query = super.getEntityManager()
					.createQuery("SELECT o FROM TaxonomicSource o where o.tasoStatus = true and o.tasoPlantae=true order by o.tasoName");
			List<TaxonomicSource> result = (List<TaxonomicSource>) query.getResultList();
			if (result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return new ArrayList<TaxonomicSource>();
		}
		return new ArrayList<TaxonomicSource>();
	}

	/**
	 * Recuperar lista de estatus reino plantae
	 * 
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public List<TaxonomicSource> findTaxonomicSourceFungi() {

		try {
			Query query = super.getEntityManager()
					.createQuery("SELECT o FROM TaxonomicSource o where o.tasoStatus = true and o.tasoFungi=true order by o.tasoName");
			List<TaxonomicSource> result = (List<TaxonomicSource>) query.getResultList();
			if (result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return new ArrayList<TaxonomicSource>();
		}
		return new ArrayList<TaxonomicSource>();
	}

	/**
	 * Recuperar lista de estatus reino eubacteria
	 * 
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public List<TaxonomicSource> findTaxonomicSourceEubacteria() {

		try {
			Query query = super.getEntityManager()
					.createQuery("SELECT o FROM TaxonomicSource o where o.tasoStatus = true and o.tasoEubacteria=true order by o.tasoName");
			List<TaxonomicSource> result = (List<TaxonomicSource>) query.getResultList();
			if (result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return new ArrayList<TaxonomicSource>();
		}
		return new ArrayList<TaxonomicSource>();
	}

	/**
	 * Recuperar lista de estatus reino arqueobacteria
	 * 
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public List<TaxonomicSource> findTaxonomicSourceArqueobacteria() {

		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM TaxonomicSource o where o.tasoStatus = true and o.tasoArcheobacteria=true order by o.tasoName");
			List<TaxonomicSource> result = (List<TaxonomicSource>) query.getResultList();
			if (result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return new ArrayList<TaxonomicSource>();
		}
		return new ArrayList<TaxonomicSource>();
	}

	/**
	 * Recuperar lista de estatus reino protista
	 * 
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public List<TaxonomicSource> findTaxonomicSourceProtista() {

		try {
			Query query = super.getEntityManager()
					.createQuery("SELECT o FROM TaxonomicSource o where o.tasoStatus = true and o.tasoProtista=true order by o.tasoName");
			List<TaxonomicSource> result = (List<TaxonomicSource>) query.getResultList();
			if (result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return new ArrayList<TaxonomicSource>();
		}
		return new ArrayList<TaxonomicSource>();
	}

	/**
	 * Recuperar lista de estatus reino chromista
	 * 
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public List<TaxonomicSource> findTaxonomicSourceChromista() {

		try {
			Query query = super.getEntityManager()
					.createQuery("SELECT o FROM TaxonomicSource o where o.tasoStatus = true and o.tasoChromista=true order by o.tasoName");
			List<TaxonomicSource> result = (List<TaxonomicSource>) query.getResultList();
			if (result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return new ArrayList<TaxonomicSource>();
		}
		return new ArrayList<TaxonomicSource>();
	}

	/**
	 * Recuperar lista de estatus reino Viruses
	 * 
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public List<TaxonomicSource> findTaxonomicSourceViruses() {

		try {
			Query query = super.getEntityManager()
					.createQuery("SELECT o FROM TaxonomicSource o where o.tasoStatus = true and o.tasoViruses=true order by o.tasoName");
			List<TaxonomicSource> result = (List<TaxonomicSource>) query.getResultList();
			if (result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return new ArrayList<TaxonomicSource>();
		}
		return new ArrayList<TaxonomicSource>();
	}

	/**
	 * Buscar Fuentes taxonomicas activas
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TaxonomicSource> findTaxonomicSourceActives() {

		try {
			Query query = super.getEntityManager()
					.createQuery("SELECT o FROM TaxonomicSource o where o.tasoStatus = true order by o.tasoName");
			List<TaxonomicSource> result = (List<TaxonomicSource>) query.getResultList();
			if (!result.isEmpty()) {
				for (TaxonomicSource item : result) {
					StringBuilder concatenacion = new StringBuilder();
					String reinos="";
					concatenacion.append("");
					if (item.getTasoAnimal() != null) {
						if (item.getTasoAnimal()) {
							concatenacion.append("AN,");
						}

					} 
					
					if (item.getTasoPlantae() != null) {
						if (item.getTasoPlantae()) {
							concatenacion.append("PL,");
						}

					} 
					
					if (item.getTasoChromista() != null) {
						if (item.getTasoChromista()) {
							concatenacion.append("CH,");
						}

					} 
					
					if (item.getTasoEubacteria() != null) {
						if (item.getTasoEubacteria()) {
							concatenacion.append("BA,");
						}

					} 
					if (item.getTasoArcheobacteria() != null) {
						if (item.getTasoArcheobacteria()) {
							concatenacion.append("AR,");
						}

					} 
					if (item.getTasoProtista() != null) {
						if (item.getTasoProtista()) {
							concatenacion.append("PR,");
						}

					} 
					if (item.getTasoFungi() != null) {
						if (item.getTasoFungi()) {
							concatenacion.append("FU,");
						}

					} 
					if (item.getTasoViruses() != null) {
						if (item.getTasoViruses()) {
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

		} catch (NoResultException e) {
			return new ArrayList<TaxonomicSource>();
		}
		return new ArrayList<TaxonomicSource>();
	}
	
	/**
	 * Guardar fuente taxonomica
	 * 
	 * @param taxonomicSource
	 * @param usuario
	 * @return
	 */
	public TaxonomicSource save(TaxonomicSource taxonomicSource, User user) {
		TaxonomicSource taxonomic = null;
		try {
			if (taxonomicSource.getTasoId() == null) {

				taxonomicSource.setTasoStatus(true);
				taxonomicSource.setTasoDateCreate(new Date());
				taxonomicSource.setTasoUserCreate(user.getUserName());
				taxonomic = create(taxonomicSource);

			} else {
				taxonomicSource.setTasoDateUpdate(new Date());
				taxonomicSource.setTasoUserUpdate(user.getUserName());
				taxonomic = edit(taxonomicSource);
			}

			return taxonomic;
		} catch (Exception ex) {
			return taxonomic;
		}
	}

	/**
	 * Buscar una fuente taxonomica por el nombre
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TaxonomicSource> findTaxonomicSourceByName(String name) {
		try {
			Query query = super.getEntityManager()
					.createQuery("SELECT o FROM TaxonomicSource o where o.tasoStatus = true and UPPER(o.tasoName) = :name");
			query.setParameter("name", name.toUpperCase());
			List<TaxonomicSource> result = (List<TaxonomicSource>) query.getResultList();
			if (!result.isEmpty()) {
				
				return result;
			}

		} catch (NoResultException e) {
			return new ArrayList<TaxonomicSource>();
		}
		return new ArrayList<TaxonomicSource>();
	}
	
	/**
	 * Buscar higher classification asociados a una fuente taxonomica
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HigherClassification> findDetailsByTaxonomicSource(Integer id) {
		try {
			Query query = super.getEntityManager()
					.createQuery("SELECT o FROM HigherClassification o where o.hiclStatus = true and o.taxonomicSource.tasoId = :id");
			query.setParameter("id", id);
			List<HigherClassification> result = (List<HigherClassification>) query.getResultList();
			if (!result.isEmpty()) {
				
				return result;
			}

		} catch (NoResultException e) {
			return new ArrayList<HigherClassification>();
		}
		return new ArrayList<HigherClassification>();
	}
	
	/**
	 * Buscar Specie Taxa asociados a una fuente Taxonomica
	 * @param id
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<SpecieTaxa> findDetailsSpecieByTaxonomicSource(Integer id) {
		try {
			Query query = super.getEntityManager()
					.createQuery("SELECT o FROM SpecieTaxa o where o.sptaStatus = true and o.taxonomicSource.tasoId = :id");
			query.setParameter("id", id);
			List<SpecieTaxa> result = (List<SpecieTaxa>) query.getResultList();
			if (!result.isEmpty()) {
				
				return result;
			}

		} catch (NoResultException e) {
			return new ArrayList<SpecieTaxa>();
		}
		return new ArrayList<SpecieTaxa>();
	}

}

