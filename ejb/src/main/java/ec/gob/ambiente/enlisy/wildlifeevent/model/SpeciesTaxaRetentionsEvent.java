package ec.gob.ambiente.enlisy.wildlifeevent.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import ec.gob.ambiente.enlisy.actionplan.model.ActionPlan;
import ec.gob.ambiente.enlisy.speciescatalog.model.HigherClassification;
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecieTaxa;

/**
 * The persistent class for the species_taxa_retentions_events database table.
 * 
 */
@Entity
@Table(name = "species_taxa_retentions_events", schema = "biodiversity")
@NamedQuery(name = "SpeciesTaxaRetentionsEvent.findAll", query = "SELECT s FROM SpeciesTaxaRetentionsEvent s")
@Where(clause = "spre_status='t'")
public class SpeciesTaxaRetentionsEvent implements Serializable {

	public static final String COB_INSPECCION_EVENTO_FOTO_EA = "COB_INSPECCION_EVENTO_FOTO_EA";
	public static final String COB_INSPECCION_EVENTO_INFORME_EA = "COB_INSPECCION_EVENTO_INFORME_EA";

	private static final long serialVersionUID = 1L;
	private Integer spreId;
	private Timestamp spreDateCreate;
	private Timestamp spreDateUpdate;
	private String spreImg;
	private Integer spreQuantity;
	private String spreScientificName;
	private Integer spreState;
	private Boolean spreStatus = true;
	private Integer spreType;
	private String spreUserCreate;
	private String spreUserUpdate;
	private String spreVernacularName;
	private Integer sptaId;
	private RunOverEvent runOverEvent;
	private ActionPlan actionPlan;
	private RetentionEvent retentionEvent;
	private RescueEvent rescueEvent;

	private Integer sexoId;
	private Integer stageLifeId;
	private Integer physicalStateId;
	private Double weight;
	
	private Integer reteWildLifeId;
	private Integer reteElementConstId;
	private Integer reteBiologicalSampleId;

	private HigherClassification higherClassification;
	private List<SpecieTaxa> lstSpecieTaxa = new ArrayList<SpecieTaxa>();
	
	private String spreKingdom;
	private String spreClass;
	private Integer spreQuantityMale;
	private Integer spreQuantityFemal;
	private Integer spreQuantityIndeterminate;
	private Double spreMonetaryCost;
	private Double spreVolume;
	private Boolean spreIsWeight;
	private String spreUnit;

	private BiodiversityGeneralCatalog statusSpecie;

