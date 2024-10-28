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
 * The persistent class for the PlanActionTracking database table.
 * 
 */
@Entity
@Table(name="plan_action_tracking", schema="biodiversity")
public class PlanActionTracking implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="plat_id")
	private Integer platId;
	
	/**
	 * Estado seguimiento
	 */
	@Getter
	@Setter
	@Column(name="plat_status_tracking")
	private String platStatusTracking;
	

	/**
	 * Porcentaje Financiador segumiento
	 */
	@Getter
	@Setter
	@Column(name="plat_percentage")
	private Integer platPercentage;
	
	/**
	 * Reporte a cargar
	 */
	@Getter
	@Setter
	@Column(name="plat_report")
	private String platReport;
	
	/**
	 * Fecha seguimiento
	 */
	@Getter
	@Setter
	@Column(name="plat_date")
	private Date platDate;
	
	/**
	 * Observacion
	 */
	@Getter
	@Setter
	@Column(name="plat_observation")
	private String platObservation;
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="plat_user_create")
	private String platUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="plat_date_create")
	private Date platDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="plat_user_update")
	private String platUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="plat_date_update")
	private Date platDateUpdate;
	
	/**
	 * Estado del registro Activo=true o Eliminacion Logica=false
	 */
	@Getter
	@Setter
	@Column(name="plat_status")
	private Boolean platStatus;
	
	/**
	 * id de PlanActionRecord
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="plar_id")
	private PlanActionRecord planActionRecord;
	
	/**
	 * id de PlanActionFunder
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="plaf_id")
	private PlanActionFunder planActionFunder;
	
	/**
	 * id de PlanActionFunder
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="pad_id")
	private PlanActionDocument planActionDocument;
}
