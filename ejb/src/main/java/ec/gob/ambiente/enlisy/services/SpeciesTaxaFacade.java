package ec.gob.ambiente.enlisy.services;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.SpeciesTaxa;
import ec.gob.ambiente.suia.crud.service.CrudServiceBean;

@Stateless
public class SpeciesTaxaFacade extends AbstractFacade<SpeciesTaxa, Integer> implements Serializable {
	private static final long serialVersionUID = -4594424897085245484L;

	@EJB
	private CrudServiceBean crudServiceBean;

	public SpeciesTaxaFacade() {
		super(SpeciesTaxa.class, Integer.class);
	}

	public SpeciesTaxa findByName(String nombre) {
		TypedQuery<SpeciesTaxa> query = super.getEntityManager().createQuery(
				"select u from SpeciesTaxa u " + "where u.sptaScientificName = :nombre ", SpeciesTaxa.class);
		query.setParameter("nombre", nombre);

		SpeciesTaxa speciesTaxa = new SpeciesTaxa();
		if (query.getResultList().size() > 0) {
			speciesTaxa = query.getResultList().get(0);
		} else {
			return null;
		}
		return speciesTaxa;
	}

	public SpeciesTaxa buscarporRango(String especie, Integer rango) {
		try {
			TypedQuery<SpeciesTaxa> query = super.getEntityManager().createQuery(
					"select o from SpeciesTaxa o where upper(o.sptaScientificName) like :especie and o.taraId = :rango and  o.sptaStatus = true order by 1 asc",
//					"select o from SpeciesTaxa o where upper(o.sptaScientificName) = :especie and o.taraId = :rango and  o.sptaStatus = true",
					SpeciesTaxa.class);
//			query.setParameter("especie", "%"+especie.toUpperCase()+"%");
			query.setParameter("especie", especie.toUpperCase());
			query.setParameter("rango", rango);
			SpeciesTaxa speciesTaxa = new SpeciesTaxa();
			if (query.getResultList().size() > 0) {
				speciesTaxa = query.getResultList().get(0);
			}
			return speciesTaxa;
		} catch (NoResultException e) {
			return null;
		}
	}
}
