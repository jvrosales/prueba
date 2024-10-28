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

import ec.gob.ambiente.enlisy.model.GeographicalLocation;
import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the vernacular_names database table.
 * 
 */
@Entity
@Table(name="species_taxa_national_endemism", schema="biodiversity")
public class SpecieTaxaNationalEndemism implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SPECIE_TAXA_NATION_END_GENERATOR")
    @SequenceGenerator(name = "SPECIE_TAXA_NATION_END_GENERATOR", initialValue = 1, sequenceName = "seq_spen_id", schema = "biodiversity", allocationSize = 1)
    @Column(name="spen_id")
	private Integer spenId;

	/**
	 * id especie
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="spta_id")
	private SpecieTaxa sptaId;
	
	/**
	 * id de la provincia
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="province_id")
	private GeographicalLocation geographicalLocation;
	
	
	/**
	 * Bandera que indica si la especie es endemica en esa provincia
	 */
	@Getter
	@Setter
	@Column(name="spen_endemic")
	private Boolean spenEndemic;
	
	/**
	 * Bandera que indica si la especie es endemica en esa provincia
	 */
	@Getter
	@Setter
	@Column(name="spen_exotic")
	private Boolean spenExotic;
	
	/**
	 * Bandera que indica si la especie es endemica en esa provincia
	 */
	@Getter
	@Setter
	@Column(name="spen_status")
	private Boolean spenStatus;
		
	/**
	 * estado del registro
	 */
	@Getter
	@Setter
	@Column(name="spen_invasive")
	private Boolean spenInvasive;
	
	/**
	 * usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="spen_user_create")
	private String spenUserCreate;
	
	/**
	 * fecha que se crea el registro
	 */
	@Getter
	@Setter
	@Column(name="spen_date_create")
	private Date spenDateCreate;
	
	/**
	 * usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="spen_user_update")
	private String spenUserUpdate;
	
	/**
	 * fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="spen_date_update")
	private Date spenDateUpdate;
	
	
	@Getter
	@Setter
	@Transient
	String valueEndemic;
	
	@Getter
	@Setter
	@Transient
	String valueExotic;
	
	@Getter
	@Setter
	@Transient
	String valueInvasive;
	
	@Getter
	@Setter
	@Transient
	Boolean disabledEndemic;
	
	@Getter
	@Setter
	@Transient
	Boolean disabledExotic;
	
	@Getter
	@Setter
	@Transient
	Boolean disabledInvasive;
	
	
	
	

}
