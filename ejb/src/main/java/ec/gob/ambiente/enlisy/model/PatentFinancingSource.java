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
@Table(name = "financing_source", schema = "biodiversity_mcm")
@NamedQuery(name = "PatentFinancingSource.findAll1", query = "SELECT o FROM PatentFinancingSource o")
public class PatentFinancingSource implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fiso_id")
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
	private CatalogoPatentes ctpaCargoAdmin;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "ctpa_id_tipo")
	private CatalogoPatentes ctpaIdTipo;

	@Getter
	@Setter
	@Column(name = "fiso_ruc")
	private String fisoRuc;

	@Getter
	@Setter
	@Column(name = "fiso_name_ruc")
	private String fisoNameRuc;

	@Getter
	@Setter
	@Column(name = "fiso_value")
	private double fisoValue;

	@Getter
	@Setter
	@Column(name = "fiso_status")
	private Boolean fisoStatus;

	@Temporal(TemporalType.TIMESTAMP)
	@Getter
	@Setter
	@Column(name = "fiso_status_date")
	private Date fisoStatusDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Getter
	@Setter
	@Column(name = "fiso_creation_date")
	private Date fisoCreationDate;

	@Getter
	@Setter
	@Column(name = "fiso_creator_user")
	private String fisoCreatorUser;

	@Temporal(TemporalType.TIMESTAMP)
	@Getter
	@Setter
	@Column(name = "fiso_date_update")
	private Date fisoDateUpdate;

	@Getter
	@Setter
	@Column(name = "fiso_user_update")
	private String fisoUserUpdate;

	@Getter
	@Setter
	@Column(name = "fiso_observation_bd")
	private String fisoObservationBd;

}
