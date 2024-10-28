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
 * The persistent class for the species_ecosystems database table.
 * 
 */
@Entity
@Table(name="species_ecosystems", schema="biodiversity")
public class SpecieEcosystem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SPECIE_ECOSYSTEM_GENERATOR")
    @SequenceGenerator(name = "SPECIE_ECOSYSTEM_GENERATOR", initialValue = 1, sequenceName = "seq_spec_id", schema = "biodiversity", allocationSize = 1)
    @Column(name="spec_id")
	private Integer specId ;

	/**
	 * id fuente de Informacion
	 */
	@Getter
	@Setter
	@Column(name="spec_code_ecosystem")
	private String specCodeEcosystem;
	
	/**
	 * id especie 
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="spta_id")
	private SpecieTaxa specie;
	
	/**
	 * nombre del ecosistema
	 */
	@Getter
	@Setter
	@Column(name="spec_name")
	private String specName;
		
	/**
	 * estado del registro
	 */
	@Getter
	@Setter
	@Column(name="spec_status")
	private Boolean specStatus;
	
	/**
	 * usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="spec_user_create")
	private String specUserCreate;
	
	/**
	 * fecha que se crea el registro
	 */
	@Getter
	@Setter
	@Column(name="spec_date_create")
	private Date specDateCreate;
	
	/**
	 * usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="spec_date_update")
	private Date specDateUpdate;
	
	/**
	 * fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="spec_user_update")
	private String specUserUpdate;
	
	@Getter
	@Setter
	@Transient
	String nombreEcosistema;
	


}
