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
 * The persistent class for the dispersion_vectors database table.
 * 
 */
@Entity
@Table(name="dispersion_vectors", schema="biodiversity")
public class DispersionVector implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DISP_VECTORS_GENERATOR")
    @SequenceGenerator(name = "DISP_VECTORS_GENERATOR", initialValue = 1, sequenceName = "seq_dive_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="dive_id")
	private Integer diveId;
	
	
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="dive_user_create")
	private String diveUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="dive_date_create")
	private Date diveDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="dive_user_update")
	private String diveUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="dive_date_update")
	private Date diveDateUpdate;
	
	/**
	 * Bandera que indica si el registro esta activo
	 */
	@Getter
	@Setter
	@Column(name="dive_status")
	private Boolean diveStatus;
	
		
	/**
	 * id sumario especie exotica y/o invasora
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="seas_id")
	private SummaryExoticAlienSpecie summaryExotic;
	
	/**
	 * Id del catalogo de tipos de vectores de dispersion
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="bigc_id")
	private BiodiversityGeneralCatalog vectorDispersion;
	
	@Getter
	@Setter
	@Transient
	String codigoVector;
	
	
	
}
