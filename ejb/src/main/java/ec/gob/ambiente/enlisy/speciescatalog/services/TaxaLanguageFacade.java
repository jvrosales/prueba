package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.speciescatalog.model.TaxaLanguage;

/**
 * Servicios para la administracion de nombres comunes
 * @author EXCO
 *
 */
@Stateless
public class TaxaLanguageFacade extends AbstractFacade<TaxaLanguage, Integer>{

	public TaxaLanguageFacade() {
		super(TaxaLanguage.class, Integer.class);		
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<TaxaLanguage> getLanguages() {
		
		List<TaxaLanguage> result=new ArrayList<TaxaLanguage>();
		try
		{
			Query query = super.getEntityManager().createQuery("select o from TaxaLanguage o where o.talaStatus = true order by o.talaName");
			result=(List<TaxaLanguage>)query.getResultList();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			
		}
		return result;
		}
	
	
	/**
	 * Buscar TaxaRank asociados a una lenguaje
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> findRankByLanguage(Integer id) {
		List<Object[]> result = new ArrayList<Object[]>();
		try {
			Query q;
			q = getEntityManager().createNativeQuery(
					"select tara_id, tala_id from biodiversity.taxa_ranks where tala_id = ?1 and tara_status = true");
			q.setParameter(1, id);
			result = (List<Object[]>) q.getResultList();
			if (q.getResultList() == null) {
				result = new ArrayList<Object[]>();
			}
		} catch (Exception ex) {
			return new ArrayList<Object[]>();
		}
		return result;
	}
	
	/**
	 * Buscar VernacularName asociados a una lenguaje
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> findVernacularNamesByLanguage(Integer id) {
		List<Object[]> result = new ArrayList<Object[]>();
		try {
			Query q;
			q = getEntityManager().createNativeQuery(
					"select vena_id, tala_id from biodiversity.vernacular_names where tala_id = ?1 and vena_status = true");
			q.setParameter(1, id);
			result = (List<Object[]>) q.getResultList();
			if (q.getResultList() == null) {
				result = new ArrayList<Object[]>();
			}
		} catch (Exception ex) {
			return new ArrayList<Object[]>();
		}
		return result;
	}
	
	
	/**
	 * Guardar lenguaje
	 * 
	 * @param taxonomicSource
	 * @param usuario
	 * @return
	 */
	public TaxaLanguage save(TaxaLanguage language, User user) {
		TaxaLanguage lenguajeRetornar = null;
		try {
			if (language.getTalaId() == null) {

				language.setTalaStatus(true);
				language.setTalaDateCreate(new Date());
				language.setTalaUserCreate(user.getUserName());
				lenguajeRetornar = create(language);

			} else {
				language.setTalaDateUpdate(new Date());
				language.setTalaUserUpdate(user.getUserName());
				lenguajeRetornar = edit(language);
			}

			return lenguajeRetornar;
		} catch (Exception ex) {
			return lenguajeRetornar;
		}
	}
	
	/**
	 * Buscar un lenguaje por el nombre
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TaxaLanguage> findLanguageByName(String name) {
		try {
			Query query = super.getEntityManager()
					.createQuery("SELECT o FROM TaxaLanguage o where o.talaStatus = true and UPPER(o.talaName) = :name");
			query.setParameter("name", name.toUpperCase());
			List<TaxaLanguage> result = (List<TaxaLanguage>) query.getResultList();
			if (!result.isEmpty()) {
				
				return result;
			}

		} catch (NoResultException e) {
			return new ArrayList<TaxaLanguage>();
		}
		return new ArrayList<TaxaLanguage>();
	}
}

