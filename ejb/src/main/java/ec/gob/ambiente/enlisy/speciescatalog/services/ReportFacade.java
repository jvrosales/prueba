package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.axis.encoding.Base64;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import ec.gob.ambiente.alfresco.connection.ConnectionCmisAlfrescoSUIA;
import ec.gob.ambiente.alfresco.connection.Documents;
import ec.gob.ambiente.alfresco.service.AlfrescoServiceBean;
import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.speciescatalog.model.SpeciePhotograph;
import ec.gob.ambiente.util.Constant;

/**
 * Servicios para la recuperacion de los datos de los reportes publicos
 * @author EXCO
 *
 */
@Stateless
public class ReportFacade extends AbstractFacade<SpeciePhotograph, Integer> {
	
	public ReportFacade() {
		super(SpeciePhotograph.class, Integer.class);		
	}
		
	@EJB
	private SpeciePhotographFacade speciePhotographFacade;
	
	@EJB(lookup = Constant.ALFRESCO_SERVICE_BEAN)
    private AlfrescoServiceBean alfrescoServiceBean;
	
	@Inject
    ConnectionCmisAlfrescoSUIA cmisAlfrescoSUIA;

	
	/**
	 * Obtener Tematicas: filtros de provincias, cites y listas rojas
	 * @return Text - Json
	 */
	@SuppressWarnings("unchecked")
	public String getTematicas(String parm_cod_lst)
	{
		try
		{
			List<Object[]> cuerpo = new ArrayList<Object[]>();
			Query q = super.getEntityManager().createNativeQuery("select cast((select row_to_json(dt) from(" + 
					"select( select array_to_json(array_agg(row_to_json(t)))" + 
					"    	from (" + 
					"			select rlec_id codigo, rlec_name || ' (' || rlec_initial || ')' as nombre,  rlec_initial as filter " + 
					"			from biodiversity.red_lists_ecuador" + 
					"			where rlec_status = true ) t) as lista_roja_ec," +
					" (select spls_title from biodiversity.species_lists where spls_id = " + parm_cod_lst +") title, " +
					" (select array_to_json(array_agg(row_to_json(t)))" +
					" from (select (select thfl_id_element from biodiversity.thematic_filters WHERE thfl_id=sltf.thfl_id) id_element," +
					" sltf_conditional conditional, sltf_visible visible FROM biodiversity.species_lists_thematic_filters sltf" +
					" where sltf_status = true and spls_id = "+ parm_cod_lst + ") t) filtros," +
					" (select array_to_json(array_agg(row_to_json(t)))" + 
					"    	from (" + 
					"			select cite_id codigo, cite_name nombre" + 
					"			from biodiversity.cites" + 
					"			where cite_status = true) t) as cites," +
					" (select array_to_json(array_agg(row_to_json(t)))" + 
					"    	from (" + 
					"			select 'I' codigo, 'Apendice I' nombre union all" + 
					"			select 'II' codigo, 'Apendice II' nombre) t) as cms," +
					" (select array_to_json(array_agg(row_to_json(t)))" + 
					"    	from (" + 
					"			select gelo_id codigo,gelo_name nombre" + 
					"			from public.geographical_locations" + 
					"			where gelo_parent_id = 1 and gelo_status = true" + 
					"			order by gelo_codification_inec)t) as provincias," + 
					"  (select row_to_json(taxon) as taxonomia from(" + 
					"select (" + 
					"	select array_to_json(array_agg(row_to_json(t)))" + 
					"    	from (" + 
					"			select hicl_id codigo, hicl_id_parent codigo_padre, hicl_scientific_name nombre " + 
					"			from biodiversity.higher_classifications" + 
					"			where hicl_taxon_rank_name = 'Reino' and hicl_status=true" + 
					"			order by tara_id) t) as reino,(" + 
					"	select array_to_json(array_agg(row_to_json(t)))" + 
					"    	from (" + 
					"			select hicl_id codigo, hicl_id_parent codigo_padre, hicl_scientific_name nombre " + 
					"			from biodiversity.higher_classifications" + 
					"			where hicl_taxon_rank_name = 'Phylum' and hicl_status=true" + 
					"			order by tara_id) t)as phylum,(" + 
					"	select array_to_json(array_agg(row_to_json(t)))" + 
					"    	from (" + 
					"			select hicl_id codigo, hicl_id_parent codigo_padre, hicl_scientific_name nombre " + 
					"			from biodiversity.higher_classifications" + 
					"			where hicl_taxon_rank_name = 'Clase' and hicl_status=true" + 
					"			order by tara_id) t) as clase,(" + 
					"	select array_to_json(array_agg(row_to_json(t)))" + 
					"    	from (" + 
					"			select hicl_id codigo, hicl_id_parent codigo_padre, hicl_scientific_name nombre " + 
					"			from biodiversity.higher_classifications" + 
					"			where hicl_taxon_rank_name = 'Orden' and hicl_status=true" + 
					"			order by tara_id) t) as orden,(" + 
					"	select array_to_json(array_agg(row_to_json(t)))" + 
					"    	from (" + 
					"			select hicl_id codigo, hicl_id_parent codigo_padre, hicl_scientific_name nombre " + 
					"			from biodiversity.higher_classifications" + 
					"			where hicl_taxon_rank_name = 'Familia' and hicl_status=true" + 
					"			order by tara_id) t) as familia,(" + 
					"	select array_to_json(array_agg(row_to_json(t)))" + 
					"    	from (" + 
					"			select hicl_id codigo, hicl_id_parent codigo_padre, hicl_scientific_name nombre " + 
					"			from biodiversity.higher_classifications" + 
					"			where hicl_taxon_rank_name = 'Género' and hicl_status=true" + 
					"			order by tara_id) t)as genero) taxon)) dt) as text) as tematicas ");
			
			cuerpo=(q.getResultList());
			if (cuerpo.isEmpty()) {
				return new String();
			} else {
				return String.valueOf(cuerpo.get(0));
			}
		}
		catch(NoResultException e)
		{
			return new String();
		}
	}

	/**
	 *Obtener el conteo del Listado de Especies
	 * @return Text
	 */
	@SuppressWarnings("unchecked")
	public String getCuentaListaEspecies(String parm_cod_lst, int page, String filters)
	{
		try
		{
			String str_where;			
			List<Object[]> cuerpo = new ArrayList<Object[]>();
			
			Query q =  super.getEntityManager().createNativeQuery(
					"select case when sl.spls_filter is null or sl.spls_filter ='' " +
					"then '' else ' where ' || sl.spls_filter end str_where " +
					"from biodiversity.species_lists sl where sl.spls_id = :parm_cod_lst "
					);
			int cod_lst = Integer.parseInt(parm_cod_lst);
			q.setParameter("parm_cod_lst", cod_lst);
			
			cuerpo= q.getResultList();
			
			if (cuerpo.isEmpty()) {
				str_where="";
			} else {
				str_where = String.valueOf(cuerpo.get(0));
			}
			
			String searchCriteria = "";
			if (filters != null && !filters.isEmpty()) {
				searchCriteria = (str_where.isEmpty() ? " where " : " and ") + filters;
			}
			
			q =  super.getEntityManager().createNativeQuery("select count(*) num_reg " 
					+ "from biodiversity.species_list_catalogs s" + str_where + searchCriteria);			

			cuerpo = q.getResultList();
			
			if (cuerpo.isEmpty()) {
				return new String();
			} else {
				return String.valueOf(cuerpo.get(0));
			}
		}
		catch(NoResultException e)
		{
			return new String();
		}
	}
	
	
	/**
	 *Obtener Listado de Especies
	 * @return Text - Json
	 */
	@SuppressWarnings("unchecked")
	public String getListaEspecies(String parm_cod_lst, int page, String filters, int exportAll)
	{
		int registrosPorPagina = exportAll==1 ? 3500 : 10;
		int offset = (page - 1) * registrosPorPagina;
		try
		{
			String str_where;			
			List<Object[]> cuerpo = new ArrayList<Object[]>();
			
			Query q =  super.getEntityManager().createNativeQuery(
					"select case when sl.spls_filter is null or sl.spls_filter ='' " +
					"then '' else ' where ' || sl.spls_filter end str_where " +
					"from biodiversity.species_lists sl where sl.spls_id = :parm_cod_lst "
					);
			int cod_lst = Integer.parseInt(parm_cod_lst);
			q.setParameter("parm_cod_lst", cod_lst);
			
			cuerpo= q.getResultList();
			
			if (cuerpo.isEmpty()) {
				str_where="";
			} else {
				str_where = String.valueOf(cuerpo.get(0));
			}
			
			String searchCriteria = "";
			if (filters != null && !filters.isEmpty()) {
				searchCriteria = (str_where.isEmpty() ? " where " : " and ") + filters;
			}
			
			if(exportAll == 1){				
				q =  super.getEntityManager().createNativeQuery("select cast((select json_agg(json_build_array("
					+ "rango_taxon->>'Clase', rango_taxon->>'Orden', rango_taxon->>'Familia', rango_taxon->>'Género', "
					+ "nombre_cientifico, autor, año, taxonrank, nomenclaturalcode, establishmentmeans, lr, lr_uicn, gui)) AS resultado " 
					+ "from (select * from biodiversity.species_list_catalogs " + str_where + searchCriteria 
					+ " order by orden, nombre_cientifico) s) as text) as listado"); 
					/*+ " order by orden, nombre_cientifico LIMIT :registrosPorPagina OFFSET :offset) s) as text) as listado"); */
				}else{
				q =  super.getEntityManager().createNativeQuery("select cast(( select array_to_json(array_agg(row_to_json(t))) from ("
					+ "select publish, id, higher_classification, nombre_cientifico, gui, lr, ecuador, endemica, exotica, nativa, invasora, "
					+ "migratoria, domestica, vernacular_names, artificial_groups,	ecosistemas, areanatural, id_alfresco, image, "
					+ "autor, año, taxonomia, cites, cite_id, provincias, rango_taxon, taxonrank, rlec_name, nomenclaturalcode, "
					+ "cms, comercial_use, establishmentmeans, lr_uicn, continental, insular, is_forestal, is_exotic, orden " 
					+ "from biodiversity.species_list_catalogs s" + str_where + searchCriteria 
					+ " order by orden, nombre_cientifico LIMIT :registrosPorPagina OFFSET :offset) t ) as text) as listado"); /* order by 3 */				
					q.setParameter("registrosPorPagina", registrosPorPagina);
					q.setParameter("offset", offset);
			}			

			cuerpo = q.getResultList();
			
			if (cuerpo.isEmpty()) {
				return new String();
			} else {
				return String.valueOf(cuerpo.get(0));
			}
		}
		catch(NoResultException e)
		{
			return new String();
		}
	}
	
