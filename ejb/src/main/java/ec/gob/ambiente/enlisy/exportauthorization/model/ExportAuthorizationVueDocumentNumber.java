package ec.gob.ambiente.enlisy.exportauthorization.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the export_authorization_vue_documents database table.
 * 
 */
@Entity
@Table(name="export_authorization_vue_documents_numbers", schema="biodiversity")
@NamedQuery(name="ExportAuthorizationVueDocumentNumber.findAll", query="SELECT p FROM ExportAuthorizationVueDocumentNumber p")
@Where(clause = "eadn_status='t'")
public class ExportAuthorizationVueDocumentNumber implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer eadnId;
	private Integer eadnType;
	private String eadnName;
	private String eadnNumber;
	private Date eadnDateCreate;
	private Date eadnDateUpdate;
	private String eadnUserCreate;
	private String eadnUserUpdate;
	private Boolean eadnStatus;


	private String username;
	private String documentType;
	private String documentNumber;
	private String documentStatus;
	private Date validDate;
	private byte[] content;

	private String eadnDocumentName;
	private String eadnMime;
	private String eadnExtension;
	private String eadnAlfrescoId;
	private String eadnIdProcess;

	private ExportAuthorizationVue exportAuthorizationVue;

	public ExportAuthorizationVueDocumentNumber() {
	}


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXPORT_AUTHORIZATION_DOCUMENT_NUMBER_GENERATOR")
	@SequenceGenerator(name = "EXPORT_AUTHORIZATION_DOCUMENT_NUMBER_GENERATOR", initialValue = 1, sequenceName = "seq_eadn_id", schema = "biodiversity", allocationSize = 1)
	@Column(name="eadn_id")
	public Integer getEadnId() {
		return this.eadnId;
	}

	public void setEadnId(Integer eadnId) {
		this.eadnId = eadnId;
	}

	@Column(name="eadn_type")
	public Integer getEadnType() {
		return eadnType;
	}

	public void setEadnType(Integer eadnType) {
		this.eadnType = eadnType;
	}

	@Column(name="eadn_name")
	public String getEadnName() {
		return eadnName;
	}

	public void setEadnName(String eadnName) {
		this.eadnName = eadnName;
	}

	@Column(name="eadn_number")
	public String getEadnNumber() {
		return eadnNumber;
	}

	public void setEadnNumber(String eadnNumber) {
		this.eadnNumber = eadnNumber;
	}

	@Column(name="eadn_date_create")
	public Date getEadnDateCreate() {
		return this.eadnDateCreate;
	}

	public void setEadnDateCreate(Date eadnDateCreate) {
		this.eadnDateCreate = eadnDateCreate;
	}


	@Column(name="eadn_date_update")
	public Date getEadnDateUpdate() {
		return this.eadnDateUpdate;
	}

	public void setEadnDateUpdate(Date eadnDateUpdate) {
		this.eadnDateUpdate = eadnDateUpdate;
	}

	@Column(name="eadn_user_create")
	public String getEadnUserCreate() {
		return this.eadnUserCreate;
	}

	public void setEadnUserCreate(String eadnUserCreate) {
		this.eadnUserCreate = eadnUserCreate;
	}


	@Column(name="eadn_user_update")
	public String getEadnUserUpdate() {
		return this.eadnUserUpdate;
	}

	public void setEadnUserUpdate(String eadnUserUpdate) {
		this.eadnUserUpdate = eadnUserUpdate;
	}

	@Column(name="eadn_status")
	public Boolean getEadnStatus() {
		return this.eadnStatus;
	}

	public void setEadnStatus(Boolean eadnStatus) {
		this.eadnStatus = eadnStatus;
	}

	//bi-directional many-to-one association to ExportAuthorizationVue
	@ManyToOne
	@JoinColumn(name="eavu_id")
	public ExportAuthorizationVue getExportAuthorizationVue() {
		return this.exportAuthorizationVue;
	}

	public void setExportAuthorizationVue(ExportAuthorizationVue exportAuthorizationVue) {
		this.exportAuthorizationVue = exportAuthorizationVue;
	}

	@Transient
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Transient
	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	@Transient
	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	@Transient
	public String getDocumentStatus() {
		return documentStatus;
	}

	public void setDocumentStatus(String documentStatus) {
		this.documentStatus = documentStatus;
	}

	@Transient
	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	@Transient
	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	@Column(name="eadn_document_name")
	public String getEadnDocumentName() {
		return eadnDocumentName;
	}

	public void setEadnDocumentName(String eadnDocumentName) {
		this.eadnDocumentName = eadnDocumentName;
	}

	@Column(name="eadn_mime")
	public String getEadnMime() {
		return eadnMime;
	}

	public void setEadnMime(String eadnMime) {
		this.eadnMime = eadnMime;
	}

	@Column(name="eadn_extension")
	public String getEadnExtension() {
		return eadnExtension;
	}

	public void setEadnExtension(String eadnExtension) {
		this.eadnExtension = eadnExtension;
	}

	@Column(name="eadn_alfresco_id")
	public String getEadnAlfrescoId() {
		return eadnAlfrescoId;
	}

	public void setEadnAlfrescoId(String eadnAlfrescoId) {
		this.eadnAlfrescoId = eadnAlfrescoId;
	}

	@Column(name="eadn_id_process")
	public String getEadnIdProcess() {
		return eadnIdProcess;
	}

	public void setEadnIdProcess(String eadnIdProcess) {
		this.eadnIdProcess = eadnIdProcess;
	}
}