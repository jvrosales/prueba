package ec.gob.ambiente.suia.iv.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.gob.ambiente.enlisy.actionplan.enumerador.EstadoRegistroEnum;
import ec.gob.ambiente.suia.dto.LineaAccionTo;
import ec.gob.ambiente.suia.iv.dao.SuiaIvLineaAccionDao;
import ec.gob.ambiente.suia.iv.model.SuiaIvLineaAccion;

/**
 * @author avfp
 *
 */
@Stateless
public class SuiaIvLineaAccionFacade {

	@EJB
	private SuiaIvLineaAccionDao suiaIvLineaAccionDao;

	/**
	 * Lista las lineas de accion activas
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<LineaAccionTo> buscarLineaAccionActivas() throws Exception {
		List<LineaAccionTo> lineaAccionToL = new ArrayList<>();
		List<SuiaIvLineaAccion> suiaIvLineaAccionL = suiaIvLineaAccionDao.buscarLineaAccionActivas();
		if (null != suiaIvLineaAccionL && suiaIvLineaAccionL.size() > 0) {
			for (SuiaIvLineaAccion suiaIvLineaAccion : suiaIvLineaAccionL) {
				lineaAccionToL.add(covertirALineaAccionTo(suiaIvLineaAccion));
			}

		}
		return lineaAccionToL;
	}

	public void actualizarEstadoRegistro(Long id, EstadoRegistroEnum estado) throws Exception {
		SuiaIvLineaAccion involucrado = suiaIvLineaAccionDao.find(id);
		if (involucrado != null) {
			involucrado.setEstado(estado);
			this.suiaIvLineaAccionDao.edit(involucrado);
		}

	}

	public void actualizar(LineaAccionTo inv) throws Exception {
		SuiaIvLineaAccion involucrado = suiaIvLineaAccionDao.find(inv.getId());
		if (involucrado != null) {
			involucrado.setNombre(inv.getNombre());
			involucrado.setUsuarioActualiza(inv.getUsuario());
			involucrado.setFechaActualiza(new Date());
			this.suiaIvLineaAccionDao.edit(involucrado);
		}

	}
	
	public LineaAccionTo guardar(LineaAccionTo responsableTo) throws Exception {
		SuiaIvLineaAccion involucrado = new SuiaIvLineaAccion();
		involucrado.setNombre(responsableTo.getNombre());
		involucrado.setEstado(EstadoRegistroEnum.ACT);
		involucrado.setFechaInserta(new Date());
		involucrado.setUsuarioInserta(responsableTo.getUsuario());
		suiaIvLineaAccionDao.guardar(involucrado);
		responsableTo.setId(involucrado.getCodigo());
		return responsableTo;
	}

	public LineaAccionTo covertirALineaAccionTo(SuiaIvLineaAccion suiaIvLineaAccion) {
		LineaAccionTo lineaAccionTo = new LineaAccionTo();
		lineaAccionTo.setId(suiaIvLineaAccion.getCodigo());
		lineaAccionTo.setNombre(suiaIvLineaAccion.getNombre());
		lineaAccionTo.setEstado(suiaIvLineaAccion.getEstado());
		return lineaAccionTo;

	}

}
