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
 * The persistent class for the specialist_publications database table.
 * 
 */
@Entity
@Table(name="specialist_publications", schema="biodiversity")
public class SpecialistPublication implements Serializable  {

private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SPECIALIST_PUBLICATION_GENERATOR")
    @SequenceGenerator(name = "SPECIALIST_PUBLICATION_GENERATOR", initialValue = 1, sequenceName = "seq_sppu_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="sppu_id")
	private Integer sppuId;
	
	/** 
	 * Id del especialista
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="spap_id")
	private SpecialistApplication spapSpecialist;
	
	/**
	 * Titulo de la publicacion
	 */
	@Getter
	@Setter
	@Column(name="sppu_title")
	private String sppuTitle;
	
	/**
	 * Anio de la publicacion
	 */
	@Getter
	@Setter
	@Column(name="sppu_year")
	private Integer sppuYear;
	
	/**
	 * Autor de la publicacion
	 */
	@Getter
	@Setter
	@Column(name="sppu_author")
	private String sppuAuthor;
	
	/**
	 * Revista
	 */
	@Getter
	@Setter
	@Column(name="sppu_magazine")
	private String sppuMagazine;
	
	/**
	 * Numero de la publicacion
	 */
	@Getter
	@Setter
	@Column(name="sppu_number")
	private String sppuNumber;
	
	/**
	 * Paginas de la publicacion
	 */
	@Getter
	@Setter
	@Column(name="sppu_page")
	private String sppuPage;
	
	/**
	 * Enlace de la publicacion
	 */
	@Getter
	@Setter
	@Column(name="sppu_url")
	private String sppuUrl;
	
	/**
	 * Indica si la publicacion es verificada
	 */
	@Getter
	@Setter
	@Column(name="sppu_verified")
	private Boolean sppuVerified;
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="sppu_user_create")
	private String sppuUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="sppu_date_create")
	private Date sppuDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="sppu_user_update")
	private String sppuUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="sppu_date_update")
	private Date sppuDateUpdate;
	
	/**
	 * Bandera que indica si el registro esta activo
	 */
	@Getter
	@Setter
	@Column(name="sppu_status")
	private Boolean sppuStatus;
}
