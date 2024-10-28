package ec.gob.ambiente.enlisy.wildlifeevent.model;


import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the people_wildlife_events_documents database table.
 * 
 */
@Entity
@Table(name="people_wildlife_events_documents", schema="biodiversity")
@NamedQuery(name="PeopleWildlifeEventsDocument.findAll", query="SELECT p FROM PeopleWildlifeEventsDocument p")
public class PeopleWildlifeEventsDocument implements Serializable {
	
	public static final String COB_INSPECCION_EVENTO_FOTO_GF = "COB_INSPECCION_EVENTO_FOTO_GF";
	public static final String COB_INSPECCION_EVENTO_INFORME_GF = "COB_INSPECCION_EVENTO_INFORME_GF";
	
	private static final long serialVersionUID = 1L;
	private Integer pwedId;
	private Integer dotyId;
	private String pwedAlfrescoId;
	private Date pwedDateCreate;
	private Date pwedDateUpdate;
	private String pwedExtension;
	private String pwedIdProcess;
	private String pwedMime;
	private String pwedName;
	private String pwedProcessName;
	private Boolean pwedStatus;
	private String pwedUserCreate;
	private String pwedUserUpdate;
	private byte[] content;
	private byte[] image;

	private Integer pwedTypeDocumentId;

	public PeopleWildlifeEventsDocument() {
	}


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PEOPLE_WILDLIFE_DOCUMENT_GENERATOR")
	@SequenceGenerator(name = "PEOPLE_WILDLIFE_DOCUMENT_GENERATOR", initialValue = 1, sequenceName = "seq_pwed_id", schema = "biodiversity", allocationSize = 1)
	@Column(name="pwed_id")
	public Integer getPwedId() {
		return this.pwedId;
	}

	public void setPwedId(Integer pwedId) {
		this.pwedId = pwedId;
	}


	@Column(name="doty_id")
	public Integer getDotyId() {
		return this.dotyId;
	}

	public void setDotyId(Integer dotyId) {
		this.dotyId = dotyId;
	}

	@Column(name="pwed_alfresco_id")
	public String getPwedAlfrescoId() {
		return this.pwedAlfrescoId;
	}

	public void setPwedAlfrescoId(String pwedAlfrescoId) {
		this.pwedAlfrescoId = pwedAlfrescoId;
	}


	@Column(name="pwed_date_create")
	public Date getPwedDateCreate() {
		return this.pwedDateCreate;
	}

	public void setPwedDateCreate(Date pwedDateCreate) {
		this.pwedDateCreate = pwedDateCreate;
	}


	@Column(name="pwed_date_update")
	public Date getPwedDateUpdate() {
		return this.pwedDateUpdate;
	}

	public void setPwedDateUpdate(Date pwedDateUpdate) {
		this.pwedDateUpdate = pwedDateUpdate;
	}


	@Column(name="pwed_extension")
	public String getPwedExtension() {
		return this.pwedExtension;
	}

	public void setPwedExtension(String pwedExtension) {
		this.pwedExtension = pwedExtension;
	}


	@Column(name="pwed_id_process")
	public String getPwedIdProcess() {
		return this.pwedIdProcess;
	}

	public void setPwedIdProcess(String pwedIdProcess) {
		this.pwedIdProcess = pwedIdProcess;
	}


	@Column(name="pwed_mime")
	public String getPwedMime() {
		return this.pwedMime;
	}

	public void setPwedMime(String pwedMime) {
		this.pwedMime = pwedMime;
	}


	@Column(name="pwed_name")
	public String getPwedName() {
		return this.pwedName;
	}

	public void setPwedName(String pwedName) {
		this.pwedName = pwedName;
	}


	@Column(name="pwed_process_name")
	public String getPwedProcessName() {
		return this.pwedProcessName;
	}

	public void setPwedProcessName(String pwedProcessName) {
		this.pwedProcessName = pwedProcessName;
	}


	@Column(name="pwed_status")
	public Boolean getPwedStatus() {
		return this.pwedStatus;
	}

	public void setPwedStatus(Boolean pwedStatus) {
		this.pwedStatus = pwedStatus;
	}


	@Column(name="pwed_user_create")
	public String getPwedUserCreate() {
		return this.pwedUserCreate;
	}

	public void setPwedUserCreate(String pwedUserCreate) {
		this.pwedUserCreate = pwedUserCreate;
	}


	@Column(name="pwed_user_update")
	public String getPwedUserUpdate() {
		return this.pwedUserUpdate;
	}

	public void setPwedUserUpdate(String pwedUserUpdate) {
		this.pwedUserUpdate = pwedUserUpdate;
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

	@Column(name="pwed_type_document_id")
	public Integer getPwedTypeDocumentId() {
		return pwedTypeDocumentId;
	}

	public void setPwedTypeDocumentId(Integer pwedTypeDocumentId) {
		this.pwedTypeDocumentId = pwedTypeDocumentId;
	}
}