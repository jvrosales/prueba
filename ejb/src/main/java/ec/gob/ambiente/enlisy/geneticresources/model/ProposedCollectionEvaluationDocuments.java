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
import javax.persistence.Transient;

import ec.gob.ambiente.enlisy.wildlifeevent.model.BiodiversityGeneralCatalog;
import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the proposed_collection_evaluation_documents database table.
 * 
 */
@Entity
@Table(name="proposed_collection_document_evaluation", schema="biodiversity")
@NamedQuery(name="ProposedCollectionEvaluationDocuments.findAll", query="SELECT o FROM ProposedCollectionEvaluationDocuments o where o.pcdeStatus = true")
public class ProposedCollectionEvaluationDocuments implements Serializable {
	private static final long serialVersionUID = 1L;
		

	public ProposedCollectionEvaluationDocuments() {
	}
	
	public ProposedCollectionEvaluationDocuments(BiodiversityGeneralCatalog evaluationItems,ProposedCollectionTechnicalEvaluation proposedCollectionTechnicalEvaluation) {				
		this.documentEvaluation=evaluationItems;
		this.proposedCollectionTechnicalEvaluation=proposedCollectionTechnicalEvaluation;
	}

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pcde_id")
	private Integer pcdeId;
	
	@Getter
	@Setter
	@Column(name="prco_id")
	private Integer prcoId;
	
	@Getter
	@Setter
	@Column(name="pcde_evaluation")
	private Boolean pcdeEvaluation;
	
	@Getter
	@Setter
	@Column(name="pcde_status")
	private Boolean pcdeStatus;
	
	@Getter
	@Setter
	@Column(name="pcde_user_create")
	private String pcdeUserCreate;
	
	@Getter
	@Setter
	@Column(name="pcde_date_create")
	private Date pcdeDateCreate;
	
	@Getter
	@Setter
	@Column(name="pcde_user_update")
	private String pcdeUserUpdate;
	
	@Getter
	@Setter
	@Column(name="pcde_date_update")
	private Date pcdeDateUpdate;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="bigc_id")
	private BiodiversityGeneralCatalog documentEvaluation;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="pcte_id")
	private ProposedCollectionTechnicalEvaluation proposedCollectionTechnicalEvaluation;
	
	@Getter
	@Setter
	@Transient
	private Boolean visible;
}