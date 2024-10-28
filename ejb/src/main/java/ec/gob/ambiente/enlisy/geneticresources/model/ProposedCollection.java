package ec.gob.ambiente.enlisy.geneticresources.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import ec.gob.ambiente.enlisy.model.People;
import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the proposed_collection database table.
 * 
 */
@Entity
@Table(name="proposed_collection", schema="biodiversity")
@NamedQuery(name="ProposedCollection.findAll", query="SELECT o FROM ProposedCollection o where o.prcoStatus = true")
public class ProposedCollection implements Serializable ,Cloneable {
	private static final long serialVersionUID = 1L;
	
	@Override
	public ProposedCollection clone() throws CloneNotSupportedException {
		ProposedCollection clone= (ProposedCollection)super.clone();
		clone.setPrcoId(null);
		clone.setPrcoUserUpdate(null);
		clone.setPrcoDateUpdate(null);
		return clone;
	}

	public ProposedCollection(Integer prcoId) {
		super();
		this.prcoId = prcoId;
	}
	
	public ProposedCollection() {
	}

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="prco_id")
	private Integer prcoId;

	@Getter
	@Setter
	@Column(name="prco_date_start")
	private Date prcoDateStart;
	
	@Getter
	@Setter
	@Column(name="prco_date_end")
	private Date prcoDateEnd;
	
	@Getter
	@Setter
	@Column(name="prco_time")
	private String prcoTime;
	
	@Getter
	@Setter
	@Column(name="prco_problem_statement")
	private String prcoProblemStatement;
	
	@Getter
	@Setter
	@Column(name="prco_general_objective")
	private String prcoGeneralObjective;
	
	@Getter
	@Setter
	@Column(name="prco_backgrounds")
	private String prcoBackgrounds;
	
	@Getter
	@Setter
	@Column(name="prco_justification")
	private String prcoJustification;
	
	@Getter
	@Setter
	@Column(name="prco_collection_phase")
	private String prcoCollectionPhase;
	
	@Getter
	@Setter
	@Column(name="prco_preservation_phase")
	private String prcoPreservationPhase;
	
	@Getter
	@Setter
	@Column(name="prco_laboratory_methodology")
	private String prcoLaboratoryMethodology;
	
	@Getter
	@Setter
	@Column(name="prco_expected_results")
	private String prcoExpectedResults;
	
	@Getter
	@Setter
	@Column(name="prco_financing")
	private Double prcoFinancing;
	
	@Getter
	@Setter
	@Column(name="prco_export_species")
	private Boolean prcoExportEpecies;
	
	@Getter
	@Setter
	@Column(name="prco_status")
	private Boolean prcoStatus;
	
	@Getter
	@Setter
	@Column(name="prco_status_proposed")
	private String prcoStatusProposed;
	
	@Getter
	@Setter
	@Column(name="prco_user_create")
	private String prcoUserCreate;
	
	@Getter
	@Setter
	@Column(name="prco_date_create")
	private Date prcoDateCreate;
	
	@Getter
	@Setter
	@Column(name="prco_user_update")
	private String prcoUserUpdate;
	
	@Getter
	@Setter
	@Column(name="prco_date_update")
	private Date prcoDateUpdate;
	
	@Getter
	@Setter
	@Column(name="prco_collection_activities_date_start")
	private Date prcoCollectionActivitiesDateStart;
	
	@Getter
	@Setter
	@Column(name="prco_collection_activities_date_end")
	private Date prcoCollectionActivitiesDateEnd;
	
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="reap_id")
	private ResearchApplication researchApplication;
	
	@Getter
	@Setter
	@Column(name="prco_biological_kingdoms")
	private String prcoBiologicalKingdoms;
	
	@Getter
	@Setter
	@Column(name="prco_program")
	private Boolean prcoProgram;
	
	@Getter
	@Setter
	@Column(name="prco_renovation")
	private Boolean prcoRenovation;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="prco_id_parent")
	private ProposedCollection proposedCollection;
	
	@Getter
	@Setter
	@Column(name="prco_title")
	private String prcoTitle;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="peop_id")
	private People people;
	
	@Getter
	@Setter
	@Column(name="prco_justification_change")
	private String prcoJustificationChange;
	
	@Getter
	@Setter
	@Column(name="prco_code")
	private String prcoCode;
	
	@Getter
	@Setter
	@Column(name="prco_id_previous")
	private Integer prcoIdPrevious;

	@Getter
	@Setter
	@Column(name="prco_control")
	private Boolean prcoControl;
	
	@Getter
	@Setter
	@Column(name = "grca_id_time")
	private Integer grcaIdTime;
	
	@Getter
	@Setter
	@Column(name="prco_location")
	private String prcoProvincias;
	
	@Getter
	@Setter
	@Column(name="prco_snap")
	private String prcoSnap;
	
	@Getter
	@Setter
	@Column(name="prco_forest")
	private String prcoBosquesProtectores;
	
	@Getter
	@Setter
	@Column(name="prco_sites")
	private String prcoSitios;
	
	@Getter
	@Setter
	@Column(name="prco_collection_site")
	private String prcoCollectionSite;
	
	@Getter
	@Setter
	@Column(name="prco_private_property")
	private Boolean prcoPrivateProperty;
	
	@Getter
	@Setter
	@Column(name="prco_num_reg_contract_access")
	private String prcoNumRegContractAccess;
	
	@Getter
	@Setter
	@Column(name="prco_num_reg_contract_consent")
	private String prcoNumRegContractConsent;
	
	@Getter
	@Setter
	@Column(name="prco_justification_add_species")
	private String prcoJustificationAddSpecies;
	
	@Getter
	@Setter
	@Column(name="prco_justification_add_species_exsitu")
	private String prcoJustificationAddSpeciesExSitu;
	
	@Getter
	@Setter
	@Column(name="prco_modification_number")
	private Integer prcoMmodificationNumber;
	
	@Getter
	@Setter
	@Column(name="prco_observation")
	private String prcoObservation;
	
	@Getter
	@Setter
	@Column(name="prco_original_record")
	private Boolean prcoOriginalRecord;
	
	@Getter
	@Setter
	@Column(name="prco_new_format")
	private Boolean prcoNewFormat;
	
	@Getter
	@Setter
	@Column(name="prco_modify_project")
	private Boolean prcoModifyProject;

	@Getter
	@Setter
	@Transient
	public TechnicalTeamsResearch coordinador;
	
	public String getCode()
	{
		if(prcoCode!=null && !prcoCode.isEmpty())
			return prcoCode;
		return researchApplication.getReapCode();
	}
}
