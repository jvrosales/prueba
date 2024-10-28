package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.axis.encoding.Base64;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.speciescatalog.model.Cite;
import ec.gob.ambiente.suia.dto.ProvinciaTo;

/**
 * Servicios para administracion de la solicitud de registro de especialista
 * @author EXCO
 *
 */
@Stateless
public class CiteFacade extends AbstractFacade<Cite, Integer>{

	public CiteFacade() {
		super(Cite.class, Integer.class);		
	}
	
	/**
	 * Buscar cites
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Cite> findCites()
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM Cite o where o.citeStatus = true");
			List<Cite> result=(List<Cite>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<Cite>();
		}
		return new ArrayList<Cite>();
	}
	
	@EJB
	private SpeciePhotographFacade speciePhotographFacade;

	
	/**
	 * Obtener Tematicas: filtros de provincias, cites y listas rojas
	 * @return Text - Json
	 */
	@SuppressWarnings("unchecked")
	public String getTematicas(String lst_redEc)
	{
		try
		{
			List<Object[]> cuerpo = new ArrayList<Object[]>();
			Query q =  super.getEntityManager().createNativeQuery("select cast((select row_to_json(dt) from(" + 
					"select( select array_to_json(array_agg(row_to_json(t)))" + 
					"    	from (" + 
					"			select rlec_id codigo, rlec_name || ' (' || rlec_initial || ')' as nombre,  rlec_initial as filter " + 
					"			from biodiversity.red_lists_ecuador" + 
					"			where rlec_status = true " + lst_redEc + ") t) as lista_roja_ec," + 
					" (select array_to_json(array_agg(row_to_json(t)))" + 
					"    	from (" + 
					"			select cite_id codigo, cite_name nombre" + 
					"			from biodiversity.cites" + 
					"			where cite_status = true) t) as cites," + 
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
	 *Obtener Listado de Especies
	 * @return Text - Json
	 */
	@SuppressWarnings("unchecked")
	public String getListaEspecies()
	{
		try
		{
			List<Object[]> cuerpo = new ArrayList<Object[]>();
			Query q =  super.getEntityManager().createNativeQuery("select cast(( select array_to_json(array_agg(row_to_json(t))) from ("
					+ "SELECT case when exists(select stag_id FROM biodiversity.species_taxa_artificial_groups stag where stag.argr_id = 36 and stag.spta_id = s.spta_id and stag.stag_status = true) then "  
					+ "(select spfs_publish from biodiversity.species_forestal_summaries spfs where spfs.spta_id = s.spta_id and spfs_status=true) else " 
					+ "case when spta_exotic = true then (select seas_publish from biodiversity.summaries_exotic_alien_species seas where seas.spta_id = s.spta_id and seas_status=true) else "  
					+ "spsu_publish end end publish,s.spta_id id, spta_higher_classification higher_classification,spta_scientific_name nombre_cientifico,spta_scientific_gui gui,spta_endemic endemica, rlec_initial lr ,"
					+ "spta_scientific_name_authorship autor, spta_name_published_year año, spta_migratory migratoria, spta_hierarchy_code taxonomia, cite_name cites, s.cite_id cite_id,"
					+ "array_to_string(array( select pro.province_id from biodiversity.species_taxa_provinces pro where pro.sptp_status=true and "
					+ "pro.spta_id=s.spta_id order by 1 ), ', ') provincias, json_object(array(select tr.tara_name FROM "
					+ "biodiversity.higher_classifications hc inner join biodiversity.taxa_ranks tr on tr.tara_id = hc.tara_id and "
					+ "tr.tara_status = true where hc.hicl_status = true and "
					+ "hc.hicl_id = any (cast(string_to_array(regexp_replace(regexp_replace(s.spta_hierarchy_code, '(.*?;){2}', ''),'([|;][^|;]*$)', ''),';') as int[])) "
					+ "order by hc.tara_id),array( select hc.hicl_scientific_name FROM biodiversity.higher_classifications hc inner join "
					+ "biodiversity.taxa_ranks tr on tr.tara_id = hc.tara_id and tr.tara_status = true where hc.hicl_status = true and "
					+ "hc.hicl_id = any (cast(string_to_array(regexp_replace(regexp_replace(s.spta_hierarchy_code, '(.*?;){2}', ''),'([|;][^|;]*$)', ''),';') as int[])) "
					+ "order by hc.tara_id)) rango_taxon, spta_taxon_rank_name taxonRank,"
					+ "spta_nomenclatural_code nomenclaturalCode, substring( case when spta_endemic then ' | endemic' else '' end || "
					+ "case when spta_exotic then ' | exotic' else '' end || case when spta_domestic then ' | domestic' else '' end || "
					+ "case when spta_native then ' | native' else '' end || case when spta_migratory then ' | migratory' else '' end || "
					+ "case when spta_alien then ' | alien' else '' end from 4) establishmentMeans, rlui_initial lr_uicn,"  
					+ "exists(select stag_id FROM biodiversity.species_taxa_artificial_groups stag where stag.argr_id = 36 and stag.spta_id = s.spta_id and stag.stag_status = true) is_forestal, spta_exotic is_exotic "
					+ "FROM biodiversity.species_taxa s left join biodiversity.species_summaries on species_summaries.spta_id = s.spta_id and "
					+ "spsu_status=true left join biodiversity.red_lists_ecuador on red_lists_ecuador.rlec_id = s.rlec_id and rlec_status=true "
					+ "left join biodiversity.cites cite on cite.cite_id = s.cite_id and cite_status=true "
					+ "left join biodiversity.red_lists_uicn on red_lists_uicn.rlui_id = s.rlui_id and rlec_status=true where spta_ecuador = true "					
					+ "and spta_status=true) t ) as text) as listado"); /* order by 3 */
			
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
	public String getListaEventos()
	{
		try
		{
			List<Object[]> cuerpo = new ArrayList<Object[]>();
			Query q =  super.getEntityManager().createNativeQuery("select cast((select array_to_json(array_agg(row_to_json(t))) from( " + 
					"select geda_code codigo_evento, bigc_description evento, geda_observations detalle,  " + 
					"geda_x lng, geda_y lat,geda_event_date fecha, glpro.gelo_name provincia,glcan.gelo_name canton, " + 
					"glpar.gelo_name parroquia " + 
					"from biodiversity.general_data gc inner join " + 
					"biodiversity.biodiversity_general_catalogs bgc on  " + 
					"gc.geda_event_type= bgc.bigc_id and bgc.bigc_status=true " + 
					"inner join public.geographical_locations glpro on " + 
					"glpro.gelo_id = gc.geda_province_id and glpro.gelo_status=true " + 
					"inner join public.geographical_locations glcan on " + 
					"glcan.gelo_id = gc.geda_canton_id and glcan.gelo_status=true " + 
					"inner join public.geographical_locations glpar on " + 
					"glpar.gelo_id = gc.geda_parroquia_id and glpar.gelo_status=true " + 
					"where geda_status=true " + 
					"    order by 2)t) as text) as listaeventos ");
			
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
	 * Obtener Datos estadísticos
	 *  @return Text - Json	 *  
	 */
	@SuppressWarnings("unchecked")
	public String getEstadisticas()
	{
		try
		{
			List<Object[]> cuerpo = new ArrayList<Object[]>();
			Query q =  super.getEntityManager().createNativeQuery(/* - Número de especies por área protegida (por reino)  */ 
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
					"(select array_to_json(array_agg(row_to_json(t))) from (select 'spph_id_' || spph_id id, spph_author autor, spph_title titulo,  " + 
					"	   'data:' || spph_mime || ';base64,' mime from biodiversity.species_photographs sp " + 
					"	   where sp.spta_id = st.spta_id and sp.spph_status = true and sp.spph_photograph_general = true and sp.spph_type_photograph='GENERAL') t) img_general, " + 
					"(select array_to_json(array_agg(row_to_json(t))) from (select 'spph_id_' || spph_id id, spph_author autor, spph_title titulo, " + 
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
					"case when spta_cites_name is not null then false else true end hide_cites, " + 
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
					"(select bigc_description from biodiversity.biodiversity_general_catalogs where bigc_code = 'mapa_dis') as txt_map_dis, " + 
					"case when spsu_author is not null and spsu_author != '' then spsu_author || ' ' else '' end " + 
					"|| CAST(COALESCE(spsu_publication_year,date_part('year',spsu_date_create)) AS VARCHAR(4)) || '. ' " + 
					"|| '<span class=\"uk-text-italic uk-text-bold\">' || spta_scientific_name || '</span>. ' " +
					"|| (select bigc_description from biodiversity.biodiversity_general_catalogs where bigc_code = 'cita') ||  " + 
					"' [ Consulta: ' || to_char(now(), 'dd \"de\" TMmonth \"de\" yyyy') || ' ]' citas, spsu_editor editores, spsu_reviser revisores,spsu_collaborator colaboradores, " + 
					"to_char(spsu_date_update, 'dd \"de\" TMmonth \"de\" yyyy') date_update, " + 
					"(select bigc_description from biodiversity.biodiversity_general_catalogs where bigc_code = 'pie_sumario') as txt_pie_sumario " + 
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
					"(select array_to_json(array_agg(row_to_json(t))) from (select 'spph_id_' || spph_id id, spph_author autor, spph_title titulo,  " + 
					"	   'data:' || spph_mime || ';base64,' mime from biodiversity.species_photographs sp " + 
					"	   where sp.spta_id = st.spta_id and sp.spph_status = true and sp.spph_photograph_general = true and sp.spph_type_photograph='GENERAL') t) img_general, " + 
					"(select array_to_json(array_agg(row_to_json(t))) from (select 'spph_id_' || spph_id id, spph_author autor, spph_title titulo, " + 
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
					"to_char(ieas_date_introduction, 'dd-TMMon-yyyy') date_introduction, bigcf.bigc_description type_introduction, cain_name cause_introduction, " + 
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
					"(select array_to_json(array_agg(row_to_json(t))) from (select bigc.bigc_id id,bigc_description grupo, " + 
					"(select array_to_json(array_agg(row_to_json(d))) from (select stis_name nombre,stis_url url, " + 
					"case when stis_principal = true then 0 else inso_id end orden " + 
					"from biodiversity.species_taxa_information_sources stis where stis.spta_id = st.spta_id and  " + 
					"stis.stis_status = true and bigc.bigc_id = stis.bigc_id order by orden) d) det_fuentes " + 
					"from biodiversity.biodiversity_general_catalogs bigc where bigc.bigc_id in  " + 
					"(select bigc_id from biodiversity.species_taxa_information_sources stis0 where stis0.spta_id = st.spta_id and stis0.stis_status = true) " + 
					"order by bigc_description) t) fuentes, " + 
					"case when seas_author is not null and seas_author != '' then seas_author || ' ' else '' end " + 
					"|| COALESCE(seas_publication_year,CAST(date_part('year',seas_date_create) AS VARCHAR(4))) || '. ' " +
					"|| '<span class=\"uk-text-italic uk-text-bold\">' || spta_scientific_name || '</span>. ' " +
					"|| (select bigc_description from biodiversity.biodiversity_general_catalogs where bigc_code = 'cita') ||  " + 
					"' [ Consulta: ' || to_char(now(),'dd \"de\" TMmonth \"de\" yyyy') || ' ]' citas,  " + 
					"seas_reviser revisores, seas_editor editores, seas_collaborator colaboradores, " + 
					"to_char(case when spsu_date_update > seas_date_update then spsu_date_update else seas_date_update end, 'dd \"de\" TMmonth \"de\" yyyy') date_update, " + 
					"(select bigc_description from biodiversity.biodiversity_general_catalogs where bigc_code = 'pie_sumario') as txt_pie_sumario " + 
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
					"spfs_similar_species similar_species,spsu_distribution distribution,  " + 
					"array_to_json(array(select eco.spec_name from  biodiversity.species_ecosystems eco " + 
					"where eco.spec_status = true AND eco.spta_id = st.spta_id order by eco.spec_name)) AS ecosistemas, " + 
					"(select bigc_description from biodiversity.biodiversity_general_catalogs where bigc_code = 'ecosistema') as txt_ecosistemas," +
					"(select array_to_json(array_agg(row_to_json(t))) from (select 'spph_id_' || spph_id id, spph_author autor, spph_title titulo,  " + 
					"	   'data:' || spph_mime || ';base64,' mime from biodiversity.species_photographs sp " + 
					"	   where sp.spta_id = st.spta_id and sp.spph_status = true and sp.spph_photograph_general = true and sp.spph_type_photograph = 'GENERAL') t) img_general, " + 
					"(select array_to_json(array_agg(row_to_json(t))) from (select 'spph_id_' || spph_id id, spph_author autor, spph_title titulo, " + 
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
					"case when spfs_conditioned_use then 'Sí' else 'No' end conditioned_use, spfs_author autores," + 
					"(select array_to_json(array_agg(row_to_json(t))) from (select 'spdm_id_' || spdm_id id, spdm_author autor, spdm_title titulo, spdm_description description, " + 
					"	'data:' || spdm_mime || ';base64,' mime, " + 
					" case when spdm_type_photograph = 'DISTRIBUCIONUNO' then 1  " + 
					"	  when spdm_type_photograph = 'DISTRIBUCIONDOS' then 2 " + 
					" 	  when spdm_type_photograph = 'DISTRIBUCIONTRES' then 3 end as orden " + 
					"	   from biodiversity.species_distribution_maps dm " + 
					"	   where dm.spsu_id = su.spsu_id and dm.spdm_status = true order by orden) t) img_dis_map, " + 
					"(select bigc_description from biodiversity.biodiversity_general_catalogs where bigc_code = 'mapa_dis') as txt_map_dis, " + 
					"spfs_ecology ecology, spfs_use use, spsu_conservation_measure conservation_measure,spfs_used_volume used_volume, " + 
					"(select array_to_json(array_agg(row_to_json(t))) from (select 'spph_id_' || spph_id id, spph_author autor, spph_title titulo, " + 
					"	   'data:' || spph_mime || ';base64,' mime, spph_type_photograph tipo from biodiversity.species_photographs sp2 " + 
					"	   where sp2.spta_id = st.spta_id and sp2.spph_status = true and sp2.spph_type_photograph != 'GENERAL' and sp2.spph_type_photograph != 'PARTICULAR') t) img_forestal," +
					"spfs_botanical_collection botanical_collection, spfs_hardness_weight hardness_weight, spfs_color_transversal color_transversal, " + 
					"spfs_odor_transversal odor_transversal, spfs_taste_transversal taste_transversal, " + 
					"spfs_latex_resin_transversal latex_resin_transversal, spfs_outer_cortex_transversal outer_cortex_transversal, " + 
					"spfs_color_tangential color_tangential, spfs_veined_tangential veined_tangential, spfs_grain_tangential grain_tangential, " + 
					"spfs_texture_tangential texture_tangential, spfs_veined_radial veined_radial, spfs_gloss_shine_radial gloss_shine_radial, " + 
					"(select bigc_description from biodiversity.biodiversity_general_catalogs where bigc_code = 'anatomicas') as txt_anatomicas," +
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
					"case when spfs_author is not null and spfs_author != '' then spfs_author || ' ' else '' end " + 
					"|| CAST(COALESCE(spfs_publication_year,date_part('year',spfs_date_create)) AS VARCHAR(4)) || '. ' "+
					"|| '<span class=\"uk-text-italic uk-text-bold\">' || spta_scientific_name || '</span>. ' " +
					"|| (select bigc_description from biodiversity.biodiversity_general_catalogs where bigc_code = 'cita') ||  " + 
					"' [ Consulta: ' || to_char(now(), 'dd \"de\" TMmonth \"de\" yyyy') || ' ]' citas, spfs_editor editores, spfs_reviser revisores, spfs_collaborator colaboradores, " + 
					"to_char(case when spsu_date_update > spfs_date_update then spsu_date_update else spfs_date_update end, 'dd \"de\" TMmonth \"de\" yyyy') date_update, " + 
					"(select bigc_description from biodiversity.biodiversity_general_catalogs where bigc_code = 'pie_sumario') as txt_pie_sumario " + 
					"from biodiversity.species_taxa st  " + 
					"left join biodiversity.species_summaries su on " + 
					"su.spta_id = st.spta_id and spsu_status = true " + 
					"left join biodiversity.species_forestal_summaries spfs on " + 
					"spfs.spta_id = st.spta_id and spfs_status = true			 " + 
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
	
	public byte[] getImageAlfresco(String id_specieAlfresco)
	{
		try
		{
			return speciePhotographFacade.descargarDocumentoPorId(id_specieAlfresco);
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ProvinciaTo> buscarProvincia() {
		List<ProvinciaTo> provinciasL = new ArrayList<>();
		List<Object[]> provincias = new ArrayList<Object[]>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select gelo_id codigo,gelo_name nombre " );
		sql.append(" from public.geographical_locations " );
		sql.append(" where gelo_parent_id = 1 and gelo_status = true " );
		sql.append(" order by gelo_codification_inec " );
		Query q =  super.getEntityManager().createNativeQuery(sql.toString());
		provincias = q.getResultList();
		for (Object[] objects : provincias) {
			ProvinciaTo provinciaTo = new ProvinciaTo();
			provinciaTo.setId((int) objects[0]);
			provinciaTo.setNombre(String.valueOf(objects[1]));
			provinciasL.add(provinciaTo);
		}
		return provinciasL;
	}

}

