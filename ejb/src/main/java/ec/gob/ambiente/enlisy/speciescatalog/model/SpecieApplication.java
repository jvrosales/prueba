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
 * The persistent class for the proposed_taxas database table.
 * 
 */
@Entity
@Table(name="specie_applications", schema="biodiversity")
public class SpecieApplication implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SPECIE_APPLICATION_GENERATOR")
    @SequenceGenerator(name = "SPECIE_APPLICATION_GENERATOR", initialValue = 1, sequenceName = "seq_sapp_id", schema = "biodiversity", allocationSize = 1)
    @Column(name="sapp_id")
	private Integer sappId;
	
	/** 
	 * Id del especialista
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="spap_id")
	private SpecialistApplication specialistApplication;
	
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="sapp_user_create")
	private String sappUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="sapp_date_create")
	private Date sappDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="sapp_user_update")
	private String sappUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="sapp_date_update")
	private Date sappDateUpdate;
	
	/**
	 * Bandera que indica si el registro esta activo
	 */
	@Getter
	@Setter
	@Column(name="sapp_status")
	private Boolean sappStatus;
	
	/**
	 * numero del tramite
	 */
	@Getter
	@Setter
	@Column(name="sapp_code")
	private String sappCode;
	
	/**
	 * estado del tramite
	 */
	@Getter
	@Setter
	@Column(name="sapp_status_application")
	private String sappStatusApplication;
}
