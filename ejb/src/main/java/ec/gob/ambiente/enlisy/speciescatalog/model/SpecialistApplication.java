package ec.gob.ambiente.enlisy.speciescatalog.model;

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

import ec.gob.ambiente.enlisy.dao.ConvertibleEntity;
import ec.gob.ambiente.enlisy.model.User;
import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the specialist_applications database table.
 * 
 */
@Entity
@Table(name="specialist_applications", schema="biodiversity")
public class SpecialistApplication extends ConvertibleEntity {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SPECIALIST_APPLICATION_GENERATOR")
    @SequenceGenerator(name = "SPECIALIST_APPLICATION_GENERATOR", initialValue = 1, sequenceName = "seq_spap_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="spap_id")
	private Integer spapId;
	
	/** 
	 * Id del usuario
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	/**
	 * Numero de tramite
	 */
	@Getter
	@Setter
	@Column(name="spap_code")
	private String spapCode;
	
	/**
	 * Numero de registro nacional de investigador
	 */
	@Getter
	@Setter
	@Column(name="spap_research_code")
	private String spapResearchCode;
	
	/**
	 * Area de especializacion
	 */
	@Getter
	@Setter
	@Column(name="spap_specialization")
	private String spapSpecialization;
	
	/**
	 * Intitucion de patrocinio/vinculacion
	 */
	@Getter
	@Setter
	@Column(name="spap_sponsorship_institution")
	private String spapSponsorshipInstitution;
	
	/**
	 * Nombre de la institucion
	 */
	@Getter
	@Setter
	@Column(name="spap_institution_name")
	private String spapInstitutionName;
	
	/**
	 * Contacto o Referencia de la Institucion
	 */
	@Getter
	@Setter
	@Column(name="spap_institution_contact")
	private String spapInstitutionContact;
	
	/**
	 * Telefono de la institucion
	 */
	@Getter
	@Setter
	@Column(name="spap_institution_phone")
	private String spapInstitutionPhone;
	
	/**
	 * Actividades realizadas en la institucion
	 */
	@Getter
	@Setter
	@Column(name="spap_institution_activity")
	private String spapInstitutionActivity;
	
	/**
	 * Seudonimo
	 */
	@Getter
	@Setter
	@Column(name="spap_pseudonym")
	private String spapPseudonym;
	
	/**
	 * Profesion
	 */
	@Getter
	@Setter
	@Column(name="spap_profession")
	private String spapProfession;
	
	/**
	 * Universidad
	 */
	@Getter
	@Setter
	@Column(name="spap_university")
	private String spapUniversity;
	
	/**
	 * Estado de la solicitud
	 */
	@Getter
	@Setter
	@Column(name="spap_status_application")
	private String spapStatusApplication;
	
	/**
	 * Estado de la solicitud
	 */
	@Getter
	@Setter
	@Column(name="spap_area")
	private String spapArea;
	
	/**
	 * Estado de la solicitud
	 */
	@Getter
	@Setter
	@Column(name="spap_observation")
	private String spapObservation;
	
	/**
	 * Area de interes
	 */
	@Getter
	@Setter
	@Column(name="spap_interest_area")
	private String spapInterestArea;
	
	/**
	 * Correo de la institucion
	 */
	@Getter
	@Setter
	@Column(name="spap_institution_email")
	private String spapInstitutionEmail;
	
	/**
	 * Pagina web de la institucion
	 */
	@Getter
	@Setter
	@Column(name="spap_institution_web_page")
	private String spapInstitutionWebPage;
	
	/**
	 * Nombre de la institucion de patrocinio
	 */
	@Getter
	@Setter
	@Column(name="spap_sponsorship_name")
	private String spapSponsorshipName;
	
	/**
	 * Nombre de la persona de contacto en la institucion de patrocinio
	 */
	@Getter
	@Setter
	@Column(name="spap_sponsorship_contact")
	private String spapSponsorshipContact;
	
	/**
	 * Telefono la institucion de patrocinio
	 */
	@Getter
	@Setter
	@Column(name="spap_sponsorship_phone")
	private String spapSponsorshipPhone;
	
	/**
	 * Correo la institucion de patrocinio
	 */
	@Getter
	@Setter
	@Column(name="spap_sponsorship_email")
	private String spapSponsorshipEmail;
	
	/**
	 * Pagina web la institucion de patrocinio
	 */
	@Getter
	@Setter
	@Column(name="spap_sponsorship_web_page")
	private String spapSponsorshipWebPage;
	
	
	/**
	 * Pagina web la institucion de patrocinio
	 */
	@Getter
	@Setter
	@Column(name="spap_verified_for")
	private String spapVerifiedFor;
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="spap_user_create")
	private String spapUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="spap_date_create")
	private Date spapDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="spap_user_update")
	private String spapUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="spap_date_update")
	private Date spapDateUpdate;
	
	/**
	 * Bandera que indica si el registro esta activo
	 */
	@Getter
	@Setter
	@Column(name="spap_status")
	private Boolean spapStatus;
	
	@Getter
	@Setter
	@Column(name="spap_is_national")
	private Boolean spapIsNational;
	
	@Getter
	@Setter
	@Transient
	private String nombresApellidos;
	
	@Getter
	@Setter
	@Transient
	private String identificacion;
	
	@Getter
	@Setter
	@Transient
	private String telefono;
	
	@Getter
	@Setter
	@Transient
	private String email;

	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return getSpapId();
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		if (getUser()!=null && getUser().getPeople()!=null) {
			return getUser().getPeople().getPeopName()+"("+getUser().getPeople().getPeopTitle()+")";
		}
		return "";
	}
}

