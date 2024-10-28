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

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the proposed_taxas database table.
 * 
 */
@Entity
@Table(name="proposed_taxas", schema="biodiversity")
public class ProposedTaxa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROPOSED_TAXAS_GENERATOR")
    @SequenceGenerator(name = "PROPOSED_TAXAS_GENERATOR", initialValue = 1, sequenceName = "seq_prta_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="prta_id")
	private Integer prtaId;
	
	/** 
	 * Id del rango
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="tara_id")
	private TaxaRank rank;
	
	/** 
	 * Id del especialista
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="sapp_id")
	private SpecieApplication specieApplication;
	
	/** 
	 * Id del documento
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="scdo_id")
	private SpecieCatalogDocument specieCatalogDocument;
	
	/**
	 * Autor de la publicacion
	 */
	@Getter
	@Setter
	@Column(name="prta_publication_author")
	private String prtaPublicationAuthor;
	
	/**
	 * Anio de la publicacion
	 */
	@Getter
	@Setter
	@Column(name="prta_publication_year")
	private Integer prtaPublicationYear;
	
	/**
	 * Nombre del reino
	 */
	@Getter
	@Setter
	@Column(name="prta_kingdom")
	private String prtaKingdom;
	
	/**
	 * Nombre del fylum
	 */
	@Getter
	@Setter
	@Column(name="prta_phylum")
	private String prtaPhylum;
	
	/**
	 * Nombre de la clase
	 */
	@Getter
	@Setter
	@Column(name="prta_class")
	private String prtaClass;
	
	/**
	 * Nombre del orden
	 */
	@Getter
	@Setter
	@Column(name="prta_order")
	private String prtaOrder;
	
	/**
	 * Nombre de la familia
	 */
	@Getter
	@Setter
	@Column(name="prta_family")
	private String prtaFamily;
	
		
	/**
	 * Nombre del genero
	 */
	@Getter
	@Setter
	@Column(name="prta_genus")
	private String prtaGenus;
	
	/**
	 * Nombre cientifico
	 */
	@Getter
	@Setter
	@Column(name="prta_scientific_name")
	private String prtaScientificName;
	
	/**
	 * Nombre comun
	 */
	@Getter
	@Setter
	@Column(name="prta_vernacular_name")
	private String prtaVernacularName;
	
	/**
	 * epiteto especifico e infraespecifico
	 */
	@Getter
	@Setter
	@Column(name="prta_specific_infraspecific_epi")
	private String prtaSpecificInfraspecificEpi;
	
	/**
	 * enlace a publicacion
	 */
	@Getter
	@Setter
	@Column(name="prta_publication_url")
	private String prtaPublicationUrl;
	
	/**
	 * Estado de la solicitud
	 */
	@Getter
	@Setter
	@Column(name="prta_publication_document")
	private String prtaPublicationDocument;
	
	/**
	 * Fuente del nombre comun
	 */
	@Getter
	@Setter
	@Column(name="prta_name_according_to")
	private String prtaNameAccordingTo;
	
	/**
	 * Bandera que indica si la especie esta en ecuador
	 */
	@Getter
	@Setter
	@Column(name="prta_ecuador")
	private Boolean prtaEcuador;
	
	/**
	 * Bandera que indica si la especie es endemica
	 */
	@Getter
	@Setter
	@Column(name="prta_endemic")
	private Boolean prtaEndemic;
	
	/**
	 * Bandera que indica si la especie es exotica
	 */
	@Getter
	@Setter
	@Column(name="prta_exotic")
	private Boolean prtaExotic;
	
	/**
	 * Bandera que indica si la especie es domestica
	 */
	@Getter
	@Setter
	@Column(name="prta_domestic")
	private Boolean prtaDomestic;
	
	/**
	 * Bandera que indica si la especie es migratoria
	 */
	@Getter
	@Setter
	@Column(name="prta_migratory")
	private Boolean prtaMigratory;
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="prta_user_create")
	private String prtaUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="prta_date_create")
	private Date prtaDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="prta_user_update")
	private String prtaUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="prta_date_update")
	private Date prtaDateUpdate;
	
	/**
	 * Bandera que indica si el registro esta activo
	 */
	@Getter
	@Setter
	@Column(name="prta_status")
	private Boolean prtaStatus;
	
	/**
	 * estado del tramite
	 */
	@Getter
	@Setter
	@Column(name="prta_status_application")
	private String prtaStatusApplication;
	
	
	/**
	 * Observaciones si la solicitud de registro de taxon fue rechazado
	 */
	@Getter
	@Setter
	@Column(name="prta_remark_application")
	private String prtaRemarkApplication;
	
	/**
	 * Nombre del nivel taxonomico
	 */
	@Getter
	@Setter
	@Column(name="prta_rank_name_proposed")
	private String prtaRankNameProposed;
	
	/**
	 * Epiteto infraespecifico para variedad, subespecie y forma
	 */
	@Getter
	@Setter
	@Column(name="prta_infraspecific_epi")
	private String prtaInfraspecificEpi;
	
	/**
	 * Bandera que indica si la especie es nativa
	 */
	@Getter
	@Setter
	@Column(name="prta_native")
	private Boolean prtaNative;
	
	
	/**
	 * Bandera que indica si la especie es nativa
	 */
	@Getter
	@Setter
	@Column(name="prta_alien")
	private Boolean prtaAlien;
	
	/**
	 * Bandera que indica si la especie es continental
	 */
	@Getter
	@Setter
	@Column(name="prta_continental")
	private Boolean prtaContinental;
	
	/**
	 * Bandera que indica si la especie es insular
	 */
	@Getter
	@Setter
	@Column(name="prta_insular")
	private Boolean prtaInsular;
}
