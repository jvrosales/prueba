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

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the vernacular_names database table.
 * 
 */
@Entity
@Table(name="species_taxa_synonyms", schema="biodiversity")
public class SpecieTaxaSynonym implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SPECIE_TAXA_SYNONYMS_GENERATOR")
    @SequenceGenerator(name = "SPECIE_TAXA_SYNONYMS_GENERATOR", initialValue = 1, sequenceName = "seq_spts_id", schema = "biodiversity", allocationSize = 1)
    @Column(name="spts_id")
	private Integer sptsId;

	/**
	 * id especie
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="spta_id")
	private SpecieTaxa specie;
	
	/**
	 * id especie sinonimo
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="spta_id_synonym")
	private SpecieTaxa sinonimo;
	
	
	
	/**
	 * estado del registro
	 */
	@Getter
	@Setter
	@Column(name="spts_status")
	private Boolean sptsStatus;
	
	/**
	 * usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="spts_user_create")
	private String sptsUserCreate;
	
	/**
	 * fecha que se crea el registro
	 */
	@Getter
	@Setter
	@Column(name="spts_date_create")
	private Date sptsDateCreate;
	
	/**
	 * usuario que se crea el registro
	 */
	@Getter
	@Setter
	@Column(name="spts_user_update")
	private String sptsUserUpdate;
	
	/**
	 * fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="spts_date_update")
	private Date sptsDateUpdate;
		
	@Getter
	@Setter
	@Transient
	String codigoSinonimo;


}
