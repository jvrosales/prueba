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
@Table(name = "data_record_type", schema = "biodiversity_mcm")
@NamedQuery(name = "DataRecordType.findAll", query = "SELECT o FROM DataRecordType o")
public class DataRecordType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "drty_id")
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
	@Column(name = "drty_value")
	private Boolean drtyValue;
	
	@Getter
	@Setter
	@Column(name = "drty_description")
	private String drtyDescription;
	
	@Getter
	@Setter
	@Column(name = "drty_status")
	private Boolean drtyStatus;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Getter
	@Setter
	@Column(name = "drty_status_date")
	private Date drtyStatusDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Getter
	@Setter
	@Column(name = "drty_creation_date")
	private Date drtyCreationDate;
	
	@Getter
	@Setter
	@Column(name = "drty_creator_user")
	private String drtyCreatorUser;
		
	@Temporal(TemporalType.TIMESTAMP)
	@Getter
	@Setter
	@Column(name = "drty_date_update")
	private Date drtyDateUpdate;
	
	@Getter
	@Setter
	@Column(name = "drty_user_update")
	private String drtyUserUpdate;
	
}
