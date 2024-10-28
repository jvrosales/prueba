package ec.gob.ambiente.enlisy.riskanalysis.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the risk_analysis_applications_documents database table.
 * 
 */
@Entity
@Table(name="era_risk_level", schema="biodiversity")
public class EraRiskLevel implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ERA_RISK_LEVEL_GENERATOR")
    @SequenceGenerator(name = "ERA_RISK_LEVEL_GENERATOR", initialValue = 1, sequenceName = "seq_errl_id", schema = "biodiversity", allocationSize = 1)
    @Column(name="errl_id")
	private Integer errlId;
	
	
	/**
	 *Valor minimo
	 */
	@Getter
	@Setter
	@Column(name="errl_minimun_value")
	private Double errlMinimunValue;
	
	/**
	 *Valor maximo
	 */
	@Getter
	@Setter
	@Column(name="errl_maximun_value")
	private Double errlMaximunValue;
	
	/**
	 *Categoria resultado de la evaluacion de los valores minimos y maximos
	 */
	@Getter
	@Setter
	@Column(name="errl_result")
	private String errlResult;
	
		
		
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="errl_user_create")
	private String errlUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="errl_date_create")
	private Date errlDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="errl_user_update")
	private String errlUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="errl_date_update")
	private Date errlDateUpdate;
	
	/**
	 * Bandera que indica si el registro esta activo
	 */
	@Getter
	@Setter
	@Column(name="errl_status")
	private Boolean errlStatus;
	
	
	

	
}
