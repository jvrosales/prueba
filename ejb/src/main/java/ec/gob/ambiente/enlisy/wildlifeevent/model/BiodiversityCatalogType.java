package ec.gob.ambiente.enlisy.wildlifeevent.model;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Where;

import java.util.List;


/**
 * The persistent class for the biodiversity_catalog_types database table.
 * 
 */
@Entity
@Table(name="biodiversity_catalog_types", schema="biodiversity")
@NamedQuery(name="BiodiversityCatalogType.findAll", query="SELECT b FROM BiodiversityCatalogType b")
public class BiodiversityCatalogType implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer bictId;
	private String bictCode;
	private Boolean bictStatus;
	private String bictType;
	private List<BiodiversityGeneralCatalog> biodiversityGeneralCatalogs;

	public BiodiversityCatalogType() {
	}


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BIODIVERSITY_CATALOG_TYPE_GENERATOR")
	@SequenceGenerator(name = "BIODIVERSITY_CATALOG_TYPE_GENERATOR", initialValue = 1, sequenceName = "seq_bict_id", schema = "biodiversity", allocationSize = 1)
	@Column(name="bict_id")
	public Integer getBictId() {
		return this.bictId;
	}

	public void setBictId(Integer bictId) {
		this.bictId = bictId;
	}


	@Column(name="bict_code")
	public String getBictCode() {
		return this.bictCode;
	}

	public void setBictCode(String bictCode) {
		this.bictCode = bictCode;
	}


	@Column(name="bict_status")
	public Boolean getBictStatus() {
		return this.bictStatus;
	}

	public void setBictStatus(Boolean bictStatus) {
		this.bictStatus = bictStatus;
	}


	@Column(name="bict_type")
	public String getBictType() {
		return this.bictType;
	}

	public void setBictType(String bictType) {
		this.bictType = bictType;
	}


	//bi-directional many-to-one association to BiodiversityGeneralCatalog
	@OneToMany(mappedBy="biodiversityCatalogType", fetch=FetchType.EAGER)
	@Where(clause = "bigc_status='t'")
	@OrderBy(value = "bigc_id")
	public List<BiodiversityGeneralCatalog> getBiodiversityGeneralCatalogs() {
		return this.biodiversityGeneralCatalogs;
	}

	public void setBiodiversityGeneralCatalogs(List<BiodiversityGeneralCatalog> biodiversityGeneralCatalogs) {
		this.biodiversityGeneralCatalogs = biodiversityGeneralCatalogs;
	}

	public BiodiversityGeneralCatalog addBiodiversityGeneralCatalog(BiodiversityGeneralCatalog biodiversityGeneralCatalog) {
		getBiodiversityGeneralCatalogs().add(biodiversityGeneralCatalog);
		biodiversityGeneralCatalog.setBiodiversityCatalogType(this);

		return biodiversityGeneralCatalog;
	}

	public BiodiversityGeneralCatalog removeBiodiversityGeneralCatalog(BiodiversityGeneralCatalog biodiversityGeneralCatalog) {
		getBiodiversityGeneralCatalogs().remove(biodiversityGeneralCatalog);
		biodiversityGeneralCatalog.setBiodiversityCatalogType(null);

		return biodiversityGeneralCatalog;
	}

}