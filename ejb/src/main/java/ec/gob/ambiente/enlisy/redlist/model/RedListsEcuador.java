package ec.gob.ambiente.enlisy.redlist.model;

import javax.persistence.*;

import org.hibernate.annotations.Where;

import ec.gob.ambiente.enlisy.dao.ConvertibleEntity;
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecieTaxa;

import java.sql.Timestamp;
import java.util.List;

/**
 * The persistent class for the red_lists_ecuador database table.
 * 
 */
@Entity
@Table(name = "red_lists_ecuador", schema = "biodiversity")
@NamedQuery(name = "RedListsEcuador.findAll", query = "SELECT r FROM RedListsEcuador r")
@Where(clause = "rlec_status='t'")
public class RedListsEcuador extends ConvertibleEntity {
	private static final long serialVersionUID = 1L;
	private Integer rlecId;
	private Timestamp rlecDateCreate;
	private Timestamp rlecDateUpdate;
	private String rlecDescription;
	private String rlecInitial;
	private String rlecName;
	private Boolean rlecStatus = true;
	private String rlecUserCreate;
	private String rlecUserUpdate;
	private Boolean rlecApliesSpecies = true;
	private Boolean rlecApliesEcosystems = true;
	private List<SpecieTaxa> speciesTaxas;

	private boolean selected = false;

	public RedListsEcuador() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RED_LIST_ECUADOR_GENERATOR")
    @SequenceGenerator(name = "RED_LIST_ECUADOR_GENERATOR", initialValue = 1, sequenceName = "seq_rlec_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name = "rlec_id", unique = true, nullable = false)
	public Integer getRlecId() {
		return this.rlecId;
	}

	public void setRlecId(Integer rlecId) {
		this.rlecId = rlecId;
	}

	@Column(name = "rlec_date_create")
	public Timestamp getRlecDateCreate() {
		return this.rlecDateCreate;
	}

	public void setRlecDateCreate(Timestamp rlecDateCreate) {
		this.rlecDateCreate = rlecDateCreate;
	}

	@Column(name = "rlec_date_update")
	public Timestamp getRlecDateUpdate() {
		return this.rlecDateUpdate;
	}

	public void setRlecDateUpdate(Timestamp rlecDateUpdate) {
		this.rlecDateUpdate = rlecDateUpdate;
	}

	@Column(name = "rlec_description", length = 2000)
	public String getRlecDescription() {
		return this.rlecDescription;
	}

	public void setRlecDescription(String rlecDescription) {
		this.rlecDescription = rlecDescription;
	}

	@Column(name = "rlec_initial", length = 100)
	public String getRlecInitial() {
		return this.rlecInitial;
	}

	public void setRlecInitial(String rlecInitial) {
		this.rlecInitial = rlecInitial;
	}

	@Column(name = "rlec_name", length = 255)
	public String getRlecName() {
		return this.rlecName;
	}

	public void setRlecName(String rlecName) {
		this.rlecName = rlecName;
	}

	@Column(name = "rlec_status")
	public Boolean getRlecStatus() {
		return this.rlecStatus;
	}

	public void setRlecStatus(Boolean rlecStatus) {
		this.rlecStatus = rlecStatus;
	}

	@Column(name = "rlec_aplies_species")
	public Boolean getRlecApliesSpecies() {
		return this.rlecApliesSpecies;
	}

	public void setRlecApliesSpecies(Boolean rlecApliesSpecies) {
		this.rlecApliesSpecies = rlecApliesSpecies;
	}

	@Column(name = "rlec_aplies_ecosystems")
	public Boolean getRlecApliesEcosystems() {
		return this.rlecApliesEcosystems;
	}

	public void setRlecApliesEcosystems(Boolean rlecApliesEcosystems) {
		this.rlecApliesEcosystems = rlecApliesEcosystems;
	}

	@Column(name = "rlec_user_create", length = 255)
	public String getRlecUserCreate() {
		return this.rlecUserCreate;
	}

	public void setRlecUserCreate(String rlecUserCreate) {
		this.rlecUserCreate = rlecUserCreate;
	}

	@Column(name = "rlec_user_update", length = 255)
	public String getRlecUserUpdate() {
		return this.rlecUserUpdate;
	}

	public void setRlecUserUpdate(String rlecUserUpdate) {
		this.rlecUserUpdate = rlecUserUpdate;
	}

	// bi-directional many-to-one association to SpeciesTaxa
	@OneToMany(mappedBy = "redlistsEcuador")
	public List<SpecieTaxa> getSpeciesTaxas() {
		return this.speciesTaxas;
	}

	public void setSpeciesTaxas(List<SpecieTaxa> speciesTaxas) {
		this.speciesTaxas = speciesTaxas;
	}

	public SpecieTaxa addSpeciesTaxa(SpecieTaxa speciesTaxa) {
		getSpeciesTaxas().add(speciesTaxa);
		speciesTaxa.setRedlistsEcuador(this);

		return speciesTaxa;
	}

	public SpecieTaxa removeSpeciesTaxa(SpecieTaxa speciesTaxa) {
		getSpeciesTaxas().remove(speciesTaxa);
		speciesTaxa.setRedlistsEcuador(null);

		return speciesTaxa;
	}

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
		return getRlecId();
	}

	@Transient
	@Override
	public String getDescription() {
		return getRlecName();
	}
}