package ec.gob.ambiente.enlisy.geneticresources.model;

import javax.persistence.*;

import ec.gob.ambiente.enlisy.model.GeographicalLocation;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the technical_report_control_monitoring database table.
 * 
 */
@Entity
@Table(name="technical_report_control_monitoring", schema="biodiversity")
@NamedQuery(name="TechnicalReportControlMonitoring.findAll", query="SELECT t FROM TechnicalReportControlMonitoring t")
public class TechnicalReportControlMonitoring implements Cloneable {
	
	@Override
	public TechnicalReportControlMonitoring clone() throws CloneNotSupportedException {	
		return (TechnicalReportControlMonitoring)super.clone();
	}

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="trcm_id")
	private Integer trcmId;

	@Getter
	@Setter
	@Column(name="bosque_gid")
	private Integer bosqueGid;

	@Setter
	@Getter
	@Column(name="bosque_name")
	private String bosqueName;

	@Getter
	@Setter
	@Column(name="snap_gid")
	private Integer snapGid;

	@Getter
	@Setter
	@Column(name="snap_name")
	private String snapName;

	@Getter
	@Setter
	@Column(name="trcm_address_contact")
	private String trcmAddressContact;

	@Getter
	@Setter
	@Column(name="trcm_altitude")
	private String trcmAltitude;

	@Getter
	@Setter
	@Column(name="trcm_phone_extension")
	private String trcmPhoneExtension;

	@Getter
	@Setter
	@Column(name="trcm_conclusion")
	private String trcmConclusion;

	@Getter
	@Setter
	@Column(name="trcm_coordinate_x")
	private String trcmCoordinateX;

	@Getter
	@Setter
	@Column(name="trcm_coordinate_y")
	private String trcmCoordinateY;

	@Getter
	@Setter
	@Column(name="trcm_date_create")
	private Date trcmDateCreate;

	@Getter
	@Setter
	@Column(name="trcm_date_create_report")
	private Date trcmDateCreateReport;

	@Getter
	@Setter
	@Column(name="trcm_date_review_approval")
	private Date trcmDateReviewApproval;

	@Getter
	@Setter
	@Column(name="trcm_date_update")
	private Date trcmDateUpdate;

	@Getter
	@Setter
	@Column(name="trcm_description")
	private String trcmDescription;

	@Getter
	@Setter
	@Column(name="trcm_legal_observations")
	private String trcmLegalObservations;

	@Getter
	@Setter
	@Column(name="trcm_legal_report")
	private Boolean trcmLegalReport;

	@Getter
	@Setter
	@Column(name="trcm_phone_contact")
	private String trcmPhoneContact;

	@Getter
	@Setter
	@Column(name="trcm_recomendation")
	private String trcmRecomendation;

	@Getter
	@Setter
	@Column(name="trcm_recomendation_action")
	private String trcmRecomendationAction;

	@Getter
	@Setter
	@Column(name="trcm_report_number")
	private String trcmReportNumber;
	
	@Getter
	@Setter
	@Column(name="trcm_favorable_report")
	private Boolean trcmFavorableReport;
	
	@Getter
	@Setter
	@Column(name="trcm_coordinator_observations")
	private String trcmCoordinatorObservations;

	@Getter
	@Setter
	@Column(name="trcm_status")
	private Boolean trcmStatus;

	@Getter
	@Setter
	@Column(name="trcm_user_create")
	private String trcmUserCreate;

	@Getter
	@Setter
	@Column(name="trcm_user_update")
	private String trcmUserUpdate;
	
	@Getter
	@Setter
	@Column(name="trcm_forms_conservation")
	private String trcmFormsConservation;
	
	@Getter
	@Setter
	@Column(name="trcm_area")
	private Integer trcmArea;	
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="gelo_id")
	private GeographicalLocation geographicalLocation;

	//bi-directional many-to-one association to ProposedCollectionControlMonitoring
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="pccm_id")
	private ProposedCollectionControlMonitoring proposedCollectionControlMonitoring;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="prco_id")
	private ProposedCollection proposedCollection;
	
	@Getter
	@Setter
	@Column(name="trcm_technical_position")
	private String trcmTechnicalPosition;
	
	@Getter
	@Setter
	@Column(name="trcm_coordinator_position")
	private String trcmCoordinatorPosition;
	
	@Getter
	@Setter
	@Column(name="trcm_zone")
	private String trcmZone;
	
	@Getter
	@Setter
	@Column(name="user_coordinator")
	private Integer userCoordinator;
	
	@Getter
	@Setter
	@Column(name="trcm_director_position")
	private String trcmDirectorPosition;
	


	public TechnicalReportControlMonitoring() {
		
	}

}