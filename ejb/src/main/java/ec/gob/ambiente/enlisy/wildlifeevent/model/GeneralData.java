package ec.gob.ambiente.enlisy.wildlifeevent.model;

import javax.persistence.*;
import org.hibernate.annotations.Where;
import ec.gob.ambiente.enlisy.dao.ConvertibleEntity;
import ec.gob.ambiente.enlisy.model.GeographicalLocation;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the general_data database table.
 * 
 */
@Entity
@Table(name="general_data", schema="biodiversity")
@NamedQuery(name="GeneralData.findAll", query="SELECT g FROM GeneralData g")
@Where(clause = "geda_status='t'")
public class GeneralData  extends ConvertibleEntity {
	private static final long serialVersionUID = 1L;
	private Integer gedaId;
	private double gedaAltitude;
	private Integer gedaCantonId;
	private String gedaCode;
	private Timestamp gedaDateCreate;
	private Timestamp gedaDateUpdate;
	private Timestamp gedaEventDate;
	private Integer gedaEventType;
	private Integer gedaInaturalistId;
	private String gedaInformerEmail;
	private Integer gedaInformerId;
	private String gedaInformerIdentification;
	private Integer gedaInformerInaturalistId;
	private String gedaInformerName;
	private String gedaInformerPhone;
	private String gedaLocation;
	private String gedaNorthSouth;
	private String gedaObservations;
	private Integer gedaParroquiaId;
	private Integer gedaProvinceId;
	private Timestamp gedaRegisterDate;
	private Integer gedaSource;
	private Boolean gedaStatus=true;
	private String gedaUserCreate;
	private String gedaUserUpdate;
	private double gedaX;
	private double gedaY;
	private String gedaZone = "EPSG:32717";
	private String gedaProcessStatus;
	private List<EventTracking> eventTrackings;
	private List<PeopleWildlifeEvent> peopleWildlifeEvents;
	private List<RunOverEvent> runOverEvents;
	private int gedaZoom=1;
	private String gedaInaturalistPicture;
	private String gedaInaturalistUrl;
	private String gedaArea;
	private GeographicalLocation province;
	private Boolean gedaFinalizeStatus=false;
	private String gedaFinalizeObservations;
	private String gedaInfoSourceOther;
	private String userCreatorName = "";
	private Integer gedaStatusTracking;
	private String gedaTrackingObservations;

	private Double gedaOriginalX;
	private Double gedaOriginalY;
	private String gedaProjectedZone;
	private String gedaCoordinateSystem;
	private String gedaInaturalistSpecies;
	private Boolean gedaWildlifeStatus;
	private GeographicalLocation canton;

	public GeneralData() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GENERAL_DATA_GENERATOR")
	@SequenceGenerator(name = "GENERAL_DATA_GENERATOR", initialValue = 1, sequenceName = "seq_geda_id", schema = "biodiversity", allocationSize = 1)
	@Column(name="geda_id")
	public Integer getGedaId() {
		return this.gedaId;
	}

	public void setGedaId(Integer gedaId) {
		this.gedaId = gedaId;
	}


	@Column(name="geda_altitude")
	public double getGedaAltitude() {
		return this.gedaAltitude;
	}

	public void setGedaAltitude(double gedaAltitude) {
		this.gedaAltitude = gedaAltitude;
	}


	@Column(name="geda_canton_id")
	public Integer getGedaCantonId() {
		return this.gedaCantonId;
	}

	public void setGedaCantonId(Integer gedaCantonId) {
		this.gedaCantonId = gedaCantonId;
	}


	@Column(name="geda_code")
	public String getGedaCode() {
		return this.gedaCode;
	}

	public void setGedaCode(String gedaCode) {
		this.gedaCode = gedaCode;
	}


	@Column(name="geda_date_create")
	public Timestamp getGedaDateCreate() {
		return this.gedaDateCreate;
	}

	public void setGedaDateCreate(Timestamp gedaDateCreate) {
		this.gedaDateCreate = gedaDateCreate;
	}


	@Column(name="geda_date_update")
	public Timestamp getGedaDateUpdate() {
		return this.gedaDateUpdate;
	}

	public void setGedaDateUpdate(Timestamp gedaDateUpdate) {
		this.gedaDateUpdate = gedaDateUpdate;
	}


