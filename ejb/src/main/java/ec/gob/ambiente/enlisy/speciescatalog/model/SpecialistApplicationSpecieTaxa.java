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

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the specialist_applications_species_taxa table.
 * 
 */
@Entity
@Table(name="specialist_applications_species_taxa", schema="biodiversity")
public class SpecialistApplicationSpecieTaxa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SPECIALIST_APPLICATION_SPECIE_GENERATOR")
    @SequenceGenerator(name = "SPECIALIST_APPLICATION_SPECIE_GENERATOR", initialValue = 1, sequenceName = "seq_sast_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="sast_id")
	private Integer sastId;
	
	/** 
	 * Id de la especie
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="spta_id")
	private SpecieTaxa specieTaxa;
	
	/**
	 * Id del especialista
	 */
	@Getter
	@Setter
	@Column(name="spap_id")
	private SpecialistApplication specialistApplication;
	
		
	/**
	 * Estado del registro
	 */
	@Getter
	@Setter
	@Column(name="sast_status")
	private Boolean sastStatus;
	
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="sast_user_create")
	private String sastUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="sast_date_create")
	private Date sastDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="sast_user_update")
	private String sastUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="sast_date_update")
	private Date sastDateUpdate;
	
	
}
