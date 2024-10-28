package ec.gob.ambiente.enlisy.services;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.SurveyResponse;

@Stateless
public class SurveyResponseFacade extends AbstractFacade<SurveyResponse, Integer> implements Serializable{

	private static final long serialVersionUID = 1L;

	public SurveyResponseFacade() {
		super(SurveyResponse.class, Integer.class);
	}	
	
	@SuppressWarnings("unchecked")
	public List<SurveyResponse> findByTramite(String tramite){
		try {
			Query query = getEntityManager().createQuery("select s from SurveyResponse s where s.srvrspProject = :tramite and s.srvrspStatus = true");
			query.setParameter("tramite", tramite);
			
			List<SurveyResponse> result = (List<SurveyResponse>) query.getResultList();
			
			if(result != null && !result.isEmpty())
				return result;
			else 
				return null;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
