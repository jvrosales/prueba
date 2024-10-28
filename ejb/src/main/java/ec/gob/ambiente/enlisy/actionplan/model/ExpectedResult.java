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
 * The persistent class for the ExpectedResult database table.
 * 
 */
@Entity
@Table(name="expected_result", schema="biodiversity")
public class ExpectedResult implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="exre_id")
	private Integer exreId;

	/**
	 * Nombre del resultado esperado
	 */
	@Getter
	@Setter
	@Column(name="exre_name")
	private String exreName;
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="exre_user_create")
	private String exreUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="exre_date_create")
	private Date exreDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="exre_user_update")
	private String exreUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="exre_date_update")
	private Date exreDateUpdate;
	
	/**
	 * Estado del registro Activo=true o Eliminacion Logica=false
	 */
	@Getter
	@Setter
	@Column(name="exre_status")
	private Boolean exreStatus;
	
	/**
	 * id de SpecificGoal
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="spgo_id")
	private SpecificGoal specificGoal;
}
