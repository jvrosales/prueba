package ec.gob.ambiente.enlisy.redlist.model;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Where;

import ec.gob.ambiente.enlisy.speciescatalog.model.SpecieTaxa;

import java.sql.Timestamp;

/**
 * The persistent class for the redlist_species database table.
 * 
 */
@Entity
@Table(name = "redlist_species", schema = "biodiversity")
@NamedQuery(name = "RedlistSpecy.findAll", query = "SELECT r FROM RedlistSpecy r")
@Where(clause = "rlsp_status='t'")
public class RedlistSpecy implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer rlspId;
	private Integer rlspCategory;
	private String rlspCriteria;
	private Timestamp rlspDateCreate;
	private Timestamp rlspDateUpdate;
	private Boolean rlspDefinitive=false;
	private String rlspJustification;
	private String rlspScientificName;
	private Boolean rlspStatus=true;
	private String rlspUserCreate;
	private String rlspUserUpdate;
	private CallRedList callRedList;
	private SpecieTaxa speciesTaxa;
 
	public RedlistSpecy() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RED_LIST_ESPECIE_GENERATOR")
    @SequenceGenerator(name = "RED_LIST_ESPECIE_GENERATOR", initialValue = 1, sequenceName = "seq_rlsp_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name = "rlsp_id", unique = true, nullable = false)
	public Integer getRlspId() {
		return this.rlspId;
	}

	public void setRlspId(Integer rlspId) {
		this.rlspId = rlspId;
	}

	@Column(name = "rlsp_category")
	public Integer getRlspCategory() {
		return this.rlspCategory;
	}

	public void setRlspCategory(Integer rlspCategory) {
		this.rlspCategory = rlspCategory;
	}

	@Column(name = "rlsp_criteria", length = 255)
	public String getRlspCriteria() {
		return this.rlspCriteria;
	}

	public void setRlspCriteria(String rlspCriteria) {
		this.rlspCriteria = rlspCriteria;
	}

	@Column(name = "rlsp_date_create", nullable = false)
	public Timestamp getRlspDateCreate() {
		return this.rlspDateCreate;
	}

	public void setRlspDateCreate(Timestamp rlspDateCreate) {
		this.rlspDateCreate = rlspDateCreate;
	}

	@Column(name = "rlsp_date_update", nullable = false)
	public Timestamp getRlspDateUpdate() {
		return this.rlspDateUpdate;
	}

	public void setRlspDateUpdate(Timestamp rlspDateUpdate) {
		this.rlspDateUpdate = rlspDateUpdate;
	}

	@Column(name = "rlsp_definitive")
	public Boolean getRlspDefinitive() {
		return this.rlspDefinitive;
	}

	public void setRlspDefinitive(Boolean rlspDefinitive) {
		this.rlspDefinitive = rlspDefinitive;
	}

	@Column(name = "rlsp_justification", length = 512)
	public String getRlspJustification() {
		return this.rlspJustification;
	}

	public void setRlspJustification(String rlspJustification) {
		this.rlspJustification = rlspJustification;
	}

	@Column(name = "rlsp_scientific_name", length = 255)
	public String getRlspScientificName() {
		return this.rlspScientificName;
	}

	public void setRlspScientificName(String rlspScientificName) {
		this.rlspScientificName = rlspScientificName;
	}

	@Column(name = "rlsp_status", nullable = false)
	public Boolean getRlspStatus() {
		return this.rlspStatus;
	}

	public void setRlspStatus(Boolean rlspStatus) {
		this.rlspStatus = rlspStatus;
	}

	@Column(name = "rlsp_user_create", nullable = false, length = 255)
	public String getRlspUserCreate() {
		return this.rlspUserCreate;
	}

	public void setRlspUserCreate(String rlspUserCreate) {
		this.rlspUserCreate = rlspUserCreate;
	}

	@Column(name = "rlsp_user_update", nullable = false, length = 255)
	public String getRlspUserUpdate() {
		return this.rlspUserUpdate;
	}

	public void setRlspUserUpdate(String rlspUserUpdate) {
		this.rlspUserUpdate = rlspUserUpdate;
	}

	// bi-directional many-to-one association to CallRedList
	@ManyToOne
	@JoinColumn(name = "care_id")
	public CallRedList getCallRedList() {
		return this.callRedList;
	}

	public void setCallRedList(CallRedList callRedList) {
		this.callRedList = callRedList;
	}

	// bi-directional many-to-one association to SpeciesTaxa
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "spta_id")
	public SpecieTaxa getSpeciesTaxa() {
		return this.speciesTaxa;
	}

	public void setSpeciesTaxa(SpecieTaxa speciesTaxa) {
		this.speciesTaxa = speciesTaxa;
	}

}