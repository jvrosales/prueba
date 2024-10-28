package ec.gob.ambiente.enlisy.speciescatalog.model;

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
import ec.gob.ambiente.enlisy.dao.ConvertibleEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the higher_classifications database table.
 * 
 */
@Entity
@Table(name="higher_classifications", schema="biodiversity")
public class HigherClassification extends ConvertibleEntity {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HIGHER_CLASS_GENERATOR")
    @SequenceGenerator(name = "HIGHER_CLASS_GENERATOR", initialValue = 1, sequenceName = "seq_spta_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="hicl_id")
	private Integer hiclId = Integer.valueOf(0);
	
	/** 
	 * gui identificador global unico
	 */
	@Getter
	@Setter
	@Column(name="hicl_scientific_gui")
	private String hiclScientificGui;
	
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="hicl_accepted_gui")
	private String hiclAccepted_gui;
	
	/**
	 * Id del padre
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="hicl_id_parent")
	private HigherClassification hiclIdParent;
	
	/**
	 * Id del rango
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="tara_id")
	private TaxaRank taxaRank;
	
	/**
	 * Nombre cientifico
	 */
	@Getter
	@Setter
	@Column(name="hicl_scientific_name")
	private String hiclScientificName;
	
	/**
	 * Nombre cientifico aceptado
	 */
	@Getter
	@Setter
	@Column(name="hicl_accepted_name")
	private String hiclAcceptedName;
	
	/**
	 * Clasificacion taxonomica superior
	 */
	@Getter
	@Setter
	@Column(name="hicl_higher_classification")
	private String hiclHigherClassification;
	
	/**
	 * Anio de publicacion
	 */
	@Getter
	@Setter
	@Column(name="hicl_name_published_year")
	private String hiclNamePublishedYear;
	
	/**
	 * Autor nombre cientifico
	 */
	@Getter
	@Setter
	@Column(name="hicl_cientific_name_authorship")
	private String hiclCientificNameAuthorship;
	
	/**
	 * nombre comun
	 */
	@Getter
	@Setter
	@Column(name="hicl_vernacular_name")
	private String hiclVernacularName;
	
	/**
	 * indica el codigo de nomenclatura bajo el cual se construye el nombre cientifico.
	 */
	@Getter
	@Setter
	@Column(name="hicl_nomenclatural_code")
	private String hiclNomenclaturalCode;
	
	/**
	 * observaciones
	 */
	@Getter
	@Setter
	@Column(name="hicl_taxon_remarks")
	private String hiclTaxonRemarks;
	
	
	/**
	 * indicas si es aceptado el nombre
	 */
	@Getter
	@Setter
	@Column(name="hicl_is_accepted_name")
	private Boolean hiclIsAcceptedName;
	
	/**
	 * Nombre del rango
	 */
	@Getter
	@Setter
	@Column(name="hicl_taxon_rank_name")
	private String hiclTaxonRankName;
	
	/**
	 * Estado del registro
	 */
	@Getter
	@Setter
	@Column(name="hicl_status")
	private Boolean hiclStatus;
	
	/**
	 * usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="hicl_user_create")
	private String hiclUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="hicl_date_create")
	private Date hiclDateCreate;
	
	/**
	 * usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="hicl_user_update")
	private String hiclUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="hicl_date_update")
	private Date hiclDateUpdate;
	
	
	
	/**
	 * Indica si el registro es verificado
	 */
	@Getter
	@Setter
	@Column(name="hicl_verified_record")
	private Boolean hiclVerifiedRecord;
	
	/**
	 * Id de la solicitud
	 */
	@Getter
	@Setter
	@Column(name="sapp_id")
	private Integer sapp_id;
	
	/**
	 * Almacena el arbol de jerarquía de los rangos taxonomicos concatenando los id de cada nivel
	 */
	@Getter
	@Setter
	@Column(name="hicl_hierarchy_code")
	private String hiclHierarchyCode;
	
	/**
	 * Id del status taxonomico
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="tast_id")
	private TaxonomicStatus taxonomicStatus;
	
	/**
	 * Id de la solicitud
	 */
	@Getter
	@Setter
	@Column(name="hicl_parent_name")
	private String hiclParentName;
	
	/**
	 * Registro verificado por
	 */
	@Getter
	@Setter
	@Column(name="hicl_verified_for")
	private String hiclVerifiedFor;
	
	/**
	 * Es taxon correcto
	 */
	@Getter
	@Setter
	@Column(name="hicl_correct_tax")
	private Boolean hiclCorrectTax;
	
	/**
	 * Epiteto especifico e infraespecifico
	 */
	@Getter
	@Setter
	@Column(name="hicl_specific_infraspecifc_epit")
	private String hiclSpecificInfraspecifcEpit;
	
	/**
	 * id Fuente Taxonomica
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="taso_id")
	private TaxonomicSource taxonomicSource;
	
	/**
	 * Epiteto especifico e infraespecifico
	 */
	@Getter
	@Setter
	@Column(name="hicl_search_code")
	private String hiclSearchCode;
	
	/**
	 * id Codigo Nomenclatural
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="noco_id")
	private NomenclaturalCode nomenclaturalCode;

	@Override
	public long getId() {
		return getHiclId();
	}

	@Override
	public String getDescription() {
		return getHiclScientificName();
	}
	
	/** 
	 * Id del taxon correcto
	 */
	@Getter
	@Setter
	@Column(name="hicl_correct_tax_id")
	private Integer hiclCorrectTaxId;
	
	/** 
	 * Nombre del taxon correcto
	 */
	@Getter
	@Setter
	@Column(name="hicl_correct_tax_name")
	private String hiclCorrectTaxName;

}
