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
@Table(name="taxonomic_sources", schema="biodiversity")
public class TaxonomicSource implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TAXONOMIC_SOURCE_GENERATOR")
    @SequenceGenerator(name = "TAXONOMIC_SOURCE_GENERATOR", initialValue = 1, sequenceName = "seq_taso_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="taso_id")
	private Integer tasoId;

	
	/**
	 * Nombre de la fuente
	 */
	@Getter
	@Setter
	@Column(name="taso_name")
	private String tasoName;
	
	/**
	 * Nomenclature
	 */
	@Getter
	@Setter
	@Column(name="taso_nomenclature")
	private String tasoNomenclature;
	
	/**
	 * Url de la fuente
	 */
	@Getter
	@Setter
	@Column(name="taso_url")
	private String tasoUrl;
	
	/**
	 * indica si la fuente es oficial
	 */
	@Getter
	@Setter
	@Column(name="taso_official")
	private Boolean tasoOfficial;
	
	/**
	 * bandera que indica si la fuente pertenece al reino animal
	 */
	@Getter
	@Setter
	@Column(name="taso_animal")
	private Boolean tasoAnimal;
	
	/**
	 * bandera que indica si la fuente pertenece al reino plantae
	 */
	@Getter
	@Setter
	@Column(name="taso_plantae")
	private Boolean tasoPlantae;
	
	/**
	 * bandera que indica si la fuente pertenece al reino fungi
	 */
	@Getter
	@Setter
	@Column(name="taso_fungi")
	private Boolean tasoFungi;
	
	/**
	 * bandera que indica si la fuente pertenece al reino eubacteria
	 */
	@Getter
	@Setter
	@Column(name="taso_eubacteria")
	private Boolean tasoEubacteria;

	/**
	 * bandera que indica si la fuente pertenece al reino arqueobacteria
	 */
	@Getter
	@Setter
	@Column(name="taso_archeobacteria")
	private Boolean tasoArcheobacteria;
	
	/**
	 * bandera que indica si la fuente pertenece al reino protista
	 */
	@Getter
	@Setter
	@Column(name="taso_protista")
	private Boolean tasoProtista;
	
	/**
	 * estado del registro
	 */
	@Getter
	@Setter
	@Column(name="taso_status")
	private Boolean tasoStatus;
	
	/**
	 * usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="taso_user_create")
	private String tasoUserCreate;
	
	/**
	 * fecha que se crea el registro
	 */
	@Getter
	@Setter
	@Column(name="taso_date_create")
	private Date tasoDateCreate;
	
	/**
	 * fecha que se crea el registro
	 */
	@Getter
	@Setter
	@Column(name="taso_user_update")
	private String tasoUserUpdate;
	
	/**
	 * fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="taso_date_update")
	private Date tasoDateUpdate;
	
	/**
	 * bandera que indica que el estado pertenece al reino chromista
	 */
	@Getter
	@Setter
	@Column(name="taso_chromista")
	private Boolean tasoChromista;
	
	/**
	 * bandera que indica que el estado pertenece al reino viruses
	 */
	@Getter
	@Setter
	@Column(name="taso_viruses")
	private Boolean tasoViruses;
	
	/**
	 * Campo que concatena los reinos
	 */
	@Getter
	@Setter
	@Transient
	private String reinos;
}
