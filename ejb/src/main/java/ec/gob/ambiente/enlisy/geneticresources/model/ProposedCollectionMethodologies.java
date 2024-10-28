package ec.gob.ambiente.enlisy.geneticresources.model;

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

import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the proposed_collection_methodologies database table.
 * 
 */
@Entity
@Table(name="proposed_collection_methodologies", schema="biodiversity")
@NamedQuery(name="ProposedCollectionMethodologies.findAll", query="SELECT o FROM ProposedCollectionMethodologies o where o.prcmStatus = true")
public class ProposedCollectionMethodologies implements Serializable,Cloneable {
	private static final long serialVersionUID = 1L;
	
	public ProposedCollectionMethodologies clone(ProposedCollection proposedCollection) throws CloneNotSupportedException {
		ProposedCollectionMethodologies clone= (ProposedCollectionMethodologies)super.clone();
		clone.setPrcmId(null);
		clone.setPrcmUserUpdate(null);
		clone.setPrcmDateUpdate(null);
		clone.setProposedCollection(proposedCollection);
		return clone;
	}
		

	public ProposedCollectionMethodologies() {		
	}
	
	public ProposedCollectionMethodologies(String biologicalGroups,ProposedCollection proposedCollection) {		
		this.prcmBiologicalGroups=biologicalGroups;
		this.proposedCollection=proposedCollection;
		
	}

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="prcm_id")
	private Integer prcmId;
	
	@Getter
	@Setter
	@Column(name = "prcm_sampling_techniques")
	private String prcmSamplingTechniques;
	
	@Getter
	@Setter
	@Column(name="prcm_sampling_design")
	private String prcmSamplingDesign;
	
	@Getter
	@Setter
	@Column(name="prcm_sampling_effort")
	private String prcmSamplingEffort;
	
	@Getter
	@Setter
	@Column(name="prcm_sampling_handling")
	private Boolean prcmSamplingHandling;
	
	@Getter
	@Setter
	@Column(name="prcm_sampling_definitive_collection")
	private Boolean prcmSamplingDefinitiveCollection;
	
	@Getter
	@Setter
	@Column(name="prcm_sampling_data_collection")
	private Boolean prcmSamplingDataCollection;
	
	@Getter
	@Setter
	@Column(name="prcm_laboratory_methodology")
	private String prcmLaboratoryMethodology;
	
	@Getter
	@Setter
	@Column(name="prcm_countryside_methodology")
	private String prcmCountrysideMethodology;
	
	@Getter
	@Setter
	@Column(name="prcm_laboratory_methodology_updated")
	private String prcmLaboratoryMethodologyUpdated;
	
	@Getter
	@Setter
	@Column(name="prcm_countryside_methodology_updated")
	private String prcmCountrysideMethodologyUpdated;
	
	@Getter
	@Setter
	@Column(name="prcm_status")
	private Boolean prcmStatus;
	
	@Getter
	@Setter
	@Column(name="prcm_user_create")
	private String prcmUserCreate;
	
	@Getter
	@Setter
	@Column(name="prcm_date_create")
	private Date prcmDateCreate;
	
	@Getter
	@Setter
	@Column(name="prcm_user_update")
	private String prcmUserUpdate;
	
	@Getter
	@Setter
	@Column(name="prcm_date_update")
	private Date prcmDateUpdate;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="prco_id")
	private ProposedCollection proposedCollection;
	
	@Getter
	@Setter
	@Column(name="prcm_biological_groups")
	private String prcmBiologicalGroups;
	
	
}
