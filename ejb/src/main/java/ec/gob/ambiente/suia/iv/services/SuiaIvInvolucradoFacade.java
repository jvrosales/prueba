package ec.gob.ambiente.suia.iv.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.gob.ambiente.enlisy.actionplan.enumerador.EstadoRegistroEnum;
import ec.gob.ambiente.enlisy.actionplan.enumerador.TipoInvolucradoEnum;
import ec.gob.ambiente.suia.dto.ResponsableTo;
import ec.gob.ambiente.suia.iv.dao.SuiaIvInvolucradoDao;
import ec.gob.ambiente.suia.iv.model.SuiaIvInvolucrado;

/**
 * @author avfp
 *
 */
@Stateless
public class SuiaIvInvolucradoFacade {

	
	@EJB
	private SuiaIvInvolucradoDao suiaIvInvolucradoDao;
	
			 	
	public ResponsableTo guardar(ResponsableTo responsableTo) throws Exception {
		SuiaIvInvolucrado involucrado = new SuiaIvInvolucrado();
		involucrado.setTipo(responsableTo.getTipo().getCodigo());
		involucrado.setNombre(responsableTo.getNombre());
		involucrado.setSiglas(responsableTo.getSiglas());
		involucrado.setRuc(responsableTo.getRuc());
		involucrado.setEstado(EstadoRegistroEnum.ACT);
		involucrado.setFechaInserta(new Date());
		involucrado.setUsuarioInserta(responsableTo.getUsuario());
		suiaIvInvolucradoDao.guardar(involucrado);
		responsableTo.setId(involucrado.getCodigo());
		return responsableTo;
	}
    
	
	public List<ResponsableTo> buscarInvolucradosActivos()throws Exception {
		List<ResponsableTo>  responsableToL = new ArrayList<>();
		List<SuiaIvInvolucrado> suiaIvInvolucradoL = suiaIvInvolucradoDao.buscarInvolucradosActivos();
		for (SuiaIvInvolucrado suiaIvInvolucrado : suiaIvInvolucradoL) {
			responsableToL.add(covertirAResponsableTo(suiaIvInvolucrado));
		}
		return responsableToL;
	}
	
	public List<ResponsableTo> buscarInvolucradosTodos()throws Exception {
		List<ResponsableTo>  responsableToL = new ArrayList<>();
		List<SuiaIvInvolucrado> suiaIvInvolucradoL = suiaIvInvolucradoDao.buscarInvolucradosTodos();
		for (SuiaIvInvolucrado suiaIvInvolucrado : suiaIvInvolucradoL) {
			responsableToL.add(covertirAResponsableTo(suiaIvInvolucrado));
		}
		return responsableToL;
	}
    
    public ResponsableTo covertirAResponsableTo(SuiaIvInvolucrado suiaIvInvolucrado) throws Exception {
    	ResponsableTo responsableTo = new ResponsableTo();
    	responsableTo.setId(suiaIvInvolucrado.getCodigo());
    	responsableTo.setNombre(suiaIvInvolucrado.getNombre());
    	responsableTo.setRuc(suiaIvInvolucrado.getRuc());
    	responsableTo.setSiglas(suiaIvInvolucrado.getSiglas());
    	responsableTo.setTipo(TipoInvolucradoEnum.convertStringToEnum(suiaIvInvolucrado.getTipo()));
    	responsableTo.setUsuario(suiaIvInvolucrado.getUsuarioInserta());
    	responsableTo.setEstado(suiaIvInvolucrado.getEstado());
    	return responsableTo;
    }
    
    public void actualizarEstadoRegistro(Long id, EstadoRegistroEnum estado) throws Exception {
    	SuiaIvInvolucrado involucrado = suiaIvInvolucradoDao.find(id);
    	if (involucrado!=null) {
    		involucrado.setEstado(estado);
    		this.suiaIvInvolucradoDao.edit(involucrado);
    	}
    	
    }
    
    public void actualizar(ResponsableTo inv) throws Exception {
    	SuiaIvInvolucrado involucrado = suiaIvInvolucradoDao.find(inv.getId());
    	if (involucrado!=null) {
    		involucrado.setNombre(inv.getNombre());
    		involucrado.setRuc(inv.getRuc());
    		involucrado.setSiglas(inv.getSiglas());
    		involucrado.setUsuarioActualiza(inv.getUsuario());
    		involucrado.setFechaActualiza(new Date());
    		this.suiaIvInvolucradoDao.edit(involucrado);
    	}
    	
    }
    
}