	/**
	 * Obtener Listado de Listas Rojas
	 *  @return Text - Json	 *  
	 */
	@SuppressWarnings("unchecked")
	public String getListaEspeciesLR()
	{
		try
		{
			List<Object[]> cuerpo = new ArrayList<Object[]>();
			Query q =  super.getEntityManager().createNativeQuery("select cast((select array_to_json(array_agg(row_to_json(t))) from (" + 
					"				SELECT spsu_publish publish,s.spta_id id,spta_scientific_name nombre_cientifico,spta_scientific_gui gui,spta_endemic endemica, rlec_initial lr ,spsu_author autor, " + 
					"				spsu_publication_year año, spta_migratory migratoria, spta_hierarchy_code taxonomia, cite_id cites, " + 
					"				array_to_string(array( " + 
					"				select pro.province_id from biodiversity.species_taxa_provinces pro " + 
					"				where pro.sptp_status=true " + 
					"				and pro.spta_id=s.spta_id order by 1 ), ', ') provincias " + 
					"				FROM biodiversity.species_taxa s left join " + 
					"				biodiversity.species_summaries on species_summaries.spta_id = s.spta_id and spsu_status=true left join " + 
					"				biodiversity.red_lists_ecuador on red_lists_ecuador.rlec_id = s.rlec_id and rlec_status=true " + 
					"				where spta_ecuador = true and spta_status=true and rlec_initial in ('EX','EW','CR','EN','VU') " + 
					"				order by 2) t) as text) as listaroja ");
			
			cuerpo=(q.getResultList());
			if (cuerpo.isEmpty()) {
				return new String();
			} else {
				return String.valueOf(cuerpo.get(0));
			}
		}
		catch(NoResultException e)
		{
			return new String();
		}
	}
	
	/**
	 * Obtener Listado de Eventos
	 *  @return Text - Json	 *  
	 */
	@SuppressWarnings("unchecked")
	public String getDatosVisor()
	{
		try
		{
			List<Object[]> cuerpo = new ArrayList<Object[]>();
			Query q =  super.getEntityManager().createNativeQuery(
					"select cast((select row_to_json(t0) from("+
					"select (select array_to_json(array_agg(row_to_json(t))) from( " + 
					"with tbl_fotos_eventos as(" + 
					"select roed_id_process as processid, roed_alfresco_id id_alfresco, 'data:' || roed_mime || ';base64,' image " + 
					"from biodiversity.run_over_events_documents roed " + 
					"where roed_status = true and roed_mime like '%image%' " + 
					"union " + 
					"select rsd_id_process as processid, rsd_alfresco_id, 'data:' || rsd_mime || ';base64,' " + 
					"from biodiversity.rescue_events_documents red " + 
					"where rsd_status = true and rsd_mime like '%image%' " + 
					"union " + 
					"select red_id_process as processid, red_alfresco_id, 'data:' || red_mime || ';base64,' " + 
					"from biodiversity.retentions_events_documents " + 
					"where red_status = true and red_mime like '%image%' " + 
					"union " + 
					"select pwed_id_process, pwed_alfresco_id, 'data:' || pwed_mime || ';base64,' " + 
					"from biodiversity.people_wildlife_events_documents pwed " + 
					"where pwed_status = true and pwed_mime like '%image%'), " +
					"temp_conn as (" + 
					"select ('dbname=' || (select bigc_value from biodiversity.biodiversity_general_catalogs where bigc_code = 'param_bdname_jbpm') || " + 
					"' user=' || (select bigc_value from biodiversity.biodiversity_general_catalogs where bigc_code = 'param_user_jbpm') || " + 
					"' password=' || (select bigc_value from biodiversity.biodiversity_general_catalogs where bigc_code = 'param_pass_jbpm'))as str_conn) " +
					"select" +
					" case when geda_code is null and " + 
					" geda_source = (SELECT bigc_id FROM biodiversity.biodiversity_general_catalogs WHERE bigc_code = 'inaturalist') then " + 
					" 'INATURALIST - ' || geda_inaturalist_id else geda_code end codigo_evento, bigc_description tipo_evento, geda_event_date fecha_evento, geda_informer_name persona_informa_evento," + 
					"	(select bigc_description from biodiversity.biodiversity_general_catalogs bgc2 where bgc2.bigc_id = geda_source and bgc2.bigc_status=true) as fuente_evento," + 
					"	geda_observations descripcion_evento," + 
					"	array("+
					" select cast ('{\"id_alfresco\":\"' || id_alfresco || '\", \"image\":\"' || image ||'\"}' as json) from tbl_fotos_eventos where processid = geda_code"+
					" union all" + 
					" select cast ('{\"id_alfresco\":\"\", \"image\":\"' || gc1.geda_inaturalist_picture ||'\"}' as json) " + 
					" from biodiversity.general_data gc1 where gc1.geda_id = gc.geda_id and gc1.geda_code is null and " + 
					" gc1.geda_source = (SELECT bigc_id FROM biodiversity.biodiversity_general_catalogs WHERE bigc_code = 'inaturalist')" +
					" ) fotografia_evento,	" + 
					"	(case when pil.status = 0 then 'Pendiente' when pil.status = 1 then 'Activo' when pil.status = 2 then 'Completado' when pil.status = 3 then 'Abortado' when pil.status = 4 then 'Suspendido' end) as estado_evento," + 
					"	geda_x lng, geda_y lat  " + 
					"from temp_conn,biodiversity.general_data gc inner join  " + 
					"	biodiversity.biodiversity_general_catalogs bgc on   " + 
					"	gc.geda_event_type = bgc.bigc_id and bgc.bigc_status=true" + 
					"	left join " + 
					"	dblink(temp_conn.str_conn,'SELECT status, value FROM public.variableinstancelog vil inner join public.processinstancelog pil on " + 
					"	vil.processinstanceid = pil.processinstanceid ')" + 
					"    AS pil(status int, value varchar(255)) on" + 
					"	gc.geda_code = pil.value	" + 
					"where geda_status=true  " + 
					"order by 2)t) as lista_eventos, " +
					"(select array ( " + 
					"select cast('{\"name\":\"' || bgc.bigc_description || '\",' || bgc.bigc_code ||'}' as json) " + 
					"from biodiversity.biodiversity_general_catalogs bgc " + 
					"inner join biodiversity.biodiversity_catalog_types bct " + 
					"on bct.bict_id = bgc.bict_id " + 
					"where bict_code in ('capas_visor') " + 
					"and bct.bict_status = true and bgc.bigc_status = true " +
					"order by bgc.bigc_value) as capas_tematicas)" + 					
					") as t0) as text) as datos_visor ");
			cuerpo=(q.getResultList());
			if (cuerpo.isEmpty()) {
				return new String();
			} else {
				return String.valueOf(cuerpo.get(0));
			}
		}
			
			/*** Para cuando se envia con las imagenes en byteArray para recorerse todos los registros***/
			/*cuerpo=(q.getResultList());
			if (cuerpo.isEmpty()) {
				return new String();
			} else {
				JSONParser parserResult = new JSONParser();
				JSONArray jsArray = (JSONArray)parserResult.parse(String.valueOf(cuerpo.get(0)));
				
				//Iterate over results dataset array
	            Iterator<JSONObject> iteratorDS = jsArray.iterator();
	            
				//Recorre el DS 
	        	while (iteratorDS.hasNext()) {
					JSONObject jsonItem = iteratorDS.next();
					JSONArray jsArrFotos = (JSONArray) jsonItem.get("fotografia_evento");
					for (int i = 0; i < jsArrFotos.size(); i++) {
						byte[] dataImage = getImageAlfresco(String.valueOf(jsArrFotos.get(i)));
						if (dataImage != null)
							jsArrFotos.set(i, Base64.encode(dataImage));
					}
				}
				
				return String.valueOf(jsArray);
			}
		}
		catch (ParseException e) {
			return new String();
		}*/
		catch(NoResultException e)
		{
			return new String();
		}
	}
	
