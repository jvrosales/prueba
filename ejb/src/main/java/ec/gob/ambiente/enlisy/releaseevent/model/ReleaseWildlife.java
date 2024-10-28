package ec.gob.ambiente.enlisy.releaseevent.model;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Where;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the release_wildlife database table.
 * 
 */
@Entity
@Table(name="release_wildlife", schema="biodiversity")
@NamedQuery(name="ReleaseWildlife.findAll", query="SELECT r FROM ReleaseWildlife r")
@Where(clause = "rewi_status='t'")
public class ReleaseWildlife implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer rewiId;
	private Integer peopId;
	private Integer rewiClass;
	private String rewiCode;
	private Timestamp rewiDate;
	private Timestamp rewiDateCreate;
	private Timestamp rewiDateUpdate;
	private String rewiRegisterJob;
	private String rewiRegisterName;
	private Boolean rewiStatus = true;
	private Integer rewiType;
	private String rewiUserCreate;
	private String rewiUserUpdate;
	private Integer userId;
	private List<ReleaseSpecy> releaseSpecies;

	public ReleaseWildlife() {
	}


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RELEASE_WILDLIFE_GENERATOR")
	@SequenceGenerator(name = "RELEASE_WILDLIFE_GENERATOR", initialValue = 1, sequenceName = "seq_rewi_id", schema = "biodiversity", allocationSize = 1)
	@Column(name="rewi_id")
	public Integer getRewiId() {
		return this.rewiId;
	}

	public void setRewiId(Integer rewiId) {
		this.rewiId = rewiId;
	}


	@Column(name="peop_id")
	public Integer getPeopId() {
		return this.peopId;
	}

	public void setPeopId(Integer peopId) {
		this.peopId = peopId;
	}


	@Column(name="rewi_class")
	public Integer getRewiClass() {
		return this.rewiClass;
	}

	public void setRewiClass(Integer rewiClass) {
		this.rewiClass = rewiClass;
	}


	@Column(name="rewi_code")
	public String getRewiCode() {
		return this.rewiCode;
	}

	public void setRewiCode(String rewiCode) {
		this.rewiCode = rewiCode;
	}


	@Column(name="rewi_date")
	public Timestamp getRewiDate() {
		return this.rewiDate;
	}

	public void setRewiDate(Timestamp rewiDate) {
		this.rewiDate = rewiDate;
	}


	@Column(name="rewi_date_create")
	public Timestamp getRewiDateCreate() {
		return this.rewiDateCreate;
	}

	public void setRewiDateCreate(Timestamp rewiDateCreate) {
		this.rewiDateCreate = rewiDateCreate;
	}


	@Column(name="rewi_date_update")
	public Timestamp getRewiDateUpdate() {
		return this.rewiDateUpdate;
	}

	public void setRewiDateUpdate(Timestamp rewiDateUpdate) {
		this.rewiDateUpdate = rewiDateUpdate;
	}


	@Column(name="rewi_register_job")
	public String getRewiRegisterJob() {
		return this.rewiRegisterJob;
	}

	public void setRewiRegisterJob(String rewiRegisterJob) {
		this.rewiRegisterJob = rewiRegisterJob;
	}


	@Column(name="rewi_register_name")
	public String getRewiRegisterName() {
		return this.rewiRegisterName;
	}

	public void setRewiRegisterName(String rewiRegisterName) {
		this.rewiRegisterName = rewiRegisterName;
	}


	@Column(name="rewi_status")
	public Boolean getRewiStatus() {
		return this.rewiStatus;
	}

	public void setRewiStatus(Boolean rewiStatus) {
		this.rewiStatus = rewiStatus;
	}


	@Column(name="rewi_type")
	public Integer getRewiType() {
		return this.rewiType;
	}

	public void setRewiType(Integer rewiType) {
		this.rewiType = rewiType;
	}


	@Column(name="rewi_user_create")
	public String getRewiUserCreate() {
		return this.rewiUserCreate;
	}

	public void setRewiUserCreate(String rewiUserCreate) {
		this.rewiUserCreate = rewiUserCreate;
	}


	@Column(name="rewi_user_update")
	public String getRewiUserUpdate() {
		return this.rewiUserUpdate;
	}

	public void setRewiUserUpdate(String rewiUserUpdate) {
		this.rewiUserUpdate = rewiUserUpdate;
	}


	@Column(name="user_id")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	//bi-directional many-to-one association to ReleaseSpecy
	@OneToMany(mappedBy="releaseWildlife", fetch=FetchType.EAGER)
	@Where(clause = "resp_status='t'")
	@OrderBy(value = "resp_id")
	public List<ReleaseSpecy> getReleaseSpecies() {
		return this.releaseSpecies;
	}

	public void setReleaseSpecies(List<ReleaseSpecy> releaseSpecies) {
		this.releaseSpecies = releaseSpecies;
	}

	public ReleaseSpecy addReleaseSpecy(ReleaseSpecy releaseSpecy) {
		getReleaseSpecies().add(releaseSpecy);
		releaseSpecy.setReleaseWildlife(this);

		return releaseSpecy;
	}

	public ReleaseSpecy removeReleaseSpecy(ReleaseSpecy releaseSpecy) {
		getReleaseSpecies().remove(releaseSpecy);
		releaseSpecy.setReleaseWildlife(null);

		return releaseSpecy;
	}

}