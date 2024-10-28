package ec.gob.ambiente.enlisy.speciescatalog.model;

import lombok.Getter;
import lombok.Setter;

public class AmbienteInvasion {

	@Getter
	@Setter
	private String nombre;
	
	@Getter
	@Setter
	private String descripcion;
	
	public AmbienteInvasion(String nombre,String descripcion) {
		this.nombre=nombre;
		this.descripcion=descripcion;
	}
}
