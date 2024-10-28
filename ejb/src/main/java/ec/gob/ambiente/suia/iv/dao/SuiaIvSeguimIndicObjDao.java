/**
 * 
 */
package ec.gob.ambiente.suia.iv.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.suia.iv.model.SuiaIvSeguimIndicObj;


/**
 * @author avfp
 *
 */
@Stateless
public class SuiaIvSeguimIndicObjDao extends AbstractFacade<SuiaIvSeguimIndicObj, Long> {

	public SuiaIvSeguimIndicObjDao() {
		super(SuiaIvSeguimIndicObj.class, Long.class);
	}
	
	public SuiaIvSeguimIndicObj guardar(SuiaIvSeguimIndicObj suiaIvSeguimIndicObj)  throws Exception {
		try {
			this.create(suiaIvSeguimIndicObj);
		} catch (Exception ex) {
			throw ex;
		}
		return suiaIvSeguimIndicObj;	
	}
	
	 public SuiaIvSeguimIndicObj actualizar(SuiaIvSeguimIndicObj suiaIvSeguimIndicObj) throws Exception {
			try {
				this.edit(suiaIvSeguimIndicObj);
			} catch (Exception ex) {
				throw ex;
			}
			return suiaIvSeguimIndicObj;	
	}
	 
	 public List<SuiaIvSeguimIndicObj> buscarTodosActivos()throws Exception {
		 List<SuiaIvSeguimIndicObj> suiaIvSeguimientoL =null;
			try {
				StringBuilder jpql = new StringBuilder();
				jpql.append("SELECT sa ");
				jpql.append(" from SuiaIvSeguimIndicObj sa ");
				jpql.append(" where sa.estado='ACT'");
				TypedQuery<SuiaIvSeguimIndicObj> tQuery = this.getEntityManager().createQuery(jpql.toString(), SuiaIvSeguimIndicObj.class);
				suiaIvSeguimientoL = tQuery.getResultList();		
			} catch (Exception ex) {
				throw ex;
			}
			return suiaIvSeguimientoL;	
		}

	 public List<SuiaIvSeguimIndicObj> buscaPorIndicador(Long codigoIndicador)throws Exception {
		 List<SuiaIvSeguimIndicObj> suiaIvSeguimientoL =null;
			try {
				StringBuilder jpql = new StringBuilder();
				jpql.append("SELECT sa ");
				jpql.append(" from SuiaIvSeguimIndicObj sa ");
				jpql.append(" where sa.suiaIvIndicador.codigo = :codigoIndicador  ");
				jpql.append(" and sa.estado='ACT' ");
				TypedQuery<SuiaIvSeguimIndicObj> tQuery = this.getEntityManager().createQuery(jpql.toString(), SuiaIvSeguimIndicObj.class);
				tQuery.setParameter("codigoIndicador", codigoIndicador);
				suiaIvSeguimientoL = tQuery.getResultList();		
			} catch (Exception ex) {
				throw ex;
			}
			return suiaIvSeguimientoL;	
		}
	
	 

}
