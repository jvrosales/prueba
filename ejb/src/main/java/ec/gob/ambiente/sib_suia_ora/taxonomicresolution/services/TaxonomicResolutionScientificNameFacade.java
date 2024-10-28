package ec.gob.ambiente.sib_suia_ora.taxonomicresolution.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.speciescatalog.model.HigherClassification;
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecieTaxa;
import ec.gob.ambiente.enlisy.speciescatalog.services.HigherClassificationFacade;
import ec.gob.ambiente.sib_suia_ora.taxonomicresolution.model.Coincidences;
import ec.gob.ambiente.sib_suia_ora.taxonomicresolution.model.RTNCResult;
import lombok.Getter;
import lombok.Setter;

/**
 * Servicios para la consulta de la informacion taxonomica de un nombre o
 * nombres cientificos enviados como parametro en el servicio web de resolucion
 * taxonomica
 * 
 * @author CristinaFactos
 *
 */
@Stateless
public class TaxonomicResolutionScientificNameFacade extends AbstractFacade<SpecieTaxa, Integer> {

	public TaxonomicResolutionScientificNameFacade() {
		super(SpecieTaxa.class, Integer.class);
	}

	@EJB
	HigherClassificationFacade higherClassificationFacade;
	
	@Getter
	@Setter
	private boolean tieneCoincidencia;

	public List<RTNCResult> getTaxonomicResolutionByScientificName(String nameSubmitted, Integer matchAccuracy,
			String onlyBestMach, String kingdom) {

		Double porcu= stringSimilarityScore(bigram("Echinodorus berteroi".toUpperCase()),
				bigram("Sticherus bicolor".toUpperCase()));
		System.out.println("porcu: "+porcu);
		System.out.println("porcentaje: "+(double) (matchAccuracy / 100.00));
		List<RTNCResult> objectAllResult = new ArrayList<>();
		List<RTNCResult> objectResult = new ArrayList<>();
		List<RTNCResult> objectOrderResult = new ArrayList<>();
		List<RTNCResult> objectSpecieResult = new ArrayList<>();
		List<RTNCResult> objectHigherResult = new ArrayList<>();
		String[] nameSubmittedSplit = nameSubmitted.split(";");
		Integer count = 0;
		Integer orderSub = 1;
		double porc = (double) (matchAccuracy / 100.00);
		System.out.println("Valor match: " + matchAccuracy);
		System.out.println("Valor onlyBest: " + onlyBestMach);
		

		try {

			
				count = consultAllHigher(objectHigherResult, count, kingdom);
				count = consultAll(objectSpecieResult, count, kingdom);
			System.out.println("Contador: " + count);
			Integer countResult = 0;
			System.out.println("Inicio similitud: " + new Date());
			if (onlyBestMach.equals("0")) {
				//if (nameSubmittedSplit.length > 1) {
					for (String nameSplit : nameSubmittedSplit) {
						tieneCoincidencia=false;
						String namSE = nameSplit.trim();
						objectAllResult = new ArrayList<>();
						objectOrderResult = new ArrayList<>();
						if (namSE != null && (namSE.split(" ").length <= 1)) {
							objectAllResult.addAll(objectHigherResult);
						} else {
							objectAllResult.addAll(objectHigherResult);
							objectAllResult.addAll(objectSpecieResult);
						}
						for (RTNCResult nameResult : objectAllResult) {
							
							double scoreTrigram = stringSimilarityScore(bigram(nameSplit.toUpperCase()),
									bigram(nameResult.getScientificName().toUpperCase()));
							countResult = compareTwoNames(objectOrderResult, porc, countResult, nameSplit, nameResult,
									scoreTrigram, orderSub);
							
									
						}
						if(!tieneCoincidencia)
						{
							RTNCResult copy = new RTNCResult();
							copy.setOrderSubmitted(orderSub);
							copy.setNameSubmitted(nameSplit);
							List<RTNCResult> coincidencias=new ArrayList<>();
							RTNCResult copyC = new RTNCResult();
							copyC.setOrderSubmitted(orderSub);
							copyC.setNameSubmitted(nameSplit);
							copyC.setOverallScore(0);
							copyC.setScientificName("SIN COINCIDENCIAS");
							coincidencias.add(copyC);
							Coincidences coincidences= new Coincidences();
							coincidences.setRtncResultCoincidence(coincidencias);
							copy.setCoincidences(coincidences);
							objectResult.add(copy);
						}
						else
						{
							RTNCResult copy = new RTNCResult();
							copy.setOrderSubmitted(orderSub);
							copy.setNameSubmitted(nameSplit);
							List<RTNCResult> coincidencias=new ArrayList<>();
							Collections.sort(objectOrderResult, new Comparator<RTNCResult>() {

								@Override
								public int compare(RTNCResult o1, RTNCResult o2) {
									
									return Integer.valueOf(o1.getOverallScore()).compareTo(Integer.valueOf(o2.getOverallScore()));
								}

							});
							if (!objectOrderResult.isEmpty()) {
								coincidencias.addAll(objectOrderResult);
							}
							Coincidences coincidences= new Coincidences();
							coincidences.setRtncResultCoincidence(coincidencias);
							copy.setCoincidences(coincidences);
							objectResult.add(copy);
						}
						
						orderSub++;
					}
			
				
			} else {
				//if (nameSubmittedSplit.length > 1) {
					for (String nameSplit : nameSubmittedSplit) {
						String namSE = nameSplit.trim();
						tieneCoincidencia=false;
						objectAllResult = new ArrayList<>();
						objectOrderResult = new ArrayList<>();
						if (namSE != null && (namSE.split(" ").length <= 1)) {
							objectAllResult.addAll(objectHigherResult);
						} else {
							objectAllResult.addAll(objectHigherResult);
							objectAllResult.addAll(objectSpecieResult);
						}

						for (RTNCResult nameResult : objectAllResult) {

							double scoreTrigram = stringSimilarityScore(bigram(nameSplit.toUpperCase()),
									bigram(nameResult.getScientificName().toUpperCase()));
							countResult = compareTwoNames(objectOrderResult, porc, countResult, nameSplit, nameResult,
									scoreTrigram, orderSub);
							
						}
						if(!tieneCoincidencia)
						{
							RTNCResult copy = new RTNCResult();
							copy.setOrderSubmitted(orderSub);
							copy.setNameSubmitted(nameSplit);
							List<RTNCResult> coincidencias=new ArrayList<>();
							RTNCResult copyC = new RTNCResult();
							copyC.setOrderSubmitted(orderSub);
							copyC.setNameSubmitted(nameSplit);
							copyC.setOverallScore(0);
							copyC.setScientificName("SIN COINCIDENCIAS");
							coincidencias.add(copyC);
							Coincidences coincidences= new Coincidences();
							coincidences.setRtncResultCoincidence(coincidencias);
							copy.setCoincidences(coincidences);
							objectResult.add(copy);
						}
						else
						{
							RTNCResult copy = new RTNCResult();
							copy.setOrderSubmitted(orderSub);
							copy.setNameSubmitted(nameSplit);
							List<RTNCResult> coincidencias=new ArrayList<>();
							
							Collections.sort(objectOrderResult, new Comparator<RTNCResult>() {

								@Override
								public int compare(RTNCResult o1, RTNCResult o2) {
									// TODO Auto-generated method stub
									return Integer.valueOf(o2.getOverallScore()).compareTo(Integer.valueOf(o1.getOverallScore()));
								}

							});
							if (!objectOrderResult.isEmpty()) {
								coincidencias.add(objectOrderResult.get(0));
							}
							Coincidences coincidences= new Coincidences();
							coincidences.setRtncResultCoincidence(coincidencias);
							copy.setCoincidences(coincidences);
							objectResult.add(copy);
						}
						
						orderSub++;
					}
				//} 
				

			}
			System.out.println("Fin similitud: " + new Date());
			System.out.println("Contador coincidencias: " + countResult);
		//}
		} catch (Exception e) {
			e.printStackTrace();
			objectResult = new ArrayList<>();
		}
		return objectResult;

	}

