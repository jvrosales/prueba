package ec.gob.ambiente.enlisy.redlist.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the history_ecosystems_redlist database table.
 * 
 */
@Entity
@Table(name="history_ecosystems_redlist", schema = "biodiversity")
@NamedQuery(name="HistoryEcosystemsRedlist.findAll", query="SELECT h FROM HistoryEcosystemsRedlist h")
public class HistoryEcosystemsRedlist implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer hierId;
	private Timestamp hierDateCreate;
	private Timestamp hierDateUpdate;
	private Integer hierRedListEc;
	private String hierRedListEcCriteria;
	private Boolean hierStatus;
	private String hierUserCreate;
	private String hierUserUpdate;
	private Integer rlecId;
	private Ecosystem ecosystem;
 
	public HistoryEcosystemsRedlist() {
	}


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HIST_ECO_RED_LIST_GENERATOR")
    @SequenceGenerator(name = "HIST_ECO_RED_LIST_GENERATOR", initialValue = 1, sequenceName = "seq_hier_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="hier_id", unique=true, nullable=false)
	public Integer getHierId() {
		return this.hierId;
	}

	public void setHierId(Integer hierId) {
		this.hierId = hierId;
	}


	@Column(name="hier_date_create", nullable=false)
	public Timestamp getHierDateCreate() {
		return this.hierDateCreate;
	}

	public void setHierDateCreate(Timestamp hierDateCreate) {
		this.hierDateCreate = hierDateCreate;
	}


	@Column(name="hier_date_update", nullable=false)
	public Timestamp getHierDateUpdate() {
		return this.hierDateUpdate;
	}

	public void setHierDateUpdate(Timestamp hierDateUpdate) {
		this.hierDateUpdate = hierDateUpdate;
	}


	@Column(name="hier_red_list_ec")
	public Integer getHierRedListEc() {
		return this.hierRedListEc;
	}

	public void setHierRedListEc(Integer hierRedListEc) {
		this.hierRedListEc = hierRedListEc;
	}


	@Column(name="hier_red_list_ec_criteria", length=255)
	public String getHierRedListEcCriteria() {
		return this.hierRedListEcCriteria;
	}

	public void setHierRedListEcCriteria(String hierRedListEcCriteria) {
		this.hierRedListEcCriteria = hierRedListEcCriteria;
	}


	@Column(name="hier_status", nullable=false)
	public Boolean getHierStatus() {
		return this.hierStatus;
	}

	public void setHierStatus(Boolean hierStatus) {
		this.hierStatus = hierStatus;
	}


	@Column(name="hier_user_create", nullable=false, length=255)
	public String getHierUserCreate() {
		return this.hierUserCreate;
	}

	public void setHierUserCreate(String hierUserCreate) {
		this.hierUserCreate = hierUserCreate;
	}


	@Column(name="hier_user_update", nullable=false, length=255)
	public String getHierUserUpdate() {
		return this.hierUserUpdate;
	}

	public void setHierUserUpdate(String hierUserUpdate) {
		this.hierUserUpdate = hierUserUpdate;
	}


	@Column(name="rlec_id", nullable=false)
	public Integer getRlecId() {
		return this.rlecId;
	}

	public void setRlecId(Integer rlecId) {
		this.rlecId = rlecId;
	}


	//bi-directional many-to-one association to Ecosystem
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ecos_id")
	public Ecosystem getEcosystem() {
		return this.ecosystem;
	}

	public void setEcosystem(Ecosystem ecosystem) {
		this.ecosystem = ecosystem;
	}

}