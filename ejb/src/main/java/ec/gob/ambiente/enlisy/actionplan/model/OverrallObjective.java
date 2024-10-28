package ec.gob.ambiente.enlisy.actionplan.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the OverrallObjective database table.
 * 
 */
@Entity
@Table(name="overrall_objective", schema="biodiversity")
public class OverrallObjective implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ovob_id")
	private Integer ovobId;

	/**
	 * Nombre del objetivo general
	 */
	@Getter
	@Setter
	@Column(name="ovob_name")
	private String ovobName;
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="ovob_user_create")
	private String ovobUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="ovob_date_create")
	private Date ovobDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="ovob_user_update")
	private String ovobUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="ovob_date_update")
	private Date ovobDateUpdate;
	
	/**
	 * Estado del registro Activo=true o Eliminacion Logica=false
	 */
	@Getter
	@Setter
	@Column(name="ovob_status")
	private Boolean ovobStatus;
	
	/**
	 * id de ActionPlan
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="stli_id")
	private StrategicLine strategicLine;
}
