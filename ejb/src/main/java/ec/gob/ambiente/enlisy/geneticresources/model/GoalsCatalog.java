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

import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the goals_catalog database table.
 * 
 */
@Entity
@Table(name="goals_catalogs", schema="biodiversity")
@NamedQuery(name="GoalsCatalog.findAll", query="SELECT o FROM GoalsCatalog o")
public class GoalsCatalog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="goca_id")
	private Integer gocaId;
	
	@Getter
	@Setter
	@Column(name="goca_description")
	private String gocaDescription;
	
	@Getter
	@Setter
	@Column(name="goca_code")
	private String gocaCode;
	
	@Getter
	@Setter
	@Column(name="goca_code_parent")
	private String gocaCodeParent;
	
	@Getter
	@Setter
	@Column(name="goca_enable")
	private Boolean gocaEnable;
	
	@Getter
	@Setter
	@Column(name="goca_enable_time")
	private String gocaEnableTime;
	
	@Getter
	@Setter
	@Column(name="goca_status")
	private Boolean gocaStatus;
	
	@Getter
	@Setter
	@Column(name="goca_user_create")
	private String gocaUserCreate;
	
	@Getter
	@Setter
	@Column(name="goca_date_create")
	private Date gocaDateCreate;
	
	@Getter
	@Setter
	@Column(name="goca_user_update")
	private String gocaUserUpdate;
	
	@Getter
	@Setter
	@Column(name="goca_date_update")
	private Date gocaDateUpdate;
	
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="grca_id")
	private GeneticResourcesCatalog typeGoal;


}