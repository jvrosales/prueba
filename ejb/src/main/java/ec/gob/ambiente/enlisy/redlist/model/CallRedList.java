package ec.gob.ambiente.enlisy.redlist.model;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Where;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the call_red_lists database table.
 * 
 */
@Entity
@Table(name="call_red_lists", schema="biodiversity")
@NamedQuery(name="CallRedList.findAll", query="SELECT c FROM CallRedList c")
@Where(clause = "care_status='t'")
public class CallRedList implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer careId;
	private Integer careProcessStatus=0;
	private Integer careRedlistType=0;
	private Timestamp careDate;
	private Timestamp careDateCreate;
	private Timestamp careDateUpdate;
	private String careDescription;
	private String careProcess;
	private Boolean careStatus=true;
	private String careUserCreate;
	private String careUserUpdate;
	private List<RedListTracking> redListTrackings;
	private List<RedlistParticipant> redlistParticipants;
	private List<RedlistSpecy> redlistSpecies;
	 
	private String statusLabel = "";
	
	@Transient
	public String getStatusLabel() {
		return statusLabel;
	}

	@Transient
	public void setStatusLabel(String statusLabel) {
		this.statusLabel = statusLabel;
	}

	public CallRedList() {
	}


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CALL_RED_LIST_GENERATOR")
    @SequenceGenerator(name = "CALL_RED_LIST_GENERATOR", initialValue = 1, sequenceName = "seq_care_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="care_id", unique=true, nullable=false)
	public Integer getCareId() {
		return this.careId;
	}

	public void setCareId(Integer careId) {
		this.careId = careId;
	}
	
	@Column(name="care_process_status", unique=true, nullable=false)
	public Integer getCareProcessStatus() {
		return this.careProcessStatus;
	}

	public void setCareProcessStatus(Integer careProcessStatus) {
		this.careProcessStatus = careProcessStatus;
	}
	
	@Column(name="care_redlist_type", unique=true, nullable=false)
	public Integer getCareRedlistType() {
		return this.careRedlistType;
	}

	public void setCareRedlistType(Integer careRedlistType) {
		this.careRedlistType = careRedlistType;
	}


	@Column(name="care_date", nullable=false)
	public Timestamp getCareDate() {
		return this.careDate;
	}

	public void setCareDate(Timestamp careDate) {
		this.careDate = careDate;
	}


	@Column(name="care_date_create", nullable=false)
	public Timestamp getCareDateCreate() {
		return this.careDateCreate;
	}

	public void setCareDateCreate(Timestamp careDateCreate) {
		this.careDateCreate = careDateCreate;
	}


	@Column(name="care_date_update", nullable=false)
	public Timestamp getCareDateUpdate() {
		return this.careDateUpdate;
	}

	public void setCareDateUpdate(Timestamp careDateUpdate) {
		this.careDateUpdate = careDateUpdate;
	}


	@Column(name="care_description", length=2147483647)
	public String getCareDescription() {
		return this.careDescription;
	}

	public void setCareDescription(String careDescription) {
		this.careDescription = careDescription;
	}


	@Column(name="care_process", length=255)
	public String getCareProcess() {
		return this.careProcess;
	}

	public void setCareProcess(String careProcess) {
		this.careProcess = careProcess;
	}


	@Column(name="care_status", nullable=false)
	public Boolean getCareStatus() {
		return this.careStatus;
	}

	public void setCareStatus(Boolean careStatus) {
		this.careStatus = careStatus;
	}


	@Column(name="care_user_create", nullable=false, length=255)
	public String getCareUserCreate() {
		return this.careUserCreate;
	}

	public void setCareUserCreate(String careUserCreate) {
		this.careUserCreate = careUserCreate;
	}


	@Column(name="care_user_update", nullable=false, length=255)
	public String getCareUserUpdate() {
		return this.careUserUpdate;
	}

	public void setCareUserUpdate(String careUserUpdate) {
		this.careUserUpdate = careUserUpdate;
	}


	//bi-directional many-to-one association to RedListTracking
	@OneToMany(mappedBy="callRedList")
	public List<RedListTracking> getRedListTrackings() {
		return this.redListTrackings;
	}

	public void setRedListTrackings(List<RedListTracking> redListTrackings) {
		this.redListTrackings = redListTrackings;
	}

	public RedListTracking addRedListTracking(RedListTracking redListTracking) {
		getRedListTrackings().add(redListTracking);
		redListTracking.setCallRedList(this);

		return redListTracking;
	}

	public RedListTracking removeRedListTracking(RedListTracking redListTracking) {
		getRedListTrackings().remove(redListTracking);
		redListTracking.setCallRedList(null);

		return redListTracking;
	}


	//bi-directional many-to-one association to RedlistParticipant
	@OneToMany(mappedBy="callRedList")
	public List<RedlistParticipant> getRedlistParticipants() {
		return this.redlistParticipants;
	}

	public void setRedlistParticipants(List<RedlistParticipant> redlistParticipants) {
		this.redlistParticipants = redlistParticipants;
	}

	public RedlistParticipant addRedlistParticipant(RedlistParticipant redlistParticipant) {
		getRedlistParticipants().add(redlistParticipant);
		redlistParticipant.setCallRedList(this);

		return redlistParticipant;
	}

	public RedlistParticipant removeRedlistParticipant(RedlistParticipant redlistParticipant) {
		getRedlistParticipants().remove(redlistParticipant);
		redlistParticipant.setCallRedList(null);

		return redlistParticipant;
	}


	//bi-directional many-to-one association to RedlistSpecy
	@OneToMany(mappedBy="callRedList")
	public List<RedlistSpecy> getRedlistSpecies() {
		return this.redlistSpecies;
	}

	public void setRedlistSpecies(List<RedlistSpecy> redlistSpecies) {
		this.redlistSpecies = redlistSpecies;
	}

	public RedlistSpecy addRedlistSpecy(RedlistSpecy redlistSpecy) {
		getRedlistSpecies().add(redlistSpecy);
		redlistSpecy.setCallRedList(this);

		return redlistSpecy;
	}

	public RedlistSpecy removeRedlistSpecy(RedlistSpecy redlistSpecy) {
		getRedlistSpecies().remove(redlistSpecy);
		redlistSpecy.setCallRedList(null);

		return redlistSpecy;
	}

}