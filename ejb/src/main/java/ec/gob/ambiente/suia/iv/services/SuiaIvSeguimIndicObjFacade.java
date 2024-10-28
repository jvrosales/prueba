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
import ec.gob.ambiente.enlisy.geneticresources.model.GoalsCatalog;
import ec.gob.ambiente.suia.dto.DocumentoTo;
import ec.gob.ambiente.suia.dto.IndicadorTo;
import ec.gob.ambiente.suia.dto.PoliticaTo;
import ec.gob.ambiente.suia.dto.SeguimientoTo;
import ec.gob.ambiente.suia.iv.dao.GoalsCatalogDao;
import ec.gob.ambiente.suia.iv.dao.SuiaIvIndicadorDao;
import ec.gob.ambiente.suia.iv.dao.SuiaIvSeguimIndicObjDao;
import ec.gob.ambiente.suia.iv.dao.SuiaIvSeguimIndicObjDocDao;
import ec.gob.ambiente.suia.iv.dao.SuiaIvSeguimIndicObjPoliticaDao;
import ec.gob.ambiente.suia.iv.model.SuiaIvIndicador;
import ec.gob.ambiente.suia.iv.model.SuiaIvPlanAccion;
import ec.gob.ambiente.suia.iv.model.SuiaIvSeguimIndicObj;
import ec.gob.ambiente.suia.iv.model.SuiaIvSeguimIndicObjDoc;
import ec.gob.ambiente.suia.iv.model.SuiaIvSeguimIndicObjPolitica;

/**
 * @author avfp
 *
 */
@Stateless
public class SuiaIvSeguimIndicObjFacade {

	@EJB
	private SuiaIvSeguimIndicObjDao suiaIvSeguimIndicObjDao;

	@EJB
	private SuiaIvActividadFacade suiaIvActividadFacade;

	@EJB
	private SuiaIvSeguimIndicObjDocDao suiaIvSeguimIndicObjDocDao;
	
	@EJB
	private SuiaIvSeguimIndicObjPoliticaDao suiaIvSeguimIndicObjPoliticaDao;
	
	@EJB
	private SuiaIvIndicadorDao suiaIvIndicadorDao;
	
	@EJB
	private GoalsCatalogDao goalsCatalogDao;
	
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
		SuiaIvSeguimIndicObj seguimiento = new SuiaIvSeguimIndicObj();
		SuiaIvIndicador suiaIvIndicadorObj = suiaIvIndicadorDao.find(seguimientoTo.getIndicador().getId());
		SuiaIvPlanAccion suiaIvPlanAccion = suiaIvIndicadorObj.getSuiaIvObjetivo().getSuiaIvPlanAccion();
		suiaIvIndicadorObj.setPorcentajeAvance(seguimientoTo.getPorcentajeAvance());
		seguimiento.setSuiaIvIndicador(suiaIvIndicadorObj);
		seguimiento.setActividadRealizada(seguimientoTo.getActividadRealizada());
		seguimiento.setPorcentajeAvance(seguimientoTo.getPorcentajeAvance());
		seguimiento.setEstado(EstadoRegistroEnum.ACT);
		seguimiento.setUsuarioInserta(seguimientoTo.getUsuario());
		seguimiento.setEstadoSeguimiento(seguimientoTo.getEstadoSeguimiento().getCodigo());		
		seguimiento.setFechaInserta(new Date());
		suiaIvSeguimIndicObjDao.guardar(seguimiento);
	
		if (null != seguimientoTo.getDocumentos() ) {
			for (DocumentoTo documentoTo : seguimientoTo.getDocumentos()) {
				SuiaIvSeguimIndicObjDoc seguimientoDocumento = new SuiaIvSeguimIndicObjDoc();
				seguimientoDocumento.setSuiaIvSeguimIndicObj(seguimiento);
				seguimientoDocumento.setEstadoDocumento(documentoTo.getEstadoDocumento().getCodigo());
				seguimientoDocumento.setRutaDocumento(documentoTo.getRuta());
				seguimientoDocumento.setNombreDocumento(documentoTo.getName());
				seguimientoDocumento.setFormatoDocumento(documentoTo.getExtension());
				seguimientoDocumento.setEstado(EstadoRegistroEnum.ACT);
				seguimientoDocumento.setUsuarioInserta(seguimientoTo.getUsuario());
				seguimientoDocumento.setFechaInserta(new Date());
				suiaIvSeguimIndicObjDocDao.guardar(seguimientoDocumento);
				String ruta = "PLAN_CONSERVACION/"+ String.valueOf(suiaIvPlanAccion.getFechaInserta().getYear() + 1900) + "/" + suiaIvPlanAccion.getCodigo().toString() + "/SEGUIMIENTO_OBJETIVO";
				String pathAlfresco = suiaIvDocumentoFacade.guardarDocumentoAlfresco(ruta, documentoTo.getName(), documentoTo.getContent(), "SEGUIMIENTO_OBJETIVO", suiaIvPlanAccion.getCodigo().intValue());
				seguimientoDocumento.setRutaDocumento(pathAlfresco);
				suiaIvSeguimIndicObjDocDao.edit(seguimientoDocumento);
			}
		}

