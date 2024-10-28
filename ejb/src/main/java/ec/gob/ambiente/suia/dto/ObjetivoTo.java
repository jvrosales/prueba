package ec.gob.ambiente.suia.dto;

import java.util.List;

import ec.gob.ambiente.enlisy.actionplan.enumerador.EstadoRegistroEnum;
import lombok.Getter;
import lombok.Setter;

public class ObjetivoTo {
	
	@Getter
	@Setter
	private Long id;
	
	@Getter
	@Setter
	private String nombre;
	
	@Getter
	@Setter
	private List<IndicadorTo> indicadores;

	@Getter
	@Setter
	private List<ActividadTo> actividades;
	
	@Getter
	@Setter
	private MetaTo meta;


	@Getter
	@Setter
	private String usuario;

	@Getter
	@Setter
	private EstadoRegistroEnum estado;
	
}
