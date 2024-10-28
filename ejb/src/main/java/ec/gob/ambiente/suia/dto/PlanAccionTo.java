package ec.gob.ambiente.suia.dto;

import java.util.Date;
import java.util.List;

import ec.gob.ambiente.enlisy.actionplan.enumerador.EstadoPlanAccionEnum;
import ec.gob.ambiente.enlisy.actionplan.enumerador.EstadoRegistroEnum;
import ec.gob.ambiente.enlisy.actionplan.enumerador.TipoPlanAccionEnum;
import lombok.Getter;
import lombok.Setter;

public class PlanAccionTo {
	
	@Getter
	@Setter
	private Long id;
	
	@Getter
	@Setter
	private String nombre;
	
	@Getter
	@Setter
	private Date fechaIngreso;
	
	@Getter
	@Setter
	private List<MetaTo> metas;
	
	@Getter
	@Setter
	private String usuario;
	
	@Getter
	@Setter
	private Date fechaModificacion;
	
	@Getter
	@Setter
	private EstadoPlanAccionEnum estadoPlan;
	
	@Getter
	@Setter
	private TipoPlanAccionEnum tipo;
	
	@Getter
	@Setter
	private Integer codigoEspecieGrupo;

	@Getter
	@Setter
	private String sptaScientificGui;
	
	@Getter
	@Setter
	private String rutaDocumento;
	
	@Getter
	@Setter
	private String nombreDocumento;
	
	@Getter
	@Setter
	private String extensionDocumento;
	
	@Getter
	@Setter
	private EstadoRegistroEnum estado;
	
	@Getter
	@Setter
	private String nombeComunEspecie;
	
	@Getter
	@Setter
	private String nombreCientificoEspecie;
	
	@Getter
	@Setter
	private Integer codigoGrupoFiltroUno;
	
	@Getter
	@Setter
	private String nombeGrupoFiltroUno;
	
	@Getter
	@Setter
	private Integer codigoGrupoFiltroDos;
	
	@Getter
	@Setter
	private String nombeGrupoFiltroDos;
	
	@Getter
	@Setter
	private Integer codigoReino;
	
	@Getter
	@Setter
	private String nombeReino;
	
	@Getter
	@Setter
	private byte[] content;
	

}
