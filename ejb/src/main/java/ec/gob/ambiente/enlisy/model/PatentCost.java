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
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cost_value", schema = "biodiversity_mcm")
@NamedQuery(name = "PatentCost.findAll", query = "SELECT o FROM PatentCost o")
public class PatentCost implements Serializable {
	private static final long serialVersionUID = 1L;


	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cova_id")
	private Integer id;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "pare_id")
	private PatentRequest patentRequest;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "ctpa_id")
	private CatalogoPatentes catalogoPatentes;

	@Getter
	@Setter
	@Column(name="cova_value")
	private Double covaValue;
	
	@Getter
	@Setter
	@Transient
	private String value;
	
	@Getter
	@Setter
	@Column(name="cova_status")
	private Boolean covaStatus;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Getter
	@Setter
	@Column(name="cova_status_date")
	private Date covaStatusDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Getter
	@Setter
	@Column(name="cova_creation_date")
	private Date covaCreationDate;
	
	@Getter
	@Setter
	@Column(name="cova_creator_user")
	private String covaCreatorUser;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Getter
	@Setter
	@Column(name="cova_date_update")
	private Date covaDateUpdate;
	
	@Getter
	@Setter
	@Column(name="cova_user_update")
	private String covaUserUpdate;
	
	@Getter
	@Setter
	@Column(name="cova_observation_bd")
	private String covaObservationBd;
		
}
