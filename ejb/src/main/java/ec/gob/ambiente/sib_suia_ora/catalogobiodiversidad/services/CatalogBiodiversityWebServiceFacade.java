package ec.gob.ambiente.sib_suia_ora.catalogobiodiversidad.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.RolesUser;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.redlist.model.RedListsEcuador;
import ec.gob.ambiente.enlisy.speciescatalog.model.ArtificialGroup;
import ec.gob.ambiente.enlisy.speciescatalog.model.Cite;
import ec.gob.ambiente.enlisy.speciescatalog.model.HigherClassification;
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecieTaxa;
import ec.gob.ambiente.enlisy.speciescatalog.model.TaxaRank;
import ec.gob.ambiente.enlisy.speciescatalog.model.TaxonomicStatus;
import ec.gob.ambiente.enlisy.speciescatalog.services.ArtificialGroupFacade;
import ec.gob.ambiente.enlisy.speciescatalog.services.HigherClassificationFacade;
import ec.gob.ambiente.enlisy.speciescatalog.services.TaxaRankFacade;
import ec.gob.ambiente.sib_suia_ora.catalogobiodiversidad.model.RTResultado;
import ec.gob.ambiente.suia.exceptions.ServiceException;

/**
 * Servicios para la consulta de la informacion taxonomica de un nombre o
 * nombres cientificos enviados como parametro en el servicio web de resolucion
 * taxonomica
 * 
 * @author CristinaFactos
 *
 */
@Stateless
public class CatalogBiodiversityWebServiceFacade extends AbstractFacade<SpecieTaxa, Integer> {

	public CatalogBiodiversityWebServiceFacade() {
		super(SpecieTaxa.class, Integer.class);
	}

	@EJB
	HigherClassificationFacade higherClassificationFacade;

	@EJB
	ArtificialGroupFacade artificialGroupFacade;

	@EJB
	TaxaRankFacade taxaRankFacade;

