package ec.gob.ambiente.enlisy.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the province_areas_snap database table.
 * 
 */
@Entity
@Table(name="province_areas_snap", schema="coa_mae")
@NamedQuery(name="ProvinceAreasSnap.findAll", query="SELECT p FROM ProvinceAreasSnap p")
public class ProvinceAreasSnap implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="prar_id")
	private Integer prarId;

	@Column(name="gelo_id")
	private Integer geloId;

	@Column(name="prar_abbreviation")
	private String prarAbbreviation;

	@Column(name="prar_creation_date")
	private Timestamp prarCreationDate;

	@Column(name="prar_creator_user")
	private String prarCreatorUser;

	@Column(name="prar_csnap")
	private String prarCsnap;

	@Column(name="prar_date_update")
	private Timestamp prarDateUpdate;

	@Column(name="prar_gid_snap")
	private Integer prarGidSnap;

	@Column(name="prar_inec_province")
	private String prarInecProvince;

	@Column(name="prar_observation_bd")
	private String prarObservationBd;

	@Column(name="prar_protected_area")
	private String prarProtectedArea;

	@Column(name="prar_protected_area_bd")
	private String prarProtectedAreaBd;

	@Column(name="prar_province")
	private String prarProvince;

	@Column(name="prar_status")
	private Boolean prarStatus;

	@Column(name="prar_suap_snap")
	private String prarSuapSnap;

	@Column(name="prar_subsytem_type")
	private Integer prarSubsytemType;

	@Column(name="prar_user_update")
	private String prarUserUpdate;

	@Column(name="user_id")
	private Integer userId;

	public ProvinceAreasSnap() {
	}

	public Integer getPrarId() {
		return this.prarId;
	}

	public void setPrarId(Integer prarId) {
		this.prarId = prarId;
	}

	public Integer getGeloId() {
		return this.geloId;
	}

	public void setGeloId(Integer geloId) {
		this.geloId = geloId;
	}

	public String getPrarAbbreviation() {
		return this.prarAbbreviation;
	}

	public void setPrarAbbreviation(String prarAbbreviation) {
		this.prarAbbreviation = prarAbbreviation;
	}

	public Timestamp getPrarCreationDate() {
		return this.prarCreationDate;
	}

	public void setPrarCreationDate(Timestamp prarCreationDate) {
		this.prarCreationDate = prarCreationDate;
	}

	public String getPrarCreatorUser() {
		return this.prarCreatorUser;
	}

	public void setPrarCreatorUser(String prarCreatorUser) {
		this.prarCreatorUser = prarCreatorUser;
	}

	public String getPrarCsnap() {
		return this.prarCsnap;
	}

	public void setPrarCsnap(String prarCsnap) {
		this.prarCsnap = prarCsnap;
	}

	public Timestamp getPrarDateUpdate() {
		return this.prarDateUpdate;
	}

	public void setPrarDateUpdate(Timestamp prarDateUpdate) {
		this.prarDateUpdate = prarDateUpdate;
	}

	public Integer getPrarGidSnap() {
		return this.prarGidSnap;
	}

	public void setPrarGidSnap(Integer prarGidSnap) {
		this.prarGidSnap = prarGidSnap;
	}

	public String getPrarInecProvince() {
		return this.prarInecProvince;
	}

	public void setPrarInecProvince(String prarInecProvince) {
		this.prarInecProvince = prarInecProvince;
	}

	public String getPrarObservationBd() {
		return this.prarObservationBd;
	}

	public void setPrarObservationBd(String prarObservationBd) {
		this.prarObservationBd = prarObservationBd;
	}

	public String getPrarProtectedArea() {
		return this.prarProtectedArea;
	}

	public void setPrarProtectedArea(String prarProtectedArea) {
		this.prarProtectedArea = prarProtectedArea;
	}

	public String getPrarProtectedAreaBd() {
		return this.prarProtectedAreaBd;
	}

	public void setPrarProtectedAreaBd(String prarProtectedAreaBd) {
		this.prarProtectedAreaBd = prarProtectedAreaBd;
	}

	public String getPrarProvince() {
		return this.prarProvince;
	}

	public void setPrarProvince(String prarProvince) {
		this.prarProvince = prarProvince;
	}

	public Boolean getPrarStatus() {
		return this.prarStatus;
	}

	public void setPrarStatus(Boolean prarStatus) {
		this.prarStatus = prarStatus;
	}

	public String getPrarSuapSnap() {
		return this.prarSuapSnap;
	}

	public void setPrarSuapSnap(String prarSuapSnap) {
		this.prarSuapSnap = prarSuapSnap;
	}

	public Integer getPrarSubsytemType() {
		return this.prarSubsytemType;
	}

	public void setPrarSubsytemType(Integer prarSubsytemType) {
		this.prarSubsytemType = prarSubsytemType;
	}

	public String getPrarUserUpdate() {
		return this.prarUserUpdate;
	}

	public void setPrarUserUpdate(String prarUserUpdate) {
		this.prarUserUpdate = prarUserUpdate;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}