	@Column(name="geda_event_date")
	public Timestamp getGedaEventDate() {
		return this.gedaEventDate;
	}

	public void setGedaEventDate(Timestamp gedaEventDate) {
		this.gedaEventDate = gedaEventDate;
	}


	@Column(name="geda_event_type")
	public Integer getGedaEventType() {
		return this.gedaEventType;
	}

	public void setGedaEventType(Integer gedaEventType) {
		this.gedaEventType = gedaEventType;
	}


	@Column(name="geda_inaturalist_id")
	public Integer getGedaInaturalistId() {
		return this.gedaInaturalistId;
	}

	public void setGedaInaturalistId(Integer gedaInaturalistId) {
		this.gedaInaturalistId = gedaInaturalistId;
	}


	@Column(name="geda_informer_email")
	public String getGedaInformerEmail() {
		return this.gedaInformerEmail;
	}

	public void setGedaInformerEmail(String gedaInformerEmail) {
		this.gedaInformerEmail = gedaInformerEmail;
	}


	@Column(name="geda_informer_id")
	public Integer getGedaInformerId() {
		return this.gedaInformerId;
	}

	public void setGedaInformerId(Integer gedaInformerId) {
		this.gedaInformerId = gedaInformerId;
	}


	@Column(name="geda_informer_identification")
	public String getGedaInformerIdentification() {
		return this.gedaInformerIdentification;
	}

	public void setGedaInformerIdentification(String gedaInformerIdentification) {
		this.gedaInformerIdentification = gedaInformerIdentification;
	}


	@Column(name="geda_informer_inaturalist_id")
	public Integer getGedaInformerInaturalistId() {
		return this.gedaInformerInaturalistId;
	}

	public void setGedaInformerInaturalistId(Integer gedaInformerInaturalistId) {
		this.gedaInformerInaturalistId = gedaInformerInaturalistId;
	}


	@Column(name="geda_informer_name")
	public String getGedaInformerName() {
		return this.gedaInformerName;
	}

	public void setGedaInformerName(String gedaInformerName) {
		this.gedaInformerName = gedaInformerName;
	}


	@Column(name="geda_informer_phone")
	public String getGedaInformerPhone() {
		return this.gedaInformerPhone;
	}

	public void setGedaInformerPhone(String gedaInformerPhone) {
		this.gedaInformerPhone = gedaInformerPhone;
	}


	@Column(name="geda_location")
	public String getGedaLocation() {
		return this.gedaLocation;
	}

	public void setGedaLocation(String gedaLocation) {
		this.gedaLocation = gedaLocation;
	}


	@Column(name="geda_north_south")
	public String getGedaNorthSouth() {
		return this.gedaNorthSouth;
	}

	public void setGedaNorthSouth(String gedaNorthSouth) {
		this.gedaNorthSouth = gedaNorthSouth;
	}


	@Column(name="geda_observations")
	public String getGedaObservations() {
		return this.gedaObservations;
	}

	public void setGedaObservations(String gedaObservations) {
		this.gedaObservations = gedaObservations;
	}


	@Column(name="geda_parroquia_id")
	public Integer getGedaParroquiaId() {
		return this.gedaParroquiaId;
	}

	public void setGedaParroquiaId(Integer gedaParroquiaId) {
		this.gedaParroquiaId = gedaParroquiaId;
	}


	@Column(name="geda_province_id")
	public Integer getGedaProvinceId() {
		return this.gedaProvinceId;
	}

	public void setGedaProvinceId(Integer gedaProvinceId) {
		this.gedaProvinceId = gedaProvinceId;
	}


	@Column(name="geda_register_date")
	public Timestamp getGedaRegisterDate() {
		return this.gedaRegisterDate;
	}

	public void setGedaRegisterDate(Timestamp gedaRegisterDate) {
		this.gedaRegisterDate = gedaRegisterDate;
	}


	@Column(name="geda_source")
	public Integer getGedaSource() {
		return this.gedaSource;
	}

	public void setGedaSource(Integer gedaSource) {
		this.gedaSource = gedaSource;
	}


	@Column(name="geda_status")
	public Boolean getGedaStatus() {
		return this.gedaStatus;
	}

