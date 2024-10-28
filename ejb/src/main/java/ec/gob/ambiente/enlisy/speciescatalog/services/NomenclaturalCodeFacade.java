package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.speciescatalog.model.NomenclaturalCode;

/**
 * Servicios para la administracion de las publicaciones del especialista
 * @author EXCO
 *
 */
@Stateless
public class NomenclaturalCodeFacade extends AbstractFacade<NomenclaturalCode, Integer>{

	public NomenclaturalCodeFacade() {
		super(NomenclaturalCode.class, Integer.class);		
	}
	
	/**
	 * Consultar y llenar el mapa de los codigos nomenclaturales
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Integer> getCodigosNomenclaturales() {
		Map<String, Integer> mapResult = new LinkedHashMap<>();
		List<Object[]> result = new ArrayList<>();
		Query q;
		EntityManager en = super.getEntityManager();
		q = en.createNativeQuery("SELECT noco_id, noco_code  FROM biodiversity.nomenclatural_codes where noco_status=true");
		result = q.getResultList();
		if (result != null && !result.isEmpty()) {
			for (Object[] row : result) {
				if (row != null) {

					mapResult.put(row[1].toString(), Integer.parseInt(row[0].toString()));

				}
			}
		}
		return mapResult;
	}
	
	/**
	 * Buscar codigos activos
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<NomenclaturalCode> findCodes()
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM NomenclaturalCode o where o.nocoStatus = true order by o.nocoCode");
			List<NomenclaturalCode> result=(List<NomenclaturalCode>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<NomenclaturalCode>();
		}
		return new ArrayList<NomenclaturalCode>();
	}
	
	/**
	 * Buscar un codigo Nomenclatural por Nombre
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<NomenclaturalCode> findNomenclaturalCodeByName(String name) {
		try {
			Query query = super.getEntityManager()
					.createQuery("SELECT o FROM NomenclaturalCode o where o.nocoStatus = true and UPPER(o.nocoName) = :name");
			query.setParameter("name", name.toUpperCase());
			List<NomenclaturalCode> result = (List<NomenclaturalCode>) query.getResultList();
			if (!result.isEmpty()) {
				
				return result;
			}

		} catch (NoResultException e) {
			return new ArrayList<NomenclaturalCode>();
		}
		return new ArrayList<NomenclaturalCode>();
	}
	
	
	/**
	 * Buscar un codigo nomenclatural por codigo
	 * @param code
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<NomenclaturalCode> findNomenclaturalCodeByCode(String code) {
		try {
			Query query = super.getEntityManager()
					.createQuery("SELECT o FROM NomenclaturalCode o where o.nocoStatus = true and UPPER(o.nocoCode) = :code");
			query.setParameter("code", code.toUpperCase());
			List<NomenclaturalCode> result = (List<NomenclaturalCode>) query.getResultList();
			if (!result.isEmpty()) {
				
				return result;
			}

		} catch (NoResultException e) {
			return new ArrayList<NomenclaturalCode>();
		}
		return new ArrayList<NomenclaturalCode>();
	}
	
	/**
	 * Metodo para buscar 
	 * @param idNomenclaturalCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getSpeciesTaxaByNomenclaturalCode(Integer idNomenclaturalCode) {
		List<Object[]> result = new ArrayList<>();
		try {
			Query q;
			q = getEntityManager().createNativeQuery(
					"select spta_id, noco_id from biodiversity.species_taxa where noco_id = ?1 and spta_status = true");
			q.setParameter(1, idNomenclaturalCode);
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
	 * Metodo para buscar registros de higher segun codigo nomenclatura
	 * @param idNomenclaturalCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getHigherClassificationsByNomenclaturalCode(Integer idNomenclaturalCode) {
		List<Object[]> result = new ArrayList<>();
		try {
			Query q;
			q = getEntityManager().createNativeQuery(
					"select hicl_id, noco_id from biodiversity.higher_classifications where noco_id = ?1 and hicl_status = true");
			q.setParameter(1, idNomenclaturalCode);
			result = (List<Object[]>) q.getResultList();
			if (q.getResultList() == null) {
				result = new ArrayList<Object[]>();
			}
		} catch (Exception ex) {
			return new ArrayList<Object[]>();
		}
		return result;
	}
	
	public NomenclaturalCode save(NomenclaturalCode nomenclaturalCode, User user) {
		NomenclaturalCode nomenclaturalReturn = null;
		try {
			if (nomenclaturalCode.getNocoId() == null) {

				nomenclaturalCode.setNocoStatus(true);
				nomenclaturalCode.setNocoDateCreate(new Date());
				nomenclaturalCode.setNocoUserCreate(user.getUserName());
				nomenclaturalReturn = create(nomenclaturalCode);

			} else {
				nomenclaturalCode.setNocoDateUpdate(new Date());
				nomenclaturalCode.setNocoUserUpdate(user.getUserName());
				nomenclaturalReturn = edit(nomenclaturalCode);
			}

			return nomenclaturalReturn;
		} catch (Exception ex) {
			return nomenclaturalReturn;
		}
	}
	
	
	/**
	 * Buscar codigos activos
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<NomenclaturalCode> findCodesOrderByName()
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM NomenclaturalCode o where o.nocoStatus = true order by o.nocoName");
			List<NomenclaturalCode> result=(List<NomenclaturalCode>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<NomenclaturalCode>();
		}
		return new ArrayList<NomenclaturalCode>();
	}
}
