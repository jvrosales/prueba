package ec.gob.ambiente.sib_suia_ora.summaries.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

import org.apache.axis.encoding.Base64;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecieTaxa;
import ec.gob.ambiente.enlisy.speciescatalog.services.ArtificialGroupFacade;
import ec.gob.ambiente.enlisy.speciescatalog.services.HigherClassificationFacade;
import ec.gob.ambiente.enlisy.speciescatalog.services.SpeciePhotographFacade;
import ec.gob.ambiente.enlisy.speciescatalog.services.TaxaRankFacade;
import ec.gob.ambiente.sib_suia_ora.summaries.model.*;

/**
 * Servicios para la consulta de la informacion taxonomica de un nombre o
 * nombres cientificos enviados como parametro en el servicio web de resolucion
 * taxonomica
 * 
 * @author CristinaFactos
 *
 */
@Stateless
public class SummaryWebServiceFacade extends AbstractFacade<SpecieTaxa, Integer> {

	public SummaryWebServiceFacade() {
		super(SpecieTaxa.class, Integer.class);
	}

	@EJB
	HigherClassificationFacade higherClassificationFacade;

	@EJB
	ArtificialGroupFacade artificialGroupFacade;

	@EJB
	TaxaRankFacade taxaRankFacade;
	
	@EJB
	private SpeciePhotographFacade speciePhotographFacade;

