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
import ec.gob.ambiente.suia.iv.model.SuiaIvSeguimIndicObjDoc;


/**
 * @author avfp
 *
 */
@Stateless
public class SuiaIvSeguimIndicObjDocDao extends AbstractFacade<SuiaIvSeguimIndicObjDoc, Long> {

	public SuiaIvSeguimIndicObjDocDao() {
		super(SuiaIvSeguimIndicObjDoc.class, Long.class);
	}

	 @TransactionAttribute(TransactionAttributeType.MANDATORY)
	public SuiaIvSeguimIndicObjDoc guardar(SuiaIvSeguimIndicObjDoc suiaIvSeguimIndicObjDoc) throws Exception {
		try {
			this.create(suiaIvSeguimIndicObjDoc);
		} catch (Exception ex) {
			throw ex;
		}
		return suiaIvSeguimIndicObjDoc;	
	}
	
	 public List<SuiaIvSeguimIndicObjDoc> buscaPorSeguimiento(Long codigoSeguimiento) throws Exception {
		 List<SuiaIvSeguimIndicObjDoc> suiaIvSeguimientoL =null;
			try {
				StringBuilder jpql = new StringBuilder();
				jpql.append("SELECT sa ");
				jpql.append(" from SuiaIvSeguimIndicObjDoc sa ");
				jpql.append(" where sa.suiaIvSeguimIndicObj.codigo = :codigoSeguimiento  ");
				jpql.append(" and sa.estado='ACT' ");
				TypedQuery<SuiaIvSeguimIndicObjDoc> tQuery = this.getEntityManager().createQuery(jpql.toString(), SuiaIvSeguimIndicObjDoc.class);
				tQuery.setParameter("codigoSeguimiento", codigoSeguimiento);
				suiaIvSeguimientoL = tQuery.getResultList();		
			} catch (Exception ex) {
				throw ex;
			}
			return suiaIvSeguimientoL;	
		}

}
