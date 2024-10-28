package ec.gob.ambiente.enlisy.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.Acronym;
import ec.gob.ambiente.enlisy.model.CategoryPatent;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class AcronymFacade extends AbstractFacade<Acronym, Integer> implements Serializable {

	private static final long serialVersionUID = -4594424897085245484L;

	public AcronymFacade() {
		super(Acronym.class, Integer.class);
	}

	/**
	 * Actualizar datos de perfil de usuario cuando es usuario
	 * 
	 * @param user
	 */

	public List<Acronym> findByUserSelected(String user) {
		TypedQuery<Acronym> query = super.getEntityManager().createQuery(
				"select u from Acronym u "
						+ "where u.usuarioAsociado.userAsociado.userName = :userName and u.status= true order by 1 desc",
				Acronym.class);
		query.setParameter("userName", user);

		List<Acronym> listaAcronym = new ArrayList<Acronym>();
		if (query.getResultList().size() > 0) {
			listaAcronym = query.getResultList();
		}
		return listaAcronym;
	}

	/**
	 * Actualizar datos de perfil de usuario cuando es organizacion
	 * 
	 * @param user
	 */

	public List<Acronym> findByOrgaSelected(String user, CategoryPatent categoryPatent) {
		TypedQuery<Acronym> query = super.getEntityManager().createQuery("select u from Acronym u "
				+ "where u.usuarioAsociado.organizacion.orgaRuc = :userName and u.creation=2  and u.status= true and u.categoryPatent = :categoryPatent",
				Acronym.class);
		query.setParameter("userName", user);
		query.setParameter("categoryPatent", categoryPatent);

		List<Acronym> listaAcronym = new ArrayList<Acronym>();
		if (query.getResultList().size() > 0) {
			listaAcronym = query.getResultList();
		}
		return listaAcronym;
	}

	public List<Acronym> findByPeopleSelected(String user, CategoryPatent categoryPatent) {
		TypedQuery<Acronym> query = super.getEntityManager().createQuery(
				"select u from Acronym u "
						+ "where u.usuarioAsociado.people.peopPin = :userName and u.creation=2 and u.status= true and u.categoryPatent = :categoryPatent",
				Acronym.class);
		query.setParameter("userName", user);
		query.setParameter("categoryPatent", categoryPatent);

		List<Acronym> listaAcronym = new ArrayList<Acronym>();
		
		if (query.getResultList().size() > 0) {
			listaAcronym = query.getResultList();
		}else {
			TypedQuery<Acronym> query1 = super.getEntityManager().createQuery(
					"select u from Acronym u "
							+ "where u.usuarioAsociado.people.peopPin = :userName and u.creation=2 and u.status= true and u.categoryPatent = :categoryPatent",
					Acronym.class);
			String user1 = user.toString().substring(0,10);
			query1.setParameter("userName", user1);
			query1.setParameter("categoryPatent", categoryPatent);
			
			if (query1.getResultList().size() > 0) {
				listaAcronym = query1.getResultList();
			}
			
		}
		return listaAcronym;
	}

	public Acronym findByAcronymName(String name) {
		TypedQuery<Acronym> query = super.getEntityManager()
				.createQuery("select u from Acronym u " + "where u.name = :name and u.status= true", Acronym.class);
		query.setParameter("name", name);
		Acronym acronym = new Acronym();

		if (query.getResultList().size() > 0) {
			acronym = query.getResultList().get(0);
		}
		return acronym;
	}

	/**
	 * Busqueda de acronimo por el estado de la solicitud
	 * 
	 * @param name
	 * @return
	 */
	public List<Acronym> findByUserCreation(Integer creation)
	{
		TypedQuery<Acronym> query = super.getEntityManager().createQuery("select u from Acronym u "
				+ "where u.creation = :creation and u.status= true", Acronym.class);
		query.setParameter("creation", creation);
		
		List<Acronym> listaAcronym = new ArrayList<Acronym>();
		if(query.getResultList().size() > 0)
		{
			listaAcronym = query.getResultList();
		}
		return listaAcronym;
	}

	public List<Acronym> findByUser()
	{
		TypedQuery<Acronym> query = super.getEntityManager().createQuery("select u from Acronym u where u.status= true and acro_creation = 2  order by 1 desc", Acronym.class);
			
		List<Acronym> listaAcronym = new ArrayList<Acronym>();
		if(query.getResultList().size() > 0)
		{
			listaAcronym = query.getResultList();
		}
		return listaAcronym;
	}
	
	public List<Acronym> findByUserAproved()
	{
		TypedQuery<Acronym> query = super.getEntityManager().createQuery("select u from Acronym u where u.status= true and u.creation = 1 order by 1 desc", Acronym.class);
			
		List<Acronym> listaAcronym = new ArrayList<Acronym>();
		if(query.getResultList().size() > 0)
		{
			listaAcronym = query.getResultList();
		}
		return listaAcronym;
	}

	/**
	 * insertar y actualizar acronimo
	 * 
	 * @param user
	 */
	
	public boolean updateUserData(Acronym acronym, User user) {
		try {
			if (acronym.getId() == null) {
				acronym.setCreationDate(new Date());
				acronym.setCreationUser(user.getUserName());
				create(acronym);
			} else {
				acronym.setDateUpdate(new Date());
				acronym.setUserUpdate(user.getUserName());
				edit(acronym);
			}
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	
	public boolean deleteAcronym(Acronym acronym, User user) {
		try {
			acronym.setDateUpdate(new Date());
			acronym.setUserUpdate(user.getUserName());
			acronym.setStatus(false);
			edit(acronym);
		} catch (Exception ex) {
			return false;
		}
		return false;
	}

}