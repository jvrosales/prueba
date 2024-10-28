package ec.gob.ambiente.enlisy.cetas.model;

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

import ec.gob.ambiente.enlisy.dao.ConvertibleEntity;
//import ec.gob.ambiente.enlisy.geneticresources.model.ProposedCollection;
import ec.gob.ambiente.enlisy.model.GeographicalLocation;
//import ec.gob.ambiente.enlisy.model.Organization;
//import ec.gob.ambiente.enlisy.model.People;
import ec.gob.ambiente.enlisy.model.User;
import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the application database table.
 * 
 */
@Entity
@Table(name="wildlife_management_center", schema="cetas")
@NamedQuery(name="WildlifeManagementCenter.findAll", query="SELECT o FROM WildlifeManagementCenter o")
public class WildlifeManagementCenter extends ConvertibleEntity {
	private static final long serialVersionUID = 1L;

/*
public class WildlifeManagementCenter implements Serializable ,Cloneable {
	private static final long serialVersionUID = 1L;
	
	@Override
	public WildlifeManagementCenter clone() throws CloneNotSupportedException {
			
		WildlifeManagementCenter clone= (WildlifeManagementCenter)super.clone();
		clone.setWmceId(null);
		clone.setWmceUserUpdate(null);
		clone.setWmceDateUpdate(null);
		return clone;
	}	
	*/
	

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="wmce_id")
	private Integer wmceId;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="cwmc_id_activity")
	private CatalogWmc Activity;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	/*
	@Getter
	@Setter
	@Column(name="wmce_code")
	private String wmceCode;
	*/
	
	/*	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="peop_id_tutor")
	private People peopleTutor; 

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="orga_id")
	private Organization organization;
		
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="grca_id_type_application")
	private GeneticResourcesCatalog typeApplication;
		
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="grca_id_type_applicant")
	private GeneticResourcesCatalog typeApplicant;
	*/
	
	/*
	 * pendiente tabla en donde están las direcciones provinciales
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="oft_id")
	private GeneticResourcesCatalog office;
	*/
	
	@Getter
	@Setter
	@Column(name="wmce_name")
	private String wmceName;	

	@Getter
	@Setter
	@Column(name="wmce_ruc")
	private String wmceRuc;
	
	@Getter
	@Setter
	@Column(name="wmce_representative_name")
	private String wmceRepresentativeName;
	
	@Getter
	@Setter
	@Column(name="wmce_representative_surname")
	private String wmceRepresentativeSurname;
	
	@Getter
	@Setter
	@Column(name="wmce_representative_email")
	private String wmceRepresentativeEmail;
	
	/*
	@Getter
	@Setter
	@Column(name="gelo_codification_province")
	private String geloCodificationProvince;
	
	@Getter
	@Setter
	@Column(name="gelo_codification_canton")
	private String geloCodificationCanton;
	
	@Getter
	@Setter
	@Column(name="gelo_codification_parish")
	private String geloCodificationParish;
	*/
	
	
	//bi-directional many-to-one association to GeographicalLocation
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="gelo_id")
	private GeographicalLocation geographicalLocation;
	
	
/*	
	@Getter
	@Setter
	@Column(name="gelo_id")
	private Integer geloId;
	*/
	
	
	@Getter
	@Setter
	@Column(name="wmce_address")
	private String wmceAddress;
	
	@Getter
	@Setter
	@Column(name="wmce_phone")
	private String wmcePhone;
	
	@Getter
	@Setter
	@Column(name="wmce_email")
	private String wmceEmail;
	
	@Getter
	@Setter
	@Column(name="wmce_zone")
	private String wmceZone;
	
	@Getter
	@Setter
	@Column(name="wmce_coordinate_x")
	private Double wmceCoordinateX;
	
	@Getter
	@Setter
	@Column(name="wmce_coordinate_y")
	private Double wmceCoordinateY;
	
	@Getter
	@Setter
	@Column(name="wmce_status")
	private Boolean wmceStatus;
	
	@Getter
	@Setter
	@Column(name="wmce_branch_office")
	private Integer wmceBranchOffice;
	
	@Getter
	@Setter
	@Column(name="wmce_objective")
	private String wmceObjective;
	
	@Getter
	@Setter
	@Column(name="wmce_registration_system")
	private String wmceRegistrationSystem;
	
	@Getter
	@Setter
	@Column(name="wmce_registration_system_other")
	private String wmceRegistrationSystemOther;
	
	@Getter
	@Setter
	@Column(name="wmce_security_system")
	private String wmceSecuritySystem;
	
	@Getter
	@Setter
	@Column(name="wmce_sanitary_measures")
	private String wmceSanitaryMeasures;
	
	@Getter
	@Setter
	@Column(name="wmce_financing")
	private Double wmceFinancing;
	
	@Getter
	@Setter
	@Column(name="wmce_financing_detail")
	private String wmceFinancingDetail;
	
	@Getter
	@Setter
	@Column(name="wmce_status_process")
	private String wmceStatusProcess;
	
	@Getter
	@Setter
	@Column(name="wmce_user_create")
	private String wmceUserCreate;
	
	@Getter
	@Setter
	@Column(name="wmce_date_create")
	private Date wmceDateCreate;
	
	@Getter
	@Setter
	@Column(name="wmce_user_update")
	private String wmceUserUpdate;
	
	@Getter
	@Setter
	@Column(name="wmce_date_update")
	private Date wmceDateUpdate;

	@Override
	public long getId() {
		return getWmceId();
	}

	@Override
	public String getDescription() {
		return getWmceName();
	}
}