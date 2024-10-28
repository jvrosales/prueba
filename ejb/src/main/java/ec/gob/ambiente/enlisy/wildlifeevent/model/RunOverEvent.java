package ec.gob.ambiente.enlisy.wildlifeevent.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the run_over_events database table.
 * 
 */
@Entity
@Table(name="run_over_events", schema="biodiversity")
@NamedQuery(name="RunOverEvent.findAll", query="SELECT r FROM RunOverEvent r")
public class RunOverEvent implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer ruovId;
	private String rouvNameVia;
	private Integer rouvRouteId;
	private Integer rouvSectionVia;
	private Integer ruovAssociatedVeterinariansI;
	private Integer ruovCenterConventionId;
	private Boolean ruovConfirmationNotification=false;
	private Timestamp ruovDateCreate;
	private Timestamp ruovDateUpdate;
	private String ruovEventOrigin;
	private Integer ruovGadId;
	private Boolean ruovNotification=false;
	private String ruovObservation;
	private Boolean ruovStatus=true;
	private String ruovUserCreate;
	private String ruovUserUpdate;
	private Boolean ruovVeterinary=false;
	private Boolean ruovWildlife=true;
	private GeneralData generalData;
	private String ruovPicture;
	private String ruovInspectionFile;

	private Integer ruovFinalDestinationType;
	private Timestamp ruovFinalDestinationDate;
	private String ruovFinalDestinationPlace;
	private String ruovFinalDestinationObservation;
	

	public RunOverEvent() {
	}


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RUN_OVER_EVENT_GENERATOR")
	@SequenceGenerator(name = "RUN_OVER_EVENT_GENERATOR", initialValue = 1, sequenceName = "seq_ruov_id", schema = "biodiversity", allocationSize = 1)
	@Column(name="ruov_id")
	public Integer getRuovId() {
		return this.ruovId;
	}

	public void setRuovId(Integer ruovId) {
		this.ruovId = ruovId;
	}


	@Column(name="rouv_name_via")
	public String getRouvNameVia() {
		return this.rouvNameVia;
	}

	public void setRouvNameVia(String rouvNameVia) {
		this.rouvNameVia = rouvNameVia;
	}


	@Column(name="rouv_route_id")
	public Integer getRouvRouteId() {
		return this.rouvRouteId;
	}

	public void setRouvRouteId(Integer rouvRouteId) {
		this.rouvRouteId = rouvRouteId;
	}


	@Column(name="rouv_section_via")
	public Integer getRouvSectionVia() {
		return this.rouvSectionVia;
	}

	public void setRouvSectionVia(Integer rouvSectionVia) {
		this.rouvSectionVia = rouvSectionVia;
	}


	@Column(name="ruov_associated_veterinarians_i")
	public Integer getRuovAssociatedVeterinariansI() {
		return this.ruovAssociatedVeterinariansI;
	}

	public void setRuovAssociatedVeterinariansI(Integer ruovAssociatedVeterinariansI) {
		this.ruovAssociatedVeterinariansI = ruovAssociatedVeterinariansI;
	}


	@Column(name="ruov_center_convention_id")
	public Integer getRuovCenterConventionId() {
		return this.ruovCenterConventionId;
	}

	public void setRuovCenterConventionId(Integer ruovCenterConventionId) {
		this.ruovCenterConventionId = ruovCenterConventionId;
	}


	@Column(name="ruov_confirmation_notification")
	public Boolean getRuovConfirmationNotification() {
		return this.ruovConfirmationNotification;
	}

	public void setRuovConfirmationNotification(Boolean ruovConfirmationNotification) {
		this.ruovConfirmationNotification = ruovConfirmationNotification;
	}


	@Column(name="ruov_date_create")
	public Timestamp getRuovDateCreate() {
		return this.ruovDateCreate;
	}

	public void setRuovDateCreate(Timestamp ruovDateCreate) {
		this.ruovDateCreate = ruovDateCreate;
	}


	@Column(name="ruov_date_update")
	public Timestamp getRuovDateUpdate() {
		return this.ruovDateUpdate;
	}

	public void setRuovDateUpdate(Timestamp ruovDateUpdate) {
		this.ruovDateUpdate = ruovDateUpdate;
	}


	@Column(name="ruov_event_origin")
	public String getRuovEventOrigin() {
		return this.ruovEventOrigin;
	}

	public void setRuovEventOrigin(String ruovEventOrigin) {
		this.ruovEventOrigin = ruovEventOrigin;
	}


	@Column(name="ruov_gad_id")
	public Integer getRuovGadId() {
		return this.ruovGadId;
	}

	public void setRuovGadId(Integer ruovGadId) {
		this.ruovGadId = ruovGadId;
	}


	@Column(name="ruov_notification")
	public Boolean getRuovNotification() {
		return this.ruovNotification;
	}

	public void setRuovNotification(Boolean ruovNotification) {
		this.ruovNotification = ruovNotification;
	}


	@Column(name="ruov_observation")
	public String getRuovObservation() {
		return this.ruovObservation;
	}

	public void setRuovObservation(String ruovObservation) {
		this.ruovObservation = ruovObservation;
	}


	@Column(name="ruov_status")
	public Boolean getRuovStatus() {
		return this.ruovStatus;
	}

	public void setRuovStatus(Boolean ruovStatus) {
		this.ruovStatus = ruovStatus;
	}


	@Column(name="ruov_user_create")
	public String getRuovUserCreate() {
		return this.ruovUserCreate;
	}

	public void setRuovUserCreate(String ruovUserCreate) {
		this.ruovUserCreate = ruovUserCreate;
	}


	@Column(name="ruov_user_update")
	public String getRuovUserUpdate() {
		return this.ruovUserUpdate;
	}

	public void setRuovUserUpdate(String ruovUserUpdate) {
		this.ruovUserUpdate = ruovUserUpdate;
	}


	@Column(name="ruov_veterinary")
	public Boolean getRuovVeterinary() {
		return this.ruovVeterinary;
	}

	public void setRuovVeterinary(Boolean ruovVeterinary) {
		this.ruovVeterinary = ruovVeterinary;
	}


	@Column(name="ruov_wildlife")
	public Boolean getRuovWildlife() {
		return this.ruovWildlife;
	}

	public void setRuovWildlife(Boolean ruovWildlife) {
		this.ruovWildlife = ruovWildlife;
	}
	
	@Column(name="ruov_picture")
	public String getRuovPicture() {
		return this.ruovPicture;
	}

	public void setRuovPicture(String ruovPicture) {
		this.ruovPicture = ruovPicture;
	}
	
	@Column(name="ruov_inspection_file")
	public String getRuovInspectionFile() {
		return this.ruovInspectionFile;
	}

	public void setRuovInspectionFile(String ruovInspectionFile) {
		this.ruovInspectionFile = ruovInspectionFile;
	}


	//bi-directional many-to-one association to GeneralData
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="geda_id")
	public GeneralData getGeneralData() {
		return this.generalData;
	}

	public void setGeneralData(GeneralData generalData) {
		this.generalData = generalData;
	}



	//bi-directional many-to-one association to SpeciesTaxaRetentionsEvent
