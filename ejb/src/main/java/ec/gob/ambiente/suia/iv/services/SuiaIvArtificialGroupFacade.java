package ec.gob.ambiente.suia.iv.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.gob.ambiente.enlisy.speciescatalog.model.ArtificialGroup;
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecieTaxaArtificialGroup;
import ec.gob.ambiente.suia.dto.EspecieGrupoTo;
import ec.gob.ambiente.suia.iv.dao.SuiaIvArtificialGroupDao;
import ec.gob.ambiente.suia.iv.dao.SuiaIvSpeciesTaxaArtificialGroupsDao;

/**
 * @author avfp
 *
 */
@Stateless
public class SuiaIvArtificialGroupFacade {

	@EJB
	private SuiaIvArtificialGroupDao suiaIvArtificialGroupDao;

	@EJB
	private SuiaIvSpeciesTaxaArtificialGroupsDao suiaIvSpeciesTaxaArtificialGroupsDao;
	
	
	public EspecieGrupoTo buscarArtificialGroupPorId(Integer codigoArtificalGroup) {
		return covertirAGrupoArtificialTo(suiaIvArtificialGroupDao.find(codigoArtificalGroup));	
	}
	
	
	/**
	 * Busca el grupo por el reino y el codigoArtificalGroup
	 * 
	 * codigoArtificalGroup  enviar 0 si en el grupo principal / caso contrario va el artifical group seleccionado.
	 * @return
	 */
	public List<EspecieGrupoTo> buscarArtificialGroupPorCodigoReino(Integer codigoReino, Integer codigoArtificalGroup) {
		List<EspecieGrupoTo> especieGrupoToL = new ArrayList<>();
		List<ArtificialGroup> artificialGroupL = suiaIvArtificialGroupDao.buscarPorCodigoReino(codigoReino, codigoArtificalGroup);
		if (null != artificialGroupL && artificialGroupL.size() > 0) {
			for (ArtificialGroup artificialGroup : artificialGroupL) {
				especieGrupoToL.add(covertirAGrupoArtificialTo(artificialGroup));
			}
		}
		return especieGrupoToL;
	}

	/**
	 * Busca el grupo por el reino y el nombre del grupo
	 * @param codigo
	 * @param nombre
	 * @return
	 */
	public List<EspecieGrupoTo> buscarArtificialGroupPorCodigoReinoYNombreGrupo(Integer codigo, String nombre){
		List<EspecieGrupoTo> especieGrupoToL = new ArrayList<>();
		List<ArtificialGroup> artificialGroupL = suiaIvArtificialGroupDao.buscarPorCodigoReinoYNombreGrupo(codigo, nombre);
		if (null != artificialGroupL && artificialGroupL.size() > 0) {
			for (ArtificialGroup artificialGroup : artificialGroupL) {
				especieGrupoToL.add(covertirAGrupoArtificialTo(artificialGroup));
			}
		}
		return especieGrupoToL;
		
	}
	
	
	/**
	 * 
	 * @param codigo
	 * @return
	 */
	public List<EspecieGrupoTo> buscarSpeciesTaxaArtificialGroup(Integer codigo){
		List<EspecieGrupoTo> especieGrupoToL = new ArrayList<>();
		List<SpecieTaxaArtificialGroup> artificialGroupL = suiaIvSpeciesTaxaArtificialGroupsDao.buscarSpeciesTaxaArtificialGroup(codigo);
		if (null != artificialGroupL && artificialGroupL.size() > 0) {
			for (SpecieTaxaArtificialGroup artificialGroup : artificialGroupL) {
				especieGrupoToL.add(covertirASpecieTaxaGrupoArtificialTo(artificialGroup));
			}
		}
		return especieGrupoToL;
		
	}
	
	public EspecieGrupoTo covertirASpecieTaxaGrupoArtificialTo(SpecieTaxaArtificialGroup artificialGroup) {
		EspecieGrupoTo especieGrupoTo = new EspecieGrupoTo();
		if (null != artificialGroup.getSpecieTaxa().getSptaId()) {
			especieGrupoTo.setId(artificialGroup.getSpecieTaxa().getSptaId());
			especieGrupoTo.setNombre(artificialGroup.getStagNombre());
			especieGrupoTo.setCientifico(artificialGroup.getStagNombre());
			especieGrupoTo.setDescripcion(artificialGroup.getStagNombre());
		}
		return especieGrupoTo;
	}
	
	
	public EspecieGrupoTo covertirAGrupoArtificialTo(ArtificialGroup artificialGroup) {
		EspecieGrupoTo especieGrupoTo = new EspecieGrupoTo();
		if (null != artificialGroup.getArgrId()) {
			especieGrupoTo.setId(artificialGroup.getArgrId());
			especieGrupoTo.setNombre(artificialGroup.getArgrName());
			especieGrupoTo.setCientifico(artificialGroup.getArgrName());
			especieGrupoTo.setDescripcion(artificialGroup.getArgrCode());
		
			
		}
		return especieGrupoTo;
	}

}
