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
 * The persistent class for the uses_exotic_alien_species database table.
 * 
 */
@Entity
@Table(name="uses_exotic_alien_species", schema="biodiversity")
public class UseExoticAlienSpecie implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USE_EXOTIC_ALI_ESP_GENERATOR")
    @SequenceGenerator(name = "USE_EXOTIC_ALI_ESP_GENERATOR", initialValue = 1, sequenceName = "seq_ueas_id", schema = "biodiversity", allocationSize = 1)
    @Column(name="ueas_id")
	private Integer ueasId;
	
	/**
	 * Descripcion
	 */
	@Getter
	@Setter
	@Column(name="ueas_description")
	private String ueasDescription;
	
	
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="ueas_user_create")
	private String ueasUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="ueas_date_create")
	private Date ueasDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="ueas_user_update")
	private String ueasUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="ueas_date_update")
	private Date ueasDateUpdate;
	
	/**
	 * Bandera que indica si el registro esta activo
	 */
	@Getter
	@Setter
	@Column(name="ueas_status")
	private Boolean ueasStatus;
	
		
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
	@JoinColumn(name="caus_id")
	private CatalogUse catalogUso;
	
	@Getter
	@Setter
	@Transient
	String codigoUso;
	
}
