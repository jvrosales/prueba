package ec.gob.ambiente.enlisy.speciescatalog.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the cites database table.
 * 
 */
@Entity
@Table(name = "cites", schema = "biodiversity")
public class Cite implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CITES_GENERATOR")
    @SequenceGenerator(name = "CITES_GENERATOR", initialValue = 1, sequenceName = "seq_cite_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name = "cite_id")
	private Integer citeId;

	/**
	 * nombre de la cita
	 */
	@Getter
	@Setter
	@Column(name = "cite_name")
	private String citeName;

	/**
	 * Usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name = "cite_user_create")
	private String citeUserCreate;

	/**
	 * Fecha de creacion del registro
	 */
	@Getter
	@Setter
	@Column(name = "cite_date_create")
	private Date citeDateCreate;

	/**
	 * Usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name = "cite_user_update")
	private String citeUserpdate;

	/**
	 * Fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name = "cite_date_update")
	private Date citeDateUpdate;

	/**
	 * Bandera que indica si el registro esta activo
	 */
	@Getter
	@Setter
	@Column(name = "cite_status")
	private Boolean citeStatus;

	@Getter
	@Setter
	@Column(name = "cite_level")
	private String citeLevel;

	@Transient
	private boolean selected = false;

	@Transient
	public boolean isSelected() {
		return selected;
	}

	@Transient
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
