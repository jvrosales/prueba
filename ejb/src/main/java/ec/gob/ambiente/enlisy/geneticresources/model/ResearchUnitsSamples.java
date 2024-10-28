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
 * The persistent class for the research_units database table.
 * 
 */
@Entity
@Table(name="research_units_sample", schema="biodiversity")
@NamedQuery(name="ResearchUnitsSamples.findAll", query="SELECT o FROM ResearchUnitsSamples o")
public class ResearchUnitsSamples implements Serializable,Cloneable {
	private static final long serialVersionUID = 1L;
	
	public ResearchUnitsSamples clone(ResearchUnits researchUnitsCollection) throws CloneNotSupportedException {
		
		ResearchUnitsSamples clone= (ResearchUnitsSamples)super.clone();
		clone.setReusId(null);
		clone.setReusUserUpdate(null);
		clone.setReusDateUpdate(null);
		clone.setResearchUnits(researchUnitsCollection);
		return clone;
	}
	
	public ResearchUnitsSamples() {		
	}
	
	public ResearchUnitsSamples(ResearchUnits researchUnits) {		
		this.researchUnits=researchUnits;
	}

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="reus_id")
	private Integer reusId;
	
	@Getter
	@Setter
	@Column(name="reus_sample_type")
	private String reusSampleType;
	
	@Getter
	@Setter
	@Column(name="reus_sample_name")
	private String reusSampleName;
	
	@Getter
	@Setter
	@Column(name="reus_sample_number")
	private Integer reusSampleNumber;
	
	@Getter
	@Setter
	@Column(name="reus_sample_number_detail")
	private String reusSampleNumberDetail;
	
	@Getter
	@Setter
	@Column(name="reus_specie_code")
	private String reusSpecieCode;
	
	@Getter
	@Setter
	@Column(name="reus_specie_name")
	private String reusSpecieName;
	
	@Getter
	@Setter
	@Column(name="reus_specie_genus")
	private String reusSpecieGenus;
	
	@Getter
	@Setter
	@Column(name="reus_specie_family")
	private String reusSpecieFamily;
	
	@Getter
	@Setter
	@Column(name="reus_specie_order")
	private String reusSpecieOrder;
	
	@Getter
	@Setter
	@Column(name="reus_specie_class")
	private String reusSpecieClass;
	
	@Getter
	@Setter
	@Column(name="reus_specie_phylum")
	private String reusSpeciePhylum;
	
	@Getter
	@Setter
	@Column(name="reus_specie_kingdom")
	private String reusSpecieKingdom;
	
	
	@Getter
	@Setter
	@Column(name="reus_type")
	private Boolean reusType;
	
	@Getter
	@Setter
	@Column(name="reus_status")
	private Boolean reusStatus;
	
	@Getter
	@Setter
	@Column(name="reus_user_create")
	private String reusUserCreate;
	
	@Getter
	@Setter
	@Column(name="reus_date_create")
	private Date reusDateCreate;
	
	@Getter
	@Setter
	@Column(name="reus_user_update")
	private String reusUserUpdate;
	
	@Getter
	@Setter
	@Column(name="reus_date_update")
	private Date reusDateUpdate;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="reun_id")
	private ResearchUnits researchUnits;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="grca_id_taxonomy")
	private GeneticResourcesCatalog grcaIdTaxonomy;
}