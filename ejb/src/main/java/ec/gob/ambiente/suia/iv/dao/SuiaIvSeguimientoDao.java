/**
 * 
 */
package ec.gob.ambiente.suia.iv.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import ec.gob.ambiente.enlisy.actionplan.enumerador.EstadoSeguimientoEnum;
import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.suia.iv.model.SuiaIvSeguimiento;


/**
 * @author avfp
 *
 */
@Stateless
public class SuiaIvSeguimientoDao extends AbstractFacade<SuiaIvSeguimiento, Long> {

	public SuiaIvSeguimientoDao() {
		super(SuiaIvSeguimiento.class, Long.class);
	}
	
	public SuiaIvSeguimiento guardar(SuiaIvSeguimiento suiaIvSeguimiento)  throws Exception {
		try {
			this.create(suiaIvSeguimiento);
		} catch (Exception ex) {
			throw ex;
		}
		return suiaIvSeguimiento;	
	}
	
	 public SuiaIvSeguimiento actualizar(SuiaIvSeguimiento suiaIvSeguimiento) throws Exception {
			try {
				this.edit(suiaIvSeguimiento);
			} catch (Exception ex) {
				throw ex;
			}
			return suiaIvSeguimiento;	
	}
	 
	 public List<SuiaIvSeguimiento> buscarTodosActivos()throws Exception {
		 List<SuiaIvSeguimiento> suiaIvSeguimientoL =null;
			try {
				StringBuilder jpql = new StringBuilder();
				jpql.append("SELECT sa ");
				jpql.append(" from SuiaIvSeguimiento sa ");
				jpql.append(" where sa.estado='ACT'");
				TypedQuery<SuiaIvSeguimiento> tQuery = this.getEntityManager().createQuery(jpql.toString(), SuiaIvSeguimiento.class);
				suiaIvSeguimientoL = tQuery.getResultList();		
			} catch (Exception ex) {
				throw ex;
			}
			return suiaIvSeguimientoL;	
		}

	 public List<SuiaIvSeguimiento> buscaPorActividad(Long codigoActividad)throws Exception {
		 List<SuiaIvSeguimiento> suiaIvSeguimientoL =null;
			try {
				StringBuilder jpql = new StringBuilder();
				jpql.append("SELECT sa ");
				jpql.append(" from SuiaIvSeguimiento sa ");
				jpql.append(" where sa.suiaIvActividad.codigo = :codigoActividad  ");
				jpql.append(" and sa.estado='ACT' ");
				TypedQuery<SuiaIvSeguimiento> tQuery = this.getEntityManager().createQuery(jpql.toString(), SuiaIvSeguimiento.class);
				tQuery.setParameter("codigoActividad", codigoActividad);
				suiaIvSeguimientoL = tQuery.getResultList();		
			} catch (Exception ex) {
				throw ex;
			}
			return suiaIvSeguimientoL;	
		}
	 
	 public List<SuiaIvSeguimiento> buscaPorObjetivo(Long codigoObjetivo)throws Exception {
		 List<SuiaIvSeguimiento> suiaIvSeguimientoL =null;
			try {
				StringBuilder jpql = new StringBuilder();
				jpql.append("SELECT sa ");
				jpql.append(" from SuiaIvSeguimiento sa,  SuiaIvActividad ac");
				jpql.append(" where sa.suiaIvActividad.codigo = ac.codigo  ");
				jpql.append(" and ac.suiaIvObjetivo.codigo = :codigoObjetivo ");
				jpql.append(" and ac.estado = 'ACT' ");
				jpql.append(" and sa.estado = 'ACT' ");
				jpql.append(" and sa.estadoSeguimiento = :estadoSeguimiento ");
				TypedQuery<SuiaIvSeguimiento> tQuery = this.getEntityManager().createQuery(jpql.toString(), SuiaIvSeguimiento.class);
				tQuery.setParameter("codigoObjetivo", codigoObjetivo);
				tQuery.setParameter("estadoSeguimiento", EstadoSeguimientoEnum.INGRESADO.getCodigo());
				suiaIvSeguimientoL = tQuery.getResultList();		
			} catch (Exception ex) {
				throw ex;
			}
			return suiaIvSeguimientoL;	
		}
	 
	 
	 public List<SuiaIvSeguimiento> buscaPorIndicador(Long codigoIndicador)throws Exception {
		 List<SuiaIvSeguimiento> suiaIvSeguimientoL =null;
			try {
				StringBuilder jpql = new StringBuilder();
				jpql.append("SELECT sa ");
				jpql.append(" from SuiaIvSeguimiento sa , SuiaIvIndicadorActividad sin ");
				jpql.append(" where sa.suiaIvIndicadorActividad.codigo =  sin.codigo");
				jpql.append(" and  sin.codigo = :codigoIndicador ");
				jpql.append(" and sa.estado = 'ACT' ");
				jpql.append(" and sa.estadoSeguimiento = :estadoSeguimiento ");
				TypedQuery<SuiaIvSeguimiento> tQuery = this.getEntityManager().createQuery(jpql.toString(), SuiaIvSeguimiento.class);
				tQuery.setParameter("codigoIndicador", codigoIndicador);
				tQuery.setParameter("estadoSeguimiento", EstadoSeguimientoEnum.INGRESADO.getCodigo());
				suiaIvSeguimientoL = tQuery.getResultList();		
			} catch (Exception ex) {
				throw ex;
			}
			return suiaIvSeguimientoL;	
		}
	 

}
