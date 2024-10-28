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
import ec.gob.ambiente.enlisy.wildlifeevent.model.BiodiversityGeneralCatalog;
import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the vernacular_names database table.
 * 
 */
@Entity
@Table(name="species_taxa_provinces", schema="biodiversity")
public class SpecieTaxaProvince implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SPECIE_TAXA_PROVINCE_GENERATOR")
    @SequenceGenerator(name = "SPECIE_TAXA_PROVINCE_GENERATOR", initialValue = 1, sequenceName = "seq_sptp_id", schema = "biodiversity", allocationSize = 1)
    @Column(name="sptp_id")
	private Integer sptpId;

	/**
	 * id fuente de Informacion
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="inso_id")
	private InformationSource informationSource;
	
	/**
	 * id especie 
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="spta_id")
	private SpecieTaxa specie;
	
	/**
	 * Id provincia
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="province_id")
	private GeographicalLocation geographicalLocation;
	
	/**
	 * id dpa de la provincia
	 */
	@Getter
	@Setter
	@Column(name="province_codification_inec")
	private String provinceCodificationInec;
	
	/**
	 * Nota
	 */
	@Getter
	@Setter
	@Column(name="sptp_note")
	private String sptpNote;
	
	
	/**
	 * estado del registro
	 */
	@Getter
	@Setter
	@Column(name="sptp_status")
	private Boolean sptpStatus;
	
	/**
	 * usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="sptp_user_create")
	private String sptpUserCreate;
	
	/**
	 * fecha que se crea el registro
	 */
	@Getter
	@Setter
	@Column(name="sptp_date_create")
	private Date sptpDateCreate;
	
	/**
	 * usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="sptp_user_update")
	private String sptpUserUpdate;
	
	/**
	 * fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="sptp_date_update")
	private Date sptpDateUpdate;
	
	
	/**
	 * Localidad sumario especies exoticas
	 */
	@Getter
	@Setter
	@Column(name="sptp_locality")
	private String sptpLocality;
	
	/**
	 * Observaciones sumario especies exoticas
	 */
	@Getter
	@Setter
	@Column(name="sptp_remark")
	private String sptpRemark;
	
	/**
	 * Id del catalogo de ambiente
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="bigc_id")
	private BiodiversityGeneralCatalog ambiente;
	
	/**
	 * Id del catalogo de situacion poblacional
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="bigc_situation")
	private BiodiversityGeneralCatalog situacion;
	
	
	
	@Getter
	@Setter
	@Transient
	String codigoProvincia;
	
	@Getter
	@Setter
	@Transient
	String codigoAmbiente;
	
	@Getter
	@Setter
	@Transient
	String codigoSituacion;
	


}
