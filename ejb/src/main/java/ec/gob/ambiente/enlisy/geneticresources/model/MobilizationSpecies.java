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
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the mobilization_species database table.
 * 
 */
@Entity
@Table(name="mobilization_species", schema="biodiversity")
public class MobilizationSpecies implements Serializable {
	private static final long serialVersionUID = 1L;	

	public MobilizationSpecies() {		
	}
	
	public MobilizationSpecies(MobilizationGuide mobilizationGuide) {
		this.mobilizationGuide=mobilizationGuide;
	}

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="mosp_id")
	private Integer mospId;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="mogu_id")
	private MobilizationGuide mobilizationGuide;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="prcs_id")
	private ProposedCollectionSpecies proposedCollectionSpecies;

	@Getter
	@Setter	
	@Column(name="mosp_biological_groups")
	private String mospBiologicalGroups;
	
	@Getter
	@Setter	
	@Column(name="mosp_sample_type")
	private String mospSampleType;
	
	@Getter
	@Setter	
	@Column(name="mosp_sample_number")
	private Integer mospSampleNumber;

	@Getter
	@Setter
	@Column(name="mosp_sample_batch")
	private Integer mospSampleBatch;
		
	@Getter
	@Setter
	@Column(name="mosp_status")
	private Boolean mospStatus;
	
	@Getter
	@Setter
	@Column(name="mosp_user_create")
	private String mospUserCreate;
	
	@Getter
	@Setter
	@Column(name="mosp_date_create")
	private Date mospDateCreate;
	
	@Getter
	@Setter
	@Column(name="mosp_user_update")
	private String mospUserUpdate;
	
	@Getter
	@Setter
	@Column(name="mosp_date_update")
	private Date mospDateUpdate;
	
	@Getter	
	@Transient
	private Boolean batch;

	@Getter
	@Setter
	@Transient
	private String clasificacion;
	
	public void setBatch(Boolean batch)
	{
		this.batch=batch;
		
		if(batch==null)
		{
			mospSampleBatch=null;
			mospSampleNumber=null;
		}else if(batch)
		{
			mospSampleNumber=null;
		}		
		else{
			mospSampleBatch=null;
		}
			
	}
	
	public String getName()
	{
		
		if(mospBiologicalGroups!=null && !mospBiologicalGroups.isEmpty())
			return mospBiologicalGroups;
		
		if(proposedCollectionSpecies!=null)
		{
			if(clasificacion != null)
				return clasificacion; 				
			String result=proposedCollectionSpecies.getPrcsSpecieKingdom();
			result+= proposedCollectionSpecies.getPrcsSpecieClass()==null?"":"-"+proposedCollectionSpecies.getPrcsSpecieClass();
			result+= proposedCollectionSpecies.getPrcsSpecieOrder()==null?"":"-"+proposedCollectionSpecies.getPrcsSpecieOrder();
			result+= proposedCollectionSpecies.getPrcsSpecieFamily()==null?"":"-"+proposedCollectionSpecies.getPrcsSpecieFamily();
			result+= proposedCollectionSpecies.getPrcsSpecieGenus()==null?"":"-"+proposedCollectionSpecies.getPrcsSpecieGenus();
			result+= proposedCollectionSpecies.getPrcsSpecieName()==null?"":"-"+proposedCollectionSpecies.getPrcsSpecieName();
			return result;
		}				
		return null;
	}
}