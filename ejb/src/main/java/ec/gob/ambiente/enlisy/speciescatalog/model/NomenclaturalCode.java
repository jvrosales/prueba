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
 * The persistent class for the nomenclatural_codes database table.
 * 
 */
@Entity
@Table(name="nomenclatural_codes", schema="biodiversity")
public class NomenclaturalCode implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOMENCLATURAL_CODE_GENERATOR")
    @SequenceGenerator(name = "NOMENCLATURAL_CODE_GENERATOR", initialValue = 1, sequenceName = "seq_noco_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="noco_id")
	private Integer nocoId;

	
	/**
	 * Nombre del codigo
	 */
	@Getter
	@Setter
	@Column(name="noco_name")
	private String nocoName;
	
	/**
	 * Codigo 
	 */
	@Getter
	@Setter
	@Column(name="noco_code")
	private String nocoCode;
	
	
	
	/**
	 * estado del registro
	 */
	@Getter
	@Setter
	@Column(name="noco_status")
	private Boolean nocoStatus;
	
	/**
	 * usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="noco_user_create")
	private String nocoUserCreate;
	
	/**
	 * fecha que se crea el registro
	 */
	@Getter
	@Setter
	@Column(name="noco_date_create")
	private Date nocoDateCreate;
	
	/**
	 * suaurio que se actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="noco_user_update")
	private String nocoUserUpdate;
	
	/**
	 * fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="noco_date_update")
	private Date nocoDateUpdate;
	

	
	
}