	private Integer compareTwoNames(List<RTNCResult> objectResultf, double porc, Integer countResult, String nameSplit,
			RTNCResult nameResult, double scoreTrigram, Integer orderSubmmited) {
		if (scoreTrigram >= porc) {

			RTNCResult copy = new RTNCResult();
			copy.setCites(nameResult.getCites());
			copy.setClas(nameResult.getClas());
			copy.setTaxonID(nameResult.getTaxonID());
			copy.setCorrectName(nameResult.getCorrectName());
			copy.setFamily(nameResult.getFamily());
			copy.setGenus(nameResult.getGenus());
			copy.setIsAcceptedName(nameResult.getIsAcceptedName());
			copy.setIsDomestic(nameResult.getIsDomestic());
			copy.setIsEndemic(nameResult.getIsEndemic());
			copy.setIsExotic(nameResult.getIsExotic());
			copy.setIsInvasive(nameResult.getIsInvasive());
			copy.setIsMigratory(nameResult.getIsMigratory());
			copy.setIsNative(nameResult.getIsNative());
			copy.setKingdom(nameResult.getKingdom());
			copy.setNamePublishedInYear(nameResult.getNamePublishedInYear());
			copy.setNationalRedList(nameResult.getNationalRedList());
			copy.setOrder(nameResult.getOrder());
			copy.setPhylum(nameResult.getPhylum());
			copy.setScientificName(nameResult.getScientificName());
			copy.setScientificNameAuthorship(nameResult.getScientificNameAuthorship());
			copy.setSpecificInfraspecifcEpit(nameResult.getSpecificInfraspecifcEpit());
			copy.setTaxonomicStatus(nameResult.getTaxonomicStatus());
			copy.setTaxonRank(nameResult.getTaxonRank());
			copy.setOverallScore(Math.round(((float) (scoreTrigram)) * 100));
			// NameSubmitted nam = new NameSubmitted();
			// nam.setNombreEnviado(nameSplit);
			copy.setNameSubmitted(nameSplit);
			copy.setOrderSubmitted(orderSubmmited);
			copy.setId(nameResult.getId());
			objectResultf.add(copy);
			countResult++;
			tieneCoincidencia=true;
		}
		
		
		return countResult;
	}

