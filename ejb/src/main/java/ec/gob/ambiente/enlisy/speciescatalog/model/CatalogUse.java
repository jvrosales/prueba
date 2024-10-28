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
 * The persistent class for the catalog_uses database table.
 * 
 */
@Entity
@Table(name="catalog_uses", schema="biodiversity")
public class CatalogUse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATALOG_USE_GENERATOR")
    @SequenceGenerator(name = "CATALOG_USE_GENERATOR", initialValue = 1, sequenceName = "seq_caus_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="caus_id")
	private Integer causId;

	
	/**
	 * Nombre del uso
	 */
	@Getter
	@Setter
	@Column(name="caus_name")
	private String causName;
	
			
	/**
	 * bandera que indica si el uso es del reino animal
	 */
	@Getter
	@Setter
	@Column(name="caus_animal")
	private Boolean causAnimal;
	
	/**
	 * bandera que indica si el uso pertenece al reino plantae
	 */
	@Getter
	@Setter
	@Column(name="caus_plantae")
	private Boolean causPlantae;
	
	/**
	 * bandera que indica si el uso pertenece al reino fungi
	 */
	@Getter
	@Setter
	@Column(name="caus_fungi")
	private Boolean causFungi;
	
	/**
	 * bandera que indica si el uso pertenece al reino eubacteria
	 */
	@Getter
	@Setter
	@Column(name="caus_eubacteria")
	private Boolean causEubacteria;

	/**
	 * bandera que indica si el uso pertenece al reino arqueobacteria
	 */
	@Getter
	@Setter
	@Column(name="caus_archeobacteria")
	private Boolean causArcheobacteria;
	
	/**
	 * bandera que indica si el uso pertenece al reino protista
	 */
	@Getter
	@Setter
	@Column(name="caus_protista")
	private Boolean causProtista;
	
	
	/**
	 * estado del registro
	 */
	@Getter
	@Setter
	@Column(name="caus_status")
	private Boolean causStatus;
	
	/**
	 * usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="caus_user_create")
	private String causUserCreate;
	
	/**
	 * fecha que se crea el registro
	 */
	@Getter
	@Setter
	@Column(name="caus_date_create")
	private Date causDateCreate;
	
	/**
	 * fecha que se actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="caus_date_update")
	private String causDateUpdate;
	
	/**
	 * usuario de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="caus_user_update")
	private Date causUserUpdate;
	
	/**
	 * bandera que indica que el estado pertenece al reino chromista
	 */
	@Getter
	@Setter
	@Column(name="caus_chromista")
	private Boolean causChromista;
	
	/**
	 * bandera que indica que el estado pertenece al reino viruses
	 */
	@Getter
	@Setter
	@Column(name="caus_viruses")
	private Boolean causViruses;
	
}
