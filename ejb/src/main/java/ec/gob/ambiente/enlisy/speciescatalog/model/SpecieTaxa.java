package ec.gob.ambiente.enlisy.speciescatalog.model;

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
import javax.persistence.Transient;

import ec.gob.ambiente.enlisy.dao.ConvertibleEntity;
import ec.gob.ambiente.enlisy.redlist.model.RedListsEcuador;
import ec.gob.ambiente.enlisy.redlist.model.RedListsUicn;
import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the higher_classifications database table.
 * 
 */
@Entity
@Table(name = "species_taxa", schema = "biodiversity")
public class SpecieTaxa extends ConvertibleEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SPECIE_TAXA_GENERATOR")
    @SequenceGenerator(name = "SPECIE_TAXA_GENERATOR", initialValue = 1, sequenceName = "seq_spta_id", schema = "biodiversity", allocationSize = 1)
    @Column(name = "spta_id")
	private Integer sptaId = Integer.valueOf(0);

	/**
	 * id de la clasificacion taxonomica
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "hicl_id")
	private HigherClassification higherClassification;

	/**
	 * id de status taxonomico
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "tast_id")
	private TaxonomicStatus taxonomicStatus;

	/**
	 * id rango taxonomico
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "tara_id")
	private TaxaRank taxaRank;

	/**
	 * id cite
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "cite_id")
	private Cite cite;

	/**
	 * id Lista Roja Ecuador
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "rlec_id")
	private RedListsEcuador redlistsEcuador;

	/**
	 * id Lista Roja Ecuador
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "rlui_id")
	private RedListsUicn redlistsUicn;

	/**
	 * epiteto especifico infraespecifico
	 */
	@Getter
	@Setter
	@Column(name = "spta_specific_infraspecifc_epit")
	private String sptaSpecificInfraspecifcEpit;

	/**
	 * Nombre cientifico
	 */
	@Getter
	@Setter
	@Column(name = "spta_scientific_name")
	private String sptaScientificName;

	/**
	 * GUI identificador unico
	 */
	@Getter
	@Setter
	@Column(name = "spta_scientific_gui")
	private String sptaScientificGui;

	/**
	 * Nombre lista roja de ecuador
	 */
	@Getter
	@Setter
	@Column(name = "spta_red_list_ec")
	private String sptaRedListEc;

	/**
	 * Criterios lista roja ecuador
	 */
	@Getter
	@Setter
	@Column(name = "spta_red_list_ec_criteria")
	private String sptaRedListEcCriteria;

	/**
	 * La especie se encuentra en ecuador
	 */
	@Getter
	@Setter
	@Column(name = "spta_ecuador")
	private Boolean sptaEcuador;

	/**
	 * La especie es endemica
	 */
	@Getter
	@Setter
	@Column(name = "spta_endemic")
	private Boolean sptaEndemic;

	/**
	 * La especie es exotica
	 */
	@Getter
	@Setter
	@Column(name = "spta_exotic")
	private Boolean sptaExotic;

	/**
	 * La especie es domestica
	 */
	@Getter
	@Setter
	@Column(name = "spta_domestic")
	private Boolean sptaDomestic;

	/**
	 * La especie es nativa
	 */
	@Getter
	@Setter
	@Column(name = "spta_native")
	private Boolean sptaNative;

	/**
	 * La especie es migratoria
	 */
	@Getter
	@Setter
	@Column(name = "spta_migratory")
	private Boolean sptaMigratory;

	/**
	 * La especie es invasora
	 */
	@Getter
	@Setter
	@Column(name = "spta_alien")
	private Boolean sptaAlien;

	/**
	 * Indica si es nombre cientifico aceptado
	 */
	@Getter
	@Setter
	@Column(name = "spta_is_accepted_name")
	private Boolean sptaIsAcceptedName;

	/**
	 * nombre cientifico aceptado
	 */
	@Getter
	@Setter
	@Column(name = "spta_accepted_name")
	private String sptaAcceptedName;

	/**
	 * autor nombre centifico
	 */
	@Getter
	@Setter
	@Column(name = "spta_scientific_name_authorship")
	private String sptaScientificNameAuthorship;

	/**
	 * anio de la publicacion
	 */
	@Getter
	@Setter
	@Column(name = "spta_name_published_year")
	private String sptaNamePublishedYear;

	/**
	 * observaciones
	 */
	@Getter
	@Setter
	@Column(name = "spta_taxon_remarks")
	private String sptaTaxonRemarks;

	/**
	 * indica el codigo de nomenclatura bajo el cual se construye el nombre
	 * cientifico
	 */
	@Getter
	@Setter
	@Column(name = "spta_nomenclatural_code")
	private String sptaNomenclaturalCode;

	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name = "spta_user_create")
	private String sptaUserCreate;

	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name = "spta_date_create")
	private Date sptaDateCreate;

	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name = "spta_user_update")
	private String sptaUserUpdate;

	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name = "spta_date_update")
	private Date sptaDateUpdate;

	/**
	 * Bandera que indica si el registro esta activo
	 */
	@Getter
	@Setter
	@Column(name = "spta_status")
	private Boolean sptaStatus;

	/**
	 * Almacena el arbol de jerarquía de los rangos taxonomicos concatenando los id
	 * de cada nivel
	 */
	@Getter
	@Setter
	@Column(name = "spta_hierarchy_code")
	private String sptaHierarchyCode;

	/**
	 * Fuente nombre comun
	 */
	@Getter
	@Setter
	@Column(name = "spta_taxonomic_source_tmp")
	private String sptaNameAccordingTo;

	/**
	 * Nombre rango
	 */
	@Getter
	@Setter
	@Column(name = "spta_taxon_rank_name")
	private String sptaTaxonRankName;

	/**
	 * Nombre status taxonomico
	 */
	@Getter
	@Setter
	@Column(name = "spta_taxonomic_status_name")
	private String sptaTaxonomicStatusName;

	/**
	 * Bandera que indica si es taxon correcto
	 */
	@Getter
	@Setter
	@Column(name = "spta_correct_tax")
	private Boolean sptaCorrectTax;

	/**
	 * Registro verificado por
	 */
	@Getter
	@Setter
	@Column(name = "spta_verified_for")
	private String sptaVerifiedFor;

	/**
	 * Codigo para buscar
	 */
	@Getter
	@Setter
	@Column(name = "spta_search_code")
	private String sptaSearchCode;

	/**
	 * Codigo para buscar
	 */
	@Getter
	@Setter
	@Column(name = "spta_coment")
	private String sptaComent;

	/**
	 * Es registro verificado
	 */
	@Getter
	@Setter
	@Column(name = "spta_is_verified")
	private Boolean sptaIsVerified;

	/**
	 * criterios lista roja uicn
	 */
	@Getter
	@Setter
	@Column(name = "spta_red_list_uicn_criteria")
	private String sptaRedListUicnCriteria;

	/**
	 * nombre lista roja uicn
	 */
	@Getter
	@Setter
	@Column(name = "spta_red_list_uicn")
	private String sptaRedListUicn;

	/**
	 * id Fuente Taxonomica
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "taso_id")
	private TaxonomicSource taxonomicSource;

	/**
	 * id del taxon correcto
	 */
	@Getter
	@Setter
	@Column(name = "spta_correct_tax_id")
	private Integer sptaCorrectTaxId;

	/**
	 * nombre del taxon correcto
	 */
	@Getter
	@Setter
	@Column(name = "spta_correct_tax_name")
	private String sptaCorrectTaxName;

	/**
	 * Nombre cites
	 */
	@Getter
	@Setter
	@Column(name = "spta_cites_name")
	private String sptaCiteName;
	
	/**
	 * Nombre cites
	 */
	@Getter
	@Setter
	@Column(name = "spta_cites_criteria")
	private String sptaCitesCriteria;
	
	/**
	 * campo que guarda concatenacion de los nombres de los rangos superiores 
	 */
	@Getter
	@Setter
	@Column(name = "spta_higher_classification")
	private String sptaHigherClassification;

	@Override
	public long getId() {
		return getSptaId();
	}

	@Override
	public String getDescription() {
		return getSptaScientificName();
	}

	@Transient
	private boolean selected = false;

	@Transient
	public boolean isSelected() {
		return selected;
	}

	@Transient
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Transient
	private String venaVernacularName = "";

	@Transient
	public String getVenaVernacularName() {
		return venaVernacularName;
	}

	@Transient
	public void setVenaVernacularName(String venaVernacularName) {
		this.venaVernacularName = venaVernacularName;
	}

	@Transient
	public boolean hasPhoto = false;

	@Transient
	public boolean isHasPhoto() {
		return hasPhoto;
	}

	@Transient
	public void setHasPhoto(boolean hasPhoto) {
		this.hasPhoto = hasPhoto;
	}

	@Transient
	private Boolean prioritized;

	@Transient
	public Boolean getPrioritized() {
		return prioritized;
	}

	@Transient
	public void setPrioritized(Boolean prioritized) {
		this.prioritized = prioritized;
	}

	@Transient
	private String order;

	@Transient
	public String getOrder() {
		return order;
	}

	@Transient
	public void setOrder(String order) {
		this.order = order;
	}

	@Transient
	private String family;

	@Transient
	public String getFamily() {
		return family;
	}

	@Transient
	public void setFamily(String family) {
		this.family = family;
	}

	@Transient
	private String kingdom;

	@Transient
	public String getKingdom() {
		return kingdom;
	}

	@Transient
	public void setKingdom(String kingdom) {
		this.kingdom = kingdom;
	}

	/**
	 * campo que guarda concatenacion de los grupos de criterios de lista roja nacional
	 */
	@Getter
	@Setter
	@Column(name = "spta_group_criteria_lrn")
	private String sptaGroupCriteriaLrn;
	
	/**
	 * Anio de incorporacion CITES
	 */
	@Getter
	@Setter
	@Column(name = "spta_cites_year")
	private Date sptaCitesYear;
	
	/**
	 * Fecha de incorporación a Convención sobre la conservación de las especies migratorias de animales silvestres
	 */
	@Getter
	@Setter
	@Column(name = "spta_cms_date")
	private Date sptaCmsDate;
	
	/**
	 * Bandera que indica si la especie ha sido identificada con potencial de uso o aprovechamiento comercial
	 */
	@Getter
	@Setter
	@Column(name = "spta_comercial_use")
	private Boolean sptaComercialUse;
	
	/**
	 * CMS
	 */
	@Getter
	@Setter
	@Column(name = "spta_cms")
	private String sptaCms;
	
	/**
	 * id Codigo Nomenclatural
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="noco_id")
	private NomenclaturalCode nomenclaturalCode;
	
	/**
	 * Bandera que indica si la especie es continental
	 */
	@Getter
	@Setter
	@Column(name = "spta_continental")
	private Boolean sptaContinental;
	
	/**
	 * Bandera que indica si la especie es insular
	 */
	@Getter
	@Setter
	@Column(name = "spta_insular")
	private Boolean sptaInsular;

}