	public void setGedaStatus(Boolean gedaStatus) {
		this.gedaStatus = gedaStatus;
	}


	@Column(name="geda_user_create")
	public String getGedaUserCreate() {
		return this.gedaUserCreate;
	}

	public void setGedaUserCreate(String gedaUserCreate) {
		this.gedaUserCreate = gedaUserCreate;
	}


	@Column(name="geda_user_update")
	public String getGedaUserUpdate() {
		return this.gedaUserUpdate;
	}

	public void setGedaUserUpdate(String gedaUserUpdate) {
		this.gedaUserUpdate = gedaUserUpdate;
	}


	@Column(name="geda_x")
	public double getGedaX() {
		return this.gedaX;
	}

	public void setGedaX(double gedaX) {
		this.gedaX = gedaX;
	}


	@Column(name="geda_y")
	public double getGedaY() {
		return this.gedaY;
	}

	public void setGedaY(double gedaY) {
		this.gedaY = gedaY;
	}


	@Column(name="geda_zone")
	public String getGedaZone() {
		return this.gedaZone;
	}

	public void setGedaZone(String gedaZone) {
		this.gedaZone = gedaZone;
	}
	
	@Column(name="geda_process_status")
	public String getGedaProcessStatus() {
		return this.gedaProcessStatus;
	}
	
	public void setGedaProcessStatus(String gedaProcessStatus) {
		this.gedaProcessStatus = gedaProcessStatus;
	}
	
	@Column(name="geda_inaturalist_picture")
	public String getGedaInaturalistPicture() {
		return gedaInaturalistPicture;
	}

	public void setGedaInaturalistPicture(String gedaInaturalistPicture) {
		this.gedaInaturalistPicture = gedaInaturalistPicture;
	}

	@Column(name="geda_inaturalist_url")
	public String getGedaInaturalistUrl() {
		return gedaInaturalistUrl;
	}

	public void setGedaInaturalistUrl(String gedaInaturalistUrl) {
		this.gedaInaturalistUrl = gedaInaturalistUrl;
	}

	@Column(name="geda_area")
	public String getGedaArea() {
		return gedaArea;
	}

	public void setGedaArea(String gedaArea) {
		this.gedaArea = gedaArea;
	}
	
	@Column(name="geda_finalize_status")
	public Boolean getGedaFinalizeStatus() {
		return gedaFinalizeStatus;
	}
	
	public void setGedaFinalizeStatus(Boolean gedaFinalizeStatus) {
		this.gedaFinalizeStatus = gedaFinalizeStatus;
	}
	
	@Column(name="geda_finalize_observations")
	public String getGedaFinalizeObservations() {
		return gedaFinalizeObservations;
	}

	public void setGedaFinalizeObservations(String gedaFinalizeObservations) {
		this.gedaFinalizeObservations = gedaFinalizeObservations;
	}
	
	@Column(name="geda_info_source_other")
	public String getGedaInfoSourceOther() {
		return gedaInfoSourceOther;
	}

	public void setGedaInfoSourceOther(String gedaInfoSourceOther) {
		this.gedaInfoSourceOther = gedaInfoSourceOther;
	}

	//bi-directional many-to-one association to EventTracking
	@OneToMany(mappedBy="generalData")
	public List<EventTracking> getEventTrackings() {
		return this.eventTrackings;
	}

	public void setEventTrackings(List<EventTracking> eventTrackings) {
		this.eventTrackings = eventTrackings;
	}

	public EventTracking addEventTracking(EventTracking eventTracking) {
		getEventTrackings().add(eventTracking);
		eventTracking.setGeneralData(this);

		return eventTracking;
	}

	public EventTracking removeEventTracking(EventTracking eventTracking) {
		getEventTrackings().remove(eventTracking);
		eventTracking.setGeneralData(null);

		return eventTracking;
	}

	@Column(name="geda_zoom")
	public int getGedaZoom() {
		return gedaZoom;
	}

	public void setGedaZoom(int gedaZoom) {
		this.gedaZoom = gedaZoom;
	}

	//bi-directional many-to-one association to PeopleWildlifeEvent
	@OneToMany(mappedBy="generalData")
	public List<PeopleWildlifeEvent> getPeopleWildlifeEvents() {
		return this.peopleWildlifeEvents;
	}

