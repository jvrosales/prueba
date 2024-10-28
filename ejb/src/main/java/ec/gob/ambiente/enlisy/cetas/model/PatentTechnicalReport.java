package ec.gob.ambiente.enlisy.cetas.model;

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
 * The persistent class for the patent_technical_report database table.
 * 
 */
@Entity
@Table(name="patent_technical_report", schema="cetas")
@NamedQuery(name="PatentTechnicalReport.findAll", query="SELECT o FROM PatentTechnicalReport o where o.patrStatus = true")
public class PatentTechnicalReport implements Serializable,Cloneable {
	private static final long serialVersionUID = 1L;
		
	public PatentTechnicalReport clone(PatentApplication patentApplication) throws CloneNotSupportedException {
		
		PatentTechnicalReport clone= (PatentTechnicalReport)super.clone();
		clone.setPatrId(null);
		clone.setPatrUserUpdate(null);
		clone.setPatrDateUpdate(null);
		clone.setPatentApplication(patentApplication);
		return clone;
	}
		

	public PatentTechnicalReport() {		
	}
	
	public PatentTechnicalReport(CatalogWmc catalogWmc,PatentApplication patentApplication) {
		this.typeTechnicalRepor=catalogWmc;
		this.patentApplication=patentApplication;
	}

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="patr_id")
	private Integer patrId;
	
	@Getter
	@Setter
	@Column(name="patr_report_number")
	private String patrReportNumber;
	
	@Getter
	@Setter
	@Column(name="patr_subject")
	private String patrSubject;
	
	@Getter
	@Setter
	@Column(name="patr_location")
	private String patrLocation;
	
	@Getter
	@Setter
	@Column(name="patr_participants")
	private String patrParticipants;
	
	@Getter
	@Setter
	@Column(name="patr_inspection_date")
	private Date patrInspectionDate;
	
	@Getter
	@Setter
	@Column(name="patr_report_date")
	private Date patrReportDate;
	
	@Getter
	@Setter
	@Column(name="patr_background")
	private String patrBackground;
	
	@Getter
	@Setter
	@Column(name="patr_objective")
	private String patrObjective;
	
	@Getter
	@Setter
	@Column(name="patr_infrastructure_review")
	private String patrInfrastructureReview;
	
	@Getter
	@Setter
	@Column(name="patr_species_management")
	private String patrSpeciesManagement;
	
	@Getter
	@Setter
	@Column(name="patr_commercial_production")
	private String patrCommercialProduction;
	
	@Getter
	@Setter
	@Column(name="patr_evaluation_security")
	private String patrEvaluationSecurity;
	
	@Getter
	@Setter
	@Column(name="patr_staff_assessment")
	private String patrStaffAssessment;
	
	@Getter
	@Setter
	@Column(name="patr_registration_documentation")
	private String patrRegistrationDocumentation;
	
	@Getter
	@Setter
	@Column(name="patr_results")
	private String patrResults;
	
	@Getter
	@Setter
	@Column(name="patr_conclusions")
	private String patrConclusions;
	
	@Getter
	@Setter
	@Column(name="patr_recommendations")
	private String patrRecommendations;
	
	@Getter
	@Setter
	@Column(name="patr_anexos")
	private Byte patrAnexos;
	
	@Getter
	@Setter
	@Column(name="patr_status")
	private Boolean patrStatus;
	
	@Getter
	@Setter
	@Column(name="patr_user_create")
	private String patrUserCreate;
	
	@Getter
	@Setter
	@Column(name="patr_date_create")
	private Date patrDateCreate;
	
	@Getter
	@Setter
	@Column(name="patr_user_update")
	private String patrUserUpdate;
	
	@Getter
	@Setter
	@Column(name="patr_date_update")
	private Date patrDateUpdate;

	//bi-directional many-to-one association to PatentApplication
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="paap_id")
	private PatentApplication patentApplication;
	
	//bi-directional many-to-one association to Catalog
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="cwmc_id_type_technical_report")
	private CatalogWmc typeTechnicalRepor;
	
	
}