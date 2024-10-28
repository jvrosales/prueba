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
 * The persistent class for the SpecificGoal database table.
 * 
 */
@Entity
@Table(name="specific_goal", schema="biodiversity")
public class SpecificGoal implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="spgo_id")
	private Integer spgoId;

	/**
	 * Nombre del objetivo especifico
	 */
	@Getter
	@Setter
	@Column(name="spgo_name")
	private String spgoName;
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="spgo_user_create")
	private String spgoUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="spgo_date_create")
	private Date spgoDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="spgo_user_update")
	private String spgoUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="spgo_date_update")
	private Date spgoDateUpdate;
	
	/**
	 * Estado del registro Activo=true o Eliminacion Logica=false
	 */
	@Getter
	@Setter
	@Column(name="spgo_status")
	private Boolean spgoStatus;
	
	/**
	 * id de OverrallObjective
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="ovob_id")
	private OverrallObjective overrallObjective;
}
