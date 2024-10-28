package ec.gob.ambiente.enlisy.actionplan.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the species_catalog_documents database table.
 * 
 */
@Entity
@Table(name="plan_action_documents", schema="biodiversity")
public class PlanActionDocument implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String PLAT_INFORME_SEGUIMIENTO_PLAN_ACCION="PLAT_INFORME_SEGUIMIENTO_PLAN_ACCION";
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pad_id")
	private Integer padId;
	
	/**
	 * Nombre del documento
	 */
	@Getter
	@Setter
	@Column(name="pad_name")
	private String padName;
	
	/**
	 * Mime
	 */
	@Getter
	@Setter
	@Column(name="pad_mime")
	private String padMime;
	
	/**
	 * Tipo de documento
	 */
	@Getter
	@Setter
	@Column(name="doty_id")
	private Integer doty_id;
	
	/**
	 * Extension del archivo
	 */
	@Getter
	@Setter
	@Column(name="pad_extension")
	private String padExtension;
	
	/**
	 * Id del documento en el alfresco
	 */
	@Getter
	@Setter
	@Column(name="pad_alfresco_id")
	private String padAlfrescoId;
	
	/**
	 * Nombre del proceso
	 */
	@Getter
	@Setter
	@Column(name="pad_process_name")
	private String padProcessName;
	
	/**
	 * Numero de tramite o id del tramite
	 */
	@Getter
	@Setter
	@Column(name="pad_id_process")
	private String padIdProcess;
	
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="pad_user_create")
	private String padUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="pad_date_create")
	private Date padDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="pad_user_update")
	private String padUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="pad_date_update")
	private Date padDateUpdate;
	
	/**
	 * Bandera que indica si el registro esta activo
	 */
	@Getter
	@Setter
	@Column(name="pad_status")
	private Boolean padStatus;
	
	@Getter
	@Setter
	@Transient
	byte[] content;
	
}
