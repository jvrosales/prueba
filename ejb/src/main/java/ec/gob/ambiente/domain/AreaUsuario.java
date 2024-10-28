package ec.gob.ambiente.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
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

import org.hibernate.annotations.Filter;

import ec.gob.ambiente.enlisy.model.Area;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.suia.domain.base.EntidadAuditable;
import ec.gob.ambiente.suia.domain.base.EntidadBase;


/**
 * The persistent class for the areas_users database table.
 * 
 */
@Entity
@Table(name="areas_users", schema = "public")
@AttributeOverrides({
    @AttributeOverride(name = "estado", column = @Column(name = "arus_status")),
    @AttributeOverride(name = "fechaCreacion", column = @Column(name = "arus_creation_date")),
    @AttributeOverride(name = "fechaModificacion", column = @Column(name = "arus_date_update")),
    @AttributeOverride(name = "usuarioCreacion", column = @Column(name = "arus_creator_user")),
    @AttributeOverride(name = "usuarioModificacion", column = @Column(name = "arus_user_update")) })
@Filter(name = EntidadBase.FILTER_ACTIVE, condition = "arus_status = 'TRUE'")
@NamedQuery(name="AreaUsuario.findAll", query="SELECT a FROM AreaUsuario a")
public class AreaUsuario extends EntidadAuditable{
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="arus_id")
	private Integer id;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="area_id")
	private Area area;

	@Getter
	@Setter
	@Column(name="arus_order")
	private Integer orden;

	@Getter
	@Setter
	@Column(name="arus_principal")
	private Boolean principal;

	@Getter
	@Setter
	@Column(name="arus_user_assigns")
	private Integer usuarioAsignaArea;

	@Getter
	@Setter
	@Column(name="arus_user_withdraw")
	private Integer usuarioRetiraArea;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="user_id")
	private User usuario;
	
	@Getter
	@Setter
	@Column(name="user_id", insertable = false, updatable = false)
	private Integer idUsuario;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="unar_id")
	private UnidadArea unidadArea; 

	public AreaUsuario() {
	}

}