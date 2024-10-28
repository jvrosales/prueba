package ec.gob.ambiente.suia.iv.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import ec.gob.ambiente.enlisy.actionplan.enumerador.EstadoRegistroEnum;
import ec.gob.ambiente.enlisy.actionplan.enumerador.EstadoSeguimientoEnum;
import ec.gob.ambiente.suia.dto.ActividadTo;
import ec.gob.ambiente.suia.dto.AreasProtegidasTo;
import ec.gob.ambiente.suia.dto.DocumentoTo;
import ec.gob.ambiente.suia.dto.IndicadorTo;
import ec.gob.ambiente.suia.dto.ProvinciaTo;
import ec.gob.ambiente.suia.dto.ResponsableTo;
import ec.gob.ambiente.suia.dto.SeguimientoTo;
import ec.gob.ambiente.suia.iv.dao.SuiaIvIndicadorActividadDao;
import ec.gob.ambiente.suia.iv.dao.SuiaIvSeguimientoAreaProtegidaDao;
import ec.gob.ambiente.suia.iv.dao.SuiaIvSeguimientoDao;
import ec.gob.ambiente.suia.iv.dao.SuiaIvSeguimientoDocumentoDao;
import ec.gob.ambiente.suia.iv.dao.SuiaIvSeguimientoInvolucradoDao;
import ec.gob.ambiente.suia.iv.dao.SuiaIvSeguimientoProvinciaDao;
import ec.gob.ambiente.suia.iv.model.SuiaIvActividad;
import ec.gob.ambiente.suia.iv.model.SuiaIvAreasProtegidas;
import ec.gob.ambiente.suia.iv.model.SuiaIvIndicadorActividad;
import ec.gob.ambiente.suia.iv.model.SuiaIvInvolucrado;
import ec.gob.ambiente.suia.iv.model.SuiaIvPlanAccion;
import ec.gob.ambiente.suia.iv.model.SuiaIvSeguimiento;
import ec.gob.ambiente.suia.iv.model.SuiaIvSeguimientoAreaProtegida;
import ec.gob.ambiente.suia.iv.model.SuiaIvSeguimientoDocumento;
import ec.gob.ambiente.suia.iv.model.SuiaIvSeguimientoInvolucrado;
import ec.gob.ambiente.suia.iv.model.SuiaIvSeguimientoProvincia;

/**
 * @author avfp
 *
 */
@Stateless
public class SuiaIvSeguimientoFacade {

	@EJB
	private SuiaIvSeguimientoDao suiaIvSeguimientoDao;

	@EJB
	private SuiaIvActividadFacade suiaIvActividadFacade;

	@EJB
	private SuiaIvIndicadorActividadDao suiaIvIndicadorActividadDao;

	@EJB
	private SuiaIvSeguimientoProvinciaDao suiaIvSeguimientoProvinciaDao;

	@EJB
	private SuiaIvSeguimientoInvolucradoDao suiaIvSeguimientoInvolucradoDao;

	@EJB
	private SuiaIvSeguimientoDocumentoDao suiaIvSeguimientoDocumentoDao;
	
	@EJB
	private SuiaIvSeguimientoAreaProtegidaDao suiaIvSeguimientoAreasProtegidasDao;
	
