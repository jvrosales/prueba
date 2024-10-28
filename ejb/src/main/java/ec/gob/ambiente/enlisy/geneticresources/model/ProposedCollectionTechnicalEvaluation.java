package ec.gob.ambiente.enlisy.geneticresources.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the proposed_collection_technical_evaluation database table.
 * 
 */
@Entity
@Table(name="proposed_collection_technical_evaluation", schema="biodiversity")
@NamedQuery(name="ProposedCollectionTechnicalEvaluation.findAll", query="SELECT p FROM ProposedCollectionTechnicalEvaluation p")
public class ProposedCollectionTechnicalEvaluation implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public ProposedCollectionTechnicalEvaluation() {
	}
	
	public ProposedCollectionTechnicalEvaluation(ProposedCollection proposedCollection) {
		this.proposedCollection=proposedCollection;
	}
	
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pcte_id")
	private Integer pcteId;

	@Getter
	@Setter
	@Column(name="pcte_date_create")
	private Date pcteDateCreate;

	@Getter
	@Setter
	@Column(name="pcte_date_update")
	private Date pcteDateUpdate;

	@Getter
	@Setter
	@Column(name="pcte_status")
	private Boolean pcteStatus;

	@Getter
	@Setter
	@Column(name="pcte_user_create")
	private String pcteUserCreate;

	@Getter
	@Setter
	@Column(name="pcte_user_update")
	private String pcteUserUpdate;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="prco_id")
	private ProposedCollection proposedCollection;
	
	@Getter
	@Setter
	@Column(name="pcte_score")
	private Double pcteScore;
	
	@Getter
	@Setter
	@Column(name="pcte_observation")
	private String pcteObservation; 
	
	@Getter
	@Setter
	@Column(name="pcte_type")
	private Boolean pcteType;
	
	@Getter
	@Setter
	@Column(name="grca_id_reason")
	private Integer grcaIdReason;
	
	@Getter
	@Setter
	@Column(name="pcte_proposed_evaluation")
	private Boolean pcteProposedEvaluation;
	
	@Getter
	@Setter
	@Column(name="pcte_evaluation_result")
	private String pcteeEvaluationResult;
}
