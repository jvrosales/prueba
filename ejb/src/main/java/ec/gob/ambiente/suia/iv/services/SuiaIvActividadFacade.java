package ec.gob.ambiente.suia.iv.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.gob.ambiente.enlisy.actionplan.enumerador.EstadoRegistroEnum;
import ec.gob.ambiente.suia.dto.ActividadTo;
import ec.gob.ambiente.suia.dto.IndicadorTo;
import ec.gob.ambiente.suia.iv.dao.SuiaIvActividadDao;
import ec.gob.ambiente.suia.iv.dao.SuiaIvMetaDao;
import ec.gob.ambiente.suia.iv.model.SuiaIvActividad;
import ec.gob.ambiente.suia.iv.model.SuiaIvIndicadorActividad;
import ec.gob.ambiente.suia.iv.model.SuiaIvLineaAccion;
import ec.gob.ambiente.suia.iv.model.SuiaIvMeta;
import ec.gob.ambiente.suia.iv.model.SuiaIvObjetivo;

/**
 * @author avfp
 *
 */
@Stateless
public class SuiaIvActividadFacade {
	
	@EJB
	private SuiaIvActividadDao suiaIvActividadDao;
	
	@EJB
	private SuiaIvObjetivoFacade suiaIvObjetivoFacade;
	
	@EJB
	private SuiaIvMetaDao suiaIvMetaDao;
			 	
	public ActividadTo guardar(ActividadTo actividadTo) throws Exception {
		SuiaIvActividad suiaIvActividad = new SuiaIvActividad();
		suiaIvActividad.setNombre(actividadTo.getNombre());
		SuiaIvObjetivo suiaIvObjetivo = new SuiaIvObjetivo();
		suiaIvObjetivo.setCodigo(actividadTo.getObjetivo().getId());
		suiaIvActividad.setSuiaIvObjetivo(suiaIvObjetivo);	
		SuiaIvLineaAccion suiaIvLineaAccion = new SuiaIvLineaAccion();
		suiaIvLineaAccion.setCodigo(actividadTo.getLinea().getId());
		suiaIvActividad.setSuiaIvLineaAccion(suiaIvLineaAccion);
		suiaIvActividad.setEstado(EstadoRegistroEnum.ACT);
		suiaIvActividad.setFechaInserta(new Date());
		suiaIvActividad.setUsuarioInserta(actividadTo.getUsuario());
		if(null != actividadTo.getCodigoPadre() && !actividadTo.getCodigoPadre().toString().isEmpty()) {
			suiaIvActividad.setPeso(actividadTo.getPeso());
			suiaIvActividad.setCodigoPadre(actividadTo.getCodigoPadre());
		}
		else
			suiaIvActividad.setPeso(Double.valueOf(100));
		
		suiaIvActividadDao.guardar(suiaIvActividad);
		actividadTo.setId(suiaIvActividad.getCodigo());
		return actividadTo;
	}
    
    
    public ActividadTo buscarPorCodigo(ActividadTo actividadTo) {
    	return covertirAActividadTo(suiaIvActividadDao.find(actividadTo.getId()));
    }
    
    
    public ActividadTo cambiarEstadoRegistro(ActividadTo actividadTo) throws Exception {
    	SuiaIvActividad suiaIvActividad = suiaIvActividadDao.find(actividadTo.getId());
    	suiaIvActividad.setEstado(actividadTo.getEstado());
    	suiaIvActividad.setUsuarioActualiza(actividadTo.getUsuario());
    	suiaIvActividad.setFechaActualiza(new Date());
		return covertirAActividadTo(suiaIvActividadDao.actualizar(suiaIvActividad));	
    }
    
    
    public List<ActividadTo> buscarActividadPorObjetivo(Long codigoObjetivo) throws Exception{
    	List<ActividadTo>  actividadTo = new ArrayList<>();
    	List<SuiaIvActividad>  suiaIvIndicadorL = suiaIvActividadDao.buscarActividadPorObjetivo(codigoObjetivo);
    	if(null != suiaIvIndicadorL && suiaIvIndicadorL.size()>0) {
    		for (SuiaIvActividad suiaIvActividad : suiaIvIndicadorL) {
    			actividadTo.add(covertirAActividadTo(suiaIvActividad));
    		}
    	}
    	return actividadTo;
    }

    
    public ActividadTo covertirAActividadTo(SuiaIvActividad suiaIvActividad) {
    	ActividadTo actividadTo = new ActividadTo();
    	actividadTo.setId(suiaIvActividad.getCodigo());
    	actividadTo.setNombre(suiaIvActividad.getNombre());
    	if(null != suiaIvActividad.getSuiaIvIndicadorActividad()) {
    		List<IndicadorTo> indicadorToL = new ArrayList<>();
    		for (SuiaIvIndicadorActividad indicadorActividad : suiaIvActividad.getSuiaIvIndicadorActividad()) {
    			IndicadorTo indicadorTo = new IndicadorTo();    			
    			indicadorTo.setId(indicadorActividad.getCodigo());
    			indicadorTo.setNombre(indicadorActividad.getNombre());
    			indicadorToL.add(indicadorTo);
			}
    		actividadTo.setIndicadorTo(indicadorToL);    	}
    	
    	if (null != suiaIvActividad.getSuiaIvObjetivo()) {
    		actividadTo.setObjetivo(suiaIvObjetivoFacade.covertirAObjetivoTo(suiaIvActividad.getSuiaIvObjetivo()));
    		actividadTo.setNombreObjetivo(suiaIvActividad.getSuiaIvObjetivo().getNombre());
    		List<SuiaIvMeta>  suiaIvMetaL = suiaIvMetaDao.buscarMetaPorObjetivo(suiaIvActividad.getSuiaIvObjetivo().getCodigo());
    		if(null != suiaIvMetaL && suiaIvMetaL.size()>0) {
    			SuiaIvMeta suiaIvMeta = 	suiaIvMetaL.get(0);
    			actividadTo.setNombreMeta(suiaIvMeta.getNombre());
    			actividadTo.setNombrePlan(suiaIvMeta.getSuiaIvPlanAccion().getNombre());
    		}
    	}

    	actividadTo.setNombreLinea(suiaIvActividad.getSuiaIvLineaAccion().getNombre());
    	return actividadTo;
    	
    }
    
}
