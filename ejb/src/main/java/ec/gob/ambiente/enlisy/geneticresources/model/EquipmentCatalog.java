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

import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the equipment_catalogs database table.
 * 
 */
@Entity
@Table(name="equipment_catalogs", schema="biodiversity")
@NamedQuery(name="EquipmentCatalog.findAll", query="SELECT o FROM EquipmentCatalog o")
public class EquipmentCatalog implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public EquipmentCatalog()
	{		
	}
	
	public EquipmentCatalog(String description)
	{
		setEqcaDescription(description);
	}

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="eqca_id")
	private Integer eqcaId;
	
	@Getter
	@Setter
	@Column(name="eqca_description")
	private String eqcaDescription;
	
	@Getter
	@Setter
	@Column(name="eqca_status")
	private Boolean eqcaStatus;
	
	@Getter
	@Setter
	@Column(name="eqca_user_create")
	private String eqcaUserCreate;
	
	@Getter
	@Setter
	@Column(name="eqca_date_create")
	private Date eqcaDateCreate;
	
	@Getter
	@Setter
	@Column(name="eqca_user_update")
	private String eqcaUserUpdate;
	
	@Getter
	@Setter
	@Column(name="eqca_date_update")
	private Date eqcaDateUpdate;


}