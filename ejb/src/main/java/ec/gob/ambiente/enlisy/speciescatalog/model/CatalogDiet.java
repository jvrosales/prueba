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
 * The persistent class for the catalog_diets database table.
 * 
 */
@Entity
@Table(name="catalog_diets", schema="biodiversity")
public class CatalogDiet implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATALOG_DIET_GENERATOR")
    @SequenceGenerator(name = "CATALOG_DIET_GENERATOR", initialValue = 1, sequenceName = "seq_cdie_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="cdie_id")
	private Integer cdieId;

	
	/**
	 * Nombre de la dieta
	 */
	@Getter
	@Setter
	@Column(name="cdie_name")
	private String cdieName;
	
			
	/**
	 * bandera que indica si la dieta es del reino animal
	 */
	@Getter
	@Setter
	@Column(name="cdie_animal")
	private Boolean cdieAnimal;
	
	/**
	 * bandera que indica si la dieta pertenece al reino plantae
	 */
	@Getter
	@Setter
	@Column(name="cdie_plantae")
	private Boolean cdiePlantae;
	
	/**
	 * bandera que indica si la dieta pertenece al reino fungi
	 */
	@Getter
	@Setter
	@Column(name="cdie_fungi")
	private Boolean cdieFungi;
	
	/**
	 * bandera que indica si la dieta pertenece al reino eubacteria
	 */
	@Getter
	@Setter
	@Column(name="cdie_eubacteria")
	private Boolean cdieEubacteria;

	/**
	 * bandera que indica si la dieta pertenece al reino arqueobacteria
	 */
	@Getter
	@Setter
	@Column(name="cdie_archeobacteria")
	private Boolean cdieArcheobacteria;
	
	/**
	 * bandera que indica si la dieta pertenece al reino protista
	 */
	@Getter
	@Setter
	@Column(name="cdie_protista")
	private Boolean cdieProtista;
	
	
	/**
	 * estado del registro
	 */
	@Getter
	@Setter
	@Column(name="cdie_status")
	private Boolean cdieStatus;
	
	/**
	 * usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="cdie_user_create")
	private String cdieUserCreate;
	
	/**
	 * fecha que se crea el registro
	 */
	@Getter
	@Setter
	@Column(name="cdie_date_create")
	private Date cdieDateCreate;
	
	/**
	 * fecha que se actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="cdie_date_update")
	private String cdieDateUpdate;
	
	/**
	 * usuario de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="cdie_user_update")
	private Date cdieUserUpdate;
		
	/**
	 * bandera que indica que el estado pertenece al reino chromista
	 */
	@Getter
	@Setter
	@Column(name="cdie_chromista")
	private Boolean cdieChromista;
	
	/**
	 * bandera que indica que el estado pertenece al reino viruses
	 */
	@Getter
	@Setter
	@Column(name="cdie_viruses")
	private Boolean cdieViruses;
}
