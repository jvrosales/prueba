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
 * The persistent class for the species_taxa_artificial_groups database table.
 * 
 */
@Entity
@Table(name="species_taxa_artificial_groups", schema="biodiversity")
public class SpecieTaxaArtificialGroup implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SPECIE_TAXA_ARTIF_GROUP_GENERATOR")
    @SequenceGenerator(name = "SPECIE_TAXA_ARTIF_GROUP_GENERATOR", initialValue = 1, sequenceName = "seq_stag_id", schema = "biodiversity", allocationSize = 1)
    @Column(name="stag_id")
	private Integer stagId;

	/**
	 * Id de la especie
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="spta_id")
	private SpecieTaxa specieTaxa;
	
	/**
	 * Id del grupo artificial
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="argr_id")
	private ArtificialGroup artificialGroup;
	
			
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="stag_user_create")
	private String stagUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="stag_date_create")
	private Date stagDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="stag_user_update")
	private String stagUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="stag_date_update")
	private Date stagDateUpdate;
	
	/**
	 * Bandera que indica si el registro esta activo
	 */
	@Getter
	@Setter
	@Column(name="stag_status")
	private Boolean stagStatus;
	
	/**
	 * Nombre del grupo artificial
	 */
	@Getter
	@Setter
	@Column(name="stag_nombre")
	private String stagNombre;
	
	@Getter
	@Setter
	@Transient
	String codigoGrupoArtificial;
}
