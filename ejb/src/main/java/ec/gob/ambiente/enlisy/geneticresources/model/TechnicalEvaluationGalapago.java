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
 * The persistent class for the technical_evaluation_galapagos database table.
 * 
 */
@Entity
@Table(name="technical_evaluation_galapagos", schema="biodiversity")
@NamedQuery(name="TechnicalEvaluationGalapago.findAll", query="SELECT t FROM TechnicalEvaluationGalapago t")
public class TechnicalEvaluationGalapago implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tega_id")
	private Integer tegaId;	

	@Getter
	@Setter
	@Column(name="tega_date_create")
	private Date tegaDateCreate;

	@Getter
	@Setter
	@Column(name="tega_date_update")
	private Date tegaDateUpdate;

	@Getter
	@Setter
	@Column(name="tega_investigation_field")
	private String tegaInvestigationField;

	@Getter
	@Setter
	@Column(name="tega_negative_impacts")
	private String tegaNegativeImpacts;

	@Getter
	@Setter
	@Column(name="tega_project_benefits")
	private String tegaProjectBenefits;

	@Getter
	@Setter
	@Column(name="tega_project_includes")
	private String tegaProjectIncludes;

	@Getter
	@Setter
	@Column(name="tega_recommendation")
	private String tegaRecommendation;

	@Getter
	@Setter
	@Column(name="tega_status")
	private Boolean tegaStatus;

	@Getter
	@Setter
	@Column(name="tega_user_create")
	private String tegaUserCreate;

	@Getter
	@Setter
	@Column(name="tega_user_update")
	private String tegaUserUpdate;
	
	@Getter
	@Setter
	@Column(name="tega_other_investigation_field")
	private String tegaOtherInvestigationField;
	
	@Getter
	@Setter
	@Column(name="tega_other_project_benefit")
	private String tegaOtherProjectBenefit;
	
	@Getter
	@Setter
	@Column(name="tega_other_project_includes")
	private String tegaOtherProjectIncludes;
	
	@Getter
	@Setter
	@Column(name="tega_islands")
	private String tegaIslands;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="prco_id")
	private ProposedCollection proposedCollection;

	public TechnicalEvaluationGalapago() {
	}
	
	public TechnicalEvaluationGalapago(ProposedCollection proposedCollection) {
		this.proposedCollection = proposedCollection;
	}

	
}