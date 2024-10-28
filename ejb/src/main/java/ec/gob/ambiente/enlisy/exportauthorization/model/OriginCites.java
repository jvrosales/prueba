package ec.gob.ambiente.enlisy.exportauthorization.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="origin_cites", schema="biodiversity")
@Where(clause = "orci_status='t'")
public class OriginCites implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORIGIN_CITES_GENERATOR")
    @SequenceGenerator(name = "ORIGIN_CITES_GENERATOR", initialValue = 1, sequenceName = "seq_orci_id", schema = "biodiversity", allocationSize = 1)
    @Column(name="orci_id")
    private Integer orciId;


    @Getter
    @Setter
    @Column(name="orci_description")
    private String orciDescription;

    @Getter
    @Setter
    @Column(name="orci_code")
    private String orciCode;

    @Getter
    @Setter
    @Column(name="orci_animal")
    private Boolean orciAnimal;

    @Getter
    @Setter
    @Column(name="orci_plantae")
    private Boolean orciPlantae;

    @Getter
    @Setter
    @Column(name="orci_fungi")
    private Boolean orciFungi;

    @Getter
    @Setter
    @Column(name="orci_eubacteria")
    private Boolean orciEubacteria;

    @Getter
    @Setter
    @Column(name="orci_archeobacteria")
    private Boolean orciArqueobacteria;

    @Getter
    @Setter
    @Column(name="orci_protista")
    private Boolean orciProtista;

    @Getter
    @Setter
    @Column(name="orci_chromista")
    private Boolean orciChromista;

    @Getter
    @Setter
    @Column(name="orci_viruses")
    private Boolean orciViruses;

    @Getter
    @Setter
    @Column(name="orci_user_create")
    private String orciUserCreate;

    @Getter
    @Setter
    @Column(name="orci_date_create")
    private Date orciDateCreate;

    @Getter
    @Setter
    @Column(name="orci_user_update")
    private String orciUserUpdate;

    @Getter
    @Setter
    @Column(name="orci_date_update")
    private Date orciDateUpdate;

    @Getter
    @Setter
    @Column(name="orci_status")
    private Boolean orciStatus;

    @Getter
    @Setter
    @Transient
    private String displayText;
}