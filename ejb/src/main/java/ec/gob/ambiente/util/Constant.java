package ec.gob.ambiente.util;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import ec.gob.ambiente.configuration.bean.ConfigurationBean;


public class Constant {
	
	//ORIGEN
	public static final String NOMBRE_ORIGEN_PROYECTO = "Biodiversidad";
	
	// LOOKUP	
	public static final String ALFRESCO_SERVICE_BEAN = "java:global/alfresco-ejb-1.0/AlfrescoServiceBean!ec.gob.ambiente.alfresco.service.AlfrescoServiceBean";	
	public static final String JBPM_EJB_PROCESS_BEAN = "java:global/jbpm-ejb-1.0/ProcessBeanFacade!ec.gob.ambiente.jbpm.facade.ProcessBeanFacade";
	public static final String JBPM_EJB_TASK_BEAN = "java:global/jbpm-ejb-1.0/TaskBeanFacade!ec.gob.ambiente.jbpm.facade.TaskBeanFacade";
	
	// NOMBRES PROCESOS
	public static final String NOMBRE_PROCESO_SOLICITUD_PERMISO_INVESTIGACION = "Biodiversidad.SolicitudPermisoInvestigacion";
	public static final String NOMBRE_PROCESO_AUTORIZACION_SIN_FINES_COMERCIAES = "Biodiversidad.AutorizacionRecoleccionEspeciesSinFinesComerciales";
	public static final String NOMBRE_PROCESO_CONTRATO_MARCO = "Biodiversidad.AutorizacionRecoleccionEspeciesContratoMarco";
	public static final String NOMBRE_PROCESO_INFORME_FINAL_INVESTIGACION_ESPECIES = "Biodiversidad.InformeFinalInvestigacionEspecies";
	public static final String NOMBRE_PROCESO_CONTROL_SEGUIMIENTO_CAMPO = "Biodiversidad.ControlYSeguimientoEnCampo";
	public static final String NOMBRE_PROCESO_PERMISOS_EXPORTACION = "Biodiversidad.EmisionPermisosExportacion";
	public static final String NOMBRE_PROCESO_PATENTES_CENTROS_TENENCIA_MANEJO = "Biodiversidad.PatentesCentrosTenenciaManejo";
	public static final String NOMBRE_PROCESO_PATENTES_MCM = "Biodiversidad.PatentesBiodiversidadMCM";
		
	
	public static final String NOMBRE_PROCESO_PATENTES_NEW = "";
	public static final String RCOA_ESTUDIO_IMPACTO_AMBIENTAL_v2 = "rcoa.EstudioImpactoAmbiental_v2";
	
	// VARIABLES PROCESOS
	public static final String VARIABLE_TRAMITE_NO_PROYECTO = "No asociado";
	public static final String VARIABLE_PROCESO_TRAMITE_RESOLVER = "procedureResolverClass";
	public static final String VARIABLE_SESION_PROCESS_ID = "VARIABLE_SESION_PROCESS_ID";
	public static final String CODIGO_PROYECTO = "codigoProyecto";
	public static final String ID_PROYECTO = "idProyecto";
	public static final String USUARIO_VISTA_MIS_PROCESOS = "usuarioVistaMisProcesos";
	public static final String USUARIO_FUNCIONARIO_VISTA_MIS_PROCESOS = "usuarioFuncionarioVistaMisProcesos";
	
	
	// UTILES
	public static final String ESTADO_PROCESO_NO_INICIADO = "No iniciado";
	public static final String ESTADO_PROCESO_ABORTADO = "Abortado";
	public static final String ESTADO_TAREA_INICIADA = "En curso";
	public static final String ESTADO_TAREA_COMPLETADA = "Completada";
	public static final String ESTADO_EVENTO_TAREA_COMPLETADA = "COMPLETED";
	public static final String SIGLAS_INSTITUCION = getSiglasInstitucion();
	public static final String TASK_NAME_DESCARGA_GUIAS_ESIA = "Descargar GuIas para elaboracion de EsIA";
	public static final String NEW_TASK_NAME_DESCARGA_GUIAS_ESIA = "Ingreso de estudio de impacto ambiental";
	
	
	//CATALOGO NACIONAL BIODIVERSIDAD
	public static final String NOMBRE_PROCESO_SOLICITUD_REGISTRO_ESPECIALISTA = "Biodiversidad.SolicitudRegistroEspecialista";
	public static final String NOMBRE_PROCESO_SOLICITUD_REGISTRO_TAXONOMIA = "Biodiversidad.SolicitudRegistroTaxonomia";
	
