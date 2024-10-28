package ec.gob.ambiente.enlisy.services;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.jbpm.service.ProcessService;
import ec.gob.ambiente.suia.crud.service.CrudServiceBean;
import ec.gob.ambiente.util.Constant;
import lombok.Getter;
import lombok.Setter;

//import ec.gob.ambiente.rcoa.estudioImpactoAmbiental.model.InformacionProyectoEia;
//import ec.gob.ambiente.rcoa.estudioImpactoAmbiental.model.ProrrogaModificacionEstudio;
//import ec.gob.ambiente.rcoa.facade.ProyectoLicenciaCoaFacade;
//import ec.gob.ambiente.rcoa.model.ProyectoLicenciaCoa;
//import ec.gob.ambiente.suia.administracion.facade.FeriadosFacade;
//import ec.gob.ambiente.suia.crud.service.CrudServiceBean;
//import ec.gob.ambiente.suia.domain.Usuario;
//import ec.gob.ambiente.suia.proceso.facade.ProcesoFacade;
//import ec.gob.ambiente.suia.usuario.facade.UsuarioFacade;
//import ec.gob.ambiente.suia.utils.Constantes;

@Stateless
public class MigracionFlujoPatenteFacade {

	@EJB
	private CrudServiceBean crudServiceBean;
	@EJB
	private UserFacade usuarioFacade;
	@EJB
	private ProcessService procesoFacade;
	// @EJB
	// private ProcesoFacade procesoFacade;
	// @EJB
	// private UsuarioFacade usuarioFacade;
	// @EJB
	// private FeriadosFacade feriadosFacade;
	// @EJB
	// private InformacionProyectoEIACoaFacade informacionProyectoEIACoaFacade;
	// @EJB
	// private ProyectoLicenciaCoaFacade proyectoLicenciaCoaFacade;
	// @EJB
	// private ProrrogaModificacionEstudioFacade
	// prorrogaModificacionEstudioFacade;

	// public String dblinkBpmsSuiaiii = Constantes.getDblinkBpmsSuiaiii();

	// public ProrrogaModificacionEstudio prorrogaModificacionEstudio;

	@Getter
	@Setter
	public User usuario;

	public String dblinkBpmsBiodiversidad = Constant
			.getDblinkBpmsBiodiversidad();

