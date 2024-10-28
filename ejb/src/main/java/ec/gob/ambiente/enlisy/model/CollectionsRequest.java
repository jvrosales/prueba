package ec.gob.ambiente.enlisy.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ec.gob.ambiente.enlisy.speciescatalog.model.HigherClassification;
import ec.gob.ambiente.sib_suia_ora.indexcollectiongbif.model.DatasetIpt;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "collections_request", schema = "biodiversity_mcm")
@NamedQuery(name = "CollectionsRequest.findAll", query = "SELECT o FROM CollectionsRequest o")
public class CollectionsRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "coll_id")
	private Integer id;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "pain_id")
	private PatentInformation patentInformation;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "pare_id")
	private PatentRequest patentRequest;

	@Getter
	@Setter
	@Column(name = "coll_specie_code")
	private String collSpecieCode;

	@Getter
	@Setter
	@Column(name = "coll_kingdom")
	private String collKingdom;

	@Getter
	@Setter
	@Column(name = "coll_class")
	private String collClass;

	@Getter
	@Setter
	@Column(name = "coll_order")
	private String collOrder;

	@Getter
	@Setter
	@Column(name = "coll_family")
	private String collFamily;

	@Getter
	@Setter
	@Column(name = "coll_genus")
	private String collGenus;

	@Getter
	@Setter
	@Column(name = "coll_species")
	private String collSpecies;

	@Getter
	@Setter
	@Column(name = "coll_sex")
	private String collSex;

	@Getter
	@Setter
	@Column(name = "coll_kind")
	private String collKind;

	@Getter
	@Setter
	@Column(name = "coll_origin")
	private String collOrigin;

	@Getter
	@Setter
	@Column(name = "coll_identification_methods")
	private String collIdentificationMethods;

	@Getter
	@Setter
	@Column(name = "coll_registry_number")
	private String collRegistryNumber;

	@Getter
	@Setter
	@Column(name = "coll_constituent_elements")
	private String collConstituentElements;

	@Getter
	@Setter
	@Column(name = "coll_source_supply")
	private String collSourceSupply;

	@Getter
	@Setter
	@Column(name = "coll_location_specimen")
	private String collLocationSpecimen;

	@Temporal(TemporalType.TIMESTAMP)
	@Getter
	@Setter
	@Column(name = "coll_date_entry")
	private Date collDateEntry;

	@Getter
	@Setter
	@Column(name = "coll_status")
	private Boolean collStatus;

	@Getter
	@Setter
	@Column(name = "coll_input_type")
	private String collInputType;

	@Getter
	@Setter
	@Column(name = "coll_import_license_cites")
	private String collImportLicenseCites;

	@Getter
	@Setter
	@Column(name = "coll_import_license_no_cites")
	private String collImportLicenseNoCites;

	@Getter
	@Setter
	@Column(name = "coll_export_license_cites")
	private String collExportLicenseCites;

	@Getter
	@Setter
	@Column(name = "coll_export_license_no_cites")
	private String collExportLicenseNoCites;

	// Campos exclusivos de Zoocriadero,Vivero
	@Getter
	@Setter
	@Column(name = "coll_quantity")
	private Integer collQuantity;

	@Getter
	@Setter
	@Column(name = "coll_quota")
	private Integer collQuota;

	@Getter
	@Setter
	@Column(name = "coll_males_number")
	private Integer collMalesNumber;

	@Getter
	@Setter
	@Column(name = "coll_females_number")
	private Integer collFemalesNumber;

	// Campos exclusivos de Museos,Herbarios

	@Getter
	@Setter
	@Column(name = "coll_barcode")
	private String collBarcode;

	@Getter
	@Setter
	@Column(name = "gelo_id")
	private Integer geloId;

	@Getter
	@Setter
	@Column(name = "coll_state_specimen")
	private String collStateSpecimen;

	@Getter
	@Setter
	@Column(name = "coll_id_museum_herbal")
	private String collIdMuseumHerbal;

	@Getter
	@Setter
	@Column(name = "coll_coordinate_x")
	private Double collCoordinateX;

	@Getter
	@Setter
	@Column(name = "coll_coordinate_y")
	private Double collCoordinateY;
	
	@Getter
	@Setter
	@Column(name = "coll_min_altitude")
	private Integer collMinAltitude;

	@Getter
	@Setter
	@Column(name = "coll_max_altitude")
	private Integer collMaxAltitude;

	@Temporal(TemporalType.TIMESTAMP)
	@Getter
	@Setter
	@Column(name = "coll_collection_date")
	private Date collCollectionDate;

	@Getter
	@Setter
	@Column(name = "coll_collector_name")
	private String collCollectorName;

	@Getter
	@Setter
	@Column(name = "coll_collection_number")
	private String collCollectionNumber;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "dsipt_id")
	private DatasetIpt datasetIPT;
	
	@Getter
	@Setter
	@Column(name = "coll_status_ipt")
	private Boolean collStatusIPT;

	@Getter
	@Setter
	@Column(name = "coll_rights_holder")
	private String collRightsHolder;
	
	@Getter
	@Setter
	@Column(name = "coll_access_rights")
	private String collAccessRights;
	
	@Getter
	@Setter
	@Column(name = "coll_institucion_code")
	private String collInstitucionCode;
	
	@Getter
	@Setter
	@Column(name = "coll_collection_id")
	private String collCollectionId;
		
	@Getter
	@Setter
	@Column(name = "coll_collection_code")
	private String collCollectionCode;
	
	@Getter
	@Setter
	@Column(name = "coll_dataset_name")
	private String collDatasetName;
	
	@Getter
	@Setter
	@Column(name = "coll_owner_institution_code")
	private String collOwnerInstitutionCode;
	
	@Getter
	@Setter
	@Column(name = "coll_basis_of_record")
	private String collBasisOfRecord;
	
	@Getter
	@Setter
	@Column(name = "coll_information_with_held")
	private String collInformationWithHeld;
	
	@Getter
	@Setter
	@Column(name = "coll_scientific_name")
	private String collScientificName;
	
	@Getter
	@Setter
	@Column(name = "coll_identification_qualifier")
	private String collIdentificationQualifier;
	
	@Getter
	@Setter
	@Column(name = "coll_identification_confidence")
	private String collIdentificationConfidence;
	
	@Getter
	@Setter
	@Column(name = "coll_event_year")
	private String collEventYear;
	
	@Getter
	@Setter
	@Column(name = "coll_event_month")
	private String collEventMonth;
	
	@Getter
	@Setter
	@Column(name = "coll_event_day")
	private String collEventDay;
	
	@Getter
	@Setter
	@Column(name = "coll_province")
	private String collProvince;
	
	@Getter
	@Setter
	@Column(name = "coll_municipality")
	private String collMunicipality;
	
	@Getter
	@Setter
	@Column(name = "coll_locality")
	private String collLocality;
	
	@Getter
	@Setter
	@Column(name = "coll_coordinate_system")
	private String collCoordinateSystem;
	
	@Getter
	@Setter
	@Column(name = "coll_srs")
	private String collSrs;
	
	@Getter
	@Setter
	@Column(name = "coll_decimal_latitude")
	private Double collDecimalLatitude;
	
	@Getter
	@Setter
	@Column(name = "coll_decimal_longitude")
	private Double collDecimalLongitude;
	
	@Getter
	@Setter
	@Column(name = "coll_depth")
	private Integer collDepth;
	
	@Getter
	@Setter
	@Column(name = "coll_phylum")
	private String collPhylum;
	
	@Getter
	@Setter
	@Column(name = "coll_infraspecific_epithet")
	private String collInfraspecificEpithet;
	
	@Getter
	@Setter
	@Column(name = "coll_taxon_rank")
	private String collTaxonRank;
	
	@Getter
	@Setter
	@Column(name = "coll_observation")
	private String collObservation;
	
	@Getter
	@Setter
	@Column(name = "coll_arsfc_code")
	private String collArsfcCode;
	
	@Getter
	@Setter
	@Column(name = "coll_specific_epithet")
	private String collSpecificEpithet;
	
	@Getter
	@Setter
	@Column(name = "coll_verbatim_latitude")
	private Double collVerbatimLatitude;
	
	@Getter
	@Setter
	@Column(name = "coll_verbatim_longitude")
	private Double collVerbatimLongitude;
	
	@Getter
	@Setter
	@Column(name = "coll_country_code")
	private String collCountryCode;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Getter
	@Setter
	@Column(name = "coll_creation_date")
	private Date collCreationDate;
	
	@Getter
	@Setter
	@Column(name = "coll_creator_user")
	private String collCreatorUser;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Getter
	@Setter
	@Column(name = "coll_date_update")
	private Date collDateUpdate;
	
	@Getter
	@Setter
	@Column(name = "coll_user_update")
	private String collUserUpdate;
	
	@Getter
	@Setter
	@Column(name = "coll_observation_bd")
	private String collObservationBd;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Getter
	@Setter
	@Column(name = "coll_date_create")
	private Date collDateCreate;
	
	@Getter
	@Setter
	@Column(name = "coll_user_create")
	private String collUserCreate;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "coll_id_nai")
	private CatalogoPatentes collIdNai;
	
	@Getter
	@Setter
	@Column(name = "coll_copyright_registration_author")
	private String collCopyrightRegistrationAuthor;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "coll_id_ti")
	private CatalogoPatentes collIdTi;

	@Getter
	@Setter
	@Column(name = "coll_method_identification_description")
	private String collMethodIdentificationDescription;

	@Getter
	@Setter
	@Column(name = "coll_number_not_determined")
	private Integer collNumberNotDetermined;

	@Getter
	@Setter
	@Column(name = "coll_batch_number")
	private String collBatchNumber;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "spta_id")
	private SpeciesTaxa speciesTaxa;
		
	@Getter
	@Setter
	@Column(name = "coll_order_entry")
	private Integer collOrderEntry;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Getter
	@Setter
	@Column(name = "coll_status_date")
	private Date collStatusDate;
	
	@Getter
	@Setter
	@Column(name = "coll_generation")
	private Integer collGeneration;
	
	@Getter
	@Setter
	@Column(name = "coll_loading_method")  
	private Integer collLoadingMethod;  /* Almacena el metodo de carga de la información,
											1 = Manual
											2 = Hoja de excel
											3 = Servicio web
											4 = Ingreso de especies nuevas
											5 = Ingreso de grupos nuevos del DOI  */
	
	@Getter
	@Setter
	@Column(name = "coll_specie_code_error")
	private String collSpecieCodeError;
	
	@Getter
	@Setter
	@Column(name = "coll_quota_authorized")
	private Integer collQuotaAuthorized;

//	public Integer setCollQuotaAuthorized(Object newValue) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "hicl_id")
	private HigherClassification higherClassification;
	
}