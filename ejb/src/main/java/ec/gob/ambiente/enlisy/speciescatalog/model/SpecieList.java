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

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the agreements database table.
 * 
 */
@Entity
@Table(name="species_lists", schema="biodiversity")
public class SpecieList implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SPECIES__LISTS_GENERATOR")
    @SequenceGenerator(name = "SPECIES__LISTS_GENERATOR", initialValue = 1, sequenceName = "seq_spls_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="spls_id")
	private Integer splsId;

	
	/**
	 * Codigo del listado a presentar en la pantalla principal
	 */
	@Getter
	@Setter
	@Column(name="spls_code")
	private String splsCode;
	
	/**
	 * Imagen del listado para presentar en la pantalla principal
	 */
	@Getter
	@Setter
	@Column(name="spls_image")
	private String splsImage;
	
	/**
	 * Estilos para aplicar fill, cover, contain, scale-down a la imagen del listado para presentar en la pantalla principal
	 */
	@Getter
	@Setter
	@Column(name="spls_image_fit")
	private String splsImageFit;
	
	/**
	 * Titulo del listado para presentar en la pantalla principal
	 */
	@Getter
	@Setter
	@Column(name="spls_title")
	private String splsTitle;
	
	/**
	 * Filtro por defecto que afecta al listado de las especies
	 */
	@Getter
	@Setter
	@Column(name="spls_filter")
	private String splsFilter;
	
	/**
	 * Orden del listado para ser presentado en la pantalla principal
	 */
	@Getter
	@Setter
	@Column(name="spls_order")
	private String splsOrder;
	
	/**
	 * Para habilitar o deshabilitar el listado en la pantalla principal
	 */
	@Getter
	@Setter
	@Column(name="spls_enabled")
	private Boolean splsEnabled;
	
	/**
	 * Para visualizar el listado en la pantalla principal
	 */
	@Getter
	@Setter
	@Column(name="spls_visible")
	private Boolean splsVisible;
	
	
	/**
	 * estado del registro
	 */
	@Getter
	@Setter
	@Column(name="spls_status")
	private Boolean splsStatus;
	
	/**
	 * usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="spls_user_create")
	private String splsUserCreate;
	
	/**
	 * fecha que se crea el registro
	 */
	@Getter
	@Setter
	@Column(name="spls_date_create")
	private Date splsDateCreate;
	
	/**
	 * suaurio que se actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="spls_user_update")
	private String splsUserUpdate;
	
	/**
	 * fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="spls_date_update")
	private Date splsDateUpdate;
	

	
	
}
