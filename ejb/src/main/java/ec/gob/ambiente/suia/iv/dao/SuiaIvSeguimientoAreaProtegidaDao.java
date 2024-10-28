/**
 * 
 */
package ec.gob.ambiente.suia.iv.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.suia.iv.model.SuiaIvSeguimientoAreaProtegida;

/**
 * @author avfp
 *
 */
@Stateless
public class SuiaIvSeguimientoAreaProtegidaDao extends AbstractFacade<SuiaIvSeguimientoAreaProtegida, Long> {

	public SuiaIvSeguimientoAreaProtegidaDao() {
		super(SuiaIvSeguimientoAreaProtegida.class, Long.class);
	}

	public SuiaIvSeguimientoAreaProtegida guardar(SuiaIvSeguimientoAreaProtegida suiaIvSeguimientoAreaProtegida) throws Exception {
		try {
			this.create(suiaIvSeguimientoAreaProtegida);
		} catch (Exception ex) {
			throw ex;
		}
		return suiaIvSeguimientoAreaProtegida;
	}

	public List<SuiaIvSeguimientoAreaProtegida> buscarSeguimientoAreasProtegidas() throws Exception {
		List<SuiaIvSeguimientoAreaProtegida> suiaIvSeguAreasProtegidasL = null;
		try {
			StringBuilder jpql = new StringBuilder();
			jpql.append(" Select inv ");
			jpql.append(" from  SuiaIvSeguimientoAreaProtegida inv ");
			jpql.append(" where  inv.estado = 'ACT' ");
			TypedQuery<SuiaIvSeguimientoAreaProtegida> tQuery = this.getEntityManager().createQuery(jpql.toString(),
					SuiaIvSeguimientoAreaProtegida.class);
			suiaIvSeguAreasProtegidasL = tQuery.getResultList();
		} catch (Exception ex) {
			throw ex;
		}
		return suiaIvSeguAreasProtegidasL;
	}
	
	
	public List<SuiaIvSeguimientoAreaProtegida> buscarSegAreaProtegPorSeguimiento(Long codigoSeguimiento) throws Exception {
		List<SuiaIvSeguimientoAreaProtegida> suiaIvSeguAreasProtegidasL = null;
		try {
			StringBuilder jpql = new StringBuilder();
			jpql.append(" Select inv ");
			jpql.append(" from  SuiaIvSeguimientoAreaProtegida inv ");
			jpql.append(" where  inv.suiaIvSeguimiento.codigo =  :codigoSeguimiento ");
			jpql.append(" and inv.estado = 'ACT' ");
			TypedQuery<SuiaIvSeguimientoAreaProtegida> tQuery = this.getEntityManager().createQuery(jpql.toString(),
					SuiaIvSeguimientoAreaProtegida.class);
			tQuery.setParameter("codigoSeguimiento", codigoSeguimiento);
			suiaIvSeguAreasProtegidasL = tQuery.getResultList();
		} catch (Exception ex) {
			throw ex;
		}
		return suiaIvSeguAreasProtegidasL;
	}
	

}
