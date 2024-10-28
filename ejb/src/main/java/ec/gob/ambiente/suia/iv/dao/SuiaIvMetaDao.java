package ec.gob.ambiente.suia.iv.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.suia.iv.model.SuiaIvMeta;


/**
 * @author avfp
 *
 */
@Stateless
public class SuiaIvMetaDao extends AbstractFacade<SuiaIvMeta, Long> {

	public SuiaIvMetaDao() {
		super(SuiaIvMeta.class, Long.class);
	}


	public SuiaIvMeta guardar(SuiaIvMeta suiaIvMeta) throws Exception {
		try {
			this.create(suiaIvMeta);
		} catch (Exception ex) {
			throw ex;
		}
		return suiaIvMeta;	
	}
	
	
	public SuiaIvMeta actualizar(SuiaIvMeta suiaIvMeta) throws Exception {
		try {
			this.edit(suiaIvMeta);
		} catch (Exception ex) {
			throw ex;
		}
		return suiaIvMeta;
	}
	 
	 
	public List<SuiaIvMeta> buscarMetasPorPlanAccion(Long codigoPlanAccion){
		List<SuiaIvMeta>  suiaIvMetaL = null;
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT sm");
		jpql.append(" from  SuiaIvMeta sm");
		jpql.append(" where sm.suiaIvPlanAccion.codigo = :codigoPlan");
		jpql.append(" and sm.estado ='ACT'");
		jpql.append(" order by sm.fechaInserta ASC");
		TypedQuery<SuiaIvMeta> tQuery = this.getEntityManager().createQuery(jpql.toString(), SuiaIvMeta.class );
		tQuery.setParameter("codigoPlan", codigoPlanAccion);
		suiaIvMetaL = tQuery.getResultList();
		return suiaIvMetaL;
	} 
	
	public List<SuiaIvMeta> buscarMetaPorObjetivo(Long codigoObjetivo){
		List<SuiaIvMeta>  suiaIvMetaL = null;
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT sm");
		jpql.append(" from  SuiaIvMeta sm, SuiaIvObjetivo ojb");
		jpql.append(" where ojb.suiaIvMeta.codigo = sm.codigo");
		jpql.append(" and ojb.codigo = :codigoObjetivo");
		jpql.append(" and sm.estado ='ACT'");
		jpql.append(" order by sm.fechaInserta ASC");
		TypedQuery<SuiaIvMeta> tQuery = this.getEntityManager().createQuery(jpql.toString(), SuiaIvMeta.class );
		tQuery.setParameter("codigoObjetivo", codigoObjetivo);
		suiaIvMetaL = tQuery.getResultList();
		return suiaIvMetaL;
	} 

}
