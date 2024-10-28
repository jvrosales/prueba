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
@Table(name = "finalized_patent", schema = "biodiversity_mcm")
@NamedQuery(name = "FinalizedPatent.findAll", query = "SELECT o FROM FinalizedPatent o")
public class FinalizedPatent implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fipa_id")
	private Integer id;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="pain_id")
	private PatentInformation  patentInformation;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="pare_id")
	private PatentRequest patentRequest;
	
	@Getter
	@Setter
	@Column(name="fipa_status")
	private Boolean status;
	
	@Getter
	@Setter
	@Column(name="fipa_finalized")
	private Boolean finalizada;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Getter
	@Setter
	@Column(name="fipa_creation_date")
	private Date fipaCreationDate;
	
	@Getter
	@Setter
	@Column(name="fipa_creator_user")
	private String fipaCreatorUser;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Getter
	@Setter
	@Column(name="fipa_date_update")
	private Date fipaDateUpdate;
	
	@Getter
	@Setter
	@Column(name="fipa_user_update")
	private String fipaUserUpdate;
	
	@Getter
	@Setter
	@Column(name="fipa_observation_bd")
	private String fipaObservationBD;
	
	@Getter
	@Setter
	@Column(name="fipa_requesting_user")
	private String usuarioSolicitante;

	@Getter
	@Setter
	@Column(name="fipa_patent_status")
	private Boolean estadoPatente;

	@Getter
	@Setter
	@Column(name="fipa_patent_survey_status")
	private Boolean estadoEncuesta;

	@Getter
	@Setter
	@Column(name="fipa_inrenovation")
	private Boolean estadoRenovacion;

	@Getter
	@Setter
	@Column(name="fipa_download")
	private Boolean documentoDescargado;
	
	@Getter
	@Setter
	@Column(name="fipa_year")
	private Integer anioRenovacion;

}
