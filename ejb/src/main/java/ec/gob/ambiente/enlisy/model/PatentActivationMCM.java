package ec.gob.ambiente.enlisy.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the application database table.
 * 
 */
@Entity
@Table(name="patent_activation", schema="biodiversity_mcm")
@NamedQuery(name="patentActivationMCM.findAll", query="SELECT o FROM PatentActivationMCM o")
public class PatentActivationMCM implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="paac_id")
	private Integer Id;
	
	@Getter
	@Setter
	@Column(name="paac_procedure")
	private String procedure;
	
	@Getter
	@Setter
	@Column(name="paac_period")
	private Integer period;
	
	@Getter
	@Setter
	@Column(name="paac_serial")
	private String serial;
	
	@Getter
	@Setter
	@Column(name="paac_patent_start_date")
	private Date patentStartDate;
	
	@Getter
	@Setter
	@Column(name="paac_patent_end_date")
	private Date patentEndDate;
	
	@Getter
	@Setter
	@Column(name="paac_procedure_start_date")	
	private Date procedureStartDate;
	
	@Getter
	@Setter
	@Column(name="paac_procedure_end_date")
	private Date procedureEndtDate;
	
	@Getter
	@Setter
	@Column(name="paac_status")
	private Boolean status;
	
	@Getter
	@Setter
	@Column(name="paac_description")
	private String description;
	
	@Getter
	@Setter
	@Column(name="paac_user_create")
	private String userCreate;
	
	
	@Getter
	@Setter
	@Column(name="paac_date_create")
	private Date dateCreate;
	
	@Getter
	@Setter
	@Column(name="paac_user_update")
	private String userUpdate;
	
	@Getter
	@Setter
	@Column(name="paac_date_update")
	private Date dateUpdate;
}