//	@OneToMany(mappedBy="runOverEvent")
//	public List<SpeciesTaxaRetentionsEvent> getSpeciesTaxaRetentionsEvents() {
//		return this.speciesTaxaRetentionsEvents;
//	}
//
//	public void setSpeciesTaxaRetentionsEvents(List<SpeciesTaxaRetentionsEvent> speciesTaxaRetentionsEvents) {
//		this.speciesTaxaRetentionsEvents = speciesTaxaRetentionsEvents;
//	}
//
//	public SpeciesTaxaRetentionsEvent addSpeciesTaxaRetentionsEvent(SpeciesTaxaRetentionsEvent speciesTaxaRetentionsEvent) {
//		getSpeciesTaxaRetentionsEvents().add(speciesTaxaRetentionsEvent);
//		speciesTaxaRetentionsEvent.setRunOverEvent(this);
//
//		return speciesTaxaRetentionsEvent;
//	}
//
//	public SpeciesTaxaRetentionsEvent removeSpeciesTaxaRetentionsEvent(SpeciesTaxaRetentionsEvent speciesTaxaRetentionsEvent) {
//		getSpeciesTaxaRetentionsEvents().remove(speciesTaxaRetentionsEvent);
//		speciesTaxaRetentionsEvent.setRunOverEvent(null);
//
//		return speciesTaxaRetentionsEvent;
//	}


	@Column(name="ruov_final_destination_type")
	public Integer getRuovFinalDestinationType() {
		return ruovFinalDestinationType;
	}

	public void setRuovFinalDestinationType(Integer ruovFinalDestinationType) {
		this.ruovFinalDestinationType = ruovFinalDestinationType;
	}

	@Column(name="ruov_final_destination_date")
	public Timestamp getRuovFinalDestinationDate() {
		return ruovFinalDestinationDate;
	}

	public void setRuovFinalDestinationDate(Timestamp ruovFinalDestinationDate) {
		this.ruovFinalDestinationDate = ruovFinalDestinationDate;
	}

	@Column(name="ruov_final_destination_place")
	public String getRuovFinalDestinationPlace() {
		return ruovFinalDestinationPlace;
	}

	public void setRuovFinalDestinationPlace(String ruovFinalDestinationPlace) {
		this.ruovFinalDestinationPlace = ruovFinalDestinationPlace;
	}

	@Column(name="ruov_final_destination_observation")
	public String getRuovFinalDestinationObservation() {
		return ruovFinalDestinationObservation;
	}

	public void setRuovFinalDestinationObservation(String ruovFinalDestinationObservation) {
		this.ruovFinalDestinationObservation = ruovFinalDestinationObservation;
	}

}