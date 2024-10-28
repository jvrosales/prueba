package ec.gob.ambiente.enlisy.geneticresources.model;

import java.io.InputStream;
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
 * The persistent class for the proposed_documents database table.
 * 
 */
@Entity
@Table(name="proposed_documents", schema="biodiversity")
@NamedQuery(name="ProposedDocument.findAll", query="SELECT p FROM ProposedDocument p ")
public class ProposedDocument  implements Serializable,Cloneable {
	
	private static final long serialVersionUID = 1L;
	
	public ProposedDocument clone(ProposedCollection proposedCollection) throws CloneNotSupportedException {
		
		ProposedDocument clone= (ProposedDocument)super.clone();
		clone.setDocu_id(null);
		clone.setDocu_user_update(null);
		clone.setDocu_date_update(null);
		clone.setProposedCollection(proposedCollection);
		return clone;
	}		
	
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
	public static final Integer CMARG_PRONUNCIAMIENTO_NEGATIVO=3025;
	public static final Integer CMARG_CONTRATO_MARCO=3011;
	public static final Integer CMARG_CONTRATO_MARCO_PROYECTO_PERSONA_JURIDICA=3015;
	public static final Integer CMARG_CONTRATO_MARCO_PROYECTO_PERSONA_DELEGACION=3016;
	public static final Integer CMARG_CONTRATO_MARCO_PROYECTO_PERSONA_AVAL=3017;
	public static final Integer CMARG_CONTRATO_MARCO_PROGRAMA_PERSONA_JURIDICA=3018;
	public static final Integer CMARG_CONTRATO_MARCO_PROGRAMA_PERSONA_DELEGACION=3019;
	public static final Integer CMARG_PRONUNCIAMIENTO_MODIFICACION_CONTRATO_MARCO_PROYECTO=3021;
	public static final Integer CMARG_PRONUNCIAMIENTO_MODIFICACION_CONTRATO_MARCO_PROGRAMA=3023;
	public static final Integer CMARG_CONTRATO_MARCO_MODIFICATORIO=3012;
	public static final Integer CMARG_NOMBRAMIENTO_AUTORIDAD_INSTITUCION_APOYO=3070;
	public static final Integer CMARG_DELEGACION_INVESTIGADORES_INSTITUCION=3071;
	public static final Integer CMARG_REPORTE_EVALUACION_TECNICA=3250;
	public static final Integer CMARG_REGISTRO_CONTRATO_MARCO_PROYECTO_PROGRAMA=3251;
	public static final Integer GUIA_MOVILIZACION_BIODIVERSIDAD=3014;
	public static final Integer CERTIFICADO_DEPOSITO_ESPECIES_BIODIVERSIDAD=3015;
	public static final Integer CMARG_DELEGACION_OFICIAL_REPRESENTANTE_LEGAL=3016;
	public static final Integer CSC_INFORME_TECNICO=3020;
	public static final Integer CSC_DOCUMENTO_JUSTIFICACION=3021;
	public static final Integer CSC_INFORME_TECNICO_JURIDICO=3022;
	public static final Integer CSC_RESOLUCION_PROCESO_ADMINISTRATIVO=3023;
	public static final Integer CSC_IMAGEN_INFORME_TECNICO=3024;
	public static final Integer CSC_IMAGEN_INFORME_NEGACION=3025;
	public static final Integer AEIC_ACUERDO_TRANSFERENCIA_MATERIALES=3030;
	public static final Integer AEIC_PRONUNCIAMIENTO_NEGADO=3031;
	public static final Integer AEIC_AUTORIZACION_EXPORTACION_CIENTIFICA=3032;

	public static final Integer ARCM_INFORME_FINAL=3041;
	public static final Integer ARCM_DOCUMENTO_RESPALDO=3042;
	public static final Integer CSC_INFORME_TECNICO_JURIDICO_FIRMADO=3026;
	
	public static final Integer CMARG_CARTA_INTENCION_APOYO = 3050;
	public static final Integer CMARG_COPIA_PASAPORTE = 3051;
	public static final Integer CMARG_CARTA_ACEPTACION_PREDIO = 3052;
	public static final Integer CMARG_CARTA_RESPALDO_AVAL = 3053;
	public static final Integer CMARG_INFORME_FINAL_ACTIVIDADES=3054;

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="docu_id")
	private Integer docu_id;

	@Getter
	@Setter
	@Column(name="docu_alfresco_id")
	private String docu_alfresco_id;

	@Getter
	@Setter
	@Column(name="docu_code_public")
	private String docu_code_public;

	@Getter
	@Setter
	@Column(name="docu_creation_date")
	private Date docu_creation_date;

	@Getter
	@Setter
	@Column(name="docu_creator_user")
	private String docu_creator_user;

	@Getter
	@Setter
	@Column(name="docu_date_update")
	private Date docu_date_update;

	@Getter
	@Setter
	@Column(name="docu_description")
	private String docu_description;

	@Getter
	@Setter
	@Column(name="docu_extesion")
	private String docu_extesion;

	@Getter
	@Setter
	@Column(name="docu_mime")
	private String docu_mime;

	@Getter
	@Setter
	@Column(name="docu_name")
	private String docu_name;

	@Getter
	@Setter
	@Column(name="docu_status")
	private Boolean docu_status;

	@Getter
	@Setter
	@Column(name="docu_table_class")
	private String docu_table_class;

	@Getter
	@Setter
	@Column(name="docu_table_id")
	private Integer docu_table_id;

	@Getter
	@Setter
	@Column(name="docu_user_update")
	private String docu_user_update;

	@Getter
	@Setter
	@Column(name="doty_id")
	private Integer doty_id;

	//bi-directional many-to-one association to ProposedCollection
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="prco_id")
	private ProposedCollection proposedCollection;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="exau_id")
	private ExportAuthorization exportAuthorization;
	
	@Getter
	@Setter
	@Transient
	byte[] content;
	
	@Getter
	@Setter
	@Transient
	private boolean subido;
	
	@Getter
	@Setter
	@Transient
	private String urlPath, descripcion;
	
	@Getter
	@Setter
	@Transient
	private ProposedDocument documentoSubido;

	
	@Getter
	@Setter
	@Transient
	private InputStream is;
}
