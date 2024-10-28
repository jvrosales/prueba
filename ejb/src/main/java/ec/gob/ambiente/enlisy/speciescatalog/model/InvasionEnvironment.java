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
 * The persistent class for the invasion_environments database table.
 * 
 */
@Entity
@Table(name="invasion_environments", schema="biodiversity")
public class InvasionEnvironment implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INVASION_ENVIROMENT_GENERATOR")
    @SequenceGenerator(name = "INVASION_ENVIROMENT_GENERATOR", initialValue = 1, sequenceName = "seq_inen_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name="inen_id")
	private Integer inenId;
	
	
	/**
	 * Descripcion
	 */
	@Getter
	@Setter
	@Column(name="inen_description")
	private String inenDescription;
	
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name="inen_user_create")
	private String inenUserCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name="inen_date_create")
	private Date inenDateCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name="inen_user_update")
	private String inenUserUpdate;
	
	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name="inen_date_update")
	private Date inenDateUpdate;
	
	/**
	 * Bandera que indica si el registro esta activo
	 */
	@Getter
	@Setter
	@Column(name="inen_status")
	private Boolean inenStatus;
	
		
	/**
	 * id sumario especie exotica y/o invasora
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="seas_id")
	private SummaryExoticAlienSpecie summaryExotic;
	
	/**
	 * Id del catalogo de tipo de dieta
	 */
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="bigc_id")
	private BiodiversityGeneralCatalog ambienteInvasion;
	
	@Getter
	@Setter
	@Transient
	String codigoAmbiente;
	
	
}
