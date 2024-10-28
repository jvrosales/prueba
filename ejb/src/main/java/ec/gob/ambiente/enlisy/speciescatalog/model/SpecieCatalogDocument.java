package ec.gob.ambiente.enlisy.speciescatalog.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="species_catalog_documents", schema="biodiversity")
public class SpecieCatalogDocument implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String COB_PUBLICACION_TAXON_PROPUESTO="COB_PUBLICACION_TAXON_PROPUESTO";
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SPECIE_CATALOG_DOCUMENT_GENERATOR")
    @SequenceGenerator(name = "SPECIE_CATALOG_DOCUMENT_GENERATOR", initialValue = 1, sequenceName = "seq_scdo_id", schema = "biodiversity", allocationSize = 1)
    @Column(name="scdo_id")
	private Integer scdoId;
	
	/**
	 * Nombre del documento
	 */
	@Getter
	@Setter
	@Column(name="scdo_name")
	private String scdoName;
	
	/**
	 * Mime
	 */
	@Getter
	@Setter
	@Column(name="scdo_mime")
	private String scdoMime;
	
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
	@Column(name="scdo_extension")
	private String scdoExtension;
	
	/**
	 * Id del documento en el alfresco
	 */
	@Getter
	@Setter
	@Column(name="scdo_alfresco_id")
	private String scdoAlfrescoId;
	
	/**
	 * Nombre del proceso
	 */
	@Getter
	@Setter
	@Column(name="scdo_process_name")
	private String scdoProcessName;
	
	/**
	 * Numero de tramite o id del tramite
	 */
	@Getter
	@Setter
	@Column(name="scdo_id_process")
	private String scdoIdProcess;
	
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="scdo_user_create")
	private String scdoUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="scdo_date_create")
	private Date scdoDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="scdo_user_update")
	private String scdoUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="scdo_date_update")
	private Date scdoDateUpdate;
	
	/**
	 * Bandera que indica si el registro esta activo
	 */
	@Getter
	@Setter
	@Column(name="scdo_status")
	private Boolean scdoStatus;
	
	@Getter
	@Setter
	@Transient
	byte[] content;
	
}
