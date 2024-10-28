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
 * The persistent class for the StrategicLine database table.
 * 
 */
@Entity
@Table(name="strategic_line", schema="biodiversity")
public class StrategicLine implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="stli_id")
	private Integer stliId;

	/**
	 * Nombre de la linea estrategica
	 */
	@Getter
	@Setter
	@Column(name="stli_name")
	private String stliName;
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="stli_user_create")
	private String stliUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="stli_date_create")
	private Date stliDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="stli_user_update")
	private String stliUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="stli_date_update")
	private Date stliDateUpdate;
	
	/**
	 * Estado del registro Activo=true o Eliminacion Logica=false
	 */
	@Getter
	@Setter
	@Column(name="stli_status")
	private Boolean stliStatus;
	
	/**
	 * id de ActionPlan
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="acpl_id")
	private ActionPlan actionPlan;
}
