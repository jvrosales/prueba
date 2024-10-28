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
 * The persistent class for the specialist_references database table.
 * 
 */

@Entity
@Table(name="species_taxa_agreements", schema="biodiversity")
public class SpecieTaxaAgreement implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SPECIE_TAXA_AGREEMENT_GENERATOR")
    @SequenceGenerator(name = "SPECIE_TAXA_AGREEMENT_GENERATOR", initialValue = 1, sequenceName = "seq_sagr_id", schema = "biodiversity", allocationSize = 1)
    @Column(name="sagr_id")
	private Integer sagrId;
	
	/** 
	 * Id de la especie
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="spta_id")
	private SpecieTaxa sptaId;
	
	/** 
	 * Id de l convenio
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="agre_id")
	private Agreement agreId;
	
	/**
	 * Fecha en la que la especie ingresa al convenio
	 */
	@Getter
	@Setter
	@Column(name="sagr_date")
	private Date sagrDate;
	
	
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="sagr_user_create")
	private String sagrUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="sagr_date_create")
	private Date sagrDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="sagr_user_update")
	private String sagrUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="sagr_date_update")
	private Date sagrDateUpdate;
	
	/**
	 * Bandera que indica si el registro esta activo
	 */
	@Getter
	@Setter
	@Column(name="sagr_status")
	private Boolean sagrStatus;
	
	@Getter
	@Setter
	@Transient
	String codigoConvenio;
}
