package ec.gob.ambiente.enlisy.exportauthorization.model;

import ec.gob.ambiente.enlisy.speciescatalog.model.SpecieTaxa;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "species_taxa_subheading", schema = "biodiversity")
public class SpecieTaxaSubheading implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2473607444231904949L;

	@Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SPECIE_TAXA_SUBHEADING_GENERATOR")
    @SequenceGenerator(name = "SPECIE_TAXA_SUBHEADING_GENERATOR", initialValue = 1, sequenceName = "seq_stas_id", schema = "biodiversity", allocationSize = 1)
    @Column(name="stas_id")
    private Integer stasId;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name="tars_id")
    private TariffSubheading tariffSubheading;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name="spta_id")
    private SpecieTaxa specieTaxa;

    @Getter
    @Setter
    @Column(name="cotc_id")
    private Integer cotcId;

    @Getter
    @Setter
    @Column(name="stas_comm_term_code")
    private String stasCommTermCode;

    @Getter
    @Setter
    @Column(name="stas_status")
    private Boolean stasStatus;

    @Getter
    @Setter
    @Column(name="stas_user_create")
    private String stasUserCreate;

    @Getter
    @Setter
    @Column(name="stas_date_create")
    private Date stasDateCreate;

    @Getter
    @Setter
    @Column(name="stas_user_update")
    private String stasUserUpdate;

    @Getter
    @Setter
    @Column(name="stas_date_update")
    private Date stasDateUpdate;
}