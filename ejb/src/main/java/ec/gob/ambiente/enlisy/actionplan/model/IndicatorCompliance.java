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
 * The persistent class for the IndicatorCompliance database table.
 * 
 */
@Entity
@Table(name="indicator_compliance", schema="biodiversity")
public class IndicatorCompliance implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="inco_id")
	private Integer incoId;

	/**
	 * Indicador de cumplimiento
	 */
	@Getter
	@Setter
	@Column(name="inco_name")
	private String incoName;
	
	/**
	 * Formula Indicador de cumplimiento
	 */
	@Getter
	@Setter
	@Column(name="inco_formula")
	private Double incoFormula;
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="inco_user_create")
	private String incoUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="inco_date_create")
	private Date incoDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="inco_user_update")
	private String incoUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="inco_date_update")
	private Date incoDateUpdate;
	
	/**
	 * Estado del registro Activo=true o Eliminacion Logica=false
	 */
	@Getter
	@Setter
	@Column(name="inco_status")
	private Boolean incoStatus;
}
