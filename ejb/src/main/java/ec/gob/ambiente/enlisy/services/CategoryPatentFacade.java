package ec.gob.ambiente.enlisy.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.CategoryPatent;


@Stateless
public class CategoryPatentFacade extends AbstractFacade<CategoryPatent, Integer> implements Serializable {
	
	private static final long serialVersionUID = -4594424897085245484L;

	public CategoryPatentFacade() {
		super(CategoryPatent.class, Integer.class);		
	}
	
	/**
	 * Actualizar datos de perfil de usuario
	 * @param user
	 */

	public CategoryPatent findByCode(String code)
	{
		TypedQuery<CategoryPatent> query = super.getEntityManager().createQuery("select u from CategoryPatent u "
				+ "where u.code = :code and u.status = true", CategoryPatent.class);
		query.setParameter("code", code);
		
		CategoryPatent categoryPatent = new CategoryPatent();
		if(query.getResultList().size() > 0)
		{
			categoryPatent = query.getResultList().get(0);
		}
		return categoryPatent;
	}
	
	public CategoryPatent findById(Integer integer)
	{
		TypedQuery<CategoryPatent> query = super.getEntityManager().createQuery("select u from CategoryPatent u "
				+ "where u.capaId = :capaId and u.status = true", CategoryPatent.class);
		query.setParameter("capaId", integer);
		
		CategoryPatent categoryPatent = new CategoryPatent();
		if(query.getResultList().size() > 0)
		{
			categoryPatent = query.getResultList().get(0);
		}
		return categoryPatent;
	}
	
	public List<CategoryPatent> findByAcronym()
	{
		TypedQuery<CategoryPatent> query = super.getEntityManager().createQuery("select u from CategoryPatent u "
				+ "where u.acronym = :acronym and u.status = true", CategoryPatent.class);
		query.setParameter("acronym", true);
		
		List<CategoryPatent> listaCategoryPatent = new ArrayList<CategoryPatent>();
		if(query.getResultList().size() > 0)
		{
			listaCategoryPatent = query.getResultList();
		}
		return listaCategoryPatent;
	}
	
	@SuppressWarnings("unchecked")
	public List<CategoryPatent> findbyAll() {
		try {
			Query query = super.getEntityManager()
					.createQuery("SELECT o FROM CategoryPatent o where o.status = true");

			List<CategoryPatent> result = (List<CategoryPatent>) query.getResultList();
			if (result.size() > 0)
				return result;

		} catch (NoResultException e) {
			e.printStackTrace();
		}

		return null;

	}


}