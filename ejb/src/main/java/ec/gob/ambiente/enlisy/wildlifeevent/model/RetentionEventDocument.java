package ec.gob.ambiente.enlisy.wildlifeevent.model;

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
 * The persistent class for the species_catalog_documents database table.
 * 
 */
@Entity
@Table(name="retentions_events_documents", schema="biodiversity")
public class RetentionEventDocument implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String RETE_ACTA_INSPECCION_RETENCION="RETE_ACTA_INSPECCION_RETENCION";
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RETENTION_EVENT_DOCUMENT_GENERATOR")
    @SequenceGenerator(name = "RETENTION_EVENT_DOCUMENT_GENERATOR", initialValue = 1, sequenceName = "seq_red_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="red_id")
	private Integer redId;
	
	/**
	 * Nombre del documento
	 */
	@Getter
	@Setter
	@Column(name="red_name")
	private String redName;
	
	/**
	 * Mime
	 */
	@Getter
	@Setter
	@Column(name="red_mime")
	private String redMime;
	
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
	@Column(name="red_extension")
	private String redExtension;
	
	/**
	 * Id del documento en el alfresco
	 */
	@Getter
	@Setter
	@Column(name="red_alfresco_id")
	private String redAlfrescoId;
	
	/**
	 * Nombre del proceso
	 */
	@Getter
	@Setter
	@Column(name="red_process_name")
	private String redProcessName;
	
	/**
	 * Numero de tramite o id del tramite
	 */
	@Getter
	@Setter
	@Column(name="red_id_process")
	private String redIdProcess;
	
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="red_user_create")
	private String redUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="red_date_create")
	private Date redDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="red_user_update")
	private String redUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="red_date_update")
	private Date redDateUpdate;
	
	/**
	 * Bandera que indica si el registro esta activo
	 */
	@Getter
	@Setter
	@Column(name="red_status")
	private Boolean redStatus;
	
	@Getter
	@Setter
	@Transient
	byte[] content;
	
	/**
	 * Tipo de documento
	 */
	@Getter
	@Setter
	@Column(name="red_type")
	private String redType;
	
	/**
	 * Id del evento de retencion
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "rete_id")
	private RetentionEvent retentionEvent;
	
}
