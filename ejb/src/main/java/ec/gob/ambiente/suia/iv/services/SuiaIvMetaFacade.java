package ec.gob.ambiente.suia.iv.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ec.gob.ambiente.enlisy.actionplan.enumerador.EstadoRegistroEnum;
import ec.gob.ambiente.suia.dto.MetaTo;
import ec.gob.ambiente.suia.iv.dao.SuiaIvActividadDao;
import ec.gob.ambiente.suia.iv.dao.SuiaIvIndicadorActividadDao;
import ec.gob.ambiente.suia.iv.dao.SuiaIvIndicadorDao;
import ec.gob.ambiente.suia.iv.dao.SuiaIvMetaDao;
import ec.gob.ambiente.suia.iv.dao.SuiaIvObjetivoDao;
import ec.gob.ambiente.suia.iv.model.SuiaIvActividad;
import ec.gob.ambiente.suia.iv.model.SuiaIvIndicador;
import ec.gob.ambiente.suia.iv.model.SuiaIvIndicadorActividad;
import ec.gob.ambiente.suia.iv.model.SuiaIvMeta;
import ec.gob.ambiente.suia.iv.model.SuiaIvObjetivo;
import ec.gob.ambiente.suia.iv.model.SuiaIvPlanAccion;

/**
 * @author avfp
 *
 */
@Stateless
public class SuiaIvMetaFacade {

	@EJB
	private SuiaIvMetaDao suiaIvMetaDao;

	@EJB
	private SuiaIvObjetivoFacade suiaIvObjetivoFacade;

	@EJB
	private SuiaIvPlanAccionFacade suiaIvPlanAccionFacade;

	@EJB
	private SuiaIvObjetivoDao suiaIvObjetivoDao;

	@EJB
	private SuiaIvActividadDao suiaIvActividadDao;

	@EJB
	private SuiaIvIndicadorDao suiaIvIndicadorDao;
	
	
	@EJB
	private SuiaIvIndicadorActividadDao  suiaIvIndicadorActividadDao;
	/**
	 * 
	 * Guarda las metas
	 * 
	 * @param metaTo
	 * @return
	 * @throws Exception
	 */
	public MetaTo guardar(MetaTo metaTo) throws Exception {
		SuiaIvMeta suiaIvMeta = new SuiaIvMeta();
		suiaIvMeta.setNombre(metaTo.getNombre());
		SuiaIvPlanAccion suiaIvPlanAccion = new SuiaIvPlanAccion();
		suiaIvPlanAccion.setCodigo(Long.valueOf(metaTo.getPlanAccionTo().getId()));
		suiaIvMeta.setSuiaIvPlanAccion(suiaIvPlanAccion);
		suiaIvMeta.setEstado(EstadoRegistroEnum.ACT);
		suiaIvMeta.setUsuarioInserta(metaTo.getUsuario());
		suiaIvMeta.setFechaInserta(new Date());
		suiaIvMetaDao.guardar(suiaIvMeta);
		metaTo.setId(suiaIvMeta.getCodigo());
		return metaTo;
	}

	/**
	 * busca la meta por la clave primaria
	 * 
	 * @param metaTo
	 * @return
	 * @throws Exception
	 */
	public MetaTo buscarPorCodigo(MetaTo metaTo) throws Exception {
		return covertirAMetaTo(suiaIvMetaDao.find(metaTo.getId()));
	}

	/**
	 * Cambia el estado del registro
	 * 
	 * @param metaTo
	 * @return
	 * @throws Exception
	 */
	public MetaTo cambiarEstadoRegistro(MetaTo metaTo) throws Exception {
		SuiaIvMeta suiaIvMeta = suiaIvMetaDao.find(Long.valueOf(metaTo.getId()));
		suiaIvMeta.setEstado(metaTo.getEstado());
		suiaIvMeta.setUsuarioActualiza(metaTo.getUsuario());
		suiaIvMeta.setFechaActualiza(new Date());
		return covertirAMetaTo(suiaIvMeta);
	}

	/**
	 * busca las metas por el plan de accion
	 * 
	 * @param codigoPlanAccion
	 * @return
	 */
	public List<MetaTo> buscarMetasPorPlanAccion(Long codigoPlanAccion) {
		List<MetaTo> metaToL = new ArrayList<>();
		List<SuiaIvMeta> suiaIvMetaL = suiaIvMetaDao.buscarMetasPorPlanAccion(codigoPlanAccion);
		if (null != suiaIvMetaL && suiaIvMetaL.size() > 0) {
			for (SuiaIvMeta suiaIvMeta : suiaIvMetaL) {
				metaToL.add(covertirAMetaTo(suiaIvMeta));
			}
		}
		return metaToL;
	}

	public Boolean existeMetasOAIPorPlanAccion(Long codigoPlanAccion) throws Exception {
		List<SuiaIvMeta> suiaIvMetaL = suiaIvMetaDao.buscarMetasPorPlanAccion(codigoPlanAccion);
		if (null == suiaIvMetaL || suiaIvMetaL.size() == 0)
			return false;

		for (SuiaIvMeta suiaIvMeta : suiaIvMetaL) {
			if (null == suiaIvMeta.getSuiaIvObjetivos() || suiaIvMeta.getSuiaIvObjetivos().size() == 0)
				return false;
			else {
				
				List<SuiaIvObjetivo> objetivoL = suiaIvObjetivoDao.buscarObjetivoPorMeta(suiaIvMeta.getCodigo());
				if (null == objetivoL || objetivoL.size() == 0)
					return false;
				for (SuiaIvObjetivo objetivo : objetivoL) {
					List<SuiaIvActividad> suiaIvActividadL = suiaIvActividadDao
							.buscarActividadPorObjetivo(objetivo.getCodigo());
					if (null == suiaIvActividadL || suiaIvActividadL.size() == 0)
						return false;
					else {
						for (SuiaIvActividad  actividad : suiaIvActividadL) {
							List<SuiaIvIndicadorActividad> indicadorActividadL = suiaIvIndicadorActividadDao.buscarIndicadorPorActividad(actividad.getCodigo());
							if (null == indicadorActividadL || indicadorActividadL.size() == 0)
								return false;		
						}
					}
					List<SuiaIvIndicador> suiaIvIndicadorL = suiaIvIndicadorDao
							.buscarIndicadorPorObjetivo(objetivo.getCodigo());
					if (null == suiaIvIndicadorL || suiaIvIndicadorL.size() == 0)
						return false;
				}
			}
		}
		return true;
	}

	public MetaTo covertirAMetaTo(SuiaIvMeta suiaIvMeta) {
		MetaTo metaTo = new MetaTo();
		metaTo.setId(suiaIvMeta.getCodigo());
		metaTo.setNombre(suiaIvMeta.getNombre());
		if (null != suiaIvMeta.getSuiaIvPlanAccion())
			metaTo.setPlanAccionTo(suiaIvPlanAccionFacade.covertirAPlanAccionTo(suiaIvMeta.getSuiaIvPlanAccion()));

		return metaTo;

	}

}
