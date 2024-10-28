/**
 * 
 */
package ec.gob.ambiente.suia.iv.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.suia.iv.model.SuiaIvLineaAccion;


/**
 * @author avfp
 *
 */
@Stateless
public class SuiaIvLineaAccionDao extends AbstractFacade<SuiaIvLineaAccion, Long> {

	public SuiaIvLineaAccionDao() {
		super(SuiaIvLineaAccion.class, Long.class);
	}

	public SuiaIvLineaAccion guardar(SuiaIvLineaAccion suiaIvLineaAccion) throws Exception {
		try {
			this.create(suiaIvLineaAccion);
		} catch (Exception ex) {
			throw ex;
		}
		return suiaIvLineaAccion;	
	}
	
	
	
	public List<SuiaIvLineaAccion> buscarLineaAccionActivas() throws Exception {
		List<SuiaIvLineaAccion> suiaIvLineaAccionL = null;
		try {
			StringBuilder jpql = new StringBuilder();
			jpql.append("SELECT lac  ");
			jpql.append(" from SuiaIvLineaAccion lac ");
			jpql.append(" where lac.estado = 'ACT' ");
			TypedQuery<SuiaIvLineaAccion> tQuery = this.getEntityManager().createQuery(jpql.toString(), SuiaIvLineaAccion.class);
			suiaIvLineaAccionL = tQuery.getResultList();		
		} catch (Exception ex) {
			throw ex;
		}
		return suiaIvLineaAccionL;
	}
	
	
	

}
