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
 * The persistent class for the species_taxa_information_sources database table.
 * 
 */
@Entity
@Table(name="species_taxa_information_sources", schema="biodiversity")
public class SpecieTaxaInformationSource implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SPECIE_TAXA_INFORM_SOURCE_GENERATOR")
    @SequenceGenerator(name = "SPECIE_TAXA_INFORM_SOURCE_GENERATOR", initialValue = 1, sequenceName = "seq_stis_id", schema = "biodiversity", allocationSize = 1)
    @Column(name="stis_id")
	private Integer stisId;

	/**
	 * Id de la especie
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="spta_id")
	private SpecieTaxa specieTaxa;
	
	
	
	/**
	 * Id tipo de fuente
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="bigc_id")
	private BiodiversityGeneralCatalog sourceType;
	
		
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="stis_user_create")
	private String stisUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="stis_date_create")
	private Date stisDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="stis_user_update")
	private String stisUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="stis_date_update")
	private Date stisDateUpdate;
	
	/**
	 * Bandera que indica si el registro esta activo
	 */
	@Getter
	@Setter
	@Column(name="stis_status")
	private Boolean stisStatus;
	
	/**
	 * Nombre fuente
	 */
	@Getter
	@Setter
	@Column(name="stis_name")
	private String stisName;
	
	/**
	 * Url de la fuente
	 */
	@Getter
	@Setter
	@Column(name="stis_url")
	private String stisUrl;
	
	/**
	 * Bandera que indica si es la fuente principal
	 */
	@Getter
	@Setter
	@Column(name="stis_principal")
	private Boolean stisPrincipal;
	
	
	
	@Getter
	@Setter
	@Transient
	String codigoTipoFuente;
}
