package ec.gob.ambiente.enlisy.model;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "patent_request", schema = "biodiversity_mcm")
@NamedQuery(name = "PatentRequest.findAll", query = "SELECT o FROM PatentRequest o")
public class PatentRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pare_id")
	private Integer id;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="pain_id")
	private PatentInformation  patentInformation;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="capa_id")
	private CategoryPatent categoryPatent;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="user_id_institution")
	private User userIdInstitution;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="user_id_applicant")
	private User userIdApplicant;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="ctpa_id")
	private CatalogoPatentes ctpaCargoAdmin;
	
	@Getter
	@Setter
	@Column(name="pare_name_collection")
	private String pareNameCollection;
	
	@Getter
	@Setter
	@Column(name="pare_coorx")
	private Double pareCoorx;
	
	@Getter
	@Setter
	@Column(name="pare_coory")
	private Double pareCoory;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="ctpa_id_zone")
	private CatalogoPatentes ctpaIdZone;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="gelo_id")
	private GeographicalLocation geographicalLocation;
	
	@Getter
	@Setter
	@Column(name="pare_address")
	private String pareAddress;
	
	@Getter
	@Setter
	@Column(name="pare_objective")
	private String pareObjective;
	
	@Getter
	@Setter
	@Column(name="pare_security_system")
	private String pareSecuritySystem;
	
	@Getter
	@Setter
	@Column(name="pare_sanitary_measures")
	private String pareSanitaryMeasures;
	
	@Getter
	@Setter
	@Column(name="pare_acronym")
	private String pare_acronym;
	
	
	@Getter
	@Setter
	@Column(name="pare_status")
	private Boolean pareStatus;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Getter
	@Setter
	@Column(name="pare_status_date")
	private Date pareStatusDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Getter
	@Setter
	@Column(name="pare_creation_date")
	private Date pareCreationDate;
	
	@Getter
	@Setter
	@Column(name="pare_creator_user")
	private String pareCreatorUser;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Getter
	@Setter
	@Column(name="pare_date_update")
	private Date pareDateUpdate;
	
	@Getter
	@Setter
	@Column(name="pare_user_update")
	private String pareUserUpdate;
		
	
	@Getter
	@Setter
	@Column(name="pare_species_management")
	private Boolean pareSpeciesManagement;
	
	@Getter
	@Setter
	@Column(name="pare_cost")
	private Double pareCost;
	
	@Getter
	@Setter
	@Column(name="pare_financing")
	private Double pareFinancing;
	
	@Getter
	@Setter
	@Column(name="pare_status_document")
	private Boolean pareStatusDocument;
	
	@Getter
	@Setter
	@Column(name="pare_comply_pmn")
	private Boolean pareComplyPmn;
	
	@Getter
	@Setter
	@Column(name="pare_status_payment_inspection")
	private Boolean pareStatusPaymentInspection;
	
	@Getter
	@Setter
	@Column(name="pare_status_payment_patent")
	private Boolean pareStatusPaymentPatent;
	
	@Getter
	@Setter
	@Column(name="pare_compliance_obligations")
	private String pareComplianceObligations;
	
	@Getter
	@Setter
	@Column(name="pare_value_payment_inspection")
	private Float pareValuePaymentInspection;
	
	@Getter
	@Setter
	@Column(name="pare_value_payment_patent")
	private Float pareValuePaymentPatent;
	
	
}
