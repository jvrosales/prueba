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
 * The persistent class for the dispersion_exotic_alien_species database table.
 * 
 */
@Entity
@Table(name="dispersion_exotic_alien_species", schema="biodiversity")
public class DispersionExoticAlienSpecie implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DISP_EXALIEN_SPECIE_GENERATOR")
    @SequenceGenerator(name = "DISP_EXALIEN_SPECIE_GENERATOR", initialValue = 1, sequenceName = "seq_deas_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="deas_id")
	private Integer deasId;
	
	
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="deas_user_create")
	private String deas_user_create;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="deas_date_create")
	private Date deasDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="deas_user_update")
	private String deasUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="deas_date_update")
	private Date deasDateUpdate;
	
	/**
	 * Bandera que indica si el registro esta activo
	 */
	@Getter
	@Setter
	@Column(name="deas_status")
	private Boolean deasStatus;
	
		
	/**
	 * id sumario especie exotica y/o invasora
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="seas_id")
	private SummaryExoticAlienSpecie summaryExotic;
	
	/**
	 * Id del catalogo de tipos de dispersion
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="cadi_id")
	private CatalogDispersion catalogDispersion;
	
	@Getter
	@Setter
	@Transient
	String codigoDispersion;
	
}