	@EJB
	private SuiaIvDocumentoFacade suiaIvDocumentoFacade;
	/**
	 * Guarda el seguimiento de una actividad
	 * 
	 * @param seguimientoTo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public SeguimientoTo guardar(SeguimientoTo seguimientoTo) throws Exception {
		SuiaIvSeguimiento seguimiento = new SuiaIvSeguimiento();
		SuiaIvActividad actividad = new SuiaIvActividad();
		actividad.setCodigo(seguimientoTo.getActividad().getId());
		seguimiento.setSuiaIvActividad(actividad);
		SuiaIvIndicadorActividad suiaIvIndicadorActividad = suiaIvIndicadorActividadDao.find(seguimientoTo.getIndicador().getId());
		SuiaIvPlanAccion suiaIvPlanAccion = suiaIvIndicadorActividad.getSuiaIvActividad().getSuiaIvObjetivo().getSuiaIvPlanAccion();
		suiaIvIndicadorActividad.setPorcentajeAvance(seguimientoTo.getPorcentajeAvance());
		seguimiento.setSuiaIvIndicadorActividad(suiaIvIndicadorActividad);
		seguimiento.setActividadRealizada(seguimientoTo.getActividadRealizada());
		seguimiento.setDetalleUbicacion(seguimientoTo.getDetalleUbicacion());
		seguimiento.setPorcentajeAvance(seguimientoTo.getPorcentajeAvance());
		seguimiento.setFechaInicio(seguimientoTo.getFechaInicio());
		seguimiento.setFechaFin(seguimientoTo.getFechaFin());
		seguimiento.setEstado(EstadoRegistroEnum.ACT);
		seguimiento.setUsuarioInserta(seguimientoTo.getUsuario());
		seguimiento.setEstadoSeguimiento(seguimientoTo.getEstadoSeguimiento().getCodigo());		
		seguimiento.setFechaInserta(new Date());
		suiaIvSeguimientoDao.guardar(seguimiento);
		for (ResponsableTo responsablesTo : seguimientoTo.getResponsables()) {
			SuiaIvSeguimientoInvolucrado seguimientoInvolucrado = new SuiaIvSeguimientoInvolucrado();
			seguimientoInvolucrado.setSuiaIvSeguimiento(seguimiento);
			SuiaIvInvolucrado involucrado = new SuiaIvInvolucrado();
			involucrado.setCodigo(responsablesTo.getId());
			seguimientoInvolucrado.setSuiaIvInvolucrado(involucrado);
			seguimientoInvolucrado.setEstado(EstadoRegistroEnum.ACT);
			seguimientoInvolucrado.setUsuarioInserta(seguimientoTo.getUsuario());
			seguimientoInvolucrado.setFechaInserta(new Date());
			suiaIvSeguimientoInvolucradoDao.guardar(seguimientoInvolucrado);

		}

		for (ProvinciaTo provinciaTo : seguimientoTo.getProvincias()) {
			SuiaIvSeguimientoProvincia seguimientoProvincia = new SuiaIvSeguimientoProvincia();
			seguimientoProvincia.setSuiaIvSeguimiento(seguimiento);
			seguimientoProvincia.setGeloId(provinciaTo.getId());
			seguimientoProvincia.setEstado(EstadoRegistroEnum.ACT);
			seguimientoProvincia.setUsuarioInserta(seguimientoTo.getUsuario());
			seguimientoProvincia.setFechaInserta(new Date());
			suiaIvSeguimientoProvinciaDao.guardar(seguimientoProvincia);
		}
		if (null != seguimientoTo.getDocumentos() ) {
			for (DocumentoTo documentoTo : seguimientoTo.getDocumentos()) {
				SuiaIvSeguimientoDocumento seguimientoDocumento = new SuiaIvSeguimientoDocumento();
				seguimientoDocumento.setSuiaIvSeguimiento(seguimiento);
				seguimientoDocumento.setEstadoDocumento(documentoTo.getEstadoDocumento().getCodigo());
				seguimientoDocumento.setRutaDocumento(documentoTo.getRuta());
				seguimientoDocumento.setNombreDocumento(documentoTo.getName());
				seguimientoDocumento.setFormatoDocumento(documentoTo.getExtension());		
				seguimientoDocumento.setEstado(EstadoRegistroEnum.ACT);
				seguimientoDocumento.setUsuarioInserta(seguimientoTo.getUsuario());
				seguimientoDocumento.setFechaInserta(new Date());
				suiaIvSeguimientoDocumentoDao.guardar(seguimientoDocumento);
				String ruta = "PLAN_CONSERVACION/"+ String.valueOf(suiaIvPlanAccion.getFechaInserta().getYear() + 1900) + "/" + suiaIvPlanAccion.getCodigo().toString()+ "/SEGUIMIENTO_ACTIVIDAD";
				String pathAlfresco = suiaIvDocumentoFacade.guardarDocumentoAlfresco(ruta, documentoTo.getName(), documentoTo.getContent(), "SEGUIMIENTO_ACTIVIDAD", suiaIvPlanAccion.getCodigo().intValue());
				seguimientoDocumento.setRutaDocumento(pathAlfresco);
				suiaIvSeguimientoDocumentoDao.edit(seguimientoDocumento);
			}
		}

		if ( null != seguimientoTo.getAreasProtegidas()) {
			for (AreasProtegidasTo areasProtegidasTo : seguimientoTo.getAreasProtegidas()) {
				SuiaIvSeguimientoAreaProtegida suiaIvSeguimeintoAreaProtegida = new SuiaIvSeguimientoAreaProtegida();
				suiaIvSeguimeintoAreaProtegida.setSuiaIvSeguimiento(seguimiento);
				SuiaIvAreasProtegidas areasProtegidas = new SuiaIvAreasProtegidas();
				areasProtegidas.setCodigo(areasProtegidasTo.getId());	
				suiaIvSeguimeintoAreaProtegida.setSuiaIvAreaProtegida(areasProtegidas);			
				suiaIvSeguimeintoAreaProtegida.setEstado(EstadoRegistroEnum.ACT);
				suiaIvSeguimeintoAreaProtegida.setUsuarioInserta(seguimientoTo.getUsuario());
				suiaIvSeguimeintoAreaProtegida.setFechaInserta(new Date());
				
				suiaIvSeguimientoAreasProtegidasDao.guardar(suiaIvSeguimeintoAreaProtegida);
			}
		}
		
		
		seguimientoTo.setId(seguimiento.getCodigo());
		// Actualizar el indicadorActividad el pocentaje
		suiaIvIndicadorActividadDao.actualizar(suiaIvIndicadorActividad);

		return seguimientoTo;
	}

	/**
	 * busca un seguimiento por la clave primaria
	 * 
	 * @param codigo
	 * @return
	 */
	public SeguimientoTo buscarPorCodigo(SeguimientoTo seguimientoTo) throws Exception {
		SuiaIvSeguimiento seguimiento = suiaIvSeguimientoDao.find(seguimientoTo.getId());
		return covertirASeguimientoTo(seguimiento);
	}