	/**
	 * Obtener Datos estadísticos
	 *  @return Text - Json	 *  
	 */
	@SuppressWarnings("unchecked")
	public String getEstadisticas()
	{
		try
		{
			List<Object[]> cuerpo = new ArrayList<Object[]>();
			Query q =   super.getEntityManager().createNativeQuery(/* - Número de especies por área protegida (por reino)  */ 
					"select cast((select row_to_json(t) from( " + 
					"select (select row_to_json(t) chart1 from( " + 
					"with t01 as (with t as(select count(*) numero,pa.sppa_name as label, hicl_scientific_name reino " + 
					"from biodiversity.species_taxa st  " + 
					"inner join biodiversity.species_protected_areas pa " + 
					"on pa.spta_id = st.spta_id " + 
					"inner join biodiversity.higher_classifications hc " + 
					"on cast(substring(st.spta_hierarchy_code from '(^\\d*);') as integer) = hc.hicl_id " + 
					"where st.spta_status = true and pa.sppa_status = true " + 
					"group by pa.sppa_name,hicl_scientific_name " + 
					"order by 3,2), " + 
					"t1 as (select distinct label from t), " + 
					"t2 as (select distinct reino from t), " + 
					"t3 as (select label, reino from t1,t2) " + 
					"select * from t union all " + 
					"select 0 numero, label, reino " + 
					"from t3 " + 
					"where label || reino not in (select label || reino from t) " + 
					"group by label,reino " + 
					"order by 3,2) " + 
					"select array(select reino from t01 " + 
					"group by reino " + 
					"order by reino) as labels, array_to_json(array_agg(row_to_json(t02))) as datasets from( " + 
					"select label, ARRAY_AGG (numero) as data from t01 " + 
					"group by label " + 
					"order by label) t02)t), " + 
					/* - Número de especies por categoría de listas rojas (por reino) */  
					"(select row_to_json(t) chart2 from( " + 
					"with t01 as (with t as(select count(*) numero,spta_red_list_ec as label, hicl_scientific_name reino " + 
					"from biodiversity.species_taxa st  " + 
					"inner join biodiversity.higher_classifications hc " + 
					"on cast(substring(st.spta_hierarchy_code from '(^\\d*);') as integer) = hc.hicl_id " + 
					"where st.spta_status = true and spta_red_list_ec is not null and spta_red_list_ec <>'' " + 
					"group by spta_red_list_ec,hicl_scientific_name " + 
					"order by hicl_scientific_name), " + 
					"t1 as (select distinct label from t), " + 
					"t2 as (select distinct reino from t), " + 
					"t3 as (select label, reino from t1,t2) " + 
					"select * from t union all " + 
					"select 0 numero, label, reino " + 
					"from t3 " + 
					"where label || reino not in (select label || reino from t) " + 
					"group by label,reino " + 
					"order by 3,2) " + 
					"select array(select reino from t01 " + 
					"group by reino " + 
					"order by reino) as labels, array_to_json(array_agg(row_to_json(t02))) as datasets from( " + 
					"select label, ARRAY_AGG (numero) as data from t01 " + 
					"group by label " + 
					"order by label)	t02) t), " + 
					/* - Número de especies por categoría CITES (por reino) */ 
					"(select row_to_json(t) chart3 from( " + 
					"with t01 as (with t as(select count(*) numero,spta_cites_name as label, hicl_scientific_name reino " + 
					"from biodiversity.species_taxa st  " + 
					"inner join biodiversity.higher_classifications hc " + 
					"on cast(substring(st.spta_hierarchy_code from '(^\\d*);') as integer) = hc.hicl_id " + 
					"where st.spta_status = true and spta_cites_name is not null and spta_cites_name <> '' " + 
					"group by spta_cites_name,hicl_scientific_name " + 
					"order by 3,2), " + 
					"t1 as (select distinct label from t), " + 
					"t2 as (select distinct reino from t), " + 
					"t3 as (select label, reino from t1,t2) " + 
					"select * from t union all " + 
					"select 0 numero, label, reino " + 
					"from t3 " + 
					"where label || reino not in (select label || reino from t) " + 
					"group by label,reino " + 
					"order by 3,2) " + 
					"select array(select reino from t01 " + 
					"group by reino " + 
					"order by reino) as labels, array_to_json(array_agg(row_to_json(t02))) as datasets from( " + 
					"select label, ARRAY_AGG (numero) as data from t01 " + 
					"group by label " + 
					"order by label)	t02) t), " + 
					/* - Número de especies por reino Animal y Plantae endémicas, exóticas (clasificadas por exóticas y exóticas invasoras). */  
					"(select row_to_json(t) chart4 from(with t0 as ( " + 
					"select count(*) numero,'Total' grupo   " + 
					"from biodiversity.species_taxa st    " + 
					"where st.spta_status = true and (spta_ecuador = true or spta_native=true) and spta_higher_classification like 'Animal%' " + 
					"union all " + 
					"select count(*) numero,'Endémicas' grupo   " + 
					"from biodiversity.species_taxa st    " + 
					"where st.spta_status = true and spta_endemic = true and (spta_ecuador = true or spta_native=true) and spta_higher_classification like 'Animal%' " + 
					"union all " + 
					"select count(*) ,'Exóticas' grupo   " + 
					"from biodiversity.species_taxa st    " + 
					"where st.spta_status = true and spta_exotic = true and spta_alien = false and spta_ecuador = true and spta_higher_classification like 'Animal%' " + 
					"union all " + 
					"select count(*),'Exóticas invasoras' grupo   " + 
					"from biodiversity.species_taxa st    " + 
					"where st.spta_status = true and spta_exotic = true and spta_alien = true and spta_ecuador = true and spta_higher_classification like 'Animal%')   " + 
					"select array(select grupo from t0) as labels,    " + 
					"array(select row_to_json(t01) as datasets from(select 'Número especies: ' as label, array(select numero from t0) as data) t01) datasets) t)," + 
					"(select row_to_json(t) chart5 from(with t0 as ( " + 
					"select count(*) numero,'Total' grupo   " + 
					"from biodiversity.species_taxa st    " + 
					"where st.spta_status = true and (spta_ecuador = true or spta_native=true) and spta_higher_classification like 'Plantae%' " + 
					"union all " + 
					"select count(*) numero,'Endémicas' grupo   " + 
					"from biodiversity.species_taxa st    " + 
					"where st.spta_status = true and spta_endemic = true and (spta_ecuador = true or spta_native=true) and spta_higher_classification like 'Plantae%' " + 
					"union all " + 
					"select count(*) ,'Exóticas' grupo   " + 
					"from biodiversity.species_taxa st    " + 
					"where st.spta_status = true and spta_exotic = true and spta_alien = false and spta_ecuador = true and spta_higher_classification like 'Plantae%' " + 
					"union all  " + 
					"select count(*),'Exóticas invasoras' grupo   " + 
					"from biodiversity.species_taxa st    " + 
					"where st.spta_status = true and spta_exotic = true and spta_alien = true and spta_ecuador = true and spta_higher_classification like 'Plantae%') " + 
					"select array(select grupo from t0) as labels, " + 
					"array(select row_to_json(t01) as datasets from(select 'Número especies: ' as label, array(select numero from t0) as data) t01) datasets) t) " +
					")t) as text) as estadisticas");			
			cuerpo=(q.getResultList());
			if (cuerpo.isEmpty()) {
				return new String();
			} else {
				return String.valueOf(cuerpo.get(0));
			}
		}
		catch(NoResultException e)
		{
			return new String();
		}
	}
	
