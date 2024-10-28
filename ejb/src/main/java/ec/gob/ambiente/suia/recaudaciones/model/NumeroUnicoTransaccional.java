package ec.gob.ambiente.suia.recaudaciones.model;

import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.ForeignKey;

import ec.gob.ambiente.suia.domain.base.EntidadAuditable;
import ec.gob.ambiente.suia.domain.base.EntidadBase;

/**
 * 
 * <b> Entity que representa la tabla NumeroUnicoTransaccional. </b>
 * 
 * @author Santiago Flores
 * @version Revision: 1.0
 *          <p>
 *          [Autor: Santiago Flores, Fecha: 29/05/2019]
 *          </p>
 */
@Entity
@Table(name = "unique_transaction_number", schema = "payments")
@NamedQueries({
		@NamedQuery(name = NumeroUnicoTransaccional.LISTAR_TODOS, query = "SELECT c FROM NumeroUnicoTransaccional c WHERE c.estado= true"),
		@NamedQuery(name = NumeroUnicoTransaccional.LISTAR_POR_ID, query = "SELECT c FROM NumeroUnicoTransaccional c WHERE c.estado= true and c.id=:id"),
		@NamedQuery(name = NumeroUnicoTransaccional.LISTAR_POR_SOLICITUD, query = "SELECT c FROM NumeroUnicoTransaccional c WHERE c.estado= true and c.solicitudUsuario.solicitudCodigo=:solicitudCodigo order by c.id "),
		@NamedQuery(name = NumeroUnicoTransaccional.LISTAR_POR_SOLICITUDID, query = "SELECT c FROM NumeroUnicoTransaccional c WHERE c.estado= true and c.solicitudUsuario.id=:solicitudId"),
		@NamedQuery(name = NumeroUnicoTransaccional.LISTAR_NUT_HABILITADOS, query = "SELECT c FROM NumeroUnicoTransaccional c WHERE c.estado= true and c.estadosNut.id=2"),
		@NamedQuery(name = NumeroUnicoTransaccional.LISTAR_POR_TRAMITE, query = "SELECT c FROM NumeroUnicoTransaccional c WHERE c.estado= true and c.nutCodigoProyecto=:codigoTramite"),
		@NamedQuery(name = NumeroUnicoTransaccional.LISTAR_POR_NUMBERO_TRAMITE, query = "SELECT c FROM NumeroUnicoTransaccional c WHERE c.estado= true and c.bnfTramitNumber=:tramitNumber"),
		@NamedQuery(name = NumeroUnicoTransaccional.LISTAR_POR_CODIGO_NUT, query = "SELECT c FROM NumeroUnicoTransaccional c WHERE c.estado= true and c.nutCodigo = :codigo"),
		@NamedQuery(name = NumeroUnicoTransaccional.LISTAR_NUTS_POR_ESTADO, query = "SELECT c FROM NumeroUnicoTransaccional c WHERE c.estado= true and c.estadosNut.id = :estado order by nutCodigo"),
		@NamedQuery(name = NumeroUnicoTransaccional.LISTAR_ACTIVOS_POR_TRAMITE_TIPO_PAGO, query = "SELECT c FROM NumeroUnicoTransaccional c WHERE c.estado= true and c.nutCodigoProyecto=:codigoTramite and (c.nutUsado is null or c.nutUsado = false or c.nutUsado = true) and c.nutPaymentType=:tipoProyecto order by c.id desc "),
		@NamedQuery(name = NumeroUnicoTransaccional.LISTAR_ACTIVOS_POR_TRAMITE, query = "SELECT c FROM NumeroUnicoTransaccional c WHERE c.estado= true and c.nutCodigoProyecto=:codigoTramite and (c.nutUsado is null or c.nutUsado = false or c.nutUsado = true) order by c.id desc ") })
@AttributeOverrides({ @AttributeOverride(name = "estado", column = @Column(name = "nut_status")),
		@AttributeOverride(name = "fechaCreacion", column = @Column(name = "nut_date_create")),
		@AttributeOverride(name = "fechaModificacion", column = @Column(name = "nut_date_update")),
		@AttributeOverride(name = "usuarioCreacion", column = @Column(name = "nut_user_create")),
		@AttributeOverride(name = "usuarioModificacion", column = @Column(name = "nut_user_update")) })
@Filter(name = EntidadBase.FILTER_ACTIVE, condition = "nut_status = 'TRUE'")
public class NumeroUnicoTransaccional extends EntidadAuditable {

