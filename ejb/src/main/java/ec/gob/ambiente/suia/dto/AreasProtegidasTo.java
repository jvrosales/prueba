package ec.gob.ambiente.suia.dto;

import ec.gob.ambiente.enlisy.actionplan.enumerador.EstadoRegistroEnum;
import lombok.Getter;
import lombok.Setter;

public class AreasProtegidasTo {
	
	@Getter
	@Setter
	private Long id;
	
	@Getter
	@Setter
	private String nombre;
	
	@Getter
	@Setter
	private String descripcion;
	
	@Getter
	@Setter
	private String usuario;

	@Getter
	@Setter
	private EstadoRegistroEnum estado;
}