	/**
	 * Obtener Sumario General Especie
	 *  @return Text - Json	 *  
	 */
	@SuppressWarnings("unchecked")
	public String getSumarioGeneral(String gui_specie)
	{
		try
		{
			List<Object[]> cuerpo = new ArrayList<Object[]>();
			Query q =  super.getEntityManager().createNativeQuery("select cast((select row_to_json(t) from( " + 
					"select spta_scientific_name scientific_name,spta_scientific_gui gui, " + 
					"spta_scientific_name_authorship author, spta_name_published_year publication_year, " + 
					"(select array_to_json(array_agg(row_to_json(t))) from (select vena_vernacular_name nombre,  " + 
					"CASE when tala_name is not null and tala_name != '' then tala_name else 'N/D' end lenguaje, " + 
					"case when etgr_name is not null and etgr_name != '' then etgr_name else 'N/D' end grupo_etnico, " + 
					"case when inso_short_reference is not null and inso_short_reference != '' then inso_short_reference else 'N/D' end citacorta " + 
					"from biodiversity.vernacular_names vn " + 
					"left join biodiversity.ethnic_groups eg on eg.etgr_id =  vn.etgr_id and eg.etgr_status = true " + 
					"left join biodiversity.taxa_languages tl on tl.tala_id = vn.tala_id and tl.tala_status = true " + 
					"left join biodiversity.information_sources inso on inso.inso_id = vn.inso_id and inso_status = true " + 
					"where vn.spta_id = st.spta_id and vena_status = true order by tl.tala_id) t) vernacular_name, " + 
					"COALESCE(rlec_initial,'NE') rl_initial, " + 
					"(select array_to_json(array_agg(row_to_json(t))) from (select 'spph_id_' || spph_id id, spph_author autor, spph_title titulo, spph_description description,  " + 
					"	   'data:' || spph_mime || ';base64,' mime from biodiversity.species_photographs sp " + 
					"	   where sp.spta_id = st.spta_id and sp.spph_status = true and sp.spph_photograph_general = true and sp.spph_type_photograph='GENERAL') t) img_general, " + 
					"(select array_to_json(array_agg(row_to_json(t))) from (select 'spph_id_' || spph_id id, spph_author autor, spph_title titulo, spph_description description, " + 
					"	   'data:' || spph_mime || ';base64,' mime from biodiversity.species_photographs sp2 " + 
					"	   where sp2.spta_id = st.spta_id and sp2.spph_status = true and sp2.spph_photograph_general != true and sp2.spph_type_photograph='PARTICULAR') t) img_particular, " + 
					"(select array_to_json(array_agg(row_to_json(t))) from (select 'spdm_id_' || spdm_id id, spdm_author autor, spdm_title titulo, spdm_description description, " + 
					"	'data:' || spdm_mime || ';base64,' mime, " + 
					" case when spdm_type_photograph = 'DISTRIBUCIONUNO' then 1  " + 
					"	  when spdm_type_photograph = 'DISTRIBUCIONDOS' then 2 " + 
					" 	  when spdm_type_photograph = 'DISTRIBUCIONTRES' then 3 end as orden " + 
					"	   from biodiversity.species_distribution_maps dm " + 
					"	   where dm.spsu_id = su.spsu_id and dm.spdm_status = true order by orden) t) img_dis_map, " + 
					"(select json_object(array( select  hicl_taxon_rank_name " + 
					"				FROM biodiversity.higher_classifications hc " + 
					"				where (st.spta_hierarchy_code LIKE '%;' || hc.hicl_id || ';%' or st.spta_hierarchy_code LIKE hc.hicl_id || ';%') " + 
					"				and  hc.hicl_status = true order by hc.tara_id),  " + 
					"				array( select hc.hicl_scientific_name " + 
					"				FROM biodiversity.higher_classifications hc " + 
					"				where (st.spta_hierarchy_code LIKE '%;' || hc.hicl_id || ';%' or st.spta_hierarchy_code LIKE hc.hicl_id || ';%') " + 
					"				and  hc.hicl_status = true order by hc.tara_id))) taxonomia, " + 
					"spta_specific_infraspecifc_epit epiteto," +
					"case when spta_endemic then 'Endémica' else 'false' end endemic," +
					"case when spta_exotic then 'Exótica' else 'false' end exotic," +
					"case when spta_alien then 'Invasora' else 'false' end alien," +
					"case when spta_native then 'De Ecuador' else 'false' end native," +
					"case when spta_migratory then 'Migratoria' else 'false' end migratory," + 
					"COALESCE(rlec_name,'No evaluado') rlec_name,spta_red_list_uicn uicn_name, " + 
					"case when spta_red_list_uicn is not null then false else true end hide_uicn, " + 
					"(array_to_json(array( select '<strong>Cites: </strong> ' || (case when spta_cites_name is not null and spta_cites_name !='' then " +
					"'<a href=\"https://speciesplus.net/species#/taxon_concepts?taxon_concept_query=' || spta_scientific_name || '\" target=\"_blank\">' || spta_cites_name || " + 
					"case when spta_cites_criteria is not null and spta_cites_criteria !='' then ' (' || spta_cites_criteria || ')' else '' end || '</a>' " + 
					"else 'No listado' end) || (case when st.spta_cites_year is not null then ', fecha de inclusión: ' || TO_CHAR(st.spta_cites_year, 'DD-MM-YYYY') else '' end) " + 
					"from biodiversity.species_taxa " + 
					"where spta_id = st.spta_id " + 
					"union all " + 
					"select '<strong>CMS: </strong> ' || case when spta_cms is not null and spta_cms!='' then " + 
					"'<a href=\"https://speciesplus.net/species#/taxon_concepts?taxon_concept_query=' || spta_scientific_name || '\" target=\"_blank\"> Apéndice ' || spta_cms || '</a>' " +
					"else 'No listado' end || case when spta_cms_date is not null then ', fecha de inclusión: ' || TO_CHAR(spta_cms_date, 'DD-MM-YYYY') else '' end " + 
					"from biodiversity.species_taxa " + 
					"where spta_id = st.spta_id " + 
					"union all " + 
					"SELECT '<strong>' || a.agre_name || '</strong>, fecha de inclusión: ' || TO_CHAR(sta.sagr_date, 'DD-MM-YYYY') " + 
					"FROM biodiversity.species_taxa_agreements sta " + 
					"inner join biodiversity.agreements a on " + 
					"a.agre_id = sta.agre_id and a.agre_status = true " + 
					"where sta.sagr_status = true and sta.spta_id = st.spta_id))) agreements, " +
					/*"case when spta_cites_name is not null then false else true end hide_cites, " +*/ 
					"(select case when CURRENT_TIMESTAMP between stv.spve_date_start and stv.spve_date_finish then " + 
					"'<strong>Veda: </strong> Acuerdo Ministerial ' || " + 
					"(case when v.veda_url is not null then '<a href=\"' || v.veda_url || '\" target=\"_blank\">' || v.veda_ministerial_agreement || '</a>' else " +
					"v.veda_ministerial_agreement end) || ' de fecha ' || TO_CHAR(v.veda_date_publish , 'DD-MM-YYYY') || '.<br>' " +
					"|| 'Inicio: '|| TO_CHAR(stv.spve_date_start, 'DD-MM-YYYY') || ', Fin: ' || TO_CHAR(stv.spve_date_finish, 'DD-MM-YYYY') else null end vedas " + 
					"from biodiversity.species_taxa_vedas stv inner join " + 
					"biodiversity.vedas v on stv.veda_id = v.veda_id and v.veda_status = true " + 
					"where stv.spta_id = st.spta_id and stv.spve_status = true), " +
					"(select array_to_json(array_agg(row_to_json(t))) from (select bigc.bigc_id id,bigc_description grupo, " + 
					"(select array_to_json(array_agg(row_to_json(d))) from (select stis_name nombre,stis_url url, " + 
					"case when stis_principal = true then 0 else inso_id end orden " + 
					"from biodiversity.species_taxa_information_sources stis where stis.spta_id = st.spta_id and  " + 
					"stis.stis_status = true and bigc.bigc_id = stis.bigc_id order by orden) d) det_fuentes " + 
					"from biodiversity.biodiversity_general_catalogs bigc where bigc.bigc_id in  " + 
					"(select bigc_id from biodiversity.species_taxa_information_sources stis0 where stis0.spta_id = st.spta_id and stis0.stis_status = true) " + 
					"order by bigc_description) t) fuentes, " + 
					"spsu_description description, " + 
					"array_to_json(array(select spta_scientific_name from biodiversity.species_taxa " + 
					"where spta_id in (select UNNEST (cast(string_to_array(replace(biodiversity.get_synonyms_specie(st.spta_id),'nd','0'),';') as int[]))))) sinonimos, " + 
					"spsu_distribution distribution, spsu_altitudinal_rank altitudinal_rank, " + 
					"(select array_to_json(array_agg(row_to_json(t))) from (select ha.habi_name nombre, inso_short_reference fuente  " + 
					"from biodiversity.species_taxa_habits sth inner join  " + 
					"biodiversity.habits ha on ha.habi_id = sth.habi_id and ha.habi_status = true inner join " + 
					"biodiversity.information_sources inso on inso.inso_id = sth.inso_id and inso_status = true " + 
					"where sth.stha_status = true and sth.spta_id = st.spta_id order by ha.habi_name) t) AS habito, " + 
					"case when spta_comercial_use = true then (select bcca_value from biodiversity.biodiversity_catalog_parameters where bcca_code = 'fichas.texto.use_comercial' and bcca_status=true) else null end use_comercial, " +
					"spsu_conservation_measure conservation_measure, " + 
					"COALESCE(rlec_name,'No evaluado') || ' (' || COALESCE(rlec_initial,'NE') || case when spta_red_list_ec_criteria is not null and  " + 
					"spta_red_list_ec_criteria !='' then ' ' || spta_red_list_ec_criteria else '' end  || ')' lr_info, " + 
					"COALESCE(spta_red_list_uicn,'') || ' (' || COALESCE(rlui_initial,'NE') || case when spta_red_list_uicn_criteria is not null and " +
					"spta_red_list_ec_criteria !='' then ' ' || spta_red_list_uicn_criteria else '' end || ')' uicn_info, " + 
					"case when spta_cites_name is not null and spta_cites_name !='' then spta_cites_name else 'No incluida' end " +
					"|| case when spta_cites_criteria is not null and spta_cites_criteria !='' then ' (' || spta_cites_criteria || ')' else '' end cites_info, " + 
					"array_to_json(array(select gl.gelo_name from biodiversity.species_taxa_provinces pr inner join  " + 
					"geographical_locations gl on pr.province_id = gl.gelo_id and gl.gelo_status = true " + 
					"where pr.sptp_status = true AND pr.spta_id = st.spta_id order by pr.province_id)) AS provincia, " + 
					"array_to_json(array(select pa.sppa_name from  biodiversity.species_protected_areas pa " + 
					"where pa.sppa_status = true AND pa.spta_id = st.spta_id order by pa.sppa_name)) AS areanatural, " + 
					"array_to_json(array(select eco.spec_name from  biodiversity.species_ecosystems eco " + 
					"where eco.spec_status = true AND eco.spta_id = st.spta_id order by eco.spec_name)) AS ecosistemas, " + 
					"(select bcca_value from biodiversity.biodiversity_catalog_parameters where bcca_code = 'fichas.texto.mapa_dis' and bcca_status=true) as txt_map_dis, " +
					"array_to_json(array(select is2.inso_long_reference " + 
					"FROM biodiversity.information_sources is2 " + 
					"inner join biodiversity.species_taxa_literary_references stlr on " + 
					"stlr.inso_id = is2.inso_id and is2.inso_status = true " + 
					"where stlr.spta_id = st.spta_id and stlr.stlr_status = true " + 
					"and (is2.inso_long_reference is not null or is2.inso_long_reference !=''))) literary_references, "+
					"case when spsu_author is not null and spsu_author != '' then spsu_author || ' ' else '' end " + 
					"|| CAST(COALESCE(spsu_publication_year,date_part('year',spsu_date_create)) AS VARCHAR(4)) || '. ' " + 
					"|| '<span class=\"uk-text-italic uk-text-bold\">' || spta_scientific_name || '</span>. ' " +
					"|| (select bcca_value from biodiversity.biodiversity_catalog_parameters where bcca_code = 'fichas.texto.cita' and bcca_status=true) ||  " + 
					"' [ Consulta: ' citas, spsu_editor editores, spsu_reviser revisores,spsu_collaborator colaboradores, " + 
					"spsu_date_update date_update, " + 
					"(select bcca_value from biodiversity.biodiversity_catalog_parameters where bcca_code = 'sumario.general.access.rights' and bcca_status=true) as txt_pie_sumario, " +
					"(select bcca_value from biodiversity.biodiversity_catalog_parameters where bcca_code = 'reportes.texto.pie_pagina' and bcca_status=true) as pie_pagina_sumario " + 
					"from biodiversity.species_taxa st  " + 
					"left join biodiversity.species_summaries su on " + 
					"su.spta_id = st.spta_id and spsu_status = true " + 
					"left join biodiversity.red_lists_ecuador rl on  " + 
					"rl.rlec_id = st.rlec_id and rlec_status=true " + 
					"left join biodiversity.red_lists_uicn rluicn on  " + 
					"rluicn.rlui_id = st.rlui_id and rlui_status=true " + 
					"where spta_scientific_gui = '"+ gui_specie +"')t) as text) as sumario");
			
			cuerpo=(q.getResultList());
			if (cuerpo.isEmpty()) {
				return new String();
			} else {
				String resultado = String.valueOf(cuerpo.get(0));
				String str_imagenes = getDataImage(gui_specie);
				if(!str_imagenes.isEmpty()) {
					resultado = resultado.substring(0, resultado.length()-1).concat("," + str_imagenes + "}");
				}
				return resultado;
			}
		}
		catch(NoResultException e)
		{
			return new String();
		}
	}
	
