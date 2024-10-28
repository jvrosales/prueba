package ec.gob.ambiente.enlisy.redlist.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the redlist_ecosystems database table.
 * 
 */
@Entity
@Table(name="redlist_ecosystems", schema = "biodiversity")
@NamedQuery(name="RedlistEcosystem.findAll", query="SELECT r FROM RedlistEcosystem r")
public class RedlistEcosystem implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer rlesId;
	private Integer rlesCategory;
	private String rlesCriteria;
	private Timestamp rlesDateCreate;
	private Timestamp rlesDateUpdate;
	private Boolean rlesDefinitive;
	private String rlesJustification;
	private String rlesName;
	private Boolean rlesStatus=true;
	private String rlesUserCreate;
	private String rlesUserUpdate;
	private CallRedList callRedList;
	private Ecosystem ecosystem;
 
	public RedlistEcosystem() {
	}


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RED_LIST_ECOSY_GENERATOR")
    @SequenceGenerator(name = "RED_LIST_ECOSY_GENERATOR", initialValue = 1, sequenceName = "seq_rles_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="rles_id", unique=true, nullable=false)
	public Integer getRlesId() {
		return this.rlesId;
	}

	public void setRlesId(Integer rlesId) {
		this.rlesId = rlesId;
	}


	@Column(name="rles_category")
	public Integer getRlesCategory() {
		return this.rlesCategory;
	}

	public void setRlesCategory(Integer rlesCategory) {
		this.rlesCategory = rlesCategory;
	}


	@Column(name="rles_criteria", length=255)
	public String getRlesCriteria() {
		return this.rlesCriteria;
	}

	public void setRlesCriteria(String rlesCriteria) {
		this.rlesCriteria = rlesCriteria;
	}


	@Column(name="rles_date_create", nullable=false)
	public Timestamp getRlesDateCreate() {
		return this.rlesDateCreate;
	}

	public void setRlesDateCreate(Timestamp rlesDateCreate) {
		this.rlesDateCreate = rlesDateCreate;
	}


	@Column(name="rles_date_update", nullable=false)
	public Timestamp getRlesDateUpdate() {
		return this.rlesDateUpdate;
	}

	public void setRlesDateUpdate(Timestamp rlesDateUpdate) {
		this.rlesDateUpdate = rlesDateUpdate;
	}


	@Column(name="rles_definitive")
	public Boolean getRlesDefinitive() {
		return this.rlesDefinitive;
	}

	public void setRlesDefinitive(Boolean rlesDefinitive) {
		this.rlesDefinitive = rlesDefinitive;
	}


	@Column(name="rles_justification", length=512)
	public String getRlesJustification() {
		return this.rlesJustification;
	}

	public void setRlesJustification(String rlesJustification) {
		this.rlesJustification = rlesJustification;
	}


	@Column(name="rles_name", length=255)
	public String getRlesName() {
		return this.rlesName;
	}

	public void setRlesName(String rlesName) {
		this.rlesName = rlesName;
	}


	@Column(name="rles_status", nullable=false)
	public Boolean getRlesStatus() {
		return this.rlesStatus;
	}

	public void setRlesStatus(Boolean rlesStatus) {
		this.rlesStatus = rlesStatus;
	}


	@Column(name="rles_user_create", nullable=false, length=255)
	public String getRlesUserCreate() {
		return this.rlesUserCreate;
	}

	public void setRlesUserCreate(String rlesUserCreate) {
		this.rlesUserCreate = rlesUserCreate;
	}


	@Column(name="rles_user_update", nullable=false, length=255)
	public String getRlesUserUpdate() {
		return this.rlesUserUpdate;
	}

	public void setRlesUserUpdate(String rlesUserUpdate) {
		this.rlesUserUpdate = rlesUserUpdate;
	}


	//bi-directional many-to-one association to CallRedList
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="care_id")
	public CallRedList getCallRedList() {
		return this.callRedList;
	}

	public void setCallRedList(CallRedList callRedList) {
		this.callRedList = callRedList;
	}


	//bi-directional many-to-one association to Ecosystem
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ecos_id", nullable=false)
	public Ecosystem getEcosystem() {
		return this.ecosystem;
	}

	public void setEcosystem(Ecosystem ecosystem) {
		this.ecosystem = ecosystem;
	}

}