package ec.gob.ambiente.suia.iv.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.gob.ambiente.enlisy.actionplan.enumerador.EstadoRegistroEnum;
import ec.gob.ambiente.suia.dto.AreasProtegidasTo;
import ec.gob.ambiente.suia.iv.dao.SuiaIvAreasProtegidasDao;
import ec.gob.ambiente.suia.iv.model.SuiaIvAreasProtegidas;

/**
 * @author avfp
 *
 */
@Stateless
public class SuiaIvAreasProtegidasFacade {
	
	@EJB
	private SuiaIvAreasProtegidasDao suiaIvAreasProtegidasDao;
	
			 	
	public AreasProtegidasTo guardar(AreasProtegidasTo areasProtegidasTo) throws Exception {
		SuiaIvAreasProtegidas suiaIvAreasProtegidas = new SuiaIvAreasProtegidas();
		suiaIvAreasProtegidas.setNombre(areasProtegidasTo.getNombre());
		suiaIvAreasProtegidas.setDescripcion(areasProtegidasTo.getDescripcion());
		suiaIvAreasProtegidas.setEstado(EstadoRegistroEnum.ACT);
		suiaIvAreasProtegidas.setFechaInserta(new Date());
		suiaIvAreasProtegidas.setUsuarioInserta(areasProtegidasTo.getUsuario());
		suiaIvAreasProtegidasDao.guardar(suiaIvAreasProtegidas);
		areasProtegidasTo.setId(suiaIvAreasProtegidas.getCodigo());
		return areasProtegidasTo;
	}
    
	
	  public void actualizar(AreasProtegidasTo areasProtegidasTo) throws Exception {
		  SuiaIvAreasProtegidas suiaIvAreasProtegidas = suiaIvAreasProtegidasDao.find(areasProtegidasTo.getId());
	    	if (suiaIvAreasProtegidas!=null) {
	    		suiaIvAreasProtegidas.setNombre(areasProtegidasTo.getNombre());
	    		suiaIvAreasProtegidas.setDescripcion(areasProtegidasTo.getDescripcion());
	    		suiaIvAreasProtegidas.setUsuarioActualiza(areasProtegidasTo.getUsuario());
	    		suiaIvAreasProtegidas.setFechaActualiza(new Date());
	    		this.suiaIvAreasProtegidasDao.edit(suiaIvAreasProtegidas);
	    	}
	    	
	    }
	  
    
    public AreasProtegidasTo buscarPorCodigo(AreasProtegidasTo areasProtegidasTo) {
    	return covertirAAreasProtegidasTo(suiaIvAreasProtegidasDao.find(areasProtegidasTo.getId()));
    }
    
    
    public AreasProtegidasTo cambiarEstadoRegistro(AreasProtegidasTo areasProtegidasTo) throws Exception {
    	SuiaIvAreasProtegidas suiaIvAreasProtegidas = suiaIvAreasProtegidasDao.find(areasProtegidasTo.getId());
    	suiaIvAreasProtegidas.setEstado(areasProtegidasTo.getEstado());
    	suiaIvAreasProtegidas.setUsuarioActualiza(areasProtegidasTo.getUsuario());
    	suiaIvAreasProtegidas.setFechaActualiza(new Date());
		return covertirAAreasProtegidasTo(suiaIvAreasProtegidasDao.actualizar(suiaIvAreasProtegidas));
    }
 
    public List<AreasProtegidasTo> buscarAreasProtegidasTodas()throws Exception {
		List<AreasProtegidasTo>  areaProtegidaToL = new ArrayList<>();
		List<SuiaIvAreasProtegidas> suiaIvAreaProtegidaL = suiaIvAreasProtegidasDao.buscarAreasProtegidasTodas();
		for (SuiaIvAreasProtegidas suiaIvInvolucrado : suiaIvAreaProtegidaL) {
			areaProtegidaToL.add(covertirAAreasProtegidasTo(suiaIvInvolucrado));
		}
		return areaProtegidaToL;
	}

	public AreasProtegidasTo covertirAAreasProtegidasTo(SuiaIvAreasProtegidas suiaIvAreasProtegidas) {
    	AreasProtegidasTo areasProtegidasTo = new AreasProtegidasTo();
    	areasProtegidasTo.setId(suiaIvAreasProtegidas.getCodigo());
    	areasProtegidasTo.setNombre(suiaIvAreasProtegidas.getNombre());
    	areasProtegidasTo.setDescripcion(suiaIvAreasProtegidas.getDescripcion());
    	return areasProtegidasTo;
    	
    }
    
}
