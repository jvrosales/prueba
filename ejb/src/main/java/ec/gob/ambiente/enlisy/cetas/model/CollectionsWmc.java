package ec.gob.ambiente.enlisy.cetas.model;

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

import ec.gob.ambiente.sib_suia_ora.indexcollectiongbif.model.DatasetIpt;
import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the proposed_collection_species database table.
 * 
 */
@Entity
@Table(name="collections_wmc", schema="cetas")
@NamedQuery(name="CollectionsWmc.findAll", query="SELECT o FROM CollectionsWmc o where o.collStatus = true")
public class CollectionsWmc implements Serializable,Cloneable {
	private static final long serialVersionUID = 1L;
	
	//se comenta la siguiente linea porque se hace referencia que tabla collections es hija de patent_application
	//public Collections clone(WildlifeManagementCenter wildlifeManagementCenter) throws CloneNotSupportedException {
	public CollectionsWmc clone(PatentApplication patentApplication) throws CloneNotSupportedException {
		
		CollectionsWmc clone= (CollectionsWmc)super.clone();
		clone.setCollId(null);
		clone.setCollUserUpdate(null);
		clone.setCollDateUpdate(null);
		//clone.setWildlifeManagementCenter(wildlifeManagementCenter);
		clone.setPatentApplication(patentApplication);
		return clone;
	}		

	public CollectionsWmc() {		
	}
	
	/*
	public Collections(WildlifeManagementCenter wildlifeManagementCenter) {
		this.wildlifeManagementCenter=wildlifeManagementCenter;
	}*/
	
	public CollectionsWmc(PatentApplication patentApplication) {
		this.patentApplication=patentApplication;
	}

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="coll_id")
	private Integer collId;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="wmce_id")
	private WildlifeManagementCenter wildlifeManagementCenter;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="paap_id")
	private PatentApplication patentApplication;
	
	@Getter
	@Setter	
	@Column(name="coll_specie_code")
	private String collSpecieCode;
	
	@Getter
	@Setter	
	@Column(name="coll_kingdom")
	private String collKingdom;
	
	@Getter
	@Setter	
	@Column(name="coll_class")
	private String collClass;
	
	@Getter
	@Setter	
	@Column(name="coll_order")
	private String collOrder;
	
	@Getter
	@Setter
	@Column(name="coll_family")
	private String collFamily;
	
	@Getter
	@Setter
	@Column(name="coll_genus")
	private String collGenus;
	
	@Getter
	@Setter
	@Column(name="coll_species")
	private String collSpecies;
	
	/*
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="cwmc_id_sex")
	private CatalogWmc Sex;
	*/
	
	@Getter
	@Setter
	@Column(name="coll_sex")
	private String collSex;
	
	@Getter
	@Setter
	@Column(name="coll_kind")
	private String collKind;
	
	@Getter
	@Setter
	@Column(name="coll_origin")
	private String collOrigin;
	
	
	/*
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="cwmc_id_identification_methods")
	private CatalogWmc Identification;
	*/
	
	@Getter
	@Setter
	@Column(name="coll_identification_methods")
	private String collIdentificationMethods;
	
	@Getter
	@Setter
	@Column(name="coll_registry_number")
	private String collRegistryNumber;
	
	@Getter
	@Setter
	@Column(name="coll_constituent_elements")
	private String collConstituentElements;
	
	/*
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="cwmc_id_source_supply")
	private CatalogWmc Source;
	*/
	
	@Getter
	@Setter
	@Column(name="coll_source_supply")
	private String collSourceSupply;
	
	@Getter
	@Setter
	@Column(name="coll_location_specimen")
	private String collLocationSpecimen;
	
	@Getter
	@Setter
	@Column(name="coll_date_entry")
	private Date collDateEntry;
	
	@Getter
	@Setter
	@Column(name="coll_status")
	private Boolean collStatus;
	
	@Getter
	@Setter
	@Column(name="coll_input_type")
	private String collInputType;
	
	@Getter
	@Setter
	@Column(name="coll_import_license_cites")
	private String collImportLicenseCites;
	
	@Getter
	@Setter
	@Column(name="coll_import_license_no_cites")
	private String collImportLicenseNoCites;
	
	@Getter
	@Setter
	@Column(name="coll_export_license_cites")
	private String collExportLicenseCites;
	
	@Getter
	@Setter
	@Column(name="coll_export_license_no_cites")
	private String collExportLicenseNoCites;
	
	
	//Campos exclusivos de Zoocriadero,Vivero
	@Getter
	@Setter
	@Column(name="coll_quantity")
	private Integer collQuantity;
	
	@Getter
	@Setter
	@Column(name="coll_quota")
	private Integer collQuota;
	
	@Getter
	@Setter
	@Column(name="coll_males_number")
	private Integer collMalesNumber;
	
	@Getter
	@Setter
	@Column(name="coll_females_number")
	private Integer collFemalesNumber;
	
	
	//Campos exclusivos de Museos,Herbarios
	
	@Getter
	@Setter
	@Column(name="coll_barcode")
	private String collBarcode;
	
	@Getter
	@Setter
	@Column(name="gelo_id")
	private Integer geloId;
	
	@Getter
	@Setter
	@Column(name="coll_state_specimen")
	private String collStateSpecimen;
	
	@Getter
	@Setter
	@Column(name="coll_id_museum_herbal")
	private String collIdMuseumHerbal;
	
	@Getter
	@Setter
	@Column(name="coll_collection_number")
	private String collCollectionNumber;
	
	@Getter
	@Setter
	@Column(name="coll_coordinate_x")
	private Double collCoordinateX;
	
	@Getter
	@Setter
	@Column(name="coll_coordinate_y")
	private Double collCoordinateY;
	
	@Getter
	@Setter
	@Column(name="coll_min_altitude")
	private Integer collMinAltitude;
	
	@Getter
	@Setter
	@Column(name="coll_max_altitude")
	private Integer collMaxAltitude;
	
	@Getter
	@Setter
	@Column(name="coll_collection_date")
	private Date collCollectionDate;
	
	/*
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="peop_id")
	private People people;
	*/
	
	@Getter
	@Setter
	@Column(name="coll_collector_name")
	private String collCollectorName;
	
	
	@Getter
	@Setter
	@Column(name="coll_user_create")
	private String collUserCreate;
	
	@Getter
	@Setter
	@Column(name="coll_date_create")
	private Date collDateCreate;
	
	@Getter
	@Setter
	@Column(name="coll_user_update")
	private String collUserUpdate;
	
	@Getter
	@Setter
	@Column(name="coll_date_update")
	private Date collDateUpdate;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="dsipt_id")
	private DatasetIpt datasetIPT;
	
	@Getter
	@Setter
	@Column(name="coll_status_ipt")
	private Boolean collStatusIPT;	
	
}