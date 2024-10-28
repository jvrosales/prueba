package ec.gob.ambiente.enlisy.exportauthorization.model;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The persistent class for the export_authorization_vue_documents database table.
 * 
 */
@Entity
@Table(name="export_authorization_vue_documents", schema="biodiversity")
@NamedQuery(name="ExportAuthorizationVueDocument.findAll", query="SELECT p FROM ExportAuthorizationVueDocument p")
public class ExportAuthorizationVueDocument implements Serializable {
	
	public static final String COB_CRI_TECNICO_BIOSEGURIDAD_TB = "COB_CRI_TECNICO_BIOSEGURIDAD_TB";
	public static final String COB_CRI_TECNICO_AUTORIDAD_TA = "COB_CRI_TECNICO_AUTORIDAD_TA";

	public static final Integer PERMISO_CITES= 5702;
	public static final Integer PERMISO_CITES_DETALLE = 5703;
	public static final Integer PERMISO_CITES_ANEXO = 5704;
	public static final Integer PERMISO_CITES_ANEXO_DETALLE = 5705;

	public static final String PERMISO_CITES_CODE = "PERMISO_CITES";
	public static final String PERMISO_CITES_DETALLE_CODE = "PERMISO_CITES_DET";
	public static final String PERMISO_CITES_ANEXO_CODE = "PERMISO_CITES_ANEXO";
	public static final String PERMISO_CITES_ANEXO_DETALLE_CODE = "PERMISO_CITES_ANEXO_DET";
	private static final long serialVersionUID = 1L;
	private Integer eavdId;
	private Integer dotyId;
	private String eavdAlfrescoId;
	private Date eavdDateCreate;
	private Date eavdDateUpdate;
	private String eavdExtension;
	private String eavdIdProcess;
	private String eavdMime;
	private String eavdName;
	private String eavdProcessName;
	private Boolean eavdStatus;
	private String eavdUserCreate;
	private String eavdUserUpdate;
	private byte[] content;
	private byte[] image;
	private Integer documentId;
	private InputStream is;
	private String documentName;
	private String applyAppendix;

	public ExportAuthorizationVueDocument() {
	}


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXPORT_AUTHORIZATION_DOCUMENT_GENERATOR")
	@SequenceGenerator(name = "EXPORT_AUTHORIZATION_DOCUMENT_GENERATOR", initialValue = 1, sequenceName = "seq_eavd_id", schema = "biodiversity", allocationSize = 1)
	@Column(name="eavd_id")
	public Integer getEavdId() {
		return this.eavdId;
	}

	public void setEavdId(Integer eavdId) {
		this.eavdId = eavdId;
	}


	@Column(name="doty_id")
	public Integer getDotyId() {
		return this.dotyId;
	}

	public void setDotyId(Integer dotyId) {
		this.dotyId = dotyId;
	}

	@Column(name="eavd_alfresco_id")
	public String getEavdAlfrescoId() {
		return this.eavdAlfrescoId;
	}

	public void setEavdAlfrescoId(String eavdAlfrescoId) {
		this.eavdAlfrescoId = eavdAlfrescoId;
	}


	@Column(name="eavd_date_create")
	public Date getEavdDateCreate() {
		return this.eavdDateCreate;
	}

	public void setEavdDateCreate(Date eavdDateCreate) {
		this.eavdDateCreate = eavdDateCreate;
	}


	@Column(name="eavd_date_update")
	public Date getEavdDateUpdate() {
		return this.eavdDateUpdate;
	}

	public void setEavdDateUpdate(Date eavdDateUpdate) {
		this.eavdDateUpdate = eavdDateUpdate;
	}


	@Column(name="eavd_extension")
	public String getEavdExtension() {
		return this.eavdExtension;
	}

	public void setEavdExtension(String eavdExtension) {
		this.eavdExtension = eavdExtension;
	}


	@Column(name="eavd_id_process")
	public String getEavdIdProcess() {
		return this.eavdIdProcess;
	}

	public void setEavdIdProcess(String eavdIdProcess) {
		this.eavdIdProcess = eavdIdProcess;
	}


	@Column(name="eavd_mime")
	public String getEavdMime() {
		return this.eavdMime;
	}

	public void setEavdMime(String eavdMime) {
		this.eavdMime = eavdMime;
	}


	@Column(name="eavd_name")
	public String getEavdName() {
		return this.eavdName;
	}

	public void setEavdName(String eavdName) {
		this.eavdName = eavdName;
	}


	@Column(name="eavd_process_name")
	public String getEavdProcessName() {
		return this.eavdProcessName;
	}

	public void setEavdProcessName(String eavdProcessName) {
		this.eavdProcessName = eavdProcessName;
	}


	@Column(name="eavd_status")
	public Boolean getEavdStatus() {
		return this.eavdStatus;
	}

	public void setEavdStatus(Boolean eavdStatus) {
		this.eavdStatus = eavdStatus;
	}


	@Column(name="eavd_user_create")
	public String getEavdUserCreate() {
		return this.eavdUserCreate;
	}

	public void setEavdUserCreate(String eavdUserCreate) {
		this.eavdUserCreate = eavdUserCreate;
	}


	@Column(name="eavd_user_update")
	public String getEavdUserUpdate() {
		return this.eavdUserUpdate;
	}

	public void setEavdUserUpdate(String eavdUserUpdate) {
		this.eavdUserUpdate = eavdUserUpdate;
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

	@Transient
	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	@Column(name="eavd_document_id")
	public Integer getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Integer documentId) {
		this.documentId = documentId;
	}

	@Transient
	public String getApplyAppendix() {
		return applyAppendix;
	}

	public void setApplyAppendix(String applyAppendix) {
		this.applyAppendix = applyAppendix;
	}

	@Transient
	public InputStream getIs() {
		return is;
	}


	public void setIs(InputStream is) {
		this.is = is;
	}
	
	
}