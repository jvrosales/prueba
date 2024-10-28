package ec.gob.ambiente.enlisy.cetas.model;

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
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the patent_documents database table.
 * 
 */
@Entity
@Table(name="patent_documents", schema="cetas")
@NamedQuery(name="PatentDocuments.findAll", query="SELECT p FROM PatentDocuments p ")
public class PatentDocuments  implements Serializable,Cloneable {
	
	private static final long serialVersionUID = 1L;
	
	public PatentDocuments clone(PatentApplication patentApplication) throws CloneNotSupportedException {
		
		PatentDocuments clone= (PatentDocuments)super.clone();
		clone.setPado_id(null);
		clone.setPado_user_update(null);
		clone.setPado_date_update(null);
		clone.setPatentApplication(patentApplication);
		return clone;
	}

		
	/*
	public static final Integer ARSFC_PRONUNCIAMIENTO_APROBADO=3000;
	public static final Integer ARSFC_PRONUNCIAMIENTO_NEGADO=3001;
	public static final Integer ARSFC_AUTORIZACION_INVESTIGADOR=3002;
	public static final Integer ARSFC_AUTORIZACION_INSTITUCION=3003;
	public static final Integer ARSFC_NUEVA_ESPECIE=3004;	
	public static final Integer CMARG_PLAN_CONSENTIMIENTO_COMPONENTE_INTANGIBLE=3013;
	public static final Integer CMARG_CREACION_INSTITUCION=3006;
	public static final Integer CMARG_DOCUMENTO_APOYO_INSTITUCION=3007;
	public static final Integer CMARG_NOMBRAMIENTO_ACCION_PERSONAL_REPRESENTANTE_LEGAL=3008;
	public static final Integer CMARG_ACREDITACION_INVESTIGADOR_SENECYT=3009;
	public static final Integer CMARG_PRONUNCIAMIENTO_EVALUACION_TECNICA=3010;
	public static final Integer CMARG_CONTRATO_MARCO=3011;
	public static final Integer CMARG_CONTRATO_MARCO_MODIFICATORIO=3012;
	*/
	
	public static final Integer CTMVS_DERECHO_PROPIEDAD=3201;
	public static final Integer CTMVS_PLAN_MANEJO=3202;
	public static final Integer CTMVS_SOLICITUD_APOYO_PLANTA_CENTRAL=3203;
	public static final Integer CTMVS_INFORME_TECNICO_PROVINCIAL=3204;
	public static final Integer CTMVS_INFORME_TECNICO_PLANTA_CENTRAL=3205;
	public static final Integer CTMVS_DOCUMENTO_AJUSTES_OBSERVACIONES_INFORME_TECNICO=3206;
	public static final Integer CTMVS_COMPROBANTE_PAGO=3207;
	public static final Integer CTMVS_PATENTE_CENTROS_TENENCIA_MANEJO_ZOOLOGICO=3208;
	public static final Integer CTMVS_PATENTE_CENTROS_TENENCIA_MANEJO_CENTRO_RESCATE=3209;
	public static final Integer CTMVS_PATENTE_CENTROS_TENENCIA_MANEJO_JARDIN_BOTANICO=3210;
	public static final Integer CTMVS_PATENTE_CENTROS_TENENCIA_MANEJO_MUSEO=3211;
	public static final Integer CTMVS_PATENTE_CENTROS_TENENCIA_MANEJO_HERBARIO=3212;
	public static final Integer CTMVS_PATENTE_CENTROS_TENENCIA_MANEJO_ZOOCRIADERO=3213;
	public static final Integer CTMVS_PATENTE_CENTROS_TENENCIA_MANEJO_VIVERO=3214;
	public static final Integer CTMVS_PATENTE_CENTROS_TENENCIA_MANEJO_ACUARIO=3216;
	public static final Integer CTMVS_INFORME_ACTIVIDADES=3215;
	/*
	public static final Integer CTMVS_INFORME_TECNICO_PROVINCIAL=3203;
	public static final Integer CTMVS_INFORME_TECNICO_PLANTA_CENTRAL=3204;
	public static final Integer CTMVS_SOLICITUD_APOYO_PLANTA_CENTRAL=3205;
	public static final Integer CTMVS_DOCUMENTO_JUSTIFICACION_OBSERVACIONES=3206;
	public static final Integer CTMVS_COMPROBANTE_PAGO=3207;
	*/
	
	
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pado_id")
	private Integer pado_id;
	
	@Getter
	@Setter
	@Column(name="pado_name")
	private String pado_name;
	
	@Getter
	@Setter
	@Column(name="pado_mime")
	private String pado_mime;
	
	@Getter
	@Setter
	@Column(name="doty_id")
	private Integer doty_id;

	//bi-directional many-to-one association to PatentApplication
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="paap_id")
	private PatentApplication patentApplication;
	
	@Getter
	@Setter
	@Column(name="pado_code_public")
	private String pado_code_public;
	
	@Getter
	@Setter
	@Column(name="pado_extesion")
	private String pado_extesion;

	@Getter
	@Setter
	@Column(name="pado_status")
	private Boolean pado_status;
	
	@Getter
	@Setter
	@Column(name="pado_table_id")
	private Integer pado_table_id;
	
	@Getter
	@Setter
	@Column(name="pado_table_class")
	private String pado_table_class;

	@Getter
	@Setter
	@Column(name="pado_alfresco_id")
	private String pado_alfresco_id;
	
	@Getter
	@Setter
	@Column(name="pado_description")
	private String pado_description;
	
	@Getter
	@Setter
	@Column(name="pado_user_create")
	private String pado_user_create;

	@Getter
	@Setter
	@Column(name="pado_date_create")
	private Date pado_date_create;

	@Getter
	@Setter
	@Column(name="pado_user_update")
	private String pado_user_update;
	
	@Getter
	@Setter
	@Column(name="pado_date_update")
	private Date pado_date_update;

	@Getter
	@Setter
	@Transient
	byte[] content;

}