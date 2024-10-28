package ec.gob.ambiente.suia.iv.model;

import java.io.Serializable;
import javax.persistence.*;

import ec.gob.ambiente.enlisy.actionplan.enumerador.EstadoRegistroEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


/**
 * The persistent class for the "SUIA_IV_SEGUIMIENTO_PROVINCIA" database table.
 * 
 */
@Entity
@Table(name="suia_iv_seguimiento_provincia", schema = "suia_iv")
@NamedQuery(name="SuiaIvSeguimientoProvincia.findAll", query="SELECT s FROM SuiaIvSeguimientoProvincia s")
public class SuiaIvSeguimientoProvincia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@SequenceGenerator(name="seq_suia_iv_seg_provincia" ,initialValue = 1, sequenceName = "seq_suia_iv_seg_provincia", schema = "suia_iv",  allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_suia_iv_seg_provincia")
	@Column(name="codigo_seguimiento_provincia")
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
	@Column(name="gelo_id")
	private Integer geloId;

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

	public SuiaIvSeguimientoProvincia() {
	}


}