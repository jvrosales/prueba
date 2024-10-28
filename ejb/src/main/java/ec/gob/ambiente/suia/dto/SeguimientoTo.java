package ec.gob.ambiente.suia.dto;

import java.util.Date;
import java.util.List;

import ec.gob.ambiente.enlisy.actionplan.enumerador.EstadoRegistroEnum;
import ec.gob.ambiente.enlisy.actionplan.enumerador.EstadoSeguimientoEnum;
import lombok.Getter;
import lombok.Setter;

public class SeguimientoTo {

	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private String actividadRealizada;
	
	@Getter
	@Setter
	private String detalleUbicacion;
	
	@Getter
	@Setter
	private Date fechaIngreso;
	
	@Getter
	@Setter
	private Date fechaInicio;
	
	@Getter
	@Setter
	private Double porcentajeAvance;
	
	@Getter
	@Setter
	private Date fechaFin;

	@Getter
	@Setter
	private List<ResponsableTo> responsables;
	
	@Getter
	@Setter
	private List<ProvinciaTo> provincias;

	@Getter
	@Setter
	private List<DocumentoTo> documentos;
	

	@Getter
	@Setter
	private List<PoliticaTo> politicas;


	@Getter
	@Setter
	private List<AreasProtegidasTo> areasProtegidas;
	
	
	@Getter
	@Setter
	private ActividadTo actividad;
	
	@Getter
	@Setter
	private IndicadorTo indicador;
	
	@Getter
	@Setter
	private String detalle;
	
	@Getter
	@Setter
	private EstadoSeguimientoEnum estadoSeguimiento;

	@Getter
	@Setter
	private String usuario;
	
	@Getter
	@Setter
	private EstadoRegistroEnum estado;

	@Getter
	@Setter
	private String objetivoPolitica;
	
	@Getter
	@Setter
	private String resultadoPolitica;

	@Getter
	@Setter
	private String metaPolitica;
	
}
