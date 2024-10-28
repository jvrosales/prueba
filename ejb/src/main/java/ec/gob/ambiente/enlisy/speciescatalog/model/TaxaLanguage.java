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
 * The persistent class for the taxa_languages database table.
 * 
 */
@Entity
@Table(name="taxa_languages", schema="biodiversity")
public class TaxaLanguage implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TAXA_LANGUAGE_GENERATOR")
    @SequenceGenerator(name = "TAXA_LANGUAGE_GENERATOR", initialValue = 1, sequenceName = "seq_tala_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="tala_id")
	private Integer talaId;

	
	/**
	 * Nombre
	 */
	@Getter
	@Setter
	@Column(name="tala_name")
	private String talaName;
	
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="tala_user_create")
	private String talaUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="tala_date_create")
	private Date talaDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="tala_user_update")
	private String talaUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="tala_date_update")
	private Date talaDateUpdate;
	
	/**
	 * Bandera que indica si el registro esta activo
	 */
	@Getter
	@Setter
	@Column(name="tala_status")
	private Boolean talaStatus;
}
