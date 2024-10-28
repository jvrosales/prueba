package ec.gob.ambiente.enlisy.services;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.PatentCost;
import ec.gob.ambiente.enlisy.model.User;

@Stateless
public class PatentCostFacade  extends AbstractFacade<PatentCost, Integer> implements Serializable {

	private static final long serialVersionUID = -4594424897085245484L;
	
	public PatentCostFacade() {	
		super(PatentCost.class, Integer.class);	
		
	}
	
	
	public PatentCost findById(Integer id) {
		TypedQuery<PatentCost> query = super.getEntityManager()
				.createQuery("select u from PatentCost u " + "where u.id = :id ", PatentCost.class);
		query.setParameter("id", id);

		return query.getSingleResult();
	}
	
	public boolean saveUpdate(PatentCost patentCost, User user ) {
		try
		{			
			if(patentCost.getId() == null)
			{
				patentCost.setCovaStatus(true);
				patentCost.setCovaCreationDate(new Date());
				patentCost.setCovaCreatorUser(user.getUserName());
				create(patentCost);
			}
			else
			{
				patentCost.setCovaDateUpdate(new Date());
				patentCost.setCovaUserUpdate(user.getUserName());
				edit(patentCost);
			}
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	
	public PatentCost findByParent(Integer id) {

		TypedQuery<PatentCost> query = super.getEntityManager().createQuery(
				"select u from PatentCost u " + "where u.patentRequest.id = :id ", PatentCost.class);
		query.setParameter("id", id);

		PatentCost patentCost = new PatentCost();
		if (query.getResultList().size() > 0) {
			patentCost = query.getResultList().get(0);
		}
		return patentCost;

	}
	
	@SuppressWarnings("unchecked")
	public List<PatentCost> findLikeParent(Integer id)
	{
		try {
			Query query = super.getEntityManager().createQuery("select o from PatentCost o where o.patentRequest.id= :patentRequest and o.covaStatus = true order by 1");
			query.setParameter("patentRequest", id);
						List<PatentCost> catalogs = (List<PatentCost>) query.getResultList();	
			
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
