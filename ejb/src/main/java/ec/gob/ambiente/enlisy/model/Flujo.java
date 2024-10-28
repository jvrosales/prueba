package ec.gob.ambiente.enlisy.model;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the flows database table.
 * 
 */
@Entity
@Table(name="flows", schema="suia_iii")
@NamedQuery(name="Flujo.findAll", query="SELECT f FROM Flujo f")
public class Flujo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="flow_id")
	private Integer id;

	@Getter
	@Setter
	@Column(name="flow_docu_table_name")
	private String tablaDocumento;

	@Getter
	@Setter
	@Column(name="flow_docu_table_prefix")
	private String prefijoTablaDocumento;

	@Getter
	@Setter
	@Column(name="flow_key")
	private String idProceso;

	@Getter
	@Setter
	@Column(name="flow_name")
	private String nombreFlujo;

	@Getter
	@Setter
	@Column(name="flow_order")
	private Integer orden;

	@Getter
	@Setter
	@Column(name="flow_status")
	private Boolean estado;

	@Setter
	@Getter
	@Column(name="flow_system_version")
	private Integer versionSistema;

	public Flujo() {
	}

	

}