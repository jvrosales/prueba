package ec.gob.ambiente.enlisy.actionplan.enumerador;

import lombok.Getter;

public enum EstadoRegistroEnum {
	
	ACT("ACTIVO"),
	INA("INACTIVO"),
	ELI("ELIMINADO");	
	
	@Getter
    private String descripcion;
	
	private EstadoRegistroEnum(String descripcion) {
	       this.descripcion = descripcion;
	}
	
}