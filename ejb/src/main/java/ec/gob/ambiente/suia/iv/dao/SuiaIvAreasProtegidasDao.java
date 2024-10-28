/**
 * 
 */
package ec.gob.ambiente.suia.iv.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.suia.iv.model.SuiaIvAreasProtegidas;

/**
 * @author avfp
 *
 */
@Stateless
public class SuiaIvAreasProtegidasDao extends AbstractFacade<SuiaIvAreasProtegidas, Long> {

	public SuiaIvAreasProtegidasDao() {
		super(SuiaIvAreasProtegidas.class, Long.class);
	}

	public SuiaIvAreasProtegidas guardar(SuiaIvAreasProtegidas suiaIvAreasProtegidas) throws Exception {
		try {
			this.create(suiaIvAreasProtegidas);
		} catch (Exception ex) {
			throw ex;
		}
		return suiaIvAreasProtegidas;
	}
	
	public SuiaIvAreasProtegidas actualizar(SuiaIvAreasProtegidas suiaIvAreasProtegidas) throws Exception {
		try {
			this.edit(suiaIvAreasProtegidas);
		} catch (Exception ex) {
			throw ex;
		}
		return suiaIvAreasProtegidas;
	}

	public List<SuiaIvAreasProtegidas> buscarAreasProtegidasTodas() throws Exception {
		List<SuiaIvAreasProtegidas> suiaIvAreasProtegidasL = null;
		try {
			StringBuilder jpql = new StringBuilder();
			jpql.append(" Select inv ");
			jpql.append(" from  SuiaIvAreasProtegidas inv ");
			jpql.append(" where  inv.estado = 'ACT' ");
			TypedQuery<SuiaIvAreasProtegidas> tQuery = this.getEntityManager().createQuery(jpql.toString(),
					SuiaIvAreasProtegidas.class);
			suiaIvAreasProtegidasL = tQuery.getResultList();
		} catch (Exception ex) {
			throw ex;
		}
		return suiaIvAreasProtegidasL;
	}
	

}
