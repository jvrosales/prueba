package ec.gob.ambiente.enlisy.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the consultants database table.
 * 
 */
@Entity
@Table(name = "consultants", schema = "suia_iii")
public class Consultants{
	

	@Getter
	@Setter
	@Column(name = "cons_id")
	@Id	
	private Integer id;

	@Getter
	@Setter
	@Column(name = "cons_category")
	private String consCategory;
	
	@Getter
	@Setter
	@Column(name = "cons_record")
	private String consRecord;
	
	@Getter
	@Setter
	@Column(name = "cons_name")
	private String consName;
	
	@Getter
	@Setter
	@Column(name = "cons_city")
	private String consCity;
	
	@Getter
	@Setter
	@Column(name = "cons_ruc")
	private String consRuc;
	
	@Getter
	@Setter
	@Column(name = "cons_address")
	private String consAddress;
	
	@Getter
	@Setter
	@Column(name = "cons_phone")
	private String consPhone;
	
	@Getter
	@Setter
	@Column(name = "cons_email")
	private String consEmail;
	
	@Getter
	@Setter
	@Column(name = "cons_observations")
	private String consObservations;
	
	@Getter
	@Setter
	@Column(name = "cons_status")
	private Boolean consStatus;
	
	@Getter
	@Setter
	@Column(name = "cons_type_consultant")
	private String consTypeConsultant;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Getter
	@Setter
	@Column(name = "cons_date_creation")
	private Date consDateCreation;
	
	@Getter
	@Setter
	@Column(name = "cons_date_modification")
	private Date consDateModification;
	
	@Getter
	@Setter
	@Column(name = "cons_user_creation")
	private String consUserCreation;
	
	@Getter
	@Setter
	@Column(name = "cons_user_modification")
	private String consUserModification;
	
	@Getter
	@Setter
	@Column(name = "cons_qualified")
	private Integer consQualified;	

}