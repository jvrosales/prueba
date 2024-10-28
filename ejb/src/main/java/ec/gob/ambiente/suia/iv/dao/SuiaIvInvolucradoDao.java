/**
 * 
 */
package ec.gob.ambiente.suia.iv.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.suia.iv.model.SuiaIvInvolucrado;

/**
 * @author avfp
 *
 */
@Stateless
public class SuiaIvInvolucradoDao extends AbstractFacade<SuiaIvInvolucrado, Long> {

	public SuiaIvInvolucradoDao() {
		super(SuiaIvInvolucrado.class, Long.class);
	}

	public SuiaIvInvolucrado guardar(SuiaIvInvolucrado suiaIvInvolucrado) throws Exception {
		try {
			this.create(suiaIvInvolucrado);
		} catch (Exception ex) {
			throw ex;
		}
		return suiaIvInvolucrado;
	}

	public List<SuiaIvInvolucrado> buscarInvolucradosActivos() throws Exception {
		List<SuiaIvInvolucrado> involucradoL = null;
		try {
			StringBuilder jpql = new StringBuilder();
			jpql.append(" Select inv ");
			jpql.append(" from  SuiaIvInvolucrado inv ");
			jpql.append(" where  inv.estado = 'ACT' ");
			TypedQuery<SuiaIvInvolucrado> tQuery = this.getEntityManager().createQuery(jpql.toString(),
					SuiaIvInvolucrado.class);
			involucradoL = tQuery.getResultList();
		} catch (Exception ex) {
			throw ex;
		}
		return involucradoL;
	}
	
	public List<SuiaIvInvolucrado> buscarInvolucradosTodos() throws Exception {
		List<SuiaIvInvolucrado> involucradoL = null;
		try {
			StringBuilder jpql = new StringBuilder();
			jpql.append(" Select inv ");
			jpql.append(" from  SuiaIvInvolucrado inv ");
			jpql.append(" where  inv.estado in  ('INA', 'ACT') ");
			TypedQuery<SuiaIvInvolucrado> tQuery = this.getEntityManager().createQuery(jpql.toString(),
					SuiaIvInvolucrado.class);
			involucradoL = tQuery.getResultList();
		} catch (Exception ex) {
			throw ex;
		}
		return involucradoL;
	}

}
