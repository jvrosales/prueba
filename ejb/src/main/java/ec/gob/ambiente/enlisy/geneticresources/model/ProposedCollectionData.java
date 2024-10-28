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
 * The persistent class for the proposed_collection_data database table.
 * 
 */
@Entity
@Table(name="proposed_collection_data", schema="biodiversity")
@NamedQuery(name="ProposedCollectionData.findAll", query="SELECT o FROM ProposedCollectionData o where o.prcdStatus = true")
public class ProposedCollectionData implements Serializable,Cloneable {
	private static final long serialVersionUID = 1L;
		
	public ProposedCollectionData clone(ProposedCollection proposedCollection) throws CloneNotSupportedException {
		ProposedCollectionData clone= (ProposedCollectionData)super.clone();
		clone.setPrcdId(null);
		clone.setPrcdUserUpdate(null);
		clone.setPrcdDateUpdate(null);
		clone.setProposedCollection(proposedCollection);
		return clone;
	}
		

	public ProposedCollectionData() {		
	}
	
	public ProposedCollectionData(GeneticResourcesCatalog geneticResourcesCatalog,ProposedCollection proposedCollection) {
		this.typeInformation=geneticResourcesCatalog;
		this.proposedCollection=proposedCollection;
	}

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="prcd_id")
	private Integer prcdId;
	
	@Getter
	@Setter
	@Column(name="prcd_value")
	private String prcdValue;	
	
	@Getter
	@Setter
	@Column(name="prcd_status")
	private Boolean prcdStatus;
	
	@Getter
	@Setter
	@Column(name="prcd_user_create")
	private String prcdUserCreate;
	
	@Getter
	@Setter
	@Column(name="prcd_date_create")
	private Date prcdDateCreate;
	
	@Getter
	@Setter
	@Column(name="prcd_user_update")
	private String prcdUserUpdate;
	
	@Getter
	@Setter
	@Column(name="prcd_date_update")
	private Date prcdDateUpdate;
	
	@Getter
	@Setter
	@Column(name="prcd_original_record")
	private Boolean prcdOriginalRecord;

	//bi-directional many-to-one association to ProposedCollection
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="prco_id")
	private ProposedCollection proposedCollection;
	
	//bi-directional many-to-one association to Catalog
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="grca_id_type_information")
	private GeneticResourcesCatalog typeInformation;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="exau_id")
	private ExportAuthorization exportAuthorization;
	
}
