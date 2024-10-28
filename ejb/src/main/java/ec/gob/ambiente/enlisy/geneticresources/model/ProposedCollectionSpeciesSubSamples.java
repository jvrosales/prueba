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
 * The persistent class for the proposed_collection_species database table.
 * 
 */
@Entity
@Table(name="proposed_collection_species_subsample", schema="biodiversity")
@NamedQuery(name="ProposedCollectionSpeciesSubSamples.findAll", query="SELECT o FROM ProposedCollectionSpeciesSubSamples o where o.pcssStatus = true")
public class ProposedCollectionSpeciesSubSamples implements Serializable,Cloneable {
	private static final long serialVersionUID = 1L;
	
	public ProposedCollectionSpeciesSubSamples clone(ProposedCollectionSpecies proposedCollectionSpecies) throws CloneNotSupportedException {
		
		ProposedCollectionSpeciesSubSamples clone= (ProposedCollectionSpeciesSubSamples)super.clone();
		clone.setPcssId(null);
		clone.setPcssUserUpdate(null);
		clone.setPcssDateUpdate(null);
		clone.setProposedCollectionSpecies(proposedCollectionSpecies);
		return clone;
	}		

	public ProposedCollectionSpeciesSubSamples() {		
	}
	
	public ProposedCollectionSpeciesSubSamples(ProposedCollectionSpecies proposedCollectionSpecies) {
		this.proposedCollectionSpecies=proposedCollectionSpecies;
	}

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pcss_id")
	private Integer pcssId;
	
	@Getter
	@Setter	
	@Column(name="pcss_subsample_type")
	private String pcssSubsampleType;
	
	@Getter
	@Setter
	@Column(name = "pcss_subsample_number")
	private Integer pcssSubsampleNumber;
		
	@Getter
	@Setter
	@Column(name="pcss_status")
	private Boolean pcssStatus;
	
	@Getter
	@Setter
	@Column(name="pcss_user_create")
	private String pcssUserCreate;
	
	@Getter
	@Setter
	@Column(name="pcss_date_create")
	private Date pcssDateCreate;
	
	@Getter
	@Setter
	@Column(name="pcss_user_update")
	private String pcssUserUpdate;
	
	@Getter
	@Setter
	@Column(name="pcss_date_update")
	private Date pcssDateUpdate;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="prcs_id")
	private ProposedCollectionSpecies proposedCollectionSpecies;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pcssId == null) ? 0 : pcssId.hashCode());
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
		ProposedCollectionSpeciesSubSamples other = (ProposedCollectionSpeciesSubSamples) obj;
		if (pcssId != null && other.pcssId!=null) {
			return pcssId.equals(other.pcssId);
		}
		return false;
	}
}