package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.speciescatalog.model.TaxaValidation;






/**
 * Servicios para la administracion de 
 * las validaciones del estado del taxon
 * @author EXCO
 *
 */
@Stateless
public class TaxaValidationFacade extends AbstractFacade<TaxaValidation, Integer>{

	public TaxaValidationFacade() {
		super(TaxaValidation.class, Integer.class);		
	}

	
	/**
	 * Buscar coincidencias nombre cientifico
	 * @param nombreCientifico
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<Object[]> buscarCoincidenciasValidaciones(String sqlNativo) {
		List<Object[]> result = null;

		try {

			Query q;
			q = getEntityManager().createNativeQuery(sqlNativo);
			
			result=q.getResultList();
			
		} catch (Exception e) {
			result = null;
		}
		return result;

	}
		
	
}
