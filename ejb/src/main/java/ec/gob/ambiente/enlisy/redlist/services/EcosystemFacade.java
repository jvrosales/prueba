package ec.gob.ambiente.enlisy.redlist.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jfree.util.Log;

import ec.gob.ambiente.enlisy.dao.AbstractFacadeModel;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.redlist.model.Ecosystem; 
 
@Stateless
public class EcosystemFacade extends AbstractFacadeModel<Ecosystem> {

	private static final long serialVersionUID = 1L;
	
	public EcosystemFacade() {
		super(Ecosystem.class);
	}
	
	@Override
	public void configure(String filter) {
		initialize();
		addOrderByPredicate(getCriteriaBuilder().desc(getRecord().get("ecosName")));
	}

	public void save(Ecosystem ecosystem, User user) {
		try {
			if (ecosystem.getEcosId() == null || ecosystem.getEcosId().compareTo(Integer.valueOf(0)) == 0) {
				ecosystem.setEcosStatus(true);
				ecosystem.setEcosUserCreate(user.getUserName());
				ecosystem.setEcosDateCreate(new Date());
				getEntityManager().persist(ecosystem);

			} else {
				ecosystem.setEcosDateUpdate(new Date());
				ecosystem.setEcosUserUpdate(user.getUserName());
				getEntityManager().merge(ecosystem);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Ecosystem> findByFilter(String text, int firstResult, int maxResults) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT E FROM Ecosystem E ");
			if (text != null && !text.equals("")) {
				sql.append("WHERE UPPER(E.ecosName) LIKE '%" + text.toUpperCase() + "%' ");
			}
			sql.append("ORDER BY 1 ");
			Query query = getEntityManager().createQuery(sql.toString());
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResults);
			return query.getResultList();
		}
		catch (Exception e) {
			Log.error(e.getMessage());
			return null;
		}
	}
	
	public Long countByFilter(String text) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT coalesce(count(*),0) FROM Ecosystem E ");
			if (text != null && !text.equals("")) {
				sql.append("WHERE UPPER(E.ecosName) LIKE '%" + text.toUpperCase() + "%' ");
			}
			Query query = getEntityManager().createQuery(sql.toString());
			Long result = (Long) query.getSingleResult();
			if (result != null) {
				return result;
			}
		} catch (NoResultException e) {
			return Long.valueOf(0);
		}
		return Long.valueOf(0);
	}
}
