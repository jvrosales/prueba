package ec.gob.ambiente.suia.iv.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;

import ec.gob.ambiente.enlisy.actionplan.enumerador.EstadoRegistroEnum;
import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the "SUIA_IV_PLAN_ACCION" database table.
 * 
 */
@Entity
@Table(name="suia_iv_plan_accion", schema = "suia_iv")
@DynamicUpdate
public class SuiaIvPlanAccion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@SequenceGenerator(name="seq_suia_iv_plan_accion" ,initialValue = 1, sequenceName = "seq_suia_iv_plan_accion", schema = "suia_iv", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_suia_iv_plan_accion")
	@Column(name="codigo_plan_accion")
	private Long codigo;

	@Getter
	@Setter
	@Column(name="codigo_especie_grupo")
	private Integer codigoEspecieGrupo;

	@Getter
	@Setter
	@Column(name="codigo_grupo_filtro_uno")
	private Integer codigoGrupoFiltroUno;

	@Getter
	@Setter
	@Column(name="codigo_grupo_filtro_dos")
	private Integer codigoGrupoFiltroDos;

	@Getter
	@Setter
	@Column(name="codigo_reino")
	private Integer codigoReino;

	@Getter
	@Setter
	@Column(name="tipo_plan")
	private String tipoPlan;

	@Getter
	@Setter
	@Column(name="estado_plan")
	private String estadoPlan;

	@Getter
	@Setter
	@Column(name="fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	@Getter
	@Setter
	@Column(name="fecha_actualiza")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaActualiza;

	@Getter
	@Setter
	@Column(name="fecha_inserta")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaInserta;

	@Getter
	@Setter
	@Column(name="nombre")
	private String nombre;

	@Getter
	@Setter
	@Column(name="estado")
	@Enumerated(EnumType.STRING)
	private EstadoRegistroEnum estado;

	@Getter
	@Setter
	@Column(name="ruta_documento")
	private String rutaDocumento;	
	
	@Getter
	@Setter
	@Column(name="nombre_documento")
	private String nombreDocumento;
	
	@Getter
	@Setter
	@Column(name="formato_documento")
	private String formatoDocumento;
	
	@Getter
	@Setter
	@Column(name="spta_scientific_gui")
	private String sptaScientificGui;

	@Getter
	@Setter
	@Column(name="usuario_actualiza")
	private String usuarioActualiza;

	@Getter
	@Setter
	@Column(name="usuario_inserta")
	private String usuarioInserta;

	@Getter
	@Setter
	@OneToMany(mappedBy="suiaIvPlanAccion", fetch = FetchType.LAZY)
	private List<SuiaIvMeta> suiaIvMetas;

	@Getter
	@Setter
	@OneToMany(mappedBy="suiaIvPlanAccion", fetch = FetchType.LAZY)
	private List<SuiaIvObjetivo> suiaIvObjetivos;

	@Getter
	@Setter
	@Transient
	private String nombreComunEspecie;
	
	@Getter
	@Setter
	@Transient
	private String nombreCientificoEspecie;
	

	@Getter
	@Setter
	@Transient
	private String nombeGrupoFiltroUno;
	
	
	@Getter
	@Setter
	@Transient
	private String nombeGrupoFiltroDos;
	
	@Getter
	@Setter
	@Transient
	private String nombreReino;
	
	
	
	public SuiaIvPlanAccion() {
	}


}
