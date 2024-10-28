package ec.gob.ambiente.suia.iv.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecieTaxaArtificialGroup;


/**
 * @author avfp
 *
 */
@Stateless
public class SuiaIvSpeciesTaxaArtificialGroupsDao extends AbstractFacade<SpecieTaxaArtificialGroup, Integer> {

	public SuiaIvSpeciesTaxaArtificialGroupsDao() {
		super(SpecieTaxaArtificialGroup.class, Integer.class);
	}
	

	
	public List<SpecieTaxaArtificialGroup> buscarSpeciesTaxaArtificialGroup(Integer codigo) {
		List<SpecieTaxaArtificialGroup>  artificialGroupL = null;
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT stx ");
		jpql.append(" from  SpecieTaxaArtificialGroup stx, ArtificialGroup artg");
		jpql.append(" where  stx.artificialGroup.argrId  = artg.argrId  ");
		jpql.append(" and  artg.argrId =  :codigo ");
		jpql.append(" and  stx.stagStatus =  true");
		TypedQuery<SpecieTaxaArtificialGroup> tQuery = this.getEntityManager().createQuery(jpql.toString(), SpecieTaxaArtificialGroup.class );
		tQuery.setParameter("codigo", codigo);
		artificialGroupL = tQuery.getResultList();
		return artificialGroupL;
	} 
	
	
	

}
