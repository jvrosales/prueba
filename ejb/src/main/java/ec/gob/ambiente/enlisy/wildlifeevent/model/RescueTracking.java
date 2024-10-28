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
@Table(name="rescue_trackings", schema="biodiversity")
public class RescueTracking implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RESCUE_TRACKING_GENERATOR")
    @SequenceGenerator(name = "RESCUE_TRACKING_GENERATOR", initialValue = 1, sequenceName = "seq_rtra_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="rtra_id")
	private Integer rtraId;

	/**
	 * Id del medio de conservación registrado en el SIB-Ec que tienen patentes de medios de conservacion
	 */
	@Getter
	@Setter
	@Column(name="rtra_final_destination")
	private Integer rtraFinalDestination;
	
	
	/**
	 * Tipo de desctino Final
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="bigc_id")
	private BiodiversityGeneralCatalog biodiversityGeneralCatalog;
	
	/**
	 * Fecha del seguimiento
	 */
	@Getter
	@Setter
	@Column(name="rtra_date_tracking")
	private Date rtraDateTracking;
	
	/**
	 * Numero de acta de destino final
	 */
	@Getter
	@Setter
	@Column(name="rtra_act_number")
	private String rtraActNumber;
	
	/**
	 * Fecha y hora de entrega
	 */
	@Getter
	@Setter
	@Column(name="rtra_date_delivery")
	private Date rtraDateDelivery;
	
	/**
	 * Observaciones
	 */
	@Getter
	@Setter
	@Column(name="rtra_remark")
	private String rtraRemark;
	
	/**
	 * Evento de Rescate
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="rese_id")
	private RescueEvent reseId;
	
	
	/**
	 * Numero de tramite de rescate
	 */
	@Getter
	@Setter
	@Column(name="rtra_process_number")
	private String rtraProcessNumber;
	
	/**
	 * Numero de tramite de rescate
	 */
	@Getter
	@Setter
	@Column(name="rtra_responsable_name")
	private String rtraResponsableName;
	
	
	@Getter
	@Setter
	@Column(name = "rtra_date_create")
	private Date rtraDateCreate;

	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name = "rtra_user_update")
	private String rtraUserUpdate;
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name = "rtra_user_create")
	private String rtraUserCreate;

	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name = "rtra_date_update")
	private Date rtraDateUpdate;

	/**
	 * Bandera que indica si el registro esta activo
	 */
	@Getter
	@Setter
	@Column(name = "rtra_status")
	private Boolean rtraStatus;
	
	/**
	 * Nombre del del medio de conservacion registrado en el SIB-Ec que tienen patentes de medios de conservacion
	 */
	@Getter
	@Setter
	@Column(name="rtra_name_final_destination")
	private String rtraNameFinalDestination;
	
	
}
