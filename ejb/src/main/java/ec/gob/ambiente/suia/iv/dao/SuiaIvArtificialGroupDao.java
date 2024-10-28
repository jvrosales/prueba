package ec.gob.ambiente.suia.iv.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import ec.gob.ambiente.enlisy.actionplan.enumerador.ReinoEnum;
import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.speciescatalog.model.ArtificialGroup;
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecieTaxa;


/**
 * @author avfp
 *
 */
@Stateless
public class SuiaIvArtificialGroupDao extends AbstractFacade<ArtificialGroup, Integer> {

	public SuiaIvArtificialGroupDao() {
		super(ArtificialGroup.class, Integer.class);
	}


	
	public List<ArtificialGroup> buscarPorCodigoReino(Integer codigoReino, Integer codigoArtificalGroup) {
		List<ArtificialGroup>  artificialGroupL = null;
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT hc ");
		jpql.append(" from  ArtificialGroup hc ");
		jpql.append(" where hc.argrStatus =  true ");
		if(codigoReino == ReinoEnum.BACTERIA.getCodigo()){
			jpql.append(" and hc.argrEubacteria = true ");
		}
		if(codigoReino == ReinoEnum.ARCHAEA.getCodigo()){
			jpql.append(" and hc.argrArcheobacteria = true ");
		}
		if(codigoReino == ReinoEnum.VIRUSES.getCodigo()){
			jpql.append(" and hc.argrViruses = true ");
		}
		if(codigoReino == ReinoEnum.CHROMISTA.getCodigo()){
			jpql.append(" and hc.argrChromista = true ");
		}
		if(codigoReino == ReinoEnum.PROTOZOA.getCodigo()){
			jpql.append(" and hc.argrProtista = true ");
		}
		if(codigoReino == ReinoEnum.FUNGI.getCodigo()){
			jpql.append(" and hc.argrFungi = true ");
		}	
		if(codigoReino == ReinoEnum.PLANTAE.getCodigo()){
			jpql.append(" and hc.argrPlantae = true ");
		}
		if(codigoReino == ReinoEnum.ANIMAL.getCodigo()){
			jpql.append(" and hc.argrAnimal = true ");
		}
		jpql.append(" and hc.argrIdParent = :codigoArtificalGroup "); 
		jpql.append(" order by hc.argrName ASC");
		TypedQuery<ArtificialGroup> tQuery = this.getEntityManager().createQuery(jpql.toString(), ArtificialGroup.class );
		tQuery.setParameter("codigoArtificalGroup", codigoArtificalGroup);
		artificialGroupL = tQuery.getResultList();
		return artificialGroupL;
	} 
	
	public List<ArtificialGroup> buscarPorCodigoReinoYNombreGrupo(Integer codigoReino, String nombreGrupo) {
		List<ArtificialGroup>  artificialGroupL = null;
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT hc ");
		jpql.append(" from  ArtificialGroup hc ");
		jpql.append(" where hc.argrStatus =  true ");
		if(codigoReino == ReinoEnum.BACTERIA.getCodigo()){
			jpql.append(" and hc.argrEubacteria = true ");
		}
		if(codigoReino == ReinoEnum.ARCHAEA.getCodigo()){
			jpql.append(" and hc.argrArcheobacteria = true ");
		}
		if(codigoReino == ReinoEnum.VIRUSES.getCodigo()){
			jpql.append(" and hc.argrViruses = true ");
		}
		if(codigoReino == ReinoEnum.CHROMISTA.getCodigo()){
			jpql.append(" and hc.argrChromista = true ");
		}
		if(codigoReino == ReinoEnum.PROTOZOA.getCodigo()){
			jpql.append(" and hc.argrProtista = true ");
		}
		if(codigoReino == ReinoEnum.FUNGI.getCodigo()){
			jpql.append(" and hc.argrFungi = true ");
		}	
		if(codigoReino == ReinoEnum.PLANTAE.getCodigo()){
			jpql.append(" and hc.argrPlantae = true ");
		}
		if(codigoReino == ReinoEnum.ANIMAL.getCodigo()){
			jpql.append(" and hc.argrAnimal = true ");
		}
		jpql.append(" and LOWER(hc.argrName) LIKE :nombre ");
		jpql.append(" order by hc.argrName ASC");
		TypedQuery<ArtificialGroup> tQuery = this.getEntityManager().createQuery(jpql.toString(), ArtificialGroup.class );
		tQuery.setParameter("nombre", "%"+nombreGrupo.toLowerCase()+"%");
		
		artificialGroupL = tQuery.getResultList();
		return artificialGroupL;
	} 
	
	
	
	public List<SpecieTaxa> buscarEspeciePorGrupoNomEspecie(Integer codigo, String nombre) {
		List<SpecieTaxa>  artificialGroupL = null;
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT stx ");
		jpql.append(" from  SpecieTaxa stx, SpecieTaxaArtificialGroup ag ");
		jpql.append(" where  stx.sptaId =  ag.specieTaxa.sptaId ");
		jpql.append(" and  ag.artificialGroup.argrId =  :codigo ");
		jpql.append(" and  ag.stagStatus =  true ");
		if(null != nombre && nombre.length()>0) {
			jpql.append(" and LOWER(stx.sptaScientificGui) LIKE :nombre ");
		}
		TypedQuery<SpecieTaxa> tQuery = this.getEntityManager().createQuery(jpql.toString(), SpecieTaxa.class );
		tQuery.setParameter("codigo", codigo);
		if(null != nombre && nombre.length()>0) {
			tQuery.setParameter("nombre", "%"+nombre.toLowerCase()+"%");
		}
		artificialGroupL = tQuery.getResultList();
		return artificialGroupL;
	} 
	
	
	

}
