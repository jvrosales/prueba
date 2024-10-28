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

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the taxa_ranks database table.
 * 
 */
@Entity
@Table(name="taxa_ranks", schema="biodiversity")
public class TaxaRank implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TAXA_RANK_GENERATOR")
    @SequenceGenerator(name = "TAXA_RANK_GENERATOR", initialValue = 1, sequenceName = "seq_tara_id", schema = "biodiversity", allocationSize = 1)
    @Column(name="tara_id")
	private Integer taraId;

	/** 
	 * Id del lenguaje
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="tala_id")
	private TaxaLanguage lenguaje;
	
	
	/**
	 * Nombre del Rango
	 */
	@Getter
	@Setter
	@Column(name="tara_name")
	private String taraName;
	
	/**
	 * Nivel del rango
	 */
	@Getter
	@Setter
	@Column(name="tara_level")
	private Integer taraLevel;
	
	/**
	 * Nivel del padre
	 */
	@Getter
	@Setter
	@Column(name="tara_level_parent")
	private String taraLevelParent;
	
		
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="tara_user_create")
	private String taraUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="tara_date_create")
	private Date taraDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="tara_user_update")
	private String taraUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="tara_date_update")
	private Date taraDateUpdate;
	
	/**
	 * Bandera que indica si el registro esta activo
	 */
	@Getter
	@Setter
	@Column(name="tara_status")
	private Boolean taraStatus;
}
