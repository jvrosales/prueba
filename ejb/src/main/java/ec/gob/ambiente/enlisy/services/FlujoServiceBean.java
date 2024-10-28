package ec.gob.ambiente.enlisy.services;

import java.util.List;

import javax.ejb.Stateless;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.Flujo;

@Stateless
public class FlujoServiceBean extends AbstractFacade<Flujo, Integer>{
	
	
	public FlujoServiceBean() {
		super(Flujo.class, Integer.class);
	}

	@SuppressWarnings("unchecked")
	public List<Flujo> listarFlujos() {
		List<Flujo> flujos = super.getEntityManager()
				.createQuery("From Flujo f").getResultList();
		return flujos;
	}
	
	public Flujo obtenerFlujo(Integer id) {
		return super.getEntityManager().find(Flujo.class, id);
	}
	
	public Flujo obtenerIdProcess(String nombre) {
//		if(nombre.equals("Biodiversidad.PatentesBiodiversidadMCM")) {
//			nombre = "Biodiversidad.PatentesBiodiversidadMCM";
//		}
		Flujo flujo = (Flujo) super.getEntityManager()
				.createQuery("From Flujo f where f.idProceso = :nombre")
				.setParameter("nombre", nombre).getSingleResult();
		return flujo;
	}

}
