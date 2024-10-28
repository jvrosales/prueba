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
@Table(name="suia_iv_objetivo",  schema = "suia_iv")
@NamedQuery(name="SuiaIvObjetivo.findAll", query="SELECT s FROM SuiaIvObjetivo s")
public class SuiaIvObjetivo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	@Id
	@SequenceGenerator(name="seq_suia_iv_objetivo" ,initialValue = 1, sequenceName = "seq_suia_iv_objetivo", schema = "suia_iv",  allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_suia_iv_objetivo")
	@Column(name="codigo_objetivo")
	private Long codigo;

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
	@OneToMany(mappedBy="suiaIvObjetivo" , fetch=FetchType.LAZY)
	private List<SuiaIvActividad> suiaIvActividad;

	@Getter
	@Setter
	@OneToMany(mappedBy="suiaIvObjetivo" ,fetch=FetchType.LAZY)
	private List<SuiaIvIndicador> suiaIvIndicadors;

	@Getter
	@Setter
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_meta")
	private SuiaIvMeta suiaIvMeta;

	@Getter
	@Setter
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_plan_accion")
	private SuiaIvPlanAccion suiaIvPlanAccion;

	public SuiaIvObjetivo() {
	}

	

}