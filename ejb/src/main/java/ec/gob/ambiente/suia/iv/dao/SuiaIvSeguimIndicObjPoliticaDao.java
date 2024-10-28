/**
 * 
 */
package ec.gob.ambiente.suia.iv.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.suia.iv.model.SuiaIvSeguimIndicObjPolitica;

/**
 * @author avfp
 *
 */
@Stateless
public class SuiaIvSeguimIndicObjPoliticaDao extends AbstractFacade<SuiaIvSeguimIndicObjPolitica, Long> {

	public SuiaIvSeguimIndicObjPoliticaDao() {
		super(SuiaIvSeguimIndicObjPolitica.class, Long.class);
	}

	public SuiaIvSeguimIndicObjPolitica guardar(SuiaIvSeguimIndicObjPolitica suiaIvSeguimientoPolitica) throws Exception {
		try {
			this.create(suiaIvSeguimientoPolitica);
		} catch (Exception ex) {
			throw ex;
		}
		return suiaIvSeguimientoPolitica;
	}

	public List<SuiaIvSeguimIndicObjPolitica> buscarSeguimientoPolitica() throws Exception {
		List<SuiaIvSeguimIndicObjPolitica> suiaIvSeguimientoPoliticaL = null;
		try {
			StringBuilder jpql = new StringBuilder();
			jpql.append(" Select inv ");
			jpql.append(" from  SuiaIvSeguimIndicObjPolitica inv ");
			jpql.append(" where  inv.estado = 'ACT' ");
			TypedQuery<SuiaIvSeguimIndicObjPolitica> tQuery = this.getEntityManager().createQuery(jpql.toString(),
					SuiaIvSeguimIndicObjPolitica.class);
			suiaIvSeguimientoPoliticaL = tQuery.getResultList();
		} catch (Exception ex) {
			throw ex;
		}
		return suiaIvSeguimientoPoliticaL;
	}
	
	public List<SuiaIvSeguimIndicObjPolitica> buscarSeguimientoPoliticaPorSeguimiento(Long codigoSeguimiento) throws Exception {
		List<SuiaIvSeguimIndicObjPolitica> suiaIvSeguimientoPoliticaL = null;
		try {
			StringBuilder jpql = new StringBuilder();
			jpql.append(" Select inv ");
			jpql.append(" from  SuiaIvSeguimIndicObjPolitica inv ");
			jpql.append(" where  inv.suiaIvSeguimIndicObj.codigo = ':codigoSeguimiento ");
			jpql.append(" and  inv.estado = 'ACT' ");
			TypedQuery<SuiaIvSeguimIndicObjPolitica> tQuery = this.getEntityManager().createQuery(jpql.toString(),
					SuiaIvSeguimIndicObjPolitica.class);
			tQuery.setParameter("codigoSeguimiento", codigoSeguimiento);
			suiaIvSeguimientoPoliticaL = tQuery.getResultList();
		} catch (Exception ex) {
			throw ex;
		}
		return suiaIvSeguimientoPoliticaL;
	}

}
