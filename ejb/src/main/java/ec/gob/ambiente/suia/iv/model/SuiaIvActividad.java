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

@Entity
@Table(name="suia_iv_actividad", schema = "suia_iv")
@NamedQuery(name="SuiaIvActividad.findAll", query="SELECT s FROM SuiaIvActividad s")
public class SuiaIvActividad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@SequenceGenerator(name="seq_suia_iv_actividad" ,initialValue = 1, sequenceName = "seq_suia_iv_actividad", schema = "suia_iv", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_suia_iv_actividad")
	@Column(name="codigo_actividad")
	private Long codigo;

	@Getter
	@Setter
	@Column(name="estado")
	@Enumerated(EnumType.STRING)
	private EstadoRegistroEnum  estado;

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
	@Column(name="usuario_actualiza")
	private String usuarioActualiza;
	
	@Getter
	@Setter
	@Column(name="usuario_inserta")
	private String usuarioInserta;

	@Getter
	@Setter
	@Column(name="peso")
	private Double peso;
	
	@Getter
	@Setter
	@Column(name="codigo_actividad_padre")
	private Long codigoPadre;
	
	
	@Getter
	@Setter
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_linea_accion")
	private SuiaIvLineaAccion suiaIvLineaAccion;
	
	@Getter
	@Setter
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_objetivo")
	private SuiaIvObjetivo suiaIvObjetivo;

	@Getter
	@Setter
	@OneToMany(mappedBy="suiaIvActividad", fetch=FetchType.LAZY)
	private List<SuiaIvSeguimiento> suiaIvSeguimientos;

	@Getter
	@Setter
	@OneToMany(mappedBy="suiaIvActividad", fetch=FetchType.LAZY)
	private List<SuiaIvIndicadorActividad> suiaIvIndicadorActividad;
	
	
	public SuiaIvActividad() {
	}

}