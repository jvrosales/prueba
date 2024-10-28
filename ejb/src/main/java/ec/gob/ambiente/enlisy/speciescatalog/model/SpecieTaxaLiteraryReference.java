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
 * The persistent class for the species_taxa_literary_references database table.
 * 
 */
@Entity
@Table(name="species_taxa_literary_references", schema="biodiversity")
public class SpecieTaxaLiteraryReference implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SPECIE_TAXA_LITER_REFE_GENERATOR")
    @SequenceGenerator(name = "SPECIE_TAXA_LITER_REFE_GENERATOR", initialValue = 1, sequenceName = "seq_stlr_id", schema = "biodiversity", allocationSize = 1)
    @Column(name="stlr_id")
	private Integer stlrId;

	/**
	 * Id de la especie
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="spta_id")
	private SpecieTaxa specieTaxa;
	
	/**
	 * Id de la referencia literaria
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="inso_id")
	private InformationSource insoId;
	
	
	
			
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="stlr_user_create")
	private String stlrUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="stlr_date_create")
	private Date stlrDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="stlr_user_update")
	private String stlrUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="stlr_date_update")
	private Date stlrDateUpdate;
	
	/**
	 * Bandera que indica si el registro esta activo
	 */
	@Getter
	@Setter
	@Column(name="stlr_status")
	private Boolean stlrStatus;
	
	@Getter
	@Setter
	@Transient
	String codigoReference;
	
	
}
