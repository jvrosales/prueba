package ec.gob.ambiente.suia.iv.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.gob.ambiente.enlisy.actionplan.enumerador.EstadoPlanAccionEnum;
import ec.gob.ambiente.enlisy.actionplan.enumerador.EstadoRegistroEnum;
import ec.gob.ambiente.enlisy.actionplan.enumerador.ReinoEnum;
import ec.gob.ambiente.enlisy.actionplan.enumerador.TipoPlanAccionEnum;
import ec.gob.ambiente.suia.dto.ActividadTo;
import ec.gob.ambiente.suia.dto.EspecieGrupoTo;
import ec.gob.ambiente.suia.dto.IndicadorTo;
import ec.gob.ambiente.suia.dto.MetaTo;
import ec.gob.ambiente.suia.dto.ObjetivoTo;
import ec.gob.ambiente.suia.dto.PlanAccionTo;
import ec.gob.ambiente.suia.iv.dao.SuiaIvPlanAccionDao;
import ec.gob.ambiente.suia.iv.model.SuiaIvPlanAccion;

/**
 * @author avfp
 *
 */
@Stateless
public class SuiaIvPlanAccionFacade {

	@EJB
	private SuiaIvPlanAccionDao suiaIvPlanAccionDao;

	@EJB
	private SuiaIvObjetivoFacade suiaIvObjetivoFacade;

	@EJB
	private SuiaIvSpecieTaxaFacade suiaIvSpecieTaxaFacade;
	
	@EJB 
	private SuiaIvArtificialGroupFacade suiaIvArtificialGroupFacade;
	
	@EJB
	private SuiaIvMetaFacade suiaIvMetaFacade;
	
	@EJB
	private SuiaIvActividadFacade suiaIvActividadFacade;
	
	@EJB
	private SuiaIvIndicadorFacade suiaIvIndicadorFacade;

	@EJB
	private SuiaIvSeguimientoFacade suiaIvSeguimientoFacade;
	
	@EJB
	private SuiaIvIndicadorActividadFacade suiaIvIndicadorActividadFacade;
	
