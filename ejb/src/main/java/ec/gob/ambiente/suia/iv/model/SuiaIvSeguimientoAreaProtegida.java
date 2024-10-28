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
import java.util.Date;


/**
 * The persistent class for the "SUIA_IV_SEGUIMIENTO_DOCUMENTO" database table.
 * 
 */
@Entity
@Table(name="suia_iv_seguimiento_area_protegida", schema = "suia_iv")
@NamedQuery(name="SuiaIvSeguimientoAreaProtegida.findAll", query="SELECT s FROM SuiaIvSeguimientoAreaProtegida s")
public class SuiaIvSeguimientoAreaProtegida implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@SequenceGenerator(name="seq_suia_iv_seguimiento_area" ,initialValue = 1, sequenceName = "seq_suia_iv_seguimiento_area", schema = "suia_iv", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_suia_iv_seguimiento_area")
	@Column(name="codigo_seguimiento_area")
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
	@Column(name="usuario_actualiza")
	private String usuarioActualiza;

	@Getter
	@Setter
	@Column(name="usuario_inserta")
	private String usuarioInserta;

	@Getter
	@Setter
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_seguimiento")
	private SuiaIvSeguimiento suiaIvSeguimiento;
	
	@Getter
	@Setter
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_area_protegida")
	private SuiaIvAreasProtegidas suiaIvAreaProtegida;

	public SuiaIvSeguimientoAreaProtegida() {
	}
	
}