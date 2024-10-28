package ec.gob.ambiente.sib_suia_ora.catalogobiodiversidad.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the cites database table.
 * 
 */
@Entity
@Table(name = "users_access_web_services", schema = "biodiversity")
public class UserAccessWebService implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uaws_id")
	private Integer uawsId;

	/**
	 * Id del usuario
	 */
	@Getter
	@Setter
	@Column(name = "user_id")
	private Integer userId;

	/**
	 * Bandera que indica el acceso al sw de catalogo de objetos biologicos
	 */
	@Getter
	@Setter
	@Column(name = "uaws_ws_catalog_taxonomic")
	private Boolean uawsWsCatalogTaxonomic;

	/**
	 * Bandera que indica el acceso al sw de sumarios
	 */
	@Getter
	@Setter
	@Column(name = "uaws_ws_summary")
	private Boolean uawsWsSummary;

	/**
	 * Estado que determina si el acceso esta registrado o ya fue aprobado por el administrador del catalogo
	 */
	@Getter
	@Setter
	@Column(name = "uaws_access_status")
	private String uawsAccessStatus;
	
	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name = "uaws_user_create")
	private String uawsUserCreate;
	
	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name = "uaws_user_update")
	private String uawsUserUpdate;

	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name = "uaws_date_create")
	private Date uawsDateCreate;
	
	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name = "uaws_date_update")
	private Date uawsDateUpdate;

	/**
	 * Bandera que indica si el registro esta activo
	 */
	@Getter
	@Setter
	@Column(name = "uaws_status")
	private Boolean uawsStatus;

	/**
	 * Username
	 */
	@Getter
	@Setter
	@Transient
	String username;
	
	/**
	 * Bandera que indica el acceso al sw de cites
	 */
	@Getter
	@Setter
	@Column(name = "uaws_ws_cites")
	private Boolean uawsWsCites;
	
	/**
	 * Username
	 */
	@Getter
	@Setter
	@Transient
	String nombre;
	
	/**
	 * Username
	 */
	@Getter
	@Setter
	@Transient
	String email;
}
