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
 * The persistent class for the RetentionEvent database table.
 * 
 */
@Entity
@Table(name="retentions_events", schema="biodiversity")
public class RetentionEvent implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RETENTION_EVENT_GENERATOR")
    @SequenceGenerator(name = "RETENTION_EVENT_GENERATOR", initialValue = 1, sequenceName = "seq_rete_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="rete_id")
	private Integer reteId;

	/**
	 * Codigo del evento registrado
	 */
	@Getter
	@Setter
	@Column(name="rete_register_code")
	private String reteRegisterCode;
	
	/**
	 * Registro del destino final para la custodia de la especie.
	 */
	@Getter
	@Setter
	@Column(name="rete_final_destination")
	private Integer reteFinalDestination;
	
	/**
	 * Registro del destino final infractor de la especie.
	 */
	@Getter
	@Setter
	@Column(name="rete_final_destination_offender")
	private Integer reteFinalDestinationOffender;
	
	/**
	 * Registro del vehículo donde fue movilzado a la custodia la especie.
	 */
	@Getter
	@Setter
	@Column(name="rete_mobilized_in")
	private Integer reteMobilizedIn;
	
	/**
	 * Registro de la ruta de movilización que fue tomada para la custodia de la especie.
	 */
	@Getter
	@Setter
	@Column(name="rete_route")
	private Integer reteRoute;
	
	/**
	 * Código de la provincia donde se custodia la especie.
	 */
	@Getter
	@Setter
	@Column(name="rete_province_id")
	private Integer reteProvinceId;
	
	
	/**
	 * Código del cantón donde se custodia la especie.
	 */
	@Getter
	@Setter
	@Column(name="rete_canton_id")
	private Integer reteCantonId;
	
	
	/**
	 * Identificador del registro del infractor
	 */
	@Getter
	@Setter
	@Column(name="rete_offender_id")
	private Integer reteOffenderId;
		
	
	/**
	 * Nombre y apellidos del infractor
	 */
	@Getter
	@Setter
	@Column(name="rete_offender_name")
	private String reteOffenderName;
	
	/**
	 * Número de identificación del infractor.
	 */
	@Getter
	@Setter
	@Column(name="rete_offender_identification")
	private String reteOffenderIdentification;
	
	/**
	 * Nacionalidad del infractor.
	 */
	@Getter
	@Setter
	@Column(name="rete_offender_nationality")
	private Integer reteOffenderNationality;
	
	/**
	 * Dirección del infractor.
	 */
	@Getter
	@Setter
	@Column(name="rete_offender_address")
	private String reteOffenderAddress;
	
	/**
	 * Placa del infractor
	 */
	@Getter
	@Setter
	@Column(name="rete_offender_plate")
	private String reteOffenderPlate;
	
	
	/**
	 * Tipo de pasaporte del infractor.
	 */
	@Getter
	@Setter
	@Column(name="rete_offender_passport_type")
	private Integer reteOffenderPassportType;
	
	/**
	 * Cooperativa de transporte
	 */
	@Getter
	@Setter
	@Column(name="rete_offender_transport_coop")
	private String reteOffenderTransportCoop;
	
	/**
	 * Nro. de licencia
	 */
	@Getter
	@Setter
	@Column(name="rete_nro_license")
	private String reteNroLicense;
	
	/**
	 * Nro. de matricula
	 */
	@Getter
	@Setter
	@Column(name="rete_nro_enrollment")
	private String reteNroEnrollment;
	
	/**
	 * Identificador Vida Silvestre
	 */
	@Getter
	@Setter
	@Column(name="rete_wild_life_id")
	private Integer reteWildLifeId;
	
	/**
	 * Identificador Elemento Constitutivo
	 */
	@Getter
	@Setter
	@Column(name="rete_element_const_id")
	private Integer reteElementConstId;
	
	/**
	 * Identificador Muestra biologica
	 */
	@Getter
	@Setter
	@Column(name="rete_biological_sample_id")
	private Integer reteBiologicalSampleId;
	
	/**
	 * Recomendaciones Dirección provincial
	 */
	@Getter
	@Setter
	@Column(name="rete_recomen_dire_provi")
	private String reteRecomenDireProvi;
	
	/**
	 * Identificador del registro del custodio
	 */
	@Getter
	@Setter
	@Column(name="rete_custodian_identification")
	private Integer reteCustodianIdentification;
		
	
	/**
	 * Nombre y apellidos del custodio
	 */
	@Getter
	@Setter
	@Column(name="rete_custodian_name")
	private String reteCustodianName;
	
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="rete_user_create")
	private String reteUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="rete_date_create")
	private Date reteDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="rete_user_update")
	private String reteUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="rete_date_update")
	private Date reteDateUpdate;
	
	/**
	 * Estado del registro Activo=true o Eliminacion Logica=false
	 */
	@Getter
	@Setter
	@Column(name="rete_status")
	private Boolean reteStatus;
	
	/**
	 * Infractor - Nombre destino para la especie
	 */
	@Getter
	@Setter
	@Column(name="rete_offender_destination")
	private String reteOffenderDestination;
	
	/**
	 * Infractor - Nombre destino para la especie
	 */
	@Getter
	@Setter
	@Column(name="rete_custodia_destination")
	private String reteCustodiaDestination;
	
	/**
	 * id de General Data
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="geda_id")
	private GeneralData generalData;
	
	/**
	 * Tipo de transporte
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="rete_transport_type")
	private BiodiversityGeneralCatalog reteTransportType;
	
	/**
	 * Lugar de escondite
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="rete_hiding_place")
	private BiodiversityGeneralCatalog reteHidingPlace;
	
	/**
	 * Causal de retencion
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="rete_causal_retention")
	private BiodiversityGeneralCatalog reteCausalRetention;
	
	/**
	 * Detalle del escondite
	 */
	@Getter
	@Setter
	@Column(name="hideout_detail")
	private String hideoutDetail;
	
	/**
	 * Numero de acta
	 */
	@Getter
	@Setter
	@Column(name="rete_act_number")
	private String reteActNumber;
	
}
