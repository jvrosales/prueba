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
 * The persistent class for the "SUIA_IV_PLAN_ACCION" database table.
 * 
 */
@Entity
@Table(name="suia_iv_areas_protegidas", schema = "suia_iv")
@NamedQuery(name="SuiaIvAreasProtegidas.findAll", query="SELECT s FROM SuiaIvAreasProtegidas s")
public class SuiaIvAreasProtegidas implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@SequenceGenerator(name="seq_suia_iv_area_protegida" ,initialValue = 1, sequenceName = "seq_suia_iv_area_protegida", schema = "suia_iv", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_suia_iv_area_protegida")
	@Column(name="codigo_area_protegida")
	private Long codigo;
	
	@Getter
	@Setter
	@Column(name="nombre")
	private String nombre;
	
	@Getter
	@Setter
	@Column(name="descripcion")
	private String descripcion;

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
	@Column(name="estado")
	@Enumerated(EnumType.STRING)
	private EstadoRegistroEnum estado;


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
	@OneToMany(mappedBy="suiaIvAreaProtegida", fetch=FetchType.LAZY)
	private List<SuiaIvSeguimientoAreaProtegida> suiaIvSeguimientoAreaProtegida;

	public SuiaIvAreasProtegidas() {
	}


}