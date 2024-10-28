package ec.gob.ambiente.enlisy.speciescatalog.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.speciescatalog.model.SpecieList;


/**
 * Servicios para la administracion de convenios
 * @author EXCO
 *
 */
@Stateless
public class SpecieListFacade extends AbstractFacade<SpecieList, Integer>{

	public SpecieListFacade() {
		super(SpecieList.class, Integer.class);		
	}
	
		
	/**
	 * Obtener lista de convenios activos
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SpecieList> findSpeciesLists()
	{
		
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM SpecieList o where o.splsStatus = true order by o.splsOrder");
			List<SpecieList> result=(List<SpecieList>)query.getResultList();
			if(!result.isEmpty())
			{
				
				return result;
			}
			
		}catch(NoResultException e)
		{
			return new ArrayList<SpecieList>();
		}
		return new ArrayList<SpecieList>();
	}
	
	
	
	/**
	 * Guardar un convenio
	 * @param artificialGroup
	 * @param user
	 * @return
	 */
	public SpecieList save(SpecieList specieList, User user) {
		SpecieList agre = null;
		try {
			if (specieList.getSplsId() == null) {

				specieList.setSplsStatus(true);
				specieList.setSplsDateCreate(new Date());
				specieList.setSplsUserCreate(user.getUserName());
				agre = create(specieList);

			} else {
				specieList.setSplsDateUpdate(new Date());
				specieList.setSplsUserUpdate(user.getUserName());
				agre = edit(specieList);
			}

			return agre;
		} catch (Exception ex) {
			return agre;
		}
	}
	
	/**
	 * Buscar un lista por orden
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SpecieList> findByOrder(String order) {
		try {
			Query query = super.getEntityManager()
					.createQuery("SELECT o FROM SpecieList o where o.splsStatus = true and UPPER(o.splsOrder) = :order");
			query.setParameter("order", order.toUpperCase());
			List<SpecieList> result = (List<SpecieList>) query.getResultList();
			if (!result.isEmpty()) {
				
				return result;
			}

		} catch (NoResultException e) {
			return new ArrayList<SpecieList>();
		}
		return new ArrayList<SpecieList>();
	}
	
	/**
	 * Buscar un lista por codigo
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SpecieList> findByCode(String code) {
		try {
			Query query = super.getEntityManager()
					.createQuery("SELECT o FROM SpecieList o where o.splsStatus = true and UPPER(o.splsCode) = :code");
			query.setParameter("code", code.toUpperCase());
			List<SpecieList> result = (List<SpecieList>) query.getResultList();
			if (!result.isEmpty()) {
				
				return result;
			}

		} catch (NoResultException e) {
			return new ArrayList<SpecieList>();
		}
		return new ArrayList<SpecieList>();
	}
}
