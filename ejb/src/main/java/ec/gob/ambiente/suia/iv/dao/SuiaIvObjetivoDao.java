/**
 * 
 */
package ec.gob.ambiente.suia.iv.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.suia.iv.model.SuiaIvObjetivo;


/**
 * @author avfp
 *
 */
@Stateless
public class SuiaIvObjetivoDao extends AbstractFacade<SuiaIvObjetivo, Long> {

	public SuiaIvObjetivoDao() {
		super(SuiaIvObjetivo.class, Long.class);
	}

	/**
	 * Guardar datos del plan
	 * @param suiaIvPlanAccion
	 * @return
	 * @throws Exception
	 */
	public SuiaIvObjetivo guardar(SuiaIvObjetivo suiaIvObjetivo) throws Exception {
		try {
			this.create(suiaIvObjetivo);
		} catch (Exception ex) {
			throw ex;
		}
		return suiaIvObjetivo;	
	}
	
	/**
	 * Actualizar datos del plan
	 * 
	 * @param suiaIvPlanAccion
	 * @return
	 * @throws Exception
	 */
	public SuiaIvObjetivo actualizar(SuiaIvObjetivo suiaIvObjetivo) throws Exception {
		try {
			this.edit(suiaIvObjetivo);
		} catch (Exception ex) {
			throw ex;
		}
		return suiaIvObjetivo;	
	}
	
	
	/**
	 * Busca los objetivos con sus indicadores activos por el plan de accion
	 * @param codigPlanAccion
	 * @return
	 * @throws Exception
	 */
	public List<SuiaIvObjetivo> buscarObjIndiActPorPlanAccion(Long codigPlanAccion) throws Exception {
		List<SuiaIvObjetivo> suiaIvObjetivoL = null;
		try {
			StringBuilder jpql = new StringBuilder();
			jpql.append("SELECT ob  ");
			jpql.append(" from SuiaIvObjetivo ob, SuiaIvActividad act ,SuiaIvIndicador ind ");
			jpql.append(" where ob.suiaIvPlanAccion.codigo = :codigoPlan ");
			jpql.append(" and ob.codigo = act.suiaIvObjetivo.codigo ");
			jpql.append(" and ob.codigo = ind.suiaIvObjetivo.codigo ");
			jpql.append(" and ob.estado ='ACT'");
			jpql.append(" and ind.estado ='ACT'");
			jpql.append(" and act.estado ='ACT'");
			TypedQuery<SuiaIvObjetivo> tQuery = this.getEntityManager().createQuery(jpql.toString(), SuiaIvObjetivo.class);
			tQuery.setParameter("codigoPlan", codigPlanAccion);
			suiaIvObjetivoL = tQuery.getResultList();		
		} catch (Exception ex) {
			throw ex;
		}
		return suiaIvObjetivoL;
	}
	
	/**
	 * Busca los objetivos activos por meta
	 * 
	 * @param codigoMeta
	 * @return
	 * @throws Exception
	 */
	public List<SuiaIvObjetivo> buscarObjetivoPorMeta(Long codigoMeta) throws Exception {
		List<SuiaIvObjetivo> suiaIvObjetivoL = null;
		try {
			StringBuilder jpql = new StringBuilder();
			jpql.append("SELECT ob  ");
			jpql.append(" from SuiaIvObjetivo ob ");
			jpql.append(" where ob.suiaIvMeta.codigo = :codigoMeta ");
			jpql.append(" and ob.estado ='ACT'");
			jpql.append(" order by ob.fechaInserta ASC");
			TypedQuery<SuiaIvObjetivo> tQuery = this.getEntityManager().createQuery(jpql.toString(), SuiaIvObjetivo.class);
			tQuery.setParameter("codigoMeta", codigoMeta);
			suiaIvObjetivoL = tQuery.getResultList();		
		} catch (Exception ex) {
			throw ex;
		}
		return suiaIvObjetivoL;
	}
	
}
