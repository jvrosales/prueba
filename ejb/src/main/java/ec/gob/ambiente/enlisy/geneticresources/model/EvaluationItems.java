package ec.gob.ambiente.enlisy.geneticresources.model;

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
 * The persistent class for the evaluation_items database table.
 * 
 */
@Entity
@Table(name="evaluation_items", schema="biodiversity")
@NamedQuery(name="EvaluationItems.findAll", query="SELECT o FROM EvaluationItems o")
public class EvaluationItems implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="evit_id")
	private Integer evitId;
	
	@Getter
	@Setter
	@Column(name="evit_item")
	private String evitItem;
	
	@Getter
	@Setter
	@Column(name="evit_subitem")
	private String evitSubitem;
	
	@Getter
	@Setter
	@Column(name="evit_criteria")
	private String evitCriteria;
	
	@Getter
	@Setter
	@Column(name="evit_evaluation_code")
	private String evitEvaluationCode;
	
	@Getter
	@Setter
	@Column(name="evit_weighing")
	private Double evitWeighing;  
	
	@Getter
	@Setter
	@Column(name="evit_description")
	private String evitDescription;
	
	@Getter
	@Setter
	@Column(name="evit_status")
	private Boolean evitStatus;
	
	@Getter
	@Setter
	@Column(name="evit_user_create")
	private String evitUserCreate;
	
	@Getter
	@Setter
	@Column(name="evit_date_create")
	private Date evitDateCreate;
	
	@Getter
	@Setter
	@Column(name="evit_user_update")
	private String evitUserUpdate;
	
	@Getter
	@Setter
	@Column(name="evit_date_update")
	private Date evitDateUpdate;


}