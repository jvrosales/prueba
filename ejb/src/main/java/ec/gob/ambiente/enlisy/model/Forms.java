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
@Table(name="Forms", schema="biodiversity_mcm")
@NamedQuery(name="Forms.findAll", query="SELECT o FROM Forms o")
public class Forms implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="form_id")
	private Integer Id;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="capa_id")
	private CategoryPatent categoryPatent;
	
	@Getter
	@Setter
	@Column(name="form_administrator")
	private Boolean administrator;
	
	@Getter
	@Setter
	@Column(name="form_people")
	private Boolean people;
	
	@Getter
	@Setter
	@Column(name="form_url")
	private String url;
	
	@Getter
	@Setter
	@Column(name="form_status")
	private Boolean status;
	
	@Getter
	@Setter
	@Column(name="form_status_date")
	private Date statusDate;
}