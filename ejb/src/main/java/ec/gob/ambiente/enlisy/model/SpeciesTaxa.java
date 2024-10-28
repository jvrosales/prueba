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
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "species_taxa", schema = "biodiversity")
@NamedQuery(name = "SpeciesTaxa.findAll", query = "SELECT o FROM SpeciesTaxa o")

public class SpeciesTaxa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "spta_id")
	private Integer id;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "hicl_id")
	private HigherClassification hiclId;   
	
	@Getter
	@Setter
	@Column(name = "tast_id")
	private Integer tastId;
	
	@Getter
	@Setter
	@Column(name = "tara_id")
	private Integer taraId;
	
	@Getter
	@Setter
	@Column(name = "cite_id")
	private Integer citeId;
	
	@Getter
	@Setter
	@Column(name = "spta_specific_infraspecifc_epit")
	private String sptaSpecificInfraspecifcEpit;
	
	@Getter
	@Setter
	@Column(name = "spta_scientific_name")
	private String sptaScientificName;
		
	@Getter
	@Setter
	@Column(name = "spta_scientific_gui")
	private String sptaScientificGui;
	
	@Getter
	@Setter
	@Column(name = "spta_red_list_ec_criteria")
	private String sptaRedListEcCriteria;
	
	@Getter
	@Setter
	@Column(name = "spta_cites_criteria")
	private String sptaCitesCriteria;
		
	@Getter
	@Setter
	@Column(name = "spta_ecuador")
	private Boolean sptaEcuador;
	
	@Getter
	@Setter
	@Column(name = "spta_endemic")
	private Boolean sptaEndemic;
	
	@Getter
	@Setter
	@Column(name = "spta_exotic")
	private Boolean sptaExotic;
	
	@Getter
	@Setter
	@Column(name = "spta_domestic")
	private Boolean sptaDomestic;
	
	@Getter
	@Setter
	@Column(name = "spta_native")
	private Boolean sptaNative;
	
	@Getter
	@Setter
	@Column(name = "spta_migratory")
	private Boolean sptaMigratory;
	
	@Getter
	@Setter
	@Column(name = "spta_alien")
	private Boolean sptaAlien;
	
	@Getter
	@Setter
	@Column(name = "spta_is_accepted_name")
	private Boolean sptaIsAcceptedName;
	
	@Getter
	@Setter
	@Column(name = "spta_accepted_name")
	private String sptaAcceptedName;
	
	@Getter
	@Setter
	@Column(name = "spta_taxonomic_source_tmp")
	private String sptaTaxonomicSourceTmp;
	
	@Getter
	@Setter
	@Column(name = "spta_scientific_name_authorship")
	private String sptaScientificNameAuthorship;
	
	@Getter
	@Setter
	@Column(name = "spta_name_published_year")
	private String sptaNamePublishedYear;
	
	@Getter
	@Setter
	@Column(name = "spta_taxon_remarks")
	private String sptaTaxonRemarks;
	
	@Getter
	@Setter
	@Column(name = "spta_taxon_rank_name")
	private String sptaTaxonRankName;
	
	@Getter
	@Setter
	@Column(name = "spta_red_list_ec")
	private String sptaRedListEc;
	
	@Getter
	@Setter
	@Column(name = "spta_cites_name")
	private String sptaCitesName;
	
	@Getter
	@Setter
	@Column(name = "spta_taxonomic_status_name")
	private String sptaTaxonomicStatusName;
	
	@Getter
	@Setter
	@Column(name = "spta_nomenclatural_code")
	private String sptaNomenclaturalCode;
	
	@Getter
	@Setter
	@Column(name = "spta_status")
	private Boolean sptaStatus;
	
	@Getter
	@Setter
	@Column(name = "spta_user_create")
	private String sptaUserCreate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Getter
	@Setter
	@Column(name = "spta_date_create")
	private Date sptaDateCreate;
		
	@Getter
	@Setter
	@Column(name = "spta_user_update")
	private String sptaUserUpdate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Getter
	@Setter
	@Column(name = "spta_date_update")
	private Date sptaDateUpdate;
	
	@Getter
	@Setter
	@Column(name = "spta_hierarchy_code")
	private String sptaHierarchyCode;
	
	@Getter
	@Setter
	@Column(name = "spta_correct_tax")
	private Boolean sptaCorrectTax;
		
	@Getter
	@Setter
	@Column(name = "spta_verified_for")
	private String sptaVerifiedFor;
	
	@Getter
	@Setter
	@Column(name = "spta_search_code")
	private String sptaSearchCode;
	
	@Getter
	@Setter
	@Column(name = "spta_coment")
	private String sptaComent;
	
	@Getter
	@Setter
	@Column(name = "spta_is_verified")
	private Boolean sptaIsVerified;
	
	@Getter
	@Setter
	@Column(name = "spta_red_list_uicn_criteria")
	private String sptaRedListUicnCriteria;
	
	@Getter
	@Setter
	@Column(name = "spta_red_list_uicn")
	private String sptaRedListUicn;
	
	@Getter
	@Setter
	@Column(name = "rlec_id")
	private Integer rlecId;
	
	@Getter
	@Setter
	@Column(name = "rlui_id")
	private Integer rluiId;
	
	@Getter
	@Setter
	@Column(name = "rlsp_id")
	private Integer rlspId;
	
	@Getter
	@Setter
	@Column(name = "scdo_id")
	private Integer scdoId;
	
	@Getter
	@Setter
	@Column(name = "taso_id")
	private Integer tasoId;
	
	@Getter
	@Setter
	@Column(name = "spta_correct_tax_id")
	private Integer sptaCorrectTaxId;
	
	@Getter
	@Setter
	@Column(name = "spta_correct_tax_name")
	private String sptaCorrectTaxName;
	
	@Getter
	@Setter
	@Column(name = "spta_higher_classification")
	private String sptaHigherClassification;
	
	@Getter
	@Setter
	@Column(name = "noco_id")
	private Integer nocoId;
		
	@Getter
	@Setter
	@Column(name = "spta_group_criteria_lrn")
	private String sptaGroupCriteriaLrn;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Getter
	@Setter
	@Column(name = "spta_cites_year")
	private Date sptaCitesYear;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Getter
	@Setter
	@Column(name = "spta_cms_date")
	private Date sptaCmsDate;
	
	@Getter
	@Setter
	@Column(name = "spta_comercial_use")
	private Boolean sptaComercialUse;
	
	@Getter
	@Setter
	@Column(name = "spta_cms")
	private String sptaCms;
	
	@Getter
	@Setter
	@Column(name = "spta_continental")
	private Boolean sptaContinental;
	
	@Getter
	@Setter
	@Column(name = "spta_insular")
	private Boolean sptaInsular;
	
}