	/**
	 * Cambiar el estado del registro
	 * 
	 * @param seguimientoTo
	 * @return
	 * @throws Exception
	 */
	public SeguimientoTo cambiarEstadoRegistro(SeguimientoTo seguimientoTo) throws Exception {
		SuiaIvSeguimiento seguimiento = suiaIvSeguimientoDao.find(seguimientoTo.getId());
		seguimiento.setEstado(seguimientoTo.getEstado());
		seguimiento.setUsuarioActualiza(seguimientoTo.getUsuario());
		seguimiento.setFechaActualiza(new Date());
		return covertirASeguimientoTo(suiaIvSeguimientoDao.actualizar(seguimiento));
	}

	/**
	 * busca los segumientos por la actividad
	 * 
	 * @param actividadTo
	 * @return
	 * @throws Exception
	 */
	public List<SeguimientoTo> buscaPorActividad(ActividadTo actividadTo) throws Exception {
		List<SeguimientoTo> seguimientoToL = new ArrayList<>();
		List<SuiaIvSeguimiento> seguimientoL = suiaIvSeguimientoDao.buscaPorActividad(actividadTo.getId());
		if (null != seguimientoL && seguimientoL.size() > 0) {
			for (SuiaIvSeguimiento suiaIvSeguimiento : seguimientoL) {
				seguimientoToL.add(covertirASeguimientoTo(suiaIvSeguimiento));
			}
		}
		return seguimientoToL;
	}
	
	
	/**
	 * busca los segumientos por el objetivo
	 * 
	 * @param actividadTo
	 * @return
	 * @throws Exception
	 */
	public List<SeguimientoTo> buscaPorObjetivo(Long codigoObjetivo) throws Exception {
		List<SeguimientoTo> seguimientoToL = new ArrayList<>();
		List<SuiaIvSeguimiento> seguimientoL = suiaIvSeguimientoDao.buscaPorObjetivo(codigoObjetivo);
		if (null != seguimientoL && seguimientoL.size() > 0) {
			for (SuiaIvSeguimiento suiaIvSeguimiento : seguimientoL) {
				seguimientoToL.add(covertirASeguimientoTo(suiaIvSeguimiento));
			}
		}
		return seguimientoToL;
	}

