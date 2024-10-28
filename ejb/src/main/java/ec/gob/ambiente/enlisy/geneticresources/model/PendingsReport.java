package ec.gob.ambiente.enlisy.geneticresources.model;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the pendings_reports database table.
 * 
 */
@Entity
@Table(name="pendings_reports", schema="biodiversity")
@NamedQuery(name="PendingsReport.findAll", query="SELECT p FROM PendingsReport p")
public class PendingsReport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pere_id")
	private Integer pereId;

	@Column(name="pere_code")
	private String pereCode;

	@Column(name="pere_date_add")
	private Date pereDateAdd;

	@Column(name="pere_date_update")
	private Date pereDateUpdate;

	@Column(name="pere_document_id")
	private String pereDocumentId;

	@Column(name="pere_project_name")
	private String pereProjectName;

	@Column(name="pere_status")
	private Boolean pereStatus;

	@Column(name="pere_status_report")
	private Boolean pereStatusReport;

	@Column(name="user_id_add")
	private Integer userIdAdd;

	@Column(name="user_id_update")
	private Integer userIdUpdate;

	public PendingsReport() {
	}

	public Integer getPereId() {
		return this.pereId;
	}

	public void setPereId(Integer pereId) {
		this.pereId = pereId;
	}

	public String getPereCode() {
		return this.pereCode;
	}

	public void setPereCode(String pereCode) {
		this.pereCode = pereCode;
	}

	public Date getPereDateAdd() {
		return this.pereDateAdd;
	}

	public void setPereDateAdd(Timestamp pereDateAdd) {
		this.pereDateAdd = pereDateAdd;
	}

	public Date getPereDateUpdate() {
		return this.pereDateUpdate;
	}

	public void setPereDateUpdate(Timestamp pereDateUpdate) {
		this.pereDateUpdate = pereDateUpdate;
	}

	public String getPereDocumentId() {
		return this.pereDocumentId;
	}

	public void setPereDocumentId(String pereDocumentId) {
		this.pereDocumentId = pereDocumentId;
	}

	public String getPereProjectName() {
		return this.pereProjectName;
	}

	public void setPereProjectName(String pereProjectName) {
		this.pereProjectName = pereProjectName;
	}

	public Boolean getPereStatus() {
		return this.pereStatus;
	}

	public void setPereStatus(Boolean pereStatus) {
		this.pereStatus = pereStatus;
	}

	public Boolean getPereStatusReport() {
		return this.pereStatusReport;
	}

	public void setPereStatusReport(Boolean pereStatusReport) {
		this.pereStatusReport = pereStatusReport;
	}

	public Integer getUserIdAdd() {
		return this.userIdAdd;
	}

	public void setUserIdAdd(Integer userIdAdd) {
		this.userIdAdd = userIdAdd;
	}

	public Integer getUserIdUpdate() {
		return this.userIdUpdate;
	}

	public void setUserIdUpdate(Integer userIdUpdate) {
		this.userIdUpdate = userIdUpdate;
	}

}