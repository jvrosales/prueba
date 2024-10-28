package ec.gob.ambiente.enlisy.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the roles_users database table.
 * 
 */
@Entity
@Table(name="roles_users", schema="suia_iii")
@NamedQuery(name="RolesUser.findAll", query="SELECT r FROM RolesUser r")
public class RolesUser implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String FIND_BY_USER_ROLE = "ec.com.magmasoft.business.domain.RolUsuario.findByUserRol";
	public static final String FIND_BY_USERa = "ec.com.magmasoft.business.domain.RolUsuario.findByUser";
	public static final String COUNT_ROL_USUARIO = "ec.com.magmasoft.business.domain.RolUsuario.countRolUsuario";


	@Id
	@SequenceGenerator(name = "ROUS_ID_GENERATOR", initialValue = 1, sequenceName = "seq_rous_id", schema = "suia_iii", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROUS_ID_GENERATOR")
	@Column(name="rous_id")
	private Integer rousId;

	@Column(name="rous_status")
	private Boolean rousStatus;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="role_id")
	private Role role;
	
	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public RolesUser() {
	}

	public Integer getRousId() {
		return this.rousId;
	}

	public void setRousId(Integer rousId) {
		this.rousId = rousId;
	}

	public Boolean getRousStatus() {
		return this.rousStatus;
	}

	public void setRousStatus(Boolean rousStatus) {
		this.rousStatus = rousStatus;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}