	// @SuppressWarnings("unchecked")
	// public void migrarProyectosEsia() {
	// try {
	// String
	// sqlProcesos="select * from dblink('"+Constantes.getDblinkBpmsSuiaiii()+"', "
	// + "'select distinct processinstanceid, actualowner_id from task "
	// + "where processinstanceid in (select processinstanceid "
	// +
	// "from processinstancelog where processid = ''rcoa.EstudioImpactoAmbiental'' and processversion = ''1.1'') "
	// + "and status = ''Reserved'' "
	// + "') as (processinstanceid text, actualowner_id text)";
	//
	// Query q_tareas =
	// crudServiceBean.getEntityManager().createNativeQuery(sqlProcesos);
	//
	// List<Object[]> listProcesosActivos = (List<Object[]>)
	// q_tareas.getResultList();
	// if (listProcesosActivos.size() > 0) {
	// for (int i = 0; i < listProcesosActivos.size(); i++) {
	// Object[] dataProject = (Object[]) listProcesosActivos.get(i);
	//
	// Long idProcesoActual = Long.parseLong(dataProject[0].toString());
	// Usuario usuario = usuarioFacade.buscarUsuario(dataProject[1].toString());
	//
	// String
	// sqlTareasProcesoActual="select * from dblink('"+Constantes.getDblinkBpmsSuiaiii()+"', "
	// + "'select id, status, formname from task "
	// + "where processinstanceid = "+ idProcesoActual
	// +" order by id') as (id text, status text, formname text)";
	//
	// List<Object[]> listTareasProcesoActual = (List<Object[]>)
	// crudServiceBean.getEntityManager().createNativeQuery(sqlTareasProcesoActual).getResultList();
	//
	// Map<String, Object> parametros =
	// procesoFacade.recuperarVariablesProceso(usuario, idProcesoActual);
	//
	// Boolean existeNormativaPago =
	// Constantes.getPropertyAsBoolean("rcoa.esia.existe.normativa.pago");
	// parametros.put("existeNormativaPago", existeNormativaPago);
	//
	// String tramite = (String)
	// parametros.get(Constantes.VARIABLE_PROCESO_TRAMITE);
	//
	// Long idNuevoProceso = procesoFacade.iniciarProceso(usuario,
	// Constantes.RCOA_ESTUDIO_IMPACTO_AMBIENTAL_v2, tramite, parametros);
	//
	// if(listTareasProcesoActual.size() > 0) {
	// for (int j = 0; j < listTareasProcesoActual.size(); j++) {
	// Object[] dataTareaProcesoActual = (Object[])
	// listTareasProcesoActual.get(j);
	// Long idTareaProcesoActual =
	// Long.parseLong(dataTareaProcesoActual[0].toString());
	// String estadoTareaProcesoActual = dataTareaProcesoActual[1].toString();
	//
	// String
	// sqlTareaActivaNuevoProceso="select * from dblink('"+Constantes.getDblinkBpmsSuiaiii()+"', "
	// + "'select id, actualowner_id, formname from task "
	// + "where processinstanceid = "+ idNuevoProceso
	// +" and status = ''Reserved'' ') as (id text, actualowner_id text, formname text)";
	//
	// List<Object[]> listTareaActivaNuevoProceso = (List<Object[]>)
	// crudServiceBean.getEntityManager().createNativeQuery(sqlTareaActivaNuevoProceso).getResultList();
	//
	// if (listTareaActivaNuevoProceso.size() > 0) {
	// Object[] dataTarea = (Object[]) listTareaActivaNuevoProceso.get(0);
	//
	// Long idTareaNuevoProceso = Long.parseLong(dataTarea[0].toString());
	// Usuario usuarioTarea =
	// usuarioFacade.buscarUsuario(dataTarea[1].toString());
	//
	// if(estadoTareaProcesoActual.equals("Completed")) {
	// procesoFacade.aprobarTarea(usuarioTarea, idTareaNuevoProceso,
	// idNuevoProceso, null);
	// }
	//
	// if(dataTarea[2].toString().equals(dataTareaProcesoActual[2].toString())
	// || (dataTarea[2].toString().equals("firmarResponsabilidadEsia") &&
	// dataTareaProcesoActual[2].toString().equals("pagarTasaRevisionEsIA"))) {
	//
	// igualFechaTareas(idProcesoActual, idNuevoProceso, idTareaProcesoActual,
	// idTareaNuevoProceso);
	//
	// }
	// }
	// }
	// }
	//
	// // igualar fecha proceso
	// String sqlUpdateProcess = "select dblink_exec('" +
	// Constantes.getDblinkBpmsSuiaiii() + "',"
	// + "'update processinstancelog set "
	// +
	// "start_date = (select start_date from  processinstancelog where processinstanceid = "
	// + idProcesoActual + "), "
	// +
	// "parentprocessinstanceid = (select parentprocessinstanceid from  processinstancelog where processinstanceid = "
	// + idProcesoActual + ") "
	// + "where processinstanceid = " + idNuevoProceso+ "') as result";
	//
	// Query queryUpdateProcess =
	// crudServiceBean.getEntityManager().createNativeQuery(sqlUpdateProcess);
	// if (queryUpdateProcess.getResultList().size() > 0) {
	// queryUpdateProcess.getSingleResult();
	// }
	//
	// abortarProcesoAnterior(idProcesoActual);
	//
	// //actualizar documentos del proceso
	// String sqlUpdateDocumentos =
	// "update coa_environmental_impact_study.documents_impact_study "
	// + "set dois_process_instance_id = " + idNuevoProceso
	// + " where dois_process_instance_id = " + idProcesoActual ;
	//
	// crudServiceBean.insertUpdateByNativeQuery(sqlUpdateDocumentos.toString(),
	// null);
	// }
	// }
	// }catch(Exception e){
	// e.printStackTrace();
	// }
	// }
	//
	// @SuppressWarnings("unchecked")
	// private void igualFechaTareas(Long idProcesoActual, Long idNuevoProceso,
	// Long idTareaProcesoActual, Long idTareaNuevoProceso) {
	// //igualar fechas tareas
	//
	// String
	// sqlUpdateBamtasksummary="select dblink_exec('"+Constantes.getDblinkBpmsSuiaiii()
	// +"','UPDATE bamtasksummary "
	// +
	// "SET createddate = (select createddate from bamtasksummary where processinstanceid = "
	// + idProcesoActual+" and taskid = "+idTareaProcesoActual+"),  "
	// +
	// "enddate = (select enddate from bamtasksummary where processinstanceid = "
	// + idProcesoActual+" and taskid = "+idTareaProcesoActual+"),  "
	// +
	// "startdate = (select startdate from bamtasksummary where processinstanceid = "
	// + idProcesoActual+" and taskid = "+idTareaProcesoActual+") "
	// +
	// "WHERE processinstanceid = "+idNuevoProceso+" and taskid = "+idTareaNuevoProceso+"') as result";
	//
	// Query queryUpdateBamtasksummary =
	// crudServiceBean.getEntityManager().createNativeQuery(sqlUpdateBamtasksummary);
	// if (queryUpdateBamtasksummary.getResultList().size() > 0) {
	// queryUpdateBamtasksummary.getSingleResult();
	// }
	//
	// String
	// sqlUpdateTask="select dblink_exec('"+Constantes.getDblinkBpmsSuiaiii()
	// +"','UPDATE task "
	// +
	// "set activationtime = (select activationtime from task where processinstanceid = "+idProcesoActual+" and id = "+idTareaProcesoActual+"),  "
	// +
	// "createdon = (select createdon from task where processinstanceid = "+idProcesoActual+" and id = "+idTareaProcesoActual+") "
	// +
	// "where processinstanceid = "+idNuevoProceso+" and id = "+idTareaNuevoProceso+"') as result";
	//
	// Query queryUpdateTask =
	// crudServiceBean.getEntityManager().createNativeQuery(sqlUpdateTask);
	// if(queryUpdateTask.getResultList().size()>0) {
	// queryUpdateTask.getSingleResult();
	// }
	//
	// String
	// sqlTaskevent="select * from dblink('"+Constantes.getDblinkBpmsSuiaiii()+"', "
	// + "' SELECT taskid, logtime, type FROM taskevent  "
	// +
	// "WHERE taskid = "+idTareaProcesoActual+" ') as (id text, time varchar, type text)";
	//
	// List<Object[]> listTaskevents = (List<Object[]>)
	// crudServiceBean.getEntityManager().createNativeQuery(sqlTaskevent).getResultList();
	//
	// for (int t = 0; t < listTaskevents.size(); t++) {
	// Object[] dataTaskevent = (Object[]) listTaskevents.get(t);
	// String
	// sqlUpdateTaskevent="select dblink_exec('"+Constantes.getDblinkBpmsSuiaiii()
	// +"','update taskevent set logtime = ''"+dataTaskevent[1].toString()+"'' "
	// +
	// " where taskid = "+idTareaNuevoProceso+" and type = ''"+dataTaskevent[2].toString()+"'' ') as result";
	//
	// Query queryUpdate =
	// crudServiceBean.getEntityManager().createNativeQuery(sqlUpdateTaskevent);
	// if(queryUpdate.getResultList().size()>0)
	// queryUpdate.getSingleResult();
	// }
	// }
	//
	private void abortarProcesoAnterior(Long idProcesoActual) {
		// abortar proceso anterior

		String sqlUpdateAbortarProcess = "select dblink_exec('"
				+ dblinkBpmsBiodiversidad
				+ "','update processinstancelog set status = 4 "
				+ " where processinstanceid = " + idProcesoActual
				+ "') as result";

		Query queryUpdateAbortarProcess = crudServiceBean.getEntityManager()
				.createNativeQuery(sqlUpdateAbortarProcess);
		if (queryUpdateAbortarProcess.getResultList().size() > 0) {
			queryUpdateAbortarProcess.getSingleResult();
		}

		String sqlUpdateAbortarSumm = "select dblink_exec('"
				+ dblinkBpmsBiodiversidad
				+ "','update bamtasksummary set status = ''Exited'' "
				+ "where processinstanceid =  " + idProcesoActual
				+ "') as result";

		Query queryUpdateAbortarSumm = crudServiceBean.getEntityManager()
				.createNativeQuery(sqlUpdateAbortarSumm);
		if (queryUpdateAbortarSumm.getResultList().size() > 0) {
			queryUpdateAbortarSumm.getSingleResult();
		}

		String sqlUpdate = "select dblink_exec('" + dblinkBpmsBiodiversidad
				+ "','update task set status = ''Exited'' "
				+ "where processinstanceid = " + idProcesoActual
				+ "') as result";

		Query queryUpdate = crudServiceBean.getEntityManager()
				.createNativeQuery(sqlUpdate);
		if (queryUpdate.getResultList().size() > 0) {
			queryUpdate.getSingleResult();
		}
	}

