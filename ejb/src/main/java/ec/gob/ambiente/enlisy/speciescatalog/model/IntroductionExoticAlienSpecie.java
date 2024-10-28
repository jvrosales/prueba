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
import javax.persistence.Transient;

import ec.gob.ambiente.enlisy.wildlifeevent.model.BiodiversityGeneralCatalog;
import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the introduction_exotic_alien_species database table.
 * 
 */
@Entity
@Table(name="introduction_exotic_alien_species", schema="biodiversity")
public class IntroductionExoticAlienSpecie implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INT_EXOAL_SPECIE_GENERATOR")
    @SequenceGenerator(name = "INT_EXOAL_SPECIE_GENERATOR", initialValue = 1, sequenceName = "seq_ieas_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="ieas_id")
	private Integer ieasId;
	
	/**
	 * Introduccion legal
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="bigc_id_legal")
	private BiodiversityGeneralCatalog legal;
	
	/**
	 * Forma de introduccion
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="bigc_id_form")
	private BiodiversityGeneralCatalog form;
	
	/**
	 * Causa de la introduccion
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="cain_id")
	private CauseIntroduction causeIntroduction ;
	
	/**
	 * Fecha de introduccion
	 */
	@Getter
	@Setter
	@Column(name="ieas_date_introduction ")
	private Date ieasDateIntroduction ;
	
	
	/**
	 * Lugar de introduccion
	 */
	@Getter
	@Setter
	@Column(name="ieas_place_introduction ")
	private String ieasPlaceIntroduction ;
	
	/**
	 * Autor
	 */
	@Getter
	@Setter
	@Column(name="ieas_author ")
	private String ieasAuthor ;
	
	/**
	 * Titulo
	 */
	@Getter
	@Setter
	@Column(name="ieas_title ")
	private String ieasTitle ;
	
	/**
	 * Indica si la especie es exotica en la region
	 */
	@Getter
	@Setter
	@Column(name="ieas_exotic_in_region ")
	private Boolean ieasExoticInRegion ;
	
	/**
	 * Descripcion
	 */
	@Getter
	@Setter
	@Column(name="ieas_description")
	private String ieasDescription;
	
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="ieas_user_create")
	private String ieasUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="ieas_date_create")
	private Date ieasDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="ieas_user_update")
	private String ieasUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="ieas_date_update")
	private Date ieasDateUpdate;
	
	/**
	 * Bandera que indica si el registro esta activo
	 */
	@Getter
	@Setter
	@Column(name="ieas_status")
	private Boolean ieasStatus;
	
		
	/**
	 * id especie
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="spta_id")
	private SpecieTaxa specie;
	
	/**
	 * id catalogo categoria de riesgo
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="bigc_id")
	private BiodiversityGeneralCatalog categoriaRiesgo;
	
	
	@Getter
	@Setter
	@Transient
	private String codigoCategoriaRiesgo;
	
	@Getter
	@Setter
	@Transient
	String codigoLegal;
	
	@Getter
	@Setter
	@Transient
	String codigoForma;
	
	@Getter
	@Setter
	@Transient
	String codigoCausa;
	
	
	
}
