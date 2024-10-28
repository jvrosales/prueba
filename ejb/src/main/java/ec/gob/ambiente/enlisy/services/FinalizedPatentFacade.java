package ec.gob.ambiente.enlisy.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.FinalizedPatent;
import ec.gob.ambiente.enlisy.model.PatentInformation;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class FinalizedPatentFacade extends AbstractFacade<FinalizedPatent, Integer> implements Serializable {
	private static final long serialVersionUID = -4594424897085245484L;

	public FinalizedPatentFacade() {
		super(FinalizedPatent.class, Integer.class);
	}

	public FinalizedPatent findById(Integer id) {
		TypedQuery<FinalizedPatent> query = super.getEntityManager()
				.createQuery("select u from FinalizedPatent u where u.id = :id ", FinalizedPatent.class);
		query.setParameter("id", id);

		return query.getSingleResult();
	}

	public FinalizedPatent findByPatentInformation(PatentInformation patentInformation) {
		try {

			TypedQuery<FinalizedPatent> query = super.getEntityManager().createQuery(
					"select u from FinalizedPatent u where u.patentInformation.Id = :id and u.estadoEncuesta = true and u.documentoDescargado = true", FinalizedPatent.class);
			query.setParameter("id", patentInformation.getId());

			return query.getSingleResult();

		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}
	
	public FinalizedPatent findByPatentInformationSurvey(PatentInformation patentInformation) {
		try {

			TypedQuery<FinalizedPatent> query = super.getEntityManager().createQuery(
					"select u from FinalizedPatent u where u.patentInformation.Id = :id and u.finalizada = true and u.status = true and (u.documentoDescargado = false or u.documentoDescargado = NULL)", FinalizedPatent.class);
			query.setParameter("id", patentInformation.getId());

			return query.getSingleResult();

		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}

	public FinalizedPatent findByPatentInformationDownload(Boolean estadoPatente, PatentInformation patentInformation) {
		try {

			TypedQuery<FinalizedPatent> query = super.getEntityManager().createQuery(
					"select u from FinalizedPatent u where u.status = true and u.patentInformation.Id = :id and u.estadoPatente = :estadoPatente and u.estadoEncuesta = true", FinalizedPatent.class);
			query.setParameter("id", patentInformation.getId());
			query.setParameter("estadoPatente", estadoPatente);

			return query.getSingleResult();

		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}
	
	public List<FinalizedPatent> patentesFinalizadas(User userSolicitante, Boolean estadoPatente) {
		TypedQuery<FinalizedPatent> query = super.getEntityManager().createQuery(
				"select u from FinalizedPatent u where u.status = true and u.usuarioSolicitante = :solicitante  and u.estadoPatente = :estadoPatente",
				FinalizedPatent.class);
		query.setParameter("solicitante", userSolicitante.getUserName());
		query.setParameter("estadoPatente", estadoPatente);
		List<FinalizedPatent> listaPatentesFinalizadas = new ArrayList<FinalizedPatent>();
		if (query.getResultList().size() > 0) {
			listaPatentesFinalizadas = (List<FinalizedPatent>) query.getResultList();
		}
		return listaPatentesFinalizadas;

	}
	
	public List<FinalizedPatent> patentesFinalizadasAdmin(User userSolicitante, Boolean estadoPatente, Integer organizacion) {
		TypedQuery<FinalizedPatent> query = super.getEntityManager().createQuery(
				"select u from FinalizedPatent u JOIN u.patentInformation p where u.status = true  and u.estadoPatente = :estadoPatente and (u.usuarioSolicitante = :solicitante or u.patentInformation.usuarioAsociado in (select lub.id from UsuarioAsociado lub where lub.organizacion.id = :organizacion))",
				FinalizedPatent.class);
		query.setParameter("solicitante", userSolicitante.getUserName());
		query.setParameter("estadoPatente", estadoPatente);
		query.setParameter("organizacion", organizacion);
		List<FinalizedPatent> listaPatentesFinalizadas = new ArrayList<FinalizedPatent>();
		if (query.getResultList().size() > 0) {
			listaPatentesFinalizadas = (List<FinalizedPatent>) query.getResultList();
		}
		return listaPatentesFinalizadas;

	}
	
	
	public List<FinalizedPatent> patentesFinalizadasConEncuestas(User userSolicitante, Boolean estadoPatente) {
		TypedQuery<FinalizedPatent> query = super.getEntityManager().createQuery(
				"select u from FinalizedPatent u where u.status = true and u.usuarioSolicitante = :solicitante  and u.estadoPatente = :estadoPatente and u.estadoEncuesta = true and u.documentoDescargado = true",
				FinalizedPatent.class);
		query.setParameter("solicitante", userSolicitante.getUserName());
		query.setParameter("estadoPatente", estadoPatente);
		List<FinalizedPatent> listaPatentesFinalizadas = new ArrayList<FinalizedPatent>();
		if (query.getResultList().size() > 0) {
			listaPatentesFinalizadas = (List<FinalizedPatent>) query.getResultList();
		}
		return listaPatentesFinalizadas;

	}
	
	public boolean updateUserData(FinalizedPatent patenteFinalizada, User user) {

		try {
			if (patenteFinalizada.getId() == null) {
				patenteFinalizada.setStatus(true);
				patenteFinalizada.setFipaCreationDate((new Date()));
				patenteFinalizada.setFipaDateUpdate((new Date()));
				patenteFinalizada.setFipaCreatorUser(user.getUserName());

				create(patenteFinalizada);
			} else {
				patenteFinalizada.setFipaDateUpdate(new Date());
				patenteFinalizada.setFipaUserUpdate(user.getUserName());
				edit(patenteFinalizada);
			}
			return true;
		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
	}

	public void delete(FinalizedPatent patenteFinalizada, User user) {
		patenteFinalizada.setStatus(false);
		patenteFinalizada.setFipaDateUpdate(new Date());
		patenteFinalizada.setFipaUserUpdate(user.getUserName());
		edit(patenteFinalizada);
	}

}
