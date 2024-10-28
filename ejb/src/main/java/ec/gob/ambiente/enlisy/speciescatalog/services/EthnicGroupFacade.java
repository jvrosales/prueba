package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.speciescatalog.model.EthnicGroup;

/**
 * Servicios para la administracion de nombres comunes
 * @author EXCO
 *
 */
@Stateless
public class EthnicGroupFacade extends AbstractFacade<EthnicGroup, Integer>{

	public EthnicGroupFacade() {
		super(EthnicGroup.class, Integer.class);		
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<EthnicGroup> getEtnias() {
		
		List<EthnicGroup> result=new ArrayList<EthnicGroup>();
		try
		{
			Query query = super.getEntityManager().createQuery("select o from EthnicGroup o where o.etgrStatus = true order by o.etgrName");
			result=(List<EthnicGroup>)query.getResultList();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			
		}
		return result;
		}
	
	/**
	 * Obtener registros de nombres comunes segun etnia
	 * @param idEthnic
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVernacularNameByEthnic(Integer idEthnic) {
		List<Object[]> result = new ArrayList<Object[]>();
		try {
			Query q;
			q = getEntityManager().createNativeQuery(
					"select vena_id, etgr_id from biodiversity.vernacular_names where etgr_id = ?1 and vena_status = true");
			q.setParameter(1, idEthnic);
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
	 * Guardar un registro de etnia
	 * @param ethnicGroup
	 * @param user
	 * @return
	 */
	public EthnicGroup save(EthnicGroup ethnicGroup, User user) {
		EthnicGroup ethnic = null;
		try {
			if (ethnicGroup.getEtgrId() == null) {

				ethnicGroup.setEtgrStatus(true);
				ethnicGroup.setEtgrDateCreate(new Date());
				ethnicGroup.setEtgrUserCreate(user.getUserName());
				ethnic = create(ethnicGroup);

			} else {
				ethnicGroup.setEtgrDateUpdate(new Date());
				ethnicGroup.setEtgrUserUpdate(user.getUserName());
				ethnic = edit(ethnicGroup);
			}

			return ethnic;
		} catch (Exception ex) {
			return ethnic;
		}
	}
	
	/**
	 * Buscar un EthnicGroup por el nombre
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<EthnicGroup> findEthnicGroupByName(String name) {
		try {
			Query query = super.getEntityManager()
					.createQuery("SELECT o FROM EthnicGroup o where o.etgrStatus = true and UPPER(o.etgrName) = :name");
			query.setParameter("name", name.toUpperCase());
			List<EthnicGroup> result = (List<EthnicGroup>) query.getResultList();
			if (!result.isEmpty()) {
				
				return result;
			}

		} catch (NoResultException e) {
			return new ArrayList<EthnicGroup>();
		}
		return new ArrayList<EthnicGroup>();
	}
	
	/**
	 * Buscar un EthnicGroup por codigo
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<EthnicGroup> findEthnicGroupByCode(String code) {
		try {
			Query query = super.getEntityManager()
					.createQuery("SELECT o FROM EthnicGroup o where o.etgrStatus = true and UPPER(o.etgrAcronym) = :name");
			query.setParameter("name", code.toUpperCase());
			List<EthnicGroup> result = (List<EthnicGroup>) query.getResultList();
			if (!result.isEmpty()) {
				
				return result;
			}

		} catch (NoResultException e) {
			return new ArrayList<EthnicGroup>();
		}
		return new ArrayList<EthnicGroup>();
	}
	
	
}

