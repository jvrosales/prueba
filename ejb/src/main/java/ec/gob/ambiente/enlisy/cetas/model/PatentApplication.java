package ec.gob.ambiente.enlisy.cetas.model;

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

//import ec.gob.ambiente.enlisy.model.Organization;
//import ec.gob.ambiente.enlisy.model.People;
//import ec.gob.ambiente.enlisy.model.User;
import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the application database table.
 * 
 */
@Entity
@Table(name="patent_application", schema="cetas")
@NamedQuery(name="PatentApplication.findAll", query="SELECT o FROM PatentApplication o")
public class PatentApplication implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="paap_id")
	private Integer paapId;
	 
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="wmce_id")
	private WildlifeManagementCenter wildlifeManagementCenter;
	
	/*
	 * pendiente tabla en donde están las direcciones provinciales
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="oft_id")
	private GeneticResourcesCatalog office;
	*/
	
	/*
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="cwmc_id_status")
	private CatalogWmc Status;
	*/
	
	@Getter
	@Setter
	@Column(name="paap_code")
	private String paapCode;
		
	@Getter
	@Setter
	@Column(name="paap_validity_year")
	private Integer paapValidityYear;
		
	@Getter
	@Setter
	@Column(name="paap_validity_months")
	private Integer paapValidityMonths;
		
	@Getter
	@Setter
	@Column(name="paap_start_date")
	private Date paapStartDate;
		
	@Getter
	@Setter
	@Column(name="paap_end_date")
	private Date paapEndDate;
	
	@Getter
	@Setter
	@Column(name="paap_annual_food_budget")
	private Double paapAnnualFoodBudget;
	
	@Getter
	@Setter
	@Column(name="paap_patent_number")
	private String paapPatentNumber;
	
	@Getter
	@Setter
	@Column(name="paap_status_patent")
	private String paapStatusPatent;	
	
	@Getter
	@Setter
	@Column(name="paap_status")
	private Boolean paapStatus;	
	
	@Getter
	@Setter
	@Column(name="paap_user_create")
	private String paapUserCreate;
	
	@Getter
	@Setter
	@Column(name="paap_date_create")
	private Date paapDateCreate;
	
	@Getter
	@Setter
	@Column(name="paap_user_update")
	private String paapUserUpdate;
	
	@Getter
	@Setter
	@Column(name="paap_date_update")
	private Date paapDateUpdate;
}