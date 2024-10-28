package ec.gob.ambiente.enlisy.redlist.model;

import javax.persistence.*;

import ec.gob.ambiente.enlisy.dao.ConvertibleEntity;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ecosystems database table.
 * 
 */
@Entity
@Table(name="ecosystems", schema = "biodiversity")
@NamedQuery(name="Ecosystem.findAll", query="SELECT e FROM Ecosystem e")
public class Ecosystem extends ConvertibleEntity {
	private static final long serialVersionUID = 1L;
	private Integer ecosId;
	private String ecosCode;
	private Date ecosDateCreate;
	private Date ecosDateUpdate;
	private String ecosName;
	private String ecosRedListEcCriteria;
	private Boolean ecosStatus;
	private String ecosUserCreate;
	private String ecosUserUpdate;
	private RedListsEcuador redListsEcuador;
	private List<HistoryEcosystemsRedlist> historyEcosystemsRedlists;
	private List<RedlistEcosystem> redlistEcosystems;

	public Ecosystem() {
	}


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ECOSYSTEM_GENERATOR")
    @SequenceGenerator(name = "ECOSYSTEM_GENERATOR", initialValue = 1, sequenceName = "seq_ecos_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="ecos_id", unique=true, nullable=false)
	public Integer getEcosId() {
		return this.ecosId;
	}

	public void setEcosId(Integer ecosId) {
		this.ecosId = ecosId;
	}


	@Column(name="ecos_code", length=8)
	public String getEcosCode() {
		return this.ecosCode;
	}

	public void setEcosCode(String ecosCode) {
		this.ecosCode = ecosCode;
	}


	@Column(name="ecos_date_create", nullable=false)
	public Date getEcosDateCreate() {
		return this.ecosDateCreate;
	}

	public void setEcosDateCreate(Date ecosDateCreate) {
		this.ecosDateCreate = ecosDateCreate;
	}


	@Column(name="ecos_date_update", nullable=false)
	public Date getEcosDateUpdate() {
		return this.ecosDateUpdate;
	}

	public void setEcosDateUpdate(Date ecosDateUpdate) {
		this.ecosDateUpdate = ecosDateUpdate;
	}


	@Column(name="ecos_name", length=255)
	public String getEcosName() {
		return this.ecosName;
	}

	public void setEcosName(String ecosName) {
		this.ecosName = ecosName;
	}


	@Column(name="ecos_red_list_ec_criteria", length=255)
	public String getEcosRedListEcCriteria() {
		return this.ecosRedListEcCriteria;
	}

	public void setEcosRedListEcCriteria(String ecosRedListEcCriteria) {
		this.ecosRedListEcCriteria = ecosRedListEcCriteria;
	}


	@Column(name="ecos_status", nullable=false)
	public Boolean getEcosStatus() {
		return this.ecosStatus;
	}

	public void setEcosStatus(Boolean ecosStatus) {
		this.ecosStatus = ecosStatus;
	}


	@Column(name="ecos_user_create", nullable=false, length=255)
	public String getEcosUserCreate() {
		return this.ecosUserCreate;
	}

	public void setEcosUserCreate(String ecosUserCreate) {
		this.ecosUserCreate = ecosUserCreate;
	}


	@Column(name="ecos_user_update", nullable=false, length=255)
	public String getEcosUserUpdate() {
		return this.ecosUserUpdate;
	}

	public void setEcosUserUpdate(String ecosUserUpdate) {
		this.ecosUserUpdate = ecosUserUpdate;
	}


	//bi-directional many-to-one association to RedListsEcuador
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ecos_red_list_ec")
	public RedListsEcuador getRedListsEcuador() {
		return this.redListsEcuador;
	}

	public void setRedListsEcuador(RedListsEcuador redListsEcuador) {
		this.redListsEcuador = redListsEcuador;
	}


	//bi-directional many-to-one association to HistoryEcosystemsRedlist
	@OneToMany(mappedBy="ecosystem")
	public List<HistoryEcosystemsRedlist> getHistoryEcosystemsRedlists() {
		return this.historyEcosystemsRedlists;
	}

	public void setHistoryEcosystemsRedlists(List<HistoryEcosystemsRedlist> historyEcosystemsRedlists) {
		this.historyEcosystemsRedlists = historyEcosystemsRedlists;
	}

	public HistoryEcosystemsRedlist addHistoryEcosystemsRedlist(HistoryEcosystemsRedlist historyEcosystemsRedlist) {
		getHistoryEcosystemsRedlists().add(historyEcosystemsRedlist);
		historyEcosystemsRedlist.setEcosystem(this);

		return historyEcosystemsRedlist;
	}

	public HistoryEcosystemsRedlist removeHistoryEcosystemsRedlist(HistoryEcosystemsRedlist historyEcosystemsRedlist) {
		getHistoryEcosystemsRedlists().remove(historyEcosystemsRedlist);
		historyEcosystemsRedlist.setEcosystem(null);

		return historyEcosystemsRedlist;
	}


	//bi-directional many-to-one association to RedlistEcosystem
	@OneToMany(mappedBy="ecosystem")
	public List<RedlistEcosystem> getRedlistEcosystems() {
		return this.redlistEcosystems;
	}

	public void setRedlistEcosystems(List<RedlistEcosystem> redlistEcosystems) {
		this.redlistEcosystems = redlistEcosystems;
	}

	public RedlistEcosystem addRedlistEcosystem(RedlistEcosystem redlistEcosystem) {
		getRedlistEcosystems().add(redlistEcosystem);
		redlistEcosystem.setEcosystem(this);

		return redlistEcosystem;
	}

	public RedlistEcosystem removeRedlistEcosystem(RedlistEcosystem redlistEcosystem) {
		getRedlistEcosystems().remove(redlistEcosystem);
		redlistEcosystem.setEcosystem(null);

		return redlistEcosystem;
	}

	@Transient
	private boolean selected = false;

	@Transient
	public boolean isSelected() {
		return selected;
	}

	@Transient
	public void setSelected(boolean selected) {
		this.selected = selected;
	}


	@Transient
	@Override
	public long getId() {
		return getEcosId();
	}

	@Transient
	@Override
	public String getDescription() {
		return getEcosName();
	}
}