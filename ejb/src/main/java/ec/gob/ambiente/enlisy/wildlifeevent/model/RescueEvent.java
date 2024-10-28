package ec.gob.ambiente.enlisy.wildlifeevent.model;

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
 * The persistent class for the RescueEvent database table.
 * 
 */
@Entity
@Table(name="rescue_events", schema="biodiversity")
public class RescueEvent implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Identificador de los eventos de rescate de vida silvestre
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RESCUE_EVENT_GENERATOR")
    @SequenceGenerator(name = "RESCUE_EVENT_GENERATOR", initialValue = 1, sequenceName = "seq_rese_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="rese_id")
	private Integer reseId;

	/**
	 * Codigo del evento registrado
	 */
	@Getter
	@Setter
	@Column(name="rese_register_code")
	private String reseRegisterCode;
	
	/**
	 * Nombre y apellidos del rescatista
	 */
	@Getter
	@Setter
	@Column(name="rese_rescuer_name")
	private String reseRescuerName;
	
	/**
	 * Número de identificación del rescatista.
	 */
	@Getter
	@Setter
	@Column(name="rese_rescuer_identification")
	private String reseRescuerIdentification;
	
	/**
	 * Nacionalidad del rescatista.
	 */
	@Getter
	@Setter
	@Column(name="rese_rescuer_nationality")
	private Integer reseRescuerNationality;
	
	/**
	 * Dirección del rescatista.
	 */
	@Getter
	@Setter
	@Column(name="rese_rescuer_address")
	private String reseRescuerAddress;
	
	/**
	 * correo del rescatista
	 */
	@Getter
	@Setter
	@Column(name="rese_recuer_mail")
	private String reseRecuerMail;
	
	/**
	 * Telefono del rescatista
	 */
	@Getter
	@Setter
	@Column(name="rese_phone")
	private String resePhone;
	
	
	/**
	 * Tipo de pasaporte del rescatista.
	 */
	@Getter
	@Setter
	@Column(name="rese_rescuer_passport_type")
	private Integer reseRescuerPassportType;
	
	/**
	 * Institucion a la que pertenece
	 */
	@Getter
	@Setter
	@Column(name="rese_institution")
	private String reseInstitution;
	
	/**
	 * Recomendaciones Dirección provincial
	*/
	@Getter
	@Setter
	@Column(name="rese_recomen_dire_provi")
	private String reseRecomenDireProvi;
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="rese_user_create")
	private String reseUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="rese_date_create")
	private Date reseDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="rese_user_update")
	private String reseUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="rese_date_update")
	private Date reseDateUpdate;
	
	/**
	 * Estado del registro Activo=true o Eliminacion Logica=false
	 */
	@Getter
	@Setter
	@Column(name="rese_status")
	private Boolean reseStatus;
	
	/**
	 *Registro del destino final de la especie.
	 */
	@Getter
	@Setter
	@Column(name="rese_final_destination")
	private Integer reseFinalDestination;
	
	/**
	 * Registro del destino final de la especie.
	 */
	@Getter
	@Setter
	@Column(name="rese_destination")
	private String reseDestination;
	
	/**
	 * Número de identificación responsable.
	 */
	@Getter
	@Setter
	@Column(name="rese_dest_identification")
	private String reseDestIdentification;
	
	/**
	 * Nombre y apellidos del responsable.
	 */
	@Getter
	@Setter
	@Column(name="rese_dest_name")
	private String reseDestName;
	
	/**
	 * Telefono del responsable.
	 */
	@Getter
	@Setter
	@Column(name="rese_dest_phone")
	private String reseDestPhone;
	
	/**
	 * id de General Data
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="geda_id")
	private GeneralData generalData;
	
	/**
	 * Numero de acta del rescate
	 */
	@Getter
	@Setter
	@Column(name="rese_act_number")
	private String reseActNumber;
	
	
}