	@SuppressWarnings("unchecked")
	private Integer consultByNameSpecie(Integer matchAccuracy, List<RTNCResult> objectResult,
			Integer count, String nameSplit, String kingdom, Integer orderSubmitted) {

		List<Object[]> result;
		String idKingdom = getKingdomByName(kingdom) + ";%";
		Query q;
		EntityManager en = super.getEntityManager();
		q = en.createNativeQuery(
				"SELECT spta_id,spta_scientific_gui,spta_taxon_rank_name,spta_scientific_name,spta_higher_classification, tara_id, spta_scientific_name_authorship, spta_name_published_year , spta_is_accepted_name, spta_taxonomic_status_name, spta_correct_tax_name, spta_red_list_ec, spta_cites_name, spta_endemic, spta_exotic, spta_domestic, spta_native, spta_migratory, spta_alien, spta_specific_infraspecifc_epit, similarity(spta_scientific_name, ?1) AS sml from biodiversity.species_taxa where  similarity(spta_scientific_name, ?2) >= ?3 and spta_hierarchy_code like ?4 order by sml desc");
		// "SELECT
		// spta_id,spta_scientific_gui,spta_taxon_rank_name,spta_scientific_name,spta_higher_classification,
		// tara_id from biodiversity.species_taxa where spta_hierarchy_code like ?1 ");
		q.setParameter(1, nameSplit);
		q.setParameter(2, nameSplit);
		q.setParameter(3, (double) (matchAccuracy / 100.00));
		q.setParameter(4, idKingdom);
		q.setHint("javax.persistence.query.timeout", 840000);
		System.out.println((double) (matchAccuracy / 100.00));
		result = q.getResultList();
		
		if (result != null && !result.isEmpty()) {

			for (Object[] row : result) {
				RTNCResult specieR = new RTNCResult();
				specieR.setOrderSubmitted(orderSubmitted);
				specieR.setNameSubmitted(nameSplit);

				if (row[0] != null) {
					specieR.setId((Integer)row[0]);
				}
				
				if (row[1] != null) {
					specieR.setTaxonID(row[1].toString());
				}

				if (row[2] != null) {
					specieR.setTaxonRank(row[2].toString());
				}
				if (row[3] != null) {
					specieR.setScientificName(row[3].toString());
				}

				if (row[4] != null) {
					String[] tax = row[4].toString().split("\\|");
					if (tax.length > 1) {

						if (tax.length == 1) {

							specieR.setKingdom(tax[0]);

						}
						if (tax.length == 2) {
							if (tax[0] != null) {
								specieR.setKingdom(tax[0]);
							}
							if (tax[1] != null) {
								specieR.setPhylum(tax[1]);
							}
						}
						if (tax.length == 3) {

							if (tax[0] != null) {
								specieR.setKingdom(tax[0]);
							}
							if (tax[1] != null) {
								specieR.setPhylum(tax[1]);
							}
							if (tax[2] != null) {
								specieR.setClas(tax[2]);
							}

						}

						if (tax.length == 4) {

							if (tax[0] != null) {
								specieR.setKingdom(tax[0]);
							}
							if (tax[1] != null) {
								specieR.setPhylum(tax[1]);
							}
							if (tax[2] != null) {
								specieR.setClas(tax[2]);
							}

							if (tax[3] != null) {
								specieR.setOrder(tax[3]);
							}
						}
						if (tax.length == 5) {
							if (tax[0] != null) {
								specieR.setKingdom(tax[0]);
							}
							if (tax[1] != null) {
								specieR.setPhylum(tax[1]);
							}
							if (tax[2] != null) {
								specieR.setClas(tax[2]);
							}

							if (tax[3] != null) {
								specieR.setOrder(tax[3]);
							}
							if (tax[4] != null) {
								specieR.setFamily(tax[4]);
							}
						}
						if (tax.length == 6) {

							if (tax[0] != null) {
								specieR.setKingdom(tax[0]);
							}
							if (tax[1] != null) {
								specieR.setPhylum(tax[1]);
							}
							if (tax[2] != null) {
								specieR.setClas(tax[2]);
							}

							if (tax[3] != null) {
								specieR.setOrder(tax[3]);
							}
							if (tax[4] != null) {
								specieR.setFamily(tax[4]);
							}

							if (tax[5] != null) {
								specieR.setGenus(tax[5]);
							}
						}
					}
				}

				if (row[6] != null) {
					specieR.setScientificNameAuthorship(row[6].toString());
				}

				if (row[7] != null) {
					specieR.setNamePublishedInYear(row[7].toString());
				}

				if (row[8] != null) {
					specieR.setIsAcceptedName((boolean) row[8]);
				}

				if (row[9] != null) {
					specieR.setTaxonomicStatus(row[9].toString());
				}

				if (row[10] != null) {
					specieR.setCorrectName(row[10].toString());
				}

				if (row[11] != null) {
					specieR.setNationalRedList(row[11].toString());
				}

				if (row[12] != null) {
					specieR.setCites(row[12].toString());
				}
				if (row[13] != null) {
					specieR.setIsEndemic((boolean) row[13]);
				}

				if (row[14] != null) {
					specieR.setIsExotic((boolean) row[14]);
				}

				if (row[15] != null) {
					specieR.setIsDomestic((boolean) row[15]);
				}

				if (row[16] != null) {
					specieR.setIsNative((boolean) row[16]);
				}

				if (row[17] != null) {
					specieR.setIsMigratory((boolean) row[17]);
				}

				if (row[18] != null) {
					specieR.setIsInvasive((boolean) row[18]);
				}

				if (row[19] != null) {
					specieR.setSpecificInfraspecifcEpit(row[19].toString());
				}
				
				if (row[20] != null) {
					specieR.setOverallScore(Math.round(((Float) (row[20])) * 100));
				}

				//NameSubmitted name = new NameSubmitted();
				// specieR.setNameSubmitted(name);
				objectResult.add(specieR);
				count++;
			}
		}

		return count;
	}
	
