package ec.gob.ambiente.enlisy.geneticresources.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the contracts database table.
 * 
 */
@Entity
@Table(name="contracts", schema="biodiversity")
@NamedQuery(name="Contracts.findAll", query="SELECT o FROM Contracts o")
public class Contracts  implements Cloneable{
	
	@Override
	public Contracts clone() throws CloneNotSupportedException {
		return (Contracts)super.clone();
	}
	
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cotr_id")
	private Integer cotrId;

	@Getter
	@Setter
	@Column(name="cotr_code")
	private String cotrCode;
	
	@Getter
	@Setter
	@Column(name="cotr_processing_number")
	private String cotrProcessingNumber;
	
	@Getter
	@Setter
	@Column(name="cotr_html_template")
	private String cotrHtmlTemplate;
	
	@Getter
	@Setter
	@Column(name="cotr_applicant_grammatical_article")
	private String cotrApplicantGrammaticalArticle;
	
	@Getter
	@Setter
	@Column(name="cotr_applicant_siglas")
	private String cotrApplicantSiglas;
	
	@Getter
	@Setter
	@Column(name="cotr_applicant_responsable")
	private String cotrApplicantResponsable;
	
	@Getter
	@Setter
	@Column(name="cotr_applicant_email")
	private String cotrApplicantEmail;
	
	@Getter
	@Setter
	@Column(name="cotr_accept_contract")
	private Boolean cotrAcceptContract;
	
	@Getter
	@Setter
	@Column(name="cotr_observation")
	private String cotrObservation;
	
	@Getter
	@Setter
	@Column(name="cotr_folio_number")
	private String cotrFolioNumber;
	
	@Getter
	@Setter
	@Column(name="cotr_register_date")
	private Date cotrRegisterDate;	

	@Getter
	@Setter
	@Column(name="cotr_status")
	private Boolean cotrStatus;
	
	@Getter
	@Setter
	@Column(name="cotr_user_create")
	private String cotrUserCreate;
	
	@Getter
	@Setter
	@Column(name="cotr_date_create")
	private Date cotrDateCreate;
	
	@Getter
	@Setter
	@Column(name="cotr_user_update")
	private String cotrUserUpdate;
	
	@Getter
	@Setter
	@Column(name="cotr_date_update")
	private Date cotrDateUpdate;
	
	@Getter
	@Setter
	@Column(name = "cotr_legal_observation")
	private String cotrLegalObservation;
	
	@Getter
	@Setter
	@Column(name = "cotr_staff_action")
	private String cotrStaffAction;

	@Getter
	@Setter
	@Column(name = "cotr_staff_action_date")
	private Date cotrStaffActionDate;
	
	@Getter
	@Setter
	@Column(name = "cotr_legal_representative_date")
	private Date cotrLegalRepresentativeDate;
	
	@Getter
	@Setter
	@Column(name = "cotr_executive_order_date")
	private Date cotrExecutiveOrderDate;
	
	@Getter
	@Setter
	@Column(name = "cotr_official_register_date")
	private Date cotrOfficialRegisterDate;
	
	@Getter
	@Setter
	@Column(name = "cotr_official_register_number")
	private String cotrOfficialRegisterNumber;
	
	@Getter
	@Setter
	@Column(name = "cotr_memo_number")
	private String cotrMemoNumber;
	
	@Getter
	@Setter
	@Column(name = "cotr_memo_date")
	private Date cotrMemoDate;
	
	@Getter
	@Setter
	@Column(name = "cotr_biodiversity_memo_number")
	private String cotrBiodiversityMemoNumber;
	
	@Getter
	@Setter
	@Column(name = "cotr_biodiversity_memo_date")
	private Date cotrBiodiversityMemoDate;
	
	@Getter
	@Setter
	@Column(name = "cotr_legal_memo_number")
	private String cotrLegalMemoNumber;
	
	@Getter
	@Setter
	@Column(name = "cotr_legal_memo_date")
	private Date cotrLegalMemoDate;
	
	@Getter
	@Setter
	@Column(name = "cotr_modifying_contract")
	private Boolean cotrModifyingContract;
	
	@Getter
	@Setter
	@Column(name = "cotr_notary_information")
	private String cotrNotaryInformation;
	
