package ec.gob.ambiente.enlisy.cetas.model;

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
 * The persistent class for the patent_activation database table.
 * 
 */
@Entity
@Table(name="patent_activation", schema="cetas")
@NamedQuery(name="PatentActivation.findAll", query="SELECT c FROM PatentActivation c")
public class PatentActivation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="paac_id")
	private Integer paacId;
	
	@Getter
	@Setter
	@Column(name="paac_procedure")
	private String paacProcedure;
	
	@Getter
	@Setter
	@Column(name="paac_period")
	private Integer paacPeriod;
	
	@Getter
	@Setter
	@Column(name="paac_patent_start_date")
	private Date paacPatentStartDate;
	
	@Getter
	@Setter
	@Column(name="paac_patent_end_date")
	private Date paacPatentEndDate;
	
	@Getter
	@Setter
	@Column(name="paac_procedure_start_date")
	private Date paacProcedureStartDate;
	
	@Getter
	@Setter
	@Column(name="paac_procedure_end_date")
	private Date paacProcedureEndDate;
	
	@Getter
	@Setter
	@Column(name="paac_status")
	private Boolean paacStatus;
	
	@Getter
	@Setter
	@Column(name="paac_description")
	private String paacDescription;
	
	@Getter
	@Setter
	@Column(name="paac_user_create")
	private String paacUserCreate;
	
	@Getter
	@Setter
	@Column(name="paac_date_create")
	private Date paacDateCreate;
	
	@Getter
	@Setter
	@Column(name="paac_user_update")
	private String paacUserUpdate;
	
	@Getter
	@Setter
	@Column(name="paac_date_update")
	private Date paacDateUpdate;


}