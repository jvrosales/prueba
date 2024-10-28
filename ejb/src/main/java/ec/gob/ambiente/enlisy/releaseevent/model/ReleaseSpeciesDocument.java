package ec.gob.ambiente.enlisy.releaseevent.model;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Where;
import java.util.Date;



/**
 * The persistent class for the release_species_documents database table.
 * 
 */
@Entity
@Table(name="release_species_documents", schema="biodiversity")
@NamedQuery(name="ReleaseSpeciesDocument.findAll", query="SELECT r FROM ReleaseSpeciesDocument r")
@Where(clause = "resd_status='t'")
public class ReleaseSpeciesDocument implements Serializable {
	
	public static final String COB_ESPECIE_LIBERADA_DOC_EL = "COB_ESPECIE_LIBERADA_DOCUMENTO_EL";
	
	private static final long serialVersionUID = 1L;
	private Integer resdId;
	private Integer dotyId;
	private String resdAlfrescoId;
	private Date resdDateCreate;
	private Date resdDateUpdate;
	private String resdExtension;
	private String resdIdProcess;
	private String resdMime;
	private String resdName;
	private String resdProcessName;
	private Boolean resdStatus;
	private String resdUserCreate;
	private String resdUserUpdate;
	private byte[] content;
	private byte[] image;

	public ReleaseSpeciesDocument() {
	}


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RELEASE_SPECIES_DOCUMENT_GENERATOR")
	@SequenceGenerator(name = "RELEASE_SPECIES_DOCUMENT_GENERATOR", initialValue = 1, sequenceName = "seq_resd_id", schema = "biodiversity", allocationSize = 1)
	@Column(name="resd_id")
	public Integer getResdId() {
		return this.resdId;
	}

	public void setResdId(Integer resdId) {
		this.resdId = resdId;
	}


	@Column(name="doty_id")
	public Integer getDotyId() {
		return this.dotyId;
	}

	public void setDotyId(Integer dotyId) {
		this.dotyId = dotyId;
	}


	@Column(name="resd_alfresco_id")
	public String getResdAlfrescoId() {
		return this.resdAlfrescoId;
	}

	public void setResdAlfrescoId(String resdAlfrescoId) {
		this.resdAlfrescoId = resdAlfrescoId;
	}


	@Column(name="resd_date_create")
	public Date getResdDateCreate() {
		return this.resdDateCreate;
	}

	public void setResdDateCreate(Date resdDateCreate) {
		this.resdDateCreate = resdDateCreate;
	}


	@Column(name="resd_date_update")
	public Date getResdDateUpdate() {
		return this.resdDateUpdate;
	}

	public void setResdDateUpdate(Date resdDateUpdate) {
		this.resdDateUpdate = resdDateUpdate;
	}


	@Column(name="resd_extension")
	public String getResdExtension() {
		return this.resdExtension;
	}

	public void setResdExtension(String resdExtension) {
		this.resdExtension = resdExtension;
	}


	@Column(name="resd_id_process")
	public String getResdIdProcess() {
		return this.resdIdProcess;
	}

	public void setResdIdProcess(String resdIdProcess) {
		this.resdIdProcess = resdIdProcess;
	}


	@Column(name="resd_mime")
	public String getResdMime() {
		return this.resdMime;
	}

	public void setResdMime(String resdMime) {
		this.resdMime = resdMime;
	}


	@Column(name="resd_name")
	public String getResdName() {
		return this.resdName;
	}

	public void setResdName(String resdName) {
		this.resdName = resdName;
	}


	@Column(name="resd_process_name")
	public String getResdProcessName() {
		return this.resdProcessName;
	}

	public void setResdProcessName(String resdProcessName) {
		this.resdProcessName = resdProcessName;
	}


	@Column(name="resd_status")
	public Boolean getResdStatus() {
		return this.resdStatus;
	}

	public void setResdStatus(Boolean resdStatus) {
		this.resdStatus = resdStatus;
	}


	@Column(name="resd_user_create")
	public String getResdUserCreate() {
		return this.resdUserCreate;
	}

	public void setResdUserCreate(String resdUserCreate) {
		this.resdUserCreate = resdUserCreate;
	}


	@Column(name="resd_user_update")
	public String getResdUserUpdate() {
		return this.resdUserUpdate;
	}

	public void setResdUserUpdate(String resdUserUpdate) {
		this.resdUserUpdate = resdUserUpdate;
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