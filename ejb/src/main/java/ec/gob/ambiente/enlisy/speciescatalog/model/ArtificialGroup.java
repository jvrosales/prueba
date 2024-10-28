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
 * The persistent class for the artificial_groups database table.
 * 
 */
@Entity
@Table(name = "artificial_groups", schema = "biodiversity")
public class ArtificialGroup implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Clave primaria
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ARTIFICIAL_GROUPS_GENERATOR")
    @SequenceGenerator(name = "ARTIFICIAL_GROUPS_GENERATOR", initialValue = 1, sequenceName = "seq_argr_id", schema = "biodiversity", allocationSize = 1)
   	@Column(name = "argr_id")
	private Integer argrId;

	/**
	 * Nombre del grupo artificial
	 */
	@Getter
	@Setter
	@Column(name = "argr_name")
	private String argrName;

	/**
	 * Codigo del grupo
	 */
	@Getter
	@Setter
	@Column(name = "argr_code")
	private String argrCode;

	/**
	 * Codigo del grupo artifiicial padre
	 */
	@Getter
	@Setter
	@Column(name = "argr_id_parent")
	private Integer argrIdParent;

	/**
	 * bandera que indica si el grupo es del reino animal
	 */
	@Getter
	@Setter
	@Column(name = "argr_animal")
	private Boolean argrAnimal;

	/**
	 * bandera que indica si el grupo pertenece al reino plantae
	 */
	@Getter
	@Setter
	@Column(name = "argr_plantae")
	private Boolean argrPlantae;

	/**
	 * bandera que indica si el grupo pertenece al reino fungi
	 */
	@Getter
	@Setter
	@Column(name = "argr_fungi")
	private Boolean argrFungi;

	/**
	 * bandera que indica que el grupo pertenece al reino eubacteria
	 */
	@Getter
	@Setter
	@Column(name = "argr_eubacteria")
	private Boolean argrEubacteria;

	/**
	 * bandera que indica que el grupo pertenece al reino arqueobacteria
	 */
	@Getter
	@Setter
	@Column(name = "argr_archeobacteria")
	private Boolean argrArcheobacteria;

	/**
	 * bandera que indica que el grupo pertenece al reino protista
	 */
	@Getter
	@Setter
	@Column(name = "argr_protista")
	private Boolean argrProtista;

	@Getter
	@Setter
	@Column(name = "argr_biodiversity")
	private Boolean argrBiodiversity;

	/**
	 * estado del registro
	 */
	@Getter
	@Setter
	@Column(name = "argr_status")
	private Boolean argrStatus;

	/**
	 * usuario que crea el registro
	 */
	@Getter
	@Setter
	@Column(name = "argr_user_create")
	private String argrUserCreate;

	/**
	 * fecha que se crea el registro
	 */
	@Getter
	@Setter
	@Column(name = "argr_date_create")
	private Date argrDateCreate;

	/**
	 * usuario que actualiza el registro
	 */
	@Getter
	@Setter
	@Column(name = "argr_user_update")
	private String argrUserUpdate;

	/**
	 * fecha de actualizacion del registro
	 */
	@Getter
	@Setter
	@Column(name = "argr_date_update")
	private Date argrDateUpdate;
	
	/**
	 * Campo que concatena los reinos
	 */
	@Getter
	@Setter
	@Transient
	private String reinos;

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
	
	/**
	 * bandera que indica que el estado pertenece al reino chromista
	 */
	@Getter
	@Setter
	@Column(name="argr_chromista")
	private Boolean argrChromista;
	
	/**
	 * bandera que indica que el estado pertenece al reino viruses
	 */
	@Getter
	@Setter
	@Column(name="argr_viruses")
	private Boolean argrViruses;

}
