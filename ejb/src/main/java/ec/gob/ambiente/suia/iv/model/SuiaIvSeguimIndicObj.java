package ec.gob.ambiente.suia.iv.model;

import java.io.Serializable;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

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
@Table(name="suia_iv_seguim_indic_obj", schema = "suia_iv")
@NamedQuery(name="SuiaIvSeguimIndicObj.findAll", query="SELECT s FROM SuiaIvSeguimIndicObj s")
public class SuiaIvSeguimIndicObj implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@SequenceGenerator(name="seq_suia_iv_seguim_indic_obj" ,initialValue = 1, sequenceName = "seq_suia_iv_seguim_indic_obj", schema = "suia_iv",  allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_suia_iv_seguim_indic_obj")
	@Column(name="codigo_seguim_indic_obj")
	private Long codigo;

	@Getter
	@Setter
	@Column(name="actividad_realizada")
	private String actividadRealizada;


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
	@Transient
//	@Column(name="estado_seguimiento")
	private String estadoSeguimiento;

	@Getter
	@Setter
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_indicador")
	private SuiaIvIndicador suiaIvIndicador;

	@Getter
	@Setter
	@OneToMany(mappedBy="suiaIvSeguimIndicObj", fetch=FetchType.LAZY)
	private List<SuiaIvSeguimIndicObjDoc> suiaIvSeguimIndicObjDoc;

	@Getter
	@Setter
	@OneToMany(mappedBy="suiaIvSeguimIndicObj", fetch=FetchType.LAZY)
	private List<SuiaIvSeguimIndicObjPolitica> suiaIvSeguimIndicObjPolitica;

	public SuiaIvSeguimIndicObj() {
	}

}