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
import ec.gob.ambiente.suia.iv.model.SuiaIvIndicador;

/**
 * @author avfp
 *
 */
@Stateless
public class SuiaIvIndicadorDao extends AbstractFacade<SuiaIvIndicador, Long> {

	public SuiaIvIndicadorDao() {
		super(SuiaIvIndicador.class, Long.class);
	}

	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public SuiaIvIndicador guardar(SuiaIvIndicador suiaIvIndicador) throws Exception {
		try {
			this.create(suiaIvIndicador);
		} catch (Exception ex) {
			throw ex;
		}
		return suiaIvIndicador;
	}

	public SuiaIvIndicador actualizar(SuiaIvIndicador suiaIvIndicador) throws Exception {
		try {
			this.edit(suiaIvIndicador);
		} catch (Exception ex) {
			throw ex;
		}
		return suiaIvIndicador;
	}

	/**
	 * Busca los indicadores por objetivo
	 * 
	 * @param codigoObjetivo
	 * @return
	 */
	public List<SuiaIvIndicador> buscarIndicadorPorObjetivo(Long codigoObjetivo) throws Exception {
		List<SuiaIvIndicador> suiaIvIndicadorL = null;
		try {
			StringBuilder jpql = new StringBuilder();
			jpql.append("SELECT si");
			jpql.append(" from  SuiaIvIndicador si");
			jpql.append(" where si.suiaIvObjetivo.codigo = :codigoObjetivo");
			jpql.append(" and si.estado ='ACT'");
			jpql.append(" order by si.fechaInserta ASC");
			TypedQuery<SuiaIvIndicador> tQuery = this.getEntityManager().createQuery(jpql.toString(),
					SuiaIvIndicador.class);
			tQuery.setParameter("codigoObjetivo", codigoObjetivo);
			suiaIvIndicadorL = tQuery.getResultList();
		} catch (Exception ex) {
			throw ex;
		}
		return suiaIvIndicadorL;
	}

}
