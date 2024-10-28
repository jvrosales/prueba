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
@Table(name = "acronym", schema = "biodiversity_mcm")
@NamedQuery(name = "Acronym.findAll", query = "SELECT o FROM Acronym o")
public class Acronym implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "acro_id")
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
	@Column(name = "acro_email")
	private String email;

	@Getter
	@Setter
	@Column(name = "acro_name")
	private String name;

	@Getter
	@Setter
	@Column(name = "acro_collection_name")
	private String collectionName;

	@Getter
	@Setter
	@Column(name = "acro_creation")
	private Integer creation;

	@Getter
	@Setter
	@Column(name = "acro_status")
	private Boolean status;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	/*
	 * @Getter
	 * 
	 * @Setter
	 * 
	 * @ManyToOne
	 * 
	 * @JoinColumn(name="task_id") private Task task;
	 */
	@Getter
	@Setter
	@Column(name = "acro_used")
	private Boolean used;

	@Getter
	@Setter
	@Column(name = "acro_creation_date")
	private Date creationDate;

	@Getter
	@Setter
	@Column(name = "acro_creator_user")
	private String creationUser;

	@Getter
	@Setter
	@Column(name = "acro_date_update")
	private Date dateUpdate;

	@Getter
	@Setter
	@Column(name = "acro_user_update")
	private String userUpdate;

	@Getter
	@Setter
	@Column(name = "acro_observation_bd")
	private String observationBd;

	@Getter
	@Setter
	@Column(name = "acro_curator_name")
	private String curatorName;

	@Getter
	@Setter
	@Column(name = "acro_curator_phone")
	private String curatorPhone;

	@Getter
	@Setter
	@Column(name = "acro_curator_email")
	private String curatorEmail;
}