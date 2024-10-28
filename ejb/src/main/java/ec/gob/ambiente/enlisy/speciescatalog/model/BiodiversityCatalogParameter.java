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
 * The persistent class for the biodiversity_catalog_parameters database table.
 * 
 */
@Entity
@Table(name="biodiversity_catalog_parameters", schema="biodiversity")
public class BiodiversityCatalogParameter implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BIODIVERSITY_CATPAR_GENERATOR")
    @SequenceGenerator(name = "BIODIVERSITY_CATPAR_GENERATOR", initialValue = 1, sequenceName = "seq_bcca_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="bcca_id")
	private Integer bccaId;

	
	/**
	 * Valor del parametro
	 */
	@Getter
	@Setter
	@Column(name="bcca_value")
	private String bccaValue;
	
	/**
	 * Codigo del parametro
	 */
	@Getter
	@Setter
	@Column(name="bcca_code")
	private String bccaCode;
	
		
	/**
	 * estado del registro
	 */
	@Getter
	@Setter
	@Column(name=" bcca_status")
	private Boolean  bccaStatus;
	
	/**
	 * usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="bcca_user_create")
	private String bccaUserCreate;
	
	/**
	 * fecha que se crea el registro
	 */
	@Getter
	@Setter
	@Column(name="bcca_date_create")
	private Date bccaDateCreate;
	
	/**
	 * fecha que se crea el registro
	 */
	@Getter
	@Setter
	@Column(name="bcca_user_update")
	private String bccaUserUpdate;
	
	/**
	 * fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="bcca_date_update")
	private Date bccaDateUpdate;
		
	
}
