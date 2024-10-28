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
 * The persistent class for the reproduction_exotic_alien_species database table.
 * 
 */
@Entity
@Table(name="reproduction_exotic_alien_species", schema="biodiversity")
public class ReproductionExoticAlienSpecie implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REPROD_EXOALI_GENERATOR")
    @SequenceGenerator(name = "REPROD_EXOALI_GENERATOR", initialValue = 1, sequenceName = "seq_reas_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="reas_id")
	private Integer reasId;
	
	
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="reas_user_create")
	private String reasUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="reas_date_create")
	private Date reasDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="reas_user_update")
	private String reasUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="reas_date_update")
	private Date reasDateUpdate;
	
	/**
	 * Bandera que indica si el registro esta activo
	 */
	@Getter
	@Setter
	@Column(name="reas_status")
	private Boolean reasStatus;
	
		
	/**
	 * id sumario especie exotica y/o invasora
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="seas_id")
	private SummaryExoticAlienSpecie summaryExotic;
	
	/**
	 * Id del catalogo de tipos de reproduccion
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="crep_id")
	private CatalogReproduction catalogoReproduccion;
	
	@Getter
	@Setter
	@Transient
	String codigoReproduccion;
	
}
