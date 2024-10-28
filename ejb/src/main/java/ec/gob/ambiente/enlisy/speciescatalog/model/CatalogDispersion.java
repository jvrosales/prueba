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
 * The persistent class for the catalog_dispersion database table.
 * 
 */
@Entity
@Table(name="catalog_dispersion", schema="biodiversity")
public class CatalogDispersion implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATALOG_DISPERSION_GENERATOR")
    @SequenceGenerator(name = "CATALOG_DISPERSION_GENERATOR", initialValue = 1, sequenceName = "seq_cadi_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="cadi_id")
	private Integer cadiId;

	
	/**
	 * Nombre de la dispersion
	 */
	@Getter
	@Setter
	@Column(name="cadi_name")
	private String cadiName;
	
			
	/**
	 * bandera que indica si la dispersionn es del reino animal
	 */
	@Getter
	@Setter
	@Column(name="cadi_animal")
	private Boolean cadiAnimal;
	
	/**
	 * bandera que indica si la dispersion pertenece al reino plantae
	 */
	@Getter
	@Setter
	@Column(name="cadi_plantae")
	private Boolean cadiPlantae;
	
	/**
	 * bandera que indica si la dispersion pertenece al reino fungi
	 */
	@Getter
	@Setter
	@Column(name="cadi_fungi")
	private Boolean cadiFungi;
	
	/**
	 * bandera que indica si la dispersion pertenece al reino eubacteria
	 */
	@Getter
	@Setter
	@Column(name="cadi_eubacteria")
	private Boolean cadiEubacteria;

	/**
	 * bandera que indica si la dispersion pertenece al reino arqueobacteria
	 */
	@Getter
	@Setter
	@Column(name="cadi_archeobacteria")
	private Boolean cadiArcheobacteria;
	
	/**
	 * bandera que indica que el grupo pertenece al reino protista
	 */
	@Getter
	@Setter
	@Column(name="cadi_protista")
	private Boolean cadiProtista;
	
	
	/**
	 * estado del registro
	 */
	@Getter
	@Setter
	@Column(name="cadi_status")
	private Boolean cadiStatus;
	
	/**
	 * usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="cadi_user_create")
	private String cadiUserCreate;
	
	/**
	 * fecha que se crea el registro
	 */
	@Getter
	@Setter
	@Column(name="cadi_date_create")
	private Date cadiDateCreate;
	
	/**
	 * fecha que se actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="cadi_date_update")
	private String cadiDateUpdate;
	
	/**
	 * usuario de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="cadi_user_update")
	private Date cadiUserUpdate;
	
	/**
	 * bandera que indica que el estado pertenece al reino chromista
	 */
	@Getter
	@Setter
	@Column(name="cadi_chromista")
	private Boolean cadiChromista;
	
	/**
	 * bandera que indica que el estado pertenece al reino viruses
	 */
	@Getter
	@Setter
	@Column(name="cadi_viruses")
	private Boolean cadiViruses;
	
	
		
	
}
