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
 * The persistent class for the specialist_interests_catalogs database table.
 * 
 */
@Entity
@Table(name="specialist_interests_catalogs", schema="biodiversity")
public class SpecialistInterestCatalog implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SPECIALIST_INTEREST_CATALOG_GENERATOR")
    @SequenceGenerator(name = "SPECIALIST_INTEREST_CATALOG_GENERATOR", initialValue = 1, sequenceName = "seq_spin_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="spin_id")
	private Integer spinId;

	
	/**
	 * Descripcion
	 */
	@Getter
	@Setter
	@Column(name="spin_description")
	private String spinDescription;
	
	/**
	 * estado del registro
	 */
	@Getter
	@Setter
	@Column(name="spin_status")
	private Boolean spinStatus;
	
	/**
	 * usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="spin_user_create")
	private String spinUserCreate;
	
	/**
	 * fecha que se crea el registro
	 */
	@Getter
	@Setter
	@Column(name="spin_date_create")
	private Date spinDateCreate;
	
	/**
	 * fecha que se crea el registro
	 */
	@Getter
	@Setter
	@Column(name="spin_user_update")
	private String spinUserUpdate;
	
	/**
	 * fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="spin_date_update")
	private Date spinDateUpdate;
	
	/**
	 * campo para ordenar los intereses en pantalla
	 */
	@Getter
	@Setter
	@Column(name="spin_order")
	private Integer spinOrder;
		
	
}
