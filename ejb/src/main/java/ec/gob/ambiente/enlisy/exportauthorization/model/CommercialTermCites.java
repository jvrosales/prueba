package ec.gob.ambiente.enlisy.exportauthorization.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="commercial_terms_cites", schema="biodiversity")
@Where(clause = "cotc_status='t'")
public class CommercialTermCites  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMERCIAL_TERMS_CITES_GENERATOR")
    @SequenceGenerator(name = "COMMERCIAL_TERMS_CITES_GENERATOR", initialValue = 1, sequenceName = "seq_cotc_id", schema = "biodiversity", allocationSize = 1)
    @Column(name="cotc_id")
    private Integer cotcId;


    @Getter
    @Setter
    @Column(name="cotc_description")
    private String cotciDescription;

    @Getter
    @Setter
    @Column(name="cotc_code")
    private String cotcCode;

    @Getter
    @Setter
    @Column(name="cotc_animal")
    private Boolean cotcAnimal;

    @Getter
    @Setter
    @Column(name="cotc_plantae")
    private Boolean cotcPlantae;

    @Getter
    @Setter
    @Column(name="cotc_fungi")
    private Boolean cotcFungi;

    @Getter
    @Setter
    @Column(name="cotc_eubacteria")
    private Boolean cotcEubacteria;

    @Getter
    @Setter
    @Column(name="cotc_archeobacteria")
    private Boolean cotcArqueobacteria;

    @Getter
    @Setter
    @Column(name="cotc_protista")
    private Boolean cotcProtista;

    @Getter
    @Setter
    @Column(name="cotc_chromista")
    private Boolean cotcChromista;

    @Getter
    @Setter
    @Column(name="cotc_viruses")
    private Boolean cotcViruses;

    @Getter
    @Setter
    @Column(name="cotc_user_create")
    private String cotcUserCreate;

    @Getter
    @Setter
    @Column(name="cotc_date_create")
    private Date cotcDateCreate;

    @Getter
    @Setter
    @Column(name="cotc_user_update")
    private String cotcUserUpdate;

    @Getter
    @Setter
    @Column(name="cotc_date_update")
    private Date cotcDateUpdate;

    @Getter
    @Setter
    @Column(name="cotc_status")
    private Boolean cotcStatus;

    @Getter
    @Setter
    @Transient
    private String displayText;
}