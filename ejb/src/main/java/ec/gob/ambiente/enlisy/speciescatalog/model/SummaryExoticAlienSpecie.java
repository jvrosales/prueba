package ec.gob.ambiente.enlisy.speciescatalog.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the summaries_exotic_alien_species database table.
 * 
 */
@Entity
@Table(name="summaries_exotic_alien_species", schema="biodiversity")
public class SummaryExoticAlienSpecie implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUMMARY_EXAL_SPEC_GENERATOR")
    @SequenceGenerator(name = "SUMMARY_EXAL_SPEC_GENERATOR", initialValue = 1, sequenceName = "seq_seas_id", schema = "biodiversity", allocationSize = 1)
    @Column(name="seas_id")
	private Integer seasId;
	
	/**
	 * Impacto ecologico
	 */
	@Getter
	@Setter
	@Column(name="seas_ecological_impact")
	private String seasEcologicalImpact;
	
	/**
	 * Impacto economico
	 */
	@Getter
	@Setter
	@Column(name="seas_economic_impact")
	private String seasEconomicImpact;
	
	/**
	 * Impacto social
	 */
	@Getter
	@Setter
	@Column(name="seas_social_impact ")
	private String seasSocialImpact ;
	
	/**
	 * Impacto en salud
	 */
	@Getter
	@Setter
	@Column(name="seas_health_impact ")
	private String seasHealthImpact ;
	
	
	/**
	 * Control fisico
	 */
	@Getter
	@Setter
	@Column(name="seas_physical_control ")
	private String seasPhysicalControl ;
	
	/**
	 * Control quimico
	 */
	@Getter
	@Setter
	@Column(name="seas_chemical_control ")
	private String seasChemicalControl ;
	
	/**
	 * Control biológico
	 */
	@Getter
	@Setter
	@Column(name="seas_biologic_control ")
	private String seasBiologiControl ;
	
	/**
	 * Medidas de prevencion
	 */
	@Getter
	@Setter
	@Column(name="seas_prevention_measure ")
	private String seasPreventionMeasure ;
	
	/**
	 * Analisis de riesgos
	 */
	@Getter
	@Setter
	@Column(name="seas_risk_analysis")
	private String seasRiskAnalysis;
	
	/**
	 * Descripción morfologica de la especie
	 */
	@Getter
	@Setter
	@Column(name="seas_morphological_description")
	private String seasMorphologicalDescription;
	
	/**
	 * Area Distributiva nativa
	 */
	@Getter
	@Setter
	@Column(name="seas_native_distributive_area")
	private String seasNativeDistributiveArea;
	
	/**
	 * Autor del sumario
	 */
	@Getter
	@Setter
	@Column(name="seas_author")
	private String seasAuthor;
	
	/**
	 * Revisor del sumario
	 */
	@Getter
	@Setter
	@Column(name="seas_reviser")
	private String seasReviser;
	
	/**
	 * Editor del sumario
	 */
	@Getter
	@Setter
	@Column(name="seas_editor")
	private String seasEditor;
	
	/**
	 * Colaborador sumario
	 */
	@Getter
	@Setter
	@Column(name="seas_collaborator")
	private String seasCollaborator;
	
	/**
	 * Anio de publicacion
	 */
	@Getter
	@Setter
	@Column(name="seas_publication_year")
	private String seasPublicationYear;
	
	/**
	 * Anio de introduccion
	 */
	@Getter
	@Setter
	@Column(name="seas_introduction_year")
	private String seasIntroductionYear;
	
	/**
	 * Razon por la que se produjo la introduccion
	 */
	@Getter
	@Setter
	@Column(name="seas_introduction_reason")
	private String seasIntroductionReason;
	
	
	/**
	 * Razon por la que se produjo la introduccion
	 */
	@Getter
	@Setter
	@Column(name="seas_natural_environment")
	private String seasNaturalEnvironment;
	
	
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="seas_user_create")
	private String seasUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="seas_date_create")
	private Date seasDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="seas_user_update")
	private String seasUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="seas_date_update")
	private Date seasDateUpdate;
	
	/**
	 * Bandera que indica si el registro esta activo
	 */
	@Getter
	@Setter
	@Column(name="seas_status")
	private Boolean seasStatus;
	
		
	/**
	 * id especie 
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="spta_id")
	private SpecieTaxa specie;
	
	/**
	 * Bandera que indica si se publica la ficha
	 */
	@Getter
	@Setter
	@Column(name="seas_publish")
	private Boolean seasPublish;
	
	
	
}
