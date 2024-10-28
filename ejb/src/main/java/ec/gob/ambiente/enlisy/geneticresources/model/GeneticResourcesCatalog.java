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
 * The persistent class for the genetic_resources_grcalogs database table.
 * 
 */
@Entity
@Table(name="genetic_resources_catalogs", schema="biodiversity")
@NamedQuery(name="GeneticResourcesCatalog.findAll", query="SELECT c FROM GeneticResourcesCatalog c")
public class GeneticResourcesCatalog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="grca_id")
	private Integer grcaId;
	
	@Getter
	@Setter
	@Column(name="grca_description")
	private String grcaDescription;
	
	@Getter
	@Setter
	@Column(name="grca_code")
	private String grcaCode;	
	
	@Getter
	@Setter
	@Column(name="grca_status")
	private Boolean grcaStatus;
	
	@Getter
	@Setter
	@Column(name="grca_user_create")
	private String grcaUserCreate;
	
	@Getter
	@Setter
	@Column(name="grca_date_create")
	private Date grcaDateCreate;
	
	@Getter
	@Setter
	@Column(name="grca_user_update")
	private String grcaUserUpdate;
	
	@Getter
	@Setter
	@Column(name="grca_date_update")
	private Date grcaDateUpdate;


}