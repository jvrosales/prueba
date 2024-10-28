package ec.gob.ambiente.enlisy.geneticresources.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ec.gob.ambiente.enlisy.model.GeographicalLocation;
import ec.gob.ambiente.enlisy.model.User;
import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the proposed_collection_control_monitoring database table.
 * 
 */
@Entity
@Table(name="proposed_collection_control_monitoring", schema="biodiversity")
@NamedQuery(name="ProposedCollectionControlMonitoring.findAll", query="SELECT p FROM ProposedCollectionControlMonitoring p")
public class ProposedCollectionControlMonitoring implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pccm_id")
	private Integer pccmId;
	
	@Getter
	@Setter
	@Column(name="pccm_code")
	private String pccmCode;
	
	@Getter
	@Setter
	@Column(name="pccm_control_period")
	private Date pccmControlPeriod;
	
	@Getter
	@Setter
	@Column(name="pccm_justifies_information")
	private Boolean pccmJustifiesInformation;
	
	@Getter
	@Setter
	@Column(name="pccm_project_type")
	private String pccmProjectType;

	@Getter
	@Setter
	@Column(name="pccm_date_create")
	private Date pccmDateCreate;

	@Getter
	@Setter
	@Column(name="pccm_date_update")
	private Date pccmDateUpdate;

	@Getter
	@Setter
	@Column(name="pccm_status")
	private Boolean pccmStatus;

	@Getter
	@Setter
	@Column(name="pccm_user_create")
	private String pccmUserCreate;

	@Getter
	@Setter
	@Column(name="pccm_user_update")
	private String pccmUserUpdate;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;	

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="prco_id")
	private ProposedCollection proposedCollection;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="gelo_id")
	private GeographicalLocation geographicalLocation;

	//bi-directional many-to-one association to TechnicalReportControlMonitoring
	@Getter
	@Setter
	@OneToMany(mappedBy="proposedCollectionControlMonitoring")
	private List<TechnicalReportControlMonitoring> technicalReportControlMonitorings;

	public ProposedCollectionControlMonitoring() {
	}

	public TechnicalReportControlMonitoring addTechnicalReportControlMonitoring(TechnicalReportControlMonitoring technicalReportControlMonitoring) {
		getTechnicalReportControlMonitorings().add(technicalReportControlMonitoring);
		technicalReportControlMonitoring.setProposedCollectionControlMonitoring(this);

		return technicalReportControlMonitoring;
	}

	public TechnicalReportControlMonitoring removeTechnicalReportControlMonitoring(TechnicalReportControlMonitoring technicalReportControlMonitoring) {
		getTechnicalReportControlMonitorings().remove(technicalReportControlMonitoring);
		technicalReportControlMonitoring.setProposedCollectionControlMonitoring(null);

		return technicalReportControlMonitoring;
	}

}