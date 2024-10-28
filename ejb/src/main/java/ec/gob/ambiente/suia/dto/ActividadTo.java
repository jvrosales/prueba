package ec.gob.ambiente.suia.dto;

import java.util.List;

import ec.gob.ambiente.enlisy.actionplan.enumerador.EstadoRegistroEnum;
import lombok.Getter;
import lombok.Setter;

public class ActividadTo {

	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private ObjetivoTo objetivo;

	@Getter
	@Setter
	private LineaAccionTo linea;

	@Getter
	@Setter
	private String nombreObjetivo;

	@Getter
	@Setter
	private String nombrePlan;

	@Getter
	@Setter
	private String nombreMeta;

	@Getter
	@Setter
	private String nombre;

	@Getter
	@Setter
	private String indicador;

	@Getter
	@Setter
	private Integer idLinea;

	@Getter
	@Setter
	private String nombreLinea;

	@Getter
	@Setter
	private String usuario;
	
	@Getter
	@Setter
	private Double peso;
	
	@Getter
	@Setter
	private Long codigoPadre;

	@Getter
	@Setter
	private List<SeguimientoTo> seguimientoTo;
	
	@Getter
	@Setter
	private List<IndicadorTo> indicadorTo;
	
	@Getter
	@Setter
	private EstadoRegistroEnum estado;

}
