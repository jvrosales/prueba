/**
 * 
 */
package ec.gob.ambiente.suia.iv.dao;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.suia.iv.model.SuiaIvSeguimientoProvincia;


/**
 * @author avfp
 *
 */
@Stateless
public class SuiaIvSeguimientoProvinciaDao extends AbstractFacade<SuiaIvSeguimientoProvincia, Long> {

	public SuiaIvSeguimientoProvinciaDao() {
		super(SuiaIvSeguimientoProvincia.class, Long.class);
	}

	 @TransactionAttribute(TransactionAttributeType.MANDATORY)
	public SuiaIvSeguimientoProvincia guardar(SuiaIvSeguimientoProvincia suiaIvSeguimientoProvincia) throws Exception {
		try {
			this.create(suiaIvSeguimientoProvincia);
		} catch (Exception ex) {
			throw ex;
		}
		return suiaIvSeguimientoProvincia;	
	}
	

}
