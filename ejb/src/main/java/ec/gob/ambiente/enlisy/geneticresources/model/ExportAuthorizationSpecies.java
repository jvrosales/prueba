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
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the export_authorization database table.
 * 
 */
@Entity
@Table(name = "export_authorization_species", schema = "biodiversity")
@NamedQuery(name = "ExportAuthorizationSpecies.findAll", query = "SELECT o FROM ExportAuthorizationSpecies o where o.exasStatus = true")
public class ExportAuthorizationSpecies implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	@Override
	public ExportAuthorizationSpecies clone() throws CloneNotSupportedException {

		ExportAuthorizationSpecies clone = (ExportAuthorizationSpecies) super.clone();
		clone.setExasId(null);
		clone.setExasUserUpdate(null);
		clone.setExasDateUpdate(null);
		return clone;
	}
	
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "exas_id")
	private Integer exasId;
	
	@Getter
	@Setter	
	@Column(name="exas_sample_type")
	private String exasSampleType;
	
	@Getter
	@Setter	
	@Column(name="exas_other_sample_type")
	private String exasOtherSampleType;
	
	@Getter
	@Setter
	@Column(name = "exas_sample_number")
	private Integer exasSampleNumber;
	
	@Getter
	@Setter
	@Column(name="exas_sample_batch")
	private Integer exasSampleBatch;
	
	@Getter
	@Setter
	@Column(name="exas_total_quota")
	private Integer exasTotalQuota;
	
	@Getter
	@Setter
	@Column(name="exas_sample_code")
	private String exasSampleCode;
	
	@Getter
	@Setter
	@Column(name="exas_sample_sequence")
	private Integer exasSampleSequence;
	
	@Getter
	@Setter
	@Column(name = "exas_is_historical")
	private Boolean exasIsHistorical;
	
	@Getter
	@Setter
	@Column(name="exas_status")
	private Boolean exasStatus;
	
	@Getter
	@Setter
	@Column(name="exas_user_create")
	private String exasUserCreate;
	
	@Getter
	@Setter
	@Column(name="exas_date_create")
	private Date exasDateCreate;
	
	@Getter
	@Setter
	@Column(name="exas_user_update")
	private String exasUserUpdate;
	
	@Getter
	@Setter
	@Column(name="exas_date_update")
	private Date exasDateUpdate;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="exau_id")
	private ExportAuthorization exportAuthorization;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="prcs_id")
	private ProposedCollectionSpecies proposedCollectionSpecies;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="unsp_id")
	private UnidentifiedSpecies unidentifiedSpecies;
	
	@Getter
	@Setter
	@Transient
	private Integer esOtroTipoMuestra = 0;
	
	@Getter
	@Setter
	@Transient
	private Boolean sampleTypeModified = false;
	
	@Getter
	@Setter
	@Transient
	private Boolean sampleNumberModified = false;
	
	@Getter
	@Setter
	@Transient
	private Boolean sampleBatchModified = false;
	
	@Getter
	@Setter
	@Transient
	private Boolean totalQuotaModified = false;
	
	@Getter
	@Setter
	@Transient
	private Boolean otherSampleTypeModified = false;
	
	@Getter
	@Setter
	@Transient
	private Boolean specieOrderModified = false;
	
	@Getter
	@Setter
	@Transient
	private Boolean specieGenusModified = false;
	
	@Getter
	@Setter
	@Transient
	private Boolean specieNameModified = false;
}
