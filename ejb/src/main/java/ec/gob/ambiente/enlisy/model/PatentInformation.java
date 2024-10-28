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

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the application database table.
 * 
 */
@Entity
@Table(name = "patent_information", schema = "biodiversity_mcm")
@NamedQuery(name = "PatentInformation.findAll", query = "SELECT o FROM PatentInformation o")
public class PatentInformation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pain_id")
	private Integer Id;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "lubi_id")
	private UsuarioAsociado usuarioAsociado;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "capa_id")
	private CategoryPatent categoryPatent;

	@Getter
	@Setter
	@Column(name = "pain_ruc")
	private String ruc;

	@Getter
	@Setter
	@Column(name = "pain_preliminary")
	private String preliminary;

	@Getter
	@Setter
	@Column(name = "pain_sequence")
	private String secuence;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "acro_id")
	private Acronym acronym;

	@Getter
	@Setter
	@Column(name = "pain_unique_code")
	private String uniqueCode;

	@Getter
	@Setter
	@Column(name = "pain_status")
	private Boolean status;

	@Getter
	@Setter
	@Column(name = "pain_status_date")
	private Date statusDate;

	@Getter
	@Setter
	@Column(name = "pain_creation_date")
	private Date creationDate;

	@Getter
	@Setter
	@Column(name = "pain_creator_user")
	private String creatorUser;

	@Getter
	@Setter
	@Column(name = "pain_date_update")
	private Date dateUpdate;

	@Getter
	@Setter
	@Column(name = "pain_user_update")
	private String userUpdate;

	@Getter
	@Setter
	@Column(name = "pain_observation_bd")
	private String painObservationBd;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "pain_user_id")
	private User user;

	@Getter
	@Setter
	@Column(name = "pain_secuence_int")
	private Integer secuenceInt;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "paac_id")
	private PatentActivationMCM patentActivation;

	@Getter
	@Setter
	@Column(name = "pain_code")
	private String code;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "ctpa_id_pronouncement")
	private CatalogoPatentes catalogoPatentes;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "form_id")
	private Forms forms;
	
	@Getter
	@Setter
	@Column(name = "pain_name_mcm")
	private String painNameMcm;
	
	// Campo para Renovacion
	@Getter
	@Setter
	@Column(name = "pain_pare_id")
	private Integer painPareId;
	
	// Hijo del patent Information
	@Getter
	@Setter
	@Column(name = "pare_id")
	private Integer pareId;
	
	@Getter
	@Setter
	@Column(name = "pain_status_patent")
	private Integer painStatusPatent;
	
	@Getter
	@Setter
	@Column(name = "pain_status_patent_date")
	private Date painStatusPatentDate;
	
	@Getter
	@Setter
	@Column(name = "pain_final_patent_end_date")
	private Date finalPatentEndDate;
	
	@Getter
	@Setter
	@Column(name = "pain_final_patent_start_date")
	private Date finalPatentStartDate;
	
	@Getter
	@Setter
	@Column(name = "pain_provisional_patent_end_date")
	private Date provisionalPatentEndDate;
	
	@Getter
	@Setter
	@Column(name = "pain_provisional_patent_start_date")
	private Date provisionalPatentStartDate;

}