	@SuppressWarnings("unchecked")
	private Integer consultByNameHigher(Integer matchAccuracy, List<RTNCResult> objectResult,
			Integer count, String nameSplit, String kingdom,Integer orderSubmitted) {

		List<Object[]> result;
		String idKingdom = getKingdomByName(kingdom) + ";%";
		Query q;
		EntityManager en = super.getEntityManager();
		q = en.createNativeQuery(
				"SELECT hicl_scientific_gui,hicl_taxon_rank_name,hicl_scientific_name,hicl_higher_classification,hicl_cientific_name_authorship, hicl_name_published_year , hicl_is_accepted_name, tast_name , similarity(hicl_scientific_name, ?1) AS sml, biodiversity.higher_classifications.hicl_id from biodiversity.higher_classifications, biodiversity.taxonomic_status where biodiversity.higher_classifications.tast_id=biodiversity.taxonomic_status.tast_id and  similarity(hicl_scientific_name, ?2) >= ?3 and hicl_hierarchy_code like ?4 order by sml desc");
		// "SELECT
		// spta_id,spta_scientific_gui,spta_taxon_rank_name,spta_scientific_name,spta_higher_classification,
		// tara_id from biodiversity.species_taxa where spta_hierarchy_code like ?1 ");
		q.setParameter(1, nameSplit);
		q.setParameter(2, nameSplit);
		q.setParameter(3, (double) (matchAccuracy / 100.00));
		q.setParameter(4, idKingdom);
		q.setHint("javax.persistence.query.timeout", 840000);
		System.out.println((double) (matchAccuracy / 100.00));
		result = q.getResultList();
		
		if (result != null && !result.isEmpty()) {

			for (Object[] row : result) {
				RTNCResult specieR = new RTNCResult();
				
				specieR.setOrderSubmitted(orderSubmitted);
				specieR.setNameSubmitted(nameSplit);

				if (row[0] != null) {
					specieR.setTaxonID(row[0].toString());
				}

				if (row[1] != null) {
					specieR.setTaxonRank(row[1].toString());
				}
				if (row[2] != null) {
					specieR.setScientificName(row[2].toString());
				}

				if (row[3] != null) {
					String[] tax = row[3].toString().split("\\|");
					if (tax.length > 1) {

						if (tax.length == 1) {

							specieR.setKingdom(tax[0]);

						}
						if (tax.length == 2) {
							if (tax[0] != null) {
								specieR.setKingdom(tax[0]);
							}
							if (tax[1] != null) {
								specieR.setPhylum(tax[1]);
							}
						}
						if (tax.length == 3) {

							if (tax[0] != null) {
								specieR.setKingdom(tax[0]);
							}
							if (tax[1] != null) {
								specieR.setPhylum(tax[1]);
							}
							if (tax[2] != null) {
								specieR.setClas(tax[2]);
							}

						}

						if (tax.length == 4) {

							if (tax[0] != null) {
								specieR.setKingdom(tax[0]);
							}
							if (tax[1] != null) {
								specieR.setPhylum(tax[1]);
							}
							if (tax[2] != null) {
								specieR.setClas(tax[2]);
							}

							if (tax[3] != null) {
								specieR.setOrder(tax[3]);
							}
						}
						if (tax.length == 5) {
							if (tax[0] != null) {
								specieR.setKingdom(tax[0]);
							}
							if (tax[1] != null) {
								specieR.setPhylum(tax[1]);
							}
							if (tax[2] != null) {
								specieR.setClas(tax[2]);
							}

							if (tax[3] != null) {
								specieR.setOrder(tax[3]);
							}
							if (tax[4] != null) {
								specieR.setFamily(tax[4]);
							}
						}
						if (tax.length == 6) {

							if (tax[0] != null) {
								specieR.setKingdom(tax[0]);
							}
							if (tax[1] != null) {
								specieR.setPhylum(tax[1]);
							}
							if (tax[2] != null) {
								specieR.setClas(tax[2]);
							}

							if (tax[3] != null) {
								specieR.setOrder(tax[3]);
							}
							if (tax[4] != null) {
								specieR.setFamily(tax[4]);
							}

							if (tax[5] != null) {
								specieR.setGenus(tax[5]);
							}
						}
					}
				}

				if (row[4] != null) {
					specieR.setScientificNameAuthorship(row[4].toString());
				}

				if (row[5] != null) {
					specieR.setNamePublishedInYear(row[5].toString());
				}

				if (row[6] != null) {
					specieR.setIsAcceptedName((boolean) row[6]);
				}

				if (row[7] != null) {
					specieR.setTaxonomicStatus(row[7].toString());
				}
				
				if (row[8] != null) {
					specieR.setOverallScore(Math.round(((Float) (row[8])) * 100));
				}
				
				if (row[9] != null) {
					specieR.setId((Integer) row[9]);
				}

				// NameSubmitted name = new NameSubmitted();
				// specieR.setNameSubmitted(name);
				objectResult.add(specieR);
				count++;
			}
		}

		return count;
	}

