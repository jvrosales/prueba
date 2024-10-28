package ec.gob.ambiente.enlisy.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the survey_response database table.
 * 
 */
@Entity
@Table(name="survey_response", schema="suia_survey")
@NamedQuery(name="SurveyResponse.findAll", query="SELECT s FROM SurveyResponse s")
public class SurveyResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="srvrsp_id")
	private Integer srvrspId;

	@Column(name="srvrsp_app")
	private String srvrspApp;

	@Column(name="srvrsp_create_date")
	private Timestamp srvrspCreateDate;

	@Column(name="srvrsp_create_user")
	private String srvrspCreateUser;

	@Column(name="srvrsp_person_type")
	private String srvrspPersonType;

	@Column(name="srvrsp_project")
	private String srvrspProject;

	@Column(name="srvrsp_saf_user_type")
	private String srvrspSafUserType;

	@Column(name="srvrsp_status")
	private Boolean srvrspStatus;

	@Column(name="srvrsp_suggestions")
	private String srvrspSuggestions;

	@Column(name="srvrsp_update_date")
	private Timestamp srvrspUpdateDate;

	@Column(name="srvrsp_update_user")
	private String srvrspUpdateUser;

	@Column(name="srvrsp_user_type")
	private String srvrspUserType;

	@Column(name="survey_id")
	private Integer surveyId;

	public SurveyResponse() {
	}

	public Integer getSrvrspId() {
		return this.srvrspId;
	}

	public void setSrvrspId(Integer srvrspId) {
		this.srvrspId = srvrspId;
	}

	public String getSrvrspApp() {
		return this.srvrspApp;
	}

	public void setSrvrspApp(String srvrspApp) {
		this.srvrspApp = srvrspApp;
	}

	public Timestamp getSrvrspCreateDate() {
		return this.srvrspCreateDate;
	}

	public void setSrvrspCreateDate(Timestamp srvrspCreateDate) {
		this.srvrspCreateDate = srvrspCreateDate;
	}

	public String getSrvrspCreateUser() {
		return this.srvrspCreateUser;
	}

	public void setSrvrspCreateUser(String srvrspCreateUser) {
		this.srvrspCreateUser = srvrspCreateUser;
	}

	public String getSrvrspPersonType() {
		return this.srvrspPersonType;
	}

	public void setSrvrspPersonType(String srvrspPersonType) {
		this.srvrspPersonType = srvrspPersonType;
	}

	public String getSrvrspProject() {
		return this.srvrspProject;
	}

	public void setSrvrspProject(String srvrspProject) {
		this.srvrspProject = srvrspProject;
	}

	public String getSrvrspSafUserType() {
		return this.srvrspSafUserType;
	}

	public void setSrvrspSafUserType(String srvrspSafUserType) {
		this.srvrspSafUserType = srvrspSafUserType;
	}

	public Boolean getSrvrspStatus() {
		return this.srvrspStatus;
	}

	public void setSrvrspStatus(Boolean srvrspStatus) {
		this.srvrspStatus = srvrspStatus;
	}

	public String getSrvrspSuggestions() {
		return this.srvrspSuggestions;
	}

	public void setSrvrspSuggestions(String srvrspSuggestions) {
		this.srvrspSuggestions = srvrspSuggestions;
	}

	public Timestamp getSrvrspUpdateDate() {
		return this.srvrspUpdateDate;
	}

	public void setSrvrspUpdateDate(Timestamp srvrspUpdateDate) {
		this.srvrspUpdateDate = srvrspUpdateDate;
	}

	public String getSrvrspUpdateUser() {
		return this.srvrspUpdateUser;
	}

	public void setSrvrspUpdateUser(String srvrspUpdateUser) {
		this.srvrspUpdateUser = srvrspUpdateUser;
	}

	public String getSrvrspUserType() {
		return this.srvrspUserType;
	}

	public void setSrvrspUserType(String srvrspUserType) {
		this.srvrspUserType = srvrspUserType;
	}

	public Integer getSurveyId() {
		return this.surveyId;
	}

	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}

}