package ec.gob.ambiente.enlisy.riskanalysis.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.User;
import ec.gob.ambiente.enlisy.riskanalysis.model.RiskAnalysisApplication;


/**
 * Servicios para la administracion de solicitudes de analisis de riesgo
 * @author EXCO
 *
 */
@Stateless
public class RiskAnalysisApplicationFacade extends AbstractFacade<RiskAnalysisApplication, Integer>{

	public RiskAnalysisApplicationFacade() {
		super(RiskAnalysisApplication.class, Integer.class);		
	}
	
	/**
	 * Guardar solicitud de evaluacion de riesgo
	 * @param riskAnalysisApplication
	 * @param user
	 * @return
	 */
		
	public RiskAnalysisApplication save(RiskAnalysisApplication riskAnalysisApplication, User user) {
		RiskAnalysisApplication application = null;
		try {
			if (riskAnalysisApplication.getRiaaId() == null) {

				riskAnalysisApplication.setRiaaStatus(true);
				riskAnalysisApplication.setRiaaDateCreate(new Date());
				riskAnalysisApplication.setRiaaUserCreate(user.getUserName());
				application = create(riskAnalysisApplication);

			} else {
				riskAnalysisApplication.setRiaaDateUpdate(new Date());
				riskAnalysisApplication.setRiaaUserUpdate(user.getUserName());
				application = edit(riskAnalysisApplication);
			}

			return application;
		} catch (Exception ex) {
			return application;
		}
	}
	
	/**
	 * Buscar una solicitud de analisis de riesgo por numero de tramite
	 */
	public RiskAnalysisApplication findByCode(String code) {
		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM RiskAnalysisApplication o where o.riaaCode= :codigo and o.riaaStatus = true");
			query.setParameter("codigo", code);

			RiskAnalysisApplication result = (RiskAnalysisApplication) query.getSingleResult();
			if (result != null)
				return result;

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}
	
	/**
	 * Buscar solicitudes finalizadas
	 * @param user
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<RiskAnalysisApplication> findNyUserFinalize(User user)
	{
		try {
			Query query = super.getEntityManager().createQuery(
					"SELECT o FROM RiskAnalysisApplication o where o.user= :usuario and o.riaaStatus = true and riaaStatusApplication= :estado");
			query.setParameter("usuario", user);
			query.setParameter("estado", "FINALIZADO");

			List<RiskAnalysisApplication> result = (List<RiskAnalysisApplication>) query.getResultList();
			if (!result.isEmpty())
				return result;

		} catch (NoResultException e) {
			return null;
		}
		return null;
	}
}