	@SuppressWarnings("unchecked")
	private Integer consultAll(List<RTNCResult> objectResult, Integer count, String kingdom) {

		List<Object[]> result;
		String idKingdom = getKingdomByName(kingdom) + ";%";
		Query q;
		System.out.println("Inicio: " + new Date());
		EntityManager en = super.getEntityManager();
		q = en.createNativeQuery(
				// "SELECT
				// spta_id,spta_scientific_gui,spta_taxon_rank_name,spta_scientific_name,spta_higher_classification,
				// tara_id, similarity(spta_scientific_name, ?1) AS sml from
				// biodiversity.species_taxa where similarity(spta_scientific_name, ?2) >= ?3
				// and spta_hierarchy_code like ?4 order by sml desc");
				"SELECT spta_id,spta_scientific_gui,spta_taxon_rank_name,spta_scientific_name,spta_higher_classification, tara_id, spta_scientific_name_authorship, spta_name_published_year , spta_is_accepted_name, spta_taxonomic_status_name, spta_correct_tax_name, spta_red_list_ec, spta_cites_name, spta_endemic, spta_exotic, spta_domestic, spta_native, spta_migratory, spta_alien, spta_specific_infraspecifc_epit  from biodiversity.species_taxa where  spta_hierarchy_code like ?1 ");
		q.setParameter(1, idKingdom);
		result = q.getResultList();

		if (result != null && !result.isEmpty()) {
			for (Object[] row : result) {
				RTNCResult specieR = new RTNCResult();

				if (row[0] != null) {
					specieR.setId((Integer)row[0]);
				}
				
				if (row[1] != null) {
					specieR.setTaxonID(row[1].toString());
				}

				if (row[2] != null) {
					specieR.setTaxonRank(row[2].toString());
				}
				if (row[3] != null) {
					specieR.setScientificName(row[3].toString());
				}

				if (row[4] != null) {
					String[] tax = row[4].toString().split("\\|");
					if (tax.length > 1) {

						if (tax.length == 1) {

							specieR.setKingdom(tax[0]);

						}
						if (tax.length == 2) {
							if (tax[0] != null) {
								specieR.setKingdom(tax[0]);
							}
							if (tax[1] != null) {
								specieR.setPhylum(tax[1]);
							}
						}
						if (tax.length == 3) {

							if (tax[0] != null) {
								specieR.setKingdom(tax[0]);
							}
							if (tax[1] != null) {
								specieR.setPhylum(tax[1]);
							}
							if (tax[2] != null) {
								specieR.setClas(tax[2]);
							}

						}

						if (tax.length == 4) {

							if (tax[0] != null) {
								specieR.setKingdom(tax[0]);
							}
							if (tax[1] != null) {
								specieR.setPhylum(tax[1]);
							}
							if (tax[2] != null) {
								specieR.setClas(tax[2]);
							}

							if (tax[3] != null) {
								specieR.setOrder(tax[3]);
							}
						}
						if (tax.length == 5) {
							if (tax[0] != null) {
								specieR.setKingdom(tax[0]);
							}
							if (tax[1] != null) {
								specieR.setPhylum(tax[1]);
							}
							if (tax[2] != null) {
								specieR.setClas(tax[2]);
							}

							if (tax[3] != null) {
								specieR.setOrder(tax[3]);
							}
							if (tax[4] != null) {
								specieR.setFamily(tax[4]);
							}
						}
						if (tax.length == 6) {

							if (tax[0] != null) {
								specieR.setKingdom(tax[0]);
							}
							if (tax[1] != null) {
								specieR.setPhylum(tax[1]);
							}
							if (tax[2] != null) {
								specieR.setClas(tax[2]);
							}

							if (tax[3] != null) {
								specieR.setOrder(tax[3]);
							}
							if (tax[4] != null) {
								specieR.setFamily(tax[4]);
							}

							if (tax[5] != null) {
								specieR.setGenus(tax[5]);
							}
						}
					}
				}

				if (row[6] != null) {
					specieR.setScientificNameAuthorship(row[6].toString());
				}

				if (row[7] != null) {
					specieR.setNamePublishedInYear(row[7].toString());
				}

				if (row[8] != null) {
					specieR.setIsAcceptedName((boolean) row[8]);
				}

				if (row[9] != null) {
					specieR.setTaxonomicStatus(row[9].toString());
				}

				if (row[10] != null) {
					specieR.setCorrectName(row[10].toString());
				}

				if (row[11] != null) {
					specieR.setNationalRedList(row[11].toString());
				}

				if (row[12] != null) {
					specieR.setCites(row[12].toString());
				}
				if (row[13] != null) {
					specieR.setIsEndemic((boolean) row[13]);
				}

				if (row[14] != null) {
					specieR.setIsExotic((boolean) row[14]);
				}

				if (row[15] != null) {
					specieR.setIsDomestic((boolean) row[15]);
				}

				if (row[16] != null) {
					specieR.setIsNative((boolean) row[16]);
				}

				if (row[17] != null) {
					specieR.setIsMigratory((boolean) row[17]);
				}

				if (row[18] != null) {
					specieR.setIsInvasive((boolean) row[18]);
				}

				if (row[19] != null) {
					specieR.setSpecificInfraspecifcEpit(row[19].toString());
				}

				//NameSubmitted name = new NameSubmitted();
				// specieR.setNameSubmitted(name);
				objectResult.add(specieR);
				count++;
			}
		}

		System.out.println("Fin: " + new Date());
		return count;
	}

