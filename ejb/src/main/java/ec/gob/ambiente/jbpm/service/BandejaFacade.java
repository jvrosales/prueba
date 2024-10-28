package ec.gob.ambiente.jbpm.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import ec.gob.ambiente.suia.crud.service.CrudServiceBean;
import ec.gob.ambiente.util.Constant;

@Stateless
public class BandejaFacade {
	
private static final Logger LOG = Logger.getLogger(BandejaFacade.class);
	
	@EJB
	private CrudServiceBean crudServiceBean;
	
	@EJB
	private ProcessService processService;
	public String dblinkBpmsSuiaiii= Constant.getDblinkBpmsBiodiversidad();
	
	public String nombreFormulario(long taskId) {
		String formulario = "";
		try {	
			
			String sql = "select * "
					+ "from dblink('"+dblinkBpmsSuiaiii+"',' "
					+ "select id,t.formname as descripcion "
					+ "from "
					+ "task t "
					+ "where "
					+ "id = " + taskId  
					+"  ') "
					+ "as ( "
					+ "taskid bigint, "
					+ "descripcion character varying(255))";
			Query query = crudServiceBean.getEntityManager().createNativeQuery(sql);
			@SuppressWarnings("unchecked")
			List<Object[]> resultList = (List<Object[]>) query.getResultList();			
			if (resultList.size() > 0) {
				for (int i = 0; i < resultList.size(); i++) {
					Object[] dataProject = (Object[]) resultList.get(i);
					formulario = dataProject[1].toString();
				}
			}

		} catch (Exception ex) {
			LOG.error("Ocurrió un error recuperando tareas del usuario en el sistema suia", ex);
		}
		return formulario;
	}

}
