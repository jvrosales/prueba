package ec.gob.ambiente.enlisy.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the general_catalogs database table.
 * 
 */
@Entity
@Table(name="general_catalogs", schema="public")
@NamedQuery(name="GeneralCatalog.findAll", query="SELECT g FROM GeneralCatalog g")
public class GeneralCatalog implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer gecaId;
	private Integer catyId;
	private String gecaCode;
	private String gecaDescription;
	private Boolean gecaStatus;
	private String gecaValue;
	private Integer sectorId;
	private GeneralCatalog generalCatalog;
	private List<GeneralCatalog> generalCatalogs;

	public GeneralCatalog() {
	}


	@Id
	@SequenceGenerator(name="GENERAL_CATALOGS_GECAID_GENERATOR", sequenceName="SEQ_GECA_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GENERAL_CATALOGS_GECAID_GENERATOR")
	@Column(name="geca_id")
	public Integer getGecaId() {
		return this.gecaId;
	}

	public void setGecaId(Integer gecaId) {
		this.gecaId = gecaId;
	}


	@Column(name="caty_id")
	public Integer getCatyId() {
		return this.catyId;
	}

	public void setCatyId(Integer catyId) {
		this.catyId = catyId;
	}


	@Column(name="geca_code")
	public String getGecaCode() {
		return this.gecaCode;
	}

	public void setGecaCode(String gecaCode) {
		this.gecaCode = gecaCode;
	}


	@Column(name="geca_description")
	public String getGecaDescription() {
		return this.gecaDescription;
	}

	public void setGecaDescription(String gecaDescription) {
		this.gecaDescription = gecaDescription;
	}


	@Column(name="geca_status")
	public Boolean getGecaStatus() {
		return this.gecaStatus;
	}

	public void setGecaStatus(Boolean gecaStatus) {
		this.gecaStatus = gecaStatus;
	}


	@Column(name="geca_value")
	public String getGecaValue() {
		return this.gecaValue;
	}

	public void setGecaValue(String gecaValue) {
		this.gecaValue = gecaValue;
	}


	@Column(name="sector_id")
	public Integer getSectorId() {
		return this.sectorId;
	}

	public void setSectorId(Integer sectorId) {
		this.sectorId = sectorId;
	}


	//bi-directional many-to-one association to GeneralCatalog
	@ManyToOne
	@JoinColumn(name="geca_parent_id")
	public GeneralCatalog getGeneralCatalog() {
		return this.generalCatalog;
	}

	public void setGeneralCatalog(GeneralCatalog generalCatalog) {
		this.generalCatalog = generalCatalog;
	}


	//bi-directional many-to-one association to GeneralCatalog
	@OneToMany(mappedBy="generalCatalog")
	public List<GeneralCatalog> getGeneralCatalogs() {
		return this.generalCatalogs;
	}

	public void setGeneralCatalogs(List<GeneralCatalog> generalCatalogs) {
		this.generalCatalogs = generalCatalogs;
	}

	public GeneralCatalog addGeneralCatalog(GeneralCatalog generalCatalog) {
		getGeneralCatalogs().add(generalCatalog);
		generalCatalog.setGeneralCatalog(this);

		return generalCatalog;
	}

	public GeneralCatalog removeGeneralCatalog(GeneralCatalog generalCatalog) {
		getGeneralCatalogs().remove(generalCatalog);
		generalCatalog.setGeneralCatalog(null);

		return generalCatalog;
	}

}