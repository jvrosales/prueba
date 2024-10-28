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
 * The persistent class for the biological_forms database table.
 * 
 */
@Entity
@Table(name="biological_forms", schema="biodiversity")
public class BiologicalForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BIOLOGICAL_FORM_GENERATOR")
    @SequenceGenerator(name = "BIOLOGICAL_FORM_GENERATOR", initialValue = 1, sequenceName = "seq_bifo_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="bifo_id")
	private Integer bifoId;
	
	
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="bifo_user_create")
	private String bifoUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="bifo_date_create")
	private Date bifoDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="bifo_user_update")
	private String bifoUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="bifo_date_update")
	private Date bifoDateUpdate;
	
	/**
	 * Bandera que indica si el registro esta activo
	 */
	@Getter
	@Setter
	@Column(name="bifo_status")
	private Boolean bifoStatus;
	
		
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
	@JoinColumn(name="cabf_id")
	private CatalogBiologicalForm forma;
	
	
	@Getter
	@Setter
	@Transient
	String codigoFormaBiologica;
}
