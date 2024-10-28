package ec.gob.ambiente.enlisy.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="country_codes", schema="biodiversity")
@NamedQuery(name="CountryCodes.findAll", query="SELECT e FROM CountryCodes e")
public class CountryCodes implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer cncodId;
    private String cncodIsoCode;
    private String cncodCountryName;
    private  Integer geloId;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="cncod_id")
    public Integer getCncodId() {
        return cncodId;
    }

    public void setCncodId(Integer cncodId) {
        this.cncodId = cncodId;
    }

    @Column(name="cncod_iso_code")
    public String getCncodIsoCode() {
        return cncodIsoCode;
    }

    public void setCncodIsoCode(String cncodIsoCode) {
        this.cncodIsoCode = cncodIsoCode;
    }

    @Column(name="cncod_country_name")
    public String getCncodCountryName() {
        return cncodCountryName;
    }

    public void setCncodCountryName(String cncodCountryName) {
        this.cncodCountryName = cncodCountryName;
    }

    @Column(name="gelo_id")
    public Integer getGeloId() {
        return geloId;
    }

    public void setGeloId(Integer geloId) {
        this.geloId = geloId;
    }

}