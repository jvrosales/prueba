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
 * The persistent class for the vedas database table.
 * 
 */
@Entity
@Table(name="vedas", schema="biodiversity")
public class Veda implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VEDAS_GENERATOR")
    @SequenceGenerator(name = "VEDAS_GENERATOR", initialValue = 1, sequenceName = "seq_veda_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="veda_id")
	private Integer vedaId;

	
	/**
	 * Nombre de la veda
	 */
	@Getter
	@Setter
	@Column(name="veda_name")
	private String vedaName;
	
	/**
	 * Acuerdo Ministerial
	 */
	@Getter
	@Setter
	@Column(name="veda_ministerial_agreement")
	private String vedaMinisterialAgreement;
	
	/**
	 * Fecha de publicacion de la veda
	 */
	@Getter
	@Setter
	@Column(name="veda_date_publish")
	private Date vedaDatePublish;
	
	/**
	 * Fecha de inicio de la veda
	 */
	@Getter
	@Setter
	@Column(name="veda_date_start")
	private Date vedaDateStart;
	
	/**
	 * Fecha fin de la veda
	 */
	@Getter
	@Setter
	@Column(name="veda_date_finish")
	private Date vedaDateFinish;
	
	/**
	 * Periodicidad
	 */
	@Getter
	@Setter
	@Column(name="veda_periodicity")
	private String vedaPeriodicity;
	
	/**
	 * Estado 
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "bigc_id")
	private BiodiversityGeneralCatalog bigcId;
	
	/**
	 * Comentarios acerca de la veda
	 */
	@Getter
	@Setter
	@Column(name="veda_comment")
	private String vedaComment;
	
	/**
	 * estado del registro
	 */
	@Getter
	@Setter
	@Column(name="veda_status")
	private Boolean vedaStatus;
	
	/**
	 * usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="veda_user_create")
	private String vedaUserCreate;
	
	/**
	 * fecha que se crea el registro
	 */
	@Getter
	@Setter
	@Column(name="veda_date_create")
	private Date vedaDateCreate;
	
	/**
	 * suaurio que se actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="veda_user_update")
	private String vedaUserUpdate;
	
	/**
	 * fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="veda_date_update")
	private Date vedaDateUpdate;
	
	@Getter
	@Setter
	@Column(name="veda_url")
	private String vedaUrl;

	
	
}
