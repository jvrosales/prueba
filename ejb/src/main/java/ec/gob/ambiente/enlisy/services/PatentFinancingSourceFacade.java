package ec.gob.ambiente.enlisy.services;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.PatentFinancingSource;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class PatentFinancingSourceFacade extends AbstractFacade<PatentFinancingSource, Integer>
		implements Serializable {
	private static final long serialVersionUID = -4594424897085245484L;

	public PatentFinancingSourceFacade() {
		super(PatentFinancingSource.class, Integer.class);
	}

	public PatentFinancingSource findById(Integer id) {
		TypedQuery<PatentFinancingSource> query = super.getEntityManager().createQuery(
				"select u from PatentFinancingSource u " + "where u.id = :id ", PatentFinancingSource.class);
		query.setParameter("id", id);

		return query.getSingleResult();
	}

	public boolean saveUpdate(PatentFinancingSource patentFinancingSource, User user) {
		try {
			if (patentFinancingSource.getId() == null) {
				patentFinancingSource.setFisoStatus(true);
				patentFinancingSource.setFisoCreationDate(new Date());
				patentFinancingSource.setFisoCreatorUser(user.getUserName());
				create(patentFinancingSource);
			} else {
				patentFinancingSource.setFisoDateUpdate(new Date());
				patentFinancingSource.setFisoUserUpdate(user.getUserName());
				edit(patentFinancingSource);
			}
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<PatentFinancingSource> findLikeParent(Integer id)
	{
		try {
			Query query = super.getEntityManager().createQuery("select o from PatentFinancingSource o where o.patentRequest.id= :patentRequest and o.fisoStatus = true order by 1");
			query.setParameter("patentRequest", id);
						List<PatentFinancingSource> catalogs = (List<PatentFinancingSource>) query.getResultList();	
			
			if(catalogs.size() > 0)
			{					
				return catalogs;
			}
		}catch (NoResultException e) {
			e.printStackTrace();
		}
				
		return null;
		
	}

}