	@SuppressWarnings("unchecked")
	private Integer consultAllHigher(List<RTNCResult> objectResult, Integer count, String kingdom) {

		List<Object[]> result;
		Integer idKin=getKingdomByName(kingdom);
		String idKingdom = idKin+ ";%";
		Query q;
		System.out.println("Inicio: " + new Date());
		EntityManager en = super.getEntityManager();
		q = en.createNativeQuery(
				// "SELECT
				// spta_id,spta_scientific_gui,spta_taxon_rank_name,spta_scientific_name,spta_higher_classification,
				// tara_id, similarity(spta_scientific_name, ?1) AS sml from
				// biodiversity.species_taxa where similarity(spta_scientific_name, ?2) >= ?3
				// and spta_hierarchy_code like ?4 order by sml desc");
				"SELECT hicl_scientific_gui,hicl_taxon_rank_name,hicl_scientific_name,hicl_higher_classification,hicl_cientific_name_authorship, hicl_name_published_year , hicl_is_accepted_name, tast_name, biodiversity.higher_classifications.hicl_id  from biodiversity.higher_classifications, biodiversity.taxonomic_status where biodiversity.higher_classifications.tast_id=biodiversity.taxonomic_status.tast_id and (hicl_hierarchy_code like ?1 or hicl_hierarchy_code = ?2) ");
		q.setParameter(1, idKingdom);
		q.setParameter(2, idKin.toString());
		result = q.getResultList();

		if (result != null && !result.isEmpty()) {
			for (Object[] row : result) {
				RTNCResult specieR = new RTNCResult();

				if (row[0] != null) {
					specieR.setTaxonID(row[0].toString());
				}

				if (row[1] != null) {
					specieR.setTaxonRank(row[1].toString());
				}
				if (row[2] != null) {
					specieR.setScientificName(row[2].toString());
				}

				if (row[3] != null) {
					String[] tax = row[3].toString().split("\\|");
					if (tax.length > 1) {

						
						if (tax.length == 2) {
							if (tax[0] != null) {
								specieR.setKingdom(tax[0]);
							}
							if (tax[1] != null) {
								specieR.setPhylum(tax[1]);
							}
						}
						if (tax.length == 3) {

							if (tax[0] != null) {
								specieR.setKingdom(tax[0]);
							}
							if (tax[1] != null) {
								specieR.setPhylum(tax[1]);
							}
							if (tax[2] != null) {
								specieR.setClas(tax[2]);
							}

						}

						if (tax.length == 4) {

							if (tax[0] != null) {
								specieR.setKingdom(tax[0]);
							}
							if (tax[1] != null) {
								specieR.setPhylum(tax[1]);
							}
							if (tax[2] != null) {
								specieR.setClas(tax[2]);
							}

							if (tax[3] != null) {
								specieR.setOrder(tax[3]);
							}
						}
						if (tax.length == 5) {
							if (tax[0] != null) {
								specieR.setKingdom(tax[0]);
							}
							if (tax[1] != null) {
								specieR.setPhylum(tax[1]);
							}
							if (tax[2] != null) {
								specieR.setClas(tax[2]);
							}

							if (tax[3] != null) {
								specieR.setOrder(tax[3]);
							}
							if (tax[4] != null) {
								specieR.setFamily(tax[4]);
							}
						}
						if (tax.length == 6) {

							if (tax[0] != null) {
								specieR.setKingdom(tax[0]);
							}
							if (tax[1] != null) {
								specieR.setPhylum(tax[1]);
							}
							if (tax[2] != null) {
								specieR.setClas(tax[2]);
							}

							if (tax[3] != null) {
								specieR.setOrder(tax[3]);
							}
							if (tax[4] != null) {
								specieR.setFamily(tax[4]);
							}

							if (tax[5] != null) {
								specieR.setGenus(tax[5]);
							}
						}
					}
					else
					{
						if (tax.length == 1) {

							specieR.setKingdom(tax[0]);

						}
					}
				}

				if (row[4] != null) {
					specieR.setScientificNameAuthorship(row[4].toString());
				}

				if (row[5] != null) {
					specieR.setNamePublishedInYear(row[5].toString());
				}

				if (row[6] != null) {
					specieR.setIsAcceptedName((boolean) row[6]);
				}

				if (row[7] != null) {
					specieR.setTaxonomicStatus(row[7].toString());
				}
				
				if (row[8] != null) {
					specieR.setId((Integer)row[8]);
				}

				// NameSubmitted name = new NameSubmitted();
				// specieR.setNameSubmitted(name);
				objectResult.add(specieR);
				count++;
			}
		}

		System.out.println("Fin: " + new Date());
		return count;
	}