	@SuppressWarnings("unchecked")
	public List<RTResultado> getTaxonomyByFilter(Integer levelRank, String sName, String sTaxonomic, String artificialG,
			String evalArtificialG, Boolean end, Boolean exo, Boolean dom, Boolean nat, Boolean mig, Boolean inv,
			Boolean ecu, String cites, String lrEC, String guiPadre, Integer level) {
		Map<Integer, Integer> map = new LinkedHashMap<>();
		List<RTResultado> objectResult = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("");
		
		if(level>0)
		{
			
			if (sName.equalsIgnoreCase("ALL")) {
				sql.append("SELECT spta_id,spta_scientific_gui,spta_taxon_rank_name,spta_scientific_name,spta_higher_classification, tara_id, spta_scientific_name_authorship, spta_name_published_year , spta_is_accepted_name, spta_taxonomic_status_name, spta_correct_tax_name, spta_red_list_ec, spta_cites_name, spta_endemic, spta_exotic, spta_domestic, spta_native, spta_migratory, spta_alien, spta_specific_infraspecifc_epit,artificialgroup, spta_hierarchy_code FROM biodiversity.mvw_taxonomy WHERE tara_id = ");
				sql.append(level);
				
				if (sTaxonomic != null && !sTaxonomic.equals("")) {
					TaxonomicStatus status = getTaxonomicStatusByCode(sTaxonomic);
					sql.append(" and tast_id = ");
					sql.append(status.getTastId());
				}
				
				if (end != null) {
					if (end) {
						sql.append(" and spta_endemic = true");
					} else {
						sql.append(" and spta_endemic = false");
					}
				}

				if (exo != null) {
					if (exo) {
						sql.append(" and spta_exotic = true");
					} else {
						sql.append(" and spta_exotic = false");
					}
				}

				if (dom != null) {
					if (dom) {
						sql.append(" and spta_domestic = true");
					} else {
						sql.append(" and spta_domestic = false");
					}
				}

				if (nat != null) {
					if (nat) {
						sql.append(" and spta_native = true");
					} else {
						sql.append(" and spta_native = false");
					}
				}

				if (mig != null) {
					if (mig) {
						sql.append(" and spta_migratory= true");
					} else {
						sql.append(" and spta_migratory= false");
					}
				}

				if (inv != null) {
					if (inv) {
						sql.append(" and spta_alien= true");
					} else {
						sql.append(" and spta_alien= false");
					}
				}
				if (ecu != null) {
					if (ecu) {
						sql.append(" and spta_ecuador= true");
					} else {
						sql.append(" and spta_ecuador= false");
					}
				}
				if (cites != null && !cites.equals("")) {
					Cite cite = getCiteByCode(cites);
					sql.append(" and cite_id=");
					sql.append(cite.getCiteId());
				}

				if (lrEC != null && !lrEC.equals("")) {
					RedListsEcuador redList = getRedListByCode(lrEC);
					sql.append(" and rlec_id=");
					sql.append(redList.getId());
				}
				
				if (artificialG != null && !artificialG.equals("")) {
					String[] groups = artificialG.split(";");
					List<String> groupsO = Arrays.asList(groups);
					Collections.sort(groupsO);
					if (groups.length > 1) {
						StringBuilder idGr = new StringBuilder();
						idGr.append("");
						List<ArtificialGroup> groupsN = new ArrayList<>();
						for (int i = 0; i < groupsO.size(); i++) {
							List<ArtificialGroup> artG = getArtificialGroupByCodeAll(groupsO.get(i));
							if (artG != null && !artG.isEmpty()) {
								groupsN.addAll(artG);
							}

						}
						for (int i = 0; i < groupsN.size(); i++) {
							if (i == (groupsN.size() - 1)) {
								idGr.append(groupsN.get(i).getArgrId());
							} else {
								idGr.append(groupsN.get(i).getArgrId());
								idGr.append(",");
							}

						}

						if (evalArtificialG.equalsIgnoreCase("O")) {
							sql.append(
									" and (artificiaLgroupid && ARRAY[");
							sql.append(idGr.toString());
							sql.append("])");

						} else {
							sql.append(
									" and (artificiaLgroupid = ARRAY[");
							sql.append(idGr.toString());
							sql.append("])");
						}

					} else {

						StringBuilder idGr = new StringBuilder();
						List<ArtificialGroup> artG = getArtificialGroupByCodeAll(groups[0]);
						for (int i = 0; i < artG.size(); i++) {
							if (i == (artG.size() - 1)) {
								idGr.append(artG.get(i).getArgrId());
							} else {
								idGr.append(artG.get(i).getArgrId());
								idGr.append(",");
							}

						}
						sql.append(" and (artificiaLgroupid && ARRAY[");
						sql.append(idGr.toString());
						sql.append("])");

					}
				}
				
			}
			else
			{
				List<TaxaRank> rank = taxaRankFacade.findRankForLevelAndSpanish(levelRank);
				sql.append("SELECT spta_id,spta_scientific_gui,spta_taxon_rank_name,spta_scientific_name,spta_higher_classification, tara_id, spta_scientific_name_authorship, spta_name_published_year , spta_is_accepted_name, spta_taxonomic_status_name, spta_correct_tax_name, spta_red_list_ec, spta_cites_name, spta_endemic, spta_exotic, spta_domestic, spta_native, spta_migratory, spta_alien, spta_specific_infraspecifc_epit,artificialgroup, spta_hierarchy_code FROM biodiversity.mvw_taxonomy WHERE tara_id > ");
				sql.append(rank.get(0).getTaraId());
				sql.append(" and tara_id =");
				sql.append(level);
				
				List<HigherClassification> hig = findByScientificName(sName, rank.get(0).getTaraId());
				
				if (levelRank == 10) {
					sql.append(" and spta_hierarchy_code LIKE '");
					sql.append(hig.get(0).getId());
					sql.append(";");
					sql.append("%'");

				}
				
				if (levelRank == 20) {
					sql.append(" and spta_hierarchy_code LIKE '%;");
					sql.append(hig.get(0).getId());
					sql.append(";");
					sql.append("%'");

				
				}

				if (levelRank == 30) {
					sql.append(" and spta_hierarchy_code LIKE '%;%;");
					sql.append(hig.get(0).getId());
					sql.append(";");
					sql.append("%'");

				
				}

				if (levelRank == 40) {
					sql.append(" and spta_hierarchy_code LIKE '%;%;%;");
					sql.append(hig.get(0).getId());
					sql.append(";");
					sql.append("%'");

					
				}

				if (levelRank == 50) {
					sql.append(" and spta_hierarchy_code LIKE '%;%;%;%;");
					sql.append(hig.get(0).getId());
					sql.append(";");
					sql.append("%'");

					
				}
				
				if (levelRank == 60) {
					sql.append(" and spta_hierarchy_code LIKE '%;%;%;%;%;");
					sql.append(hig.get(0).getId());
					sql.append(";");
					sql.append("%'");

					
				}

				if (sTaxonomic != null && !sTaxonomic.equals("")) {
					TaxonomicStatus status = getTaxonomicStatusByCode(sTaxonomic);
					sql.append(" and tast_id = ");
					sql.append(status.getTastId());
					
				}
				
				if (end != null) {
					if (end) {
						sql.append(" and spta_endemic = true");
					} else {
						sql.append(" and spta_endemic = false");
					}
				}

				if (exo != null) {
					if (exo) {
						sql.append(" and spta_exotic = true");
					} else {
						sql.append(" and spta_exotic = false");
					}
				}

				if (dom != null) {
					if (dom) {
						sql.append(" and spta_domestic = true");
					} else {
						sql.append(" and spta_domestic = false");
					}
				}

				if (nat != null) {
					if (nat) {
						sql.append(" and spta_native = true");
					} else {
						sql.append(" and spta_native = false");
					}
				}

				if (mig != null) {
					if (mig) {
						sql.append(" and spta_migratory= true");
					} else {
						sql.append(" and spta_migratory= false");
					}
				}

				if (inv != null) {
					if (inv) {
						sql.append(" and spta_alien= true");
					} else {
						sql.append(" and spta_alien= false");
					}
				}
				if (ecu != null) {
					if (ecu) {
						sql.append(" and spta_ecuador= true");
					} else {
						sql.append(" and spta_ecuador= false");
					}
				}
				if (cites != null && !cites.equals("")) {
					Cite cite = getCiteByCode(cites);
					sql.append(" and cite_id=");
					sql.append(cite.getCiteId());
				}

				if (lrEC != null && !lrEC.equals("")) {
					RedListsEcuador redList = getRedListByCode(lrEC);
					sql.append(" and rlec_id=");
					sql.append(redList.getId());
				}
				
				
				if (artificialG != null && !artificialG.equals("")) {
					String[] groups = artificialG.split(";");
					List<String> groupsO = Arrays.asList(groups);
					Collections.sort(groupsO);
					if (groups.length > 1) {
						StringBuilder idGr = new StringBuilder();
						idGr.append("");
						List<ArtificialGroup> groupsN = new ArrayList<>();
						for (int i = 0; i < groupsO.size(); i++) {
							List<ArtificialGroup> artG = getArtificialGroupByCode(groupsO.get(i), guiPadre);
							if (artG != null && !artG.isEmpty()) {
								groupsN.addAll(artG);
							}

						}
						for (int i = 0; i < groupsN.size(); i++) {
							if (i == (groupsN.size() - 1)) {
								idGr.append(groupsN.get(i).getArgrId());
							} else {
								idGr.append(groupsN.get(i).getArgrId());
								idGr.append(",");
							}

						}
						if (evalArtificialG.equalsIgnoreCase("O")) {
							sql.append(" and (artificiaLgroupid && ARRAY[");
							sql.append(idGr.toString());
							sql.append("])");

						} else {
							sql.append(" and (artificiaLgroupid = ARRAY[");
							sql.append(idGr.toString());
							sql.append("])");
						}

					} else {

						StringBuilder idGr = new StringBuilder();
						List<ArtificialGroup> artG = getArtificialGroupByCode(groups[0], guiPadre);
						for (int i = 0; i < artG.size(); i++) {
							if (i == (artG.size() - 1)) {
								idGr.append(artG.get(i).getArgrId());
							} else {
								idGr.append(artG.get(i).getArgrId());
								idGr.append(",");
							}

						}

				sql.append(" and (artificiaLgroupid && ARRAY[");
				sql.append(idGr.toString());
				sql.append("])");

					}
				}
			}
		}
		else
		{
			if (sName.equalsIgnoreCase("ALL")) {
				sql.append("SELECT spta_id,spta_scientific_gui,spta_taxon_rank_name,spta_scientific_name,spta_higher_classification, tara_id, spta_scientific_name_authorship, spta_name_published_year , spta_is_accepted_name, spta_taxonomic_status_name, spta_correct_tax_name, spta_red_list_ec, spta_cites_name, spta_endemic, spta_exotic, spta_domestic, spta_native, spta_migratory, spta_alien, spta_specific_infraspecifc_epit,artificialgroup, spta_hierarchy_code FROM biodiversity.mvw_taxonomy WHERE tara_id>0");
				
				if (sTaxonomic != null && !sTaxonomic.equals("")) {
					TaxonomicStatus status = getTaxonomicStatusByCode(sTaxonomic);
					sql.append(" and tast_id = ");
					sql.append(status.getTastId());
				}
			}
			else
			{
				List<TaxaRank> rank = taxaRankFacade.findRankForLevelAndSpanish(levelRank);
				sql.append("SELECT spta_id,spta_scientific_gui,spta_taxon_rank_name,spta_scientific_name,spta_higher_classification, tara_id, spta_scientific_name_authorship, spta_name_published_year , spta_is_accepted_name, spta_taxonomic_status_name, spta_correct_tax_name, spta_red_list_ec, spta_cites_name, spta_endemic, spta_exotic, spta_domestic, spta_native, spta_migratory, spta_alien, spta_specific_infraspecifc_epit,artificialgroup, spta_hierarchy_code FROM biodiversity.mvw_taxonomy WHERE tara_id > ");
				sql.append(rank.get(0).getTaraId());
				List<HigherClassification> hig = findByScientificName(sName, rank.get(0).getTaraId());
				
				if (levelRank == 10) {
					sql.append(" and spta_hierarchy_code LIKE '");
					sql.append(hig.get(0).getId());
					sql.append(";");
					sql.append("%'");

				}
				
				if (levelRank == 20) {
					sql.append(" and spta_hierarchy_code LIKE '%;");
					sql.append(hig.get(0).getId());
					sql.append(";");
					sql.append("%'");

				
				}

				if (levelRank == 30) {
					sql.append(" and spta_hierarchy_code LIKE '%;%;");
					sql.append(hig.get(0).getId());
					sql.append(";");
					sql.append("%'");

				
				}

				if (levelRank == 40) {
					sql.append(" and spta_hierarchy_code LIKE '%;%;%;");
					sql.append(hig.get(0).getId());
					sql.append(";");
					sql.append("%'");

					
				}

				if (levelRank == 50) {
					sql.append(" and spta_hierarchy_code LIKE '%;%;%;%;");
					sql.append(hig.get(0).getId());
					sql.append(";");
					sql.append("%'");

					
				}
				
				if (levelRank == 60) {
					sql.append(" and spta_hierarchy_code LIKE '%;%;%;%;%;");
					sql.append(hig.get(0).getId());
					sql.append(";");
					sql.append("%'");

					
				}

				if (sTaxonomic != null && !sTaxonomic.equals("")) {
					TaxonomicStatus status = getTaxonomicStatusByCode(sTaxonomic);
					sql.append(" and tast_id = ");
					sql.append(status.getTastId());
					
				}
				
			}
			
		}
		sql.append(" ORDER BY tara_id");
		List<Object[]> result;
		Query q;
		System.out.println("El sql es: "+sql.toString());
		EntityManager en = super.getEntityManager();
		q = en.createNativeQuery(sql.toString());
		result = q.getResultList();
		if (result != null && !result.isEmpty()) {
			for (Object[] row : result) {
				RTResultado specieR = new RTResultado();

				if (row[0] != null) {
					specieR.setId((Integer) row[0]);
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
						specieR.setKingdom(row[4].toString());
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
					specieR.setArtificialGroup(row[20].toString());
				}

				if (row[21] != null) {
					String[] codigos = (row[21].toString()).split(";");
					for (int i = 1; i < codigos.length-1; i++) {
						map.put(Integer.parseInt(codigos[i]), Integer.parseInt(codigos[i]));
					}
				}

				// NameSubmitted name = new NameSubmitted();
				// specieR.setNameSubmitted(name);
				objectResult.add(specieR);

			}
		}
		System.out.println("Total de objetos : "+objectResult.size());
		
	
		return objectResult;

	}

