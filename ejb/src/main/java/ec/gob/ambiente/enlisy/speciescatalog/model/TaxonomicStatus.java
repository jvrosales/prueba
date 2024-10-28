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
 * The persistent class for the taxa_ranks database table.
 * 
 */
@Entity
@Table(name="taxonomic_status", schema="biodiversity")
public class TaxonomicStatus implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TAXONOMIC_STATUS_GENERATOR")
    @SequenceGenerator(name = "TAXONOMIC_STATUS_GENERATOR", initialValue = 1, sequenceName = "seq_tast_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="tast_id")
	private Integer tastId;

	
	/**
	 * Nombre del estado
	 */
	@Getter
	@Setter
	@Column(name="tast_name")
	private String tastName;
	
	/**
	 * Codigo del estado
	 */
	@Getter
	@Setter
	@Column(name="tast_code")
	private String tastCode;
	
	/**
	 * bandera que indica si el estado es del reino animal
	 */
	@Getter
	@Setter
	@Column(name="tast_animal")
	private Boolean tastAnimal;
	
	/**
	 * bandera que indica si el estado pertenece al reino plantae
	 */
	@Getter
	@Setter
	@Column(name="tast_plantae")
	private Boolean tastPlantae;
	
	/**
	 * bandera que indica si el estado pertenece al reino fungi
	 */
	@Getter
	@Setter
	@Column(name="tast_fungi")
	private Boolean tastFungi;
	
	/**
	 * bandera que indica que el estado pertenece al reino eubacteria
	 */
	@Getter
	@Setter
	@Column(name="tast_eubacteria")
	private Boolean tastEubacteria;

	/**
	 * bandera que indica que el estado pertenece al reino arqueobacteria
	 */
	@Getter
	@Setter
	@Column(name="tast_archeobacteria")
	private Boolean tastArqueobacteria;
	
	/**
	 * bandera que indica que el estado pertenece al reino protista
	 */
	@Getter
	@Setter
	@Column(name="tast_protista")
	private Boolean tastProtista;
	
	/**
	 * estado del registro
	 */
	@Getter
	@Setter
	@Column(name="tast_status")
	private Boolean tastStatus;
	
	/**
	 * usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="tast_user_create")
	private String tastUserCreate;
	
	/**
	 * fecha que se crea el registro
	 */
	@Getter
	@Setter
	@Column(name="tast_date_create")
	private Date tastDateCreate;
	
	/**
	 * fecha que se crea el registro
	 */
	@Getter
	@Setter
	@Column(name="tast_user_update")
	private String tastUserUpdate;
	
	/**
	 * fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="tast_date_update")
	private Date tastDateUpdate;
	

	/**
	 * bandera que indica que el estado pertenece al reino chromista
	 */
	@Getter
	@Setter
	@Column(name="tast_chromista")
	private Boolean tastChromista;
	
	/**
	 * bandera que indica que el estado pertenece al reino viruses
	 */
	@Getter
	@Setter
	@Column(name="tast_viruses")
	private Boolean tastViruses;
	
	/**
	 * Campo que concatena los reinos
	 */
	@Getter
	@Setter
	@Transient
	private String reinos;
		
	
}