	private Integer getKingdomByName(String kingdom) {
		List<HigherClassification> kingdomOb = higherClassificationFacade.findByScientificNameAndRank(kingdom, 1);
		return kingdomOb.get(0).getHiclId();
	}

	public double stringSimilarityScore(List<char[]> bigram1, List<char[]> bigram2) {
		List<char[]> copy = new ArrayList<char[]>(bigram2);
		int matches = 0;
		for (int i = bigram1.size(); --i >= 0;) {
			char[] bigram = bigram1.get(i);
			for (int j = copy.size(); --j >= 0;) {
				char[] toMatch = copy.get(j);
				if (bigram[0] == toMatch[0] && bigram[1] == toMatch[1] && bigram[2] == toMatch[2]&& bigram[3] == toMatch[3]) {
					copy.remove(j);
					matches += 1;
					break;
				}
			}
		}
		int trigramSimil=(bigram1.size() + bigram2.size())-matches;
		return (double) matches / trigramSimil;
	}

	public List<char[]> bigram(String input) {
		ArrayList<char[]> bigram = new ArrayList<char[]>();
		if(input.length()>2)
		{
			//char[] chars = new char[3];
			//chars[0] = ' ';
			//chars[1] = ' ';
			//chars[2] = input.charAt(0);
			
			//bigram.add(chars);
			
			char[] chars2 = new char[4];
			chars2[0] = ' ';
			chars2[1] = input.charAt(0);
			chars2[2] = input.charAt(1);
			chars2[3] = input.charAt(2);
			bigram.add(chars2);
			
			//char[] chars3 = new char[3];
			//chars3[0] = input.charAt((input.length())-2);
			//chars3[1] = input.charAt((input.length())-1);
		    //chars3[2] = ' ';
			
			//bigram.add(chars3);
		}
		for (int i = 0; i < input.length() - 3; i++) {
			char[] chars = new char[4];
			chars[0] = input.charAt(i);
			chars[1] = input.charAt(i + 1);
			chars[2] = input.charAt(i + 2);
			chars[3] = input.charAt(i + 3);
			
			bigram.add(chars);
		}
		return bigram;
	}

