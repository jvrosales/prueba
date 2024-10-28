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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ec.gob.ambiente.enlisy.actionplan.enumerador.EstadoRegistroEnum;
import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the "SUIA_IV_INDICADOR" database table.
 * 
 */
@Entity
@Table(name="suia_iv_indicador_actividad",  schema="suia_iv")
@NamedQuery(name="SuiaIvIndicadorActividad.findAll", query="SELECT s FROM SuiaIvIndicadorActividad s")
public class SuiaIvIndicadorActividad implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	@Id
	@SequenceGenerator(name="seq_suia_iv_indicador_act" ,initialValue = 1, sequenceName = "seq_suia_iv_indicador_act", schema = "suia_iv", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_suia_iv_indicador_act")
	@Column(name="codigo_indicador_act")
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
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_actividad")
	private SuiaIvActividad suiaIvActividad;
	
	@Getter
	@Setter
	@OneToMany(mappedBy="suiaIvIndicadorActividad", fetch=FetchType.LAZY)
	private List<SuiaIvSeguimiento> suiaIvSeguimientos;
	
	@Getter
	@Setter
	@Column(name="porcentaje_avance")
	private Double porcentajeAvance;
	

	public SuiaIvIndicadorActividad() {
	}

	

	
}