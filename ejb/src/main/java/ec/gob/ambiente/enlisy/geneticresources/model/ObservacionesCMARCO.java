package ec.gob.ambiente.enlisy.geneticresources.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.ForeignKey;

import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.suia.domain.base.EntidadBase;
import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the proposed_collection database table.
 * 
 */
@Entity
@Table(name="form_comments", schema="biodiversity")
@NamedQueries({
    @NamedQuery(name = ObservacionesCMARCO.LISTAR_POR_ID_CLASE_NOMBRE_CLASE_SECCION, query = "SELECT u FROM ObservacionesCMARCO u WHERE u.idClase = :idClase AND u.nombreClase = :nombreClase AND u.seccionFormulario in :seccion ORDER BY u.seccionFormulario, u.id"),
    @NamedQuery(name = ObservacionesCMARCO.LISTAR_POR_ID_CLASE_NOMBRE_CLASE, query = "SELECT u FROM ObservacionesCMARCO u WHERE u.idClase = :idClase AND u.nombreClase = :nombreClase ORDER BY u.id"),
    @NamedQuery(name = ObservacionesCMARCO.LISTAR_POR_ID_CLASE_NOMBRE_CLASE_NO_CORREGIDAS, query = "SELECT u FROM ObservacionesCMARCO u WHERE u.idClase = :idClase AND u.nombreClase = :nombreClase AND u.observacionCorregida = false ORDER BY u.seccionFormulario"),
    @NamedQuery(name = ObservacionesCMARCO.LISTAR_POR_ID_CLASE_NOMBRE_CLASE_USUARIO_NO_CORREGIDAS, query = "SELECT u FROM ObservacionesCMARCO u WHERE u.idClase = :idClase AND u.nombreClase = :nombreClase AND u.usuario.id = :idUsuario AND u.observacionCorregida = false ORDER BY u.seccionFormulario"),
    @NamedQuery(name = ObservacionesCMARCO.LISTAR_POR_ID_USUARIO, query = "SELECT u FROM ObservacionesCMARCO u WHERE u.usuario.id = :idUsuario"),
    @NamedQuery(name = ObservacionesCMARCO.LISTAR_POR_ID_CLASE_SECCION_NO_CORREGIDAS, query = "SELECT u FROM ObservacionesCMARCO u WHERE u.idClase = :idClase AND u.seccionFormulario in :seccion AND u.observacionCorregida = false ORDER BY u.seccionFormulario, u.id")
    })
@AttributeOverrides({@AttributeOverride(name = "estado", column = @Column(name = "foco_status"))})
@Filter(name = EntidadBase.FILTER_ACTIVE, condition = "foco_status = 'TRUE'")
public class ObservacionesCMARCO extends EntidadBase implements Serializable {
    private static final long serialVersionUID = 5016494380218175574L;
    private static final String PAQUETE = "ec.gob.ambiente.enlisy.geneticresources.model.";
    public static final String LISTAR_POR_ID_CLASE_NOMBRE_CLASE_SECCION = PAQUETE + "ObservacionesCMARCO.listaPorIdClaseNombreClaseSeccion";
    public static final String LISTAR_POR_ID_CLASE_NOMBRE_CLASE = PAQUETE + "ObservacionesCMARCO.listaPorIdClaseNombreClase";
    public static final String LISTAR_POR_ID_CLASE_NOMBRE_CLASE_NO_CORREGIDAS = PAQUETE + "ObservacionesCMARCO.listaPorIdClaseNombreClaseNoCorregidas";
    public static final String LISTAR_POR_ID_CLASE_NOMBRE_CLASE_USUARIO_NO_CORREGIDAS = PAQUETE + "ObservacionesCMARCO.listaPorIdClaseNombreClaseUsuarioNoCorregidas";
    public static final String LISTAR_POR_ID_USUARIO = PAQUETE + "ObservacionesCMARCO.listaPorIdUsuario";
    public static final String LISTAR_POR_ID_CLASE_SECCION_NO_CORREGIDAS = PAQUETE + "ObservacionesCMARCO.listaPorIdClaseSeccionNoCorregidas";
    
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "foco_id")
    private Integer id;
    @Getter
    @Setter
    @Column(name = "foco_field", length = 255)
    private String campo;
    @Getter
    @Setter
    @Column(name = "foco_description", length = 500)
    private String descripcion;
    @Getter
    @Setter
    @Column(name = "foco_class_name", length = 255)
    private String nombreClase;
    @Getter
    @Setter
    @Column(name = "foco_id_class")
    private Integer idClase;
    @Getter
    @Setter
    @Column(name = "foco_section_form", length = 500)
    private String seccionFormulario;
    @Getter
    @Setter
    @Transient
    private int indice;
    @Getter
    @Setter
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne
    @ForeignKey(name = "fk_form_comments_user_id_user_user_id")
    private User usuario;
    @Getter
    @Setter
    @Column(insertable = false, updatable = false, name = "user_id")
    private Integer idUsuario;
    @Getter
    @Setter
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "foco_date")
    private Date fechaRegistro;
    @Getter
    @Setter
    @Transient
    private boolean disabled;
    @Getter
    @Setter
    @Column(name = "foco_observation_corrected")
    private boolean observacionCorregida;
    @Getter
    @Setter
    @Column(name = "taskId")
    private Long idTarea;

    @Getter
    @Setter
    @Column(name = "foco_answer", length = 500)
    private String respuesta;
	
	@Getter
	@Setter
	@Column(name="foco_creator_user")
	private String focoCreatorUser;
	
	@Getter
	@Setter
	@Column(name="foco_creation_date")
	private Date focoCreationDate;
	
	@Getter
	@Setter
	@Column(name="foco_user_update")
	private String focoUserUpdate;
	
	@Getter
	@Setter
	@Column(name="foco_date_update")
	private Date focoUpdateDate;    
}