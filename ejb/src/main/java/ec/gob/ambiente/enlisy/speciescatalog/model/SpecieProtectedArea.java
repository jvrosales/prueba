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
 * The persistent class for the species_protected_areas database table.
 * 
 */
@Entity
@Table(name="species_protected_areas", schema="biodiversity")
public class SpecieProtectedArea implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SPECIE_PROTECTED_AREA_GENERATOR")
    @SequenceGenerator(name = "SPECIE_PROTECTED_AREA_GENERATOR", initialValue = 1, sequenceName = "seq_sppa_id", schema = "biodiversity", allocationSize = 1)
    @Column(name="sppa_id")
	private Integer sppaId;

	/**
	 * id fuente de Informacion
	 */
	@Getter
	@Setter
	@Column(name="sppa_code_area")
	private String sppaCodeArea;
	
	/**
	 * id especie 
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="spta_id")
	private SpecieTaxa specie;
	
	/**
	 * nombre del area
	 */
	@Getter
	@Setter
	@Column(name="sppa_name")
	private String sppaName;
		
	/**
	 * estado del registro
	 */
	@Getter
	@Setter
	@Column(name="sppa_status")
	private Boolean sppaStatus;
	
	/**
	 * usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="sppa_user_create")
	private String sppaUserCreate;
	
	/**
	 * fecha que se crea el registro
	 */
	@Getter
	@Setter
	@Column(name="sppa_date_create")
	private Date sppaDateCreate;
	
	/**
	 * usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="sppa_user_update")
	private String sppaUserUpdate;
	
	/**
	 * fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="sppa_date_update")
	private Date sppaDateUpdate;
	
	@Getter
	@Setter
	@Transient
	String nombreArea;
	


}
