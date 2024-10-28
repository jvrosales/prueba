package ec.gob.ambiente.enlisy.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.Organization;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.model.UsuarioAsociado;

@Stateless
public class UserAsociadoModuloFacade extends AbstractFacade<UsuarioAsociado, Integer> implements Serializable {

	private static final long serialVersionUID = -4594424897085245484L;

	public UserAsociadoModuloFacade() {
		super(UsuarioAsociado.class, Integer.class);
	}

	/**
	 * Actualizar datos de perfil de usuario
	 * 
	 * @param user
	 */

	public boolean updateUserData(UsuarioAsociado usuarioAsociado, User user) {
		try {
			if (usuarioAsociado.getLubiId() == null) {
				usuarioAsociado.setLubiCreationDate(new Date());
				usuarioAsociado.setLubiCreatorUser(user.getUserName());
				create(usuarioAsociado);
			} else {
				usuarioAsociado.setLubiDateUpdate(new Date());
				usuarioAsociado.setLubiUseUpdate(user.getUserName());
				edit(usuarioAsociado);
			}
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public List<UsuarioAsociado> findUserSociadoByUserOrga(Organization organizacion) {
		TypedQuery<UsuarioAsociado> query = super.getEntityManager().createQuery(
				"select u from UsuarioAsociado u "
						+ "where u.organizacion.orgaId = :organizacionId and u.lubiStatus = true and u.lubiPatents = true",
				UsuarioAsociado.class);
		query.setParameter("organizacionId", organizacion.getOrgaId());
		
		List<UsuarioAsociado> listaUsuarioModulo = new ArrayList<UsuarioAsociado>();
		if (query.getResultList().size() > 0) {
			listaUsuarioModulo = query.getResultList();
		}
		return listaUsuarioModulo;
	}
	
	
	public List<UsuarioAsociado> findUserSociadoByUser(Organization organizacion) {
		TypedQuery<UsuarioAsociado> query = super.getEntityManager().createQuery(
				"select u from UsuarioAsociado u "
						+ "where u.organizacion.orgaId = :organizacionId and u.lubiStatus = true",
				UsuarioAsociado.class);
		query.setParameter("organizacionId", organizacion.getOrgaId());

		List<UsuarioAsociado> listaUsuarioModulo = new ArrayList<UsuarioAsociado>();
		if (query.getResultList().size() > 0) {
			listaUsuarioModulo = query.getResultList();
		}
		return listaUsuarioModulo;
	}
	
	public List<UsuarioAsociado> findUserSociadoByUserPatent(Organization organizacion) {
		TypedQuery<UsuarioAsociado> query = super.getEntityManager().createQuery(
				"select u from UsuarioAsociado u "
						+ "where u.organizacion.orgaId = :organizacionId and u.lubiStatus = true and u.lubiPatents = true",
				UsuarioAsociado.class);
		query.setParameter("organizacionId", organizacion.getOrgaId());

		List<UsuarioAsociado> listaUsuarioModulo = new ArrayList<UsuarioAsociado>();
		if (query.getResultList().size() > 0) {
			listaUsuarioModulo = query.getResultList();
		}
		return listaUsuarioModulo;
	}
	
	public UsuarioAsociado findUserAsociadoByUser(User usuario) {
		TypedQuery<UsuarioAsociado> query = super.getEntityManager().createQuery(
				"select u from UsuarioAsociado u " + "where u.userAsociado.userId = :usuarioId and u.lubiStatus = true",
				UsuarioAsociado.class);
		query.setParameter("usuarioId", usuario.getUserId());

		UsuarioAsociado usuarioAsociado = new UsuarioAsociado();
		if (query.getResultList().size() > 0) {
			usuarioAsociado = query.getResultList().get(0);
		}
		return usuarioAsociado;
	}



	public UsuarioAsociado findUserSociadoByUserId(User usuario) {

		TypedQuery<UsuarioAsociado> query = super.getEntityManager().createQuery("select u from UsuarioAsociado u "
				+ "where u.userAsociado.userId = :userId and u.lubiStatus = true",
				UsuarioAsociado.class);
		query.setParameter("userId", usuario.getUserId());
		UsuarioAsociado usuarioAsociado = new UsuarioAsociado();
		if (query.getResultList().size() > 0) {
			usuarioAsociado = query.getResultList().get(0);
		}
		return usuarioAsociado;

	}

	public List<UsuarioAsociado> findUserSociadoByUserIds(User usuario) {
		TypedQuery<UsuarioAsociado> query = super.getEntityManager().createQuery("select u from UsuarioAsociado u "
				+ "where u.userAsociado.userId = :userId and u.lubiStatus = true and u.organizacion.orgaId<>null",
				UsuarioAsociado.class);
		query.setParameter("userId", usuario.getUserId());

		List<UsuarioAsociado> listaUsuarioModulo = new ArrayList<UsuarioAsociado>();
		if (query.getResultList().size() > 0) {
			listaUsuarioModulo = query.getResultList();
		}
		return listaUsuarioModulo;

	}

	public UsuarioAsociado findUserAsociadoById(Integer lubiId) {
		TypedQuery<UsuarioAsociado> query = super.getEntityManager().createQuery(
				"select u from UsuarioAsociado u " + "where u.lubiId = :lubiId and u.lubiStatus = true",
				UsuarioAsociado.class);
		query.setParameter("lubiId", lubiId);

		UsuarioAsociado usuarioAsociado = new UsuarioAsociado();
		if (query.getResultList().size() > 0) {
			usuarioAsociado = query.getResultList().get(0);
		}
		return usuarioAsociado;
	}

	public UsuarioAsociado findUserAsociadoByRuc(String orgaRuc) {
		try {
			TypedQuery<UsuarioAsociado> query = super.getEntityManager().createQuery(
					"select u from UsuarioAsociado u "
							+ "where u.organizacion.orgaRuc = :orgaRuc and u.lubiStatus = true ",
					UsuarioAsociado.class);
			query.setParameter("orgaRuc", orgaRuc);
			query.setMaxResults(1);
			return query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public UsuarioAsociado findUserAsociadoByRucPN(String orgaRuc) {
		try {
			TypedQuery<UsuarioAsociado> query = super.getEntityManager().createQuery(
					"select u from UsuarioAsociado u "
							+ "where u.organizacion.orgaRuc = :orgaRuc and u.lubiStatus = true ",
					UsuarioAsociado.class);
			query.setParameter("orgaRuc", orgaRuc);
			query.setMaxResults(1);
			return query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public UsuarioAsociado findOrganizacion(Integer orgaRuc) {
		TypedQuery<UsuarioAsociado> query = super.getEntityManager().createQuery(
				"select u from UsuarioAsociado u where u.organizacion.orgaRuc = :orgaRuc and u.lubiStatus = true",
				UsuarioAsociado.class);
		query.setParameter("orgaRuc", orgaRuc);

		UsuarioAsociado usuarioAsociado = new UsuarioAsociado();
		if (query.getResultList().size() > 0) {
			usuarioAsociado = query.getResultList().get(0);
		}
		return usuarioAsociado;

	}

	public UsuarioAsociado findUserAsociadoByPeople(Integer peopleId) {
		TypedQuery<UsuarioAsociado> query = super.getEntityManager().createQuery(
				"select u from UsuarioAsociado u "
						+ "where u.people.peopId = :peopleId and u.lubiStatus = true",
				UsuarioAsociado.class);
		query.setParameter("peopleId", peopleId);
		UsuarioAsociado usuarioAsociado = new UsuarioAsociado();
		if (query.getResultList().size() > 0) {
			usuarioAsociado = query.getResultList().get(0);
		}
		return usuarioAsociado;

	}
	
	public UsuarioAsociado findUserAsociadoByPeople(Integer peopleId, Integer orgaId) {
		TypedQuery<UsuarioAsociado> query = super.getEntityManager().createQuery(
				"select u from UsuarioAsociado u where u.people.peopId = :peopleId and u.organizacion.orgaId = :orgaId and u.lubiStatus = true", UsuarioAsociado.class);
		query.setParameter("peopleId", peopleId);
		query.setParameter("orgaId", orgaId);
		UsuarioAsociado usuarioAsociado = new UsuarioAsociado();
		if (query.getResultList().size() > 0) {
			usuarioAsociado = query.getResultList().get(0);
		}
		return usuarioAsociado;

	}
	
	public UsuarioAsociado findUserSociadoByUserIdOrg(User usuario, Organization organization) {
		TypedQuery<UsuarioAsociado> query = super.getEntityManager().createQuery("select u from UsuarioAsociado u "
				+ "where u.userAsociado.userId = :userId and u.lubiStatus = true and u.organizacion.orgaId = :orgaId",
				UsuarioAsociado.class);
		query.setParameter("userId", usuario.getUserId());
		query.setParameter("orgaId", organization.getOrgaId());

		UsuarioAsociado usuarioAsociado = new UsuarioAsociado();
		if (query.getResultList().size() > 0) {
			usuarioAsociado = query.getResultList().get(0);
		}
		return usuarioAsociado;
	}
	
}