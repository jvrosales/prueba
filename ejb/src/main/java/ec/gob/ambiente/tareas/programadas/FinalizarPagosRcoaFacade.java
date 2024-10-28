package ec.gob.ambiente.tareas.programadas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.geneticresources.model.ProposedCollection;
import ec.gob.ambiente.enlisy.geneticresources.services.GeneticResourcesCatalogFacade;
import ec.gob.ambiente.enlisy.geneticresources.services.ProposedCollectionFacade;
import ec.gob.ambiente.enlisy.model.Area;
import ec.gob.ambiente.enlisy.model.PatentInformation;
import ec.gob.ambiente.enlisy.model.PatentRequest;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.services.AreaFacade;
import ec.gob.ambiente.enlisy.services.InstitucionFinancieraFacade;
import ec.gob.ambiente.enlisy.services.PatentInformationFacade;
import ec.gob.ambiente.enlisy.services.PatentRequestFacade;
import ec.gob.ambiente.enlisy.services.RolesUserFacade;
import ec.gob.ambiente.enlisy.services.TransaccionFinancieraFacade;
import ec.gob.ambiente.enlisy.services.UserFacade;
import ec.gob.ambiente.exceptions.JbpmException;
import ec.gob.ambiente.jbpm.service.ProcessService;
import ec.gob.ambiente.suia.crud.service.CrudServiceBean;
import ec.gob.ambiente.suia.domain.base.InstitucionFinanciera;
import ec.gob.ambiente.suia.domain.base.TransaccionFinanciera;
import ec.gob.ambiente.suia.recaudaciones.facade.NumeroUnicoTransaccionalFacade;
import ec.gob.ambiente.suia.recaudaciones.model.NumeroUnicoTransaccional;
import ec.gob.ambiente.util.Constant;


@Stateless 
public class FinalizarPagosRcoaFacade {

	
	@EJB
	private CrudServiceBean crudServiceBean;
	@EJB
	private ProcessService procesoFacade;
	@EJB
	private UserFacade usuarioFacade;
	@EJB
	private InstitucionFinancieraFacade institucionFinancieraFacade;
	@EJB
	private TransaccionFinancieraFacade transaccionFinancieraFacade;
	@EJB
	private NumeroUnicoTransaccionalFacade numeroUnicoTransaccionalFacade;
	@EJB
	private AreaFacade areaFacade;
	@EJB
	private RolesUserFacade rolesUserFacade;
	@EJB
	private GeneticResourcesCatalogFacade catalogFacade;
	@EJB
	private ProposedCollectionFacade proposedCollectionFacade;

	@EJB
	private PatentRequestFacade patentRequestFacade;

	@EJB
	private PatentInformationFacade patentInformationFacade;
	
