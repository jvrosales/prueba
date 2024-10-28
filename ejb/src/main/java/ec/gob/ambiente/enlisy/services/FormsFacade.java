package ec.gob.ambiente.enlisy.services;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.Forms;


@Stateless
public class FormsFacade extends AbstractFacade<Forms, Integer> implements Serializable {
	
	private static final long serialVersionUID = -4594424897085245484L;

	public FormsFacade() {
		super(Forms.class, Integer.class);		
	}
	
	public Forms findByParameters(Integer capaId,Boolean administrator,Boolean people) {
		try{
			TypedQuery<Forms> query = super.getEntityManager().createQuery(
					"select u from Forms u " + "where u.categoryPatent.id=:capaId and administrator=:administrator and people=:people  ", Forms.class);
			query.setParameter("capaId", capaId);
			query.setParameter("administrator", administrator);
			query.setParameter("people", people);
			return query.getSingleResult();
		}catch(NoResultException ex){
			System.out.println("sin resultados");
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	

	

}
