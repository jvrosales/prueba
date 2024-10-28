/**
 * 
 */
package ec.gob.ambiente.suia.iv.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.suia.iv.model.SuiaIvPlanAccion;


/**
 * @author avfp
 *
 */
@Stateless
public class SuiaIvPlanAccionDao extends AbstractFacade<SuiaIvPlanAccion, Long> {

	public SuiaIvPlanAccionDao() {
		super(SuiaIvPlanAccion.class, Long.class);
	}

	public SuiaIvPlanAccion guardar(SuiaIvPlanAccion suiaIvPlanAccion) throws Exception {
		try {
			this.create(suiaIvPlanAccion);
		} catch (Exception ex) {
			throw ex;
		}
		return suiaIvPlanAccion;	
	}
	
	 public SuiaIvPlanAccion actualizar(SuiaIvPlanAccion suiaIvPlanAccion) throws Exception {
			try {
				this.edit(suiaIvPlanAccion);
			} catch (Exception ex) {
				throw ex;
			}
			return suiaIvPlanAccion;	
		}
	 
	public List<SuiaIvPlanAccion> buscarTodosActivos()throws Exception {
		 List<SuiaIvPlanAccion> suiaIvPlanAccionL =null;
			try {
				StringBuilder jpql = new StringBuilder();
				jpql.append("SELECT pa ");
				jpql.append(" from SuiaIvPlanAccion pa ");
				jpql.append(" where pa.estado='ACT'");
				jpql.append(" order by pa.fechaInserta ASC");
				TypedQuery<SuiaIvPlanAccion> tQuery = this.getEntityManager().createQuery(jpql.toString(), SuiaIvPlanAccion.class);
				suiaIvPlanAccionL = tQuery.getResultList();		
			} catch (Exception ex) {
				throw ex;
			}
			return suiaIvPlanAccionL;	
		}

	 /**
	  * Retorna el plan de acción activo y publicado
	  * 
	  * @return
	  */
	 public List<SuiaIvPlanAccion> buscarActivosPublicados(){
		 List<SuiaIvPlanAccion> suiaIvPlanAccionL =null;
			try {
				StringBuilder jpql = new StringBuilder();
				jpql.append("SELECT pa ");
				jpql.append(" from SuiaIvPlanAccion pa ");
				jpql.append(" where pa.estado='ACT'");
				jpql.append(" and pa.estadoPlan = 'PUB'");
				jpql.append(" order by pa.fechaInserta ASC");
				TypedQuery<SuiaIvPlanAccion> tQuery = this.getEntityManager().createQuery(jpql.toString(), SuiaIvPlanAccion.class);
				suiaIvPlanAccionL = tQuery.getResultList();		
			} catch (Exception ex) {
				throw ex;
			}
			return suiaIvPlanAccionL;	
		 
	 }
	 
	 public List<SuiaIvPlanAccion> buscarPlanPorNombre(String nombrePlan)throws Exception {
		 List<SuiaIvPlanAccion> suiaIvPlanAccionL =null;
			try {
				StringBuilder jpql = new StringBuilder();
				jpql.append("SELECT pa ");
				jpql.append(" from SuiaIvPlanAccion pa ");
				jpql.append(" where LOWER(pa.nombre) like :nombrePlan");
				jpql.append(" and  pa.estado='ACT'");
				jpql.append(" order by pa.fechaInserta ASC");
				TypedQuery<SuiaIvPlanAccion> tQuery = this.getEntityManager().createQuery(jpql.toString(), SuiaIvPlanAccion.class);
				tQuery.setParameter("nombrePlan", "%"+nombrePlan.toLowerCase()+"%");
				suiaIvPlanAccionL = tQuery.getResultList();		
			} catch (Exception ex) {
				throw ex;
			}
			return suiaIvPlanAccionL;	
		}
	 
	 
	 
}
