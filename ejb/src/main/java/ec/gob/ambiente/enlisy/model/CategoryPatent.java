
package ec.gob.ambiente.enlisy.model;

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
 * The persistent class for the application database table.
 * 
 */
@Entity
@Table(name="category_patent", schema="biodiversity_mcm")
@NamedQuery(name="CategoryPatent.findAll", query="SELECT o FROM CategoryPatent o")
public class CategoryPatent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="capa_id")
	private Integer capaId;
	
	@Getter
	@Setter
	@Column(name="capa_code")
	private String code;
	
	@Getter
	@Setter
	@Column(name="capa_name")
	private String name;
	
	@Getter
	@Setter
	@Column(name="capa_description")
	private String description;
	
	@Getter
	@Setter
	@Column(name="capa_image_url")
	private String imageUrl;
	
	@Getter
	@Setter
	@Column(name="capa_order")
	private Integer order;
	
	@Getter
	@Setter
	@Column(name="capa_status")
	private Boolean status;
	
	@Getter
	@Setter
	@Column(name="capa_status_date")
	private Date statusDate;
	
	@Getter
	@Setter
	@Column(name="capa_creation_date")
	private Date creationDate;
	
	@Getter
	@Setter
	@Column(name="capa_creator_user")
	private String creationUser;
	
	@Getter
	@Setter
	@Column(name="capa_date_update")
	private Date dateUpdate;
	
	@Getter
	@Setter
	@Column(name="capa_user_update")
	private String userUpdate;
	
	@Getter
	@Setter
	@Column(name="capa_observation_bd")
	private String observationBd;
	
	@Getter
	@Setter
	@Column(name="capa_reg_1900")
	private Boolean reg1900;
	
	@Getter
	@Setter
	@Column(name="capa_gbif")
	private Boolean gbif;
	
	@Getter
	@Setter
	@Column(name="capa_species_specimens")
	private Boolean speciesSpecimens;
	
	@Getter
	@Setter
	@Column(name="capa_species")
	private Boolean species;
	
	@Getter
	@Setter
	@Column(name="capa_specimens")
	private Boolean specimens;
	
	@Getter
	@Setter
	@Column(name="capa_acronym")
	private Boolean acronym;
	
	@Getter
    @Setter
    @Column(name="capa_activity")
    private String actividad;

}