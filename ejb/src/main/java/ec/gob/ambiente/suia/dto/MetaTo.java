package ec.gob.ambiente.suia.dto;

import java.util.List;
import ec.gob.ambiente.enlisy.actionplan.enumerador.EstadoRegistroEnum;
import lombok.Getter;
import lombok.Setter;

public class MetaTo {
	
	@Getter
	@Setter
	private Long id;
	
	@Getter
	@Setter
	private String nombre;
	
	@Getter
	@Setter
	private List<ObjetivoTo> objetivos;

	
	@Getter
	@Setter
	private PlanAccionTo planAccionTo;
	
	@Getter
	@Setter
	private String usuario;
	
	@Getter
	@Setter
	private EstadoRegistroEnum estado;
	
}
