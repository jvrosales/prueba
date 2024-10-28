/**
 * 
 */
package ec.gob.ambiente.suia.iv.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.suia.iv.model.SuiaIvActividad;

/**
 * @author avfp
 *
 */
@Stateless
public class SuiaIvActividadDao extends AbstractFacade<SuiaIvActividad, Long> {

	public SuiaIvActividadDao() {
		super(SuiaIvActividad.class, Long.class);
	}

	public SuiaIvActividad guardar(SuiaIvActividad suiaIvActividad) throws Exception {
		try {
			this.create(suiaIvActividad);
		} catch (Exception ex) {
			throw ex;
		}
		return suiaIvActividad;
	}

	public SuiaIvActividad actualizar(SuiaIvActividad suiaIvActividad) throws Exception {
		try {
			this.edit(suiaIvActividad);
		} catch (Exception ex) {
			throw ex;
		}
		return suiaIvActividad;
	}

	/**
	 * Busca las actividad por objetivo
	 * 
	 * @param codigoObjetivo
	 * @return
	 */
	public List<SuiaIvActividad> buscarActividadPorObjetivo(Long codigoObjetivo) throws Exception {
		List<SuiaIvActividad> suiaIvActividadL = null;
		try {
			StringBuilder jpql = new StringBuilder();
			jpql.append("SELECT sa");
			jpql.append(" from  SuiaIvActividad sa");
			jpql.append(" where sa.suiaIvObjetivo.codigo = :codigoObjetivo");
			jpql.append(" and sa.estado ='ACT'");
			TypedQuery<SuiaIvActividad> tQuery = this.getEntityManager().createQuery(jpql.toString(),
					SuiaIvActividad.class);
			tQuery.setParameter("codigoObjetivo", codigoObjetivo);
			suiaIvActividadL = tQuery.getResultList();
		} catch (Exception ex) {
			throw ex;
		}
		return suiaIvActividadL;
	}


	/**
	 * Busca las actividad por objetivo y por nombre
	 * 
	 * @param codigoObjetivo
	 * @return
	 */
	public List<SuiaIvActividad> buscarActPadrePorNombreYObjetivo(Long codigoObjetivo, String nombreActividad) throws Exception {
		List<SuiaIvActividad> suiaIvActividadL = null;
		try {
			StringBuilder jpql = new StringBuilder();
			jpql.append("SELECT sa");
			jpql.append(" from  SuiaIvActividad sa");
			jpql.append(" where sa.suiaIvObjetivo.codigo = :codigoObjetivo");
			jpql.append(" and LOWER(sa.nombre) like :nombreActividad");
			jpql.append(" and sa.codigoPadre  is null");
			jpql.append(" and sa.estado ='ACT'");
			TypedQuery<SuiaIvActividad> tQuery = this.getEntityManager().createQuery(jpql.toString(),
					SuiaIvActividad.class);
			tQuery.setParameter("codigoObjetivo", codigoObjetivo);
			tQuery.setParameter("nombreActividad", "%"+nombreActividad.toLowerCase()+"%");
			suiaIvActividadL = tQuery.getResultList();
		} catch (Exception ex) {
			throw ex;
		}
		return suiaIvActividadL;
	}
	
	
}
