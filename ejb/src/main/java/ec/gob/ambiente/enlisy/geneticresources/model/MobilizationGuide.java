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

import lombok.Getter;
import lombok.Setter;
import ec.gob.ambiente.enlisy.model.GeographicalLocation;


/**
 * The persistent class for the mobilization_guide database table.
 * 
 */
@Entity
@Table(name="mobilization_guide", schema="biodiversity")
public class MobilizationGuide implements Serializable {
	private static final long serialVersionUID = 1L;		

	public MobilizationGuide() {
		moguDateCreate=new Date();
		moguFinalized=false;
	}

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="mogu_id")
	private Integer moguId;
	
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="prco_id")
	private ProposedCollection proposedCollection;
	
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="grca_id_type_transport")
	private GeneticResourcesCatalog typeTransport;
	
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="gelo_id_origin")
	private GeographicalLocation geloOrigin;
		
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="gelo_id_destination")
	private GeographicalLocation geloDestination;	
	
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="pchc_id")
	private ProposedCollectionHandlingCenter handlingCenter;	
	
	@Getter
	@Setter
	@Column(name="mogu_date_expiration")
	private Date moguDateExpiration;
	
	@Getter
	@Setter
	@Column(name="mogu_finalized")
	private Boolean moguFinalized;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="docu_id")
	private ProposedDocument proposedDocument;
	
	@Getter
	@Setter
	@Column(name="mogu_status")
	private Boolean moguStatus;
	
	@Getter
	@Setter
	@Column(name="mogu_user_create")
	private String moguUserCreate;
	
	@Getter
	@Setter
	@Column(name="mogu_date_create")
	private Date moguDateCreate;
	
	@Getter
	@Setter
	@Column(name="mogu_user_update")
	private String moguUserUpdate;
	
	@Getter
	@Setter
	@Column(name="mogu_date_update")
	private Date moguDateUpdate;	
	
	
	public Date getDateStart(){
		return moguDateUpdate!=null?moguDateUpdate:moguDateCreate;
	}
	  
	
	
}