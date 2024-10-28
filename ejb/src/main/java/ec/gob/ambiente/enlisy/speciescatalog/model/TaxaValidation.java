package ec.gob.ambiente.enlisy.speciescatalog.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the taxa_validations database table.
 * 
 */
@Entity
@Table(name="taxa_validations", schema="biodiversity")
public class TaxaValidation implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TAXA_VALIDATION_GENERATOR")
    @SequenceGenerator(name = "TAXA_VALIDATION_GENERATOR", initialValue = 1, sequenceName = "seq_tava_id", schema = "biodiversity", allocationSize = 1)
    @Column(name="tava_id")
	private Integer tavaId;

	
	/**
	 * Bandera que indica si la especie esta en ecuador
	 */
	@Getter
	@Setter
	@Column(name="tava_en_ecuador")
	private Boolean tavaEnEcuador;
	
	/**
	 * Bandera que indica si la especie es nativa
	 */
	@Getter
	@Setter
	@Column(name="tava_nativa")
	private Boolean tavaNativa;
	
	/**
	 * Bandera que indica si la especie es endemica
	 */
	@Getter
	@Setter
	@Column(name="tava_endemica")
	private Boolean tavaEndemica;
	
	/**
	 * Bandera que indica si la especie es exotica
	 */
	@Getter
	@Setter
	@Column(name="tava_exotica")
	private Boolean tavaExotica;
	
	/**
	 * Bandera que indica si la especie es invasora
	 */
	@Getter
	@Setter
	@Column(name="tava_invasora")
	private Boolean tavaInvasora;
	
	/**
	 * Bandera que indica si la especie es domestica
	 */
	@Getter
	@Setter
	@Column(name="tava_domestica")
	private Boolean tavaDomestica;
	
	/**
	 * Bandera que indica si la especie es migratoria
	 */
	@Getter
	@Setter
	@Column(name="tava_migratoria")
	private Boolean tavaMigratoria;
	
	/**
	 * estado del registro
	 */
	@Getter
	@Setter
	@Column(name="tava_status")
	private Boolean tavaStatus;
	
	/**
	 * usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="tava_user_create")
	private String tavaUserCreate;
	
	/**
	 * fecha que se crea el registro
	 */
	@Getter
	@Setter
	@Column(name="tava_date_create")
	private Date tavaDateCreate;
	
	/**
	 * usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="tava_user_update")
	private String tavaUserUpdate;
	
	/**
	 * fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="tava_date_update")
	private Date tavaDateUpdate;
}
