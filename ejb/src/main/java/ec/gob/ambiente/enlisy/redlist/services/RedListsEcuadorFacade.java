package ec.gob.ambiente.enlisy.redlist.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacadeModel;
import ec.gob.ambiente.enlisy.redlist.model.RedListsEcuador;

@Stateless
public class RedListsEcuadorFacade extends AbstractFacadeModel<RedListsEcuador> {

	private static final long serialVersionUID = 1L;

	public RedListsEcuadorFacade() {
		super(RedListsEcuador.class);
	}

	@Override
	public void configure(String filter) {
		initialize();
	}

	/**
	 * Buscar listas rojas ecuador
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<RedListsEcuador> findLists() {
		try {
			Query query = super.getEntityManager()
					.createQuery("SELECT o FROM RedListsEcuador o where o.rlecStatus = true");
			List<RedListsEcuador> result = (List<RedListsEcuador>) query.getResultList();
			if (result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return new ArrayList<RedListsEcuador>();
		}
		return new ArrayList<RedListsEcuador>();
	}

	@SuppressWarnings("unchecked")
	public List<RedListsEcuador> findForSpecies() {
		try {
			Query query = super.getEntityManager()
					.createQuery("SELECT o FROM RedListsEcuador o where o.rlecStatus = true "
							+ " and o.rlecApliesSpecies=true ORDER BY o.rlecName ");
			List<RedListsEcuador> result = (List<RedListsEcuador>) query.getResultList();
			if (result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return new ArrayList<RedListsEcuador>();
		}
		return new ArrayList<RedListsEcuador>();
	}
	
	@SuppressWarnings("unchecked")
	public List<RedListsEcuador> findForEcosystems() {
		try {
			Query query = super.getEntityManager()
					.createQuery("SELECT o FROM RedListsEcuador o where o.rlecStatus = true "
							+ " and o.rlecApliesEcosystems=true ORDER BY o.rlecName ");
			List<RedListsEcuador> result = (List<RedListsEcuador>) query.getResultList();
			if (result.size() > 0)
				return result;

		} catch (NoResultException e) {
			return new ArrayList<RedListsEcuador>();
		}
		return new ArrayList<RedListsEcuador>();
	}
}
