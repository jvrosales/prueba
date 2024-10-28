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

import ec.gob.ambiente.enlisy.wildlifeevent.model.BiodiversityGeneralCatalog;
import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the agreements database table.
 * 
 */
@Entity
@Table(name="agreements", schema="biodiversity")
public class Agreement implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AGREEMENTS_GENERATOR")
    @SequenceGenerator(name = "AGREEMENTS_GENERATOR", initialValue = 1, sequenceName = "seq_agre_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="agre_id")
	private Integer agreId;

	
	/**
	 * Nombre del convenio
	 */
	@Getter
	@Setter
	@Column(name="agre_name")
	private String agreName;
	
	/**
	 * Fecha del convenio 
	 */
	@Getter
	@Setter
	@Column(name="agre_date")
	private Date agreDate;
	
	/**
	 * Estado 
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "bigc_id")
	private BiodiversityGeneralCatalog bigcId;
	
	/**
	 * Comentarios acerca del convenio
	 */
	@Getter
	@Setter
	@Column(name="agre_comment")
	private String agreComment;
	
	/**
	 * estado del registro
	 */
	@Getter
	@Setter
	@Column(name="agre_status")
	private Boolean agreStatus;
	
	/**
	 * usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="agre_user_create")
	private String agreUserCreate;
	
	/**
	 * fecha que se crea el registro
	 */
	@Getter
	@Setter
	@Column(name="agre_date_create")
	private Date agreDateCreate;
	
	/**
	 * suaurio que se actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="agre_user_update")
	private String agreUserUpdate;
	
	/**
	 * fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="agre_date_update")
	private Date agreDateUpdate;
	

	/**
	 * Url
	 */
	@Getter
	@Setter
	@Column(name="agre_url")
	private String agreUrl;
	
	
}