	private static final long serialVersionUID = -1756612644167548934L;

	private static final String PAQUETE_CLASE = "ec.gob.ambiente.suia.recaudaciones.model.NumeroUnicoTransaccional";
	public static final String LISTAR_TODOS = PAQUETE_CLASE + "listarTodos";
	public static final String LISTAR_POR_ID = PAQUETE_CLASE + "listarPorId";
	public static final String LISTAR_POR_SOLICITUD = PAQUETE_CLASE + "listarPorSolicitud";
	public static final String LISTAR_POR_SOLICITUDID = PAQUETE_CLASE + "listarPorSolicitudId";
	public static final String LISTAR_NUT_HABILITADOS = PAQUETE_CLASE + "listarNutHabilitados";
	public static final String LISTAR_POR_TRAMITE = PAQUETE_CLASE + "listarPorTramite";
	public static final String LISTAR_POR_NUMBERO_TRAMITE = PAQUETE_CLASE + "listarPorNumeroTramite";
	public static final String LISTAR_POR_CODIGO_NUT = PAQUETE_CLASE + "listarPorCodigoNut";
	public static final String LISTAR_NUTS_POR_ESTADO = PAQUETE_CLASE + "listarNutsPorEstado";
	public static final String LISTAR_ACTIVOS_POR_TRAMITE = PAQUETE_CLASE + "listarActivosPorTramite";
	public static final String LISTAR_ACTIVOS_POR_TRAMITE_TIPO_PAGO=PAQUETE_CLASE+"listarActivosPorTramiteTipoPago";

	@Getter
	@Setter
	@Id
	@Column(name = "nut_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Getter
	@Setter
	@Column(name = "nut_code")
	private String nutCodigo;

	@Getter
	@Setter
	@JoinColumn(name = "acco_id", referencedColumnName = "acco_id")
    @ManyToOne
    @ForeignKey(name = "fk_account_nut")
    private Cuentas cuentas;
	
	@Getter
	@Setter
	@Column(name = "nut_value")
	private Double nutValor;
	
	@Getter
	@Setter
	@Column(name = "nut_payment_type")
	private Integer nutPaymentType;
	
	@Getter
	@Setter
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "nut_date_activation")
	private Date nutFechaActivacion;
	
