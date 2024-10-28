package ec.gob.ambiente.enlisy.releaseevent.model;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Where;
import ec.gob.ambiente.enlisy.cetas.model.WildlifeManagementCenter;
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecieTaxa;
import ec.gob.ambiente.enlisy.wildlifeevent.model.GeneralData;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the release_species database table.
 * 
 */
@Entity
@Table(name="release_species", schema="biodiversity")
@NamedQuery(name="ReleaseSpecy.findAll", query="SELECT r FROM ReleaseSpecy r")
@Where(clause = "resp_status='t'")
public class ReleaseSpecy implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer respId;
	private String rescId;
	private double respAltitude;
	private Integer respCantonId;
	private String respCategoryUicn;
	private Timestamp respDate;
	private Timestamp respDateCreate;
	private Timestamp respDateUpdate;
	private Integer respHealthCondition;
	private Integer respLifeStage;
	private String respLocation;
	private String respNorthSouth;
	private String respObservations;
	private Integer respParroquiaId;
	private Integer respProvinceId;
	private String respRedListEc;
	private String respScientificName;
	private Integer respSex;
	private Boolean respStatus = true;
	private Integer respTenancyCenter;
	private String respTrackingDevice;
	private String respTrackingDeviceCode;
	private Integer respTypeTenancyCenter2;
	private String respUserCreate;
	private String respUserUpdate;
	private String respVernacularName;
	private double respX;
	private double respY;
	private String respZone;
	private ReleaseWildlife releaseWildlife;
	private SpecieTaxa speciesTaxa;
	private List<ReleaseTracking> releaseTrackings;
	private int respZoom=1;
	private String respNotification;
	private Boolean respFinalizeStatus = false;
	private String respFinalizeObservations;
	private Integer respVeterinaryReport;
	
	private WildlifeManagementCenter tenancyCenter;
	private GeneralData retentionRescueAct = new GeneralData();
	public ReleaseSpecy() {
	}


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RELEASE_SPECIES_GENERATOR")
	@SequenceGenerator(name = "RELEASE_SPECIES_GENERATOR", initialValue = 1, sequenceName = "seq_resp_id", schema = "biodiversity", allocationSize = 1)
	@Column(name="resp_id")
	public Integer getRespId() {
		return this.respId;
	}

	public void setRespId(Integer respId) {
		this.respId = respId;
	}


	@Column(name="resc_id")
	public String getRescId() {
		return this.rescId;
	}

	public void setRescId(String rescId) {
		this.rescId = rescId;
	}


	@Column(name="resp_altitude")
	public double getRespAltitude() {
		return this.respAltitude;
	}

	public void setRespAltitude(double respAltitude) {
		this.respAltitude = respAltitude;
	}


	@Column(name="resp_canton_id")
	public Integer getRespCantonId() {
		return this.respCantonId;
	}

	public void setRespCantonId(Integer respCantonId) {
		this.respCantonId = respCantonId;
	}


	@Column(name="resp_category_uicn")
	public String getRespCategoryUicn() {
		return this.respCategoryUicn;
	}

	public void setRespCategoryUicn(String respCategoryUicn) {
		this.respCategoryUicn = respCategoryUicn;
	}


	@Column(name="resp_date")
	public Timestamp getRespDate() {
		return this.respDate;
	}

	public void setRespDate(Timestamp respDate) {
		this.respDate = respDate;
	}


	@Column(name="resp_date_create")
	public Timestamp getRespDateCreate() {
		return this.respDateCreate;
	}

	public void setRespDateCreate(Timestamp respDateCreate) {
		this.respDateCreate = respDateCreate;
	}


	@Column(name="resp_date_update")
	public Timestamp getRespDateUpdate() {
		return this.respDateUpdate;
	}

	public void setRespDateUpdate(Timestamp respDateUpdate) {
		this.respDateUpdate = respDateUpdate;
	}


	@Column(name="resp_health_condition")
	public Integer getRespHealthCondition() {
		return this.respHealthCondition;
	}

	public void setRespHealthCondition(Integer respHealthCondition) {
		this.respHealthCondition = respHealthCondition;
	}


	@Column(name="resp_life_stage")
	public Integer getRespLifeStage() {
		return this.respLifeStage;
	}

	public void setRespLifeStage(Integer respLifeStage) {
		this.respLifeStage = respLifeStage;
	}


	@Column(name="resp_location")
	public String getRespLocation() {
		return this.respLocation;
	}

	public void setRespLocation(String respLocation) {
		this.respLocation = respLocation;
	}


	@Column(name="resp_north_south")
	public String getRespNorthSouth() {
		return this.respNorthSouth;
	}

	public void setRespNorthSouth(String respNorthSouth) {
		this.respNorthSouth = respNorthSouth;
	}


	@Column(name="resp_observations")
	public String getRespObservations() {
		return this.respObservations;
	}

	public void setRespObservations(String respObservations) {
		this.respObservations = respObservations;
	}


	@Column(name="resp_parroquia_id")
	public Integer getRespParroquiaId() {
		return this.respParroquiaId;
	}

	public void setRespParroquiaId(Integer respParroquiaId) {
		this.respParroquiaId = respParroquiaId;
	}


	@Column(name="resp_province_id")
	public Integer getRespProvinceId() {
		return this.respProvinceId;
	}

	public void setRespProvinceId(Integer respProvinceId) {
		this.respProvinceId = respProvinceId;
	}


	@Column(name="resp_red_list_ec")
	public String getRespRedListEc() {
		return this.respRedListEc;
	}

	public void setRespRedListEc(String respRedListEc) {
		this.respRedListEc = respRedListEc;
	}


	@Column(name="resp_scientific_name")
	public String getRespScientificName() {
		return this.respScientificName;
	}

	public void setRespScientificName(String respScientificName) {
		this.respScientificName = respScientificName;
	}


	@Column(name="resp_sex")
	public Integer getRespSex() {
		return this.respSex;
	}

	public void setRespSex(Integer respSex) {
		this.respSex = respSex;
	}


	@Column(name="resp_status")
	public Boolean getRespStatus() {
		return this.respStatus;
	}

	public void setRespStatus(Boolean respStatus) {
		this.respStatus = respStatus;
	}


	@Column(name="resp_tenancy_center")
	public Integer getRespTenancyCenter() {
		return this.respTenancyCenter;
	}

	public void setRespTenancyCenter(Integer respTenancyCenter) {
		this.respTenancyCenter = respTenancyCenter;
	}


	@Column(name="resp_tracking_device")
	public String getRespTrackingDevice() {
		return this.respTrackingDevice;
	}

	public void setRespTrackingDevice(String respTrackingDevice) {
		this.respTrackingDevice = respTrackingDevice;
	}


	@Column(name="resp_tracking_device_code")
	public String getRespTrackingDeviceCode() {
		return this.respTrackingDeviceCode;
	}

	public void setRespTrackingDeviceCode(String respTrackingDeviceCode) {
		this.respTrackingDeviceCode = respTrackingDeviceCode;
	}


	@Column(name="resp_type_tenancy_center2")
	public Integer getRespTypeTenancyCenter2() {
		return this.respTypeTenancyCenter2;
	}

	public void setRespTypeTenancyCenter2(Integer respTypeTenancyCenter2) {
		this.respTypeTenancyCenter2 = respTypeTenancyCenter2;
	}


	@Column(name="resp_user_create")
	public String getRespUserCreate() {
		return this.respUserCreate;
	}

	public void setRespUserCreate(String respUserCreate) {
		this.respUserCreate = respUserCreate;
	}


	@Column(name="resp_user_update")
	public String getRespUserUpdate() {
		return this.respUserUpdate;
	}

	public void setRespUserUpdate(String respUserUpdate) {
		this.respUserUpdate = respUserUpdate;
	}


	@Column(name="resp_vernacular_name")
	public String getRespVernacularName() {
		return this.respVernacularName;
	}

	public void setRespVernacularName(String respVernacularName) {
		this.respVernacularName = respVernacularName;
	}


	@Column(name="resp_x")
	public double getRespX() {
		return this.respX;
	}

	public void setRespX(double respX) {
		this.respX = respX;
	}


	@Column(name="resp_y")
	public double getRespY() {
		return this.respY;
	}

	public void setRespY(double respY) {
		this.respY = respY;
	}


	@Column(name="resp_zone")
	public String getRespZone() {
		return this.respZone;
	}

	public void setRespZone(String respZone) {
		this.respZone = respZone;
	}
	
	@Column(name="resp_zoom")
	public int getRespZoom() {
		return respZoom;
	}

	public void setRespZoom(int respZoom) {
		this.respZoom = respZoom;
	}
	
	@Column(name="resp_notification")
	public String getRespNotification() {
		return respNotification;
	}

	public void setRespNotification(String respNotification) {
		this.respNotification = respNotification;
	}

	@Column(name="resp_finalize_status")
	public Boolean getRespFinalizeStatus() {
		return respFinalizeStatus;
	}

	public void setRespFinalizeStatus(Boolean respFinalizeStatus) {
		this.respFinalizeStatus = respFinalizeStatus;
	}

	@Column(name="resp_finalize_observations")
	public String getRespFinalizeObservations() {
		return respFinalizeObservations;
	}

	public void setRespFinalizeObservations(String respFinalizeObservations) {
		this.respFinalizeObservations = respFinalizeObservations;
	}
	
	@Column(name="resp_veterinary_report")
	public Integer getRespVeterinaryReport() {
		return respVeterinaryReport;
	}

	public void setRespVeterinaryReport(Integer respVeterinaryReport) {
		this.respVeterinaryReport = respVeterinaryReport;
	}


	//bi-directional many-to-one association to ReleaseWildlife
	@ManyToOne
	@JoinColumn(name="rewi_id")
	public ReleaseWildlife getReleaseWildlife() {
		return this.releaseWildlife;
	}

	public void setReleaseWildlife(ReleaseWildlife releaseWildlife) {
		this.releaseWildlife = releaseWildlife;
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


	//bi-directional many-to-one association to ReleaseTracking
	@OneToMany(mappedBy="releaseSpecy")
	public List<ReleaseTracking> getReleaseTrackings() {
		return this.releaseTrackings;
	}

	public void setReleaseTrackings(List<ReleaseTracking> releaseTrackings) {
		this.releaseTrackings = releaseTrackings;
	}

	public ReleaseTracking addReleaseTracking(ReleaseTracking releaseTracking) {
		getReleaseTrackings().add(releaseTracking);
		releaseTracking.setReleaseSpecy(this);

		return releaseTracking;
	}

	public ReleaseTracking removeReleaseTracking(ReleaseTracking releaseTracking) {
		getReleaseTrackings().remove(releaseTracking);
		releaseTracking.setReleaseSpecy(null);

		return releaseTracking;
	}

	@Transient
	public WildlifeManagementCenter getTenancyCenter() {
		return tenancyCenter;
	}

	public void setTenancyCenter(WildlifeManagementCenter tenancyCenter) {
		this.tenancyCenter = tenancyCenter;
	}
	
	@Transient
	public GeneralData getRetentionRescueAct() {
		return retentionRescueAct;
	}

	public void setRetentionRescueAct(GeneralData retentionRescueAct) {
		this.retentionRescueAct = retentionRescueAct;
	}

}