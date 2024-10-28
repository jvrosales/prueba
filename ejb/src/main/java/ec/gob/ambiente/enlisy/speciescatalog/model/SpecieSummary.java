package ec.gob.ambiente.enlisy.speciescatalog.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the species_summaries database table.
 * 
 */
@Entity
@Table(name="species_summaries", schema="biodiversity")
public class SpecieSummary implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SPECIE_SUMMARY_GENERATOR")
    @SequenceGenerator(name = "SPECIE_SUMMARY_GENERATOR", initialValue = 1, sequenceName = "seq_spsu_id", schema = "biodiversity", allocationSize = 1)
    @Column(name="spsu_id")
	private Integer spsuId;
	
	/**
	 * Texto de descripción de la especie
	 */
	@Getter
	@Setter
	@Column(name="spsu_description")
	private String spsuDescription;
	
	/**
	 * Ecologia de la especie
	 */
	@Getter
	@Setter
	@Column(name="spsu_ecology")
	private String spsuEcology;
	
	
	
	/**
	 * Usos de la especie
	 */
	@Getter
	@Setter
	@Column(name="spsu_use")
	private String spsuUse;
	
	/**
	 * Rango altitudinal de la especie
	 */
	@Getter
	@Setter
	@Column(name="spsu_altitudinal_rank")
	private String spsuAltitudinalRank;
	
	/**
	 * Distribucion de la especie
	 */
	@Getter
	@Setter
	@Column(name="spsu_distribution")
	private String spsuDistribution;
	
	/**
	 * Autor del sumario
	 */
	@Getter
	@Setter
	@Column(name="spsu_author")
	private String spsuAuthor;
	
	/**
	 * Revisor del sumario
	 */
	@Getter
	@Setter
	@Column(name="spsu_reviser")
	private String spsuReviser;
	
	/**
	 * Editor del sumario
	 */
	@Getter
	@Setter
	@Column(name="spsu_editor")
	private String spsuEditor;
	
	/**
	 * Colaborador del sumario
	 */
	@Getter
	@Setter
	@Column(name="spsu_collaborator")
	private String spsuCollaborator;
	
	/**
	 * Bandera que indica si se publica el sumario
	 */
	@Getter
	@Setter
	@Column(name="spsu_publish")
	private Boolean spsuPublish;
	
	
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="spsu_user_create")
	private String spsuUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="spsu_date_create")
	private Date spsuDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="spsu_user_update")
	private String spsuUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="spsu_date_update")
	private Date spsuDateUpdate;
	
	/**
	 * Bandera que indica si el registro esta activo
	 */
	@Getter
	@Setter
	@Column(name="spsu_status")
	private Boolean spsuStatus;
	
		
	/**
	 * id especie 
	 */
	@Getter
	@Setter
	@OneToOne()
    @JoinColumn(name = "spta_id")
	private SpecieTaxa specie;
	
	
	/**
	 * Medidas de conservacion
	 * campo nuevo
	 */
	@Getter
	@Setter
	@Column(name="spsu_conservation_measure")
	private String spsuConservationMeasure;
	
	/**
	 * Anio de publicacion
	 * campo nuevo
	 */
	@Getter
	@Setter
	@Column(name="spsu_publication_year")
	private Integer spsuPublicationYear;
	
	/**
	 * Especies similares
	 */
	@Getter
	@Setter
	@Column(name="spsu_similar_species")
	private String spsuSimilarSpecie;
	
	
}
