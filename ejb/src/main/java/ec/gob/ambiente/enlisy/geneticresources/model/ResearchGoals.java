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
 * The persistent class for the application database table.
 * 
 */
@Entity
@Table(name="research_goals", schema="biodiversity")
@NamedQuery(name="ResearchGoals.findAll", query="SELECT o FROM ResearchGoals o")
public class ResearchGoals implements Serializable,Cloneable {
	private static final long serialVersionUID = 1L;
	
	public ResearchGoals clone(ProposedCollection proposedCollection) throws CloneNotSupportedException {
		
		ResearchGoals clone= (ResearchGoals)super.clone();
		clone.setRegoId(null);
		clone.setRegoUserUpdate(null);
		clone.setRegoDateUpdate(null);
		clone.setProposedCollection(proposedCollection);
		return clone;
	}
	
	public ResearchGoals() {		
	}
	
	public ResearchGoals(ProposedCollection proposedCollection) {		
		this.proposedCollection=proposedCollection;
	}

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="rego_id")
	private Integer regoId;
    
    @Getter
	@Setter
	@Column(name="rego_description")
	private String regoDescription;
	
	@Getter
	@Setter
	@Column(name="rego_status")
	private Boolean regoStatus;
	
	@Getter
	@Setter
	@Column(name="rego_user_create")
	private String regoUserCreate;
	
	@Getter
	@Setter
	@Column(name="rego_date_create")
	private Date regoDateCreate;
	
	@Getter
	@Setter
	@Column(name="rego_user_update")
	private String regoUserUpdate;
	
	@Getter
	@Setter
	@Column(name="rego_date_update")
	private Date regoDateUpdate;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="prco_id")
	private ProposedCollection proposedCollection;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="goca_id")
	private GoalsCatalog goalsCatalog;
}