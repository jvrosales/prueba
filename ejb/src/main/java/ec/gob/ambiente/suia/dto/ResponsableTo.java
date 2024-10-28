package ec.gob.ambiente.suia.dto;

import ec.gob.ambiente.enlisy.actionplan.enumerador.EstadoRegistroEnum;
import ec.gob.ambiente.enlisy.actionplan.enumerador.TipoInvolucradoEnum;
import lombok.Getter;
import lombok.Setter;

public class ResponsableTo {

	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private String nombre;
	
	@Getter
	@Setter
	private String siglas;
	
	@Getter
	@Setter
	private String ruc;
	
	@Getter
	@Setter
	private TipoInvolucradoEnum tipo;

	@Getter
	@Setter
	private EstadoRegistroEnum estado;
	
	@Getter
	@Setter
	private String usuario;

}
