package ec.gob.ambiente.enlisy.services;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.Nationality;
import ec.gob.ambiente.enlisy.model.People;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class PeopleFacade extends AbstractFacade<People, Integer> implements Serializable {

	private static final long serialVersionUID = 1164242243166182670L;

	public PeopleFacade() {
		super(People.class, Integer.class);
	}

	@SuppressWarnings("unchecked")
	public People findByPin(String peopPin) {

		try {
			Query query = getEntityManager()
					.createQuery("select o from People o where o.peopPin = :peopPin order by o.peopId desc")
					.setParameter("peopPin", peopPin);
			List<People> peopleList = (List<People>) query.getResultList();
			if (!peopleList.isEmpty())
				return peopleList.get(0);
			else
				return null;
		} catch (NoResultException e) {
			People people = findByUserName(peopPin);
			if (people != null)
				people.setPeopPin(peopPin);
			return people;
		}

	}

	@SuppressWarnings("unchecked")
	public People findById(String peopleId) {

		try {
			Query query = getEntityManager().createQuery("select o from People o where o.peopId = :peopleId")
					.setParameter("peopleId", peopleId);
			List<People> peopleList = (List<People>) query.getResultList();
			if (!peopleList.isEmpty())
				return peopleList.get(0);
			else
				return null;
		} catch (NoResultException e) {
			People people = findByUserName(peopleId);
			if (people != null)
				people.setPeopPin(peopleId);
			return people;
		}

	}

	@SuppressWarnings("unchecked")
	public People findByIdInt(Integer peopleId) {

		Query query = getEntityManager().createQuery("select o from People o where o.peopId = :peopleId")
				.setParameter("peopleId", peopleId);
		List<People> peopleList = (List<People>) query.getResultList();
		if (!peopleList.isEmpty())
			return peopleList.get(0);
		else
			return null;

	}

	@SuppressWarnings("unchecked")
	public People findByPinRuc(String peopPin) {

		try {
			Query query = getEntityManager().createQuery(
					"select o from People o where o.peopId = (select (u.people.peopId) from User u where u.userName  = (:peopPin)) order by o.peopId desc")

					.setParameter("peopPin", peopPin);

			List<People> peopleList = (List<People>) query.getResultList();
			if (!peopleList.isEmpty())
				return peopleList.get(0);
			else
				return null;
		} catch (NoResultException e) {
			People people = findByUserName(peopPin);
			if (people != null)
				people.setPeopPin(peopPin);
			return people;
		}

	}

	public People findByUserName(String userName) {

		try {
			Query query = getEntityManager()
					.createQuery(
							"select o from People o,User u where o.peopId=u.people.peopId and u.userName = :userName")
					.setParameter("userName", userName);
			return (People) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}

	@Deprecated
	public boolean save(People people) {
		boolean result = false;
		try {
			if (people.getPeopId() == null) {
				if (people.getNationality() == null)
					people.setNationality(new Nationality(18));
				create(people);
			} else {
				edit(people);
			}

			result = true;
		} catch (Exception ex) {
			result = false;
		}
		return result;
	}

	public boolean save(People people, User user) {
		boolean result = false;
		try {
			if (people.getPeopId() == null) {
				if (people.getNationality() == null)
					people.setNationality(new Nationality(18));

				people.setPeopUserCreate(user.getUserName());
				people.setPeopDateCreate(new Date());
				create(people);
			} else {
				people.setPeopUserUpdate(user.getUserName());
				people.setPeopDateUpdate(new Date());
				edit(people);
			}

			result = true;
		} catch (Exception ex) {
			result = false;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<People> findByRol(Integer rol) {
		try {
			Query query = getEntityManager().createQuery(
					"select p from People p where p.peopId in (select (u.people.peopId) from Role r, RolesUser ru, User u where r.roleId in (:roles) and r.roleId = ru.role and ru.user = u.userId)")
					.setParameter("roles", rol);
			List<People> peopleList = (List<People>) query.getResultList();
			if (!peopleList.isEmpty()) {
				return peopleList;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}