	public SpeciesTaxaRetentionsEvent() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SPECIE_TAXA_RETENTION_EVENT_GENERATOR")
    @SequenceGenerator(name = "SPECIE_TAXA_RETENTION_EVENT_GENERATOR", initialValue = 1, sequenceName = "seq_spre_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name = "spre_id")
	public Integer getSpreId() {
		return this.spreId;
	}

	public void setSpreId(Integer spreId) {
		this.spreId = spreId;
	}

	@Column(name = "spre_date_create")
	public Timestamp getSpreDateCreate() {
		return this.spreDateCreate;
	}

	public void setSpreDateCreate(Timestamp spreDateCreate) {
		this.spreDateCreate = spreDateCreate;
	}

	@Column(name = "spre_date_update")
	public Timestamp getSpreDateUpdate() {
		return this.spreDateUpdate;
	}

	public void setSpreDateUpdate(Timestamp spreDateUpdate) {
		this.spreDateUpdate = spreDateUpdate;
	}

	@Column(name = "spre_img")
	public String getSpreImg() {
		return this.spreImg;
	}

	public void setSpreImg(String spreImg) {
		this.spreImg = spreImg;
	}

	@Column(name = "spre_quantity")
	public Integer getSpreQuantity() {
		return this.spreQuantity;
	}

	public void setSpreQuantity(Integer spreQuantity) {
		this.spreQuantity = spreQuantity;
	}

	@Column(name = "spre_scientific_name")
	public String getSpreScientificName() {
		return this.spreScientificName;
	}

	public void setSpreScientificName(String spreScientificName) {
		this.spreScientificName = spreScientificName;
	}

	@Column(name = "spre_state")
	public Integer getSpreState() {
		return this.spreState;
	}

	public void setSpreState(Integer spreState) {
		this.spreState = spreState;
	}

	@Column(name = "spre_status")
	public Boolean getSpreStatus() {
		return this.spreStatus;
	}

	public void setSpreStatus(Boolean spreStatus) {
		this.spreStatus = spreStatus;
	}

	@Column(name = "spre_type")
	public Integer getSpreType() {
		return this.spreType;
	}

	public void setSpreType(Integer spreType) {
		this.spreType = spreType;
	}

	@Column(name = "spre_user_create")
	public String getSpreUserCreate() {
		return this.spreUserCreate;
	}

	public void setSpreUserCreate(String spreUserCreate) {
		this.spreUserCreate = spreUserCreate;
	}

	@Column(name = "spre_user_update")
	public String getSpreUserUpdate() {
		return this.spreUserUpdate;
	}

	public void setSpreUserUpdate(String spreUserUpdate) {
		this.spreUserUpdate = spreUserUpdate;
	}

	@Column(name = "spre_vernacular_name")
	public String getSpreVernacularName() {
		return this.spreVernacularName;
	}

	public void setSpreVernacularName(String spreVernacularName) {
		this.spreVernacularName = spreVernacularName;
	}

	@Column(name = "spta_id")
	public Integer getSptaId() {
		return this.sptaId;
	}

	public void setSptaId(Integer sptaId) {
		this.sptaId = sptaId;
	}

	@Column(name = "sexo_id")
	public Integer getSexoId() {
		return this.sexoId;
	}

	public void setSexoId(Integer sexoId) {
		this.sexoId = sexoId;
	}

	@Column(name = "stage_life_id")
	public Integer getStageLifeId() {
		return this.stageLifeId;
	}

	public void setStageLifeId(Integer stageLifeId) {
		this.stageLifeId = stageLifeId;
	}

	@Column(name = "physical_state_id")
	public Integer getPhysicalStateId() {
		return this.physicalStateId;
	}

	public void setPhysicalStateId(Integer physicalStateId) {
		this.physicalStateId = physicalStateId;
	}

	@Column(name = "weight")
	public Double getWeight() {
		return this.weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}
	
	/**
	 * Identificador Vida Silvestre
	 */
	@Column(name="rete_wild_life_id")
	public Integer getReteWildLifeId() {
		return this.reteWildLifeId;
	}

	public void setReteWildLifeId(Integer reteWildLifeId) {
		this.reteWildLifeId = reteWildLifeId;
	}
	
	/**
	 * Identificador Elemento Constitutivo
	 */
	@Column(name="rete_element_const_id")
	public Integer getReteElementConstId() {
		return this.reteElementConstId;
	}

	public void setReteElementConstId(Integer reteElementConstId) {
		this.reteElementConstId = reteElementConstId;
	}
	
	/**
	 * Identificador Muestra biologica
	 */
	@Column(name="rete_biological_sample_id")
	public Integer getReteBiologicalSampleId() {
		return this.reteBiologicalSampleId;
	}

	public void setReteBiologicalSampleId(Integer reteBiologicalSampleId) {
		this.reteBiologicalSampleId = reteBiologicalSampleId;
	}
	

	// bi-directional many-to-one association to RunOverEvent
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ruov_id")
	public RunOverEvent getRunOverEvent() {
		return this.runOverEvent;
	}

	public void setRunOverEvent(RunOverEvent runOverEvent) {
		this.runOverEvent = runOverEvent;
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

	/**
	 * id de ActionPlan
	 */
	@ManyToOne
	@JoinColumn(name = "acpl_id")
	public ActionPlan getActionPlan() {
		return this.actionPlan;
	}

	public void setActionPlan(ActionPlan actionPlan) {
		this.actionPlan = actionPlan;
	}

	/**
	 * id de retention
	 */
	@ManyToOne
	@JoinColumn(name = "rete_id")
	public RetentionEvent getRetentionEvent() {
		return this.retentionEvent;
	}

	public void setRetentionEvent(RetentionEvent retentionEvent) {
		this.retentionEvent = retentionEvent;
	}

	/**
	 * id de retention
	 */
	@ManyToOne
	@JoinColumn(name = "rese_id")
	public RescueEvent getRescueEvent() {
		return this.rescueEvent;
	}

	public void setRescueEvent(RescueEvent rescueEvent) {
		this.rescueEvent = rescueEvent;
	}

	@Transient
	private SpecieTaxa specieTaxa;

	@Transient
	public SpecieTaxa getSpecieTaxa() {
		return specieTaxa;
	}

	@Transient
	public void setSpecieTaxa(SpecieTaxa specieTaxa) {
		this.specieTaxa = specieTaxa;
	}
	
	@Column(name = "spre_kingdom")
	public String getSpreKingdom() {
		return this.spreKingdom;
	}

	public void setSpreKingdom(String spreKingdom) {
		this.spreKingdom = spreKingdom;
	}
	
	@Column(name = "spre_class")
	public String getSpreClass() {
		return this.spreClass;
	}

	public void setSpreClass(String spreClass) {
		this.spreClass = spreClass;
	}
	
	@Column(name = "spre_quantity_male")
	public Integer getSpreQuantityMale() {
		return this.spreQuantityMale;
	}

	public void setSpreQuantityMale(Integer spreQuantityMale) {
		this.spreQuantityMale = spreQuantityMale;
	}
	
	@Column(name = "spre_quantity_femal")
	public Integer getSpreQuantityFemal() {
		return this.spreQuantityFemal;
	}

	public void setSpreQuantityFemal(Integer spreQuantityFemal) {
		this.spreQuantityFemal = spreQuantityFemal;
	}
	
	@Column(name = "spre_quantity_indeterminate")
	public Integer getSpreQuantityIndeterminate() {
		return this.spreQuantityIndeterminate;
	}

	public void setSpreQuantityIndeterminate(Integer spreQuantityIndeterminate) {
		this.spreQuantityIndeterminate = spreQuantityIndeterminate;
	}
	
	@Transient
	public String nameStageLife;

	@Transient
	public String getNameStageLife() {
		return nameStageLife;
	}

	@Transient
	public void setNameStageLife(String nameStageLife) {
		this.nameStageLife = nameStageLife;
	}

	
	@Transient
	public String namePhysicalState;

	@Transient
	public String getNamePhysicalState() {
		return namePhysicalState;
	}

	@Transient
	public void setNamePhysicalState(String namePhysicalState) {
		this.namePhysicalState = namePhysicalState;
	}
	
	
	@Column(name = "spre_monetary_cost")
	public Double getSpreMonetaryCost() {
		return this.spreMonetaryCost;
	}

	public void setSpreMonetaryCost(Double spreMonetaryCost) {
		this.spreMonetaryCost = spreMonetaryCost;
	}
	
	@Transient
	public String nameType;

	@Transient
	public String getNameType() {
		return nameType;
	}

	@Transient
	public void setNameType(String nameType) {
		this.nameType = nameType;
	}
	
	@Column(name = "spre_volume")
	public Double getSpreVolume() {
		return this.spreVolume;
	}

	public void setSpreVolume(Double spreVolume) {
		this.spreVolume = spreVolume;
	}
	
	@Column(name = "spre_is_weight")
	public Boolean getSpreIsWeight() {
		return this.spreIsWeight;
	}

	public void setSpreIsWeight(Boolean spreIsWeight) {
		this.spreIsWeight = spreIsWeight;
	}
	
	@Column(name = "spre_unit")
	public String getSpreUnit() {
		return this.spreUnit;
	}

	public void setSpreUnit(String spreUnit) {
		this.spreUnit = spreUnit;
	}

	@Transient
	public BiodiversityGeneralCatalog getStatusSpecie() {
		return statusSpecie;
	}

	@Transient
	public void setStatusSpecie(BiodiversityGeneralCatalog statusSpecie) {
		this.statusSpecie = statusSpecie;
	}
}