	/**
	 * busca los segumientos por el indicador
	 * 
	 * @param actividadTo
	 * @return
	 * @throws Exception
	 */
	public List<SeguimientoTo> buscaPorIndicador(Long codigoIndicador) throws Exception {
		List<SeguimientoTo> seguimientoToL = new ArrayList<>();
		List<SuiaIvSeguimiento> seguimientoL = suiaIvSeguimientoDao.buscaPorIndicador(codigoIndicador);
		if (null != seguimientoL && seguimientoL.size() > 0) {
			for (SuiaIvSeguimiento suiaIvSeguimiento : seguimientoL) {
				seguimientoToL.add(covertirASeguimientoTo(suiaIvSeguimiento));
			}
		}
		return seguimientoToL;
	}

	/**
	 * Convierte a TO el seguimiento
	 * 
	 * @param suiaIvSeguimiento
	 * @return
	 */
	public SeguimientoTo covertirASeguimientoTo(SuiaIvSeguimiento suiaIvSeguimiento) {
		SeguimientoTo seguimientoTo = new SeguimientoTo();
		seguimientoTo.setId(suiaIvSeguimiento.getCodigo());
		if (null != suiaIvSeguimiento.getSuiaIvActividad())
			seguimientoTo
					.setActividad(suiaIvActividadFacade.covertirAActividadTo(suiaIvSeguimiento.getSuiaIvActividad()));
		seguimientoTo.setActividadRealizada(suiaIvSeguimiento.getActividadRealizada());
		seguimientoTo.setDetalleUbicacion(suiaIvSeguimiento.getDetalleUbicacion());
		seguimientoTo.setPorcentajeAvance(suiaIvSeguimiento.getPorcentajeAvance());
		seguimientoTo.setFechaIngreso(suiaIvSeguimiento.getFechaInserta());
		seguimientoTo.setFechaInicio(suiaIvSeguimiento.getFechaInicio());
		seguimientoTo.setFechaFin(suiaIvSeguimiento.getFechaFin());
		seguimientoTo.setEstado(suiaIvSeguimiento.getEstado());
		seguimientoTo.setUsuario(suiaIvSeguimiento.getUsuarioInserta());
		seguimientoTo.setEstadoSeguimiento(EstadoSeguimientoEnum.convertStringToEnum(suiaIvSeguimiento.getEstadoSeguimiento()));
		if (null != suiaIvSeguimiento.getSuiaIvIndicadorActividad()) {
			IndicadorTo  indicadorTo = new IndicadorTo ();
			indicadorTo.setId(suiaIvSeguimiento.getSuiaIvIndicadorActividad().getCodigo());
			indicadorTo.setNombre(suiaIvSeguimiento.getSuiaIvIndicadorActividad().getNombre());
			indicadorTo.setPorcentajeAvance(suiaIvSeguimiento.getSuiaIvIndicadorActividad().getPorcentajeAvance());
			seguimientoTo.setIndicador(indicadorTo);
		}
		return seguimientoTo;
	}

}
