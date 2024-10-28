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
 * The persistent class for the species_forestal_summaries database table.
 * 
 */
@Entity
@Table(name="species_forestal_summaries", schema="biodiversity")
public class SpecieForestalSummary implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SPECIE_FORESTAL_SUMMARY_GENERATOR")
    @SequenceGenerator(name = "SPECIE_FORESTAL_SUMMARY_GENERATOR", initialValue = 1, sequenceName = "seq_spfs_id", schema = "biodiversity", allocationSize = 1)
    @Column(name="spfs_id")
	private Integer spfsId;
	
	/**
	 * Descripcion botanica
	 */
	@Getter
	@Setter
	@Column(name="spfs_botanical_description")
	private String spfsBotanicalDescription;
	
	/**
	 * Especies similares
	 */
	@Getter
	@Setter
	@Column(name="spfs_similar_species")
	private String spfsSimilarSpecies;
	
	
	
	/**
	 * Ecologia
	 */
	@Getter
	@Setter
	@Column(name="spfs_ecology")
	private String spfsEcology;
	
	/**
	 * Usos
	 */
	@Getter
	@Setter
	@Column(name="spfs_use")
	private String spfsUse;
	
	/**
	 * SAF volumen aprovechado
	 */
	@Getter
	@Setter
	@Column(name="spfs_used_volume")
	private String spfsUsedVolume;
	
	/**
	 * Colecciones botanicas
	 * 
	 */
	@Getter
	@Setter
	@Column(name="spfs_botanical_collection")
	private String spfsBotanicalCollection;
	
	/**
	 * Dureza o peso
	 */
	@Getter
	@Setter
	@Column(name="spfs_hardness_weight")
	private String spfsHardnessWeight;
	
	/**
	 * color: Albura/Duramen
	 */
	@Getter
	@Setter
	@Column(name="spfs_color_transversal")
	private String spfsColorTransversal;
	
	/**
	 *Olor
	 */
	@Getter
	@Setter
	@Column(name="spfs_odor_transversal")
	private String spfsOdorTransversal;
	
	/**
	 * Sabor
	 */
	@Getter
	@Setter
	@Column(name="spfs_taste_transversal")
	private String spfsTasteTransversal;
	
	/**
	 * Latex resina
	 */
	@Getter
	@Setter
	@Column(name="spfs_latex_resin_transversal")
	private String spfsLatexResinTransversal;
	
	/**
	 * Corteza externa
	 */
	@Getter
	@Setter
	@Column(name="spfs_outer_cortex_transversal")
	private String spfsOuterCortexTransversal;
	
	
	/**
	 * Color corte tangencial
	 */
	@Getter
	@Setter
	@Column(name="spfs_color_tangential")
	private String spfsColorTangential;
	
	/**
	 * veteado corte tangencial
	 */
	@Getter
	@Setter
	@Column(name="spfs_veined_tangential")
	private String spfsVeinedTangential;
	
	/**
	 * Grano corte tangencial
	 */
	@Getter
	@Setter
	@Column(name="spfs_grain_tangential")
	private String spfsGrainTangential;
	
	/**
	 * Textura corte tangencial
	 */
	@Getter
	@Setter
	@Column(name="spfs_texture_tangential")
	private String spfsTextureTangential;
	
	/**
	 * veteado corte radial
	 */
	@Getter
	@Setter
	@Column(name="spfs_veined_radial")
	private String spfsVeinedRadial;
	
	/**
	 * Lustre brillo corte radial
	 */
	@Getter
	@Setter
	@Column(name="spfs_gloss_shine_radial")
	private String spfsGlossShineRadial;
	
	/**
	 * Anillos de crecimiento
	 */
	@Getter
	@Setter
	@Column(name="spfs_growth_rings")
	private String spfsGrowthRings;
	
	/**
	 * Poros corte transversal
	 */
	@Getter
	@Setter
	@Column(name="spfs_pores_transversal")
	private String spfsPoresTransversal;
	
	/**
	 * Parenquima axial corte transversal
	 */
	@Getter
	@Setter
	@Column(name="spfs_axial_parenchyma_transversal")
	private String spfsAxialParenchymaTransversal;
	
	/**
	 * Parenquima radial corte transversal
	 */
	@Getter
	@Setter
	@Column(name="spfs_radial_parenchyma_transversal")
	private String spfsRadialParenchymaTransversal;
	
	/**
	 * Densidad verde
	 */
	@Getter
	@Setter
	@Column(name="spfs_density_green")
	private Double spfsDensityGreen;
	
	/**
	 * Seca al aire
	 */
	@Getter
	@Setter
	@Column(name="spfs_density_air_dry")
	private Double spfsDensityAirDry;
	
	/**
	 * Anhidra
	 */
	@Getter
	@Setter
	@Column(name="spfs_density_anhydrous")
	private Double spfsDensityAnhydrous;
	
	/**
	 * Densidad basica
	 */
	@Getter
	@Setter
	@Column(name="spfs_density_basic")
	private Double spfsDensityBasic;
	
	/**
	 * Radia contraccion normal
	 */
	@Getter
	@Setter
	@Column(name="spfs_contraction_radia")
	private Double spfsContractionRadia;
	
	/**
	 * Contraccion normal tangencial
	 */
	@Getter
	@Setter
	@Column(name="spfs_contraction_tangential")
	private Double spfsContractionTangential;
	
	/**
	 * Contraccion normal columetrica
	 */
	@Getter
	@Setter
	@Column(name="spfs_contraction_columetrica")
	private Double spfsContractionColumetrica;
	
	/**
	 * Contraccion total radia
	 */
	@Getter
	@Setter
	@Column(name="spfs_contraction_total_radia")
	private Double spfsContractionTotalRadia;
	
	
	/**
	 * Contraccion total tangencial
	 */
	@Getter
	@Setter
	@Column(name="spfs_contraction_total_tangential")
	private Double spfsContractionTotalTangential;
	
	/**
	 * Contraccion total volumetrica
	 */
	@Getter
	@Setter
	@Column(name="spfs_contraction_total_volumetric")
	private Double spfsContractionTotalVolumetric;
	
	/**
	 *Relacion T/R
	 */
	@Getter
	@Setter
	@Column(name="spfs_contraction_total_relationship_tr")
	private Double spfsContractionTotalRelationshipTr;
	
	
	/**
	 * id especie 
	 */
	@Getter
	@Setter
	@OneToOne()
    @JoinColumn(name = "spta_id")
	private SpecieTaxa specie;
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="spfs_user_create")
	private String spfsUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="spfs_date_create")
	private Date spfsDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="spfs_user_update")
	private String spfsUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="spfs_date_update")
	private Date spfsDateUpdate;
	
	/**
	 * Bandera que indica si el registro esta activo
	 */
	@Getter
	@Setter
	@Column(name="spfs_status")
	private Boolean spfsStatus;
	
	/**
	 * Aprovechamiento condicionado
	 */
	@Getter
	@Setter
	@Column(name="spfs_conditioned_use")
	private Boolean spfsConditionedUse;
	
	
	/**
	 * Autor del sumario
	 */
	@Getter
	@Setter
	@Column(name="spfs_author")
	private String spfsAuthor;
	
	/**
	 * Revisor del sumario
	 */
	@Getter
	@Setter
	@Column(name="spfs_reviser")
	private String spfsReviser;
	
	/**
	 * Editor del sumario
	 */
	@Getter
	@Setter
	@Column(name="spfs_editor")
	private String spfsEditor;
	
	
	
	/**
	 * Colaborador del sumario
	 */
	@Getter
	@Setter
	@Column(name="spfs_collaborator")
	private String spfsCollaborator;
	
	/**
	 * Bandera que indica si se publica la ficha
	 */
	@Getter
	@Setter
	@Column(name="spfs_publish")
	private Boolean spfsPublish;
	
	/**
	 * Bandera que indica si se publica la ficha
	 */
	@Getter
	@Setter
	@Column(name="spfs_publication_year")
	private Integer spfsPublicationYear;
	
	/**
	 * id especie 
	 */
	@Getter
	@Setter
	@OneToOne()
    @JoinColumn(name = "spsu_id")
	private SpecieSummary summary;
	
	
	
}
