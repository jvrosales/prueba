package ec.gob.ambiente.suia.iv.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.speciescatalog.model.HigherClassification;


/**
 * @author avfp
 *
 */
@Stateless
public class SuiaIvHigherClassificationDao extends AbstractFacade<HigherClassification, Integer> {

	public SuiaIvHigherClassificationDao() {
		super(HigherClassification.class, Integer.class);
	}


	
	public List<HigherClassification> buscarTodos (){
		List<HigherClassification>  suiaHigherClassificationL = null;
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT hc ");
		jpql.append(" from  HigherClassification hc ");
		jpql.append(" where hc.hiclTaxonRankName =  'Reino' ");
		jpql.append(" and hc.hiclStatus = true ");
		jpql.append(" order by hc.hiclScientificName ASC");
		TypedQuery<HigherClassification> tQuery = this.getEntityManager().createQuery(jpql.toString(), HigherClassification.class );
		suiaHigherClassificationL = tQuery.getResultList();
		return suiaHigherClassificationL;
	} 

}