	@SuppressWarnings("unused")
	private Integer getKingdomByName(String kingdom) {
		List<HigherClassification> kingdomOb = higherClassificationFacade.findByScientificNameAndRank(kingdom, 1);
		return kingdomOb.get(0).getHiclId();
	}

	/**
	 * Metodo para obtener un estatus taxonomico segun el codigo
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public TaxonomicStatus getTaxonomicStatusByCode(String codeStatus) {
		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM TaxonomicStatus o where o.tastStatus = true and UPPER(o.tastCode) =:codeStatus");
			query.setParameter("codeStatus", codeStatus.toUpperCase());
			List<TaxonomicStatus> result = (List<TaxonomicStatus>) query.getResultList();
			if (result.size() > 0)
				return result.get(0);

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}

	/**
	 * Metodo para obtener un grupo artificial segun el codigo
	 * 
	 * @param codeArtificial
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ArtificialGroup> getArtificialGroupByCode(String codeArtificial, String codigoGui) {
		try {
			List<ArtificialGroup> result = null;
			if (codigoGui.equals("1")) {
				Query query = super.getEntityManager().createQuery(
						"SELECT o FROM ArtificialGroup o where o.argrStatus = true and UPPER(o.argrCode) =:codeArtificial and o.argrPlantae=true");
				query.setParameter("codeArtificial", codeArtificial.toUpperCase());
				result = (List<ArtificialGroup>) query.getResultList();

			}
			if (codigoGui.equals("42931")) {
				Query query = super.getEntityManager().createQuery(
						"SELECT o FROM ArtificialGroup o where o.argrStatus = true and UPPER(o.argrCode) =:codeArtificial and o.argrAnimal=true");
				query.setParameter("codeArtificial", codeArtificial.toUpperCase());
				result = (List<ArtificialGroup>) query.getResultList();

			}
			if (codigoGui.equals("51228")) {
				Query query = super.getEntityManager().createQuery(
						"SELECT o FROM ArtificialGroup o where o.argrStatus = true and UPPER(o.argrCode) =:codeArtificial and o.argrFungi=true");
				query.setParameter("codeArtificial", codeArtificial.toUpperCase());
				result = (List<ArtificialGroup>) query.getResultList();

			}
			if (codigoGui.equals("45538")) {
				Query query = super.getEntityManager().createQuery(
						"SELECT o FROM ArtificialGroup o where o.argrStatus = true and UPPER(o.argrCode) =:codeArtificial and o.argrEubacteria=true");
				query.setParameter("codeArtificial", codeArtificial.toUpperCase());
				result = (List<ArtificialGroup>) query.getResultList();

			}
			if (codigoGui.equals("45549")) {
				Query query = super.getEntityManager().createQuery(
						"SELECT o FROM ArtificialGroup o where o.argrStatus = true and UPPER(o.argrCode) =:codeArtificial and o.argrArcheobacteria=true");
				query.setParameter("codeArtificial", codeArtificial.toUpperCase());
				result = (List<ArtificialGroup>) query.getResultList();

			}
			if (codigoGui.equals("45588")) {
				Query query = super.getEntityManager().createQuery(
						"SELECT o FROM ArtificialGroup o where o.argrStatus = true and UPPER(o.argrCode) =:codeArtificial and o.argrProtista=true");
				query.setParameter("codeArtificial", codeArtificial.toUpperCase());
				result = (List<ArtificialGroup>) query.getResultList();

			}

			if (codigoGui.equals("43621")) {
				Query query = super.getEntityManager().createQuery(
						"SELECT o FROM ArtificialGroup o where o.argrStatus = true and UPPER(o.argrCode) =:codeArtificial and o.argrChromista=true");
				query.setParameter("codeArtificial", codeArtificial.toUpperCase());
				result = (List<ArtificialGroup>) query.getResultList();

			}
			if (codigoGui.equals("57110")) {
				Query query = super.getEntityManager().createQuery(
						"SELECT o FROM ArtificialGroup o where o.argrStatus = true and UPPER(o.argrCode) =:codeArtificial and o.argrViruses=true");
				query.setParameter("codeArtificial", codeArtificial.toUpperCase());
				result = (List<ArtificialGroup>) query.getResultList();

			}

			if (result != null && result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<ArtificialGroup> getArtificialGroupByCodeAll(String codeArtificial) {
		try {
			List<ArtificialGroup> result = null;

			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM ArtificialGroup o where o.argrStatus = true and UPPER(o.argrCode) =:codeArtificial ");
			query.setParameter("codeArtificial", codeArtificial.toUpperCase());
			result = (List<ArtificialGroup>) query.getResultList();

			if (result != null && result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}

	/**
	 * Metodo para obtener cite segun el codigo
	 * 
	 * @param codeArtificial
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Cite getCiteByCode(String codeCite) {
		try {
			Query query = super.getEntityManager()
					.createQuery("SELECT o FROM Cite o where o.citeStatus = true and UPPER(o.citeName) =:codeCite");
			query.setParameter("codeCite", codeCite.toUpperCase());
			List<Cite> result = (List<Cite>) query.getResultList();
			if (result.size() > 0)
				return result.get(0);

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}

	/**
	 * Metodo para obtener lista roja segun el codigo
	 * 
	 * @param codeArtificial
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public RedListsEcuador getRedListByCode(String codeRedList) {
		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM RedListsEcuador o where o.rlecStatus = true and UPPER(o.rlecInitial) =:codeRedList");
			query.setParameter("codeRedList", codeRedList.toUpperCase());
			List<RedListsEcuador> result = (List<RedListsEcuador>) query.getResultList();
			if (result.size() > 0)
				return result.get(0);

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}

	/**
	 * Buscar una clasificacion por nombre cientifica
	 * 
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HigherClassification> findByScientificName(String name, Integer rankId) {
		try {
			Query query = super.getEntityManager().createQuery(
					"select o from HigherClassification o where upper(o.hiclScientificName) =:nombre and o.hiclStatus = true and o.taxaRank.taraId =:rankId");
			query.setParameter("nombre", name.toUpperCase());
			query.setParameter("rankId", rankId);
			List<HigherClassification> result = (List<HigherClassification>) query.getResultList();
			if (result.size() > 0)
				return result;
		} catch (NoResultException e) {
			return new ArrayList<HigherClassification>();
		}
		return new ArrayList<HigherClassification>();
	}

	/**
	 * Buscar Roles por usuario
	 * 
	 * @param userName
	 * @param roleName
	 * @return Devuelve null si no encuntra ningun registro
	 */
	public List<RolesUser> findByUserNameAndRoleName(String userName) {
		TypedQuery<RolesUser> query = super.getEntityManager().createQuery(""
				+ "select o from RolesUser o , UserAccessWebService ua where o.user.userStatus=true and o.user.userName = :userName and (o.role.roleName='Biodiversidad-Servicios Web SIB') and o.rousStatus = true and o.user.userId = ua.userId and ua.uawsStatus=true and ua.uawsWsCatalogTaxonomic=true and ua.uawsAccessStatus='APROBADO' ",
				RolesUser.class);
		query.setParameter("userName", userName);

		List<RolesUser> result = (List<RolesUser>) query.getResultList();
		if (result.size() > 0)
			return result;
		return null;
	}

