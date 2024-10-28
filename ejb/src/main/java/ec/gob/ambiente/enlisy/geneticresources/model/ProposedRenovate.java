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
 * The persistent class for the proposed_renovate database table.
 * 
 */
@Entity
@Table(name="proposed_renovate", schema="biodiversity")
@NamedQuery(name="ProposedRenovate.findAll", query="SELECT p FROM ProposedRenovate p")
public class ProposedRenovate implements Serializable {
	private static final long serialVersionUID = 1L;

	
	public ProposedRenovate() {		
	}
	
	public ProposedRenovate(ProposedCollection proposedCollection) {
		this.proposedCollection=proposedCollection;
	}
	
	
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="prre_id")
	private Integer prreId;


	@Getter
	@Setter
	@Column(name="prre_date_create")
	private Date prreDateCreate;

	@Getter
	@Setter
	@Column(name="prre_date_update")
	private Date prreDateUpdate;

	@Getter
	@Setter
	@Column(name="prre_goals")
	private String prreGoals;

	@Getter
	@Setter
	@Column(name="prre_introduction")
	private String prreIntroduction;

	@Getter
	@Setter
	@Column(name="prre_project_scope")
	private String prreProjectScope;

	@Getter
	@Setter
	@Column(name="prre_recommendations")
	private String prreRecommendations;

	@Getter
	@Setter
	@Column(name="prre_results_achieved")
	private String prreResultsAchieved;

	@Getter
	@Setter
	@Column(name="prre_statistical_analysis")
	private String prreStatisticalAnalysis;

	@Getter
	@Setter
	@Column(name="prre_status")
	private Boolean prreStatus;

	@Getter
	@Setter
	@Column(name="prre_user_create")
	private String prreUserCreate;

	@Getter
	@Setter
	@Column(name="prre_user_update")
	private String prreUserUpdate;

	//bi-directional many-to-one association to ProposedCollection
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="prco_id")
	private ProposedCollection proposedCollection;

	
}