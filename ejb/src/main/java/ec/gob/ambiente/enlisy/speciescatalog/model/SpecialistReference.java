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
 * The persistent class for the specialist_references database table.
 * 
 */

@Entity
@Table(name="specialist_references", schema="biodiversity")
public class SpecialistReference implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SPECIALIST_REFERENCE_GENERATOR")
    @SequenceGenerator(name = "SPECIALIST_REFERENCE_GENERATOR", initialValue = 1, sequenceName = "seq_spre_id", schema = "biodiversity", allocationSize = 1)
    @Column(name="spre_id")
	private Integer spreId;
	
	/** 
	 * Id del especialista
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="spap_id")
	private SpecialistApplication spapSpecialist;
	
	/**
	 * Nombre contacto
	 */
	@Getter
	@Setter
	@Column(name="spre_contact_name")
	private String spreContactName;
	
	/**
	 * Institucion contacto
	 */
	@Getter
	@Setter
	@Column(name="spre_institution")
	private String spreInstitution;
	
	/**
	 * correo electronico del contacto
	 */
	@Getter
	@Setter
	@Column(name="spre_email")
	private String spreEmail;
	
	/**
	 * Telefono del contacto
	 */
	@Getter
	@Setter
	@Column(name="spre_phone")
	private String sprePhone;
	
	/**
	 * Periodo de vinculacion
	 */
	@Getter
	@Setter
	@Column(name="spre_period")
	private String sprePeriod;
	
	/**
	 * Actividades Desarrolladas
	 */
	@Getter
	@Setter
	@Column(name="spre_activity")
	private String spreActivity;
	
	/**
	 * relacion de parentesco de la referencia personal
	 */
	@Getter
	@Setter
	@Column(name="spre_relation")
	private String spreRelation;
	
	
	/**
	 * tipo de referencia pr profesional, pe personal
	 */
	@Getter
	@Setter
	@Column(name="spre_type")
	private String spreType;
	
	/**
	 * Indica si la referencia es verificada
	 */
	@Getter
	@Setter
	@Column(name="spre_verified")
	private Boolean spreVerified;
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="spre_user_create")
	private String spreUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="spre_date_create")
	private Date spreDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="spre_user_update")
	private String spreUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="spre_date_update")
	private Date spreDateUpdate;
	
	/**
	 * Bandera que indica si el registro esta activo
	 */
	@Getter
	@Setter
	@Column(name="spre_status")
	private Boolean spreStatus;
}
