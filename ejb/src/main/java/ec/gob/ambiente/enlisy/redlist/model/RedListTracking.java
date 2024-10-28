package ec.gob.ambiente.enlisy.redlist.model;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Where;

import java.sql.Timestamp;


/**
 * The persistent class for the red_list_trackings database table.
 * 
 */
@Entity
@Table(name="red_list_trackings", schema="biodiversity")
@NamedQuery(name="RedListTracking.findAll", query="SELECT r FROM RedListTracking r")
@Where(clause = "rltr_status='t'")
public class RedListTracking implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer rltrId;
	private Integer rltrActionType;
	private Timestamp rltrDate;
	private Timestamp rltrDateCreate;
	private Timestamp rltrDateUpdate;
	private String rltrDescription;
	private String rltrDocs;
	private String rltrSignatory;
	private Boolean rltrStatus=true;
	private String rltrUserCreate;
	private String rltrUserUpdate;
	private CallRedList callRedList;

	public RedListTracking() {
	}


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RED_LIST_TRACKING_GENERATOR")
    @SequenceGenerator(name = "RED_LIST_TRACKING_GENERATOR", initialValue = 1, sequenceName = "seq_rltr_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="rltr_id", unique=true, nullable=false)
	public Integer getRltrId() {
		return this.rltrId;
	}

	public void setRltrId(Integer rltrId) {
		this.rltrId = rltrId;
	}


	@Column(name="rltr_action_type")
	public Integer getRltrActionType() {
		return this.rltrActionType;
	}

	public void setRltrActionType(Integer rltrActionType) {
		this.rltrActionType = rltrActionType;
	}


	@Column(name="rltr_date")
	public Timestamp getRltrDate() {
		return this.rltrDate;
	}

	public void setRltrDate(Timestamp rltrDate) {
		this.rltrDate = rltrDate;
	}


	@Column(name="rltr_date_create", nullable=false)
	public Timestamp getRltrDateCreate() {
		return this.rltrDateCreate;
	}

	public void setRltrDateCreate(Timestamp rltrDateCreate) {
		this.rltrDateCreate = rltrDateCreate;
	}


	@Column(name="rltr_date_update", nullable=false)
	public Timestamp getRltrDateUpdate() {
		return this.rltrDateUpdate;
	}

	public void setRltrDateUpdate(Timestamp rltrDateUpdate) {
		this.rltrDateUpdate = rltrDateUpdate;
	}


	@Column(name="rltr_description", length=2147483647)
	public String getRltrDescription() {
		return this.rltrDescription;
	}

	public void setRltrDescription(String rltrDescription) {
		this.rltrDescription = rltrDescription;
	}


	@Column(name="rltr_docs", length=255)
	public String getRltrDocs() {
		return this.rltrDocs;
	}

	public void setRltrDocs(String rltrDocs) {
		this.rltrDocs = rltrDocs;
	}


	@Column(name="rltr_signatory", length=64)
	public String getRltrSignatory() {
		return this.rltrSignatory;
	}

	public void setRltrSignatory(String rltrSignatory) {
		this.rltrSignatory = rltrSignatory;
	}


	@Column(name="rltr_status", nullable=false)
	public Boolean getRltrStatus() {
		return this.rltrStatus;
	}

	public void setRltrStatus(Boolean rltrStatus) {
		this.rltrStatus = rltrStatus;
	}


	@Column(name="rltr_user_create", nullable=false, length=255)
	public String getRltrUserCreate() {
		return this.rltrUserCreate;
	}

	public void setRltrUserCreate(String rltrUserCreate) {
		this.rltrUserCreate = rltrUserCreate;
	}


	@Column(name="rltr_user_update", nullable=false, length=255)
	public String getRltrUserUpdate() {
		return this.rltrUserUpdate;
	}

	public void setRltrUserUpdate(String rltrUserUpdate) {
		this.rltrUserUpdate = rltrUserUpdate;
	}


	//bi-directional many-to-one association to CallRedList
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="care_id")
	public CallRedList getCallRedList() {
		return this.callRedList;
	}

	public void setCallRedList(CallRedList callRedList) {
		this.callRedList = callRedList;
	}

}