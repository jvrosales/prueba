package ec.gob.ambiente.enlisy.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
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

import org.hibernate.annotations.Filter;

import ec.gob.ambiente.suia.domain.base.EntidadBase;
import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the application database table.
 *
 */
@Entity
@Table(name = "patent_technical_report", schema = "biodiversity_mcm")
@NamedQuery(name = "PatentTechnicalReportV1.findAll", query = "SELECT o FROM PatentTechnicalReportV1 o")
@AttributeOverrides({
	@AttributeOverride(name = "estado", column = @Column(name = "patr_status_date")),
	@AttributeOverride(name = "fechaCreacion", column = @Column(name = "patr_date_create")),
	@AttributeOverride(name = "fechaModificacion", column = @Column(name = "patr_date_update")),
	@AttributeOverride(name = "usuarioCreacion", column = @Column(name = "patr_user_create")),
	@AttributeOverride(name = "usuarioModificacion", column = @Column(name = "patr_user_update")) })
@Filter(name = EntidadBase.FILTER_ACTIVE, condition = "patr_status = 'TRUE'")

public class PatentTechnicalReportV1 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "patr_id")
	private Integer id;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "pare_id")
	private PatentRequest patentRequest;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "ctpa_id")
	private CatalogoPatentes catalogoPatentes;

	@Getter
	@Setter
	@Column(name = "patr_report_number")
	private String reportNumber;

	@Getter
	@Setter
	@Column(name = "patr_subject")
	private String subject;

	@Getter
	@Setter
	@Column(name = "patr_participants")
	private String participants;

	@Getter
	@Setter
	@Column(name = "patr_inspection_date")
	private Date inspectionDate;

	@Getter
	@Setter
	@Column(name = "patr_report_date")
	private Date reportDate;

	@Getter
	@Setter
	@Column(name = "patr_background")
	private String background;

	@Getter
	@Setter
	@Column(name = "patr_objective")
	private String objective;

	@Getter
	@Setter
	@Column(name = "patr_coorx")
	private Double coorx;

	@Getter
	@Setter
	@Column(name = "patr_coory")
	private Double coory;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "gelo_id")
	private GeographicalLocation geloId;

	@Getter
	@Setter
	@Column(name = "patr_justifies_possession_specimens")
	private String justifiesPossessionSpecimens;

	@Getter
	@Setter
	@Column(name = "patr_infrastructure_review")
	private String infrastructureReview;

	@Getter
	@Setter
	@Column(name = "patr_species_management_description")
	private String speciesManagementDescription;
    
	@Getter
	@Setter
	@Column(name = "patr_species_management")
	private Boolean speciesManagement;

	@Getter
	@Setter
	@Column(name = "patr_commercial_production")
	private String commercialProduction;
    
	@Getter
	@Setter
	@Column(name = "patr_evaluation_security")
	private String evaluationSecurity;
    
	@Getter
	@Setter
	@Column(name = "patr_evaluation_biosecurity")
	private String evaluationBiosecurity;
    
	@Getter
	@Setter
	@Column(name = "patr_staff_assessment")
	private String staffAssessment;
    
	@Getter
	@Setter
	@Column(name = "patr_technical_verification")
	private String technicalVerification;
    
	@Getter
	@Setter
	@Column(name = "patr_registration_verification")
	private String registrationVerification;
    
	@Getter
	@Setter
	@Column(name = "patr_conclusions")
	private String conclusions;
    
	@Getter
	@Setter
	@Column(name = "patr_recommendations")
	private String recommendations;
    
	@Getter
	@Setter
	@Column(name = "patr_contraception_plan")
	private Boolean contraceptionPlan;
    
	@Getter
	@Setter
	@Column(name = "patr_contraception_plan_description")
	private String contraceptionPlanDescription;
    
	@Getter
	@Setter
	@Column(name = "patr_section")
	private String section;
    

    @Getter
	@Setter
	@Transient
	private byte[] archivo;
    
	@Getter
	@Setter
	@Transient
	private String path;

}
