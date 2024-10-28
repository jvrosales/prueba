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
@Table(name="rescue_events_documents", schema="biodiversity")
public class RescueEventDocument implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String RETE_ACTA_INSPECCION_RESCATE="RETE_ACTA_INSPECCION_RESCATE";
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RESCUE_EVENT_DOCUMENT_GENERATOR")
    @SequenceGenerator(name = "RESCUE_EVENT_DOCUMENT_GENERATOR", initialValue = 1, sequenceName = "seq_rsd_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="rsd_id")
	private Integer rsdId;
	
	/**
	 * Nombre del documento
	 */
	@Getter
	@Setter
	@Column(name="rsd_name")
	private String rsdName;
	
	/**
	 * Mime
	 */
	@Getter
	@Setter
	@Column(name="rsd_mime")
	private String rsdMime;
	
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
	@Column(name="rsd_extension")
	private String rsdExtension;
	
	/**
	 * Id del documento en el alfresco
	 */
	@Getter
	@Setter
	@Column(name="rsd_alfresco_id")
	private String rsdAlfrescoId;
	
	/**
	 * Nombre del proceso
	 */
	@Getter
	@Setter
	@Column(name="rsd_process_name")
	private String rsdProcessName;
	
	/**
	 * Numero de tramite o id del tramite
	 */
	@Getter
	@Setter
	@Column(name="rsd_id_process")
	private String rsdIdProcess;
	
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="rsd_user_create")
	private String rsdUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="rsd_date_create")
	private Date rsdDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="rsd_user_update")
	private String rsdUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="rsd_date_update")
	private Date rsdDateUpdate;
	
	/**
	 * Bandera que indica si el registro esta activo
	 */
	@Getter
	@Setter
	@Column(name="rsd_status")
	private Boolean rsdStatus;
	
	/**
	 * Id del evento de rescate
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "rese_id")
	private RescueEvent rescueEvent;
	
	/**
	 * Tipo de documento
	 */
	@Getter
	@Setter
	@Column(name="rsd_type")
	private String rsdType;
	
	@Getter
	@Setter
	@Transient
	byte[] content;
}