	/**
	 * Obtener Sumario Especies Exóticas
	 *  @return Text - Json	 *  
	 */
	@SuppressWarnings("unchecked")
	public String getSumarioExoticas(String gui_specie)
	{
		try
		{
			List<Object[]> cuerpo = new ArrayList<Object[]>();
			
			Query q =  super.getEntityManager().createNativeQuery("select cast((select row_to_json(t) from( " + 
					"select spta_scientific_name scientific_name,spta_scientific_gui gui, " + 
					"spta_scientific_name_authorship author, spta_name_published_year publication_year, " + 
					"(select array_to_json(array_agg(row_to_json(t))) from (select 'spph_id_' || spph_id id, spph_author autor, spph_title titulo, spph_description description, " + 
					"	   'data:' || spph_mime || ';base64,' mime from biodiversity.species_photographs sp " + 
					"	   where sp.spta_id = st.spta_id and sp.spph_status = true and sp.spph_photograph_general = true and sp.spph_type_photograph='GENERAL') t) img_general, " + 
					"(select array_to_json(array_agg(row_to_json(t))) from (select 'spph_id_' || spph_id id, spph_author autor, spph_title titulo, spph_description description, " + 
					"	   'data:' || spph_mime || ';base64,' mime from biodiversity.species_photographs sp2 " + 
					"	   where sp2.spta_id = st.spta_id and sp2.spph_status = true and sp2.spph_photograph_general != true and sp2.spph_type_photograph = 'PARTICULAR') t) img_particular, " + 
					"(select json_object(array( select  hicl_taxon_rank_name " + 
					"				FROM biodiversity.higher_classifications hc " + 
					"				where (st.spta_hierarchy_code LIKE '%;' || hc.hicl_id || ';%' or st.spta_hierarchy_code LIKE hc.hicl_id || ';%') " + 
					"				and  hc.hicl_status = true order by hc.tara_id),  " + 
					"				array( select hc.hicl_scientific_name " + 
					"				FROM biodiversity.higher_classifications hc " + 
					"				where (st.spta_hierarchy_code LIKE '%;' || hc.hicl_id || ';%' or st.spta_hierarchy_code LIKE hc.hicl_id || ';%') " + 
					"				and  hc.hicl_status = true order by hc.tara_id))) taxonomia,  " + 
					"case when spta_exotic then 'Exótica' else '' end || case when spta_exotic and spta_alien then ', ' else '' end || case when spta_alien then 'Invasora' else '' end status, " + 
					"(select array_to_json(array_agg(row_to_json(t))) from (select vena_vernacular_name nombre,  " + 
					"CASE when tala_name is not null and tala_name != '' then tala_name else 'N/D' end lenguaje, " + 
					"case when etgr_name is not null and etgr_name != '' then etgr_name else 'N/D' end grupo_etnico,  " + 
					"case when inso_short_reference is not null and inso_short_reference != '' then inso_short_reference else 'N/D' end citacorta " + 
					"from biodiversity.vernacular_names vn " + 
					"left join biodiversity.ethnic_groups eg on eg.etgr_id =  vn.etgr_id and eg.etgr_status = true " + 
					"left join biodiversity.taxa_languages tl on tl.tala_id = vn.tala_id and tl.tala_status = true " + 
					"left join biodiversity.information_sources inso on inso.inso_id = vn.inso_id and inso_status = true " + 
					"where vn.spta_id = st.spta_id and vena_status = true order by tl.tala_id) t) vernacular_name, " + 
					"seas_morphological_description morphological_description, " + 
					"array_to_json(array(select '<li><i>' || spta_scientific_name || '</i>&nbsp;&nbsp;<spam style=\"color:Gray;\">' || COALESCE(spta_scientific_name_authorship, '' ) || " + 
					"	  '&nbsp;'|| case when spta_name_published_year is not null and spta_name_published_year!='' then '('||CAST(spta_name_published_year AS VARCHAR(4)) || ')' else '' end " +
					"||'</spam></li>'  from biodiversity.species_taxa " + 
					"where spta_id in (select UNNEST (cast(string_to_array(replace(biodiversity.get_synonyms_specie(st.spta_id),'nd','0'),';') as int[]))))) sinonimos, " + 
					"seas_native_distributive_area native_distributive_area,seas_natural_environment natural_environment, " + 
					"array_to_json(array(select gl.gelo_name from biodiversity.species_taxa_provinces pr inner join  " + 
					"geographical_locations gl on pr.province_id = gl.gelo_id and gl.gelo_status = true " + 
					"where pr.sptp_status = true AND pr.spta_id = st.spta_id order by pr.province_id)) AS provincia, " + 
					"array_to_json(array(select pa.sppa_name from  biodiversity.species_protected_areas pa " + 
					"where pa.sppa_status = true AND pa.spta_id = st.spta_id order by pa.sppa_name)) AS areanatural, " + 
					"array_to_json(array(select eco.spec_name from  biodiversity.species_ecosystems eco " + 
					"where eco.spec_status = true AND eco.spta_id = st.spta_id order by eco.spec_name)) AS ecosistemas, " + 
					"seas_introduction_year introduction_year,seas_introduction_reason introduction_reason, " + 
					"(select array_to_json(array_agg(row_to_json(d))) from (select ieas_title title, ieas_author author, ieas_place_introduction place_introduction, " + 
					"ieas_date_introduction date_introduction, bigcf.bigc_description type_introduction, cain_name cause_introduction, " + 
					"ieas_description description, bigc.bigc_description cat_invasion, case when ieas_exotic_in_region then 'Sí' else 'No' end exotic_in_region " + 
					"from biodiversity.introduction_exotic_alien_species ieas  " + 
					"left join biodiversity.biodiversity_general_catalogs bigcf on bigcf.bigc_id = ieas.bigc_id_form and bigcf.bigc_status=true " + 
					"left join biodiversity.cause_introduction cain on cain.cain_id = ieas.cain_id " + 
					"left join biodiversity.biodiversity_general_catalogs bigc on bigc.bigc_id = ieas.bigc_id and bigc.bigc_status=true " + 
					"where ieas.spta_id = st.spta_id and ieas.ieas_status = true order by ieas_id) d) proy_introduccion, " + 
					"array(select '<li><i>' || sptaf.spta_scientific_name || '</i>&nbsp;&nbsp;<spam style=\"color:Gray;\">' || COALESCE(sptaf.spta_scientific_name_authorship, '' ) || " + 
					"	  '&nbsp;'|| case when sptaf.spta_name_published_year is not null and sptaf.spta_name_published_year!='' then '('|| CAST(sptaf.spta_name_published_year AS VARCHAR(4)) || ')' else '' end " +
					"||'</spam></li>' from biodiversity.species_taxa sptaf inner join biodiversity.affected_species afsp on " + 
					"afsp.spta_id_affected = sptaf.spta_id and afsp_status=true and sptaf.spta_status=true " + 
					"where afsp.seas_id=seas.seas_id) affected_species, " + 
					"seas_ecological_impact ecological_impact, seas_economic_impact economic_impact, seas_social_impact social_impact, " + 
					"seas_health_impact health_impact, " + 
					"array(select cadi_name from biodiversity.catalog_dispersion cadi inner join biodiversity.dispersion_exotic_alien_species deas on " + 
					"deas.cadi_id = cadi.cadi_id and deas_status=true and cadi_status=true " + 
					"where deas.seas_id=seas.seas_id) dispersion_features, " + 
					"array(select crep_name from biodiversity.catalog_reproductions crep inner join biodiversity.reproduction_exotic_alien_species reas on " + 
					"reas.crep_id = crep.crep_id and reas_status=true and crep_status=true " + 
					"where reas.seas_id=seas.seas_id) reproduction_features, " + 
					"array(select cdie_name from biodiversity.catalog_diets cdie inner join biodiversity.diet_exotic_alien_species diea on " + 
					"diea.cdie_id = cdie.cdie_id and diea_status=true and cdie_status=true " + 
					"where diea.seas_id=seas.seas_id) diet_features, " + 
					"array(select bigc_description from biodiversity.biodiversity_general_catalogs bigc inner join biodiversity.dispersion_vectors dive on " + 
					"dive.bigc_id = bigc.bigc_id and dive_status=true and bigc_status=true " + 
					"where dive.seas_id=seas.seas_id) dispersion_vectors_features, " + 
					"array(select bigc_description from biodiversity.biodiversity_general_catalogs bigc inner join biodiversity.dispersion_routes diro on " + 
					"diro.bigc_id = bigc.bigc_id and diro_status=true and bigc_status=true " + 
					"where diro.seas_id=seas.seas_id) dispersion_routes_features, " + 
					"array(select cabf_name from biodiversity.catalog_biological_forms cabf inner join biodiversity.biological_forms bifo on " + 
					"bifo.cabf_id = cabf.cabf_id and bifo_status=true and cabf_status=true " + 
					"where bifo.seas_id=seas.seas_id) biological_forms_features, " + 
					"array(select caus_name ||': '|| ueas_description from biodiversity.catalog_uses caus inner join biodiversity.uses_exotic_alien_species ueas on " + 
					"ueas.caus_id = caus.caus_id and ueas_status=true and caus_status=true " + 
					"where ueas.seas_id=seas.seas_id) uses_features, " + 
					"array(select bigc_description || ': ' || inen_description from biodiversity.biodiversity_general_catalogs bigc inner join biodiversity.invasion_environments inen on " + 
					"inen.bigc_id = bigc.bigc_id and inen_status=true and bigc_status=true " + 
					"where inen.seas_id=seas.seas_id) invasion_environments_features, " + 
					"seas_physical_control physical_control, seas_chemical_control chemical_control, seas_biologic_control biologic_control, " + 
					"seas_prevention_measure prevention_measure, seas_risk_analysis risk_analysis,  " + 
					"(select array_to_json(array_agg(row_to_json(t))) from (" + 
					"select gl.gelo_name provincia, stne.spen_endemic endemic, stne.spen_exotic exotic, stne.spen_invasive invasive " + 
					"from biodiversity.species_taxa_national_endemism stne inner join " + 
					"public.geographical_locations gl on gl.gelo_id = stne.province_id and gl.gelo_status = true " + 
					"where stne.spta_id = st.spta_id and (stne.spen_endemic = true or stne.spen_exotic = true or stne.spen_exotic = true))t) endemismo, " +
					"(select array_to_json(array_agg(row_to_json(t))) from (select bigc.bigc_id id,bigc_description grupo, " + 
					"(select array_to_json(array_agg(row_to_json(d))) from (select stis_name nombre,stis_url url, " + 
					"case when stis_principal = true then 0 else inso_id end orden " + 
					"from biodiversity.species_taxa_information_sources stis where stis.spta_id = st.spta_id and  " + 
					"stis.stis_status = true and bigc.bigc_id = stis.bigc_id order by orden) d) det_fuentes " + 
					"from biodiversity.biodiversity_general_catalogs bigc where bigc.bigc_id in  " + 
					"(select bigc_id from biodiversity.species_taxa_information_sources stis0 where stis0.spta_id = st.spta_id and stis0.stis_status = true) " + 
					"order by bigc_description) t) fuentes, " + 
					"array_to_json(array(select is2.inso_long_reference " + 
					"FROM biodiversity.information_sources is2 " + 
					"inner join biodiversity.species_taxa_literary_references stlr on " + 
					"stlr.inso_id = is2.inso_id and is2.inso_status = true " + 
					"where stlr.spta_id = st.spta_id and stlr.stlr_status = true " + 
					"and (is2.inso_long_reference is not null or is2.inso_long_reference !=''))) literary_references, "+
					"case when seas_author is not null and seas_author != '' then seas_author || ' ' else '' end " + 
					"|| COALESCE(seas_publication_year,CAST(date_part('year',seas_date_create) AS VARCHAR(4))) || '. ' " +
					"|| '<span class=\"uk-text-italic uk-text-bold\">' || spta_scientific_name || '</span>. ' " +
					"|| (select bcca_value from biodiversity.biodiversity_catalog_parameters where bcca_code = 'fichas.texto.cita' and bcca_status=true) ||  " + 
					"' [ Consulta: ' citas,  " + 
					"seas_reviser revisores, seas_editor editores, seas_collaborator colaboradores, " + 
					"case when spsu_date_update > seas_date_update then spsu_date_update else seas_date_update end date_update, " + 
					"(select bcca_value from biodiversity.biodiversity_catalog_parameters where bcca_code = 'sumario.general.access.rights' and bcca_status=true) as txt_pie_sumario, " +
					"(select bcca_value from biodiversity.biodiversity_catalog_parameters where bcca_code = 'reportes.texto.pie_pagina' and bcca_status=true) as pie_pagina_sumario " + 
					"from biodiversity.species_taxa st  " + 
					"left join biodiversity.species_summaries su on " +
					"su.spta_id = st.spta_id and spsu_status = true " +
					"left join biodiversity.summaries_exotic_alien_species seas on " + 
					"seas.spta_id = st.spta_id and seas_status = true " + 
					"where spta_exotic = true and spta_scientific_gui = '"+ gui_specie +"')t) as text) as sumario");					
			
			cuerpo=(q.getResultList());
			if (cuerpo.isEmpty()) {
				return new String();
			} else {
				String resultado = String.valueOf(cuerpo.get(0));
				String str_imagenes = getDataImage(gui_specie);
				if(!str_imagenes.isEmpty()) {
					resultado = resultado.substring(0, resultado.length()-1).concat("," + str_imagenes + "}");
				}
				return resultado;
						}
		}
		catch(NoResultException e)
		{
			return new String();
		}
	}
	
