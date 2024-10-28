package ec.gob.ambiente.enlisy.riskanalysis.model;

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
 * The persistent class for the risk_analysis_applications_documents database table.
 * 
 */
@Entity
@Table(name="era_risk_analysis_applications", schema="biodiversity")
public class EraRiskAnalysisApplication implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ERA_RISK_ANALYSIS_APPLI_GENERATOR")
    @SequenceGenerator(name = "ERA_RISK_ANALYSIS_APPLI_GENERATOR", initialValue = 1, sequenceName = "seq_eraa_id", schema = "biodiversity", allocationSize = 1)
    @Column(name="eraa_id")
	private Integer eraaId;
	
	/** 
	 * Id de la solicitud
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="riaa_id")
	private RiskAnalysisApplication riaaId;
	
	/**
	 *Respuesta historial de invasividad de la especie
	 */
	@Getter
	@Setter
	@Column(name="eraa_inv_hist_answ_cat")
	private String eraaInvHistAnswCat;
	
	/**
	 *Valor Respuesta historial de invasividad de la especie
	 */
	@Getter
	@Setter
	@Column(name="eraa_inv_hist_answ_val")
	private Double eraaInvHistAnswVal;
	
	/**
	 *Incertidumbre historial de invasividad de la especie
	 */
	@Getter
	@Setter
	@Column(name="eraa_inv_hist_uncer_cat")
	private String eraaInvHistUncerCat;
	
	/**
	 *Valor Incertidumbre historial de invasividad de la especie
	 */
	@Getter
	@Setter
	@Column(name="eraa_inv_hist_uncer_val")
	private Double eraaInvHistUncerVal;
	
	/**
	 *Respuesta relaciones con taxones invasores cercanos
	 */
	@Getter
	@Setter
	@Column(name="eraa_rel_tax_answ_cat")
	private String eraaRelTaxAnswCat;
	
	/**
	 *Valor Respuesta relaciones con taxones invasores cercanos
	 */
	@Getter
	@Setter
	@Column(name="eraa_rel_tax_answ_val")
	private Double eraaRelTaxAnswVal;
	
	/**
	 *Incertidumbre relaciones con taxones invasores cercanos
	 */
	@Getter
	@Setter
	@Column(name="eraa_rel_tax_uncer_cat")
	private String eraaRelTaxUncerCat;
	
	/**
	 *Valor Incertidumbre relaciones con taxones invasores cercanos
	 */
	@Getter
	@Setter
	@Column(name="eraa_rel_tax_uncer_val")
	private Double eraaRelTaxUncerVal;
	
	/**
	 *Respuesta vectores de especies invasoras
	 */
	@Getter
	@Setter
	@Column(name="eraa_vec_tax_answ_cat")
	private String eraaVecTaxAnswCat;
	
	/**
	 *Valor Respuesta vectores de especies invasoras
	 */
	@Getter
	@Setter
	@Column(name="eraa_vec_tax_answ_val")
	private Double eraaVecTaxAnswVal;
	
	/**
	 *Incertidumbre vectores de especies invasoras
	 */
	@Getter
	@Setter
	@Column(name="eraa_vec_tax_uncer_cat")
	private String eraaVecTaxUncerCat;
	
	/**
	 *Valor Incertidumbre vectores de especies invasoras
	 */
	@Getter
	@Setter
	@Column(name="eraa_vec_tax_uncer_val")
	private Double eraaVecTaxUncerVal;
	
	/**
	 *Respuesta riesgo de introduccion
	 */
	@Getter
	@Setter
	@Column(name="eraa_intrrisk_answ_cat")
	private String eraaIntrriskAnswCat;
	
	/**
	 *Valor Incertidumbre vectores de especies invasoras
	 */
	@Getter
	@Setter
	@Column(name="eraa_intrrisk_answ_val")
	private Double eraaIntrriskAnswVal;
	
	/**
	 *Respuesta riesgo de introduccion
	 */
	@Getter
	@Setter
	@Column(name="eraa_intrrisk_uncer_cat")
	private String eraaIntrriskUncerCat;
	
	/**
	 *Valor Incertidumbre riesgo de introduccion
	 */
	@Getter
	@Setter
	@Column(name="eraa_intrrisk_uncer_val")
	private Double eraaIntrriskUncerVal;
	
	/**
	 * Respuesta riesgo de establecimiento
	 * 
	 */
	@Getter
	@Setter
	@Column(name="eraa_estrisk_answ_cat")
	private String eraaEstriskAnswCat;
	
	/**
	 *Valor Respuesta riesgo de establecimiento
	 */
	@Getter
	@Setter
	@Column(name="eraa_estrisk_answ_val")
	private Double eraaEstriskAnswVal;
	
	/**
	 * Incertidumbre riesgo de establecimiento
	 * 
	 */
	@Getter
	@Setter
	@Column(name="eraa_estrisk_uncer_cat")
	private String eraaEstriskUncerCat;
	
	/**
	 * Valor Incertidumbre riesgo de establecimiento
	 */
	@Getter
	@Setter
	@Column(name="eraa_estrisk_uncer_val")
	private Double eraaEstriskUncerVal;
	
	/**
	 * Respuesta riesgo de dispersion
	 */
	@Getter
	@Setter
	@Column(name="eraa_disprisk_answ_cat")
	private String eraaDispriskAnswCat;
	
	/**
	 * Valor Respuesta riesgo de dispersion
	 */
	@Getter
	@Setter
	@Column(name="eraa_disprisk_answ_val")
	private Double eraaDispriskAnswVal;
	
	/**
	 * Incertidumbre riesgo de dispersion
	 */
	@Getter
	@Setter
	@Column(name="eraa_disprisk_uncer_cat")
	private String eraaDispriskUncerCat;
	
	/**
	 * Valor Incertidumbre riesgo de dispersion
	 */
	@Getter
	@Setter
	@Column(name="eraa_disprisk_uncer_val")
	private Double eraaDispriskUncerVal;
	
	/**
	 * Respuesta riesgo de impactos sanitarios
	 */
	@Getter
	@Setter
	@Column(name="eraa_healimp_answ_cat")
	private String eraaHealimpAnswCat;
	
	/**
	 * Valor Respuesta riesgo de impactos sanitarios
	 */
	@Getter
	@Setter
	@Column(name="eraa_healimp_answ_val")
	private Double eraaHealimpAnswVal;
	
	/**
	 * Incertidumbre riesgo de impactos sanitarios
	 */
	@Getter
	@Setter
	@Column(name="eraa_healimp_uncer_cat")
	private String eraaHealimpUncerCat;
	
	/**
	 * Valor Incertidumbre riesgo de impactos sanitarios
	 */
	@Getter
	@Setter
	@Column(name="eraa_healimp_uncer_val")
	private Double eraaHealimpUncerVal;
	
	/**
	 * Respuesta riesgo de impactos economicos
	 */
	@Getter
	@Setter
	@Column(name="eraa_ecoimp_answ_cat")
	private String eraaEcoimpAnswCat;
	
	/**
	 * Valor Respuesta riesgo de impactos economicos
	 */
	@Getter
	@Setter
	@Column(name="eraa_ecoimp_answ_val")
	private Double eraaEcoimpAnswVal;
	
	/**
	 * Incertidumbre riesgo de impactos economicos
	 */
	@Getter
	@Setter
	@Column(name="eraa_ecoimp_uncer_cat")
	private String eraaEcoimpUncerCat;
	
	/**
	 * Valor Incertidumbre riesgo de impactos economicos
	 */
	@Getter
	@Setter
	@Column(name="eraa_ecoimp_uncer_val")
	private Double eraaEcoimpUncerVal;
	
	/**
	 * Respuesta riesgo de impactos ambientales
	 */
	@Getter
	@Setter
	@Column(name="eraa_envimp_answ_cat")
	private String eraaEnvimpAnswCat;
	
	/**
	 * Valor Respuesta riesgo de impactos ambientales
	 */
	@Getter
	@Setter
	@Column(name="eraa_envimp_answ_val")
	private Double eraaEnvimpAnswVal;
	
	/**
	 * Respuesta riesgo de impactos ambientales
	 */
	@Getter
	@Setter
	@Column(name="eraa_envimp_uncer_cat")
	private String eraaEnvimpUncerCat;
	
	/**
	 * Valor Incertidumbre riesgo de impactos ambientales
	 */
	@Getter
	@Setter
	@Column(name="eraa_envimp_uncer_val")
	private Double eraaEnvimpUncerVal;
	
	/**
	 * Respuesta riesgo de impactos ecologicos
	 */
	@Getter
	@Setter
	@Column(name="eraa_ecolimp_answ_cat")
	private String eraaEcolimpAnswCat;
	
	/**
	 * Valor Respuesta riesgo de impactos ecologicos
	 */
	@Getter
	@Setter
	@Column(name="eraa_ecolimp_answ_val")
	private Double eraaEcolimpAnswVal;
	
	/**
	 * Incertidumbre riesgo de impactos ecologicos
	 */
	@Getter
	@Setter
	@Column(name="eraa_ecolimp_uncer_cat")
	private String eraaEcolimpUncerCat;
	
	/**
	 *Valor Incertidumbre riesgo de impactos ecologicos
	 */
	@Getter
	@Setter
	@Column(name="eraa_ecolimp_uncer_val")
	private Double eraaEcolimpUncerVal;
	
	/**
	 * Valor estatus calculado
	 */
	@Getter
	@Setter
	@Column(name="eraa_status_value")
	private Double eraaStatusValue;
	
	/**
	 * Valor riesgo de introduccion calculado
	 */
	@Getter
	@Setter
	@Column(name="eraa_intrisk_value")
	private Double eraaIntrisValue;
	
	/**
	 * Valor riesgo de establecimiento calculado
	 */
	@Getter
	@Setter
	@Column(name="eraa_estrisk_value")
	private Double eraaEstriskValue;
	
	/**
	 * Valor riesgo de dispersion calculado
	 */
	@Getter
	@Setter
	@Column(name="eraa_disprisk_value")
	private Double eraaDispriskValue;
	
	/**
	 * Valor riesgo de invasividad calculado
	 */
	@Getter
	@Setter
	@Column(name="eraa_inv_risk_value")
	private Double eraaInvRiskValue;
	
	/**
	 * Valor riesgo de impactos calculado
	 */
	@Getter
	@Setter
	@Column(name="eraa_imp_risk_value")
	private Double eraaImpRiskValue;
	
	/**
	 * Valor nivel de riesgo
	 */
	@Getter
	@Setter
	@Column(name="eraa_risk_level_value")
	private Double eraaRiskLevelValue;
	
	/**
	 * Categoria nivel de riesgo
	 */
	@Getter
	@Setter
	@Column(name="eraa_risk_level_cat")
	private String eraaRiskLevelCat;
	
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="eraa_user_create")
	private String eraaUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="eraa_date_create")
	private Date eraaDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="eraa_user_update")
	private String eraaUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="eraa_date_update")
	private Date eraaDateUpdate;
	
	/**
	 * Bandera que indica si el registro esta activo
	 */
	@Getter
	@Setter
	@Column(name="eraa_status")
	private Boolean eraaStatus;
	
	
	

	
}
