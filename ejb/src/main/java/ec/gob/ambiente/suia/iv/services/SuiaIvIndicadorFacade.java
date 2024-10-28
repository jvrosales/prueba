package ec.gob.ambiente.suia.iv.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import ec.gob.ambiente.enlisy.actionplan.enumerador.EstadoRegistroEnum;
import ec.gob.ambiente.suia.dto.IndicadorTo;
import ec.gob.ambiente.suia.iv.dao.SuiaIvIndicadorDao;
import ec.gob.ambiente.suia.iv.model.SuiaIvIndicador;
import ec.gob.ambiente.suia.iv.model.SuiaIvObjetivo;

/**
 * @author avfp
 *
 */
@Stateless
public class SuiaIvIndicadorFacade {

	
	@EJB
	private SuiaIvIndicadorDao suiaIvIndicadorDao;
	
	@EJB
	private SuiaIvObjetivoFacade suiaIvObjetivoFacade;
	
	/**
	 * Guarda el registro
	 * 	
	 * @param indicadorTo
	 * @return
	 * @throws Exception
	 */
	public IndicadorTo guardar(IndicadorTo indicadorTo) throws Exception {
		SuiaIvIndicador suiaIvIndicador = new SuiaIvIndicador();
		suiaIvIndicador.setNombre(indicadorTo.getNombre());
		suiaIvIndicador.setMedioVerificacion(indicadorTo.getMedioVerificacion());
		SuiaIvObjetivo suiaIvObjetivo = new SuiaIvObjetivo();
		suiaIvObjetivo.setCodigo(indicadorTo.getObjetivoTo().getId());
		suiaIvIndicador.setSuiaIvObjetivo(suiaIvObjetivo);
		suiaIvIndicador.setEstado(EstadoRegistroEnum.ACT);
		suiaIvIndicador.setFechaInserta(new Date());
		suiaIvIndicador.setUsuarioInserta(indicadorTo.getUsuario());
		suiaIvIndicadorDao.guardar(suiaIvIndicador);
		indicadorTo.setId(suiaIvIndicador.getCodigo());
		return indicadorTo;
	}
    
    /**
     * busca por la clave primaria de la tabla
     * @param indicadorTo
     * @return
     */
    public IndicadorTo buscarPorCodigo(IndicadorTo indicadorTo) {
       	return covertirAIndicadorTo(suiaIvIndicadorDao.find(indicadorTo.getId()));
    }
    
    /**
     * Cambia el estado del registro 
     * 
     * @param indicadorTo
     * @return
     * @throws Exception
     */
    public IndicadorTo cambiarEstadoRegistro(IndicadorTo indicadorTo) throws Exception {
    	SuiaIvIndicador suiaIvIndicador = suiaIvIndicadorDao.find(indicadorTo.getId());
    	suiaIvIndicador.setEstado(indicadorTo.getEstado());
    	suiaIvIndicador.setUsuarioActualiza(indicadorTo.getUsuario());
    	suiaIvIndicador.setFechaActualiza(new Date());
		return covertirAIndicadorTo(suiaIvIndicadorDao.actualizar(suiaIvIndicador));	
    }
    
    
    /**
     * Busca los idnciadores por objetivo
     * @param codigoObjetivo
     * @return
     * @throws Exception 
     */
    public List<IndicadorTo> buscarIndicadorPorObjetivo(Long codigoObjetivo) throws Exception{
    	List<IndicadorTo>  indicadorToL = new ArrayList<>();
    	List<SuiaIvIndicador> suiaIvIndicadorL = suiaIvIndicadorDao.buscarIndicadorPorObjetivo(codigoObjetivo);
		if (null != suiaIvIndicadorL && suiaIvIndicadorL.size()>0) {
			for (SuiaIvIndicador suiaIvIndicador : suiaIvIndicadorL) {
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
    public IndicadorTo covertirAIndicadorTo(SuiaIvIndicador suiaIvIndicador) {
    	IndicadorTo indicadorTo = new IndicadorTo();
    	indicadorTo.setId(suiaIvIndicador.getCodigo());
    	indicadorTo.setNombre(suiaIvIndicador.getNombre());
    	indicadorTo.setMedioVerificacion(suiaIvIndicador.getMedioVerificacion());
		indicadorTo.setPorcentajeAvance(suiaIvIndicador.getPorcentajeAvance());
    	if(null != suiaIvIndicador.getSuiaIvObjetivo()) 
    		indicadorTo.setObjetivoTo(suiaIvObjetivoFacade.covertirAObjetivoTo(suiaIvIndicador.getSuiaIvObjetivo()));
    	
    	return indicadorTo;
    	
    }
    
}
