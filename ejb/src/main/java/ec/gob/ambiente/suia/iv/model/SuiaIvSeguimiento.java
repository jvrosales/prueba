package ec.gob.ambiente.suia.iv.model;

import java.io.Serializable;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ec.gob.ambiente.enlisy.actionplan.enumerador.EstadoRegistroEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.NamedQuery;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the "SUIA_IV_SEGUIMIENTO" database table.
 * 
 */
@Entity
@Table(name="suia_iv_seguimiento", schema = "suia_iv")
@NamedQuery(name="SuiaIvSeguimiento.findAll", query="SELECT s FROM SuiaIvSeguimiento s")
public class SuiaIvSeguimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@SequenceGenerator(name="seq_suia_iv_seguimiento" ,initialValue = 1, sequenceName = "seq_suia_iv_seguimiento", schema = "suia_iv",  allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_suia_iv_seguimiento")
	@Column(name="codigo_seguimiento")
	private Long codigo;

	@Getter
	@Setter
	@Column(name="actividad_realizada")
	private String actividadRealizada;

	@Getter
	@Setter
	@Column(name="detalle_ubicacion")
	private String detalleUbicacion;

	@Getter
	@Setter
	@Column(name="estado")
	@Enumerated(EnumType.STRING)
	private EstadoRegistroEnum estado;

	@Getter
	@Setter
	@Column(name="fecha_actualiza")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaActualiza;

	@Getter
	@Setter
	@Column(name="fecha_fin")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaFin;

	@Getter
	@Setter
	@Column(name="fecha_inicio")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaInicio;

	@Getter
	@Setter
	@Column(name="fecha_inserta")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaInserta;

	@Getter
	@Setter
	@Column(name="porcentaje_avance")
	private Double porcentajeAvance;

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
	@Column(name="estado_seguimiento")
	private String estadoSeguimiento;

	@Getter
	@Setter
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_actividad")
	private SuiaIvActividad suiaIvActividad;
	
	@Getter
	@Setter
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_indicador_act")
	private SuiaIvIndicadorActividad suiaIvIndicadorActividad;

	@Getter
	@Setter
	@OneToMany(mappedBy="suiaIvSeguimiento", fetch=FetchType.LAZY)
	private List<SuiaIvSeguimientoDocumento> suiaIvSeguimientoDocumentos;

	@Getter
	@Setter
	@OneToMany(mappedBy="suiaIvSeguimiento", fetch=FetchType.LAZY)
	private List<SuiaIvSeguimientoInvolucrado> suiaIvSeguimientoInvolucrados;

	@Getter
	@Setter
	@OneToMany(mappedBy="suiaIvSeguimiento", fetch=FetchType.LAZY)
	private List<SuiaIvSeguimientoProvincia> suiaIvSeguimientoProvincias;
	
	
	@Getter
	@Setter
	@OneToMany(mappedBy="suiaIvSeguimiento", fetch=FetchType.LAZY)
	private List<SuiaIvSeguimientoAreaProtegida> suiaIvSeguimientoAreaProtegida;
	

	public SuiaIvSeguimiento() {
	}

}