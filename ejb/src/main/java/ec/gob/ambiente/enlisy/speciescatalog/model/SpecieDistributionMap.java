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
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the species_distribution_maps database table.
 * 
 */
@Entity
@Table(name="species_distribution_maps", schema="biodiversity")
public class SpecieDistributionMap implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SPECIE_DISTRIBUTION_MAP_GENERATOR")
    @SequenceGenerator(name = "SPECIE_DISTRIBUTION_MAP_GENERATOR", initialValue = 1, sequenceName = "seq_spdm_id", schema = "biodiversity", allocationSize = 1)
    @Column(name="spdm_id")
	private Integer spdmId;
	
	/**
	 * nombre del archivo
	 */
	@Getter
	@Setter
	@Column(name="spdm_name")
	private String spdmName;
	
	/**
	 * Mime
	 */
	@Getter
	@Setter
	@Column(name="spdm_mime")
	private String spdmMime;
	
	
	
	/**
	 * Extension del archivo
	 */
	@Getter
	@Setter
	@Column(name="spdm_extension")
	private String spdmExtension;
	
	/**
	 * Id del documento en el alfresco
	 */
	@Getter
	@Setter
	@Column(name="spdm_alfresco_id")
	private String spdmAlfrescoId;
	
	/**
	 * Nombre de la carpeta-codigo gui de la especie
	 */
	@Getter
	@Setter
	@Column(name="spdm_gui_specie")
	private String spdmGuiSpecie;
	
	
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="spdm_user_create")
	private String spdmUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="spdm_date_create")
	private Date spdmDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="spdm_user_update")
	private String spdmUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="spdm_date_update")
	private Date spdmDateUpdate;
	
	/**
	 * Bandera que indica si el registro esta activo
	 */
	@Getter
	@Setter
	@Column(name="spdm_status")
	private Boolean spdmStatus;
	
		
	/**
	 * id especie 
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="spsu_id")
	private SpecieSummary specieSummary;
	
	
	/**
	 * Tipo fotografia
	 */
	@Getter
	@Setter
	@Column(name="spdm_type_photograph")
	private String spdmTypePhotograph;
	
	/**
	 * Autor fotografia
	 */
	@Getter
	@Setter
	@Column(name="spdm_author")
	private String spdmAuthor;
	
	/**
	 * Descripcion fotografia
	 */
	@Getter
	@Setter
	@Column(name="spdm_description")
	private String spdmDescription ;
	
	/**
	 * Titulo fotografia
	 */
	@Getter
	@Setter
	@Column(name="spdm_title")
	private String spdmTitle ;
	
	
	
	@Getter
	@Setter
	@Transient
	byte[] content;
	
}
