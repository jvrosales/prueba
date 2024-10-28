package ec.gob.ambiente.enlisy.redlist.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacadeModel;
import ec.gob.ambiente.enlisy.redlist.model.RedListsUicn;

@Stateless
public class RedListsUicnFacade extends AbstractFacadeModel<RedListsUicn>{

	private static final long serialVersionUID = 1L;
	
	public RedListsUicnFacade() {
		super(RedListsUicn.class);
	}
	
	@Override
	public void configure(String filter) {
		initialize();
	}

	/**
	 * Buscar listas rojas uicn
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<RedListsUicn> findLists()
	{
		try
		{
			Query query = super.getEntityManager().createQuery("SELECT o FROM RedListsUicn o where o.rluiStatus = true");
			List<RedListsUicn> result=(List<RedListsUicn>)query.getResultList();
			if(result.size()>0)
				return result;
			
		}catch(NoResultException e)
		{
			return new ArrayList<RedListsUicn>();
		}
		return new ArrayList<RedListsUicn>();
	}
}