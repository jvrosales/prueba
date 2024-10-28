package ec.gob.ambiente.suia.iv.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ec.gob.ambiente.enlisy.actionplan.enumerador.EstadoRegistroEnum;
import ec.gob.ambiente.suia.dto.IndicadorTo;
import ec.gob.ambiente.suia.iv.dao.SuiaIvIndicadorActividadDao;
import ec.gob.ambiente.suia.iv.model.SuiaIvActividad;
import ec.gob.ambiente.suia.iv.model.SuiaIvIndicadorActividad;

/**
 * @author avfp
 *
 */
@Stateless
public class SuiaIvIndicadorActividadFacade {

	
	@EJB
	private SuiaIvIndicadorActividadDao suiaIvIndicadorActividadDao;

	/**
	 * Guarda el registro
	 * 	
	 * @param indicadorTo
	 * @return
	 * @throws Exception
	 */
	public IndicadorTo guardar(IndicadorTo indicadorTo) throws Exception {
		SuiaIvIndicadorActividad suiaIvIndicadorActividad = new SuiaIvIndicadorActividad();
		suiaIvIndicadorActividad.setNombre(indicadorTo.getNombre());
		SuiaIvActividad suiaIvActividad = new SuiaIvActividad();
		suiaIvActividad.setCodigo(indicadorTo.getActividadTo().getId());
		suiaIvIndicadorActividad.setSuiaIvActividad(suiaIvActividad);
		suiaIvIndicadorActividad.setEstado(EstadoRegistroEnum.ACT);
		suiaIvIndicadorActividad.setFechaInserta(new Date());
		suiaIvIndicadorActividad.setUsuarioInserta(indicadorTo.getUsuario());
		suiaIvIndicadorActividadDao.guardar(suiaIvIndicadorActividad);
		indicadorTo.setId(suiaIvIndicadorActividad.getCodigo());
		return indicadorTo;
	}
    
    /**
     * busca por la clave primaria de la tabla
     * @param indicadorTo
     * @return
     */
    public IndicadorTo buscarPorCodigo(IndicadorTo indicadorTo) {
       	return covertirAIndicadorTo(suiaIvIndicadorActividadDao.find(indicadorTo.getId()));
    }
    
    /**
     * Cambia el estado del registro 
     * 
     * @param indicadorTo
     * @return
     * @throws Exception
     */
    public IndicadorTo cambiarEstadoRegistro(IndicadorTo indicadorTo) throws Exception {
    	SuiaIvIndicadorActividad suiaIvIndicador = suiaIvIndicadorActividadDao.find(indicadorTo.getId());
    	suiaIvIndicador.setEstado(indicadorTo.getEstado());
    	suiaIvIndicador.setUsuarioActualiza(indicadorTo.getUsuario());
    	suiaIvIndicador.setFechaActualiza(new Date());
		return covertirAIndicadorTo(suiaIvIndicadorActividadDao.actualizar(suiaIvIndicador));	
    }
    
    
    /**
     * Busca los idnciadores por objetivo
     * @param codigoObjetivo
     * @return
     * @throws Exception 
     */
    public List<IndicadorTo> buscarIndicadorPorActividad(Long codigoActividad) throws Exception{
    	List<IndicadorTo>  indicadorToL = new ArrayList<>();
    	List<SuiaIvIndicadorActividad> suiaIvIndicadorL = suiaIvIndicadorActividadDao.buscarIndicadorPorActividad(codigoActividad);
		if (null != suiaIvIndicadorL && suiaIvIndicadorL.size()>0) {
			for (SuiaIvIndicadorActividad suiaIvIndicador : suiaIvIndicadorL) {
				indicadorToL.add(covertirAIndicadorTo(suiaIvIndicador));
			}
		}
    	return indicadorToL;
    }
    
    /**
     * Convierte la entidad a TO
     * 
     * @param suiaIvIndicador
     * @return
     */
    public IndicadorTo covertirAIndicadorTo(SuiaIvIndicadorActividad suiaIvIndicador) {
    	IndicadorTo indicadorTo = new IndicadorTo();
    	indicadorTo.setId(suiaIvIndicador.getCodigo());
    	indicadorTo.setNombre(suiaIvIndicador.getNombre());
    	indicadorTo.setPorcentajeAvance(suiaIvIndicador.getPorcentajeAvance());
    	if (null != suiaIvIndicador.getSuiaIvActividad()){
    		indicadorTo.setNombreActividad(suiaIvIndicador.getSuiaIvActividad().getNombre());
    	}
    	
    	return indicadorTo;
    	
    }
    
}