	//EVENTOS
	public static final String NOMBRE_PROCESO_SOLICITUD_EVENTO_GENTE_FAUNA = "Biodiversidad.SolicitudEventoGenteFauna";
	public static final String NOMBRE_PROCESO_SOLICITUD_EVENTO_ATROPELLAMIENTO = "Biodiversidad.SolicitudEventoAtropellamiento";
	public static final String NOMBRE_PROCESO_SOLICITUD_EVENTO_LIBERACION_MONITOREO = "Biodiversidad.SolicitudEventoLiberacionMonitoreo";
	public static final String NOMBRE_PROCESO_SOLICITUD_RETENCION = "Biodiversidad.SolicitudRetencion";
	public static final String NOMBRE_PROCESO_SOLICITUD_RESCATE = "Biodiversidad.SolicitudRescate";
	public static final String NOMBRE_PROCESO_SOLICITUD_EVENTO_PERMISO_EXPORTACION = "Biodiversidad.SolicitudPermisoExportacion";
	public static final String NOMBRE_PROCESO_SOLICITUD_PERMISO_CITES = "Biodiversidad.SolicitudPermisoCites";
	
	//ANALISIS DE RIESGO
	public static final String NOMBRE_PROCESO_SOLICITUD_ANALISIS_RIESGO = "Biodiversidad.SolicitudEvaluacionRiesgo";

		
	
	//VARIABLE GLOBAL
		public static final String NOMBRE_MINISTERIO = "Ministerio del Ambiente, Agua y Transición Ecológica";
	

	
	//CONSTANTES SOLICITUDES
	public static final String PREFIJO_SOLICITUD = "MAATE-MCMEVS";
	
	public static final String ESTADO_SOLICITUD_SOLICITADA = "SOLICITADA";
	
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(Constant.class);
	
	public static final String NOMBRE_PROPIEDADES_UTILS = "ec/gob/ambiente/core/resources/utils_es.properties";
	
	// PAGOENLINEA KUSHKI
	public static final String Aux_Direccion = "MAATE";
	public static final String Aux_Pais = "Ecuador";
	public static final String Aux_Provincia = "Provincia";
	public static final String Aux_Ciudad = "Ciudad";
	public static final String respuestaKushki = "Error en el Pago";
	public static final String celular_response = "0999999999";
	public static final Integer respuesta_servicio_online = 201;
	
	// WEBSERVICE SRI
	//public static final String USUARIO_WS_MAE_SRI_RC = "1712444593";
	public static final String USUARIO_WS_MAE_SRI_RC = getUserWebServicesSnap();
	//public static final String PASSWORD_WS_MAE_SRI_RC = "web_consultora";
	public static final String PASSWORD_WS_MAE_SRI_RC = "1234";
	public static final String NO_ERROR_WS_MAE_SRI_RC = "NO ERROR";
	
	public static Integer getRemoteApiTimeout() {
		return getPropertyAsInteger("server.bpms.remote.api.timeout");		
	}
	
	public static int getPropertyAsInteger(String name) {
		return Integer.parseInt(getPropertyAsString(name));
	}
	
	public static String getPropertyAsString(String name) {
		return loadFromAppProperties(name);
	}
	
	private static String loadFromAppProperties(String key) {
		String result = null;
		if (result == null)
			result = BeanLocator.getInstance(ConfigurationBean.class).getConfigurationValue(key);
		return result;		
	}
	
	public static String getDeploymentId() {		
		return getPropertyAsString("server.bpms.deploymentId.biodiversidad");
	}
	
	public static String getUrlBusinessCentral() {		
		return getPropertyAsString("server.bpms.aplicativos");
	}
	
	public static String getNotificationService() {
		return getPropertyAsString("app.service.notifications");
	}
	
	public static String getUrlWsRegistroCivilSri() {
		return getPropertyAsString("app.service.bus.snap");
	}
	
	public static Boolean getPermitirRUCPasivo() {
		return existsProperty("app.ruc.pasivo") ? getPropertyAsBoolean("app.ruc.pasivo") : false;
	}
	
