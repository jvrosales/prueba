package ec.gob.ambiente.enlisy.geneticresources.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the export_authorization database table.
 * 
 */
@Entity
@Table(name = "unidentified_species", schema = "biodiversity")
@NamedQuery(name = "UnidentifiedSpecies.findAll", query = "SELECT o FROM UnidentifiedSpecies o where o.unspStatus = true")
public class UnidentifiedSpecies implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	@Override
	public UnidentifiedSpecies clone() throws CloneNotSupportedException {

		UnidentifiedSpecies clone = (UnidentifiedSpecies) super.clone();
		clone.setUnspId(null);
		clone.setUnspUserUpdate(null);
		clone.setUnspDateUpdate(null);
		return clone;
	}
	
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "unsp_id")
	private Integer unspId;
	
	@Getter
	@Setter
	@Column(name="unsp_specie_name")
	private String unspSpecieName;
	
	@Getter
	@Setter
	@Column(name="unsp_specie_genus")
	private String unspSpecieGenus;
	
	@Getter
	@Setter
	@Column(name="unsp_specie_family")
	private String unspSpecieFamily;
	
	@Getter
	@Setter
	@Column(name="unsp_specie_order")
	private String unspSpecieOrder;
	
	@Getter
	@Setter
	@Column(name="unsp_specie_class")
	private String unspSpecieClass;
	
	@Getter
	@Setter
	@Column(name="unsp_specie_kingdom")
	private String unspSpecieKingdom;
	
	@Getter
	@Setter
	@Column(name="unsp_id_initial_specie")
	private Integer unspIdInitialSpecie;
	
	@Getter
	@Setter
	@Column(name = "unsp_is_historical")
	private Boolean unspIsHistorical;
	
	@Getter
	@Setter
	@Column(name="unsp_status")
	private Boolean unspStatus;
	
	@Getter
	@Setter
	@Column(name="unsp_user_create")
	private String unspUserCreate;
	
	@Getter
	@Setter
	@Column(name="unsp_date_create")
	private Date unspDateCreate;
	
	@Getter
	@Setter
	@Column(name="unsp_user_update")
	private String unspUserUpdate;
	
	@Getter
	@Setter
	@Column(name="unsp_date_update")
	private Date unspDateUpdate;
	
	@Getter
	@Setter
	@Transient
	private Boolean isNewItem = true;

}