	@Getter
	@Setter
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "nut_date_finalized_activation")
	private Date nutFechaDesactivacion;

	@Getter
    @Setter
    @JoinColumn(name = "nuts_id", referencedColumnName = "nuts_id")
    @ManyToOne
    @ForeignKey(name = "fk_nut_status_nut")
    private EstadosNut estadosNut;
	
	@Getter
    @Setter
    @JoinColumn(name = "reus_id", referencedColumnName = "reus_id")
    @ManyToOne
    @ForeignKey(name = "fk_unique_transaction_number_reus_id_request_user_reus_id")
    private SolicitudUsuario solicitudUsuario;
	
	@Getter
	@Setter
	@OneToMany(mappedBy = "numeroUnicoTransaccional", fetch = FetchType.EAGER)
	private List<TarifasNUT> listTarifasNUT;
	
	@Getter
	@Setter
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "bnf_date_pay")
	private Date bnfFechaPago;
	
	@Getter
	@Setter
	@Column(name = "bnf_tramit_number")
	private String bnfTramitNumber;
	
	@Getter
	@Setter
	@Column(name = "nut_used")
	private Boolean nutUsado;
	
	@Getter
	@Setter
	@Column(name = "nut_project_code")
	private String nutCodigoProyecto;
	
	@Getter
	@Setter
	@Column(name = "nut_sender_ip")
	private String nutIpRemitente;
	
	@Getter
	@Setter
	@Column(name = "nut_description")
	private String nutDescripcion;
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof NumeroUnicoTransaccional)) {
			return false;
		}
		NumeroUnicoTransaccional other = (NumeroUnicoTransaccional) obj;
		if (((this.id == null) && (other.id != null)) || ((this.id != null) && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "NumeroUnicoTransaccional [id=" + id + ", nutCodigo=" + nutCodigo + ", cuentas=" + cuentas
				+ ", nutValor=" + nutValor + ", nutFechaActivacion=" + nutFechaActivacion + ", nutFechaDesactivacion="
				+ nutFechaDesactivacion + ", estadosNut=" + estadosNut + ", solicitudUsuario=" + solicitudUsuario
				+ ", listTarifasNUT=" + listTarifasNUT + ", bnfFechaPago=" + bnfFechaPago + ", bnfTramitNumber="
				+ bnfTramitNumber + ", nutUsado=" + nutUsado + ", nutCodigoProyecto=" + nutCodigoProyecto
				+ ", nutIpRemitente=" + nutIpRemitente + ", nutDescripcion=" + nutDescripcion + ", codigoNutReporte="
				+ codigoNutReporte + ", nombreProponenteReporte=" + nombreProponenteReporte
				+ ", codigoSolicitudReporte=" + codigoSolicitudReporte + ", solicitudReporte=" + solicitudReporte
				+ ", cuentaApagarReporte=" + cuentaApagarReporte + ", tasaDescripcion=" + tasaDescripcion
				+ ", totalNutReporte=" + totalNutReporte + ", descripcionNuts=" + descripcionNuts + ", nombreBanco="
				+ nombreBanco + ", fechaEmision=" + fechaEmision + ", registroBancario=" + registroBancario
				+ ", fechaCreacion=" + fechaCreacion + ", fechaModificacion=" + fechaModificacion + ", usuarioCreacion="
				+ usuarioCreacion + ", usuarioModificacion=" + usuarioModificacion + ", estado=" + estado + ", getId()="
				+ getId() + ", getNutCodigo()=" + getNutCodigo() + ", getCuentas()=" + getCuentas() + ", getNutValor()="
				+ getNutValor() + ", getNutFechaActivacion()=" + getNutFechaActivacion()
				+ ", getNutFechaDesactivacion()=" + getNutFechaDesactivacion() + ", getEstadosNut()=" + getEstadosNut()
				+ ", getSolicitudUsuario()=" + getSolicitudUsuario() + ", getListTarifasNUT()=" + getListTarifasNUT()
				+ ", getBnfFechaPago()=" + getBnfFechaPago() + ", getBnfTramitNumber()=" + getBnfTramitNumber()
				+ ", getNutUsado()=" + getNutUsado() + ", getNutCodigoProyecto()=" + getNutCodigoProyecto()
				+ ", getNutIpRemitente()=" + getNutIpRemitente() + ", getNutDescripcion()=" + getNutDescripcion()
				+ ", getCodigoNutReporte()=" + getCodigoNutReporte() + ", getNombreProponenteReporte()="
				+ getNombreProponenteReporte() + ", getCodigoSolicitudReporte()=" + getCodigoSolicitudReporte()
				+ ", getSolicitudReporte()=" + getSolicitudReporte() + ", getCuentaApagarReporte()="
				+ getCuentaApagarReporte() + ", getTasaDescripcion()=" + getTasaDescripcion()
				+ ", getTotalNutReporte()=" + getTotalNutReporte() + ", getDescripcionNuts()=" + getDescripcionNuts()
				+ ", getNombreBanco()=" + getNombreBanco() + ", getFechaEmision()=" + getFechaEmision()
				+ ", getRegistroBancario()=" + getRegistroBancario() + ", getFechaCreacion()=" + getFechaCreacion()
				+ ", getFechaModificacion()=" + getFechaModificacion() + ", getUsuarioCreacion()="
				+ getUsuarioCreacion() + ", getUsuarioModificacion()=" + getUsuarioModificacion() + ", isPersisted()="
				+ isPersisted() + ", getEstado()=" + getEstado() + ", isSeleccionado()=" + isSeleccionado()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	//para el reporte pdf
	@Getter
	@Setter
	@Transient
	private String codigoNutReporte;
	
	@Getter
	@Setter
	@Transient
	private String nombreProponenteReporte;
	
	@Getter
	@Setter
	@Transient
	private String codigoSolicitudReporte;
	
	@Getter
	@Setter
	@Transient
	private String solicitudReporte;
	
	@Getter
	@Setter
	@Transient
	private String cuentaApagarReporte;

	@Getter
	@Setter
	@Transient
	private String tasaDescripcion;
	
	@Getter
	@Setter
	@Transient
	private String totalNutReporte;
	
	@Getter
	@Setter
	@Transient
	private String descripcionNuts;
	
	@Getter
	@Setter
	@Transient
	private String nombreBanco;
	
	@Getter
	@Setter
	@Transient
	private String fechaEmision;
	
	@Getter
	@Setter
	@Transient
	private EntityRegistroBancario registroBancario;
	
	

}
