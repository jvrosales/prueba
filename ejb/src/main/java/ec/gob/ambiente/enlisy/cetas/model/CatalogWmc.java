package ec.gob.ambiente.enlisy.cetas.model;

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
 * The persistent class for the genetic_resources_grcalogs database table.
 * 
 */
@Entity
@Table(name="catalog_wmc", schema="cetas")
@NamedQuery(name="CatalogWmc.findAll", query="SELECT c FROM CatalogWmc c")
public class CatalogWmc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cwmc_id")
	private Integer cwmcId;
	
	@Getter
	@Setter
	@Column(name="cwmc_id_parent")
	private Integer cwmcIdParent;
	
	@Getter
	@Setter
	@Column(name="cwmc_name")
	private String cwmcName;
	
	@Getter
	@Setter
	@Column(name="cwmc_description")
	private String cwmcDescription;
	
	@Getter
	@Setter
	@Column(name="cwmc_code")
	private String cwmcCode;
	
	@Getter
	@Setter
	@Column(name="cwmc_level")
	private Integer cwmcLevel;
	
	@Getter
	@Setter
	@Column(name="cwmc_status")
	private Boolean cwmcStatus;
	
	@Getter
	@Setter
	@Column(name="cwmc_user_create")
	private String cwmcUserCreate;
	
	@Getter
	@Setter
	@Column(name="cwmc_date_create")
	private Date cwmcDateCreate;
	
	@Getter
	@Setter
	@Column(name="cwmc_user_update")
	private String cwmcUserUpdate;
	
	@Getter
	@Setter
	@Column(name="cwmc_date_update")
	private Date cwmcDateUpdate;


}