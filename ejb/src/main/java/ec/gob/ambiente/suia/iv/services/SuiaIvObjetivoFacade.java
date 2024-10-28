package ec.gob.ambiente.suia.iv.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.gob.ambiente.enlisy.actionplan.enumerador.EstadoRegistroEnum;
import ec.gob.ambiente.suia.dto.ObjetivoTo;
import ec.gob.ambiente.suia.iv.dao.SuiaIvObjetivoDao;
import ec.gob.ambiente.suia.iv.model.SuiaIvMeta;
import ec.gob.ambiente.suia.iv.model.SuiaIvObjetivo;
import ec.gob.ambiente.suia.iv.model.SuiaIvPlanAccion;

/**
 * @author avfp
 *
 */
@Stateless
public class SuiaIvObjetivoFacade {

	@EJB
	private SuiaIvObjetivoDao suiaIvObjetivoDao;

	@EJB
	private SuiaIvMetaFacade suiaIvMetaFacade;

	public ObjetivoTo guardar(ObjetivoTo objetivoTo) throws Exception {
		SuiaIvObjetivo suiaIvObjetivo = new SuiaIvObjetivo();
		suiaIvObjetivo.setNombre(objetivoTo.getNombre());
		SuiaIvMeta suiaIvMeta = new SuiaIvMeta();
		suiaIvMeta.setCodigo(objetivoTo.getMeta().getId());
		SuiaIvPlanAccion planAccion = new SuiaIvPlanAccion();
		planAccion.setCodigo(objetivoTo.getMeta().getPlanAccionTo().getId());
		suiaIvObjetivo.setSuiaIvMeta(suiaIvMeta);
		suiaIvObjetivo.setSuiaIvPlanAccion(planAccion);
		suiaIvObjetivo.setEstado(EstadoRegistroEnum.ACT);
		suiaIvObjetivo.setUsuarioInserta(objetivoTo.getUsuario());
		suiaIvObjetivo.setFechaInserta(new Date());
		suiaIvObjetivoDao.guardar(suiaIvObjetivo);
		objetivoTo.setId(suiaIvObjetivo.getCodigo());
		return objetivoTo;
	}

	/**
	 * Buscar por clave primaria
	 * 
	 * @param objetivoTo
	 * @return
	 */
	public ObjetivoTo buscarPorCodigo(ObjetivoTo objetivoTo) {
		return covertirAObjetivoTo(suiaIvObjetivoDao.find(Long.valueOf(objetivoTo.getId())));
	}

	/**
	 * Cambia el estado de registro a ELI o ACT
	 * 
	 * @param objetivoTo
	 * @return
	 * @throws Exception
	 */
	public ObjetivoTo cambiarEstadoRegistro(ObjetivoTo objetivoTo) throws Exception {
		SuiaIvObjetivo suiaIvObjetivo = suiaIvObjetivoDao.find(Long.valueOf(objetivoTo.getId()));
		suiaIvObjetivo.setEstado(objetivoTo.getEstado());
		suiaIvObjetivo.setUsuarioActualiza(objetivoTo.getUsuario());
		suiaIvObjetivo.setFechaActualiza(new Date());
		return covertirAObjetivoTo(suiaIvObjetivoDao.actualizar(suiaIvObjetivo));
	}


	/**
	 * busco los objetivos por la meta
	 * 
	 * @param codigoMeta
	 * @return
	 * @throws Exception
	 */
	public List<ObjetivoTo> buscarObjetivoPorMeta(Long codigoMeta) throws Exception {
		List<ObjetivoTo> objetivosToL = new ArrayList<>();
		List<SuiaIvObjetivo> suiaIvObjetivoL = suiaIvObjetivoDao.buscarObjetivoPorMeta(codigoMeta);
		if (null != suiaIvObjetivoL && suiaIvObjetivoL.size() > 0) {
			for (SuiaIvObjetivo suiaIvObjetivo : suiaIvObjetivoL) {
				objetivosToL.add(covertirAObjetivoTo(suiaIvObjetivo));
			}
		}
		return objetivosToL;
	}

	public ObjetivoTo covertirAObjetivoTo(SuiaIvObjetivo suiaIvObjetivo) {
		ObjetivoTo objetivoTo = new ObjetivoTo();
		objetivoTo.setId(suiaIvObjetivo.getCodigo());
		objetivoTo.setNombre(suiaIvObjetivo.getNombre());
		if (null != suiaIvObjetivo.getSuiaIvMeta()) {
			objetivoTo.setMeta(suiaIvMetaFacade.covertirAMetaTo(suiaIvObjetivo.getSuiaIvMeta()));
		}
		return objetivoTo;
	}

}
