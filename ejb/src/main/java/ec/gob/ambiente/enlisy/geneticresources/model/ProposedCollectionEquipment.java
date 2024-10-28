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
 * The persistent class for the proposed_collection_equipment database table.
 * 
 */
@Entity
@Table(name="proposed_collection_equipment", schema="biodiversity")
@NamedQuery(name="ProposedCollectionEquipment.findAll", query="SELECT o FROM ProposedCollectionEquipment o where o.prceStatus = true")
public class ProposedCollectionEquipment implements Serializable,Cloneable {
	private static final long serialVersionUID = 1L;
	
	public ProposedCollectionEquipment clone(ProposedCollection proposedCollection) throws CloneNotSupportedException {
		
		ProposedCollectionEquipment clone= (ProposedCollectionEquipment)super.clone();
		clone.setPrceId(null);
		clone.setPrceUserUpdate(null);
		clone.setPrceDateUpdate(null);
		clone.setProposedCollection(proposedCollection);
		return clone;
	}
		

	public ProposedCollectionEquipment() {		
	}
	
	public ProposedCollectionEquipment(ProposedCollection proposedCollection,GeneticResourcesCatalog typeEquipment,String biologicalGroups,EquipmentCatalog equipmentCatalog) {
		this.proposedCollection=proposedCollection;
		this.typeEquipment=typeEquipment;
		this.prceBiologicalGroups=biologicalGroups;
		this.equipmentCatalog=equipmentCatalog;
	}

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="prce_id")
	private Integer prceId;
	
	@Getter
	@Setter
	@Column(name="prce_status")
	private Boolean prceStatus;
	
	@Getter
	@Setter
	@Column(name="prce_user_create")
	private String prceUserCreate;
	
	@Getter
	@Setter
	@Column(name="prce_date_create")
	private Date prceDateCreate;
	
	@Getter
	@Setter
	@Column(name="prce_user_update")
	private String prceUserUpdate;
	
	@Getter
	@Setter
	@Column(name="prce_date_update")
	private Date prceDateUpdate;

	//bi-directional many-to-one association to ProposedCollection
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="prco_id")
	private ProposedCollection proposedCollection;
	
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="grca_id_equipment_type")
	private GeneticResourcesCatalog typeEquipment;
	
	@Getter
	@Setter	
	@Column(name="prce_biological_groups")
	private String prceBiologicalGroups;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="eqca_id")
	private EquipmentCatalog equipmentCatalog;
	
	
}