	public static boolean existsProperty(String name) {
		return getPropertyAsString(name) != null;
	}
	
	public static boolean getPropertyAsBoolean(String name) {
		return Boolean.parseBoolean(getPropertyAsString(name));
	}
	
	public static String getHttpSuiaImagenFooterMail() {
		return getPropertyAsString("path.http.footermail");
	}
	
	public static String getUserWebServicesSnap() {
		return getPropertyAsString("user.web.services.snap");
	}

	public static String getHttpSuiaImagenInfoMail() {
		return getPropertyAsString("path.http.infomail");
	}
	
	public static String getLinkMaeTransparente() {
		return getPropertyAsString("link.maetransparente");
	}
	
	//Metodo utilizado en administrador de procesos
	//busca la url del servicio de 'Definiciones de Procesos del JBPM'
	public static String getUrlSuiaDefinicionesProcesos() {
		return reemplazarServidorRestServices(getPropertyAsString("app.service.listadefinicionesprocesos"));
	}
	
	//Metodo utilizado en notificaciones
	public static String getUrlSuiaEventosTarea() {
		return reemplazarServidorRestServices(getPropertyAsString("app.service.listaeventostarea"));
	}
	
	//Metodo utilizado en administrador de procesos
	public static String getUrlSuiaResumenTarea() {
		return reemplazarServidorRestServices(getPropertyAsString("app.service.listaresumentarea"));
	}
	
	//Metodo utilizado en notificaciones
	public static String getUrlJbpmRestService() {
		return getPropertyAsString("app.service.jbpmrest");
	}
	
	//Metodo utilizado en notificaciones
	public static String getUrlNodeInstanceLog() {
		return getUrlJbpmRestService() + "nodeinstancelog/";
	}
	
	public static String getFirmaDigital() {	
		return getPropertyAsString("app.digital.sign.jws");
	}	
	
//	public static String getFirmaDigital() {	
//		return getPropertyAsString("app.digital.sign");
//	}	

	public static String getRootId() {
		return getPropertyAsString("app.alfresco.root.id.biodiversidad");
	}

	public static String getRootStaticDocumentsId() {
		return getPropertyAsString("app.alfresco.root.static.documents.id");
	}
	
	public static String getServicePayAddres() {
		return getPropertyAsString("app.service.suiaPay");		
	}
	
	public static String getBancoCuentaMae() {	
		return getPropertyAsString("cuenta.mae.oficial");		
	}
	
	private static String reemplazarServidorRestServices(String url)
	{		
		String urlBussiness=getUrlBusinessCentral();		
		int indexUrl=url.indexOf("jbpm-rest-services");		
//		int indexServerBpm=urlBussiness.indexOf("business-central");
		int indexServerBpm=urlBussiness.indexOf("kie-server");
		
		//Reemplaza servidor por el que se utiliza en la bandeja de tareas "business-central"
		urlBussiness=urlBussiness.substring(0,indexServerBpm);		
		url=urlBussiness+url.substring(indexUrl,url.length());
		
		return url;
	}
	
	public static Boolean getAmbienteProduccion() {    
//        return getPropertyAsBoolean("ambiente.produccion");  
		return true;
    }
    
    public static String getServiceClienteCapas() {
        return getPropertyAsString("app.service.consulta_capas");        
    }
    
    public static String getLinkEncuesta() {    
        return getPropertyAsString("suia.survey.link");        
    }
    
    public static String getDblinkBpmsBiodiversidad() {
        return getPropertyAsString("dblink.bpms.biodiversidad")+" user=postgres password=postgres";
    }

    public static String getSiglasInstitucion() {
		return getPropertyAsString("app.siglas.institucion.principal");
	}
    
	public static String getFechaPagosConNut() {
		return getPropertyAsString("fecha.pagos.con.nut");
	}
    
	public static String getRoleAreaName(String role) {
		InputStream inputStream = null;
		String result = null;
		try {
			try {
				inputStream = Constant.class.getClassLoader()
						.getResourceAsStream(Constant.NOMBRE_PROPIEDADES_UTILS);
				Properties prop = new Properties();
				prop.load(inputStream);
				result = prop.getProperty(role);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (inputStream != null)
					inputStream.close();
				inputStream = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

 
}