	/**
	 * Buscar usuario por username y contraseña
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	public User findByUserNameAndPassw(String userName, String password) throws ServiceException {
		TypedQuery<User> query = super.getEntityManager().createQuery(""
				+ "select o from User o where o.userStatus=true and o.userName = :userName and o.userPassword= :userPass and  o.userStatus = true ",
				User.class);
		query.setParameter("userName", userName);
		query.setParameter("userPass", password);

		List<User> result = (List<User>) query.getResultList();
		if (result != null && result.size() > 0)
			return result.get(0);
		return null;
	}

	/**
	 * Buscar usuario por username
	 * 
	 * @param userName
	 * 
	 * @return
	 */
	public User findByUserName(String userName) throws ServiceException {
		TypedQuery<User> query = super.getEntityManager().createQuery(
				"" + "select o from User o where o.userStatus=true and o.userName = :userName and o.userStatus = true ",
				User.class);
		query.setParameter("userName", userName);

		List<User> result = (List<User>) query.getResultList();
		if (result != null && result.size() > 0)
			return result.get(0);
		return null;
	}

	/**
	 * Metodo para obtener un grupo artificial segun el codigo
	 * 
	 * @param codeArtificial
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TaxonomicStatus> getTaxonomicStatusByCodeAndKigdom(String codeStatus, String codigoGui) {
		try {
			List<TaxonomicStatus> result = null;
			if (codigoGui.equals("1")) {
				Query query = super.getEntityManager().createQuery(
						"SELECT o FROM TaxonomicStatus o where o.tastStatus = true and UPPER(o.tastCode) =:codeStatus and o.tastPlantae=true");
				query.setParameter("codeStatus", codeStatus.toUpperCase());
				result = (List<TaxonomicStatus>) query.getResultList();

			}
			if (codigoGui.equals("42931")) {
				Query query = super.getEntityManager().createQuery(
						"SELECT o FROM TaxonomicStatus o where o.tastStatus = true and UPPER(o.tastCode) =:codeStatus and o.tastAnimal=true");
				query.setParameter("codeStatus", codeStatus.toUpperCase());
				result = (List<TaxonomicStatus>) query.getResultList();

			}
			if (codigoGui.equals("51228")) {
				Query query = super.getEntityManager().createQuery(
						"SELECT o FROM TaxonomicStatus o where o.tastStatus = true and UPPER(o.tastCode) =:codeStatus and o.tastFungi=true");
				query.setParameter("codeStatus", codeStatus.toUpperCase());
				result = (List<TaxonomicStatus>) query.getResultList();

			}
			if (codigoGui.equals("45538")) {
				Query query = super.getEntityManager().createQuery(
						"SELECT o FROM TaxonomicStatus o where o.tastStatus = true and UPPER(o.tastCode) =:codeStatus and o.tastEubacteria=true");
				query.setParameter("codeStatus", codeStatus.toUpperCase());
				result = (List<TaxonomicStatus>) query.getResultList();

			}
			if (codigoGui.equals("45549")) {
				Query query = super.getEntityManager().createQuery(
						"SELECT o FROM TaxonomicStatus o where o.tastStatus = true and UPPER(o.tastCode) =:codeStatus and o.tastArqueobacteria=true");
				query.setParameter("codeStatus", codeStatus.toUpperCase());
				result = (List<TaxonomicStatus>) query.getResultList();

			}
			if (codigoGui.equals("45588")) {
				Query query = super.getEntityManager().createQuery(
						"SELECT o FROM TaxonomicStatus o where o.tastStatus = true and UPPER(o.tastCode) =:codeStatus and o.tastProtista=true");
				query.setParameter("codeStatus", codeStatus.toUpperCase());
				result = (List<TaxonomicStatus>) query.getResultList();

			}

			if (codigoGui.equals("ec.bio.spp.43621")) {
				Query query = super.getEntityManager().createQuery(
						"SELECT o FROM TaxonomicStatus o where o.tastStatus = true and UPPER(o.tastCode) =:codeStatus and o.tastChromista=true");
				query.setParameter("codeStatus", codeStatus.toUpperCase());
				result = (List<TaxonomicStatus>) query.getResultList();

			}
			if (codigoGui.equals("57110")) {
				Query query = super.getEntityManager().createQuery(
						"SELECT o FROM TaxonomicStatus o where o.tastStatus = true and UPPER(o.tastCode) =:codeStatus and o.tastViruses=true");
				query.setParameter("codeStatus", codeStatus.toUpperCase());
				result = (List<TaxonomicStatus>) query.getResultList();

			}

			if (result != null && result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}
	
	/**
	 * Validar si un usuario tiene asignado el permiso de acceso al servicio web de consulta de sumario
	 * @param userName
	 * @return
	 */
	public List<RolesUser> findByUserNameAndRoleNameSum(String userName) {
		TypedQuery<RolesUser> query = super.getEntityManager().createQuery(""
				+ "select o from RolesUser o , UserAccessWebService ua where o.user.userStatus=true and o.user.userName = :userName and (o.role.roleName='Biodiversidad-Servicios Web SIB') and o.rousStatus = true and o.user.userId = ua.userId and ua.uawsStatus=true and ua.uawsWsSummary=true and ua.uawsAccessStatus='APROBADO' ",
				RolesUser.class);
		query.setParameter("userName", userName);

		List<RolesUser> result = (List<RolesUser>) query.getResultList();
		if (result.size() > 0)
			return result;
		return null;
	}

	public List<RolesUser> findByUserNameAndRoleNameCites(String userName) {
		TypedQuery<RolesUser> query = super.getEntityManager().createQuery(""
						+ "select o from RolesUser o , UserAccessWebService ua where o.user.userStatus=true and o.user.userName = :userName and (o.role.roleName='Biodiversidad-Servicios Web SIB') and o.rousStatus = true and o.user.userId = ua.userId and ua.uawsStatus=true and ua.uawsWsCites=true and ua.uawsAccessStatus='APROBADO' ",
				RolesUser.class);
		query.setParameter("userName", userName);

		List<RolesUser> result = (List<RolesUser>) query.getResultList();
		if (result.size() > 0)
			return result;
		return null;
	}
}
