package ec.gob.ambiente.enlisy.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.DoiTable;
import ec.gob.ambiente.enlisy.model.PatentInformation;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class DoiTableFacade extends AbstractFacade<DoiTable, Integer> implements Serializable {
	private static final long serialVersionUID = -4594424897085245484L;

	public DoiTableFacade() {
		super(DoiTable.class, Integer.class);
	}

//	public FinalizedPatent findById(Integer id) {
//		TypedQuery<FinalizedPatent> query = super.getEntityManager()
//				.createQuery("select u from DescargaPatenteFinalizadas u " + "where u.id = :id ", FinalizedPatent.class);
//		query.setParameter("id", id);
//
//		return query.getSingleResult();
//	}

	public List<DoiTable> findDoi(PatentInformation patentInformation) {
		TypedQuery<DoiTable> query = super.getEntityManager().createQuery(
				"select o from DoiTable o where o.patentInformation.Id = :patentInformation and o.status = true",
				DoiTable.class);
		query.setParameter("patentInformation", patentInformation.getId());
		List<DoiTable> listaDOI = new ArrayList<DoiTable>();
		if (query.getResultList().size() > 0) {
			listaDOI = (List<DoiTable>) query.getResultList();
		}
		return listaDOI;

	}

	public List<DoiTable> findDoiRenova(PatentInformation patentInformation, PatentInformation patenteAnterior) {
		TypedQuery<DoiTable> query = super.getEntityManager().createQuery(
				"select o from DoiTable o where o.patentInformation.Id in (:patentInformation, :patenteAnterior) and o.status = true",
				DoiTable.class);
		query.setParameter("patentInformation", patentInformation.getId());
		query.setParameter("patenteAnterior", patenteAnterior.getId());
		List<DoiTable> listaDOI = new ArrayList<DoiTable>();
		if (query.getResultList().size() > 0) {
			listaDOI = (List<DoiTable>) query.getResultList();
		}
		return listaDOI;

	}

	public boolean updateUserData(DoiTable doi, User user) {

		try {
			if (doi.getId() == null) {
				doi.setStatus(true);
				;
				doi.setDotaCreationDate((new Date()));
				doi.setDotaDateUpdate((new Date()));
				doi.setDotaCreatorUser(user.getUserName());

				create(doi);
			} else {
				doi.setDotaDateUpdate(new Date());
				doi.setDotaUserUpdate(user.getUserName());
				edit(doi);
			}
			return true;
		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
	}

	public void delete(DoiTable doi, User user) {
		doi.setStatus(false);
		doi.setDotaDateUpdate(new Date());
		doi.setDotaUserUpdate(user.getUserName());
		edit(doi);
	}

}