	public void evaluateShortCoincidence(String onlyBestMach, Integer count, String[] nameSubmittedSplit,
			List<RTNCResult> objectAllResult,  Integer matchAccuracy,String kingdom, String nameSubmitted) {
		System.out.println("Contador: " + count);
		List<RTNCResult> allresult;
		Integer countResult = 0;
		Integer orderSubmitted=1;
		System.out.println("Inicio similitud: " + new Date());
		if (onlyBestMach.equals("0")) {
			if (nameSubmittedSplit.length > 1) {
				for (String nameSplit : nameSubmittedSplit) {
					String namSE = nameSplit.trim();
					allresult = new ArrayList<>();
					if (namSE != null && (namSE.split(" ").length <= 1)) {
						consultByNameHigher(matchAccuracy, allresult, countResult, nameSplit, kingdom,orderSubmitted);
					} else {
						consultByNameHigher(matchAccuracy, allresult, countResult, nameSplit, kingdom,orderSubmitted);
						consultByNameSpecie(matchAccuracy, allresult, countResult, nameSplit, kingdom,orderSubmitted);
						
					}
					Collections.sort(allresult, new Comparator<RTNCResult>() {

						@Override
						public int compare(RTNCResult o1, RTNCResult o2) {
							
							return Integer.valueOf(o1.getOverallScore()).compareTo(Integer.valueOf(o2.getOverallScore()));
						}

					});
					objectAllResult.addAll(allresult);
					orderSubmitted++;
				}
					
			} else {
				
				allresult = new ArrayList<>();
				if (nameSubmitted != null && (nameSubmitted.split(" ").length <= 1)) {
					consultByNameHigher(matchAccuracy, allresult, countResult, nameSubmitted, kingdom,orderSubmitted);
				} else {
					consultByNameHigher(matchAccuracy, allresult, countResult, nameSubmitted, kingdom,orderSubmitted);
					consultByNameSpecie(matchAccuracy, allresult, countResult, nameSubmitted, kingdom,orderSubmitted);
					
				}

				Collections.sort(allresult, new Comparator<RTNCResult>() {

					@Override
					public int compare(RTNCResult o1, RTNCResult o2) {
						
						return Integer.valueOf(o1.getOverallScore()).compareTo(Integer.valueOf(o2.getOverallScore()));
					}

				});
				if (!allresult.isEmpty()) {
					objectAllResult.addAll(allresult);
				}
			}
		} else {
			if (nameSubmittedSplit.length > 1) {
				for (String nameSplit : nameSubmittedSplit) {
					String namSE = nameSplit.trim();
					allresult = new ArrayList<>();
					if (namSE != null && (namSE.split(" ").length <= 1)) {
						consultByNameHigher(matchAccuracy, allresult, countResult, nameSplit, kingdom,orderSubmitted);
					} else {
						consultByNameHigher(matchAccuracy, allresult, countResult, nameSplit, kingdom,orderSubmitted);
						consultByNameSpecie(matchAccuracy, allresult, countResult, nameSplit, kingdom,orderSubmitted);
						
					}
					
					Collections.sort(allresult, new Comparator<RTNCResult>() {

						@Override
						public int compare(RTNCResult o1, RTNCResult o2) {
							
							return Integer.valueOf(o2.getOverallScore()).compareTo(Integer.valueOf(o1.getOverallScore()));
						}

					});
										
					if (!allresult.isEmpty()) {
						objectAllResult.add(allresult.get(0));
					}
					orderSubmitted++;
				}
			} else {
				allresult = new ArrayList<>();
				if (nameSubmitted != null && (nameSubmitted.split(" ").length <= 1)) {
					consultByNameHigher(matchAccuracy, allresult, countResult, nameSubmitted, kingdom,orderSubmitted);
				} else {
					consultByNameHigher(matchAccuracy, allresult, countResult, nameSubmitted, kingdom,orderSubmitted);
					consultByNameSpecie(matchAccuracy, allresult, countResult, nameSubmitted, kingdom,orderSubmitted);
					
				}
				
				Collections.sort(allresult, new Comparator<RTNCResult>() {

					@Override
					public int compare(RTNCResult o1, RTNCResult o2) {
						
						return Integer.valueOf(o2.getOverallScore()).compareTo(Integer.valueOf(o1.getOverallScore()));
					}

				});
						
				if (!allresult.isEmpty()) {
					objectAllResult.add(allresult.get(0));
				}
			}

		}
		System.out.println("Fin similitud: " + new Date());
		System.out.println("Contador coincidencias: " + countResult);
	}

	
}
