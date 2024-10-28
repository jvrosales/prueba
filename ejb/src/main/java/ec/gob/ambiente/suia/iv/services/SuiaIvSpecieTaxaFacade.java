package ec.gob.ambiente.suia.iv.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.gob.ambiente.enlisy.speciescatalog.model.SpecieTaxa;
import ec.gob.ambiente.suia.dto.EspecieGrupoTo;
import ec.gob.ambiente.suia.iv.dao.SuiaIvSpecieTaxaDao;

/**
 * Servicios para la administracion de las publicaciones del especialista
 * 
 * @author EXCO
 *
 */
@Stateless
public class SuiaIvSpecieTaxaFacade   {

	@EJB
	private SuiaIvSpecieTaxaDao suiaIvSpecieTaxaDao;

	
	/**
	 * BUSCA LAS ESPECIES
	 * @param codigo
	 * @param nombre
	 * @return
	 */
	public List<EspecieGrupoTo> buscarEspeciePorGrupoNomEspecie(Integer codigo, String nombre){
		List<EspecieGrupoTo> especieGrupoToL = new ArrayList<>();
		List<SpecieTaxa> specieTaxaL = suiaIvSpecieTaxaDao.buscarEspeciePorGrupoNomEspecie(codigo, nombre);
		if (null != specieTaxaL && specieTaxaL.size() > 0) {
			for (SpecieTaxa specieTaxa : specieTaxaL) {
				especieGrupoToL.add(covertirAEspecieToTaxa(specieTaxa));
			}
		}
		return especieGrupoToL;
		
	}
	
	public EspecieGrupoTo buscarEspeciePorId(Integer codigoEspecie){
		return covertirAEspecieToTaxa(suiaIvSpecieTaxaDao.find(codigoEspecie));
		
	}
	
	public EspecieGrupoTo covertirAEspecieToTaxa(SpecieTaxa specieTaxa) {
		EspecieGrupoTo especieGrupoTo = new EspecieGrupoTo();
		if (null != specieTaxa.getSptaId()) {
			especieGrupoTo.setId(specieTaxa.getSptaId());
			especieGrupoTo.setNombre(specieTaxa.getSptaScientificName());
			especieGrupoTo.setCientifico(specieTaxa.getSptaScientificName());
			especieGrupoTo.setSptaScientificGui(specieTaxa.getSptaScientificGui());	
			

			
		}
		return especieGrupoTo;
	}

	
	
}
