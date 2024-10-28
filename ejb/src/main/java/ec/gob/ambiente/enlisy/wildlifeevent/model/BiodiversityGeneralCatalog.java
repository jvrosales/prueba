package ec.gob.ambiente.enlisy.wildlifeevent.model;

import javax.persistence.*;
import org.hibernate.annotations.Where;
import ec.gob.ambiente.enlisy.dao.ConvertibleEntity;
import java.util.List;


/**
 * The persistent class for the biodiversity_general_catalogs database table.
 *
 */
@Entity
@Table(name="biodiversity_general_catalogs", schema="biodiversity")
@Where(clause = "bigc_status='t'")
@NamedQuery(name="BiodiversityGeneralCatalog.findAll", query="SELECT b FROM BiodiversityGeneralCatalog b")
public class BiodiversityGeneralCatalog extends ConvertibleEntity {
    private static final long serialVersionUID = 1L;
    private Integer bigcId;
    private String bigcCode;
    private String bigcDescription;
    private Boolean bigcStatus = true;
    private String bigcValue;
    private BiodiversityCatalogType biodiversityCatalogType;
    private BiodiversityGeneralCatalog biodiversityGeneralCatalog;
    private List<BiodiversityGeneralCatalog> biodiversityGeneralCatalogs;

    @Transient
    private String displayText;

    public BiodiversityGeneralCatalog() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BIODIVERSITY_GENERAL_CATALOG_GENERATOR")
    @SequenceGenerator(name = "BIODIVERSITY_GENERAL_CATALOG_GENERATOR", initialValue = 1, sequenceName = "seq_bigc_id", schema = "biodiversity", allocationSize = 1)
    @Column(name="bigc_id")
    public Integer getBigcId() {
        return this.bigcId;
    }

    public void setBigcId(Integer bigcId) {
        this.bigcId = bigcId;
    }


    @Column(name="bigc_code")
    public String getBigcCode() {
        return this.bigcCode;
    }

    public void setBigcCode(String bigcCode) {
        this.bigcCode = bigcCode;
    }


    @Column(name="bigc_description")
    public String getBigcDescription() {
        return this.bigcDescription;
    }

    public void setBigcDescription(String bigcDescription) {
        this.bigcDescription = bigcDescription;
    }


    @Column(name="bigc_status")
    public Boolean getBigcStatus() {
        return this.bigcStatus;
    }

    public void setBigcStatus(Boolean bigcStatus) {
        this.bigcStatus = bigcStatus;
    }


    @Column(name="bigc_value")
    public String getBigcValue() {
        return this.bigcValue;
    }

    public void setBigcValue(String bigcValue) {
        this.bigcValue = bigcValue;
    }


    //bi-directional many-to-one association to BiodiversityCatalogType
    @ManyToOne
    @JoinColumn(name="bict_id")
    public BiodiversityCatalogType getBiodiversityCatalogType() {
        return this.biodiversityCatalogType;
    }

    public void setBiodiversityCatalogType(BiodiversityCatalogType biodiversityCatalogType) {
        this.biodiversityCatalogType = biodiversityCatalogType;
    }


    //bi-directional many-to-one association to BiodiversityGeneralCatalog
    @ManyToOne
    @JoinColumn(name="bigc_parent_id")
    public BiodiversityGeneralCatalog getBiodiversityGeneralCatalog() {
        return this.biodiversityGeneralCatalog;
    }

    public void setBiodiversityGeneralCatalog(BiodiversityGeneralCatalog biodiversityGeneralCatalog) {
        this.biodiversityGeneralCatalog = biodiversityGeneralCatalog;
    }


    //bi-directional many-to-one association to BiodiversityGeneralCatalog
    @OneToMany(mappedBy="biodiversityGeneralCatalog")
    public List<BiodiversityGeneralCatalog> getBiodiversityGeneralCatalogs() {
        return this.biodiversityGeneralCatalogs;
    }

    public void setBiodiversityGeneralCatalogs(List<BiodiversityGeneralCatalog> biodiversityGeneralCatalogs) {
        this.biodiversityGeneralCatalogs = biodiversityGeneralCatalogs;
    }

    public BiodiversityGeneralCatalog addBiodiversityGeneralCatalog(BiodiversityGeneralCatalog biodiversityGeneralCatalog) {
        getBiodiversityGeneralCatalogs().add(biodiversityGeneralCatalog);
        biodiversityGeneralCatalog.setBiodiversityGeneralCatalog(this);

        return biodiversityGeneralCatalog;
    }

    public BiodiversityGeneralCatalog removeBiodiversityGeneralCatalog(BiodiversityGeneralCatalog biodiversityGeneralCatalog) {
        getBiodiversityGeneralCatalogs().remove(biodiversityGeneralCatalog);
        biodiversityGeneralCatalog.setBiodiversityGeneralCatalog(null);

        return biodiversityGeneralCatalog;
    }

    @Transient
    @Override
    public long getId() {
        return getBigcId();
    }

    @Transient
    @Override
    public String getDescription() {
        return getBigcDescription();
    }

    @Transient
    public String getDisplayText() {
        return displayText;
    }

    @Transient
    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }
}