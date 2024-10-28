package ec.gob.ambiente.enlisy.services;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.GeographicalLocation;

@Stateless
public class GeographicalLocationFacade extends AbstractFacade<GeographicalLocation, Integer> implements Serializable  {
	
	private static final long serialVersionUID = -1153093606324655752L;

	public GeographicalLocationFacade() {
		super(GeographicalLocation.class, Integer.class);		
	}
	
    /**
	 * Buscar por ID
	 * @param cataId id del catalogo que se esta buscando
	 * @return catalogo encontrada.
	 */
	public GeographicalLocation findById(Integer geloId) {
	    Query query = getEntityManager().createQuery("Select o from GeographicalLocation o where o.geloStatus =true and o.geloId =:geloId");
	    query.setParameter("geloId", geloId);	   
	
	    if (query.getResultList().size() == 1) {
	        return (GeographicalLocation) query.getResultList().get(0);
	    } else {
	        return null;
	    }

	}
	
	/**
	 * Buscar por padre ID
	 * @param geloParentId
	 * @return
	 */
	public List<GeographicalLocation> findByParentId(Integer geloParentId) {
		TypedQuery<GeographicalLocation> query = super.getEntityManager().createQuery("SELECT o FROM GeographicalLocation o WHERE o.geographicalLocation.geloId = :geloParentId and o.geloStatus = true order by o.geloName, o.geographicalLocation.geloName", GeographicalLocation.class);
		query.setParameter("geloParentId", geloParentId);
		return query.getResultList();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<GeographicalLocation> buscarParroquia(String codigoInec) {

		try {
			Query query = super.getEntityManager().createQuery("SELECT u FROM GeographicalLocation u WHERE u.geloCodificationInec = :codeInec  and u.geloCodificationInec not in ('9001','9002','9003','9004') and u.geloStatus=true ");
			query.setParameter("codeInec", codigoInec);

			List<GeographicalLocation> result = (List<GeographicalLocation>) query.getResultList();
			if (result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return null;
		}
		return null;

	}
	
	
	public GeographicalLocation buscarProvinciaPorNombre(String nombre) {
		try {
			Query query = super.getEntityManager().createQuery("SELECT u FROM GeographicalLocation u where "
					+ "upper(translate (u.geloName,'áéíóúÁÉÍÓÚäëïöüÄËÏÖÜñ', 'aeiouAEIOUaeiouAEIOUÑ')) = translate(:nombreProvincia,'áéíóúÁÉÍÓÚäëïöüÄËÏÖÜñ', 'aeiouAEIOUaeiouAEIOUÑ') "
					+ "  and u.geloStatus=true AND u.geographicalLocation.geloId = 1");
			query.setParameter("nombreProvincia", nombre);

		    if (query.getResultList().size() == 1) 
		        return (GeographicalLocation) query.getResultList().get(0);
		} catch (NoResultException e) {
			return null;
		}
		return null;
	}
	
	/**
	 * Buscar paises
	 * @return lista de paises.
	 */
	@SuppressWarnings("unchecked")
	public List<GeographicalLocation> findByGeloParentNull() {
		try {
			Query query = getEntityManager().createQuery(
							"Select o from GeographicalLocation o where o.geographicalLocation = null and o.geloStatus = true order by o.geloName");

			List<GeographicalLocation> result = (List<GeographicalLocation>) query.getResultList();
			if (result.size() > 0) {
				return result;
			}
		} catch (NoResultException e) {
			return null;
		}
		return null;

	}

	

	/**
	 * Buscar por Codigo INEC
	 * @param inecCode
	 * @return
	 */
	
	public GeographicalLocation findByInecCode(String inecCode) {
	    Query query = getEntityManager().createQuery("Select o from GeographicalLocation o where o.geloStatus =true and o.geloCodificationInec =:inecCode");
	    query.setParameter("inecCode", inecCode); 	
	    
	    if (query.getResultList().size() > 0) {
	        return (GeographicalLocation) query.getResultList().get(0);
	    } else {
	        return null;
	    }
	}
}