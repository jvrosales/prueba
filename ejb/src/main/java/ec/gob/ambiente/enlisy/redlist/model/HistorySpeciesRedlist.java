package ec.gob.ambiente.enlisy.redlist.model;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Where;

import ec.gob.ambiente.enlisy.speciescatalog.model.SpecieTaxa;

import java.sql.Timestamp;


/**
 * The persistent class for the history_species_redlist database table.
 * 
 */
@Entity
@Table(name="history_species_redlist", schema = "biodiversity")
@NamedQuery(name="HistorySpeciesRedlist.findAll", query="SELECT h FROM HistorySpeciesRedlist h")
@Where(clause = "hisp_status='t'")
public class HistorySpeciesRedlist implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer hispId;
	private Timestamp hispDateCreate;
	private Timestamp hispDateUpdate;
	private Integer hispRedListEc;
	private String hispRedListEcCriteria;
	private Boolean hispStatus=true;
	private String hispUserCreate;
	private String hispUserUpdate;
	private Integer rlspId;
	private SpecieTaxa speciesTaxa;
 
	public HistorySpeciesRedlist() {
	}


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HIST_SPEC_RED_LIST_GENERATOR")
    @SequenceGenerator(name = "HIST_SPEC_RED_LIST_GENERATOR", initialValue = 1, sequenceName = "seq_hisp_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="hisp_id", unique=true, nullable=false)
	public Integer getHispId() {
		return this.hispId;
	}

	public void setHispId(Integer hispId) {
		this.hispId = hispId;
	}


	@Column(name="hisp_date_create", nullable=false)
	public Timestamp getHispDateCreate() {
		return this.hispDateCreate;
	}

	public void setHispDateCreate(Timestamp hispDateCreate) {
		this.hispDateCreate = hispDateCreate;
	}


	@Column(name="hisp_date_update", nullable=false)
	public Timestamp getHispDateUpdate() {
		return this.hispDateUpdate;
	}

	public void setHispDateUpdate(Timestamp hispDateUpdate) {
		this.hispDateUpdate = hispDateUpdate;
	}


	@Column(name="hisp_red_list_ec")
	public Integer getHispRedListEc() {
		return this.hispRedListEc;
	}

	public void setHispRedListEc(Integer hispRedListEc) {
		this.hispRedListEc = hispRedListEc;
	}


	@Column(name="hisp_red_list_ec_criteria", length=255)
	public String getHispRedListEcCriteria() {
		return this.hispRedListEcCriteria;
	}

	public void setHispRedListEcCriteria(String hispRedListEcCriteria) {
		this.hispRedListEcCriteria = hispRedListEcCriteria;
	}


	@Column(name="hisp_status", nullable=false)
	public Boolean getHispStatus() {
		return this.hispStatus;
	}

	public void setHispStatus(Boolean hispStatus) {
		this.hispStatus = hispStatus;
	}


	@Column(name="hisp_user_create", nullable=false, length=255)
	public String getHispUserCreate() {
		return this.hispUserCreate;
	}

	public void setHispUserCreate(String hispUserCreate) {
		this.hispUserCreate = hispUserCreate;
	}


	@Column(name="hisp_user_update", nullable=false, length=255)
	public String getHispUserUpdate() {
		return this.hispUserUpdate;
	}

	public void setHispUserUpdate(String hispUserUpdate) {
		this.hispUserUpdate = hispUserUpdate;
	}


	@Column(name="rlsp_id")
	public Integer getRlspId() {
		return this.rlspId;
	}

	public void setRlspId(Integer rlspId) {
		this.rlspId = rlspId;
	}


	//bi-directional many-to-one association to SpeciesTaxa
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="spta_id")
	public SpecieTaxa getSpeciesTaxa() {
		return this.speciesTaxa;
	}

	public void setSpeciesTaxa(SpecieTaxa speciesTaxa) {
		this.speciesTaxa = speciesTaxa;
	}

}