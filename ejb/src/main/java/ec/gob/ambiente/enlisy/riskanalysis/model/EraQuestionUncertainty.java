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

import ec.gob.ambiente.enlisy.wildlifeevent.model.BiodiversityGeneralCatalog;
import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the risk_analysis_applications_documents database table.
 * 
 */
@Entity
@Table(name="era_questions_uncertainty", schema="biodiversity")
public class EraQuestionUncertainty implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ERA_QUESTIONS_UNCER_GENERATOR")
    @SequenceGenerator(name = "ERA_QUESTIONS_UNCER_GENERATOR", initialValue = 1, sequenceName = "seq_erqu_id", schema = "biodiversity", allocationSize = 1)
    @Column(name="erqu_id")
	private Integer erquId;
	
	/**
	 *Nombre de la Categoria de la pregunta o incertidumbre
	 */
	@Getter
	@Setter
	@Column(name="erqu_category")
	private String erquCategory;
	
	
	/**
	 *Valor de la categoria
	 */
	@Getter
	@Setter
	@Column(name="erqu_value")
	private Double erquValue;
	
	/**
	 *Codigo de la Categoria de la pregunta o incertidumbre
	 */
	@Getter
	@Setter
	@Column(name="erqu_code")
	private String erquCode;
	
	/** 
	 * Id de tipo es decir respuesta o incertidumbre
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="type_id")
	private BiodiversityGeneralCatalog typeId;
	
		
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="erqu_user_create")
	private String erquUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="erqu_date_create")
	private Date erquDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="erqu_user_update")
	private String erquUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="erqu_date_update")
	private Date erquDateUpdate;
	
	/**
	 * Bandera que indica si el registro esta activo
	 */
	@Getter
	@Setter
	@Column(name="erqu_status")
	private Boolean erquStatus;
	
	
	

	
}
