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
 * The persistent class for the information_sources database table.
 * 
 */
@Entity
@Table(name="information_sources", schema="biodiversity")
public class InformationSource implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INFORMATION_SOURCES_GENERATOR")
    @SequenceGenerator(name = "INFORMATION_SOURCES_GENERATOR", initialValue = 1, sequenceName = "seq_inso_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="inso_id")
	private Integer insoId;

	
	/**
	 * Cita Corta
	 */
	@Getter
	@Setter
	@Column(name="inso_short_reference")
	private String insoShortAppoinment;
	
	/**
	 * Cita Larga
	 */
	@Getter
	@Setter
	@Column(name="inso_long_reference")
	private String insoLongAppoinment;

	
	/**
	 * estado del registro
	 */
	@Getter
	@Setter
	@Column(name="inso_status")
	private Boolean insoStatus;
	
	/**
	 * usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="inso_user_create")
	private String insoUserCreate;
	
	/**
	 * fecha que se crea el registro
	 */
	@Getter
	@Setter
	@Column(name="inso_date_create")
	private Date insoDateCreate;
	
	/**
	 * fecha que se crea el registro
	 */
	@Getter
	@Setter
	@Column(name="inso_user_update")
	private String insoUserUpdate;
	
	/**
	 * fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="inso_date_update")
	private Date insoDateUpdate;
	
	
		
	
}
