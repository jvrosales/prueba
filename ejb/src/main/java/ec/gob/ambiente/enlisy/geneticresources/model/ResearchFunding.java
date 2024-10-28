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
import ec.gob.ambiente.enlisy.model.Nationality;


/**
 * The persistent class for the research_funding database table.
 * 
 */
@Entity
@Table(name="research_funding", schema="biodiversity")
@NamedQuery(name="ResearchFunding.findAll", query="SELECT o FROM ResearchFunding o")
public class ResearchFunding implements Serializable,Cloneable {
	private static final long serialVersionUID = 1L;
	
	public ResearchFunding clone(ProposedCollection proposedCollection) throws CloneNotSupportedException {
		
		ResearchFunding clone= (ResearchFunding)super.clone();
		clone.setRefuId(null);
		clone.setRefuUserUpdate(null);
		clone.setRefuDateUpdate(null);
		clone.setProposedCollection(proposedCollection);
		return clone;
	}
	
	public ResearchFunding() {		
	}
	
	public ResearchFunding(ProposedCollection proposedCollection) {		
		this.proposedCollection=proposedCollection;
	}

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="refu_id")
	private Integer refuId;
    
    @Getter
	@Setter
	@Column(name="refu_name_entity")
	private String refuNameEntity;
    
    @Getter
	@Setter
	@Column(name="refu_public_amount")
	private Double refuPublicAmount;
    
    @Getter
	@Setter
	@Column(name="refu_private_amount")
	private Double refuPrivateAmount;
	
	@Getter
	@Setter
	@Column(name="refu_status")
	private Boolean refuStatus;
	
	@Getter
	@Setter
	@Column(name="refu_user_create")
	private String refuUserCreate;
	
	@Getter
	@Setter
	@Column(name="refu_date_create")
	private Date refuDateCreate;
	
	@Getter
	@Setter
	@Column(name="refu_user_update")
	private String refuUserUpdate;
	
	@Getter
	@Setter
	@Column(name="refu_date_update")
	private Date refuDateUpdate;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="prco_id")
	private ProposedCollection proposedCollection;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="nati_id")
	private Nationality Nationality;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="geca_id_type_entity")
	private GeneticResourcesCatalog geneticResourcesCatalog;
}