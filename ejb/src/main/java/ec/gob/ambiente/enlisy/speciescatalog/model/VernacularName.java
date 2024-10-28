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
@Table(name="vernacular_names", schema="biodiversity")
public class VernacularName implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VERNACULAR_NAME_GENERATOR")
    @SequenceGenerator(name = "VERNACULAR_NAME_GENERATOR", initialValue = 1, sequenceName = "seq_vena_id", schema = "biodiversity", allocationSize = 1)
    @Column(name="vena_id")
	private Integer venaId;

	/**
	 * id grupo etnico
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="etgr_id")
	private EthnicGroup ethnicGroup;
	
	/**
	 * id especie
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="spta_id")
	private SpecieTaxa specieTaxa;
	
	/**
	 * id lenguaje
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="tala_id")
	private TaxaLanguage language;
	
	/**
	 * id lenguaje
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="inso_id")
	private InformationSource informationSource;
	
	/**
	 * Nombre comun
	 */
	@Getter
	@Setter
	@Column(name="vena_vernacular_name")
	private String venaVernacularName;
	
	
	/**
	 * estado del registro
	 */
	@Getter
	@Setter
	@Column(name="vena_status")
	private Boolean venaStatus;
	
	/**
	 * usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="vena_user_create")
	private String venaUserCreate;
	
	/**
	 * fecha que se crea el registro
	 */
	@Getter
	@Setter
	@Column(name="vena_date_create")
	private Date venaDateCreate;
	
	/**
	 * usuario que se crea el registro
	 */
	@Getter
	@Setter
	@Column(name="vena_user_update")
	private String venaUserUpdate;
	
	/**
	 * fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="vena_date_update")
	private Date venaDateUpdate;
		
	@Getter
	@Setter
	@Transient
	String codigoLenguaje;
	
	@Getter
	@Setter
	@Transient
	String codigoEtnia;
	
	@Getter
	@Setter
	@Transient
	String codigoFuenteInformacion;


}
