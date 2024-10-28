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
 * The persistent class for the proposed_collection_evaluation_values database table.
 * 
 */
@Entity
@Table(name="proposed_collection_evaluation_values", schema="biodiversity")
@NamedQuery(name="ProposedCollectionEvaluationValues.findAll", query="SELECT o FROM ProposedCollectionEvaluationValues o where o.pcevStatus = true")
public class ProposedCollectionEvaluationValues implements Serializable {
	private static final long serialVersionUID = 1L;
		

	public ProposedCollectionEvaluationValues() {		
	}
	
	public ProposedCollectionEvaluationValues(EvaluationItems evaluationItems,ProposedCollectionTechnicalEvaluation proposedCollectionTechnicalEvaluation) {				
		this.evaluationItems=evaluationItems;
		this.proposedCollectionTechnicalEvaluation=proposedCollectionTechnicalEvaluation;
	}

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pcev_id")
	private Integer pcevId;
	
	@Getter
	@Setter
	@Column(name="pcev_value")
	private Integer pcevValue;
	
	@Getter
	@Setter
	@Column(name="pcev_weighing")
	private Double pcevWeighing;
	
	@Getter
	@Setter
	@Column(name="pcev_status")
	private Boolean pcevStatus;
	
	@Getter
	@Setter
	@Column(name="pcev_user_create")
	private String pcevUserCreate;
	
	@Getter
	@Setter
	@Column(name="pcev_date_create")
	private Date pcevDateCreate;
	
	@Getter
	@Setter
	@Column(name="pcev_user_update")
	private String pcevUserUpdate;
	
	@Getter
	@Setter
	@Column(name="pcev_date_update")
	private Date pcevDateUpdate;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="evit_id")
	private EvaluationItems evaluationItems;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="pcte_id")
	private ProposedCollectionTechnicalEvaluation proposedCollectionTechnicalEvaluation;			
}