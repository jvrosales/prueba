package ec.gob.ambiente.suia.iv.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecieTaxa;


/**
 * @author avfp
 *
 */
@Stateless
public class SuiaIvSpecieTaxaDao extends AbstractFacade<SpecieTaxa, Integer> {

	public SuiaIvSpecieTaxaDao() {
		super(SpecieTaxa.class, Integer.class);
	}
	
	
	public List<SpecieTaxa> buscarEspeciePorGrupoNomEspecie(Integer codigo, String nombre) {
		List<SpecieTaxa>  artificialGroupL = null;
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT stx ");
		jpql.append(" from  SpecieTaxa stx, SpecieTaxaArtificialGroup ag ");
		jpql.append(" where  stx.sptaId =  ag.specieTaxa.sptaId ");
		jpql.append(" and  ag.artificialGroup.argrId =  :codigo ");
		jpql.append(" and  ag.stagStatus =  true ");
		jpql.append(" and LOWER(stx.sptaScientificName) LIKE :nombre ");
		
		TypedQuery<SpecieTaxa> tQuery = this.getEntityManager().createQuery(jpql.toString(), SpecieTaxa.class );
		tQuery.setParameter("codigo", codigo);
		tQuery.setParameter("nombre", "%"+nombre.toLowerCase()+"%");
		artificialGroupL = tQuery.getResultList();
		return artificialGroupL;
	} 
	
	
	

}