	/**
	 * Obtener Sumario Especies Forestales
	 *  @return Text - Json	 *  
	 */
	@SuppressWarnings("unchecked")
	public String getSumarioForestal(String gui_specie)
	{
		try
		{
			List<Object[]> cuerpo = new ArrayList<Object[]>();
			
			Query q =  super.getEntityManager().createNativeQuery("select cast((select row_to_json(t) from( " + 
					"select spta_scientific_name scientific_name,spta_scientific_name_authorship author, " + 
					"spta_name_published_year publication_year,spta_scientific_gui gui, COALESCE(rlec_initial,'NE') rl_initial, " + 
					"(select json_object(array( select  hicl_taxon_rank_name " + 
					"				FROM biodiversity.higher_classifications hc " + 
					"				where (st.spta_hierarchy_code LIKE '%;' || hc.hicl_id || ';%' or st.spta_hierarchy_code LIKE hc.hicl_id || ';%') " + 
					"				and  hc.hicl_status = true order by hc.tara_id),  " + 
					"				array( select hc.hicl_scientific_name " + 
					"				FROM biodiversity.higher_classifications hc " + 
					"				where (st.spta_hierarchy_code LIKE '%;' || hc.hicl_id || ';%' or st.spta_hierarchy_code LIKE hc.hicl_id || ';%') " + 
					"				and  hc.hicl_status = true order by hc.tara_id))) taxonomia, " + 
					"array_to_json(array(select '<li><i>' || spta_scientific_name || '</i>&nbsp;&nbsp;' || COALESCE(spta_scientific_name_authorship, '' ) || '</li>' from biodiversity.species_taxa " + 
					"where spta_id in (select UNNEST (cast(string_to_array(replace(biodiversity.get_synonyms_specie(st.spta_id),'nd','0'),';') as int[]))))) sinonimos, " + 
					"(select array_to_json(array_agg(row_to_json(t))) from (select ha.habi_name nombre, inso_short_reference fuente  " + 
					"from biodiversity.species_taxa_habits sth inner join  " + 
					"biodiversity.habits ha on ha.habi_id = sth.habi_id and ha.habi_status = true inner join " + 
					"biodiversity.information_sources inso on inso.inso_id = sth.inso_id and inso_status = true " + 
					"where sth.stha_status = true and sth.spta_id = st.spta_id order by ha.habi_name) t) AS habito, " + 
					"spsu_description description, spfs_botanical_description botanical_description, " + 
					"spsu_similar_species similar_species,spsu_distribution distribution,  " + 
					"array_to_json(array(select eco.spec_name from  biodiversity.species_ecosystems eco " + 
					"where eco.spec_status = true AND eco.spta_id = st.spta_id order by eco.spec_name)) AS ecosistemas, " + 
					"(select bcca_value from biodiversity.biodiversity_catalog_parameters where bcca_code = 'fichas.texto.ecosistema' and bcca_status=true) as txt_ecosistemas," +
					"(select array_to_json(array_agg(row_to_json(t))) from (select 'spph_id_' || spph_id id, spph_author autor, spph_title titulo, spph_description description,  " + 
					"	   'data:' || spph_mime || ';base64,' mime from biodiversity.species_photographs sp " + 
					"	   where sp.spta_id = st.spta_id and sp.spph_status = true and sp.spph_photograph_general = true and sp.spph_type_photograph = 'GENERAL') t) img_general, " + 
					"(select array_to_json(array_agg(row_to_json(t))) from (select 'spph_id_' || spph_id id, spph_author autor, spph_title titulo, spph_description description, " + 
					"	   'data:' || spph_mime || ';base64,' mime, spph_type_photograph tipo from biodiversity.species_photographs sp2 " + 
					"	   where sp2.spta_id = st.spta_id and sp2.spph_status = true and sp2.spph_photograph_general != true and sp2.spph_type_photograph = 'PARTICULAR') t) img_particular, " + 
					"case when spta_endemic then 'Endémica' else '' end ||  " + 
					"case when spta_exotic then (case when spta_endemic then ', ' else '' end) || 'Exótica' else '' end || " + 
					"case when spta_alien then (case when spta_endemic or spta_exotic then ', ' else '' end) || 'Invasora' else '' end || " + 
					"case when spta_native then (case when spta_endemic or spta_exotic or spta_alien then ', ' else '' end) || 'de Ecuador' else '' end || " + 
					"case when spta_migratory then 'Migratoria' else '' end status, " + 
					"COALESCE(rlec_name,'No evaluado') rlec_name,spta_red_list_uicn uicn_name, " + 
					"COALESCE(rlec_name,'No evaluado') || ' (' || COALESCE(rlec_initial,'NE') || case when spta_red_list_ec_criteria is not null and  " + 
					"spta_red_list_ec_criteria !='' then ' ' || spta_red_list_ec_criteria else '' end  || ')' lr_info, " +
					"COALESCE(rlui_initial,'NE') || case when spta_red_list_uicn_criteria is not null and " +
					"spta_red_list_ec_criteria !='' then ' ' || spta_red_list_uicn_criteria else '' end uicn_info, " +
					"case when spta_red_list_uicn is not null then false else true end hide_uicn, " + 
					"spsu_altitudinal_rank altitudinal_rank,	 " + 
					"array_to_json(array(select gl.gelo_name from biodiversity.species_taxa_provinces pr inner join  " + 
					"geographical_locations gl on pr.province_id = gl.gelo_id and gl.gelo_status = true " + 
					"where pr.sptp_status = true AND pr.spta_id = st.spta_id order by pr.province_id)) AS provincia, " + 
					"array_to_json(array(select pa.sppa_name from  biodiversity.species_protected_areas pa " + 
					"where pa.sppa_status = true AND pa.spta_id = st.spta_id order by pa.sppa_name)) AS areanatural, " + 
					"array_to_json(array(select vena_vernacular_name nombre from biodiversity.vernacular_names vn " + 
					"where vn.spta_id = st.spta_id and vena_status = true order by vena_vernacular_name)) AS vernacular_name, " +
					"case when spta_cites_name is not null and spta_cites_name !='' then spta_cites_name else '' end "+
					"|| case when spta_cites_criteria is not null and spta_cites_criteria !='' then ' (' || spta_cites_criteria || ')' else '' end cites_info, " +  
					"case when spta_cites_name is not null then false else true end hide_cites, " +  
					"case when spfs_conditioned_use then 'Sí' else 'No' end conditioned_use, spsu_author autores," + 
					"(select array_to_json(array_agg(row_to_json(t))) from (select 'spdm_id_' || spdm_id id, cast(spdm_author as varchar) autor, spdm_title titulo, spdm_description description, " + 
					"	'data:' || spdm_mime || ';base64,' mime, " + 
					" case when spdm_type_photograph = 'DISTRIBUCIONUNO' then 1  " + 
					"	  when spdm_type_photograph = 'DISTRIBUCIONDOS' then 2 " + 
					" 	  when spdm_type_photograph = 'DISTRIBUCIONTRES' then 3 end as orden " + 
					"	   from biodiversity.species_distribution_maps dm " + 
					"	   where dm.spsu_id = su.spsu_id and dm.spdm_status = true order by orden) t) img_dis_map, " + 
					"(select bcca_value from biodiversity.biodiversity_catalog_parameters where bcca_code = 'fichas.texto.mapa_dis' and bcca_status=true) as txt_map_dis, " +
					"case when spta_comercial_use = true then (select bcca_value from biodiversity.biodiversity_catalog_parameters where bcca_code = 'fichas.texto.use_comercial' and bcca_status=true) else null end use_comercial, " +
					"spsu_ecology ecology, spsu_use use, spsu_conservation_measure conservation_measure,spfs_used_volume used_volume, " + 
					"(select array_to_json(array_agg(row_to_json(t))) from (select 'spph_id_' || spph_id id, spph_author autor, spph_title titulo, spph_description description, " + 
					"	   'data:' || spph_mime || ';base64,' mime, spph_type_photograph tipo from biodiversity.species_photographs sp2 " + 
					"	   where sp2.spta_id = st.spta_id and sp2.spph_status = true and sp2.spph_type_photograph != 'GENERAL' and sp2.spph_type_photograph != 'PARTICULAR') t) img_forestal," +
					"spfs_botanical_collection botanical_collection, spfs_hardness_weight hardness_weight, spfs_color_transversal color_transversal, " + 
					"spfs_odor_transversal odor_transversal, spfs_taste_transversal taste_transversal, " + 
					"spfs_latex_resin_transversal latex_resin_transversal, spfs_outer_cortex_transversal outer_cortex_transversal, " + 
					"spfs_color_tangential color_tangential, spfs_veined_tangential veined_tangential, spfs_grain_tangential grain_tangential, " + 
					"spfs_texture_tangential texture_tangential, spfs_veined_radial veined_radial, spfs_gloss_shine_radial gloss_shine_radial, " + 
					"(select bcca_value from biodiversity.biodiversity_catalog_parameters where bcca_code = 'fichas.texto.anatomicas' and bcca_status=true) as txt_anatomicas," +
					"spfs_growth_rings growth_rings, spfs_pores_transversal pores_transversal, " + 
					"spfs_axial_parenchyma_transversal axial_parenchyma_transversal, spfs_radial_parenchyma_transversal radial_parenchyma_transversal, " + 
					"spfs_density_green density_green, spfs_density_air_dry density_air_dry, spfs_density_anhydrous density_anhydrous, " + 
					"spfs_density_basic density_basic, spfs_contraction_radia contraction_radia,spfs_contraction_tangential contraction_tangential, " + 
					"spfs_contraction_columetrica contraction_columetrica,spfs_contraction_total_radia contraction_total_radia, " + 
					"spfs_contraction_total_tangential contraction_total_tangential,spfs_contraction_total_volumetric contraction_total_volumetric, " + 
					"spfs_contraction_total_relationship_tr contraction_total_relationship_tr, " + 
					"(select array_to_json(array_agg(row_to_json(t))) from (select bigc.bigc_id id,bigc_description grupo, " + 
					"(select array_to_json(array_agg(row_to_json(d))) from (select stis_name nombre,stis_url url, " + 
					"case when stis_principal = true then 0 else inso_id end orden " + 
					"from biodiversity.species_taxa_information_sources stis where stis.spta_id = st.spta_id and  " + 
					"stis.stis_status = true and bigc.bigc_id = stis.bigc_id order by orden) d) det_fuentes " + 
					"from biodiversity.biodiversity_general_catalogs bigc where bigc.bigc_id in  " + 
					"(select bigc_id from biodiversity.species_taxa_information_sources stis0 where stis0.spta_id = st.spta_id and stis0.stis_status = true) " + 
					"order by bigc_description) t) fuentes, " + 
					"array_to_json(array(select is2.inso_long_reference " + 
					"FROM biodiversity.information_sources is2 " + 
					"inner join biodiversity.species_taxa_literary_references stlr on " + 
					"stlr.inso_id = is2.inso_id and is2.inso_status = true " + 
					"where stlr.spta_id = st.spta_id and stlr.stlr_status = true " + 
					"and (is2.inso_long_reference is not null or is2.inso_long_reference !=''))) literary_references, "+
					"case when spsu_author is not null and spsu_author != '' then spsu_author || ' ' else '' end " + 
					"|| CAST(COALESCE(spsu_publication_year,date_part('year',spsu_date_create)) AS VARCHAR(4)) || '. ' "+
					"|| '<span class=\"uk-text-italic uk-text-bold\">' || spta_scientific_name || '</span>. ' " +
					"|| (select bcca_value from biodiversity.biodiversity_catalog_parameters where bcca_code = 'fichas.texto.cita' and bcca_status=true) ||  " + 
					"' [ Consulta: ' citas, spsu_editor editores, spsu_reviser revisores, spsu_collaborator colaboradores, " + 
					"case when spsu_date_update > spfs_date_update then spsu_date_update else spfs_date_update end date_update, " + 
					"(select bcca_value from biodiversity.biodiversity_catalog_parameters where bcca_code = 'sumario.general.access.rights' and bcca_status=true) as txt_pie_sumario, " +
					"(select bcca_value from biodiversity.biodiversity_catalog_parameters where bcca_code = 'reportes.texto.pie_pagina' and bcca_status=true) as pie_pagina_sumario " +
					"from biodiversity.species_taxa st  " + 
					"left join biodiversity.species_summaries su on " + 
					"su.spta_id = st.spta_id and spsu_status = true " + 
					"left join biodiversity.species_forestal_summaries spfs on " + 
					"spfs.spsu_id = su.spsu_id and spfs_status = true			 " + 
					"left join biodiversity.red_lists_ecuador rl on  " + 
					"rl.rlec_id = st.rlec_id and rlec_status=true " + 
					"left join biodiversity.red_lists_uicn rluicn on  " + 
					"rluicn.rlui_id = st.rlui_id and rlui_status=true " + 
					"where spta_scientific_gui = '" + gui_specie + "')t) as text) as sumario");				
			
			cuerpo=(q.getResultList());
			if (cuerpo.isEmpty()) {
				return new String();
			} else {
				String resultado = String.valueOf(cuerpo.get(0));
				String str_imagenes = getDataImage(gui_specie);
				if(!str_imagenes.isEmpty()) {
					resultado = resultado.substring(0, resultado.length()-1).concat("," + str_imagenes + "}");
				}
				return resultado;
			}
		}
		catch(NoResultException e)
		{
			return new String();
		}
	}
	
