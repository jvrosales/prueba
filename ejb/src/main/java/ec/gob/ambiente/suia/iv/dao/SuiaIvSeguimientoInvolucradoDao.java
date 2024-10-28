/**
 * 
 */
package ec.gob.ambiente.suia.iv.dao;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.suia.iv.model.SuiaIvSeguimientoInvolucrado;


/**
 * @author avfp
 *
 */
@Stateless
public class SuiaIvSeguimientoInvolucradoDao extends AbstractFacade<SuiaIvSeguimientoInvolucrado, Long> {

	public SuiaIvSeguimientoInvolucradoDao() {
		super(SuiaIvSeguimientoInvolucrado.class, Long.class);
	}

	 @TransactionAttribute(TransactionAttributeType.MANDATORY)
	public SuiaIvSeguimientoInvolucrado guardar(SuiaIvSeguimientoInvolucrado suiaIvSeguimientoInvolucrado) throws Exception {
		try {
			this.create(suiaIvSeguimientoInvolucrado);
		} catch (Exception ex) {
			throw ex;
			}
		return suiaIvSeguimientoInvolucrado;	
	}
	

}
