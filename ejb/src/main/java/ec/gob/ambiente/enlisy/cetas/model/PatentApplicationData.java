package ec.gob.ambiente.enlisy.cetas.model;

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
 * The persistent class for the patent_application_data database table.
 * 
 */
@Entity
@Table(name="patent_application_data", schema="cetas")
@NamedQuery(name="PatentApplicationData.findAll", query="SELECT o FROM PatentApplicationData o where o.paadStatus = true")
public class PatentApplicationData implements Serializable,Cloneable {
	private static final long serialVersionUID = 1L;
		
	public PatentApplicationData clone(PatentApplication patentApplication) throws CloneNotSupportedException {
		
		PatentApplicationData clone= (PatentApplicationData)super.clone();
		clone.setPaadId(null);
		clone.setPaadUserUpdate(null);
		clone.setPaadDateUpdate(null);
		clone.setPatentApplication(patentApplication);
		return clone;
	}
		

	public PatentApplicationData() {		
	}
	
	public PatentApplicationData(CatalogWmc catalogWmc,PatentApplication patentApplication) {
		this.typeInformation=catalogWmc;
		this.patentApplication=patentApplication;
	}

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="paad_id")
	private Integer paadId;
	
	@Getter
	@Setter
	@Column(name="paad_value")
	private String paadValue;	
	
	@Getter
	@Setter
	@Column(name="paad_status")
	private Boolean paadStatus;
	
	@Getter
	@Setter
	@Column(name="paad_user_create")
	private String paadUserCreate;
	
	@Getter
	@Setter
	@Column(name="paad_date_create")
	private Date paadDateCreate;
	
	@Getter
	@Setter
	@Column(name="paad_user_update")
	private String paadUserUpdate;
	
	@Getter
	@Setter
	@Column(name="paad_date_update")
	private Date paadDateUpdate;

	//bi-directional many-to-one association to PatentApplication
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="paap_id")
	private PatentApplication patentApplication;
	
	//bi-directional many-to-one association to Catalog
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="cwmc_id_type_information")
	private CatalogWmc typeInformation;
	
	
}