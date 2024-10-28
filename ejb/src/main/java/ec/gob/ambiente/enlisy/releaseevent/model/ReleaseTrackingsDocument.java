package ec.gob.ambiente.enlisy.releaseevent.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.Where;



/**
 * The persistent class for the release_trackings_documents database table.
 * 
 */
@Entity
@Table(name="release_trackings_documents", schema="biodiversity")
@NamedQuery(name="ReleaseTrackingsDocument.findAll", query="SELECT r FROM ReleaseTrackingsDocument r")
@Where(clause = "retd_status='t'")
public class ReleaseTrackingsDocument implements Serializable {
	
	public static final String COB_MONITOREO_LIBERACION_IMG_ML = "COB_MONITOREO_LIBERACION_IMAGEN_ML";
	public static final String COB_MONITOREO_LIBERACION_DOC_ML = "COB_MONITOREO_LIBERACION_DOCUMENTO_ML";
	
	private static final long serialVersionUID = 1L;
	private Integer retdId;
	private Integer dotyId;
	private String retdAlfrescoId;
	private Date retdDateCreate;
	private Date retdDateUpdate;
	private String retdExtension;
	private String retdIdProcess;
	private String retdMime;
	private String retdName;
	private String retdProcessName;
	private Boolean retdStatus;
	private String retdUserCreate;
	private String retdUserUpdate;
	private byte[] content;
	private byte[] image;

	public ReleaseTrackingsDocument() {
	}


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RELEASE_TRACKING_DOCUMENT_GENERATOR")
	@SequenceGenerator(name = "RELEASE_TRACKING_DOCUMENT_GENERATOR", initialValue = 1, sequenceName = "seq_retd_id", schema = "biodiversity", allocationSize = 1)
	@Column(name="retd_id")
	public Integer getRetdId() {
		return this.retdId;
	}

	public void setRetdId(Integer retdId) {
		this.retdId = retdId;
	}


	@Column(name="doty_id")
	public Integer getDotyId() {
		return this.dotyId;
	}

	public void setDotyId(Integer dotyId) {
		this.dotyId = dotyId;
	}


	@Column(name="retd_alfresco_id")
	public String getRetdAlfrescoId() {
		return this.retdAlfrescoId;
	}

	public void setRetdAlfrescoId(String retdAlfrescoId) {
		this.retdAlfrescoId = retdAlfrescoId;
	}


	@Column(name="retd_date_create")
	public Date getRetdDateCreate() {
		return this.retdDateCreate;
	}

	public void setRetdDateCreate(Date retdDateCreate) {
		this.retdDateCreate = retdDateCreate;
	}


	@Column(name="retd_date_update")
	public Date getRetdDateUpdate() {
		return this.retdDateUpdate;
	}

	public void setRetdDateUpdate(Date retdDateUpdate) {
		this.retdDateUpdate = retdDateUpdate;
	}


	@Column(name="retd_extension")
	public String getRetdExtension() {
		return this.retdExtension;
	}

	public void setRetdExtension(String retdExtension) {
		this.retdExtension = retdExtension;
	}


	@Column(name="retd_id_process")
	public String getRetdIdProcess() {
		return this.retdIdProcess;
	}

	public void setRetdIdProcess(String retdIdProcess) {
		this.retdIdProcess = retdIdProcess;
	}


	@Column(name="retd_mime")
	public String getRetdMime() {
		return this.retdMime;
	}

	public void setRetdMime(String retdMime) {
		this.retdMime = retdMime;
	}


	@Column(name="retd_name")
	public String getRetdName() {
		return this.retdName;
	}

	public void setRetdName(String retdName) {
		this.retdName = retdName;
	}


	@Column(name="retd_process_name")
	public String getRetdProcessName() {
		return this.retdProcessName;
	}

	public void setRetdProcessName(String retdProcessName) {
		this.retdProcessName = retdProcessName;
	}


	@Column(name="retd_status")
	public Boolean getRetdStatus() {
		return this.retdStatus;
	}

	public void setRetdStatus(Boolean retdStatus) {
		this.retdStatus = retdStatus;
	}


	@Column(name="retd_user_create")
	public String getRetdUserCreate() {
		return this.retdUserCreate;
	}

	public void setRetdUserCreate(String retdUserCreate) {
		this.retdUserCreate = retdUserCreate;
	}


	@Column(name="retd_user_update")
	public String getRetdUserUpdate() {
		return this.retdUserUpdate;
	}

	public void setRetdUserUpdate(String retdUserUpdate) {
		this.retdUserUpdate = retdUserUpdate;
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