	@SuppressWarnings("unchecked")
	public Result getSummaryByFilter(String gui, String sName, String rank, String type, String lrEC, String cites,
			String status, String codigoPadre, Integer idRank) {
		Result resultado = new Result();
		List<SumGSpResult> objectResultG = new ArrayList<>();
		List<SumForeSpResult> objectResultF = new ArrayList<>();
		List<SumExSpResult> objectResultE = new ArrayList<>();
		Map<String, Object[]> mapaResultados;
		mapaResultados = new LinkedHashMap<>();
		StringBuilder sql = new StringBuilder();
		sql.append("");
		StringBuilder sqlForestal = new StringBuilder();
		sqlForestal.append("");
		StringBuilder sqlExotica = new StringBuilder();
		sqlExotica.append("");

		if (type != null && !type.equals("")) {
			if (type.equals("general") || type.equals("g")) {
				sql.append(
						"select spta_correct_tax,spta_correct_tax_name,tast_name,spta_taxon_rank_name,spta_scientific_name scientific_name,spta_scientific_gui gui,           \r\n" + 
						"						 								 					 spta_scientific_name_authorship author, spta_name_published_year publication_year,           \r\n" + 
						"						 								 					 cast ((select array_to_json(array_agg(row_to_json(t))) from (select vena_vernacular_name nombre,            \r\n" + 
						"						 								 					 CASE when tala_name is not null and tala_name != '' then tala_name else 'N/D' end lenguaje,           \r\n" + 
						"						 								 					 case when etgr_name is not null and etgr_name != '' then etgr_name else 'N/D' end grupo_etnico,           \r\n" + 
						"						 								 					 case when inso_short_reference is not null and inso_short_reference != '' then inso_short_reference else 'N/D' end citacorta           \r\n" + 
						"						 								 					 from biodiversity.vernacular_names vn           \r\n" + 
						"						 								 					 left join biodiversity.ethnic_groups eg on eg.etgr_id =  vn.etgr_id and eg.etgr_status = true           \r\n" + 
						"						 								 					 left join biodiversity.taxa_languages tl on tl.tala_id = vn.tala_id and tl.tala_status = true           \r\n" + 
						"						 								 					 left join biodiversity.information_sources inso on inso.inso_id = vn.inso_id and inso_status = true           \r\n" + 
						"						 								 					 where vn.spta_id = st.spta_id and vena_status = true order by tl.tala_id) t) as text)vernacular_name,           \r\n" + 
						"						 								 					         \r\n" + 
						"						 								 					 cast ((select array_to_json(array_agg(row_to_json(t))) from (select 'spph_id_' || spph_id id, spph_author autor, spph_title titulo,            \r\n" + 
						"						 								 					 	   'data:' || spph_mime || ';base64,' mime, spph_description, spph_alfresco_id from biodiversity.species_photographs sp           \r\n" + 
						"						 								 					 	   where sp.spta_id = st.spta_id and sp.spph_status = true and sp.spph_photograph_general = true and sp.spph_type_photograph='GENERAL') t) as text) img_general,           \r\n" + 
						"						 								 					 cast ((select array_to_json(array_agg(row_to_json(t))) from (select 'spph_id_' || spph_id id, spph_author autor, spph_title titulo,           \r\n" + 
						"						 								 					 	   'data:' || spph_mime || ';base64,' mime , spph_description,spph_alfresco_id from biodiversity.species_photographs sp2           \r\n" + 
						"						 								 					 	   where sp2.spta_id = st.spta_id and sp2.spph_status = true and sp2.spph_photograph_general != true and sp2.spph_type_photograph='PARTICULAR') t) as text) img_particular,           \r\n" + 
						"						 								 					 cast ((select array_to_json(array_agg(row_to_json(t))) from (select 'spdm_id_' || spdm_id id, rtrim(spdm_author) autor, spdm_title titulo, spdm_description description,           \r\n" + 
						"						 								 					 	'data:' || spdm_mime || ';base64,' mime,           \r\n" + 
						"						 								 					  case when spdm_type_photograph = 'DISTRIBUCIONUNO' then 1            \r\n" + 
						"						 								 					 	  when spdm_type_photograph = 'DISTRIBUCIONDOS' then 2           \r\n" + 
						"						 								 					  	  when spdm_type_photograph = 'DISTRIBUCIONTRES' then 3 end as orden           \r\n" + 
						"						 								 					 	   ,spdm_alfresco_id from biodiversity.species_distribution_maps dm           \r\n" + 
						"						 								 					 	   where dm.spsu_id = su.spsu_id and dm.spdm_status = true order by orden) t) as text) img_dis_map,           \r\n" + 
						"						 								 					 cast ((select json_object(array( select  hicl_taxon_rank_name           \r\n" + 
						"						 								 					 				FROM biodiversity.higher_classifications hc           \r\n" + 
						"						 								 					 				where (st.spta_hierarchy_code LIKE '%;' || hc.hicl_id || ';%' or st.spta_hierarchy_code LIKE hc.hicl_id || ';%')           \r\n" + 
						"						 								 					 				and  hc.hicl_status = true order by hc.tara_id),            \r\n" + 
						"						 								 					 				array( select hc.hicl_scientific_name           \r\n" + 
						"						 								 					 				FROM biodiversity.higher_classifications hc           \r\n" + 
						"						 								 					 				where (st.spta_hierarchy_code LIKE '%;' || hc.hicl_id || ';%' or st.spta_hierarchy_code LIKE hc.hicl_id || ';%')           \r\n" + 
						"						 								 					 				and  hc.hicl_status = true order by hc.tara_id))) as text) taxonomia,           \r\n" + 
						"						 								 					 spta_specific_infraspecifc_epit epiteto,      \r\n" + 
						"						 								 					 spta_ecuador ,      					 spta_endemic,         \r\n" + 
						"						 								 					 spta_exotic,      					 spta_alien ,         \r\n" + 
						"						 								 					 spta_native,      					 spta_migratory,      \r\n" + 
						"						 								 					 spta_domestic,         \r\n" + 
						"						 								 					 COALESCE(rlec_name,'No evaluado') rlec_name,spta_red_list_uicn uicn_name,           \r\n" + 
						"						 								 					         \r\n" + 
						"						 								 					 cast ((select array_to_json(array_agg(row_to_json(t))) from (select bigc.bigc_id id,bigc_description grupo,           \r\n" + 
						"						 								 					 (select array_to_json(array_agg(row_to_json(d))) from (select stis_name nombre,stis_url url, bigc_description,          \r\n" + 
						"						 								 					 stis_principal           \r\n" + 
						"						 								 					 from biodiversity.species_taxa_information_sources stis where stis.spta_id = st.spta_id and            \r\n" + 
						"						 								 					 stis.stis_status = true and bigc.bigc_id = stis.bigc_id ) d) det_fuentes           \r\n" + 
						"						 								 					 from biodiversity.biodiversity_general_catalogs bigc where bigc.bigc_id in            \r\n" + 
						"						 								 					 (select bigc_id from biodiversity.species_taxa_information_sources stis0 where stis0.spta_id = st.spta_id and stis0.stis_status = true)           \r\n" + 
						"						 								 					 order by bigc_description) t) as text) fuentes,           \r\n" + 
						"						 								 					 spsu_description description,           \r\n" + 
						"						 								 					 cast ((select array_to_json(array_agg(row_to_json(t))) from (select spta_scientific_name,spta_scientific_gui  from biodiversity.species_taxa            \r\n" + 
						"						 								 								 					 where spta_id in (select UNNEST (cast(string_to_array(replace(biodiversity.get_synonyms_specie(st.spta_id),'nd','0'),';') as int[])))) t) as text) sinonimos,     \r\n" + 
						"						 								 					 spsu_distribution distribution, spsu_altitudinal_rank altitudinal_rank,           \r\n" + 
						"						 								 					 cast((select array_to_json(array_agg(row_to_json(t))) from (select ha.habi_name nombre, inso_short_reference fuente            \r\n" + 
						"						 								 					 from biodiversity.species_taxa_habits sth inner join            \r\n" + 
						"						 								 					 biodiversity.habits ha on ha.habi_id = sth.habi_id and ha.habi_status = true inner join           \r\n" + 
						"						 								 					 biodiversity.information_sources inso on inso.inso_id = sth.inso_id and inso_status = true           \r\n" + 
						"						 								 					 where sth.stha_status = true and sth.spta_id = st.spta_id order by ha.habi_name) t) as text) AS habito,           \r\n" + 
						"						 								 					 spsu_conservation_measure conservation_measure,           \r\n" + 
						"						 								 					 spta_red_list_ec_criteria,           \r\n" + 
						"						 								 					 spta_cites_name,           \r\n" + 
						"						 								 					 cast (array_to_json(array(select gl.gelo_name from biodiversity.species_taxa_provinces pr inner join            \r\n" + 
						"						 								 					 geographical_locations gl on pr.province_id = gl.gelo_id and gl.gelo_status = true           \r\n" + 
						"						 								 					 where pr.sptp_status = true AND pr.spta_id = st.spta_id order by pr.province_id))as text) AS provincia,      \r\n" + 
						"						 								 					 cast (array_to_json(array(select gl.argr_name from biodiversity.species_taxa_artificial_groups pr inner join            \r\n" + 
						"						 								 					 biodiversity.artificial_groups gl on pr.argr_id = gl.argr_id and gl.argr_status = true           \r\n" + 
						"						 								 					 where pr.stag_status = true AND pr.spta_id = st.spta_id order by gl.argr_name)) as text) AS gruposart,           \r\n" + 
						"						 								 					 cast(array_to_json(array(select pa.sppa_name from  biodiversity.species_protected_areas pa           \r\n" + 
						"						 								 					 where pa.sppa_status = true AND pa.spta_id = st.spta_id order by pa.sppa_name)) as text) AS areanatural,           \r\n" + 
						"						 								 					 cast(array_to_json(array(select eco.spec_name from  biodiversity.species_ecosystems eco           \r\n" + 
						"						 								 					 where eco.spec_status = true AND eco.spta_id = st.spta_id order by eco.spec_name))as text) AS ecosistemas,           \r\n" + 
						"						 								 					 (select bigc_description from biodiversity.biodiversity_general_catalogs where bigc_code = 'mapa_dis') as txt_map_dis,           \r\n" + 
						"						 								 					 spsu_author, spsu_editor editores, spsu_reviser revisores,spsu_collaborator colaboradores,           \r\n" + 
						"						 								 					 spsu_publication_year,           \r\n" + 
						"						 								 					 (select bcca_value from biodiversity.biodiversity_catalog_parameters where bcca_code = 'sumario.general.access.rights') as access_rights           \r\n" + 
						"						 								 					 from biodiversity.species_taxa st            \r\n" + 
						"						 								 					 inner join biodiversity.species_summaries su on           \r\n" + 
						"						 								 					 su.spta_id = st.spta_id            \r\n" + 
						"						 								 					 left join biodiversity.red_lists_ecuador rl on            \r\n" + 
						"						 								 					 rl.rlec_id = st.rlec_id and rlec_status=true           \r\n" + 
						"						 								 					 left join biodiversity.red_lists_uicn rluicn on            \r\n" + 
						"						 								 					 rluicn.rlui_id = st.rlui_id      \r\n" + 
						"						 								 					 left join biodiversity.taxonomic_status taxstat on            \r\n" + 
						"						 								 					 taxstat.tast_id = st.tast_id  where spsu_status = true ");

				if ((gui != null && !gui.equals("")) && (sName != null && !sName.equals(""))
						&& (rank != null && !rank.equals(""))) {

					if (idRank.equals(1)) {
						sql.append("and spta_hierarchy_code like '");
						sql.append(codigoPadre);
						sql.append(";%;%;%;%;%;%' ");

					} else {
						if (idRank.equals(2)) {
							sql.append("and spta_hierarchy_code like '");
							sql.append(codigoPadre);
							sql.append(";%;%;%;%;%' ");

						} else {
							if (idRank.equals(3)) {
								sql.append("and spta_hierarchy_code like '");
								sql.append(codigoPadre);
								sql.append(";%;%;%;%' ");

							} else {
								if (idRank.equals(4)) {
									sql.append("and spta_hierarchy_code like '");
									sql.append(codigoPadre);
									sql.append(";%;%;%' ");

								} else {
									if (idRank.equals(5)) {
										sql.append("and spta_hierarchy_code like '");
										sql.append(codigoPadre);
										sql.append(";%;%' ");

									} else {
										if (idRank.equals(6)) {
											sql.append("and spta_hierarchy_code like '");
											sql.append(codigoPadre);
											sql.append(";%' ");

										}
									}
								}
							}
						}
					}
				} else {
					if ((gui != null && !gui.equals("")) && (sName != null && !sName.equals(""))
							&& (rank == null || rank.equals(""))) {
						sql.append("and spta_scientific_gui = '");
						sql.append(gui);
						sql.append("' ");
					} else {
						if ((gui != null && !gui.equals("")) && (sName == null || sName.equals(""))
								&& (rank != null && !rank.equals(""))) {

							if (idRank.equals(1)) {
								sql.append("and spta_hierarchy_code like '");
								sql.append(codigoPadre);
								sql.append(";%;%;%;%;%;%' ");

							} else {
								if (idRank.equals(2)) {
									sql.append("and spta_hierarchy_code like '");
									sql.append(codigoPadre);
									sql.append(";%;%;%;%;%' ");

								} else {
									if (idRank.equals(3)) {
										sql.append("and spta_hierarchy_code like '");
										sql.append(codigoPadre);
										sql.append(";%;%;%;%' ");

									} else {
										if (idRank.equals(4)) {
											sql.append("and spta_hierarchy_code like '");
											sql.append(codigoPadre);
											sql.append(";%;%;%' ");

										} else {
											if (idRank.equals(5)) {
												sql.append("and spta_hierarchy_code like '");
												sql.append(codigoPadre);
												sql.append(";%;%' ");

											} else {
												if (idRank.equals(6)) {
													sql.append("and spta_hierarchy_code like '");
													sql.append(codigoPadre);
													sql.append(";%' ");

												}
											}
										}
									}
								}
							}

						} else {
							if ((gui != null && !gui.equals("")) && (sName == null || sName.equals(""))
									&& (rank == null || rank.equals(""))) {
								sql.append("and spta_scientific_gui = '");
								sql.append(gui);
								sql.append("' ");
							} else {
								if ((gui == null || gui.equals("")) && (sName == null || sName.equals(""))
										&& (rank != null && !rank.equals(""))) {
									if (idRank.equals(1)) {
										sql.append("and spta_hierarchy_code like '");
										sql.append(codigoPadre);
										sql.append(";%;%;%;%;%;%' ");

									} else {
										if (idRank.equals(2)) {
											sql.append("and spta_hierarchy_code like '");
											sql.append(codigoPadre);
											sql.append(";%;%;%;%;%' ");

										} else {
											if (idRank.equals(3)) {
												sql.append("and spta_hierarchy_code like '");
												sql.append(codigoPadre);
												sql.append(";%;%;%;%' ");

											} else {
												if (idRank.equals(4)) {
													sql.append("and spta_hierarchy_code like '");
													sql.append(codigoPadre);
													sql.append(";%;%;%' ");

												} else {
													if (idRank.equals(5)) {
														sql.append("and spta_hierarchy_code like '");
														sql.append(codigoPadre);
														sql.append(";%;%' ");

													} else {
														if (idRank.equals(6)) {
															sql.append("and spta_hierarchy_code like '");
															sql.append(codigoPadre);
															sql.append(";%' ");

														}
													}
												}
											}
										}
									}
								} else {
									if ((gui == null || gui.equals("")) && (sName != null && !sName.equals(""))
											&& (rank != null && !rank.equals(""))) {
										if (idRank.equals(1)) {
											sql.append("and spta_hierarchy_code like '");
											sql.append(codigoPadre);
											sql.append(";%;%;%;%;%;%' ");

										} else {
											if (idRank.equals(2)) {
												sql.append("and spta_hierarchy_code like '");
												sql.append(codigoPadre);
												sql.append(";%;%;%;%;%' ");

											} else {
												if (idRank.equals(3)) {
													sql.append("and spta_hierarchy_code like '");
													sql.append(codigoPadre);
													sql.append(";%;%;%;%' ");

												} else {
													if (idRank.equals(4)) {
														sql.append("and spta_hierarchy_code like '");
														sql.append(codigoPadre);
														sql.append(";%;%;%' ");

													} else {
														if (idRank.equals(5)) {
															sql.append("and spta_hierarchy_code like '");
															sql.append(codigoPadre);
															sql.append(";%;%' ");

														} else {
															if (idRank.equals(6)) {
																sql.append("and spta_hierarchy_code like '");
																sql.append(codigoPadre);
																sql.append(";%' ");

															}
														}
													}
												}
											}
										}
									} else {
										if ((gui == null || gui.equals("")) && (sName != null && !sName.equals(""))
												&& (rank == null || rank.equals(""))) {
											sql.append("and spta_scientific_name = '");
											sql.append(sName);
											sql.append("' ");
										} 
									}
								}
							}
						}
					}
				}

				if (lrEC != null && !lrEC.equals("")) {
					sql.append("and UPPER(rlec_initial) in ('");
					String[] iniciales = lrEC.split(",");
					if (iniciales.length > 1) {
						for (int i = 0; i < iniciales.length; i++) {
							if (i != (iniciales.length - 1)) {
								sql.append(iniciales[i]);
								sql.append("','");
							} else {
								sql.append(iniciales[i]);
								sql.append(") ");
							}
						}
					} else {
						sql.append("and UPPER(rlec_initial) = '");
						sql.append(lrEC.toUpperCase());
						sql.append("' ");
					}
				}

				if (cites != null && !cites.equals("")) {

					String[] iniciales = cites.split(",");
					if (iniciales.length > 1) {
						sql.append("and cite_id in ('");
						Integer citeId = 0;
						for (int i = 0; i < iniciales.length; i++) {
							if (iniciales[i].equalsIgnoreCase("I") || iniciales[i].equals("1")) {
								citeId = 1;
							}
							if (iniciales[i].equalsIgnoreCase("II") || iniciales[i].equals("2")) {
								citeId = 2;
							}

							if (iniciales[i].equalsIgnoreCase("III") || iniciales[i].equals("3")) {
								citeId = 3;
							}
							if (i != (iniciales.length - 1)) {
								sql.append(citeId);
								sql.append("','");
							} else {
								sql.append(citeId);
								sql.append(") ");
							}
						}
					} else {
						Integer citeId = 0;
						if (cites.equalsIgnoreCase("I") || cites.equals("1")) {
							citeId = 1;
						}
						if (cites.equalsIgnoreCase("II") || cites.equals("2")) {
							citeId = 2;
						}

						if (cites.equalsIgnoreCase("III") || cites.equals("3")) {
							citeId = 3;
						}

						sql.append("and cite_id = ");
						sql.append(citeId);
						sql.append(" ");
					}
				}

				if (status != null && !status.equals("")) {

					String[] iniciales = status.split(",");
					if (iniciales.length > 1) {
						sql.append("and cite_id in ('");
						Integer citeId = 0;
						for (int i = 0; i < iniciales.length; i++) {
							if (iniciales[i].equalsIgnoreCase("I") || iniciales[i].equals("1")) {
								citeId = 1;
							}
							if (iniciales[i].equalsIgnoreCase("II") || iniciales[i].equals("2")) {
								citeId = 2;
							}

							if (iniciales[i].equalsIgnoreCase("III") || iniciales[i].equals("3")) {
								citeId = 3;
							}
							if (i != (iniciales.length - 1)) {
								sql.append(citeId);
								sql.append("','");
							} else {
								sql.append(citeId);
								sql.append(") ");
							}
						}
					} else {

						if (status.equalsIgnoreCase("end") || status.equals("e")) {
							sql.append("and spta_endemic = true ");

						}
						if (status.equalsIgnoreCase("exo") || status.equals("x")) {
							sql.append("and spta_exotic = true ");
						}

						if (status.equalsIgnoreCase("dom") || status.equals("d")) {
							sql.append("and spta_domestic = true ");
						}

						if (status.equalsIgnoreCase("cul") || status.equals("c")) {
							sql.append("and spta_domestic = true ");
						}

						if (status.equalsIgnoreCase("nat") || status.equals("n")) {
							sql.append("and spta_native = true ");
						}

						if (status.equalsIgnoreCase("mig") || status.equals("m")) {
							sql.append("and spta_migratory = true ");
						}

						if (status.equalsIgnoreCase("inv") || status.equals("i")) {
							sql.append("and spta_alien = true ");
						}

					}
				}

				List<Object[]> resu = new ArrayList<Object[]>();

				Query q = super.getEntityManager().createNativeQuery(sql.toString());
				resu = (q.getResultList());
				if (!resu.isEmpty()) {
					for (Object[] row : resu) {
						SumGSpResult sumarioG = new SumGSpResult();
						Metadata meta = new Metadata();
						meta.setComment(
								"El Ministerio del Ambiente y Agua se reserva el derecho de modificar sin previo aviso el listado de especies con información disponible, el contenido del sumario, y/o la estructura del servicio web. Para información actualizada se puede consultar en http://suia.ambiente.gob.ec");
						Author author = new Author();
						author.setComment(
								"Autor. Persona o personas que contribuyeron con su conocimiento intelectual y de manera significativa al contenido del sumario");
						if (row[37] != null) {
							author.setValue(row[37].toString());
						}
						Reviewer reviewer = new Reviewer();
						reviewer.setComment(
								"Revisor. Persona o personas que revisaron o examinaron cuidadosamente el contenido del sumario");
						if (row[39] != null) {
							reviewer.setValue(row[39].toString());
						}
						Editor editor = new Editor();
						editor.setComment(
								"Editor. Persona o personas que contribuyeron a corregir y adaptar el contenido del sumario para uso del público objetivo");
						if (row[38] != null) {
							editor.setValue(row[38].toString());
						}
						Collaborator collaborator = new Collaborator();
						collaborator.setComment(
								"Colaborador. Persona que trabajó con el o los autores en la realización del sumario sin aportar conocimiento sino esfuerzo en sistematizar y digitalizar la información");
						if (row[40] != null) {
							collaborator.setValue(row[40].toString());
						}
						PublicationYear publicationYear = new PublicationYear();
						publicationYear.setComment("Año de publicación del sumario de la especie");
						if (row[41] != null) {
							publicationYear.setValue(((Integer) row[41]).toString());
						}
						Language language = new Language();
						language.setComment(
								"Idioma utilizado para escribir la información del sumario de la especie, se utiliza el código ISO 639-2 (https://www.loc.gov/standards/iso639-2/php/code_list.php)");
						language.setValue("SPA");
						RightsHolder rightsHolder = new RightsHolder();
						rightsHolder.setComment(
								"Nombre de la Institución con los derechos de propiedad o administracion del sumario");
						rightsHolder.setValue("Todos los derechos reservados - Ministerio del Ambiente y Agua, 2020");
						AccessRights accessRights = new AccessRights();
						accessRights.setComment(
								"Nombre de la Institución con los derechos de propiedad o administracion del sumario");
						if (row[42] != null) {
							accessRights.setValue(row[42].toString());
						}
						License license = new License();
						license.setComment(
								"Declaración de permiso oficial para hacer uso del sumario y su información");
						license.setValue("https://creativecommons.org/licenses/by-sa/4.0/");
						Version version = new Version();
						version.setComment("Número y fecha de la versión actual");
						version.setValue("01.2020");
						meta.setAccessRights(accessRights);
						meta.setAuthor(author);
						meta.setCollaborator(collaborator);
						meta.setEditor(editor);
						meta.setLanguage(language);
						meta.setLicense(license);
						meta.setPublicationYear(publicationYear);
						meta.setReviewer(reviewer);
						meta.setRightsHolder(rightsHolder);
						meta.setVersion(version);
						sumarioG.setMetadata(meta);
						Taxon taxon = new Taxon();
						Gui guiS = new Gui();
						guiS.setComment(
								"Global Unique Identifier. Código único nacional del Catálogo Nacional de Objetos Biológicos");
						if (row[5] != null) {
							guiS.setValue(row[5].toString());
						}
						Kingdom kingdom = new Kingdom();
						kingdom.setComment("El nombre científico completo del reino en el que se clasifica el taxón");
						Phylum phylum = new Phylum();
						phylum.setComment("El nombre científico completo del phylum en el que se clasifica el taxón");
						Clas clas = new Clas();
						clas.setComment("El nombre científico completo de la clase en el que se clasifica el taxón");
						Order order = new Order();
						order.setComment("El nombre científico completo del orden en el que se clasifica el taxón");
						Family family = new Family();
						family.setComment(
								"El nombre científico completo de la familia en el que se clasifica el taxón");
						Genus genus = new Genus();
						genus.setComment("El nombre científico completo del género en el que se clasifica el taxón");

						JSONParser parser = new JSONParser();
						try {
							if (row[12] != null) {
								JSONObject json = (JSONObject) parser.parse(row[12].toString());
								if(json.get("Reino")!=null)
								{
								kingdom.setValue(json.get("Reino").toString());
								}
								if(json.get("Phylum")!=null)
								{
								phylum.setValue(json.get("Phylum").toString());
								}
								if(json.get("Clase")!=null)
								{
								clas.setValue(json.get("Clase").toString());
								}
								if(json.get("Orden")!=null)
								{
								order.setValue(json.get("Orden").toString());
								}
								if(json.get("Familia")!=null)
								{
								family.setValue(json.get("Familia").toString());
								}
								if(json.get("Género")!=null)
								{
								genus.setValue(json.get("Género").toString());
								}
							}
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						SpecificInfraspecificEpithet specificInfraspecificEpithet = new SpecificInfraspecificEpithet();
						specificInfraspecificEpithet.setComment(
								"El nombre científico completo del epíteto específico y el epíteto infraespecífico (de ser el caso) en el rango taxonómico más bajo");
						if (row[13] != null) {
							specificInfraspecificEpithet.setValue(row[13].toString());
						}
						ScientificName scientificName = new ScientificName();
						scientificName.setComment("El nombre científico completo, con la autoría y año");
						if (row[14] != null) {
							scientificName.setValue(row[14].toString());
						}
						TaxonRank taxonRank = new TaxonRank();
						taxonRank.setComment(
								"El rango taxonómico del nombre más específico provisto en el campo scientificName. Se usa vocabulario controlado (Reino, Phylum, Clase, Orden, Familia, Genero, especie, subespecie, variedad, forma)");
						if (row[3] != null) {
							taxonRank.setValue(row[3].toString());
						}
						ScientificNameAuthorship scientificNameAuthorship = new ScientificNameAuthorship();
						scientificNameAuthorship.setComment("Información de autoría del nombre científico");
						if (row[6] != null) {
							scientificNameAuthorship.setValue(row[6].toString());
						}
						NamePublishedInYear namePublishedInYear = new NamePublishedInYear();
						namePublishedInYear
								.setComment("El año de cuatro dígitos en el que se publicó el nombre científico");
						if (row[7] != null) {
							namePublishedInYear.setValue(row[7].toString());
						}
						InEcuador inEcuador = new InEcuador();
						inEcuador.setComment("Es una especie registrada en Ecuador");
						if (row[14] != null) {
							inEcuador.setValue((Boolean) row[14]);
						}
						IsNative isNative = new IsNative();
						isNative.setComment("Es una especie nativa");
						if (row[18] != null) {
							isNative.setValue((Boolean) row[18]);
						}
						IsEndemic isEndemic = new IsEndemic();
						isEndemic.setComment("Es una especie endémica");
						if (row[15] != null) {
							isEndemic.setValue((Boolean) row[15]);
						}
						IsExotic isExotic = new IsExotic();
						isExotic.setComment("Es una especie exótica");
						if (row[16] != null) {
							isExotic.setValue((Boolean) row[16]);
						}
						IsInvasive isInvasive = new IsInvasive();
						isInvasive.setComment("Es una especie invasora");
						if (row[17] != null) {
							isExotic.setValue((Boolean) row[17]);
						}
						IsDomestic isDomestic = new IsDomestic();
						isDomestic.setComment(
								"En caso de flora y hongos, el término utilizado es cultivada. Doméstico aplica para animales");
						if (row[20] != null) {
							isDomestic.setValue((Boolean) row[20]);
						}
						IsMigratory isMigratory = new IsMigratory();
						if (row[19] != null) {
							isMigratory.setValue((Boolean) row[19]);
						}
						TaxonomicStatus taxonomicStatus = new TaxonomicStatus();
						if (row[2] != null) {
							taxonomicStatus.setValue(row[2].toString());
						}
						IsCorrectName isCorrectName = new IsCorrectName();
						if (row[0] != null) {
							isCorrectName.setValue((Boolean) row[0]);
						}
						CorrectScientificName correctScientificName = new CorrectScientificName();
						if (row[1] != null) {
							correctScientificName.setValue(row[1].toString());
						}
						List<ArtificialGroup> listArt = new ArrayList<>();
						JSONParser parserArt = new JSONParser();
						try {
							if (row[33] != null) {

								JSONArray json1 = (JSONArray) parserArt.parse(row[33].toString());
								for (int i = 0; i < json1.size(); i++) {
									ArtificialGroup art = new ArtificialGroup();
									String jsonObj = (String) (json1.get(i));
									art.setValue(jsonObj);
									listArt.add(art);
								}

							}
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						ArtificialGroups artificialGroups = new ArtificialGroups();
						artificialGroups.setArtificialGroup(listArt);
						taxon.setArtificialGroups(artificialGroups);
						taxon.setClas(clas);
						taxon.setCorrectScientificName(correctScientificName);
						taxon.setFamily(family);
						taxon.setGenus(genus);
						taxon.setGui(guiS);
						taxon.setInEcuador(inEcuador);
						taxon.setIsCorrectName(isCorrectName);
						taxon.setIsDomestic(isDomestic);
						taxon.setIsEndemic(isEndemic);
						taxon.setIsExotic(isExotic);
						taxon.setIsInvasive(isInvasive);
						taxon.setIsMigratory(isMigratory);
						taxon.setIsNative(isNative);
						taxon.setKingdom(kingdom);
						taxon.setNamePublishedInYear(namePublishedInYear);
						taxon.setOrder(order);
						taxon.setPhylum(phylum);
						taxon.setScientificName(scientificName);
						taxon.setScientificNameAuthorship(scientificNameAuthorship);
						taxon.setSpecificInfraspecificEpithet(specificInfraspecificEpithet);
						taxon.setTaxonomicStatus(taxonomicStatus);
						taxon.setTaxonRank(taxonRank);
						sumarioG.setTaxon(taxon);
						Description description = new Description();
						General general = new General();
						if (row[24] != null) {
							general.setValue(row[24].toString());
						}
						description.setGeneral(general);
						sumarioG.setDescription(description);
						List<VernacularN> listVern = new ArrayList<>();
						JSONParser parserVern = new JSONParser();
						try {
							if (row[8] != null) {

								JSONArray json1 = (JSONArray) parserVern.parse(row[8].toString());
								for (int i = 0; i < json1.size(); i++) {
									VernacularN art = new VernacularN();
									VernacularName verName = new VernacularName();
									Locality locality = new Locality();
									LanguageV languageV = new LanguageV();
									Source source = new Source();
									JSONObject json = (JSONObject) (json1.get(i));
									if(json.get("nombre")!=null)
									{
									verName.setValue(json.get("nombre").toString());
									}
									if(json.get("grupo_etnico")!=null)
									{
									locality.setValue(json.get("grupo_etnico").toString());
									}
									if(json.get("lenguaje")!=null)
									{
									languageV.setValue(json.get("lenguaje").toString());
									}
									if(json.get("citacorta")!=null)
									{
									source.setValue(json.get("citacorta").toString());
									}
									art.setLanguage(languageV);
									art.setLocality(locality);
									art.setSource(source);
									art.setVernacularName(verName);
									listVern.add(art);

								}

							}
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						VernacularNames vNames = new VernacularNames();
						vNames.setVernacularN(listVern);
						sumarioG.setVernacularNames(vNames);
						List<Synonym> listSin = new ArrayList<>();
						JSONParser parserSino = new JSONParser();
						try {
							if (row[25] != null) {

								JSONArray json1 = (JSONArray) parserSino.parse(row[25].toString());
								for (int i = 0; i < json1.size(); i++) {
									Synonym synonym = new Synonym();
									GuiSin guiSin = new GuiSin();
									ScientificNameSin scientificNameSin = new ScientificNameSin();
									JSONObject json = (JSONObject) (json1.get(i));
									if(json.get("spta_scientific_name")!=null)
									{
									guiSin.setValue(json.get("spta_scientific_name").toString());
									}
									if(json.get("spta_scientific_gui")!=null)
									{
									scientificNameSin.setValue(json.get("spta_scientific_gui").toString());
									}
									synonym.setGui(guiSin);
									synonym.setScientificName(scientificNameSin);
									listSin.add(synonym);

								}

							}
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Synonyms synonyms = new Synonyms();
						synonyms.setSynonym(listSin);
						sumarioG.setSynonyms(synonyms);
						List<Habit> listHab = new ArrayList<>();
						JSONParser parserHab = new JSONParser();
						try {
							if (row[28] != null) {

								JSONArray json1 = (JSONArray) parserHab.parse(row[28].toString());
								for (int i = 0; i < json1.size(); i++) {
									Habit habit = new Habit();
									HabitName habitName = new HabitName();
									SourceHab sourceHab = new SourceHab();
									JSONObject json = (JSONObject) (json1.get(i));
									if(json.get("nombre")!=null)
									{
									habitName.setValue(json.get("nombre").toString());
									}
									if(json.get("fuente")!=null)
									{
									sourceHab.setValue(json.get("fuente").toString());
									}
									habit.setHabit(habitName);
									habit.setSource(sourceHab);
									listHab.add(habit);

								}

							}
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Habits habits = new Habits();
						habits.setHabit(listHab);
						sumarioG.setHabits(habits);
						
						Distribution distribution = new Distribution();
						DistributionDis distributionDis = new DistributionDis();
						AltitudeRange altitudeRange = new AltitudeRange();
						Provinces provinces = new Provinces();
						ProtectedAreas protectedAreas = new ProtectedAreas();
						Ecosystems ecosystems = new Ecosystems();
						if (row[26] != null) {
							distributionDis.setValue(row[26].toString());
						}
						if (row[27] != null) {
							altitudeRange.setValue(row[27].toString());
						}
						List<Province> listProv = new ArrayList<>();
						JSONParser parserProv = new JSONParser();
						try {
							if (row[32] != null) {

								JSONArray json1 = (JSONArray) parserProv.parse(row[32].toString());
								for (int i = 0; i < json1.size(); i++) {
									Province prov = new Province();
									String jsonObj = (String) (json1.get(i));
									prov.setValue(jsonObj);
									listProv.add(prov);
								}

							}
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						provinces.setProvince(listProv);
						List<ProtectedArea> listProt = new ArrayList<>();
						JSONParser parserProt = new JSONParser();
						try {
							if (row[34] != null) {

								JSONArray json1 = (JSONArray) parserProt.parse(row[34].toString());
								for (int i = 0; i < json1.size(); i++) {
									ProtectedArea protect = new ProtectedArea();
									String jsonObj = (String) (json1.get(i));
									protect.setValue(jsonObj);
									listProt.add(protect);
								}

							}
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						protectedAreas.setProtectedArea(listProt);
						List<Ecosystem> listEcos = new ArrayList<>();
						JSONParser parserEcos = new JSONParser();
						try {
							if (row[35] != null) {

								JSONArray json1 = (JSONArray) parserEcos.parse(row[35].toString());
								for (int i = 0; i < json1.size(); i++) {
									Ecosystem ecos = new Ecosystem();
									String jsonObj = (String) (json1.get(i));
									ecos.setValue(jsonObj);
									listEcos.add(ecos);
								}

							}
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						ecosystems.setEcosystem(listEcos);
						distribution.setDistribution(distributionDis);
						distribution.setAltitudeRange(altitudeRange);
						distribution.setProvinces(provinces);
						distribution.setProtectedAreas(protectedAreas);
						distribution.setEcosystems(ecosystems);
						sumarioG.setDistribution(distribution);
						Conservation conservation = new Conservation();
						ThreatStatus threatStatus = new ThreatStatus();
						RedListEcCriteria redListEcCriteria= new RedListEcCriteria();
						AppendixCITES appendixCITES = new AppendixCITES();
						ConservationMeasure conservationMeasure= new ConservationMeasure();
						if (row[21] != null) {
							threatStatus.setValue(row[21].toString());
						}
						if (row[30] != null) {
							redListEcCriteria.setValue(row[30].toString());
						}
						if (row[31] != null) {
							appendixCITES.setValue(row[31].toString());
						}
						if (row[29] != null) {
							conservationMeasure.setValue(row[29].toString());
						}
						conservation.setAppendixCITES(appendixCITES);
						conservation.setConservationMeasure(conservationMeasure);
						conservation.setRedListEcCriteria(redListEcCriteria);
						conservation.setThreatStatus(threatStatus);
						sumarioG.setConservation(conservation);
						LiteratureReferences literatureReferences= new LiteratureReferences();
						List<LiteratureReference> liteList=new ArrayList<>();
						JSONParser parserF = new JSONParser();
						try {
							if (row[23] != null) {
								JSONArray json = (JSONArray) parserF.parse(row[23].toString());
								for (int i = 0; i < json.size(); i++) {
									JSONObject jsonObjeto1 = (JSONObject) (json.get(i));
									JSONArray json1 = (JSONArray) parserF.parse(jsonObjeto1.get("det_fuentes").toString());
									System.out.println(i);
									for (int k = 0; k < json1.size(); k++) {
										LiteratureReference lite = new LiteratureReference();
										Type typeL = new Type();
										Url url = new Url();
										Principal principal= new Principal();
										BibliographicCitation bibliographicCitation = new BibliographicCitation();
										JSONObject jsonObjeto = (JSONObject) (json1.get(k));
										if(jsonObjeto.get("bigc_description")!=null)
										{
										typeL.setValue(jsonObjeto.get("bigc_description").toString());
										}
										if(jsonObjeto.get("url")!=null)
										{
										url.setValue(jsonObjeto.get("url").toString());
										}
										if(jsonObjeto.get("stis_principal")!=null)
										{
										principal.setValue(jsonObjeto.get("stis_principal").toString());
										}
										if(jsonObjeto.get("nombre")!=null)
										{
										bibliographicCitation.setValue(jsonObjeto.get("nombre").toString());
										}
										lite.setBibliographicCitation(bibliographicCitation);
										lite.setPrincipal(principal);
										lite.setUrl(url);
										lite.setType(typeL);
										liteList.add(lite);
									}
								}
								
								
							}
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						literatureReferences.setLiteratureReference(liteList);
						sumarioG.setLiteratureReferences(literatureReferences);
						Images images= new Images();
						List<Image> imageList=new ArrayList<>();
						JSONParser parserI = new JSONParser();
						try {
							if (row[9] != null) {
								JSONArray json1 = (JSONArray) parserI.parse(row[9].toString());
								for (int i = 0; i < json1.size(); i++) {
									Image image = new Image();
									TypeIma typeI = new TypeIma();
									Title title = new Title();
									Creator creator= new Creator();
									DescriptionIma descriptionI = new DescriptionIma();
									JSONObject jsonObjeto = (JSONObject) (json1.get(i));
									typeI.setValue("Principal");
									if(jsonObjeto.get("titulo")!=null)
									{
									title.setValue(jsonObjeto.get("titulo").toString());
									}
									if(jsonObjeto.get("autor")!=null)
									{
									creator.setValue(jsonObjeto.get("autor").toString());
									}
									if(jsonObjeto.get("spph_description")!=null)
									{
									descriptionI.setValue(jsonObjeto.get("spph_description").toString());
									}
									
									Identifier iden=new Identifier();
									if(jsonObjeto.get("spph_alfresco_id")!=null)
									{
										byte [] imgArr=getImageAlfresco(jsonObjeto.get("spph_alfresco_id").toString());
										if(imgArr!=null)
										{
											iden.setValue(Base64.encode(imgArr));
										}
									
									}
									image.setIdentifier(iden);
									image.setCreator(creator);
									image.setDescription(descriptionI);
									image.setTitle(title);
									image.setType(typeI);
									imageList.add(image);
								}
								
							}
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						try {
							if (row[10] != null) {
								JSONArray json1 = (JSONArray) parserI.parse(row[10].toString());
								for (int i = 0; i < json1.size(); i++) {
									Image image = new Image();
									TypeIma typeI = new TypeIma();
									Title title = new Title();
									Creator creator= new Creator();
									DescriptionIma descriptionI = new DescriptionIma();
									JSONObject jsonObjeto = (JSONObject) (json1.get(i));
									typeI.setValue("Secundaria");
									if(jsonObjeto.get("titulo")!=null)
									{
									title.setValue(jsonObjeto.get("titulo").toString());
									}
									if(jsonObjeto.get("autor")!=null)
									{
									creator.setValue(jsonObjeto.get("autor").toString());
									}
									if(jsonObjeto.get("spph_description")!=null)
									{
									descriptionI.setValue(jsonObjeto.get("spph_description").toString());
									}
									
									Identifier iden=new Identifier();
									if(jsonObjeto.get("spph_alfresco_id")!=null)
									{
										byte [] imgArr=getImageAlfresco(jsonObjeto.get("spph_alfresco_id").toString());
										if(imgArr!=null)
										{
											iden.setValue(Base64.encode(imgArr));
										}
									
									}
									image.setIdentifier(iden);
									image.setCreator(creator);
									image.setDescription(descriptionI);
									image.setTitle(title);
									image.setType(typeI);
									imageList.add(image);
								}
								
							}
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						try {
							if (row[11] != null) {
								JSONArray json1 = (JSONArray) parserI.parse(row[11].toString());
								for (int i = 0; i < json1.size(); i++) {
									Image image = new Image();
									TypeIma typeI = new TypeIma();
									Title title = new Title();
									Creator creator= new Creator();
									DescriptionIma descriptionI = new DescriptionIma();
									JSONObject jsonObjeto = (JSONObject) (json1.get(i));
									typeI.setValue("Mapa");
									if(jsonObjeto.get("titulo")!=null)
									{
									title.setValue(jsonObjeto.get("titulo").toString());
									}
									if(jsonObjeto.get("autor")!=null)
									{
									creator.setValue(jsonObjeto.get("autor").toString());
									}
									if(jsonObjeto.get("description")!=null)
									{
									descriptionI.setValue(jsonObjeto.get("description").toString());
									}
									
									Identifier iden=new Identifier();
									if(jsonObjeto.get("spdm_alfresco_id")!=null)
									{
										byte [] imgArr=getImageAlfresco(jsonObjeto.get("spdm_alfresco_id").toString());
										if(imgArr!=null)
										{
											iden.setValue(Base64.encode(imgArr));
										}
									
									}
									image.setIdentifier(iden);
									image.setCreator(creator);
									image.setDescription(descriptionI);
									image.setTitle(title);
									image.setType(typeI);
									imageList.add(image);
								}
								
							}
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						images.setImage(imageList);
						sumarioG.setImages(images);
						objectResultG.add(sumarioG);

					}
				}

			} else {
				if (type.equals("forestal") || type.equals("f")) {
					sql.append(
							"select spta_correct_tax,spta_correct_tax_name,tast_name,spta_taxon_rank_name,spta_scientific_name scientific_name,spta_scientific_name_authorship author,       \r\n" + 
							"									   					 spta_name_published_year publication_year,spta_scientific_gui gui,        \r\n" + 
							"									   					 cast((select json_object(array( select  hicl_taxon_rank_name       \r\n" + 
							"									   					 				FROM biodiversity.higher_classifications hc       \r\n" + 
							"									   					 				where (st.spta_hierarchy_code LIKE '%;' || hc.hicl_id || ';%' or st.spta_hierarchy_code LIKE hc.hicl_id || ';%')       \r\n" + 
							"									   					 				and  hc.hicl_status = true order by hc.tara_id),        \r\n" + 
							"									   					 				array( select hc.hicl_scientific_name       \r\n" + 
							"									   					 				FROM biodiversity.higher_classifications hc       \r\n" + 
							"									   					 				where (st.spta_hierarchy_code LIKE '%;' || hc.hicl_id || ';%' or st.spta_hierarchy_code LIKE hc.hicl_id || ';%')       \r\n" + 
							"									   					 				and  hc.hicl_status = true order by hc.tara_id))) as text) taxonomia,  \r\n" + 
							"									   					 spta_specific_infraspecifc_epit,       \r\n" + 
							"									   					 cast ((select array_to_json(array_agg(row_to_json(t))) from (select spta_scientific_name,spta_scientific_gui  from biodiversity.species_taxa        \r\n" + 
							"									   								 					 where spta_id in (select UNNEST (cast(string_to_array(replace(biodiversity.get_synonyms_specie(st.spta_id),'nd','0'),';') as int[])))) t) as text) sinonimos, \r\n" + 
							"									   					 cast ((select array_to_json(array_agg(row_to_json(t))) from (select ha.habi_name nombre, inso_short_reference fuente        \r\n" + 
							"									   					 from biodiversity.species_taxa_habits sth inner join        \r\n" + 
							"									   					 biodiversity.habits ha on ha.habi_id = sth.habi_id and ha.habi_status = true inner join       \r\n" + 
							"									   					 biodiversity.information_sources inso on inso.inso_id = sth.inso_id and inso_status = true       \r\n" + 
							"									   					 where sth.stha_status = true and sth.spta_id = st.spta_id order by ha.habi_name) t) as text) AS habito,       \r\n" + 
							"									   					 spsu_description description, spfs_botanical_description botanical_description,       \r\n" + 
							"									   					 spfs_similar_species similar_species,spsu_distribution distribution,        \r\n" + 
							"									   					 cast (array_to_json(array(select eco.spec_name from  biodiversity.species_ecosystems eco       \r\n" + 
							"									   					 where eco.spec_status = true AND eco.spta_id = st.spta_id order by eco.spec_name))as text) AS ecosistemas,       \r\n" + 
							"									   					      \r\n" + 
							"									   					 cast ((select array_to_json(array_agg(row_to_json(t))) from (select 'spph_id_' || spph_id id, spph_author autor, spph_title titulo,        \r\n" + 
							"									   					 	   'data:' || spph_mime || ';base64,' mime, spph_description, spph_alfresco_id from biodiversity.species_photographs sp       \r\n" + 
							"									   					 	   where sp.spta_id = st.spta_id and sp.spph_status = true and sp.spph_photograph_general = true and sp.spph_type_photograph = 'GENERAL') t)as text) img_general,       \r\n" + 
							"									   					 cast ((select array_to_json(array_agg(row_to_json(t))) from (select 'spph_id_' || spph_id id, spph_author autor, spph_title titulo,       \r\n" + 
							"									   					 	   'data:' || spph_mime || ';base64,' mime, spph_description, spph_type_photograph tipo, spph_alfresco_id from biodiversity.species_photographs sp2       \r\n" + 
							"									   					 	   where sp2.spta_id = st.spta_id and sp2.spph_status = true and sp2.spph_photograph_general != true and sp2.spph_type_photograph = 'PARTICULAR') t) as text) img_particular,       \r\n" + 
							"									   					 spta_endemic,        \r\n" + 
							"									   					 spta_exotic,      					 spta_alien,  \r\n" + 
							"									   					 spta_native,      					 spta_migratory,   \r\n" + 
							"									   					 spta_ecuador,      					 spta_domestic,   \r\n" + 
							"									   					        					 rlec_name,       \r\n" + 
							"									   					 spta_red_list_ec_criteria,       \r\n" + 
							"									   					 spsu_altitudinal_rank altitudinal_rank,	       \r\n" + 
							"									   					 cast (array_to_json(array(select gl.gelo_name from biodiversity.species_taxa_provinces pr inner join        \r\n" + 
							"									   					 geographical_locations gl on pr.province_id = gl.gelo_id and gl.gelo_status = true       \r\n" + 
							"									   					 where pr.sptp_status = true AND pr.spta_id = st.spta_id order by pr.province_id))as text) AS provincia,       \r\n" + 
							"									   					 cast (array_to_json(array(select pa.sppa_name from  biodiversity.species_protected_areas pa       \r\n" + 
							"									   					 where pa.sppa_status = true AND pa.spta_id = st.spta_id order by pa.sppa_name)) as text) AS areanatural,       \r\n" + 
							"									   					 cast ((select array_to_json(array_agg(row_to_json(t))) from (select vena_vernacular_name nombre,                 \r\n" + 
							"						 						 								 					 CASE when tala_name is not null and tala_name != '' then tala_name else 'N/D' end lenguaje,                \r\n" + 
							"						 						 								 					 case when etgr_name is not null and etgr_name != '' then etgr_name else 'N/D' end grupo_etnico,                \r\n" + 
							"						 						 								 					 case when inso_short_reference is not null and inso_short_reference != '' then inso_short_reference else 'N/D' end citacorta                \r\n" + 
							"						 						 								 					 from biodiversity.vernacular_names vn                \r\n" + 
							"						 						 								 					 left join biodiversity.ethnic_groups eg on eg.etgr_id =  vn.etgr_id and eg.etgr_status = true                \r\n" + 
							"						 						 								 					 left join biodiversity.taxa_languages tl on tl.tala_id = vn.tala_id and tl.tala_status = true                \r\n" + 
							"						 						 								 					 left join biodiversity.information_sources inso on inso.inso_id = vn.inso_id and inso_status = true                \r\n" + 
							"						 						 								 					 where vn.spta_id = st.spta_id and vena_status = true order by tl.tala_id) t) as text)vernacular_name,      \r\n" + 
							"									   					 spfs_conditioned_use , spfs_author as author_sum,      \r\n" + 
							"									   					 cast ((select array_to_json(array_agg(row_to_json(t))) from (select 'spdm_id_' || spdm_id id, rtrim(spdm_author) autor, spdm_title titulo, spdm_description description,       \r\n" + 
							"									   					 	'data:' || spdm_mime || ';base64,' mime,       \r\n" + 
							"									   					  case when spdm_type_photograph = 'DISTRIBUCIONUNO' then 1        \r\n" + 
							"									   					 	  when spdm_type_photograph = 'DISTRIBUCIONDOS' then 2       \r\n" + 
							"									   					  	  when spdm_type_photograph = 'DISTRIBUCIONTRES' then 3 end as orden       \r\n" + 
							"									   					 	   ,spdm_alfresco_id from biodiversity.species_distribution_maps dm       \r\n" + 
							"									   					 	   where dm.spsu_id = su.spsu_id and dm.spdm_status = true order by orden) t)as text) img_dis_map,       \r\n" + 
							"									   					 cast ((select bigc_description from biodiversity.biodiversity_general_catalogs where bigc_code = 'mapa_dis')as text) as txt_map_dis,       \r\n" + 
							"									   					 spfs_ecology ecology, spfs_use, spsu_conservation_measure conservation_measure,spfs_used_volume ,       \r\n" + 
							"									   					 cast ((select array_to_json(array_agg(row_to_json(t))) from (select 'spph_id_' || spph_id id, spph_author autor, spph_title titulo,       \r\n" + 
							"									   					 	   'data:' || spph_mime || ';base64,' mime, spph_type_photograph tipo, spph_alfresco_id from biodiversity.species_photographs sp2       \r\n" + 
							"									   					 	   where sp2.spta_id = st.spta_id and sp2.spph_status = true and sp2.spph_type_photograph = 'ANA_VOL_APR_SAF') t) as text) img_forestal,     \r\n" + 
							"									   					 spfs_botanical_collection botanical_collection, spfs_hardness_weight hardness_weight, spfs_color_transversal color_transversal,       \r\n" + 
							"									   					 spfs_odor_transversal odor_transversal, spfs_taste_transversal taste_transversal,       \r\n" + 
							"									   					 spfs_latex_resin_transversal latex_resin_transversal, spfs_outer_cortex_transversal outer_cortex_transversal,       \r\n" + 
							"									   					 spfs_color_tangential color_tangential, spfs_veined_tangential veined_tangential, spfs_grain_tangential grain_tangential,       \r\n" + 
							"									   					 spfs_texture_tangential texture_tangential, spfs_veined_radial veined_radial, spfs_gloss_shine_radial gloss_shine_radial,       \r\n" + 
							"									   					 spfs_growth_rings growth_rings, spfs_pores_transversal pores_transversal,       \r\n" + 
							"									   					 spfs_axial_parenchyma_transversal axial_parenchyma_transversal, spfs_radial_parenchyma_transversal radial_parenchyma_transversal,       \r\n" + 
							"									   					 spfs_density_green density_green, spfs_density_air_dry density_air_dry, spfs_density_anhydrous density_anhydrous,       \r\n" + 
							"									   					 spfs_density_basic density_basic, spfs_contraction_radia contraction_radia,spfs_contraction_tangential contraction_tangential,       \r\n" + 
							"									   					 spfs_contraction_columetrica contraction_columetrica,spfs_contraction_total_radia contraction_total_radia,       \r\n" + 
							"									   					 spfs_contraction_total_tangential contraction_total_tangential,spfs_contraction_total_volumetric contraction_total_volumetric,       \r\n" + 
							"									   					 spfs_contraction_total_relationship_tr contraction_total_relationship_tr,       \r\n" + 
							"						 								 					 cast ((select array_to_json(array_agg(row_to_json(t))) from (select bigc.bigc_id id,bigc_description grupo,           \r\n" + 
							"						 								 					 (select array_to_json(array_agg(row_to_json(d))) from (select stis_name nombre,stis_url url, bigc_description,          \r\n" + 
							"						 								 					 stis_principal           \r\n" + 
							"						 								 					 from biodiversity.species_taxa_information_sources stis where stis.spta_id = st.spta_id and            \r\n" + 
							"						 								 					 stis.stis_status = true and bigc.bigc_id = stis.bigc_id ) d) det_fuentes           \r\n" + 
							"									   					 from biodiversity.biodiversity_general_catalogs bigc where bigc.bigc_id in        \r\n" + 
							"									   					 (select bigc_id from biodiversity.species_taxa_information_sources stis0 where stis0.spta_id = st.spta_id and stis0.stis_status = true)       \r\n" + 
							"									   					 order by bigc_description) t) as text) fuentes,       \r\n" + 
							"									   					 spfs_author,spfs_publication_year, spfs_editor editores, spfs_reviser revisores, spfs_collaborator colaboradores,       \r\n" + 
							"									   					 (select bcca_value from biodiversity.biodiversity_catalog_parameters where bcca_code = 'sumario.general.access.rights') as access_rights,  \r\n" + 
							"									   					 cast (array_to_json(array(select gl.argr_name from biodiversity.species_taxa_artificial_groups pr inner join   \r\n" + 
							"									   					biodiversity.artificial_groups gl on pr.argr_id = gl.argr_id and gl.argr_status = true   \r\n" + 
							"									   					where pr.stag_status = true AND pr.spta_id = st.spta_id order by gl.argr_name)) as text) AS gruposart,spta_cites_name,\r\n" + 
							"									   					cast ((select array_to_json(array_agg(row_to_json(t))) from (select 'spph_id_' || spph_id id, spph_author autor, spph_title titulo,       \r\n" + 
							"									   					 	   'data:' || spph_mime || ';base64,' mime,spph_description,spph_type_photograph tipo from biodiversity.species_photographs sp2       \r\n" + 
							"									   					 	   where sp2.spta_id = st.spta_id and sp2.spph_status = true and sp2.spph_type_photograph = 'ORG_TRANS_ROLL_UNO' or sp2.spph_type_photograph = 'ORG_TRANS_ROLL_DOS' or sp2.spph_type_photograph = 'ORG_TRANS_ROLL_TRES' ) t) as text) img_transversal,\r\n" + 
							"									   					cast ((select array_to_json(array_agg(row_to_json(t))) from (select 'spph_id_' || spph_id id, spph_author autor, spph_title titulo,       \r\n" + 
							"									   					 	   'data:' || spph_mime || ';base64,' mime, spph_description, spph_type_photograph tipo from biodiversity.species_photographs sp2       \r\n" + 
							"									   					 	   where sp2.spta_id = st.spta_id and sp2.spph_status = true and sp2.spph_type_photograph = 'ORG_TANG_ASERR_UNO' or sp2.spph_type_photograph = 'ORG_TANG_ASERR_DOS' or sp2.spph_type_photograph = 'ORG_TANG_ASERR_TRES' ) t) as text) img_tangencial, \r\n" + 
							"									   					cast ((select array_to_json(array_agg(row_to_json(t))) from (select 'spph_id_' || spph_id id, spph_author autor, spph_title titulo,       \r\n" + 
							"									   					 	   'data:' || spph_mime || ';base64,' mime,spph_description, spph_type_photograph tipo from biodiversity.species_photographs sp2       \r\n" + 
							"									   					 	   where sp2.spta_id = st.spta_id and sp2.spph_status = true and sp2.spph_type_photograph = 'ORG_RAD_ASERR_UNO' or sp2.spph_type_photograph = 'ORG_RAD_ASERR_DOS' or sp2.spph_type_photograph = 'ORG_RAD_ASERR_TRES' ) t) as text) img_radial, \r\n" + 
							"									   					 	   cast ((select array_to_json(array_agg(row_to_json(t))) from (select 'spph_id_' || spph_id id, spph_author autor, spph_title titulo,       \r\n" + 
							"									   					 	   'data:' || spph_mime || ';base64,' mime,spph_description, spph_type_photograph tipo from biodiversity.species_photographs sp2       \r\n" + 
							"									   					 	   where sp2.spta_id = st.spta_id and sp2.spph_status = true and sp2.spph_type_photograph = 'ANAT_TRANS_ASERR_UNO' or sp2.spph_type_photograph = 'ANAT_TRANS_ASERR_DOS' or sp2.spph_type_photograph = 'ANAT_TRANS_ASERR_TRES' ) t) as text) img_anatomica	      \r\n" + 
							"									   					from biodiversity.species_taxa st        \r\n" + 
							"									   					 inner join biodiversity.species_forestal_summaries spfs on       \r\n" + 
							"									   					 spfs.spta_id = st.spta_id   \r\n" + 
							"									   					left join biodiversity.species_summaries su on       \r\n" + 
							"									   					 su.spta_id = st.spta_id and spsu_status = true   			       \r\n" + 
							"									   					 left join biodiversity.red_lists_ecuador rl on        \r\n" + 
							"									   					 rl.rlec_id = st.rlec_id and rlec_status=true       \r\n" + 
							"									   					 left join biodiversity.red_lists_uicn rluicn on        \r\n" + 
							"									   					 rluicn.rlui_id = st.rlui_id and rlui_status=true  \r\n" + 
							"									   					 left join biodiversity.taxonomic_status taxstat on   \r\n" + 
							"									   					 taxstat.tast_id = st.tast_id where spfs_status = true ");

					if ((gui != null && !gui.equals("")) && (sName != null && !sName.equals(""))
							&& (rank != null && !rank.equals(""))) {

						if (idRank.equals(1)) {
							sql.append("and spta_hierarchy_code like '");
							sql.append(codigoPadre);
							sql.append(";%;%;%;%;%;%' ");

						} else {
							if (idRank.equals(2)) {
								sql.append("and spta_hierarchy_code like '");
								sql.append(codigoPadre);
								sql.append(";%;%;%;%;%' ");

							} else {
								if (idRank.equals(3)) {
									sql.append("and spta_hierarchy_code like '");
									sql.append(codigoPadre);
									sql.append(";%;%;%;%' ");

								} else {
									if (idRank.equals(4)) {
										sql.append("and spta_hierarchy_code like '");
										sql.append(codigoPadre);
										sql.append(";%;%;%' ");

									} else {
										if (idRank.equals(5)) {
											sql.append("and spta_hierarchy_code like '");
											sql.append(codigoPadre);
											sql.append(";%;%' ");

										} else {
											if (idRank.equals(6)) {
												sql.append("and spta_hierarchy_code like '");
												sql.append(codigoPadre);
												sql.append(";%' ");

											}
										}
									}
								}
							}
						}
					} else {
						if ((gui != null && !gui.equals("")) && (sName != null && !sName.equals(""))
								&& (rank == null || rank.equals(""))) {
							sql.append("and spta_scientific_gui = '");
							sql.append(gui);
							sql.append("' ");
						} else {
							if ((gui != null && !gui.equals("")) && (sName == null || sName.equals(""))
									&& (rank != null && !rank.equals(""))) {

								if (idRank.equals(1)) {
									sql.append("and spta_hierarchy_code like '");
									sql.append(codigoPadre);
									sql.append(";%;%;%;%;%;%' ");

								} else {
									if (idRank.equals(2)) {
										sql.append("and spta_hierarchy_code like '");
										sql.append(codigoPadre);
										sql.append(";%;%;%;%;%' ");

									} else {
										if (idRank.equals(3)) {
											sql.append("and spta_hierarchy_code like '");
											sql.append(codigoPadre);
											sql.append(";%;%;%;%' ");

										} else {
											if (idRank.equals(4)) {
												sql.append("and spta_hierarchy_code like '");
												sql.append(codigoPadre);
												sql.append(";%;%;%' ");

											} else {
												if (idRank.equals(5)) {
													sql.append("and spta_hierarchy_code like '");
													sql.append(codigoPadre);
													sql.append(";%;%' ");

												} else {
													if (idRank.equals(6)) {
														sql.append("and spta_hierarchy_code like '");
														sql.append(codigoPadre);
														sql.append(";%' ");

													}
												}
											}
										}
									}
								}

							} else {
								if ((gui != null && !gui.equals("")) && (sName == null || sName.equals(""))
										&& (rank == null || rank.equals(""))) {
									sql.append("and spta_scientific_gui = '");
									sql.append(gui);
									sql.append("' ");
								} else {
									if ((gui == null || gui.equals("")) && (sName == null || sName.equals(""))
											&& (rank != null && !rank.equals(""))) {
										if (idRank.equals(1)) {
											sql.append("and spta_hierarchy_code like '");
											sql.append(codigoPadre);
											sql.append(";%;%;%;%;%;%' ");

										} else {
											if (idRank.equals(2)) {
												sql.append("and spta_hierarchy_code like '");
												sql.append(codigoPadre);
												sql.append(";%;%;%;%;%' ");

											} else {
												if (idRank.equals(3)) {
													sql.append("and spta_hierarchy_code like '");
													sql.append(codigoPadre);
													sql.append(";%;%;%;%' ");

												} else {
													if (idRank.equals(4)) {
														sql.append("and spta_hierarchy_code like '");
														sql.append(codigoPadre);
														sql.append(";%;%;%' ");

													} else {
														if (idRank.equals(5)) {
															sql.append("and spta_hierarchy_code like '");
															sql.append(codigoPadre);
															sql.append(";%;%' ");

														} else {
															if (idRank.equals(6)) {
																sql.append("and spta_hierarchy_code like '");
																sql.append(codigoPadre);
																sql.append(";%' ");

															}
														}
													}
												}
											}
										}
									} else {
										if ((gui == null || gui.equals("")) && (sName != null && !sName.equals(""))
												&& (rank != null && !rank.equals(""))) {
											if (idRank.equals(1)) {
												sql.append("and spta_hierarchy_code like '");
												sql.append(codigoPadre);
												sql.append(";%;%;%;%;%;%' ");

											} else {
												if (idRank.equals(2)) {
													sql.append("and spta_hierarchy_code like '");
													sql.append(codigoPadre);
													sql.append(";%;%;%;%;%' ");

												} else {
													if (idRank.equals(3)) {
														sql.append("and spta_hierarchy_code like '");
														sql.append(codigoPadre);
														sql.append(";%;%;%;%' ");

													} else {
														if (idRank.equals(4)) {
															sql.append("and spta_hierarchy_code like '");
															sql.append(codigoPadre);
															sql.append(";%;%;%' ");

														} else {
															if (idRank.equals(5)) {
																sql.append("and spta_hierarchy_code like '");
																sql.append(codigoPadre);
																sql.append(";%;%' ");

															} else {
																if (idRank.equals(6)) {
																	sql.append("and spta_hierarchy_code like '");
																	sql.append(codigoPadre);
																	sql.append(";%' ");

																}
															}
														}
													}
												}
											}
										} else {
											if ((gui == null || gui.equals("")) && (sName != null && !sName.equals(""))
													&& (rank == null || rank.equals(""))) {
												sql.append("and spta_scientific_name = '");
												sql.append(sName);
												sql.append("' ");
											} 
										}
									}
								}
							}
						}
					}

					if (lrEC != null && !lrEC.equals("")) {
						sql.append("and UPPER(rlec_initial) in ('");
						String[] iniciales = lrEC.split(",");
						if (iniciales.length > 1) {
							for (int i = 0; i < iniciales.length; i++) {
								if (i != (iniciales.length - 1)) {
									sql.append(iniciales[i]);
									sql.append("','");
								} else {
									sql.append(iniciales[i]);
									sql.append(") ");
								}
							}
						} else {
							sql.append("and UPPER(rlec_initial) = '");
							sql.append(lrEC.toUpperCase());
							sql.append("' ");
						}
					}

					if (cites != null && !cites.equals("")) {

						String[] iniciales = cites.split(",");
						if (iniciales.length > 1) {
							sql.append("and cite_id in ('");
							Integer citeId = 0;
							for (int i = 0; i < iniciales.length; i++) {
								if (iniciales[i].equalsIgnoreCase("I") || iniciales[i].equals("1")) {
									citeId = 1;
								}
								if (iniciales[i].equalsIgnoreCase("II") || iniciales[i].equals("2")) {
									citeId = 2;
								}

								if (iniciales[i].equalsIgnoreCase("III") || iniciales[i].equals("3")) {
									citeId = 3;
								}
								if (i != (iniciales.length - 1)) {
									sql.append(citeId);
									sql.append("','");
								} else {
									sql.append(citeId);
									sql.append(") ");
								}
							}
						} else {
							Integer citeId = 0;
							if (cites.equalsIgnoreCase("I") || cites.equals("1")) {
								citeId = 1;
							}
							if (cites.equalsIgnoreCase("II") || cites.equals("2")) {
								citeId = 2;
							}

							if (cites.equalsIgnoreCase("III") || cites.equals("3")) {
								citeId = 3;
							}

							sql.append("and cite_id = ");
							sql.append(citeId);
							sql.append(" ");
						}
					}

					if (status != null && !status.equals("")) {

						String[] iniciales = status.split(",");
						if (iniciales.length > 1) {
							sql.append("and cite_id in ('");

							for (int i = 0; i < iniciales.length; i++) {
								if (iniciales[i].equalsIgnoreCase("end") || iniciales[i].equals("e")) {
									sql.append("and spta_endemic = true ");

								}
								if (iniciales[i].equalsIgnoreCase("exo") || iniciales[i].equals("x")) {
									sql.append("and spta_exotic = true ");
								}

								if (iniciales[i].equalsIgnoreCase("dom") || iniciales[i].equals("d")) {
									sql.append("and spta_domestic = true ");
								}

								if (iniciales[i].equalsIgnoreCase("cul") || iniciales[i].equals("c")) {
									sql.append("and spta_domestic = true ");
								}

								if (iniciales[i].equalsIgnoreCase("nat") || iniciales[i].equals("n")) {
									sql.append("and spta_native = true ");
								}

								if (iniciales[i].equalsIgnoreCase("mig") || iniciales[i].equals("m")) {
									sql.append("and spta_migratory = true ");
								}

								if (iniciales[i].equalsIgnoreCase("inv") || iniciales[i].equals("i")) {
									sql.append("and spta_alien = true ");
								}
							}
						} else {

							if (status.equalsIgnoreCase("end") || status.equals("e")) {
								sql.append("and spta_endemic = true ");

							}
							if (status.equalsIgnoreCase("exo") || status.equals("x")) {
								sql.append("and spta_exotic = true ");
							}

							if (status.equalsIgnoreCase("dom") || status.equals("d")) {
								sql.append("and spta_domestic = true ");
							}

							if (status.equalsIgnoreCase("cul") || status.equals("c")) {
								sql.append("and spta_domestic = true ");
							}

							if (status.equalsIgnoreCase("nat") || status.equals("n")) {
								sql.append("and spta_native = true ");
							}

							if (status.equalsIgnoreCase("mig") || status.equals("m")) {
								sql.append("and spta_migratory = true ");
							}

							if (status.equalsIgnoreCase("inv") || status.equals("i")) {
								sql.append("and spta_alien = true ");
							}

						}
					}
					
					List<Object[]> resu = new ArrayList<Object[]>();

					Query q = super.getEntityManager().createNativeQuery(sql.toString());
					resu = (q.getResultList());
					if (!resu.isEmpty()) {
						for (Object[] row : resu) {
							SumForeSpResult sumarioF = new SumForeSpResult();
							Metadata meta = new Metadata();
							meta.setComment(
									"El Ministerio del Ambiente y Agua se reserva el derecho de modificar sin previo aviso el listado de especies con información disponible, el contenido del sumario, y/o la estructura del servicio web. Para información actualizada se puede consultar en http://suia.ambiente.gob.ec");
							Author author = new Author();
							author.setComment(
									"Autor. Persona o personas que contribuyeron con su conocimiento intelectual y de manera significativa al contenido del sumario");
							if (row[70] != null) {
								author.setValue(row[70].toString());
							}
							Reviewer reviewer = new Reviewer();
							reviewer.setComment(
									"Revisor. Persona o personas que revisaron o examinaron cuidadosamente el contenido del sumario");
							if (row[73] != null) {
								reviewer.setValue(row[73].toString());
							}
							Editor editor = new Editor();
							editor.setComment(
									"Editor. Persona o personas que contribuyeron a corregir y adaptar el contenido del sumario para uso del público objetivo");
							if (row[72] != null) {
								editor.setValue(row[72].toString());
							}
							Collaborator collaborator = new Collaborator();
							collaborator.setComment(
									"Colaborador. Persona que trabajó con el o los autores en la realización del sumario sin aportar conocimiento sino esfuerzo en sistematizar y digitalizar la información");
							if (row[74] != null) {
								collaborator.setValue(row[74].toString());
							}
							PublicationYear publicationYear = new PublicationYear();
							publicationYear.setComment("Año de publicación del sumario de la especie");
							if (row[71] != null) {
								publicationYear.setValue(((Integer) row[71]).toString());
							}
							Language language = new Language();
							language.setComment(
									"Idioma utilizado para escribir la información del sumario de la especie, se utiliza el código ISO 639-2 (https://www.loc.gov/standards/iso639-2/php/code_list.php)");
							language.setValue("SPA");
							RightsHolder rightsHolder = new RightsHolder();
							rightsHolder.setComment(
									"Nombre de la Institución con los derechos de propiedad o administracion del sumario");
							rightsHolder.setValue("Todos los derechos reservados - Ministerio del Ambiente y Agua, 2020");
							AccessRights accessRights = new AccessRights();
							accessRights.setComment(
									"Nombre de la Institución con los derechos de propiedad o administracion del sumario");
							if (row[75] != null) {
								accessRights.setValue(row[75].toString());
							}
							License license = new License();
							license.setComment(
									"Declaración de permiso oficial para hacer uso del sumario y su información");
							license.setValue("https://creativecommons.org/licenses/by-sa/4.0/");
							Version version = new Version();
							version.setComment("Número y fecha de la versión actual");
							version.setValue("01.2020");
							meta.setAccessRights(accessRights);
							meta.setAuthor(author);
							meta.setCollaborator(collaborator);
							meta.setEditor(editor);
							meta.setLanguage(language);
							meta.setLicense(license);
							meta.setPublicationYear(publicationYear);
							meta.setReviewer(reviewer);
							meta.setRightsHolder(rightsHolder);
							meta.setVersion(version);
							sumarioF.setMetadata(meta);
							Taxon taxon = new Taxon();
							Gui guiS = new Gui();
							guiS.setComment(
									"Global Unique Identifier. Código único nacional del Catálogo Nacional de Objetos Biológicos");
							if (row[7] != null) {
								guiS.setValue(row[7].toString());
							}
							Kingdom kingdom = new Kingdom();
							kingdom.setComment("El nombre científico completo del reino en el que se clasifica el taxón");
							Phylum phylum = new Phylum();
							phylum.setComment("El nombre científico completo del phylum en el que se clasifica el taxón");
							Clas clas = new Clas();
							clas.setComment("El nombre científico completo de la clase en el que se clasifica el taxón");
							Order order = new Order();
							order.setComment("El nombre científico completo del orden en el que se clasifica el taxón");
							Family family = new Family();
							family.setComment(
									"El nombre científico completo de la familia en el que se clasifica el taxón");
							Genus genus = new Genus();
							genus.setComment("El nombre científico completo del género en el que se clasifica el taxón");

							JSONParser parser = new JSONParser();
							try {
								if (row[8] != null) {
									JSONObject json = (JSONObject) parser.parse(row[8].toString());
									if(json.get("Reino")!=null)
									{
									kingdom.setValue(json.get("Reino").toString());
									}
									if(json.get("Phylum")!=null)
									{
									phylum.setValue(json.get("Phylum").toString());
									}
									if(json.get("Clase")!=null)
									{
									clas.setValue(json.get("Clase").toString());
									}
									if(json.get("Orden")!=null)
									{
									order.setValue(json.get("Orden").toString());
									}
									if(json.get("Familia")!=null)
									{
									family.setValue(json.get("Familia").toString());
									}
									if(json.get("Género")!=null)
									{
									genus.setValue(json.get("Género").toString());
									}
								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							SpecificInfraspecificEpithet specificInfraspecificEpithet = new SpecificInfraspecificEpithet();
							specificInfraspecificEpithet.setComment(
									"El nombre científico completo del epíteto específico y el epíteto infraespecífico (de ser el caso) en el rango taxonómico más bajo");
							if (row[9] != null) {
								specificInfraspecificEpithet.setValue(row[9].toString());
							}
							ScientificName scientificName = new ScientificName();
							scientificName.setComment("El nombre científico completo, con la autoría y año");
							if (row[4] != null) {
								scientificName.setValue(row[4].toString());
							}
							TaxonRank taxonRank = new TaxonRank();
							taxonRank.setComment(
									"El rango taxonómico del nombre más específico provisto en el campo scientificName. Se usa vocabulario controlado (Reino, Phylum, Clase, Orden, Familia, Genero, especie, subespecie, variedad, forma)");
							if (row[3] != null) {
								taxonRank.setValue(row[3].toString());
							}
							ScientificNameAuthorship scientificNameAuthorship = new ScientificNameAuthorship();
							scientificNameAuthorship.setComment("Información de autoría del nombre científico");
							if (row[5] != null) {
								scientificNameAuthorship.setValue(row[5].toString());
							}
							NamePublishedInYear namePublishedInYear = new NamePublishedInYear();
							namePublishedInYear
									.setComment("El año de cuatro dígitos en el que se publicó el nombre científico");
							if (row[6] != null) {
								namePublishedInYear.setValue(row[6].toString());
							}
							InEcuador inEcuador = new InEcuador();
							inEcuador.setComment("Es una especie registrada en Ecuador");
							if (row[24] != null) {
								inEcuador.setValue((Boolean) row[24]);
							}
							IsNative isNative = new IsNative();
							isNative.setComment("Es una especie nativa");
							if (row[22] != null) {
								isNative.setValue((Boolean) row[22]);
							}
							IsEndemic isEndemic = new IsEndemic();
							isEndemic.setComment("Es una especie endémica");
							if (row[19] != null) {
								isEndemic.setValue((Boolean) row[19]);
							}
							IsExotic isExotic = new IsExotic();
							isExotic.setComment("Es una especie exótica");
							if (row[20] != null) {
								isExotic.setValue((Boolean) row[20]);
							}
							IsInvasive isInvasive = new IsInvasive();
							isInvasive.setComment("Es una especie invasora");
							if (row[21] != null) {
								isExotic.setValue((Boolean) row[21]);
							}
							IsDomestic isDomestic = new IsDomestic();
							isDomestic.setComment(
									"En caso de flora y hongos, el término utilizado es cultivada. Doméstico aplica para animales");
							if (row[25] != null) {
								isDomestic.setValue((Boolean) row[25]);
							}
							IsMigratory isMigratory = new IsMigratory();
							if (row[23] != null) {
								isMigratory.setValue((Boolean) row[23]);
							}
							TaxonomicStatus taxonomicStatus = new TaxonomicStatus();
							if (row[2] != null) {
								taxonomicStatus.setValue(row[2].toString());
							}
							IsCorrectName isCorrectName = new IsCorrectName();
							if (row[0] != null) {
								isCorrectName.setValue((Boolean) row[0]);
							}
							CorrectScientificName correctScientificName = new CorrectScientificName();
							if (row[1] != null) {
								correctScientificName.setValue(row[1].toString());
							}
							List<ArtificialGroup> listArt = new ArrayList<>();
							JSONParser parserArt = new JSONParser();
							try {
								if (row[76] != null) {

									JSONArray json1 = (JSONArray) parserArt.parse(row[76].toString());
									for (int i = 0; i < json1.size(); i++) {
										ArtificialGroup art = new ArtificialGroup();
										String jsonObj = (String) (json1.get(i));
										art.setValue(jsonObj);
										listArt.add(art);
									}

								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							ArtificialGroups artificialGroups = new ArtificialGroups();
							artificialGroups.setArtificialGroup(listArt);
							taxon.setArtificialGroups(artificialGroups);
							taxon.setClas(clas);
							taxon.setCorrectScientificName(correctScientificName);
							taxon.setFamily(family);
							taxon.setGenus(genus);
							taxon.setGui(guiS);
							taxon.setInEcuador(inEcuador);
							taxon.setIsCorrectName(isCorrectName);
							taxon.setIsDomestic(isDomestic);
							taxon.setIsEndemic(isEndemic);
							taxon.setIsExotic(isExotic);
							taxon.setIsInvasive(isInvasive);
							taxon.setIsMigratory(isMigratory);
							taxon.setIsNative(isNative);
							taxon.setKingdom(kingdom);
							taxon.setNamePublishedInYear(namePublishedInYear);
							taxon.setOrder(order);
							taxon.setPhylum(phylum);
							taxon.setScientificName(scientificName);
							taxon.setScientificNameAuthorship(scientificNameAuthorship);
							taxon.setSpecificInfraspecificEpithet(specificInfraspecificEpithet);
							taxon.setTaxonomicStatus(taxonomicStatus);
							taxon.setTaxonRank(taxonRank);
							sumarioF.setTaxon(taxon);
							DescriptionForestal description = new DescriptionForestal();
							General general = new General();
							if (row[12] != null) {
								general.setValue(row[12].toString());
							}
							
							BotanicalDescription botanicalDescription = new BotanicalDescription();
							if (row[13] != null) {
								botanicalDescription.setValue(row[13].toString());
							}
							
							ConditionalUse conditionalUse= new ConditionalUse();
							if (row[32] != null) {
								conditionalUse.setValue(row[32].toString());
							}
							
							SimilarSpecies similarSpecies = new SimilarSpecies();
							if (row[14] != null) {
								similarSpecies.setValue(row[14].toString());
							}
							
							Ecology ecology= new Ecology();
							if (row[36] != null) {
								ecology.setValue(row[36].toString());
							}
							
							Uses uses= new Uses();
							if (row[37] != null) {
								uses.setValue(row[37].toString());
							}
							
							VolumenSafAnalysis volumenSafAnalysis= new VolumenSafAnalysis();
							if (row[39] != null) {
								volumenSafAnalysis.setValue(row[39].toString());
							}	
							
							BotanicalWoodCollections botanicalWoodCollections= new BotanicalWoodCollections();
							if (row[41] != null) {
								botanicalWoodCollections.setValue(row[41].toString());
							}
							
							description.setGeneral(general);
							description.setBotanicalDescription(botanicalDescription);
							description.setBotanicalWoodCollections(botanicalWoodCollections);
							description.setConditionalUse(conditionalUse);
							description.setEcology(ecology);
							description.setSimilarSpecies(similarSpecies);
							description.setUses(uses);
							description.setVolumenSafAnalysis(volumenSafAnalysis);
							sumarioF.setDescription(description);
							OrganolepticCharacteristics organolepticCharacteristics= new OrganolepticCharacteristics();
							HardnessOrWeight hardnessOrWeight= new HardnessOrWeight();
							if (row[42] != null) {
								hardnessOrWeight.setValue(row[42].toString());
							}
							organolepticCharacteristics.setHardnessOrWeight(hardnessOrWeight);
							CrossCutRollizaWood crossCutRollizaWood= new CrossCutRollizaWood();
							ColorHeartwood colorHeartwood= new ColorHeartwood();
							if (row[43] != null) {
								colorHeartwood.setValue(row[43].toString());
							}
							Odor odor= new Odor();
							if (row[44] != null) {
								odor.setValue(row[44].toString());
							}
							
							Taste taste= new Taste();
							if (row[45] != null) {
								taste.setValue(row[45].toString());
							}
							
							LatexResin latexResin= new LatexResin();
							if (row[46] != null) {
								latexResin.setValue(row[46].toString());
							}
							
							OuterCortex outerCortex= new OuterCortex();
							if (row[47] != null) {
								outerCortex.setValue(row[47].toString());
							}
							 crossCutRollizaWood.setColorHeartwood(colorHeartwood);
							 crossCutRollizaWood.setLatexResin(latexResin);
							 crossCutRollizaWood.setOdor(odor);
							 crossCutRollizaWood.setOuterCortex(outerCortex);
							 crossCutRollizaWood.setTaste(taste);
							 organolepticCharacteristics.setCrossCutRollizaWood(crossCutRollizaWood);
							 
							 TangentalCutSawnWood tangentalCutSawnWood= new TangentalCutSawnWood();
							 Color color= new Color();
							 if (row[48] != null) {
									color.setValue(row[48].toString());
								}
							 Veined veined= new Veined();
							 if (row[49] != null) {
								 veined.setValue(row[49].toString());
								}
							 
							 Grain grain= new Grain();
							 if (row[50] != null) {
								 grain.setValue(row[50].toString());
								}
							 
							 Texture texture= new Texture();
							 if (row[51] != null) {
								 texture.setValue(row[51].toString());
								}
							 
							 tangentalCutSawnWood.setColor(color);
							 tangentalCutSawnWood.setGrain(grain);
							 tangentalCutSawnWood.setTexture(texture);
							 tangentalCutSawnWood.setVeined(veined);
							 organolepticCharacteristics.setTangentalCutSawnWood(tangentalCutSawnWood);
							 
							 RadialCutSawnWood radialCutSawnWood= new RadialCutSawnWood();
							 Veined veinedR= new Veined();
							 if (row[52] != null) {
								 veinedR.setValue(row[52].toString());
								}
							 
							 GlossShine glossShine= new GlossShine();
							 if (row[53] != null) {
								 glossShine.setValue(row[53].toString());
								}
							 radialCutSawnWood.setGlossShine(glossShine);
							 radialCutSawnWood.setVeined(veinedR);
							 organolepticCharacteristics.setRadialCutSawnWood(radialCutSawnWood);
							 sumarioF.setOrganolepticCharacteristics(organolepticCharacteristics);
							 
							 AnatomicalCharacteristics anatomicalCharacteristics= new AnatomicalCharacteristics();
							 CrossCutSawnWood crossCutSawnWood= new CrossCutSawnWood();
							 GrowthRings growthRings = new GrowthRings();
							 if (row[54] != null) {
								 growthRings.setValue(row[54].toString());
								}
							 
							 Pores pores= new Pores();
							 if (row[55] != null) {
								 pores.setValue(row[55].toString());
								}
							 
							 AxialParenchyma axialParenchyma= new AxialParenchyma();
							 if (row[56] != null) {
								 axialParenchyma.setValue(row[56].toString());
								}
							 
							 RadialParenchyma radialParenchyma= new RadialParenchyma();
							 if (row[57] != null) {
								 radialParenchyma.setValue(row[57].toString());
								}
							 
							 crossCutSawnWood.setAxialParenchyma(axialParenchyma);
							 crossCutSawnWood.setGrowthRings(growthRings);
							 crossCutSawnWood.setPores(pores);
							 crossCutSawnWood.setRadialParenchyma(radialParenchyma);
							 anatomicalCharacteristics.setCrossCutSawnWood(crossCutSawnWood);
							 sumarioF.setAnatomicalCharacteristics(anatomicalCharacteristics);
							 PhysicalCharacteristics physicalCharacteristics = new PhysicalCharacteristics();
							 Density density= new Density();
							 InGreen inGreen = new InGreen();
							 if (row[58] != null) {
								 inGreen.setValue(((BigDecimal)row[58]).toString());
								}
							 AirDry airDry= new AirDry();
							 if (row[59] != null) {
								 airDry.setValue(((BigDecimal)row[59]).toString());
								}
							 
							 Anhydrous anhydrous= new Anhydrous();
							 if (row[60] != null) {
								 anhydrous.setValue(((BigDecimal)row[60]).toString());
								}
							 
							 Basic basic= new Basic();
							 if (row[61] != null) {
								 basic.setValue(((BigDecimal)row[61]).toString());
								}
							 
							 density.setAirDry(airDry);
							 density.setAnhydrous(anhydrous);
							 density.setBasic(basic);
							 density.setInGreen(inGreen);
							 physicalCharacteristics.setDensity(density);
							 
							 NormalContraction normalContraction = new NormalContraction();
							 Radial radial= new Radial();
							 if (row[62] != null) {
								 radial.setValue(((BigDecimal)row[62]).toString());
								}
							 
							 Tangential tangential = new Tangential();
							 if (row[63] != null) {
								 tangential.setValue(((BigDecimal)row[63]).toString());
								}
							 
							 Volumetric volumetric= new Volumetric();
							 if (row[64] != null) {
								 volumetric.setValue(((BigDecimal)row[64]).toString());
								}
							 
							 normalContraction.setRadial(radial);
							 normalContraction.setTangential(tangential);
							 normalContraction.setVolumetric(volumetric);
							 physicalCharacteristics.setNormalContraction(normalContraction);
							 
							 TotalContraction totalContraction= new TotalContraction();
							 Radial radialT = new Radial();
							 if (row[65] != null) {
								 radialT.setValue(((BigDecimal)row[65]).toString());
								}
							 
							 Tangential tangentialT = new Tangential();
							 if (row[66] != null) {
								 tangentialT.setValue(((BigDecimal)row[66]).toString());
								}
							 
							 Volumetric volumetricT = new Volumetric();
							 if (row[67] != null) {
								 volumetricT.setValue(((BigDecimal)row[67]).toString());
								}
							 
							 TrRatio trRatio = new TrRatio();
							 if (row[68] != null) {
								 trRatio.setValue(((BigDecimal)row[68]).toString());
								}
							 
							 totalContraction.setRadial(radialT);
							 totalContraction.setTangential(tangentialT);
							 totalContraction.setTrRatio(trRatio);
							 totalContraction.setVolumetric(volumetricT);
							  physicalCharacteristics.setTotalContraction(totalContraction);
							  sumarioF.setPhysicalCharacteristics(physicalCharacteristics);
							List<VernacularN> listVern = new ArrayList<>();
							JSONParser parserVern = new JSONParser();
							try {
								if (row[31] != null) {

									JSONArray json1 = (JSONArray) parserVern.parse(row[31].toString());
									for (int i = 0; i < json1.size(); i++) {
										VernacularN art = new VernacularN();
										VernacularName verName = new VernacularName();
										Locality locality = new Locality();
										LanguageV languageV = new LanguageV();
										Source source = new Source();
										JSONObject json = (JSONObject) (json1.get(i));
										if(json.get("nombre")!=null)
										{
										verName.setValue(json.get("nombre").toString());
										}
										if(json.get("grupo_etnico")!=null)
										{
										locality.setValue(json.get("grupo_etnico").toString());
										}
										if(json.get("lenguaje")!=null)
										{
										languageV.setValue(json.get("lenguaje").toString());
										}
										if(json.get("citacorta")!=null)
										{
										source.setValue(json.get("citacorta").toString());
										}
										art.setLanguage(languageV);
										art.setLocality(locality);
										art.setSource(source);
										art.setVernacularName(verName);
										listVern.add(art);

									}

								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							VernacularNames vNames = new VernacularNames();
							vNames.setVernacularN(listVern);
							sumarioF.setVernacularNames(vNames);
							List<Synonym> listSin = new ArrayList<>();
							JSONParser parserSino = new JSONParser();
							try {
								if (row[10] != null) {

									JSONArray json1 = (JSONArray) parserSino.parse(row[10].toString());
									for (int i = 0; i < json1.size(); i++) {
										Synonym synonym = new Synonym();
										GuiSin guiSin = new GuiSin();
										ScientificNameSin scientificNameSin = new ScientificNameSin();
										JSONObject json = (JSONObject) (json1.get(i));
										if(json.get("spta_scientific_name")!=null)
										{
										guiSin.setValue(json.get("spta_scientific_name").toString());
										}
										if(json.get("spta_scientific_gui")!=null)
										{
										scientificNameSin.setValue(json.get("spta_scientific_gui").toString());
										}
										synonym.setGui(guiSin);
										synonym.setScientificName(scientificNameSin);
										listSin.add(synonym);

									}

								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Synonyms synonyms = new Synonyms();
							synonyms.setSynonym(listSin);
							sumarioF.setSynonyms(synonyms);
							List<Habit> listHab = new ArrayList<>();
							JSONParser parserHab = new JSONParser();
							try {
								if (row[11] != null) {

									JSONArray json1 = (JSONArray) parserHab.parse(row[11].toString());
									for (int i = 0; i < json1.size(); i++) {
										Habit habit = new Habit();
										HabitName habitName = new HabitName();
										SourceHab sourceHab = new SourceHab();
										JSONObject json = (JSONObject) (json1.get(i));
										if(json.get("nombre")!=null)
										{
										habitName.setValue(json.get("nombre").toString());
										}
										if(json.get("fuente")!=null)
										{
										sourceHab.setValue(json.get("fuente").toString());
										}
										habit.setHabit(habitName);
										habit.setSource(sourceHab);
										listHab.add(habit);

									}

								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Habits habits = new Habits();
							habits.setHabit(listHab);
							sumarioF.setHabits(habits);
							
							Distribution distribution = new Distribution();
							DistributionDis distributionDis = new DistributionDis();
							AltitudeRange altitudeRange = new AltitudeRange();
							Provinces provinces = new Provinces();
							ProtectedAreas protectedAreas = new ProtectedAreas();
							Ecosystems ecosystems = new Ecosystems();
							if (row[15] != null) {
								distributionDis.setValue(row[15].toString());
							}
							if (row[28] != null) {
								altitudeRange.setValue(row[28].toString());
							}
							List<Province> listProv = new ArrayList<>();
							JSONParser parserProv = new JSONParser();
							try {
								if (row[29] != null) {

									JSONArray json1 = (JSONArray) parserProv.parse(row[29].toString());
									for (int i = 0; i < json1.size(); i++) {
										Province prov = new Province();
										String jsonObj = (String) (json1.get(i));
										prov.setValue(jsonObj);
										listProv.add(prov);
									}

								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							provinces.setProvince(listProv);
							List<ProtectedArea> listProt = new ArrayList<>();
							JSONParser parserProt = new JSONParser();
							try {
								if (row[30] != null) {

									JSONArray json1 = (JSONArray) parserProt.parse(row[30].toString());
									for (int i = 0; i < json1.size(); i++) {
										ProtectedArea protect = new ProtectedArea();
										String jsonObj = (String) (json1.get(i));
										protect.setValue(jsonObj);
										listProt.add(protect);
									}

								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							protectedAreas.setProtectedArea(listProt);
							List<Ecosystem> listEcos = new ArrayList<>();
							JSONParser parserEcos = new JSONParser();
							try {
								if (row[16] != null) {

									JSONArray json1 = (JSONArray) parserEcos.parse(row[16].toString());
									for (int i = 0; i < json1.size(); i++) {
										Ecosystem ecos = new Ecosystem();
										String jsonObj = (String) (json1.get(i));
										ecos.setValue(jsonObj);
										listEcos.add(ecos);
									}

								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							ecosystems.setEcosystem(listEcos);
							distribution.setDistribution(distributionDis);
							distribution.setAltitudeRange(altitudeRange);
							distribution.setProvinces(provinces);
							distribution.setProtectedAreas(protectedAreas);
							distribution.setEcosystems(ecosystems);
							sumarioF.setDistribution(distribution);
							Conservation conservation = new Conservation();
							ThreatStatus threatStatus = new ThreatStatus();
							RedListEcCriteria redListEcCriteria= new RedListEcCriteria();
							AppendixCITES appendixCITES = new AppendixCITES();
							ConservationMeasure conservationMeasure= new ConservationMeasure();
							if (row[26] != null) {
								threatStatus.setValue(row[26].toString());
							}
							if (row[27] != null) {
								redListEcCriteria.setValue(row[27].toString());
							}
							if (row[77] != null) {
								appendixCITES.setValue(row[77].toString());
							}
							if (row[38] != null) {
								conservationMeasure.setValue(row[38].toString());
							}
							conservation.setAppendixCITES(appendixCITES);
							conservation.setConservationMeasure(conservationMeasure);
							conservation.setRedListEcCriteria(redListEcCriteria);
							conservation.setThreatStatus(threatStatus);
							sumarioF.setConservation(conservation);
							LiteratureReferences literatureReferences= new LiteratureReferences();
							List<LiteratureReference> liteList=new ArrayList<>();
							JSONParser parserF = new JSONParser();
							try {
								if (row[69] != null) {
									JSONArray json = (JSONArray) parserF.parse(row[69].toString());
									for (int i = 0; i < json.size(); i++) {
										JSONObject jsonObjeto1 = (JSONObject) (json.get(i));
										JSONArray json1 = (JSONArray) parserF.parse(jsonObjeto1.get("det_fuentes").toString());
										for (int k = 0; k < json1.size(); k++) {
											LiteratureReference lite = new LiteratureReference();
											Type typeL = new Type();
											Url url = new Url();
											Principal principal= new Principal();
											BibliographicCitation bibliographicCitation = new BibliographicCitation();
											JSONObject jsonObjeto = (JSONObject) (json1.get(k));
											if(jsonObjeto.get("bigc_description")!=null)
											{
											typeL.setValue(jsonObjeto.get("bigc_description").toString());
											}
											if(jsonObjeto.get("url")!=null)
											{
											url.setValue(jsonObjeto.get("url").toString());
											}
											if(jsonObjeto.get("stis_principal")!=null)
											{
											principal.setValue(jsonObjeto.get("stis_principal").toString());
											}
											if(jsonObjeto.get("nombre")!=null)
											{
											bibliographicCitation.setValue(jsonObjeto.get("nombre").toString());
											}
											lite.setBibliographicCitation(bibliographicCitation);
											lite.setPrincipal(principal);
											lite.setUrl(url);
											lite.setType(typeL);
											liteList.add(lite);
										}
									}
									
									
								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							literatureReferences.setLiteratureReference(liteList);
							sumarioF.setLiteratureReferences(literatureReferences);
							Images images= new Images();
							List<Image> imageList=new ArrayList<>();
							JSONParser parserI = new JSONParser();
							try {
								if (row[17] != null) {
									JSONArray json1 = (JSONArray) parserI.parse(row[17].toString());
									for (int i = 0; i < json1.size(); i++) {
										Image image = new Image();
										TypeIma typeI = new TypeIma();
										Title title = new Title();
										Creator creator= new Creator();
										DescriptionIma descriptionI = new DescriptionIma();
										JSONObject jsonObjeto = (JSONObject) (json1.get(i));
										typeI.setValue("Principal");
										if(jsonObjeto.get("titulo")!=null)
										{
										title.setValue(jsonObjeto.get("titulo").toString());
										}
										if(jsonObjeto.get("autor")!=null)
										{
										creator.setValue(jsonObjeto.get("autor").toString());
										}
										if(jsonObjeto.get("spph_description")!=null)
										{
										descriptionI.setValue(jsonObjeto.get("spph_description").toString());
										}
										
										Identifier iden=new Identifier();
										if(jsonObjeto.get("spph_alfresco_id")!=null)
										{
											byte [] imgArr=getImageAlfresco(jsonObjeto.get("spph_alfresco_id").toString());
											if(imgArr!=null)
											{
												iden.setValue(Base64.encode(imgArr));
											}
										
										}
										image.setIdentifier(iden);
										image.setCreator(creator);
										image.setDescription(descriptionI);
										image.setTitle(title);
										image.setType(typeI);
										imageList.add(image);
									}
									
								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							try {
								if (row[18] != null) {
									JSONArray json1 = (JSONArray) parserI.parse(row[18].toString());
									for (int i = 0; i < json1.size(); i++) {
										Image image = new Image();
										TypeIma typeI = new TypeIma();
										Title title = new Title();
										Creator creator= new Creator();
										DescriptionIma descriptionI = new DescriptionIma();
										JSONObject jsonObjeto = (JSONObject) (json1.get(i));
										typeI.setValue("Secundaria");
										if(jsonObjeto.get("titulo")!=null)
										{
										title.setValue(jsonObjeto.get("titulo").toString());
										}
										if(jsonObjeto.get("autor")!=null)
										{
										creator.setValue(jsonObjeto.get("autor").toString());
										}
										if(jsonObjeto.get("spph_description")!=null)
										{
										descriptionI.setValue(jsonObjeto.get("spph_description").toString());
										}
										Identifier iden=new Identifier();
										if(jsonObjeto.get("spph_alfresco_id")!=null)
										{
											byte [] imgArr=getImageAlfresco(jsonObjeto.get("spph_alfresco_id").toString());
											if(imgArr!=null)
											{
												iden.setValue(Base64.encode(imgArr));
											}
										
										}
										image.setIdentifier(iden);
										image.setCreator(creator);
										image.setDescription(descriptionI);
										image.setTitle(title);
										image.setType(typeI);
										imageList.add(image);
									}
									
								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							try {
								if (row[34] != null) {
									JSONArray json1 = (JSONArray) parserI.parse(row[34].toString());
									for (int i = 0; i < json1.size(); i++) {
										Image image = new Image();
										TypeIma typeI = new TypeIma();
										Title title = new Title();
										Creator creator= new Creator();
										DescriptionIma descriptionI = new DescriptionIma();
										JSONObject jsonObjeto = (JSONObject) (json1.get(i));
										typeI.setValue("Mapa");
										if(jsonObjeto.get("titulo")!=null)
										{
										title.setValue(jsonObjeto.get("titulo").toString());
										}
										if(jsonObjeto.get("autor")!=null)
										{
										creator.setValue(jsonObjeto.get("autor").toString());
										}
										if(jsonObjeto.get("description")!=null)
										{
										descriptionI.setValue(jsonObjeto.get("description").toString());
										}
										Identifier iden=new Identifier();
										if(jsonObjeto.get("spdm_alfresco_id")!=null)
										{
											byte [] imgArr=getImageAlfresco(jsonObjeto.get("spdm_alfresco_id").toString());
											if(imgArr!=null)
											{
												iden.setValue(Base64.encode(imgArr));
											}
										
										}
										image.setIdentifier(iden);
										image.setCreator(creator);
										image.setDescription(descriptionI);
										image.setTitle(title);
										image.setType(typeI);
										imageList.add(image);
									}
									
								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							try {
								if (row[40] != null) {
									JSONArray json1 = (JSONArray) parserI.parse(row[40].toString());
									for (int i = 0; i < json1.size(); i++) {
										Image image = new Image();
										TypeIma typeI = new TypeIma();
										Title title = new Title();
										Creator creator= new Creator();
										DescriptionIma descriptionI = new DescriptionIma();
										JSONObject jsonObjeto = (JSONObject) (json1.get(i));
										typeI.setValue("Forestal");
										if(jsonObjeto.get("titulo")!=null)
										{
										title.setValue(jsonObjeto.get("titulo").toString());
										}
										if(jsonObjeto.get("autor")!=null)
										{
										creator.setValue(jsonObjeto.get("autor").toString());
										}
										if(jsonObjeto.get("description")!=null)
										{
										descriptionI.setValue(jsonObjeto.get("description").toString());
										}
										Identifier iden=new Identifier();
										if(jsonObjeto.get("spdm_alfresco_id")!=null)
										{
											byte [] imgArr=getImageAlfresco(jsonObjeto.get("spdm_alfresco_id").toString());
											if(imgArr!=null)
											{
												iden.setValue(Base64.encode(imgArr));
											}
										
										}
										image.setIdentifier(iden);
										image.setCreator(creator);
										image.setDescription(descriptionI);
										image.setTitle(title);
										image.setType(typeI);
										imageList.add(image);
									}
									
								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							try {
								if (row[78] != null) {
									JSONArray json1 = (JSONArray) parserI.parse(row[78].toString());
									for (int i = 0; i < json1.size(); i++) {
										Image image = new Image();
										TypeIma typeI = new TypeIma();
										Title title = new Title();
										Creator creator= new Creator();
										DescriptionIma descriptionI = new DescriptionIma();
										JSONObject jsonObjeto = (JSONObject) (json1.get(i));
										typeI.setValue("Transversal");
										if(jsonObjeto.get("titulo")!=null)
										{
										title.setValue(jsonObjeto.get("titulo").toString());
										}
										if(jsonObjeto.get("autor")!=null)
										{
										creator.setValue(jsonObjeto.get("autor").toString());
										}
										if(jsonObjeto.get("spph_description")!=null)
										{
										descriptionI.setValue(jsonObjeto.get("spph_description").toString());
										}
										Identifier iden=new Identifier();
										if(jsonObjeto.get("spph_alfresco_id")!=null)
										{
											byte [] imgArr=getImageAlfresco(jsonObjeto.get("spph_alfresco_id").toString());
											if(imgArr!=null)
											{
												iden.setValue(Base64.encode(imgArr));
											}
										
										}
										image.setIdentifier(iden);
										image.setCreator(creator);
										image.setDescription(descriptionI);
										image.setTitle(title);
										image.setType(typeI);
										imageList.add(image);
									}
									
								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							try {
								if (row[79] != null) {
									JSONArray json1 = (JSONArray) parserI.parse(row[79].toString());
									for (int i = 0; i < json1.size(); i++) {
										Image image = new Image();
										TypeIma typeI = new TypeIma();
										Title title = new Title();
										Creator creator= new Creator();
										DescriptionIma descriptionI = new DescriptionIma();
										JSONObject jsonObjeto = (JSONObject) (json1.get(i));
										typeI.setValue("Tangencial");
										if(jsonObjeto.get("titulo")!=null)
										{
										title.setValue(jsonObjeto.get("titulo").toString());
										}
										if(jsonObjeto.get("autor")!=null)
										{
										creator.setValue(jsonObjeto.get("autor").toString());
										}
										if(jsonObjeto.get("spph_description")!=null)
										{
										descriptionI.setValue(jsonObjeto.get("spph_description").toString());
										}
										Identifier iden=new Identifier();
										if(jsonObjeto.get("spph_alfresco_id")!=null)
										{
											byte [] imgArr=getImageAlfresco(jsonObjeto.get("spph_alfresco_id").toString());
											if(imgArr!=null)
											{
												iden.setValue(Base64.encode(imgArr));
											}
										
										}
										image.setIdentifier(iden);
										image.setCreator(creator);
										image.setDescription(descriptionI);
										image.setTitle(title);
										image.setType(typeI);
										imageList.add(image);
									}
									
								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							try {
								if (row[80] != null) {
									JSONArray json1 = (JSONArray) parserI.parse(row[80].toString());
									for (int i = 0; i < json1.size(); i++) {
										Image image = new Image();
										TypeIma typeI = new TypeIma();
										Title title = new Title();
										Creator creator= new Creator();
										DescriptionIma descriptionI = new DescriptionIma();
										JSONObject jsonObjeto = (JSONObject) (json1.get(i));
										typeI.setValue("Radial");
										if(jsonObjeto.get("titulo")!=null)
										{
										title.setValue(jsonObjeto.get("titulo").toString());
										}
										if(jsonObjeto.get("autor")!=null)
										{
										creator.setValue(jsonObjeto.get("autor").toString());
										}
										if(jsonObjeto.get("spph_description")!=null)
										{
										descriptionI.setValue(jsonObjeto.get("spph_description").toString());
										}
										Identifier iden=new Identifier();
										if(jsonObjeto.get("spph_alfresco_id")!=null)
										{
											byte [] imgArr=getImageAlfresco(jsonObjeto.get("spph_alfresco_id").toString());
											if(imgArr!=null)
											{
												iden.setValue(Base64.encode(imgArr));
											}
										
										}
										image.setIdentifier(iden);
										image.setCreator(creator);
										image.setDescription(descriptionI);
										image.setTitle(title);
										image.setType(typeI);
										imageList.add(image);
									}
									
								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							try {
								if (row[81] != null) {
									JSONArray json1 = (JSONArray) parserI.parse(row[81].toString());
									for (int i = 0; i < json1.size(); i++) {
										Image image = new Image();
										TypeIma typeI = new TypeIma();
										Title title = new Title();
										Creator creator= new Creator();
										DescriptionIma descriptionI = new DescriptionIma();
										JSONObject jsonObjeto = (JSONObject) (json1.get(i));
										typeI.setValue("Anatómica");
										if(jsonObjeto.get("titulo")!=null)
										{
										title.setValue(jsonObjeto.get("titulo").toString());
										}
										if(jsonObjeto.get("autor")!=null)
										{
										creator.setValue(jsonObjeto.get("autor").toString());
										}
										if(jsonObjeto.get("spph_description")!=null)
										{
										descriptionI.setValue(jsonObjeto.get("spph_description").toString());
										}
										Identifier iden=new Identifier();
										if(jsonObjeto.get("spph_alfresco_id")!=null)
										{
											byte [] imgArr=getImageAlfresco(jsonObjeto.get("spph_alfresco_id").toString());
											if(imgArr!=null)
											{
												iden.setValue(Base64.encode(imgArr));
											}
										
										}
										image.setIdentifier(iden);
										image.setCreator(creator);
										image.setDescription(descriptionI);
										image.setTitle(title);
										image.setType(typeI);
										imageList.add(image);
									}
									
								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							images.setImage(imageList);
							sumarioF.setImages(images);
							objectResultF.add(sumarioF);

						}
					}
					
				} else {
					sql.append(
							"select spta_correct_tax,spta_correct_tax_name,tast_name,spta_taxon_rank_name,spta_scientific_name scientific_name,spta_scientific_gui gui,       \r\n" + 
							"									   					 spta_scientific_name_authorship author, spta_name_published_year publication_year,       \r\n" + 
							"									   					 cast ((select array_to_json(array_agg(row_to_json(t))) from (select 'spph_id_' || spph_id id, spph_author autor, spph_title titulo,             \r\n" + 
							"							 									   					 	   'data:' || spph_mime || ';base64,' mime, spph_description, spph_alfresco_id from biodiversity.species_photographs sp            \r\n" + 
							"							 									   					 	   where sp.spta_id = st.spta_id and sp.spph_status = true and sp.spph_photograph_general = true and sp.spph_type_photograph = 'GENERAL') t)as text) img_general,        \r\n" + 
							"									   					 cast ((select array_to_json(array_agg(row_to_json(t))) from (select 'spph_id_' || spph_id id, spph_author autor, spph_title titulo,            \r\n" + 
							"							 									   					 	   'data:' || spph_mime || ';base64,' mime, spph_description, spph_type_photograph tipo, spph_alfresco_id from biodiversity.species_photographs sp2            \r\n" + 
							"							 									   					 	   where sp2.spta_id = st.spta_id and sp2.spph_status = true and sp2.spph_photograph_general != true and sp2.spph_type_photograph = 'PARTICULAR') t) as text) img_particular,        \r\n" + 
							"									   					 cast ((select json_object(array( select  hicl_taxon_rank_name       \r\n" + 
							"									   					 				FROM biodiversity.higher_classifications hc       \r\n" + 
							"									   					 				where (st.spta_hierarchy_code LIKE '%;' || hc.hicl_id || ';%' or st.spta_hierarchy_code LIKE hc.hicl_id || ';%')       \r\n" + 
							"									   					 				and  hc.hicl_status = true order by hc.tara_id),        \r\n" + 
							"									   					 				array( select hc.hicl_scientific_name       \r\n" + 
							"									   					 				FROM biodiversity.higher_classifications hc       \r\n" + 
							"									   					 				where (st.spta_hierarchy_code LIKE '%;' || hc.hicl_id || ';%' or st.spta_hierarchy_code LIKE hc.hicl_id || ';%')       \r\n" + 
							"									   					 				and  hc.hicl_status = true order by hc.tara_id))) as text) taxonomia,  spta_specific_infraspecifc_epit,      \r\n" + 
							"									   					 spta_endemic,          					 spta_exotic,  \r\n" + 
							"									   					 spta_alien,      					 spta_native,  \r\n" + 
							"									   					 spta_migratory,       					 spta_ecuador,  \r\n" + 
							"									   					 spta_domestic,       \r\n" + 
							"									   					 cast ((select array_to_json(array_agg(row_to_json(t))) from (select vena_vernacular_name nombre,        \r\n" + 
							"									   					 CASE when tala_name is not null and tala_name != '' then tala_name else 'N/D' end lenguaje,       \r\n" + 
							"									   					 case when etgr_name is not null and etgr_name != '' then etgr_name else 'N/D' end grupo_etnico,        \r\n" + 
							"									   					 case when inso_short_reference is not null and inso_short_reference != '' then inso_short_reference else 'N/D' end citacorta       \r\n" + 
							"									   					 from biodiversity.vernacular_names vn       \r\n" + 
							"									   					 left join biodiversity.ethnic_groups eg on eg.etgr_id =  vn.etgr_id and eg.etgr_status = true       \r\n" + 
							"									   					 left join biodiversity.taxa_languages tl on tl.tala_id = vn.tala_id and tl.tala_status = true       \r\n" + 
							"									   					 left join biodiversity.information_sources inso on inso.inso_id = vn.inso_id and inso_status = true       \r\n" + 
							"									   					 where vn.spta_id = st.spta_id and vena_status = true order by tl.tala_id) t) as text )vernacular_name,       \r\n" + 
							"									   					 seas_morphological_description morphological_description,       \r\n" + 
							"									   					 cast ((select array_to_json(array_agg(row_to_json(t))) from (select spta_scientific_name,spta_scientific_gui  from biodiversity.species_taxa        \r\n" + 
							"									   								 					 where spta_id in (select UNNEST (cast(string_to_array(replace(biodiversity.get_synonyms_specie(st.spta_id),'nd','0'),';') as int[])))) t) as text) sinonimos, \r\n" + 
							"									   					 seas_native_distributive_area native_distributive_area,seas_natural_environment natural_environment,       \r\n" + 
							"									   					 cast (array_to_json(array(select gl.gelo_name from biodiversity.species_taxa_provinces pr inner join        \r\n" + 
							"									   					 geographical_locations gl on pr.province_id = gl.gelo_id and gl.gelo_status = true       \r\n" + 
							"									   					 where pr.sptp_status = true AND pr.spta_id = st.spta_id order by pr.province_id)) as text) AS provincia,       \r\n" + 
							"									   					 cast (array_to_json(array(select pa.sppa_name from  biodiversity.species_protected_areas pa       \r\n" + 
							"									   					 where pa.sppa_status = true AND pa.spta_id = st.spta_id order by pa.sppa_name)) as text) AS areanatural,       \r\n" + 
							"									   					 cast (array_to_json(array(select eco.spec_name from  biodiversity.species_ecosystems eco       \r\n" + 
							"									   					 where eco.spec_status = true AND eco.spta_id = st.spta_id order by eco.spec_name)) as text) AS ecosistemas,       \r\n" + 
							"									   					 seas_introduction_year introduction_year,seas_introduction_reason introduction_reason,       \r\n" + 
							"									   					 cast ((select array_to_json(array_agg(row_to_json(d))) from (select ieas_title title, ieas_author author, ieas_place_introduction place_introduction,       \r\n" + 
							"									   					 to_char(ieas_date_introduction, 'dd-TMMon-yyyy') date_introduction, bigcf.bigc_description type_introduction, cain_name cause_introduction,       \r\n" + 
							"									   					 ieas_description description, bigc.bigc_description cat_invasion,ieas_exotic_in_region exotic_in_region       \r\n" + 
							"									   					 from biodiversity.introduction_exotic_alien_species ieas        \r\n" + 
							"									   					 left join biodiversity.biodiversity_general_catalogs bigcf on bigcf.bigc_id = ieas.bigc_id_form and bigcf.bigc_status=true       \r\n" + 
							"									   					 left join biodiversity.cause_introduction cain on cain.cain_id = ieas.cain_id       \r\n" + 
							"									   					 left join biodiversity.biodiversity_general_catalogs bigc on bigc.bigc_id = ieas.bigc_id and bigc.bigc_status=true       \r\n" + 
							"									   					 where ieas.spta_id = st.spta_id and ieas.ieas_status = true order by ieas_id) d) as text) proy_introduccion,       \r\n" + 
							"									   					 cast ( array_to_json(array(select sptaf.spta_scientific_name from biodiversity.species_taxa sptaf inner join biodiversity.affected_species afsp on       \r\n" + 
							"									   					 afsp.spta_id_affected = sptaf.spta_id and afsp_status=true and sptaf.spta_status=true       \r\n" + 
							"									   					 where afsp.seas_id=seas.seas_id)) as text) affected_species,       \r\n" + 
							"									   					 seas_ecological_impact ecological_impact, seas_economic_impact economic_impact, seas_social_impact social_impact,       \r\n" + 
							"									   					 seas_health_impact health_impact,       \r\n" + 
							"									   					 cast ( array_to_json(array(select cadi_name from biodiversity.catalog_dispersion cadi inner join biodiversity.dispersion_exotic_alien_species deas on       \r\n" + 
							"									   					 deas.cadi_id = cadi.cadi_id and deas_status=true and cadi_status=true       \r\n" + 
							"									   					 where deas.seas_id=seas.seas_id)) as text) dispersion_features,       \r\n" + 
							"									   					 cast (array_to_json(array(select crep_name from biodiversity.catalog_reproductions crep inner join biodiversity.reproduction_exotic_alien_species reas on       \r\n" + 
							"									   					 reas.crep_id = crep.crep_id and reas_status=true and crep_status=true       \r\n" + 
							"									   					 where reas.seas_id=seas.seas_id)) as text) reproduction_features,       \r\n" + 
							"									   					 cast (array_to_json( array(select cdie_name from biodiversity.catalog_diets cdie inner join biodiversity.diet_exotic_alien_species diea on       \r\n" + 
							"									   					 diea.cdie_id = cdie.cdie_id and diea_status=true and cdie_status=true       \r\n" + 
							"									   					 where diea.seas_id=seas.seas_id)) as text) diet_features,       \r\n" + 
							"									   					 cast (array_to_json(array(select bigc_description from biodiversity.biodiversity_general_catalogs bigc inner join biodiversity.dispersion_vectors dive on       \r\n" + 
							"									   					 dive.bigc_id = bigc.bigc_id and dive_status=true and bigc_status=true       \r\n" + 
							"									   					 where dive.seas_id=seas.seas_id)) as text) dispersion_vectors_features,       \r\n" + 
							"									   					 cast (array_to_json(array(select bigc_description from biodiversity.biodiversity_general_catalogs bigc inner join biodiversity.dispersion_routes diro on       \r\n" + 
							"									   					 diro.bigc_id = bigc.bigc_id and diro_status=true and bigc_status=true       \r\n" + 
							"									   					 where diro.seas_id=seas.seas_id)) as text) dispersion_routes_features,       \r\n" + 
							"									   					 cast (array_to_json(array(select cabf_name from biodiversity.catalog_biological_forms cabf inner join biodiversity.biological_forms bifo on       \r\n" + 
							"									   					 bifo.cabf_id = cabf.cabf_id and bifo_status=true and cabf_status=true       \r\n" + 
							"									   					 where bifo.seas_id=seas.seas_id)) as text) biological_forms_features,       \r\n" + 
							"									   					 cast ( array_to_json(array(select caus_name ||': '|| ueas_description from biodiversity.catalog_uses caus inner join biodiversity.uses_exotic_alien_species ueas on       \r\n" + 
							"									   					 ueas.caus_id = caus.caus_id and ueas_status=true and caus_status=true       \r\n" + 
							"									   					 where ueas.seas_id=seas.seas_id)) as text) uses_features,       \r\n" + 
							"									   					 cast (array_to_json(array(select bigc_description || ': ' || inen_description from biodiversity.biodiversity_general_catalogs bigc inner join biodiversity.invasion_environments inen on       \r\n" + 
							"									   					 inen.bigc_id = bigc.bigc_id and inen_status=true and bigc_status=true       \r\n" + 
							"									   					 where inen.seas_id=seas.seas_id)) as text) invasion_environments_features,       \r\n" + 
							"									   					 seas_physical_control physical_control, seas_chemical_control chemical_control, seas_biologic_control biologic_control,       \r\n" + 
							"									   					 seas_prevention_measure prevention_measure, seas_risk_analysis risk_analysis,        \r\n" + 
							"									   					 cast ((select array_to_json(array_agg(row_to_json(t))) from (select bigc.bigc_id id,bigc_description grupo,                \r\n" + 
							"							 						 								 					 (select array_to_json(array_agg(row_to_json(d))) from (select stis_name nombre,stis_url url, bigc_description,               \r\n" + 
							"							 						 								 					 stis_principal                \r\n" + 
							"							 						 								 					 from biodiversity.species_taxa_information_sources stis where stis.spta_id = st.spta_id and                 \r\n" + 
							"							 						 								 					 stis.stis_status = true and bigc.bigc_id = stis.bigc_id ) d) det_fuentes                \r\n" + 
							"							 									   					 from biodiversity.biodiversity_general_catalogs bigc where bigc.bigc_id in             \r\n" + 
							"							 									   					 (select bigc_id from biodiversity.species_taxa_information_sources stis0 where stis0.spta_id = st.spta_id and stis0.stis_status = true)            \r\n" + 
							"							 									   					 order by bigc_description) t) as text) fuentes,       \r\n" + 
							"									   					 seas_author , seas_publication_year,       \r\n" + 
							"									   					 seas_reviser revisores, seas_editor editores, seas_collaborator colaboradores,       \r\n" + 
							"									   					 (select bcca_value from biodiversity.biodiversity_catalog_parameters where bcca_code = 'sumario.general.access.rights') as access_rights       \r\n" + 
							"									   					 from biodiversity.species_taxa st  \r\n" + 
							"									   					 inner join biodiversity.summaries_exotic_alien_species seas on       \r\n" + 
							"									   					 seas.spta_id = st.spta_id      \r\n" + 
							"									   					 left join biodiversity.species_summaries su on      \r\n" + 
							"									   					 su.spta_id = st.spta_id and spsu_status = true    \r\n" + 
							"									   					 left join biodiversity.taxonomic_status taxstat on  \r\n" + 
							"									   					 taxstat.tast_id = st.tast_id  where seas_status = true ");

					if ((gui != null && !gui.equals("")) && (sName != null && !sName.equals(""))
							&& (rank != null && !rank.equals(""))) {

						if (idRank.equals(1)) {
							sql.append("and spta_hierarchy_code like '");
							sql.append(codigoPadre);
							sql.append(";%;%;%;%;%;%' ");

						} else {
							if (idRank.equals(2)) {
								sql.append("and spta_hierarchy_code like '");
								sql.append(codigoPadre);
								sql.append(";%;%;%;%;%' ");

							} else {
								if (idRank.equals(3)) {
									sql.append("and spta_hierarchy_code like '");
									sql.append(codigoPadre);
									sql.append(";%;%;%;%' ");

								} else {
									if (idRank.equals(4)) {
										sql.append("and spta_hierarchy_code like '");
										sql.append(codigoPadre);
										sql.append(";%;%;%' ");

									} else {
										if (idRank.equals(5)) {
											sql.append("and spta_hierarchy_code like '");
											sql.append(codigoPadre);
											sql.append(";%;%' ");

										} else {
											if (idRank.equals(6)) {
												sql.append("and spta_hierarchy_code like '");
												sql.append(codigoPadre);
												sql.append(";%' ");

											}
										}
									}
								}
							}
						}
					} else {
						if ((gui != null && !gui.equals("")) && (sName != null && !sName.equals(""))
								&& (rank == null || rank.equals(""))) {
							sql.append("and spta_scientific_gui = '");
							sql.append(gui);
							sql.append("' ");
						} else {
							if ((gui != null && !gui.equals("")) && (sName == null || sName.equals(""))
									&& (rank != null && !rank.equals(""))) {

								if (idRank.equals(1)) {
									sql.append("and spta_hierarchy_code like '");
									sql.append(codigoPadre);
									sql.append(";%;%;%;%;%;%' ");

								} else {
									if (idRank.equals(2)) {
										sql.append("and spta_hierarchy_code like '");
										sql.append(codigoPadre);
										sql.append(";%;%;%;%;%' ");

									} else {
										if (idRank.equals(3)) {
											sql.append("and spta_hierarchy_code like '");
											sql.append(codigoPadre);
											sql.append(";%;%;%;%' ");

										} else {
											if (idRank.equals(4)) {
												sql.append("and spta_hierarchy_code like '");
												sql.append(codigoPadre);
												sql.append(";%;%;%' ");

											} else {
												if (idRank.equals(5)) {
													sql.append("and spta_hierarchy_code like '");
													sql.append(codigoPadre);
													sql.append(";%;%' ");

												} else {
													if (idRank.equals(6)) {
														sql.append("and spta_hierarchy_code like '");
														sql.append(codigoPadre);
														sql.append(";%' ");

													}
												}
											}
										}
									}
								}

							} else {
								if ((gui != null && !gui.equals("")) && (sName == null || sName.equals(""))
										&& (rank == null || rank.equals(""))) {
									sql.append("and spta_scientific_gui = '");
									sql.append(gui);
									sql.append("' ");
								} else {
									if ((gui == null || gui.equals("")) && (sName == null || sName.equals(""))
											&& (rank != null && !rank.equals(""))) {
										if (idRank.equals(1)) {
											sql.append("and spta_hierarchy_code like '");
											sql.append(codigoPadre);
											sql.append(";%;%;%;%;%;%' ");

										} else {
											if (idRank.equals(2)) {
												sql.append("and spta_hierarchy_code like '");
												sql.append(codigoPadre);
												sql.append(";%;%;%;%;%' ");

											} else {
												if (idRank.equals(3)) {
													sql.append("and spta_hierarchy_code like '");
													sql.append(codigoPadre);
													sql.append(";%;%;%;%' ");

												} else {
													if (idRank.equals(4)) {
														sql.append("and spta_hierarchy_code like '");
														sql.append(codigoPadre);
														sql.append(";%;%;%' ");

													} else {
														if (idRank.equals(5)) {
															sql.append("and spta_hierarchy_code like '");
															sql.append(codigoPadre);
															sql.append(";%;%' ");

														} else {
															if (idRank.equals(6)) {
																sql.append("and spta_hierarchy_code like '");
																sql.append(codigoPadre);
																sql.append(";%' ");

															}
														}
													}
												}
											}
										}
									} else {
										if ((gui == null || gui.equals("")) && (sName != null && !sName.equals(""))
												&& (rank != null && !rank.equals(""))) {
											if (idRank.equals(1)) {
												sql.append("and spta_hierarchy_code like '");
												sql.append(codigoPadre);
												sql.append(";%;%;%;%;%;%' ");

											} else {
												if (idRank.equals(2)) {
													sql.append("and spta_hierarchy_code like '");
													sql.append(codigoPadre);
													sql.append(";%;%;%;%;%' ");

												} else {
													if (idRank.equals(3)) {
														sql.append("and spta_hierarchy_code like '");
														sql.append(codigoPadre);
														sql.append(";%;%;%;%' ");

													} else {
														if (idRank.equals(4)) {
															sql.append("and spta_hierarchy_code like '");
															sql.append(codigoPadre);
															sql.append(";%;%;%' ");

														} else {
															if (idRank.equals(5)) {
																sql.append("and spta_hierarchy_code like '");
																sql.append(codigoPadre);
																sql.append(";%;%' ");

															} else {
																if (idRank.equals(6)) {
																	sql.append("and spta_hierarchy_code like '");
																	sql.append(codigoPadre);
																	sql.append(";%' ");

																}
															}
														}
													}
												}
											}
										} else {
											if ((gui == null || gui.equals("")) && (sName != null && !sName.equals(""))
													&& (rank == null || rank.equals(""))) {
												sql.append("and spta_scientific_name = '");
												sql.append(sName);
												sql.append("' ");
											} 
										}
									}
								}
							}
						}
					}

					if (lrEC != null && !lrEC.equals("")) {
						sql.append("and UPPER(rlec_initial) in ('");
						String[] iniciales = lrEC.split(",");
						if (iniciales.length > 1) {
							for (int i = 0; i < iniciales.length; i++) {
								if (i != (iniciales.length - 1)) {
									sql.append(iniciales[i]);
									sql.append("','");
								} else {
									sql.append(iniciales[i]);
									sql.append(") ");
								}
							}
						} else {
							sql.append("and UPPER(rlec_initial) = '");
							sql.append(lrEC.toUpperCase());
							sql.append("' ");
						}
					}

					if (cites != null && !cites.equals("")) {

						String[] iniciales = cites.split(",");
						if (iniciales.length > 1) {
							sql.append("and cite_id in ('");
							Integer citeId = 0;
							for (int i = 0; i < iniciales.length; i++) {
								if (iniciales[i].equalsIgnoreCase("I") || iniciales[i].equals("1")) {
									citeId = 1;
								}
								if (iniciales[i].equalsIgnoreCase("II") || iniciales[i].equals("2")) {
									citeId = 2;
								}

								if (iniciales[i].equalsIgnoreCase("III") || iniciales[i].equals("3")) {
									citeId = 3;
								}
								if (i != (iniciales.length - 1)) {
									sql.append(citeId);
									sql.append("','");
								} else {
									sql.append(citeId);
									sql.append(") ");
								}
							}
						} else {
							Integer citeId = 0;
							if (cites.equalsIgnoreCase("I") || cites.equals("1")) {
								citeId = 1;
							}
							if (cites.equalsIgnoreCase("II") || cites.equals("2")) {
								citeId = 2;
							}

							if (cites.equalsIgnoreCase("III") || cites.equals("3")) {
								citeId = 3;
							}

							sql.append("and cite_id = ");
							sql.append(citeId);
							sql.append(" ");
						}
					}

					if (status != null && !status.equals("")) {

						String[] iniciales = status.split(",");
						if (iniciales.length > 1) {
							sql.append("and cite_id in ('");

							for (int i = 0; i < iniciales.length; i++) {
								if (iniciales[i].equalsIgnoreCase("end") || iniciales[i].equals("e")) {
									sql.append("and spta_endemic = true ");

								}
								if (iniciales[i].equalsIgnoreCase("exo") || iniciales[i].equals("x")) {
									sql.append("and spta_exotic = true ");
								}

								if (iniciales[i].equalsIgnoreCase("dom") || iniciales[i].equals("d")) {
									sql.append("and spta_domestic = true ");
								}

								if (iniciales[i].equalsIgnoreCase("cul") || iniciales[i].equals("c")) {
									sql.append("and spta_domestic = true ");
								}

								if (iniciales[i].equalsIgnoreCase("nat") || iniciales[i].equals("n")) {
									sql.append("and spta_native = true ");
								}

								if (iniciales[i].equalsIgnoreCase("mig") || iniciales[i].equals("m")) {
									sql.append("and spta_migratory = true ");
								}

								if (iniciales[i].equalsIgnoreCase("inv") || iniciales[i].equals("i")) {
									sql.append("and spta_alien = true ");
								}
							}
						} else {

							if (status.equalsIgnoreCase("end") || status.equals("e")) {
								sql.append("and spta_endemic = true ");

							}
							if (status.equalsIgnoreCase("exo") || status.equals("x")) {
								sql.append("and spta_exotic = true ");
							}

							if (status.equalsIgnoreCase("dom") || status.equals("d")) {
								sql.append("and spta_domestic = true ");
							}

							if (status.equalsIgnoreCase("cul") || status.equals("c")) {
								sql.append("and spta_domestic = true ");
							}

							if (status.equalsIgnoreCase("nat") || status.equals("n")) {
								sql.append("and spta_native = true ");
							}

							if (status.equalsIgnoreCase("mig") || status.equals("m")) {
								sql.append("and spta_migratory = true ");
							}

							if (status.equalsIgnoreCase("inv") || status.equals("i")) {
								sql.append("and spta_alien = true ");
							}

						}
					}
					
					List<Object[]> resu = new ArrayList<Object[]>();

					Query q = super.getEntityManager().createNativeQuery(sql.toString());
					resu = (q.getResultList());
					if (!resu.isEmpty()) {
						for (Object[] row : resu) {
							SumExSpResult sumarioE = new SumExSpResult();
							Metadata meta = new Metadata();
							meta.setComment(
									"El Ministerio del Ambiente y Agua se reserva el derecho de modificar sin previo aviso el listado de especies con información disponible, el contenido del sumario, y/o la estructura del servicio web. Para información actualizada se puede consultar en http://suia.ambiente.gob.ec");
							Author author = new Author();
							author.setComment(
									"Autor. Persona o personas que contribuyeron con su conocimiento intelectual y de manera significativa al contenido del sumario");
							if (row[49] != null) {
								author.setValue(row[49].toString());
							}
							Reviewer reviewer = new Reviewer();
							reviewer.setComment(
									"Revisor. Persona o personas que revisaron o examinaron cuidadosamente el contenido del sumario");
							if (row[51] != null) {
								reviewer.setValue(row[51].toString());
							}
							Editor editor = new Editor();
							editor.setComment(
									"Editor. Persona o personas que contribuyeron a corregir y adaptar el contenido del sumario para uso del público objetivo");
							if (row[52] != null) {
								editor.setValue(row[52].toString());
							}
							Collaborator collaborator = new Collaborator();
							collaborator.setComment(
									"Colaborador. Persona que trabajó con el o los autores en la realización del sumario sin aportar conocimiento sino esfuerzo en sistematizar y digitalizar la información");
							if (row[53] != null) {
								collaborator.setValue(row[53].toString());
							}
							PublicationYear publicationYear = new PublicationYear();
							publicationYear.setComment("Año de publicación del sumario de la especie");
							if (row[50] != null) {
								publicationYear.setValue(row[50].toString());
							}
							Language language = new Language();
							language.setComment(
									"Idioma utilizado para escribir la información del sumario de la especie, se utiliza el código ISO 639-2 (https://www.loc.gov/standards/iso639-2/php/code_list.php)");
							language.setValue("SPA");
							RightsHolder rightsHolder = new RightsHolder();
							rightsHolder.setComment(
									"Nombre de la Institución con los derechos de propiedad o administracion del sumario");
							rightsHolder.setValue("Todos los derechos reservados - Ministerio del Ambiente y Agua, 2020");
							AccessRights accessRights = new AccessRights();
							accessRights.setComment(
									"Nombre de la Institución con los derechos de propiedad o administracion del sumario");
							if (row[54] != null) {
								accessRights.setValue(row[54].toString());
							}
							License license = new License();
							license.setComment(
									"Declaración de permiso oficial para hacer uso del sumario y su información");
							license.setValue("https://creativecommons.org/licenses/by-sa/4.0/");
							Version version = new Version();
							version.setComment("Número y fecha de la versión actual");
							version.setValue("01.2020");
							meta.setAccessRights(accessRights);
							meta.setAuthor(author);
							meta.setCollaborator(collaborator);
							meta.setEditor(editor);
							meta.setLanguage(language);
							meta.setLicense(license);
							meta.setPublicationYear(publicationYear);
							meta.setReviewer(reviewer);
							meta.setRightsHolder(rightsHolder);
							meta.setVersion(version);
							sumarioE.setMetadata(meta);
							TaxonExo taxon = new TaxonExo();
							Gui guiS = new Gui();
							guiS.setComment(
									"Global Unique Identifier. Código único nacional del Catálogo Nacional de Objetos Biológicos");
							if (row[5] != null) {
								guiS.setValue(row[5].toString());
							}
							Kingdom kingdom = new Kingdom();
							kingdom.setComment("El nombre científico completo del reino en el que se clasifica el taxón");
							Phylum phylum = new Phylum();
							phylum.setComment("El nombre científico completo del phylum en el que se clasifica el taxón");
							Clas clas = new Clas();
							clas.setComment("El nombre científico completo de la clase en el que se clasifica el taxón");
							Order order = new Order();
							order.setComment("El nombre científico completo del orden en el que se clasifica el taxón");
							Family family = new Family();
							family.setComment(
									"El nombre científico completo de la familia en el que se clasifica el taxón");
							Genus genus = new Genus();
							genus.setComment("El nombre científico completo del género en el que se clasifica el taxón");

							JSONParser parser = new JSONParser();
							try {
								if (row[10] != null) {
									JSONObject json = (JSONObject) parser.parse(row[10].toString());
									if(json.get("Reino")!=null)
									{
									kingdom.setValue(json.get("Reino").toString());
									}
									if(json.get("Phylum")!=null)
									{
									phylum.setValue(json.get("Phylum").toString());
									}
									if(json.get("Clase")!=null)
									{
									clas.setValue(json.get("Clase").toString());
									}
									if(json.get("Orden")!=null)
									{
									order.setValue(json.get("Orden").toString());
									}
									if(json.get("Familia")!=null)
									{
									family.setValue(json.get("Familia").toString());
									}
									if(json.get("Género")!=null)
									{
									genus.setValue(json.get("Género").toString());
									}
								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							SpecificInfraspecificEpithet specificInfraspecificEpithet = new SpecificInfraspecificEpithet();
							specificInfraspecificEpithet.setComment(
									"El nombre científico completo del epíteto específico y el epíteto infraespecífico (de ser el caso) en el rango taxonómico más bajo");
							if (row[11] != null) {
								specificInfraspecificEpithet.setValue(row[11].toString());
							}
							ScientificName scientificName = new ScientificName();
							scientificName.setComment("El nombre científico completo, con la autoría y año");
							if (row[4] != null) {
								scientificName.setValue(row[4].toString());
							}
							TaxonRank taxonRank = new TaxonRank();
							taxonRank.setComment(
									"El rango taxonómico del nombre más específico provisto en el campo scientificName. Se usa vocabulario controlado (Reino, Phylum, Clase, Orden, Familia, Genero, especie, subespecie, variedad, forma)");
							if (row[3] != null) {
								taxonRank.setValue(row[3].toString());
							}
							ScientificNameAuthorship scientificNameAuthorship = new ScientificNameAuthorship();
							scientificNameAuthorship.setComment("Información de autoría del nombre científico");
							if (row[6] != null) {
								scientificNameAuthorship.setValue(row[6].toString());
							}
							NamePublishedInYear namePublishedInYear = new NamePublishedInYear();
							namePublishedInYear
									.setComment("El año de cuatro dígitos en el que se publicó el nombre científico");
							if (row[7] != null) {
								namePublishedInYear.setValue(row[7].toString());
							}
							InEcuador inEcuador = new InEcuador();
							inEcuador.setComment("Es una especie registrada en Ecuador");
							if (row[17] != null) {
								inEcuador.setValue((Boolean) row[17]);
							}
							IsNative isNative = new IsNative();
							isNative.setComment("Es una especie nativa");
							if (row[15] != null) {
								isNative.setValue((Boolean) row[15]);
							}
							IsEndemic isEndemic = new IsEndemic();
							isEndemic.setComment("Es una especie endémica");
							if (row[12] != null) {
								isEndemic.setValue((Boolean) row[12]);
							}
							IsExotic isExotic = new IsExotic();
							isExotic.setComment("Es una especie exótica");
							if (row[13] != null) {
								isExotic.setValue((Boolean) row[13]);
							}
							IsInvasive isInvasive = new IsInvasive();
							isInvasive.setComment("Es una especie invasora");
							if (row[14] != null) {
								isExotic.setValue((Boolean) row[14]);
							}
							IsDomestic isDomestic = new IsDomestic();
							isDomestic.setComment(
									"En caso de flora y hongos, el término utilizado es cultivada. Doméstico aplica para animales");
							if (row[18] != null) {
								isDomestic.setValue((Boolean) row[18]);
							}
							IsMigratory isMigratory = new IsMigratory();
							if (row[16] != null) {
								isMigratory.setValue((Boolean) row[16]);
							}
							TaxonomicStatus taxonomicStatus = new TaxonomicStatus();
							if (row[2] != null) {
								taxonomicStatus.setValue(row[2].toString());
							}
							IsCorrectName isCorrectName = new IsCorrectName();
							if (row[0] != null) {
								isCorrectName.setValue((Boolean) row[0]);
							}
							CorrectScientificName correctScientificName = new CorrectScientificName();
							if (row[1] != null) {
								correctScientificName.setValue(row[1].toString());
							}
							
							taxon.setClas(clas);
							taxon.setCorrectScientificName(correctScientificName);
							taxon.setFamily(family);
							taxon.setGenus(genus);
							taxon.setGui(guiS);
							taxon.setInEcuador(inEcuador);
							taxon.setIsCorrectName(isCorrectName);
							taxon.setIsDomestic(isDomestic);
							taxon.setIsEndemic(isEndemic);
							taxon.setIsExotic(isExotic);
							taxon.setIsInvasive(isInvasive);
							taxon.setIsNative(isNative);
							taxon.setKingdom(kingdom);
							taxon.setNamePublishedInYear(namePublishedInYear);
							taxon.setOrder(order);
							taxon.setPhylum(phylum);
							taxon.setScientificName(scientificName);
							taxon.setScientificNameAuthorship(scientificNameAuthorship);
							taxon.setSpecificInfraspecificEpithet(specificInfraspecificEpithet);
							taxon.setTaxonomicStatus(taxonomicStatus);
							taxon.setTaxonRank(taxonRank);
							sumarioE.setTaxon(taxon);
							DescriptionExotica descripcion= new DescriptionExotica();
							MorphologicalDescription morphological = new MorphologicalDescription();
							if (row[20] != null) {
								morphological.setValue(row[20].toString());
							}
							
							descripcion.setMorphologicalDescription(morphological);
							
							sumarioE.setDescription(descripcion);
							
							Characteristics characteristics= new Characteristics();
							Dispersion dispersion = new Dispersion();
							List<DispersionValue> dispersionList= new ArrayList<>();
							JSONParser parserArt = new JSONParser();
							try {
								if (row[35] != null) {

									JSONArray json1 = (JSONArray) parserArt.parse(row[35].toString());
									for (int i = 0; i < json1.size(); i++) {
										DispersionValue art = new DispersionValue();
										String jsonObj = (String) (json1.get(i));
										art.setValue(jsonObj);
										dispersionList.add(art);
									}

								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							dispersion.setDispersion(dispersionList);
							characteristics.setDispersion(dispersion);
							
							Diet diet = new Diet();
							List<DietValue> dietList= new ArrayList<>();
							JSONParser parserDiet = new JSONParser();
							try {
								if (row[37] != null) {

									JSONArray json1 = (JSONArray) parserDiet.parse(row[37].toString());
									for (int i = 0; i < json1.size(); i++) {
										DietValue art = new DietValue();
										String jsonObj = (String) (json1.get(i));
										art.setValue(jsonObj);
										dietList.add(art);
									}

								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							diet.setDiet(dietList);
							characteristics.setDiet(diet);
							
							DispersionRoutes dispersionRoutes= new DispersionRoutes();
							List<DispersionRoutesValue> dispersionRoutesList= new ArrayList<>();
							
							JSONParser parserDispRout = new JSONParser();
							try {
								if (row[39] != null) {

									JSONArray json1 = (JSONArray) parserDispRout.parse(row[39].toString());
									for (int i = 0; i < json1.size(); i++) {
										DispersionRoutesValue art = new DispersionRoutesValue();
										String jsonObj = (String) (json1.get(i));
										art.setValue(jsonObj);
										dispersionRoutesList.add(art);
									}

								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							dispersionRoutes.setDispersionRoutes(dispersionRoutesList);
							characteristics.setDispersionRoutes(dispersionRoutes);
							
							Reprodution reproduction= new Reprodution();
							List<ReproductionValue> reproductionList= new ArrayList<>();
							
							JSONParser parserReproduction = new JSONParser();
							try {
								if (row[36] != null) {

									JSONArray json1 = (JSONArray) parserReproduction.parse(row[36].toString());
									for (int i = 0; i < json1.size(); i++) {
										ReproductionValue art = new ReproductionValue();
										String jsonObj = (String) (json1.get(i));
										art.setValue(jsonObj);
										reproductionList.add(art);
									}

								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							reproduction.setReproduction(reproductionList);
							characteristics.setReproduction(reproduction);
							
							DispertionVectors dispertionVectors= new DispertionVectors();
							List<DispertionVectorsValue> dispertionVectorsList= new ArrayList<>();
							
							JSONParser parserVector = new JSONParser();
							try {
								if (row[38] != null) {

									JSONArray json1 = (JSONArray) parserVector.parse(row[38].toString());
									for (int i = 0; i < json1.size(); i++) {
										DispertionVectorsValue art = new DispertionVectorsValue();
										String jsonObj = (String) (json1.get(i));
										art.setValue(jsonObj);
										dispertionVectorsList.add(art);
									}

								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							dispertionVectors.setDispertionVectors(dispertionVectorsList);
							characteristics.setDispertionVectors(dispertionVectors);
							
							BiologicalForm biologicalForm= new BiologicalForm();
							List<BiologicalFormValue> biologicalFormList= new ArrayList<>();
							
							JSONParser parserBioFor = new JSONParser();
							try {
								if (row[40] != null) {

									JSONArray json1 = (JSONArray) parserBioFor.parse(row[40].toString());
									for (int i = 0; i < json1.size(); i++) {
										BiologicalFormValue art = new BiologicalFormValue();
										String jsonObj = (String) (json1.get(i));
										art.setValue(jsonObj);
										biologicalFormList.add(art);
									}

								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							biologicalForm.setBiologicalForm(biologicalFormList);
							characteristics.setBiologicalForm(biologicalForm);
							
							UsesExo uses = new UsesExo();
							List<UsesValue> usesList= new ArrayList<>();
							
							JSONParser parserUse = new JSONParser();
							try {
								if (row[41] != null) {

									JSONArray json1 = (JSONArray) parserUse.parse(row[41].toString());
									for (int i = 0; i < json1.size(); i++) {
										UsesValue art = new UsesValue();
										String jsonObj = (String) (json1.get(i));
										art.setValue(jsonObj);
										usesList.add(art);
									}

								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							uses.setUses(usesList);
							characteristics.setUses(uses);
							
							PreferentialInvasionEnvironments preferentialInvasionEnvironments = new PreferentialInvasionEnvironments();
							List<PreferentialInvasionEnvironmentsValue> preferentialInvasionEnvironmentsList= new ArrayList<>();
							
							JSONParser parserPref = new JSONParser();
							try {
								if (row[42] != null) {

									JSONArray json1 = (JSONArray) parserPref.parse(row[42].toString());
									for (int i = 0; i < json1.size(); i++) {
										PreferentialInvasionEnvironmentsValue art = new PreferentialInvasionEnvironmentsValue();
										String jsonObj = (String) (json1.get(i));
										art.setValue(jsonObj);
										preferentialInvasionEnvironmentsList.add(art);
									}

								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							preferentialInvasionEnvironments.setPreferentialInvasionEnvironments(preferentialInvasionEnvironmentsList);
							characteristics.setPreferentialInvasionEnvironments(preferentialInvasionEnvironments);
							sumarioE.setCharacteristics(characteristics);
							
							Impacts impacts= new Impacts();
							SpeciesAffected speciesAffected= new SpeciesAffected();
							List<SpeciesAffectedValue> speciesAffectedList= new ArrayList<>();
							
							JSONParser parserSpecAf = new JSONParser();
							try {
								if (row[30] != null) {

									JSONArray json1 = (JSONArray) parserSpecAf.parse(row[30].toString());
									for (int i = 0; i < json1.size(); i++) {
										SpeciesAffectedValue art = new SpeciesAffectedValue();
										String jsonObj = (String) (json1.get(i));
										art.setValue(jsonObj);
										speciesAffectedList.add(art);
									}

								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							speciesAffected.setSpeciesAffected(speciesAffectedList);
							impacts.setSpeciesAffected(speciesAffected);
							
							EcologicalImpact ecologicalImpact = new EcologicalImpact();
							if (row[31] != null) {
								ecologicalImpact.setValue(row[31].toString());
							}
							
							impacts.setEcologicalImpact(ecologicalImpact);
							EconomicImpact economicImpact = new EconomicImpact();
							if (row[32] != null) {
								economicImpact.setValue(row[32].toString());
							}
							
							impacts.setEconomicImpact(economicImpact);
							SocialImpact socialImpact = new SocialImpact();
							if (row[33] != null) {
								socialImpact.setValue(row[33].toString());
							}
							
							impacts.setSocialImpact(socialImpact);
							
							HealthImpact healthImpac = new HealthImpact();
							if (row[34] != null) {
								healthImpac.setValue(row[34].toString());
							}
							
							impacts.setHealthImpact(healthImpac);
							sumarioE.setImpacts(impacts);
							
												
							List<VernacularN> listVern = new ArrayList<>();
							JSONParser parserVern = new JSONParser();
							try {
								if (row[19] != null) {

									JSONArray json1 = (JSONArray) parserVern.parse(row[19].toString());
									for (int i = 0; i < json1.size(); i++) {
										VernacularN art = new VernacularN();
										VernacularName verName = new VernacularName();
										Locality locality = new Locality();
										LanguageV languageV = new LanguageV();
										Source source = new Source();
										JSONObject json = (JSONObject) (json1.get(i));
										if(json.get("nombre")!=null)
										{
										verName.setValue(json.get("nombre").toString());
										}
										if(json.get("grupo_etnico")!=null)
										{
										locality.setValue(json.get("grupo_etnico").toString());
										}
										if(json.get("lenguaje")!=null)
										{
										languageV.setValue(json.get("lenguaje").toString());
										}
										if(json.get("citacorta")!=null)
										{
										source.setValue(json.get("citacorta").toString());
										}
										
										art.setLanguage(languageV);
										art.setLocality(locality);
										art.setSource(source);
										art.setVernacularName(verName);
										listVern.add(art);

									}

								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							VernacularNames vNames = new VernacularNames();
							vNames.setVernacularN(listVern);
							sumarioE.setVernacularNames(vNames);
							List<Synonym> listSin = new ArrayList<>();
							JSONParser parserSino = new JSONParser();
							try {
								if (row[21] != null) {

									JSONArray json1 = (JSONArray) parserSino.parse(row[21].toString());
									for (int i = 0; i < json1.size(); i++) {
										Synonym synonym = new Synonym();
										GuiSin guiSin = new GuiSin();
										ScientificNameSin scientificNameSin = new ScientificNameSin();
										JSONObject json = (JSONObject) (json1.get(i));
										if(json.get("spta_scientific_name")!=null)
										{
										guiSin.setValue(json.get("spta_scientific_name").toString());
										}
										if(json.get("spta_scientific_gui")!=null)
										{
										scientificNameSin.setValue(json.get("spta_scientific_gui").toString());
										}
										synonym.setGui(guiSin);
										synonym.setScientificName(scientificNameSin);
										listSin.add(synonym);

									}

								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Synonyms synonyms = new Synonyms();
							synonyms.setSynonym(listSin);
							sumarioE.setSynonyms(synonyms);
							
							Introduction introduction= new Introduction();
														
							IntroductionYear introductionYear = new IntroductionYear();
							if (row[27] != null) {
								introductionYear.setValue(row[27].toString());
							}
							introduction.setIntroductionYear(introductionYear);
							
							IntroductionReason introductionReason = new IntroductionReason();
							if (row[28] != null) {
								introductionReason.setValue(row[28].toString());
							}
							
							introduction.setIntroductionReason(introductionReason);
							
							IntroductionProject introductionProject= new IntroductionProject();
							List<IntroductionProjectValue> listInttr = new ArrayList<>();
							JSONParser parserIntr = new JSONParser();
							try {
								if (row[29] != null) {

									JSONArray json1 = (JSONArray) parserIntr.parse(row[29].toString());
									for (int i = 0; i < json1.size(); i++) {
										IntroductionProjectValue art = new IntroductionProjectValue();
										TypeInt typeInt = new TypeInt();
										IntroductionCause introductionCause = new IntroductionCause();
										EventDate eventDate = new EventDate();
										LocalityExo locality = new LocalityExo();
										AuthorExo authorExo = new AuthorExo();
										TitleExo title= new TitleExo();
										InvasionRiskCategory invasionRiskCategory= new InvasionRiskCategory();
										IsExoticInRegion isExoticInRegion= new IsExoticInRegion();
										IntroductionDescription introductionDescription = new IntroductionDescription();
										JSONObject json = (JSONObject) (json1.get(i));
										if(json.get("type_introduction")!=null)
										{
										typeInt.setValue(json.get("type_introduction").toString());
										}
										if(json.get("cause_introduction")!=null)
										{
										introductionCause.setValue(json.get("cause_introduction").toString());
										}
										if(json.get("date_introduction")!=null)
										{
										eventDate.setValue(json.get("date_introduction").toString());
										}
										if(json.get("place_introduction")!=null)
										{
										locality.setValue(json.get("place_introduction").toString());
										}
										if(json.get("author")!=null)
										{
										authorExo.setValue(json.get("author").toString());
										}
										if(json.get("title")!=null)
										{
										title.setValue(json.get("title").toString());
										}
										if(json.get("cat_invasion")!=null)
										{
										invasionRiskCategory.setValue(json.get("cat_invasion").toString());
										}
										if(json.get("exotic_in_region")!=null)
										{
										isExoticInRegion.setValue((Boolean)(json.get("exotic_in_region")));
										}
										if(json.get("description")!=null)
										{
										introductionDescription.setValue(json.get("description").toString());
										}
										art.setAuthor(authorExo);
										art.setEventDate(eventDate);
										art.setIntroductionCause(introductionCause);
										art.setIntroductionDescription(introductionDescription);
										art.setInvasionRiskCategory(invasionRiskCategory);
										art.setIsExoticInRegion(isExoticInRegion);
										art.setLocality(locality);
										art.setTitle(title);
										art.setType(typeInt);
										listInttr.add(art);

									}

								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							introductionProject.setIntroductionProject(listInttr);
							introduction.setIntroductionProjects(introductionProject);
							
							sumarioE.setIntroduction(introduction);
							
							Control control = new Control();
							PhysicalControl physicalControl= new PhysicalControl();
							
							if (row[43] != null) {
								physicalControl.setValue(row[43].toString());
							}
							
							control.setPhysicalControl(physicalControl);
							
							
							ChemicalControl chemicalControl= new ChemicalControl();
							
							if (row[44] != null) {
								chemicalControl.setValue(row[44].toString());
							}
							
							control.setChemicalControl(chemicalControl);
							
							BiologicalControl biologicalControl= new BiologicalControl();
							if (row[45] != null) {
								biologicalControl.setValue(row[45].toString());
							}
							
							control.setBiologicalControl(biologicalControl);
							
							PreventionMeasure preventionMeasure = new PreventionMeasure();
							
							if (row[46] != null) {
								preventionMeasure.setValue(row[46].toString());
							}
							
							control.setPreventionMeasure(preventionMeasure);
							
							RiskAnalysis riskAnalysis = new RiskAnalysis();
							
							if (row[47] != null) {
								riskAnalysis.setValue(row[47].toString());
							}
							
							control.setRiskAnalysis(riskAnalysis);
							
							sumarioE.setControl(control);
							
													
							DistributionExo distribution = new DistributionExo();
							NativeDistributionArea nativeDistributionArea= new NativeDistributionArea();
							if (row[22] != null) {
								nativeDistributionArea.setValue(row[22].toString());
							}
							
							distribution.setNativeDistributionArea(nativeDistributionArea);
							NaturalEnvironment naturalEnvironment= new NaturalEnvironment();
							if (row[23] != null) {
								naturalEnvironment.setValue(row[23].toString());
							}
							
							distribution.setNaturalEnvironment(naturalEnvironment);
							
							Provinces provinces = new Provinces();
							ProtectedAreas protectedAreas = new ProtectedAreas();
							Ecosystems ecosystems = new Ecosystems();
							
							List<Province> listProv = new ArrayList<>();
							JSONParser parserProv = new JSONParser();
							try {
								if (row[24] != null) {

									JSONArray json1 = (JSONArray) parserProv.parse(row[24].toString());
									for (int i = 0; i < json1.size(); i++) {
										Province prov = new Province();
										String jsonObj = (String) (json1.get(i));
										prov.setValue(jsonObj);
										listProv.add(prov);
									}

								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							provinces.setProvince(listProv);
							List<ProtectedArea> listProt = new ArrayList<>();
							JSONParser parserProt = new JSONParser();
							try {
								if (row[25] != null) {

									JSONArray json1 = (JSONArray) parserProt.parse(row[25].toString());
									for (int i = 0; i < json1.size(); i++) {
										ProtectedArea protect = new ProtectedArea();
										String jsonObj = (String) (json1.get(i));
										protect.setValue(jsonObj);
										listProt.add(protect);
									}

								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							protectedAreas.setProtectedArea(listProt);
							List<Ecosystem> listEcos = new ArrayList<>();
							JSONParser parserEcos = new JSONParser();
							try {
								if (row[26] != null) {

									JSONArray json1 = (JSONArray) parserEcos.parse(row[26].toString());
									for (int i = 0; i < json1.size(); i++) {
										Ecosystem ecos = new Ecosystem();
										String jsonObj = (String) (json1.get(i));
										ecos.setValue(jsonObj);
										listEcos.add(ecos);
									}

								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							ecosystems.setEcosystem(listEcos);
							distribution.setProvinces(provinces);
							distribution.setProtectedAreas(protectedAreas);
							distribution.setEcosystems(ecosystems);
							sumarioE.setDistribution(distribution);
							LiteratureReferences literatureReferences= new LiteratureReferences();
							List<LiteratureReference> liteList=new ArrayList<>();
							JSONParser parserF = new JSONParser();
							try {
								if (row[48] != null) {
									JSONArray json = (JSONArray) parserF.parse(row[48].toString());
									for (int i = 0; i < json.size(); i++) {
										JSONObject jsonObjeto1 = (JSONObject) (json.get(i));
										JSONArray json1 = (JSONArray) parserF.parse(jsonObjeto1.get("det_fuentes").toString());
										for (int k = 0; k < json1.size(); k++) {
											LiteratureReference lite = new LiteratureReference();
											Type typeL = new Type();
											Url url = new Url();
											Principal principal= new Principal();
											BibliographicCitation bibliographicCitation = new BibliographicCitation();
											JSONObject jsonObjeto = (JSONObject) (json1.get(k));
											if(jsonObjeto.get("bigc_description")!=null)
											{
											typeL.setValue(jsonObjeto.get("bigc_description").toString());
											}
											if(jsonObjeto.get("url")!=null)
											{
											url.setValue(jsonObjeto.get("url").toString());
											}
											if(jsonObjeto.get("stis_principal")!=null)
											{
											principal.setValue(jsonObjeto.get("stis_principal").toString());
											}
											if(jsonObjeto.get("nombre")!=null)
											{
											bibliographicCitation.setValue(jsonObjeto.get("nombre").toString());
											}
											lite.setBibliographicCitation(bibliographicCitation);
											lite.setPrincipal(principal);
											lite.setUrl(url);
											lite.setType(typeL);
											liteList.add(lite);
										}
									}
									
									
								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							literatureReferences.setLiteratureReference(liteList);
							sumarioE.setLiteratureReferences(literatureReferences);
							Images images= new Images();
							List<Image> imageList=new ArrayList<>();
							JSONParser parserI = new JSONParser();
							try {
								if (row[8] != null) {
									JSONArray json1 = (JSONArray) parserI.parse(row[8].toString());
									for (int i = 0; i < json1.size(); i++) {
										Image image = new Image();
										TypeIma typeI = new TypeIma();
										Title title = new Title();
										Creator creator= new Creator();
										DescriptionIma descriptionI = new DescriptionIma();
										JSONObject jsonObjeto = (JSONObject) (json1.get(i));
										typeI.setValue("Principal");
										if(jsonObjeto.get("titulo")!=null)
										{
										title.setValue(jsonObjeto.get("titulo").toString());
										}
										if(jsonObjeto.get("autor")!=null)
										{
										creator.setValue(jsonObjeto.get("autor").toString());
										}
										if(jsonObjeto.get("spph_description")!=null)
										{
										descriptionI.setValue(jsonObjeto.get("spph_description").toString());
										}
										Identifier iden=new Identifier();
										if(jsonObjeto.get("spph_alfresco_id")!=null)
										{
											byte [] imgArr=getImageAlfresco(jsonObjeto.get("spph_alfresco_id").toString());
											if(imgArr!=null)
											{
												iden.setValue(Base64.encode(imgArr));
											}
										
										}
										image.setIdentifier(iden);
										image.setCreator(creator);
										image.setDescription(descriptionI);
										image.setTitle(title);
										image.setType(typeI);
										imageList.add(image);
									}
									
								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							try {
								if (row[9] != null) {
									JSONArray json1 = (JSONArray) parserI.parse(row[9].toString());
									for (int i = 0; i < json1.size(); i++) {
										Image image = new Image();
										TypeIma typeI = new TypeIma();
										Title title = new Title();
										Creator creator= new Creator();
										DescriptionIma descriptionI = new DescriptionIma();
										JSONObject jsonObjeto = (JSONObject) (json1.get(i));
										typeI.setValue("Secundaria");
										if(jsonObjeto.get("titulo")!=null)
										{
										title.setValue(jsonObjeto.get("titulo").toString());
										}
										if(jsonObjeto.get("autor")!=null)
										{
										creator.setValue(jsonObjeto.get("autor").toString());
										}
										if(jsonObjeto.get("spph_description")!=null)
										{
										descriptionI.setValue(jsonObjeto.get("spph_description").toString());
										}
										Identifier iden=new Identifier();
										if(jsonObjeto.get("spph_alfresco_id")!=null)
										{
											byte [] imgArr=getImageAlfresco(jsonObjeto.get("spph_alfresco_id").toString());
											if(imgArr!=null)
											{
												iden.setValue(Base64.encode(imgArr));
											}
										
										}
										image.setIdentifier(iden);
										image.setCreator(creator);
										image.setDescription(descriptionI);
										image.setTitle(title);
										image.setType(typeI);
										imageList.add(image);
									}
									
								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							
							images.setImage(imageList);
							sumarioE.setImages(images);
							objectResultE.add(sumarioE);

						}
					}
				}
			}

		}
		else
		{
			sql.append(
					"select spta_correct_tax,spta_correct_tax_name,tast_name,spta_taxon_rank_name,spta_scientific_name scientific_name,spta_scientific_gui gui,           \r\n" + 
					"						 								 					 spta_scientific_name_authorship author, spta_name_published_year publication_year,           \r\n" + 
					"						 								 					 cast ((select array_to_json(array_agg(row_to_json(t))) from (select vena_vernacular_name nombre,            \r\n" + 
					"						 								 					 CASE when tala_name is not null and tala_name != '' then tala_name else 'N/D' end lenguaje,           \r\n" + 
					"						 								 					 case when etgr_name is not null and etgr_name != '' then etgr_name else 'N/D' end grupo_etnico,           \r\n" + 
					"						 								 					 case when inso_short_reference is not null and inso_short_reference != '' then inso_short_reference else 'N/D' end citacorta           \r\n" + 
					"						 								 					 from biodiversity.vernacular_names vn           \r\n" + 
					"						 								 					 left join biodiversity.ethnic_groups eg on eg.etgr_id =  vn.etgr_id and eg.etgr_status = true           \r\n" + 
					"						 								 					 left join biodiversity.taxa_languages tl on tl.tala_id = vn.tala_id and tl.tala_status = true           \r\n" + 
					"						 								 					 left join biodiversity.information_sources inso on inso.inso_id = vn.inso_id and inso_status = true           \r\n" + 
					"						 								 					 where vn.spta_id = st.spta_id and vena_status = true order by tl.tala_id) t) as text)vernacular_name,           \r\n" + 
					"						 								 					         \r\n" + 
					"						 								 					 cast ((select array_to_json(array_agg(row_to_json(t))) from (select 'spph_id_' || spph_id id, spph_author autor, spph_title titulo,            \r\n" + 
					"						 								 					 	   'data:' || spph_mime || ';base64,' mime, spph_description from biodiversity.species_photographs sp           \r\n" + 
					"						 								 					 	   where sp.spta_id = st.spta_id and sp.spph_status = true and sp.spph_photograph_general = true and sp.spph_type_photograph='GENERAL') t) as text) img_general,           \r\n" + 
					"						 								 					 cast ((select array_to_json(array_agg(row_to_json(t))) from (select 'spph_id_' || spph_id id, spph_author autor, spph_title titulo,           \r\n" + 
					"						 								 					 	   'data:' || spph_mime || ';base64,' mime , spph_description from biodiversity.species_photographs sp2           \r\n" + 
					"						 								 					 	   where sp2.spta_id = st.spta_id and sp2.spph_status = true and sp2.spph_photograph_general != true and sp2.spph_type_photograph='PARTICULAR') t) as text) img_particular,           \r\n" + 
					"						 								 					 cast ((select array_to_json(array_agg(row_to_json(t))) from (select 'spdm_id_' || spdm_id id, rtrim(spdm_author) autor, spdm_title titulo, spdm_description description,           \r\n" + 
					"						 								 					 	'data:' || spdm_mime || ';base64,' mime,           \r\n" + 
					"						 								 					  case when spdm_type_photograph = 'DISTRIBUCIONUNO' then 1            \r\n" + 
					"						 								 					 	  when spdm_type_photograph = 'DISTRIBUCIONDOS' then 2           \r\n" + 
					"						 								 					  	  when spdm_type_photograph = 'DISTRIBUCIONTRES' then 3 end as orden           \r\n" + 
					"						 								 					 	   from biodiversity.species_distribution_maps dm           \r\n" + 
					"						 								 					 	   where dm.spsu_id = su.spsu_id and dm.spdm_status = true order by orden) t) as text) img_dis_map,           \r\n" + 
					"						 								 					 cast ((select json_object(array( select  hicl_taxon_rank_name           \r\n" + 
					"						 								 					 				FROM biodiversity.higher_classifications hc           \r\n" + 
					"						 								 					 				where (st.spta_hierarchy_code LIKE '%;' || hc.hicl_id || ';%' or st.spta_hierarchy_code LIKE hc.hicl_id || ';%')           \r\n" + 
					"						 								 					 				and  hc.hicl_status = true order by hc.tara_id),            \r\n" + 
					"						 								 					 				array( select hc.hicl_scientific_name           \r\n" + 
					"						 								 					 				FROM biodiversity.higher_classifications hc           \r\n" + 
					"						 								 					 				where (st.spta_hierarchy_code LIKE '%;' || hc.hicl_id || ';%' or st.spta_hierarchy_code LIKE hc.hicl_id || ';%')           \r\n" + 
					"						 								 					 				and  hc.hicl_status = true order by hc.tara_id))) as text) taxonomia,           \r\n" + 
					"						 								 					 spta_specific_infraspecifc_epit epiteto,      \r\n" + 
					"						 								 					 spta_ecuador ,      					 spta_endemic,         \r\n" + 
					"						 								 					 spta_exotic,      					 spta_alien ,         \r\n" + 
					"						 								 					 spta_native,      					 spta_migratory,      \r\n" + 
					"						 								 					 spta_domestic,         \r\n" + 
					"						 								 					 COALESCE(rlec_name,'No evaluado') rlec_name,spta_red_list_uicn uicn_name,           \r\n" + 
					"						 								 					         \r\n" + 
					"						 								 					 cast ((select array_to_json(array_agg(row_to_json(t))) from (select bigc.bigc_id id,bigc_description grupo,           \r\n" + 
					"						 								 					 (select array_to_json(array_agg(row_to_json(d))) from (select stis_name nombre,stis_url url, bigc_description,          \r\n" + 
					"						 								 					 stis_principal           \r\n" + 
					"						 								 					 from biodiversity.species_taxa_information_sources stis where stis.spta_id = st.spta_id and            \r\n" + 
					"						 								 					 stis.stis_status = true and bigc.bigc_id = stis.bigc_id ) d) det_fuentes           \r\n" + 
					"						 								 					 from biodiversity.biodiversity_general_catalogs bigc where bigc.bigc_id in            \r\n" + 
					"						 								 					 (select bigc_id from biodiversity.species_taxa_information_sources stis0 where stis0.spta_id = st.spta_id and stis0.stis_status = true)           \r\n" + 
					"						 								 					 order by bigc_description) t) as text) fuentes,           \r\n" + 
					"						 								 					 spsu_description description,           \r\n" + 
					"						 								 					 cast ((select array_to_json(array_agg(row_to_json(t))) from (select spta_scientific_name,spta_scientific_gui  from biodiversity.species_taxa            \r\n" + 
					"						 								 								 					 where spta_id in (select UNNEST (cast(string_to_array(replace(biodiversity.get_synonyms_specie(st.spta_id),'nd','0'),';') as int[])))) t) as text) sinonimos,     \r\n" + 
					"						 								 					 spsu_distribution distribution, spsu_altitudinal_rank altitudinal_rank,           \r\n" + 
					"						 								 					 cast((select array_to_json(array_agg(row_to_json(t))) from (select ha.habi_name nombre, inso_short_reference fuente            \r\n" + 
					"						 								 					 from biodiversity.species_taxa_habits sth inner join            \r\n" + 
					"						 								 					 biodiversity.habits ha on ha.habi_id = sth.habi_id and ha.habi_status = true inner join           \r\n" + 
					"						 								 					 biodiversity.information_sources inso on inso.inso_id = sth.inso_id and inso_status = true           \r\n" + 
					"						 								 					 where sth.stha_status = true and sth.spta_id = st.spta_id order by ha.habi_name) t) as text) AS habito,           \r\n" + 
					"						 								 					 spsu_conservation_measure conservation_measure,           \r\n" + 
					"						 								 					 spta_red_list_ec_criteria,           \r\n" + 
					"						 								 					 spta_cites_name,           \r\n" + 
					"						 								 					 cast (array_to_json(array(select gl.gelo_name from biodiversity.species_taxa_provinces pr inner join            \r\n" + 
					"						 								 					 geographical_locations gl on pr.province_id = gl.gelo_id and gl.gelo_status = true           \r\n" + 
					"						 								 					 where pr.sptp_status = true AND pr.spta_id = st.spta_id order by pr.province_id))as text) AS provincia,      \r\n" + 
					"						 								 					 cast (array_to_json(array(select gl.argr_name from biodiversity.species_taxa_artificial_groups pr inner join            \r\n" + 
					"						 								 					 biodiversity.artificial_groups gl on pr.argr_id = gl.argr_id and gl.argr_status = true           \r\n" + 
					"						 								 					 where pr.stag_status = true AND pr.spta_id = st.spta_id order by gl.argr_name)) as text) AS gruposart,           \r\n" + 
					"						 								 					 cast(array_to_json(array(select pa.sppa_name from  biodiversity.species_protected_areas pa           \r\n" + 
					"						 								 					 where pa.sppa_status = true AND pa.spta_id = st.spta_id order by pa.sppa_name)) as text) AS areanatural,           \r\n" + 
					"						 								 					 cast(array_to_json(array(select eco.spec_name from  biodiversity.species_ecosystems eco           \r\n" + 
					"						 								 					 where eco.spec_status = true AND eco.spta_id = st.spta_id order by eco.spec_name))as text) AS ecosistemas,           \r\n" + 
					"						 								 					 (select bigc_description from biodiversity.biodiversity_general_catalogs where bigc_code = 'mapa_dis') as txt_map_dis,           \r\n" + 
					"						 								 					 spsu_author, spsu_editor editores, spsu_reviser revisores,spsu_collaborator colaboradores,           \r\n" + 
					"						 								 					 spsu_publication_year,           \r\n" + 
					"						 								 					 (select bcca_value from biodiversity.biodiversity_catalog_parameters where bcca_code = 'sumario.general.access.rights') as access_rights, CAST('general' as text) AS tipsum     \r\n" + 
					"						 								 					 from biodiversity.species_taxa st            \r\n" + 
					"						 								 					 inner join biodiversity.species_summaries su on           \r\n" + 
					"						 								 					 su.spta_id = st.spta_id            \r\n" + 
					"						 								 					 left join biodiversity.red_lists_ecuador rl on            \r\n" + 
					"						 								 					 rl.rlec_id = st.rlec_id and rlec_status=true           \r\n" + 
					"						 								 					 left join biodiversity.red_lists_uicn rluicn on            \r\n" + 
					"						 								 					 rluicn.rlui_id = st.rlui_id      \r\n" + 
					"						 								 					 left join biodiversity.taxonomic_status taxstat on            \r\n" + 
					"						 								 					 taxstat.tast_id = st.tast_id  where spsu_status = true ");
			
			sqlForestal.append(
					"select spta_correct_tax,spta_correct_tax_name,tast_name,spta_taxon_rank_name,spta_scientific_name scientific_name,spta_scientific_name_authorship author,       \r\n" + 
					"									   					 spta_name_published_year publication_year,spta_scientific_gui gui,        \r\n" + 
					"									   					 cast((select json_object(array( select  hicl_taxon_rank_name       \r\n" + 
					"									   					 				FROM biodiversity.higher_classifications hc       \r\n" + 
					"									   					 				where (st.spta_hierarchy_code LIKE '%;' || hc.hicl_id || ';%' or st.spta_hierarchy_code LIKE hc.hicl_id || ';%')       \r\n" + 
					"									   					 				and  hc.hicl_status = true order by hc.tara_id),        \r\n" + 
					"									   					 				array( select hc.hicl_scientific_name       \r\n" + 
					"									   					 				FROM biodiversity.higher_classifications hc       \r\n" + 
					"									   					 				where (st.spta_hierarchy_code LIKE '%;' || hc.hicl_id || ';%' or st.spta_hierarchy_code LIKE hc.hicl_id || ';%')       \r\n" + 
					"									   					 				and  hc.hicl_status = true order by hc.tara_id))) as text) taxonomia,  \r\n" + 
					"									   					 spta_specific_infraspecifc_epit,       \r\n" + 
					"									   					 cast ((select array_to_json(array_agg(row_to_json(t))) from (select spta_scientific_name,spta_scientific_gui  from biodiversity.species_taxa        \r\n" + 
					"									   								 					 where spta_id in (select UNNEST (cast(string_to_array(replace(biodiversity.get_synonyms_specie(st.spta_id),'nd','0'),';') as int[])))) t) as text) sinonimos, \r\n" + 
					"									   					 cast ((select array_to_json(array_agg(row_to_json(t))) from (select ha.habi_name nombre, inso_short_reference fuente        \r\n" + 
					"									   					 from biodiversity.species_taxa_habits sth inner join        \r\n" + 
					"									   					 biodiversity.habits ha on ha.habi_id = sth.habi_id and ha.habi_status = true inner join       \r\n" + 
					"									   					 biodiversity.information_sources inso on inso.inso_id = sth.inso_id and inso_status = true       \r\n" + 
					"									   					 where sth.stha_status = true and sth.spta_id = st.spta_id order by ha.habi_name) t) as text) AS habito,       \r\n" + 
					"									   					 spsu_description description, spfs_botanical_description botanical_description,       \r\n" + 
					"									   					 spfs_similar_species similar_species,spsu_distribution distribution,        \r\n" + 
					"									   					 cast (array_to_json(array(select eco.spec_name from  biodiversity.species_ecosystems eco       \r\n" + 
					"									   					 where eco.spec_status = true AND eco.spta_id = st.spta_id order by eco.spec_name))as text) AS ecosistemas,       \r\n" + 
					"									   					      \r\n" + 
					"									   					 cast ((select array_to_json(array_agg(row_to_json(t))) from (select 'spph_id_' || spph_id id, spph_author autor, spph_title titulo,        \r\n" + 
					"									   					 	   'data:' || spph_mime || ';base64,' mime, spph_description from biodiversity.species_photographs sp       \r\n" + 
					"									   					 	   where sp.spta_id = st.spta_id and sp.spph_status = true and sp.spph_photograph_general = true and sp.spph_type_photograph = 'GENERAL') t)as text) img_general,       \r\n" + 
					"									   					 cast ((select array_to_json(array_agg(row_to_json(t))) from (select 'spph_id_' || spph_id id, spph_author autor, spph_title titulo,       \r\n" + 
					"									   					 	   'data:' || spph_mime || ';base64,' mime, spph_description, spph_type_photograph tipo from biodiversity.species_photographs sp2       \r\n" + 
					"									   					 	   where sp2.spta_id = st.spta_id and sp2.spph_status = true and sp2.spph_photograph_general != true and sp2.spph_type_photograph = 'PARTICULAR') t) as text) img_particular,       \r\n" + 
					"									   					 spta_endemic,        \r\n" + 
					"									   					 spta_exotic,      					 spta_alien,  \r\n" + 
					"									   					 spta_native,      					 spta_migratory,   \r\n" + 
					"									   					 spta_ecuador,      					 spta_domestic,   \r\n" + 
					"									   					        					 rlec_name,       \r\n" + 
					"									   					 spta_red_list_ec_criteria,       \r\n" + 
					"									   					 spsu_altitudinal_rank altitudinal_rank,	       \r\n" + 
					"									   					 cast (array_to_json(array(select gl.gelo_name from biodiversity.species_taxa_provinces pr inner join        \r\n" + 
					"									   					 geographical_locations gl on pr.province_id = gl.gelo_id and gl.gelo_status = true       \r\n" + 
					"									   					 where pr.sptp_status = true AND pr.spta_id = st.spta_id order by pr.province_id))as text) AS provincia,       \r\n" + 
					"									   					 cast (array_to_json(array(select pa.sppa_name from  biodiversity.species_protected_areas pa       \r\n" + 
					"									   					 where pa.sppa_status = true AND pa.spta_id = st.spta_id order by pa.sppa_name)) as text) AS areanatural,       \r\n" + 
					"									   					 cast ((select array_to_json(array_agg(row_to_json(t))) from (select vena_vernacular_name nombre,                 \r\n" + 
					"						 						 								 					 CASE when tala_name is not null and tala_name != '' then tala_name else 'N/D' end lenguaje,                \r\n" + 
					"						 						 								 					 case when etgr_name is not null and etgr_name != '' then etgr_name else 'N/D' end grupo_etnico,                \r\n" + 
					"						 						 								 					 case when inso_short_reference is not null and inso_short_reference != '' then inso_short_reference else 'N/D' end citacorta                \r\n" + 
					"						 						 								 					 from biodiversity.vernacular_names vn                \r\n" + 
					"						 						 								 					 left join biodiversity.ethnic_groups eg on eg.etgr_id =  vn.etgr_id and eg.etgr_status = true                \r\n" + 
					"						 						 								 					 left join biodiversity.taxa_languages tl on tl.tala_id = vn.tala_id and tl.tala_status = true                \r\n" + 
					"						 						 								 					 left join biodiversity.information_sources inso on inso.inso_id = vn.inso_id and inso_status = true                \r\n" + 
					"						 						 								 					 where vn.spta_id = st.spta_id and vena_status = true order by tl.tala_id) t) as text)vernacular_name,      \r\n" + 
					"									   					 spfs_conditioned_use , spfs_author as author_sum,      \r\n" + 
					"									   					 cast ((select array_to_json(array_agg(row_to_json(t))) from (select 'spdm_id_' || spdm_id id, rtrim(spdm_author) autor, spdm_title titulo, spdm_description description,       \r\n" + 
					"									   					 	'data:' || spdm_mime || ';base64,' mime,       \r\n" + 
					"									   					  case when spdm_type_photograph = 'DISTRIBUCIONUNO' then 1        \r\n" + 
					"									   					 	  when spdm_type_photograph = 'DISTRIBUCIONDOS' then 2       \r\n" + 
					"									   					  	  when spdm_type_photograph = 'DISTRIBUCIONTRES' then 3 end as orden       \r\n" + 
					"									   					 	   from biodiversity.species_distribution_maps dm       \r\n" + 
					"									   					 	   where dm.spsu_id = su.spsu_id and dm.spdm_status = true order by orden) t)as text) img_dis_map,       \r\n" + 
					"									   					 cast ((select bigc_description from biodiversity.biodiversity_general_catalogs where bigc_code = 'mapa_dis')as text) as txt_map_dis,       \r\n" + 
					"									   					 spfs_ecology ecology, spfs_use, spsu_conservation_measure conservation_measure,spfs_used_volume ,       \r\n" + 
					"									   					 cast ((select array_to_json(array_agg(row_to_json(t))) from (select 'spph_id_' || spph_id id, spph_author autor, spph_title titulo,       \r\n" + 
					"									   					 	   'data:' || spph_mime || ';base64,' mime, spph_type_photograph tipo from biodiversity.species_photographs sp2       \r\n" + 
					"									   					 	   where sp2.spta_id = st.spta_id and sp2.spph_status = true and sp2.spph_type_photograph = 'ANA_VOL_APR_SAF') t) as text) img_forestal,     \r\n" + 
					"									   					 spfs_botanical_collection botanical_collection, spfs_hardness_weight hardness_weight, spfs_color_transversal color_transversal,       \r\n" + 
					"									   					 spfs_odor_transversal odor_transversal, spfs_taste_transversal taste_transversal,       \r\n" + 
					"									   					 spfs_latex_resin_transversal latex_resin_transversal, spfs_outer_cortex_transversal outer_cortex_transversal,       \r\n" + 
					"									   					 spfs_color_tangential color_tangential, spfs_veined_tangential veined_tangential, spfs_grain_tangential grain_tangential,       \r\n" + 
					"									   					 spfs_texture_tangential texture_tangential, spfs_veined_radial veined_radial, spfs_gloss_shine_radial gloss_shine_radial,       \r\n" + 
					"									   					 spfs_growth_rings growth_rings, spfs_pores_transversal pores_transversal,       \r\n" + 
					"									   					 spfs_axial_parenchyma_transversal axial_parenchyma_transversal, spfs_radial_parenchyma_transversal radial_parenchyma_transversal,       \r\n" + 
					"									   					 spfs_density_green density_green, spfs_density_air_dry density_air_dry, spfs_density_anhydrous density_anhydrous,       \r\n" + 
					"									   					 spfs_density_basic density_basic, spfs_contraction_radia contraction_radia,spfs_contraction_tangential contraction_tangential,       \r\n" + 
					"									   					 spfs_contraction_columetrica contraction_columetrica,spfs_contraction_total_radia contraction_total_radia,       \r\n" + 
					"									   					 spfs_contraction_total_tangential contraction_total_tangential,spfs_contraction_total_volumetric contraction_total_volumetric,       \r\n" + 
					"									   					 spfs_contraction_total_relationship_tr contraction_total_relationship_tr,       \r\n" + 
					"						 								 					 cast ((select array_to_json(array_agg(row_to_json(t))) from (select bigc.bigc_id id,bigc_description grupo,           \r\n" + 
					"						 								 					 (select array_to_json(array_agg(row_to_json(d))) from (select stis_name nombre,stis_url url, bigc_description,          \r\n" + 
					"						 								 					 stis_principal           \r\n" + 
					"						 								 					 from biodiversity.species_taxa_information_sources stis where stis.spta_id = st.spta_id and            \r\n" + 
					"						 								 					 stis.stis_status = true and bigc.bigc_id = stis.bigc_id ) d) det_fuentes           \r\n" + 
					"									   					 from biodiversity.biodiversity_general_catalogs bigc where bigc.bigc_id in        \r\n" + 
					"									   					 (select bigc_id from biodiversity.species_taxa_information_sources stis0 where stis0.spta_id = st.spta_id and stis0.stis_status = true)       \r\n" + 
					"									   					 order by bigc_description) t) as text) fuentes,       \r\n" + 
					"									   					 spfs_author,spfs_publication_year, spfs_editor editores, spfs_reviser revisores, spfs_collaborator colaboradores,       \r\n" + 
					"									   					 (select bcca_value from biodiversity.biodiversity_catalog_parameters where bcca_code = 'sumario.general.access.rights') as access_rights,  \r\n" + 
					"									   					 cast (array_to_json(array(select gl.argr_name from biodiversity.species_taxa_artificial_groups pr inner join   \r\n" + 
					"									   					biodiversity.artificial_groups gl on pr.argr_id = gl.argr_id and gl.argr_status = true   \r\n" + 
					"									   					where pr.stag_status = true AND pr.spta_id = st.spta_id order by gl.argr_name)) as text) AS gruposart,spta_cites_name,\r\n" + 
					"									   					cast ((select array_to_json(array_agg(row_to_json(t))) from (select 'spph_id_' || spph_id id, spph_author autor, spph_title titulo,       \r\n" + 
					"									   					 	   'data:' || spph_mime || ';base64,' mime,spph_description,spph_type_photograph tipo from biodiversity.species_photographs sp2       \r\n" + 
					"									   					 	   where sp2.spta_id = st.spta_id and sp2.spph_status = true and sp2.spph_type_photograph = 'ORG_TRANS_ROLL_UNO' or sp2.spph_type_photograph = 'ORG_TRANS_ROLL_DOS' or sp2.spph_type_photograph = 'ORG_TRANS_ROLL_TRES' ) t) as text) img_transversal,\r\n" + 
					"									   					cast ((select array_to_json(array_agg(row_to_json(t))) from (select 'spph_id_' || spph_id id, spph_author autor, spph_title titulo,       \r\n" + 
					"									   					 	   'data:' || spph_mime || ';base64,' mime, spph_description, spph_type_photograph tipo from biodiversity.species_photographs sp2       \r\n" + 
					"									   					 	   where sp2.spta_id = st.spta_id and sp2.spph_status = true and sp2.spph_type_photograph = 'ORG_TANG_ASERR_UNO' or sp2.spph_type_photograph = 'ORG_TANG_ASERR_DOS' or sp2.spph_type_photograph = 'ORG_TANG_ASERR_TRES' ) t) as text) img_tangencial, \r\n" + 
					"									   					cast ((select array_to_json(array_agg(row_to_json(t))) from (select 'spph_id_' || spph_id id, spph_author autor, spph_title titulo,       \r\n" + 
					"									   					 	   'data:' || spph_mime || ';base64,' mime,spph_description, spph_type_photograph tipo from biodiversity.species_photographs sp2       \r\n" + 
					"									   					 	   where sp2.spta_id = st.spta_id and sp2.spph_status = true and sp2.spph_type_photograph = 'ORG_RAD_ASERR_UNO' or sp2.spph_type_photograph = 'ORG_RAD_ASERR_DOS' or sp2.spph_type_photograph = 'ORG_RAD_ASERR_TRES' ) t) as text) img_radial, \r\n" + 
					"									   					 	   cast ((select array_to_json(array_agg(row_to_json(t))) from (select 'spph_id_' || spph_id id, spph_author autor, spph_title titulo,       \r\n" + 
					"									   					 	   'data:' || spph_mime || ';base64,' mime,spph_description, spph_type_photograph tipo from biodiversity.species_photographs sp2       \r\n" + 
					"									   					 	   where sp2.spta_id = st.spta_id and sp2.spph_status = true and sp2.spph_type_photograph = 'ANAT_TRANS_ASERR_UNO' or sp2.spph_type_photograph = 'ANAT_TRANS_ASERR_DOS' or sp2.spph_type_photograph = 'ANAT_TRANS_ASERR_TRES' ) t) as text) img_anatomica,  CAST('forestal' as text) AS tipsum	      \r\n" + 
					"									   					from biodiversity.species_taxa st        \r\n" + 
					"									   					 inner join biodiversity.species_forestal_summaries spfs on       \r\n" + 
					"									   					 spfs.spta_id = st.spta_id   \r\n" + 
					"									   					left join biodiversity.species_summaries su on       \r\n" + 
					"									   					 su.spta_id = st.spta_id and spsu_status = true   			       \r\n" + 
					"									   					 left join biodiversity.red_lists_ecuador rl on        \r\n" + 
					"									   					 rl.rlec_id = st.rlec_id and rlec_status=true       \r\n" + 
					"									   					 left join biodiversity.red_lists_uicn rluicn on        \r\n" + 
					"									   					 rluicn.rlui_id = st.rlui_id and rlui_status=true  \r\n" + 
					"									   					 left join biodiversity.taxonomic_status taxstat on   \r\n" + 
					"									   					 taxstat.tast_id = st.tast_id where spfs_status = true ");
			
			sqlExotica.append(
					"select spta_correct_tax,spta_correct_tax_name,tast_name,spta_taxon_rank_name,spta_scientific_name scientific_name,spta_scientific_gui gui,       \r\n" + 
					"									   					 spta_scientific_name_authorship author, spta_name_published_year publication_year,       \r\n" + 
					"									   					 cast ((select array_to_json(array_agg(row_to_json(t))) from (select 'spph_id_' || spph_id id, spph_author autor, spph_title titulo,             \r\n" + 
					"							 									   					 	   'data:' || spph_mime || ';base64,' mime, spph_description from biodiversity.species_photographs sp            \r\n" + 
					"							 									   					 	   where sp.spta_id = st.spta_id and sp.spph_status = true and sp.spph_photograph_general = true and sp.spph_type_photograph = 'GENERAL') t)as text) img_general,        \r\n" + 
					"									   					 cast ((select array_to_json(array_agg(row_to_json(t))) from (select 'spph_id_' || spph_id id, spph_author autor, spph_title titulo,            \r\n" + 
					"							 									   					 	   'data:' || spph_mime || ';base64,' mime, spph_description, spph_type_photograph tipo from biodiversity.species_photographs sp2            \r\n" + 
					"							 									   					 	   where sp2.spta_id = st.spta_id and sp2.spph_status = true and sp2.spph_photograph_general != true and sp2.spph_type_photograph = 'PARTICULAR') t) as text) img_particular,        \r\n" + 
					"									   					 cast ((select json_object(array( select  hicl_taxon_rank_name       \r\n" + 
					"									   					 				FROM biodiversity.higher_classifications hc       \r\n" + 
					"									   					 				where (st.spta_hierarchy_code LIKE '%;' || hc.hicl_id || ';%' or st.spta_hierarchy_code LIKE hc.hicl_id || ';%')       \r\n" + 
					"									   					 				and  hc.hicl_status = true order by hc.tara_id),        \r\n" + 
					"									   					 				array( select hc.hicl_scientific_name       \r\n" + 
					"									   					 				FROM biodiversity.higher_classifications hc       \r\n" + 
					"									   					 				where (st.spta_hierarchy_code LIKE '%;' || hc.hicl_id || ';%' or st.spta_hierarchy_code LIKE hc.hicl_id || ';%')       \r\n" + 
					"									   					 				and  hc.hicl_status = true order by hc.tara_id))) as text) taxonomia,  spta_specific_infraspecifc_epit,      \r\n" + 
					"									   					 spta_endemic,          					 spta_exotic,  \r\n" + 
					"									   					 spta_alien,      					 spta_native,  \r\n" + 
					"									   					 spta_migratory,       					 spta_ecuador,  \r\n" + 
					"									   					 spta_domestic,       \r\n" + 
					"									   					 cast ((select array_to_json(array_agg(row_to_json(t))) from (select vena_vernacular_name nombre,        \r\n" + 
					"									   					 CASE when tala_name is not null and tala_name != '' then tala_name else 'N/D' end lenguaje,       \r\n" + 
					"									   					 case when etgr_name is not null and etgr_name != '' then etgr_name else 'N/D' end grupo_etnico,        \r\n" + 
					"									   					 case when inso_short_reference is not null and inso_short_reference != '' then inso_short_reference else 'N/D' end citacorta       \r\n" + 
					"									   					 from biodiversity.vernacular_names vn       \r\n" + 
					"									   					 left join biodiversity.ethnic_groups eg on eg.etgr_id =  vn.etgr_id and eg.etgr_status = true       \r\n" + 
					"									   					 left join biodiversity.taxa_languages tl on tl.tala_id = vn.tala_id and tl.tala_status = true       \r\n" + 
					"									   					 left join biodiversity.information_sources inso on inso.inso_id = vn.inso_id and inso_status = true       \r\n" + 
					"									   					 where vn.spta_id = st.spta_id and vena_status = true order by tl.tala_id) t) as text )vernacular_name,       \r\n" + 
					"									   					 seas_morphological_description morphological_description,       \r\n" + 
					"									   					 cast ((select array_to_json(array_agg(row_to_json(t))) from (select spta_scientific_name,spta_scientific_gui  from biodiversity.species_taxa        \r\n" + 
					"									   								 					 where spta_id in (select UNNEST (cast(string_to_array(replace(biodiversity.get_synonyms_specie(st.spta_id),'nd','0'),';') as int[])))) t) as text) sinonimos, \r\n" + 
					"									   					 seas_native_distributive_area native_distributive_area,seas_natural_environment natural_environment,       \r\n" + 
					"									   					 cast (array_to_json(array(select gl.gelo_name from biodiversity.species_taxa_provinces pr inner join        \r\n" + 
					"									   					 geographical_locations gl on pr.province_id = gl.gelo_id and gl.gelo_status = true       \r\n" + 
					"									   					 where pr.sptp_status = true AND pr.spta_id = st.spta_id order by pr.province_id)) as text) AS provincia,       \r\n" + 
					"									   					 cast (array_to_json(array(select pa.sppa_name from  biodiversity.species_protected_areas pa       \r\n" + 
					"									   					 where pa.sppa_status = true AND pa.spta_id = st.spta_id order by pa.sppa_name)) as text) AS areanatural,       \r\n" + 
					"									   					 cast (array_to_json(array(select eco.spec_name from  biodiversity.species_ecosystems eco       \r\n" + 
					"									   					 where eco.spec_status = true AND eco.spta_id = st.spta_id order by eco.spec_name)) as text) AS ecosistemas,       \r\n" + 
					"									   					 seas_introduction_year introduction_year,seas_introduction_reason introduction_reason,       \r\n" + 
					"									   					 cast ((select array_to_json(array_agg(row_to_json(d))) from (select ieas_title title, ieas_author author, ieas_place_introduction place_introduction,       \r\n" + 
					"									   					 to_char(ieas_date_introduction, 'dd-TMMon-yyyy') date_introduction, bigcf.bigc_description type_introduction, cain_name cause_introduction,       \r\n" + 
					"									   					 ieas_description description, bigc.bigc_description cat_invasion,ieas_exotic_in_region exotic_in_region       \r\n" + 
					"									   					 from biodiversity.introduction_exotic_alien_species ieas        \r\n" + 
					"									   					 left join biodiversity.biodiversity_general_catalogs bigcf on bigcf.bigc_id = ieas.bigc_id_form and bigcf.bigc_status=true       \r\n" + 
					"									   					 left join biodiversity.cause_introduction cain on cain.cain_id = ieas.cain_id       \r\n" + 
					"									   					 left join biodiversity.biodiversity_general_catalogs bigc on bigc.bigc_id = ieas.bigc_id and bigc.bigc_status=true       \r\n" + 
					"									   					 where ieas.spta_id = st.spta_id and ieas.ieas_status = true order by ieas_id) d) as text) proy_introduccion,       \r\n" + 
					"									   					 cast ( array_to_json(array(select sptaf.spta_scientific_name from biodiversity.species_taxa sptaf inner join biodiversity.affected_species afsp on       \r\n" + 
					"									   					 afsp.spta_id_affected = sptaf.spta_id and afsp_status=true and sptaf.spta_status=true       \r\n" + 
					"									   					 where afsp.seas_id=seas.seas_id)) as text) affected_species,       \r\n" + 
					"									   					 seas_ecological_impact ecological_impact, seas_economic_impact economic_impact, seas_social_impact social_impact,       \r\n" + 
					"									   					 seas_health_impact health_impact,       \r\n" + 
					"									   					 cast ( array_to_json(array(select cadi_name from biodiversity.catalog_dispersion cadi inner join biodiversity.dispersion_exotic_alien_species deas on       \r\n" + 
					"									   					 deas.cadi_id = cadi.cadi_id and deas_status=true and cadi_status=true       \r\n" + 
					"									   					 where deas.seas_id=seas.seas_id)) as text) dispersion_features,       \r\n" + 
					"									   					 cast (array_to_json(array(select crep_name from biodiversity.catalog_reproductions crep inner join biodiversity.reproduction_exotic_alien_species reas on       \r\n" + 
					"									   					 reas.crep_id = crep.crep_id and reas_status=true and crep_status=true       \r\n" + 
					"									   					 where reas.seas_id=seas.seas_id)) as text) reproduction_features,       \r\n" + 
					"									   					 cast (array_to_json( array(select cdie_name from biodiversity.catalog_diets cdie inner join biodiversity.diet_exotic_alien_species diea on       \r\n" + 
					"									   					 diea.cdie_id = cdie.cdie_id and diea_status=true and cdie_status=true       \r\n" + 
					"									   					 where diea.seas_id=seas.seas_id)) as text) diet_features,       \r\n" + 
					"									   					 cast (array_to_json(array(select bigc_description from biodiversity.biodiversity_general_catalogs bigc inner join biodiversity.dispersion_vectors dive on       \r\n" + 
					"									   					 dive.bigc_id = bigc.bigc_id and dive_status=true and bigc_status=true       \r\n" + 
					"									   					 where dive.seas_id=seas.seas_id)) as text) dispersion_vectors_features,       \r\n" + 
					"									   					 cast (array_to_json(array(select bigc_description from biodiversity.biodiversity_general_catalogs bigc inner join biodiversity.dispersion_routes diro on       \r\n" + 
					"									   					 diro.bigc_id = bigc.bigc_id and diro_status=true and bigc_status=true       \r\n" + 
					"									   					 where diro.seas_id=seas.seas_id)) as text) dispersion_routes_features,       \r\n" + 
					"									   					 cast (array_to_json(array(select cabf_name from biodiversity.catalog_biological_forms cabf inner join biodiversity.biological_forms bifo on       \r\n" + 
					"									   					 bifo.cabf_id = cabf.cabf_id and bifo_status=true and cabf_status=true       \r\n" + 
					"									   					 where bifo.seas_id=seas.seas_id)) as text) biological_forms_features,       \r\n" + 
					"									   					 cast ( array_to_json(array(select caus_name ||': '|| ueas_description from biodiversity.catalog_uses caus inner join biodiversity.uses_exotic_alien_species ueas on       \r\n" + 
					"									   					 ueas.caus_id = caus.caus_id and ueas_status=true and caus_status=true       \r\n" + 
					"									   					 where ueas.seas_id=seas.seas_id)) as text) uses_features,       \r\n" + 
					"									   					 cast (array_to_json(array(select bigc_description || ': ' || inen_description from biodiversity.biodiversity_general_catalogs bigc inner join biodiversity.invasion_environments inen on       \r\n" + 
					"									   					 inen.bigc_id = bigc.bigc_id and inen_status=true and bigc_status=true       \r\n" + 
					"									   					 where inen.seas_id=seas.seas_id)) as text) invasion_environments_features,       \r\n" + 
					"									   					 seas_physical_control physical_control, seas_chemical_control chemical_control, seas_biologic_control biologic_control,       \r\n" + 
					"									   					 seas_prevention_measure prevention_measure, seas_risk_analysis risk_analysis,        \r\n" + 
					"									   					 cast ((select array_to_json(array_agg(row_to_json(t))) from (select bigc.bigc_id id,bigc_description grupo,                \r\n" + 
					"							 						 								 					 (select array_to_json(array_agg(row_to_json(d))) from (select stis_name nombre,stis_url url, bigc_description,               \r\n" + 
					"							 						 								 					 stis_principal                \r\n" + 
					"							 						 								 					 from biodiversity.species_taxa_information_sources stis where stis.spta_id = st.spta_id and                 \r\n" + 
					"							 						 								 					 stis.stis_status = true and bigc.bigc_id = stis.bigc_id ) d) det_fuentes                \r\n" + 
					"							 									   					 from biodiversity.biodiversity_general_catalogs bigc where bigc.bigc_id in             \r\n" + 
					"							 									   					 (select bigc_id from biodiversity.species_taxa_information_sources stis0 where stis0.spta_id = st.spta_id and stis0.stis_status = true)            \r\n" + 
					"							 									   					 order by bigc_description) t) as text) fuentes,       \r\n" + 
					"									   					 seas_author , seas_publication_year,       \r\n" + 
					"									   					 seas_reviser revisores, seas_editor editores, seas_collaborator colaboradores,       \r\n" + 
					"									   					 (select bcca_value from biodiversity.biodiversity_catalog_parameters where bcca_code = 'sumario.general.access.rights') as access_rights,  CAST('exotica' as text) AS tipsum      \r\n" + 
					"									   					 from biodiversity.species_taxa st  \r\n" + 
					"									   					 inner join biodiversity.summaries_exotic_alien_species seas on       \r\n" + 
					"									   					 seas.spta_id = st.spta_id      \r\n" + 
					"									   					 left join biodiversity.species_summaries su on      \r\n" + 
					"									   					 su.spta_id = st.spta_id and spsu_status = true    \r\n" + 
					"									   					 left join biodiversity.taxonomic_status taxstat on  \r\n" + 
					"									   					 taxstat.tast_id = st.tast_id  where seas_status = true ");
			
			if ((gui != null && !gui.equals("")) && (sName != null && !sName.equals(""))
					&& (rank != null && !rank.equals(""))) {

				if (idRank.equals(1)) {
					sql.append("and spta_hierarchy_code like '");
					sql.append(codigoPadre);
					sql.append(";%;%;%;%;%;%' ");
					sqlForestal.append("and spta_hierarchy_code like '");
					sqlForestal.append(codigoPadre);
					sqlForestal.append(";%;%;%;%;%;%' ");
					sqlExotica.append("and spta_hierarchy_code like '");
					sqlExotica.append(codigoPadre);
					sqlExotica.append(";%;%;%;%;%;%' ");

				} else {
					if (idRank.equals(2)) {
						sql.append("and spta_hierarchy_code like '");
						sql.append(codigoPadre);
						sql.append(";%;%;%;%;%' ");
						sqlForestal.append("and spta_hierarchy_code like '");
						sqlForestal.append(codigoPadre);
						sqlForestal.append(";%;%;%;%;%' ");
						sqlExotica.append("and spta_hierarchy_code like '");
						sqlExotica.append(codigoPadre);
						sqlExotica.append(";%;%;%;%;%' ");

					} else {
						if (idRank.equals(3)) {
							sql.append("and spta_hierarchy_code like '");
							sql.append(codigoPadre);
							sql.append(";%;%;%;%' ");
							sqlForestal.append("and spta_hierarchy_code like '");
							sqlForestal.append(codigoPadre);
							sqlForestal.append(";%;%;%;%' ");
							sqlExotica.append("and spta_hierarchy_code like '");
							sqlExotica.append(codigoPadre);
							sqlExotica.append(";%;%;%;%' ");

						} else {
							if (idRank.equals(4)) {
								sql.append("and spta_hierarchy_code like '");
								sql.append(codigoPadre);
								sql.append(";%;%;%' ");
								sqlForestal.append("and spta_hierarchy_code like '");
								sqlForestal.append(codigoPadre);
								sqlForestal.append(";%;%;%' ");
								sqlExotica.append("and spta_hierarchy_code like '");
								sqlExotica.append(codigoPadre);
								sqlExotica.append(";%;%;%' ");

							} else {
								if (idRank.equals(5)) {
									sql.append("and spta_hierarchy_code like '");
									sql.append(codigoPadre);
									sql.append(";%;%' ");
									sqlForestal.append("and spta_hierarchy_code like '");
									sqlForestal.append(codigoPadre);
									sqlForestal.append(";%;%' ");
									sqlExotica.append("and spta_hierarchy_code like '");
									sqlExotica.append(codigoPadre);
									sqlExotica.append(";%;%' ");

								} else {
									if (idRank.equals(6)) {
										sql.append("and spta_hierarchy_code like '");
										sql.append(codigoPadre);
										sql.append(";%' ");
										sqlForestal.append("and spta_hierarchy_code like '");
										sqlForestal.append(codigoPadre);
										sqlForestal.append(";%' ");
										sqlExotica.append("and spta_hierarchy_code like '");
										sqlExotica.append(codigoPadre);
										sqlExotica.append(";%' ");

									}
								}
							}
						}
					}
				}
			} else {
				if ((gui != null && !gui.equals("")) && (sName != null && !sName.equals(""))
						&& (rank == null || rank.equals(""))) {
					sql.append("and spta_scientific_gui = '");
					sql.append(gui);
					sql.append("' ");
					sqlForestal.append("and spta_scientific_gui = '");
					sqlForestal.append(gui);
					sqlForestal.append("' ");
					sqlExotica.append("and spta_scientific_gui = '");
					sqlExotica.append(gui);
					sqlExotica.append("' ");
				} else {
					if ((gui != null && !gui.equals("")) && (sName == null || sName.equals(""))
							&& (rank != null && !rank.equals(""))) {

						if (idRank.equals(1)) {
							sql.append("and spta_hierarchy_code like '");
							sql.append(codigoPadre);
							sql.append(";%;%;%;%;%;%' ");
							sqlForestal.append("and spta_hierarchy_code like '");
							sqlForestal.append(codigoPadre);
							sqlForestal.append(";%;%;%;%;%;%' ");
							sqlExotica.append("and spta_hierarchy_code like '");
							sqlExotica.append(codigoPadre);
							sqlExotica.append(";%;%;%;%;%;%' ");

						} else {
							if (idRank.equals(2)) {
								sql.append("and spta_hierarchy_code like '");
								sql.append(codigoPadre);
								sql.append(";%;%;%;%;%' ");
								sqlForestal.append("and spta_hierarchy_code like '");
								sqlForestal.append(codigoPadre);
								sqlForestal.append(";%;%;%;%;%' ");
								sqlExotica.append("and spta_hierarchy_code like '");
								sqlExotica.append(codigoPadre);
								sqlExotica.append(";%;%;%;%;%' ");

							} else {
								if (idRank.equals(3)) {
									sql.append("and spta_hierarchy_code like '");
									sql.append(codigoPadre);
									sql.append(";%;%;%;%' ");
									sqlForestal.append("and spta_hierarchy_code like '");
									sqlForestal.append(codigoPadre);
									sqlForestal.append(";%;%;%;%' ");
									sqlExotica.append("and spta_hierarchy_code like '");
									sqlExotica.append(codigoPadre);
									sqlExotica.append(";%;%;%;%' ");

								} else {
									if (idRank.equals(4)) {
										sql.append("and spta_hierarchy_code like '");
										sql.append(codigoPadre);
										sql.append(";%;%;%' ");
										sqlForestal.append("and spta_hierarchy_code like '");
										sqlForestal.append(codigoPadre);
										sqlForestal.append(";%;%;%' ");
										sqlExotica.append("and spta_hierarchy_code like '");
										sqlExotica.append(codigoPadre);
										sqlExotica.append(";%;%;%' ");

									} else {
										if (idRank.equals(5)) {
											sql.append("and spta_hierarchy_code like '");
											sql.append(codigoPadre);
											sql.append(";%;%' ");
											sqlForestal.append("and spta_hierarchy_code like '");
											sqlForestal.append(codigoPadre);
											sqlForestal.append(";%;%' ");
											sqlExotica.append("and spta_hierarchy_code like '");
											sqlExotica.append(codigoPadre);
											sqlExotica.append(";%;%' ");

										} else {
											if (idRank.equals(6)) {
												sql.append("and spta_hierarchy_code like '");
												sql.append(codigoPadre);
												sql.append(";%' ");
												sqlForestal.append("and spta_hierarchy_code like '");
												sqlForestal.append(codigoPadre);
												sqlForestal.append(";%' ");
												sqlExotica.append("and spta_hierarchy_code like '");
												sqlExotica.append(codigoPadre);
												sqlExotica.append(";%' ");

											}
										}
									}
								}
							}
						}

					} else {
						if ((gui != null && !gui.equals("")) && (sName == null || sName.equals(""))
								&& (rank == null || rank.equals(""))) {
							sql.append("and spta_scientific_gui = '");
							sql.append(gui);
							sql.append("' ");
							sqlForestal.append("and spta_scientific_gui = '");
							sqlForestal.append(gui);
							sqlForestal.append("' ");
							sqlExotica.append("and spta_scientific_gui = '");
							sqlExotica.append(gui);
							sqlExotica.append("' ");
						} else {
							if ((gui == null || gui.equals("")) && (sName == null || sName.equals(""))
									&& (rank != null && !rank.equals(""))) {
								if (idRank.equals(1)) {
									sql.append("and spta_hierarchy_code like '");
									sql.append(codigoPadre);
									sql.append(";%;%;%;%;%;%' ");
									sqlForestal.append("and spta_hierarchy_code like '");
									sqlForestal.append(codigoPadre);
									sqlForestal.append(";%;%;%;%;%;%' ");
									sqlExotica.append("and spta_hierarchy_code like '");
									sqlExotica.append(codigoPadre);
									sqlExotica.append(";%;%;%;%;%;%' ");

								} else {
									if (idRank.equals(2)) {
										sql.append("and spta_hierarchy_code like '");
										sql.append(codigoPadre);
										sql.append(";%;%;%;%;%' ");
										sqlForestal.append("and spta_hierarchy_code like '");
										sqlForestal.append(codigoPadre);
										sqlForestal.append(";%;%;%;%;%' ");
										sqlExotica.append("and spta_hierarchy_code like '");
										sqlExotica.append(codigoPadre);
										sqlExotica.append(";%;%;%;%;%' ");

									} else {
										if (idRank.equals(3)) {
											sql.append("and spta_hierarchy_code like '");
											sql.append(codigoPadre);
											sql.append(";%;%;%;%' ");
											sqlForestal.append("and spta_hierarchy_code like '");
											sqlForestal.append(codigoPadre);
											sqlForestal.append(";%;%;%;%' ");
											sqlExotica.append("and spta_hierarchy_code like '");
											sqlExotica.append(codigoPadre);
											sqlExotica.append(";%;%;%;%' ");

										} else {
											if (idRank.equals(4)) {
												sql.append("and spta_hierarchy_code like '");
												sql.append(codigoPadre);
												sql.append(";%;%;%' ");
												sqlForestal.append("and spta_hierarchy_code like '");
												sqlForestal.append(codigoPadre);
												sqlForestal.append(";%;%;%' ");
												sqlExotica.append("and spta_hierarchy_code like '");
												sqlExotica.append(codigoPadre);
												sqlExotica.append(";%;%;%' ");

											} else {
												if (idRank.equals(5)) {
													sql.append("and spta_hierarchy_code like '");
													sql.append(codigoPadre);
													sql.append(";%;%' ");
													sqlForestal.append("and spta_hierarchy_code like '");
													sqlForestal.append(codigoPadre);
													sqlForestal.append(";%;%' ");
													sqlExotica.append("and spta_hierarchy_code like '");
													sqlExotica.append(codigoPadre);
													sqlExotica.append(";%;%' ");

												} else {
													if (idRank.equals(6)) {
														sql.append("and spta_hierarchy_code like '");
														sql.append(codigoPadre);
														sql.append(";%' ");
														sqlForestal.append("and spta_hierarchy_code like '");
														sqlForestal.append(codigoPadre);
														sqlForestal.append(";%' ");
														sqlExotica.append("and spta_hierarchy_code like '");
														sqlExotica.append(codigoPadre);
														sqlExotica.append(";%' ");

													}
												}
											}
										}
									}
								}
							} else {
								if ((gui == null || gui.equals("")) && (sName != null && !sName.equals(""))
										&& (rank != null && !rank.equals(""))) {
									if (idRank.equals(1)) {
										sql.append("and spta_hierarchy_code like '");
										sql.append(codigoPadre);
										sql.append(";%;%;%;%;%;%' ");
										sqlForestal.append("and spta_hierarchy_code like '");
										sqlForestal.append(codigoPadre);
										sqlForestal.append(";%;%;%;%;%;%' ");
										sqlExotica.append("and spta_hierarchy_code like '");
										sqlExotica.append(codigoPadre);
										sqlExotica.append(";%;%;%;%;%;%' ");

									} else {
										if (idRank.equals(2)) {
											sql.append("and spta_hierarchy_code like '");
											sql.append(codigoPadre);
											sql.append(";%;%;%;%;%' ");
											sqlForestal.append("and spta_hierarchy_code like '");
											sqlForestal.append(codigoPadre);
											sqlForestal.append(";%;%;%;%;%' ");
											sqlExotica.append("and spta_hierarchy_code like '");
											sqlExotica.append(codigoPadre);
											sqlExotica.append(";%;%;%;%;%' ");

										} else {
											if (idRank.equals(3)) {
												sql.append("and spta_hierarchy_code like '");
												sql.append(codigoPadre);
												sql.append(";%;%;%;%' ");
												sqlForestal.append("and spta_hierarchy_code like '");
												sqlForestal.append(codigoPadre);
												sqlForestal.append(";%;%;%;%' ");

											} else {
												if (idRank.equals(4)) {
													sql.append("and spta_hierarchy_code like '");
													sql.append(codigoPadre);
													sql.append(";%;%;%' ");
													sqlForestal.append("and spta_hierarchy_code like '");
													sqlForestal.append(codigoPadre);
													sqlForestal.append(";%;%;%' ");
													sqlExotica.append("and spta_hierarchy_code like '");
													sqlExotica.append(codigoPadre);
													sqlExotica.append(";%;%;%' ");

												} else {
													if (idRank.equals(5)) {
														sql.append("and spta_hierarchy_code like '");
														sql.append(codigoPadre);
														sql.append(";%;%;%' ");
														sqlForestal.append("and spta_hierarchy_code like '");
														sqlForestal.append(codigoPadre);
														sqlForestal.append(";%;%;%' ");
														sqlExotica.append("and spta_hierarchy_code like '");
														sqlExotica.append(codigoPadre);
														sqlExotica.append(";%;%;%' ");

													} else {
														if (idRank.equals(6)) {
															sql.append("and spta_hierarchy_code like '");
															sql.append(codigoPadre);
															sql.append(";%' ");
															sqlForestal.append("and spta_hierarchy_code like '");
															sqlForestal.append(codigoPadre);
															sqlForestal.append(";%' ");
															sqlExotica.append("and spta_hierarchy_code like '");
															sqlExotica.append(codigoPadre);
															sqlExotica.append(";%' ");

														}
													}
												}
											}
										}
									}
								} else {
									if ((gui == null || gui.equals("")) && (sName != null && !sName.equals(""))
											&& (rank == null || rank.equals(""))) {
										sql.append("and spta_scientific_name = '");
										sql.append(sName);
										sql.append("' ");
										sqlForestal.append("and spta_scientific_name = '");
										sqlForestal.append(sName);
										sqlForestal.append("' ");
										sqlExotica.append("and spta_scientific_name = '");
										sqlExotica.append(sName);
										sqlExotica.append("' ");
									} else {

									}
								}
							}
						}
					}
				}
			}

			if (lrEC != null && !lrEC.equals("")) {
				sql.append("and UPPER(rlec_initial) in ('");
				String[] iniciales = lrEC.split(",");
				if (iniciales.length > 1) {
					for (int i = 0; i < iniciales.length; i++) {
						if (i != (iniciales.length - 1)) {
							sql.append(iniciales[i]);
							sql.append("','");
							sqlForestal.append(iniciales[i]);
							sqlForestal.append("','");
							sqlExotica.append(iniciales[i]);
							sqlExotica.append("','");
						} else {
							sql.append(iniciales[i]);
							sql.append(") ");
							sqlForestal.append(iniciales[i]);
							sqlForestal.append(") ");
							sqlExotica.append(iniciales[i]);
							sqlExotica.append(") ");
						}
					}
				} else {
					sql.append("and UPPER(rlec_initial) = '");
					sql.append(lrEC.toUpperCase());
					sql.append("' ");
					sqlForestal.append("and UPPER(rlec_initial) = '");
					sqlForestal.append(lrEC.toUpperCase());
					sqlForestal.append("' ");
					sqlExotica.append("and UPPER(rlec_initial) = '");
					sqlExotica.append(lrEC.toUpperCase());
					sqlExotica.append("' ");
				}
			}

			if (cites != null && !cites.equals("")) {

				String[] iniciales = cites.split(",");
				if (iniciales.length > 1) {
					sql.append("and cite_id in ('");
					Integer citeId = 0;
					for (int i = 0; i < iniciales.length; i++) {
						if (iniciales[i].equalsIgnoreCase("I") || iniciales[i].equals("1")) {
							citeId = 1;
						}
						if (iniciales[i].equalsIgnoreCase("II") || iniciales[i].equals("2")) {
							citeId = 2;
						}

						if (iniciales[i].equalsIgnoreCase("III") || iniciales[i].equals("3")) {
							citeId = 3;
						}
						if (i != (iniciales.length - 1)) {
							sql.append(citeId);
							sql.append("','");
							sqlForestal.append(citeId);
							sqlForestal.append("','");
							sqlExotica.append(citeId);
							sqlExotica.append("','");
						} else {
							sql.append(citeId);
							sql.append(") ");
							sqlForestal.append(citeId);
							sqlForestal.append(") ");
							sqlExotica.append(citeId);
							sqlExotica.append(") ");
						}
					}
				} else {
					Integer citeId = 0;
					if (cites.equalsIgnoreCase("I") || cites.equals("1")) {
						citeId = 1;
					}
					if (cites.equalsIgnoreCase("II") || cites.equals("2")) {
						citeId = 2;
					}

					if (cites.equalsIgnoreCase("III") || cites.equals("3")) {
						citeId = 3;
					}

					sql.append("and cite_id = ");
					sql.append(citeId);
					sql.append(" ");
					sqlForestal.append("and cite_id = ");
					sqlForestal.append(citeId);
					sqlForestal.append(" ");
					sqlExotica.append("and cite_id = ");
					sqlExotica.append(citeId);
					sqlExotica.append(" ");
				}
			}

			if (status != null && !status.equals("")) {

				String[] iniciales = status.split(",");
				if (iniciales.length > 1) {
					sql.append("and cite_id in ('");
					Integer citeId = 0;
					for (int i = 0; i < iniciales.length; i++) {
						if (iniciales[i].equalsIgnoreCase("I") || iniciales[i].equals("1")) {
							citeId = 1;
						}
						if (iniciales[i].equalsIgnoreCase("II") || iniciales[i].equals("2")) {
							citeId = 2;
						}

						if (iniciales[i].equalsIgnoreCase("III") || iniciales[i].equals("3")) {
							citeId = 3;
						}
						if (i != (iniciales.length - 1)) {
							sql.append(citeId);
							sql.append("','");
							sqlForestal.append(citeId);
							sqlForestal.append("','");
							sqlExotica.append(citeId);
							sqlExotica.append("','");
						} else {
							sql.append(citeId);
							sql.append(") ");
							sqlForestal.append(citeId);
							sqlForestal.append(") ");
							sqlExotica.append(citeId);
							sqlExotica.append(") ");
						}
					}
				} else {

					if (status.equalsIgnoreCase("end") || status.equals("e")) {
						sql.append("and spta_endemic = true ");
						sqlForestal.append("and spta_endemic = true ");
						sqlExotica.append("and spta_endemic = true ");

					}
					if (status.equalsIgnoreCase("exo") || status.equals("x")) {
						sql.append("and spta_exotic = true ");
						sqlForestal.append("and spta_exotic = true ");
						sqlExotica.append("and spta_exotic = true ");
					}

					if (status.equalsIgnoreCase("dom") || status.equals("d")) {
						sql.append("and spta_domestic = true ");
						sqlForestal.append("and spta_domestic = true ");
						sqlExotica.append("and spta_domestic = true ");
					}

					if (status.equalsIgnoreCase("cul") || status.equals("c")) {
						sql.append("and spta_domestic = true ");
						sqlForestal.append("and spta_domestic = true ");
						sqlExotica.append("and spta_domestic = true ");
					}

					if (status.equalsIgnoreCase("nat") || status.equals("n")) {
						sql.append("and spta_native = true ");
						sqlForestal.append("and spta_native = true ");
						sqlExotica.append("and spta_native = true ");
					}

					if (status.equalsIgnoreCase("mig") || status.equals("m")) {
						sql.append("and spta_migratory = true ");
						sqlForestal.append("and spta_migratory = true ");
						sqlExotica.append("and spta_migratory = true ");
					}

					if (status.equalsIgnoreCase("inv") || status.equals("i")) {
						sql.append("and spta_alien = true ");
						sqlForestal.append("and spta_alien = true ");
						sqlExotica.append("and spta_alien = true ");
					}

				}
			}

			
			
			
			List<Object[]> resuGen = new ArrayList<Object[]>();

			Query qG = super.getEntityManager().createNativeQuery(sql.toString());
			resuGen = (qG.getResultList());
			if (!resuGen.isEmpty()) {
				for (Object[] row : resuGen) {
					 
					if(row[5]!=null)
					{
					mapaResultados.put(row[5].toString(), row);
					}
				}
			}
			
			List<Object[]> resuExo = new ArrayList<Object[]>();

			Query qE = super.getEntityManager().createNativeQuery(sqlExotica.toString());
			resuExo = (qE.getResultList());
			if (!resuExo.isEmpty()) {
				for (Object[] row : resuExo) {
					 
					if(row[5]!=null)
					{
					mapaResultados.put(row[5].toString(), row);
					}
				}
			}
			
			List<Object[]> resuFor = new ArrayList<Object[]>();

			Query qF = super.getEntityManager().createNativeQuery(sqlForestal.toString());
			System.out.println(sqlForestal.toString());
			resuFor = (qF.getResultList());
			if (!resuFor.isEmpty()) {
				for (Object[] row : resuFor) {
					 
					if(row[7]!=null)
					{
					mapaResultados.put(row[7].toString(), row);
					}
				}
			}
			
			
			for (Map.Entry<String, Object []> entry : mapaResultados.entrySet()) 
			{
				Object [] result= entry.getValue();
				if(result[result.length-1].toString().equals("general"))
				{
					SumGSpResult resulSum=obtenerSumarioGeneral(result);
					objectResultG.add(resulSum);
					
					
				}
				else
				{
					if(result[result.length-1].toString().equals("exotica"))
					{
						SumExSpResult resulSum=obtenerSumarioExotica(result);
						objectResultE.add(resulSum);
					}
					else
					{
						SumForeSpResult resulSum=obtenerSumarioForestal(result);
						objectResultF.add(resulSum);
						
					}
				}
			}

		}
		resultado.setSumGSpResult(objectResultG);
		resultado.setSumForeSpResult(objectResultF);
		resultado.setSumExSpResult(objectResultE);
		
		return resultado;

	}
	
	
	public SumGSpResult obtenerSumarioGeneral(Object[] row)
	{
		SumGSpResult sumarioG = new SumGSpResult();
		Metadata meta = new Metadata();
		meta.setComment(
				"El Ministerio del Ambiente y Agua se reserva el derecho de modificar sin previo aviso el listado de especies con información disponible, el contenido del sumario, y/o la estructura del servicio web. Para información actualizada se puede consultar en http://suia.ambiente.gob.ec");
		Author author = new Author();
		author.setComment(
				"Autor. Persona o personas que contribuyeron con su conocimiento intelectual y de manera significativa al contenido del sumario");
		if (row[37] != null) {
			author.setValue(row[37].toString());
		}
		Reviewer reviewer = new Reviewer();
		reviewer.setComment(
				"Revisor. Persona o personas que revisaron o examinaron cuidadosamente el contenido del sumario");
		if (row[39] != null) {
			reviewer.setValue(row[39].toString());
		}
		Editor editor = new Editor();
		editor.setComment(
				"Editor. Persona o personas que contribuyeron a corregir y adaptar el contenido del sumario para uso del público objetivo");
		if (row[38] != null) {
			editor.setValue(row[38].toString());
		}
		Collaborator collaborator = new Collaborator();
		collaborator.setComment(
				"Colaborador. Persona que trabajó con el o los autores en la realización del sumario sin aportar conocimiento sino esfuerzo en sistematizar y digitalizar la información");
		if (row[40] != null) {
			collaborator.setValue(row[40].toString());
		}
		PublicationYear publicationYear = new PublicationYear();
		publicationYear.setComment("Año de publicación del sumario de la especie");
		if (row[41] != null) {
			publicationYear.setValue(((Integer) row[41]).toString());
		}
		Language language = new Language();
		language.setComment(
				"Idioma utilizado para escribir la información del sumario de la especie, se utiliza el código ISO 639-2 (https://www.loc.gov/standards/iso639-2/php/code_list.php)");
		language.setValue("SPA");
		RightsHolder rightsHolder = new RightsHolder();
		rightsHolder.setComment(
				"Nombre de la Institución con los derechos de propiedad o administracion del sumario");
		rightsHolder.setValue("Todos los derechos reservados - Ministerio del Ambiente y Agua, 2020");
		AccessRights accessRights = new AccessRights();
		accessRights.setComment(
				"Nombre de la Institución con los derechos de propiedad o administracion del sumario");
		if (row[42] != null) {
			accessRights.setValue(row[42].toString());
		}
		License license = new License();
		license.setComment(
				"Declaración de permiso oficial para hacer uso del sumario y su información");
		license.setValue("https://creativecommons.org/licenses/by-sa/4.0/");
		Version version = new Version();
		version.setComment("Número y fecha de la versión actual");
		version.setValue("01.2020");
		meta.setAccessRights(accessRights);
		meta.setAuthor(author);
		meta.setCollaborator(collaborator);
		meta.setEditor(editor);
		meta.setLanguage(language);
		meta.setLicense(license);
		meta.setPublicationYear(publicationYear);
		meta.setReviewer(reviewer);
		meta.setRightsHolder(rightsHolder);
		meta.setVersion(version);
		sumarioG.setMetadata(meta);
		Taxon taxon = new Taxon();
		Gui guiS = new Gui();
		guiS.setComment(
				"Global Unique Identifier. Código único nacional del Catálogo Nacional de Objetos Biológicos");
		if (row[5] != null) {
			guiS.setValue(row[5].toString());
		}
		Kingdom kingdom = new Kingdom();
		kingdom.setComment("El nombre científico completo del reino en el que se clasifica el taxón");
		Phylum phylum = new Phylum();
		phylum.setComment("El nombre científico completo del phylum en el que se clasifica el taxón");
		Clas clas = new Clas();
		clas.setComment("El nombre científico completo de la clase en el que se clasifica el taxón");
		Order order = new Order();
		order.setComment("El nombre científico completo del orden en el que se clasifica el taxón");
		Family family = new Family();
		family.setComment(
				"El nombre científico completo de la familia en el que se clasifica el taxón");
		Genus genus = new Genus();
		genus.setComment("El nombre científico completo del género en el que se clasifica el taxón");

		JSONParser parser = new JSONParser();
		try {
			if (row[12] != null) {
				JSONObject json = (JSONObject) parser.parse(row[12].toString());
				if(json.get("Reino")!=null)
				{
				kingdom.setValue(json.get("Reino").toString());
				}
				if(json.get("Phylum")!=null)
				{
				phylum.setValue(json.get("Phylum").toString());
				}
				if(json.get("Clase")!=null)
				{
				clas.setValue(json.get("Clase").toString());
				}
				if(json.get("Orden")!=null)
				{
				order.setValue(json.get("Orden").toString());
				}
				if(json.get("Familia")!=null)
				{
				family.setValue(json.get("Familia").toString());
				}
				if(json.get("Género")!=null)
				{
				genus.setValue(json.get("Género").toString());
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SpecificInfraspecificEpithet specificInfraspecificEpithet = new SpecificInfraspecificEpithet();
		specificInfraspecificEpithet.setComment(
				"El nombre científico completo del epíteto específico y el epíteto infraespecífico (de ser el caso) en el rango taxonómico más bajo");
		if (row[13] != null) {
			specificInfraspecificEpithet.setValue(row[13].toString());
		}
		ScientificName scientificName = new ScientificName();
		scientificName.setComment("El nombre científico completo, con la autoría y año");
		if (row[14] != null) {
			scientificName.setValue(row[14].toString());
		}
		TaxonRank taxonRank = new TaxonRank();
		taxonRank.setComment(
				"El rango taxonómico del nombre más específico provisto en el campo scientificName. Se usa vocabulario controlado (Reino, Phylum, Clase, Orden, Familia, Genero, especie, subespecie, variedad, forma)");
		if (row[3] != null) {
			taxonRank.setValue(row[3].toString());
		}
		ScientificNameAuthorship scientificNameAuthorship = new ScientificNameAuthorship();
		scientificNameAuthorship.setComment("Información de autoría del nombre científico");
		if (row[6] != null) {
			scientificNameAuthorship.setValue(row[6].toString());
		}
		NamePublishedInYear namePublishedInYear = new NamePublishedInYear();
		namePublishedInYear
				.setComment("El año de cuatro dígitos en el que se publicó el nombre científico");
		if (row[7] != null) {
			namePublishedInYear.setValue(row[7].toString());
		}
		InEcuador inEcuador = new InEcuador();
		inEcuador.setComment("Es una especie registrada en Ecuador");
		if (row[14] != null) {
			inEcuador.setValue((Boolean) row[14]);
		}
		IsNative isNative = new IsNative();
		isNative.setComment("Es una especie nativa");
		if (row[18] != null) {
			isNative.setValue((Boolean) row[18]);
		}
		IsEndemic isEndemic = new IsEndemic();
		isEndemic.setComment("Es una especie endémica");
		if (row[15] != null) {
			isEndemic.setValue((Boolean) row[15]);
		}
		IsExotic isExotic = new IsExotic();
		isExotic.setComment("Es una especie exótica");
		if (row[16] != null) {
			isExotic.setValue((Boolean) row[16]);
		}
		IsInvasive isInvasive = new IsInvasive();
		isInvasive.setComment("Es una especie invasora");
		if (row[17] != null) {
			isExotic.setValue((Boolean) row[17]);
		}
		IsDomestic isDomestic = new IsDomestic();
		isDomestic.setComment(
				"En caso de flora y hongos, el término utilizado es cultivada. Doméstico aplica para animales");
		if (row[20] != null) {
			isDomestic.setValue((Boolean) row[20]);
		}
		IsMigratory isMigratory = new IsMigratory();
		if (row[19] != null) {
			isMigratory.setValue((Boolean) row[19]);
		}
		TaxonomicStatus taxonomicStatus = new TaxonomicStatus();
		if (row[2] != null) {
			taxonomicStatus.setValue(row[2].toString());
		}
		IsCorrectName isCorrectName = new IsCorrectName();
		if (row[0] != null) {
			isCorrectName.setValue((Boolean) row[0]);
		}
		CorrectScientificName correctScientificName = new CorrectScientificName();
		if (row[1] != null) {
			correctScientificName.setValue(row[1].toString());
		}
		List<ArtificialGroup> listArt = new ArrayList<>();
		JSONParser parserArt = new JSONParser();
		try {
			if (row[33] != null) {

				JSONArray json1 = (JSONArray) parserArt.parse(row[33].toString());
				for (int i = 0; i < json1.size(); i++) {
					ArtificialGroup art = new ArtificialGroup();
					String jsonObj = (String) (json1.get(i));
					art.setValue(jsonObj);
					listArt.add(art);
				}

			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArtificialGroups artificialGroups = new ArtificialGroups();
		artificialGroups.setArtificialGroup(listArt);
		taxon.setArtificialGroups(artificialGroups);
		taxon.setClas(clas);
		taxon.setCorrectScientificName(correctScientificName);
		taxon.setFamily(family);
		taxon.setGenus(genus);
		taxon.setGui(guiS);
		taxon.setInEcuador(inEcuador);
		taxon.setIsCorrectName(isCorrectName);
		taxon.setIsDomestic(isDomestic);
		taxon.setIsEndemic(isEndemic);
		taxon.setIsExotic(isExotic);
		taxon.setIsInvasive(isInvasive);
		taxon.setIsMigratory(isMigratory);
		taxon.setIsNative(isNative);
		taxon.setKingdom(kingdom);
		taxon.setNamePublishedInYear(namePublishedInYear);
		taxon.setOrder(order);
		taxon.setPhylum(phylum);
		taxon.setScientificName(scientificName);
		taxon.setScientificNameAuthorship(scientificNameAuthorship);
		taxon.setSpecificInfraspecificEpithet(specificInfraspecificEpithet);
		taxon.setTaxonomicStatus(taxonomicStatus);
		taxon.setTaxonRank(taxonRank);
		sumarioG.setTaxon(taxon);
		Description description = new Description();
		General general = new General();
		if (row[24] != null) {
			general.setValue(row[24].toString());
		}
		description.setGeneral(general);
		sumarioG.setDescription(description);
		List<VernacularN> listVern = new ArrayList<>();
		JSONParser parserVern = new JSONParser();
		try {
			if (row[8] != null) {

				JSONArray json1 = (JSONArray) parserVern.parse(row[8].toString());
				for (int i = 0; i < json1.size(); i++) {
					VernacularN art = new VernacularN();
					VernacularName verName = new VernacularName();
					Locality locality = new Locality();
					LanguageV languageV = new LanguageV();
					Source source = new Source();
					JSONObject json = (JSONObject) (json1.get(i));
					if(json.get("nombre")!=null)
					{
					verName.setValue(json.get("nombre").toString());
					}
					if(json.get("grupo_etnico")!=null)
					{
					locality.setValue(json.get("grupo_etnico").toString());
					}
					if(json.get("lenguaje")!=null)
					{
					languageV.setValue(json.get("lenguaje").toString());
					}
					if(json.get("citacorta")!=null)
					{
					source.setValue(json.get("citacorta").toString());
					}
					art.setLanguage(languageV);
					art.setLocality(locality);
					art.setSource(source);
					art.setVernacularName(verName);
					listVern.add(art);

				}

			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		VernacularNames vNames = new VernacularNames();
		vNames.setVernacularN(listVern);
		sumarioG.setVernacularNames(vNames);
		List<Synonym> listSin = new ArrayList<>();
		JSONParser parserSino = new JSONParser();
		try {
			if (row[25] != null) {

				JSONArray json1 = (JSONArray) parserSino.parse(row[25].toString());
				for (int i = 0; i < json1.size(); i++) {
					Synonym synonym = new Synonym();
					GuiSin guiSin = new GuiSin();
					ScientificNameSin scientificNameSin = new ScientificNameSin();
					JSONObject json = (JSONObject) (json1.get(i));
					if(json.get("spta_scientific_name")!=null)
					{
					guiSin.setValue(json.get("spta_scientific_name").toString());
					}
					if(json.get("spta_scientific_gui")!=null)
					{
					scientificNameSin.setValue(json.get("spta_scientific_gui").toString());
					}
					synonym.setGui(guiSin);
					synonym.setScientificName(scientificNameSin);
					listSin.add(synonym);

				}

			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Synonyms synonyms = new Synonyms();
		synonyms.setSynonym(listSin);
		sumarioG.setSynonyms(synonyms);
		List<Habit> listHab = new ArrayList<>();
		JSONParser parserHab = new JSONParser();
		try {
			if (row[28] != null) {

				JSONArray json1 = (JSONArray) parserHab.parse(row[28].toString());
				for (int i = 0; i < json1.size(); i++) {
					Habit habit = new Habit();
					HabitName habitName = new HabitName();
					SourceHab sourceHab = new SourceHab();
					JSONObject json = (JSONObject) (json1.get(i));
					if(json.get("nombre")!=null)
					{
					habitName.setValue(json.get("nombre").toString());
					}
					if(json.get("fuente")!=null)
					{
					sourceHab.setValue(json.get("fuente").toString());
					}
					habit.setHabit(habitName);
					habit.setSource(sourceHab);
					listHab.add(habit);

				}

			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Habits habits = new Habits();
		habits.setHabit(listHab);
		sumarioG.setHabits(habits);
		
		Distribution distribution = new Distribution();
		DistributionDis distributionDis = new DistributionDis();
		AltitudeRange altitudeRange = new AltitudeRange();
		Provinces provinces = new Provinces();
		ProtectedAreas protectedAreas = new ProtectedAreas();
		Ecosystems ecosystems = new Ecosystems();
		if (row[26] != null) {
			distributionDis.setValue(row[26].toString());
		}
		if (row[27] != null) {
			altitudeRange.setValue(row[27].toString());
		}
		List<Province> listProv = new ArrayList<>();
		JSONParser parserProv = new JSONParser();
		try {
			if (row[32] != null) {

				JSONArray json1 = (JSONArray) parserProv.parse(row[32].toString());
				for (int i = 0; i < json1.size(); i++) {
					Province prov = new Province();
					String jsonObj = (String) (json1.get(i));
					prov.setValue(jsonObj);
					listProv.add(prov);
				}

			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		provinces.setProvince(listProv);
		List<ProtectedArea> listProt = new ArrayList<>();
		JSONParser parserProt = new JSONParser();
		try {
			if (row[34] != null) {

				JSONArray json1 = (JSONArray) parserProt.parse(row[34].toString());
				for (int i = 0; i < json1.size(); i++) {
					ProtectedArea protect = new ProtectedArea();
					String jsonObj = (String) (json1.get(i));
					protect.setValue(jsonObj);
					listProt.add(protect);
				}

			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		protectedAreas.setProtectedArea(listProt);
		List<Ecosystem> listEcos = new ArrayList<>();
		JSONParser parserEcos = new JSONParser();
		try {
			if (row[35] != null) {

				JSONArray json1 = (JSONArray) parserEcos.parse(row[35].toString());
				for (int i = 0; i < json1.size(); i++) {
					Ecosystem ecos = new Ecosystem();
					String jsonObj = (String) (json1.get(i));
					ecos.setValue(jsonObj);
					listEcos.add(ecos);
				}

			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ecosystems.setEcosystem(listEcos);
		distribution.setDistribution(distributionDis);
		distribution.setAltitudeRange(altitudeRange);
		distribution.setProvinces(provinces);
		distribution.setProtectedAreas(protectedAreas);
		distribution.setEcosystems(ecosystems);
		sumarioG.setDistribution(distribution);
		Conservation conservation = new Conservation();
		ThreatStatus threatStatus = new ThreatStatus();
		RedListEcCriteria redListEcCriteria= new RedListEcCriteria();
		AppendixCITES appendixCITES = new AppendixCITES();
		ConservationMeasure conservationMeasure= new ConservationMeasure();
		if (row[21] != null) {
			threatStatus.setValue(row[21].toString());
		}
		if (row[30] != null) {
			redListEcCriteria.setValue(row[30].toString());
		}
		if (row[31] != null) {
			appendixCITES.setValue(row[31].toString());
		}
		if (row[29] != null) {
			conservationMeasure.setValue(row[29].toString());
		}
		conservation.setAppendixCITES(appendixCITES);
		conservation.setConservationMeasure(conservationMeasure);
		conservation.setRedListEcCriteria(redListEcCriteria);
		conservation.setThreatStatus(threatStatus);
		sumarioG.setConservation(conservation);
		LiteratureReferences literatureReferences= new LiteratureReferences();
		List<LiteratureReference> liteList=new ArrayList<>();
		JSONParser parserF = new JSONParser();
		try {
			if (row[23] != null) {
				JSONArray json = (JSONArray) parserF.parse(row[23].toString());
				for (int i = 0; i < json.size(); i++) {
					JSONObject jsonObjeto1 = (JSONObject) (json.get(i));
					JSONArray json1 = (JSONArray) parserF.parse(jsonObjeto1.get("det_fuentes").toString());
					System.out.println(i);
					for (int k = 0; k < json1.size(); k++) {
						LiteratureReference lite = new LiteratureReference();
						Type typeL = new Type();
						Url url = new Url();
						Principal principal= new Principal();
						BibliographicCitation bibliographicCitation = new BibliographicCitation();
						JSONObject jsonObjeto = (JSONObject) (json1.get(k));
						if(jsonObjeto.get("bigc_description")!=null)
						{
						typeL.setValue(jsonObjeto.get("bigc_description").toString());
						}
						if(jsonObjeto.get("url")!=null)
						{
						url.setValue(jsonObjeto.get("url").toString());
						}
						if(jsonObjeto.get("stis_principal")!=null)
						{
						principal.setValue(jsonObjeto.get("stis_principal").toString());
						}
						if(jsonObjeto.get("nombre")!=null)
						{
						bibliographicCitation.setValue(jsonObjeto.get("nombre").toString());
						}
						lite.setBibliographicCitation(bibliographicCitation);
						lite.setPrincipal(principal);
						lite.setUrl(url);
						lite.setType(typeL);
						liteList.add(lite);
					}
				}
				
				
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		literatureReferences.setLiteratureReference(liteList);
		sumarioG.setLiteratureReferences(literatureReferences);
		Images images= new Images();
		List<Image> imageList=new ArrayList<>();
		JSONParser parserI = new JSONParser();
		try {
			if (row[9] != null) {
				JSONArray json1 = (JSONArray) parserI.parse(row[9].toString());
				for (int i = 0; i < json1.size(); i++) {
					Image image = new Image();
					TypeIma typeI = new TypeIma();
					Title title = new Title();
					Creator creator= new Creator();
					DescriptionIma descriptionI = new DescriptionIma();
					JSONObject jsonObjeto = (JSONObject) (json1.get(i));
					typeI.setValue("Principal");
					if(jsonObjeto.get("titulo")!=null)
					{
					title.setValue(jsonObjeto.get("titulo").toString());
					}
					if(jsonObjeto.get("autor")!=null)
					{
					creator.setValue(jsonObjeto.get("autor").toString());
					}
					if(jsonObjeto.get("spph_description")!=null)
					{
					descriptionI.setValue(jsonObjeto.get("spph_description").toString());
					}
					Identifier iden=new Identifier();
					if(jsonObjeto.get("spph_alfresco_id")!=null)
					{
						byte [] imgArr=getImageAlfresco(jsonObjeto.get("spph_alfresco_id").toString());
						if(imgArr!=null)
						{
							iden.setValue(Base64.encode(imgArr));
						}
					
					}
					image.setIdentifier(iden);
					image.setCreator(creator);
					image.setDescription(descriptionI);
					image.setTitle(title);
					image.setType(typeI);
					imageList.add(image);
				}
				
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			if (row[10] != null) {
				JSONArray json1 = (JSONArray) parserI.parse(row[10].toString());
				for (int i = 0; i < json1.size(); i++) {
					Image image = new Image();
					TypeIma typeI = new TypeIma();
					Title title = new Title();
					Creator creator= new Creator();
					DescriptionIma descriptionI = new DescriptionIma();
					JSONObject jsonObjeto = (JSONObject) (json1.get(i));
					typeI.setValue("Secundaria");
					if(jsonObjeto.get("titulo")!=null)
					{
					title.setValue(jsonObjeto.get("titulo").toString());
					}
					if(jsonObjeto.get("autor")!=null)
					{
					creator.setValue(jsonObjeto.get("autor").toString());
					}
					if(jsonObjeto.get("spph_description")!=null)
					{
					descriptionI.setValue(jsonObjeto.get("spph_description").toString());
					}
					Identifier iden=new Identifier();
					if(jsonObjeto.get("spph_alfresco_id")!=null)
					{
						byte [] imgArr=getImageAlfresco(jsonObjeto.get("spph_alfresco_id").toString());
						if(imgArr!=null)
						{
							iden.setValue(Base64.encode(imgArr));
						}
					
					}
					image.setIdentifier(iden);
					image.setCreator(creator);
					image.setDescription(descriptionI);
					image.setTitle(title);
					image.setType(typeI);
					imageList.add(image);
				}
				
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			if (row[11] != null) {
				JSONArray json1 = (JSONArray) parserI.parse(row[11].toString());
				for (int i = 0; i < json1.size(); i++) {
					Image image = new Image();
					TypeIma typeI = new TypeIma();
					Title title = new Title();
					Creator creator= new Creator();
					DescriptionIma descriptionI = new DescriptionIma();
					JSONObject jsonObjeto = (JSONObject) (json1.get(i));
					typeI.setValue("Mapa");
					if(jsonObjeto.get("titulo")!=null)
					{
					title.setValue(jsonObjeto.get("titulo").toString());
					}
					if(jsonObjeto.get("autor")!=null)
					{
					creator.setValue(jsonObjeto.get("autor").toString());
					}
					if(jsonObjeto.get("description")!=null)
					{
					descriptionI.setValue(jsonObjeto.get("description").toString());
					}
					Identifier iden=new Identifier();
					if(jsonObjeto.get("spdm_alfresco_id")!=null)
					{
						byte [] imgArr=getImageAlfresco(jsonObjeto.get("spdm_alfresco_id").toString());
						if(imgArr!=null)
						{
							iden.setValue(Base64.encode(imgArr));
						}
					
					}
					image.setIdentifier(iden);
					image.setCreator(creator);
					image.setDescription(descriptionI);
					image.setTitle(title);
					image.setType(typeI);
					imageList.add(image);
				}
				
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		images.setImage(imageList);
		sumarioG.setImages(images);
		return sumarioG;
	}

	
	public SumForeSpResult obtenerSumarioForestal(Object[] row)
	{
		SumForeSpResult sumarioF = new SumForeSpResult();
		Metadata meta = new Metadata();
		meta.setComment(
				"El Ministerio del Ambiente y Agua se reserva el derecho de modificar sin previo aviso el listado de especies con información disponible, el contenido del sumario, y/o la estructura del servicio web. Para información actualizada se puede consultar en http://suia.ambiente.gob.ec");
		Author author = new Author();
		author.setComment(
				"Autor. Persona o personas que contribuyeron con su conocimiento intelectual y de manera significativa al contenido del sumario");
		if (row[70] != null) {
			author.setValue(row[70].toString());
		}
		Reviewer reviewer = new Reviewer();
		reviewer.setComment(
				"Revisor. Persona o personas que revisaron o examinaron cuidadosamente el contenido del sumario");
		if (row[73] != null) {
			reviewer.setValue(row[73].toString());
		}
		Editor editor = new Editor();
		editor.setComment(
				"Editor. Persona o personas que contribuyeron a corregir y adaptar el contenido del sumario para uso del público objetivo");
		if (row[72] != null) {
			editor.setValue(row[72].toString());
		}
		Collaborator collaborator = new Collaborator();
		collaborator.setComment(
				"Colaborador. Persona que trabajó con el o los autores en la realización del sumario sin aportar conocimiento sino esfuerzo en sistematizar y digitalizar la información");
		if (row[74] != null) {
			collaborator.setValue(row[74].toString());
		}
		PublicationYear publicationYear = new PublicationYear();
		publicationYear.setComment("Año de publicación del sumario de la especie");
		if (row[71] != null) {
			publicationYear.setValue(((Integer) row[71]).toString());
		}
		Language language = new Language();
		language.setComment(
				"Idioma utilizado para escribir la información del sumario de la especie, se utiliza el código ISO 639-2 (https://www.loc.gov/standards/iso639-2/php/code_list.php)");
		language.setValue("SPA");
		RightsHolder rightsHolder = new RightsHolder();
		rightsHolder.setComment(
				"Nombre de la Institución con los derechos de propiedad o administracion del sumario");
		rightsHolder.setValue("Todos los derechos reservados - Ministerio del Ambiente y Agua, 2020");
		AccessRights accessRights = new AccessRights();
		accessRights.setComment(
				"Nombre de la Institución con los derechos de propiedad o administracion del sumario");
		if (row[75] != null) {
			accessRights.setValue(row[75].toString());
		}
		License license = new License();
		license.setComment(
				"Declaración de permiso oficial para hacer uso del sumario y su información");
		license.setValue("https://creativecommons.org/licenses/by-sa/4.0/");
		Version version = new Version();
		version.setComment("Número y fecha de la versión actual");
		version.setValue("01.2020");
		meta.setAccessRights(accessRights);
		meta.setAuthor(author);
		meta.setCollaborator(collaborator);
		meta.setEditor(editor);
		meta.setLanguage(language);
		meta.setLicense(license);
		meta.setPublicationYear(publicationYear);
		meta.setReviewer(reviewer);
		meta.setRightsHolder(rightsHolder);
		meta.setVersion(version);
		sumarioF.setMetadata(meta);
		Taxon taxon = new Taxon();
		Gui guiS = new Gui();
		guiS.setComment(
				"Global Unique Identifier. Código único nacional del Catálogo Nacional de Objetos Biológicos");
		if (row[7] != null) {
			guiS.setValue(row[7].toString());
		}
		Kingdom kingdom = new Kingdom();
		kingdom.setComment("El nombre científico completo del reino en el que se clasifica el taxón");
		Phylum phylum = new Phylum();
		phylum.setComment("El nombre científico completo del phylum en el que se clasifica el taxón");
		Clas clas = new Clas();
		clas.setComment("El nombre científico completo de la clase en el que se clasifica el taxón");
		Order order = new Order();
		order.setComment("El nombre científico completo del orden en el que se clasifica el taxón");
		Family family = new Family();
		family.setComment(
				"El nombre científico completo de la familia en el que se clasifica el taxón");
		Genus genus = new Genus();
		genus.setComment("El nombre científico completo del género en el que se clasifica el taxón");

		JSONParser parser = new JSONParser();
		try {
			if (row[8] != null) {
				JSONObject json = (JSONObject) parser.parse(row[8].toString());
				if(json.get("Reino")!=null)
				{
				kingdom.setValue(json.get("Reino").toString());
				}
				if(json.get("Phylum")!=null)
				{
				phylum.setValue(json.get("Phylum").toString());
				}
				if(json.get("Clase")!=null)
				{
				clas.setValue(json.get("Clase").toString());
				}
				if(json.get("Orden")!=null)
				{
				order.setValue(json.get("Orden").toString());
				}
				if(json.get("Familia")!=null)
				{
				family.setValue(json.get("Familia").toString());
				}
				if(json.get("Género")!=null)
				{
				genus.setValue(json.get("Género").toString());
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SpecificInfraspecificEpithet specificInfraspecificEpithet = new SpecificInfraspecificEpithet();
		specificInfraspecificEpithet.setComment(
				"El nombre científico completo del epíteto específico y el epíteto infraespecífico (de ser el caso) en el rango taxonómico más bajo");
		if (row[9] != null) {
			specificInfraspecificEpithet.setValue(row[9].toString());
		}
		ScientificName scientificName = new ScientificName();
		scientificName.setComment("El nombre científico completo, con la autoría y año");
		if (row[4] != null) {
			scientificName.setValue(row[4].toString());
		}
		TaxonRank taxonRank = new TaxonRank();
		taxonRank.setComment(
				"El rango taxonómico del nombre más específico provisto en el campo scientificName. Se usa vocabulario controlado (Reino, Phylum, Clase, Orden, Familia, Genero, especie, subespecie, variedad, forma)");
		if (row[3] != null) {
			taxonRank.setValue(row[3].toString());
		}
		ScientificNameAuthorship scientificNameAuthorship = new ScientificNameAuthorship();
		scientificNameAuthorship.setComment("Información de autoría del nombre científico");
		if (row[5] != null) {
			scientificNameAuthorship.setValue(row[5].toString());
		}
		NamePublishedInYear namePublishedInYear = new NamePublishedInYear();
		namePublishedInYear
				.setComment("El año de cuatro dígitos en el que se publicó el nombre científico");
		if (row[6] != null) {
			namePublishedInYear.setValue(row[6].toString());
		}
		InEcuador inEcuador = new InEcuador();
		inEcuador.setComment("Es una especie registrada en Ecuador");
		if (row[24] != null) {
			inEcuador.setValue((Boolean) row[24]);
		}
		IsNative isNative = new IsNative();
		isNative.setComment("Es una especie nativa");
		if (row[22] != null) {
			isNative.setValue((Boolean) row[22]);
		}
		IsEndemic isEndemic = new IsEndemic();
		isEndemic.setComment("Es una especie endémica");
		if (row[19] != null) {
			isEndemic.setValue((Boolean) row[19]);
		}
		IsExotic isExotic = new IsExotic();
		isExotic.setComment("Es una especie exótica");
		if (row[20] != null) {
			isExotic.setValue((Boolean) row[20]);
		}
		IsInvasive isInvasive = new IsInvasive();
		isInvasive.setComment("Es una especie invasora");
		if (row[21] != null) {
			isExotic.setValue((Boolean) row[21]);
		}
		IsDomestic isDomestic = new IsDomestic();
		isDomestic.setComment(
				"En caso de flora y hongos, el término utilizado es cultivada. Doméstico aplica para animales");
		if (row[25] != null) {
			isDomestic.setValue((Boolean) row[25]);
		}
		IsMigratory isMigratory = new IsMigratory();
		if (row[23] != null) {
			isMigratory.setValue((Boolean) row[23]);
		}
		TaxonomicStatus taxonomicStatus = new TaxonomicStatus();
		if (row[2] != null) {
			taxonomicStatus.setValue(row[2].toString());
		}
		IsCorrectName isCorrectName = new IsCorrectName();
		if (row[0] != null) {
			isCorrectName.setValue((Boolean) row[0]);
		}
		CorrectScientificName correctScientificName = new CorrectScientificName();
		if (row[1] != null) {
			correctScientificName.setValue(row[1].toString());
		}
		List<ArtificialGroup> listArt = new ArrayList<>();
		JSONParser parserArt = new JSONParser();
		try {
			if (row[76] != null) {

				JSONArray json1 = (JSONArray) parserArt.parse(row[76].toString());
				for (int i = 0; i < json1.size(); i++) {
					ArtificialGroup art = new ArtificialGroup();
					String jsonObj = (String) (json1.get(i));
					art.setValue(jsonObj);
					listArt.add(art);
				}

			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArtificialGroups artificialGroups = new ArtificialGroups();
		artificialGroups.setArtificialGroup(listArt);
		taxon.setArtificialGroups(artificialGroups);
		taxon.setClas(clas);
		taxon.setCorrectScientificName(correctScientificName);
		taxon.setFamily(family);
		taxon.setGenus(genus);
		taxon.setGui(guiS);
		taxon.setInEcuador(inEcuador);
		taxon.setIsCorrectName(isCorrectName);
		taxon.setIsDomestic(isDomestic);
		taxon.setIsEndemic(isEndemic);
		taxon.setIsExotic(isExotic);
		taxon.setIsInvasive(isInvasive);
		taxon.setIsMigratory(isMigratory);
		taxon.setIsNative(isNative);
		taxon.setKingdom(kingdom);
		taxon.setNamePublishedInYear(namePublishedInYear);
		taxon.setOrder(order);
		taxon.setPhylum(phylum);
		taxon.setScientificName(scientificName);
		taxon.setScientificNameAuthorship(scientificNameAuthorship);
		taxon.setSpecificInfraspecificEpithet(specificInfraspecificEpithet);
		taxon.setTaxonomicStatus(taxonomicStatus);
		taxon.setTaxonRank(taxonRank);
		sumarioF.setTaxon(taxon);
		DescriptionForestal description = new DescriptionForestal();
		General general = new General();
		if (row[12] != null) {
			general.setValue(row[12].toString());
		}
		
		BotanicalDescription botanicalDescription = new BotanicalDescription();
		if (row[13] != null) {
			botanicalDescription.setValue(row[13].toString());
		}
		
		ConditionalUse conditionalUse= new ConditionalUse();
		if (row[32] != null) {
			conditionalUse.setValue(row[32].toString());
		}
		
		SimilarSpecies similarSpecies = new SimilarSpecies();
		if (row[14] != null) {
			similarSpecies.setValue(row[14].toString());
		}
		
		Ecology ecology= new Ecology();
		if (row[36] != null) {
			ecology.setValue(row[36].toString());
		}
		
		Uses uses= new Uses();
		if (row[37] != null) {
			uses.setValue(row[37].toString());
		}
		
		VolumenSafAnalysis volumenSafAnalysis= new VolumenSafAnalysis();
		if (row[39] != null) {
			volumenSafAnalysis.setValue(row[39].toString());
		}	
		
		BotanicalWoodCollections botanicalWoodCollections= new BotanicalWoodCollections();
		if (row[41] != null) {
			botanicalWoodCollections.setValue(row[41].toString());
		}
		
		description.setGeneral(general);
		description.setBotanicalDescription(botanicalDescription);
		description.setBotanicalWoodCollections(botanicalWoodCollections);
		description.setConditionalUse(conditionalUse);
		description.setEcology(ecology);
		description.setSimilarSpecies(similarSpecies);
		description.setUses(uses);
		description.setVolumenSafAnalysis(volumenSafAnalysis);
		sumarioF.setDescription(description);
		OrganolepticCharacteristics organolepticCharacteristics= new OrganolepticCharacteristics();
		HardnessOrWeight hardnessOrWeight= new HardnessOrWeight();
		if (row[42] != null) {
			hardnessOrWeight.setValue(row[42].toString());
		}
		organolepticCharacteristics.setHardnessOrWeight(hardnessOrWeight);
		CrossCutRollizaWood crossCutRollizaWood= new CrossCutRollizaWood();
		ColorHeartwood colorHeartwood= new ColorHeartwood();
		if (row[43] != null) {
			colorHeartwood.setValue(row[43].toString());
		}
		Odor odor= new Odor();
		if (row[44] != null) {
			odor.setValue(row[44].toString());
		}
		
		Taste taste= new Taste();
		if (row[45] != null) {
			taste.setValue(row[45].toString());
		}
		
		LatexResin latexResin= new LatexResin();
		if (row[46] != null) {
			latexResin.setValue(row[46].toString());
		}
		
		OuterCortex outerCortex= new OuterCortex();
		if (row[47] != null) {
			outerCortex.setValue(row[47].toString());
		}
		 crossCutRollizaWood.setColorHeartwood(colorHeartwood);
		 crossCutRollizaWood.setLatexResin(latexResin);
		 crossCutRollizaWood.setOdor(odor);
		 crossCutRollizaWood.setOuterCortex(outerCortex);
		 crossCutRollizaWood.setTaste(taste);
		 organolepticCharacteristics.setCrossCutRollizaWood(crossCutRollizaWood);
		 
		 TangentalCutSawnWood tangentalCutSawnWood= new TangentalCutSawnWood();
		 Color color= new Color();
		 if (row[48] != null) {
				color.setValue(row[48].toString());
			}
		 Veined veined= new Veined();
		 if (row[49] != null) {
			 veined.setValue(row[49].toString());
			}
		 
		 Grain grain= new Grain();
		 if (row[50] != null) {
			 grain.setValue(row[50].toString());
			}
		 
		 Texture texture= new Texture();
		 if (row[51] != null) {
			 texture.setValue(row[51].toString());
			}
		 
		 tangentalCutSawnWood.setColor(color);
		 tangentalCutSawnWood.setGrain(grain);
		 tangentalCutSawnWood.setTexture(texture);
		 tangentalCutSawnWood.setVeined(veined);
		 organolepticCharacteristics.setTangentalCutSawnWood(tangentalCutSawnWood);
		 
		 RadialCutSawnWood radialCutSawnWood= new RadialCutSawnWood();
		 Veined veinedR= new Veined();
		 if (row[52] != null) {
			 veinedR.setValue(row[52].toString());
			}
		 
		 GlossShine glossShine= new GlossShine();
		 if (row[53] != null) {
			 glossShine.setValue(row[53].toString());
			}
		 radialCutSawnWood.setGlossShine(glossShine);
		 radialCutSawnWood.setVeined(veinedR);
		 organolepticCharacteristics.setRadialCutSawnWood(radialCutSawnWood);
		 sumarioF.setOrganolepticCharacteristics(organolepticCharacteristics);
		 
		 AnatomicalCharacteristics anatomicalCharacteristics= new AnatomicalCharacteristics();
		 CrossCutSawnWood crossCutSawnWood= new CrossCutSawnWood();
		 GrowthRings growthRings = new GrowthRings();
		 if (row[54] != null) {
			 growthRings.setValue(row[54].toString());
			}
		 
		 Pores pores= new Pores();
		 if (row[55] != null) {
			 pores.setValue(row[55].toString());
			}
		 
		 AxialParenchyma axialParenchyma= new AxialParenchyma();
		 if (row[56] != null) {
			 axialParenchyma.setValue(row[56].toString());
			}
		 
		 RadialParenchyma radialParenchyma= new RadialParenchyma();
		 if (row[57] != null) {
			 radialParenchyma.setValue(row[57].toString());
			}
		 
		 crossCutSawnWood.setAxialParenchyma(axialParenchyma);
		 crossCutSawnWood.setGrowthRings(growthRings);
		 crossCutSawnWood.setPores(pores);
		 crossCutSawnWood.setRadialParenchyma(radialParenchyma);
		 anatomicalCharacteristics.setCrossCutSawnWood(crossCutSawnWood);
		 sumarioF.setAnatomicalCharacteristics(anatomicalCharacteristics);
		 PhysicalCharacteristics physicalCharacteristics = new PhysicalCharacteristics();
		 Density density= new Density();
		 InGreen inGreen = new InGreen();
		 if (row[58] != null) {
			 inGreen.setValue(((BigDecimal)row[58]).toString());
			}
		 AirDry airDry= new AirDry();
		 if (row[59] != null) {
			 airDry.setValue(((BigDecimal)row[59]).toString());
			}
		 
		 Anhydrous anhydrous= new Anhydrous();
		 if (row[60] != null) {
			 anhydrous.setValue(((BigDecimal)row[60]).toString());
			}
		 
		 Basic basic= new Basic();
		 if (row[61] != null) {
			 basic.setValue(((BigDecimal)row[61]).toString());
			}
		 
		 density.setAirDry(airDry);
		 density.setAnhydrous(anhydrous);
		 density.setBasic(basic);
		 density.setInGreen(inGreen);
		 physicalCharacteristics.setDensity(density);
		 
		 NormalContraction normalContraction = new NormalContraction();
		 Radial radial= new Radial();
		 if (row[62] != null) {
			 radial.setValue(((BigDecimal)row[62]).toString());
			}
		 
		 Tangential tangential = new Tangential();
		 if (row[63] != null) {
			 tangential.setValue(((BigDecimal)row[63]).toString());
			}
		 
		 Volumetric volumetric= new Volumetric();
		 if (row[64] != null) {
			 volumetric.setValue(((BigDecimal)row[64]).toString());
			}
		 
		 normalContraction.setRadial(radial);
		 normalContraction.setTangential(tangential);
		 normalContraction.setVolumetric(volumetric);
		 physicalCharacteristics.setNormalContraction(normalContraction);
		 
		 TotalContraction totalContraction= new TotalContraction();
		 Radial radialT = new Radial();
		 if (row[65] != null) {
			 radialT.setValue(((BigDecimal)row[65]).toString());
			}
		 
		 Tangential tangentialT = new Tangential();
		 if (row[66] != null) {
			 tangentialT.setValue(((BigDecimal)row[66]).toString());
			}
		 
		 Volumetric volumetricT = new Volumetric();
		 if (row[67] != null) {
			 volumetricT.setValue(((BigDecimal)row[67]).toString());
			}
		 
		 TrRatio trRatio = new TrRatio();
		 if (row[68] != null) {
			 trRatio.setValue(((BigDecimal)row[68]).toString());
			}
		 
		 totalContraction.setRadial(radialT);
		 totalContraction.setTangential(tangentialT);
		 totalContraction.setTrRatio(trRatio);
		 totalContraction.setVolumetric(volumetricT);
		  physicalCharacteristics.setTotalContraction(totalContraction);
		  sumarioF.setPhysicalCharacteristics(physicalCharacteristics);
		List<VernacularN> listVern = new ArrayList<>();
		JSONParser parserVern = new JSONParser();
		try {
			if (row[31] != null) {

				JSONArray json1 = (JSONArray) parserVern.parse(row[31].toString());
				for (int i = 0; i < json1.size(); i++) {
					VernacularN art = new VernacularN();
					VernacularName verName = new VernacularName();
					Locality locality = new Locality();
					LanguageV languageV = new LanguageV();
					Source source = new Source();
					JSONObject json = (JSONObject) (json1.get(i));
					if(json.get("nombre")!=null)
					{
					verName.setValue(json.get("nombre").toString());
					}
					if(json.get("grupo_etnico")!=null)
					{
					locality.setValue(json.get("grupo_etnico").toString());
					}
					if(json.get("lenguaje")!=null)
					{
					languageV.setValue(json.get("lenguaje").toString());
					}
					if(json.get("citacorta")!=null)
					{
					source.setValue(json.get("citacorta").toString());
					}
					art.setLanguage(languageV);
					art.setLocality(locality);
					art.setSource(source);
					art.setVernacularName(verName);
					listVern.add(art);

				}

			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		VernacularNames vNames = new VernacularNames();
		vNames.setVernacularN(listVern);
		sumarioF.setVernacularNames(vNames);
		List<Synonym> listSin = new ArrayList<>();
		JSONParser parserSino = new JSONParser();
		try {
			if (row[10] != null) {

				JSONArray json1 = (JSONArray) parserSino.parse(row[10].toString());
				for (int i = 0; i < json1.size(); i++) {
					Synonym synonym = new Synonym();
					GuiSin guiSin = new GuiSin();
					ScientificNameSin scientificNameSin = new ScientificNameSin();
					JSONObject json = (JSONObject) (json1.get(i));
					if(json.get("spta_scientific_name")!=null)
					{
					guiSin.setValue(json.get("spta_scientific_name").toString());
					}
					if(json.get("spta_scientific_gui")!=null)
					{
					scientificNameSin.setValue(json.get("spta_scientific_gui").toString());
					}
					synonym.setGui(guiSin);
					synonym.setScientificName(scientificNameSin);
					listSin.add(synonym);

				}

			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Synonyms synonyms = new Synonyms();
		synonyms.setSynonym(listSin);
		sumarioF.setSynonyms(synonyms);
		List<Habit> listHab = new ArrayList<>();
		JSONParser parserHab = new JSONParser();
		try {
			if (row[11] != null) {

				JSONArray json1 = (JSONArray) parserHab.parse(row[11].toString());
				for (int i = 0; i < json1.size(); i++) {
					Habit habit = new Habit();
					HabitName habitName = new HabitName();
					SourceHab sourceHab = new SourceHab();
					JSONObject json = (JSONObject) (json1.get(i));
					if(json.get("nombre")!=null)
					{
					habitName.setValue(json.get("nombre").toString());
					}
					if(json.get("fuente")!=null)
					{
					sourceHab.setValue(json.get("fuente").toString());
					}
					habit.setHabit(habitName);
					habit.setSource(sourceHab);
					listHab.add(habit);

				}

			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Habits habits = new Habits();
		habits.setHabit(listHab);
		sumarioF.setHabits(habits);
		
		Distribution distribution = new Distribution();
		DistributionDis distributionDis = new DistributionDis();
		AltitudeRange altitudeRange = new AltitudeRange();
		Provinces provinces = new Provinces();
		ProtectedAreas protectedAreas = new ProtectedAreas();
		Ecosystems ecosystems = new Ecosystems();
		if (row[15] != null) {
			distributionDis.setValue(row[15].toString());
		}
		if (row[28] != null) {
			altitudeRange.setValue(row[28].toString());
		}
		List<Province> listProv = new ArrayList<>();
		JSONParser parserProv = new JSONParser();
		try {
			if (row[29] != null) {

				JSONArray json1 = (JSONArray) parserProv.parse(row[29].toString());
				for (int i = 0; i < json1.size(); i++) {
					Province prov = new Province();
					String jsonObj = (String) (json1.get(i));
					prov.setValue(jsonObj);
					listProv.add(prov);
				}

			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		provinces.setProvince(listProv);
		List<ProtectedArea> listProt = new ArrayList<>();
		JSONParser parserProt = new JSONParser();
		try {
			if (row[30] != null) {

				JSONArray json1 = (JSONArray) parserProt.parse(row[30].toString());
				for (int i = 0; i < json1.size(); i++) {
					ProtectedArea protect = new ProtectedArea();
					String jsonObj = (String) (json1.get(i));
					protect.setValue(jsonObj);
					listProt.add(protect);
				}

			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		protectedAreas.setProtectedArea(listProt);
		List<Ecosystem> listEcos = new ArrayList<>();
		JSONParser parserEcos = new JSONParser();
		try {
			if (row[16] != null) {

				JSONArray json1 = (JSONArray) parserEcos.parse(row[16].toString());
				for (int i = 0; i < json1.size(); i++) {
					Ecosystem ecos = new Ecosystem();
					String jsonObj = (String) (json1.get(i));
					ecos.setValue(jsonObj);
					listEcos.add(ecos);
				}

			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ecosystems.setEcosystem(listEcos);
		distribution.setDistribution(distributionDis);
		distribution.setAltitudeRange(altitudeRange);
		distribution.setProvinces(provinces);
		distribution.setProtectedAreas(protectedAreas);
		distribution.setEcosystems(ecosystems);
		sumarioF.setDistribution(distribution);
		Conservation conservation = new Conservation();
		ThreatStatus threatStatus = new ThreatStatus();
		RedListEcCriteria redListEcCriteria= new RedListEcCriteria();
		AppendixCITES appendixCITES = new AppendixCITES();
		ConservationMeasure conservationMeasure= new ConservationMeasure();
		if (row[26] != null) {
			threatStatus.setValue(row[26].toString());
		}
		if (row[27] != null) {
			redListEcCriteria.setValue(row[27].toString());
		}
		if (row[77] != null) {
			appendixCITES.setValue(row[77].toString());
		}
		if (row[38] != null) {
			conservationMeasure.setValue(row[38].toString());
		}
		conservation.setAppendixCITES(appendixCITES);
		conservation.setConservationMeasure(conservationMeasure);
		conservation.setRedListEcCriteria(redListEcCriteria);
		conservation.setThreatStatus(threatStatus);
		sumarioF.setConservation(conservation);
		LiteratureReferences literatureReferences= new LiteratureReferences();
		List<LiteratureReference> liteList=new ArrayList<>();
		JSONParser parserF = new JSONParser();
		try {
			if (row[69] != null) {
				JSONArray json = (JSONArray) parserF.parse(row[69].toString());
				for (int i = 0; i < json.size(); i++) {
					JSONObject jsonObjeto1 = (JSONObject) (json.get(i));
					JSONArray json1 = (JSONArray) parserF.parse(jsonObjeto1.get("det_fuentes").toString());
					for (int k = 0; k < json1.size(); k++) {
						LiteratureReference lite = new LiteratureReference();
						Type typeL = new Type();
						Url url = new Url();
						Principal principal= new Principal();
						BibliographicCitation bibliographicCitation = new BibliographicCitation();
						JSONObject jsonObjeto = (JSONObject) (json1.get(k));
						if(jsonObjeto.get("bigc_description")!=null)
						{
						typeL.setValue(jsonObjeto.get("bigc_description").toString());
						}
						if(jsonObjeto.get("url")!=null)
						{
						url.setValue(jsonObjeto.get("url").toString());
						}
						if(jsonObjeto.get("stis_principal")!=null)
						{
						principal.setValue(jsonObjeto.get("stis_principal").toString());
						}
						if(jsonObjeto.get("nombre")!=null)
						{
						bibliographicCitation.setValue(jsonObjeto.get("nombre").toString());
						}
						lite.setBibliographicCitation(bibliographicCitation);
						lite.setPrincipal(principal);
						lite.setUrl(url);
						lite.setType(typeL);
						liteList.add(lite);
					}
				}
				
				
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		literatureReferences.setLiteratureReference(liteList);
		sumarioF.setLiteratureReferences(literatureReferences);
		Images images= new Images();
		List<Image> imageList=new ArrayList<>();
		JSONParser parserI = new JSONParser();
		try {
			if (row[17] != null) {
				JSONArray json1 = (JSONArray) parserI.parse(row[17].toString());
				for (int i = 0; i < json1.size(); i++) {
					Image image = new Image();
					TypeIma typeI = new TypeIma();
					Title title = new Title();
					Creator creator= new Creator();
					DescriptionIma descriptionI = new DescriptionIma();
					JSONObject jsonObjeto = (JSONObject) (json1.get(i));
					typeI.setValue("Principal");
					if(jsonObjeto.get("titulo")!=null)
					{
					title.setValue(jsonObjeto.get("titulo").toString());
					}
					if(jsonObjeto.get("autor")!=null)
					{
					creator.setValue(jsonObjeto.get("autor").toString());
					}
					if(jsonObjeto.get("spph_description")!=null)
					{
					descriptionI.setValue(jsonObjeto.get("spph_description").toString());
					}
					Identifier iden=new Identifier();
					if(jsonObjeto.get("spph_alfresco_id")!=null)
					{
						byte [] imgArr=getImageAlfresco(jsonObjeto.get("spph_alfresco_id").toString());
						if(imgArr!=null)
						{
							iden.setValue(Base64.encode(imgArr));
						}
					
					}
					image.setIdentifier(iden);
					image.setCreator(creator);
					image.setDescription(descriptionI);
					image.setTitle(title);
					image.setType(typeI);
					imageList.add(image);
				}
				
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			if (row[18] != null) {
				JSONArray json1 = (JSONArray) parserI.parse(row[18].toString());
				for (int i = 0; i < json1.size(); i++) {
					Image image = new Image();
					TypeIma typeI = new TypeIma();
					Title title = new Title();
					Creator creator= new Creator();
					DescriptionIma descriptionI = new DescriptionIma();
					JSONObject jsonObjeto = (JSONObject) (json1.get(i));
					typeI.setValue("Secundaria");
					if(jsonObjeto.get("titulo")!=null)
					{
					title.setValue(jsonObjeto.get("titulo").toString());
					}
					if(jsonObjeto.get("autor")!=null)
					{
					creator.setValue(jsonObjeto.get("autor").toString());
					}
					if(jsonObjeto.get("spph_description")!=null)
					{
					descriptionI.setValue(jsonObjeto.get("spph_description").toString());
					}
					Identifier iden=new Identifier();
					if(jsonObjeto.get("spph_alfresco_id")!=null)
					{
						byte [] imgArr=getImageAlfresco(jsonObjeto.get("spph_alfresco_id").toString());
						if(imgArr!=null)
						{
							iden.setValue(Base64.encode(imgArr));
						}
					
					}
					image.setIdentifier(iden);
					image.setCreator(creator);
					image.setDescription(descriptionI);
					image.setTitle(title);
					image.setType(typeI);
					imageList.add(image);
				}
				
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			if (row[34] != null) {
				JSONArray json1 = (JSONArray) parserI.parse(row[34].toString());
				for (int i = 0; i < json1.size(); i++) {
					Image image = new Image();
					TypeIma typeI = new TypeIma();
					Title title = new Title();
					Creator creator= new Creator();
					DescriptionIma descriptionI = new DescriptionIma();
					JSONObject jsonObjeto = (JSONObject) (json1.get(i));
					typeI.setValue("Mapa");
					if(jsonObjeto.get("titulo")!=null)
					{
					title.setValue(jsonObjeto.get("titulo").toString());
					}
					if(jsonObjeto.get("autor")!=null)
					{
					creator.setValue(jsonObjeto.get("autor").toString());
					}
					if(jsonObjeto.get("description")!=null)
					{
					descriptionI.setValue(jsonObjeto.get("description").toString());
					}
					Identifier iden=new Identifier();
					if(jsonObjeto.get("spdm_alfresco_id")!=null)
					{
						byte [] imgArr=getImageAlfresco(jsonObjeto.get("spdm_alfresco_id").toString());
						if(imgArr!=null)
						{
							iden.setValue(Base64.encode(imgArr));
						}
					
					}
					image.setIdentifier(iden);
					image.setCreator(creator);
					image.setDescription(descriptionI);
					image.setTitle(title);
					image.setType(typeI);
					imageList.add(image);
				}
				
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			if (row[40] != null) {
				JSONArray json1 = (JSONArray) parserI.parse(row[40].toString());
				for (int i = 0; i < json1.size(); i++) {
					Image image = new Image();
					TypeIma typeI = new TypeIma();
					Title title = new Title();
					Creator creator= new Creator();
					DescriptionIma descriptionI = new DescriptionIma();
					JSONObject jsonObjeto = (JSONObject) (json1.get(i));
					typeI.setValue("Forestal");
					if(jsonObjeto.get("titulo")!=null)
					{
					title.setValue(jsonObjeto.get("titulo").toString());
					}
					if(jsonObjeto.get("autor")!=null)
					{
					creator.setValue(jsonObjeto.get("autor").toString());
					}
					if(jsonObjeto.get("description")!=null)
					{
					descriptionI.setValue(jsonObjeto.get("description").toString());
					}
					Identifier iden=new Identifier();
					if(jsonObjeto.get("spdm_alfresco_id")!=null)
					{
						byte [] imgArr=getImageAlfresco(jsonObjeto.get("spdm_alfresco_id").toString());
						if(imgArr!=null)
						{
							iden.setValue(Base64.encode(imgArr));
						}
					
					}
					image.setIdentifier(iden);
					image.setCreator(creator);
					image.setDescription(descriptionI);
					image.setTitle(title);
					image.setType(typeI);
					imageList.add(image);
				}
				
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			if (row[78] != null) {
				JSONArray json1 = (JSONArray) parserI.parse(row[78].toString());
				for (int i = 0; i < json1.size(); i++) {
					Image image = new Image();
					TypeIma typeI = new TypeIma();
					Title title = new Title();
					Creator creator= new Creator();
					DescriptionIma descriptionI = new DescriptionIma();
					JSONObject jsonObjeto = (JSONObject) (json1.get(i));
					typeI.setValue("Transversal");
					if(jsonObjeto.get("titulo")!=null)
					{
					title.setValue(jsonObjeto.get("titulo").toString());
					}
					if(jsonObjeto.get("autor")!=null)
					{
					creator.setValue(jsonObjeto.get("autor").toString());
					}
					if(jsonObjeto.get("spph_description")!=null)
					{
					descriptionI.setValue(jsonObjeto.get("spph_description").toString());
					}
					Identifier iden=new Identifier();
					if(jsonObjeto.get("spph_alfresco_id")!=null)
					{
						byte [] imgArr=getImageAlfresco(jsonObjeto.get("spph_alfresco_id").toString());
						if(imgArr!=null)
						{
							iden.setValue(Base64.encode(imgArr));
						}
					
					}
					image.setIdentifier(iden);
					image.setCreator(creator);
					image.setDescription(descriptionI);
					image.setTitle(title);
					image.setType(typeI);
					imageList.add(image);
				}
				
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			if (row[79] != null) {
				JSONArray json1 = (JSONArray) parserI.parse(row[79].toString());
				for (int i = 0; i < json1.size(); i++) {
					Image image = new Image();
					TypeIma typeI = new TypeIma();
					Title title = new Title();
					Creator creator= new Creator();
					DescriptionIma descriptionI = new DescriptionIma();
					JSONObject jsonObjeto = (JSONObject) (json1.get(i));
					typeI.setValue("Tangencial");
					if(jsonObjeto.get("titulo")!=null)
					{
					title.setValue(jsonObjeto.get("titulo").toString());
					}
					if(jsonObjeto.get("autor")!=null)
					{
					creator.setValue(jsonObjeto.get("autor").toString());
					}
					if(jsonObjeto.get("spph_description")!=null)
					{
					descriptionI.setValue(jsonObjeto.get("spph_description").toString());
					}
					
					Identifier iden=new Identifier();
					if(jsonObjeto.get("spph_alfresco_id")!=null)
					{
						byte [] imgArr=getImageAlfresco(jsonObjeto.get("spph_alfresco_id").toString());
						if(imgArr!=null)
						{
							iden.setValue(Base64.encode(imgArr));
						}
					
					}
					image.setIdentifier(iden);
					image.setCreator(creator);
					image.setDescription(descriptionI);
					image.setTitle(title);
					image.setType(typeI);
					imageList.add(image);
				}
				
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			if (row[80] != null) {
				JSONArray json1 = (JSONArray) parserI.parse(row[80].toString());
				for (int i = 0; i < json1.size(); i++) {
					Image image = new Image();
					TypeIma typeI = new TypeIma();
					Title title = new Title();
					Creator creator= new Creator();
					DescriptionIma descriptionI = new DescriptionIma();
					JSONObject jsonObjeto = (JSONObject) (json1.get(i));
					typeI.setValue("Radial");
					if(jsonObjeto.get("titulo")!=null)
					{
					title.setValue(jsonObjeto.get("titulo").toString());
					}
					if(jsonObjeto.get("autor")!=null)
					{
					creator.setValue(jsonObjeto.get("autor").toString());
					}
					if(jsonObjeto.get("spph_description")!=null)
					{
					descriptionI.setValue(jsonObjeto.get("spph_description").toString());
					}
					Identifier iden=new Identifier();
					if(jsonObjeto.get("spph_alfresco_id")!=null)
					{
						byte [] imgArr=getImageAlfresco(jsonObjeto.get("spph_alfresco_id").toString());
						if(imgArr!=null)
						{
							iden.setValue(Base64.encode(imgArr));
						}
					
					}
					image.setIdentifier(iden);
					image.setCreator(creator);
					image.setDescription(descriptionI);
					image.setTitle(title);
					image.setType(typeI);
					imageList.add(image);
				}
				
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			if (row[81] != null) {
				JSONArray json1 = (JSONArray) parserI.parse(row[81].toString());
				for (int i = 0; i < json1.size(); i++) {
					Image image = new Image();
					TypeIma typeI = new TypeIma();
					Title title = new Title();
					Creator creator= new Creator();
					DescriptionIma descriptionI = new DescriptionIma();
					JSONObject jsonObjeto = (JSONObject) (json1.get(i));
					typeI.setValue("Anatómica");
					if(jsonObjeto.get("titulo")!=null)
					{
					title.setValue(jsonObjeto.get("titulo").toString());
					}
					if(jsonObjeto.get("autor")!=null)
					{
					creator.setValue(jsonObjeto.get("autor").toString());
					}
					if(jsonObjeto.get("spph_description")!=null)
					{
					descriptionI.setValue(jsonObjeto.get("spph_description").toString());
					}
					Identifier iden=new Identifier();
					if(jsonObjeto.get("spph_alfresco_id")!=null)
					{
						byte [] imgArr=getImageAlfresco(jsonObjeto.get("spph_alfresco_id").toString());
						if(imgArr!=null)
						{
							iden.setValue(Base64.encode(imgArr));
						}
					
					}
					image.setIdentifier(iden);
					image.setCreator(creator);
					image.setDescription(descriptionI);
					image.setTitle(title);
					image.setType(typeI);
					imageList.add(image);
				}
				
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		images.setImage(imageList);
		sumarioF.setImages(images);
		return sumarioF;
	}
	
	public SumExSpResult obtenerSumarioExotica(Object [] row)
	{
		SumExSpResult sumarioE = new SumExSpResult();
		Metadata meta = new Metadata();
		meta.setComment(
				"El Ministerio del Ambiente y Agua se reserva el derecho de modificar sin previo aviso el listado de especies con información disponible, el contenido del sumario, y/o la estructura del servicio web. Para información actualizada se puede consultar en http://suia.ambiente.gob.ec");
		Author author = new Author();
		author.setComment(
				"Autor. Persona o personas que contribuyeron con su conocimiento intelectual y de manera significativa al contenido del sumario");
		if (row[49] != null) {
			author.setValue(row[49].toString());
		}
		Reviewer reviewer = new Reviewer();
		reviewer.setComment(
				"Revisor. Persona o personas que revisaron o examinaron cuidadosamente el contenido del sumario");
		if (row[51] != null) {
			reviewer.setValue(row[51].toString());
		}
		Editor editor = new Editor();
		editor.setComment(
				"Editor. Persona o personas que contribuyeron a corregir y adaptar el contenido del sumario para uso del público objetivo");
		if (row[52] != null) {
			editor.setValue(row[52].toString());
		}
		Collaborator collaborator = new Collaborator();
		collaborator.setComment(
				"Colaborador. Persona que trabajó con el o los autores en la realización del sumario sin aportar conocimiento sino esfuerzo en sistematizar y digitalizar la información");
		if (row[53] != null) {
			collaborator.setValue(row[53].toString());
		}
		PublicationYear publicationYear = new PublicationYear();
		publicationYear.setComment("Año de publicación del sumario de la especie");
		if (row[50] != null) {
			publicationYear.setValue(row[50].toString());
		}
		Language language = new Language();
		language.setComment(
				"Idioma utilizado para escribir la información del sumario de la especie, se utiliza el código ISO 639-2 (https://www.loc.gov/standards/iso639-2/php/code_list.php)");
		language.setValue("SPA");
		RightsHolder rightsHolder = new RightsHolder();
		rightsHolder.setComment(
				"Nombre de la Institución con los derechos de propiedad o administracion del sumario");
		rightsHolder.setValue("Todos los derechos reservados - Ministerio del Ambiente y Agua, 2020");
		AccessRights accessRights = new AccessRights();
		accessRights.setComment(
				"Nombre de la Institución con los derechos de propiedad o administracion del sumario");
		if (row[54] != null) {
			accessRights.setValue(row[54].toString());
		}
		License license = new License();
		license.setComment(
				"Declaración de permiso oficial para hacer uso del sumario y su información");
		license.setValue("https://creativecommons.org/licenses/by-sa/4.0/");
		Version version = new Version();
		version.setComment("Número y fecha de la versión actual");
		version.setValue("01.2020");
		meta.setAccessRights(accessRights);
		meta.setAuthor(author);
		meta.setCollaborator(collaborator);
		meta.setEditor(editor);
		meta.setLanguage(language);
		meta.setLicense(license);
		meta.setPublicationYear(publicationYear);
		meta.setReviewer(reviewer);
		meta.setRightsHolder(rightsHolder);
		meta.setVersion(version);
		sumarioE.setMetadata(meta);
		TaxonExo taxon = new TaxonExo();
		Gui guiS = new Gui();
		guiS.setComment(
				"Global Unique Identifier. Código único nacional del Catálogo Nacional de Objetos Biológicos");
		if (row[5] != null) {
			guiS.setValue(row[5].toString());
		}
		Kingdom kingdom = new Kingdom();
		kingdom.setComment("El nombre científico completo del reino en el que se clasifica el taxón");
		Phylum phylum = new Phylum();
		phylum.setComment("El nombre científico completo del phylum en el que se clasifica el taxón");
		Clas clas = new Clas();
		clas.setComment("El nombre científico completo de la clase en el que se clasifica el taxón");
		Order order = new Order();
		order.setComment("El nombre científico completo del orden en el que se clasifica el taxón");
		Family family = new Family();
		family.setComment(
				"El nombre científico completo de la familia en el que se clasifica el taxón");
		Genus genus = new Genus();
		genus.setComment("El nombre científico completo del género en el que se clasifica el taxón");

		JSONParser parser = new JSONParser();
		try {
			if (row[10] != null) {
				JSONObject json = (JSONObject) parser.parse(row[10].toString());
				if(json.get("Reino")!=null)
				{
				kingdom.setValue(json.get("Reino").toString());
				}
				if(json.get("Phylum")!=null)
				{
				phylum.setValue(json.get("Phylum").toString());
				}
				if(json.get("Clase")!=null)
				{
				clas.setValue(json.get("Clase").toString());
				}
				if(json.get("Orden")!=null)
				{
				order.setValue(json.get("Orden").toString());
				}
				if(json.get("Familia")!=null)
				{
				family.setValue(json.get("Familia").toString());
				}
				if(json.get("Género")!=null)
				{
				genus.setValue(json.get("Género").toString());
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SpecificInfraspecificEpithet specificInfraspecificEpithet = new SpecificInfraspecificEpithet();
		specificInfraspecificEpithet.setComment(
				"El nombre científico completo del epíteto específico y el epíteto infraespecífico (de ser el caso) en el rango taxonómico más bajo");
		if (row[11] != null) {
			specificInfraspecificEpithet.setValue(row[11].toString());
		}
		ScientificName scientificName = new ScientificName();
		scientificName.setComment("El nombre científico completo, con la autoría y año");
		if (row[4] != null) {
			scientificName.setValue(row[4].toString());
		}
		TaxonRank taxonRank = new TaxonRank();
		taxonRank.setComment(
				"El rango taxonómico del nombre más específico provisto en el campo scientificName. Se usa vocabulario controlado (Reino, Phylum, Clase, Orden, Familia, Genero, especie, subespecie, variedad, forma)");
		if (row[3] != null) {
			taxonRank.setValue(row[3].toString());
		}
		ScientificNameAuthorship scientificNameAuthorship = new ScientificNameAuthorship();
		scientificNameAuthorship.setComment("Información de autoría del nombre científico");
		if (row[6] != null) {
			scientificNameAuthorship.setValue(row[6].toString());
		}
		NamePublishedInYear namePublishedInYear = new NamePublishedInYear();
		namePublishedInYear
				.setComment("El año de cuatro dígitos en el que se publicó el nombre científico");
		if (row[7] != null) {
			namePublishedInYear.setValue(row[7].toString());
		}
		InEcuador inEcuador = new InEcuador();
		inEcuador.setComment("Es una especie registrada en Ecuador");
		if (row[17] != null) {
			inEcuador.setValue((Boolean) row[17]);
		}
		IsNative isNative = new IsNative();
		isNative.setComment("Es una especie nativa");
		if (row[15] != null) {
			isNative.setValue((Boolean) row[15]);
		}
		IsEndemic isEndemic = new IsEndemic();
		isEndemic.setComment("Es una especie endémica");
		if (row[12] != null) {
			isEndemic.setValue((Boolean) row[12]);
		}
		IsExotic isExotic = new IsExotic();
		isExotic.setComment("Es una especie exótica");
		if (row[13] != null) {
			isExotic.setValue((Boolean) row[13]);
		}
		IsInvasive isInvasive = new IsInvasive();
		isInvasive.setComment("Es una especie invasora");
		if (row[14] != null) {
			isExotic.setValue((Boolean) row[14]);
		}
		IsDomestic isDomestic = new IsDomestic();
		isDomestic.setComment(
				"En caso de flora y hongos, el término utilizado es cultivada. Doméstico aplica para animales");
		if (row[18] != null) {
			isDomestic.setValue((Boolean) row[18]);
		}
		IsMigratory isMigratory = new IsMigratory();
		if (row[16] != null) {
			isMigratory.setValue((Boolean) row[16]);
		}
		TaxonomicStatus taxonomicStatus = new TaxonomicStatus();
		if (row[2] != null) {
			taxonomicStatus.setValue(row[2].toString());
		}
		IsCorrectName isCorrectName = new IsCorrectName();
		if (row[0] != null) {
			isCorrectName.setValue((Boolean) row[0]);
		}
		CorrectScientificName correctScientificName = new CorrectScientificName();
		if (row[1] != null) {
			correctScientificName.setValue(row[1].toString());
		}
		
		taxon.setClas(clas);
		taxon.setCorrectScientificName(correctScientificName);
		taxon.setFamily(family);
		taxon.setGenus(genus);
		taxon.setGui(guiS);
		taxon.setInEcuador(inEcuador);
		taxon.setIsCorrectName(isCorrectName);
		taxon.setIsDomestic(isDomestic);
		taxon.setIsEndemic(isEndemic);
		taxon.setIsExotic(isExotic);
		taxon.setIsInvasive(isInvasive);
		taxon.setIsNative(isNative);
		taxon.setKingdom(kingdom);
		taxon.setNamePublishedInYear(namePublishedInYear);
		taxon.setOrder(order);
		taxon.setPhylum(phylum);
		taxon.setScientificName(scientificName);
		taxon.setScientificNameAuthorship(scientificNameAuthorship);
		taxon.setSpecificInfraspecificEpithet(specificInfraspecificEpithet);
		taxon.setTaxonomicStatus(taxonomicStatus);
		taxon.setTaxonRank(taxonRank);
		sumarioE.setTaxon(taxon);
		DescriptionExotica descripcion= new DescriptionExotica();
		MorphologicalDescription morphological = new MorphologicalDescription();
		if (row[20] != null) {
			morphological.setValue(row[20].toString());
		}
		
		descripcion.setMorphologicalDescription(morphological);
		
		sumarioE.setDescription(descripcion);
		
		Characteristics characteristics= new Characteristics();
		Dispersion dispersion = new Dispersion();
		List<DispersionValue> dispersionList= new ArrayList<>();
		JSONParser parserArt = new JSONParser();
		try {
			if (row[35] != null) {

				JSONArray json1 = (JSONArray) parserArt.parse(row[35].toString());
				for (int i = 0; i < json1.size(); i++) {
					DispersionValue art = new DispersionValue();
					String jsonObj = (String) (json1.get(i));
					art.setValue(jsonObj);
					dispersionList.add(art);
				}

			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dispersion.setDispersion(dispersionList);
		characteristics.setDispersion(dispersion);
		
		Diet diet = new Diet();
		List<DietValue> dietList= new ArrayList<>();
		JSONParser parserDiet = new JSONParser();
		try {
			if (row[37] != null) {

				JSONArray json1 = (JSONArray) parserDiet.parse(row[37].toString());
				for (int i = 0; i < json1.size(); i++) {
					DietValue art = new DietValue();
					String jsonObj = (String) (json1.get(i));
					art.setValue(jsonObj);
					dietList.add(art);
				}

			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		diet.setDiet(dietList);
		characteristics.setDiet(diet);
		
		DispersionRoutes dispersionRoutes= new DispersionRoutes();
		List<DispersionRoutesValue> dispersionRoutesList= new ArrayList<>();
		
		JSONParser parserDispRout = new JSONParser();
		try {
			if (row[39] != null) {

				JSONArray json1 = (JSONArray) parserDispRout.parse(row[39].toString());
				for (int i = 0; i < json1.size(); i++) {
					DispersionRoutesValue art = new DispersionRoutesValue();
					String jsonObj = (String) (json1.get(i));
					art.setValue(jsonObj);
					dispersionRoutesList.add(art);
				}

			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dispersionRoutes.setDispersionRoutes(dispersionRoutesList);
		characteristics.setDispersionRoutes(dispersionRoutes);
		
		Reprodution reproduction= new Reprodution();
		List<ReproductionValue> reproductionList= new ArrayList<>();
		
		JSONParser parserReproduction = new JSONParser();
		try {
			if (row[36] != null) {

				JSONArray json1 = (JSONArray) parserReproduction.parse(row[36].toString());
				for (int i = 0; i < json1.size(); i++) {
					ReproductionValue art = new ReproductionValue();
					String jsonObj = (String) (json1.get(i));
					art.setValue(jsonObj);
					reproductionList.add(art);
				}

			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		reproduction.setReproduction(reproductionList);
		characteristics.setReproduction(reproduction);
		
		DispertionVectors dispertionVectors= new DispertionVectors();
		List<DispertionVectorsValue> dispertionVectorsList= new ArrayList<>();
		
		JSONParser parserVector = new JSONParser();
		try {
			if (row[38] != null) {

				JSONArray json1 = (JSONArray) parserVector.parse(row[38].toString());
				for (int i = 0; i < json1.size(); i++) {
					DispertionVectorsValue art = new DispertionVectorsValue();
					String jsonObj = (String) (json1.get(i));
					art.setValue(jsonObj);
					dispertionVectorsList.add(art);
				}

			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dispertionVectors.setDispertionVectors(dispertionVectorsList);
		characteristics.setDispertionVectors(dispertionVectors);
		
		BiologicalForm biologicalForm= new BiologicalForm();
		List<BiologicalFormValue> biologicalFormList= new ArrayList<>();
		
		JSONParser parserBioFor = new JSONParser();
		try {
			if (row[40] != null) {

				JSONArray json1 = (JSONArray) parserBioFor.parse(row[40].toString());
				for (int i = 0; i < json1.size(); i++) {
					BiologicalFormValue art = new BiologicalFormValue();
					String jsonObj = (String) (json1.get(i));
					art.setValue(jsonObj);
					biologicalFormList.add(art);
				}

			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		biologicalForm.setBiologicalForm(biologicalFormList);
		characteristics.setBiologicalForm(biologicalForm);
		
		UsesExo uses = new UsesExo();
		List<UsesValue> usesList= new ArrayList<>();
		
		JSONParser parserUse = new JSONParser();
		try {
			if (row[41] != null) {

				JSONArray json1 = (JSONArray) parserUse.parse(row[41].toString());
				for (int i = 0; i < json1.size(); i++) {
					UsesValue art = new UsesValue();
					String jsonObj = (String) (json1.get(i));
					art.setValue(jsonObj);
					usesList.add(art);
				}

			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		uses.setUses(usesList);
		characteristics.setUses(uses);
		
		PreferentialInvasionEnvironments preferentialInvasionEnvironments = new PreferentialInvasionEnvironments();
		List<PreferentialInvasionEnvironmentsValue> preferentialInvasionEnvironmentsList= new ArrayList<>();
		
		JSONParser parserPref = new JSONParser();
		try {
			if (row[42] != null) {

				JSONArray json1 = (JSONArray) parserPref.parse(row[42].toString());
				for (int i = 0; i < json1.size(); i++) {
					PreferentialInvasionEnvironmentsValue art = new PreferentialInvasionEnvironmentsValue();
					String jsonObj = (String) (json1.get(i));
					art.setValue(jsonObj);
					preferentialInvasionEnvironmentsList.add(art);
				}

			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		preferentialInvasionEnvironments.setPreferentialInvasionEnvironments(preferentialInvasionEnvironmentsList);
		characteristics.setPreferentialInvasionEnvironments(preferentialInvasionEnvironments);
		sumarioE.setCharacteristics(characteristics);
		
		Impacts impacts= new Impacts();
		SpeciesAffected speciesAffected= new SpeciesAffected();
		List<SpeciesAffectedValue> speciesAffectedList= new ArrayList<>();
		
		JSONParser parserSpecAf = new JSONParser();
		try {
			if (row[30] != null) {

				JSONArray json1 = (JSONArray) parserSpecAf.parse(row[30].toString());
				for (int i = 0; i < json1.size(); i++) {
					SpeciesAffectedValue art = new SpeciesAffectedValue();
					String jsonObj = (String) (json1.get(i));
					art.setValue(jsonObj);
					speciesAffectedList.add(art);
				}

			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		speciesAffected.setSpeciesAffected(speciesAffectedList);
		impacts.setSpeciesAffected(speciesAffected);
		
		EcologicalImpact ecologicalImpact = new EcologicalImpact();
		if (row[31] != null) {
			ecologicalImpact.setValue(row[31].toString());
		}
		
		impacts.setEcologicalImpact(ecologicalImpact);
		EconomicImpact economicImpact = new EconomicImpact();
		if (row[32] != null) {
			economicImpact.setValue(row[32].toString());
		}
		
		impacts.setEconomicImpact(economicImpact);
		SocialImpact socialImpact = new SocialImpact();
		if (row[33] != null) {
			socialImpact.setValue(row[33].toString());
		}
		
		impacts.setSocialImpact(socialImpact);
		
		HealthImpact healthImpac = new HealthImpact();
		if (row[34] != null) {
			healthImpac.setValue(row[34].toString());
		}
		
		impacts.setHealthImpact(healthImpac);
		sumarioE.setImpacts(impacts);
		
							
		List<VernacularN> listVern = new ArrayList<>();
		JSONParser parserVern = new JSONParser();
		try {
			if (row[19] != null) {

				JSONArray json1 = (JSONArray) parserVern.parse(row[19].toString());
				for (int i = 0; i < json1.size(); i++) {
					VernacularN art = new VernacularN();
					VernacularName verName = new VernacularName();
					Locality locality = new Locality();
					LanguageV languageV = new LanguageV();
					Source source = new Source();
					JSONObject json = (JSONObject) (json1.get(i));
					if(json.get("nombre")!=null)
					{
					verName.setValue(json.get("nombre").toString());
					}
					if(json.get("grupo_etnico")!=null)
					{
					locality.setValue(json.get("grupo_etnico").toString());
					}
					if(json.get("lenguaje")!=null)
					{
					languageV.setValue(json.get("lenguaje").toString());
					}
					if(json.get("citacorta")!=null)
					{
					source.setValue(json.get("citacorta").toString());
					}
					
					art.setLanguage(languageV);
					art.setLocality(locality);
					art.setSource(source);
					art.setVernacularName(verName);
					listVern.add(art);

				}

			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		VernacularNames vNames = new VernacularNames();
		vNames.setVernacularN(listVern);
		sumarioE.setVernacularNames(vNames);
		List<Synonym> listSin = new ArrayList<>();
		JSONParser parserSino = new JSONParser();
		try {
			if (row[21] != null) {

				JSONArray json1 = (JSONArray) parserSino.parse(row[21].toString());
				for (int i = 0; i < json1.size(); i++) {
					Synonym synonym = new Synonym();
					GuiSin guiSin = new GuiSin();
					ScientificNameSin scientificNameSin = new ScientificNameSin();
					JSONObject json = (JSONObject) (json1.get(i));
					if(json.get("spta_scientific_name")!=null)
					{
					guiSin.setValue(json.get("spta_scientific_name").toString());
					}
					if(json.get("spta_scientific_gui")!=null)
					{
					scientificNameSin.setValue(json.get("spta_scientific_gui").toString());
					}
					synonym.setGui(guiSin);
					synonym.setScientificName(scientificNameSin);
					listSin.add(synonym);

				}

			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Synonyms synonyms = new Synonyms();
		synonyms.setSynonym(listSin);
		sumarioE.setSynonyms(synonyms);
		
		Introduction introduction= new Introduction();
									
		IntroductionYear introductionYear = new IntroductionYear();
		if (row[27] != null) {
			introductionYear.setValue(row[27].toString());
		}
		introduction.setIntroductionYear(introductionYear);
		
		IntroductionReason introductionReason = new IntroductionReason();
		if (row[28] != null) {
			introductionReason.setValue(row[28].toString());
		}
		
		introduction.setIntroductionReason(introductionReason);
		
		IntroductionProject introductionProject= new IntroductionProject();
		List<IntroductionProjectValue> listInttr = new ArrayList<>();
		JSONParser parserIntr = new JSONParser();
		try {
			if (row[29] != null) {

				JSONArray json1 = (JSONArray) parserIntr.parse(row[29].toString());
				for (int i = 0; i < json1.size(); i++) {
					IntroductionProjectValue art = new IntroductionProjectValue();
					TypeInt typeInt = new TypeInt();
					IntroductionCause introductionCause = new IntroductionCause();
					EventDate eventDate = new EventDate();
					LocalityExo locality = new LocalityExo();
					AuthorExo authorExo = new AuthorExo();
					TitleExo title= new TitleExo();
					InvasionRiskCategory invasionRiskCategory= new InvasionRiskCategory();
					IsExoticInRegion isExoticInRegion= new IsExoticInRegion();
					IntroductionDescription introductionDescription = new IntroductionDescription();
					JSONObject json = (JSONObject) (json1.get(i));
					if(json.get("type_introduction")!=null)
					{
					typeInt.setValue(json.get("type_introduction").toString());
					}
					if(json.get("cause_introduction")!=null)
					{
					introductionCause.setValue(json.get("cause_introduction").toString());
					}
					if(json.get("date_introduction")!=null)
					{
					eventDate.setValue(json.get("date_introduction").toString());
					}
					if(json.get("place_introduction")!=null)
					{
					locality.setValue(json.get("place_introduction").toString());
					}
					if(json.get("author")!=null)
					{
					authorExo.setValue(json.get("author").toString());
					}
					if(json.get("title")!=null)
					{
					title.setValue(json.get("title").toString());
					}
					if(json.get("cat_invasion")!=null)
					{
					invasionRiskCategory.setValue(json.get("cat_invasion").toString());
					}
					if(json.get("exotic_in_region")!=null)
					{
					isExoticInRegion.setValue((Boolean)(json.get("exotic_in_region")));
					}
					if(json.get("description")!=null)
					{
					introductionDescription.setValue(json.get("description").toString());
					}
					art.setAuthor(authorExo);
					art.setEventDate(eventDate);
					art.setIntroductionCause(introductionCause);
					art.setIntroductionDescription(introductionDescription);
					art.setInvasionRiskCategory(invasionRiskCategory);
					art.setIsExoticInRegion(isExoticInRegion);
					art.setLocality(locality);
					art.setTitle(title);
					art.setType(typeInt);
					listInttr.add(art);

				}

			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		introductionProject.setIntroductionProject(listInttr);
		introduction.setIntroductionProjects(introductionProject);
		
		sumarioE.setIntroduction(introduction);
		
		Control control = new Control();
		PhysicalControl physicalControl= new PhysicalControl();
		
		if (row[43] != null) {
			physicalControl.setValue(row[43].toString());
		}
		
		control.setPhysicalControl(physicalControl);
		
		
		ChemicalControl chemicalControl= new ChemicalControl();
		
		if (row[44] != null) {
			chemicalControl.setValue(row[44].toString());
		}
		
		control.setChemicalControl(chemicalControl);
		
		BiologicalControl biologicalControl= new BiologicalControl();
		if (row[45] != null) {
			biologicalControl.setValue(row[45].toString());
		}
		
		control.setBiologicalControl(biologicalControl);
		
		PreventionMeasure preventionMeasure = new PreventionMeasure();
		
		if (row[46] != null) {
			preventionMeasure.setValue(row[46].toString());
		}
		
		control.setPreventionMeasure(preventionMeasure);
		
		RiskAnalysis riskAnalysis = new RiskAnalysis();
		
		if (row[47] != null) {
			riskAnalysis.setValue(row[47].toString());
		}
		
		control.setRiskAnalysis(riskAnalysis);
		
		sumarioE.setControl(control);
		
								
		DistributionExo distribution = new DistributionExo();
		NativeDistributionArea nativeDistributionArea= new NativeDistributionArea();
		if (row[22] != null) {
			nativeDistributionArea.setValue(row[22].toString());
		}
		
		distribution.setNativeDistributionArea(nativeDistributionArea);
		NaturalEnvironment naturalEnvironment= new NaturalEnvironment();
		if (row[23] != null) {
			naturalEnvironment.setValue(row[23].toString());
		}
		
		distribution.setNaturalEnvironment(naturalEnvironment);
		
		Provinces provinces = new Provinces();
		ProtectedAreas protectedAreas = new ProtectedAreas();
		Ecosystems ecosystems = new Ecosystems();
		
		List<Province> listProv = new ArrayList<>();
		JSONParser parserProv = new JSONParser();
		try {
			if (row[24] != null) {

				JSONArray json1 = (JSONArray) parserProv.parse(row[24].toString());
				for (int i = 0; i < json1.size(); i++) {
					Province prov = new Province();
					String jsonObj = (String) (json1.get(i));
					prov.setValue(jsonObj);
					listProv.add(prov);
				}

			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		provinces.setProvince(listProv);
		List<ProtectedArea> listProt = new ArrayList<>();
		JSONParser parserProt = new JSONParser();
		try {
			if (row[25] != null) {

				JSONArray json1 = (JSONArray) parserProt.parse(row[25].toString());
				for (int i = 0; i < json1.size(); i++) {
					ProtectedArea protect = new ProtectedArea();
					String jsonObj = (String) (json1.get(i));
					protect.setValue(jsonObj);
					listProt.add(protect);
				}

			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		protectedAreas.setProtectedArea(listProt);
		List<Ecosystem> listEcos = new ArrayList<>();
		JSONParser parserEcos = new JSONParser();
		try {
			if (row[26] != null) {

				JSONArray json1 = (JSONArray) parserEcos.parse(row[26].toString());
				for (int i = 0; i < json1.size(); i++) {
					Ecosystem ecos = new Ecosystem();
					String jsonObj = (String) (json1.get(i));
					ecos.setValue(jsonObj);
					listEcos.add(ecos);
				}

			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ecosystems.setEcosystem(listEcos);
		distribution.setProvinces(provinces);
		distribution.setProtectedAreas(protectedAreas);
		distribution.setEcosystems(ecosystems);
		sumarioE.setDistribution(distribution);
		LiteratureReferences literatureReferences= new LiteratureReferences();
		List<LiteratureReference> liteList=new ArrayList<>();
		JSONParser parserF = new JSONParser();
		try {
			if (row[48] != null) {
				JSONArray json = (JSONArray) parserF.parse(row[48].toString());
				for (int i = 0; i < json.size(); i++) {
					JSONObject jsonObjeto1 = (JSONObject) (json.get(i));
					JSONArray json1 = (JSONArray) parserF.parse(jsonObjeto1.get("det_fuentes").toString());
					for (int k = 0; k < json1.size(); k++) {
						LiteratureReference lite = new LiteratureReference();
						Type typeL = new Type();
						Url url = new Url();
						Principal principal= new Principal();
						BibliographicCitation bibliographicCitation = new BibliographicCitation();
						JSONObject jsonObjeto = (JSONObject) (json1.get(k));
						if(jsonObjeto.get("bigc_description")!=null)
						{
						typeL.setValue(jsonObjeto.get("bigc_description").toString());
						}
						if(jsonObjeto.get("url")!=null)
						{
						url.setValue(jsonObjeto.get("url").toString());
						}
						if(jsonObjeto.get("stis_principal")!=null)
						{
						principal.setValue(jsonObjeto.get("stis_principal").toString());
						}
						if(jsonObjeto.get("nombre")!=null)
						{
						bibliographicCitation.setValue(jsonObjeto.get("nombre").toString());
						}
						lite.setBibliographicCitation(bibliographicCitation);
						lite.setPrincipal(principal);
						lite.setUrl(url);
						lite.setType(typeL);
						liteList.add(lite);
					}
				}
				
				
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		literatureReferences.setLiteratureReference(liteList);
		sumarioE.setLiteratureReferences(literatureReferences);
		Images images= new Images();
		List<Image> imageList=new ArrayList<>();
		JSONParser parserI = new JSONParser();
		try {
			if (row[8] != null) {
				JSONArray json1 = (JSONArray) parserI.parse(row[8].toString());
				for (int i = 0; i < json1.size(); i++) {
					Image image = new Image();
					TypeIma typeI = new TypeIma();
					Title title = new Title();
					Creator creator= new Creator();
					DescriptionIma descriptionI = new DescriptionIma();
					JSONObject jsonObjeto = (JSONObject) (json1.get(i));
					typeI.setValue("Principal");
					if(jsonObjeto.get("titulo")!=null)
					{
					title.setValue(jsonObjeto.get("titulo").toString());
					}
					if(jsonObjeto.get("autor")!=null)
					{
					creator.setValue(jsonObjeto.get("autor").toString());
					}
					if(jsonObjeto.get("spph_description")!=null)
					{
					descriptionI.setValue(jsonObjeto.get("spph_description").toString());
					}
					Identifier iden=new Identifier();
					if(jsonObjeto.get("spph_alfresco_id")!=null)
					{
						byte [] imgArr=getImageAlfresco(jsonObjeto.get("spph_alfresco_id").toString());
						if(imgArr!=null)
						{
							iden.setValue(Base64.encode(imgArr));
						}
					
					}
					image.setIdentifier(iden);
					image.setCreator(creator);
					image.setDescription(descriptionI);
					image.setTitle(title);
					image.setType(typeI);
					imageList.add(image);
				}
				
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			if (row[9] != null) {
				JSONArray json1 = (JSONArray) parserI.parse(row[9].toString());
				for (int i = 0; i < json1.size(); i++) {
					Image image = new Image();
					TypeIma typeI = new TypeIma();
					Title title = new Title();
					Creator creator= new Creator();
					DescriptionIma descriptionI = new DescriptionIma();
					JSONObject jsonObjeto = (JSONObject) (json1.get(i));
					typeI.setValue("Secundaria");
					if(jsonObjeto.get("titulo")!=null)
					{
					title.setValue(jsonObjeto.get("titulo").toString());
					}
					if(jsonObjeto.get("autor")!=null)
					{
					creator.setValue(jsonObjeto.get("autor").toString());
					}
					if(jsonObjeto.get("spph_description")!=null)
					{
					descriptionI.setValue(jsonObjeto.get("spph_description").toString());
					}
					Identifier iden=new Identifier();
					if(jsonObjeto.get("spph_alfresco_id")!=null)
					{
						byte [] imgArr=getImageAlfresco(jsonObjeto.get("spph_alfresco_id").toString());
						if(imgArr!=null)
						{
							iden.setValue(Base64.encode(imgArr));
						}
					
					}
					image.setIdentifier(iden);
					image.setCreator(creator);
					image.setDescription(descriptionI);
					image.setTitle(title);
					image.setType(typeI);
					imageList.add(image);
				}
				
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		images.setImage(imageList);
		sumarioE.setImages(images);
		return sumarioE;
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
}
