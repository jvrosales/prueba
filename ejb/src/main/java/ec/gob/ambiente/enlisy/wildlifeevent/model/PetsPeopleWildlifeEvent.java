package ec.gob.ambiente.enlisy.wildlifeevent.model;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Where;

import ec.gob.ambiente.enlisy.speciescatalog.model.HigherClassification;
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecieTaxa;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the pets_people_wildlife_events database table.
 * 
 */
@Entity
@Table(name="pets_people_wildlife_events", schema="biodiversity")
@NamedQuery(name="PetsPeopleWildlifeEvent.findAll", query="SELECT p FROM PetsPeopleWildlifeEvent p")
@Where(clause = "pepe_status='t'")
public class PetsPeopleWildlifeEvent implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer pepeId;
	private Timestamp pepeDateCreate;
	private Timestamp pepeDateUpdate;
	private Integer pepeDeadAnimal;
	private Integer pepeInjuredAnimal;
	private Boolean pepeStatus = true;
	private String pepeUserCreate;
	private String pepeUserUpdate;
	private PeopleWildlifeEvent peopleWildlifeEvent;
	private SpecieTaxa speciesTaxa;
	
	private HigherClassification higherClassification;
	private List<SpecieTaxa> lstSpecieTaxa = new ArrayList<SpecieTaxa>();

	private Integer pepeMaleAnimal;
	private Integer pepeFemaleAnimal;
	private Integer pepeYouthAnimal;
	private Integer pepeAdultAnimal;
	private BigDecimal pepeEconomicLossValue;

	private Integer pepeSexNd;

	private Integer pepeConditionNd;

	public PetsPeopleWildlifeEvent() {
	}


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PETS_PEOPLE_WILDLIFE_GENERATOR")
	@SequenceGenerator(name = "PETS_PEOPLE_WILDLIFE_GENERATOR", initialValue = 1, sequenceName = "seq_pepe_id", schema = "biodiversity", allocationSize = 1)
	@Column(name="pepe_id")
	public Integer getPepeId() {
		return this.pepeId;
	}

	public void setPepeId(Integer pepeId) {
		this.pepeId = pepeId;
	}


	@Column(name="pepe_date_create")
	public Timestamp getPepeDateCreate() {
		return this.pepeDateCreate;
	}

	public void setPepeDateCreate(Timestamp pepeDateCreate) {
		this.pepeDateCreate = pepeDateCreate;
	}


	@Column(name="pepe_date_update")
	public Timestamp getPepeDateUpdate() {
		return this.pepeDateUpdate;
	}

	public void setPepeDateUpdate(Timestamp pepeDateUpdate) {
		this.pepeDateUpdate = pepeDateUpdate;
	}


	@Column(name="pepe_dead_animal")
	public Integer getPepeDeadAnimal() {
		return this.pepeDeadAnimal;
	}

	public void setPepeDeadAnimal(Integer pepeDeadAnimal) {
		this.pepeDeadAnimal = pepeDeadAnimal;
	}


	@Column(name="pepe_injured_animal")
	public Integer getPepeInjuredAnimal() {
		return this.pepeInjuredAnimal;
	}

	public void setPepeInjuredAnimal(Integer pepeInjuredAnimal) {
		this.pepeInjuredAnimal = pepeInjuredAnimal;
	}


	@Column(name="pepe_status")
	public Boolean getPepeStatus() {
		return this.pepeStatus;
	}

	public void setPepeStatus(Boolean pepeStatus) {
		this.pepeStatus = pepeStatus;
	}


	@Column(name="pepe_user_create")
	public String getPepeUserCreate() {
		return this.pepeUserCreate;
	}

	public void setPepeUserCreate(String pepeUserCreate) {
		this.pepeUserCreate = pepeUserCreate;
	}


	@Column(name="pepe_user_update")
	public String getPepeUserUpdate() {
		return this.pepeUserUpdate;
	}

	public void setPepeUserUpdate(String pepeUserUpdate) {
		this.pepeUserUpdate = pepeUserUpdate;
	}


	//bi-directional many-to-one association to PeopleWildlifeEvent
	@ManyToOne
	@JoinColumn(name="pewi_id")
	public PeopleWildlifeEvent getPeopleWildlifeEvent() {
		return this.peopleWildlifeEvent;
	}

	public void setPeopleWildlifeEvent(PeopleWildlifeEvent peopleWildlifeEvent) {
		this.peopleWildlifeEvent = peopleWildlifeEvent;
	}


	//bi-directional many-to-one association to SpeciesTaxa
	@ManyToOne
	@JoinColumn(name="spta_id")
	public SpecieTaxa getSpeciesTaxa() {
		return this.speciesTaxa;
	}

	public void setSpeciesTaxa(SpecieTaxa speciesTaxa) {
		this.speciesTaxa = speciesTaxa;
	}

	@Transient
	public HigherClassification getHigherClassification() {
		return higherClassification;
	}

	public void setHigherClassification(HigherClassification higherClassification) {
		this.higherClassification = higherClassification;
	}

	@Transient
	public List<SpecieTaxa> getLstSpecieTaxa() {
		return lstSpecieTaxa;
	}


	public void setLstSpecieTaxa(List<SpecieTaxa> lstSpecieTaxa) {
		this.lstSpecieTaxa = lstSpecieTaxa;
	}

	@Column(name="pepe_male_animal")
	public Integer getPepeMaleAnimal() {
		return pepeMaleAnimal;
	}

	public void setPepeMaleAnimal(Integer pepeMaleAnimal) {
		this.pepeMaleAnimal = pepeMaleAnimal;
	}

	@Column(name="pepe_female_animal")
	public Integer getPepeFemaleAnimal() {
		return pepeFemaleAnimal;
	}

	public void setPepeFemaleAnimal(Integer pepeFemaleAnimal) {
		this.pepeFemaleAnimal = pepeFemaleAnimal;
	}

	@Column(name="pepe_youth_animal")
	public Integer getPepeYouthAnimal() {
		return pepeYouthAnimal;
	}

	public void setPepeYouthAnimal(Integer pepeYouthAnimal) {
		this.pepeYouthAnimal = pepeYouthAnimal;
	}

	@Column(name="pepe_adult_animal")
	public Integer getPepeAdultAnimal() {
		return pepeAdultAnimal;
	}

	public void setPepeAdultAnimal(Integer pepeAdultAnimal) {
		this.pepeAdultAnimal = pepeAdultAnimal;
	}

	@Column(name="pepe_economic_loss_value")
	public BigDecimal getPepeEconomicLossValue() {
		return pepeEconomicLossValue;
	}

	public void setPepeEconomicLossValue(BigDecimal pepeEconomicLossValue) {
		this.pepeEconomicLossValue = pepeEconomicLossValue;
	}

	@Column(name="pepe_sex_nd")
	public Integer getPepeSexNd() {
		return pepeSexNd;
	}

	public void setPepeSexNd(Integer pepeSexNd) {
		this.pepeSexNd = pepeSexNd;
	}

	@Column(name="pepe_condition_nd")
	public Integer getPepeConditionNd() {
		return pepeConditionNd;
	}

	public void setPepeConditionNd(Integer pepeConditionNd) {
		this.pepeConditionNd = pepeConditionNd;
	}
}