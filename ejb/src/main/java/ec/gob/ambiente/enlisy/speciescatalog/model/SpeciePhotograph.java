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
 * The persistent class for the species_catalog_documents database table.
 * 
 */
@Entity
@Table(name="species_photographs", schema="biodiversity")
public class SpeciePhotograph implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SPECIE_PHOTOGRAPH_SUMMARY_GENERATOR")
    @SequenceGenerator(name = "SPECIE_PHOTOGRAPH_SUMMARY_GENERATOR", initialValue = 1, sequenceName = "seq_spph_id", schema = "biodiversity", allocationSize = 1)
    @Column(name="spph_id")
	private Integer spphId;
	
	/**
	 * Nombre del archivo de la fotografia
	 */
	@Getter
	@Setter
	@Column(name="spph_name")
	private String spphName;
	
	/**
	 * Mime
	 */
	@Getter
	@Setter
	@Column(name="spph_mime")
	private String spphMime;
	
	
	
	/**
	 * Extension del archivo
	 */
	@Getter
	@Setter
	@Column(name="spph_extension")
	private String spphExtension;
	
	/**
	 * Id del documento en el alfresco
	 */
	@Getter
	@Setter
	@Column(name="spph_alfresco_id")
	private String spphAlfrescoId;
	
	/**
	 * Nombre de la carpeta-codigo gui de la especie
	 */
	@Getter
	@Setter
	@Column(name="spph_gui_specie")
	private String spphGuiSpecie;
	
	
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="spph_user_create")
	private String spphUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="spph_date_create")
	private Date spphDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="spph_user_update")
	private String spphUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="spph_date_update")
	private Date spphDateUpdate;
	
	/**
	 * Bandera que indica si el registro esta activo
	 */
	@Getter
	@Setter
	@Column(name="spph_status")
	private Boolean spphStatus;
	
	/**
	 * Bandera que indica si la fotografia es general
	 */
	@Getter
	@Setter
	@Column(name="spph_photograph_general")
	private Boolean spphPhotographGeneral;
	
	/**
	 * Descripcion
	 */
	@Getter
	@Setter
	@Column(name="spph_description")
	private String spphDescription;
	
	/**
	 * id especie 
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="spta_id")
	private SpecieTaxa specie;
	
	/**
	 * Id de la fuente de informacion
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="inso_id")
	private InformationSource informationSource;
	
	/**
	 * Tipo fotografia
	 */
	@Getter
	@Setter
	@Column(name="spph_type_photograph")
	private String spphTypePhotograph;
	
	/**
	 * Autor fotografia
	 */
	@Getter
	@Setter
	@Column(name="spph_author")
	private String spphAuthor;
	
	/**
	 * Titulo fotografia
	 */
	@Getter
	@Setter
	@Column(name="spph_title")
	private String spphTitle ;
	
	@Getter
	@Setter
	@Transient
	byte[] content;
	
	@Getter
	@Setter
	@Transient
	String codigoFuenteInformacion;
	
}