		if ( null != seguimientoTo.getPoliticas()) {
			for (PoliticaTo politicaTo : seguimientoTo.getPoliticas()) {
				SuiaIvSeguimIndicObjPolitica suiaIvSeguimientoPolitica = new SuiaIvSeguimIndicObjPolitica();
				suiaIvSeguimientoPolitica.setSuiaIvSeguimIndicObj(seguimiento);
				GoalsCatalog suiaIvPolitica = new GoalsCatalog();
				suiaIvPolitica.setGocaId(politicaTo.getId());	
				suiaIvSeguimientoPolitica.setSuiaIvPolitica(suiaIvPolitica);			
				suiaIvSeguimientoPolitica.setEstado(EstadoRegistroEnum.ACT);
				suiaIvSeguimientoPolitica.setUsuarioInserta(seguimientoTo.getUsuario());
				suiaIvSeguimientoPolitica.setFechaInserta(new Date());
				suiaIvSeguimIndicObjPoliticaDao.guardar(suiaIvSeguimientoPolitica);
			}
		}
		
		
		seguimientoTo.setId(seguimiento.getCodigo());
		
		suiaIvIndicadorDao.actualizar(suiaIvIndicadorObj);

		return seguimientoTo;
	}

	/**
	 * busca un seguimiento por la clave primaria
	 * 
	 * @param codigo
	 * @return
	 */
	public SeguimientoTo buscarPorCodigo(SeguimientoTo seguimientoTo) throws Exception {
		SuiaIvSeguimIndicObj seguimiento = suiaIvSeguimIndicObjDao.find(seguimientoTo.getId());
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
		SuiaIvSeguimIndicObj seguimiento = suiaIvSeguimIndicObjDao.find(seguimientoTo.getId());
		seguimiento.setEstado(seguimientoTo.getEstado());
		seguimiento.setUsuarioActualiza(seguimientoTo.getUsuario());
		seguimiento.setFechaActualiza(new Date());
		return covertirASeguimientoTo(suiaIvSeguimIndicObjDao.actualizar(seguimiento));
	}

	
	 public List<SeguimientoTo> buscaPorIndicador(Long codigoIndicador)throws Exception {
			List<SeguimientoTo> seguimientoToL = new ArrayList<>();
			List<SuiaIvSeguimIndicObj> seguimientoL = suiaIvSeguimIndicObjDao.buscaPorIndicador(codigoIndicador);
			if (null != seguimientoL && seguimientoL.size() > 0) {
				for (SuiaIvSeguimIndicObj suiaIvSeguimiento : seguimientoL) {
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
	public SeguimientoTo covertirASeguimientoTo(SuiaIvSeguimIndicObj suiaIvSeguimiento) {
		SeguimientoTo seguimientoTo = new SeguimientoTo();
		seguimientoTo.setId(suiaIvSeguimiento.getCodigo());
		seguimientoTo.setActividadRealizada(suiaIvSeguimiento.getActividadRealizada());
		seguimientoTo.setPorcentajeAvance(suiaIvSeguimiento.getPorcentajeAvance());
		seguimientoTo.setFechaIngreso(suiaIvSeguimiento.getFechaInserta());
		seguimientoTo.setEstadoSeguimiento(EstadoSeguimientoEnum.convertStringToEnum(suiaIvSeguimiento.getEstadoSeguimiento()));
		if (null != suiaIvSeguimiento.getSuiaIvIndicador()){
			IndicadorTo  indicadorTo = new IndicadorTo ();
			indicadorTo.setId(suiaIvSeguimiento.getSuiaIvIndicador().getCodigo());
			indicadorTo.setNombre(suiaIvSeguimiento.getSuiaIvIndicador().getNombre());
			seguimientoTo.setIndicador(indicadorTo);
		}
		
		if (null != suiaIvSeguimiento.getSuiaIvSeguimIndicObjPolitica()){
			PoliticaTo politicaTo = new PoliticaTo();
			politicaTo.setId(suiaIvSeguimiento.getSuiaIvSeguimIndicObjPolitica().get(0).getSuiaIvPolitica().getGocaId());
			politicaTo.setNombre(suiaIvSeguimiento.getSuiaIvSeguimIndicObjPolitica().get(0).getSuiaIvPolitica().getGocaDescription());
			List<PoliticaTo> politicaToL = new ArrayList<>();
			seguimientoTo.setPoliticas(politicaToL);
			
			seguimientoTo.setMetaPolitica(suiaIvSeguimiento.getSuiaIvSeguimIndicObjPolitica().get(0).getSuiaIvPolitica().getGocaDescription());
			
			GoalsCatalog resultadoPolitica = goalsCatalogDao.findByCode(suiaIvSeguimiento.getSuiaIvSeguimIndicObjPolitica().get(0).getSuiaIvPolitica().getGocaCodeParent(), false);
			if (null != resultadoPolitica) {
				seguimientoTo.setResultadoPolitica(resultadoPolitica.getGocaDescription());
			}
			
			GoalsCatalog objetivoPolitica = goalsCatalogDao.findByCode(resultadoPolitica.getGocaCodeParent(), false);
			if (null != objetivoPolitica) {
				seguimientoTo.setObjetivoPolitica(objetivoPolitica.getGocaDescription());
			}

		}
		
		return seguimientoTo;
	}

}
