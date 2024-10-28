/**
 * 
 */
package ec.gob.ambiente.suia.iv.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.TypedQuery;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.suia.iv.model.SuiaIvSeguimientoDocumento;


/**
 * @author avfp
 *
 */
@Stateless
public class SuiaIvSeguimientoDocumentoDao extends AbstractFacade<SuiaIvSeguimientoDocumento, Long> {

	public SuiaIvSeguimientoDocumentoDao() {
		super(SuiaIvSeguimientoDocumento.class, Long.class);
	}

	 @TransactionAttribute(TransactionAttributeType.MANDATORY)
	public SuiaIvSeguimientoDocumento guardar(SuiaIvSeguimientoDocumento suiaIvSeguimientoDocumento) throws Exception {
		try {
			this.create(suiaIvSeguimientoDocumento);
		} catch (Exception ex) {
			throw ex;
		}
		return suiaIvSeguimientoDocumento;	
	}
	
	 
	 public List<SuiaIvSeguimientoDocumento> buscaPorSeguimiento(Long codigoSeguimiento) throws Exception {
		 List<SuiaIvSeguimientoDocumento> suiaIvSeguimientoL =null;
			try {
				StringBuilder jpql = new StringBuilder();
				jpql.append("SELECT sa ");
				jpql.append(" from SuiaIvSeguimientoDocumento sa ");
				jpql.append(" where sa.suiaIvSeguimiento.codigo = :codigoSeguimiento  ");
				jpql.append(" and sa.estado='ACT' ");
				TypedQuery<SuiaIvSeguimientoDocumento> tQuery = this.getEntityManager().createQuery(jpql.toString(), SuiaIvSeguimientoDocumento.class);
				tQuery.setParameter("codigoSeguimiento", codigoSeguimiento);
				suiaIvSeguimientoL = tQuery.getResultList();		
			} catch (Exception ex) {
				throw ex;
			}
			return suiaIvSeguimientoL;	
		}

}