	/**
	 * Obtener Fotografías del sumario de la especie
	 *  @return string
	 */
	
	@SuppressWarnings("unchecked")
	public String getDataImage(String gui_specie)
	{
		try
		{
			List<Object[]> cuerpo = new ArrayList<Object[]>();
			Query q =  super.getEntityManager().createNativeQuery("with st as(select su.spta_id, spsu_id from biodiversity.species_taxa spta " +
					"inner join biodiversity.species_summaries su on su.spta_id = spta.spta_id and spsu_status = true "+
					"where spta_scientific_gui = '" + gui_specie + "' ) "+
					"select 'spph_id_' || spph_id id, spph_alfresco_id id_alfresco " +
					"from biodiversity.species_photographs sp, st " + 
					"where sp.spta_id = st.spta_id and sp.spph_status = true " + 
					"union all " + 
					"select 'spdm_id_' || spdm_id,spdm_alfresco_id  " + 
					"from biodiversity.species_distribution_maps dm, st " + 
					"where dm.spsu_id = st.spsu_id and dm.spdm_status = true");			
			cuerpo=(q.getResultList());
			if (cuerpo.isEmpty()) {
				return new String();
			} else {
				String resultado ="\"images_especies\":{";
				for (Object [] idImage : cuerpo) {
					byte[] dataImage = getImageAlfresco(String.valueOf(idImage[1]));
					resultado += "\""+ String.valueOf(idImage[0]) + "\":\"" ;					
					if (dataImage != null)
						resultado += Base64.encode(dataImage);
					resultado += "\",";
				}
				resultado = resultado.substring(0, resultado.length()-1) + "}";
				return resultado;
			}
		}
		catch(NoResultException e)
		{
			return new String();
		}
	}
	/**
	 * Obtener byte array de la fotografías del sumario de la especie
	 *  @return byte[] 
	 */
	