	@EJB
	private SuiaIvDocumentoFacade suiaIvDocumentoFacade;
	/**
	 * guarda un plan de accion el encabezado
	 * 
	 * @param planAccionTo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public PlanAccionTo guardar(PlanAccionTo planAccionTo) throws Exception {
		/* PLAN ACCION */
		SuiaIvPlanAccion planAccion = new SuiaIvPlanAccion();
		planAccion.setCodigoEspecieGrupo(planAccionTo.getCodigoEspecieGrupo());
		planAccion.setSptaScientificGui(planAccionTo.getSptaScientificGui());	
		planAccion.setCodigoReino(planAccionTo.getCodigoReino());
		planAccion.setCodigoGrupoFiltroUno(planAccionTo.getCodigoGrupoFiltroUno());
		planAccion.setCodigoGrupoFiltroDos(planAccionTo.getCodigoGrupoFiltroDos());
		planAccion.setTipoPlan(planAccionTo.getTipo().getCodigo());
		planAccion.setEstadoPlan(planAccionTo.getEstadoPlan().getCodigo());
		planAccion.setFecha(planAccionTo.getFechaIngreso());
		planAccion.setNombre(planAccionTo.getNombre());
		/* TODO guardar primero en alfresco para obtener la ruta */
		planAccion.setRutaDocumento(planAccionTo.getRutaDocumento());
		planAccion.setNombreDocumento(planAccionTo.getNombreDocumento());
		planAccion.setFormatoDocumento(planAccionTo.getExtensionDocumento());
		planAccion.setEstado(EstadoRegistroEnum.ACT);
		planAccion.setUsuarioInserta(planAccionTo.getUsuario());
		planAccion.setFechaInserta(new Date());
		suiaIvPlanAccionDao.guardar(planAccion);
		String ruta = "PLAN_CONSERVACION/"+ String.valueOf(planAccion.getFechaInserta().getYear() + 1900) + "/" + planAccion.getCodigo().toString();
		String pathAlfresco = suiaIvDocumentoFacade.guardarDocumentoAlfresco(ruta, planAccionTo.getNombreDocumento(), planAccionTo.getContent(), "PLAN", planAccion.getCodigo().intValue());
		planAccion.setRutaDocumento(pathAlfresco);
		suiaIvPlanAccionDao.actualizar(planAccion);
		planAccionTo.setId(planAccion.getCodigo());
		return planAccionTo;
	}

	@SuppressWarnings("deprecation")
	public PlanAccionTo actualizar(PlanAccionTo planAccionTo) throws Exception {
			SuiaIvPlanAccion planAccion = suiaIvPlanAccionDao.find(planAccionTo.getId());
			planAccion.setCodigoEspecieGrupo(planAccionTo.getCodigoEspecieGrupo());
			planAccion.setSptaScientificGui(planAccionTo.getSptaScientificGui());
			planAccion.setCodigoReino(planAccionTo.getCodigoReino());
			planAccion.setCodigoGrupoFiltroUno(planAccionTo.getCodigoGrupoFiltroUno());
			planAccion.setCodigoGrupoFiltroDos(planAccionTo.getCodigoGrupoFiltroDos());
			planAccion.setTipoPlan(planAccionTo.getTipo().getCodigo());
			planAccion.setNombre(planAccionTo.getNombre());
			planAccion.setRutaDocumento(planAccionTo.getRutaDocumento());
			if(planAccion.getRutaDocumento().isEmpty()) {
				String ruta = "PLAN_CONSERVACION/"+ String.valueOf(planAccion.getFechaInserta().getYear() + 1900) + "/" + planAccion.getCodigo().toString();
				String pathAlfresco = suiaIvDocumentoFacade.guardarDocumentoAlfresco(ruta, planAccionTo.getNombreDocumento(), planAccionTo.getContent(), "PLAN", planAccion.getCodigo().intValue());
				planAccion.setRutaDocumento(pathAlfresco);
				planAccion.setNombreDocumento(planAccionTo.getNombreDocumento());
				planAccion.setFormatoDocumento(planAccionTo.getExtensionDocumento());
			}
			
			planAccion.setFechaActualiza(new Date());
			planAccion.setUsuarioActualiza(planAccionTo.getUsuario());
			suiaIvPlanAccionDao.actualizar(planAccion);
		return planAccionTo;
	}

	/**
	 * busca un plan de accion por la clave primaria
	 * 
	 * @param codigo
	 * @return
	 */
	public PlanAccionTo buscarPorCodigo(PlanAccionTo planAccionTo) {
		SuiaIvPlanAccion planAccion = suiaIvPlanAccionDao.find(Long.valueOf(planAccionTo.getId()));
		return covertirAPlanAccionTo(planAccion);
	}

	/**
	 * buscar todos los activos
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<PlanAccionTo> buscarTodosActivos() throws Exception {
		List<PlanAccionTo> planAccionToL = new ArrayList<PlanAccionTo>();
		List<SuiaIvPlanAccion> planAccionL = suiaIvPlanAccionDao.buscarTodosActivos();
		if (null != planAccionL && planAccionL.size() > 0) {
			for (SuiaIvPlanAccion planAccion : planAccionL) {
				planAccionToL.add(covertirAPlanAccionTo(planAccion));
			}
		}
		return planAccionToL;
	}

	/**
	 * buscar todos los activos
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<PlanAccionTo> buscarTodosActivosPublicados() throws Exception {
		List<PlanAccionTo> planAccionToL = new ArrayList<PlanAccionTo>();
		List<SuiaIvPlanAccion> planAccionL = suiaIvPlanAccionDao.buscarActivosPublicados();
		if (null != planAccionL && planAccionL.size() > 0) {
			for (SuiaIvPlanAccion planAccion : planAccionL) {
				EspecieGrupoTo especieGrupoTo = null;
				if (TipoPlanAccionEnum.TIPO_GRUPO.getCodigo().equals(planAccion.getTipoPlan())) {
					especieGrupoTo = suiaIvArtificialGroupFacade.buscarArtificialGroupPorId(planAccion.getCodigoEspecieGrupo());
					planAccion.setNombreCientificoEspecie(especieGrupoTo.getCientifico());
					planAccion.setNombreComunEspecie(especieGrupoTo.getNombre());
				} else {
					especieGrupoTo = suiaIvSpecieTaxaFacade.buscarEspeciePorId(planAccion.getCodigoEspecieGrupo());
					planAccion.setNombreCientificoEspecie(especieGrupoTo.getCientifico());
					planAccion.setNombreComunEspecie(especieGrupoTo.getNombre());
					
					especieGrupoTo = suiaIvArtificialGroupFacade.buscarArtificialGroupPorId(planAccion.getCodigoGrupoFiltroUno());
					planAccion.setNombeGrupoFiltroUno(especieGrupoTo.getCientifico());
					if (null != planAccion.getCodigoGrupoFiltroDos()) {
						especieGrupoTo = suiaIvArtificialGroupFacade.buscarArtificialGroupPorId(planAccion.getCodigoGrupoFiltroDos());
						planAccion.setNombeGrupoFiltroDos(especieGrupoTo.getCientifico());			
					}
				}
				planAccionToL.add(covertirAPlanAccionTo(planAccion));
			}
		}
		return planAccionToL;
	}
	  
	/**
	 * cambia el estado de un registro
	 * 
	 * @param planAccionTo
	 * @return
	 * @throws Exception
	 */
	public PlanAccionTo cambiarEstadoRegistro(PlanAccionTo planAccionTo) throws Exception {
		SuiaIvPlanAccion planAccion = suiaIvPlanAccionDao.find(planAccionTo.getId());
		planAccion.setEstado(planAccionTo.getEstado());
		planAccion.setUsuarioActualiza(planAccionTo.getUsuario());
		planAccion.setFechaActualiza(new Date());
		return covertirAPlanAccionTo(suiaIvPlanAccionDao.actualizar(planAccion));
	}

	/**
	 * Cambia el estado del plan a publicado
	 * 
	 * @param planAccionTo
	 * @return
	 * @throws Exception
	 */
	public Boolean cambiarEstadoPlanPUB(PlanAccionTo planAccionTo) throws Exception {
		if (suiaIvMetaFacade.existeMetasOAIPorPlanAccion(planAccionTo.getId())) {
			SuiaIvPlanAccion planAccion = suiaIvPlanAccionDao.find(planAccionTo.getId());
			planAccion.setEstadoPlan(EstadoPlanAccionEnum.PUBLICADO.getCodigo());
			planAccion.setUsuarioActualiza(planAccionTo.getUsuario());
			planAccion.setFechaActualiza(new Date());
			suiaIvPlanAccionDao.actualizar(planAccion);
			return true;
		}
		return false;
	}
	
	
	/**
	 * Busca todo lo referente al plan de accion
	 * @param codigoPlan
	 * @return
	 * @throws Exception 
	 */
	public PlanAccionTo buscarPlanMetObjIndAct(Long codigoPlanAccion) throws Exception {
		PlanAccionTo planAccionRespuestTo = new PlanAccionTo();
		SuiaIvPlanAccion planAccion = suiaIvPlanAccionDao.find(codigoPlanAccion);
		EspecieGrupoTo especieGrupoTo = null;
		planAccion.setNombreReino(ReinoEnum.obtenerNombre(planAccion.getCodigoReino()));	
		if (TipoPlanAccionEnum.TIPO_GRUPO.getCodigo().equals(planAccion.getTipoPlan())) {
			especieGrupoTo = suiaIvArtificialGroupFacade.buscarArtificialGroupPorId(planAccion.getCodigoEspecieGrupo());
			planAccion.setNombreCientificoEspecie(especieGrupoTo.getCientifico());
			planAccion.setNombreComunEspecie(especieGrupoTo.getNombre());
			
		} else {
			especieGrupoTo = suiaIvSpecieTaxaFacade.buscarEspeciePorId(planAccion.getCodigoEspecieGrupo());
			planAccion.setNombreCientificoEspecie(especieGrupoTo.getCientifico());
			planAccion.setNombreComunEspecie(especieGrupoTo.getNombre());
			
			especieGrupoTo = suiaIvArtificialGroupFacade.buscarArtificialGroupPorId(planAccion.getCodigoGrupoFiltroUno());
			planAccion.setNombeGrupoFiltroUno(especieGrupoTo.getCientifico());
			if (null != planAccion.getCodigoGrupoFiltroDos()) {
				especieGrupoTo = suiaIvArtificialGroupFacade.buscarArtificialGroupPorId(planAccion.getCodigoGrupoFiltroDos());
				planAccion.setNombeGrupoFiltroDos(especieGrupoTo.getCientifico());			
			}
		}
		planAccionRespuestTo.setId(planAccion.getCodigo());
		planAccionRespuestTo.setNombre(planAccion.getNombre());
		//Metas
		List<MetaTo> suiaIvMetaL = suiaIvMetaFacade.buscarMetasPorPlanAccion(codigoPlanAccion);
		if (null != suiaIvMetaL && suiaIvMetaL.size() > 0) {
			int index = 0;
			for (MetaTo metaTo : suiaIvMetaL) {
				//Objetivo
				List<ObjetivoTo> objetivoL =  suiaIvObjetivoFacade.buscarObjetivoPorMeta(metaTo.getId());
				if (null != objetivoL && objetivoL.size() > 0) {
					int indexObj = 0;
					for (ObjetivoTo objetivoTo : objetivoL) {
						//Actividades
						List<ActividadTo> actividadToL =suiaIvActividadFacade.buscarActividadPorObjetivo(objetivoTo.getId());
						int indexIndAc = 0;
						for (ActividadTo actividadTo : actividadToL) {
							List<IndicadorTo> indicadorTo = suiaIvIndicadorActividadFacade.buscarIndicadorPorActividad(actividadTo.getId());
							actividadToL.get(indexIndAc).setIndicadorTo(indicadorTo);
							indexIndAc ++;
						}
						objetivoL.get(indexObj).setActividades(actividadToL);	
						//Indicadores
						List<IndicadorTo> indicadorToL = suiaIvIndicadorFacade.buscarIndicadorPorObjetivo(objetivoTo.getId());
						objetivoL.get(indexObj).setIndicadores(indicadorToL);
						indexObj++;
					}
					suiaIvMetaL.get(index).setObjetivos(objetivoL);
					index++;
				}
			}
		}
		planAccionRespuestTo.setMetas(suiaIvMetaL);
		return planAccionRespuestTo;
	}
	
	
	/**
	 * Busca el plan de accion por nombre
	 * @param codigoPlan
	 * @return
	 */
	public List<PlanAccionTo> buscarPlanPorNombre(String nombrePlan) throws Exception {
		List<PlanAccionTo> planAccionToL = new ArrayList<PlanAccionTo>();
		List<SuiaIvPlanAccion> planAccionL = suiaIvPlanAccionDao.buscarPlanPorNombre(nombrePlan);
		if (null != planAccionL && planAccionL.size() > 0) {
			for (SuiaIvPlanAccion planAccion : planAccionL) {
				EspecieGrupoTo especieGrupoTo = null;
				planAccion.setNombreReino(ReinoEnum.obtenerNombre(planAccion.getCodigoReino()));	
				if (TipoPlanAccionEnum.TIPO_GRUPO.getCodigo().equals(planAccion.getTipoPlan())) {
					especieGrupoTo = suiaIvArtificialGroupFacade.buscarArtificialGroupPorId(planAccion.getCodigoEspecieGrupo());
					planAccion.setNombreCientificoEspecie(especieGrupoTo.getCientifico());
					planAccion.setNombreComunEspecie(especieGrupoTo.getNombre());
					
				} else {
					especieGrupoTo = suiaIvSpecieTaxaFacade.buscarEspeciePorId(planAccion.getCodigoEspecieGrupo());
					planAccion.setNombreCientificoEspecie(especieGrupoTo.getCientifico());
					planAccion.setNombreComunEspecie(especieGrupoTo.getNombre());
					
					especieGrupoTo = suiaIvArtificialGroupFacade.buscarArtificialGroupPorId(planAccion.getCodigoGrupoFiltroUno());
					planAccion.setNombeGrupoFiltroUno(especieGrupoTo.getCientifico());
					if (null != planAccion.getCodigoGrupoFiltroDos()) {
						especieGrupoTo = suiaIvArtificialGroupFacade.buscarArtificialGroupPorId(planAccion.getCodigoGrupoFiltroDos());
						planAccion.setNombeGrupoFiltroDos(especieGrupoTo.getCientifico());			
					}
				}
				planAccionToL.add(covertirAPlanAccionTo(planAccion));
			}
		}
		return planAccionToL;
	}
	
	
	

	/**
	 * convierte un plande_accion a PlanAccionTo
	 * 
	 * @param planAccion
	 * @return
	 */
	public PlanAccionTo covertirAPlanAccionTo(SuiaIvPlanAccion planAccion) {
		PlanAccionTo planAccionRespuestTo = new PlanAccionTo();
		planAccionRespuestTo.setId(planAccion.getCodigo());
		planAccionRespuestTo.setNombre(planAccion.getNombre());
		planAccionRespuestTo.setFechaIngreso(planAccion.getFecha());
		planAccionRespuestTo.setRutaDocumento(planAccion.getRutaDocumento());
		planAccionRespuestTo.setNombreDocumento(planAccion.getNombreDocumento());
		planAccionRespuestTo.setExtensionDocumento(planAccion.getFormatoDocumento());
		planAccionRespuestTo.setNombreCientificoEspecie(planAccion.getNombreCientificoEspecie());
		planAccionRespuestTo.setSptaScientificGui(planAccion.getSptaScientificGui());
		planAccionRespuestTo.setUsuario(planAccion.getUsuarioInserta());
		planAccionRespuestTo.setEstadoPlan(EstadoPlanAccionEnum.convertStringToEnum(planAccion.getEstadoPlan()));
		planAccionRespuestTo.setTipo(TipoPlanAccionEnum.convertStringToEnum(planAccion.getTipoPlan()));
		planAccionRespuestTo.setCodigoEspecieGrupo(planAccion.getCodigoEspecieGrupo());
		planAccionRespuestTo.setCodigoReino(planAccion.getCodigoReino());
		planAccionRespuestTo.setNombeReino(planAccion.getNombreReino());
		planAccionRespuestTo.setCodigoGrupoFiltroUno(planAccion.getCodigoGrupoFiltroUno());
		planAccionRespuestTo.setCodigoGrupoFiltroDos(planAccion.getCodigoGrupoFiltroDos());
		planAccionRespuestTo.setNombeGrupoFiltroUno(planAccion.getNombeGrupoFiltroUno());
		planAccionRespuestTo.setNombeGrupoFiltroDos(planAccion.getNombeGrupoFiltroDos());
		planAccionRespuestTo.setEstado(planAccion.getEstado());
		return planAccionRespuestTo;
	}

	
	
}
