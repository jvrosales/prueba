package ec.gob.ambiente.enlisy.speciescatalog.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the taxa_classification database table.
 * 
 */
@Entity
@Table(name="vw_taxa_classification", schema="biodiversity")
public class VwTaxaClassification implements Serializable {

	private static final long serialVersionUID = 7983025632702435432L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@Column(name="id_taxon")
	private Integer idTaxon;
	
	@Getter
	@Setter
	@Column(name="gui")
	private String gui;
	
	@Getter
	@Setter
	@Column(name="id_taxon_parent")
	private Integer idTaxonParent;
	
	@Getter
	@Setter
	@Column(name="taxon_name")
	private String taxonName;
	
	@Getter
	@Setter
	@Column(name="scientific_name")
	private String scientificName;
	
	@Getter
	@Setter
	@Column(name="id_rank")
	private Integer idRank;
	
	@Getter
	@Setter
	@Column(name="rank_name")
	private String rankName;
	
	@Getter
	@Setter
	@Column(name="authorship")
	private String authorship;
	
	@Getter
	@Setter
	@Column(name="published_year")
	private String publishedYear;
	
	@Getter
	@Setter
	@Column(name="nomenclatural_code")
	private String nomenclaturalCode;
	
	@Getter
	@Setter
	@Column(name="correct_tax")
	private Boolean correctTax;
	
	@Getter
	@Setter
	@Column(name="is_accepted_name")
	private Boolean isAcceptedName;
	
	@Getter
	@Setter
	@Column(name="accepted_name")
	private String acceptedName;
	
	@Getter
	@Setter
	@Column(name="id_taxonomic_status")
	private Integer idTaxonomicStatus;
	
	@Getter
	@Setter
	@Column(name="id_taxonomic_source")
	private Integer idTaxonomicSource;
	
	@Getter
	@Setter
	@Column(name="hierarchy_code")
	private String hierarchyCode;
	
	@Getter
	@Setter
	@Column(name="higher_classification")
	private String higherClassification;
	
	@Getter
	@Setter
	@Column(name="search_code")
	private String searchCode;
	
	@Getter
	@Setter
	@Column(name="status")
	private Boolean status;
	
	@Getter
	@Setter
	@Column(name="ecuador")
	private Boolean ecuador;
	
	@Getter
	@Setter
	@Column(name="endemic")
	private Boolean endemic;
	
	@Getter
	@Setter
	@Column(name="exotic")
	private Boolean exotic;
	
	@Getter
	@Setter
	@Column(name="domestic")
	private Boolean domestic;
	
	@Getter
	@Setter
	@Column(name="native")
	private Boolean nativo;
	
	@Getter
	@Setter
	@Column(name="migratory")
	private Boolean migratory;
	
	@Getter
	@Setter
	@Column(name="alien")
	private Boolean alien;
	
	@Getter
	@Setter
	@Column(name="id_red_list_ec")
	private Integer idRedListEc;
	
	@Getter
	@Setter
	@Column(name="red_list_ec")
	private String redListEc;
	
	@Getter
	@Setter
	@Column(name="id_red_list_uicn")
	private Integer idRedListUicn;
	
	@Getter
	@Setter
	@Column(name="red_list_uicn")
	private String redListUicn;

	@Getter
	@Setter
	@Column(name="id_cites")
	private Integer idCites;
	
	@Getter
	@Setter
	@Column(name="cites")
	private String cites;
	
	@Getter
	@Setter
	@Column(name="correct_tax_id")
	private Integer correctTaxId;
	
	@Getter
	@Setter
	@Column(name="correct_tax_name")
	private String correctTaxName;

}
