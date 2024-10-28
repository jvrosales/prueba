package ec.gob.ambiente.enlisy.riskanalysis.services;

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.riskanalysis.model.EraRiskAnalysisApplication;


/**
 * Servicios para la administracion de los valores de la matriz era
 * de una solicitud de evaluacion de riesgo
 * @author EXCO
 *
 */
@Stateless
public class EraRiskAnalysisApplicationFacade extends AbstractFacade<EraRiskAnalysisApplication, Integer>{

	public EraRiskAnalysisApplicationFacade() {
		super(EraRiskAnalysisApplication.class, Integer.class);		
	}
	
	/**
	 * Guardar valores de una matriz de era
	 * @param eraRiskAnalysisApplication
	 * @param user
	 * @return
	 */
		
	public EraRiskAnalysisApplication save(EraRiskAnalysisApplication eraRiskAnalysisApplication, User user) {
		EraRiskAnalysisApplication era = null;
		try {
			if (eraRiskAnalysisApplication.getEraaId() == null) {

				eraRiskAnalysisApplication.setEraaStatus(true);
				eraRiskAnalysisApplication.setEraaDateCreate(new Date());
				eraRiskAnalysisApplication.setEraaUserCreate(user.getUserName());
				era = create(eraRiskAnalysisApplication);

			} else {
				eraRiskAnalysisApplication.setEraaDateUpdate(new Date());
				eraRiskAnalysisApplication.setEraaUserUpdate(user.getUserName());
				era = edit(eraRiskAnalysisApplication);
			}

			return era;
		} catch (Exception ex) {
			return era;
		}
	}
	
	/**
	 * Buscar matriz de era por tramite
	 */
	public EraRiskAnalysisApplication findBySolicitud(Integer code) {
		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM EraRiskAnalysisApplication o where o.riaaId.riaaId= :codigo and o.eraaStatus = true");
			query.setParameter("codigo", code);

			EraRiskAnalysisApplication result = (EraRiskAnalysisApplication) query.getSingleResult();
			if (result != null)
				return result;

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}
}
