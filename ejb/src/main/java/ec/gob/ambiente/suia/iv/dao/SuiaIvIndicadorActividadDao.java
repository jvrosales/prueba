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
import ec.gob.ambiente.suia.iv.model.SuiaIvIndicadorActividad;

/**
 * @author avfp
 *
 */
@Stateless
public class SuiaIvIndicadorActividadDao extends AbstractFacade<SuiaIvIndicadorActividad, Long> {

	public SuiaIvIndicadorActividadDao() {
		super(SuiaIvIndicadorActividad.class, Long.class);
	}

	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public SuiaIvIndicadorActividad guardar(SuiaIvIndicadorActividad suiaIvIndicadorActividad) throws Exception {
		try {
			this.create(suiaIvIndicadorActividad);
		} catch (Exception ex) {
			throw ex;
		}
		return suiaIvIndicadorActividad;
	}

	public SuiaIvIndicadorActividad actualizar(SuiaIvIndicadorActividad suiaIvIndicadorActividad) throws Exception {
		try {
			this.edit(suiaIvIndicadorActividad);
		} catch (Exception ex) {
			throw ex;
		}
		return suiaIvIndicadorActividad;
	}

	/**
	 * Busca los indicadores por objetivo
	 * 
	 * @param codigoObjetivo
	 * @return
	 */
	public List<SuiaIvIndicadorActividad> buscarIndicadorPorActividad(Long codigoActividad) throws Exception {
		List<SuiaIvIndicadorActividad> suiaIvIndicadorL = null;
		try {
			StringBuilder jpql = new StringBuilder();
			jpql.append("SELECT si");
			jpql.append(" from  SuiaIvIndicadorActividad si");
			jpql.append(" where si.suiaIvActividad.codigo = :codigoActividad");
			jpql.append(" and si.estado ='ACT'");
			jpql.append(" order by si.fechaInserta ASC");
			TypedQuery<SuiaIvIndicadorActividad> tQuery = this.getEntityManager().createQuery(jpql.toString(),
					SuiaIvIndicadorActividad.class);
			tQuery.setParameter("codigoActividad", codigoActividad);
			suiaIvIndicadorL = tQuery.getResultList();
		} catch (Exception ex) {
			throw ex;
		}
		return suiaIvIndicadorL;
	}

}
