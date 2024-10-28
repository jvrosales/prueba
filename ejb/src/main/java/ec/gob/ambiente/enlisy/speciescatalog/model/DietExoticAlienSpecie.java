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
 * The persistent class for the diet_exotic_alien_species database table.
 * 
 */
@Entity
@Table(name="diet_exotic_alien_species", schema="biodiversity")
public class DietExoticAlienSpecie implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DIET_EXALIEN_SPECIE_GENERATOR")
    @SequenceGenerator(name = "DIET_EXALIEN_SPECIE_GENERATOR", initialValue = 1, sequenceName = "seq_diea_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="diea_id")
	private Integer dieaId;
	
	
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="diea_user_create")
	private String dieaUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="diea_date_create")
	private Date dieaDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="diea_user_update")
	private String dieaUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="diea_date_update")
	private Date dieaDateUpdate;
	
	/**
	 * Bandera que indica si el registro esta activo
	 */
	@Getter
	@Setter
	@Column(name="diea_status")
	private Boolean dieaStatus;
	
		
	/**
	 * id sumario especie exotica y/o invasora
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="seas_id")
	private SummaryExoticAlienSpecie summaryExotic;
	
	/**
	 * Id del catalogo de tipo de dieta
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="cdie_id")
	private CatalogDiet catalogoDieta;
	
	@Getter
	@Setter
	@Transient
	String codigoDieta;
	
	
	
}
