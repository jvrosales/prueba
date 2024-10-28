package ec.gob.ambiente.enlisy.wildlifeevent.model;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;
import org.hibernate.annotations.OrderBy;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the people_wildlife_events database table.
 * 
 */
@Entity
@Table(name="people_wildlife_events", schema="biodiversity")
@NamedQuery(name="PeopleWildlifeEvent.findAll", query="SELECT p FROM PeopleWildlifeEvent p")
public class PeopleWildlifeEvent implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer pewiId;
	private Boolean pewiAcceptance;
	private Integer pewiAttackingAnimal;
	private Timestamp pewiDateCreate;
	private Timestamp pewiDateUpdate;
	private Boolean pewiFirstEvent;
	private Boolean pewiInProtectedAreas;
	private Boolean pewiInWaterSource;
	private Boolean pewiInWoodedAreas;
	private String pewiInspectionFile;
	private String pewiInterviewedCelular;
	private String pewiInterviewedCommunity;
	private String pewiInterviewedEmail;
	private Integer pewiInterviewedId;
	private String pewiInterviewedIdentification;
	private String pewiInterviewedName;
	private String pewiInterviewedPhone;
	private String pewiInterviewedSector;
	private Integer pewiInterviewerId;
	private String pewiInterviewerJob;
	private String pewiInterviewerName;
	private String pewiJustification;
	private Integer pewiNumEvents;
	private String pewiPicture;
	private String pewiPicture2;
	private String pewiPicture3;
	private String pewiPrecautionaryMeasures;
	private String pewiRecommendations;
	private String pewiScientificName;
	private Boolean pewiStatus=true;
	private Boolean pewiSupportCentralPlant;
	private String pewiTaxonRank;
	private String pewiUserCreate;
	private String pewiUserUpdate;
	private String pewiVernacularName;
	private GeneralData generalData;
	private List<PetsPeopleWildlifeEvent> petsPeopleWildlifeEvents;
	private Boolean pewiResponseStatus=false;
	private String pewiCoordinatorResponse;

	private String pewiInterviewedAddress;
	private BigDecimal pewiExtensionProperty;
	private Integer pewiInterviewedActivity;
	private Boolean pewiAnimalDamage;
	private Boolean pewiCropDamage;
	private Boolean pewiAnimalHairs;
	private Boolean pewiAnimalFeces;
	private Boolean pewiAnimalFootprints;
	private Boolean pewiAnimalTraces;
	private String pewiTypeCropsAffected;
	private BigDecimal pewiNumberCropsAffected;
	private BigDecimal economicLossValue;


	public PeopleWildlifeEvent() {
	}


	@Id
	@SequenceGenerator(name="PEOPLE_WILDLIFE_EVENTS_PEWIID_GENERATOR",schema="BIODIVERSITY", sequenceName="SEQ_PEWI_ID", allocationSize =1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PEOPLE_WILDLIFE_EVENTS_PEWIID_GENERATOR")
	@Column(name="pewi_id")
	public Integer getPewiId() {
		return this.pewiId;
	}

	public void setPewiId(Integer pewiId) {
		this.pewiId = pewiId;
	}


	@Column(name="pewi_acceptance")
	public Boolean getPewiAcceptance() {
		return this.pewiAcceptance;
	}

	public void setPewiAcceptance(Boolean pewiAcceptance) {
		this.pewiAcceptance = pewiAcceptance;
	}


	@Column(name="pewi_attacking_animal")
	public Integer getPewiAttackingAnimal() {
		return this.pewiAttackingAnimal;
	}

	public void setPewiAttackingAnimal(Integer pewiAttackingAnimal) {
		this.pewiAttackingAnimal = pewiAttackingAnimal;
	}


	@Column(name="pewi_date_create")
	public Timestamp getPewiDateCreate() {
		return this.pewiDateCreate;
	}

	public void setPewiDateCreate(Timestamp pewiDateCreate) {
		this.pewiDateCreate = pewiDateCreate;
	}


	@Column(name="pewi_date_update")
	public Timestamp getPewiDateUpdate() {
		return this.pewiDateUpdate;
	}

	public void setPewiDateUpdate(Timestamp pewiDateUpdate) {
		this.pewiDateUpdate = pewiDateUpdate;
	}


	@Column(name="pewi_first_event")
	public Boolean getPewiFirstEvent() {
		return this.pewiFirstEvent;
	}

	public void setPewiFirstEvent(Boolean pewiFirstEvent) {
		this.pewiFirstEvent = pewiFirstEvent;
	}


	@Column(name="pewi_in_protected_areas")
	public Boolean getPewiInProtectedAreas() {
		return this.pewiInProtectedAreas;
	}

	public void setPewiInProtectedAreas(Boolean pewiInProtectedAreas) {
		this.pewiInProtectedAreas = pewiInProtectedAreas;
	}


	@Column(name="pewi_in_water_source")
	public Boolean getPewiInWaterSource() {
		return this.pewiInWaterSource;
	}

	public void setPewiInWaterSource(Boolean pewiInWaterSource) {
		this.pewiInWaterSource = pewiInWaterSource;
	}


	@Column(name="pewi_in_wooded_areas")
	public Boolean getPewiInWoodedAreas() {
		return this.pewiInWoodedAreas;
	}

	public void setPewiInWoodedAreas(Boolean pewiInWoodedAreas) {
		this.pewiInWoodedAreas = pewiInWoodedAreas;
	}


	@Column(name="pewi_inspection_file")
	public String getPewiInspectionFile() {
		return this.pewiInspectionFile;
	}

	public void setPewiInspectionFile(String pewiInspectionFile) {
		this.pewiInspectionFile = pewiInspectionFile;
	}


	@Column(name="pewi_interviewed_celular")
	public String getPewiInterviewedCelular() {
		return this.pewiInterviewedCelular;
	}

	public void setPewiInterviewedCelular(String pewiInterviewedCelular) {
		this.pewiInterviewedCelular = pewiInterviewedCelular;
	}


	@Column(name="pewi_interviewed_community")
	public String getPewiInterviewedCommunity() {
		return this.pewiInterviewedCommunity;
	}

	public void setPewiInterviewedCommunity(String pewiInterviewedCommunity) {
		this.pewiInterviewedCommunity = pewiInterviewedCommunity;
	}


	@Column(name="pewi_interviewed_email")
	public String getPewiInterviewedEmail() {
		return this.pewiInterviewedEmail;
	}

	public void setPewiInterviewedEmail(String pewiInterviewedEmail) {
		this.pewiInterviewedEmail = pewiInterviewedEmail;
	}


	@Column(name="pewi_interviewed_id")
	public Integer getPewiInterviewedId() {
		return this.pewiInterviewedId;
	}

	public void setPewiInterviewedId(Integer pewiInterviewedId) {
		this.pewiInterviewedId = pewiInterviewedId;
	}


	@Column(name="pewi_interviewed_identification")
	public String getPewiInterviewedIdentification() {
		return this.pewiInterviewedIdentification;
	}

	public void setPewiInterviewedIdentification(String pewiInterviewedIdentification) {
		this.pewiInterviewedIdentification = pewiInterviewedIdentification;
	}


	@Column(name="pewi_interviewed_name")
	public String getPewiInterviewedName() {
		return this.pewiInterviewedName;
	}

	public void setPewiInterviewedName(String pewiInterviewedName) {
		this.pewiInterviewedName = pewiInterviewedName;
	}


	@Column(name="pewi_interviewed_phone")
	public String getPewiInterviewedPhone() {
		return this.pewiInterviewedPhone;
	}

	public void setPewiInterviewedPhone(String pewiInterviewedPhone) {
		this.pewiInterviewedPhone = pewiInterviewedPhone;
	}


	@Column(name="pewi_interviewed_sector")
	public String getPewiInterviewedSector() {
		return this.pewiInterviewedSector;
	}

	public void setPewiInterviewedSector(String pewiInterviewedSector) {
		this.pewiInterviewedSector = pewiInterviewedSector;
	}


	@Column(name="pewi_interviewer_id")
	public Integer getPewiInterviewerId() {
		return this.pewiInterviewerId;
	}

	public void setPewiInterviewerId(Integer pewiInterviewerId) {
		this.pewiInterviewerId = pewiInterviewerId;
	}


	@Column(name="pewi_interviewer_job")
	public String getPewiInterviewerJob() {
		return this.pewiInterviewerJob;
	}

	public void setPewiInterviewerJob(String pewiInterviewerJob) {
		this.pewiInterviewerJob = pewiInterviewerJob;
	}


	@Column(name="pewi_interviewer_name")
	public String getPewiInterviewerName() {
		return this.pewiInterviewerName;
	}

	public void setPewiInterviewerName(String pewiInterviewerName) {
		this.pewiInterviewerName = pewiInterviewerName;
	}


	@Column(name="pewi_justification")
	public String getPewiJustification() {
		return this.pewiJustification;
	}

	public void setPewiJustification(String pewiJustification) {
		this.pewiJustification = pewiJustification;
	}


	@Column(name="pewi_num_events")
	public Integer getPewiNumEvents() {
		return this.pewiNumEvents;
	}

	public void setPewiNumEvents(Integer pewiNumEvents) {
		this.pewiNumEvents = pewiNumEvents;
	}


	@Column(name="pewi_picture")
	public String getPewiPicture() {
		return this.pewiPicture;
	}

	public void setPewiPicture(String pewiPicture) {
		this.pewiPicture = pewiPicture;
	}
	
	@Column(name="pewi_picture2")
	public String getPewiPicture2() {
		return this.pewiPicture2;
	}

	public void setPewiPicture2(String pewiPicture2) {
		this.pewiPicture2 = pewiPicture2;
	}
	
	@Column(name="pewi_picture3")
	public String getPewiPicture3() {
		return this.pewiPicture3;
	}

	public void setPewiPicture3(String pewiPicture3) {
		this.pewiPicture3 = pewiPicture3;
	}


	@Column(name="pewi_precautionary_measures")
	public String getPewiPrecautionaryMeasures() {
		return this.pewiPrecautionaryMeasures;
	}

	public void setPewiPrecautionaryMeasures(String pewiPrecautionaryMeasures) {
		this.pewiPrecautionaryMeasures = pewiPrecautionaryMeasures;
	}


	@Column(name="pewi_recommendations")
	public String getPewiRecommendations() {
		return this.pewiRecommendations;
	}

	public void setPewiRecommendations(String pewiRecommendations) {
		this.pewiRecommendations = pewiRecommendations;
	}


	@Column(name="pewi_scientific_name")
	public String getPewiScientificName() {
		return this.pewiScientificName;
	}

	public void setPewiScientificName(String pewiScientificName) {
		this.pewiScientificName = pewiScientificName;
	}


	@Column(name="pewi_status")
	public Boolean getPewiStatus() {
		return this.pewiStatus;
	}

	public void setPewiStatus(Boolean pewiStatus) {
		this.pewiStatus = pewiStatus;
	}


	@Column(name="pewi_support_central_plant")
	public Boolean getPewiSupportCentralPlant() {
		return this.pewiSupportCentralPlant;
	}

	public void setPewiSupportCentralPlant(Boolean pewiSupportCentralPlant) {
		this.pewiSupportCentralPlant = pewiSupportCentralPlant;
	}


	@Column(name="pewi_taxon_rank")
	public String getPewiTaxonRank() {
		return this.pewiTaxonRank;
	}

	public void setPewiTaxonRank(String pewiTaxonRank) {
		this.pewiTaxonRank = pewiTaxonRank;
	}


	@Column(name="pewi_user_create")
	public String getPewiUserCreate() {
		return this.pewiUserCreate;
	}

	public void setPewiUserCreate(String pewiUserCreate) {
		this.pewiUserCreate = pewiUserCreate;
	}


	@Column(name="pewi_user_update")
	public String getPewiUserUpdate() {
		return this.pewiUserUpdate;
	}

	public void setPewiUserUpdate(String pewiUserUpdate) {
		this.pewiUserUpdate = pewiUserUpdate;
	}


	@Column(name="pewi_vernacular_name")
	public String getPewiVernacularName() {
		return this.pewiVernacularName;
	}

	public void setPewiVernacularName(String pewiVernacularName) {
		this.pewiVernacularName = pewiVernacularName;
	}

	@Column(name="pewi_response_status")
	public Boolean getPewiResponseStatus() {
		return pewiResponseStatus;
	}

	public void setPewiResponseStatus(Boolean pewiResponseStatus) {
		this.pewiResponseStatus = pewiResponseStatus;
	}

	@Column(name="pewi_coordinator_response")
	public String getPewiCoordinatorResponse() {
		return pewiCoordinatorResponse;
	}

	public void setPewiCoordinatorResponse(String pewiCoordinatorResponse) {
		this.pewiCoordinatorResponse = pewiCoordinatorResponse;
	}

	//bi-directional many-to-one association to GeneralData
	@ManyToOne
	@JoinColumn(name="geda_id")
	public GeneralData getGeneralData() {
		return this.generalData;
	}

	public void setGeneralData(GeneralData generalData) {
		this.generalData = generalData;
	}


	//bi-directional many-to-one association to PetsPeopleWildlifeEvent
	@OneToMany(mappedBy="peopleWildlifeEvent", fetch=FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	@Where(clause = "pepe_status='t'")
	@OrderBy(clause = "pepe_id")
	public List<PetsPeopleWildlifeEvent> getPetsPeopleWildlifeEvents() {
		return this.petsPeopleWildlifeEvents;
	}

	public void setPetsPeopleWildlifeEvents(List<PetsPeopleWildlifeEvent> petsPeopleWildlifeEvents) {
		this.petsPeopleWildlifeEvents = petsPeopleWildlifeEvents;
	}

	public PetsPeopleWildlifeEvent addPetsPeopleWildlifeEvent(PetsPeopleWildlifeEvent petsPeopleWildlifeEvent) {
		getPetsPeopleWildlifeEvents().add(petsPeopleWildlifeEvent);
		petsPeopleWildlifeEvent.setPeopleWildlifeEvent(this);

		return petsPeopleWildlifeEvent;
	}

	public PetsPeopleWildlifeEvent removePetsPeopleWildlifeEvent(PetsPeopleWildlifeEvent petsPeopleWildlifeEvent) {
		getPetsPeopleWildlifeEvents().remove(petsPeopleWildlifeEvent);
		petsPeopleWildlifeEvent.setPeopleWildlifeEvent(null);

		return petsPeopleWildlifeEvent;
	}

	@Column(name="pewi_interviewed_address")
	public String getPewiInterviewedAddress() {
		return pewiInterviewedAddress;
	}

	public void setPewiInterviewedAddress(String pewiInterviewedAddress) {
		this.pewiInterviewedAddress = pewiInterviewedAddress;
	}

	@Column(name="pewi_extension_property")
	public BigDecimal getPewiExtensionProperty() {
		return pewiExtensionProperty;
	}

	public void setPewiExtensionProperty(BigDecimal pewiExtensionProperty) {
		this.pewiExtensionProperty = pewiExtensionProperty;
	}

	@Column(name="pewi_interviewed_activity")
	public Integer getPewiInterviewedActivity() {
		return pewiInterviewedActivity;
	}

	public void setPewiInterviewedActivity(Integer pewiInterviewedActivity) {
		this.pewiInterviewedActivity = pewiInterviewedActivity;
	}

	@Column(name="pewi_animal_damage")
	public Boolean getPewiAnimalDamage() {
		return pewiAnimalDamage;
	}

	public void setPewiAnimalDamage(Boolean pewiAnimalDamage) {
		this.pewiAnimalDamage = pewiAnimalDamage;
	}

	@Column(name="pewi_crop_damage")
	public Boolean getPewiCropDamage() {
		return pewiCropDamage;
	}

	public void setPewiCropDamage(Boolean pewiCropDamage) {
		this.pewiCropDamage = pewiCropDamage;
	}

	@Column(name="pewi_animal_hairs")
	public Boolean getPewiAnimalHairs() {
		return pewiAnimalHairs;
	}

	public void setPewiAnimalHairs(Boolean pewiAnimalHairs) {
		this.pewiAnimalHairs = pewiAnimalHairs;
	}

	@Column(name="pewi_animal_feces")
	public Boolean getPewiAnimalFeces() {
		return pewiAnimalFeces;
	}

	public void setPewiAnimalFeces(Boolean pewiAnimalFeces) {
		this.pewiAnimalFeces = pewiAnimalFeces;
	}

	@Column(name="pewi_animal_footprints")
	public Boolean getPewiAnimalFootprints() {
		return pewiAnimalFootprints;
	}

	public void setPewiAnimalFootprints(Boolean pewiAnimalFootprints) {
		this.pewiAnimalFootprints = pewiAnimalFootprints;
	}

	@Column(name="pewi_animal_traces")
	public Boolean getPewiAnimalTraces() {
		return pewiAnimalTraces;
	}

	public void setPewiAnimalTraces(Boolean pewiAnimalTraces) {
		this.pewiAnimalTraces = pewiAnimalTraces;
	}

	@Column(name="pewi_type_crops_affected")
	public String getPewiTypeCropsAffected() {
		return pewiTypeCropsAffected;
	}

	public void setPewiTypeCropsAffected(String pewiTypeCropsAffected) {
		this.pewiTypeCropsAffected = pewiTypeCropsAffected;
	}

	@Column(name="pewi_number_crops_affected")
	public BigDecimal getPewiNumberCropsAffected() {
		return pewiNumberCropsAffected;
	}

	public void setPewiNumberCropsAffected(BigDecimal pewiNumberCropsAffected) {
		this.pewiNumberCropsAffected = pewiNumberCropsAffected;
	}

	@Column(name="economic_loss_value")
	public BigDecimal getEconomicLossValue() {
		return economicLossValue;
	}

	public void setEconomicLossValue(BigDecimal economicLossValue) {
		this.economicLossValue = economicLossValue;
	}
}