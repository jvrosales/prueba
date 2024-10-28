package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.speciescatalog.model.VistaEspecies;

@Stateless
public class VistaEspeciesFacade extends AbstractFacade<VistaEspecies, Integer> implements Serializable{

	private static final long serialVersionUID = 8852726480903658111L;

	public VistaEspeciesFacade() {
		super(VistaEspecies.class, Integer.class);
	}

	public List<String> listDistinctColumn2ByColumn1(String column1, String column2, String valueColumn1, String view) {
		try {
			List<String> list = new ArrayList<String>();
			for (Object object : getEntityManager().createNativeQuery("SELECT distinct(o." + column2 + ") FROM " + view
					+ " o where upper(o." + column1 + ") like upper('" + valueColumn1 + "')").getResultList()) {
				list.add(object.toString());
			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<String> listarPorColumnaYGruposArtificiales(String columnaParametro, String columnaRetorno, List<String> valoresParametro, List<String> gruposArtificiales) {
		try {
			String valoresIn = "";
			for (String valor : valoresParametro) {
				if(!valoresIn.isEmpty()) {
					valoresIn += ",";
				}
				valoresIn += "'" + valor.toUpperCase() + "'";
			}

			String gruposLike = "";
			for (String grupo : gruposArtificiales) {
				// gruposLike+="or upper(grupoartificial) like '%"+grupo.toUpperCase()+"%'";
				gruposLike += "or upper(clase) like '%" + grupo.toUpperCase() + "%'";
			}

			if(valoresIn.isEmpty() || gruposLike.isEmpty() || columnaRetorno == null || columnaRetorno.isEmpty()) {
				return null;
			}

			String nombreVista ="vw_lstespecies";
			switch (columnaRetorno) {
			case "reino":
			case "clase":
			case "orden":
			case "phylum":
				nombreVista ="vw_lstespecies_orden";
				break;
			}
			String querySql = "select distinct " + columnaRetorno + " from biodiversity."+nombreVista+" where upper("
					+ columnaParametro + ") in(" + valoresIn + ") and (clase is null " + gruposLike + ") and "
					+ columnaRetorno + " is not null order by 1";
			// System.out.println("Consulta SQL A:"+querySql);

			List<String> list = new ArrayList<String>();
			for (Object object : getEntityManager().createNativeQuery(querySql).getResultList()) {
				if(object != null) // validacion para que no tome los valores nulos
					list.add(object.toString());
			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<String> listarPorColumnaYReinos(String columnaParametro, String columnaRetorno,
			List<String> valoresParametro) {
		try {
			String valoresIn = "";
			for (String valor : valoresParametro) {
				if(!valoresIn.isEmpty()) {
					valoresIn += ",";
				}
				valoresIn += "'" + valor.toUpperCase() + "'";
			}
			if(valoresIn.isEmpty() || columnaRetorno == null || columnaRetorno.isEmpty()) {
				return null;
			}
			String querySql = "select distinct " + columnaRetorno + " from biodiversity.vw_lstespecies where upper("
					+ columnaParametro + ") in(" + valoresIn + ") and " + columnaRetorno + " is not null order by 1";

			List<String> list = new ArrayList<String>();
			for (Object object : getEntityManager().createNativeQuery(querySql).getResultList()) {
				if(object != null)
					list.add(object.toString());
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<String> listarPorColumnaPorReinos(String columnaReino, String columnaParametro, String columnaRetorno, List<String> valoresParametro) {
		try {
			String valoresIn = "";
			for (String valor : valoresParametro) {
				if(!valoresIn.isEmpty()) {
					valoresIn += ",";
				}
				valoresIn += "'" + valor.toUpperCase() + "'";
			}
			if(valoresIn.isEmpty() || columnaRetorno == null || columnaRetorno.isEmpty()) {
				return null;
			}
			String nombreVista ="vw_lstespecies";
			switch (columnaRetorno.toLowerCase()) {
			case "reino":
			case "clase":
			case "orden":
				nombreVista ="vw_lstespecies_orden";
				break;
			case "género":
				columnaRetorno ="genero";
				break;
			case "especie":
				columnaRetorno="nombreCientifico";
				break;
			}
			String querySql = "select distinct " + columnaRetorno + " from biodiversity."+nombreVista+" where  upper(reino) = '"+ columnaReino.toUpperCase()+"'"
					+ " and upper(" + columnaParametro + ") in(" + valoresIn + ") and " + columnaRetorno + " is not null order by 1";
			List<String> list = new ArrayList<String>();
			for (Object object :getEntityManager().createNativeQuery(querySql).getResultList()) {
				if(object != null)
					list.add(object.toString());
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<String> listarPorColumnaYGruposArtificialesNombreCientifico(String columnaParametro,String columnaRetorno,List<String> valoresParametro,List<String> gruposArtificiales, String nombreCientifico, boolean buscarIgual) {
		try {
			String valoresIn = "";
			for (String valor : valoresParametro) {
				if(!valoresIn.isEmpty()) {
					valoresIn += ",";
				}
				valoresIn += "'" + valor.toUpperCase() + "'";
			}

			String gruposLike="";
			for (String grupo : gruposArtificiales) {
				//gruposLike+="or upper(grupoartificial) like '%"+grupo.toUpperCase()+"%'";
				gruposLike+="or upper(clase) like '%"+grupo.toUpperCase()+"%'";
			}

			if(valoresIn.isEmpty() || gruposLike.isEmpty()||columnaRetorno==null||columnaRetorno.isEmpty()) {
				return null;
			}

			String nombreVista ="vw_lstespecies";
			switch (columnaRetorno.toLowerCase()) {
			case "reino":
			case "clase":
			case "orden":
				nombreVista ="vw_lstespecies_orden";
				break;
			case "género":
				columnaRetorno ="genero";
				break;
			case "especie":
				columnaRetorno="nombreCientifico";
				break;
			}
			String querySql="select distinct "+columnaRetorno+" from biodiversity."+nombreVista+" where upper("+columnaParametro+") in("+valoresIn+") and (clase is null "+gruposLike+") ";
			if(buscarIgual)
				querySql +=" and upper("+columnaRetorno+") = upper('"+nombreCientifico+"') ";
			else
				querySql +=" and upper("+columnaRetorno+") like upper('%"+nombreCientifico+"%') ";
			querySql +=" order by 1";
			
			List<String> list= new ArrayList<String>();
			for (Object object :getEntityManager().createNativeQuery(querySql).getResultList()) {
				if(object != null)  // validacion para que no tome los valores nulos
					list.add(object.toString());
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<String> listarGrupoTaxonomicoPorNombreCientifico(String columnaParametro, String nombreCientifico) {
		try {
			if(columnaParametro.equalsIgnoreCase("Género"))
				columnaParametro = "genero";
			String nombreVista ="vw_lstespecies";
			String querySql="select distinct coalesce(reino, '') || ';'|| coalesce(phylum, '')  || ';' ||  coalesce(clase, '')  || ';' || coalesce(orden, '')  || ';' || coalesce(familia, '') || ';' || coalesce(genero, '')  || ';' || coalesce(especie, '')  "
					+ " from biodiversity."+nombreVista+" where upper("+columnaParametro+") = upper('"+nombreCientifico+"') ";
			querySql +=" order by 1";
			List<String> list= new ArrayList<String>();
			for (Object object :getEntityManager().createNativeQuery(querySql).getResultList()) {
				if(object != null)  // validacion para que no tome los valores nulos
					list.add(object.toString());
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unused")
	public VistaEspecies findByEspecie(String especie) {
		try {
			Query query = super.getEntityManager().createQuery("select o from VistaEspecies o where o.nombrecientifico = :especie");
			String ESP = especie.toUpperCase();
			query.setParameter("especie", especie);
			System.out.println(query.getFirstResult());
			
			return (VistaEspecies) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}

	public VistaEspecies findByOrden(String orden) {
		try {
			Query query = super.getEntityManager().createQuery("select o from VistaEspecies o where lower(o.orden) = :orden");
			query.setParameter("orden", orden.toLowerCase());

			return (VistaEspecies) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}

	public VistaEspecies findByFamilia(String familia) {
		try {
			Query query = super.getEntityManager()
					.createQuery("select o from VistaEspecies o where o.familia = :familia");
			query.setParameter("familia", familia);

			return (VistaEspecies) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}

	public VistaEspecies findByGenero(String genero) {
		try {
			Query query = super.getEntityManager()
					.createQuery("select o from VistaEspecies o where o.genero = :genero");
			query.setParameter("genero", genero);

			return (VistaEspecies) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}

	public VistaEspecies findByClase(String clase) {
		try {
			Query query = super.getEntityManager().createQuery("select o from VistaEspecies o where o.clase = :clase");
			query.setParameter("clase", clase);

			return (VistaEspecies) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public VistaEspecies findByTypeTaxonomia(String valorReino, String taxonomia, String valorTaxo) {
		try {
			Query query = super.getEntityManager().createQuery("select o from VistaEspecies o where upper(o.reino) = '"+ valorReino.toUpperCase() +"' and o."+taxonomia+" = :valorTaxo ", VistaEspecies.class);
			query.setParameter("valorTaxo", valorTaxo).setMaxResults(1);
			List<VistaEspecies> resultado = query.getResultList(); 
			if(resultado != null && resultado.size() > 0)
				return resultado.get(0); 
			return null;
		} catch (NoResultException e) {
			return null;
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<VistaEspecies> findBySpeceAutocomplete(String especie, String reino) {
		try {
			Query query = super.getEntityManager().createQuery("select o from VistaEspecies o where o.especie like :especie and o.reino = :reino");
//			Query query = super.getEntityManager().createQuery("select o.nombrecientifico, o.gui_especie from VistaEspecies o where o.especie like :especie and o.reino = :reino");
			query.setParameter("especie", especie);
			query.setParameter("reino", reino);
			List<VistaEspecies> vistaEspe = (List<VistaEspecies>) query.getResultList();
			if (vistaEspe.size() > 0) {
				return vistaEspe;
			}
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> findBySpeceAutocompleteString(String nombreCientifico, String reino) {
		try {
			Query query = super.getEntityManager().createQuery("select o.nombrecientifico from VistaEspecies o where lower(o.nombrecientifico) like :nombreCientifico and o.reino = :reino");
			query.setParameter("nombreCientifico", nombreCientifico);
			query.setParameter("reino", reino);
			List<String> vistaEspe = (List<String>) query.getResultList();
			if (vistaEspe.size() > 0) {
				return vistaEspe;
			}
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String findByNombreCientifico(String nombreCientifico) {
		try {
			Query query = super.getEntityManager().createQuery("select o.gui_especie from VistaEspecies o where o.nombrecientifico = :nombreCientifico");
			query.setParameter("nombreCientifico", nombreCientifico);
			String vistaEspe = (String) query.getSingleResult();
				return vistaEspe;
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return null;

	}

	public List<String> listReinos() {
		try {
			List<String> list = new ArrayList<String>();
			for (Object object : getEntityManager()
					.createNativeQuery("select distinct(reino) from biodiversity.vw_lstespecies_orden order by reino ").getResultList()) {
				list.add(object.toString());
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * public List<String> listGruposBiologicos(List<String> reinos) { if (reinos !=
	 * null && !reinos.isEmpty()) try { String where = ""; for (String reino :
	 * reinos) { if (!where.isEmpty()) where += ",";
	 * 
	 * where += "'" + reino.toUpperCase() + "'"; }
	 * 
	 * List<String> list = new ArrayList<String>(); for (Object object :
	 * getEntityManager().createNativeQuery(
	 * "SELECT distinct(nombre) FROM catalogo.vw_grupoartificialpadre where biodiversidad=true and upper(reino) in("
	 * + where+") order by 1").getResultList()) { list.add(object.toString()); }
	 * return list;
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } return null; }
	 */

	public List<String> listGruposBiologicos(List<String> reinos) {
		if (reinos != null && !reinos.isEmpty())
			try {
				String where = "";
				for (String reino : reinos) {
					if (!where.isEmpty())
						where += ",";

					where += "'" + reino.toUpperCase() + "'";
				}

				List<String> list = new ArrayList<String>();
				for (Object object : getEntityManager().createNativeQuery(
						"SELECT distinct(clase) FROM biodiversity.vw_lstespecies_orden where upper(reino) in(" + where
								+ ") and clase is not null order by 1").getResultList()) {
					list.add(object.toString());
				}
				return list;

			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}
}