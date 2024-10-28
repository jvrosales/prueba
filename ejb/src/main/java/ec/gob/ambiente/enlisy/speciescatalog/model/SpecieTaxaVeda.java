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

import ec.gob.ambiente.enlisy.wildlifeevent.model.BiodiversityGeneralCatalog;
import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the specialist_references database table.
 * 
 */

@Entity
@Table(name="species_taxa_vedas", schema="biodiversity")
public class SpecieTaxaVeda implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SPECIE_TAXA_VEDA_GENERATOR")
    @SequenceGenerator(name = "SPECIE_TAXA_VEDA_GENERATOR", initialValue = 1, sequenceName = "seq_spve_id", schema = "biodiversity", allocationSize = 1)
    @Column(name="spve_id")
	private Integer spveId;
	
	/** 
	 * Id de la especie
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="spta_id")
	private SpecieTaxa sptaId;
	
	/** 
	 * Id de la veda
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="veda_id")
	private Veda vedaId;
	
	/**
	 * Fecha de inicio de la veda
	 */
	@Getter
	@Setter
	@Column(name="spve_date_start")
	private Date spveDateStart;
	
	/**
	 * Fecha fin de la veda
	 */
	@Getter
	@Setter
	@Column(name="spve_date_finish")
	private Date spveDateFinish;
	
	
	/**
	 * Periodicidad
	 */
	@Getter
	@Setter
	@Column(name="spve_periodicity")
	private String spvePeriodicity;
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="spve_user_create")
	private String spveUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="spve_date_create")
	private Date spveDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="spve_user_update")
	private String spveUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="spve_date_update")
	private Date spveDateUpdate;
	
	/**
	 * Bandera que indica si el registro esta activo
	 */
	@Getter
	@Setter
	@Column(name="spve_status")
	private Boolean spveStatus;
	
	/*
	**
	 * Fecha de la veda
	 */
	@Getter
	@Setter
	@Column(name="spve_date")
	private Date spveDate;
	
	@Getter
	@Setter
	@Transient
	String codigoVeda;
	
	@Getter
	@Setter
	@Transient
	String textoVeda;
	
	/**
	 * Estado 
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "bigc_id")
	private BiodiversityGeneralCatalog bigcId;
}
