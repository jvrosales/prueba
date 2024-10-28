package ec.gob.ambiente.enlisy.exportauthorization.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="tariff_subheading", schema="biodiversity")
@Where(clause = "tars_status='t'")
public class TariffSubheading implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TARIFF_SUBHEADING_GENERATOR")
    @SequenceGenerator(name = "TARIFF_SUBHEADING_GENERATOR", initialValue = 1, sequenceName = "seq_tars_id", schema = "biodiversity", allocationSize = 1)
    @Column(name="tars_id")
    private Integer tarsId;

    @Getter
    @Setter
    @Column(name="tars_description")
    private String tarsDescription;

    @Getter
    @Setter
    @Column(name="tars_code")
    private String tarsCode;

    @Getter
    @Setter
    @Column(name="tars_user_create")
    private String tarsUserCreate;

    @Getter
    @Setter
    @Column(name="tars_date_create")
    private Date tarsDateCreate;

    @Getter
    @Setter
    @Column(name="tars_user_update")
    private String tarsUserUpdate;

    @Getter
    @Setter
    @Column(name="tars_date_update")
    private Date tarsDateUpdate;

    @Getter
    @Setter
    @Column(name="tars_status")
    private Boolean tarsStatus;

    @Getter
    @Setter
    @Column(name="tars_complementary_code")
    private String tarsComplementaryCode;

    @Getter
    @Setter
    @Column(name="tars_supplementary_code")
    private String tarsSupplementaryCode;

    @Getter
    @Setter
    @Transient
    private String text;

    @Getter
    @Setter
    @Transient
    private String error;
}