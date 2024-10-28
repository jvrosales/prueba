package ec.gob.ambiente.enlisy.actionplan.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the ActionPlan database table.
 * 
 */
@Entity
@Table(name="action_plan", schema="biodiversity")
public class ActionPlan implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="acpl_id")
	private Integer acplId;

	/**
	 * Nombre del plan de acción
	 */
	@Getter
	@Setter
	@Column(name="acpl_name")
	private String acplName;
	
	/**
	 * Fecha acuerdo ministerial
	 */
	@Getter
	@Setter
	@Column(name="acpl_date_agreement")
	private Date acplDateAgreement;
	
	
	/**
	 * Numero Acuerdo ministerial
	 */
	@Getter
	@Setter
	@Column(name="acpl_ministerial_agreement")
	private String acplMinisterialAgreement;
	
	/**
	 * Numero registro oficial
	 */
	@Getter
	@Setter
	@Column(name="acpl_official_register")
	private String acplOfficialRegister;
	
	/**
	 * Archivo a cargar
	 */
	@Getter
	@Setter
	@Column(name="acpl_file")
	private String acplFile;
	
	/**
	 * Vigencia plan
	 */
	@Getter
	@Setter
	@Column(name="acpl_validity")
	private Integer acplValidity;
		
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="acpl_user_create")
	private String acplUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="acpl_date_create")
	private Date acplDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="acpl_user_update")
	private String acplUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="acpl_date_update")
	private Date acplDateUpdate;
	
	/**
	 * Estado del registro Activo=true o Eliminacion Logica=false
	 */
	@Getter
	@Setter
	@Column(name="acpl_status")
	private Boolean acplStatus;
}
