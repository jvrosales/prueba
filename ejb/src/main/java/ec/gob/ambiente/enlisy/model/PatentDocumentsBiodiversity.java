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
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the patent_documents database table.
 * 
 */
@Entity
@Table(name = "patent_documents", schema = "biodiversity_mcm")
@NamedQuery(name = "PatentDocumentsBiodiversity.findAll", query = "SELECT p FROM PatentDocumentsBiodiversity p ")
public class PatentDocumentsBiodiversity implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	public PatentDocumentsBiodiversity clone(PatentRequest patentRequest) throws CloneNotSupportedException {

		PatentDocumentsBiodiversity clone = (PatentDocumentsBiodiversity) super.clone();
		clone.setPadoId(null);
		clone.setPadoUserUpdate(null);
		clone.setPadoDateUpdate(null);
		clone.setPatentRequest(patentRequest);
		return clone;
	}

	public static final Integer CTMVS_DERECHO_PROPIEDAD = 3201;
	public static final Integer CTMVS_CERTIFICACIÓN_DENOMINACIÓN_ADMINISTRADOR_CURADOR = 3217;
	public static final Integer CTMVS_ARCHIVO_GPX_LINDERO_DE_CENTRO_DE_MANEJO = 3218;
	public static final Integer CTMVS_PLAN_MANEJO = 3202;
	public static final Integer MCMES_PLAN_MANEJO = 3230;
	public static final Integer MCMES_PAGO_INSPECCION = 3231;
	public static final Integer MCMES_ANEXOS = 3232;
	public static final Integer MCMES_INFORME_FIRMADO = 3233;
	public static final Integer MCMES_PAGO_PATENTE = 3234;
	public static final Integer MCMES_DOCUMENTO_SUBSANACION = 3235;
	public static final Integer MCMES_PLAN_ANTICONCEPCION = 3236;
	
	public static final Integer MCMES_PRONUNCIAMIENTO_ARCHIVO_SOLICITUD_PATENTE = 5905;
	public static final Integer MCMES_PRONUNCIAMIENTO_NEGACION_PATENTE = 5906;
	public static final Integer MCMES_INFORME_TECNICO = 5907;
	public static final Integer MCMES_PATENTE_PROVISIONAL = 5908;
	public static final Integer MCMES_PATENTE = 5909;
	    
    public static final Integer MCMES_revisarpatentesEmiRenCenResRehJarBotVivOrqZoo = 5910;
	public static final Integer MCMES_revisarpatentesEmiPerNatCatMusHerCep = 5911;
	public static final Integer MCMES_revisarpatentesEmiRenCenResRehJarBotVivOrqZooAdm = 5912;
	public static final Integer MCMES_revisarpatentesEmiMusHerCepSinAdm = 5913;
	public static final Integer MCMES_EmiCenResRehJarBotVivOrqZooAdm = 5914;
	public static final Integer MCMES_EmiMusHerCepAdm = 5915;
	
	public static final Integer MCMES_INFORME_ACTIVIDADES = 5916;


	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pado_id")
	private Integer padoId;

	@Getter
	@Setter
	@Column(name = "pado_name")
	private String padoName;

	@Getter
	@Setter
	@Column(name = "pado_mime")
	private String padoMime;

	@Getter
	@Setter
	@Column(name = "doty_id")
	private Integer dotyId;

	@Getter
	@Setter
	@Column(name = "pado_extesion")
	private String padoExtesion;

	@Getter
	@Setter
	@Column(name = "pado_status")
	private Boolean padoStatus;

	@Getter
	@Setter
	@Column(name = "pado_alfresco_id")
	private String padoAlfrescoId;

	// bi-directional many-to-one association to PatenRequest
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "pare_id")
	private PatentRequest patentRequest;

	@Getter
	@Setter
	@Column(name = "pado_type_user")
	private Integer padoTypeUser;

	@Getter
	@Setter
	@Column(name = "pado_table_id")
	private Integer padoTableId;

	@Getter
	@Setter
	@Column(name = "pado_description")
	private String padoDescription;

	@Getter
	@Setter
	@Column(name = "pado_table_class")
	private String padoTableClass;

	@Getter
	@Setter
	@Column(name = "pado_code_public")
	private String padoCodePublic;

	@Getter
	@Setter
	@Column(name = "pado_process_instance_id")
	private Integer padoProcessInstanceId;

	@Getter
	@Setter
	@Column(name = "pado_update_number")
	private Integer padoUpdateNumber;

	@Getter
	@Setter
	@Column(name = "pado_creation_date")
	private Date padoCreationDate;

	@Getter
	@Setter
	@Column(name = "pado_creator_user")
	private String padoCreatorUser;

	@Getter
	@Setter
	@Column(name = "pado_user_update")
	private String padoUserUpdate;

	@Getter
	@Setter
	@Column(name = "pado_date_update")
	private Date padoDateUpdate;

	@Getter
	@Setter
	@Transient
	byte[] content;

	@Getter
	@Setter
	@Column(name="signed_document")
	private Boolean patenteFirmada;
	
}