package ec.gob.ambiente.enlisy.geneticresources.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the proposed_collection_species database table.
 * 
 */
@Entity
@Table(name="proposed_collection_species", schema="biodiversity")
@NamedQuery(name="ProposedCollectionSpecies.findAll", query="SELECT o FROM ProposedCollectionSpecies o where o.prcsStatus = true")
public class ProposedCollectionSpecies implements Serializable,Cloneable {
	private static final long serialVersionUID = 1L;
	
	public ProposedCollectionSpecies clone(ProposedCollection proposedCollection) throws CloneNotSupportedException {		
		ProposedCollectionSpecies clone= (ProposedCollectionSpecies)super.clone();
		clone.setPrcsId(null);
		clone.setPrcsUserUpdate(null);
		clone.setPrcsDateUpdate(null);
		clone.setProposedCollection(proposedCollection);
		return clone;
	}		

	public ProposedCollectionSpecies() {		
	}
	
	public ProposedCollectionSpecies(ProposedCollection proposedCollection) {
		this.proposedCollection=proposedCollection;
	}

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="prcs_id")
	private Integer prcsId;
	
	@Getter
	@Setter	
	@Column(name="prcs_sample_type")
	private String prcsSampleType;
	
	@Getter
	@Setter
	@Column(name = "prcs_sample_number")
	private Integer prcsSampleNumber;
	
	@Getter
	@Setter
	@Column(name="prcs_sample_batch")
	private Integer prcsSampleBatch;
	
	@Getter
	@Setter
	@Column(name = "prcs_sample_number_description")
	private String prcsSampleNumberDescription;
	
	@Getter
	@Setter
	@Column(name="prcs_sample_batch_description")
	private String prcsSampleBatchDescription;
	
	@Getter
	@Setter
	@Column(name="prcs_specie_code")
	private String prcsSpecieCode;
	
	@Getter
	@Setter
	@Column(name="prcs_specie_name")
	private String prcsSpecieName;
	
	@Getter
	@Setter
	@Column(name="prcs_specie_genus")
	private String prcsSpecieGenus;
	
	@Getter
	@Setter
	@Column(name="prcs_specie_family")
	private String prcsSpecieFamily;
	
	@Getter
	@Setter
	@Column(name="prcs_specie_order")
	private String prcsSpecieOrder;
	
	@Getter
	@Setter
	@Column(name="prcs_specie_class")
	private String prcsSpecieClass;
	
	@Getter
	@Setter
	@Column(name="prcs_specie_phylum")
	private String prcsSpeciePhylum;
	
	@Getter
	@Setter
	@Column(name="prcs_specie_kingdom")
	private String prcsSpecieKingdom;
	
	@Getter
	@Setter
	@Column(name="prcs_endemism")
	private Boolean prcsEndemica;
	
	@Getter
	@Setter
	@Column(name="prcs_threatened")
	private Boolean prcsAmenazada;
	
	@Getter
	@Setter
	@Column(name="prcs_veda")
	private Boolean prcsVeda;
	
	@Getter
	@Setter
	@Column(name="prcs_cites")
	private Boolean prcsCites;
	
	@Getter
	@Setter
	@Column(name="prcs_location")
	private String prcsUbicacion;
	
	@Getter
	@Setter
	@Column(name="prcs_snap")
	private String prcsAreasProtegidas;
	
	@Getter
	@Setter
	@Column(name="prcs_forest")
	private String prcsBosquesProtectores;
	
	@Getter
	@Setter
	@Column(name="prcs_sites")
	private String prcsSitios;
		
	@Getter
	@Setter
	@Column(name="prcs_status")
	private Boolean prcsStatus;
	
	@Getter
	@Setter
	@Column(name="prcs_user_create")
	private String prcsUserCreate;
	
	@Getter
	@Setter
	@Column(name="prcs_date_create")
	private Date prcsDateCreate;
	
	@Getter
	@Setter
	@Column(name="prcs_user_update")
	private String prcsUserUpdate;
	
	@Getter
	@Setter
	@Column(name="prcs_date_update")
	private Date prcsDateUpdate;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="prco_id")
	private ProposedCollection proposedCollection;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="grca_id_taxonomy")
	private GeneticResourcesCatalog grcaIdTaxonomy;
	
	@Getter
	@Setter
	@Column(name="prcs_original_record")
	private Boolean prcsOriginalRecord;

	@Getter
	@Setter
	@Transient
	private String prcsMuestraType;
	
	@Getter
	@Setter
	@Transient
	private List<ProposedCollectionSpeciesSubSamples> listaSubmuestras;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((prcsId == null) ? 0 : prcsId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProposedCollectionSpecies other = (ProposedCollectionSpecies) obj;
		if (prcsId != null && other.prcsId!=null) {
			return prcsId.equals(other.prcsId);
		}
		return false;
	}
	
	
	
	
}