	// }
	//
	//
	// import java.util.ArrayList;
	// import java.util.Date;
	// import java.util.HashMap;
	// import java.util.List;
	// import java.util.Map;
	// import java.util.concurrent.ConcurrentHashMap;
	//
	// import javax.ejb.EJB;
	// import javax.ejb.Stateless;
	// import javax.persistence.Query;
	//
	// import
	// ec.gob.ambiente.rcoa.estudioImpactoAmbiental.model.InformacionProyectoEia;
	// import
	// ec.gob.ambiente.rcoa.estudioImpactoAmbiental.model.ProrrogaModificacionEstudio;
	// import ec.gob.ambiente.rcoa.facade.ProyectoLicenciaCoaFacade;
	// import ec.gob.ambiente.rcoa.model.ProyectoLicenciaCoa;
	// import ec.gob.ambiente.suia.administracion.facade.FeriadosFacade;
	// import ec.gob.ambiente.suia.crud.service.CrudServiceBean;
	// import ec.gob.ambiente.suia.domain.Usuario;
	// import ec.gob.ambiente.suia.proceso.facade.ProcesoFacade;
	// import ec.gob.ambiente.suia.usuario.facade.UsuarioFacade;
	// import ec.gob.ambiente.suia.utils.Constantes;
	// import ec.gob.ambiente.util.Constant;
	//
	// @Stateless
	// public class MigracionFlujoPatenteFacade {
	//
	//
	// @EJB
	// private CrudServiceBean crudServiceBean;
	// @EJB
	// private ProcesoFacade procesoFacade;
	// @EJB
	// private UsuarioFacade usuarioFacade;
	// @EJB
	// private FeriadosFacade feriadosFacade;
	// @EJB
	// private InformacionProyectoEIACoaFacade informacionProyectoEIACoaFacade;
	// @EJB
	// private ProyectoLicenciaCoaFacade proyectoLicenciaCoaFacade;
	// @EJB
	// private ProrrogaModificacionEstudioFacade
	// prorrogaModificacionEstudioFacade;
	//
	//
	// public String dblinkBpmsSuiaiii = Constantes.getDblinkBpmsSuiaiii();
	//
	// public ProrrogaModificacionEstudio prorrogaModificacionEstudio;
	//
	// @SuppressWarnings("unchecked")
	// public void obtenerTareasModificacion(){
	// try {
	//
	// String
	// sql="select * from dblink('"+dblinkBpmsSuiaiii+"','select t.id, t.actualowner_id, t.processinstanceid, v.value, t.activationtime "
	// +
	// "from task t inner join variableinstancelog v on t.processinstanceid = v.processinstanceid "
	// +
	// "where t.formname like ''%modificarEstudioImpactoAmbiental%'' and v.variableid = ''tramite'' "
	// + "and t.status not in (''Completed'',''Exited'',''Ready'') "
	// + "and t.actualowner_id is not null and "
	// +
	// "t.formname is not null and t.processid in (''rcoa.EstudioImpactoAmbiental_v2'', ''rcoa.EstudioImpactoAmbiental'') ') "
	// +
	// "as (id varchar, usuario varchar, processinstanceid varchar, tramite varchar, fecha varchar) "
	// + "order by 1";
	//
	//
	// Query query = crudServiceBean.getEntityManager().createNativeQuery(sql);
	// List<Object> resultList = new ArrayList<Object>();
	// resultList=query.getResultList();
	// List<String[]>listaCodigosProyectos= new ArrayList<String[]>();
	// if (resultList.size() > 0) {
	// for (Object a : resultList) {
	// Object[] row = (Object[]) a;
	// listaCodigosProyectos.add(new String[] { (String) row[0],(String) row[1],
	// (String) row[2], (String) row[3], (String) row[4] });
	// }
	// }
	//
	// for (String[] codigoProyecto : listaCodigosProyectos) {
	//
	// String tramite = codigoProyecto[3];
	//
	// ProyectoLicenciaCoa proyecto =
	// proyectoLicenciaCoaFacade.buscarProyecto(tramite);
	//
	// InformacionProyectoEia estudio =
	// informacionProyectoEIACoaFacade.obtenerInformacionProyectoEIAPorProyecto(proyecto);
	//
	// ProrrogaModificacionEstudio prorroga =
	// prorrogaModificacionEstudioFacade.getPorEstudio(estudio.getId());
	//
	// Date fechaActual = new Date();
	//
	// if(prorroga != null){
	// if(prorroga.getNumeroDiasProrroga() != null){
	//
	// if (prorroga.getFechaFinModificacionProrroga().before(fechaActual) ||
	// prorroga.getFechaFinModificacionProrroga().equals(fechaActual)){
	// Usuario usuario = usuarioFacade.buscarUsuario(codigoProyecto[1]);
	//
	// Map<String, Object> params = new HashMap<String, Object>();
	//
	// params.put("finalizoModificacionEstudio", true);
	// params.put("emitioRespuesta", false);
	//
	// procesoFacade.modificarVariablesProceso(usuario,
	// Long.parseLong(codigoProyecto[2]), params);
	//
	// Map<String, Object> data = new ConcurrentHashMap<String, Object>();
	// procesoFacade.aprobarTarea(usuario, Long.parseLong(codigoProyecto[0]),
	// Long.parseLong(codigoProyecto[2]), data);
	//
	// }
	// }else{
	//
	// if (prorroga.getFechaFinModificacion().before(fechaActual) ||
	// prorroga.getFechaFinModificacion().equals(fechaActual)){
	// Usuario usuario = usuarioFacade.buscarUsuario(codigoProyecto[1]);
	//
	// Map<String, Object> params = new HashMap<String, Object>();
	//
	// params.put("finalizoModificacionEstudio", true);
	// params.put("emitioRespuesta", false);
	//
	// procesoFacade.modificarVariablesProceso(usuario,
	// Long.parseLong(codigoProyecto[2]), params);
	//
	// Map<String, Object> data = new ConcurrentHashMap<String, Object>();
	// procesoFacade.aprobarTarea(usuario, Long.parseLong(codigoProyecto[0]),
	// Long.parseLong(codigoProyecto[2]), data);
	//
	// }
	// }
	// }
	//
	// }
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	//
	// @SuppressWarnings("unchecked")
	// public void terminarTareaPago(){
	// try {
	// String
	// sql="select * from dblink('"+dblinkBpmsSuiaiii+"','select t.id, t.actualowner_id, t.processinstanceid, v.value, t.activationtime "
	// +
	// "from task t inner join variableinstancelog v on t.processinstanceid = v.processinstanceid "
	// +
	// "where t.formname like ''%realizarPagoTerceraRevision%'' and v.variableid = ''tramite'' "
	// + "and t.status not in (''Completed'',''Exited'',''Ready'') "
	// + "and t.actualowner_id is not null and "
	// +
	// "t.formname is not null and t.processid in (''rcoa.EstudioImpactoAmbiental_v2'', ''rcoa.EstudioImpactoAmbiental'') ') "
	// +
	// "as (id varchar, usuario varchar, processinstanceid varchar, tramite varchar, fecha varchar) "
	// + "order by 1";
	//
	//
	// Query query = crudServiceBean.getEntityManager().createNativeQuery(sql);
	// List<Object> resultList = new ArrayList<Object>();
	// resultList=query.getResultList();
	// List<String[]>listaCodigosProyectos= new ArrayList<String[]>();
	// if (resultList.size() > 0) {
	// for (Object a : resultList) {
	// Object[] row = (Object[]) a;
	// listaCodigosProyectos.add(new String[] { (String) row[0],(String) row[1],
	// (String) row[2], (String) row[3], (String) row[4] });
	// }
	// }
	//
	// for (String[] codigoProyecto : listaCodigosProyectos) {
	//
	// String tramite = codigoProyecto[3];
	//
	// ProyectoLicenciaCoa proyecto =
	// proyectoLicenciaCoaFacade.buscarProyecto(tramite);
	//
	// InformacionProyectoEia estudio =
	// informacionProyectoEIACoaFacade.obtenerInformacionProyectoEIAPorProyecto(proyecto);
	//
	// ProrrogaModificacionEstudio prorroga =
	// prorrogaModificacionEstudioFacade.getPorEstudio(estudio.getId());
	//
	// Date fechaActual = new Date();
	//
	// if(prorroga != null){
	// if(prorroga.getNumeroDiasProrroga() != null){
	//
	// if (prorroga.getFechaFinModificacionProrroga().before(fechaActual) ||
	// prorroga.getFechaFinModificacionProrroga().equals(fechaActual)){
	// Usuario usuario = usuarioFacade.buscarUsuario(codigoProyecto[1]);
	//
	// Map<String, Object> params = new HashMap<String, Object>();
	//
	// params.put("pagoTerceraRevision", false);
	//
	// procesoFacade.modificarVariablesProceso(usuario,
	// Long.parseLong(codigoProyecto[2]), params);
	//
	// Map<String, Object> data = new ConcurrentHashMap<String, Object>();
	// procesoFacade.aprobarTarea(usuario, Long.parseLong(codigoProyecto[0]),
	// Long.parseLong(codigoProyecto[2]), data);
	//
	// }
	// }else{
	//
	// if (prorroga.getFechaFinModificacion().before(fechaActual) ||
	// prorroga.getFechaFinModificacion().equals(fechaActual)){
	// Usuario usuario = usuarioFacade.buscarUsuario(codigoProyecto[1]);
	//
	// Map<String, Object> params = new HashMap<String, Object>();
	//
	// params.put("pagoTerceraRevision", false);
	//
	// procesoFacade.modificarVariablesProceso(usuario,
	// Long.parseLong(codigoProyecto[2]), params);
	//
	// Map<String, Object> data = new ConcurrentHashMap<String, Object>();
	// procesoFacade.aprobarTarea(usuario, Long.parseLong(codigoProyecto[0]),
	// Long.parseLong(codigoProyecto[2]), data);
	//
	// }
	// }
	// }
	// }
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	//
	// @SuppressWarnings("unchecked")
	// public void obtenerRegistraFechaReunion(){
	// try {
	//
	// String
	// sql="select * from dblink('"+dblinkBpmsSuiaiii+"','select t.id, t.actualowner_id, t.processinstanceid, v.value, t.activationtime "
	// +
	// "from task t inner join variableinstancelog v on t.processinstanceid = v.processinstanceid "
	// +
	// "where t.formname like ''%registrarFechaReunionAclaratoria%'' and v.variableid = ''tramite'' "
	// + "and t.status not in (''Completed'',''Exited'',''Ready'') "
	// + "and t.actualowner_id is not null and "
	// +
	// "t.formname is not null and t.processid in (''rcoa.EstudioImpactoAmbiental_v2'', ''rcoa.EstudioImpactoAmbiental'') ') "
	// +
	// "as (id varchar, usuario varchar, processinstanceid varchar, tramite varchar, fecha varchar) "
	// + "order by 1";
	//
	//
	// Query query = crudServiceBean.getEntityManager().createNativeQuery(sql);
	// List<Object> resultList = new ArrayList<Object>();
	// resultList=query.getResultList();
	// List<String[]>listaCodigosProyectos= new ArrayList<String[]>();
	// if (resultList.size() > 0) {
	// for (Object a : resultList) {
	// Object[] row = (Object[]) a;
	// listaCodigosProyectos.add(new String[] { (String) row[0],(String) row[1],
	// (String) row[2], (String) row[3], (String) row[4] });
	// }
	// }
	//
	// for (String[] codigoProyecto : listaCodigosProyectos) {
	//
	// String tramite = codigoProyecto[3];
	//
	// ProyectoLicenciaCoa proyecto =
	// proyectoLicenciaCoaFacade.buscarProyecto(tramite);
	//
	// InformacionProyectoEia estudio =
	// informacionProyectoEIACoaFacade.obtenerInformacionProyectoEIAPorProyecto(proyecto);
	//
	// ProrrogaModificacionEstudio prorroga =
	// prorrogaModificacionEstudioFacade.getPorEstudio(estudio.getId());
	//
	// Date fechaActual = new Date();
	//
	// if(prorroga != null){
	// if(prorroga.getNumeroDiasProrroga() != null){
	//
	// if (prorroga.getFechaFinModificacionProrroga().before(fechaActual) ||
	// prorroga.getFechaFinModificacionProrroga().equals(fechaActual)){
	// Usuario usuario = usuarioFacade.buscarUsuario(codigoProyecto[1]);
	//
	// Map<String, Object> data = new ConcurrentHashMap<String, Object>();
	// procesoFacade.aprobarTarea(usuario, Long.parseLong(codigoProyecto[0]),
	// Long.parseLong(codigoProyecto[2]), data);
	//
	// }
	// }else{
	//
	// if (prorroga.getFechaFinModificacion().before(fechaActual) ||
	// prorroga.getFechaFinModificacion().equals(fechaActual)){
	// Usuario usuario = usuarioFacade.buscarUsuario(codigoProyecto[1]);
	//
	// Map<String, Object> data = new ConcurrentHashMap<String, Object>();
	// procesoFacade.aprobarTarea(usuario, Long.parseLong(codigoProyecto[0]),
	// Long.parseLong(codigoProyecto[2]), data);
	//
	// }
	// }
	// }
	//
	// }
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	//
	// @SuppressWarnings("unchecked")
	// public void obtenerTareaSubirActaReunion(){
	// try {
	//
	// String
	// sql="select * from dblink('"+dblinkBpmsSuiaiii+"','select t.id, t.actualowner_id, t.processinstanceid, v.value, t.activationtime "
	// +
	// "from task t inner join variableinstancelog v on t.processinstanceid = v.processinstanceid "
	// +
	// "where t.formname like ''%subirActaReunion%'' and v.variableid = ''tramite'' "
	// + "and t.status not in (''Completed'',''Exited'',''Ready'') "
	// + "and t.actualowner_id is not null and "
	// +
	// "t.formname is not null and t.processid in (''rcoa.EstudioImpactoAmbiental_v2'', ''rcoa.EstudioImpactoAmbiental'') ') "
	// +
	// "as (id varchar, usuario varchar, processinstanceid varchar, tramite varchar, fecha varchar) "
	// + "order by 1";
	//
	//
	// Query query = crudServiceBean.getEntityManager().createNativeQuery(sql);
	// List<Object> resultList = new ArrayList<Object>();
	// resultList=query.getResultList();
	// List<String[]>listaCodigosProyectos= new ArrayList<String[]>();
	// if (resultList.size() > 0) {
	// for (Object a : resultList) {
	// Object[] row = (Object[]) a;
	// listaCodigosProyectos.add(new String[] { (String) row[0],(String) row[1],
	// (String) row[2], (String) row[3], (String) row[4] });
	// }
	// }
	//
	// for (String[] codigoProyecto : listaCodigosProyectos) {
	//
	// String tramite = codigoProyecto[3];
	//
	// ProyectoLicenciaCoa proyecto =
	// proyectoLicenciaCoaFacade.buscarProyecto(tramite);
	//
	// InformacionProyectoEia estudio =
	// informacionProyectoEIACoaFacade.obtenerInformacionProyectoEIAPorProyecto(proyecto);
	//
	// ProrrogaModificacionEstudio prorroga =
	// prorrogaModificacionEstudioFacade.getPorEstudio(estudio.getId());
	//
	// Date fechaActual = new Date();
	//
	// if(prorroga != null){
	// if(prorroga.getNumeroDiasProrroga() != null){
	//
	// if (prorroga.getFechaFinModificacionProrroga().before(fechaActual) ||
	// prorroga.getFechaFinModificacionProrroga().equals(fechaActual)){
	// Usuario usuario = usuarioFacade.buscarUsuario(codigoProyecto[1]);
	//
	// Map<String, Object> data = new ConcurrentHashMap<String, Object>();
	// procesoFacade.aprobarTarea(usuario, Long.parseLong(codigoProyecto[0]),
	// Long.parseLong(codigoProyecto[2]), data);
	//
	// }
	// }else{
	//
	// if (prorroga.getFechaFinModificacion().before(fechaActual) ||
	// prorroga.getFechaFinModificacion().equals(fechaActual)){
	// Usuario usuario = usuarioFacade.buscarUsuario(codigoProyecto[1]);
	//
	// Map<String, Object> data = new ConcurrentHashMap<String, Object>();
	// procesoFacade.aprobarTarea(usuario, Long.parseLong(codigoProyecto[0]),
	// Long.parseLong(codigoProyecto[2]), data);
	//
	// }
	// }
	// }
	//
	// }
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	@SuppressWarnings({ "unchecked", "unused" })
	public void migrarProyectosPatente() {
		try {

			String sqlProcesos = "select * from dblink('"
					+ dblinkBpmsBiodiversidad
					+ "', "
					+ "'select distinct v.processinstanceid, actualowner_id "
					+ "from task t inner join variableinstancelog v on t.processinstanceid = v.processinstanceid "
					+
					// "where t.formname like ''%patentesEmiRenCenResRehJarBotVivOrqZoo%''"
					// +
					"where v.variableid = ''numero_tramite''"
					+ "and t.status not in (''Completed'',''Exited'',''Ready'') "
					+ "and t.actualowner_id is not null "
					+ "and t.deploymentid = ''ec.gob.ambiente:biodiversidad:4.1'' "
					+ "and v.value = (''MAATE-MCMEVS-2023-121'') "
					+ "and t.formname is not null and t.processid like ''%Biodiversidad.PatentesBiodiversidadMCM%'' "
					+ "') as (processinstanceid text, actualowner_id text)";

//			String sqlProcesos = "select * from dblink('"
//			+ dblinkBpmsBiodiversidad 
//			+ "', "
//			+ " 'select id from variableinstancelog where id  = 145130; ') as (id text)";

			Query q_tareas = crudServiceBean.getEntityManager()
					.createNativeQuery(sqlProcesos);

			List<Object[]> listProcesosActivos = (List<Object[]>) q_tareas
					.getResultList();
			if (listProcesosActivos.size() > 0) {
				for (int i = 0; i < listProcesosActivos.size(); i++) {
					try {
						Object[] dataProject = (Object[]) listProcesosActivos
								.get(i);

						Long idProcesoActual = Long.parseLong(dataProject[0]
								.toString());
						User usuario = usuarioFacade.findByUserName(dataProject[1]
								.toString());

						String sqlTareasProcesoActual = "select * from dblink('"
								+ dblinkBpmsBiodiversidad
								+ "', "
								+ "'select id, status, formname from task "
								+ "where processinstanceid = "
								+ idProcesoActual
								+ " order by id') as (id text, status text, formname text)";

						List<Object[]> listTareasProcesoActual = (List<Object[]>) crudServiceBean
								.getEntityManager()
								.createNativeQuery(sqlTareasProcesoActual)
								.getResultList();

						// Boolean existeNormativaPago =
						// Constantes.getPropertyAsBoolean("rcoa.esia.existe.normativa.pago");

						Map<String, Object> processVariables = procesoFacade
								.recuperarVariablesProceso(usuario, idProcesoActual);
						processVariables.put("es_renovacion", false);

						// try {
						// processVariables.get("es_renovacion");
						// processVariables.put("es_renovacion",
						// processVariables.get("es_renovacion")=="true"?true:false);
						// } catch (Exception e) {
						// // TODO: handle exception
						// }
						//
						//
						// try {
						// processVariables.get("patente_observada");
						 processVariables.put("patente_observada", true);
						 processVariables.put("observaciones_subsanadas", true);
//						 processVariables.put("patente_admitida", true);
//						 processVariables.put("plan_manejo_adjuntado", true);
						// processVariables.get("patente_observada")=="true"?true:false);
						// } catch (Exception e) {
						// // TODO: handle exception
						// }
						//
						// try {
						// processVariables.get("observaciones_subsanadas");
						// processVariables.put("observaciones_subsanadas",
						// processVariables.get("observaciones_subsanadas")=="true"?true:false);
						// } catch (Exception e) {
						// // TODO: handle exception
						// }
						//
						// try {
						// processVariables.get("patente_admitida");
						// processVariables.put("patente_admitida",
						// processVariables.get("patente_admitida")=="true"?true:false);
						// } catch (Exception e) {
						// // TODO: handle exception
						// }

						// processVariables.put("patente_observada", true);

						// parametros.put("existeNormativaPago",
						// existeNormativaPago);

						String tramite = (String) processVariables
								.get("numero_tramite");

						Long idNuevoProceso = procesoFacade.iniciarProceso(usuario,
								"Biodiversidad.PatentesBiodiversidadMCM",
								processVariables);

						if (listTareasProcesoActual.size() > 0) {
							for (int j = 0; j < listTareasProcesoActual.size(); j++) {
								Object[] dataTareaProcesoActual = (Object[]) listTareasProcesoActual
										.get(j);
								Long idTareaProcesoActual = Long
										.parseLong(dataTareaProcesoActual[0]
												.toString());
								String estadoTareaProcesoActual = dataTareaProcesoActual[1]
										.toString();

								String sqlTareaActivaNuevoProceso = "select * from dblink('"
										+ dblinkBpmsBiodiversidad
										+ "', "
										+ "'select id, actualowner_id, formname from task "
										+ "where processinstanceid = "
										+ idNuevoProceso
										+ " and status = ''Reserved'' ') as (id text, actualowner_id text, formname text)";

								List<Object[]> listTareaActivaNuevoProceso = (List<Object[]>) crudServiceBean
										.getEntityManager()
										.createNativeQuery(
												sqlTareaActivaNuevoProceso)
										.getResultList();

								if (listTareaActivaNuevoProceso.size() > 0) {
									Object[] dataTarea = (Object[]) listTareaActivaNuevoProceso
											.get(0);

									Long idTareaNuevoProceso = Long
											.parseLong(dataTarea[0].toString());
									User usuarioTarea = usuarioFacade
											.findByUserName(dataTarea[1].toString());

									if (estadoTareaProcesoActual
											.equals("Completed")) {

										// procesoFacade.modificarVariablesProceso(usuarioTarea,
										// idNuevoProceso, processVariables);
										procesoFacade.aprobarTarea(usuarioTarea,
												idTareaNuevoProceso,
												idNuevoProceso, null);
									}

									if (dataTarea[2].toString().equals(
											dataTareaProcesoActual[2].toString())
											|| (dataTarea[2].toString().equals(
													"firmarResponsabilidadEsia") && dataTareaProcesoActual[2]
													.toString()
													.equals("pagarTasaRevisionEsIA"))) {

										igualFechaTareas(idProcesoActual,
												idNuevoProceso,
												idTareaProcesoActual,
												idTareaNuevoProceso);

									}
								}
							}
						}

						// igualar fecha proceso
						String sqlUpdateProcess = "select dblink_exec('"
								+ dblinkBpmsBiodiversidad
								+ "',"
								+ "'update processinstancelog set "
								+ "start_date = (select start_date from  processinstancelog where processinstanceid = "
								+ idProcesoActual
								+ "), "
								+ "parentprocessinstanceid = (select parentprocessinstanceid from  processinstancelog where processinstanceid = "
								+ idProcesoActual + ") "
								+ "where processinstanceid = " + idNuevoProceso
								+ "') as result";

						Query queryUpdateProcess = crudServiceBean
								.getEntityManager().createNativeQuery(
										sqlUpdateProcess);
						if (queryUpdateProcess.getResultList().size() > 0) {
							queryUpdateProcess.getSingleResult();
						}

						abortarProcesoAnterior(idProcesoActual);

						// actualizar documentos del proceso
						String sqlUpdateDocumentos = "update coa_environmental_impact_study.documents_impact_study "
								+ "set dois_process_instance_id = "
								+ idNuevoProceso
								+ " where dois_process_instance_id = "
								+ idProcesoActual;

						crudServiceBean.insertUpdateByNativeQuery(
								sqlUpdateDocumentos.toString(), null);
					} catch (Exception e) {
						System.out.println("error al migrar el proyecto: " +e);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void igualFechaTareas(Long idProcesoActual, Long idNuevoProceso,
			Long idTareaProcesoActual, Long idTareaNuevoProceso) {
		// igualar fechas tareas

		String sqlUpdateBamtasksummary = "select dblink_exec('"
				+ dblinkBpmsBiodiversidad
				+ "','UPDATE bamtasksummary "
				+ "SET createddate = (select createddate from bamtasksummary where processinstanceid = "
				+ idProcesoActual
				+ " and taskid = "
				+ idTareaProcesoActual
				+ "),  "
				+ "enddate = (select enddate from bamtasksummary where processinstanceid = "
				+ idProcesoActual
				+ " and taskid = "
				+ idTareaProcesoActual
				+ "),  "
				+ "startdate = (select startdate from bamtasksummary where processinstanceid = "
				+ idProcesoActual + " and taskid = " + idTareaProcesoActual
				+ ") " + "WHERE processinstanceid = " + idNuevoProceso
				+ " and taskid = " + idTareaNuevoProceso + "') as result";

		Query queryUpdateBamtasksummary = crudServiceBean.getEntityManager()
				.createNativeQuery(sqlUpdateBamtasksummary);
		if (queryUpdateBamtasksummary.getResultList().size() > 0) {
			queryUpdateBamtasksummary.getSingleResult();
		}

		String sqlUpdateTask = "select dblink_exec('"
				+ dblinkBpmsBiodiversidad
				+ "','UPDATE task "
				+ "set activationtime = (select activationtime from task where processinstanceid = "
				+ idProcesoActual
				+ " and id = "
				+ idTareaProcesoActual
				+ "),  "
				+ "createdon = (select createdon from task where processinstanceid = "
				+ idProcesoActual + " and id = " + idTareaProcesoActual + ") "
				+ "where processinstanceid = " + idNuevoProceso + " and id = "
				+ idTareaNuevoProceso + "') as result";

		Query queryUpdateTask = crudServiceBean.getEntityManager()
				.createNativeQuery(sqlUpdateTask);
		if (queryUpdateTask.getResultList().size() > 0) {
			queryUpdateTask.getSingleResult();
		}

		String sqlTaskevent = "select * from dblink('"
				+ dblinkBpmsBiodiversidad + "', "
				+ "' SELECT taskid, logtime, type FROM taskevent  "
				+ "WHERE taskid = " + idTareaProcesoActual
				+ " ') as (id text, time varchar, type text)";

		List<Object[]> listTaskevents = (List<Object[]>) crudServiceBean
				.getEntityManager().createNativeQuery(sqlTaskevent)
				.getResultList();

		for (int t = 0; t < listTaskevents.size(); t++) {
			Object[] dataTaskevent = (Object[]) listTaskevents.get(t);
			String sqlUpdateTaskevent = "select dblink_exec('"
					+ dblinkBpmsBiodiversidad
					+ "','update taskevent set logtime = ''"
					+ dataTaskevent[1].toString() + "'' " + " where taskid = "
					+ idTareaNuevoProceso + " and type = ''"
					+ dataTaskevent[2].toString() + "'' ') as result";

			Query queryUpdate = crudServiceBean.getEntityManager()
					.createNativeQuery(sqlUpdateTaskevent);
			if (queryUpdate.getResultList().size() > 0)
				queryUpdate.getSingleResult();
		}
	}

}
