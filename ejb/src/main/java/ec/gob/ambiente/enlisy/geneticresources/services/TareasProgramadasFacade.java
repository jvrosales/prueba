package ec.gob.ambiente.enlisy.geneticresources.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.suia.crud.service.CrudServiceBean;
import ec.gob.ambiente.util.Constant;

@Stateless
public class TareasProgramadasFacade {

	@EJB
	private CrudServiceBean crudServiceBean;

	public String dblinkBpmsBiodiversidad = Constant.getDblinkBpmsBiodiversidad();
	
	
	@SuppressWarnings("unchecked")
	public List<Object> buscarProyectosArchivarFinalizar(String taskName, Integer days) {

		Integer d1 = days + 1;
		Integer d2 = days + 2;

		String days1 = d1.toString();
		String days2 = d2.toString();

		try {

			String sql = " select * from dblink('" + dblinkBpmsBiodiversidad
					+ "','select  b.userid ,  v.value,  b.taskname, b.processinstanceid, t.id " + 
					"from  public.processinstancelog p  " + 
					"inner join public.bamtasksummary b on p.processinstanceid = b.processinstanceid " + 
					"inner join public.variableinstancelog v on  v.processinstanceid = p.processinstanceid " + 
					"inner join public.task t on t.processinstanceid = p.processinstanceid " + 
					"where  p.processid like ''%Biodiversidad.PatentesBiodiversidadMCM%'' " + 
					"and b.taskname = ''"+taskName+"'' " + 
					"and t.name = ''"+taskName+"'' " + 
					"and b.status = ''Reserved'' " + 
					"and t.status = ''Reserved'' " +
					"and (now()+ cast(''0 days'' as interval)) between (CAST(b.createddate AS DATE) + cast(''"+days1+" days'' as interval)) and (CAST(b.createddate AS DATE) + cast(''"+days2+" days'' as interval)) " + 
					"and v.variableid = ''numero_tramite''') " + 
					"as (usuario varchar, tramite varchar, taskname varchar, processinstanceid varchar, taskid varchar);"; 


			Query query = crudServiceBean.getEntityManager().createNativeQuery(sql);

			List<Object> resultList = new ArrayList<Object>();
			resultList = query.getResultList();
			List<String[]> listaCodigosProyectos = new ArrayList<String[]>();
			if (resultList.size() > 0) {
				for (Object a : resultList) {
					Object[] row = (Object[]) a;
					listaCodigosProyectos.add(new String[] { (String) row[0], (String) row[1] });
				}
			}
			return resultList;
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> buscarProyectosNotificarCorreo(String taskName, Integer days) {



		try {

			String sql = " select * from dblink('" + dblinkBpmsBiodiversidad
					+ "','select distinct on (v.value) v.value as value, b.userid ,   b.taskname, b.processinstanceid, t.id " + 
					"from  public.processinstancelog p " + 
					"inner join public.bamtasksummary b on p.processinstanceid = b.processinstanceid " + 
					"inner join public.variableinstancelog v on  v.processinstanceid = p.processinstanceid " + 
					"inner join public.task t on	t.processinstanceid = p.processinstanceid  " + 
					"where  p.processid like ''%Biodiversidad.PatentesBiodiversidadMCM%'' " + 
					"and b.taskname = ''"+taskName+"'' " + 
					"and t.name = ''"+taskName+"'' " + 
					"and b.status = ''Reserved'' " + 
					"and (CAST(now() +  cast(''0 days'' as interval) AS DATE)) = (CAST(b.createddate + cast(''"+days.toString()+" days'' as interval) AS DATE)) " + 
					"and v.variableid = ''numero_tramite''') " + 
					"as (usuario varchar, tramite varchar, taskname varchar, processinstanceid varchar, taskid varchar);"; 


			Query query = crudServiceBean.getEntityManager().createNativeQuery(sql);

			List<Object> resultList = new ArrayList<Object>();
			resultList = query.getResultList();
			List<String[]> listaCodigosProyectos = new ArrayList<String[]>();
			if (resultList.size() > 0) {
				for (Object a : resultList) {
					Object[] row = (Object[]) a;
					listaCodigosProyectos.add(new String[] { (String) row[0], (String) row[1] });
				}
			}
			return resultList;
		} catch (NoResultException e) {
			return null;
		}
	}
	
	
	
	
	
	public String buscarUsuarioSolicitanteProyecto(String proyecto) {
		
		
		try {
			
			String sql = " select * from dblink('" + dblinkBpmsBiodiversidad
					+ "','select v.value"
					+ " from public.variableinstancelog v"
					+ " where v.processinstanceid = (select processinstanceid from public.variableinstancelog v2 where v2.value = ''"+proyecto+"'')"
					+ " and variableid = ''usuario_solicitante''') " + 
					"as (value varchar)"; 
			
			
			Query query = crudServiceBean.getEntityManager().createNativeQuery(sql);
			
			String result = "";
			result = (String) query.getSingleResult();
			
			return result;
		} catch (NoResultException e) {
			return null;
		}
	}
	
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@SuppressWarnings("unchecked")
	public List<Object> resultadoMasCuatroDias() {
		try {
			String sql = " select * from dblink('" + dblinkBpmsBiodiversidad
					+ "','select b.userid ,v.value from public.processinstancelog p "
					+ "inner join public.bamtasksummary b on p.processinstanceid = b.processinstanceid	"
					+ "inner join public.variableinstancelog v on v.processinstanceid = p.processinstanceid "
					+ "where p.processid like ''%Biodiversidad.PatentesBiodiversidadMCM%'' "
					+ "and  b.taskname = ''Revisar Solicitud'' and b.status = ''Reserved'' "
					+ "and b.createddate <= (now() - CAST(''4 days'' AS INTERVAL)) "
					+ "and v.variableid = ''numero_tramite'' ') " + "as (usuario varchar, tramite varchar)";
			Query query = crudServiceBean.getEntityManager().createNativeQuery(sql);
			List<Object> resultList = new ArrayList<Object>();
			resultList = query.getResultList();
			List<String[]> listaCodigosProyectos = new ArrayList<String[]>();
			if (resultList.size() > 0) {
				for (Object a : resultList) {
					Object[] row = (Object[]) a;
					listaCodigosProyectos.add(new String[] { (String) row[0], (String) row[1] });
				}
			}
			return resultList;
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Object> resultadoMasCincoDias() {

		try {
			String sql = " select * from dblink('" + dblinkBpmsBiodiversidad
					+ "','select b.userid ,v.value from public.processinstancelog p "
					+ "inner join public.bamtasksummary b on p.processinstanceid = b.processinstanceid	"
					+ "inner join public.variableinstancelog v on v.processinstanceid = p.processinstanceid "
					+ "where p.processid like ''%Biodiversidad.PatentesBiodiversidadMCM%'' "
					+ "and  b.taskname = ''Subsanar observaciones'' and b.status = ''Reserved'' "
					+ "and  ((b.createddate <= (now() - CAST('5 days' AS INTERVAL))) "
					+ "or (b.createddate <= (now() - CAST('10 days' AS INTERVAL))) "
					+ "or (b.createddate <= (now() - CAST('15 days' AS INTERVAL)))) "
					+ "and v.variableid = ''numero_tramite'' ') " + "as (usuario varchar, tramite varchar)";
			System.out.println(sql);
			Query query = crudServiceBean.getEntityManager().createNativeQuery(sql);
			List<Object> resultList = new ArrayList<Object>();
			resultList = query.getResultList();
			List<String[]> listaCodigosProyectos = new ArrayList<String[]>();
			if (resultList.size() > 0) {
				for (Object a : resultList) {
					Object[] row = (Object[]) a;
					listaCodigosProyectos.add(new String[] { (String) row[0], (String) row[1] });
				}
			}
			return resultList;
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Object> resultadoVeinteDias() {
		try {
			String sql = " select * from dblink('" + dblinkBpmsBiodiversidad
					+ "','select b.userid ,v.value from public.processinstancelog p "
					+ "inner join public.bamtasksummary b on p.processinstanceid = b.processinstanceid	"
					+ "inner join public.variableinstancelog v on v.processinstanceid = p.processinstanceid "
					+ "where p.processid like ''%Biodiversidad.PatentesBiodiversidadMCM%'' "
					+ "and  b.taskname = ''Subsanar observaciones'' and b.status = ''Reserved'' "
					+ "and b.createddate <= (now() - CAST(''20 days'' AS INTERVAL)) "
					+ "and v.variableid = ''numero_tramite'' ') " + "as (usuario varchar, tramite varchar)";
			Query query = crudServiceBean.getEntityManager().createNativeQuery(sql);
			List<Object> resultList = new ArrayList<Object>();
			resultList = query.getResultList();
			List<String[]> listaCodigosProyectos = new ArrayList<String[]>();
			if (resultList.size() > 0) {
				for (Object a : resultList) {
					Object[] row = (Object[]) a;
					listaCodigosProyectos.add(new String[] { (String) row[0], (String) row[1] });
				}
			}
			return resultList;
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Object> resultadoTreintaDias() {
		try {
			String sql = " select * from dblink('" + dblinkBpmsBiodiversidad
					+ "','select b.userid ,v.value from public.processinstancelog p "
					+ "inner join public.bamtasksummary b on p.processinstanceid = b.processinstanceid	"
					+ "inner join public.variableinstancelog v on v.processinstanceid = p.processinstanceid "
					+ "where p.processid like ''%Biodiversidad.PatentesBiodiversidadMCM%'' "
					+ "and  b.taskname = ''Descargar, elaborar y adjuntar el plan de manejo'' and b.status = ''Reserved'' "
					+ "and b.createddate <= (now() - CAST(''30 days'' AS INTERVAL)) "
					+ "and v.variableid = ''numero_tramite'' ') " + "as (usuario varchar, tramite varchar)";
			Query query = crudServiceBean.getEntityManager().createNativeQuery(sql);
			List<Object> resultList = new ArrayList<Object>();
			resultList = query.getResultList();
			List<String[]> listaCodigosProyectos = new ArrayList<String[]>();
			if (resultList.size() > 0) {
				for (Object a : resultList) {
					Object[] row = (Object[]) a;
					listaCodigosProyectos.add(new String[] { (String) row[0], (String) row[1] });
				}
			}
			return resultList;
		} catch (NoResultException e) {
			return null;
		}
	}

	
	@SuppressWarnings("unchecked")
	public List<Object> buscarProyecto(String proyecto) {
		try {

			String sql = "select * from dblink('"+dblinkBpmsBiodiversidad+"', " + 
						 "'select processinstanceid, processid, status, actualowner_id, name, id from task t where processinstanceid = (select processinstanceid from public.variableinstancelog where value like ''"+proyecto+"'' order by 1 desc limit 1) and status =''Reserved''') " + 
						 "as (processinstanceid varchar, processid varchar,  status varchar, actualowner_id varchar, name varchar, taskid varchar);"; 
			Query query = crudServiceBean.getEntityManager().createNativeQuery(sql);

			List<Object> resultList = new ArrayList<Object>();
			resultList = query.getResultList();
			List<String[]> listaCodigosProyectos = new ArrayList<String[]>();
			if (resultList.size() > 0) {
				for (Object a : resultList) {
					Object[] row = (Object[]) a;
					listaCodigosProyectos.add(new String[] { (String) row[0], (String) row[1] });
				}
			}
			return resultList;
		} catch (NoResultException e) {
			return null;	
		}
	}
	
	
}