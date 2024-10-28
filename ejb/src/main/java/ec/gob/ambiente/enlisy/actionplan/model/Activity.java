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
 * The persistent class for the Activity database table.
 * 
 */
@Entity
@Table(name="activity", schema="biodiversity")
public class Activity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="acti_id")
	private Integer actiId;

	/**
	 * Nombre de la actividad
	 */
	@Getter
	@Setter
	@Column(name="acti_name")
	private String actiName;
	
	/**
	 * Responsable de la actividad
	 */
	@Getter
	@Setter
	@Column(name="acti_responsible")
	private String actiResponsible;
	
	/**
	 * Fecha inicio Actividad
	 */
	@Getter
	@Setter
	@Column(name="acti_date_start")
	private Date actiDateStart;
	
	/**
	 * Fecha fin Actividad
	 */
	@Getter
	@Setter
	@Column(name="acti_date_finish")
	private Date actiDateFinish;
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="acti_user_create")
	private String actiUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="acti_date_create")
	private Date actiDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="acti_user_update")
	private String actiUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="acti_date_update")
	private Date actiDateUpdate;
	
	/**
	 * Estado del registro Activo=true o Eliminacion Logica=false
	 */
	@Getter
	@Setter
	@Column(name="acti_status")
	private Boolean actiStatus;
	
	/**
	 * id de ExpectedResult
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="exre_id")
	private ExpectedResult expectedResult;
}
