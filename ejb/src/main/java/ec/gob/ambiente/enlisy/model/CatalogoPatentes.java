package ec.gob.ambiente.enlisy.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "catalogs_patent", schema = "biodiversity_mcm")
@NamedQuery(name = "CatalogoPatentes.findAll", query = "SELECT o FROM CatalogoPatentes o")
public class CatalogoPatentes implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String EXCEL_FormatoEspeciesCentroCriaCFC = "FormatoEspeciesCentroCriaCFC.xls";
	public static final String EXCEL_FormatoEspeciesCentroCriaSFC = "FormatoEspeciesCentroCriaSFC.xls";
	public static final String EXCEL_FormatoEspeciesCentroPaso = "FormatoEspeciesCentroPaso.xls";
	public static final String EXCEL_FormatoEspeciesJardinBotanico = "FormatoEspeciesJardinBotanico.xls";
	public static final String EXCEL_FormatoEspeciesViveroOrquidearioCFC = "FormatoEspeciesViveroOrquidearioCFC.xls";
	public static final String EXCEL_FormatoEspeciesViveroOrquidearioSFC = "FormatoEspeciesViveroOrquidearioSFC.xls";
	public static final String EXCEL_FormatoEspeciesZooCenResAcu = "FormatoEspeciesZooCenResAcu.xls";
	public static final String EXCEL_FormatoEspeciesMuseosHerbariosCeparios = "FormatoMuseoHerbarioSepario.xlsx";
	public static final String DOCX_PM_Acuarios = "PM_Acuarios.docx";
	public static final String DOCX_PM_CentroCriaCon = "PM_Centros_cria_con_comerciales.docx";
	public static final String DOCX_PM_CentroCriaSin = "PM_Centros_cria_sin_comerciales.docx";
	public static final String DOCX_PM_CentrosdePaso = "PM_Centros_de_paso.docx";
	public static final String DOCX_PM_CentrodeRescate = "PM_Centros_de_rescate_y_rehabilitacion.docx";
	public static final String DOCX_PM_Micotecas = "PM_Micotecas.docx";
	public static final String DOCX_PM_Herbarios = "PM_Herbarios.docx";
	public static final String DOCX_PM_Jardines = "PM_Jardines_botanicos.docx";
	public static final String DOCX_PM_Museos = "PM_Museos.docx";
	public static final String DOCX_PM_ViverosCon = "PM_Viveros_con_comerciales.docx";
	public static final String DOCX_PM_ViverosSin = "PM_Viveros_sin_comerciales.docx";
	public static final String DOCX_PM_Zoologicos = "PM_Zoologicos.docx";
	public static final String DOCX_FormatoRenovacion = "Informe_actividades_renovacion.docx";
			
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ctpa_id")
	private Integer id;

	@Getter
	@Setter
	@Column(name = "ctpa_parent_id")
	private Integer parentId;

	@Getter
	@Setter
	@Column(name = "ctpa_name")
	private String ctpaName;

	@Getter
	@Setter
	@Column(name = "ctpa_description")
	private String ctpaDescription;

	@Getter
	@Setter
	@Column(name = "ctpa_status")
	private boolean ctpaStatus;

	@Getter
	@Setter
	@Column(name = "ctpa_code")
	private String ctpaCode;

	@Getter
	@Setter
	@Column(name = "ctpa_value")
	private String ctpaValue;

	@Getter
	@Setter
	@Column(name = "ctpa_creation_date")
	private Date ctpaCreationDate;

	@Getter
	@Setter
	@Column(name = "ctpa_creator_user")
	private String ctpaCreatorUser;

	@Getter
	@Setter
	@Column(name = "ctpa_date_update")
	private Date ctpaDateUpdate;

	@Getter
	@Setter
	@Column(name = "ctpa_user_update")
	private String ctpaUserUpdate;

	@Getter
	@Setter
	@Column(name = "ctpa_observation_bd")
	private String ctpaObservationBd;

	@Getter
	@Setter
	@Column(name = "ctpa_order")
	private String ctpaOrder;
	
	@Getter
	@Setter
	@Column(name = "ctpa_url")
	private String ctpaUrl;	
	
	@Getter
	@Setter
	@Transient
	byte[] content;

}
