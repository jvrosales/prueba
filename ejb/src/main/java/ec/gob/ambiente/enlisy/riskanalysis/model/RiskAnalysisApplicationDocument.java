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
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the risk_analysis_applications_documents database table.
 * 
 */
@Entity
@Table(name="risk_analysis_applications_documents", schema="biodiversity")
public class RiskAnalysisApplicationDocument implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RISK_ANALYSIS_APPL_DOCUMENT_GENERATOR")
    @SequenceGenerator(name = "RISK_ANALYSIS_APPL_DOCUMENT_GENERATOR", initialValue = 1, sequenceName = "seq_raad_id", schema = "biodiversity", allocationSize = 1)
    @Column(name="raad_id")
	private Integer raadId;
	
	/**
	 * Nombre del documento
	 */
	@Getter
	@Setter
	@Column(name="raad_name")
	private String raadName;
	
	
	/**
	 * Mime del documento
	 */
	@Getter
	@Setter
	@Column(name="raad_mime")
	private String raadMime;
	
	/**
	 * Extension del documento
	 */
	@Getter
	@Setter
	@Column(name="raad_extension")
	private String raadExtension;
	
	/**
	 * Id del documento en alfresco
	 */
	@Getter
	@Setter
	@Column(name="raad_alfresco_id")
	private String raadAlfrescoId;

	/** 
	 * Id de la solicitud
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="riaa_id")
	private RiskAnalysisApplication riaaId;
	
	/**
	 * Tipo de documento
	 */
	@Getter
	@Setter
	@Column(name="raad_type")
	private String raadType;
	
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="raad_user_create")
	private String raadUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="raad_date_create")
	private Date raadDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="raad_user_update")
	private String raadUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="raad_date_update")
	private Date raadDateUpdate;
	
	/**
	 * Bandera que indica si el registro esta activo
	 */
	@Getter
	@Setter
	@Column(name="raad_status")
	private Boolean raadStatus;
	
	@Getter
	@Setter
	@Transient
	byte[] content;
	

	
}
