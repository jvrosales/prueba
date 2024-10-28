package ec.gob.ambiente.enlisy.wildlifeevent.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the RescueEvent database table.
 * 
 */
@Entity
@Table(name="retention_trackings", schema="biodiversity")
public class RetentionTracking implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RETENTION_TRACKING_GENERATOR")
    @SequenceGenerator(name = "RETENTION_TRACKING_GENERATOR", initialValue = 1, sequenceName = "seq_reta_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="reta_id")
	private Integer retaId;

	/**
	 * Id del medio de conservación registrado en el SIB-Ec que tienen patentes de medios de conservación
	 */
	@Getter
	@Setter
	@Column(name="reta_final_destination")
	private Integer retaFinalDestination;
	
	
	/**
	 * Tipo de desctino Final
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="bigc_id")
	private BiodiversityGeneralCatalog biodiversityGeneralCatalog;
	
	/**
	 * Tipo proceso
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="process_type_id")
	private BiodiversityGeneralCatalog processTypeId;
	
	/**
	 * Fecha del seguimiento
	 */
	@Getter
	@Setter
	@Column(name="reta_date_tracking")
	private Date retaDateTracking;
	
	/**
	 * Numero de acta de destino final
	 */
	@Getter
	@Setter
	@Column(name="reta_act_number")
	private String retaActNumber;
	
	/**
	 * Fecha y hora de entrega
	 */
	@Getter
	@Setter
	@Column(name="reta_date_delivery")
	private Date retaDateDelivery;
	
	/**
	 * Observaciones
	 */
	@Getter
	@Setter
	@Column(name="reta_remark")
	private String retaRemark;
	
	/**
	 * Evento de Rescate
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="rete_id")
	private RetentionEvent reteId;
	
	
	/**
	 * Numero de tramite de rescate
	 */
	@Getter
	@Setter
	@Column(name="reta_process_number")
	private String retaProcessNumber;
	
	/**
	 * Numero de tramite de rescate
	 */
	@Getter
	@Setter
	@Column(name="reta_responsable_name")
	private String retaResponsableName;
	
	
	@Getter
	@Setter
	@Column(name = "reta_date_create")
	private Date retaDateCreate;

	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name = "reta_user_update")
	private String retaUserUpdate;
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name = "reta_user_create")
	private String retaUserCreate;

	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name = "reta_date_update")
	private Date retaDateUpdate;

	/**
	 * Bandera que indica si el registro esta activo
	 */
	@Getter
	@Setter
	@Column(name = "reta_status")
	private Boolean retaStatus;
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name = "reta_process_number_tracking")
	private String retaProcessNumberTracking;
	
	/**
	 * Nombre del del medio de conservacion registrado en el SIB-Ec que tienen patentes de medios de conservacion
	 */
	@Getter
	@Setter
	@Column(name="reta_name_final_destination")
	private String retaNameFinalDestination;
	
	
}
