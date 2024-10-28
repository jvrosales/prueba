package ec.gob.ambiente.enlisy.actionplan.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the PlanActionFunder database table.
 * 
 */
@Entity
@Table(name="plan_action_funder", schema="biodiversity")
public class PlanActionFunder implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="plaf_id")
	private Integer plafId;

	/**
	 * Porcentaje Financiador
	 */
	@Getter
	@Setter
	@Column(name="plaf_percentage")
	private Integer plafPercentage;
	
	/**
	 * Valor Financiador
	 */
	@Getter
	@Setter
	@Column(name="plaf_value")
	private Double plafValue;
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="plaf_user_create")
	private String plafUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="plaf_date_create")
	private Date plafDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="plaf_user_update")
	private String plafUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="plaf_date_update")
	private Date plafDateUpdate;
	
	/**
	 * Estado del registro Activo=true o Eliminacion Logica=false
	 */
	@Getter
	@Setter
	@Column(name="plaf_status")
	private Boolean plafStatus;
	
	/**
	 * id de Funder
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="fund_id")
	private Funder funder;
	
	/**
	 * id de PlanActionRecord
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="plar_id")
	private PlanActionRecord planActionRecord;
}
