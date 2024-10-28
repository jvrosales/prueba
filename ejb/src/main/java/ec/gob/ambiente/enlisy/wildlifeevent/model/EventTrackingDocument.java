package ec.gob.ambiente.enlisy.wildlifeevent.model;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Where;
import java.util.Date;


/**
 * The persistent class for the event_tracking_documents database table.
 * 
 */
@Entity
@Table(name="event_tracking_documents", schema="biodiversity")
@NamedQuery(name="EventTrackingDocument.findAll", query="SELECT e FROM EventTrackingDocument e")
@Where(clause = "evtd_status='t'")
public class EventTrackingDocument implements Serializable {
	
	public static final String COB_SEGUIMIENTO_EVENTO_DOC_SE = "COB_SEGUIMIENTO_EVENTO_DOC_SE";
	
	private static final long serialVersionUID = 1L;
	private Integer evtdId;
	private Integer dotyId;
	private String evtdAlfrescoId;
	private Date evtdDateCreate;
	private Date evtdDateUpdate;
	private String evtdExtension;
	private String evtdIdProcess;
	private String evtdMime;
	private String evtdName;
	private String evtdProcessName;
	private Boolean evtdStatus;
	private String evtdUserCreate;
	private String evtdUserUpdate;
	private byte[] content;
	private byte[] image;

	public EventTrackingDocument() {
	}


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVENT_TRACKING_DOCUMENT_GENERATOR")
	@SequenceGenerator(name = "EVENT_TRACKING_DOCUMENT_GENERATOR", initialValue = 1, sequenceName = "seq_evtd_id", schema = "biodiversity", allocationSize = 1)
	@Column(name="evtd_id")
	public Integer getEvtdId() {
		return this.evtdId;
	}

	public void setEvtdId(Integer evtdId) {
		this.evtdId = evtdId;
	}


	@Column(name="doty_id")
	public Integer getDotyId() {
		return this.dotyId;
	}

	public void setDotyId(Integer dotyId) {
		this.dotyId = dotyId;
	}


	@Column(name="evtd_alfresco_id")
	public String getEvtdAlfrescoId() {
		return this.evtdAlfrescoId;
	}

	public void setEvtdAlfrescoId(String evtdAlfrescoId) {
		this.evtdAlfrescoId = evtdAlfrescoId;
	}


	@Column(name="evtd_date_create")
	public Date getEvtdDateCreate() {
		return this.evtdDateCreate;
	}

	public void setEvtdDateCreate(Date evtdDateCreate) {
		this.evtdDateCreate = evtdDateCreate;
	}


	@Column(name="evtd_date_update")
	public Date getEvtdDateUpdate() {
		return this.evtdDateUpdate;
	}

	public void setEvtdDateUpdate(Date evtdDateUpdate) {
		this.evtdDateUpdate = evtdDateUpdate;
	}


	@Column(name="evtd_extension")
	public String getEvtdExtension() {
		return this.evtdExtension;
	}

	public void setEvtdExtension(String evtdExtension) {
		this.evtdExtension = evtdExtension;
	}


	@Column(name="evtd_id_process")
	public String getEvtdIdProcess() {
		return this.evtdIdProcess;
	}

	public void setEvtdIdProcess(String evtdIdProcess) {
		this.evtdIdProcess = evtdIdProcess;
	}


	@Column(name="evtd_mime")
	public String getEvtdMime() {
		return this.evtdMime;
	}

	public void setEvtdMime(String evtdMime) {
		this.evtdMime = evtdMime;
	}


	@Column(name="evtd_name")
	public String getEvtdName() {
		return this.evtdName;
	}

	public void setEvtdName(String evtdName) {
		this.evtdName = evtdName;
	}


	@Column(name="evtd_process_name")
	public String getEvtdProcessName() {
		return this.evtdProcessName;
	}

	public void setEvtdProcessName(String evtdProcessName) {
		this.evtdProcessName = evtdProcessName;
	}


	@Column(name="evtd_status")
	public Boolean getEvtdStatus() {
		return this.evtdStatus;
	}

	public void setEvtdStatus(Boolean evtdStatus) {
		this.evtdStatus = evtdStatus;
	}


	@Column(name="evtd_user_create")
	public String getEvtdUserCreate() {
		return this.evtdUserCreate;
	}

	public void setEvtdUserCreate(String evtdUserCreate) {
		this.evtdUserCreate = evtdUserCreate;
	}


	@Column(name="evtd_user_update")
	public String getEvtdUserUpdate() {
		return this.evtdUserUpdate;
	}

	public void setEvtdUserUpdate(String evtdUserUpdate) {
		this.evtdUserUpdate = evtdUserUpdate;
	}
	
	@Transient
	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	@Transient
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
}