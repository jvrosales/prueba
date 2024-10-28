package ec.gob.ambiente.enlisy.geneticresources.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the research_units database table.
 * 
 */
@Entity
@Table(name="research_units", schema="biodiversity")
@NamedQuery(name="ResearchUnits.findAll", query="SELECT o FROM ResearchUnits o")
public class ResearchUnits implements Serializable,Cloneable  {
	private static final long serialVersionUID = 1L;
	
	public ResearchUnits clone(ProposedCollection proposedCollection) throws CloneNotSupportedException {
		ResearchUnits clone= (ResearchUnits)super.clone();
		clone.setReunId(null);
		clone.setReunUserUpdate(null);
		clone.setReunDateUpdate(null);
		clone.setProposedCollection(proposedCollection);
		return clone;
	}
	
	public ResearchUnits() {		
	}
	
	public ResearchUnits(ProposedCollection proposedCollection) {		
		this.proposedCollection=proposedCollection;
	}

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="reun_id")
	private Integer reunId;
    
    @Getter
	@Setter
	@Column(name="reun_name")
	private String reunName;
	
	@Getter
	@Setter
	@Column(name="reun_administrator")
	private String reunAdministrator;
	
	@Getter
	@Setter
	@Column(name="reun_acronym")
	private String reunAcronym;
	
	@Getter
	@Setter
	@Column(name="reun_email")
	private String reunEmail;
	
	@Getter
	@Setter
	@Column(name="reun_fono")
	private String reunFono;
    
    @Getter
	@Setter
	@Column(name="reun_name_legal_representative")
	private String reunNameLegalRepresentative;
    
    @Getter
	@Setter
	@Column(name="reun_address")
	private String reunAddress;
    
    @Getter
	@Setter
	@Column(name="reun_patent_number")
	private String reunPatentNumber;
    
    @Getter
	@Setter
	@Column(name="reun_patent_date_update")
	private Date reunPatentDateUpdate;	
	
	@Getter
	@Setter
	@Column(name="reun_status")
	private Boolean reunStatus;
	
	@Getter
	@Setter
	@Column(name="reun_user_create")
	private String reunUserCreate;
	
	@Getter
	@Setter
	@Column(name="reun_date_create")
	private Date reunDateCreate;
	
	@Getter
	@Setter
	@Column(name="reun_user_update")
	private String reunUserUpdate;
	
	@Getter
	@Setter
	@Column(name="reun_date_update")
	private Date reunDateUpdate;
	
	@Getter
	@Setter
	@Column(name="reun_original_record")
	private Boolean reunOriginalRecord;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="prco_id")
	private ProposedCollection proposedCollection;

	@Getter
	@Setter
	@Transient
	private List<ResearchUnitsSamples> muestrasCollection;
}