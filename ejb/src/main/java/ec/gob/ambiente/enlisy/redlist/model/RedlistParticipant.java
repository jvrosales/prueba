package ec.gob.ambiente.enlisy.redlist.model;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Where;

import ec.gob.ambiente.enlisy.speciescatalog.model.SpecialistApplication;

import java.sql.Timestamp;

/**
 * The persistent class for the redlist_participants database table.
 * 
 */
@Entity
@Table(name = "redlist_participants", schema = "biodiversity")
@NamedQuery(name = "RedlistParticipant.findAll", query = "SELECT r FROM RedlistParticipant r")
@Where(clause = "rspa_status='t'")
public class RedlistParticipant implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer rlpaId;
	private Boolean rlspColaborator = Boolean.valueOf(false);
	private Boolean rlspEditor = Boolean.valueOf(false);
	private Boolean rlspEvaluator = Boolean.valueOf(false);
	private Integer rlspLevelParticipation;
	private Timestamp rspaDateCreate;
	private Timestamp rspaDateUpdate;
	private Boolean rspaStatus = true;
	private String rspaUserCreate;
	private String rspaUserUpdate;
	private Boolean rlspParticipated = Boolean.valueOf(false);
	private String rlspObservations;
	private CallRedList callRedList;
	private SpecialistApplication specialistApplication;
 
	public RedlistParticipant() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RED_LIST_PARTICI_GENERATOR")
    @SequenceGenerator(name = "RED_LIST_PARTICI_GENERATOR", initialValue = 1, sequenceName = "seq_rlpa_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name = "rlpa_id", unique = true, nullable = false)
	public Integer getRlpaId() {
		return this.rlpaId;
	}

	public void setRlpaId(Integer rlpaId) {
		this.rlpaId = rlpaId;
	}

	@Column(name = "rlsp_colaborator")
	public Boolean getRlspColaborator() {
		return this.rlspColaborator;
	}

	public void setRlspColaborator(Boolean rlspColaborator) {
		this.rlspColaborator = rlspColaborator;
	}

	@Column(name = "rlsp_editor")
	public Boolean getRlspEditor() {
		return this.rlspEditor;
	}

	public void setRlspEditor(Boolean rlspEditor) {
		this.rlspEditor = rlspEditor;
	}

	@Column(name = "rlsp_evaluator")
	public Boolean getRlspEvaluator() {
		return this.rlspEvaluator;
	}

	public void setRlspEvaluator(Boolean rlspEvaluator) {
		this.rlspEvaluator = rlspEvaluator;
	}

	@Column(name = "rlsp_level_participation")
	public Integer getRlspLevelParticipation() {
		return this.rlspLevelParticipation;
	}

	public void setRlspLevelParticipation(Integer rlspLevelParticipation) {
		this.rlspLevelParticipation = rlspLevelParticipation;
	}

	@Column(name = "rspa_date_create", nullable = false)
	public Timestamp getRspaDateCreate() {
		return this.rspaDateCreate;
	}

	public void setRspaDateCreate(Timestamp rspaDateCreate) {
		this.rspaDateCreate = rspaDateCreate;
	}

	@Column(name = "rspa_date_update", nullable = false)
	public Timestamp getRspaDateUpdate() {
		return this.rspaDateUpdate;
	}

	public void setRspaDateUpdate(Timestamp rspaDateUpdate) {
		this.rspaDateUpdate = rspaDateUpdate;
	}

	@Column(name = "rspa_status", nullable = false)
	public Boolean getRspaStatus() {
		return this.rspaStatus;
	}

	public void setRspaStatus(Boolean rspaStatus) {
		this.rspaStatus = rspaStatus;
	}

	@Column(name = "rspa_user_create", nullable = false, length = 255)
	public String getRspaUserCreate() {
		return this.rspaUserCreate;
	}

	public void setRspaUserCreate(String rspaUserCreate) {
		this.rspaUserCreate = rspaUserCreate;
	}

	@Column(name = "rspa_user_update", nullable = false, length = 255)
	public String getRspaUserUpdate() {
		return this.rspaUserUpdate;
	}

	public void setRspaUserUpdate(String rspaUserUpdate) {
		this.rspaUserUpdate = rspaUserUpdate;
	}

	// bi-directional many-to-one association to CallRedList
	@ManyToOne
	@JoinColumn(name = "care_id")
	public CallRedList getCallRedList() {
		return this.callRedList;
	}

	public void setCallRedList(CallRedList callRedList) {
		this.callRedList = callRedList;
	}

	// bi-directional many-to-one association to SpecialistApplication
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "spap_id")
	public SpecialistApplication getSpecialistApplication() {
		return this.specialistApplication;
	}

	public void setSpecialistApplication(SpecialistApplication specialistApplication) {
		this.specialistApplication = specialistApplication;
	}

	@Column(name = "rlsp_participated")
	public Boolean getRlspParticipated() {
		return rlspParticipated;
	}

	public void setRlspParticipated(Boolean rlspParticipated) {
		this.rlspParticipated = rlspParticipated;
	}

	@Column(name = "rlsp_observations", length = 512)
	public String getRlspObservations() {
		return rlspObservations;
	}

	public void setRlspObservations(String rlspObservations) {
		this.rlspObservations = rlspObservations;
	}

}