	public byte[] getImageAlfresco(String id_Alfresco)
	{
		try
		{
			return speciePhotographFacade.descargarDocumentoPorId(id_Alfresco);
			//return alfrescoServiceBean.downloadDocumentById(id_Alfresco);
		}
		catch(Exception e)
		{
			return null;
		}
	}

	/**
	 * Obtener Menu de reportes estadísticos
	 *  @return Text - Json
	 */
	@SuppressWarnings("unchecked")
	public String getMenuReportesEstadisticos()
	{
		try
		{
			List<Object[]> cuerpo = new ArrayList<Object[]>();
			Query q =  super.getEntityManager().createNativeQuery("select cast((select row_to_json(t) from (" + 
					"select bct.bict_type as title," + 
					"(select array_to_json(array_agg(row_to_json(d)))" + 
					"      from (" + 
					"      select bgc.bigc_description as title, array(" + 
					"      select cast('{\"title\":\"' || sub_bgc.bigc_description || '\",' || sub_bgc.bigc_code || '}' as json) as item" + 
					"		from biodiversity.biodiversity_general_catalogs sub_bgc " + 
					"		where bgc.bigc_id = sub_bgc.bigc_parent_id and sub_bgc.bigc_status = true " + 
					"		order by sub_bgc.bigc_value" + 
					"      ) as items " + 
					"from biodiversity.biodiversity_general_catalogs bgc " + 
					"where bct.bict_id = bgc.bict_id and bgc.bigc_status = true and bgc.bigc_parent_id isnull " + 
					"order by " + 
					"	bgc.bigc_value" + 
					") d) item " + 
					"from biodiversity.biodiversity_catalog_types bct " + 
					"where bct.bict_status = true and bct.bict_code = 'menu_reportes_estadisticos')t) as text) as menu");
			
			cuerpo=(q.getResultList());
			if (cuerpo.isEmpty()) {
				return new String();
			} else {
				return String.valueOf(cuerpo.get(0));
			}
		}
		catch(NoResultException e)
		{
			return new String();
		}
	}
	
	/**
	 * Obtener Menu de Sumarios
	 *  @return Text - Json
	 */
	@SuppressWarnings("unchecked")
	public String getMenuSumarios()
	{
		try
		{
			List<Object[]> cuerpo = new ArrayList<Object[]>();
			Query q =  super.getEntityManager().createNativeQuery("select cast((select array_to_json(array_agg(row_to_json(t))) from (" + 
					"select spls_id id, spls_image image, spls_image_fit image_fit, spls_title title, spls_enabled enabled " + 
					"from biodiversity.species_lists " + 
					"where spls_status = true and spls_visible = true " + 
					"order by spls_order)t) as text) as data");			
			cuerpo=(q.getResultList());
			if (cuerpo.isEmpty()) {
				return new String();
			} else {
				JSONParser jsonParser = new JSONParser();
				JSONArray resultList = (JSONArray) jsonParser.parse(String.valueOf(cuerpo.get(0)));
				for (int i = 0; i < resultList.size(); i++) {
					JSONObject itemMenu = (JSONObject) (resultList.get(i));
					byte[] dataImage = getImageAlfresco(String.valueOf(itemMenu.get("image")));
					if (dataImage != null)
						itemMenu.put("image", Base64.encode(dataImage));					
				}				
				return resultList.toJSONString();
			}
		}
		catch(NoResultException e)
		{
			return new String();
		} 
		catch (ParseException e) {
			e.printStackTrace();
			return new String();
		}
	}
	
	/**
	 * Obtener la url del documento a descargar
	 * @param nameDoc: nombre del documento
	 * @return String
	 */
	public String getURLDocument(String nameDoc) {
		try {
			
			return alfrescoServiceBean.downloadDocument(nameDoc);
			
		} catch (Exception e) {
			return new String();
		}
	}
	
	
	/**
	 * Obtener los documentos de una carpeta determinada
	 * @param nameFolder: nombre de la carpeta
	 * @return String
	 */
	public String getDocumentsByFolder(String nameFolder) {
		try {
			String idFolder = alfrescoServiceBean.parentRoot(nameFolder);
			String resultado = "{" + loopFolders(idFolder) + "}";
			return resultado;		
		} catch (Exception e) {
			return new String();
		}
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private String loopFolders(String idFolder) {
		try {
			String resultado ="";
			List<Object[]> lstFolders = (List)cmisAlfrescoSUIA.getFolderContent(idFolder,false);
			if (!lstFolders.isEmpty()) {
				resultado +="\"folders\":[";
				for (Object [] folder : lstFolders) {
					resultado += "{\"id\":\"" + folder[0] + "\"," +
							     "\"name\":\"" + folder[2] +"\"";
					String resultado2 = loopFolders(folder[0].toString());
					resultado += (resultado2.length()>0?(","+resultado2):"")+ "},";				
				}
				resultado = resultado.substring(0, resultado.length()-1)+"]";
			}
			List<Documents> lst_Docs = cmisAlfrescoSUIA.findDocumentByFolder(idFolder);
			if (!lst_Docs.isEmpty()) {
				resultado += (resultado.length()>0?",":"") + loopDocs(lst_Docs);	
			}
			return resultado;		
		} catch (Exception e) {
			return new String();
		}
	}
	
	@SuppressWarnings("unused")
	private String loopDocs(List<Documents> lst_Docs) {
		try {
			String resultado = "\"documents\":[";			
			for (Documents document : lst_Docs) {
				String title = "";
				resultado += "{\"nameDoc\":\"" + document.getName() + "\","; 
							 for (String detail : document.getDetails()) {
								 String[] parts = detail.split(": ");
								 if(parts.length==2) {
									 resultado += "\"" + parts[0].replaceAll(" ", "_").toLowerCase() + "\":\"" + parts[1] +"\",";
								 }
							} 
				/*byte[] dataDoc = alfrescoServiceBean.downloadDocumentById(document.getObjectId());
				String strDoc="";
				if (dataDoc != null)
					strDoc = Base64.encode(dataDoc);*/
				resultado = resultado.substring(0, resultado.length()-1) +  "," +
				 		     "\"url\":\"" + document.getObjectId() + "\"}," ;
			}
			resultado = resultado.substring(0, resultado.length()-1) + "]";
			return resultado;		
		} catch (Exception e) {
			return new String();
		}
	}
}
