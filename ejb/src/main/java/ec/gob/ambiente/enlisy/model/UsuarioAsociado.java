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
@Table(name="link_users_biodiversity", schema="public")
@NamedQuery(name="UsuarioAsociado.findAll", query="SELECT o FROM UsuarioAsociado o")
public class UsuarioAsociado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="lubi_id")
	private Integer lubiId;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="orga_id")
	private Organization organizacion;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="user_id")
	private User userAsociado;
	
	@Getter
	@Setter
	@Column(name="lubi_status")
	private Boolean lubiStatus;
	
	@Getter
	@Setter
	@Column(name="lubi_patents")
	private Boolean lubiPatents;
	
	@Getter
	@Setter
	@Column(name="lubi_contract")
	private Boolean lubiContract;
	
	@Getter
	@Setter
	@Column(name="lubi_status_date")
	private Date lubiStatusDate;
	
	@Getter
	@Setter
	@Column(name="lubi_creation_date")
	private Date lubiCreationDate;
	
	@Getter
	@Setter
	@Column(name="lubi_creator_user")
	private String lubiCreatorUser;	
	
	@Getter
	@Setter
	@Column(name="lubi_date_update")
	private Date lubiDateUpdate;
	
	@Getter
	@Setter
	@Column(name="lubi_user_update")
	private String lubiUseUpdate;
	
	@Getter
	@Setter
	@Column(name="lubi_observation_bd")
	private String lubiObservationBd;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="peop_id")
	private People people;
}