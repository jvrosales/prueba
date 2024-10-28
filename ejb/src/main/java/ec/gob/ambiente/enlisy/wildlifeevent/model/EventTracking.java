package ec.gob.ambiente.enlisy.wildlifeevent.model;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Where;

import java.sql.Timestamp;


/**
 * The persistent class for the event_tracking database table.
 * 
 */
@Entity
@Table(name="event_tracking", schema="biodiversity")
@NamedQuery(name="EventTracking.findAll", query="SELECT e FROM EventTracking e")
@Where(clause = "evtr_status='t'")
public class EventTracking implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer evtrId;
	private Integer evtrActionType;
	private Timestamp evtrDate;
	private Timestamp evtrDateCreate;
	private Timestamp evtrDateUpdate;
	private String evtrDescription;
	private String evtrDocs;
	private String evtrSignatory;
	private Boolean evtrStatus=true;
	private String evtrUserCreate;
	private String evtrUserUpdate;
	private GeneralData generalData;
	
	private EventTrackingDocument eventDocument = new EventTrackingDocument();

	public EventTracking() {
	}


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVENT_TRACKING_GENERATOR")
	@SequenceGenerator(name = "EVENT_TRACKING_GENERATOR", initialValue = 1, sequenceName = "seq_evtr_id", schema = "biodiversity", allocationSize = 1)
	@Column(name="evtr_id")
	public Integer getEvtrId() {
		return this.evtrId;
	}

	public void setEvtrId(Integer evtrId) {
		this.evtrId = evtrId;
	}


	@Column(name="evtr_action_type")
	public Integer getEvtrActionType() {
		return this.evtrActionType;
	}

	public void setEvtrActionType(Integer evtrActionType) {
		this.evtrActionType = evtrActionType;
	}


	@Column(name="evtr_date")
	public Timestamp getEvtrDate() {
		return this.evtrDate;
	}

	public void setEvtrDate(Timestamp evtrDate) {
		this.evtrDate = evtrDate;
	}


	@Column(name="evtr_date_create")
	public Timestamp getEvtrDateCreate() {
		return this.evtrDateCreate;
	}

	public void setEvtrDateCreate(Timestamp evtrDateCreate) {
		this.evtrDateCreate = evtrDateCreate;
	}


	@Column(name="evtr_date_update")
	public Timestamp getEvtrDateUpdate() {
		return this.evtrDateUpdate;
	}

	public void setEvtrDateUpdate(Timestamp evtrDateUpdate) {
		this.evtrDateUpdate = evtrDateUpdate;
	}


	@Column(name="evtr_description")
	public String getEvtrDescription() {
		return this.evtrDescription;
	}

	public void setEvtrDescription(String evtrDescription) {
		this.evtrDescription = evtrDescription;
	}


	@Column(name="evtr_docs")
	public String getEvtrDocs() {
		return this.evtrDocs;
	}

	public void setEvtrDocs(String evtrDocs) {
		this.evtrDocs = evtrDocs;
	}


	@Column(name="evtr_signatory")
	public String getEvtrSignatory() {
		return this.evtrSignatory;
	}

	public void setEvtrSignatory(String evtrSignatory) {
		this.evtrSignatory = evtrSignatory;
	}


	@Column(name="evtr_status")
	public Boolean getEvtrStatus() {
		return this.evtrStatus;
	}

	public void setEvtrStatus(Boolean evtrStatus) {
		this.evtrStatus = evtrStatus;
	}


	@Column(name="evtr_user_create")
	public String getEvtrUserCreate() {
		return this.evtrUserCreate;
	}

	public void setEvtrUserCreate(String evtrUserCreate) {
		this.evtrUserCreate = evtrUserCreate;
	}


	@Column(name="evtr_user_update")
	public String getEvtrUserUpdate() {
		return this.evtrUserUpdate;
	}

	public void setEvtrUserUpdate(String evtrUserUpdate) {
		this.evtrUserUpdate = evtrUserUpdate;
	}


	//bi-directional many-to-one association to GeneralData
	@ManyToOne
	@JoinColumn(name="geda_id")
	public GeneralData getGeneralData() {
		return this.generalData;
	}

	public void setGeneralData(GeneralData generalData) {
		this.generalData = generalData;
	}

	@Transient
	public EventTrackingDocument getEventDocument() {
		return eventDocument;
	}

	public void setEventDocument(EventTrackingDocument eventDocument) {
		this.eventDocument = eventDocument;
	}
}