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

/**
 * The persistent class for the application database table.
 *
 */
@Entity
@Table(name = "patent_technical_report_comments", schema = "biodiversity_mcm")
@NamedQuery(name = "PatentTechnicalReportComments.findAll", query = "SELECT o FROM PatentTechnicalReportComments o")

//@AttributeOverrides({
//	@AttributeOverride(name = "estado", column = @Column(name = "ptrc_status_date")),
//	@AttributeOverride(name = "fechaCreacion", column = @Column(name = "ptrc_date_create")),
//	@AttributeOverride(name = "fechaModificacion", column = @Column(name = "ptrc_date_update")),
//	@AttributeOverride(name = "usuarioCreacion", column = @Column(name = "ptrc_user_create")),
//	@AttributeOverride(name = "usuarioModificacion", column = @Column(name = "ptrc_user_update")) })
//@Filter(name = EntidadBase.FILTER_ACTIVE, condition = "ptrc_status = 'TRUE'")

public class PatentTechnicalReportComments implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ptrc_id")
	private Integer id;

	@Getter
	@Setter
	@Column(name = "ptrc_description")
	private String descripcion;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="pain_id")
	private PatentInformation patentInformation;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="pare_id")
	private PatentRequest patentRequest;
    
	@Getter
	@Setter
	@Column(name = "ptrc_observation_number")
	private String numero_revision;
    
	@Getter
	@Setter
	@Column(name = "ptrc_creation_date")
	private Date fechaCreacion;
    
	@Getter
	@Setter
	@Column(name = "ptrc_status")
	private Boolean status;
//    
//	@Temporal(TemporalType.TIMESTAMP)
//	@Getter
//	@Setter
//	@Column(name="ptrc_creation_date")
//	private Date ptrcCreationDate;
	
	@Getter
	@Setter
	@Column(name="ptrc_user_create")
	private String ptrcCreatorUser;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Getter
	@Setter
	@Column(name="ptrc_date_update")
	private Date ptrcDateUpdate;
	
	@Getter
	@Setter
	@Column(name="ptrc_user_update")
	private String ptrcUserUpdate;
	
	@Getter
	@Setter
	@Column(name="ptrc_observation_bd")
	private String ptrcObservationBD;


}
