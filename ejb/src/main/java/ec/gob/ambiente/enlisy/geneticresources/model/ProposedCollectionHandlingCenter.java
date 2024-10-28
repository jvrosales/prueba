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
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="proposed_collection_handling_center", schema="biodiversity")
public class ProposedCollectionHandlingCenter implements Serializable,Cloneable {
	private static final long serialVersionUID = 1L;
	
	public ProposedCollectionHandlingCenter clone(ProposedCollection proposedCollection) throws CloneNotSupportedException {
		ProposedCollectionHandlingCenter clone= (ProposedCollectionHandlingCenter)super.clone();
		clone.setPchcId(null);
		clone.setPchcUserUpdate(null);
		clone.setPchcDateUpdate(null);
		clone.setProposedCollection(proposedCollection);
		return clone;
	}

	public ProposedCollectionHandlingCenter() {		
	}
	
	public ProposedCollectionHandlingCenter(ProposedCollection proposedCollection, String biologicalGroups) {
		this.proposedCollection=proposedCollection;
		this.pchcBiologicalGroups=biologicalGroups;
	}
	
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pchc_id")
	private Integer pchcId;

	@Getter
	@Setter
	@Column(name="pchc_handling_center_id")
	private Integer pchcHandlingCenterId;
	
	@Getter
	@Setter
	@Column(name="pchc_handling_center_name")
	private String pchcHandlingCenterName;
	
	@Getter
	@Setter
	@Column(name="pchc_handling_foreign_center_name")
	private String pchcHandlingForeingCenterName;
	
	@Getter
	@Setter
	@Column(name="pchc_biological_groups")
	private String pchcBiologicalGroups;
	
	
	@Getter
	@Setter
	@Column(name="pchc_status")
	private Boolean pchcStatus;

	@Getter
	@Setter
	@Column(name="pchc_date_create")
	private Date pchcDateCreate;

	@Getter
	@Setter
	@Column(name="pchc_date_update")
	private Date pchcDateUpdate;

	@Getter
	@Setter
	@Column(name="pchc_user_create")
	private String pchcUserCreate;

	@Getter
	@Setter
	@Column(name="pchc_user_update")
	private String pchcUserUpdate;

	@Getter
	@Setter
	@Column(name="pchc_original_record")
	private Boolean pchcOriginalRecord;

	//bi-directional many-to-one association to ProposedCollection
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="prco_id")
	private ProposedCollection proposedCollection;


}
