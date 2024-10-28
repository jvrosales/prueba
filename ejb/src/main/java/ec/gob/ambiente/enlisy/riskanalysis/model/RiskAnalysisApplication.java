package ec.gob.ambiente.enlisy.riskanalysis.model;

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

import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecieTaxa;
import ec.gob.ambiente.enlisy.wildlifeevent.model.BiodiversityGeneralCatalog;
import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the risk_analysis_applications database table.
 * 
 */
@Entity
@Table(name="risk_analysis_applications", schema="biodiversity")
public class RiskAnalysisApplication implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RISK_ANALYSIS_APPL_GENERATOR")
    @SequenceGenerator(name = "RISK_ANALYSIS_APPL_GENERATOR", initialValue = 1, sequenceName = "seq_riaa_id", schema = "biodiversity", allocationSize = 1)
    @Column(name="riaa_id")
	private Integer riaaId;
	
	/**
	 * Numero de tramite
	 */
	@Getter
	@Setter
	@Column(name="riaa_code")
	private String riaaCode;
	
	/** 
	 * Id del usuario
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	
	/**
	 * Fecha de la solicitud
	 */
	@Getter
	@Setter
	@Column(name="riaa_date_application")
	private Date riaaDateApplication;
	
	/**
	 * Nombre del Proyecto
	 */
	@Getter
	@Setter
	@Column(name="riaa_project_name")
	private String riaaProjectName;
	
	
	/**
	 * Nombre del representante legal
	 */
	@Getter
	@Setter
	@Column(name="riaa_legal_representative")
	private String riaaLegalRepresentative;
	
	/**
	 * Identificacion del representante legal
	 */
	@Getter
	@Setter
	@Column(name="riaa_iden_legal_representative")
	private String riaaIdenLegalRepresentative;
	
	
	/** 
	 * Id de la especie
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="spta_id")
	private SpecieTaxa sptaId;
	
	/**
	 * Nombre comun de la especie
	 */
	@Getter
	@Setter
	@Column(name="riaa_vernacular_name")
	private String riaaVernacularName;
	
	/**
	 * Nombre cientifico de la especie
	 */
	@Getter
	@Setter
	@Column(name="riaa_scientific_name")
	private String riaaScientificName;
	
	
	/** 
	 * Id del tipo de destino
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="type_destination_id")
	private BiodiversityGeneralCatalog typeDestinationId;
	
	
	/**
	 * Coordenada X
	 */
	@Getter
	@Setter
	@Column(name="riaa_x")
	private Double riaaX;
	
	/**
	 * Coordenada Y
	 */
	@Getter
	@Setter
	@Column(name="riaa_y")
	private Double riaaY;
	
	/**
	 * Sistema de Coordenadas
	 */
	@Getter
	@Setter
	@Column(name="riaa_coordinate_system")
	private String riaaCoordinateSystem;
	
	/**
	 * Objetivo de introduccion
	 */
	@Getter
	@Setter
	@Column(name="riaa_introduction_objective")
	private String riaaIntroductionObjective;
	
	/**
	 * Historial de Invasividad de la Especie
	 */
	@Getter
	@Setter
	@Column(name="riaa_invasiveness_history")
	private String riaaInvasivenessHistory;
	
	/**
	 * Relaciones con Taxones Invasores Cercanos
	 */
	@Getter
	@Setter
	@Column(name="riaa_taxon_relationship")
	private String riaaTaxonRelationship;
	
	
	/**
	 * Vectores de Especies Invasoras
	 */
	@Getter
	@Setter
	@Column(name="riaa_invasive_vector")
	private String riaaInvasiveVector;
	
	/**
	 * Riesgo de Introduccion
	 */
	@Getter
	@Setter
	@Column(name="riaa_introduction_risk")
	private String riaaIntroductionRisk;
	
	
	/**
	 *Riesgo de establecimiento
	 */
	@Getter
	@Setter
	@Column(name="riaa_establishment_risk")
	private String riaaEstablishmentRisk;
	
	/**
	 *Riesgo de dispersion
	 */
	@Getter
	@Setter
	@Column(name="riaa_dispersion_risk")
	private String riaaDispersionRisk;
	
	/**
	 *Comportamiento en el sitio de origen
	 */
	@Getter
	@Setter
	@Column(name="riaa_behaviour")
	private String riaaBehaviour;
	
	/**
	 *Impactos Sanitarios
	 */
	@Getter
	@Setter
	@Column(name="riaa_health_impact")
	private String riaaHealthImpact;
	
	/**
	 *Impactos Economicos
	 */
	@Getter
	@Setter
	@Column(name="riaa_economic_impact")
	private String riaaEconomicImpact;
	
	/**
	 *Impactos ambientales
	 */
	@Getter
	@Setter
	@Column(name="riaa_environmental_impact")
	private String riaaEnvironmentalImpact;
	
	/**
	 *Impactos ecologicos
	 */
	@Getter
	@Setter
	@Column(name="riaa_ecological_impact")
	private String riaaEcologicalImpact;
	
	/** 
	 * Id del tipo de solicitud
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="type_application_id")
	private BiodiversityGeneralCatalog typeApplicationId;
	
	/**
	 *Estado de la solicitud
	 */
	@Getter
	@Setter
	@Column(name="riaa_status_application")
	private String riaaStatusApplication;
	
	/**
	 *Objetivo de introduccion que ingresa el tecnico
	 */
	@Getter
	@Setter
	@Column(name="riaa_intr_obj_technical")
	private String riaaIntrObjTechnical;
	
	/**
	 *Comportamiento en el sitio de origen que ingresa el tecnico
	 */
	@Getter
	@Setter
	@Column(name="riaa_behaviour_technical")
	private String riaaBehaviourTechnical;
	
	/**
	 *Potencial reproductivo que ingresa el tecnico
	 */
	@Getter
	@Setter
	@Column(name="riaa_reproductive_potential")
	private String riaaReproductivePotential;
	
	/**
	 *Enfermedades asociadas que ingresa el tecnico
	 */
	@Getter
	@Setter
	@Column(name="riaa_associated_diseases")
	private String riaaAssociatedDiseases;
	
	/**
	 *Potencial de la especie como plaga que ingresa el tecnico
	 */
	@Getter
	@Setter
	@Column(name="riaa_potential_pest")
	private String riaaPotentialPest;
	
	/**
	 *Potencial de hibridación con especies nativas
	 */
	@Getter
	@Setter
	@Column(name="riaa_hybridization_potential")
	private String riaaHybridizationPotential;
	
	/**
	 *Potencial de dispersion a partir del sitio de introduccion
	 */
	@Getter
	@Setter
	@Column(name="riaa_dispersion_potential")
	private String riaaDispersionPotential;
	
	/**
	 *Metodos de control de la poblacion para la especie
	 */
	@Getter
	@Setter
	@Column(name="riaa_population_control_method")
	private String riaaPopulationControlMethod;
	
	/**
	 *Antecedentes de introduccion de la especie en otro pais o region
	 */
	@Getter
	@Setter
	@Column(name="riaa_introduction_record")
	private String riaaIntroductionRecord;
	
	/**
	 *Conclusiones que ingresa el tecnico
	 */
	@Getter
	@Setter
	@Column(name="riaa_conclusion")
	private String riaaConclusion;
	
	/**
	 *Recomendaciones que ingresa el tecnico
	 */
	@Getter
	@Setter
	@Column(name="riaa_recommendation")
	private String riaaRecommendation;
	
	/**
	 *observaciones que realizo la autoridad competente acerca del informe tecnico cuando la actividad o proceso es Informe Observado
	 */
	@Getter
	@Setter
	@Column(name="riaa_observation_technical_report")
	private String riaaObservationTechnicalReport;
	
	
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="riaa_user_create")
	private String riaaUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="riaa_date_create")
	private Date riaaDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="riaa_user_update")
	private String riaaUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="riaa_date_update")
	private Date riaaDateUpdate;
	
	/**
	 * Bandera que indica si el registro esta activo
	 */
	@Getter
	@Setter
	@Column(name="riaa_status")
	private Boolean riaaStatus;
	
	/**
	 *Nombre del Tecnico Responsable
	 */
	@Getter
	@Setter
	@Column(name="riaa_responsable_technical")
	private String riaaResponsableTechnical;
	
	/**
	 * Fecha de aprobacion
	 */
	@Getter
	@Setter
	@Column(name="riaa_approval_date")
	private Date riaaApprovalDate;
	
	/**
	 *Pronunciamiento autoridad competente
	 */
	@Getter
	@Setter
	@Column(name="riaa_pronouncement")
	private String riaaPronouncement;
	
	/**
	 * Id de la provincia
	 */
	@Getter
	@Setter
	@Column(name="riaa_province_id")
	private Integer riaaProvinceId;
	
	/**
	 * Id del canton
	 */
	@Getter
	@Setter
	@Column(name="riaa_canton_id")
	private Integer riaaCantonId;
	
	/**
	 * Id de la parroquia
	 */
	@Getter
	@Setter
	@Column(name="riaa_parroquia_id")
	private Integer riaaParroquiaId;
	
	/**
	 * Localidad
	 */
	@Getter
	@Setter
	@Column(name="riaa_location")
	private String riaaLocation;
	
	/**
	 * Zone
	 */
	@Getter
	@Setter
	@Column(name="riaa_zone")
	private String riaaZone;
	
	/**
	 * Zone
	 */
	@Getter
	@Setter
	@Column(name="riaa_north_south")
	private String riaaNorthSouth;
	
	/**
	 * Altitud
	 */
	@Getter
	@Setter
	@Column(name="riaa_altitude")
	private Double riaaAltitude;
	
	/**
	 * Zoom
	 */
	@Getter
	@Setter
	@Column(name="riaa_zoom")
	private Integer riaaZoom;
	
	/**
	 * Coordenada x sin conversión
	 */
	@Getter
	@Setter
	@Column(name="riaa_original_x")
	private Double riaaOriginalX;
	
	/**
	 * Coordenada y sin conversión
	 */
	@Getter
	@Setter
	@Column(name="riaa_original_y")
	private Double riaaOriginalY;
	
	/**
	 * Zona Proyectada
	 */
	@Getter
	@Setter
	@Column(name="riaa_projected_zone")
	private String riaaProjectedZone;
	
	/**
	 * Comentario cuando se elige otro tipo de destino
	 */
	@Getter
	@Setter
	@Column(name="riaa_other_destination")
	private String riaaOtherDestination;
	
	/**
	 * Username del tecnico responsable
	 */
	@Getter
	@Setter
	@Column(name="riaa_responsable_technical_id")
	private String riaaResponsableTechnicalId;
	
	

	
}
