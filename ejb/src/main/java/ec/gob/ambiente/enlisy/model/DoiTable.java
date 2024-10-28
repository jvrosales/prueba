package ec.gob.ambiente.enlisy.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "doi_table", schema = "biodiversity_mcm")
@NamedQuery(name = "DoiTable.findAll", query = "SELECT o FROM DoiTable o")
public class DoiTable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dota_id")
	private Integer id;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "pain_id")
	private PatentInformation patentInformation;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "pare_id")
	private PatentRequest patentRequest;

	@Getter
	@Setter
	@Column(name = "dota_doi_url")
	private String url;
	
	@Getter
	@Setter
	@Column(name = "dota_doi")
	private String doi;

	@Getter
	@Setter
	@Column(name = "dota_status")
	private Boolean status;

	@Temporal(TemporalType.TIMESTAMP)
	@Getter
	@Setter
	@Column(name = "dota_creation_date")
	private Date dotaCreationDate;

	@Getter
	@Setter
	@Column(name = "dota_creator_user")
	private String dotaCreatorUser;

	@Temporal(TemporalType.TIMESTAMP)
	@Getter
	@Setter
	@Column(name = "dota_date_update")
	private Date dotaDateUpdate;

	@Getter
	@Setter
	@Column(name = "dota_user_update")
	private String dotaUserUpdate;

	@Getter
	@Setter
	@Column(name = "dota_observation_bd")
	private String dotaOnservationBD;

}
