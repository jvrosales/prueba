package ec.gob.ambiente.suia.dto;

import ec.gob.ambiente.enlisy.actionplan.enumerador.EstadoRegistroEnum;
import lombok.Getter;
import lombok.Setter;

public class IndicadorTo {
	
	@Getter
	@Setter
	private Long id;
	
	@Getter
	@Setter
	private String nombre;
	
	
	@Getter
	@Setter
	private String medioVerificacion;


	@Getter
	@Setter
	private String usuario;
	
	@Getter
	@Setter
	private PlanAccionTo planAccionTo;
	
	@Getter
	@Setter
	private ObjetivoTo objetivoTo;
	
	@Getter
	@Setter
	private ActividadTo actividadTo;
	
	@Getter
	@Setter
	private String nombreActividad;
	
	@Getter
	@Setter
	private Double porcentajeAvance;
	
	@Getter
	@Setter
	private EstadoRegistroEnum estado;
}
