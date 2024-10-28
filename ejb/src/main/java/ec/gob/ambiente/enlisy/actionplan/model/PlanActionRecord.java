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
 * The persistent class for the PlanActionRecord database table.
 * 
 */
@Entity
@Table(name="plan_action_record", schema="biodiversity")
public class PlanActionRecord implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="plar_id")
	private Integer plarId = Integer.valueOf(0);

	/**
	 * Posibles involucrados
	 */
	@Getter
	@Setter
	@Column(name="plar_possible_involved")
	private String plarPossibleInvolved;
	
	/**
	 * Fecha registro plan de acción
	 */
	@Getter
	@Setter
	@Column(name="plar_date")
	private Date plarDate;
	
	/**
	 * Año inicio de tarea
	 */
	@Getter
	@Setter
	@Column(name="plar_home_task")
	private Integer plarHomeTask;
	
	/**
	 * Medios de verificación
	 */
	@Getter
	@Setter
	@Column(name="plar_verification_means")
	private String plarVerificationMeans;
	
	/**
	 * Area de intervención
	 */
	@Getter
	@Setter
	@Column(name="plar_area_intervention")
	private Double plarAreaIntervention;
	
	/**
	 * Porcentaje de avance
	 */
	@Getter
	@Setter
	@Column(name="plar_advance_percentage")
	private Integer plarAdvancePercentage;
	
	/**
	 * Ejecutor
	 */
	@Getter
	@Setter
	@Column(name="plar_executor")
	private String plarExecutor;
	
	/**
	 * Protección
	 */
	@Getter
	@Setter
	@Column(name="plar_protection")
	private String plarProtection;
	
	/**
	 * Sitio
	 */
	@Getter
	@Setter
	@Column(name="plar_site")
	private String plarSite;
	
	/**
	 * Porcentaje presupuesto
	 */
	@Getter
	@Setter
	@Column(name="plar_percentage")
	private Integer plarPercentage;
	
	/**
	 * Rubro presupuesto
	 */
	@Getter
	@Setter
	@Column(name="plar_item")
	private Integer plarItem;
	
	/**
	 * Estado Plan de acción
	 */
	@Getter
	@Setter
	@Column(name="plat_status_record")
	private String platStatusRecord;
	
	
	/**
	 * Detalle presupuesto
	 */
	@Getter
	@Setter
	@Column(name="plar_detail")
	private String plarDetail;
	
	/**
	 * Cantidad presupuesto
	 */
	@Getter
	@Setter
	@Column(name="plar_quantity")
	private Integer plarQuantity;
	
	/**
	 * Costo unitario presupuesto
	 */
	@Getter
	@Setter
	@Column(name="plar_unit_cost")
	private Double plarUnitCost;
	
	/**
	 * Costo total presupuesto
	 */
	@Getter
	@Setter
	@Column(name="plar_total_cost")
	private Double plarTotalCost;
	
	/**
	 * Coordenada X
	 */
	@Getter
	@Setter
	@Column(name="plar_x")
	private Double plarX;
	
	/**
	 * Coordenada Y
	 */
	@Getter
	@Setter
	@Column(name="plar_y")
	private Double plarY;
	
	/**
	 * Id provincia
	 */
	@Getter
	@Setter
	@Column(name="plar_province_id")
	private Integer plarProvinceId;
	
	/**
	 * Id canton
	 */
	@Getter
	@Setter
	@Column(name="plar_canton_id")
	private Integer plarCantonId;
	
	/**
	 * Id parroquia
	 */
	@Getter
	@Setter
	@Column(name="plar_parroquia_id")
	private Integer plarParroquiaId;
	
	/**
	 * Localidad
	 */
	@Getter
	@Setter
	@Column(name="plar_location")
	private String plarLocation;
	
	/**
	 * Zona
	 */
	@Getter
	@Setter
	@Column(name="plar_zone")
	private String plarZone="EPSG:32717";
	
	
	@Getter
	@Setter
	@Column(name="plar_zoom")
	private int plarZoom=1;
	
	/**
	 * Coordenada Y
	 */
	@Getter
	@Setter
	@Column(name="plar_altitude")
	private Double plarAltitude;
	
	
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="plar_user_create")
	private String plarUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="plar_date_create")
	private Date plarDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="plar_user_update")
	private String plarUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="plar_date_update")
	private Date plarDateUpdate;
	
	/**
	 * Estado del registro Activo=true o Eliminacion Logica=false
	 */
	@Getter
	@Setter
	@Column(name="plar_status")
	private Boolean plarStatus;
	
	/**
	 * id de Activity
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="acti_id")
	private Activity activity;
	
	/**
	 * id de IndicatorCompliance
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="inco_id")
	private IndicatorCompliance indicatorCompliance;
}
