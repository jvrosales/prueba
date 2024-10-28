package ec.gob.ambiente.enlisy.geneticresources.model;

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

import ec.gob.ambiente.enlisy.model.Organization;
import ec.gob.ambiente.enlisy.model.People;
import ec.gob.ambiente.enlisy.model.User;
import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the application database table.
 * 
 */
@Entity
@Table(name="research_applications", schema="biodiversity")
@NamedQuery(name="ResearchApplication.findAll", query="SELECT o FROM ResearchApplication o")
public class ResearchApplication implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="reap_id")
	private Integer reapId;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
		
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="peop_id_tutor")
	private People peopleTutor;
		
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="orga_id")
	private Organization organization;
		
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="grca_id_type_application")
	private GeneticResourcesCatalog typeApplication;
		
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="grca_id_type_applicant")
	private GeneticResourcesCatalog typeApplicant;
	
	@Getter
	@Setter
	@Column(name="reap_code")
	private String reapCode;	

	@Getter
	@Setter
	@Column(name="reap_title")
	private String reapTitle;
	
	@Getter
	@Setter
	@Column(name="reap_senecyt_number_user")
	private String reapSenecytNumberUser;
	
	@Getter
	@Setter
	@Column(name="reap_senecyt_number_tutor")
	private String reapSenecytNumberTutor;
	
	@Getter
	@Setter
	@Column(name="reap_senecyt_number_institution")
	private String reapSenecytNumberInstitution;
	
	@Getter
	@Setter
	@Column(name="reap_mae_acreditation_number_user")
	private String reapMaeAcreditationNumberUser;
	
	@Getter
	@Setter
	@Column(name="reap_mae_acreditation_number_tutor")
	private String reapMaeAcreditationNumberTutor;
	
	@Getter
	@Setter
	@Column(name="reap_mae_acreditation_number_institution")
	private String reapMaeAcreditationNumberInstitution;
	
	@Getter
	@Setter
	@Column(name="reap_institution_activity")
	private String reapInstitutionActivity;
	
	@Getter
	@Setter
	@Column(name="reap_tutor_position")
	private String reapTutorPosition;
	
	@Getter
	@Setter
	@Column(name="reap_status")
	private Boolean reapStatus;
	
	@Getter
	@Setter
	@Column(name="reap_user_create")
	private String reapUserCreate;
	
	@Getter
	@Setter
	@Column(name="reap_date_create")
	private Date reapDateCreate;
	
	@Getter
	@Setter
	@Column(name="reap_user_update")
	private String reapUserUpdate;
	
	@Getter
	@Setter
	@Column(name="reap_date_update")
	private Date reapDateUpdate;
	
	@Getter
	@Setter
	@Column(name="reap_additional_email")
	private String reapAdditionalEmail;
}
