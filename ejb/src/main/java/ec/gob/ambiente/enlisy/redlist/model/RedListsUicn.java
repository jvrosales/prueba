package ec.gob.ambiente.enlisy.redlist.model;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Where;

import ec.gob.ambiente.enlisy.speciescatalog.model.SpecieTaxa;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the red_lists_uicn database table.
 * 
 */
@Entity
@Table(name="red_lists_uicn", schema="biodiversity")
@NamedQuery(name="RedListsUicn.findAll", query="SELECT r FROM RedListsUicn r")
@Where(clause = "rlui_status='t'")
public class RedListsUicn implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer rluiId;
	private Timestamp rluiDateCreate;
	private Timestamp rluiDateUpdate;
	private String rluiDescription;
	private String rluiInitial;
	private String rluiName;
	private Boolean rluiStatus=true;
	private String rluiUserCreate;
	private String rluiUserUpdate;
	private List<SpecieTaxa> SpecieTaxas;

	public RedListsUicn() {
	}


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RED_LIST_UICN_GENERATOR")
    @SequenceGenerator(name = "RED_LIST_UICN_GENERATOR", initialValue = 1, sequenceName = "seq_rlui_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="rlui_id", unique=true, nullable=false)
	public Integer getRluiId() {
		return this.rluiId;
	}

	public void setRluiId(Integer rluiId) {
		this.rluiId = rluiId;
	}


	@Column(name="rlui_date_create")
	public Timestamp getRluiDateCreate() {
		return this.rluiDateCreate;
	}

	public void setRluiDateCreate(Timestamp rluiDateCreate) {
		this.rluiDateCreate = rluiDateCreate;
	}


	@Column(name="rlui_date_update")
	public Timestamp getRluiDateUpdate() {
		return this.rluiDateUpdate;
	}

	public void setRluiDateUpdate(Timestamp rluiDateUpdate) {
		this.rluiDateUpdate = rluiDateUpdate;
	}


	@Column(name="rlui_description", length=2000)
	public String getRluiDescription() {
		return this.rluiDescription;
	}

	public void setRluiDescription(String rluiDescription) {
		this.rluiDescription = rluiDescription;
	}


	@Column(name="rlui_initial", length=100)
	public String getRluiInitial() {
		return this.rluiInitial;
	}

	public void setRluiInitial(String rluiInitial) {
		this.rluiInitial = rluiInitial;
	}


	@Column(name="rlui_name", length=255)
	public String getRluiName() {
		return this.rluiName;
	}

	public void setRluiName(String rluiName) {
		this.rluiName = rluiName;
	}


	@Column(name="rlui_status")
	public Boolean getRluiStatus() {
		return this.rluiStatus;
	}

	public void setRluiStatus(Boolean rluiStatus) {
		this.rluiStatus = rluiStatus;
	}


	@Column(name="rlui_user_create", length=255)
	public String getRluiUserCreate() {
		return this.rluiUserCreate;
	}

	public void setRluiUserCreate(String rluiUserCreate) {
		this.rluiUserCreate = rluiUserCreate;
	}


	@Column(name="rlui_user_update", length=255)
	public String getRluiUserUpdate() {
		return this.rluiUserUpdate;
	}

	public void setRluiUserUpdate(String rluiUserUpdate) {
		this.rluiUserUpdate = rluiUserUpdate;
	}


	//bi-directional many-to-one association to SpecieTaxa
	@OneToMany(mappedBy="redlistsUicn")
	public List<SpecieTaxa> getSpecieTaxas() {
		return this.SpecieTaxas;
	}

	public void setSpecieTaxas(List<SpecieTaxa> SpecieTaxas) {
		this.SpecieTaxas = SpecieTaxas;
	}

	public SpecieTaxa addSpecieTaxa(SpecieTaxa SpecieTaxa) {
		getSpecieTaxas().add(SpecieTaxa);
		SpecieTaxa.setRedlistsUicn(this);

		return SpecieTaxa;
	}

	public SpecieTaxa removeSpecieTaxa(SpecieTaxa SpecieTaxa) {
		getSpecieTaxas().remove(SpecieTaxa);
		SpecieTaxa.setRedlistsUicn(null);

		return SpecieTaxa;
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

}