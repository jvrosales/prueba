package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.speciescatalog.model.InformationSource;


/**
 * Servicios para la administracion de fuentes de informacion
 * @author EXCO
 *
 */
@Stateless
public class InformationSourceFacade extends AbstractFacade<InformationSource, Integer>{

	public InformationSourceFacade() {
		super(InformationSource.class, Integer.class);		
	}
	
	/**
	 * Obtener fuentes de informacion
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<InformationSource> getInformationSource() {
		
		List<InformationSource> result=new ArrayList<InformationSource>();
		try
		{
			Query query = super.getEntityManager().createQuery("select o from InformationSource o where o.insoStatus = true order by o.insoShortAppoinment");
			result=(List<InformationSource>)query.getResultList();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			
		}
		return result;
		}
	
	/**
	 * Guardar referencia
	 * 
	 * @param informationSource
	 * @param user
	 * @return
	 */
	public InformationSource save(InformationSource informationSource, User user) {
		InformationSource fuente = null;
		try {
			if (informationSource.getInsoId() == null ) {

				informationSource.setInsoStatus(true);
				informationSource.setInsoUserCreate(user.getUserName());
				informationSource.setInsoDateCreate(new Date());
				fuente = create(informationSource);

			} else {
				informationSource.setInsoDateUpdate(new Date());
				informationSource.setInsoUserUpdate(user.getUserName());
				fuente = edit(informationSource);
			}

			return fuente;
		} catch (Exception ex) {
			return fuente;
		}
	}
	
	
	/**
	 * Obtener registros de nombres comunes segun referencia literaria
	 * @param idInformationSource
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVernacularNameByInformationSource(Integer idInformationSource) {
		List<Object[]> result = new ArrayList<Object[]>();
		try {
			Query q;
			q = getEntityManager().createNativeQuery(
					"select vena_id, inso_id from biodiversity.vernacular_names where inso_id = ?1 and vena_status = true");
			q.setParameter(1, idInformationSource);
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
	 * Obtener fotografias segun referencia literaria
	 * @param idInformationSource
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getPhotographsByInformationSource(Integer idInformationSource) {
		List<Object[]> result = new ArrayList<Object[]>();
		try {
			Query q;
			q = getEntityManager().createNativeQuery(
					"select spph_id, inso_id from biodiversity.species_photographs where inso_id = ?1 and spph_status = true");
			q.setParameter(1, idInformationSource);
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
	 * Obtener provincias segun referencia literaria
	 * @param idInformationSource
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getProvincesByInformationSource(Integer idInformationSource) {
		List<Object[]> result = new ArrayList<Object[]>();
		try {
			Query q;
			q = getEntityManager().createNativeQuery(
					"select sptp_id, inso_id from biodiversity.species_taxa_provinces where inso_id = ?1 and sptp_status = true");
			q.setParameter(1, idInformationSource);
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
	 * Obtener habitats segun referencia literaria
	 * @param idInformationSource
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getHabitatsByInformationSource(Integer idInformationSource) {
		List<Object[]> result = new ArrayList<Object[]>();
		try {
			Query q;
			q = getEntityManager().createNativeQuery(
					"select stha_id, inso_id from biodiversity.species_taxa_habits where inso_id = ?1 and stha_status = true");
			q.setParameter(1, idInformationSource);
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
	 * Buscar una referencia Literaria por cita corta
	 * 	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<InformationSource> findEthnicGroupByName(String name) {
		try {
			Query query = super.getEntityManager()
					.createQuery("SELECT o FROM InformationSource o where o.insoStatus = true and UPPER(o.insoShortAppoinment) = :name");
			query.setParameter("name", name.toUpperCase());
			List<InformationSource> result = (List<InformationSource>) query.getResultList();
			if (!result.isEmpty()) {
				
				return result;
			}

		} catch (NoResultException e) {
			return new ArrayList<InformationSource>();
		}
		return new ArrayList<InformationSource>();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getSpecieByInformationSource(Integer idInformationSource) {
		List<Object[]> result = new ArrayList<Object[]>();
		try {
			Query q;
			q = getEntityManager().createNativeQuery(
					"select stlr_id, inso_id from biodiversity.species_taxa_literary_references where inso_id = ?1 and stlr_status = true");
			q.setParameter(1, idInformationSource);
			result = (List<Object[]>) q.getResultList();
			if (q.getResultList() == null) {
				result = new ArrayList<Object[]>();
			}
		} catch (Exception ex) {
			return new ArrayList<Object[]>();
		}
		return result;
	}
	
}

