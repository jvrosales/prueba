package ec.gob.ambiente.enlisy.wildlifeevent.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the run_over_events_documents database table.
 * 
 */
@Entity
@Table(name = "run_over_events_documents", schema = "biodiversity")
@NamedQuery(name = "RunOverEventsDocument.findAll", query = "SELECT r FROM RunOverEventsDocument r")
public class RunOverEventsDocument implements Serializable {

	public static final String COB_INSPECCION_EVENTO_FOTO_EA = "COB_INSPECCION_EVENTO_FOTO_EA";
	public static final String COB_INSPECCION_EVENTO_INFORME_EA = "COB_INSPECCION_EVENTO_INFORME_EA";
	public static final String COB_REGISTRO_EVENTO_PREFOTO_EA = "COB_REGISTRO_EVENTO_PREFOTO_EA";
	public static final String COB_INSPECCION_EVENTO_COMPRIMIDO_EA = "COB_INSPECCION_EVENTO_COMPRIMIDO_EA";
	public static final String COB_ACTA_DESTINO_FINAL_EA = "COB_ACTA_DESTINO_FINAL_EA";

	private static final long serialVersionUID = 1L;
	private Integer roedId;
	private Integer dotyId;
	private String roedAlfrescoId;
	private Date roedDateCreate;
	private Date roedDateUpdate;
	private String roedExtension;
	private String roedIdProcess;
	private String roedMime;
	private String roedName;
	private String roedProcessName;
	private Boolean roedStatus;
	private String roedUserCreate;
	private String roedUserUpdate;
	private byte[] content;
	private byte[] image;

	public RunOverEventsDocument() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RUN_OVER_EVENTS_DOCUMENT_GENERATOR")
	@SequenceGenerator(name = "RUN_OVER_EVENTS_DOCUMENT_GENERATOR", initialValue = 1, sequenceName = "seq_roed_id", schema = "biodiversity", allocationSize = 1)
	@Column(name = "roed_id", unique = true, nullable = false)
	public Integer getRoedId() {
		return this.roedId;
	}

	public void setRoedId(Integer roedId) {
		this.roedId = roedId;
	}

	@Column(name = "doty_id")
	public Integer getDotyId() {
		return this.dotyId;
	}

	public void setDotyId(Integer dotyId) {
		this.dotyId = dotyId;
	}

	@Column(name = "roed_alfresco_id", length = 255)
	public String getRoedAlfrescoId() {
		return this.roedAlfrescoId;
	}

	public void setRoedAlfrescoId(String roedAlfrescoId) {
		this.roedAlfrescoId = roedAlfrescoId;
	}

	@Column(name = "roed_date_create")
	public Date getRoedDateCreate() {
		return this.roedDateCreate;
	}

	public void setRoedDateCreate(Date roedDateCreate) {
		this.roedDateCreate = roedDateCreate;
	}

	@Column(name = "roed_date_update")
	public Date getRoedDateUpdate() {
		return this.roedDateUpdate;
	}

	public void setRoedDateUpdate(Date roedDateUpdate) {
		this.roedDateUpdate = roedDateUpdate;
	}

	@Column(name = "roed_extension", length = 255)
	public String getRoedExtension() {
		return this.roedExtension;
	}

	public void setRoedExtension(String roedExtension) {
		this.roedExtension = roedExtension;
	}

	@Column(name = "roed_id_process", length = 255)
	public String getRoedIdProcess() {
		return this.roedIdProcess;
	}

	public void setRoedIdProcess(String roedIdProcess) {
		this.roedIdProcess = roedIdProcess;
	}

	@Column(name = "roed_mime", length = 255)
	public String getRoedMime() {
		return this.roedMime;
	}

	public void setRoedMime(String roedMime) {
		this.roedMime = roedMime;
	}

	@Column(name = "roed_name", length = 255)
	public String getRoedName() {
		return this.roedName;
	}

	public void setRoedName(String roedName) {
		this.roedName = roedName;
	}

	@Column(name = "roed_process_name", length = 255)
	public String getRoedProcessName() {
		return this.roedProcessName;
	}

	public void setRoedProcessName(String roedProcessName) {
		this.roedProcessName = roedProcessName;
	}

	@Column(name = "roed_status")
	public Boolean getRoedStatus() {
		return this.roedStatus;
	}

	public void setRoedStatus(Boolean roedStatus) {
		this.roedStatus = roedStatus;
	}

	@Column(name = "roed_user_create", length = 255)
	public String getRoedUserCreate() {
		return this.roedUserCreate;
	}

	public void setRoedUserCreate(String roedUserCreate) {
		this.roedUserCreate = roedUserCreate;
	}

	@Column(name = "roed_user_update", length = 255)
	public String getRoedUserUpdate() {
		return this.roedUserUpdate;
	}

	public void setRoedUserUpdate(String roedUserUpdate) {
		this.roedUserUpdate = roedUserUpdate;
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