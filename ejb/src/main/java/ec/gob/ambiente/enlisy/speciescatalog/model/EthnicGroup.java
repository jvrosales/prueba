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
 * The persistent class for the ethnic_groups database table.
 * 
 */
@Entity
@Table(name="ethnic_groups", schema="biodiversity")
public class EthnicGroup implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ETHNIC_GROUPS_GENERATOR")
    @SequenceGenerator(name = "ETHNIC_GROUPS_GENERATOR", initialValue = 1, sequenceName = "seq_etgr_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="etgr_id")
	private Integer etgrId;

	
	/**
	 * Nombre de la etnia
	 */
	@Getter
	@Setter
	@Column(name="etgr_name")
	private String etgrName;
	
	/**
	 * Acronimo de la etnia
	 */
	@Getter
	@Setter
	@Column(name="etgr_acronym")
	private String etgrAcronym;
	
	
	
	/**
	 * Localizacion de la etnia
	 */
	@Getter
	@Setter
	@Column(name="etgr_location")
	private String etgrLocation;
	
	/**
	 * Estado del registro
	 */
	@Getter
	@Setter
	@Column(name="etgr_status")
	private Boolean etgrStatus;
	
	/**
	 * usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="etgr_user_create")
	private String etgrUserCreate;
	
	/**
	 * fecha que se crea el registro
	 */
	@Getter
	@Setter
	@Column(name="etgr_date_create")
	private Date etgrDateCreate;
	
	/**
	 * usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="etgr_user_update")
	private String etgrUserUpdate;
	
	/**
	 * fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="etgr_date_update")
	private Date etgrDateUpdate;
		
	
}