	public String dblinkBpmsSuiaiii = Constant.getDblinkBpmsBiodiversidad();
	private Integer tipoPago;

	
	@SuppressWarnings("unchecked")
	public void obtenerPagosRealizados(){
		try {
			String sql="select reus_id, nut_project_code, sum(case nuts_id when 3 then nut_value else 0 end) totalPagado, sum(nut_value) total "
					+ "from payments.unique_transaction_number "
					+ " where nut_status  and (nut_used is false or nut_used is null) "
					+ " and bnf_date_pay is not null and (bnf_date_pay +  interval '15 minute') < now() "
					+ " and  (nut_project_code like '%ARSFC%' or nut_project_code like '%MCMEVS%' )"
					+ "group by reus_id, nut_project_code "
					+ "having sum(case nuts_id when 3 then nut_value else 0 end)  = sum(nut_value) ";	
			Query query = crudServiceBean.getEntityManager().createNativeQuery(sql);
			List<Object>  resultList = new ArrayList<Object>();
			resultList=query.getResultList();
			if (resultList.size() > 0) {
				for (Object objNut : resultList) {
					Object[] row = (Object[]) objNut;
					Integer solicitudId = (Integer) row[0];
					String codigoProyecto = (String) row[1];
					// busco cual es la tarea de pago 
					obtenerTareasPagoRcoa(codigoProyecto, solicitudId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	public void obtenerTareasPagoRcoa(String codigoProyecto, Integer solicitudId){
		try {
			String sql="select * from dblink('"+dblinkBpmsSuiaiii+"','select t.id, t.actualowner_id, t.processinstanceid, v.value, t.activationtime, bt.taskname, t.processid, p.processname "
					+ "from task t inner join variableinstancelog v on t.processinstanceid = v.processinstanceid "
					+ " inner join bamtasksummary bt on t.id = bt.taskid "
					+ " inner join processinstancelog p on t.processinstanceid = p.processinstanceid "
					+ "where value = ''"+codigoProyecto+"'' and v.variableid = ''numero_tramite'' "
					+ "and t.status in (''Reserved'') "
					+ "and t.actualowner_id is not null and "
					+ " bt.taskname is not null and (lower(bt.taskname) like ''%pago%'' or lower(bt.taskname) like ''%valor %'') and "
					+ "t.processid in( ''Biodiversidad.AutorizacionRecoleccionEspeciesSinFinesComerciales'',   ''Biodiversidad.PatentesBiodiversidadMCM'' ) ') "
					+ "as (id varchar, usuario varchar, processinstanceid varchar, tramite varchar, fecha varchar, nombretarea varchar, processid varchar, procesname varchar) "
					+ "order by 1";
			Query query = crudServiceBean.getEntityManager().createNativeQuery(sql);
			List<Object>  resultList = new ArrayList<Object>();
			resultList=query.getResultList();
			if (resultList.size() > 0) {
				for (Object a : resultList) {
					Object[] objTarea = (Object[]) a;
					Long processInstanceId = Long.parseLong(objTarea[2].toString());
					User usuario = usuarioFacade.findByUserName((String) objTarea[1]);
					boolean correcto =false;
					List<NumeroUnicoTransaccional> listaNuts = numeroUnicoTransaccionalFacade.listBuscaNUTPorIdSolicitud(solicitudId);
					for (NumeroUnicoTransaccional objNut : listaNuts) {

						switch ((String) objTarea[6]) {
							case "Biodiversidad.AutorizacionRecoleccionEspeciesSinFinesComerciales":
								tipoPago = 9;  // pago por recoleccion sin finis comerciales
								break;
							case "Biodiversidad.PatentesBiodiversidadMCM":
								if(((String) objTarea[5]).contains("Registrar pago de inspeccion"))
									tipoPago = 10;  // pago de inspeccion
								else
									tipoPago = 11;  // pago de patente
								break;
						}
						correcto = registrarPago(objNut, objTarea);
						if(!correcto){
							break;
						}
					}
					if(correcto){
						boolean pasoTarea = false;
						switch ((String) objTarea[6]) {
							case "Biodiversidad.AutorizacionRecoleccionEspeciesSinFinesComerciales":
								pasoTarea = finalizarRecoleccion(objTarea, usuario);
								break;
							case "Biodiversidad.PatentesBiodiversidadMCM":
								pasoTarea = finalizarPatente(objTarea, usuario);
								break;
							default:
								break;
						}
						if(pasoTarea)
							finalizarNut(codigoProyecto, Long.parseLong(objTarea[2].toString()));
					}
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@SuppressWarnings("unused")
	public boolean finalizarRecoleccion(Object[] tarea, User usuario) {
		boolean pasaTarea = false;
		Map<String, Object> params = new HashMap<String, Object>();
		try{
			String rol = "Biodiversidad-Director Nacional Biodiversidad";
			User user = rolesUserFacade.findUniqueByRoleName(rol);
			String email = user.getPeople().getEmail();
			if (email != null) {
			//	Email.sendEmail(email, "asuntoMensaje", disenioEmailDeRecoleccion(user.getPeople().getPeopName(), "notificacion.director_firma", tarea[3].toString()));
			}

			// para actualizar la variable de director
			params.put("usuario_director_biodiversidad", user.getUserName());

			// Inicia la instancia del proceso
			procesoFacade.modificarVariablesProceso(usuario, Long.parseLong(tarea[2].toString()), params);
			procesoFacade.aprobarTarea(usuario, Long.parseLong(tarea[0].toString()), Long.parseLong(tarea[2].toString()), null);
			return true;	
		}catch(Exception  e){
			return false;
		}
	}
	
	public boolean finalizarPatente(Object[] tarea, User usuario) throws JbpmException {
		List<User> usersEVSP = new ArrayList<User>();
		//busco el area de la patente
		PatentInformation patentInformation = patentInformationFacade.findByCode(tarea[3].toString());
		PatentRequest patentRequest = patentRequestFacade.findByPatent(patentInformation.getId());
		
		Area areaOT = areaFacade.findByCanton(patentRequest.getGeographicalLocation().getGeographicalLocation());
		Map<String, Object> params = new HashMap<String, Object>();
		// recupero las variables del proceso
		Map<String, Object> variables = procesoFacade.recuperarVariablesProceso(usuario, Long.parseLong(tarea[2].toString()));
		//busco el usuario especialista
		String especialistaBPM = variables.get("usuario_especialista").toString();
		//busco si existe el usuario
		User userAsociadoBPM = usuarioFacade.findByUserName(especialistaBPM);
		// si no existe busco el usuario especialista
		if (userAsociadoBPM.getUserId() == null) {
			String roleName = "usuario_especialista_en_Vida_Silvestre_Provincial"; //ESPECIALISTA_VIDA_SILVESTRE_PROVINCIAL;
			usersEVSP = rolesUserFacade.buscarUsuariosPorRolYArea(roleName, areaOT);
			if(usersEVSP == null || usersEVSP.size() == 0 ) {
				System.out.println("no se encontro especialista de OT para la patente"+tarea[3].toString());
				return false;
			}else{
				userAsociadoBPM = usersEVSP.get(0);	
			}
		} 

		params.put("usuario_especialista",userAsociadoBPM.getUserName());
		params.put("url_informe_inspeccion", "elaborarInformeTecnicoMCM");
		
		// Inicia la instancia del proceso
		procesoFacade.modificarVariablesProceso(usuario, Long.parseLong(tarea[2].toString()), params);
		procesoFacade.aprobarTarea(usuario, Long.parseLong(tarea[0].toString()), Long.parseLong(tarea[2].toString()), null);
		
		return true;
	}
	

	
	private void finalizarNut(String codigoProyecto, Long processInstanceId){
		Query sqlUpdateTarea = crudServiceBean.getEntityManager().createNativeQuery(" update payments.unique_transaction_number "
				+ "set nut_used = true, nut_date_update = now() "
				+ "where nut_id in ( "
				+ " 		select a.nut_id from payments.unique_transaction_number a inner join payments.request_user b on a.reus_id = b.reus_id "
				+ "					inner join payments.documents c on b.reus_id = c.reus_id  "
				+ "		where a.nut_project_code = '"+codigoProyecto+"' and a.nut_status = true  and c.docu_process_instance_id = "+processInstanceId+" and b.reus_status = true "
				+ "		and a.nuts_id = 3 and a.nut_used is not true "
				+ " ) ");
		sqlUpdateTarea.executeUpdate();
	}
	
	public boolean registrarPago(NumeroUnicoTransaccional nut, Object[] objTarea ){
		List<TransaccionFinanciera> transaccionesFinancierasProyecto= new ArrayList<TransaccionFinanciera>();
		// crea la transaccion financiera
		TransaccionFinanciera transaccionFinanciera = new TransaccionFinanciera();
		//busco la institucion financiera
		List<InstitucionFinanciera> institucionesFinancieras = institucionFinancieraFacade.obtenerInstitucionFinancieraPorId(nut.getSolicitudUsuario().getInstitucionFinanciera().getId());
		if(institucionesFinancieras != null && !institucionesFinancieras.isEmpty()){
			transaccionFinanciera.setInstitucionFinanciera(institucionesFinancieras.get(0));
		}
		transaccionFinanciera.setMontoTransaccion(nut.getNutValor());
		//transaccionFinanciera.setMontoPago(nut.getNutValor());
		transaccionFinanciera.setTipodePago(tipoPago);
		transaccionFinanciera.setIdentificadorMotivo(nut.getNutCodigoProyecto());
		transaccionFinanciera.setNumeroTransaccion(nut.getBnfTramitNumber());
		transaccionesFinancierasProyecto.add(transaccionFinanciera);
		//guarado la transacion
		boolean pagoSatisfactorio=transaccionFinancieraFacade.realizarPago(nut.getNutValor(), transaccionesFinancierasProyecto,nut.getNutCodigoProyecto());
		if(pagoSatisfactorio){
			boolean faltaRegistrarTransaccion = true;
			//busco si ya existe transacciones guardadass
			List<TransaccionFinanciera> listaTransacciones = transaccionFinancieraFacade.cargarTransacciones(Long.parseLong(objTarea[2].toString()), Long.parseLong(objTarea[0].toString()));
			if(listaTransacciones != null && listaTransacciones.size() > 0){
				if(transaccionesFinancierasProyecto.size() == 1){
					faltaRegistrarTransaccion = false;
				}else{
					List<TransaccionFinanciera> listaTransaccionesEliminar = new ArrayList<TransaccionFinanciera>();
					for (TransaccionFinanciera objTransaccionFinanciera2 : transaccionesFinancierasProyecto) {
						for (TransaccionFinanciera objTransaccionFinancieraAux : listaTransacciones) {
							if(objTransaccionFinanciera2.getNumeroTransaccion().equals(objTransaccionFinancieraAux.getNumeroTransaccion())){
								listaTransaccionesEliminar.add(objTransaccionFinanciera2);
								break;
							}
						}
					}
					//elimino las transacciones que ya fueron registradas para no volver a registrar
					if(listaTransaccionesEliminar.size() > 0){
						transaccionesFinancierasProyecto.removeAll(listaTransaccionesEliminar);
					}
				}
			}
			
			// actualizo ael nut a usado
			//nut.setNutUsado(true);
			//crudServiceBean.saveOrUpdate(nut);
			//guarado la transacion log si todavia no ha sido registrado
			if(faltaRegistrarTransaccion && transaccionesFinancierasProyecto.size() > 0)
            transaccionFinancieraFacade.guardarTransacciones(transaccionesFinancierasProyecto,
            		Long.parseLong(objTarea[0].toString()), (String) objTarea[5],	Long.parseLong(objTarea[2].toString()), (String) objTarea[6],
            		(String) objTarea[7]);
		}
		pagoSatisfactorio=true;
		return pagoSatisfactorio;
	}
	
	@SuppressWarnings("unused")
	private boolean finalizarRGD(Object[] objTarea, User usuario){
		try {
			String rolDirector = "";
			User usuarioAutoridad;
			String autoridad = "";
			Area areaTramite = new Area();
			Map<String, Object> variables = procesoFacade.recuperarVariablesProceso(usuario, Long.parseLong(objTarea[2].toString()));
			String tramite=(String)variables.get("tramite");

			Map<String, Object> params=new HashMap<>();
			params.put("realizoPago", true);
			params.put("director", autoridad);
//			procesoFacade.modificarVariablesProceso(usuario, tarea.getProcessInstanceId(), params);
//			procesoFacade.aprobarTarea(usuario, tarea.getTaskId(), tarea.getProcessInstanceId(), null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@SuppressWarnings("unused")
	private boolean finalizar(Object[] objTarea, User usuario){
		try {
			Map<String, Object> params=new HashMap<>();
			procesoFacade.modificarVariablesProceso(usuario, Long.parseLong(objTarea[2].toString()), params);
			Map<String, Object> data = new ConcurrentHashMap<String, Object>();
			procesoFacade.aprobarTarea(usuario, Long.parseLong(objTarea[0].toString()), Long.parseLong(objTarea[2].toString()), data);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unused")
	private String disenioEmailDeRecoleccion(String usuario, String tipoNotificacionEnum, String codigoTramite) {

		ProposedCollection propuesta=proposedCollectionFacade.findByCode(codigoTramite);
		
		String header = catalogFacade.findByCode("html_template.email.header.default").getGrcaDescription();
		String footer = catalogFacade.findByCode("html_template.email.footer.default").getGrcaDescription();
		String content = catalogFacade.findByCode("html_template.email.content.default").getGrcaDescription();
		String mensaje = catalogFacade.findByCode(tipoNotificacionEnum).getGrcaDescription();
		mensaje = mensaje.replace("numero_codigo", codigoTramite);
		if(propuesta != null){
			if(propuesta.getResearchApplication() != null){
				mensaje = mensaje.replace("nombre_proyecto", propuesta.getResearchApplication().getReapTitle());
			}else{
				mensaje = mensaje.replace("nombre_proyecto", propuesta.getProposedCollection().getResearchApplication().getReapTitle());
			}
		}
		mensaje = mensaje.replace("numero_tramite", codigoTramite);
		String disenio = header.replace("usuario", usuario) + content.replace("mensaje", mensaje) + footer;
		return disenio;
	}	
}
