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
import ec.gob.ambiente.enlisy.model.People;


/**
 * The persistent class for the application database table.
 * 
 */
@Entity
@Table(name="research_activities", schema="biodiversity")
@NamedQuery(name="ResearchActivities.findAll", query="SELECT o FROM ResearchActivities o")
public class ResearchActivities implements Serializable,Cloneable {
	private static final long serialVersionUID = 1L;
	
	public ResearchActivities clone(ProposedCollection proposedCollection) throws CloneNotSupportedException {
		
		ResearchActivities clone= (ResearchActivities)super.clone();
		clone.setReacId(null);
		clone.setReacUserUpdate(null);
		clone.setReacDateUpdate(null);
		clone.setProposedCollection(proposedCollection);
		return clone;
	}
	
	public ResearchActivities() {		
	}
	
	public ResearchActivities(People people,ProposedCollection proposedCollection) {
		this.people=people;
		this.proposedCollection=proposedCollection;
	}

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="reac_id")
	private Integer reacId;
    
    @Getter
	@Setter
	@Column(name="reac_activity")
	private String reacActivity;
	
	@Getter
	@Setter
	@Column(name="reac_year01_month01")
	private Boolean reacYear01Month01;
	
	@Getter
	@Setter
	@Column(name="reac_year01_month02")
	private Boolean reacYear01Month02;
	
	@Getter
	@Setter
	@Column(name="reac_year01_month03")
	private Boolean reacYear01Month03;
	
	@Getter
	@Setter
	@Column(name="reac_year01_month04")
	private Boolean reacYear01Month04;
	
	@Getter
	@Setter
	@Column(name="reac_year01_month05")
	private Boolean reacYear01Month05;
	
	@Getter
	@Setter
	@Column(name="reac_year01_month06")
	private Boolean reacYear01Month06;
	
	@Getter
	@Setter
	@Column(name="reac_year01_month07")
	private Boolean reacYear01Month07;
	
	@Getter
	@Setter
	@Column(name="reac_year01_month08")
	private Boolean reacYear01Month08;
	
	@Getter
	@Setter
	@Column(name="reac_year01_month09")
	private Boolean reacYear01Month09;
	
	@Getter
	@Setter
	@Column(name="reac_year01_month10")
	private Boolean reacYear01Month10;
	
	@Getter
	@Setter
	@Column(name="reac_year01_month11")
	private Boolean reacYear01Month11;
	
	@Getter
	@Setter
	@Column(name="reac_year01_month12")
	private Boolean reacYear01Month12;
	
	@Getter
	@Setter
	@Column(name="reac_year02_month01")
	private Boolean reacYear02Month01;
	
	@Getter
	@Setter
	@Column(name="reac_year02_month02")
	private Boolean reacYear02Month02;
	
	@Getter
	@Setter
	@Column(name="reac_year02_month03")
	private Boolean reacYear02Month03;
	
	@Getter
	@Setter
	@Column(name="reac_year02_month04")
	private Boolean reacYear02Month04;
	
	@Getter
	@Setter
	@Column(name="reac_year02_month05")
	private Boolean reacYear02Month05;
	
	@Getter
	@Setter
	@Column(name="reac_year02_month06")
	private Boolean reacYear02Month06;
	
	@Getter
	@Setter
	@Column(name="reac_year02_month07")
	private Boolean reacYear02Month07;
	
	@Getter
	@Setter
	@Column(name="reac_year02_month08")
	private Boolean reacYear02Month08;
	
	@Getter
	@Setter
	@Column(name="reac_year02_month09")
	private Boolean reacYear02Month09;
	
	@Getter
	@Setter
	@Column(name="reac_year02_month10")
	private Boolean reacYear02Month10;
	
	@Getter
	@Setter
	@Column(name="reac_year02_month11")
	private Boolean reacYear02Month11;
	
	@Getter
	@Setter
	@Column(name="reac_year02_month12")
	private Boolean reacYear02Month12;
	
	
	@Getter
	@Setter
	@Column(name="reac_status")
	private Boolean reacStatus;
	
	@Getter
	@Setter
	@Column(name="reac_user_create")
	private String reacUserCreate;
	
	@Getter
	@Setter
	@Column(name="reac_date_create")
	private Date reacDateCreate;
	
	@Getter
	@Setter
	@Column(name="reac_user_update")
	private String reacUserUpdate;
	
	@Getter
	@Setter
	@Column(name="reac_date_update")
	private Date reacDateUpdate;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="prco_id")
	private ProposedCollection proposedCollection;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="peop_id")
	private People people;
	
	@Getter
	@Setter
	@Column(name="reac_date_start")
	private Date reacDateStart;
	
	@Getter
	@Setter
	@Column(name="reac_date_end")
	private Date reacDateEnd;
	
	@Getter
	@Setter
	@Column(name="reac_field_activity")
	private Boolean reacFieldActivity;
}