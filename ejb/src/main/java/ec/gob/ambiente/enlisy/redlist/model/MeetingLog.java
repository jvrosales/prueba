package ec.gob.ambiente.enlisy.redlist.model;

import java.io.InputStream;
import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Where;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * The persistent class for the meeting_log database table.
 * 
 */
@Entity
@Table(name = "meeting_log", schema = "biodiversity")
@NamedQuery(name = "MeetingLog.findAll", query = "SELECT m FROM MeetingLog m")
@Where(clause = "melo_status='t'")
public class MeetingLog implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer meloId;
	private String meloAlfrescoFileExtension;
	private String meloAlfrescoFileId;
	private String meloAlfrescoFileName;
	private String meloAlfrescoMime;
	private String meloCity;
	private String meloCode;
	private String meloMainTopic;
	private Date meloMeetingDate = new Date();
	private String meloPlace;
	private String meloSchedule;
	private String meloScheduleActivities;
	private String meloScheduleTopics;
	private Integer meloSequence = 1;
	private Boolean meloStatus = true;
	private String meloTimeStart;
	private String meloTimeStop;
	private InputStream meloInputStream;
	
	private String meloUserCreate;
	private String meloUserUpdate;
	private Timestamp meloDateCreate;
	private Timestamp meloDateUpdate;

	private List<MeetingAgreement> meetingAgreements;
	private CallRedList callRedList;
	
	private byte[] content;
	private byte[] image;

	private String meloCodeFull = "";

	public MeetingLog() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEET_LOG_GENERATOR")
    @SequenceGenerator(name = "MEET_LOG_GENERATOR", initialValue = 1, sequenceName = "seq_melo_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name = "melo_id")
	public Integer getMeloId() {
		return this.meloId;
	}

	public void setMeloId(Integer meloId) {
		this.meloId = meloId;
	}

	@Column(name = "melo_alfresco_file_extension")
	public String getMeloAlfrescoFileExtension() {
		return this.meloAlfrescoFileExtension;
	}

	public void setMeloAlfrescoFileExtension(String meloAlfrescoFileExtension) {
		this.meloAlfrescoFileExtension = meloAlfrescoFileExtension;
	}

	@Column(name = "melo_alfresco_file_id")
	public String getMeloAlfrescoFileId() {
		return this.meloAlfrescoFileId;
	}

	public void setMeloAlfrescoFileId(String meloAlfrescoFileId) {
		this.meloAlfrescoFileId = meloAlfrescoFileId;
	}

	@Column(name = "melo_alfresco_file_name")
	public String getMeloAlfrescoFileName() {
		return this.meloAlfrescoFileName;
	}

	public void setMeloAlfrescoFileName(String meloAlfrescoFileName) {
		this.meloAlfrescoFileName = meloAlfrescoFileName;
	}

	@Column(name = "melo_alfresco_mime")
	public String getMeloAlfrescoMime() {
		return this.meloAlfrescoMime;
	}

	public void setMeloAlfrescoMime(String meloAlfrescoMime) {
		this.meloAlfrescoMime = meloAlfrescoMime;
	}

	@Column(name = "melo_city")
	public String getMeloCity() {
		return this.meloCity;
	}

	public void setMeloCity(String meloCity) {
		this.meloCity = meloCity;
	}

	@Column(name = "melo_code")
	public String getMeloCode() {
		return this.meloCode;
	}

	public void setMeloCode(String meloCode) {
		this.meloCode = meloCode;
	}

	@Column(name = "melo_date_create")
	public Timestamp getMeloDateCreate() {
		return this.meloDateCreate;
	}

	public void setMeloDateCreate(Timestamp meloDateCreate) {
		this.meloDateCreate = meloDateCreate;
	}

	@Column(name = "melo_date_update")
	public Timestamp getMeloDateUpdate() {
		return this.meloDateUpdate;
	}

	public void setMeloDateUpdate(Timestamp meloDateUpdate) {
		this.meloDateUpdate = meloDateUpdate;
	}

	@Column(name = "melo_main_topic")
	public String getMeloMainTopic() {
		return this.meloMainTopic;
	}

	public void setMeloMainTopic(String meloMainTopic) {
		this.meloMainTopic = meloMainTopic;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "melo_meeting_date")
	public Date getMeloMeetingDate() {
		return this.meloMeetingDate;
	}

	public void setMeloMeetingDate(Date meloMeetingDate) {
		this.meloMeetingDate = meloMeetingDate;
	}

	@Column(name = "melo_place")
	public String getMeloPlace() {
		return this.meloPlace;
	}

	public void setMeloPlace(String meloPlace) {
		this.meloPlace = meloPlace;
	}

	@Column(name = "melo_schedule")
	public String getMeloSchedule() {
		return this.meloSchedule;
	}

	public void setMeloSchedule(String meloSchedule) {
		this.meloSchedule = meloSchedule;
	}

	@Column(name = "melo_schedule_activities")
	public String getMeloScheduleActivities() {
		return this.meloScheduleActivities;
	}

	public void setMeloScheduleActivities(String meloScheduleActivities) {
		this.meloScheduleActivities = meloScheduleActivities;
	}

	@Column(name = "melo_schedule_topics")
	public String getMeloScheduleTopics() {
		return this.meloScheduleTopics;
	}

	public void setMeloScheduleTopics(String meloScheduleTopics) {
		this.meloScheduleTopics = meloScheduleTopics;
	}

	@Column(name = "melo_sequence")
	public Integer getMeloSequence() {
		return this.meloSequence;
	}

	public void setMeloSequence(Integer meloSequence) {
		this.meloSequence = meloSequence;
	}

	@Column(name = "melo_status")
	public Boolean getMeloStatus() {
		return this.meloStatus;
	}

	public void setMeloStatus(Boolean meloStatus) {
		this.meloStatus = meloStatus;
	}

	@Column(name = "melo_time_start")
	public String getMeloTimeStart() {
		return this.meloTimeStart;
	}

	public void setMeloTimeStart(String meloTimeStart) {
		this.meloTimeStart = meloTimeStart;
	}

	@Column(name = "melo_time_stop")
	public String getMeloTimeStop() {
		return this.meloTimeStop;
	}

	public void setMeloTimeStop(String meloTimeStop) {
		this.meloTimeStop = meloTimeStop;
	}

	@Column(name = "melo_user_create")
	public String getMeloUserCreate() {
		return this.meloUserCreate;
	}

	public void setMeloUserCreate(String meloUserCreate) {
		this.meloUserCreate = meloUserCreate;
	}

	@Column(name = "melo_user_update")
	public String getMeloUserUpdate() {
		return this.meloUserUpdate;
	}

	public void setMeloUserUpdate(String meloUserUpdate) {
		this.meloUserUpdate = meloUserUpdate;
	}

	// bi-directional many-to-one association to MeetingAgreement
	@OneToMany(mappedBy = "meetingLog")
	public List<MeetingAgreement> getMeetingAgreements() {
		return this.meetingAgreements;
	}

	public void setMeetingAgreements(List<MeetingAgreement> meetingAgreements) {
		this.meetingAgreements = meetingAgreements;
	}

	public MeetingAgreement addMeetingAgreement(MeetingAgreement meetingAgreement) {
		getMeetingAgreements().add(meetingAgreement);
		meetingAgreement.setMeetingLog(this);

		return meetingAgreement;
	}

	public MeetingAgreement removeMeetingAgreement(MeetingAgreement meetingAgreement) {
		getMeetingAgreements().remove(meetingAgreement);
		meetingAgreement.setMeetingLog(null);

		return meetingAgreement;
	}

	// bi-directional many-to-one association to CallRedList
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "care_id")
	public CallRedList getCallRedList() {
		return this.callRedList;
	}

	public void setCallRedList(CallRedList callRedList) {
		this.callRedList = callRedList;
	}

	@Transient
	public String getMeloCodeFull() {
		return meloCodeFull;
	}

	@Transient
	public void setMeloCodeFull(String meloCodeFull) {
		this.meloCodeFull = meloCodeFull;
	}

	@Transient
	public byte[] getContent() {
		return content;
	}

	@Transient
	public void setContent(byte[] content) {
		this.content = content;
	}

	@Transient
	public byte[] getImage() {
		return image;
	}

	@Transient
	public void setImage(byte[] image) {
		this.image = image;
	}

	@Transient
	public InputStream getMeloInputStream() {
		return meloInputStream;
	}

	public void setMeloInputStream(InputStream meloInputStream) {
		this.meloInputStream = meloInputStream;
	}
	
	
	
	
}