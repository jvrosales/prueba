package ec.gob.ambiente.enlisy.redlist.model;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Where;

import java.sql.Timestamp;
import java.util.Date;

/**
 * The persistent class for the meeting_agreement database table.
 * 
 */
@Entity
@Table(name = "meeting_agreement", schema = "biodiversity")
@NamedQuery(name = "MeetingAgreement.findAll", query = "SELECT m FROM MeetingAgreement m")
@Where(clause = "meag_status='t'")
public class MeetingAgreement implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer meagId;
	private Date meagDate;
	private Timestamp meagDateCreate;
	private Timestamp meagDateUpdate;
	private String meagDescription;
	private Integer meagOrder = 0;
	private String meagResponsible;
	private Boolean meagStatus = true;
	private String meagUserCreate;
	private String meagUserUpdate;
	private MeetingLog meetingLog;

	public MeetingAgreement() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEET_AGREE_GENERATOR")
    @SequenceGenerator(name = "MEET_AGREE_GENERATOR", initialValue = 1, sequenceName = "seq_meag_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name = "meag_id")
	public Integer getMeagId() {
		return this.meagId;
	}

	public void setMeagId(Integer meagId) {
		this.meagId = meagId;
	}

	@Column(name = "meag_date")
	public Date getMeagDate() {
		return this.meagDate;
	}

	public void setMeagDate(Date meagDate) {
		this.meagDate = meagDate;
	}

	@Column(name = "meag_date_create")
	public Timestamp getMeagDateCreate() {
		return this.meagDateCreate;
	}

	public void setMeagDateCreate(Timestamp meagDateCreate) {
		this.meagDateCreate = meagDateCreate;
	}

	@Column(name = "meag_date_update")
	public Timestamp getMeagDateUpdate() {
		return this.meagDateUpdate;
	}

	public void setMeagDateUpdate(Timestamp meagDateUpdate) {
		this.meagDateUpdate = meagDateUpdate;
	}

	@Column(name = "meag_description")
	public String getMeagDescription() {
		return this.meagDescription;
	}

	public void setMeagDescription(String meagDescription) {
		this.meagDescription = meagDescription;
	}

	@Column(name = "meag_order")
	public Integer getMeagOrder() {
		return this.meagOrder;
	}

	public void setMeagOrder(Integer meagOrder) {
		this.meagOrder = meagOrder;
	}

	@Column(name = "meag_responsible")
	public String getMeagResponsible() {
		return this.meagResponsible;
	}

	public void setMeagResponsible(String meagResponsible) {
		this.meagResponsible = meagResponsible;
	}

	@Column(name = "meag_status")
	public Boolean getMeagStatus() {
		return this.meagStatus;
	}

	public void setMeagStatus(Boolean meagStatus) {
		this.meagStatus = meagStatus;
	}

	@Column(name = "meag_user_create")
	public String getMeagUserCreate() {
		return this.meagUserCreate;
	}

	public void setMeagUserCreate(String meagUserCreate) {
		this.meagUserCreate = meagUserCreate;
	}

	@Column(name = "meag_user_update")
	public String getMeagUserUpdate() {
		return this.meagUserUpdate;
	}

	public void setMeagUserUpdate(String meagUserUpdate) {
		this.meagUserUpdate = meagUserUpdate;
	}

	// bi-directional many-to-one association to MeetingLog
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "melo_id")
	public MeetingLog getMeetingLog() {
		return this.meetingLog;
	}

	public void setMeetingLog(MeetingLog meetingLog) {
		this.meetingLog = meetingLog;
	}

}