	public void setPeopleWildlifeEvents(List<PeopleWildlifeEvent> peopleWildlifeEvents) {
		this.peopleWildlifeEvents = peopleWildlifeEvents;
	}
	
	//bi-directional many-to-one association to RunOverEvent
	@OneToMany(mappedBy="generalData")
	public List<RunOverEvent> getRunOverEvents() {
		return this.runOverEvents;
	}

	public void setRunOverEvents(List<RunOverEvent> runOverEvents) {
		this.runOverEvents = runOverEvents;
	}

	public PeopleWildlifeEvent addPeopleWildlifeEvent(PeopleWildlifeEvent peopleWildlifeEvent) {
		getPeopleWildlifeEvents().add(peopleWildlifeEvent);
		peopleWildlifeEvent.setGeneralData(this);

		return peopleWildlifeEvent;
	}

	public PeopleWildlifeEvent removePeopleWildlifeEvent(PeopleWildlifeEvent peopleWildlifeEvent) {
		getPeopleWildlifeEvents().remove(peopleWildlifeEvent);
		peopleWildlifeEvent.setGeneralData(null);

		return peopleWildlifeEvent;
	}
	
	@Column(name="geda_status_tracking")
	public Integer getGedaStatusTracking() {
		return this.gedaStatusTracking;
	}

	public void setGedaStatusTracking(Integer gedaStatusTracking) {
		this.gedaStatusTracking = gedaStatusTracking;
	}

	@Column(name="geda_tracking_observations")
	public String getGedaTrackingObservations() {
		return gedaTrackingObservations;
	}

	public void setGedaTrackingObservations(String gedaTrackingObservations) {
		this.gedaTrackingObservations = gedaTrackingObservations;
	}
	

	@Transient
	public String getUserCreatorName() {
		return userCreatorName;
	}

	@Transient
	public void setUserCreatorName(String userCreatorName) {
		this.userCreatorName = userCreatorName;
	}

	@Transient
	@Override
	public long getId() {
		return getGedaId();
	}

	@Transient
	@Override
	public String getDescription() {
		return getGedaCode();
	}

	@Transient
	public GeographicalLocation getProvince() {
		return province;
	}

	@Transient
	public void setProvince(GeographicalLocation province) {
		this.province = province;
	}

	@Column(name="geda_original_x")
	public Double getGedaOriginalX() {
		return gedaOriginalX;
	}

	public void setGedaOriginalX(Double gedaOriginalX) {
		this.gedaOriginalX = gedaOriginalX;
	}

	@Column(name="geda_original_y")
	public Double getGedaOriginalY() {
		return gedaOriginalY;
	}

	public void setGedaOriginalY(Double gedaOriginalY) {
		this.gedaOriginalY = gedaOriginalY;
	}

	@Column(name="geda_projected_zone")
	public String getGedaProjectedZone() {
		return gedaProjectedZone;
	}

	public void setGedaProjectedZone(String gedaProjectedZone) {
		this.gedaProjectedZone = gedaProjectedZone;
	}

	@Column(name="geda_coordinate_system")
	public String getGedaCoordinateSystem() {
		return gedaCoordinateSystem;
	}

	public void setGedaCoordinateSystem(String gedaCoordinateSystem) {
		this.gedaCoordinateSystem = gedaCoordinateSystem;
	}

	@Column(name="geda_inaturalist_species")
	public String getGedaInaturalistSpecies() {
		return gedaInaturalistSpecies;
	}

	public void setGedaInaturalistSpecies(String gedaInaturalistSpecies) {
		this.gedaInaturalistSpecies = gedaInaturalistSpecies;
	}

	@Column(name="geda_wildlife_status")
	public Boolean getGedaWildlifeStatus() {
		return gedaWildlifeStatus;
	}

	public void setGedaWildlifeStatus(Boolean gedaWildlifeStatus) {
		this.gedaWildlifeStatus = gedaWildlifeStatus;
	}

	@Transient
	public GeographicalLocation getCanton() {
		return canton;
	}

	@Transient
	public void setCanton(GeographicalLocation canton) {
		this.canton = canton;
	}
}