	@Getter
	@Setter
	@Column(name = "cotr_memo_entry_date")
	private Date cotrMemoEntryDate;
	
	@Getter
	@Setter
	@Column(name = "cotr_technical_report")
	private String cotrTechnicalReport;
	
	@Getter
	@Setter
	@Column(name = "cotr_technical_report_number")
	private String cotrTechnicalReportNumber;
	
	@Getter
	@Setter
	@Column(name = "cotr_technical_report_date")
	private Date cotrTechnicalReportDate;
	
	@Getter
	@Setter
	@Column(name = "cotr_object")
	private String cotrObject;
	
	@Getter
	@Setter
	@Column(name = "cotr_time_validity")
	private String cotrTimeValidity;
	
	@Getter
	@Setter
	@Column(name = "cotr_memo_request")
	private String cotrMemoRequest;
	
	/*******
	 *  campos aumentados 
	 */
	
	@Getter
	@Setter
	@Column(name = "cotr_institution_creation_documents")
	private String cotrInstitutionCreationDocuments;
	
	@Getter
	@Setter
	@Column(name = "cotr_legal_representative_article")
	private String cotrLegalRepresentativeArticle;
	
	@Getter
	@Setter
	@Column(name = "cotr_legal_representative_title")
	private String cotrLegalRepresentativeTitle;
	
	@Getter
	@Setter
	@Column(name = "cotr_legal_representative_name")
	private String cotrLegalRepresentativeName;
	
	@Getter
	@Setter
	@Column(name = "cotr_legal_representative_post")
	private String cotrLegalRepresentativePost;
	
	@Getter
	@Setter
	@Column(name = "cotr_legal_representative_possession")
	private String cotrLegalRepresentativePossession;
	
	@Getter
	@Setter
	@Column(name = "cotr_letter_endorsement")
	private String cotrLetterEndorsement;
	
	@Getter
	@Setter
	@Column(name = "cotr_letter_endorsement_date")
	private Date cotrLetterEndorsementDate;
	
	@Getter
	@Setter
	@Column(name = "cotr_legal_representative_delegation_number")
	private String cotrLegalRepresentativeDelegationNumber;
	
	@Getter
	@Setter
	@Column(name = "cotr_legal_representative_delegation_date")
	private Date cotrLegalRepresentativeDelegationDate;
	
	@Getter
	@Setter
	@Column(name = "cotr_institution_affiliation_name")
	private String cotrInstitutionAffiliationName;
	
	@Getter
	@Setter
	@Column(name = "cotr_undersecretary_article")
	private String cotrUndersecretaryArticle;
	
	@Getter
	@Setter
	@Column(name = "cotr_undersecretary_title")
	private String cotrUndersecretaryTitle;
	
	@Getter
	@Setter
	@Column(name = "cotr_responsable_unit_email")
	private String cotrResponsableUnitEmail;
	
	@Getter
	@Setter
	@Column(name = "cotr_copy_correspondence_unit_email")
	private String cotrCopyResponsableUnitEmail;
	
	@Getter
	@Setter
	@Column(name = "cotr_technical_evaluation_name")
	private String cotrTechnicalEvaluationName;
	
	@Getter
	@Setter
	@Column(name = "cotr_elaboration_name")
	private String cotrElaborationName;
	
	@Getter
	@Setter
	@Column(name = "cotr_revision_director_name")
	private String cotrRevisionDirectorName;
	
	@Getter
	@Setter
	@Column(name = "cotr_revision_technical_name")
	private String cotrRevisionTechnicalName;
	
	@Getter
	@Setter
	@Column(name = "cotr_revision_coordinator_cgj_name")
	private String cotrRevisionCoordinatorCgjName;
	
	@Getter
	@Setter
	@Column(name="grca_id")
	private Integer grcaIdTipoContrato;
	
	@Getter
	@Setter
	@Column(name="cotr_modified")
	private Boolean cotrModified;
	
	@Getter
	@Setter
	@Column(name="cotr_observation_firms")
	private String cotrObservationFirms;
	
	@Getter
	@Setter
	@Column(name="cotr_registration_number")
	private String cotrRegistrationNumber;

	@Getter
	@Setter
	@Transient
	private String accionRealizada;
}
