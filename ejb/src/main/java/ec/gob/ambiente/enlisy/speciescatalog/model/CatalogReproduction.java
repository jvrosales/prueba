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
 * The persistent class for the catalog_reproductions database table.
 * 
 */
@Entity
@Table(name="catalog_reproductions", schema="biodiversity")
public class CatalogReproduction implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATALOG_REPRODUCTION_GENERATOR")
    @SequenceGenerator(name = "CATALOG_REPRODUCTION_GENERATOR", initialValue = 1, sequenceName = "seq_crep_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="crep_id")
	private Integer crepId;

	
	/**
	 * Nombre de la reproduccion
	 */
	@Getter
	@Setter
	@Column(name="crep_name")
	private String crepName;
	
			
	/**
	 * bandera que indica si la reproduccion es del reino animal
	 */
	@Getter
	@Setter
	@Column(name="crep_animal")
	private Boolean crepAnimal;
	
	/**
	 * bandera que indica si la reproduccion pertenece al reino plantae
	 */
	@Getter
	@Setter
	@Column(name="crep_plantae")
	private Boolean crepPlantae;
	
	/**
	 * bandera que indica si la reproduccion pertenece al reino fungi
	 */
	@Getter
	@Setter
	@Column(name="crep_fungi")
	private Boolean crepFungi;
	
	/**
	 * bandera que indica si la reproduccion pertenece al reino eubacteria
	 */
	@Getter
	@Setter
	@Column(name="crep_eubacteria")
	private Boolean crepEubacteria;

	/**
	 * bandera que indica si la reproduccion pertenece al reino arqueobacteria
	 */
	@Getter
	@Setter
	@Column(name="crep_archeobacteria")
	private Boolean crepArcheobacteria;
	
	/**
	 * bandera que indica si la reproduccion pertenece al reino protista
	 */
	@Getter
	@Setter
	@Column(name="crep_protista")
	private Boolean crepProtista;
	
	
	/**
	 * estado del registro
	 */
	@Getter
	@Setter
	@Column(name="crep_status")
	private Boolean crepStatus;
	
	/**
	 * usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="crep_user_create")
	private String crepUserCreate;
	
	/**
	 * fecha que se crea el registro
	 */
	@Getter
	@Setter
	@Column(name="crep_date_create")
	private Date crepDateCreate;
	
	/**
	 * fecha que se actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="crep_date_update")
	private String crepDateUpdate;
	
	/**
	 * usuario de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="crep_user_update")
	private Date crepUserUpdate;
	
	/**
	 * bandera que indica que el estado pertenece al reino chromista
	 */
	@Getter
	@Setter
	@Column(name="crep_chromista")
	private Boolean crepChromista;
	
	/**
	 * bandera que indica que el estado pertenece al reino viruses
	 */
	@Getter
	@Setter
	@Column(name="crep_viruses")
	private Boolean crepViruses;
	
}
